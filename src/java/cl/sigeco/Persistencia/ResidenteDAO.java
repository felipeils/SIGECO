/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.sigeco.Persistencia;

import cl.sigeco.Entidades.Residente;
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
public class ResidenteDAO implements Serializable, ICrud {

    @Resource(mappedName = "jdbc/sigeco")
    private final Connection con;

    public ResidenteDAO() throws SQLException, IOException, ClassNotFoundException {
        DAOManager manager = new DAOManager();
        manager.init();
        this.con = manager.open();
    }

    @Override
    public boolean agregar(Object objetoInsert) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List buscarTodos() throws SQLException, IOException {
        List<Residente> lista = new ArrayList();

        try (ResultSet rst = fetch("SELECT residente.*, estado.nombre as nom_estado " +
                "FROM residente " +
                "LEFT JOIN estado ON residente.estado = estado.id_estado")) {
            while (rst.next()) {
                Residente residente = new Residente();
                residente.setIdResidente(rst.getInt("id_perfil"));
                //residente.setNombre(rst.getString("nombre"));
                residente.setEstado(rst.getInt("estado"));
                residente.setNomEstado(rst.getString("nom_estado"));
                residente.setCreado(rst.getDate("creado"));
                residente.setModificado(rst.getDate("modificado"));
                lista.add(residente);
            }
        }
        return lista;
    }
    
    public List getSelect() throws SQLException, IOException {
        List<Residente> lista = new ArrayList();

        try (ResultSet rst = fetch("SELECT id_residente, propietario " +
                "FROM residente " +
                "WHERE estado = 1")) {
            while (rst.next()) {
                Residente residente = new Residente();
                residente.setIdResidente(rst.getInt("id_residente"));
                residente.setPropietario(rst.getInt("propietario"));
                lista.add(residente);
            }
        }
        return lista;
    }

    @Override
    public boolean modificar(Object objetoUpdate) {
        Residente residente = (Residente) objetoUpdate;
        try {
            String query = "UPDATE residente SET " +
                    "nombre= ?, " +
                    "estado= ?, " +
                    "modificado= CURRENT_TIMESTAMP " +
                    "WHERE id_residente = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, residente.getPropietario());
            ps.setInt(2, residente.getEstado());
            ps.setInt(2, residente.getIdVivienda());
            ps.setInt(2, residente.getIdCuentaResidente());
            ps.setInt(2, residente.getIdPersona());
            ps.setInt(3, residente.getIdResidente());
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
            String query = "UPDATE residente SET estado = ? WHERE id_residente = ?";
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
        try (ResultSet rst = fetch("SELECT MAX(id_residente) FROM residente")) {
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
