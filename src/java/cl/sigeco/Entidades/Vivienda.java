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
@Table(name = "VIVIENDA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vivienda.findAll", query = "SELECT v FROM Vivienda v"),
    @NamedQuery(name = "Vivienda.findByIdVivienda", query = "SELECT v FROM Vivienda v WHERE v.idVivienda = :idVivienda"),
    @NamedQuery(name = "Vivienda.findByNumero", query = "SELECT v FROM Vivienda v WHERE v.numero = :numero"),
    @NamedQuery(name = "Vivienda.findByPiso", query = "SELECT v FROM Vivienda v WHERE v.piso = :piso"),
    @NamedQuery(name = "Vivienda.findByMetrosCuadrados", query = "SELECT v FROM Vivienda v WHERE v.metrosCuadrados = :metrosCuadrados"),
    @NamedQuery(name = "Vivienda.findByEstado", query = "SELECT v FROM Vivienda v WHERE v.estado = :estado"),
    @NamedQuery(name = "Vivienda.findBySector", query = "SELECT v FROM Vivienda v WHERE v.sector = :sector"),
    @NamedQuery(name = "Vivienda.findByCreado", query = "SELECT v FROM Vivienda v WHERE v.creado = :creado"),
    @NamedQuery(name = "Vivienda.findByModificado", query = "SELECT v FROM Vivienda v WHERE v.modificado = :modificado")})
public class Vivienda implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_VIVIENDA")
    private int idVivienda;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMERO")
    private int numero;
    @Column(name = "PISO")
    private int piso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "METROS_CUADRADOS")
    private int metrosCuadrados;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADO")
    private int estado;
    @Size(max = 50)
    @Column(name = "SECTOR")
    private String sector;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idVivienda")
    private List<GastoComun> gastoComunList;
    @OneToMany(mappedBy = "idVivienda")
    private List<ConsumoAgua> consumoAguaList;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "idVivienda")
    private int residente;
    @OneToMany(mappedBy = "idVivienda")
    private List<ConsumoLuz> consumoLuzList;
    @JoinColumn(name = "ID_TIPO_VIVIENDA", referencedColumnName = "ID_TIPO_VIVIENDA")
    @ManyToOne(optional = false)
    private int idTipoVivienda;
    @JoinColumn(name = "ID_CONDOMINIO", referencedColumnName = "ID_CONDOMINIO")
    @ManyToOne(optional = false)
    private int idCondominio;
    private String nomCondominio;
    private String nomTipoVivienda;
    private String nomEstado;

    public Vivienda() {
    }

    public Vivienda(int idVivienda) {
        this.idVivienda = idVivienda;
    }

    public Vivienda(int idVivienda, int numero, int metrosCuadrados, int estado, Date creado, Date modificado) {
        this.idVivienda = idVivienda;
        this.numero = numero;
        this.metrosCuadrados = metrosCuadrados;
        this.estado = estado;
        this.creado = creado;
        this.modificado = modificado;
    }

    public String getNomCondominio() {
        return nomCondominio;
    }

    public void setNomCondominio(String nomCondominio) {
        this.nomCondominio = nomCondominio;
    }

    public String getNomTipoVivienda() {
        return nomTipoVivienda;
    }

    public void setNomTipoVivienda(String nomTipoVivienda) {
        this.nomTipoVivienda = nomTipoVivienda;
    }

    public String getNomEstado() {
        return nomEstado;
    }

    public void setNomEstado(String nomEstado) {
        this.nomEstado = nomEstado;
    }

    public int getIdVivienda() {
        return idVivienda;
    }

    public void setIdVivienda(int idVivienda) {
        this.idVivienda = idVivienda;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }

    public int getMetrosCuadrados() {
        return metrosCuadrados;
    }

    public void setMetrosCuadrados(int metrosCuadrados) {
        this.metrosCuadrados = metrosCuadrados;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
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
    public List<GastoComun> getGastoComunList() {
        return gastoComunList;
    }

    public void setGastoComunList(List<GastoComun> gastoComunList) {
        this.gastoComunList = gastoComunList;
    }

    @XmlTransient
    public List<ConsumoAgua> getConsumoAguaList() {
        return consumoAguaList;
    }

    public void setConsumoAguaList(List<ConsumoAgua> consumoAguaList) {
        this.consumoAguaList = consumoAguaList;
    }

    public int getResidente() {
        return residente;
    }

    public void setResidente(int residente) {
        this.residente = residente;
    }

    @XmlTransient
    public List<ConsumoLuz> getConsumoLuzList() {
        return consumoLuzList;
    }

    public void setConsumoLuzList(List<ConsumoLuz> consumoLuzList) {
        this.consumoLuzList = consumoLuzList;
    }

    public int getIdTipoVivienda() {
        return idTipoVivienda;
    }

    public void setIdTipoVivienda(int idTipoVivienda) {
        this.idTipoVivienda = idTipoVivienda;
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
        hash = 29 * hash + this.idVivienda;
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
        final Vivienda other = (Vivienda) obj;
        return this.idVivienda == other.idVivienda;
    }

    @Override
    public String toString() {
        return "{\"data\":{" + 
                "\"idVivienda\":" + idVivienda + 
                ", \"numero\":" + numero + 
                ", \"piso\":" + piso + 
                ", \"metrosCuadrados\":" + metrosCuadrados + 
                ", \"estado\":" + estado + 
                ", \"sector\":\"" + sector + "\"" + 
                ", \"creado\":\"" + creado +  "\"" + 
                ", \"modificado\":\"" + modificado + "\"" + 
                ", \"residente\":" + residente + 
                ", \"idTipoVivienda\":" + idTipoVivienda + 
                ", \"idCondominio\":" + idCondominio + 
                ", \"nomEstado\":\"" + nomEstado + "\"}}";
    }
    
    
}
