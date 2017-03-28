/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.sigeco.Entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "LOG_TRANSACCIONES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LogTransacciones.findAll", query = "SELECT l FROM LogTransacciones l"),
    @NamedQuery(name = "LogTransacciones.findByIdTransaccion", query = "SELECT l FROM LogTransacciones l WHERE l.idTransaccion = :idTransaccion"),
    @NamedQuery(name = "LogTransacciones.findByIdRegistro", query = "SELECT l FROM LogTransacciones l WHERE l.idRegistro = :idRegistro"),
    @NamedQuery(name = "LogTransacciones.findByIdUsuario", query = "SELECT l FROM LogTransacciones l WHERE l.idUsuario = :idUsuario"),
    @NamedQuery(name = "LogTransacciones.findByTabla", query = "SELECT l FROM LogTransacciones l WHERE l.tabla = :tabla"),
    @NamedQuery(name = "LogTransacciones.findByTipoTransaccion", query = "SELECT l FROM LogTransacciones l WHERE l.tipoTransaccion = :tipoTransaccion"),
    @NamedQuery(name = "LogTransacciones.findByDetalle", query = "SELECT l FROM LogTransacciones l WHERE l.detalle = :detalle"),
    @NamedQuery(name = "LogTransacciones.findByCreado", query = "SELECT l FROM LogTransacciones l WHERE l.creado = :creado")})
public class LogTransacciones implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_TRANSACCION")
    private BigDecimal idTransaccion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_REGISTRO")
    private BigInteger idRegistro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_USUARIO")
    private BigInteger idUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "TABLA")
    private String tabla;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "TIPO_TRANSACCION")
    private String tipoTransaccion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "DETALLE")
    private String detalle;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CREADO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creado;

    public LogTransacciones() {
    }

    public LogTransacciones(BigDecimal idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public LogTransacciones(BigDecimal idTransaccion, BigInteger idRegistro, BigInteger idUsuario, String tabla, String tipoTransaccion, String detalle, Date creado) {
        this.idTransaccion = idTransaccion;
        this.idRegistro = idRegistro;
        this.idUsuario = idUsuario;
        this.tabla = tabla;
        this.tipoTransaccion = tipoTransaccion;
        this.detalle = detalle;
        this.creado = creado;
    }

    public BigDecimal getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(BigDecimal idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public BigInteger getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(BigInteger idRegistro) {
        this.idRegistro = idRegistro;
    }

    public BigInteger getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(BigInteger idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getTabla() {
        return tabla;
    }

    public void setTabla(String tabla) {
        this.tabla = tabla;
    }

    public String getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(String tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Date getCreado() {
        return creado;
    }

    public void setCreado(Date creado) {
        this.creado = creado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTransaccion != null ? idTransaccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LogTransacciones)) {
            return false;
        }
        LogTransacciones other = (LogTransacciones) object;
        if ((this.idTransaccion == null && other.idTransaccion != null) || (this.idTransaccion != null && !this.idTransaccion.equals(other.idTransaccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.sigeco.Entidades.LogTransacciones[ idTransaccion=" + idTransaccion + " ]";
    }
    
}
