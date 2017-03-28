package cl.sigeco.Entidades;

import cl.sigeco.Entidades.Servicio;
import cl.sigeco.Entidades.Usuario;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.2.v20160113-rNA", date="2016-06-03T00:26:40")
@StaticMetamodel(ReservaServicio.class)
public class ReservaServicio_ { 

    public static volatile SingularAttribute<ReservaServicio, Date> fecha;
    public static volatile SingularAttribute<ReservaServicio, Date> horaInicio;
    public static volatile SingularAttribute<ReservaServicio, BigDecimal> idReserva;
    public static volatile SingularAttribute<ReservaServicio, Date> creado;
    public static volatile SingularAttribute<ReservaServicio, Servicio> idServicio;
    public static volatile SingularAttribute<ReservaServicio, Date> modificado;
    public static volatile SingularAttribute<ReservaServicio, BigInteger> servicioId;
    public static volatile SingularAttribute<ReservaServicio, Date> horaTermino;
    public static volatile SingularAttribute<ReservaServicio, String> observaciones;
    public static volatile SingularAttribute<ReservaServicio, BigInteger> usuarioId;
    public static volatile SingularAttribute<ReservaServicio, BigInteger> multa;
    public static volatile SingularAttribute<ReservaServicio, BigInteger> estado;
    public static volatile SingularAttribute<ReservaServicio, Usuario> idUsuario;
    public static volatile SingularAttribute<ReservaServicio, BigInteger> valor;

}