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
public class MantenedorMorosidad implements Serializable, BaseController {

    public MantenedorMorosidad() {
    }

    @RequestMapping(value = "/morosidad.htm", method = RequestMethod.GET)
    public String servicios(ModelMap map, HttpServletRequest request,
            HttpServletResponse response) {
        List<Estado> estados = this.getEstados();

        map.put("estados", estados);
        return "morosidad/revisarMorosidad";
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
    
}
