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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Joe-Xidu
 */
@Entity
@Table(name = "GASTO_COMUN")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GastoComun.findAll", query = "SELECT g FROM GastoComun g"),
    @NamedQuery(name = "GastoComun.findByIdGastoComun", query = "SELECT g FROM GastoComun g WHERE g.idGastoComun = :idGastoComun"),
    @NamedQuery(name = "GastoComun.findByFechaEmision", query = "SELECT g FROM GastoComun g WHERE g.fechaEmision = :fechaEmision"),
    @NamedQuery(name = "GastoComun.findByValor", query = "SELECT g FROM GastoComun g WHERE g.valor = :valor"),
    @NamedQuery(name = "GastoComun.findByMulta", query = "SELECT g FROM GastoComun g WHERE g.multa = :multa"),
    @NamedQuery(name = "GastoComun.findByEstado", query = "SELECT g FROM GastoComun g WHERE g.estado = :estado"),
    @NamedQuery(name = "GastoComun.findByCreado", query = "SELECT g FROM GastoComun g WHERE g.creado = :creado"),
    @NamedQuery(name = "GastoComun.findByModificado", query = "SELECT g FROM GastoComun g WHERE g.modificado = :modificado")})
public class GastoComun implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_GASTO_COMUN")
    private int idGastoComun;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_EMISION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEmision;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALOR")
    private int valor;
    @Column(name = "MULTA")
    private int multa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADO")
    private int estado;
    @Column(name = "CREADO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MODIFICADO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modificado;
    @JoinColumn(name = "ID_VIVIENDA", referencedColumnName = "ID_VIVIENDA")
    @ManyToOne(optional = false)
    private int idVivienda;
    @OneToMany(mappedBy = "idGastoComun")
    private List<Pago> pagoList;
    private String nomEstado;

    public GastoComun() {
    }

    public GastoComun(int idGastoComun) {
        this.idGastoComun = idGastoComun;
    }

    public GastoComun(int idGastoComun, Date fechaEmision, int valor, int estado, Date modificado) {
        this.idGastoComun = idGastoComun;
        this.fechaEmision = fechaEmision;
        this.valor = valor;
        this.estado = estado;
        this.modificado = modificado;
    }

    public String getNomEstado() {
        return nomEstado;
    }

    public void setNomEstado(String nomEstado) {
        this.nomEstado = nomEstado;
    }

    public int getIdGastoComun() {
        return idGastoComun;
    }

    public void setIdGastoComun(int idGastoComun) {
        this.idGastoComun = idGastoComun;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getMulta() {
        return multa;
    }

    public void setMulta(int multa) {
        this.multa = multa;
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

    @XmlTransient
    public List<Pago> getPagoList() {
        return pagoList;
    }

    public void setPagoList(List<Pago> pagoList) {
        this.pagoList = pagoList;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.idGastoComun;
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
        final GastoComun other = (GastoComun) obj;
        if (this.idGastoComun != other.idGastoComun) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "{\"data\":{" + 
                "\"idGastoComun\":" + idGastoComun + 
                ", \"fechaEmision\":\"" + fechaEmision + "\"" +
                ", \"valor\":" + valor + 
                ", \"multa\":" + multa + 
                ", \"estado\":" + estado + 
                ", \"creado\":\"" + creado + "\"" +
                ", \"modificado\":\"" + modificado + "\"" +
                ", \"idVivienda\":" + idVivienda + 
                ", \"nomEstado\":\"" + nomEstado + "\"}}";
    }
    
    
}
