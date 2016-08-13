package controlBD;

import herramienta.FechaHerramienta;
import java.util.Iterator;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class FillTableModel extends AbstractTableModel {

    private final String[] headerDiarioCaja = {"Clave", "Local", "Fecha", "Notas", "Ventas con Nota", "Ventas Sin Nota", "Abono Credito", "Gastos"};
    private final String[] headerGastosGenerales = {"Clave", "Fecha", "Concepto", "Subconcepto", "Motivo", "Comentario", "Importe"};
    private final String[] headerExpedienteProveedor = {"CE", "CP", "Proveedor", "Fecha", "Estado"};
    private final String[] headerContenidoExpediente = {"Clave ConExp", /*"Clave Exp", "Clave Pro", */ "fecha", "folio", "Proveedor", "piezas", "importe"/*, "forma de pago", "abono", "estado", "saldo"*/};
    private final String[] headerConExpRes = {"Clave ConExp", "Fecha", "Folio", "Piezas", "Cargo", "Abono", "Saldo", "Estado"};
    private final String[] headerAbonoProveedores = {"Clave Abono", "Fecha", "Folio", "Cargo", "Abono", "Saldo", "Forma de pago"};
    private final String[] headerLinea = {"clave", "Nombre"};
    private final String[] headerSucursal = {"clave", "Nombre"};
    private final String[] headerProveedor = {"clave", "Nombre"};
    private final String[] headerCliente = {"clave", "Nombre"};
    private final String[] headerGastosLocales = {"Clave", "claveLocal", "local", "fecha", "concepto", "importe", "comentario"};
    private final String[] headerGastosBodega = {"Clave", "fecha", "concepto", "importe", "comentario"};
    private final String[] headerGastosPersonales = {"Clave", "fecha", "concepto", "comentario", "importe"};
    private final String[] headerVehiculo = {"Clave", "Vehiculo", "Dueño", "Color", "Año", "Placas"};
    private final String[] headerGastogasolina = {"Clave", "Fecha", "Vehiculo", "Comentario", "Importe"};
    private final String[] headerMantenimiento = {"Clave", "Mantenimiento"};
    private final String[] headerGastomantenimientov = {"Clave", "Fecha", "Vehiculo", "Mantenimiento", "Coementario", "Importe"};
    private final String[] headerGastosFinancieros = {"id", "Fecha", "Concepto", "Importe", "Coementario", "Generado por"};

    //Este arreglo guarda los encabezados y lo registros
    public Object[][] tableModel;
    //Recibe el nombre de la tabla que se va a cargar
    public String tablaACargar = null;
    //Estos son los encabezados que tendrá el modelo
    private String[] titulosDeColumnas = null;

    public FillTableModel(String nombreTabla, List registrosConsulta) {
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
            case "Mantenimiento":
                titulosDeColumnas = headerMantenimiento;
                break;
            case "GastosOficinaBodega":
                titulosDeColumnas = headerGastosBodega;
                break;
            case "GastosLocales":
                titulosDeColumnas = headerGastosLocales;
                break;
            case "GastosPersonales":
                titulosDeColumnas = headerGastosPersonales;
                break;
            case "Expediente":
                titulosDeColumnas = headerExpedienteProveedor;
                break;
            case "ContenidoExpediente":
                titulosDeColumnas = headerContenidoExpediente;
                break;
            case "ContenidoExpediente2":
                titulosDeColumnas = headerConExpRes;
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
            case "AbonoProveedores":
                titulosDeColumnas = headerAbonoProveedores;
                break;
            case "Vehiculo":
                titulosDeColumnas = headerVehiculo;
                break;
            case "Gastogasolina":
                titulosDeColumnas = headerGastogasolina;
                break;
            case "Gastomantenimientov":
                titulosDeColumnas = headerGastomantenimientov;
                break;
            case "Gastofinanciero":
                titulosDeColumnas = headerGastosFinancieros;
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
            //Carga los pagos de los clientes
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
            case "GastosOficinaBodega":
                try {
                    while (listaDeObjetos.hasNext()) {
                        while (contCol < numColumnas && listaDeObjetos.hasNext()) {
                            pojos.Gastosoficinabodega concepto = (pojos.Gastosoficinabodega) listaDeObjetos.next();
                            tableModel[contFila][contCol] = concepto.getIdGastosOficinaBodega();
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = FechaHerramienta.formatoYMD(concepto.getFecha());
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = concepto.getConcepto();
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = concepto.getImporte();
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = concepto.getComentarios();
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
            case "Gastofinanciero":
                try {
                    while (listaDeObjetos.hasNext()) {
                        while (contCol < numColumnas && listaDeObjetos.hasNext()) {
                            pojos.Gastosfinancieros concepto = (pojos.Gastosfinancieros) listaDeObjetos.next();
                            tableModel[contFila][contCol] = concepto.getIdGastosFinancieros();
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = FechaHerramienta.formatoYMD(concepto.getFecha());
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = concepto.getConcepto();
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = concepto.getImporte();
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = concepto.getComentarios();
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = concepto.getAcreedor();
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
            case "Gastomantenimientov":
                try {
                    while (listaDeObjetos.hasNext()) {
                        while (contCol < numColumnas && listaDeObjetos.hasNext()) {
                            pojos.Gastomantenimientov concepto = (pojos.Gastomantenimientov) listaDeObjetos.next();
                            tableModel[contFila][contCol] = concepto.getIdGastoMantenimientoV();
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = FechaHerramienta.formatoYMD(concepto.getFecha());
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = concepto.getVehiculo();
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = concepto.getMantenimiento();
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = concepto.getComentario();
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = concepto.getImporte();
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
            case "Gastogasolina":
                try {
                    while (listaDeObjetos.hasNext()) {
                        while (contCol < numColumnas && listaDeObjetos.hasNext()) {
                            pojos.Gastogasolina concepto = (pojos.Gastogasolina) listaDeObjetos.next();
                            tableModel[contFila][contCol] = concepto.getIdGastoGasolina();
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = FechaHerramienta.formatoYMD(concepto.getFecha());
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = concepto.getVehiculo();
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = concepto.getComentario();
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = concepto.getImporte();
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
            case "GastosLocales":
                try {
                    while (listaDeObjetos.hasNext()) {
                        while (contCol < numColumnas && listaDeObjetos.hasNext()) {
                            pojos.Gastoslocales concepto = (pojos.Gastoslocales) listaDeObjetos.next();
                            tableModel[contFila][contCol] = concepto.getIdGastosLocales();
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = concepto.getIdLocal();
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = concepto.getLocal();
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = FechaHerramienta.formatoYMD(concepto.getFecha());
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = concepto.getConcepto();
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = concepto.getImporte();
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
            case "GastosPersonales":
                try {
                    while (listaDeObjetos.hasNext()) {
                        while (contCol < numColumnas && listaDeObjetos.hasNext()) {
                            pojos.Gastospersonales concepto = (pojos.Gastospersonales) listaDeObjetos.next();
                            tableModel[contFila][contCol] = concepto.getIdGastosPersonales();
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = FechaHerramienta.formatoYMD(concepto.getFecha());
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = concepto.getConcepto();
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = concepto.getComentarios();
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = concepto.getImporte();
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
                            tableModel[contFila][contCol] = concepto.getTienda();
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
            case "Vehiculo":
                try {
                    while (listaDeObjetos.hasNext()) {
                        while (contCol < numColumnas && listaDeObjetos.hasNext()) {
                            pojos.Vehiculo vehiculo = (pojos.Vehiculo) listaDeObjetos.next();
                            tableModel[contFila][contCol] = vehiculo.getIdvehiculo();
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = vehiculo.getVehiculo();
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = vehiculo.getDuenio();
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = vehiculo.getColor();
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = vehiculo.getAnio();
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = vehiculo.getPlacas();
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
                            tableModel[contFila][contCol] = expediente.getIdExpediente();
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = expediente.getIdProveedor();
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = expediente.getNombreProveedor();
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = String.valueOf(expediente.getFechaApertura());
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
                            tableModel[contFila][contCol] = contenidoExpediente.getIdContenidoExpediente();
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = String.valueOf(contenidoExpediente.getFecha());
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = contenidoExpediente.getFolio();
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = contenidoExpediente.getProveedor();
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = String.valueOf(contenidoExpediente.getPiezas());
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = String.valueOf(contenidoExpediente.getImporte());
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
            case "ContenidoExpediente2":
                try {
                    while (listaDeObjetos.hasNext()) {
                        while (contCol < numColumnas && listaDeObjetos.hasNext()) {
                            pojos.Contenidoexpediente contenidoExpediente = (pojos.Contenidoexpediente) listaDeObjetos.next();
                            tableModel[contFila][contCol] = contenidoExpediente.getIdContenidoExpediente();
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = String.valueOf(contenidoExpediente.getFecha());
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = contenidoExpediente.getFolio();
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = contenidoExpediente.getPiezas();
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = String.valueOf(contenidoExpediente.getImporte());
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = String.valueOf(contenidoExpediente.getAbono());
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = contenidoExpediente.getSaldo();
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
            case "AbonoProveedores":
                try {
                    while (listaDeObjetos.hasNext()) {
                        while (contCol < numColumnas && listaDeObjetos.hasNext()) {
                            pojos.Abonoproveedor abonoProveedor = (pojos.Abonoproveedor) listaDeObjetos.next();
                            tableModel[contFila][contCol] = abonoProveedor.getIdContenidoExpediente();
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = String.valueOf(abonoProveedor.getFecha());
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = abonoProveedor.getFolio();
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = String.valueOf(abonoProveedor.getCargo());
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = String.valueOf(abonoProveedor.getAbono());
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = abonoProveedor.getSaldo();
                            this.isCellEditable(contFila, contCol);
                            contCol++;
                            tableModel[contFila][contCol] = abonoProveedor.getFormaDePago();
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
            case "Cliente":
                try {
                    while (listaDeObjetos.hasNext()) {
                        while (contCol < numColumnas && listaDeObjetos.hasNext()) {
                            pojos.Clientes cliente = (pojos.Clientes) listaDeObjetos.next();
                            tableModel[contFila][contCol] = cliente.getIdCliente();
                            contCol++;
                            tableModel[contFila][contCol] = cliente.getNombre();
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
                            contCol++;;
                            tableModel[contFila][contCol] = proveedor.getNombre();
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
            case "Mantenimiento":
                try {
                    while (listaDeObjetos.hasNext()) {
                        while (contCol < numColumnas && listaDeObjetos.hasNext()) {
                            pojos.Mantenimiento mantenimiento = (pojos.Mantenimiento) listaDeObjetos.next();
                            tableModel[contFila][contCol] = mantenimiento.getIdMantenimiento();
                            contCol++;
                            tableModel[contFila][contCol] = mantenimiento.getMantenimiento();
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
        }

    }
}
