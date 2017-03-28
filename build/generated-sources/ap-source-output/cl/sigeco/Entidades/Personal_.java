package cl.sigeco.Entidades;

import cl.sigeco.Entidades.Condominio;
import cl.sigeco.Entidades.EmpresaContratista;
import cl.sigeco.Entidades.Persona;
import cl.sigeco.Entidades.TipoPersonal;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.2.v20160113-rNA", date="2016-06-03T00:26:41")
@StaticMetamodel(Personal.class)
public class Personal_ { 

    public static volatile SingularAttribute<Personal, BigDecimal> idPersonal;
    public static volatile SingularAttribute<Personal, String> apPaterno;
    public static volatile SingularAttribute<Personal, Date> creado;
    public static volatile SingularAttribute<Personal, TipoPersonal> idTipoPersonal;
    public static volatile SingularAttribute<Personal, Date> modificado;
    public static volatile SingularAttribute<Personal, String> apMaterno;
    public static volatile SingularAttribute<Personal, Condominio> idCondominio;
    public static volatile SingularAttribute<Personal, String> nombre;
    public static volatile SingularAttribute<Personal, BigInteger> estado;
    public static volatile SingularAttribute<Personal, String> email;
    public static volatile SingularAttribute<Personal, BigInteger> rut;
    public static volatile SingularAttribute<Personal, BigInteger> tipo;
    public static volatile SingularAttribute<Personal, EmpresaContratista> idEmpresaContratista;
    public static volatile SingularAttribute<Personal, Persona> idPersona;
    public static volatile SingularAttribute<Personal, String> dirección;

}