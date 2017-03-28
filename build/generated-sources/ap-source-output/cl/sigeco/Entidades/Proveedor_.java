package cl.sigeco.Entidades;

import cl.sigeco.Entidades.Mantencion;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.2.v20160113-rNA", date="2016-06-03T00:26:40")
@StaticMetamodel(Proveedor.class)
public class Proveedor_ { 

    public static volatile SingularAttribute<Proveedor, String> nombre;
    public static volatile ListAttribute<Proveedor, Mantencion> mantencionList;
    public static volatile SingularAttribute<Proveedor, BigDecimal> idProveedor;
    public static volatile SingularAttribute<Proveedor, String> email;
    public static volatile SingularAttribute<Proveedor, Date> creado;
    public static volatile SingularAttribute<Proveedor, BigInteger> rut;
    public static volatile SingularAttribute<Proveedor, Date> modificado;
    public static volatile SingularAttribute<Proveedor, String> dirección;

}