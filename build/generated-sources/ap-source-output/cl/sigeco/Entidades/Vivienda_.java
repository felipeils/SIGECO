package cl.sigeco.Entidades;

import cl.sigeco.Entidades.ConsumoAgua;
import cl.sigeco.Entidades.ConsumoLuz;
import cl.sigeco.Entidades.GastoComun;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.2.v20160113-rNA", date="2016-06-03T00:26:40")
@StaticMetamodel(Vivienda.class)
public class Vivienda_ { 

    public static volatile SingularAttribute<Vivienda, String> sector;
    public static volatile SingularAttribute<Vivienda, Integer> idVivienda;
    public static volatile SingularAttribute<Vivienda, Date> creado;
    public static volatile SingularAttribute<Vivienda, Date> modificado;
    public static volatile SingularAttribute<Vivienda, Integer> piso;
    public static volatile SingularAttribute<Vivienda, int> residente;
    public static volatile SingularAttribute<Vivienda, Integer> metrosCuadrados;
    public static volatile ListAttribute<Vivienda, ConsumoAgua> consumoAguaList;
    public static volatile SingularAttribute<Vivienda, int> idCondominio;
    public static volatile SingularAttribute<Vivienda, String> nomEstado;
    public static volatile SingularAttribute<Vivienda, Integer> numero;
    public static volatile SingularAttribute<Vivienda, int> idTipoVivienda;
    public static volatile SingularAttribute<Vivienda, Integer> estado;
    public static volatile SingularAttribute<Vivienda, String> nomCondominio;
    public static volatile ListAttribute<Vivienda, ConsumoLuz> consumoLuzList;
    public static volatile SingularAttribute<Vivienda, String> nomTipoVivienda;
    public static volatile ListAttribute<Vivienda, GastoComun> gastoComunList;

}