package pojos;
// Generated 6/10/2015 12:22:35 PM by Hibernate Tools 4.3.1


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Usuarios generated by hbm2java
 */
@Entity
@Table(name="usuarios"
    ,catalog="adminedredon"
)
public class Usuarios  implements java.io.Serializable {


     private int idUsuarios;
     private String nombre;
     private String apellidoP;
     private String apellidoM;
     private Boolean usuario;
     private Double password;
     private String domicilio;
     private String telefono;
     private String email;
     private Date ingreso;
     private Date salida;
     private String rol;

    public Usuarios() {
    }

	
    public Usuarios(int idUsuarios, String nombre) {
        this.idUsuarios = idUsuarios;
        this.nombre = nombre;
    }
    public Usuarios(int idUsuarios, String nombre, String apellidoP, String apellidoM, Boolean usuario, Double password, String domicilio, String telefono, String email, Date ingreso, Date salida, String rol) {
       this.idUsuarios = idUsuarios;
       this.nombre = nombre;
       this.apellidoP = apellidoP;
       this.apellidoM = apellidoM;
       this.usuario = usuario;
       this.password = password;
       this.domicilio = domicilio;
       this.telefono = telefono;
       this.email = email;
       this.ingreso = ingreso;
       this.salida = salida;
       this.rol = rol;
    }
   
     @Id 

    
    @Column(name="idUsuarios", unique=true, nullable=false)
    public int getIdUsuarios() {
        return this.idUsuarios;
    }
    
    public void setIdUsuarios(int idUsuarios) {
        this.idUsuarios = idUsuarios;
    }

    
    @Column(name="nombre", nullable=false, length=45)
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    @Column(name="apellidoP", length=20)
    public String getApellidoP() {
        return this.apellidoP;
    }
    
    public void setApellidoP(String apellidoP) {
        this.apellidoP = apellidoP;
    }

    
    @Column(name="apellidoM", length=45)
    public String getApellidoM() {
        return this.apellidoM;
    }
    
    public void setApellidoM(String apellidoM) {
        this.apellidoM = apellidoM;
    }

    
    @Column(name="usuario")
    public Boolean getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(Boolean usuario) {
        this.usuario = usuario;
    }

    
    @Column(name="password", precision=22, scale=0)
    public Double getPassword() {
        return this.password;
    }
    
    public void setPassword(Double password) {
        this.password = password;
    }

    
    @Column(name="domicilio", length=45)
    public String getDomicilio() {
        return this.domicilio;
    }
    
    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    
    @Column(name="Telefono", length=45)
    public String getTelefono() {
        return this.telefono;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    
    @Column(name="email", length=45)
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="Ingreso", length=19)
    public Date getIngreso() {
        return this.ingreso;
    }
    
    public void setIngreso(Date ingreso) {
        this.ingreso = ingreso;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="Salida", length=19)
    public Date getSalida() {
        return this.salida;
    }
    
    public void setSalida(Date salida) {
        this.salida = salida;
    }

    
    @Column(name="rol", length=45)
    public String getRol() {
        return this.rol;
    }
    
    public void setRol(String rol) {
        this.rol = rol;
    }




}


