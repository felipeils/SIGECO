package cl.sigeco.Entidades;

import cl.sigeco.Entidades.Condominio;
import cl.sigeco.Entidades.Pago;
import cl.sigeco.Entidades.ReservaServicio;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.2.v20160113-rNA", date="2016-06-03T00:26:40")
@StaticMetamodel(Servicio.class)
public class Servicio_ { 

    public static volatile SingularAttribute<Servicio, String> nombre;
    public static volatile SingularAttribute<Servicio, BigInteger> estado;
    public static volatile ListAttribute<Servicio, Pago> pagoList;
    public static volatile SingularAttribute<Servicio, Date> creado;
    public static volatile SingularAttribute<Servicio, BigInteger> valorBase;
    public static volatile SingularAttribute<Servicio, BigDecimal> idServicio;
    public static volatile SingularAttribute<Servicio, Date> modificado;
    public static volatile ListAttribute<Servicio, ReservaServicio> reservaServicioList;
    public static volatile SingularAttribute<Servicio, String> detalle;
    public static volatile SingularAttribute<Servicio, Condominio> idCondominio;

}