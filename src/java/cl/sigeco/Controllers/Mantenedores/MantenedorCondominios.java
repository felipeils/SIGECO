/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.sigeco.Controllers.Mantenedores;

import cl.sigeco.Controllers.BaseController;
import cl.sigeco.Entidades.Comuna;
import cl.sigeco.Entidades.Condominio;
import cl.sigeco.Entidades.Estado;
import cl.sigeco.Persistencia.ComunaDAO;
import cl.sigeco.Persistencia.CondominioDAO;
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
public class MantenedorCondominios implements Serializable, BaseController, Mantenedor {

    CondominioDAO cdao;
    
    public MantenedorCondominios() {
    }

    @RequestMapping(value = "/mantenedorCondominios.htm", method = RequestMethod.GET)
    public String mantenedor(ModelMap map) {
        List<Condominio> condominios = new ArrayList();
        List<Estado> estados = this.getEstados();
        List<Comuna> comunas = this.getComunas();
        try {
            cdao = new CondominioDAO();
            condominios = cdao.buscarTodos();
            cdao.close();
        } catch (IOException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(MantenedorCondominios.class.getName()).log(Level.SEVERE, null, ex);
        }

        map.put("estados", estados);
        map.put("comunas", comunas);
        map.put("condominios", condominios);
        return "mantenedores/mantenedorCondominios";
    }

    @RequestMapping(value = "/getCondominio.htm", method = RequestMethod.POST)
    public String get(ModelMap map, HttpServletRequest request,
            HttpServletResponse response) {
        Condominio condominio = new Condominio();
        try {
            cdao = new CondominioDAO();
            condominio = cdao.getCondominio(Integer.valueOf(request.getParameter("id")));
            cdao.close();
        } catch (IOException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(MantenedorCondominios.class.getName()).log(Level.SEVERE, null, ex);
        }

        map.put("json", condominio.toString());
        return "../../include/json";
    }

    @RequestMapping(value = "/eliminaCondominio.htm", method = RequestMethod.POST)
    public String delete(ModelMap map, HttpServletRequest request,
            HttpServletResponse response) {
        String json = "{\"response\":0}";
        try {
            cdao = new CondominioDAO();
            if (cdao.borrar(Integer.valueOf(request.getParameter("id"))))
                json = "{\"response\":1}";
            cdao.close();
        } catch (IOException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(MantenedorCondominios.class.getName()).log(Level.SEVERE, null, ex);
        }

        map.put("json", json);
        return "../../include/json";
    }

    @RequestMapping(value = "/nuevoCondominio.htm", method = RequestMethod.POST)
    public String add(ModelMap map, HttpServletRequest request,
            HttpServletResponse response) {
        Condominio condominio = new Condominio();
        String json = "{\"response\":0}";
        try {
            cdao = new CondominioDAO();
            
            condominio.setNombre(request.getParameter("nombre"));
            condominio.setDireccion(request.getParameter("direccion"));
            condominio.setIdComuna(Integer.valueOf(request.getParameter("idComuna")));
            condominio.setRepresentanteId(
                (!request.getParameter("representanteId").equals("null"))
                ? Integer.valueOf(request.getParameter("representanteId")) : 0
            );
            condominio.setEstado(Integer.valueOf(request.getParameter("estado")));
            condominio.setIdCuentaCondominio(1);
            if (Integer.valueOf(request.getParameter("idCondominio")) > 0){
                condominio.setIdCondominio(Integer.valueOf(request.getParameter("idCondominio")));
                if (cdao.modificar(condominio))
                    json = "{\"response\":1}";
            }
            else {
                if (cdao.agregar(condominio))
                    json = "{\"response\":1}";
            }
            cdao.close();
        } catch (IOException | SQLException | ClassNotFoundException | NumberFormatException ex) {
            Logger.getLogger(MantenedorCondominios.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(MantenedorCondominios.class.getName()).log(Level.SEVERE, null, ex);
        }
        return estados;
    }

    @Override
    public List<Comuna> getComunas() {
        List<Comuna> comunas = new ArrayList();
        ComunaDAO cdao;
        try {
            cdao = new ComunaDAO();
            comunas = cdao.buscarTodos();
            cdao.close();
        } catch (IOException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(MantenedorCondominios.class.getName()).log(Level.SEVERE, null, ex);
        }
        return comunas;
    }

}
