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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
public class MantenedorEstados implements Serializable, BaseController {
    
    EstadoDAO edao;

    public MantenedorEstados() {
    }

    @RequestMapping(value = "/mantenedorEstados.htm", method = RequestMethod.GET)
    public String mantenedor(ModelMap map) {
        List<Estado> estados = new ArrayList();
        List<Estado> estadosS = this.getEstados();
        try {
            edao = new EstadoDAO();
            estados = edao.buscarTodos();
            edao.close();
        } catch (IOException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(MantenedorEstados.class.getName()).log(Level.SEVERE, null, ex);
        }

        map.put("estadosS", estadosS);
        map.put("estados", estados);
        return "mantenedores/mantenedorEstados";
    }

    @RequestMapping(value = "/getEstado.htm", method = RequestMethod.POST)
    public String get(ModelMap map, HttpServletRequest request,
            HttpServletResponse response) {
        Estado estado = new Estado();
        try {
            edao = new EstadoDAO();
            estado = edao.get(Integer.valueOf(request.getParameter("id")));
            edao.close();
        } catch (IOException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(MantenedorEstados.class.getName()).log(Level.SEVERE, null, ex);
        }

        map.put("json", estado.toString());
        return "../../include/json";
    }

    @RequestMapping(value = "/eliminaEstado.htm", method = RequestMethod.POST)
    public String delete(ModelMap map, HttpServletRequest request,
            HttpServletResponse response) {
        String json = "{\"response\":0}";
        try {
            edao = new EstadoDAO();
            if (edao.borrar(Integer.valueOf(request.getParameter("id"))))
                json = "{\"response\":1}";
            edao.close();
        } catch (IOException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(MantenedorEstados.class.getName()).log(Level.SEVERE, null, ex);
        }

        map.put("json", json);
        return "../../include/json";
    }

    @RequestMapping(value = "/nuevoEstado.htm", method = RequestMethod.POST)
    public String add(ModelMap map, HttpServletRequest request,
            HttpServletResponse response) {
        Estado estado = new Estado();
        String json = "{\"response\":0}";
        try {
            edao = new EstadoDAO();
            
            estado.setNombre(request.getParameter("nombre"));
            estado.setEstado(Integer.valueOf(request.getParameter("estado")));
            if (Integer.valueOf(request.getParameter("idEstado")) > 0){
                estado.setIdEstado(Integer.valueOf(request.getParameter("idEstado")));
                if (edao.modificar(estado))
                    json = "{\"response\":1}";
            }
            else {
                if (edao.agregar(estado))
                    json = "{\"response\":1}";
            }
            edao.close();
        } catch (IOException | SQLException | ClassNotFoundException | NumberFormatException ex) {
            Logger.getLogger(MantenedorEstados.class.getName()).log(Level.SEVERE, null, ex);
        }

        map.put("json", json);
        return "../../include/json";
    }

    @Override
    public List<Estado> getEstados() {
        List<Estado> estados = new ArrayList();
        try {
            edao = new EstadoDAO();
            estados = edao.getSelect();
            edao.close();
        } catch (IOException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(MantenedorEstados.class.getName()).log(Level.SEVERE, null, ex);
        }
        return estados;
    }
}

