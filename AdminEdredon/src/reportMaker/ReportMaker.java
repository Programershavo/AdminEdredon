package reportMaker;

import controlBD.AccesoBD;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
//import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author lightemp
 */
public class ReportMaker {

    Connection conexionSQL = null;
    String consultaHQL = null;//Consulta para la base de datos
    String nombreReporte = null;//Nombre del reporte que se va a mostrar en pantalla
    List listTablaConsultada = null;//Lista que se llenará con los datos consultados en consultaHQL
    Map parametros;
    boolean imprimir;

    //Recibo la consulta de la tabla, y el nombre del reporte que llenare con la tabla consultada
    public ReportMaker(String consultaHQL, String nombreReporte, boolean imprimirInmediato) {
        Conexion();
        this.imprimir = imprimirInmediato;
        this.consultaHQL = consultaHQL;
        this.nombreReporte = nombreReporte;
        this.parametros = new HashMap();
        llamarReporte();
    }

    //Recibo la consulta de la tabla, y el nombre del reporte que llenare con la tabla consultada
    public ReportMaker(String consultaHQL, String nombreReporte, Map parametrosRecibidos, boolean imprimirInmediato) {
        Conexion();
        this.imprimir = imprimirInmediato;
        this.parametros = new HashMap();
        this.parametros = parametrosRecibidos;
        this.consultaHQL = consultaHQL;
        this.nombreReporte = nombreReporte;
        llamarReporte();
    }

    private void Conexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver"); //se carga el driver
            conexionSQL = DriverManager.getConnection("jdbc:mysql://localhost:3306/adminEdredon?zeroDateTimeBehavior=convertToNull", "root", "root");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void cerrar() {
        try {
            conexionSQL.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public String obtenRutaReporte(String nombreReporte) {
        switch (nombreReporte) {
            case "GastosLocales":
                nombreReporte = System.getProperty("user.dir") + "/gastosLocales.jrxml";
                break;
            case "GastosPersonales":
                nombreReporte = System.getProperty("user.dir") + "/gastosPersonales.jrxml";
                break;
            case "GastosOficinaBodega":
                nombreReporte = System.getProperty("user.dir") + "/GastosOficinaBodega.jrxml";
                break;
            case "GastoGasolina":
                nombreReporte = System.getProperty("user.dir") + "/GastosGasolina.jrxml";
                break;
            case "GastoMantenimiento":
                nombreReporte = System.getProperty("user.dir") + "/Gastomantenimiento.jrxml";
                break;
            case "GastoProveedores":
                nombreReporte = System.getProperty("user.dir") + "/GastoProveedores.jrxml";
                break;
            case "GastosFinancieros":
                nombreReporte = System.getProperty("user.dir") + "/Gastosfinancieros.jrxml";
                break;
        }
        return nombreReporte;
    }

    //Revisa si la tabla tiene registros
    private boolean hayRegistrosEnTabla(String HQL) {
        AccesoBD acceso = new AccesoBD();
        listTablaConsultada = acceso.select(HQL);
        return !listTablaConsultada.isEmpty();
    }

    private void llamarReporte() {
        if (hayRegistrosEnTabla(consultaHQL)) {
            try {
                String rutaReporte = obtenRutaReporte(nombreReporte);
                if (rutaReporte == null) {
                    JOptionPane.showMessageDialog(null, "Error al cargar reporte",
                            "Generar Reporte", JOptionPane.ERROR_MESSAGE);
                }
                JasperReport reporteMaestro = null;
                try {
                    reporteMaestro = JasperCompileManager.compileReport(rutaReporte);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e,
                            "Error al cargar reporte, o no esta o tiene un fallo", JOptionPane.ERROR_MESSAGE);
                    System.out.println(e);
                }
                if (parametros.isEmpty()) {
                    JasperPrint jasperPrint = JasperFillManager.fillReport(reporteMaestro, new HashMap(), conexionSQL);
                    jasperPrint.setProperty("net.sf.jasperreports.export.xls.ignore.graphics", "true");
                    JasperViewer.viewReport(jasperPrint, imprimir);
                } else {
                    JasperPrint jasperPrint = JasperFillManager.fillReport(reporteMaestro, parametros, conexionSQL);
                    jasperPrint.setProperty("net.sf.jasperreports.export.xls.ignore.graphics", "true");
                    JasperViewer.viewReport(jasperPrint, imprimir);
                }
                cerrar();
            } catch (JRException e) {
                JOptionPane.showMessageDialog(null, "Error al compilar reporte",
                        "Generar Reporte", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No existen registros para mostrar en reporte",
                    "Generar Reporte", JOptionPane.ERROR_MESSAGE);
        }
    }
}
