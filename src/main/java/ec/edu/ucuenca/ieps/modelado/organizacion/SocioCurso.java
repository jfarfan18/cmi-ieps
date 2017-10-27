/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ucuenca.ieps.modelado.organizacion;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jfarf
 */
@Entity
@Table(name = "socio_curso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SocioCurso.findAll", query = "SELECT s FROM SocioCurso s"),
    @NamedQuery(name = "SocioCurso.findByCodigo", query = "SELECT s FROM SocioCurso s WHERE s.codigo = :codigo"),
    @NamedQuery(name = "SocioCurso.findByCalificacion", query = "SELECT s FROM SocioCurso s WHERE s.calificacion = :calificacion"),
    @NamedQuery(name = "SocioCurso.findByFechaInicio", query = "SELECT s FROM SocioCurso s WHERE s.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "SocioCurso.findByFechaFin", query = "SELECT s FROM SocioCurso s WHERE s.fechaFin = :fechaFin")})
public class SocioCurso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CODIGO")
    private Integer codigo;
    @Size(max = 1)
    @Column(name = "CALIFICACION")
    private String calificacion;
    @Column(name = "FECHA_INICIO")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "FECHA_FIN")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @JoinColumn(name = "CURSO_CODIGO", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Curso cursoCodigo;
    @JoinColumn(name = "SOCIO_CODIGO", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Socio socioCodigo;

    public SocioCurso() {
    }

    public SocioCurso(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(String calificacion) {
        this.calificacion = calificacion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Curso getCursoCodigo() {
        return cursoCodigo;
    }

    public void setCursoCodigo(Curso cursoCodigo) {
        this.cursoCodigo = cursoCodigo;
    }

    public Socio getSocioCodigo() {
        return socioCodigo;
    }

    public void setSocioCodigo(Socio socioCodigo) {
        this.socioCodigo = socioCodigo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SocioCurso)) {
            return false;
        }
        SocioCurso other = (SocioCurso) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.ucuenca.ieps.modelado.organizacion.SocioCurso[ codigo=" + codigo + " ]";
    }
    
}
