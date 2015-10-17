package pojos;
// Generated 15/10/2015 08:46:07 PM by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Clientes generated by hbm2java
 */
@Entity
@Table(name="clientes"
    ,catalog="adminedredon"
)
public class Clientes  implements java.io.Serializable {


     private Integer idCliente;
     private Integer folio;
     private String nombre;
     private String rfc;
     private String curp;
     private String calle;
     private Integer noExterior;
     private Integer noInterior;
     private String colonia;
     private String cp;
     private String telFijo;
     private String telMovil;
     private String email;
     private String pais;
     private String estado;
     private String municipio;

    public Clientes() {
    }

    public Clientes(Integer folio, String nombre, String rfc, String curp, String calle, Integer noExterior, Integer noInterior, String colonia, String cp, String telFijo, String telMovil, String email, String pais, String estado, String municipio) {
       this.folio = folio;
       this.nombre = nombre;
       this.rfc = rfc;
       this.curp = curp;
       this.calle = calle;
       this.noExterior = noExterior;
       this.noInterior = noInterior;
       this.colonia = colonia;
       this.cp = cp;
       this.telFijo = telFijo;
       this.telMovil = telMovil;
       this.email = email;
       this.pais = pais;
       this.estado = estado;
       this.municipio = municipio;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idCliente", unique=true, nullable=false)
    public Integer getIdCliente() {
        return this.idCliente;
    }
    
    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    
    @Column(name="folio")
    public Integer getFolio() {
        return this.folio;
    }
    
    public void setFolio(Integer folio) {
        this.folio = folio;
    }

    
    @Column(name="nombre", length=45)
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    @Column(name="RFC", length=20)
    public String getRfc() {
        return this.rfc;
    }
    
    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    
    @Column(name="CURP", length=25)
    public String getCurp() {
        return this.curp;
    }
    
    public void setCurp(String curp) {
        this.curp = curp;
    }

    
    @Column(name="calle", length=45)
    public String getCalle() {
        return this.calle;
    }
    
    public void setCalle(String calle) {
        this.calle = calle;
    }

    
    @Column(name="noExterior")
    public Integer getNoExterior() {
        return this.noExterior;
    }
    
    public void setNoExterior(Integer noExterior) {
        this.noExterior = noExterior;
    }

    
    @Column(name="noInterior")
    public Integer getNoInterior() {
        return this.noInterior;
    }
    
    public void setNoInterior(Integer noInterior) {
        this.noInterior = noInterior;
    }

    
    @Column(name="colonia", length=50)
    public String getColonia() {
        return this.colonia;
    }
    
    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    
    @Column(name="cp", length=10)
    public String getCp() {
        return this.cp;
    }
    
    public void setCp(String cp) {
        this.cp = cp;
    }

    
    @Column(name="telFijo", length=45)
    public String getTelFijo() {
        return this.telFijo;
    }
    
    public void setTelFijo(String telFijo) {
        this.telFijo = telFijo;
    }

    
    @Column(name="telMovil", length=45)
    public String getTelMovil() {
        return this.telMovil;
    }
    
    public void setTelMovil(String telMovil) {
        this.telMovil = telMovil;
    }

    
    @Column(name="email", length=45)
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    
    @Column(name="pais", length=45)
    public String getPais() {
        return this.pais;
    }
    
    public void setPais(String pais) {
        this.pais = pais;
    }

    
    @Column(name="estado", length=45)
    public String getEstado() {
        return this.estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }

    
    @Column(name="municipio", length=45)
    public String getMunicipio() {
        return this.municipio;
    }
    
    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }




}


