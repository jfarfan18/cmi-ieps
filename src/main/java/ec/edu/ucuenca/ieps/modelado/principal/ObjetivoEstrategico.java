/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ucuenca.ieps.modelado.principal;

import java.io.Serializable;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jfarf
 */
@Entity
@Table(name = "objetivo_estrategico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ObjetivoEstrategico.findAll", query = "SELECT o FROM ObjetivoEstrategico o"),
    @NamedQuery(name = "ObjetivoEstrategico.findByIdObjetivoEstrategico", query = "SELECT o FROM ObjetivoEstrategico o WHERE o.idObjetivoEstrategico = :idObjetivoEstrategico"),
    @NamedQuery(name = "ObjetivoEstrategico.findByNumero", query = "SELECT o FROM ObjetivoEstrategico o WHERE o.numero = :numero"),
    @NamedQuery(name = "ObjetivoEstrategico.findByNombre", query = "SELECT o FROM ObjetivoEstrategico o WHERE o.nombre = :nombre")})
public class ObjetivoEstrategico implements Serializable {

    public static String findByIdObjetivoEstrategico="Objetivoestrategico.findByIdObjetivoEstrategico";
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idObjetivoEstrategico")
    private Integer idObjetivoEstrategico;
    @Column(name = "numero")
    private Integer numero;
    @Size(max = 1000)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idObjetivoEstrategico", fetch = FetchType.LAZY)
    private List<Actividad> actividadList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "objetivoEstrategico", fetch = FetchType.LAZY)
    private List<ObjetivoEstrategicoIndicador> objetivoEstrategicoIndicadorList;
    @JoinColumn(name = "idEstrategiaGlobal", referencedColumnName = "idEstrategiaGlobal")
    @ManyToOne(fetch = FetchType.LAZY)
    private EstrategiaGlobal idEstrategiaGlobal;
    @JoinColumn(name = "idPerspectiva", referencedColumnName = "idPerspectiva")
    @ManyToOne(fetch = FetchType.LAZY)
    private Perspectiva idPerspectiva;

    public ObjetivoEstrategico() {
    }

    public ObjetivoEstrategico(Integer idObjetivoEstrategico) {
        this.idObjetivoEstrategico = idObjetivoEstrategico;
    }

    public Integer getIdObjetivoEstrategico() {
        return idObjetivoEstrategico;
    }

    public void setIdObjetivoEstrategico(Integer idObjetivoEstrategico) {
        this.idObjetivoEstrategico = idObjetivoEstrategico;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<Actividad> getActividadList() {
        return actividadList;
    }

    public void setActividadList(List<Actividad> actividadList) {
        this.actividadList = actividadList;
    }

    @XmlTransient
    public List<ObjetivoEstrategicoIndicador> getObjetivoEstrategicoIndicadorList() {
        return objetivoEstrategicoIndicadorList;
    }

    public void setObjetivoEstrategicoIndicadorList(List<ObjetivoEstrategicoIndicador> objetivoEstrategicoIndicadorList) {
        this.objetivoEstrategicoIndicadorList = objetivoEstrategicoIndicadorList;
    }

    public EstrategiaGlobal getIdEstrategiaGlobal() {
        return idEstrategiaGlobal;
    }

    public void setIdEstrategiaGlobal(EstrategiaGlobal idEstrategiaGlobal) {
        this.idEstrategiaGlobal = idEstrategiaGlobal;
    }

    public Perspectiva getIdPerspectiva() {
        return idPerspectiva;
    }

    public void setIdPerspectiva(Perspectiva idPerspectiva) {
        this.idPerspectiva = idPerspectiva;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idObjetivoEstrategico != null ? idObjetivoEstrategico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ObjetivoEstrategico)) {
            return false;
        }
        ObjetivoEstrategico other = (ObjetivoEstrategico) object;
        if ((this.idObjetivoEstrategico == null && other.idObjetivoEstrategico != null) || (this.idObjetivoEstrategico != null && !this.idObjetivoEstrategico.equals(other.idObjetivoEstrategico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.ucuenca.ieps.modelado.principal.ObjetivoEstrategico[ idObjetivoEstrategico=" + idObjetivoEstrategico + " ]";
    }
    
}
