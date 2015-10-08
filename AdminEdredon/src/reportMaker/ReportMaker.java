package reportMaker;

import controlBD.AccesoBD;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
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

    //Recibo la consulta de la tabla, y el nombre del reporte que llenare con la tabla consultada
    public ReportMaker(String consultaHQL, String nombreReporte) {
        Conexion();
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
            case "Compra":
                nombreReporte = System.getProperty("user.dir") + "/reporteCompra.jrxml";
//              nombreReporte = getClass().getResource("/reportes/Reporte/reporteCompra.jrxml").getPath();
                break;
        }
        return nombreReporte;
    }

    //Revisa si la tabla tiene registros
    private boolean hayRegistrosEnTabla(String HQL) {
        AccesoBD acceso = new AccesoBD();
        listTablaConsultada = acceso.select(HQL);
        if (listTablaConsultada.isEmpty()) {
            return false;
        }
        return true;
    }

    private void llamarReporte() {
        if (hayRegistrosEnTabla(consultaHQL)) {
            try {
                String rutaReporte = obtenRutaReporte(nombreReporte);
                if (rutaReporte == null) {
                    JOptionPane.showMessageDialog(null, "Error al cargar reporte",
                            "Generar Reporte", JOptionPane.ERROR_MESSAGE);
                }
                JasperReport masterReport = null;
                try {
                    masterReport = JasperCompileManager.compileReport(rutaReporte);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e,
                            "Error al cargar reporte", JOptionPane.ERROR_MESSAGE);
                    System.out.println(e);
                }
                JasperPrint jasperPrint = JasperFillManager.fillReport(masterReport, new HashMap(), conexionSQL);
                JasperViewer.viewReport(jasperPrint, false);
                //JasperPrintManager.printReport(jasperPrint,true);
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
