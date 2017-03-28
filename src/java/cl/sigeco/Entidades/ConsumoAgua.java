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
@Table(name = "CONSUMO_AGUA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ConsumoAgua.findAll", query = "SELECT c FROM ConsumoAgua c"),
    @NamedQuery(name = "ConsumoAgua.findByIdConsumoAgua", query = "SELECT c FROM ConsumoAgua c WHERE c.idConsumoAgua = :idConsumoAgua"),
    @NamedQuery(name = "ConsumoAgua.findByValorUnitario", query = "SELECT c FROM ConsumoAgua c WHERE c.valorUnitario = :valorUnitario"),
    @NamedQuery(name = "ConsumoAgua.findByLecturaAnterior", query = "SELECT c FROM ConsumoAgua c WHERE c.lecturaAnterior = :lecturaAnterior"),
    @NamedQuery(name = "ConsumoAgua.findByLecturaActual", query = "SELECT c FROM ConsumoAgua c WHERE c.lecturaActual = :lecturaActual"),
    @NamedQuery(name = "ConsumoAgua.findByFechaLectura", query = "SELECT c FROM ConsumoAgua c WHERE c.fechaLectura = :fechaLectura"),
    @NamedQuery(name = "ConsumoAgua.findByCreado", query = "SELECT c FROM ConsumoAgua c WHERE c.creado = :creado"),
    @NamedQuery(name = "ConsumoAgua.findByModificado", query = "SELECT c FROM ConsumoAgua c WHERE c.modificado = :modificado")})
public class ConsumoAgua implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_CONSUMO_AGUA")
    private BigDecimal idConsumoAgua;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALOR_UNITARIO")
    private BigInteger valorUnitario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LECTURA_ANTERIOR")
    private BigInteger lecturaAnterior;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LECTURA_ACTUAL")
    private BigInteger lecturaActual;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_LECTURA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaLectura;
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
    @ManyToOne
    private Vivienda idVivienda;
    @JoinColumn(name = "ID_CONDOMINIO", referencedColumnName = "ID_CONDOMINIO")
    @ManyToOne
    private Condominio idCondominio;

    public ConsumoAgua() {
    }

    public ConsumoAgua(BigDecimal idConsumoAgua) {
        this.idConsumoAgua = idConsumoAgua;
    }

    public ConsumoAgua(BigDecimal idConsumoAgua, BigInteger valorUnitario, BigInteger lecturaAnterior, BigInteger lecturaActual, Date fechaLectura, Date creado, Date modificado) {
        this.idConsumoAgua = idConsumoAgua;
        this.valorUnitario = valorUnitario;
        this.lecturaAnterior = lecturaAnterior;
        this.lecturaActual = lecturaActual;
        this.fechaLectura = fechaLectura;
        this.creado = creado;
        this.modificado = modificado;
    }

    public BigDecimal getIdConsumoAgua() {
        return idConsumoAgua;
    }

    public void setIdConsumoAgua(BigDecimal idConsumoAgua) {
        this.idConsumoAgua = idConsumoAgua;
    }

    public BigInteger getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigInteger valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public BigInteger getLecturaAnterior() {
        return lecturaAnterior;
    }

    public void setLecturaAnterior(BigInteger lecturaAnterior) {
        this.lecturaAnterior = lecturaAnterior;
    }

    public BigInteger getLecturaActual() {
        return lecturaActual;
    }

    public void setLecturaActual(BigInteger lecturaActual) {
        this.lecturaActual = lecturaActual;
    }

    public Date getFechaLectura() {
        return fechaLectura;
    }

    public void setFechaLectura(Date fechaLectura) {
        this.fechaLectura = fechaLectura;
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

    public Vivienda getIdVivienda() {
        return idVivienda;
    }

    public void setIdVivienda(Vivienda idVivienda) {
        this.idVivienda = idVivienda;
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
        hash += (idConsumoAgua != null ? idConsumoAgua.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConsumoAgua)) {
            return false;
        }
        ConsumoAgua other = (ConsumoAgua) object;
        if ((this.idConsumoAgua == null && other.idConsumoAgua != null) || (this.idConsumoAgua != null && !this.idConsumoAgua.equals(other.idConsumoAgua))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.sigeco.Entidades.ConsumoAgua[ idConsumoAgua=" + idConsumoAgua + " ]";
    }
    
}
