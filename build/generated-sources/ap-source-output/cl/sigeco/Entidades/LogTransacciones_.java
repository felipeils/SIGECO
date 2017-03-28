package cl.sigeco.Entidades;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.2.v20160113-rNA", date="2016-06-03T00:26:40")
@StaticMetamodel(LogTransacciones.class)
public class LogTransacciones_ { 

    public static volatile SingularAttribute<LogTransacciones, BigInteger> idRegistro;
    public static volatile SingularAttribute<LogTransacciones, BigInteger> idUsuario;
    public static volatile SingularAttribute<LogTransacciones, Date> creado;
    public static volatile SingularAttribute<LogTransacciones, BigDecimal> idTransaccion;
    public static volatile SingularAttribute<LogTransacciones, String> detalle;
    public static volatile SingularAttribute<LogTransacciones, String> tabla;
    public static volatile SingularAttribute<LogTransacciones, String> tipoTransaccion;

}