/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.sigeco.Controllers.Mantenedores;

import cl.sigeco.Controllers.BaseController;
import cl.sigeco.Entidades.Estado;
import cl.sigeco.Entidades.Perfil;
import cl.sigeco.Persistencia.EstadoDAO;
import cl.sigeco.Persistencia.PerfilDAO;
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
public class MantenedorPerfiles implements Serializable, BaseController {
    
    PerfilDAO pdao;

    public MantenedorPerfiles() {
    }

    @RequestMapping(value = "/mantenedorPerfiles.htm", method = RequestMethod.GET)
    public String mantenedor(ModelMap map) {
        List<Perfil> perfiles = new ArrayList();
        List<Estado> estados = this.getEstados();
        try {
            pdao = new PerfilDAO();
            perfiles = pdao.buscarTodos();
            pdao.close();
        } catch (IOException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(MantenedorPerfiles.class.getName()).log(Level.SEVERE, null, ex);
        }

        map.put("estados", estados);
        map.put("perfiles", perfiles);
        return "mantenedores/mantenedorPerfiles";
    }

    @RequestMapping(value = "/getPerfil.htm", method = RequestMethod.POST)
    public String get(ModelMap map, HttpServletRequest request,
            HttpServletResponse response) {
        Perfil perfil = new Perfil();
        try {
            pdao = new PerfilDAO();
            perfil = pdao.getPerfil(Integer.valueOf(request.getParameter("id")));
            pdao.close();
        } catch (IOException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(MantenedorPerfiles.class.getName()).log(Level.SEVERE, null, ex);
        }

        map.put("json", perfil.toString());
        return "../../include/json";
    }

    @RequestMapping(value = "/eliminaPerfil.htm", method = RequestMethod.POST)
    public String delete(ModelMap map, HttpServletRequest request,
            HttpServletResponse response) {
        String json = "{\"response\":0}";
        try {
            pdao = new PerfilDAO();
            if (pdao.borrar(Integer.valueOf(request.getParameter("id"))))
                json = "{\"response\":1}";
            pdao.close();
        } catch (IOException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(MantenedorPerfiles.class.getName()).log(Level.SEVERE, null, ex);
        }

        map.put("json", json);
        return "../../include/json";
    }

    @RequestMapping(value = "/nuevoPerfil.htm", method = RequestMethod.POST)
    public String add(ModelMap map, HttpServletRequest request,
            HttpServletResponse response) {
        Perfil perfil = new Perfil();
        String json = "{\"response\":0}";
        try {
            pdao = new PerfilDAO();
            
            perfil.setNombre(request.getParameter("nombre"));
            perfil.setEstado(Integer.valueOf(request.getParameter("estado")));
            if (Integer.valueOf(request.getParameter("idPerfil")) > 0){
                perfil.setIdPerfil(Integer.valueOf(request.getParameter("idPerfil")));
                if (pdao.modificar(perfil))
                    json = "{\"response\":1}";
            }
            else {
                if (pdao.agregar(perfil))
                    json = "{\"response\":1}";
            }
            pdao.close();
        } catch (IOException | SQLException | ClassNotFoundException | NumberFormatException ex) {
            Logger.getLogger(MantenedorPerfiles.class.getName()).log(Level.SEVERE, null, ex);
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
            estados = edao.getSelect();
            edao.close();
        } catch (IOException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(MantenedorPerfiles.class.getName()).log(Level.SEVERE, null, ex);
        }
        return estados;
    }

}

