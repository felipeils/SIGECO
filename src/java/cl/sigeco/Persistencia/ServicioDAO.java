/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.sigeco.Persistencia;
import cl.sigeco.Entidades.Servicio;
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
public class ServicioDAO implements Serializable, ICrud {

    @Resource(mappedName = "jdbc/sigeco")
    private final Connection con;
    
    public ServicioDAO() throws SQLException, IOException, ClassNotFoundException {
        DAOManager manager = new DAOManager();
        manager.init();
        this.con = manager.open();
    }
    
    public Servicio getServicio( int id ) {
        Servicio servicio = new Servicio();

        try {
            String query = "SELECT servicio.*, estado.nombre as nom_estado, condominio.nombre as nom_condominio " +
                "FROM servicio " +
                "LEFT JOIN estado ON servicio.estado = estado.id_estado " +
                "LEFT JOIN condominio ON servicio.id_condominio = condominio.id_condominio " +
                "WHERE servicio.id_servicio = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rst = ps.executeQuery();
            while (rst.next()) {
                servicio.setIdServicio(rst.getInt("id_servicio"));
                servicio.setNombre(rst.getString("nombre"));
                servicio.setDetalle(rst.getString("detalle"));
                servicio.setValorBase(rst.getInt("valor_base"));
                servicio.setEstado(rst.getInt("estado"));
                servicio.setCreado(rst.getDate("creado"));
                servicio.setModificado(rst.getDate("modificado"));
                servicio.setIdCondominio(rst.getInt("id_condominio"));
                servicio.setNomCondominio(rst.getString("nom_condominio"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return servicio;
    }
    
    @Override
    public boolean agregar(Object objetoInsert) {
        Servicio servicio = (Servicio) objetoInsert;
        try {
            String query = "INSERT INTO servicio values (?,?,?,?,',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, ultimoID());
            ps.setString(2, servicio.getNombre());
            ps.setString(3, servicio.getDetalle());
            ps.setInt(4, servicio.getValorBase());
            ps.setInt(5, servicio.getEstado());

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean modificar(Object objetoUpdate) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean borrar(int codigo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int ultimoID() {
        int ultim = 0;
        try (ResultSet rst = fetch("SELECT MAX(id_servicio) FROM servicio")) {
            while (rst.next()) {
                ultim = rst.getInt(1);
            }
        } catch (SQLException | IOException ex) {
            Logger.getLogger(CondominioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ultim + 1;
    }
    
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
