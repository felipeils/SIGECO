package cl.sigeco.Entidades;

import cl.sigeco.Entidades.Vivienda;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.2.v20160113-rNA", date="2016-06-03T00:26:40")
@StaticMetamodel(TipoVivienda.class)
public class TipoVivienda_ { 

    public static volatile SingularAttribute<TipoVivienda, String> nombre;
    public static volatile SingularAttribute<TipoVivienda, Integer> idTipoVivienda;
    public static volatile SingularAttribute<TipoVivienda, Integer> estado;
    public static volatile SingularAttribute<TipoVivienda, Date> creado;
    public static volatile SingularAttribute<TipoVivienda, Date> modificado;
    public static volatile ListAttribute<TipoVivienda, Vivienda> viviendaList;
    public static volatile SingularAttribute<TipoVivienda, Double> participacion;
    public static volatile SingularAttribute<TipoVivienda, String> nomEstado;

}