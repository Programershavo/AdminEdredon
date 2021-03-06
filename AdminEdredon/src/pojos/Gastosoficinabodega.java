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
 * Gastosoficinabodega generated by hbm2java
 */
@Entity
@Table(name="gastosoficinabodega"
    ,catalog="adminedredon"
)
public class Gastosoficinabodega  implements java.io.Serializable {


     private Integer idGastosOficinaBodega;
     private Date fecha;
     private String concepto;
     private Double importe;
     private String comentarios;

    public Gastosoficinabodega() {
    }

    public Gastosoficinabodega(Date fecha, String concepto, Double importe, String comentarios) {
       this.fecha = fecha;
       this.concepto = concepto;
       this.importe = importe;
       this.comentarios = comentarios;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idGastosOficinaBodega", unique=true, nullable=false)
    public Integer getIdGastosOficinaBodega() {
        return this.idGastosOficinaBodega;
    }
    
    public void setIdGastosOficinaBodega(Integer idGastosOficinaBodega) {
        this.idGastosOficinaBodega = idGastosOficinaBodega;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="fecha", length=10)
    public Date getFecha() {
        return this.fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    
    @Column(name="concepto", length=45)
    public String getConcepto() {
        return this.concepto;
    }
    
    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    
    @Column(name="importe", precision=22, scale=0)
    public Double getImporte() {
        return this.importe;
    }
    
    public void setImporte(Double importe) {
        this.importe = importe;
    }

    
    @Column(name="comentarios", length=45)
    public String getComentarios() {
        return this.comentarios;
    }
    
    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }




}


