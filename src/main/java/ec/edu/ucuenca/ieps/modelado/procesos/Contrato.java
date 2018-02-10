/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ucuenca.ieps.modelado.procesos;

import ec.edu.ucuenca.ieps.modelado.organizacion.Socio;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jfarf
 */
@Entity
@Table(name = "contrato")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contrato.findAll", query = "SELECT c FROM Contrato c"),
    @NamedQuery(name = "Contrato.findByCodigo", query = "SELECT c FROM Contrato c WHERE c.codigo = :codigo"),
    @NamedQuery(name = "Contrato.findByFechaInicio", query = "SELECT c FROM Contrato c WHERE c.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "Contrato.findByFechaFin", query = "SELECT c FROM Contrato c WHERE c.fechaFin = :fechaFin"),
    @NamedQuery(name = "Contrato.findByPrecioDiaro", query = "SELECT c FROM Contrato c WHERE c.precioDiaro = :precioDiaro"),
    @NamedQuery(name = "Contrato.findByPrecioMensual", query = "SELECT c FROM Contrato c WHERE c.precioMensual = :precioMensual")})
public class Contrato implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Column(name = "fechaInicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "fechaFin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "precioDiaro")
    private BigDecimal precioDiaro;
    @Column(name = "precioMensual")
    private BigDecimal precioMensual;
    @JoinColumn(name = "codigo_socio", referencedColumnName = "CODIGO")
    @ManyToOne(fetch = FetchType.LAZY)
    private Socio codigoSocio;
    @OneToMany(mappedBy = "codigoContrato", fetch = FetchType.LAZY)
    private List<ContratoProducto> contratoProductoList;

    public Contrato() {
    }

    public Contrato(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
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

    public BigDecimal getPrecioDiaro() {
        return precioDiaro;
    }

    public void setPrecioDiaro(BigDecimal precioDiaro) {
        this.precioDiaro = precioDiaro;
    }

    public BigDecimal getPrecioMensual() {
        return precioMensual;
    }

    public void setPrecioMensual(BigDecimal precioMensual) {
        this.precioMensual = precioMensual;
    }

    public Socio getCodigoSocio() {
        return codigoSocio;
    }

    public void setCodigoSocio(Socio codigoSocio) {
        this.codigoSocio = codigoSocio;
    }

    @XmlTransient
    public List<ContratoProducto> getContratoProductoList() {
        return contratoProductoList;
    }

    public void setContratoProductoList(List<ContratoProducto> contratoProductoList) {
        this.contratoProductoList = contratoProductoList;
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
        if (!(object instanceof Contrato)) {
            return false;
        }
        Contrato other = (Contrato) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.ucuenca.ieps.modelado.procesos.Contrato[ codigo=" + codigo + " ]";
    }
    
}
