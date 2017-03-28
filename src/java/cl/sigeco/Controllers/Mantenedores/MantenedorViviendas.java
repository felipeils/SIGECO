/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.sigeco.Controllers.Mantenedores;

import cl.sigeco.Controllers.BaseController;
import cl.sigeco.Entidades.Condominio;
import cl.sigeco.Entidades.Estado;
import cl.sigeco.Entidades.Perfil;
import cl.sigeco.Entidades.TipoVivienda;
import cl.sigeco.Entidades.Vivienda;
import cl.sigeco.Persistencia.CondominioDAO;
import cl.sigeco.Persistencia.EstadoDAO;
import cl.sigeco.Persistencia.PerfilDAO;
import cl.sigeco.Persistencia.TipoViviendaDAO;
import cl.sigeco.Persistencia.ViviendaDAO;
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
public class MantenedorViviendas implements Serializable, BaseController {
    
    ViviendaDAO vdao;

    public MantenedorViviendas() {
    }

    @RequestMapping(value = "/mantenedorViviendas.htm", method = RequestMethod.GET)
    public String mantenedor(ModelMap map) {
        List<Vivienda> viviendas = new ArrayList();
        List<Estado> estados = this.getEstados();
        List<Condominio> condominios = this.getCondominios();
        List<TipoVivienda> tiposVivienda = this.getTiposVivienda();
        try {
            vdao = new ViviendaDAO();
            viviendas = vdao.buscarTodos();
            vdao.close();
        } catch (IOException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(MantenedorViviendas.class.getName()).log(Level.SEVERE, null, ex);
        }

        map.put("estados", estados);
        map.put("condominios", condominios);
        map.put("tiposVivienda", tiposVivienda);
        map.put("viviendas", viviendas);
        return "mantenedores/mantenedorViviendas";
    }

    @RequestMapping(value = "/getVivienda.htm", method = RequestMethod.POST)
    public String get(ModelMap map, HttpServletRequest request,
            HttpServletResponse response) {
        Vivienda vivienda = new Vivienda();
        try {
            vdao = new ViviendaDAO();
            vivienda = vdao.get(Integer.valueOf(request.getParameter("id")));
            vdao.close();
        } catch (IOException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(MantenedorViviendas.class.getName()).log(Level.SEVERE, null, ex);
        }

        map.put("json", vivienda.toString());
        return "../../include/json";
    }

    @RequestMapping(value = "/eliminaVivienda.htm", method = RequestMethod.POST)
    public String delete(ModelMap map, HttpServletRequest request,
            HttpServletResponse response) {
        String json = "{\"response\":0}";
        try {
            vdao = new ViviendaDAO();
            if (vdao.borrar(Integer.valueOf(request.getParameter("id"))))
                json = "{\"response\":1}";
            vdao.close();
        } catch (IOException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(MantenedorViviendas.class.getName()).log(Level.SEVERE, null, ex);
        }

        map.put("json", json);
        return "../../include/json";
    }

    @RequestMapping(value = "/nuevaVivienda.htm", method = RequestMethod.POST)
    public String add(ModelMap map, HttpServletRequest request,
            HttpServletResponse response) {
        Vivienda vivienda = new Vivienda();
        String json = "{\"response\":0}";
        try {
            vdao = new ViviendaDAO();
            
            vivienda.setIdVivienda(Integer.valueOf(request.getParameter("idVivienda")));
            vivienda.setNumero(Integer.valueOf(request.getParameter("numero")));
            vivienda.setPiso(Integer.valueOf(request.getParameter("piso")));
            vivienda.setSector(request.getParameter("sector"));
            vivienda.setMetrosCuadrados(Integer.valueOf(request.getParameter("metrosCuadrados")));
            vivienda.setIdCondominio(Integer.valueOf(request.getParameter("idCondominio")));
            vivienda.setIdTipoVivienda(Integer.valueOf(request.getParameter("idTipoVivienda")));
            vivienda.setEstado(Integer.valueOf(request.getParameter("estado")));
            if (Integer.valueOf(request.getParameter("idVivienda")) > 0){
                vivienda.setIdVivienda(Integer.valueOf(request.getParameter("idVivienda")));
                if (vdao.modificar(vivienda))
                    json = "{\"response\":1}";
            }
            else {
                if (vdao.agregar(vivienda))
                    json = "{\"response\":1}";
            }
            vdao.close();
        } catch (IOException | SQLException | ClassNotFoundException | NumberFormatException ex) {
            Logger.getLogger(MantenedorViviendas.class.getName()).log(Level.SEVERE, null, ex);
        }

        map.put("json", json);
        return "../../include/json";
    }
    
    public List<TipoVivienda> getTiposVivienda() {
        List<TipoVivienda> tiposVivienda = new ArrayList();
        TipoViviendaDAO tvdao;
        try {
            tvdao = new TipoViviendaDAO();
            tiposVivienda = tvdao.getSelect();
            tvdao.close();
        } catch (IOException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(MantenedorViviendas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tiposVivienda;
    }
    
    public List<Condominio> getCondominios() {
        List<Condominio> condominios = new ArrayList();
        CondominioDAO cdao;
        try {
            cdao = new CondominioDAO();
            condominios = cdao.getSelect();
            cdao.close();
        } catch (IOException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(MantenedorViviendas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return condominios;
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
            Logger.getLogger(MantenedorViviendas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return estados;
    }
}

