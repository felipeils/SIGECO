/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.sigeco.Controllers.Mantenedores;

import cl.sigeco.Controllers.BaseController;
import cl.sigeco.Entidades.Estado;
import cl.sigeco.Persistencia.EstadoDAO;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.SessionScoped;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Joe-Xidu
 */
@SessionScoped
@Controller
public class MantenedorResidentes implements Serializable, BaseController {

    public MantenedorResidentes() {
    }

    @RequestMapping(value = "/mantenedorResidentes.htm", method = RequestMethod.GET)
    public String mantenedor(ModelMap map) {
     //   PedidoDAO pedidoDAO = new PedidoDAO();
     //   List<Pedido> pedidos = pedidoDAO.buscarTodos();
     //   map.put("listaPedidos", pedidos);
     //   map.put("msg", "Hello Spring 4 Web MVC!");
        return "mantenedores/mantenedorResidentes";
    }

    @Override
    public List<Estado> getEstados() {
        List<Estado> estados = new ArrayList();
        EstadoDAO edao;
        try {
            edao = new EstadoDAO();
            estados = edao.getSelect();
            edao.close();
        } catch (IOException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(MantenedorPerfiles.class.getName()).log(Level.SEVERE, null, ex);
        }
        return estados;
    }
}

