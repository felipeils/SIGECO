package cl.sigeco.Entidades;

import cl.sigeco.Entidades.Personal;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.2.v20160113-rNA", date="2016-06-03T00:26:41")
@StaticMetamodel(TipoPersonal.class)
public class TipoPersonal_ { 

    public static volatile SingularAttribute<TipoPersonal, String> nombre;
    public static volatile ListAttribute<TipoPersonal, Personal> personalList;
    public static volatile SingularAttribute<TipoPersonal, BigInteger> estado;
    public static volatile SingularAttribute<TipoPersonal, Date> creado;
    public static volatile SingularAttribute<TipoPersonal, BigDecimal> idTipoPersonal;
    public static volatile SingularAttribute<TipoPersonal, Date> modificado;

}