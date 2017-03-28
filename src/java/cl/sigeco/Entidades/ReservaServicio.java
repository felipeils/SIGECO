/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.sigeco.Entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Joe-Xidu
 */
@Entity
@Table(name = "RESERVA_SERVICIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReservaServicio.findAll", query = "SELECT r FROM ReservaServicio r"),
    @NamedQuery(name = "ReservaServicio.findByIdReserva", query = "SELECT r FROM ReservaServicio r WHERE r.idReserva = :idReserva"),
    @NamedQuery(name = "ReservaServicio.findByServicioId", query = "SELECT r FROM ReservaServicio r WHERE r.servicioId = :servicioId"),
    @NamedQuery(name = "ReservaServicio.findByUsuarioId", query = "SELECT r FROM ReservaServicio r WHERE r.usuarioId = :usuarioId"),
    @NamedQuery(name = "ReservaServicio.findByFecha", query = "SELECT r FROM ReservaServicio r WHERE r.fecha = :fecha"),
    @NamedQuery(name = "ReservaServicio.findByHoraInicio", query = "SELECT r FROM ReservaServicio r WHERE r.horaInicio = :horaInicio"),
    @NamedQuery(name = "ReservaServicio.findByHoraTermino", query = "SELECT r FROM ReservaServicio r WHERE r.horaTermino = :horaTermino"),
    @NamedQuery(name = "ReservaServicio.findByValor", query = "SELECT r FROM ReservaServicio r WHERE r.valor = :valor"),
    @NamedQuery(name = "ReservaServicio.findByMulta", query = "SELECT r FROM ReservaServicio r WHERE r.multa = :multa"),
    @NamedQuery(name = "ReservaServicio.findByObservaciones", query = "SELECT r FROM ReservaServicio r WHERE r.observaciones = :observaciones"),
    @NamedQuery(name = "ReservaServicio.findByEstado", query = "SELECT r FROM ReservaServicio r WHERE r.estado = :estado"),
    @NamedQuery(name = "ReservaServicio.findByCreado", query = "SELECT r FROM ReservaServicio r WHERE r.creado = :creado"),
    @NamedQuery(name = "ReservaServicio.findByModificado", query = "SELECT r FROM ReservaServicio r WHERE r.modificado = :modificado")})
public class ReservaServicio implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_RESERVA")
    private int idReserva;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SERVICIO_ID")
    private int servicioId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "USUARIO_ID")
    private int usuarioId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "HORA_INICIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date horaInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "HORA_TERMINO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date horaTermino;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALOR")
    private int valor;
    @Column(name = "MULTA")
    private int multa;
    @Size(max = 255)
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADO")
    private int estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CREADO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MODIFICADO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modificado;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne(optional = false)
    private int idUsuario;
    @JoinColumn(name = "ID_SERVICIO", referencedColumnName = "ID_SERVICIO")
    @ManyToOne(optional = false)
    private int idServicio;
    private String nomServicio;
    private String nomEstado;

    public ReservaServicio() {
    }

    public ReservaServicio(int idReserva) {
        this.idReserva = idReserva;
    }

    public ReservaServicio(int idReserva, int servicioId, int usuarioId, Date fecha, Date horaInicio, Date horaTermino, int valor, int estado, Date creado, Date modificado) {
        this.idReserva = idReserva;
        this.servicioId = servicioId;
        this.usuarioId = usuarioId;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaTermino = horaTermino;
        this.valor = valor;
        this.estado = estado;
        this.creado = creado;
        this.modificado = modificado;
    }

    public String getNomServicio() {
        return nomServicio;
    }

    public void setNomServicio(String nomServicio) {
        this.nomServicio = nomServicio;
    }

    public String getNomEstado() {
        return nomEstado;
    }

    public void setNomEstado(String nomEstado) {
        this.nomEstado = nomEstado;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public int getServicioId() {
        return servicioId;
    }

    public void setServicioId(int servicioId) {
        this.servicioId = servicioId;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getHoraTermino() {
        return horaTermino;
    }

    public void setHoraTermino(Date horaTermino) {
        this.horaTermino = horaTermino;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getMulta() {
        return multa;
    }

    public void setMulta(int multa) {
        this.multa = multa;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Date getCreado() {
        return creado;
    }

    public void setCreado(Date creado) {
        this.creado = creado;
    }

    public Date getModificado() {
        return modificado;
    }

    public void setModificado(Date modificado) {
        this.modificado = modificado;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 13 * hash + this.idReserva;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ReservaServicio other = (ReservaServicio) obj;
        if (this.idReserva != other.idReserva) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "{\"data\":{" + 
                "\"idReserva\":" + idReserva + 
                ", \"servicioId\":" + servicioId + 
                ", \"usuarioId\":" + usuarioId + 
                ", \"fecha\":\"" + fecha + "\"" +
                ", \"horaInicio\":\"" + horaInicio + "\"" +
                ", \"horaTermino\":\"" + horaTermino + "\"" +
                ", \"valor\":" + valor + 
                ", \"multa\":" + multa + 
                ", \"observaciones\":\"" + observaciones + "\"" +
                ", \"estado\":" + estado + 
                ", \"creado\":\"" + creado + "\"" +
                ", \"modificado\":\"" + modificado + "\"" +
                ", \"idUsuario\":" + idUsuario + 
                ", \"idServicio\":" + idServicio + "}}";
    }

    
}
