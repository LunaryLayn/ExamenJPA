/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author hugop
 */
@Entity
@Table(name = "ACABADO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Acabado.findAll", query = "SELECT a FROM Acabado a")
    , @NamedQuery(name = "Acabado.findById", query = "SELECT a FROM Acabado a WHERE a.id = :id")
    , @NamedQuery(name = "Acabado.findByNombre", query = "SELECT a FROM Acabado a WHERE a.nombre = :nombre")})
public class Acabado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Short id;
    @Column(name = "NOMBRE")
    private String nombre;
    @OneToMany(mappedBy = "acabado")
    private List<Modeloacabado> modeloacabadoList;

    public Acabado() {
    }

    public Acabado(Short id) {
        this.id = id;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<Modeloacabado> getModeloacabadoList() {
        return modeloacabadoList;
    }

    public void setModeloacabadoList(List<Modeloacabado> modeloacabadoList) {
        this.modeloacabadoList = modeloacabadoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Acabado)) {
            return false;
        }
        Acabado other = (Acabado) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Acabado[ id=" + id + " ]";
    }
    
}
