package controlBD;

import java.util.Iterator;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TableModel extends AbstractTableModel {

    private String[] headerCompra = {"Clave",
        "Fecha Compra", "Nombre Sucursal", "Concepto",
        "Tipo de gasto", "Nombre Proveedor",
        "Total Abonado","Metodo pago", "Observacion", "Estatus", "Importe"};
    private String[] headerVenta = {"Clave", "Nota", "Tienda", "Cliente", "Fecha de venta",
        "Total Abonado", "Forma de pago", "Estatus", "Importe"};
    private String[] headerProducto = {"Clave", "Codigo de Barras",
        "Nombre", "Tamaño", "Linea", "Sublinea", "Precio de venta", "Precio de Compra"};
    private String[] headerProveedor = {"Clave", "Folio", "Nombre",
        "RFC", "Calle", "No Exterior", "No Interior", "Colonia",
        "CP", "Telefono", "Movil", "Email", "País", "Estado", "Municipio"};
    private String[] headerCliente = {"Clave", "Folio", "Nombre",
        "RFC", "Calle", "No Exterior", "No Interior", "Colonia",
        "CP", "Telefono", "Movil", "Email", "País", "Estado", "Municipio"};
    private String[] headerSucursal = {"Clave", "Nombre",
        "Calle", "No Exterior", "No Interior", "Colonia",
        "CP", "Estado", "Municipio"};
    private String[] headerLinea = {"Clave", "Linea"};
    private String[] headerSublinea = {"Clave", "Sublinea"};
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
            case "Compra":
                titulosDeColumnas = headerCompra;
                break;
            case "Producto":
                titulosDeColumnas = headerProducto;
                break;
            case "Proveedor":
                titulosDeColumnas = headerProveedor;
                break;
            case "Cliente":
                titulosDeColumnas = headerCliente;
                break;
            case "Sucursal":
                titulosDeColumnas = headerSucursal;
                break;
            case "Linea":
                titulosDeColumnas = headerLinea;
                break;
            case "Sublinea":
                titulosDeColumnas = headerSublinea;
                break;
            case "Venta":
                titulosDeColumnas = headerVenta;
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
            case "Compra":
                try {
                    while (listaDeObjetos.hasNext()) {
                        while (contCol < numColumnas && listaDeObjetos.hasNext()) {
                            pojos.Compra compra = (pojos.Compra) listaDeObjetos.next();
                            tableModel[contFila][contCol] = compra.getIdCompras();
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = compra.getFechaCompra();
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = compra.getNombreSucursal();
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = compra.getNombreProducto();
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = compra.getTipoGasto();
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = compra.getNombreProveedor();
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = compra.getAbono();
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = compra.getMetodoPago();
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = compra.getObservacion();
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = compra.getEstatus();
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = compra.getImporte();
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
            case "Venta":
                try {
                    while (listaDeObjetos.hasNext()) {
                        while (contCol < numColumnas && listaDeObjetos.hasNext()) {
                            pojos.Venta venta = (pojos.Venta) listaDeObjetos.next();
                            tableModel[contFila][contCol] = venta.getIdVenta();
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = venta.getNota();
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = venta.getNombreTienda();
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = venta.getNombreCliente();
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = venta.getFechaVenta();
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = venta.getAbono();
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = venta.getMetodoPago();
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = venta.getEstatus();
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = String.valueOf(venta.getImporte());
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
            case "Producto":
                try {
                    while (listaDeObjetos.hasNext()) {
                        while (contCol < numColumnas && listaDeObjetos.hasNext()) {
                            pojos.Producto producto = (pojos.Producto) listaDeObjetos.next();
                            tableModel[contFila][contCol] = producto.getCodigoDeBarras();
                            contCol++;
                            tableModel[contFila][contCol] = producto.getNombre();
                            contCol++;
                            tableModel[contFila][contCol] = producto.getSize();
                            contCol++;
                            tableModel[contFila][contCol] = producto.getLinea();
                            contCol++;
                            tableModel[contFila][contCol] = producto.getSublinea();
                            contCol++;
                            tableModel[contFila][contCol] = producto.getPrecioVenta();
                            contCol++;
                            tableModel[contFila][contCol] = producto.getPrecioCompra();
                            contCol++;
                        }
                        contCol = 0;
                        contFila++;
                    }
                } catch (Exception e) {
                    throw e;
                }
                break;
            case "Cliente":
                try {
                    while (listaDeObjetos.hasNext()) {
                        while (contCol < numColumnas && listaDeObjetos.hasNext()) {
                            pojos.Clientes cliente = (pojos.Clientes) listaDeObjetos.next();
                            tableModel[contFila][contCol] = cliente.getIdCliente();
                            contCol++;
                            tableModel[contFila][contCol] = cliente.getFolio();
                            contCol++;
                            tableModel[contFila][contCol] = cliente.getNombre();
                            contCol++;
                            tableModel[contFila][contCol] = cliente.getRfc();
                            contCol++;
                            tableModel[contFila][contCol] = cliente.getCalle();
                            contCol++;
                            tableModel[contFila][contCol] = cliente.getNoExterior();
                            contCol++;
                            tableModel[contFila][contCol] = cliente.getNoInterior();
                            contCol++;
                            tableModel[contFila][contCol] = cliente.getColonia();
                            contCol++;
                            tableModel[contFila][contCol] = cliente.getCp();
                            contCol++;
                            tableModel[contFila][contCol] = cliente.getTelFijo();
                            contCol++;
                            tableModel[contFila][contCol] = cliente.getTelMovil();
                            contCol++;
                            tableModel[contFila][contCol] = cliente.getEmail();
                            contCol++;
                            tableModel[contFila][contCol] = cliente.getPais();
                            contCol++;
                            tableModel[contFila][contCol] = cliente.getEstado();
                            contCol++;
                            tableModel[contFila][contCol] = cliente.getMunicipio();
                            contCol++;

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
                            tableModel[contFila][contCol] = proveedor.getFolio();
                            contCol++;
                            tableModel[contFila][contCol] = proveedor.getNombre();
                            contCol++;
                            tableModel[contFila][contCol] = proveedor.getRfc();
                            contCol++;
                            tableModel[contFila][contCol] = proveedor.getCalle();
                            contCol++;
                            tableModel[contFila][contCol] = proveedor.getNoExterior();
                            contCol++;
                            tableModel[contFila][contCol] = proveedor.getNoInterior();
                            contCol++;
                            tableModel[contFila][contCol] = proveedor.getColonia();
                            contCol++;
                            tableModel[contFila][contCol] = proveedor.getCp();
                            contCol++;
                            tableModel[contFila][contCol] = proveedor.getTelFijo();
                            contCol++;
                            tableModel[contFila][contCol] = proveedor.getTelMovil();
                            contCol++;
                            tableModel[contFila][contCol] = proveedor.getTelMovil();
                            contCol++;
                            tableModel[contFila][contCol] = proveedor.getEmail();
                            contCol++;
                            tableModel[contFila][contCol] = proveedor.getPais();
                            contCol++;
                            tableModel[contFila][contCol] = proveedor.getEstado();
                            contCol++;
                            tableModel[contFila][contCol] = proveedor.getMunicipio();
                            contCol++;

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
            case "Sublinea":
                try {
                    while (listaDeObjetos.hasNext()) {
                        while (contCol < numColumnas && listaDeObjetos.hasNext()) {
                            pojos.Sublinea sublinea = (pojos.Sublinea) listaDeObjetos.next();
                            tableModel[contFila][contCol] = sublinea.getIdSublinea();
                            contCol++;
                            tableModel[contFila][contCol] = sublinea.getNombre();
                            contCol++;
                        }
                        contCol = 0;
                        contFila++;
                    }
                } catch (Exception e) {
                    throw e;
                }
                break;
        }

    }
}
