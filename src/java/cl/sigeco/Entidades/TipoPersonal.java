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
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Joe-Xidu
 */
@Entity
@Table(name = "TIPO_PERSONAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoPersonal.findAll", query = "SELECT t FROM TipoPersonal t"),
    @NamedQuery(name = "TipoPersonal.findByIdTipoPersonal", query = "SELECT t FROM TipoPersonal t WHERE t.idTipoPersonal = :idTipoPersonal"),
    @NamedQuery(name = "TipoPersonal.findByNombre", query = "SELECT t FROM TipoPersonal t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TipoPersonal.findByEstado", query = "SELECT t FROM TipoPersonal t WHERE t.estado = :estado"),
    @NamedQuery(name = "TipoPersonal.findByCreado", query = "SELECT t FROM TipoPersonal t WHERE t.creado = :creado"),
    @NamedQuery(name = "TipoPersonal.findByModificado", query = "SELECT t FROM TipoPersonal t WHERE t.modificado = :modificado")})
public class TipoPersonal implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_TIPO_PERSONAL")
    private BigDecimal idTipoPersonal;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOMBRE")
    private String nombre;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoPersonal")
    private List<Personal> personalList;

    public TipoPersonal() {
    }

    public TipoPersonal(BigDecimal idTipoPersonal) {
        this.idTipoPersonal = idTipoPersonal;
    }

    public TipoPersonal(BigDecimal idTipoPersonal, String nombre, BigInteger estado, Date creado, Date modificado) {
        this.idTipoPersonal = idTipoPersonal;
        this.nombre = nombre;
        this.estado = estado;
        this.creado = creado;
        this.modificado = modificado;
    }

    public BigDecimal getIdTipoPersonal() {
        return idTipoPersonal;
    }

    public void setIdTipoPersonal(BigDecimal idTipoPersonal) {
        this.idTipoPersonal = idTipoPersonal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    @XmlTransient
    public List<Personal> getPersonalList() {
        return personalList;
    }

    public void setPersonalList(List<Personal> personalList) {
        this.personalList = personalList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoPersonal != null ? idTipoPersonal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoPersonal)) {
            return false;
        }
        TipoPersonal other = (TipoPersonal) object;
        if ((this.idTipoPersonal == null && other.idTipoPersonal != null) || (this.idTipoPersonal != null && !this.idTipoPersonal.equals(other.idTipoPersonal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.sigeco.Entidades.TipoPersonal[ idTipoPersonal=" + idTipoPersonal + " ]";
    }
    
}
