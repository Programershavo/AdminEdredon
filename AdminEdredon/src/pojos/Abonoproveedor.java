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
 * Abonoproveedor generated by hbm2java
 */
@Entity
@Table(name="abonoproveedor"
    ,catalog="adminedredon"
)
public class Abonoproveedor  implements java.io.Serializable {


     private Integer idAbonoProveedor;
     private Integer idContenidoExpediente;
     private Date fecha;
     private String folio;
     private Double cargo;
     private Double abono;
     private Double saldo;
     private String formaDePago;

    public Abonoproveedor() {
    }

    public Abonoproveedor(Integer idContenidoExpediente, Date fecha, String folio, Double cargo, Double abono, Double saldo, String formaDePago) {
       this.idContenidoExpediente = idContenidoExpediente;
       this.fecha = fecha;
       this.folio = folio;
       this.cargo = cargo;
       this.abono = abono;
       this.saldo = saldo;
       this.formaDePago = formaDePago;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idAbonoProveedor", unique=true, nullable=false)
    public Integer getIdAbonoProveedor() {
        return this.idAbonoProveedor;
    }
    
    public void setIdAbonoProveedor(Integer idAbonoProveedor) {
        this.idAbonoProveedor = idAbonoProveedor;
    }

    
    @Column(name="idContenidoExpediente")
    public Integer getIdContenidoExpediente() {
        return this.idContenidoExpediente;
    }
    
    public void setIdContenidoExpediente(Integer idContenidoExpediente) {
        this.idContenidoExpediente = idContenidoExpediente;
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

    
    @Column(name="cargo", precision=22, scale=0)
    public Double getCargo() {
        return this.cargo;
    }
    
    public void setCargo(Double cargo) {
        this.cargo = cargo;
    }

    
    @Column(name="abono", precision=22, scale=0)
    public Double getAbono() {
        return this.abono;
    }
    
    public void setAbono(Double abono) {
        this.abono = abono;
    }

    
    @Column(name="saldo", precision=22, scale=0)
    public Double getSaldo() {
        return this.saldo;
    }
    
    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    
    @Column(name="formaDePago", length=45)
    public String getFormaDePago() {
        return this.formaDePago;
    }
    
    public void setFormaDePago(String formaDePago) {
        this.formaDePago = formaDePago;
    }




}


