/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ucuenca.ieps.modelado.clientes;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jfarf
 */
@Entity
@Table(name = "persona_juridica")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PersonaJuridica.findAll", query = "SELECT p FROM PersonaJuridica p"),
    @NamedQuery(name = "PersonaJuridica.findByCodigoPersona", query = "SELECT p FROM PersonaJuridica p WHERE p.codigoPersona = :codigoPersona"),
   // @NamedQuery(name = "PersonaJuridica.findByFechaActualizacion", query = "SELECT p FROM PersonaJuridica p WHERE p.fechaActualizacion = :fechaActualizacion"),
//personalizadas
    @NamedQuery(name = "PersonaJuridica.findByIdentificacion", query = "SELECT p FROM PersonaJuridica p WHERE p.persona.identificacion = :identificacion"),
    //@NamedQuery(name = "PersonaJuridica.findByEliminado", query = "SELECT p FROM PersonaJuridica p WHERE p.persona. = :fechaActualizacion")
})
public class PersonaJuridica implements Serializable {
    public final static String findByIdentificacion="PersonaJuridica.findByIdentificacion";
    //public final static String findByEliminado="PersonaJuridica.findByEliminado";
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoPersonaJuridico", fetch = FetchType.LAZY)
    private List<RepresentanteLegal> representanteLegalList;
        
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_PERSONA")
    private Integer codigoPersona;
    @JoinColumn(name = "CODIGO_PERSONA", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private Persona persona;

    public PersonaJuridica() {
    }

    public PersonaJuridica(Integer codigoPersona) {
        this.codigoPersona = codigoPersona;
    }

    public Integer getCodigoPersona() {
        return codigoPersona;
    }

    public void setCodigoPersona(Integer codigoPersona) {
        this.codigoPersona = codigoPersona;
    }


    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoPersona != null ? codigoPersona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonaJuridica)) {
            return false;
        }
        PersonaJuridica other = (PersonaJuridica) object;
        if ((this.codigoPersona == null && other.codigoPersona != null) || (this.codigoPersona != null && !this.codigoPersona.equals(other.codigoPersona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.ucuenca.ieps.modelado.clientes.PersonaJuridica[ codigoPersona=" + codigoPersona + " ]";
    }

    @XmlTransient
    public List<RepresentanteLegal> getRepresentanteLegalList() {
        return representanteLegalList;
    }

    public void setRepresentanteLegalList(List<RepresentanteLegal> representanteLegalList) {
        this.representanteLegalList = representanteLegalList;
    }
    
}
