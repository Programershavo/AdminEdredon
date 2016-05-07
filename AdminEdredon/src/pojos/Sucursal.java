package pojos;
// Generated 1/05/2016 12:22:23 PM by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Sucursal generated by hbm2java
 */
@Entity
@Table(name="sucursal"
    ,catalog="adminedredon"
)
public class Sucursal  implements java.io.Serializable {


     private Integer idSucursal;
     private String nombre;
     private String calle;
     private String colonia;
     private String noExterior;
     private String noInterior;
     private String cp;
     private String municipio;
     private String estado;

    public Sucursal() {
    }

    public Sucursal(String nombre, String calle, String colonia, String noExterior, String noInterior, String cp, String municipio, String estado) {
       this.nombre = nombre;
       this.calle = calle;
       this.colonia = colonia;
       this.noExterior = noExterior;
       this.noInterior = noInterior;
       this.cp = cp;
       this.municipio = municipio;
       this.estado = estado;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idSucursal", unique=true, nullable=false)
    public Integer getIdSucursal() {
        return this.idSucursal;
    }
    
    public void setIdSucursal(Integer idSucursal) {
        this.idSucursal = idSucursal;
    }

    
    @Column(name="nombre", length=45)
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    @Column(name="calle", length=45)
    public String getCalle() {
        return this.calle;
    }
    
    public void setCalle(String calle) {
        this.calle = calle;
    }

    
    @Column(name="colonia", length=45)
    public String getColonia() {
        return this.colonia;
    }
    
    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    
    @Column(name="noExterior", length=45)
    public String getNoExterior() {
        return this.noExterior;
    }
    
    public void setNoExterior(String noExterior) {
        this.noExterior = noExterior;
    }

    
    @Column(name="noInterior", length=45)
    public String getNoInterior() {
        return this.noInterior;
    }
    
    public void setNoInterior(String noInterior) {
        this.noInterior = noInterior;
    }

    
    @Column(name="cp", length=45)
    public String getCp() {
        return this.cp;
    }
    
    public void setCp(String cp) {
        this.cp = cp;
    }

    
    @Column(name="municipio", length=45)
    public String getMunicipio() {
        return this.municipio;
    }
    
    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    
    @Column(name="estado", length=45)
    public String getEstado() {
        return this.estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }




}


