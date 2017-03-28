package cl.sigeco.Entidades;

import cl.sigeco.Entidades.Condominio;
import cl.sigeco.Entidades.Usuario;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.2.v20160113-rNA", date="2016-06-03T00:26:41")
@StaticMetamodel(SolicitudUsuario.class)
public class SolicitudUsuario_ { 

    public static volatile SingularAttribute<SolicitudUsuario, BigDecimal> idSolicitud;
    public static volatile SingularAttribute<SolicitudUsuario, BigInteger> estado;
    public static volatile SingularAttribute<SolicitudUsuario, Usuario> idUsuario;
    public static volatile SingularAttribute<SolicitudUsuario, Date> creado;
    public static volatile SingularAttribute<SolicitudUsuario, Date> modificado;
    public static volatile SingularAttribute<SolicitudUsuario, BigInteger> idRevisado;
    public static volatile SingularAttribute<SolicitudUsuario, Condominio> idCondominio;

}