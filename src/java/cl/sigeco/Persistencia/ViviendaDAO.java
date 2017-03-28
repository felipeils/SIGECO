/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.sigeco.Persistencia;

import cl.sigeco.Entidades.Vivienda;
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
public class ViviendaDAO implements Serializable, ICrud {

    @Resource(mappedName = "jdbc/sigeco")
    private final Connection con;
    
    public ViviendaDAO() throws SQLException, IOException, ClassNotFoundException {
        DAOManager manager = new DAOManager();
        manager.init();
        this.con = manager.open();
    }
    
    public Vivienda get( int id ) {
        Vivienda vivienda = new Vivienda();

        try {
            String query = "SELECT vivienda.*, estado.nombre as nom_estado " +
                "FROM vivienda " +
                "LEFT JOIN estado ON vivienda.estado = estado.id_estado " +
                "WHERE vivienda.id_vivienda = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rst = ps.executeQuery();
            while (rst.next()) {
                vivienda.setIdVivienda(rst.getInt("id_vivienda"));
                vivienda.setNumero(rst.getInt("numero"));
                vivienda.setPiso(rst.getInt("piso"));
                vivienda.setSector(rst.getString("sector"));
                vivienda.setMetrosCuadrados(rst.getInt("metros_cuadrados"));
                vivienda.setIdCondominio(rst.getInt("id_condominio"));
                vivienda.setIdTipoVivienda(rst.getInt("id_tipo_vivienda"));
                vivienda.setEstado(rst.getInt("estado"));
                vivienda.setNomEstado(rst.getString("nom_estado"));
                vivienda.setCreado(rst.getDate("creado"));
                vivienda.setModificado(rst.getDate("modificado"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CondominioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vivienda;
    }

    @Override
    public boolean agregar(Object objetoInsert) {
        Vivienda vivienda = (Vivienda) objetoInsert;
        try {
            String query = "INSERT INTO vivienda values (?,?,?,?,?,?,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,?,?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, ultimoID());
            ps.setInt(2, vivienda.getNumero());
            ps.setInt(3, vivienda.getPiso());
            ps.setInt(4, vivienda.getMetrosCuadrados());
            ps.setInt(5, vivienda.getEstado());
            ps.setString(6, vivienda.getSector());
            ps.setInt(7, vivienda.getIdCondominio());
            ps.setInt(8, vivienda.getIdTipoVivienda());
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
        List<Vivienda> lista = new ArrayList();

        try (ResultSet rst = fetch("SELECT vivienda.*, estado.nombre as nom_estado, condominio.nombre as nom_condominio, tipo_vivienda.nombre as nom_tipo_vivienda " +
                "FROM vivienda " +
                "LEFT JOIN estado ON vivienda.estado = estado.id_estado " +
                "LEFT JOIN condominio ON vivienda.id_condominio = condominio.id_condominio " +
                "LEFT JOIN tipo_vivienda ON vivienda.id_tipo_vivienda = tipo_vivienda.id_tipo_vivienda")) {
            while (rst.next()) {
                Vivienda vivienda = new Vivienda();
                vivienda.setIdVivienda(rst.getInt("id_vivienda"));
                vivienda.setNumero(rst.getInt("numero"));
                vivienda.setPiso(rst.getInt("piso"));
                vivienda.setSector(rst.getString("sector"));
                vivienda.setMetrosCuadrados(rst.getInt("metros_cuadrados"));
                vivienda.setIdCondominio(rst.getInt("id_condominio"));
                vivienda.setNomCondominio(rst.getString("nom_condominio"));
                vivienda.setIdTipoVivienda(rst.getInt("id_tipo_vivienda"));
                vivienda.setNomTipoVivienda(rst.getString("nom_tipo_vivienda"));
                vivienda.setEstado(rst.getInt("estado"));
                vivienda.setNomEstado(rst.getString("nom_estado"));
                vivienda.setCreado(rst.getDate("creado"));
                vivienda.setModificado(rst.getDate("modificado"));
                lista.add(vivienda);
            }
        }
        return lista;
    }
    
    public List getSelect() throws SQLException, IOException {
        List<Vivienda> lista = new ArrayList();

        try (ResultSet rst = fetch("SELECT id_vivienda, nombre " +
                "FROM vivienda " +
                "WHERE estado = 1")) {
            while (rst.next()) {
                Vivienda vivienda = new Vivienda();
                vivienda.setIdVivienda(rst.getInt("id_vivienda"));
                vivienda.setNumero(rst.getInt("numero"));
                vivienda.setPiso(rst.getInt("piso"));
                vivienda.setSector(rst.getString("sector"));
                lista.add(vivienda);
            }
        }
        return lista;
    }

    @Override
    public boolean modificar(Object objetoUpdate) {
        Vivienda vivienda = (Vivienda) objetoUpdate;
        try {
            String query = "UPDATE vivienda SET " +
                    "numero= ?, " +
                    "piso= ?, " +
                    "metros_cuadrados= ?, " +
                    "sector= ?, " +
                    "id_condominio= ?, " +
                    "id_tipo_vivienda= ?, " +
                    "estado= ?, " +
                    "modificado= CURRENT_TIMESTAMP " +
                    "WHERE id_vivienda = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, vivienda.getNumero());
            ps.setInt(2, vivienda.getPiso());
            ps.setInt(3, vivienda.getMetrosCuadrados());
            ps.setString(4, vivienda.getSector());
            ps.setInt(5, vivienda.getIdCondominio());
            ps.setInt(6, vivienda.getIdTipoVivienda());
            ps.setInt(7, vivienda.getEstado());
            ps.setInt(8, vivienda.getIdVivienda());
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
            String query = "UPDATE vivienda SET estado = ? WHERE id_vivienda = ?";
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
        try (ResultSet rst = fetch("SELECT MAX(id_vivienda) FROM vivienda")) {
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
