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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "SERVICIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Servicio.findAll", query = "SELECT s FROM Servicio s"),
    @NamedQuery(name = "Servicio.findByIdServicio", query = "SELECT s FROM Servicio s WHERE s.idServicio = :idServicio"),
    @NamedQuery(name = "Servicio.findByNombre", query = "SELECT s FROM Servicio s WHERE s.nombre = :nombre"),
    @NamedQuery(name = "Servicio.findByDetalle", query = "SELECT s FROM Servicio s WHERE s.detalle = :detalle"),
    @NamedQuery(name = "Servicio.findByValorBase", query = "SELECT s FROM Servicio s WHERE s.valorBase = :valorBase"),
    @NamedQuery(name = "Servicio.findByEstado", query = "SELECT s FROM Servicio s WHERE s.estado = :estado"),
    @NamedQuery(name = "Servicio.findByCreado", query = "SELECT s FROM Servicio s WHERE s.creado = :creado"),
    @NamedQuery(name = "Servicio.findByModificado", query = "SELECT s FROM Servicio s WHERE s.modificado = :modificado")})
public class Servicio implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_SERVICIO")
    private int idServicio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 250)
    @Column(name = "DETALLE")
    private String detalle;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALOR_BASE")
    private int valorBase;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idServicio")
    private List<ReservaServicio> reservaServicioList;
    @OneToMany(mappedBy = "idServicio")
    private List<Pago> pagoList;
    @JoinColumn(name = "ID_CONDOMINIO", referencedColumnName = "ID_CONDOMINIO")
    @ManyToOne(optional = false)
    private int idCondominio;
    private String nomEstado;
    private String nomCondominio;
    
    

    public Servicio() {
    }

    public Servicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public Servicio(int idServicio, String nombre, int valorBase, int estado, Date creado, Date modificado) {
        this.idServicio = idServicio;
        this.nombre = nombre;
        this.valorBase = valorBase;
        this.estado = estado;
        this.creado = creado;
        this.modificado = modificado;
    }

    public String getNomCondominio() {
        return nomCondominio;
    }

    public void setNomCondominio(String nomCondominio) {
        this.nomCondominio = nomCondominio;
    }
    
    
    public String getNomEstado() {
        return nomEstado;
    }

    public void setNomEstado(String nomEstado) {
        this.nomEstado = nomEstado;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public int getValorBase() {
        return valorBase;
    }

    public void setValorBase(int valorBase) {
        this.valorBase = valorBase;
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
    public List<ReservaServicio> getReservaServicioList() {
        return reservaServicioList;
    }

    public void setReservaServicioList(List<ReservaServicio> reservaServicioList) {
        this.reservaServicioList = reservaServicioList;
    }

    @XmlTransient
    public List<Pago> getPagoList() {
        return pagoList;
    }

    public void setPagoList(List<Pago> pagoList) {
        this.pagoList = pagoList;
    }

    public int getIdCondominio() {
        return idCondominio;
    }

    public void setIdCondominio(int idCondominio) {
        this.idCondominio = idCondominio;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + this.idServicio;
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
        final Servicio other = (Servicio) obj;
        if (this.idServicio != other.idServicio) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "{\"data\":{" + 
                "\"idServicio\":" + idServicio + 
                ", \"nombre\":\"" + nombre + "\"" +
                ", \"detalle\":\"" + detalle + "\"" +
                ", \"valorBase\":" + valorBase + 
                ", \"estado\":" + estado + 
                ", \"creado\":\"" + creado + "\"" +
                ", \"modificado\":\"" + modificado + "\"" +
                ", \"nomCondominio\":\"" + nomCondominio + "\"" +
                ", \"nomEstado\":\"" + nomEstado + "\"" +
                ", \"idCondominio\":" + idCondominio + "}}";
    }

    
}
