/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.sigeco.Persistencia;

import cl.sigeco.Entidades.Estado;
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
public class EstadoDAO implements Serializable, ICrud {
    
    @Resource(mappedName = "jdbc/sigeco")
    private final Connection con;
    
    public EstadoDAO() throws SQLException, IOException, ClassNotFoundException {
        DAOManager manager = new DAOManager();
        manager.init();
        this.con = manager.open();
    }
    
    public Estado get( int id ) {
        Estado estado = new Estado();

        try {
            String query = "SELECT estado.*, estado.nombre as nom_estado " +
                "FROM estado " +
                "LEFT JOIN estado status ON estado.estado = status.id_estado " +
                "WHERE estado.id_estado = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rst = ps.executeQuery();
            while (rst.next()) {
                estado.setIdEstado(rst.getInt("id_estado"));
                estado.setNombre(rst.getString("nombre"));
                estado.setEstado(rst.getInt("estado"));
                estado.setNomEstado(rst.getString("nom_estado"));
                estado.setCreado(rst.getDate("creado"));
                estado.setModificado(rst.getDate("modificado"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CondominioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return estado;
    }
    
    @Override
    public boolean agregar(Object objetoInsert) {
        Estado estado = (Estado) objetoInsert;
        try {
            String query = "INSERT INTO estado values (?,?,?,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, ultimoID());
            ps.setString(2, estado.getNombre());
            ps.setInt(3, estado.getEstado());
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
    public List<Estado> buscarTodos() throws SQLException, IOException {
        List<Estado> lista = new ArrayList();
        
        try (ResultSet rst = fetch("SELECT estado.*, status.nombre as nom_estado FROM estado " +
                "LEFT JOIN estado status ON estado.estado = status.id_estado ")) {
            while (rst.next()) {
                Estado estado = new Estado();
                estado.setIdEstado(rst.getInt("id_estado"));
                estado.setNombre(rst.getString("nombre"));
                estado.setNomEstado(rst.getString("nom_estado"));
                estado.setEstado(rst.getInt("estado"));
                lista.add(estado);
            }
        }
        return lista;
    }
    
    public List<Estado> getSelect() throws SQLException, IOException {
        List<Estado> lista = new ArrayList();
        
        try (ResultSet rst = fetch("SELECT * FROM Estado WHERE estado = 1")) {
            while (rst.next()) {
                Estado estado = new Estado();
                estado.setIdEstado(rst.getInt("id_estado"));
                estado.setNombre(rst.getString("nombre"));
                lista.add(estado);
            }
        }
        return lista;
    }

    @Override
    public boolean modificar(Object objetoUpdate) {
        Estado estado = (Estado) objetoUpdate;
        try {
            String query = "UPDATE estado SET " +
                    "nombre= ?, " +
                    "estado= ?, " +
                    "modificado= CURRENT_TIMESTAMP " +
                    "WHERE id_estado = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, estado.getNombre());
            ps.setInt(2, estado.getEstado());
            ps.setInt(3, estado.getIdEstado());
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
            String query = "UPDATE estado SET estado = ? WHERE id_estado = ?";
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
        try (ResultSet rst = fetch("SELECT MAX(id_estado) FROM estado")) {
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
