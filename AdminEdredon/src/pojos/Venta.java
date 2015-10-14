package pojos;
// Generated 13/10/2015 08:49:05 PM by Hibernate Tools 4.3.1


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Venta generated by hbm2java
 */
@Entity
@Table(name="venta"
    ,catalog="adminedredon"
)
public class Venta  implements java.io.Serializable {


     private Integer idVenta;
     private Date fechaVenta;
     private Date fechaVentaSaldada;
     private Date fechaUltimoAbono;
     private Integer detalleVenta;
     private String descripcion;
     private Double abono;
     private double total;
     private boolean estatus;
     private int usuarioUltimoAjuste;
     private Boolean credito;
     private String metodoPago;

    public Venta() {
    }

	
    public Venta(Date fechaVenta, String descripcion, double total, boolean estatus, int usuarioUltimoAjuste) {
        this.fechaVenta = fechaVenta;
        this.descripcion = descripcion;
        this.total = total;
        this.estatus = estatus;
        this.usuarioUltimoAjuste = usuarioUltimoAjuste;
    }
    public Venta(Date fechaVenta, Date fechaVentaSaldada, Date fechaUltimoAbono, Integer detalleVenta, String descripcion, Double abono, double total, boolean estatus, int usuarioUltimoAjuste, Boolean credito, String metodoPago) {
       this.fechaVenta = fechaVenta;
       this.fechaVentaSaldada = fechaVentaSaldada;
       this.fechaUltimoAbono = fechaUltimoAbono;
       this.detalleVenta = detalleVenta;
       this.descripcion = descripcion;
       this.abono = abono;
       this.total = total;
       this.estatus = estatus;
       this.usuarioUltimoAjuste = usuarioUltimoAjuste;
       this.credito = credito;
       this.metodoPago = metodoPago;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idVenta", unique=true, nullable=false)
    public Integer getIdVenta() {
        return this.idVenta;
    }
    
    public void setIdVenta(Integer idVenta) {
        this.idVenta = idVenta;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="fechaVenta", nullable=false, length=19)
    public Date getFechaVenta() {
        return this.fechaVenta;
    }
    
    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="fechaVentaSaldada", length=19)
    public Date getFechaVentaSaldada() {
        return this.fechaVentaSaldada;
    }
    
    public void setFechaVentaSaldada(Date fechaVentaSaldada) {
        this.fechaVentaSaldada = fechaVentaSaldada;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="fechaUltimoAbono", length=19)
    public Date getFechaUltimoAbono() {
        return this.fechaUltimoAbono;
    }
    
    public void setFechaUltimoAbono(Date fechaUltimoAbono) {
        this.fechaUltimoAbono = fechaUltimoAbono;
    }

    
    @Column(name="detalleVenta")
    public Integer getDetalleVenta() {
        return this.detalleVenta;
    }
    
    public void setDetalleVenta(Integer detalleVenta) {
        this.detalleVenta = detalleVenta;
    }

    
    @Column(name="descripcion", nullable=false, length=200)
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    
    @Column(name="abono", precision=22, scale=0)
    public Double getAbono() {
        return this.abono;
    }
    
    public void setAbono(Double abono) {
        this.abono = abono;
    }

    
    @Column(name="total", nullable=false, precision=22, scale=0)
    public double getTotal() {
        return this.total;
    }
    
    public void setTotal(double total) {
        this.total = total;
    }

    
    @Column(name="estatus", nullable=false)
    public boolean isEstatus() {
        return this.estatus;
    }
    
    public void setEstatus(boolean estatus) {
        this.estatus = estatus;
    }

    
    @Column(name="usuarioUltimoAjuste", nullable=false)
    public int getUsuarioUltimoAjuste() {
        return this.usuarioUltimoAjuste;
    }
    
    public void setUsuarioUltimoAjuste(int usuarioUltimoAjuste) {
        this.usuarioUltimoAjuste = usuarioUltimoAjuste;
    }

    
    @Column(name="credito")
    public Boolean getCredito() {
        return this.credito;
    }
    
    public void setCredito(Boolean credito) {
        this.credito = credito;
    }

    
    @Column(name="metodoPago", length=45)
    public String getMetodoPago() {
        return this.metodoPago;
    }
    
    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }




}


