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
 * Gastoslocales generated by hbm2java
 */
@Entity
@Table(name="gastoslocales"
    ,catalog="adminedredon"
)
public class Gastoslocales  implements java.io.Serializable {


     private Integer idGastosLocales;
     private Integer idLocal;
     private String local;
     private Date fecha;
     private String concepto;
     private Double importe;
     private String comentario;

    public Gastoslocales() {
    }

    public Gastoslocales(Integer idLocal, String local, Date fecha, String concepto, Double importe, String comentario) {
       this.idLocal = idLocal;
       this.local = local;
       this.fecha = fecha;
       this.concepto = concepto;
       this.importe = importe;
       this.comentario = comentario;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idGastosLocales", unique=true, nullable=false)
    public Integer getIdGastosLocales() {
        return this.idGastosLocales;
    }
    
    public void setIdGastosLocales(Integer idGastosLocales) {
        this.idGastosLocales = idGastosLocales;
    }

    
    @Column(name="idLocal")
    public Integer getIdLocal() {
        return this.idLocal;
    }
    
    public void setIdLocal(Integer idLocal) {
        this.idLocal = idLocal;
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

    
    @Column(name="comentario", length=45)
    public String getComentario() {
        return this.comentario;
    }
    
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }




}


