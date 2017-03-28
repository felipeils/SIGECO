/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.sigeco.Persistencia;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author Czar
 */
public class DAOManager {

    final static String DRIVER_CLASS = "oracle.jdbc.driver.OracleDriver";

    private Connection con;

    private String url;
    private String userName;
    private String password;
    private CondominioDAO condominio;

    /**
     * Initialize the Dao
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void init() throws IOException,
            ClassNotFoundException {
        // load the properties

        // get the properties
        url = "jdbc:oracle:thin:@localhost:1521:XE";
        userName = "SIGECO";
        password = "admin";

        // load the class
        Class.forName(DRIVER_CLASS);
    }

    /**
     * Open the Dao Connection
     *
     * @return Connection
     * @params fs
     * @throws SQLException
     * @throws IOException
     */
    public Connection open() throws SQLException, IOException {

        // get the connection to the database
        return con = DriverManager.getConnection(url, userName, password);
    }
        
    /**
     * Close the connection
     *
     * @throws SQLException
     */
    public void close() throws SQLException {
        if (con != null) {
            // close the connection
            con.close();
        }
    }

    /**
     * Get the Current Time via DB Query
     *
     * @return
     * @throws SQLException
     * @throws IOException
     */
    public String getCurrentTime() throws SQLException, IOException {
        String dateTime = null;
        ResultSet rst = fetch("select SYSDATE from dual");
        while (rst.next()) {
            dateTime = rst.getString(1);
        }
        // close the resultset
        rst.close();
        return dateTime;

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

}

