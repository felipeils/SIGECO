/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.sigeco.Persistencia;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface ICrud {

    public boolean agregar(Object objetoInsert);

    public List buscarTodos() throws SQLException, IOException;

    public boolean modificar(Object objetoUpdate);

    public boolean borrar(int codigo);

    public int ultimoID();
}
