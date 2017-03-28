/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.sigeco.Controllers.Mantenedores;

import java.io.Serializable;
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
public class MantenedorPersonal implements Serializable {

    public MantenedorPersonal() {
    }

    @RequestMapping(value = "/mantenedorPersonal.htm", method = RequestMethod.GET)
    public String mantenedor(ModelMap map) {
     //   PedidoDAO pedidoDAO = new PedidoDAO();
     //   List<Pedido> pedidos = pedidoDAO.buscarTodos();
     //   map.put("listaPedidos", pedidos);
     //   map.put("msg", "Hello Spring 4 Web MVC!");
        return "mantenedores/mantenedorPersonal";
    }
}

