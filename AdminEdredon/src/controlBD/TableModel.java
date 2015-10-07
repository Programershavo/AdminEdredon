package controlBD;

import java.util.Iterator;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TableModel extends AbstractTableModel {

    private String[] headerCompra = {"Clave", "Nombre Sucursal",
        "Fecha Compra", "Tipo de gasto", "Nombre Proveedor", "Metodo pago", "Observacion", "Importe"};
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
        // Aquí devolvemos true o false según queramos que una celda
        // identificada por fila,columna (row,column), sea o no editable
        if (column == 0 || column == 1) {
            return false;
        } else {
            return true;
        }
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
                            contCol++;
                            tableModel[contFila][contCol] = compra.getFechaCompra();
                            contCol++;
                            tableModel[contFila][contCol] = compra.getNombreSucursal();
                            contCol++;
                            tableModel[contFila][contCol] = compra.getTipoDeGasto();
                            contCol++;
                            tableModel[contFila][contCol] = compra.getNombreProveedor();
                            contCol++;
                            tableModel[contFila][contCol] = compra.getMetodoPago();
                            contCol++;
                            tableModel[contFila][contCol] = compra.getObservacion();
                            contCol++;
                            tableModel[contFila][contCol] = compra.getImporte();
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
