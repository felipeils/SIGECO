/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.sigeco.Controllers.Authentication;

import cl.sigeco.Controllers.BaseController;
import cl.sigeco.Controllers.Mantenedores.MantenedorCondominios;
import cl.sigeco.Entidades.Estado;
import cl.sigeco.Entidades.Usuario;
import cl.sigeco.Persistencia.EstadoDAO;
import cl.sigeco.Persistencia.UsuarioDAO;
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
public class UserLogin implements Serializable, BaseController {

    UsuarioDAO udao;

    public UserLogin() {
    }
   
    @RequestMapping(value = "/login.htm", method = RequestMethod.GET)
    public String login(ModelMap map, HttpServletRequest request,
            HttpServletResponse response) {
        return "login";
    }
   
    @RequestMapping(value = "/logout.htm", method = RequestMethod.GET)
    public String logout(ModelMap map, HttpServletRequest request,
            HttpServletResponse response) {
        request.getSession().setAttribute("user", null);
        return "login";
    }
   
    @RequestMapping(value = "/recuperarPass.htm", method = RequestMethod.GET)
    public String recuperarPass(ModelMap map) {
        return "login";
    }
   
    @RequestMapping(value = "/registrarse.htm", method = RequestMethod.GET)
    public String registrarse(ModelMap map, HttpServletRequest request,
            HttpServletResponse response) {
        return "register";
    }
   
    @RequestMapping(value = "/registrarse.htm", method = RequestMethod.POST)
    public String validaRegistro(ModelMap map, HttpServletRequest request,
            HttpServletResponse response) {
        String json = "{\"response\":0}";
        boolean valid = false;
        
        if (valid) json = "{\"response\":1}";
        map.put("json", json);
        return "../../include/json";
    }
   
    @RequestMapping(value = "/valida.htm", method = RequestMethod.POST)
    public String validateUser(ModelMap map, HttpServletRequest request,
            HttpServletResponse response) {
        String json = "{\"response\":0}";
        Usuario user = null;
        Integer id = null;
        try {
            udao = new UsuarioDAO();
            id = udao.validaUsuario(request.getParameter("usuario"), request.getParameter("pass"));
            if (null!=id) {
                user = udao.getUsuario(id);
            }
            udao.close();
        } catch (IOException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(MantenedorCondominios.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (null!=id) {
            json = "{\"response\":1}";
            request.getSession().setAttribute("user", user);
        }
        map.put("json", json);
        return "../../include/json";
    }
    
    @Override
    public List<Estado> getEstados() {
        List<Estado> estados = new ArrayList();
        EstadoDAO edao;
        try {
            edao = new EstadoDAO();
            estados = edao.buscarTodos();
            edao.close();
        } catch (IOException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(MantenedorCondominios.class.getName()).log(Level.SEVERE, null, ex);
        }
        return estados;
    }
}
