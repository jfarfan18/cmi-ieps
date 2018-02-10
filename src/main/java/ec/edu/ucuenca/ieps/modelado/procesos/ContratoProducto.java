/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ucuenca.ieps.modelado.procesos;

import java.io.Serializable;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jfarf
 */
@Entity
@Table(name = "contrato_producto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ContratoProducto.findAll", query = "SELECT c FROM ContratoProducto c"),
    @NamedQuery(name = "ContratoProducto.findByCodigo", query = "SELECT c FROM ContratoProducto c WHERE c.codigo = :codigo"),
    @NamedQuery(name = "ContratoProducto.findByEliminado", query = "SELECT c FROM ContratoProducto c WHERE c.eliminado = :eliminado")})
public class ContratoProducto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Size(max = 1)
    @Column(name = "eliminado")
    private String eliminado;
    @JoinColumn(name = "codigo_contrato", referencedColumnName = "codigo")
    @ManyToOne(fetch = FetchType.LAZY)
    private Contrato codigoContrato;
    @JoinColumn(name = "codigo_producto", referencedColumnName = "codigo")
    @ManyToOne(fetch = FetchType.LAZY)
    private Producto codigoProducto;

    public ContratoProducto() {
    }

    public ContratoProducto(Integer codigo) {
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

    public Contrato getCodigoContrato() {
        return codigoContrato;
    }

    public void setCodigoContrato(Contrato codigoContrato) {
        this.codigoContrato = codigoContrato;
    }

    public Producto getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(Producto codigoProducto) {
        this.codigoProducto = codigoProducto;
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
        if (!(object instanceof ContratoProducto)) {
            return false;
        }
        ContratoProducto other = (ContratoProducto) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.ucuenca.ieps.modelado.procesos.ContratoProducto[ codigo=" + codigo + " ]";
    }
    
}
