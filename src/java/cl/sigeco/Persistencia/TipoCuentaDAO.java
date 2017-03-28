/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.sigeco.Persistencia;

import cl.sigeco.Entidades.Perfil;
import cl.sigeco.Entidades.TipoCuenta;
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
public class TipoCuentaDAO implements Serializable, ICrud {

    @Resource(mappedName = "jdbc/sigeco")
    private final Connection con;

    public TipoCuentaDAO() throws SQLException, IOException, ClassNotFoundException {
        DAOManager manager = new DAOManager();
        manager.init();
        this.con = manager.open();
    }
    
    public TipoCuenta get( int id ) {
        TipoCuenta tipoCuenta = new TipoCuenta();

        try {
            String query = "SELECT tipo_cuenta.*, estado.nombre as nom_estado " +
                "FROM tipo_cuenta " +
                "LEFT JOIN estado ON tipo_cuenta.estado = estado.id_estado " +
                "WHERE tipo_cuenta.id_tipo_cuenta = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rst = ps.executeQuery();
            while (rst.next()) {
                tipoCuenta.setIdTipoCuenta(rst.getInt("id_tipo_cuenta"));
                tipoCuenta.setNombre(rst.getString("nombre"));
                tipoCuenta.setEstado(rst.getInt("estado"));
                tipoCuenta.setNomEstado(rst.getString("nom_estado"));
                tipoCuenta.setCreado(rst.getDate("creado"));
                tipoCuenta.setModificado(rst.getDate("modificado"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CondominioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tipoCuenta;
    }

    @Override
    public boolean agregar(Object objetoInsert) {
        TipoCuenta tipoCuenta = (TipoCuenta) objetoInsert;
        try {
            String query = "INSERT INTO tipo_cuenta values (?,?,?,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, ultimoID());
            ps.setString(2, tipoCuenta.getNombre());
            ps.setInt(3, tipoCuenta.getEstado());
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
        List<TipoCuenta> lista = new ArrayList();

        try (ResultSet rst = fetch("SELECT tipo_cuenta.*, estado.nombre as nom_estado " +
                "FROM tipo_cuenta " +
                "LEFT JOIN estado ON tipo_cuenta.estado = estado.id_estado")) {
            while (rst.next()) {
                TipoCuenta tipoCuenta = new TipoCuenta();
                tipoCuenta.setIdTipoCuenta(rst.getInt("id_tipo_cuenta"));
                tipoCuenta.setNombre(rst.getString("nombre"));
                tipoCuenta.setEstado(rst.getInt("estado"));
                tipoCuenta.setNomEstado(rst.getString("nom_estado"));
                tipoCuenta.setCreado(rst.getDate("creado"));
                tipoCuenta.setModificado(rst.getDate("modificado"));
                lista.add(tipoCuenta);
            }
        }
        return lista;
    }
    
    public List getSelect() throws SQLException, IOException {
        List<TipoCuenta> lista = new ArrayList();

        try (ResultSet rst = fetch("SELECT id_tipo_cuenta, nombre " +
                "FROM tipo_cuenta " +
                "WHERE estado = 1")) {
            while (rst.next()) {
                TipoCuenta tipoCuenta = new TipoCuenta();
                tipoCuenta.setIdTipoCuenta(rst.getInt("id_tipo_cuenta"));
                tipoCuenta.setNombre(rst.getString("nombre"));
                lista.add(tipoCuenta);
            }
        }
        return lista;
    }

    @Override
    public boolean modificar(Object objetoUpdate) {
        TipoCuenta tipoCuenta = (TipoCuenta) objetoUpdate;
        try {
            String query = "UPDATE tipo_cuenta SET " +
                    "nombre= ?, " +
                    "estado= ?, " +
                    "modificado= CURRENT_TIMESTAMP " +
                    "WHERE id_tipo_cuenta = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, tipoCuenta.getNombre());
            ps.setInt(2, tipoCuenta.getEstado());
            ps.setInt(3, tipoCuenta.getIdTipoCuenta());
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
            String query = "UPDATE tipo_cuenta SET estado = ? WHERE id_tipo_cuenta = ?";
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
        try (ResultSet rst = fetch("SELECT MAX(id_tipo_cuenta) FROM tipo_cuenta")) {
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
