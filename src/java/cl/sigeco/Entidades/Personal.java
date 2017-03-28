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
@Table(name = "PERSONAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Personal.findAll", query = "SELECT p FROM Personal p"),
    @NamedQuery(name = "Personal.findByIdPersonal", query = "SELECT p FROM Personal p WHERE p.idPersonal = :idPersonal"),
    @NamedQuery(name = "Personal.findByRut", query = "SELECT p FROM Personal p WHERE p.rut = :rut"),
    @NamedQuery(name = "Personal.findByNombre", query = "SELECT p FROM Personal p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Personal.findByApPaterno", query = "SELECT p FROM Personal p WHERE p.apPaterno = :apPaterno"),
    @NamedQuery(name = "Personal.findByApMaterno", query = "SELECT p FROM Personal p WHERE p.apMaterno = :apMaterno"),
    @NamedQuery(name = "Personal.findByDirecci\u00f3n", query = "SELECT p FROM Personal p WHERE p.direcci\u00f3n = :direcci\u00f3n"),
    @NamedQuery(name = "Personal.findByEmail", query = "SELECT p FROM Personal p WHERE p.email = :email"),
    @NamedQuery(name = "Personal.findByTipo", query = "SELECT p FROM Personal p WHERE p.tipo = :tipo"),
    @NamedQuery(name = "Personal.findByEstado", query = "SELECT p FROM Personal p WHERE p.estado = :estado"),
    @NamedQuery(name = "Personal.findByCreado", query = "SELECT p FROM Personal p WHERE p.creado = :creado"),
    @NamedQuery(name = "Personal.findByModificado", query = "SELECT p FROM Personal p WHERE p.modificado = :modificado")})
public class Personal implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PERSONAL")
    private BigDecimal idPersonal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RUT")
    private BigInteger rut;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "AP_PATERNO")
    private String apPaterno;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "AP_MATERNO")
    private String apMaterno;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "DIRECCI\u00d3N")
    private String dirección;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Correo electrónico no válido")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 200)
    @Column(name = "EMAIL")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIPO")
    private BigInteger tipo;
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
    @JoinColumn(name = "ID_TIPO_PERSONAL", referencedColumnName = "ID_TIPO_PERSONAL")
    @ManyToOne(optional = false)
    private TipoPersonal idTipoPersonal;
    @JoinColumn(name = "ID_PERSONA", referencedColumnName = "ID_PERSONA")
    @ManyToOne(optional = false)
    private Persona idPersona;
    @JoinColumn(name = "ID_EMPRESA_CONTRATISTA", referencedColumnName = "ID_EMPRESA_CONTRATISTA")
    @ManyToOne(optional = false)
    private EmpresaContratista idEmpresaContratista;
    @JoinColumn(name = "ID_CONDOMINIO", referencedColumnName = "ID_CONDOMINIO")
    @ManyToOne(optional = false)
    private Condominio idCondominio;

    public Personal() {
    }

    public Personal(BigDecimal idPersonal) {
        this.idPersonal = idPersonal;
    }

    public Personal(BigDecimal idPersonal, BigInteger rut, String nombre, String apPaterno, String apMaterno, String dirección, BigInteger tipo, BigInteger estado, Date creado, Date modificado) {
        this.idPersonal = idPersonal;
        this.rut = rut;
        this.nombre = nombre;
        this.apPaterno = apPaterno;
        this.apMaterno = apMaterno;
        this.dirección = dirección;
        this.tipo = tipo;
        this.estado = estado;
        this.creado = creado;
        this.modificado = modificado;
    }

    public BigDecimal getIdPersonal() {
        return idPersonal;
    }

    public void setIdPersonal(BigDecimal idPersonal) {
        this.idPersonal = idPersonal;
    }

    public BigInteger getRut() {
        return rut;
    }

    public void setRut(BigInteger rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApPaterno() {
        return apPaterno;
    }

    public void setApPaterno(String apPaterno) {
        this.apPaterno = apPaterno;
    }

    public String getApMaterno() {
        return apMaterno;
    }

    public void setApMaterno(String apMaterno) {
        this.apMaterno = apMaterno;
    }

    public String getDirección() {
        return dirección;
    }

    public void setDirección(String dirección) {
        this.dirección = dirección;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigInteger getTipo() {
        return tipo;
    }

    public void setTipo(BigInteger tipo) {
        this.tipo = tipo;
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

    public TipoPersonal getIdTipoPersonal() {
        return idTipoPersonal;
    }

    public void setIdTipoPersonal(TipoPersonal idTipoPersonal) {
        this.idTipoPersonal = idTipoPersonal;
    }

    public Persona getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Persona idPersona) {
        this.idPersona = idPersona;
    }

    public EmpresaContratista getIdEmpresaContratista() {
        return idEmpresaContratista;
    }

    public void setIdEmpresaContratista(EmpresaContratista idEmpresaContratista) {
        this.idEmpresaContratista = idEmpresaContratista;
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
        hash += (idPersonal != null ? idPersonal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Personal)) {
            return false;
        }
        Personal other = (Personal) object;
        if ((this.idPersonal == null && other.idPersonal != null) || (this.idPersonal != null && !this.idPersonal.equals(other.idPersonal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.sigeco.Entidades.Personal[ idPersonal=" + idPersonal + " ]";
    }
    
}
