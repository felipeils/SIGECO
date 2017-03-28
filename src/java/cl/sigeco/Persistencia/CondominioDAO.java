/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.sigeco.Persistencia;

import cl.sigeco.Entidades.Condominio;
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
 * @author José
 */
public class CondominioDAO implements Serializable, ICrud {

    @Resource(mappedName = "jdbc/sigeco")
    private Connection con;

    public CondominioDAO() throws SQLException, IOException, ClassNotFoundException {
        DAOManager manager = new DAOManager();
        manager.init();
        this.con = manager.open();
    }
    
    public Condominio getCondominio( int id ) {
        Condominio condominio = new Condominio();

        try {
            String query = "SELECT condominio.*, comuna.nombre as comuna, estado.nombre as nom_estado, usuario.usuario as representante " +
                "FROM Condominio " +
                "LEFT JOIN comuna ON condominio.id_comuna = comuna.id_comuna " +
                "LEFT JOIN cuenta_condominio ON condominio.id_cuenta_condominio = cuenta_condominio.id_cuenta_condominio " +
                "LEFT JOIN usuario ON condominio.representante_id = usuario.id_usuario " +
                "LEFT JOIN estado ON condominio.estado = estado.id_estado " +
                "WHERE condominio.id_condominio = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rst = ps.executeQuery();
            while (rst.next()) {
                condominio.setIdCondominio(rst.getInt("id_condominio"));
                condominio.setNombre(rst.getString("nombre"));
                condominio.setDireccion(rst.getString("direccion"));
                condominio.setEstado(rst.getInt("estado"));
                condominio.setNomEstado(rst.getString("nom_estado"));
                condominio.setRepresentanteId(rst.getInt("representante_id"));
                condominio.setCreado(rst.getDate("creado"));
                condominio.setModificado(rst.getDate("modificado"));
                condominio.setIdComuna(rst.getInt("id_comuna"));
                condominio.setComuna(rst.getString("comuna"));
                condominio.setIdCuentaCondominio(rst.getInt("id_cuenta_condominio"));
                condominio.setRepresentante(rst.getString("representante"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CondominioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return condominio;
    }

    @Override
    public List<Condominio> buscarTodos() throws SQLException, IOException {
        List<Condominio> lista = new ArrayList();

        try (ResultSet rst = fetch("SELECT condominio.*, Comuna.nombre as comuna, Estado.nombre as nom_estado, usuario.usuario as representante " +
                "FROM Condominio " +
                "LEFT JOIN comuna ON condominio.id_comuna = comuna.id_comuna " +
                "LEFT JOIN cuenta_condominio ON condominio.id_cuenta_condominio = cuenta_condominio.id_cuenta_condominio " +
                "LEFT JOIN usuario ON condominio.representante_id = usuario.id_usuario " +
                "LEFT JOIN estado ON condominio.estado = estado.id_estado")) {
            while (rst.next()) {
                Condominio condominio = new Condominio();
                condominio.setIdCondominio(rst.getInt("id_condominio"));
                condominio.setNombre(rst.getString("nombre"));
                condominio.setDireccion(rst.getString("direccion"));
                condominio.setEstado(rst.getInt("estado"));
                condominio.setNomEstado(rst.getString("nom_estado"));
                condominio.setRepresentanteId(rst.getInt("representante_id"));
                condominio.setCreado(rst.getDate("creado"));
                condominio.setModificado(rst.getDate("modificado"));
                condominio.setIdComuna(rst.getInt("id_comuna"));
                condominio.setComuna(rst.getString("comuna"));
                condominio.setIdCuentaCondominio(rst.getInt("id_cuenta_condominio"));
                condominio.setRepresentante(rst.getString("representante"));
                lista.add(condominio);
            }
        }
        return lista;
    }
    
    
    public List<Condominio> getSelect() throws SQLException, IOException {
        List<Condominio> lista = new ArrayList();

        try (ResultSet rst = fetch("SELECT id_condominio, nombre " +
                "FROM Condominio " +
                "WHERE estado = 1")) {
            while (rst.next()) {
                Condominio condominio = new Condominio();
                condominio.setIdCondominio(rst.getInt("id_condominio"));
                condominio.setNombre(rst.getString("nombre"));
                lista.add(condominio);
            }
        }
        return lista;
    }

    @Override
    public int ultimoID() {
        int ultim = 0;
        try (ResultSet rst = fetch("SELECT MAX(id_condominio) FROM Condominio")) {
            while (rst.next()) {
                ultim = rst.getInt(1);
            }
        } catch (SQLException | IOException ex) {
            Logger.getLogger(CondominioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ultim + 1;
    }

    @Override
    public boolean agregar(Object objetoInsert) {
        Condominio condominio = (Condominio) objetoInsert;
        try {
            String query = "INSERT INTO Condominio values (?,?,?,?,?,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,?,1)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, ultimoID());
            ps.setString(2, condominio.getNombre());
            ps.setString(3, condominio.getDireccion());
            ps.setInt(4, condominio.getEstado());
            ps.setInt(5, condominio.getRepresentanteId());
            ps.setInt(6, condominio.getIdComuna());
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
    public boolean modificar(Object objetoUpdate) {
        Condominio condominio = (Condominio) objetoUpdate;
        try {
            String query = "UPDATE condominio SET " +
                    "nombre= ?, " +
                    "direccion= ?, " +
                    "estado= ?, " +
                    "representante_id= ?, " +
                    "modificado= CURRENT_TIMESTAMP, " +
                    "id_comuna= ?, " +
                    "id_cuenta_condominio= ? " +
                    "WHERE id_condominio = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, condominio.getNombre());
            ps.setString(2, condominio.getDireccion());
            ps.setInt(3, condominio.getEstado());
            ps.setInt(4, condominio.getRepresentanteId());
            ps.setInt(5, condominio.getIdComuna());
            ps.setInt(6, condominio.getIdCuentaCondominio());
            ps.setInt(7, condominio.getIdCondominio());
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
            String query = "UPDATE condominio SET estado = ? WHERE id_condominio = ?";
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
