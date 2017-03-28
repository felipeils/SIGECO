package cl.sigeco.Entidades;

import cl.sigeco.Entidades.Condominio;
import cl.sigeco.Entidades.Proveedor;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.2.v20160113-rNA", date="2016-06-03T00:26:41")
@StaticMetamodel(Mantencion.class)
public class Mantencion_ { 

    public static volatile SingularAttribute<Mantencion, String> observacion;
    public static volatile SingularAttribute<Mantencion, BigInteger> estado;
    public static volatile SingularAttribute<Mantencion, Date> fecha;
    public static volatile SingularAttribute<Mantencion, BigInteger> valor;
    public static volatile SingularAttribute<Mantencion, Date> creado;
    public static volatile SingularAttribute<Mantencion, Proveedor> idProveedor;
    public static volatile SingularAttribute<Mantencion, BigDecimal> idMantencion;
    public static volatile SingularAttribute<Mantencion, Date> modificado;
    public static volatile SingularAttribute<Mantencion, String> detalle;
    public static volatile SingularAttribute<Mantencion, Condominio> idCondominio;

}