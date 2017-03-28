/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.sigeco.Persistencia;

import cl.sigeco.Entidades.Comuna;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

/**
 *
 * @author Joe-Xidu
 */
public class ComunaDAO implements Serializable, ICrud  {
    
    @Resource(mappedName = "jdbc/sigeco")
    private Connection con;
    
    public ComunaDAO() throws SQLException, IOException, ClassNotFoundException {
        DAOManager manager = new DAOManager();
        manager.init();
        this.con = manager.open();
    }
    
    @Override
    public boolean agregar(Object objetoInsert) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Comuna> buscarTodos() throws SQLException, IOException {
        List<Comuna> lista = new ArrayList();
        
        try (ResultSet rst = fetch("SELECT * FROM Comuna")) {
            while (rst.next()) {
                Comuna comuna = new Comuna();
                comuna.setIdComuna(rst.getInt("id_comuna"));
                comuna.setNombre(rst.getString("nombre"));
                comuna.setEstado(rst.getInt("estado"));
                lista.add(comuna);
            }
        }
        return lista;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
