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
@Table(name = "TIPO_VIVIENDA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoVivienda.findAll", query = "SELECT t FROM TipoVivienda t"),
    @NamedQuery(name = "TipoVivienda.findByIdTipoVivienda", query = "SELECT t FROM TipoVivienda t WHERE t.idTipoVivienda = :idTipoVivienda"),
    @NamedQuery(name = "TipoVivienda.findByParticipacion", query = "SELECT t FROM TipoVivienda t WHERE t.participacion = :participacion"),
    @NamedQuery(name = "TipoVivienda.findByNombre", query = "SELECT t FROM TipoVivienda t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TipoVivienda.findByEstado", query = "SELECT t FROM TipoVivienda t WHERE t.estado = :estado"),
    @NamedQuery(name = "TipoVivienda.findByCreado", query = "SELECT t FROM TipoVivienda t WHERE t.creado = :creado"),
    @NamedQuery(name = "TipoVivienda.findByModificado", query = "SELECT t FROM TipoVivienda t WHERE t.modificado = :modificado")})
public class TipoVivienda implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_TIPO_VIVIENDA")
    private int idTipoVivienda;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PARTICIPACION")
    private double participacion;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoVivienda")
    private List<Vivienda> viviendaList;
    private String nomEstado;

    public TipoVivienda() {
    }

    public TipoVivienda(int idTipoVivienda) {
        this.idTipoVivienda = idTipoVivienda;
    }

    public String getNomEstado() {
        return nomEstado;
    }

    public void setNomEstado(String nomEstado) {
        this.nomEstado = nomEstado;
    }

    public TipoVivienda(int idTipoVivienda, int participacion, String nombre, int estado, Date creado, Date modificado) {
        this.idTipoVivienda = idTipoVivienda;
        this.participacion = participacion;
        this.nombre = nombre;
        this.estado = estado;
        this.creado = creado;
        this.modificado = modificado;
    }

    public int getIdTipoVivienda() {
        return idTipoVivienda;
    }

    public void setIdTipoVivienda(int idTipoVivienda) {
        this.idTipoVivienda = idTipoVivienda;
    }

    public double getParticipacion() {
        return participacion;
    }

    public void setParticipacion(double participacion) {
        this.participacion = participacion;
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
    public List<Vivienda> getViviendaList() {
        return viviendaList;
    }

    public void setViviendaList(List<Vivienda> viviendaList) {
        this.viviendaList = viviendaList;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.idTipoVivienda;
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
        final TipoVivienda other = (TipoVivienda) obj;
        return this.idTipoVivienda == other.idTipoVivienda;
    }

    @Override
    public String toString() {
        return "{\"data\":{" + 
                "\"idTipoVivienda\":" + idTipoVivienda +
                ", \"participacion\":" + participacion + 
                ", \"nombre\":\"" + nombre +  "\"" +
                ", \"estado\":" + estado + 
                ", \"creado\":\"" + creado +  "\"" +
                ", \"modificad\":\"" + modificado +  "\"" +
                ", \"nomEstado\":\"" + nomEstado + "\"}}";
    }
    
}
