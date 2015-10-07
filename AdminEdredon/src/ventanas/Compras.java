/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author Daniel
 */
@Entity
@Table(name = "compra", catalog = "adminEdredon", schema = "")
@NamedQueries({
    @NamedQuery(name = "Compra.findAll", query = "SELECT c FROM Compra c"),
    @NamedQuery(name = "Compra.findByIdCompras", query = "SELECT c FROM Compra c WHERE c.idCompras = :idCompras"),
    @NamedQuery(name = "Compra.findByFechaCompra", query = "SELECT c FROM Compra c WHERE c.fechaCompra = :fechaCompra"),
    @NamedQuery(name = "Compra.findByFechaCompraSaldada", query = "SELECT c FROM Compra c WHERE c.fechaCompraSaldada = :fechaCompraSaldada"),
    @NamedQuery(name = "Compra.findByFechaUltimoAbono", query = "SELECT c FROM Compra c WHERE c.fechaUltimoAbono = :fechaUltimoAbono"),
    @NamedQuery(name = "Compra.findBySucursal", query = "SELECT c FROM Compra c WHERE c.sucursal = :sucursal"),
    @NamedQuery(name = "Compra.findByNombreSucursal", query = "SELECT c FROM Compra c WHERE c.nombreSucursal = :nombreSucursal"),
    @NamedQuery(name = "Compra.findByDetalleCompra", query = "SELECT c FROM Compra c WHERE c.detalleCompra = :detalleCompra"),
    @NamedQuery(name = "Compra.findByObservacion", query = "SELECT c FROM Compra c WHERE c.observacion = :observacion"),
    @NamedQuery(name = "Compra.findByAbono", query = "SELECT c FROM Compra c WHERE c.abono = :abono"),
    @NamedQuery(name = "Compra.findByImporte", query = "SELECT c FROM Compra c WHERE c.importe = :importe"),
    @NamedQuery(name = "Compra.findByEstatus", query = "SELECT c FROM Compra c WHERE c.estatus = :estatus"),
    @NamedQuery(name = "Compra.findByTipoDeGasto", query = "SELECT c FROM Compra c WHERE c.tipoDeGasto = :tipoDeGasto"),
    @NamedQuery(name = "Compra.findByProveedorCompra", query = "SELECT c FROM Compra c WHERE c.proveedorCompra = :proveedorCompra"),
    @NamedQuery(name = "Compra.findByNombreProveedor", query = "SELECT c FROM Compra c WHERE c.nombreProveedor = :nombreProveedor"),
    @NamedQuery(name = "Compra.findByCredito", query = "SELECT c FROM Compra c WHERE c.credito = :credito"),
    @NamedQuery(name = "Compra.findByTipoGasto", query = "SELECT c FROM Compra c WHERE c.tipoGasto = :tipoGasto"),
    @NamedQuery(name = "Compra.findByMetodoPago", query = "SELECT c FROM Compra c WHERE c.metodoPago = :metodoPago")})
