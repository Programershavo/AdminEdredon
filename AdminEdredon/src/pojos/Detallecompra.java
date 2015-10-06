package pojos;
// Generated 6/10/2015 12:22:35 PM by Hibernate Tools 4.3.1


import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Detallecompra generated by hbm2java
 */
@Entity
@Table(name="detallecompra"
    ,catalog="adminedredon"
)
public class Detallecompra  implements java.io.Serializable {


     private DetallecompraId id;
     private Integer tienda;
     private String descripcion;
     private String detalleCompracol;

    public Detallecompra() {
    }

	
    public Detallecompra(DetallecompraId id) {
        this.id = id;
    }
    public Detallecompra(DetallecompraId id, Integer tienda, String descripcion, String detalleCompracol) {
       this.id = id;
       this.tienda = tienda;
       this.descripcion = descripcion;
       this.detalleCompracol = detalleCompracol;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="iddetalleCompra", column=@Column(name="iddetalleCompra", nullable=false) ), 
        @AttributeOverride(name="comprasIdCompras", column=@Column(name="Compras_idCompras", nullable=false) ), 
        @AttributeOverride(name="productoIdProducto", column=@Column(name="Producto_idProducto", nullable=false) ) } )
    public DetallecompraId getId() {
        return this.id;
    }
    
    public void setId(DetallecompraId id) {
        this.id = id;
    }

    
    @Column(name="tienda")
    public Integer getTienda() {
        return this.tienda;
    }
    
    public void setTienda(Integer tienda) {
        this.tienda = tienda;
    }

    
    @Column(name="descripcion", length=100)
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    
    @Column(name="detalleCompracol", length=45)
    public String getDetalleCompracol() {
        return this.detalleCompracol;
    }
    
    public void setDetalleCompracol(String detalleCompracol) {
        this.detalleCompracol = detalleCompracol;
    }




}


