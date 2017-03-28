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
@Table(name = "EMPRESA_CONTRATISTA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EmpresaContratista.findAll", query = "SELECT e FROM EmpresaContratista e"),
    @NamedQuery(name = "EmpresaContratista.findByIdEmpresaContratista", query = "SELECT e FROM EmpresaContratista e WHERE e.idEmpresaContratista = :idEmpresaContratista"),
    @NamedQuery(name = "EmpresaContratista.findByCuenta", query = "SELECT e FROM EmpresaContratista e WHERE e.cuenta = :cuenta"),
    @NamedQuery(name = "EmpresaContratista.findByBanco", query = "SELECT e FROM EmpresaContratista e WHERE e.banco = :banco"),
    @NamedQuery(name = "EmpresaContratista.findByRutCuenta", query = "SELECT e FROM EmpresaContratista e WHERE e.rutCuenta = :rutCuenta"),
    @NamedQuery(name = "EmpresaContratista.findByNombre", query = "SELECT e FROM EmpresaContratista e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "EmpresaContratista.findByEmail", query = "SELECT e FROM EmpresaContratista e WHERE e.email = :email"),
    @NamedQuery(name = "EmpresaContratista.findByEstado", query = "SELECT e FROM EmpresaContratista e WHERE e.estado = :estado"),
    @NamedQuery(name = "EmpresaContratista.findByCreado", query = "SELECT e FROM EmpresaContratista e WHERE e.creado = :creado"),
    @NamedQuery(name = "EmpresaContratista.findByModificado", query = "SELECT e FROM EmpresaContratista e WHERE e.modificado = :modificado")})
public class EmpresaContratista implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_EMPRESA_CONTRATISTA")
    private BigDecimal idEmpresaContratista;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CUENTA")
    private BigInteger cuenta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "BANCO")
    private String banco;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RUT_CUENTA")
    private BigInteger rutCuenta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "NOMBRE")
    private String nombre;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Correo electrónico no válido")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "EMAIL")
    private String email;
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
    private BigInteger modificado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEmpresaContratista")
    private List<Personal> personalList;

    public EmpresaContratista() {
    }

    public EmpresaContratista(BigDecimal idEmpresaContratista) {
        this.idEmpresaContratista = idEmpresaContratista;
    }

    public EmpresaContratista(BigDecimal idEmpresaContratista, BigInteger cuenta, String banco, BigInteger rutCuenta, String nombre, String email, BigInteger estado, Date creado, BigInteger modificado) {
        this.idEmpresaContratista = idEmpresaContratista;
        this.cuenta = cuenta;
        this.banco = banco;
        this.rutCuenta = rutCuenta;
        this.nombre = nombre;
        this.email = email;
        this.estado = estado;
        this.creado = creado;
        this.modificado = modificado;
    }

    public BigDecimal getIdEmpresaContratista() {
        return idEmpresaContratista;
    }

    public void setIdEmpresaContratista(BigDecimal idEmpresaContratista) {
        this.idEmpresaContratista = idEmpresaContratista;
    }

    public BigInteger getCuenta() {
        return cuenta;
    }

    public void setCuenta(BigInteger cuenta) {
        this.cuenta = cuenta;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public BigInteger getRutCuenta() {
        return rutCuenta;
    }

    public void setRutCuenta(BigInteger rutCuenta) {
        this.rutCuenta = rutCuenta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public BigInteger getModificado() {
        return modificado;
    }

    public void setModificado(BigInteger modificado) {
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
        hash += (idEmpresaContratista != null ? idEmpresaContratista.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmpresaContratista)) {
            return false;
        }
        EmpresaContratista other = (EmpresaContratista) object;
        if ((this.idEmpresaContratista == null && other.idEmpresaContratista != null) || (this.idEmpresaContratista != null && !this.idEmpresaContratista.equals(other.idEmpresaContratista))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.sigeco.Entidades.EmpresaContratista[ idEmpresaContratista=" + idEmpresaContratista + " ]";
    }
    
}
