/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ucuenca.ieps.modelado.principal;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
@Table(name = "componente_formla")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ComponenteFormla.findAll", query = "SELECT c FROM ComponenteFormla c"),
    @NamedQuery(name = "ComponenteFormla.findByIdcomponenteformla", query = "SELECT c FROM ComponenteFormla c WHERE c.idcomponenteformla = :idcomponenteformla"),
    @NamedQuery(name = "ComponenteFormla.findByDescripcion", query = "SELECT c FROM ComponenteFormla c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "ComponenteFormla.findByUnidad", query = "SELECT c FROM ComponenteFormla c WHERE c.unidad = :unidad")})
public class ComponenteFormla implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcomponenteformla")
    private Integer idcomponenteformla;
    @Size(max = 45)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 20)
    @Column(name = "unidad")
    private String unidad;
    @JoinColumn(name = "idindicador", referencedColumnName = "idIndicador")
    @ManyToOne(fetch = FetchType.LAZY)
    private Indicador idindicador;
    @OneToMany(mappedBy = "idcomponenteformula", fetch = FetchType.LAZY)
    private List<DetalleHistorial> detalleHistorialList;

    public ComponenteFormla() {
    }

    public ComponenteFormla(Integer idcomponenteformla) {
        this.idcomponenteformla = idcomponenteformla;
    }

    public Integer getIdcomponenteformla() {
        return idcomponenteformla;
    }

    public void setIdcomponenteformla(Integer idcomponenteformla) {
        this.idcomponenteformla = idcomponenteformla;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public Indicador getIdindicador() {
        return idindicador;
    }

    public void setIdindicador(Indicador idindicador) {
        this.idindicador = idindicador;
    }

    @XmlTransient
    public List<DetalleHistorial> getDetalleHistorialList() {
        return detalleHistorialList;
    }

    public void setDetalleHistorialList(List<DetalleHistorial> detalleHistorialList) {
        this.detalleHistorialList = detalleHistorialList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcomponenteformla != null ? idcomponenteformla.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ComponenteFormla)) {
            return false;
        }
        ComponenteFormla other = (ComponenteFormla) object;
        if ((this.idcomponenteformla == null && other.idcomponenteformla != null) || (this.idcomponenteformla != null && !this.idcomponenteformla.equals(other.idcomponenteformla))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.ucuenca.ieps.modelado.principal.ComponenteFormla[ idcomponenteformla=" + idcomponenteformla + " ]";
    }
    
}
