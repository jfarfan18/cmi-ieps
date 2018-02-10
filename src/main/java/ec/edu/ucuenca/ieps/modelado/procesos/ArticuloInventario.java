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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "articulo_inventario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ArticuloInventario.findAll", query = "SELECT a FROM ArticuloInventario a"),
    @NamedQuery(name = "ArticuloInventario.findByCodigo", query = "SELECT a FROM ArticuloInventario a WHERE a.codigo = :codigo"),
    @NamedQuery(name = "ArticuloInventario.findByNombre", query = "SELECT a FROM ArticuloInventario a WHERE a.nombre = :nombre"),
    @NamedQuery(name = "ArticuloInventario.findByDescripcion", query = "SELECT a FROM ArticuloInventario a WHERE a.descripcion = :descripcion"),
    @NamedQuery(name = "ArticuloInventario.findByEliminado", query = "SELECT a FROM ArticuloInventario a WHERE a.eliminado = :eliminado")})
public class ArticuloInventario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CODIGO")
    private Integer codigo;
    @Size(max = 45)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 150)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Size(max = 1)
    @Column(name = "ELIMINADO")
    private String eliminado;

    public ArticuloInventario() {
    }

    public ArticuloInventario(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEliminado() {
        return eliminado;
    }

    public void setEliminado(String eliminado) {
        this.eliminado = eliminado;
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
        if (!(object instanceof ArticuloInventario)) {
            return false;
        }
        ArticuloInventario other = (ArticuloInventario) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.ucuenca.ieps.modelado.procesos.ArticuloInventario[ codigo=" + codigo + " ]";
    }
    
}
