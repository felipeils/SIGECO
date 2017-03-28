package cl.sigeco.Entidades;

import cl.sigeco.Entidades.ConsumoAgua;
import cl.sigeco.Entidades.ConsumoLuz;
import cl.sigeco.Entidades.Mantencion;
import cl.sigeco.Entidades.Personal;
import cl.sigeco.Entidades.Servicio;
import cl.sigeco.Entidades.SolicitudUsuario;
import cl.sigeco.Entidades.Usuario;
import cl.sigeco.Entidades.Vivienda;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.2.v20160113-rNA", date="2016-06-03T00:26:40")
@StaticMetamodel(Condominio.class)
public class Condominio_ { 

    public static volatile SingularAttribute<Condominio, String> direccion;
    public static volatile SingularAttribute<Condominio, int> idCuentaCondominio;
    public static volatile ListAttribute<Condominio, Mantencion> mantencionList;
    public static volatile SingularAttribute<Condominio, int> idComuna;
    public static volatile SingularAttribute<Condominio, Date> creado;
    public static volatile SingularAttribute<Condominio, Date> modificado;
    public static volatile ListAttribute<Condominio, ConsumoAgua> consumoAguaList;
    public static volatile ListAttribute<Condominio, Vivienda> viviendaList;
    public static volatile SingularAttribute<Condominio, Integer> idCondominio;
    public static volatile SingularAttribute<Condominio, String> nomEstado;
    public static volatile ListAttribute<Condominio, SolicitudUsuario> solicitudUsuarioList;
    public static volatile SingularAttribute<Condominio, String> nombre;
    public static volatile ListAttribute<Condominio, Servicio> servicioList;
    public static volatile SingularAttribute<Condominio, String> comuna;
    public static volatile SingularAttribute<Condominio, String> representante;
    public static volatile ListAttribute<Condominio, Personal> personalList;
    public static volatile SingularAttribute<Condominio, Integer> estado;
    public static volatile SingularAttribute<Condominio, Integer> representanteId;
    public static volatile ListAttribute<Condominio, Usuario> usuarioList;
    public static volatile ListAttribute<Condominio, ConsumoLuz> consumoLuzList;
    public static volatile SingularAttribute<Condominio, Integer> cuenta;

}