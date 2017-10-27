/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ucuenca.ieps.modelado.clientes;

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
@Table(name = "representante_legal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RepresentanteLegal.findAll", query = "SELECT r FROM RepresentanteLegal r"),
    @NamedQuery(name = "RepresentanteLegal.findByCodigo", query = "SELECT r FROM RepresentanteLegal r WHERE r.codigo = :codigo"),
    @NamedQuery(name = "RepresentanteLegal.findByEliminado", query = "SELECT r FROM RepresentanteLegal r WHERE r.eliminado = :eliminado"),
    @NamedQuery(name = "RepresentanteLegal.findByFechaActualizacion", query = "SELECT r FROM RepresentanteLegal r WHERE r.fechaActualizacion = :fechaActualizacion"),
//Personalizadas
    @NamedQuery(name = "RepresentanteLegal.findByCodigoPersonaJuridica", query = "SELECT r FROM RepresentanteLegal r WHERE r.codigoPersonaJuridico.codigoPersona = :codigoRepresentante"),
    @NamedQuery(name = "RepresentanteLegal.findByCodigoPersonaJuridicaEliminado", query = "SELECT r FROM RepresentanteLegal r WHERE r.codigoPersonaJuridico.codigoPersona = :codigoRepresentante and r.eliminado = :eliminado")
})
public class RepresentanteLegal implements Serializable {
    public final static String findByEliminado="RepresentanteLegal.findByEliminado";
    public final static String findByCodigoPersonaJuridica="RepresentanteLegal.findByCodigoPersonaJuridica";
    public final static String findByCodigoPersonaJuridicaEliminado="RepresentanteLegal.findByCodigoPersonaJuridicaEliminado";

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CODIGO")
    private Integer codigo;
    @Size(max = 1)
    @Column(name = "ELIMINADO")
    private String eliminado;
    @Column(name = "FECHA_ACTUALIZACION")
    @Temporal(TemporalType.DATE)
    private Date fechaActualizacion;
    @JoinColumn(name = "CODIGO_PERSONA_REPRESENTANTE", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Persona codigoPersonaRepresentante;
    @JoinColumn(name = "CODIGO_PERSONA_JURIDICO", referencedColumnName = "CODIGO_PERSONA")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PersonaJuridica codigoPersonaJuridico;

    public RepresentanteLegal() {
    }

    public RepresentanteLegal(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getEliminado() {
        return eliminado;
    }

    public void setEliminado(String eliminado) {
        this.eliminado = eliminado;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public Persona getCodigoPersonaRepresentante() {
        return codigoPersonaRepresentante;
    }

    public void setCodigoPersonaRepresentante(Persona codigoPersonaRepresentante) {
        this.codigoPersonaRepresentante = codigoPersonaRepresentante;
    }

    public PersonaJuridica getCodigoPersonaJuridico() {
        return codigoPersonaJuridico;
    }

    public void setCodigoPersonaJuridico(PersonaJuridica codigoPersonaJuridico) {
        this.codigoPersonaJuridico = codigoPersonaJuridico;
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
        if (!(object instanceof RepresentanteLegal)) {
            return false;
        }
        RepresentanteLegal other = (RepresentanteLegal) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.ucuenca.ieps.modelado.clientes.RepresentanteLegal[ codigo=" + codigo + " ]";
    }
    
}
