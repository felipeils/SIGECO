package cl.sigeco.Entidades;

import cl.sigeco.Entidades.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.2.v20160113-rNA", date="2016-06-03T00:26:40")
@StaticMetamodel(Residente.class)
public class Residente_ { 

    public static volatile SingularAttribute<Residente, Integer> idResidente;
    public static volatile SingularAttribute<Residente, int> idCuentaResidente;
    public static volatile SingularAttribute<Residente, Integer> estado;
    public static volatile SingularAttribute<Residente, int> idVivienda;
    public static volatile SingularAttribute<Residente, String> nomECuentaResidente;
    public static volatile SingularAttribute<Residente, Usuario> usuario;
    public static volatile SingularAttribute<Residente, Date> creado;
    public static volatile SingularAttribute<Residente, String> nomPropietario;
    public static volatile SingularAttribute<Residente, Date> modificado;
    public static volatile SingularAttribute<Residente, String> nomPersona;
    public static volatile SingularAttribute<Residente, Integer> propietario;
    public static volatile SingularAttribute<Residente, int> idPersona;
    public static volatile SingularAttribute<Residente, String> nomEstado;

}