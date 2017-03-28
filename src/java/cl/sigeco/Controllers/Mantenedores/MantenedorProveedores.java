/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.sigeco.Controllers.Mantenedores;

import cl.sigeco.Controllers.BaseController;
import cl.sigeco.Entidades.Proveedor;
import cl.sigeco.Persistencia.ProveedorDAO;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@SessionScoped
@Controller
public class MantenedorProveedores implements Serializable, BaseController {
    
    ProveedorDAO pdao;

    public MantenedorProveedores() {
    }

    @RequestMapping(value = "/mantenedorProveedor.htm", method = RequestMethod.GET)
    public String proveedor(ModelMap map) {
        List<Proveedor> proveedores = new ArrayList();
        List<Proveedor> proveedoresS = this.getEstados();
        try {
            pdao = new ProveedorDAO();
            proveedores = pdao.buscarTodos();
            pdao.close();
        } catch (IOException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(MantenedorEstados.class.getName()).log(Level.SEVERE, null, ex);
        }

        //map.put("estadosS", estadosS);
        map.put("proveedores", proveedores);
        return "mantenedores/mantenedorProveedores";
    }

    @RequestMapping(value = "/getporveedores.htm", method = RequestMethod.POST)
    public String get(ModelMap map, HttpServletRequest request,
            HttpServletResponse response) {
        Proveedor proveedor = new Proveedor();
        try {
            pdao = new ProveedorDAO();
            proveedor = pdao.get(Integer.valueOf(request.getParameter("id")));
            pdao.close();
        } catch (IOException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(MantenedorEstados.class.getName()).log(Level.SEVERE, null, ex);
        }

        map.put("json", proveedor.toString());
        return "../../include/json";
    }

    @RequestMapping(value = "/eliminaProveedor.htm", method = RequestMethod.POST)
    public String delete(ModelMap map, HttpServletRequest request,
            HttpServletResponse response) {
        String json = "{\"response\":0}";
        try {
            pdao = new ProveedorDAO();
            if (pdao.borrar(Integer.valueOf(request.getParameter("id"))))
                json = "{\"response\":1}";
            pdao.close();
        } catch (IOException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(MantenedorEstados.class.getName()).log(Level.SEVERE, null, ex);
        }

        map.put("json", json);
        return "../../include/json";
    }

    @RequestMapping(value = "/nuevoProvedor.htm", method = RequestMethod.POST)
    public String add(ModelMap map, HttpServletRequest request,
            HttpServletResponse response) {
        Proveedor proveedor = new Proveedor();
        String json = "{\"response\":0}";
        try {
            pdao = new ProveedorDAO();
            
            proveedor.setNombre(request.getParameter("nombre"));
            proveedor.setEstado(Integer.valueOf(request.getParameter("estado")));
            if (Integer.valueOf(request.getParameter("idProveedor")) > 0){
                proveedor.setIdProveedor(Integer.valueOf(request.getParameter("idProveedor")));
                if (pdao.modificar(proveedor))
                    json = "{\"response\":1}";
            }
            else {
                if (pdao.agregar(proveedor))
                    json = "{\"response\":1}";
            }
            pdao.close();
        } catch (IOException | SQLException | ClassNotFoundException | NumberFormatException ex) {
            Logger.getLogger(MantenedorEstados.class.getName()).log(Level.SEVERE, null, ex);
        }

        map.put("json", json);
        return "../../include/json";
    }

    //@Override
    public List<Proveedor> getProveedores() {
        List<Proveedor> estados = new ArrayList();
        try {
            pdao = new ProveedorDAO();
            estados = pdao.getSelect();
            pdao.close();
        } catch (IOException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(MantenedorEstados.class.getName()).log(Level.SEVERE, null, ex);
        }
        return estados;
    }
}

