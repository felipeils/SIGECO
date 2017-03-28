/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.sigeco.Persistencia;

import cl.sigeco.Entidades.Proveedor;
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
public class ProveedorDAO implements Serializable, ICrud {
    
    @Resource(mappedName = "jdbc/sigeco")
    private final Connection con;
    
    public ProveedorDAO() throws SQLException, IOException, ClassNotFoundException {
        DAOManager manager = new DAOManager();
        manager.init();
        this.con = manager.open();
    }
    
    public Proveedor get( int id ) {
        Proveedor proveedor = new Proveedor();

        try {
            String query = "SELECT proveedor.*, proveedor.nombre as nom_proveedor " +
                "FROM proveedor " +
                "LEFT JOIN proveedor status ON proveedor.proveedor = status.id_estado " +
                "WHERE estado.id_estado = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rst = ps.executeQuery();
            while (rst.next()) {
                proveedor.setIdProveedor(rst.getInt("id_provedor"));
                proveedor.setNombre(rst.getString("nombre"));
                proveedor.setRut(rst.getInt("rut"));
                proveedor.setDirección(rst.getString("direccion"));
                proveedor.setEmail(rst.getString("email"));
                proveedor.setCreado(rst.getDate("creado"));
                proveedor.setModificado(rst.getDate("modificado"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CondominioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return proveedor;
    }
    
    @Override
    public boolean agregar(Object objetoInsert) {
        Proveedor proveedor = (Proveedor) objetoInsert;
        try {
            String query = "INSERT INTO proveedor values (?,?,?,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP)";
            PreparedStatement ps = con.prepareStatement(query);
            
            ps.setString(1, proveedor.getNombre());
            ps.setInt(2, proveedor.getRut());
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
    public List<Proveedor> buscarTodos() throws SQLException, IOException {
        List<Proveedor> lista = new ArrayList();
        
        try (ResultSet rst = fetch("SELECT proveedor.*, status.nombre as nom_estado FROM proveedor " +
                "LEFT JOIN proveedor status ON proveedor.proveedor = status.id_proveedor ")) {
            while (rst.next()) {
                Proveedor proveedor = new Proveedor();
                proveedor.setIdProveedor(rst.getInt("id_proveedor"));
                proveedor.setNombre(rst.getString("nombre"));
                proveedor.setNomEstado(rst.getString("nom_estado"));
               // proveedor.set(rst.getInt("estado"));
                lista.add(proveedor);
            }
        }
        return lista;
    }
    
    public List<Proveedor> getSelect() throws SQLException, IOException {
        List<Proveedor> lista = new ArrayList();
        
        try (ResultSet rst = fetch("SELECT * FROM Proveedor WHERE estado = 1")) {
            while (rst.next()) {
                Proveedor proveedor = new Proveedor();
                proveedor.setIdProveedor(rst.getInt("id_proveedor"));
                proveedor.setNombre(rst.getString("nombre"));
                lista.add(proveedor);
            }
        }
        return lista;
    }

    @Override
    public boolean modificar(Object objetoUpdate) {
        Proveedor proveedor = (Proveedor) objetoUpdate;
        try {
            String query = "UPDATE proveedor SET " +
                    "nombre= ?, " +
                    "estado= ?, " +
                    "modificado= CURRENT_TIMESTAMP " +
                    "WHERE id_proveedor = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, proveedor.getNombre());
            ps.setString(2, proveedor.getNomEstado());
            ps.setInt(3, proveedor.getIdProveedor());
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
            String query = "UPDATE proveedor SET proveedor = ? WHERE id_proveedor = ?";
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
