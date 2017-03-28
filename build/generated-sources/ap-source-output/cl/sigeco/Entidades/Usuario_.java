package cl.sigeco.Entidades;

import cl.sigeco.Entidades.ReservaServicio;
import cl.sigeco.Entidades.SolicitudUsuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.2.v20160113-rNA", date="2016-06-03T00:26:40")
@StaticMetamodel(Usuario.class)
public class Usuario_ { 

    public static volatile SingularAttribute<Usuario, String> usuario;
    public static volatile SingularAttribute<Usuario, Date> creado;
    public static volatile SingularAttribute<Usuario, Date> modificado;
    public static volatile ListAttribute<Usuario, ReservaServicio> reservaServicioList;
    public static volatile SingularAttribute<Usuario, String> nomPerfil;
    public static volatile SingularAttribute<Usuario, int> idCondominio;
    public static volatile SingularAttribute<Usuario, String> nomEstado;
    public static volatile SingularAttribute<Usuario, String> pass;
    public static volatile ListAttribute<Usuario, SolicitudUsuario> solicitudUsuarioList;
    public static volatile SingularAttribute<Usuario, int> idResidente;
    public static volatile SingularAttribute<Usuario, int> idPerfil;
    public static volatile SingularAttribute<Usuario, Integer> estado;
    public static volatile SingularAttribute<Usuario, Integer> idUsuario;
    public static volatile SingularAttribute<Usuario, String> nomResidente;
    public static volatile SingularAttribute<Usuario, String> email;
    public static volatile SingularAttribute<Usuario, String> nomCondominio;

}