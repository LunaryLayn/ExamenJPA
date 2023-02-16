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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "MODELO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Modelo.findAll", query = "SELECT m FROM Modelo m")
    , @NamedQuery(name = "Modelo.findById", query = "SELECT m FROM Modelo m WHERE m.id = :id")
    , @NamedQuery(name = "Modelo.findByNombre", query = "SELECT m FROM Modelo m WHERE m.nombre = :nombre")
    , @NamedQuery(name = "Modelo.findByImagen", query = "SELECT m FROM Modelo m WHERE m.imagen = :imagen")})
public class Modelo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Short id;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "IMAGEN")
    private String imagen;
    @JoinTable(name = "TIPOMODELO", joinColumns = {
        @JoinColumn(name = "MODELO", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "TIPO", referencedColumnName = "ID")})
    @ManyToMany
    private List<Tipo> tipoList;
    @OneToMany(mappedBy = "modelo")
    private List<Modeloacabado> modeloacabadoList;
    @OneToMany(mappedBy = "modelo")
    private List<Cita> citaList;

    public Modelo() {
    }

    public Modelo(Short id) {
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

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @XmlTransient
    public List<Tipo> getTipoList() {
        return tipoList;
    }

    public void setTipoList(List<Tipo> tipoList) {
        this.tipoList = tipoList;
    }

    @XmlTransient
    public List<Modeloacabado> getModeloacabadoList() {
        return modeloacabadoList;
    }

    public void setModeloacabadoList(List<Modeloacabado> modeloacabadoList) {
        this.modeloacabadoList = modeloacabadoList;
    }

    @XmlTransient
    public List<Cita> getCitaList() {
        return citaList;
    }

    public void setCitaList(List<Cita> citaList) {
        this.citaList = citaList;
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
        if (!(object instanceof Modelo)) {
            return false;
        }
        Modelo other = (Modelo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Modelo[ id=" + id + " ]";
    }
    
}
