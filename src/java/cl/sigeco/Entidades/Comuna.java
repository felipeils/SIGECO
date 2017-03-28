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
@Table(name = "COMUNA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comuna.findAll", query = "SELECT c FROM Comuna c"),
    @NamedQuery(name = "Comuna.findByIdComuna", query = "SELECT c FROM Comuna c WHERE c.idComuna = :idComuna"),
    @NamedQuery(name = "Comuna.findByNombre", query = "SELECT c FROM Comuna c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Comuna.findByEstado", query = "SELECT c FROM Comuna c WHERE c.estado = :estado"),
    @NamedQuery(name = "Comuna.findByCreado", query = "SELECT c FROM Comuna c WHERE c.creado = :creado"),
    @NamedQuery(name = "Comuna.findByModificado", query = "SELECT c FROM Comuna c WHERE c.modificado = :modificado")})
public class Comuna implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_COMUNA")
    private int idComuna;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "NOMBRE")
    private String nombre;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idComuna")
    private List<Condominio> condominioList;

    public Comuna() {
    }

    public Comuna(int idComuna) {
        this.idComuna = idComuna;
    }

    public Comuna(int idComuna, String nombre, int estado, Date creado, Date modificado) {
        this.idComuna = idComuna;
        this.nombre = nombre;
        this.estado = estado;
        this.creado = creado;
        this.modificado = modificado;
    }

    public int getIdComuna() {
        return idComuna;
    }

    public void setIdComuna(int idComuna) {
        this.idComuna = idComuna;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    @XmlTransient
    public List<Condominio> getCondominioList() {
        return condominioList;
    }

    public void setCondominioList(List<Condominio> condominioList) {
        this.condominioList = condominioList;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + this.idComuna;
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
        final Comuna other = (Comuna) obj;
        return this.idComuna == other.idComuna;
    }

    

    @Override
    public String toString() {
        return "cl.sigeco.Entidades.Comuna[ idComuna=" + idComuna + " ]";
    }
    
}
