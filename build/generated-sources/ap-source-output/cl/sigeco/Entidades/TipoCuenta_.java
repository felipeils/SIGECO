package cl.sigeco.Entidades;

import cl.sigeco.Entidades.CuentaCondominio;
import cl.sigeco.Entidades.CuentaResidente;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.2.v20160113-rNA", date="2016-06-03T00:26:40")
@StaticMetamodel(TipoCuenta.class)
public class TipoCuenta_ { 

    public static volatile SingularAttribute<TipoCuenta, String> nombre;
    public static volatile SingularAttribute<TipoCuenta, Integer> idTipoCuenta;
    public static volatile SingularAttribute<TipoCuenta, Integer> estado;
    public static volatile SingularAttribute<TipoCuenta, Date> creado;
    public static volatile SingularAttribute<TipoCuenta, Date> modificado;
    public static volatile ListAttribute<TipoCuenta, CuentaCondominio> cuentaCondominioList;
    public static volatile ListAttribute<TipoCuenta, CuentaResidente> cuentaResidenteList;
    public static volatile SingularAttribute<TipoCuenta, String> nomEstado;

}