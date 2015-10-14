package pojos;
// Generated 13/10/2015 08:49:05 PM by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Producto generated by hbm2java
 */
@Entity
@Table(name="producto"
    ,catalog="adminedredon"
)
public class Producto  implements java.io.Serializable {


     private Integer idProducto;
     private String codigoDeBarras;
     private String nombre;
     private String size;
     private String linea;
     private String sublinea;
     private String unidadMedida;
     private Double precioVenta;
     private Double precioCompra;
     private Double iva;
     private Boolean controlStock;

    public Producto() {
    }

    public Producto(String codigoDeBarras, String nombre, String size, String linea, String sublinea, String unidadMedida, Double precioVenta, Double precioCompra, Double iva, Boolean controlStock) {
       this.codigoDeBarras = codigoDeBarras;
       this.nombre = nombre;
       this.size = size;
       this.linea = linea;
       this.sublinea = sublinea;
       this.unidadMedida = unidadMedida;
       this.precioVenta = precioVenta;
       this.precioCompra = precioCompra;
       this.iva = iva;
       this.controlStock = controlStock;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idProducto", unique=true, nullable=false)
    public Integer getIdProducto() {
        return this.idProducto;
    }
    
    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    
    @Column(name="codigoDeBarras", length=45)
    public String getCodigoDeBarras() {
        return this.codigoDeBarras;
    }
    
    public void setCodigoDeBarras(String codigoDeBarras) {
        this.codigoDeBarras = codigoDeBarras;
    }

    
    @Column(name="nombre", length=45)
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    @Column(name="size", length=45)
    public String getSize() {
        return this.size;
    }
    
    public void setSize(String size) {
        this.size = size;
    }

    
    @Column(name="linea", length=45)
    public String getLinea() {
        return this.linea;
    }
    
    public void setLinea(String linea) {
        this.linea = linea;
    }

    
    @Column(name="sublinea", length=45)
    public String getSublinea() {
        return this.sublinea;
    }
    
    public void setSublinea(String sublinea) {
        this.sublinea = sublinea;
    }

    
    @Column(name="unidadMedida", length=45)
    public String getUnidadMedida() {
        return this.unidadMedida;
    }
    
    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    
    @Column(name="precioVenta", precision=22, scale=0)
    public Double getPrecioVenta() {
        return this.precioVenta;
    }
    
    public void setPrecioVenta(Double precioVenta) {
        this.precioVenta = precioVenta;
    }

    
    @Column(name="precioCompra", precision=22, scale=0)
    public Double getPrecioCompra() {
        return this.precioCompra;
    }
    
    public void setPrecioCompra(Double precioCompra) {
        this.precioCompra = precioCompra;
    }

    
    @Column(name="iva", precision=22, scale=0)
    public Double getIva() {
        return this.iva;
    }
    
    public void setIva(Double iva) {
        this.iva = iva;
    }

    
    @Column(name="controlStock")
    public Boolean getControlStock() {
        return this.controlStock;
    }
    
    public void setControlStock(Boolean controlStock) {
        this.controlStock = controlStock;
    }




}


