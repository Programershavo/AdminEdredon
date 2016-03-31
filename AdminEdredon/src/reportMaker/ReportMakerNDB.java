package reportMaker;

import herramienta.FechaHerramienta;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
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
import pojosNDB.ResumenGeneralNBD;
import pojosNDB.VentasOGastosNBD;

public class ReportMakerNDB {

    //Recibe el objeto tabla que se va a cargar
    static JTable modeloTabla;
    //Recibe el nombre de la tabla que se va a cargar
    static String nombreReporte = "";
    //Gestiona la conexion a la base de datos
    static String rutaReporte = "";
    private static boolean imprimir;
    private Map parametros;

    public ReportMakerNDB(JTable modeloTabla, String nombreReporte, boolean imprimirInmediato) {
        //Recibo la consulta de la tabla, y el nombre del reporte que llenare con la tabla consultada
        this.modeloTabla = modeloTabla;
        this.nombreReporte = nombreReporte;
        this.imprimir = imprimirInmediato;
        this.parametros = new HashMap();
        llamarReporte();
    }

    //Recibo la consulta de la tabla, y el nombre del reporte que llenare con la tabla consultada
    public ReportMakerNDB(JTable modeloTabla, String nombreReporte, Map parametrosRecibidos, boolean imprimirInmediato) {
        this.imprimir = imprimirInmediato;
        this.modeloTabla = modeloTabla;
        this.parametros = new HashMap();
        this.parametros = parametrosRecibidos;
        this.nombreReporte = nombreReporte;
        llamarReporte();
    }

    //Dependiendo de el nombre de la tabla es el reporte que se va a cargar
    private String obtenRutaReporte(String nombreReporte) {
        switch (nombreReporte) {
            case "compra":
                rutaReporte = System.getProperty("user.dir") + "/reporteCompraNoBD.jrxml";
                break;
            case "resumenGeneral":
                rutaReporte = System.getProperty("user.dir") + "/resumenGeneralNBD.jrxml";
                break;
            case "resumenVentasOGastos":
                rutaReporte = System.getProperty("user.dir") + "/resumenVentasOGastos.jrxml";
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
            case "resumenGeneral":
                //Creo el vector que guardara mi colección de entidades de UN solo TIPO
                Vector<ResumenGeneralNBD> resumenGeneralColeccion = new Vector<ResumenGeneralNBD>();
                //Recorro fila por fila mi modelo de datos(TableModel)
                while (i < modeloTabla.getModel().getRowCount()) {
                    //Pasa cada objeto de la tabla Clientes a la coleccionesIdentidades
                    resumenGeneralColeccion.add(new ResumenGeneralNBD(
                            FechaHerramienta.convertirStringEnDate(
                                    modeloTabla.getModel().getValueAt(i, 0).toString()
                            ),
                            modeloTabla.getModel().getValueAt(i, 1).toString(),
                            modeloTabla.getModel().getValueAt(i, 2).toString(),
                            Double.parseDouble(modeloTabla.getModel().getValueAt(i, 3).toString()),
                            Double.parseDouble(modeloTabla.getModel().getValueAt(i, 4).toString()),
                            Double.parseDouble(modeloTabla.getModel().getValueAt(i, 5).toString()),
                            Double.parseDouble(modeloTabla.getModel().getValueAt(i, 6).toString()),
                            Integer.parseInt(modeloTabla.getModel().getValueAt(i, 7).toString()),
                            modeloTabla.getModel().getValueAt(i, 8).toString(),
                            Double.parseDouble(modeloTabla.getModel().getValueAt(i, 9).toString()),
                            Double.parseDouble(modeloTabla.getModel().getValueAt(i, 10).toString()),
                            Double.parseDouble(modeloTabla.getModel().getValueAt(i, 11).toString())
                    )
                    );
                    i++;
                }
                return resumenGeneralColeccion;
            case "resumenVentasOGastos":
                //Creo el vector que guardara mi colección de entidades de UN solo TIPO
                Vector<VentasOGastosNBD> ventasOGastosColeccion = new Vector<VentasOGastosNBD>();
                //Recorro fila por fila mi modelo de datos(TableModel)
                while (i < modeloTabla.getModel().getRowCount()) {
                    //Pasa cada objeto de la tabla Clientes a la coleccionesIdentidades
                    ventasOGastosColeccion.add(new VentasOGastosNBD(
                            modeloTabla.getModel().getValueAt(i, 0).toString(),
                            modeloTabla.getModel().getValueAt(i, 1).toString(),
                            modeloTabla.getModel().getValueAt(i, 2).toString(),
                            modeloTabla.getModel().getValueAt(i, 3).toString(),
                            modeloTabla.getModel().getValueAt(i, 4).toString(),
                            modeloTabla.getModel().getValueAt(i, 5).toString(),
                            modeloTabla.getModel().getValueAt(i, 6).toString(),
                            modeloTabla.getModel().getValueAt(i, 7).toString(),
                            modeloTabla.getModel().getValueAt(i, 8).toString(),
                            modeloTabla.getModel().getValueAt(i, 9).toString(),
                            modeloTabla.getModel().getValueAt(i, 10).toString(),
                            modeloTabla.getModel().getValueAt(i, 11).toString(),
                            modeloTabla.getModel().getValueAt(i, 12).toString(),
                            modeloTabla.getModel().getValueAt(i, 13).toString()
                    )
                    );
                    i++;
                }
                return ventasOGastosColeccion;

        }

        return null;
    }

    //Ejecuta y muestra en pantalla elreporte
    private void llamarReporte() {
        try {
            //Elige la ruta del reporte
            String rutaReporte = obtenRutaReporte(nombreReporte);
            //si la ruta del reporte es null marca error
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
                JasperPrint jasperPrint = JasperFillManager.fillReport(
                        reporteMaestro,//Hoja en blanco
                        new HashMap(),//Aqui van los parámetros en caso de existir
                        new JRBeanCollectionDataSource(coleccionEntidades())
                ); //La coleccion es la base de datos
                jasperPrint.setProperty("net.sf.jasperreports.export.xls.ignore.graphics", "true");
                JasperViewer.viewReport(jasperPrint, imprimir);
            } else {
                JasperPrint jasperPrint = JasperFillManager.fillReport(
                        reporteMaestro,
                        parametros,
                        new JRBeanCollectionDataSource(coleccionEntidades())
                );
                jasperPrint.setProperty("net.sf.jasperreports.export.xls.ignore.graphics", "true");
                JasperViewer.viewReport(jasperPrint, imprimir);
            }
        } catch (JRException e) {
            JOptionPane.showMessageDialog(null, "Error al compilar reporte" + e.getMessage(),
                    "Generar Reporte", JOptionPane.ERROR_MESSAGE);
        }
    }

}
