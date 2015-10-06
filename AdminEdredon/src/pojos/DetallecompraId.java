package pojos;
// Generated 6/10/2015 12:22:35 PM by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * DetallecompraId generated by hbm2java
 */
@Embeddable
public class DetallecompraId  implements java.io.Serializable {


     private int iddetalleCompra;
     private int comprasIdCompras;
     private int productoIdProducto;

    public DetallecompraId() {
    }

    public DetallecompraId(int iddetalleCompra, int comprasIdCompras, int productoIdProducto) {
       this.iddetalleCompra = iddetalleCompra;
       this.comprasIdCompras = comprasIdCompras;
       this.productoIdProducto = productoIdProducto;
    }
   


    @Column(name="iddetalleCompra", nullable=false)
    public int getIddetalleCompra() {
        return this.iddetalleCompra;
    }
    
    public void setIddetalleCompra(int iddetalleCompra) {
        this.iddetalleCompra = iddetalleCompra;
    }


    @Column(name="Compras_idCompras", nullable=false)
    public int getComprasIdCompras() {
        return this.comprasIdCompras;
    }
    
    public void setComprasIdCompras(int comprasIdCompras) {
        this.comprasIdCompras = comprasIdCompras;
    }


    @Column(name="Producto_idProducto", nullable=false)
    public int getProductoIdProducto() {
        return this.productoIdProducto;
    }
    
    public void setProductoIdProducto(int productoIdProducto) {
        this.productoIdProducto = productoIdProducto;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof DetallecompraId) ) return false;
		 DetallecompraId castOther = ( DetallecompraId ) other; 
         
		 return (this.getIddetalleCompra()==castOther.getIddetalleCompra())
 && (this.getComprasIdCompras()==castOther.getComprasIdCompras())
 && (this.getProductoIdProducto()==castOther.getProductoIdProducto());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getIddetalleCompra();
         result = 37 * result + this.getComprasIdCompras();
         result = 37 * result + this.getProductoIdProducto();
         return result;
   }   


}


