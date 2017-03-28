package cl.sigeco.Entidades;

import cl.sigeco.Entidades.Pago;
import cl.sigeco.Entidades.Residente;
import cl.sigeco.Entidades.TipoCuenta;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.2.v20160113-rNA", date="2016-06-03T00:26:40")
@StaticMetamodel(CuentaResidente.class)
public class CuentaResidente_ { 

    public static volatile SingularAttribute<CuentaResidente, BigInteger> rutCuenta;
    public static volatile SingularAttribute<CuentaResidente, String> nombre;
    public static volatile SingularAttribute<CuentaResidente, String> banco;
    public static volatile SingularAttribute<CuentaResidente, TipoCuenta> idTipoCuenta;
    public static volatile SingularAttribute<CuentaResidente, BigDecimal> idCuentaResidente;
    public static volatile SingularAttribute<CuentaResidente, BigInteger> estado;
    public static volatile ListAttribute<CuentaResidente, Pago> pagoList;
    public static volatile SingularAttribute<CuentaResidente, String> email;
    public static volatile SingularAttribute<CuentaResidente, Date> creado;
    public static volatile SingularAttribute<CuentaResidente, BigInteger> modificado;
    public static volatile ListAttribute<CuentaResidente, Residente> residenteList;
    public static volatile SingularAttribute<CuentaResidente, BigInteger> cuenta;

}