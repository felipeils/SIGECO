/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.sigeco.Persistencia;

import cl.sigeco.Entidades.TipoVivienda;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;

/**
 *
 * @author Joe-Xidu
 */
public class TipoViviendaDAO implements Serializable, ICrud {

    @Resource(mappedName = "jdbc/sigeco")
    private final Connection con;
    
    public TipoViviendaDAO() throws SQLException, IOException, ClassNotFoundException {
        DAOManager manager = new DAOManager();
        manager.init();
        this.con = manager.open();
    }
    
    public TipoVivienda getTipoVivienda( int id ) {
        TipoVivienda tipoVivienda = new TipoVivienda();

        try {
            String query = "SELECT tipo_vivienda.*, estado.nombre as nom_estado " +
                "FROM tipo_vivienda " +
                "LEFT JOIN estado ON tipo_vivienda.estado = estado.id_estado " +
                "WHERE tipo_vivienda.id_tipo_vivienda = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rst = ps.executeQuery();
            while (rst.next()) {
                tipoVivienda.setIdTipoVivienda(rst.getInt("id_tipo_vivienda"));
                tipoVivienda.setParticipacion(rst.getDouble("participacion"));
                tipoVivienda.setNombre(rst.getString("nombre"));
                tipoVivienda.setEstado(rst.getInt("estado"));
                tipoVivienda.setNomEstado(rst.getString("nom_estado"));
                tipoVivienda.setCreado(rst.getDate("creado"));
                tipoVivienda.setModificado(rst.getDate("modificado"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CondominioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tipoVivienda;
    }

    @Override
    public boolean agregar(Object objetoInsert) {
        TipoVivienda tipoVivienda = (TipoVivienda) objetoInsert;
        try {
            String query = "INSERT INTO tipo_vivienda values (?,?,?,?,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, ultimoID());
            ps.setDouble(2, tipoVivienda.getParticipacion());
            ps.setString(3, tipoVivienda.getNombre());
            ps.setInt(4, tipoVivienda.getEstado());
            try {
                return ps.executeUpdate() == 1;
            } catch (Exception e) {
                System.out.println("(1) error al insertar: " + e.getMessage());
                return false;
            }

        } catch (Exception e) {
            System.out.println("(2) error al insertar: " + e.getMessage());
        }
        return false;
    }

    @Override
    public List buscarTodos() throws SQLException, IOException {
        List<TipoVivienda> lista = new ArrayList();

        try (ResultSet rst = fetch("SELECT tipo_vivienda.*, estado.nombre as nom_estado " +
                "FROM tipo_vivienda " +
                "LEFT JOIN estado ON tipo_vivienda.estado = estado.id_estado")) {
            while (rst.next()) {
                TipoVivienda tipoVivienda = new TipoVivienda();
                tipoVivienda.setIdTipoVivienda(rst.getInt("id_tipo_vivienda"));
                tipoVivienda.setNombre(rst.getString("nombre"));
                tipoVivienda.setEstado(rst.getInt("estado"));
                tipoVivienda.setParticipacion(rst.getDouble("participacion"));
                tipoVivienda.setNomEstado(rst.getString("nom_estado"));
                tipoVivienda.setCreado(rst.getDate("creado"));
                tipoVivienda.setModificado(rst.getDate("modificado"));
                lista.add(tipoVivienda);
            }
        }
        return lista;
    }
    
    public List getSelect() throws SQLException, IOException {
        List<TipoVivienda> lista = new ArrayList();

        try (ResultSet rst = fetch("SELECT id_tipo_vivienda, nombre " +
                "FROM tipo_vivienda " +
                "WHERE estado = 1")) {
            while (rst.next()) {
                TipoVivienda tipoVivienda = new TipoVivienda();
                tipoVivienda.setIdTipoVivienda(rst.getInt("id_tipo_vivienda"));
                tipoVivienda.setNombre(rst.getString("nombre"));
                lista.add(tipoVivienda);
            }
        }
        return lista;
    }

    @Override
    public boolean modificar(Object objetoUpdate) {
        TipoVivienda tipoVivienda = (TipoVivienda) objetoUpdate;
        try {
            String query = "UPDATE tipo_vivienda SET " +
                    "nombre= ?, " +
                    "estado= ?, " +
                    "participacion= ?, " +
                    "modificado= CURRENT_TIMESTAMP " +
                    "WHERE id_tipo_vivienda = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, tipoVivienda.getNombre());
            ps.setInt(2, tipoVivienda.getEstado());
            ps.setDouble(3, tipoVivienda.getParticipacion());
            ps.setInt(4, tipoVivienda.getIdTipoVivienda());
            try {
                return ps.executeUpdate() == 1;
            } catch (Exception e) {
                System.out.println("(1) error al modificar: " + e.getMessage());
                return false;
            }

        } catch (Exception e) {
            System.out.println("(2) error al modificar: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean borrar(int codigo) {
        try {
            String query = "UPDATE tipo_vivienda SET estado = ? WHERE id_tipo_vivienda = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, 2);
            ps.setInt(2, codigo);
            try {
                return ps.executeUpdate() == 1;
            } catch (Exception e) {
                System.out.println("(1) error al borrar: " + e.getMessage());
                return false;
            }

        } catch (Exception e) {
            System.out.println("(2) error al borrar: " + e.getMessage());
        }
        return false;
    }

    @Override
    public int ultimoID() {
        int ultim = 0;
        try (ResultSet rst = fetch("SELECT MAX(id_tipo_vivienda) FROM tipo_vivienda")) {
            while (rst.next()) {
                ultim = rst.getInt(1);
            }
        } catch (SQLException | IOException ex) {
            Logger.getLogger(CondominioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ultim + 1;
    }

    /**
     * Fetch data
     *
     * @throws SQLException
     * @throws IOException
     */
    private ResultSet fetch(String sql) throws SQLException, IOException {
        // create the prepared stmt
        PreparedStatement ps = con.prepareStatement(sql);

        // execute the query
        ResultSet rs = ps.executeQuery();
        //ps.close(); // close prepared statement
        return rs;
    }

    public void close() throws SQLException {
        if (con != null) {
            // close the connection
            con.close();
        }
    }
    
}
