/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.sigeco.Persistencia;

import cl.sigeco.Entidades.GastoComun;
import cl.sigeco.Entidades.Perfil;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
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
public class GastoComunDAO implements Serializable, ICrud {

    @Resource(mappedName = "jdbc/sigeco")
    private final Connection con;

    public GastoComunDAO() throws SQLException, IOException, ClassNotFoundException {
        DAOManager manager = new DAOManager();
        manager.init();
        this.con = manager.open();
    }
    
    public GastoComun getGastoComun( int id ) {
        GastoComun gastoComun = new GastoComun();

        try {
            String query = "SELECT gasto_comun.*, estado.nombre as nom_estado " +
                "FROM gasto_comun " +
                "LEFT JOIN estado ON gasto_comun.estado = estado.id_estado " +
                "WHERE gasto_comun.id_gasto_comun = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rst = ps.executeQuery();
            while (rst.next()) {
                gastoComun.setIdGastoComun(rst.getInt("id_gasto_comun"));
                gastoComun.setFechaEmision(rst.getDate("fecha_emision"));
                gastoComun.setValor(rst.getInt("valor"));
                gastoComun.setMulta(rst.getInt("multa"));
                gastoComun.setEstado(rst.getInt("multa"));
                gastoComun.setCreado(rst.getDate("creado"));
                gastoComun.setModificado(rst.getDate("modificado"));
                gastoComun.setIdVivienda(rst.getInt("id_vivienda"));
                gastoComun.setNomEstado(rst.getString("nom_estado"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CondominioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return gastoComun;
    }

    @Override
    public boolean agregar(Object objetoInsert) {
        GastoComun gastoComun = (GastoComun) objetoInsert;
        try {
            String query = "INSERT INTO gasto_comun values (?,?,?,?,?,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, ultimoID());
            ps.setDate(2, Date.valueOf(gastoComun.getFechaEmision().toString()));
            ps.setInt(3, gastoComun.getValor());
            ps.setInt(4, gastoComun.getMulta());
            ps.setInt(5, gastoComun.getEstado());
            ps.setInt(6, gastoComun.getIdVivienda());
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
        List<GastoComun> lista = new ArrayList();
        GastoComun gastoComun;

        try (ResultSet rst = fetch("SELECT gasto_comun.*, estado.nombre as nom_estado " +
                "FROM gasto_comun " +
                "LEFT JOIN estado ON gasto_comun.estado = estado.id_estado")) {
            while (rst.next()) {
                gastoComun = new GastoComun();
                gastoComun.setIdGastoComun(rst.getInt("id_gasto_comun"));
                gastoComun.setFechaEmision(rst.getDate("fecha_emision"));
                gastoComun.setValor(rst.getInt("valor"));
                gastoComun.setMulta(rst.getInt("multa"));
                gastoComun.setEstado(rst.getInt("multa"));
                gastoComun.setCreado(rst.getDate("creado"));
                gastoComun.setModificado(rst.getDate("modificado"));
                gastoComun.setIdVivienda(rst.getInt("id_vivienda"));
                gastoComun.setNomEstado(rst.getString("nom_estado"));
                lista.add(gastoComun);
            }
        }
        return lista;
    }
    
    public List getSelect(int id_vivienda) throws SQLException, IOException {
        List<GastoComun> lista = new ArrayList();
        GastoComun gastoComun;
        String query = "SELECT id_gasto_comun, valor, fecha_emision " +
                "FROM gasto_comun " +
                "WHERE id_vivienda = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id_vivienda);
        try (ResultSet rst = ps.executeQuery()) {
            while (rst.next()) {
                gastoComun = new GastoComun();
                gastoComun.setIdGastoComun(rst.getInt("id_gasto_comun"));
                gastoComun.setValor(rst.getInt("valor"));
                gastoComun.setFechaEmision(rst.getDate("fecha_emision"));
                lista.add(gastoComun);
            }
        }
        return lista;
    }

    @Override
    public boolean modificar(Object objetoUpdate) {
        GastoComun gastoComun = (GastoComun) objetoUpdate;
        try {
            String query = "UPDATE gasto_comun SET " +
                    "fecha_emision= ?, " +
                    "valor= ?, " +
                    "multa= ? " +
                    "estado= ? " +
                    "modificado= CURRENT_TIMESTAMP " +
                    "id_vivienda= ? " +
                    "WHERE id_gasto_comun = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setDate(1, Date.valueOf(gastoComun.getFechaEmision().toString()));
            ps.setInt(2, gastoComun.getValor());
            ps.setInt(3, gastoComun.getMulta());
            ps.setInt(2, gastoComun.getEstado());
            ps.setInt(2, gastoComun.getIdVivienda());
            ps.setInt(2, gastoComun.getIdGastoComun());
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
            String query = "UPDATE gasto_comun SET estado = ? WHERE id_gasto_comun = ?";
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
        try (ResultSet rst = fetch("SELECT MAX(id_gasto_comun) FROM gasto_comun")) {
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
