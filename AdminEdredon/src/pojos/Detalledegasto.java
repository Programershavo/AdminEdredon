package pojos;
// Generated 6/10/2015 12:22:35 PM by Hibernate Tools 4.3.1


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
 * Detalledegasto generated by hbm2java
 */
@Entity
@Table(name="detalledegasto"
    ,catalog="adminedredon"
)
public class Detalledegasto  implements java.io.Serializable {


     private Integer idDetalleDeGasto;
     private Date fecha;
     private Integer proveedor;
     private Integer compra;

    public Detalledegasto() {
    }

    public Detalledegasto(Date fecha, Integer proveedor, Integer compra) {
       this.fecha = fecha;
       this.proveedor = proveedor;
       this.compra = compra;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idDetalleDeGasto", unique=true, nullable=false)
    public Integer getIdDetalleDeGasto() {
        return this.idDetalleDeGasto;
    }
    
    public void setIdDetalleDeGasto(Integer idDetalleDeGasto) {
        this.idDetalleDeGasto = idDetalleDeGasto;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="fecha", length=19)
    public Date getFecha() {
        return this.fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    
    @Column(name="proveedor")
    public Integer getProveedor() {
        return this.proveedor;
    }
    
    public void setProveedor(Integer proveedor) {
        this.proveedor = proveedor;
    }

    
    @Column(name="compra")
    public Integer getCompra() {
        return this.compra;
    }
    
    public void setCompra(Integer compra) {
        this.compra = compra;
    }




}


