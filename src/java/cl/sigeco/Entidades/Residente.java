/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.sigeco.Entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
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
@Table(name = "RESIDENTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Residente.findAll", query = "SELECT r FROM Residente r"),
    @NamedQuery(name = "Residente.findByIdResidente", query = "SELECT r FROM Residente r WHERE r.idResidente = :idResidente"),
    @NamedQuery(name = "Residente.findByPropietario", query = "SELECT r FROM Residente r WHERE r.propietario = :propietario"),
    @NamedQuery(name = "Residente.findByEstado", query = "SELECT r FROM Residente r WHERE r.estado = :estado"),
    @NamedQuery(name = "Residente.findByCreado", query = "SELECT r FROM Residente r WHERE r.creado = :creado"),
    @NamedQuery(name = "Residente.findByModificado", query = "SELECT r FROM Residente r WHERE r.modificado = :modificado")})
public class Residente implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_RESIDENTE")
    private int idResidente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PROPIETARIO")
    private int propietario;
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
    @JoinColumn(name = "ID_VIVIENDA", referencedColumnName = "ID_VIVIENDA")
    @OneToOne(optional = false)
    private int idVivienda;
    @JoinColumn(name = "ID_PERSONA", referencedColumnName = "ID_PERSONA")
    @ManyToOne(optional = false)
    private int idPersona;
    @JoinColumn(name = "ID_CUENTA_RESIDENTE", referencedColumnName = "ID_CUENTA_RESIDENTE")
    @ManyToOne(optional = false)
    private int idCuentaResidente;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "idResidente")
    private Usuario usuario;
    private String nomEstado;
    private String nomPropietario;
    private String nomECuentaResidente;
    private String nomPersona;

    public Residente() {
    }

    public Residente(int idResidente) {
        this.idResidente = idResidente;
    }

    public Residente(int idResidente, int propietario, int estado, Date creado, Date modificado) {
        this.idResidente = idResidente;
        this.propietario = propietario;
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

    public String getNomPropietario() {
        return nomPropietario;
    }

    public void setNomPropietario(String nomPropietario) {
        this.nomPropietario = nomPropietario;
    }

    public String getNomECuentaResidente() {
        return nomECuentaResidente;
    }

    public void setNomECuentaResidente(String nomECuentaResidente) {
        this.nomECuentaResidente = nomECuentaResidente;
    }

    public String getNomPersona() {
        return nomPersona;
    }

    public void setNomPersona(String nomPersona) {
        this.nomPersona = nomPersona;
    }

    public int getIdResidente() {
        return idResidente;
    }

    public void setIdResidente(int idResidente) {
        this.idResidente = idResidente;
    }

    public int getPropietario() {
        return propietario;
    }

    public void setPropietario(int propietario) {
        this.propietario = propietario;
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

    public int getIdVivienda() {
        return idVivienda;
    }

    public void setIdVivienda(int idVivienda) {
        this.idVivienda = idVivienda;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public int getIdCuentaResidente() {
        return idCuentaResidente;
    }

    public void setIdCuentaResidente(int idCuentaResidente) {
        this.idCuentaResidente = idCuentaResidente;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + this.idResidente;
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
        final Residente other = (Residente) obj;
        if (this.idResidente != other.idResidente) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "{\"data\"{" +
                "\"idResidente\":" + idResidente +
                ", \"propietario\":" + propietario +
                ", \"estado\":" + estado +
                ", \"creado\":\"" + creado + "\"" +
                ", \"modificado\":\"" + modificado + "\"" +
                ", \"idVivienda\":" + idVivienda +
                ", \"idPersona\":" + idPersona +
                ", \"idCuentaResidente\":" + idCuentaResidente +
                ", \"nomEstado\":\"" + nomEstado + "\"" +
                ", \"nomPropietario\":\"" + nomPropietario + "\"" +
                ", \"nomECuentaResidente\":\"" + nomECuentaResidente + "\"" +
                ", \"nomPersona\":\"" + nomPersona + "\"}}";
    }

    
}