public class Compras implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idCompras")
    private Integer idCompras;
    @Column(name = "fechaCompra")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCompra;
    @Column(name = "fechaCompraSaldada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCompraSaldada;
    @Column(name = "fechaUltimoAbono")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaUltimoAbono;
    @Column(name = "sucursal")
    private Integer sucursal;
    @Column(name = "nombreSucursal")
    private String nombreSucursal;
    @Column(name = "detalleCompra")
    private Integer detalleCompra;
    @Column(name = "observacion")
    private String observacion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "abono")
    private Double abono;
    @Column(name = "importe")
    private Double importe;
    @Column(name = "estatus")
    private Boolean estatus;
    @Column(name = "tipoDeGasto")
    private String tipoDeGasto;
    @Column(name = "proveedorCompra")
    private Integer proveedorCompra;
    @Column(name = "nombreProveedor")
    private String nombreProveedor;
    @Column(name = "credito")
    private Boolean credito;
    @Column(name = "tipoGasto")
    private String tipoGasto;
    @Column(name = "metodoPago")
    private String metodoPago;

    public Compras() {
    }

    public Compras(Integer idCompras) {
        this.idCompras = idCompras;
    }

    public Integer getIdCompras() {
        return idCompras;
    }

    public void setIdCompras(Integer idCompras) {
        Integer oldIdCompras = this.idCompras;
        this.idCompras = idCompras;
        changeSupport.firePropertyChange("idCompras", oldIdCompras, idCompras);
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        Date oldFechaCompra = this.fechaCompra;
        this.fechaCompra = fechaCompra;
        changeSupport.firePropertyChange("fechaCompra", oldFechaCompra, fechaCompra);
    }

    public Date getFechaCompraSaldada() {
        return fechaCompraSaldada;
    }

    public void setFechaCompraSaldada(Date fechaCompraSaldada) {
        Date oldFechaCompraSaldada = this.fechaCompraSaldada;
        this.fechaCompraSaldada = fechaCompraSaldada;
        changeSupport.firePropertyChange("fechaCompraSaldada", oldFechaCompraSaldada, fechaCompraSaldada);
    }

    public Date getFechaUltimoAbono() {
        return fechaUltimoAbono;
    }

    public void setFechaUltimoAbono(Date fechaUltimoAbono) {
        Date oldFechaUltimoAbono = this.fechaUltimoAbono;
        this.fechaUltimoAbono = fechaUltimoAbono;
        changeSupport.firePropertyChange("fechaUltimoAbono", oldFechaUltimoAbono, fechaUltimoAbono);
    }

    public Integer getSucursal() {
        return sucursal;
    }

    public void setSucursal(Integer sucursal) {
        Integer oldSucursal = this.sucursal;
        this.sucursal = sucursal;
        changeSupport.firePropertyChange("sucursal", oldSucursal, sucursal);
    }

    public String getNombreSucursal() {
        return nombreSucursal;
    }

    public void setNombreSucursal(String nombreSucursal) {
        String oldNombreSucursal = this.nombreSucursal;
        this.nombreSucursal = nombreSucursal;
        changeSupport.firePropertyChange("nombreSucursal", oldNombreSucursal, nombreSucursal);
    }

    public Integer getDetalleCompra() {
        return detalleCompra;
    }

    public void setDetalleCompra(Integer detalleCompra) {
        Integer oldDetalleCompra = this.detalleCompra;
        this.detalleCompra = detalleCompra;
        changeSupport.firePropertyChange("detalleCompra", oldDetalleCompra, detalleCompra);
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        String oldObservacion = this.observacion;
        this.observacion = observacion;
        changeSupport.firePropertyChange("observacion", oldObservacion, observacion);
    }

    public Double getAbono() {
        return abono;
    }

    public void setAbono(Double abono) {
        Double oldAbono = this.abono;
        this.abono = abono;
        changeSupport.firePropertyChange("abono", oldAbono, abono);
    }

    public Double getImporte() {
        return importe;
    }

    public void setImporte(Double importe) {
        Double oldImporte = this.importe;
        this.importe = importe;
        changeSupport.firePropertyChange("importe", oldImporte, importe);
    }

    public Boolean getEstatus() {
        return estatus;
    }

    public void setEstatus(Boolean estatus) {
        Boolean oldEstatus = this.estatus;
        this.estatus = estatus;
        changeSupport.firePropertyChange("estatus", oldEstatus, estatus);
    }

    public String getTipoDeGasto() {
        return tipoDeGasto;
    }

    public void setTipoDeGasto(String tipoDeGasto) {
        String oldTipoDeGasto = this.tipoDeGasto;
        this.tipoDeGasto = tipoDeGasto;
        changeSupport.firePropertyChange("tipoDeGasto", oldTipoDeGasto, tipoDeGasto);
    }

    public Integer getProveedorCompra() {
        return proveedorCompra;
    }

    public void setProveedorCompra(Integer proveedorCompra) {
        Integer oldProveedorCompra = this.proveedorCompra;
        this.proveedorCompra = proveedorCompra;
        changeSupport.firePropertyChange("proveedorCompra", oldProveedorCompra, proveedorCompra);
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        String oldNombreProveedor = this.nombreProveedor;
        this.nombreProveedor = nombreProveedor;
        changeSupport.firePropertyChange("nombreProveedor", oldNombreProveedor, nombreProveedor);
    }

    public Boolean getCredito() {
        return credito;
    }

    public void setCredito(Boolean credito) {
        Boolean oldCredito = this.credito;
        this.credito = credito;
        changeSupport.firePropertyChange("credito", oldCredito, credito);
    }

    public String getTipoGasto() {
        return tipoGasto;
    }

    public void setTipoGasto(String tipoGasto) {
        String oldTipoGasto = this.tipoGasto;
        this.tipoGasto = tipoGasto;
        changeSupport.firePropertyChange("tipoGasto", oldTipoGasto, tipoGasto);
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        String oldMetodoPago = this.metodoPago;
        this.metodoPago = metodoPago;
        changeSupport.firePropertyChange("metodoPago", oldMetodoPago, metodoPago);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCompras != null ? idCompras.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Compras)) {
            return false;
        }
        Compras other = (Compras) object;
        if ((this.idCompras == null && other.idCompras != null) || (this.idCompras != null && !this.idCompras.equals(other.idCompras))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "adminedredon.Compra[ idCompras=" + idCompras + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
