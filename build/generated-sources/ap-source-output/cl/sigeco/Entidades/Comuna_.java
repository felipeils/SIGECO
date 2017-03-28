package cl.sigeco.Entidades;

import cl.sigeco.Entidades.Condominio;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.2.v20160113-rNA", date="2016-06-03T00:26:41")
@StaticMetamodel(Comuna.class)
public class Comuna_ { 

    public static volatile SingularAttribute<Comuna, String> nombre;
    public static volatile SingularAttribute<Comuna, Integer> estado;
    public static volatile SingularAttribute<Comuna, Integer> idComuna;
    public static volatile SingularAttribute<Comuna, Date> creado;
    public static volatile SingularAttribute<Comuna, Date> modificado;
    public static volatile ListAttribute<Comuna, Condominio> condominioList;

}