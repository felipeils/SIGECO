package cl.sigeco.Entidades;

import cl.sigeco.Entidades.Condominio;
import cl.sigeco.Entidades.Vivienda;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.2.v20160113-rNA", date="2016-06-03T00:26:41")
@StaticMetamodel(ConsumoAgua.class)
public class ConsumoAgua_ { 

    public static volatile SingularAttribute<ConsumoAgua, Vivienda> idVivienda;
    public static volatile SingularAttribute<ConsumoAgua, Date> fechaLectura;
    public static volatile SingularAttribute<ConsumoAgua, Date> creado;
    public static volatile SingularAttribute<ConsumoAgua, BigInteger> valorUnitario;
    public static volatile SingularAttribute<ConsumoAgua, BigDecimal> idConsumoAgua;
    public static volatile SingularAttribute<ConsumoAgua, Date> modificado;
    public static volatile SingularAttribute<ConsumoAgua, BigInteger> lecturaActual;
    public static volatile SingularAttribute<ConsumoAgua, BigInteger> lecturaAnterior;
    public static volatile SingularAttribute<ConsumoAgua, Condominio> idCondominio;

}