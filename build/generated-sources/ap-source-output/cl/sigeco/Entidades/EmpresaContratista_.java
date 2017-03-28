package cl.sigeco.Entidades;

import cl.sigeco.Entidades.Personal;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.2.v20160113-rNA", date="2016-06-03T00:26:40")
@StaticMetamodel(EmpresaContratista.class)
public class EmpresaContratista_ { 

    public static volatile SingularAttribute<EmpresaContratista, BigInteger> rutCuenta;
    public static volatile SingularAttribute<EmpresaContratista, String> nombre;
    public static volatile SingularAttribute<EmpresaContratista, String> banco;
    public static volatile ListAttribute<EmpresaContratista, Personal> personalList;
    public static volatile SingularAttribute<EmpresaContratista, BigInteger> estado;
    public static volatile SingularAttribute<EmpresaContratista, String> email;
    public static volatile SingularAttribute<EmpresaContratista, Date> creado;
    public static volatile SingularAttribute<EmpresaContratista, BigDecimal> idEmpresaContratista;
    public static volatile SingularAttribute<EmpresaContratista, BigInteger> modificado;
    public static volatile SingularAttribute<EmpresaContratista, BigInteger> cuenta;

}