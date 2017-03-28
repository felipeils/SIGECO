/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.sigeco.Controllers.Mantenedores;

import cl.sigeco.Controllers.BaseController;
import cl.sigeco.Entidades.Condominio;
import cl.sigeco.Entidades.Estado;
import cl.sigeco.Entidades.Servicio;
import cl.sigeco.Entidades.Usuario;
import cl.sigeco.Persistencia.CondominioDAO;
import cl.sigeco.Persistencia.EstadoDAO;
import cl.sigeco.Persistencia.ServicioDAO;
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
public class MantenedorServicios implements Serializable, BaseController {
    ServicioDAO sdao;
    public MantenedorServicios() {
    }

    @RequestMapping(value = "/mantenedorServicios.htm", method = RequestMethod.GET)
    public String mantenedor(ModelMap map) {
        List<Servicio> servicios = new ArrayList();
        List<Condominio> condominios = this.getCondominios();
        List<Estado> estados = this.getEstados();
        try {
            sdao = new ServicioDAO();
           servicios = sdao.buscarTodos();
            sdao.close();
        } catch (IOException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(MantenedorUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
//lo que mando a la vista
        map.put("estados", estados);
        map.put("condominios", condominios);
        map.put("servicios", servicios);
        return "mantenedores/mantenedorServicios";
    }

    @RequestMapping(value = "/getServicio.htm", method = RequestMethod.POST)
    public String get(ModelMap map, HttpServletRequest request,
            HttpServletResponse response) {
        Servicio servicio = new Servicio();
        try {
            sdao = new ServicioDAO();
            servicio = sdao.getServicio(Integer.valueOf(request.getParameter("id")));
            sdao.close();
        } catch (IOException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(MantenedorUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }

        map.put("json", servicio.toString());
        return "../../include/json";
    }
    
    @RequestMapping(value = "/eliminaServicio.htm", method = RequestMethod.POST)
    public String delete(ModelMap map, HttpServletRequest request,
            HttpServletResponse response) {
        String json = "{\"response\":0}";
        try {
            sdao = new ServicioDAO();
            if (sdao.borrar(Integer.valueOf(request.getParameter("id"))))
                json = "{\"response\":1}";
            sdao.close();
        } catch (IOException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(MantenedorUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }

        map.put("json", json);
        return "../../include/json";
    }
    
    @RequestMapping(value = "/nuevoServicio.htm", method = RequestMethod.POST)
    public String add(ModelMap map, HttpServletRequest request,
            HttpServletResponse response) {
        Servicio servicio = new Servicio();
        String json = "{\"response\":0}";
        try {
            sdao = new ServicioDAO();
            
            servicio.setNombre(request.getParameter("nombre"));
            servicio.setDetalle(request.getParameter("detalle"));
            servicio.setValorBase(Integer.parseInt(request.getParameter("valorBase")));
            servicio.setIdServicio(
                (!request.getParameter("idServicio").equals("null"))
                ? Integer.valueOf(request.getParameter("idServicio")) : 0
            );
            servicio.setEstado(Integer.valueOf(request.getParameter("estado")));
            servicio.setIdCondominio(
                    (!request.getParameter("idCondominio").equals("null"))
                ? Integer.valueOf(request.getParameter("idCondominio")) : 0
            );
            
            if (Integer.valueOf(request.getParameter("idUsuario")) > 0){
                servicio.setIdServicio(Integer.valueOf(request.getParameter("idUsuario")));
                if (sdao.modificar(servicio))
                    json = "{\"response\":1}";
            }
            else {
                if (sdao.agregar(servicio))
                    json = "{\"response\":1}";
            }
            sdao.close();
        } catch (IOException | SQLException | ClassNotFoundException | NumberFormatException ex) {
            Logger.getLogger(MantenedorUsuarios.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(MantenedorUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        return estados;
    }
    
   public List<Condominio> getCondominios() {
        List<Condominio> condominios = new ArrayList();
        CondominioDAO cdao;
        try {
            cdao = new CondominioDAO();
            condominios = cdao.getSelect();
            cdao.close();
        } catch (IOException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(MantenedorUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        return condominios;
    }
}


