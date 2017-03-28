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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Joe-Xidu
 */
@Entity
@Table(name = "SOLICITUD_USUARIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SolicitudUsuario.findAll", query = "SELECT s FROM SolicitudUsuario s"),
    @NamedQuery(name = "SolicitudUsuario.findByIdSolicitud", query = "SELECT s FROM SolicitudUsuario s WHERE s.idSolicitud = :idSolicitud"),
    @NamedQuery(name = "SolicitudUsuario.findByEstado", query = "SELECT s FROM SolicitudUsuario s WHERE s.estado = :estado"),
    @NamedQuery(name = "SolicitudUsuario.findByCreado", query = "SELECT s FROM SolicitudUsuario s WHERE s.creado = :creado"),
    @NamedQuery(name = "SolicitudUsuario.findByModificado", query = "SELECT s FROM SolicitudUsuario s WHERE s.modificado = :modificado"),
    @NamedQuery(name = "SolicitudUsuario.findByIdRevisado", query = "SELECT s FROM SolicitudUsuario s WHERE s.idRevisado = :idRevisado")})
public class SolicitudUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_SOLICITUD")
    private BigDecimal idSolicitud;
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
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_REVISADO")
    private BigInteger idRevisado;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne(optional = false)
    private Usuario idUsuario;
    @JoinColumn(name = "ID_CONDOMINIO", referencedColumnName = "ID_CONDOMINIO")
    @ManyToOne(optional = false)
    private Condominio idCondominio;

    public SolicitudUsuario() {
    }

    public SolicitudUsuario(BigDecimal idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public SolicitudUsuario(BigDecimal idSolicitud, BigInteger estado, Date creado, Date modificado, BigInteger idRevisado) {
        this.idSolicitud = idSolicitud;
        this.estado = estado;
        this.creado = creado;
        this.modificado = modificado;
        this.idRevisado = idRevisado;
    }

    public BigDecimal getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(BigDecimal idSolicitud) {
        this.idSolicitud = idSolicitud;
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

    public BigInteger getIdRevisado() {
        return idRevisado;
    }

    public void setIdRevisado(BigInteger idRevisado) {
        this.idRevisado = idRevisado;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
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
        hash += (idSolicitud != null ? idSolicitud.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SolicitudUsuario)) {
            return false;
        }
        SolicitudUsuario other = (SolicitudUsuario) object;
        if ((this.idSolicitud == null && other.idSolicitud != null) || (this.idSolicitud != null && !this.idSolicitud.equals(other.idSolicitud))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.sigeco.Entidades.SolicitudUsuario[ idSolicitud=" + idSolicitud + " ]";
    }
    
}
