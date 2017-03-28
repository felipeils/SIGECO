/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.sigeco.Entidades;

import java.io.Serializable;
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
@Table(name = "TIPO_CUENTA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoCuenta.findAll", query = "SELECT t FROM TipoCuenta t"),
    @NamedQuery(name = "TipoCuenta.findByIdTipoCuenta", query = "SELECT t FROM TipoCuenta t WHERE t.idTipoCuenta = :idTipoCuenta"),
    @NamedQuery(name = "TipoCuenta.findByNombre", query = "SELECT t FROM TipoCuenta t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TipoCuenta.findByEstado", query = "SELECT t FROM TipoCuenta t WHERE t.estado = :estado"),
    @NamedQuery(name = "TipoCuenta.findByCreado", query = "SELECT t FROM TipoCuenta t WHERE t.creado = :creado"),
    @NamedQuery(name = "TipoCuenta.findByModificado", query = "SELECT t FROM TipoCuenta t WHERE t.modificado = :modificado")})
public class TipoCuenta implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_TIPO_CUENTA")
    private int idTipoCuenta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoCuenta")
    private List<CuentaCondominio> cuentaCondominioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoCuenta")
    private List<CuentaResidente> cuentaResidenteList;
    private String nomEstado;

    public TipoCuenta() {
    }

    public TipoCuenta(int idTipoCuenta) {
        this.idTipoCuenta = idTipoCuenta;
    }

    public TipoCuenta(int idTipoCuenta, String nombre, int estado, Date creado, Date modificado) {
        this.idTipoCuenta = idTipoCuenta;
        this.nombre = nombre;
        this.estado = estado;
        this.creado = creado;
        this.modificado = modificado;
    }

    public String getNomEstado() {
        return nomEstado;
    }

    public void setNomEstado(String nomEstado) {
        this.nomEstado = nomEstado;
    }

    public int getIdTipoCuenta() {
        return idTipoCuenta;
    }

    public void setIdTipoCuenta(int idTipoCuenta) {
        this.idTipoCuenta = idTipoCuenta;
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
    public List<CuentaCondominio> getCuentaCondominioList() {
        return cuentaCondominioList;
    }

    public void setCuentaCondominioList(List<CuentaCondominio> cuentaCondominioList) {
        this.cuentaCondominioList = cuentaCondominioList;
    }

    @XmlTransient
    public List<CuentaResidente> getCuentaResidenteList() {
        return cuentaResidenteList;
    }

    public void setCuentaResidenteList(List<CuentaResidente> cuentaResidenteList) {
        this.cuentaResidenteList = cuentaResidenteList;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + this.idTipoCuenta;
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
        final TipoCuenta other = (TipoCuenta) obj;
        if (this.idTipoCuenta != other.idTipoCuenta) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "{\"data\":{" + 
                "\"idTipoCuenta\":" + idTipoCuenta + 
                ", \"nombre\":\"" + nombre +  "\"" +
                ", \"estado\":" + estado + 
                ", \"creado\":\"" + creado +  "\"" +
                ", \"modificado\":\"" + modificado + "\"" +
                ", \"nomEstado\":\"" + nomEstado +  "\"}}";
    }
    
    
}
