package cl.sigeco.Entidades;

import cl.sigeco.Entidades.Condominio;
import cl.sigeco.Entidades.Vivienda;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.2.v20160113-rNA", date="2016-06-03T00:26:40")
@StaticMetamodel(ConsumoLuz.class)
public class ConsumoLuz_ { 

    public static volatile SingularAttribute<ConsumoLuz, Vivienda> idVivienda;
    public static volatile SingularAttribute<ConsumoLuz, Date> fechaLectura;
    public static volatile SingularAttribute<ConsumoLuz, Date> creado;
    public static volatile SingularAttribute<ConsumoLuz, BigInteger> valorUnitario;
    public static volatile SingularAttribute<ConsumoLuz, Date> modificado;
    public static volatile SingularAttribute<ConsumoLuz, BigInteger> lecturaActual;
    public static volatile SingularAttribute<ConsumoLuz, BigDecimal> idConsumoLuz;
    public static volatile SingularAttribute<ConsumoLuz, BigInteger> lecturaAnterior;
    public static volatile SingularAttribute<ConsumoLuz, Condominio> idCondominio;

}