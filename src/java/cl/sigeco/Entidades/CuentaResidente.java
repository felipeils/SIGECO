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
@Table(name = "CUENTA_RESIDENTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CuentaResidente.findAll", query = "SELECT c FROM CuentaResidente c"),
    @NamedQuery(name = "CuentaResidente.findByIdCuentaResidente", query = "SELECT c FROM CuentaResidente c WHERE c.idCuentaResidente = :idCuentaResidente"),
    @NamedQuery(name = "CuentaResidente.findByRutCuenta", query = "SELECT c FROM CuentaResidente c WHERE c.rutCuenta = :rutCuenta"),
    @NamedQuery(name = "CuentaResidente.findByCuenta", query = "SELECT c FROM CuentaResidente c WHERE c.cuenta = :cuenta"),
    @NamedQuery(name = "CuentaResidente.findByBanco", query = "SELECT c FROM CuentaResidente c WHERE c.banco = :banco"),
    @NamedQuery(name = "CuentaResidente.findByNombre", query = "SELECT c FROM CuentaResidente c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CuentaResidente.findByEmail", query = "SELECT c FROM CuentaResidente c WHERE c.email = :email"),
    @NamedQuery(name = "CuentaResidente.findByEstado", query = "SELECT c FROM CuentaResidente c WHERE c.estado = :estado"),
    @NamedQuery(name = "CuentaResidente.findByCreado", query = "SELECT c FROM CuentaResidente c WHERE c.creado = :creado"),
    @NamedQuery(name = "CuentaResidente.findByModificado", query = "SELECT c FROM CuentaResidente c WHERE c.modificado = :modificado")})
public class CuentaResidente implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_CUENTA_RESIDENTE")
    private BigDecimal idCuentaResidente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RUT_CUENTA")
    private BigInteger rutCuenta;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCuentaResidente")
    private List<Residente> residenteList;
    @OneToMany(mappedBy = "idCuentaResidente")
    private List<Pago> pagoList;
    @JoinColumn(name = "ID_TIPO_CUENTA", referencedColumnName = "ID_TIPO_CUENTA")
    @ManyToOne(optional = false)
    private TipoCuenta idTipoCuenta;

    public CuentaResidente() {
    }

    public CuentaResidente(BigDecimal idCuentaResidente) {
        this.idCuentaResidente = idCuentaResidente;
    }

    public CuentaResidente(BigDecimal idCuentaResidente, BigInteger rutCuenta, BigInteger cuenta, String banco, String nombre, String email, BigInteger estado, Date creado, BigInteger modificado) {
        this.idCuentaResidente = idCuentaResidente;
        this.rutCuenta = rutCuenta;
        this.cuenta = cuenta;
        this.banco = banco;
        this.nombre = nombre;
        this.email = email;
        this.estado = estado;
        this.creado = creado;
        this.modificado = modificado;
    }

    public BigDecimal getIdCuentaResidente() {
        return idCuentaResidente;
    }

    public void setIdCuentaResidente(BigDecimal idCuentaResidente) {
        this.idCuentaResidente = idCuentaResidente;
    }

    public BigInteger getRutCuenta() {
        return rutCuenta;
    }

    public void setRutCuenta(BigInteger rutCuenta) {
        this.rutCuenta = rutCuenta;
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
    public List<Residente> getResidenteList() {
        return residenteList;
    }

    public void setResidenteList(List<Residente> residenteList) {
        this.residenteList = residenteList;
    }

    @XmlTransient
    public List<Pago> getPagoList() {
        return pagoList;
    }

    public void setPagoList(List<Pago> pagoList) {
        this.pagoList = pagoList;
    }

    public TipoCuenta getIdTipoCuenta() {
        return idTipoCuenta;
    }

    public void setIdTipoCuenta(TipoCuenta idTipoCuenta) {
        this.idTipoCuenta = idTipoCuenta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCuentaResidente != null ? idCuentaResidente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CuentaResidente)) {
            return false;
        }
        CuentaResidente other = (CuentaResidente) object;
        if ((this.idCuentaResidente == null && other.idCuentaResidente != null) || (this.idCuentaResidente != null && !this.idCuentaResidente.equals(other.idCuentaResidente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.sigeco.Entidades.CuentaResidente[ idCuentaResidente=" + idCuentaResidente + " ]";
    }
    
}
