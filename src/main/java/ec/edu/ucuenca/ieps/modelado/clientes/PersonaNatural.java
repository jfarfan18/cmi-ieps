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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jfarf
 */
@Entity
@Table(name = "persona_natural")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PersonaNatural.findAll", query = "SELECT p FROM PersonaNatural p"),
    @NamedQuery(name = "PersonaNatural.findByFechaNacimiento", query = "SELECT p FROM PersonaNatural p WHERE p.fechaNacimiento = :fechaNacimiento"),
    @NamedQuery(name = "PersonaNatural.findByPrimerNombre", query = "SELECT p FROM PersonaNatural p WHERE p.primerNombre = :primerNombre"),
    @NamedQuery(name = "PersonaNatural.findBySegundoNombre", query = "SELECT p FROM PersonaNatural p WHERE p.segundoNombre = :segundoNombre"),
    @NamedQuery(name = "PersonaNatural.findByPrimerApellido", query = "SELECT p FROM PersonaNatural p WHERE p.primerApellido = :primerApellido"),
    @NamedQuery(name = "PersonaNatural.findBySegundoApellido", query = "SELECT p FROM PersonaNatural p WHERE p.segundoApellido = :segundoApellido"),
    @NamedQuery(name = "PersonaNatural.findBySexo", query = "SELECT p FROM PersonaNatural p WHERE p.sexo = :sexo"),
    @NamedQuery(name = "PersonaNatural.findByCodigoPersona", query = "SELECT p FROM PersonaNatural p WHERE p.codigoPersona = :codigoPersona"),
//personalizados
    @NamedQuery(name = "PersonaNatural.findByIdentificacion", query = "SELECT p FROM PersonaNatural p WHERE p.persona.identificacion = :identificacion"),
})
public class PersonaNatural implements Serializable {
    public final static String findByIdentificacion="PersonaNatural.findByIdentificacion";
    private static final long serialVersionUID = 1L;
    @Column(name = "FECHA_NACIMIENTO")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "PRIMER_NOMBRE")
    private String primerNombre;
    @Size(max = 45)
    @Column(name = "SEGUNDO_NOMBRE")
    private String segundoNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "PRIMER_APELLIDO")
    private String primerApellido;
    @Size(max = 45)
    @Column(name = "SEGUNDO_APELLIDO")
    private String segundoApellido;
    @Size(max = 1)
    @Column(name = "SEXO")
    private String sexo;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_PERSONA")
    private Integer codigoPersona;
    @JoinColumn(name = "CODIGO_ESTADO_CIVIL", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EstadoCivil codigoEstadoCivil;
    @JoinColumn(name = "CODIGO_PERSONA", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private Persona persona;
    @JoinColumn(name = "CODIGO_RAZA", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Raza codigoRaza;

    public PersonaNatural() {
    }

    public PersonaNatural(Integer codigoPersona) {
        this.codigoPersona = codigoPersona;
    }

    public PersonaNatural(Integer codigoPersona, String primerNombre, String primerApellido) {
        this.codigoPersona = codigoPersona;
        this.primerNombre = primerNombre;
        this.primerApellido = primerApellido;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Integer getCodigoPersona() {
        return codigoPersona;
    }

    public void setCodigoPersona(Integer codigoPersona) {
        this.codigoPersona = codigoPersona;
    }

    public EstadoCivil getCodigoEstadoCivil() {
        return codigoEstadoCivil;
    }

    public void setCodigoEstadoCivil(EstadoCivil codigoEstadoCivil) {
        this.codigoEstadoCivil = codigoEstadoCivil;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Raza getCodigoRaza() {
        return codigoRaza;
    }

    public void setCodigoRaza(Raza codigoRaza) {
        this.codigoRaza = codigoRaza;
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
        if (!(object instanceof PersonaNatural)) {
            return false;
        }
        PersonaNatural other = (PersonaNatural) object;
        if ((this.codigoPersona == null && other.codigoPersona != null) || (this.codigoPersona != null && !this.codigoPersona.equals(other.codigoPersona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.ucuenca.ieps.modelado.clientes.PersonaNatural[ codigoPersona=" + codigoPersona + " ]";
    }
    
}
