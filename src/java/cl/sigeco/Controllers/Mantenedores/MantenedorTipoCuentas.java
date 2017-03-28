/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.sigeco.Controllers.Mantenedores;

import cl.sigeco.Controllers.BaseController;
import cl.sigeco.Entidades.Estado;
import cl.sigeco.Entidades.TipoCuenta;
import cl.sigeco.Persistencia.EstadoDAO;
import cl.sigeco.Persistencia.TipoCuentaDAO;
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
public class MantenedorTipoCuentas implements Serializable, BaseController {
    
    TipoCuentaDAO tcdao;

    public MantenedorTipoCuentas() {
    }

    @RequestMapping(value = "/mantenedorTipoCuentas.htm", method = RequestMethod.GET)
    public String mantenedor(ModelMap map) {
        List<TipoCuenta> tiposCuenta = new ArrayList();
        List<Estado> estados = this.getEstados();
        try {
            tcdao = new TipoCuentaDAO();
            tiposCuenta = tcdao.buscarTodos();
            tcdao.close();
        } catch (IOException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(MantenedorTipoCuentas.class.getName()).log(Level.SEVERE, null, ex);
        }

        map.put("estados", estados);
        map.put("tiposCuenta", tiposCuenta);
        return "mantenedores/mantenedorTipoCuentas";
    }

    @RequestMapping(value = "/getTipoCuenta.htm", method = RequestMethod.POST)
    public String get(ModelMap map, HttpServletRequest request,
            HttpServletResponse response) {
        TipoCuenta tipoCuenta = new TipoCuenta();
        try {
            tcdao = new TipoCuentaDAO();
            tipoCuenta = tcdao.get(Integer.valueOf(request.getParameter("id")));
            tcdao.close();
        } catch (IOException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(MantenedorTipoCuentas.class.getName()).log(Level.SEVERE, null, ex);
        }

        map.put("json", tipoCuenta.toString());
        return "../../include/json";
    }

    @RequestMapping(value = "/eliminaTipoCuenta.htm", method = RequestMethod.POST)
    public String delete(ModelMap map, HttpServletRequest request,
            HttpServletResponse response) {
        String json = "{\"response\":0}";
        try {
            tcdao = new TipoCuentaDAO();
            if (tcdao.borrar(Integer.valueOf(request.getParameter("id"))))
                json = "{\"response\":1}";
            tcdao.close();
        } catch (IOException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(MantenedorTipoCuentas.class.getName()).log(Level.SEVERE, null, ex);
        }

        map.put("json", json);
        return "../../include/json";
    }

    @RequestMapping(value = "/nuevoTipoCuenta.htm", method = RequestMethod.POST)
    public String add(ModelMap map, HttpServletRequest request,
            HttpServletResponse response) {
        TipoCuenta tipoCuenta = new TipoCuenta();
        String json = "{\"response\":0}";
        try {
            tcdao = new TipoCuentaDAO();
            
            tipoCuenta.setNombre(request.getParameter("nombre"));
            tipoCuenta.setEstado(Integer.valueOf(request.getParameter("estado")));
            if (Integer.valueOf(request.getParameter("idTipoCuenta")) > 0){
                tipoCuenta.setIdTipoCuenta(Integer.valueOf(request.getParameter("idTipoCuenta")));
                if (tcdao.modificar(tipoCuenta))
                    json = "{\"response\":1}";
            }
            else {
                if (tcdao.agregar(tipoCuenta))
                    json = "{\"response\":1}";
            }
            tcdao.close();
        } catch (IOException | SQLException | ClassNotFoundException | NumberFormatException ex) {
            Logger.getLogger(MantenedorTipoCuentas.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(MantenedorTipoCuentas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return estados;
    }
}

