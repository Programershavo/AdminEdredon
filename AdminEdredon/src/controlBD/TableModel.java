package controlBD;

import herramienta.FechaHerramienta;
import java.util.Iterator;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TableModel extends AbstractTableModel {

    private String[] headerDiarioCaja = {"Clave", "Local", "Fecha", "Notas", "Ventas con Nota", "Ventas Sin Nota", "Abono Credito", "Gastos"};
    private String[] headerGastosGenerales = {"Clave", "Fecha", "Concepto", "Subconcepto", "Motivo", "Comentario", "Importe"};
    private String[] headerExpedienteProveedor = {"Fecha", "Folio", "Proveedor", "Importe"};
    private String[] headerContenidoExpediente = {"claveExpediente", "fecha", "producto", "piezas", "importe", "forma de pago", "estado"};
    private String[] headerLinea = {"clave", "Nombre"};
    private String[] headerSucursal = {"clave", "Nombre"};
    private String[] headerProveedor = {"clave", "Nombre"};
    private String[] headerCliente = {"clave", "Nombre"};

    //Este arreglo guarda los encabezados y lo registros
    public Object[][] tableModel;
    //Recibe el nombre de la tabla que se va a cargar
    public String tablaACargar = null;
    //Estos son los encabezados que tendrá el modelo
    private String[] titulosDeColumnas = null;

    public TableModel(String nombreTabla, List registrosConsulta) {
        this.tablaACargar = nombreTabla;
        getColumnNames();
        this.llenaModelo(registrosConsulta);
    }

    @Override
    public int getRowCount() {
        return tableModel.length;
    }

    @Override
    public int getColumnCount() {
        return titulosDeColumnas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return tableModel[rowIndex][columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        return titulosDeColumnas[column];
    }

    public Class getColumnClass(int fila, int col) {
        return tableModel[fila][col].getClass();

    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    @Override
    public void setValueAt(Object value, int Fila, int Col) {
        if (value == null) {
            return;
        }
        tableModel[Fila][Col] = value;
        this.fireTableCellUpdated(Fila, Col);
    }

    private String[] getColumnNames() {
        switch (tablaACargar) {
            case "Diariocaja":
                titulosDeColumnas = headerDiarioCaja;
                break;
            case "Gastosgenerales":
                titulosDeColumnas = headerGastosGenerales;
                break;
            case "Expediente":
                titulosDeColumnas = headerExpedienteProveedor;
                break;
            case "ContenidoExpediente":
                titulosDeColumnas = headerContenidoExpediente;
                break;
            case "Linea":
                titulosDeColumnas = headerLinea;
                break;
            case "Sucursal":
                titulosDeColumnas = headerSucursal;
                break;
            case "Proveedor":
                titulosDeColumnas = headerProveedor;
                break;
            case "Cliente":
                titulosDeColumnas = headerCliente;
                break;
        }
        return titulosDeColumnas;
    }

    private void llenaModelo(List registrosConsulta) {
        Iterator listaDeObjetos = registrosConsulta.iterator();
        int contFila = 0, contCol = 0, numColumnas = 0;
        try {
            numColumnas = getColumnCount();
        } catch (Exception e) {
            throw e;
        }
        tableModel = new Object[registrosConsulta.size()][numColumnas];
        //Elige la accion por el nombre de la pestaña activa
        switch (tablaACargar) {
            //Carga los pagos de los clietes
            case "Diariocaja":
                try {
                    while (listaDeObjetos.hasNext()) {
                        while (contCol < numColumnas && listaDeObjetos.hasNext()) {
                            pojos.Diariocaja venta = (pojos.Diariocaja) listaDeObjetos.next();
                            tableModel[contFila][contCol] = venta.getIdDiarioCaja();
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = venta.getLocal();
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = venta.getFecha();
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = venta.getNotas();
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = String.valueOf(venta.getVentaConNota());
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = String.valueOf(venta.getVentaSinNota());
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = String.valueOf(venta.getAbonoCredito());
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = String.valueOf(venta.getGastos());
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                        }
                        contCol = 0;
                        contFila++;
                    }
                } catch (Exception e) {
                    throw e;
                }
                break;
            case "Gastosgenerales":
                try {
                    while (listaDeObjetos.hasNext()) {
                        while (contCol < numColumnas && listaDeObjetos.hasNext()) {
                            pojos.Gastosgenerales concepto = (pojos.Gastosgenerales) listaDeObjetos.next();
                            tableModel[contFila][contCol] = concepto.getIdGastosGenerales();
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = FechaHerramienta.formatoYMD(concepto.getFecha());
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = concepto.getConcepto();
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = concepto.getSubconcepto();
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = concepto.getAcreedor();
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = String.valueOf(concepto.getImporte());
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = concepto.getComentario();
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                        }
                        contCol = 0;
                        contFila++;
                    }
                } catch (Exception e) {
                    throw e;
                }
                break;
            case "Expediente":
                try {
                    while (listaDeObjetos.hasNext()) {
                        while (contCol < numColumnas && listaDeObjetos.hasNext()) {
                            pojos.Expediente expediente = (pojos.Expediente) listaDeObjetos.next();
                            tableModel[contFila][contCol] = expediente.getNombreProveedor();
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = String.valueOf(expediente.getFechaApertura());
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = expediente.getFolio();
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = expediente.getEstadoGeneral();
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                        }
                        contCol = 0;
                        contFila++;
                    }
                } catch (Exception e) {
                    throw e;
                }
                break;
            case "ContenidoExpediente":
                try {
                    while (listaDeObjetos.hasNext()) {
                        while (contCol < numColumnas && listaDeObjetos.hasNext()) {
                            pojos.Contenidoexpediente contenidoExpediente = (pojos.Contenidoexpediente) listaDeObjetos.next();
                            tableModel[contFila][contCol] = contenidoExpediente.getIdExpediente();
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = String.valueOf(contenidoExpediente.getFecha());
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = contenidoExpediente.getFolio();
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = contenidoExpediente.getProducto();
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = String.valueOf(contenidoExpediente.getPiezas());
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = String.valueOf(contenidoExpediente.getImporte());
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = contenidoExpediente.getFormaDePago();
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = contenidoExpediente.getEstadoIndividualFolio();
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                        }
                        contCol = 0;
                        contFila++;
                    }
                } catch (Exception e) {
                    throw e;
                }
                break;
//            case "Producto":
//                try {
//                    while (listaDeObjetos.hasNext()) {
//                        while (contCol < numColumnas && listaDeObjetos.hasNext()) {
//                            pojos.Producto producto = (pojos.Producto) listaDeObjetos.next();
//                            tableModel[contFila][contCol] = producto.getCodigoDeBarras();
//                            contCol++;
//                            tableModel[contFila][contCol] = producto.getNombre();
//                            contCol++;
//                            tableModel[contFila][contCol] = producto.getSize();
//                            contCol++;
//                            tableModel[contFila][contCol] = producto.getLinea();
//                            contCol++;
//                            tableModel[contFila][contCol] = producto.getSublinea();
//                            contCol++;
//                            tableModel[contFila][contCol] = producto.getPrecioVenta();
//                            contCol++;
//                            tableModel[contFila][contCol] = producto.getPrecioCompra();
//                            contCol++;
//                        }
//                        contCol = 0;
//                        contFila++;
//                    }
//                } catch (Exception e) {
//                    throw e;
//                }
//                break;
            case "Cliente":
                try {
                    while (listaDeObjetos.hasNext()) {
                        while (contCol < numColumnas && listaDeObjetos.hasNext()) {
                            pojos.Clientes cliente = (pojos.Clientes) listaDeObjetos.next();
                            tableModel[contFila][contCol] = cliente.getIdCliente();
                            contCol++;
//                            tableModel[contFila][contCol] = cliente.getFolio();
//                            contCol++;
                            tableModel[contFila][contCol] = cliente.getNombre();
                            contCol++;
//                            tableModel[contFila][contCol] = cliente.getRfc();
//                            contCol++;
//                            tableModel[contFila][contCol] = cliente.getCalle();
//                            contCol++;
//                            tableModel[contFila][contCol] = cliente.getNoExterior();
//                            contCol++;
//                            tableModel[contFila][contCol] = cliente.getNoInterior();
//                            contCol++;
//                            tableModel[contFila][contCol] = cliente.getColonia();
//                            contCol++;
//                            tableModel[contFila][contCol] = cliente.getCp();
//                            contCol++;
//                            tableModel[contFila][contCol] = cliente.getTelFijo();
//                            contCol++;
//                            tableModel[contFila][contCol] = cliente.getTelMovil();
//                            contCol++;
//                            tableModel[contFila][contCol] = cliente.getEmail();
//                            contCol++;
//                            tableModel[contFila][contCol] = cliente.getPais();
//                            contCol++;
//                            tableModel[contFila][contCol] = cliente.getEstado();
//                            contCol++;
//                            tableModel[contFila][contCol] = cliente.getMunicipio();
//                            contCol++;

                        }
                        contCol = 0;
                        contFila++;
                    }
                } catch (Exception e) {
                    throw e;
                }
                break;
            case "Proveedor":
                try {
                    while (listaDeObjetos.hasNext()) {
                        while (contCol < numColumnas && listaDeObjetos.hasNext()) {
                            pojos.Proveedores proveedor = (pojos.Proveedores) listaDeObjetos.next();
                            tableModel[contFila][contCol] = proveedor.getIdProveedor();
                            contCol++;
//                            tableModel[contFila][contCol] = proveedor.getFolio();
//                            contCol++;
                            tableModel[contFila][contCol] = proveedor.getNombre();
                            contCol++;
//                            tableModel[contFila][contCol] = proveedor.getRfc();
//                            contCol++;
//                            tableModel[contFila][contCol] = proveedor.getCalle();
//                            contCol++;
//                            tableModel[contFila][contCol] = proveedor.getNoExterior();
//                            contCol++;
//                            tableModel[contFila][contCol] = proveedor.getNoInterior();
//                            contCol++;
//                            tableModel[contFila][contCol] = proveedor.getColonia();
//                            contCol++;
//                            tableModel[contFila][contCol] = proveedor.getCp();
//                            contCol++;
//                            tableModel[contFila][contCol] = proveedor.getTelFijo();
//                            contCol++;
//                            tableModel[contFila][contCol] = proveedor.getTelMovil();
//                            contCol++;
//                            tableModel[contFila][contCol] = proveedor.getTelMovil();
//                            contCol++;
//                            tableModel[contFila][contCol] = proveedor.getEmail();
//                            contCol++;
//                            tableModel[contFila][contCol] = proveedor.getPais();
//                            contCol++;
//                            tableModel[contFila][contCol] = proveedor.getEstado();
//                            contCol++;
//                            tableModel[contFila][contCol] = proveedor.getMunicipio();
//                            contCol++;

                        }
                        contCol = 0;
                        contFila++;
                    }
                } catch (Exception e) {
                    throw e;
                }
                break;
            case "Sucursal":
                try {
                    while (listaDeObjetos.hasNext()) {
                        while (contCol < numColumnas && listaDeObjetos.hasNext()) {
                            pojos.Sucursal sucursal = (pojos.Sucursal) listaDeObjetos.next();
                            tableModel[contFila][contCol] = sucursal.getIdSucursal();
                            contCol++;
                            tableModel[contFila][contCol] = sucursal.getNombre();
                            contCol++;
                        }
                        contCol = 0;
                        contFila++;
                    }
                } catch (Exception e) {
                    throw e;
                }
                break;
            case "Linea":
                try {
                    while (listaDeObjetos.hasNext()) {
                        while (contCol < numColumnas && listaDeObjetos.hasNext()) {
                            pojos.Linea linea = (pojos.Linea) listaDeObjetos.next();
                            tableModel[contFila][contCol] = linea.getIdLinea();
                            contCol++;
                            tableModel[contFila][contCol] = linea.getNombre();
                            contCol++;
                        }
                        contCol = 0;
                        contFila++;
                    }
                } catch (Exception e) {
                    throw e;
                }
                break;
//            case "Sublinea":
//                try {
//                    while (listaDeObjetos.hasNext()) {
//                        while (contCol < numColumnas && listaDeObjetos.hasNext()) {
//                            pojos.Sublinea sublinea = (pojos.Sublinea) listaDeObjetos.next();
//                            tableModel[contFila][contCol] = sublinea.getIdSublinea();
//                            contCol++;
//                            tableModel[contFila][contCol] = sublinea.getNombre();
//                            contCol++;
//                        }
//                        contCol = 0;
//                        contFila++;
//                    }
//                } catch (Exception e) {
//                    throw e;
//                }
//                break;
        }

    }
}
