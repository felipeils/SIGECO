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
import javax.persistence.OneToOne;
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
@Table(name = "USUARIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByIdUsuario", query = "SELECT u FROM Usuario u WHERE u.idUsuario = :idUsuario"),
    @NamedQuery(name = "Usuario.findByUsuario", query = "SELECT u FROM Usuario u WHERE u.usuario = :usuario"),
    @NamedQuery(name = "Usuario.findByPass", query = "SELECT u FROM Usuario u WHERE u.pass = :pass"),
    @NamedQuery(name = "Usuario.findByEmail", query = "SELECT u FROM Usuario u WHERE u.email = :email"),
    @NamedQuery(name = "Usuario.findByEstado", query = "SELECT u FROM Usuario u WHERE u.estado = :estado"),
    @NamedQuery(name = "Usuario.findByCreado", query = "SELECT u FROM Usuario u WHERE u.creado = :creado"),
    @NamedQuery(name = "Usuario.findByModificado", query = "SELECT u FROM Usuario u WHERE u.modificado = :modificado")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_USUARIO")
    private int idUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "USUARIO")
    private String usuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "PASS")
    private String pass;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Correo electrónico no válido")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "EMAIL")
    private String email;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private List<SolicitudUsuario> solicitudUsuarioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private List<ReservaServicio> reservaServicioList;
    @JoinColumn(name = "ID_RESIDENTE", referencedColumnName = "ID_RESIDENTE")
    @OneToOne(optional = false)
    private int idResidente;
    @JoinColumn(name = "ID_PERFIL", referencedColumnName = "ID_PERFIL")
    @ManyToOne(optional = false)
    private int idPerfil;
    @JoinColumn(name = "ID_CONDOMINIO", referencedColumnName = "ID_CONDOMINIO")
    @ManyToOne(optional = false)
    private int idCondominio;
    private String nomEstado;
    private String nomCondominio;
    private String nomResidente;
    private String nomPerfil;

    public Usuario() {
    }

    public Usuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuario(int idUsuario, String usuario, String pass, String email, int estado, Date creado, Date modificado) {
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.pass = pass;
        this.email = email;
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

    public String getNomCondominio() {
        return nomCondominio;
    }

    public void setNomCondominio(String nomCondominio) {
        this.nomCondominio = nomCondominio;
    }

    public String getNomResidente() {
        return nomResidente;
    }

    public void setNomResidente(String nomResidente) {
        this.nomResidente = nomResidente;
    }

    public String getNomPerfil() {
        return nomPerfil;
    }

    public void setNomPerfil(String nomPerfil) {
        this.nomPerfil = nomPerfil;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
    public List<SolicitudUsuario> getSolicitudUsuarioList() {
        return solicitudUsuarioList;
    }

    public void setSolicitudUsuarioList(List<SolicitudUsuario> solicitudUsuarioList) {
        this.solicitudUsuarioList = solicitudUsuarioList;
    }

    @XmlTransient
    public List<ReservaServicio> getReservaServicioList() {
        return reservaServicioList;
    }

    public void setReservaServicioList(List<ReservaServicio> reservaServicioList) {
        this.reservaServicioList = reservaServicioList;
    }

    public int getIdResidente() {
        return idResidente;
    }

    public void setIdResidente(int idResidente) {
        this.idResidente = idResidente;
    }

    public int getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(int idPerfil) {
        this.idPerfil = idPerfil;
    }

    public int getIdCondominio() {
        return idCondominio;
    }

    public void setIdCondominio(int idCondominio) {
        this.idCondominio = idCondominio;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.idUsuario;
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
        final Usuario other = (Usuario) obj;
        if (this.idUsuario != other.idUsuario) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "{\"data\":{" +
                "\"idUsuario\":" + idUsuario +
                ", \"usuario\":\"" + usuario + "\"" +
                ", \"pass\":\"" + pass + "\"" +
                ", \"email\":\"" + email + "\"" +
                ", \"estado\":" + estado +
                ", \"creado\":\"" + creado + "\"" +
                ", \"modificado\":\"" + modificado + "\"" +
                ", \"idResidente\":" + idResidente +
                ", \"idPerfil\":" + idPerfil +
                ", \"idCondominio\":" + idCondominio +
                ", \"nomEstado\":\"" + nomEstado + "\"" +
                ", \"nomCondominio\":\"" + nomCondominio + "\"" +
                ", \"nomResidente\":\"" + nomResidente + "\"" +
                ", \"nomPerfil\":\"" + nomPerfil + "\"}}";
    }
    
}
