/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.sigeco.Controllers.Mantenedores;

import cl.sigeco.Controllers.BaseController;
import cl.sigeco.Entidades.Estado;
import cl.sigeco.Entidades.TipoVivienda;
import cl.sigeco.Persistencia.EstadoDAO;
import cl.sigeco.Persistencia.TipoViviendaDAO;
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
public class MantenedorTipoViviendas implements Serializable, BaseController {
    
    TipoViviendaDAO tvdao;
    
    public MantenedorTipoViviendas() {
    }

    @RequestMapping(value = "/mantenedorTipoViviendas.htm", method = RequestMethod.GET)
    public String mantenedor(ModelMap map) {
        List<TipoVivienda> tipoVivienda = new ArrayList();
        List<Estado> estados = this.getEstados();
        try {
            tvdao = new TipoViviendaDAO();
            tipoVivienda = tvdao.buscarTodos();
            tvdao.close();
        } catch (IOException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(MantenedorTipoViviendas.class.getName()).log(Level.SEVERE, null, ex);
        }

        map.put("estados", estados);
        map.put("tiposVivienda", tipoVivienda);
        return "mantenedores/mantenedorTipoViviendas";
    }

    @RequestMapping(value = "/getTipoVivienda.htm", method = RequestMethod.POST)
    public String get(ModelMap map, HttpServletRequest request,
            HttpServletResponse response) {
        TipoVivienda tipoVivienda = new TipoVivienda();
        try {
            tvdao = new TipoViviendaDAO();
            tipoVivienda = tvdao.getTipoVivienda(Integer.valueOf(request.getParameter("id")));
            tvdao.close();
        } catch (IOException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(MantenedorTipoViviendas.class.getName()).log(Level.SEVERE, null, ex);
        }

        map.put("json", tipoVivienda.toString());
        return "../../include/json";
    }

    @RequestMapping(value = "/eliminaTipoVivienda.htm", method = RequestMethod.POST)
    public String delete(ModelMap map, HttpServletRequest request,
            HttpServletResponse response) {
        String json = "{\"response\":0}";
        try {
            tvdao = new TipoViviendaDAO();
            if (tvdao.borrar(Integer.valueOf(request.getParameter("id"))))
                json = "{\"response\":1}";
            tvdao.close();
        } catch (IOException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(MantenedorTipoViviendas.class.getName()).log(Level.SEVERE, null, ex);
        }

        map.put("json", json);
        return "../../include/json";
    }

    @RequestMapping(value = "/nuevoTipoVivienda.htm", method = RequestMethod.POST)
    public String add(ModelMap map, HttpServletRequest request,
            HttpServletResponse response) {
        TipoVivienda tipoVivienda = new TipoVivienda();
        String json = "{\"response\":0}";
        try {
            tvdao = new TipoViviendaDAO();
            
            tipoVivienda.setParticipacion(Double.valueOf(request.getParameter("participacion")));
            tipoVivienda.setNombre(request.getParameter("nombre"));
            tipoVivienda.setEstado(Integer.valueOf(request.getParameter("estado")));
            if (Integer.valueOf(request.getParameter("idTipoVivienda")) > 0){
                tipoVivienda.setIdTipoVivienda(Integer.valueOf(request.getParameter("idTipoVivienda")));
                if (tvdao.modificar(tipoVivienda))
                    json = "{\"response\":1}";
            }
            else {
                if (tvdao.agregar(tipoVivienda))
                    json = "{\"response\":1}";
            }
            tvdao.close();
        } catch (IOException | SQLException | ClassNotFoundException | NumberFormatException ex) {
            Logger.getLogger(MantenedorTipoViviendas.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(MantenedorTipoViviendas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return estados;
    }
}

