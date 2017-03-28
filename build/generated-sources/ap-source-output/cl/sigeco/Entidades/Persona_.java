package cl.sigeco.Entidades;

import cl.sigeco.Entidades.Personal;
import cl.sigeco.Entidades.Residente;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.2.v20160113-rNA", date="2016-06-03T00:26:40")
@StaticMetamodel(Persona.class)
public class Persona_ { 

    public static volatile SingularAttribute<Persona, String> nombre;
    public static volatile ListAttribute<Persona, Personal> personalList;
    public static volatile SingularAttribute<Persona, BigInteger> estado;
    public static volatile SingularAttribute<Persona, String> apPaterno;
    public static volatile SingularAttribute<Persona, Date> creado;
    public static volatile SingularAttribute<Persona, BigInteger> rut;
    public static volatile SingularAttribute<Persona, Date> modificado;
    public static volatile SingularAttribute<Persona, String> apMaterno;
    public static volatile ListAttribute<Persona, Residente> residenteList;
    public static volatile SingularAttribute<Persona, BigDecimal> idPersona;

}