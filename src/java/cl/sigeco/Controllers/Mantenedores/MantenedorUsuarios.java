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
import cl.sigeco.Entidades.Residente;
import cl.sigeco.Entidades.Usuario;
import cl.sigeco.Persistencia.CondominioDAO;
import cl.sigeco.Persistencia.EstadoDAO;
import cl.sigeco.Persistencia.PerfilDAO;
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
public class MantenedorUsuarios implements Serializable, BaseController {
    
    UsuarioDAO udao;
    
    public MantenedorUsuarios() {
    }

    @RequestMapping(value = "/mantenedorUsuarios.htm", method = RequestMethod.GET)
    public String mantenedor(ModelMap map) {
        List<Usuario> usuarios = new ArrayList();
        List<Residente> residentes = this.getResidentes();
        List<Perfil> perfiles = this.getPerfiles();
        List<Condominio> condominios = this.getCondominios();
        List<Estado> estados = this.getEstados();
        try {
            udao = new UsuarioDAO();
            usuarios = udao.buscarTodos();
            udao.close();
        } catch (IOException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(MantenedorUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }

        map.put("estados", estados);
        map.put("perfiles", perfiles);
        map.put("condominios", condominios);
        map.put("residentes", residentes);
        map.put("usuarios", usuarios);
        return "mantenedores/mantenedorUsuarios";
    }

    @RequestMapping(value = "/getUsuario.htm", method = RequestMethod.POST)
    public String get(ModelMap map, HttpServletRequest request,
            HttpServletResponse response) {
        Usuario usuario = new Usuario();
        try {
            udao = new UsuarioDAO();
            usuario = udao.getUsuario(Integer.valueOf(request.getParameter("id")));
            udao.close();
        } catch (IOException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(MantenedorUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }

        map.put("json", usuario.toString());
        return "../../include/json";
    }

    @RequestMapping(value = "/eliminaUsuario.htm", method = RequestMethod.POST)
    public String delete(ModelMap map, HttpServletRequest request,
            HttpServletResponse response) {
        String json = "{\"response\":0}";
        try {
            udao = new UsuarioDAO();
            if (udao.borrar(Integer.valueOf(request.getParameter("id"))))
                json = "{\"response\":1}";
            udao.close();
        } catch (IOException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(MantenedorUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }

        map.put("json", json);
        return "../../include/json";
    }

    @RequestMapping(value = "/nuevoUsuario.htm", method = RequestMethod.POST)
    public String add(ModelMap map, HttpServletRequest request,
            HttpServletResponse response) {
        Usuario usuario = new Usuario();
        String json = "{\"response\":0}";
        try {
            udao = new UsuarioDAO();
            
            usuario.setUsuario(request.getParameter("usuario"));
            usuario.setPass(request.getParameter("pass"));
            usuario.setEmail(request.getParameter("email"));
            usuario.setIdResidente(
                (!request.getParameter("idResidente").equals("null"))
                ? Integer.valueOf(request.getParameter("idResidente")) : 0
            );
            usuario.setEstado(Integer.valueOf(request.getParameter("estado")));
            usuario.setIdCondominio(
                    (!request.getParameter("idCondominio").equals("null"))
                ? Integer.valueOf(request.getParameter("idCondominio")) : 0
            );
            usuario.setIdPerfil(
                    (!request.getParameter("idPerfil").equals("null"))
                ? Integer.valueOf(request.getParameter("idPerfil")) : 0
            );
            if (Integer.valueOf(request.getParameter("idUsuario")) > 0){
                usuario.setIdUsuario(Integer.valueOf(request.getParameter("idUsuario")));
                if (udao.modificar(usuario))
                    json = "{\"response\":1}";
            }
            else {
                if (udao.agregar(usuario))
                    json = "{\"response\":1}";
            }
            udao.close();
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
    
    public List<Perfil> getPerfiles() {
        List<Perfil> perfiles = new ArrayList();
        PerfilDAO pdao;
        try {
            pdao = new PerfilDAO();
            perfiles = pdao.getSelect();
            pdao.close();
        } catch (IOException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(MantenedorUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        return perfiles;
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
    
    public List<Residente> getResidentes() {
        List<Residente> residentes = new ArrayList();
        /*ResidenteDAO rdao;
        try {
            rdao = new ResidenteDAO();
            residentes = rdao.buscarTodos();
            rdao.close();
        } catch (IOException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(MantenedorCondominios.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        return residentes;
    }
}

