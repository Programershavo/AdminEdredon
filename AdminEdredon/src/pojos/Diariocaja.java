package pojos;
// Generated 14/04/2016 03:19:14 PM by Hibernate Tools 4.3.1


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
 * Diariocaja generated by hbm2java
 */
@Entity
@Table(name="diariocaja"
    ,catalog="adminedredon"
)
public class Diariocaja  implements java.io.Serializable {


     private Integer idDiarioCaja;
     private String local;
     private Date fecha;
     private String notas;
     private Double ventaConNota;
     private Double ventaSinNota;
     private Double ventaConTarjeta;
     private Double abonoCredito;
     private Double gastos;
     private String comentario;

    public Diariocaja() {
    }

    public Diariocaja(String local, Date fecha, String notas, Double ventaConNota, Double ventaSinNota, Double ventaConTarjeta, Double abonoCredito, Double gastos, String comentario) {
       this.local = local;
       this.fecha = fecha;
       this.notas = notas;
       this.ventaConNota = ventaConNota;
       this.ventaSinNota = ventaSinNota;
       this.ventaConTarjeta = ventaConTarjeta;
       this.abonoCredito = abonoCredito;
       this.gastos = gastos;
       this.comentario = comentario;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idDiarioCaja", unique=true, nullable=false)
    public Integer getIdDiarioCaja() {
        return this.idDiarioCaja;
    }
    
    public void setIdDiarioCaja(Integer idDiarioCaja) {
        this.idDiarioCaja = idDiarioCaja;
    }

    
    @Column(name="local", length=45)
    public String getLocal() {
        return this.local;
    }
    
    public void setLocal(String local) {
        this.local = local;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="fecha", length=10)
    public Date getFecha() {
        return this.fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    
    @Column(name="notas", length=45)
    public String getNotas() {
        return this.notas;
    }
    
    public void setNotas(String notas) {
        this.notas = notas;
    }

    
    @Column(name="ventaConNota", precision=22, scale=0)
    public Double getVentaConNota() {
        return this.ventaConNota;
    }
    
    public void setVentaConNota(Double ventaConNota) {
        this.ventaConNota = ventaConNota;
    }

    
    @Column(name="ventaSinNota", precision=22, scale=0)
    public Double getVentaSinNota() {
        return this.ventaSinNota;
    }
    
    public void setVentaSinNota(Double ventaSinNota) {
        this.ventaSinNota = ventaSinNota;
    }

    
    @Column(name="ventaConTarjeta", precision=22, scale=0)
    public Double getVentaConTarjeta() {
        return this.ventaConTarjeta;
    }
    
    public void setVentaConTarjeta(Double ventaConTarjeta) {
        this.ventaConTarjeta = ventaConTarjeta;
    }

    
    @Column(name="abonoCredito", precision=22, scale=0)
    public Double getAbonoCredito() {
        return this.abonoCredito;
    }
    
    public void setAbonoCredito(Double abonoCredito) {
        this.abonoCredito = abonoCredito;
    }

    
    @Column(name="gastos", precision=22, scale=0)
    public Double getGastos() {
        return this.gastos;
    }
    
    public void setGastos(Double gastos) {
        this.gastos = gastos;
    }

    
    @Column(name="comentario", length=150)
    public String getComentario() {
        return this.comentario;
    }
    
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }




}

