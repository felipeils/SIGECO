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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Joe-Xidu
 */
@Entity
@Table(name = "PAGO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pago.findAll", query = "SELECT p FROM Pago p"),
    @NamedQuery(name = "Pago.findByIdPago", query = "SELECT p FROM Pago p WHERE p.idPago = :idPago"),
    @NamedQuery(name = "Pago.findByValor", query = "SELECT p FROM Pago p WHERE p.valor = :valor"),
    @NamedQuery(name = "Pago.findByComprobante", query = "SELECT p FROM Pago p WHERE p.comprobante = :comprobante"),
    @NamedQuery(name = "Pago.findByFechaPago", query = "SELECT p FROM Pago p WHERE p.fechaPago = :fechaPago"),
    @NamedQuery(name = "Pago.findByCreado", query = "SELECT p FROM Pago p WHERE p.creado = :creado"),
    @NamedQuery(name = "Pago.findByModificado", query = "SELECT p FROM Pago p WHERE p.modificado = :modificado")})
public class Pago implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PAGO")
    private BigDecimal idPago;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALOR")
    private BigInteger valor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COMPROBANTE")
    private BigInteger comprobante;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_PAGO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPago;
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
    @JoinColumn(name = "ID_SERVICIO", referencedColumnName = "ID_SERVICIO")
    @ManyToOne
    private Servicio idServicio;
    @JoinColumn(name = "ID_GASTO_COMUN", referencedColumnName = "ID_GASTO_COMUN")
    @ManyToOne
    private GastoComun idGastoComun;
    @JoinColumn(name = "ID_CUENTA_RESIDENTE", referencedColumnName = "ID_CUENTA_RESIDENTE")
    @ManyToOne
    private CuentaResidente idCuentaResidente;
    @JoinColumn(name = "ID_CUENTA_CONDOMINIO", referencedColumnName = "ID_CUENTA_CONDOMINIO")
    @ManyToOne
    private CuentaCondominio idCuentaCondominio;

    public Pago() {
    }

    public Pago(BigDecimal idPago) {
        this.idPago = idPago;
    }

    public Pago(BigDecimal idPago, BigInteger valor, BigInteger comprobante, Date fechaPago, Date creado, Date modificado) {
        this.idPago = idPago;
        this.valor = valor;
        this.comprobante = comprobante;
        this.fechaPago = fechaPago;
        this.creado = creado;
        this.modificado = modificado;
    }

    public BigDecimal getIdPago() {
        return idPago;
    }

    public void setIdPago(BigDecimal idPago) {
        this.idPago = idPago;
    }

    public BigInteger getValor() {
        return valor;
    }

    public void setValor(BigInteger valor) {
        this.valor = valor;
    }

    public BigInteger getComprobante() {
        return comprobante;
    }

    public void setComprobante(BigInteger comprobante) {
        this.comprobante = comprobante;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
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

    public Servicio getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(Servicio idServicio) {
        this.idServicio = idServicio;
    }

    public GastoComun getIdGastoComun() {
        return idGastoComun;
    }

    public void setIdGastoComun(GastoComun idGastoComun) {
        this.idGastoComun = idGastoComun;
    }

    public CuentaResidente getIdCuentaResidente() {
        return idCuentaResidente;
    }

    public void setIdCuentaResidente(CuentaResidente idCuentaResidente) {
        this.idCuentaResidente = idCuentaResidente;
    }

    public CuentaCondominio getIdCuentaCondominio() {
        return idCuentaCondominio;
    }

    public void setIdCuentaCondominio(CuentaCondominio idCuentaCondominio) {
        this.idCuentaCondominio = idCuentaCondominio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPago != null ? idPago.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pago)) {
            return false;
        }
        Pago other = (Pago) object;
        if ((this.idPago == null && other.idPago != null) || (this.idPago != null && !this.idPago.equals(other.idPago))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.sigeco.Entidades.Pago[ idPago=" + idPago + " ]";
    }
    
}
