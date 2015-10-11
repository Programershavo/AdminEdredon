package reportMaker;

import herramienta.FechaHerramienta;
import java.util.Collection;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import pojosNDB.CompraNBD;

public class ReportMakerNDB {

    //Recibe el objeto tabla que se va a cargar
    static JTable modeloTabla;
    //Recibe el nombre de la tabla que se va a cargar
    static String nombreReporte = "";
    //Gestiona la conexion a la base de datos
    static String rutaReporte = "";
    //Gestiona la conexion a la base de datos

    public ReportMakerNDB(JTable modeloTabla, String nombreReporte) {
        //Recupero los objetos tabla y el nombre de la tabla
        this.modeloTabla = modeloTabla;
        this.nombreReporte = nombreReporte;
        //Abre el reporte
        llamarReporte();
    }

    //Dependiendo de el nombre de la tabla es el reporte que se va a cargar
    private String obtenRutaReporte() {
        switch (nombreReporte) {
            case "Compra":
                rutaReporte = System.getProperty("user.dir") + "/reporteCompraNoBD.jrxml";
//              nombreReporte = getClass().getResource("/reportes/Reporte/reporteCompraNoBD.jrxml").getPath();
                break;
        }
        return rutaReporte;
    }

    //Creo una coleccion de entidades con las entidades 
    //de mi modelo de tabla recibido en el constructor
    private static Collection coleccionEntidades() {
        int i = 0;
        switch (nombreReporte) {
            case "Compra":
                //Creo el vector que guardara mi colección de entidades de UN solo TIPO
                Vector<CompraNBD> compraColeccion = new Vector<CompraNBD>();
                //Recorro fila por fila mi modelo de datos(TableModel)
                while (i < modeloTabla.getModel().getRowCount()) {
                    //Pasa cada objeto de la tabla Clientes a la coleccionesIdentidades
                    compraColeccion.add(new CompraNBD(
                            Integer.parseInt(modeloTabla.getModel().getValueAt(i, 0).toString()),
                            FechaHerramienta.convertirStringEnDate(modeloTabla.getModel().getValueAt(i, 1).toString()),
                            modeloTabla.getModel().getValueAt(i, 2).toString(),
                            modeloTabla.getModel().getValueAt(i, 3).toString(),
                            modeloTabla.getModel().getValueAt(i, 4).toString(),
                            modeloTabla.getModel().getValueAt(i, 5).toString(),
                            modeloTabla.getModel().getValueAt(i, 6).toString(),
                            Double.parseDouble(modeloTabla.getModel().getValueAt(i, 7).toString())
                    )
                    );
                    i++;
                }
                return compraColeccion;
        }

        return null;
    }

    //Ejecuta y muestra en pantalla elreporte
    private void llamarReporte() {
        JasperReport masterReport = null;
        //Elige la ruta del reporte
        String ruta = obtenRutaReporte();
        //si la ruta del reporte es null marca error
        if (ruta.isEmpty() || ruta == null) {
            JOptionPane.showMessageDialog(null,
                    "Error al cargar reporte, motivos:/n"
                    + "-No existe el reporte/n"
                    + "-La direccion del reporte es incorrecta",
                    "Generar Reporte", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                masterReport = JasperCompileManager.compileReport(ruta);
                //Llena el reporte con los datos especificados
                JasperPrint jasperPrint
                        = JasperFillManager.fillReport(masterReport,//Hoja en blanco
                                new HashMap(),//Aqui van los parámetros en caso de existit
                                new JRBeanCollectionDataSource(coleccionEntidades())); //La coleccion es la base de datos
                JasperViewer.viewReport(jasperPrint, false);//Muestra el reporte en pantalla
            } catch (JRException e) {
                JOptionPane.showMessageDialog(null, e,
                        "Error al compilar el reporte", JOptionPane.ERROR_MESSAGE);
                System.out.println(e);
            }
        }
    }
}
