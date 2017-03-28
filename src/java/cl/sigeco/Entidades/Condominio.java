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
@Table(name = "CONDOMINIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Condominio.findAll", query = "SELECT c FROM Condominio c"),
    @NamedQuery(name = "Condominio.findByIdCondominio", query = "SELECT c FROM Condominio c WHERE c.idCondominio = :idCondominio"),
    @NamedQuery(name = "Condominio.findByNombre", query = "SELECT c FROM Condominio c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Condominio.findByDireccion", query = "SELECT c FROM Condominio c WHERE c.direccion = :direccion"),
    @NamedQuery(name = "Condominio.findByEstado", query = "SELECT c FROM Condominio c WHERE c.estado = :estado"),
    @NamedQuery(name = "Condominio.findByRepresentanteId", query = "SELECT c FROM Condominio c WHERE c.representanteId = :representanteId"),
    @NamedQuery(name = "Condominio.findByCreado", query = "SELECT c FROM Condominio c WHERE c.creado = :creado"),
    @NamedQuery(name = "Condominio.findByModificado", query = "SELECT c FROM Condominio c WHERE c.modificado = :modificado")})
public class Condominio implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_CONDOMINIO")
    private int idCondominio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "DIRECCION")
    private String direccion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADO")
    private int estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "REPRESENTANTE_ID")
    private int representanteId;
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
    @OneToMany(mappedBy = "idCondominio")
    private List<ConsumoAgua> consumoAguaList;
    @JoinColumn(name = "ID_CUENTA_CONDOMINIO", referencedColumnName = "ID_CUENTA_CONDOMINIO")
    @ManyToOne(optional = false)
    private int idCuentaCondominio;
    @JoinColumn(name = "ID_COMUNA", referencedColumnName = "ID_COMUNA")
    @ManyToOne(optional = false)
    private int idComuna;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCondominio")
    private List<SolicitudUsuario> solicitudUsuarioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCondominio")
    private List<Personal> personalList;
    @OneToMany(mappedBy = "idCondominio")
    private List<ConsumoLuz> consumoLuzList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCondominio")
    private List<Usuario> usuarioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCondominio")
    private List<Vivienda> viviendaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCondominio")
    private List<Mantencion> mantencionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCondominio")
    private List<Servicio> servicioList;
    private String comuna;
    private String nomEstado;
    private int cuenta;
    private String representante;

    public Condominio() {
    }

    public Condominio(int idCondominio) {
        this.idCondominio = idCondominio;
    }

    public Condominio(int idCondominio, String nombre, String direccion, int estado, int representanteId, Date creado, Date modificado) {
        this.idCondominio = idCondominio;
        this.nombre = nombre;
        this.direccion = direccion;
        this.estado = estado;
        this.representanteId = representanteId;
        this.creado = creado;
        this.modificado = modificado;
    }

    public int getCuenta() {
        return cuenta;
    }

    public void setCuenta(int cuenta) {
        this.cuenta = cuenta;
    }

    public String getRepresentante() {
        return representante;
    }

    public void setRepresentante(String representante) {
        this.representante = representante;
    }

    public String getComuna() {
        return comuna;
    }

    public void setComuna(String comuna) {
        this.comuna = comuna;
    }

    public String getNomEstado() {
        return nomEstado;
    }

    public void setNomEstado(String nomEstado) {
        this.nomEstado = nomEstado;
    }
    
    public int getIdCondominio() {
        return idCondominio;
    }

    public void setIdCondominio(int idCondominio) {
        this.idCondominio = idCondominio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getRepresentanteId() {
        return representanteId;
    }

    public void setRepresentanteId(int representanteId) {
        this.representanteId = representanteId;
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
    public List<ConsumoAgua> getConsumoAguaList() {
        return consumoAguaList;
    }

    public void setConsumoAguaList(List<ConsumoAgua> consumoAguaList) {
        this.consumoAguaList = consumoAguaList;
    }

    public int getIdCuentaCondominio() {
        return idCuentaCondominio;
    }

    public void setIdCuentaCondominio(int idCuentaCondominio) {
        this.idCuentaCondominio = idCuentaCondominio;
    }

    public int getIdComuna() {
        return idComuna;
    }

    public void setIdComuna(int idComuna) {
        this.idComuna = idComuna;
    }

    @XmlTransient
    public List<SolicitudUsuario> getSolicitudUsuarioList() {
        return solicitudUsuarioList;
    }

    public void setSolicitudUsuarioList(List<SolicitudUsuario> solicitudUsuarioList) {
        this.solicitudUsuarioList = solicitudUsuarioList;
    }

    @XmlTransient
    public List<Personal> getPersonalList() {
        return personalList;
    }

    public void setPersonalList(List<Personal> personalList) {
        this.personalList = personalList;
    }

    @XmlTransient
    public List<ConsumoLuz> getConsumoLuzList() {
        return consumoLuzList;
    }

    public void setConsumoLuzList(List<ConsumoLuz> consumoLuzList) {
        this.consumoLuzList = consumoLuzList;
    }

    @XmlTransient
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    @XmlTransient
    public List<Vivienda> getViviendaList() {
        return viviendaList;
    }

    public void setViviendaList(List<Vivienda> viviendaList) {
        this.viviendaList = viviendaList;
    }

    @XmlTransient
    public List<Mantencion> getMantencionList() {
        return mantencionList;
    }

    public void setMantencionList(List<Mantencion> mantencionList) {
        this.mantencionList = mantencionList;
    }

    @XmlTransient
    public List<Servicio> getServicioList() {
        return servicioList;
    }

    public void setServicioList(List<Servicio> servicioList) {
        this.servicioList = servicioList;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.idCondominio;
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
        final Condominio other = (Condominio) obj;
        return this.idCondominio == other.idCondominio;
    }

    @Override
    public String toString() {
        return "{\"data\":{" +
                "\"idCondominio\":" + idCondominio +
                ", \"nombre\":\"" + nombre + "\"" +
                ", \"direccion\":\"" + direccion + "\"" +
                ", \"estado\":" + estado +
                ", \"representanteId\":" + representanteId +
                ", \"creado\":\"" + creado + "\"" +
                ", \"modificado\":\"" + modificado + "\"" +
                ", \"idCuentaCondominio\":" + idCuentaCondominio +
                ", \"idComuna\":" + idComuna +
                ", \"comuna\":\"" + comuna + "\"" +
                ", \"nomEstado\":\"" + nomEstado + "\"" +
                ", \"cuenta\":" + cuenta +
                ", \"representante\":\"" + representante + "\"" +
                "}}";
    }
 
}
