package pojos;
// Generated 1/05/2016 12:22:23 PM by Hibernate Tools 4.3.1


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
 * Contenidoexpediente generated by hbm2java
 */
@Entity
@Table(name="contenidoexpediente"
    ,catalog="adminedredon"
)
public class Contenidoexpediente  implements java.io.Serializable {


     private Integer idContenidoExpediente;
     private Integer idExpediente;
     private Integer idProveedor;
     private String proveedor;
     private Date fecha;
     private String folio;
     private Integer piezas;
     private Double importe;
     private String formaDePago;
     private Double abono;
     private String estadoIndividualFolio;
     private Double saldo;

    public Contenidoexpediente() {
    }

    public Contenidoexpediente(Integer idExpediente, Integer idProveedor, String proveedor, Date fecha, String folio, Integer piezas, Double importe, String formaDePago, Double abono, String estadoIndividualFolio, Double saldo) {
       this.idExpediente = idExpediente;
       this.idProveedor = idProveedor;
       this.proveedor = proveedor;
       this.fecha = fecha;
       this.folio = folio;
       this.piezas = piezas;
       this.importe = importe;
       this.formaDePago = formaDePago;
       this.abono = abono;
       this.estadoIndividualFolio = estadoIndividualFolio;
       this.saldo = saldo;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idContenidoExpediente", unique=true, nullable=false)
    public Integer getIdContenidoExpediente() {
        return this.idContenidoExpediente;
    }
    
    public void setIdContenidoExpediente(Integer idContenidoExpediente) {
        this.idContenidoExpediente = idContenidoExpediente;
    }

    
    @Column(name="idExpediente")
    public Integer getIdExpediente() {
        return this.idExpediente;
    }
    
    public void setIdExpediente(Integer idExpediente) {
        this.idExpediente = idExpediente;
    }

    
    @Column(name="idProveedor")
    public Integer getIdProveedor() {
        return this.idProveedor;
    }
    
    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    
    @Column(name="proveedor", length=45)
    public String getProveedor() {
        return this.proveedor;
    }
    
    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="fecha", length=10)
    public Date getFecha() {
        return this.fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    
    @Column(name="folio", length=45)
    public String getFolio() {
        return this.folio;
    }
    
    public void setFolio(String folio) {
        this.folio = folio;
    }

    
    @Column(name="piezas")
    public Integer getPiezas() {
        return this.piezas;
    }
    
    public void setPiezas(Integer piezas) {
        this.piezas = piezas;
    }

    
    @Column(name="importe", precision=22, scale=0)
    public Double getImporte() {
        return this.importe;
    }
    
    public void setImporte(Double importe) {
        this.importe = importe;
    }

    
    @Column(name="formaDePago", length=45)
    public String getFormaDePago() {
        return this.formaDePago;
    }
    
    public void setFormaDePago(String formaDePago) {
        this.formaDePago = formaDePago;
    }

    
    @Column(name="abono", precision=22, scale=0)
    public Double getAbono() {
        return this.abono;
    }
    
    public void setAbono(Double abono) {
        this.abono = abono;
    }

    
    @Column(name="estadoIndividualFolio", length=45)
    public String getEstadoIndividualFolio() {
        return this.estadoIndividualFolio;
    }
    
    public void setEstadoIndividualFolio(String estadoIndividualFolio) {
        this.estadoIndividualFolio = estadoIndividualFolio;
    }

    
    @Column(name="saldo", precision=22, scale=0)
    public Double getSaldo() {
        return this.saldo;
    }
    
    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }




}


