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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "acta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Acta.findAll", query = "SELECT a FROM Acta a"),
    @NamedQuery(name = "Acta.findByCodigo", query = "SELECT a FROM Acta a WHERE a.codigo = :codigo"),
    @NamedQuery(name = "Acta.findByFechaReunion", query = "SELECT a FROM Acta a WHERE a.fechaReunion = :fechaReunion"),
    @NamedQuery(name = "Acta.findByFechaActa", query = "SELECT a FROM Acta a WHERE a.fechaActa = :fechaActa"),
    @NamedQuery(name = "Acta.findByDescripcion", query = "SELECT a FROM Acta a WHERE a.descripcion = :descripcion"),
    @NamedQuery(name = "Acta.findByNumeroActa", query = "SELECT a FROM Acta a WHERE a.numeroActa = :numeroActa"),
    @NamedQuery(name = "Acta.findByDireccion", query = "SELECT a FROM Acta a WHERE a.direccion = :direccion")})
public class Acta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CODIGO")
    private Integer codigo;
    @Column(name = "FECHA_REUNION")
    @Temporal(TemporalType.DATE)
    private Date fechaReunion;
    @Column(name = "FECHA_ACTA")
    @Temporal(TemporalType.DATE)
    private Date fechaActa;
    @Size(max = 200)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Size(max = 45)
    @Column(name = "NUMERO_ACTA")
    private String numeroActa;
    @Size(max = 150)
    @Column(name = "DIRECCION")
    private String direccion;

    public Acta() {
    }

    public Acta(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Date getFechaReunion() {
        return fechaReunion;
    }

    public void setFechaReunion(Date fechaReunion) {
        this.fechaReunion = fechaReunion;
    }

    public Date getFechaActa() {
        return fechaActa;
    }

    public void setFechaActa(Date fechaActa) {
        this.fechaActa = fechaActa;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNumeroActa() {
        return numeroActa;
    }

    public void setNumeroActa(String numeroActa) {
        this.numeroActa = numeroActa;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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
        if (!(object instanceof Acta)) {
            return false;
        }
        Acta other = (Acta) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.ucuenca.ieps.modelado.organizacion.Acta[ codigo=" + codigo + " ]";
    }
    
}
