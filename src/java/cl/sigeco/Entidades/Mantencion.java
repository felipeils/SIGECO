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
@Table(name = "MANTENCION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mantencion.findAll", query = "SELECT m FROM Mantencion m"),
    @NamedQuery(name = "Mantencion.findByIdMantencion", query = "SELECT m FROM Mantencion m WHERE m.idMantencion = :idMantencion"),
    @NamedQuery(name = "Mantencion.findByFecha", query = "SELECT m FROM Mantencion m WHERE m.fecha = :fecha"),
    @NamedQuery(name = "Mantencion.findByDetalle", query = "SELECT m FROM Mantencion m WHERE m.detalle = :detalle"),
    @NamedQuery(name = "Mantencion.findByObservacion", query = "SELECT m FROM Mantencion m WHERE m.observacion = :observacion"),
    @NamedQuery(name = "Mantencion.findByValor", query = "SELECT m FROM Mantencion m WHERE m.valor = :valor"),
    @NamedQuery(name = "Mantencion.findByEstado", query = "SELECT m FROM Mantencion m WHERE m.estado = :estado"),
    @NamedQuery(name = "Mantencion.findByCreado", query = "SELECT m FROM Mantencion m WHERE m.creado = :creado"),
    @NamedQuery(name = "Mantencion.findByModificado", query = "SELECT m FROM Mantencion m WHERE m.modificado = :modificado")})
public class Mantencion implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_MANTENCION")
    private BigDecimal idMantencion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "DETALLE")
    private String detalle;
    @Size(max = 255)
    @Column(name = "OBSERVACION")
    private String observacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALOR")
    private BigInteger valor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADO")
    private BigInteger estado;
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
    @JoinColumn(name = "ID_PROVEEDOR", referencedColumnName = "ID_PROVEEDOR")
    @ManyToOne(optional = false)
    private Proveedor idProveedor;
    @JoinColumn(name = "ID_CONDOMINIO", referencedColumnName = "ID_CONDOMINIO")
    @ManyToOne(optional = false)
    private Condominio idCondominio;

    public Mantencion() {
    }

    public Mantencion(BigDecimal idMantencion) {
        this.idMantencion = idMantencion;
    }

    public Mantencion(BigDecimal idMantencion, Date fecha, String detalle, BigInteger valor, BigInteger estado, Date creado, Date modificado) {
        this.idMantencion = idMantencion;
        this.fecha = fecha;
        this.detalle = detalle;
        this.valor = valor;
        this.estado = estado;
        this.creado = creado;
        this.modificado = modificado;
    }

    public BigDecimal getIdMantencion() {
        return idMantencion;
    }

    public void setIdMantencion(BigDecimal idMantencion) {
        this.idMantencion = idMantencion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public BigInteger getValor() {
        return valor;
    }

    public void setValor(BigInteger valor) {
        this.valor = valor;
    }

    public BigInteger getEstado() {
        return estado;
    }

    public void setEstado(BigInteger estado) {
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

    public Proveedor getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Proveedor idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Condominio getIdCondominio() {
        return idCondominio;
    }

    public void setIdCondominio(Condominio idCondominio) {
        this.idCondominio = idCondominio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMantencion != null ? idMantencion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mantencion)) {
            return false;
        }
        Mantencion other = (Mantencion) object;
        if ((this.idMantencion == null && other.idMantencion != null) || (this.idMantencion != null && !this.idMantencion.equals(other.idMantencion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.sigeco.Entidades.Mantencion[ idMantencion=" + idMantencion + " ]";
    }
    
}
