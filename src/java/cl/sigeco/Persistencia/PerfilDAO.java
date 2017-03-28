/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.sigeco.Persistencia;

import cl.sigeco.Entidades.Perfil;
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
public class PerfilDAO implements Serializable, ICrud {

    @Resource(mappedName = "jdbc/sigeco")
    private final Connection con;

    public PerfilDAO() throws SQLException, IOException, ClassNotFoundException {
        DAOManager manager = new DAOManager();
        manager.init();
        this.con = manager.open();
    }
    
    public Perfil getPerfil( int id ) {
        Perfil perfil = new Perfil();

        try {
            String query = "SELECT perfil.*, estado.nombre as nom_estado " +
                "FROM perfil " +
                "LEFT JOIN estado ON perfil.estado = estado.id_estado " +
                "WHERE perfil.id_perfil = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rst = ps.executeQuery();
            while (rst.next()) {
                perfil.setIdPerfil(rst.getInt("id_perfil"));
                perfil.setNombre(rst.getString("nombre"));
                perfil.setEstado(rst.getInt("estado"));
                perfil.setNomEstado(rst.getString("nom_estado"));
                perfil.setCreado(rst.getDate("creado"));
                perfil.setModificado(rst.getDate("modificado"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CondominioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return perfil;
    }
    
    @Override
    public boolean agregar(Object objetoInsert) {
        Perfil perfil = (Perfil) objetoInsert;
        try {
            String query = "INSERT INTO perfil values (?,?,?,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, ultimoID());
            ps.setString(2, perfil.getNombre());
            ps.setInt(3, perfil.getEstado());
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
        List<Perfil> lista = new ArrayList();

        try (ResultSet rst = fetch("SELECT perfil.*, estado.nombre as nom_estado " +
                "FROM perfil " +
                "LEFT JOIN estado ON perfil.estado = estado.id_estado")) {
            while (rst.next()) {
                Perfil perfil = new Perfil();
                perfil.setIdPerfil(rst.getInt("id_perfil"));
                perfil.setNombre(rst.getString("nombre"));
                perfil.setEstado(rst.getInt("estado"));
                perfil.setNomEstado(rst.getString("nom_estado"));
                perfil.setCreado(rst.getDate("creado"));
                perfil.setModificado(rst.getDate("modificado"));
                lista.add(perfil);
            }
        }
        return lista;
    }
    //hacer en daosrvicio
    public List getSelect() throws SQLException, IOException {
        List<Perfil> lista = new ArrayList();

        try (ResultSet rst = fetch("SELECT id_perfil, nombre " +
                "FROM perfil " +
                "WHERE estado = 1")) {
            while (rst.next()) {
                Perfil perfil = new Perfil();
                perfil.setIdPerfil(rst.getInt("id_perfil"));
                perfil.setNombre(rst.getString("nombre"));
                lista.add(perfil);
            }
        }
        return lista;
    }

    @Override
    public boolean modificar(Object objetoUpdate) {
        Perfil perfil = (Perfil) objetoUpdate;
        try {
            String query = "UPDATE perfil SET " +
                    "nombre= ?, " +
                    "estado= ?, " +
                    "modificado= CURRENT_TIMESTAMP " +
                    "WHERE id_perfil = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, perfil.getNombre());
            ps.setInt(2, perfil.getEstado());
            ps.setInt(3, perfil.getIdPerfil());
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
            String query = "UPDATE perfil SET estado = ? WHERE id_perfil = ?";
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
        try (ResultSet rst = fetch("SELECT MAX(id_perfil) FROM perfil")) {
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
