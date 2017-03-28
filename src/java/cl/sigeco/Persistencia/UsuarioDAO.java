/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.sigeco.Persistencia;

import cl.sigeco.Entidades.Usuario;
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


public class UsuarioDAO implements Serializable, ICrud {

    @Resource(mappedName = "jdbc/sigeco")
    private final Connection con;

    public UsuarioDAO() throws SQLException, IOException, ClassNotFoundException {
        DAOManager manager = new DAOManager();
        manager.init();
        this.con = manager.open();
    }
    
    public int validaUsuario( String user, String pass ) {
        Integer id = null;

        try {
            String query = "SELECT usuario.id_usuario " +
                "FROM usuario " +
                "WHERE usuario.usuario = ? AND usuario.pass = ? AND estado = 1";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, pass);
            ResultSet rst = ps.executeQuery();
            while (rst.next()) {
                id = rst.getInt("id_usuario");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CondominioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    
    public Usuario getUsuario( int id ) {
        Usuario usuario = new Usuario();

        try {
            String query = "SELECT usuario.*, perfil.nombre as perfil, estado.nombre as nom_estado, condominio.nombre as condominio " +
                "FROM usuario " +
                "LEFT JOIN perfil ON usuario.id_perfil = perfil.id_perfil " +
                "LEFT JOIN condominio ON usuario.id_condominio = condominio.id_condominio " +
                "LEFT JOIN estado ON usuario.estado = estado.id_estado " +
                "WHERE usuario.id_usuario = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rst = ps.executeQuery();
            while (rst.next()) {
                usuario.setIdUsuario(rst.getInt("id_usuario"));
                usuario.setUsuario(rst.getString("usuario"));
                usuario.setPass(rst.getString("pass"));
                usuario.setEmail(rst.getString("email"));
                usuario.setEstado(rst.getInt("estado"));
                usuario.setNomEstado(rst.getString("nom_estado"));
                usuario.setIdPerfil(rst.getInt("id_perfil"));
                usuario.setNomPerfil(rst.getString("perfil"));
                usuario.setCreado(rst.getDate("creado"));
                usuario.setModificado(rst.getDate("modificado"));
                usuario.setIdCondominio(rst.getInt("id_condominio"));
                usuario.setNomCondominio(rst.getString("condominio"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CondominioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuario;
    }
    
    public List<Usuario> getSelect() throws SQLException, IOException {
        List<Usuario> lista = new ArrayList();

        try (ResultSet rst = fetch("SELECT id_usuario, usuario " +
                "FROM usuario " +
                "WHERE estado = 1")) {
            while (rst.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdCondominio(rst.getInt("id_usuario"));
                usuario.setUsuario(rst.getString("usuario"));
                lista.add(usuario);
            }
        }
        return lista;
    }
    
    @Override
    public boolean agregar(Object objetoInsert) {
        Usuario usuario = (Usuario) objetoInsert;
        try {
            boolean isResidente = usuario.getIdResidente() > 0;
            boolean isCondominio = usuario.getIdCondominio() > 0;
            String query = "INSERT INTO usuario values (?,?,?,?,?,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,?" +
                    (isCondominio?",?":",null") +
                    (isResidente?",?":",null") +
                    ")";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, ultimoID());
            ps.setString(2, usuario.getUsuario());
            ps.setString(3, usuario.getPass());
            ps.setString(4, usuario.getEmail());
            ps.setInt(5, usuario.getEstado());
            ps.setInt(6, usuario.getIdPerfil());
            if (isCondominio) ps.setInt(7, usuario.getIdCondominio());
            if (isResidente) ps.setInt(8, usuario.getIdResidente());
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
        List<Usuario> lista = new ArrayList();

        try (ResultSet rst = fetch("SELECT usuario.*, perfil.nombre as perfil, estado.nombre as nom_estado, condominio.nombre as condominio " +
                "FROM usuario " +
                "LEFT JOIN perfil ON usuario.id_perfil = perfil.id_perfil " +
                "LEFT JOIN condominio ON usuario.id_condominio = condominio.id_condominio " +
                "LEFT JOIN estado ON usuario.estado = estado.id_estado")) {
            while (rst.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rst.getInt("id_usuario"));
                usuario.setUsuario(rst.getString("usuario"));
                usuario.setPass(rst.getString("pass"));
                usuario.setEmail(rst.getString("email"));
                usuario.setEstado(rst.getInt("estado"));
                usuario.setNomEstado(rst.getString("nom_estado"));
                usuario.setIdPerfil(rst.getInt("id_perfil"));
                usuario.setNomPerfil(rst.getString("perfil"));
                usuario.setCreado(rst.getDate("creado"));
                usuario.setModificado(rst.getDate("modificado"));
                usuario.setIdCondominio(rst.getInt("id_condominio"));
                usuario.setNomCondominio(rst.getString("condominio"));
                usuario.setIdResidente(rst.getInt("id_residente"));
                lista.add(usuario);
            }
        }
        return lista;
    }

    @Override
    public boolean modificar(Object objetoUpdate) {
        Usuario usuario = (Usuario) objetoUpdate;
        try {
            boolean isResidente = usuario.getIdResidente() > 0;
            boolean isCondominio = usuario.getIdCondominio() > 0;
            int i = 1;
            String query = "UPDATE usuario SET " +
                    "usuario= ?, " +
                    "pass= ?, " +
                    "email= ?, " +
                    "estado= ?, " +
                    "modificado= CURRENT_TIMESTAMP, " +
                    "id_perfil= ? " +
                    (isCondominio?",id_condominio= ? ":"") +
                    (isResidente?",id_residente= ? ":"") +
                    "WHERE id_usuario = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(i++, usuario.getUsuario());
            ps.setString(i++, usuario.getPass());
            ps.setString(i++, usuario.getEmail());
            ps.setInt(i++, usuario.getEstado());
            ps.setInt(i++, usuario.getIdPerfil());
            if (isCondominio) ps.setInt(i++, usuario.getIdCondominio());
            if (isResidente) ps.setInt(i++, usuario.getIdResidente());
            ps.setInt(i++, usuario.getIdUsuario());
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
            String query = "UPDATE usuario SET estado = ? WHERE id_usuario = ?";
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
        try (ResultSet rst = fetch("SELECT MAX(id_usuario) FROM usuario")) {
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
