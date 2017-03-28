package cl.sigeco.Entidades;

import cl.sigeco.Entidades.Pago;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.2.v20160113-rNA", date="2016-06-03T00:26:40")
@StaticMetamodel(GastoComun.class)
public class GastoComun_ { 

    public static volatile SingularAttribute<GastoComun, Integer> multa;
    public static volatile SingularAttribute<GastoComun, Integer> idGastoComun;
    public static volatile SingularAttribute<GastoComun, Integer> estado;
    public static volatile SingularAttribute<GastoComun, int> idVivienda;
    public static volatile ListAttribute<GastoComun, Pago> pagoList;
    public static volatile SingularAttribute<GastoComun, Integer> valor;
    public static volatile SingularAttribute<GastoComun, Date> creado;
    public static volatile SingularAttribute<GastoComun, Date> modificado;
    public static volatile SingularAttribute<GastoComun, Date> fechaEmision;
    public static volatile SingularAttribute<GastoComun, String> nomEstado;

}