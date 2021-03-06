/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.sigeco.Controllers.Mantenedores;

import cl.sigeco.Controllers.BaseController;
import cl.sigeco.Entidades.Estado;
import cl.sigeco.Entidades.GastoComun;
import cl.sigeco.Entidades.Usuario;
import cl.sigeco.Persistencia.EstadoDAO;
import cl.sigeco.Persistencia.GastoComunDAO;
import cl.sigeco.Persistencia.ReservaServicioDAO;
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
public class MantenedorEspacios implements Serializable, BaseController {

    ReservaServicioDAO rsdao;
    ServicioDAO sdao;

    public MantenedorEspacios() {
    }
    
    @RequestMapping(value = "/espaciosComunes.htm", method = RequestMethod.GET)
    public String servicios(ModelMap map, HttpServletRequest request,
            HttpServletResponse response) {
        List<GastoComun> gastosComunes = new ArrayList();
        List<Estado> estados = this.getEstados();
        List<Usuario> usuarios = this.getUsuarios();

        map.put("estados", estados);
        map.put("usuarios", usuarios);
        map.put("gastosComunes", gastosComunes);
        return "servicios/espaciosComunes";
    }
    
    @RequestMapping(value = "/reservaEspaciosComunes.htm", method = RequestMethod.POST)
    public String reserva(ModelMap map, HttpServletRequest request,
            HttpServletResponse response) {
        String json = "{\"response\":0}";
        String[] fechas = request.getParameter("fechas").split(" ");
        String fechaIni = fechas[0];
        String horaIni = fechas[1];
        String horaIniMer = fechas[2];
        String fechaFin = fechas[4];
        String horaFin = fechas[5];
        String horaFinMer = fechas[6];
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
    
    public List<Usuario> getUsuarios() {
        List<Usuario> usuarios = new ArrayList();
        UsuarioDAO udao;
        try {
            udao = new UsuarioDAO();
            usuarios = udao.getSelect();
            udao.close();
        } catch (IOException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(MantenedorCondominios.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuarios;
    }
    
}
