package cl.sigeco.Entidades;

import cl.sigeco.Entidades.CuentaCondominio;
import cl.sigeco.Entidades.CuentaResidente;
import cl.sigeco.Entidades.GastoComun;
import cl.sigeco.Entidades.Servicio;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.2.v20160113-rNA", date="2016-06-03T00:26:41")
@StaticMetamodel(Pago.class)
public class Pago_ { 

    public static volatile SingularAttribute<Pago, GastoComun> idGastoComun;
    public static volatile SingularAttribute<Pago, CuentaCondominio> idCuentaCondominio;
    public static volatile SingularAttribute<Pago, CuentaResidente> idCuentaResidente;
    public static volatile SingularAttribute<Pago, BigInteger> valor;
    public static volatile SingularAttribute<Pago, Date> creado;
    public static volatile SingularAttribute<Pago, BigInteger> comprobante;
    public static volatile SingularAttribute<Pago, Date> modificado;
    public static volatile SingularAttribute<Pago, Servicio> idServicio;
    public static volatile SingularAttribute<Pago, BigDecimal> idPago;
    public static volatile SingularAttribute<Pago, Date> fechaPago;

}