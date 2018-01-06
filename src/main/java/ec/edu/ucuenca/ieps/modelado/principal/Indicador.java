/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ucuenca.ieps.modelado.principal;

import ec.edu.ucuenca.ieps.modelado.seguridades.Usuario;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jfarf
 */
@Entity
@Table(name = "indicador")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Indicador.findAll", query = "SELECT i FROM Indicador i"),
    @NamedQuery(name = "Indicador.findByIdIndicador", query = "SELECT i FROM Indicador i WHERE i.idIndicador = :idIndicador"),
    @NamedQuery(name = "Indicador.findByNombre", query = "SELECT i FROM Indicador i WHERE i.nombre = :nombre"),
    @NamedQuery(name = "Indicador.findByFormula", query = "SELECT i FROM Indicador i WHERE i.formula = :formula"),
    @NamedQuery(name = "Indicador.findByUnidades", query = "SELECT i FROM Indicador i WHERE i.unidades = :unidades"),
    @NamedQuery(name = "Indicador.findByGlosario", query = "SELECT i FROM Indicador i WHERE i.glosario = :glosario"),
    @NamedQuery(name = "Indicador.findByKpi", query = "SELECT i FROM Indicador i WHERE i.kpi = :kpi"),
    @NamedQuery(name = "Indicador.findByBase", query = "SELECT i FROM Indicador i WHERE i.base = :base"),
    @NamedQuery(name = "Indicador.findByMeta", query = "SELECT i FROM Indicador i WHERE i.meta = :meta"),
    @NamedQuery(name = "Indicador.findByTiempoMedicion", query = "SELECT i FROM Indicador i WHERE i.tiempoMedicion = :tiempoMedicion")})
public class Indicador implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idIndicador")
    private Integer idIndicador;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 250)
    @Column(name = "formula")
    private String formula;
    @Size(max = 20)
    @Column(name = "unidades")
    private String unidades;
    @Size(max = 200)
    @Column(name = "glosario")
    private String glosario;
    @Size(max = 100)
    @Column(name = "kpi")
    private String kpi;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "base")
    private BigDecimal base;
    @Column(name = "meta")
    private BigDecimal meta;
    @Size(max = 45)
    @Column(name = "tiempoMedicion")
    private String tiempoMedicion;
    @OneToMany(mappedBy = "idindicador", fetch = FetchType.LAZY)
    private List<ComponenteFormla> componenteFormlaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idIndicador", fetch = FetchType.LAZY)
    private List<Semaforo> semaforoList;
    @JoinColumn(name = "idUsuarioResponsable", referencedColumnName = "CODIGO")
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario idUsuarioResponsable;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "indicador", fetch = FetchType.LAZY)
    private List<ObjetivoEstrategicoIndicador> objetivoEstrategicoIndicadorList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idIndicador", fetch = FetchType.LAZY)
    private List<Historial> historialList;

    public Indicador() {
    }

    public Indicador(Integer idIndicador) {
        this.idIndicador = idIndicador;
    }

    public Indicador(Integer idIndicador, String nombre) {
        this.idIndicador = idIndicador;
        this.nombre = nombre;
    }

    public Integer getIdIndicador() {
        return idIndicador;
    }

    public void setIdIndicador(Integer idIndicador) {
        this.idIndicador = idIndicador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public String getUnidades() {
        return unidades;
    }

    public void setUnidades(String unidades) {
        this.unidades = unidades;
    }

    public String getGlosario() {
        return glosario;
    }

    public void setGlosario(String glosario) {
        this.glosario = glosario;
    }

    public String getKpi() {
        return kpi;
    }

    public void setKpi(String kpi) {
        this.kpi = kpi;
    }

    public BigDecimal getBase() {
        return base;
    }

    public void setBase(BigDecimal base) {
        this.base = base;
    }

    public BigDecimal getMeta() {
        return meta;
    }

    public void setMeta(BigDecimal meta) {
        this.meta = meta;
    }

    public String getTiempoMedicion() {
        return tiempoMedicion;
    }

    public void setTiempoMedicion(String tiempoMedicion) {
        this.tiempoMedicion = tiempoMedicion;
    }

    @XmlTransient
    public List<ComponenteFormla> getComponenteFormlaList() {
        return componenteFormlaList;
    }

    public void setComponenteFormlaList(List<ComponenteFormla> componenteFormlaList) {
        this.componenteFormlaList = componenteFormlaList;
    }

    @XmlTransient
    public List<Semaforo> getSemaforoList() {
        return semaforoList;
    }

    public void setSemaforoList(List<Semaforo> semaforoList) {
        this.semaforoList = semaforoList;
    }

    public Usuario getIdUsuarioResponsable() {
        return idUsuarioResponsable;
    }

    public void setIdUsuarioResponsable(Usuario idUsuarioResponsable) {
        this.idUsuarioResponsable = idUsuarioResponsable;
    }

    @XmlTransient
    public List<ObjetivoEstrategicoIndicador> getObjetivoEstrategicoIndicadorList() {
        return objetivoEstrategicoIndicadorList;
    }

    public void setObjetivoEstrategicoIndicadorList(List<ObjetivoEstrategicoIndicador> objetivoEstrategicoIndicadorList) {
        this.objetivoEstrategicoIndicadorList = objetivoEstrategicoIndicadorList;
    }

    @XmlTransient
    public List<Historial> getHistorialList() {
        return historialList;
    }

    public void setHistorialList(List<Historial> historialList) {
        this.historialList = historialList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idIndicador != null ? idIndicador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Indicador)) {
            return false;
        }
        Indicador other = (Indicador) object;
        if ((this.idIndicador == null && other.idIndicador != null) || (this.idIndicador != null && !this.idIndicador.equals(other.idIndicador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.ucuenca.ieps.modelado.principal.Indicador[ idIndicador=" + idIndicador + " ]";
    }
    
}
