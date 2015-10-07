/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Daniel
 */
@Entity
@Table(name = "almacen", catalog = "adminEdredon", schema = "")
@NamedQueries({
    @NamedQuery(name = "Almacen.findAll", query = "SELECT a FROM Almacen a"),
    @NamedQuery(name = "Almacen.findByIdAlmacen", query = "SELECT a FROM Almacen a WHERE a.idAlmacen = :idAlmacen"),
    @NamedQuery(name = "Almacen.findByNombre", query = "SELECT a FROM Almacen a WHERE a.nombre = :nombre"),
    @NamedQuery(name = "Almacen.findByMinimo", query = "SELECT a FROM Almacen a WHERE a.minimo = :minimo"),
    @NamedQuery(name = "Almacen.findByExistencias", query = "SELECT a FROM Almacen a WHERE a.existencias = :existencias")})
public class Almacen implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idAlmacen")
    private Integer idAlmacen;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "minimo")
    private Integer minimo;
    @Column(name = "existencias")
    private Integer existencias;

    public Almacen() {
    }

    public Almacen(Integer idAlmacen) {
        this.idAlmacen = idAlmacen;
    }

    public Integer getIdAlmacen() {
        return idAlmacen;
    }

    public void setIdAlmacen(Integer idAlmacen) {
        Integer oldIdAlmacen = this.idAlmacen;
        this.idAlmacen = idAlmacen;
        changeSupport.firePropertyChange("idAlmacen", oldIdAlmacen, idAlmacen);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        String oldNombre = this.nombre;
        this.nombre = nombre;
        changeSupport.firePropertyChange("nombre", oldNombre, nombre);
    }

    public Integer getMinimo() {
        return minimo;
    }

    public void setMinimo(Integer minimo) {
        Integer oldMinimo = this.minimo;
        this.minimo = minimo;
        changeSupport.firePropertyChange("minimo", oldMinimo, minimo);
    }

    public Integer getExistencias() {
        return existencias;
    }

    public void setExistencias(Integer existencias) {
        Integer oldExistencias = this.existencias;
        this.existencias = existencias;
        changeSupport.firePropertyChange("existencias", oldExistencias, existencias);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAlmacen != null ? idAlmacen.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Almacen)) {
            return false;
        }
        Almacen other = (Almacen) object;
        if ((this.idAlmacen == null && other.idAlmacen != null) || (this.idAlmacen != null && !this.idAlmacen.equals(other.idAlmacen))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ventanas.Almacen[ idAlmacen=" + idAlmacen + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
