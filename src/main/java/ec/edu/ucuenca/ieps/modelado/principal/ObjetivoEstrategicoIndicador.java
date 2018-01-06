/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ucuenca.ieps.modelado.principal;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jfarf
 */
@Entity
@Table(name = "objetivo_estrategico_indicador")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ObjetivoEstrategicoIndicador.findAll", query = "SELECT o FROM ObjetivoEstrategicoIndicador o"),
    @NamedQuery(name = "ObjetivoEstrategicoIndicador.findByIdObjetivoEstrategico", query = "SELECT o FROM ObjetivoEstrategicoIndicador o WHERE o.objetivoEstrategicoIndicadorPK.idObjetivoEstrategico = :idObjetivoEstrategico"),
    @NamedQuery(name = "ObjetivoEstrategicoIndicador.findByIdIndicador", query = "SELECT o FROM ObjetivoEstrategicoIndicador o WHERE o.objetivoEstrategicoIndicadorPK.idIndicador = :idIndicador"),
    @NamedQuery(name = "ObjetivoEstrategicoIndicador.findByMeta", query = "SELECT o FROM ObjetivoEstrategicoIndicador o WHERE o.meta = :meta"),
    @NamedQuery(name = "ObjetivoEstrategicoIndicador.findByDefinicion", query = "SELECT o FROM ObjetivoEstrategicoIndicador o WHERE o.definicion = :definicion"),
    @NamedQuery(name = "ObjetivoEstrategicoIndicador.findByAclaracion", query = "SELECT o FROM ObjetivoEstrategicoIndicador o WHERE o.aclaracion = :aclaracion"),
    @NamedQuery(name = "ObjetivoEstrategicoIndicador.findByConceptualizacion", query = "SELECT o FROM ObjetivoEstrategicoIndicador o WHERE o.conceptualizacion = :conceptualizacion")})
public class ObjetivoEstrategicoIndicador implements Serializable {

    public static String findByIdObjetivoEstrategico="Objetivoestrategicoindicador.findByIdObjetivoEstrategico";
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ObjetivoEstrategicoIndicadorPK objetivoEstrategicoIndicadorPK;
    @Size(max = 250)
    @Column(name = "meta")
    private String meta;
    @Size(max = 150)
    @Column(name = "definicion")
    private String definicion;
    @Size(max = 250)
    @Column(name = "aclaracion")
    private String aclaracion;
    @Size(max = 250)
    @Column(name = "conceptualizacion")
    private String conceptualizacion;
    @JoinColumn(name = "idIndicador", referencedColumnName = "idIndicador", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Indicador indicador;
    @JoinColumn(name = "idObjetivoEstrategico", referencedColumnName = "idObjetivoEstrategico", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ObjetivoEstrategico objetivoEstrategico;

    public ObjetivoEstrategicoIndicador() {
    }

    public ObjetivoEstrategicoIndicador(ObjetivoEstrategicoIndicadorPK objetivoEstrategicoIndicadorPK) {
        this.objetivoEstrategicoIndicadorPK = objetivoEstrategicoIndicadorPK;
    }

    public ObjetivoEstrategicoIndicador(int idObjetivoEstrategico, int idIndicador) {
        this.objetivoEstrategicoIndicadorPK = new ObjetivoEstrategicoIndicadorPK(idObjetivoEstrategico, idIndicador);
    }

    public ObjetivoEstrategicoIndicadorPK getObjetivoEstrategicoIndicadorPK() {
        return objetivoEstrategicoIndicadorPK;
    }

    public void setObjetivoEstrategicoIndicadorPK(ObjetivoEstrategicoIndicadorPK objetivoEstrategicoIndicadorPK) {
        this.objetivoEstrategicoIndicadorPK = objetivoEstrategicoIndicadorPK;
    }

    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }

    public String getDefinicion() {
        return definicion;
    }

    public void setDefinicion(String definicion) {
        this.definicion = definicion;
    }

    public String getAclaracion() {
        return aclaracion;
    }

    public void setAclaracion(String aclaracion) {
        this.aclaracion = aclaracion;
    }

    public String getConceptualizacion() {
        return conceptualizacion;
    }

    public void setConceptualizacion(String conceptualizacion) {
        this.conceptualizacion = conceptualizacion;
    }

    public Indicador getIndicador() {
        return indicador;
    }

    public void setIndicador(Indicador indicador) {
        this.indicador = indicador;
    }

    public ObjetivoEstrategico getObjetivoEstrategico() {
        return objetivoEstrategico;
    }

    public void setObjetivoEstrategico(ObjetivoEstrategico objetivoEstrategico) {
        this.objetivoEstrategico = objetivoEstrategico;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (objetivoEstrategicoIndicadorPK != null ? objetivoEstrategicoIndicadorPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ObjetivoEstrategicoIndicador)) {
            return false;
        }
        ObjetivoEstrategicoIndicador other = (ObjetivoEstrategicoIndicador) object;
        if ((this.objetivoEstrategicoIndicadorPK == null && other.objetivoEstrategicoIndicadorPK != null) || (this.objetivoEstrategicoIndicadorPK != null && !this.objetivoEstrategicoIndicadorPK.equals(other.objetivoEstrategicoIndicadorPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.ucuenca.ieps.modelado.principal.ObjetivoEstrategicoIndicador[ objetivoEstrategicoIndicadorPK=" + objetivoEstrategicoIndicadorPK + " ]";
    }
    
}
