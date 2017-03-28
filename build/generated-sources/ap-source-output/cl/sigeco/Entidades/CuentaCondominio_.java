package cl.sigeco.Entidades;

import cl.sigeco.Entidades.Condominio;
import cl.sigeco.Entidades.Pago;
import cl.sigeco.Entidades.TipoCuenta;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.2.v20160113-rNA", date="2016-06-03T00:26:40")
@StaticMetamodel(CuentaCondominio.class)
public class CuentaCondominio_ { 

    public static volatile SingularAttribute<CuentaCondominio, BigInteger> rutCuenta;
    public static volatile SingularAttribute<CuentaCondominio, String> nombre;
    public static volatile SingularAttribute<CuentaCondominio, String> banco;
    public static volatile SingularAttribute<CuentaCondominio, BigDecimal> idCuentaCondominio;
    public static volatile SingularAttribute<CuentaCondominio, TipoCuenta> idTipoCuenta;
    public static volatile SingularAttribute<CuentaCondominio, BigInteger> estado;
    public static volatile ListAttribute<CuentaCondominio, Pago> pagoList;
    public static volatile SingularAttribute<CuentaCondominio, String> email;
    public static volatile SingularAttribute<CuentaCondominio, Date> creado;
    public static volatile SingularAttribute<CuentaCondominio, BigInteger> modificado;
    public static volatile ListAttribute<CuentaCondominio, Condominio> condominioList;
    public static volatile SingularAttribute<CuentaCondominio, BigInteger> cuenta;

}