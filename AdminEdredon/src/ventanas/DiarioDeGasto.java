/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import controlBD.AccesoBD;
import herramienta.FechaHerramienta;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import pojos.Abonoproveedor;
import pojos.Clientes;
import pojos.Contenidoexpediente;
import pojos.Gastogasolina;
import pojos.Gastosfinancieros;
import pojos.Gastosgenerales;
import pojos.Gastoslocales;
import pojos.Gastospersonales;
import pojos.Linea;
import pojos.Mantenimiento;
import pojos.Proveedores;
import pojos.Sucursal;
import pojos.Vehiculo;

/**
 *
 * @author Daniel
 */
public class DiarioDeGasto extends javax.swing.JInternalFrame {

    private String fechaInicio = "";
    private String fechaFin = "";
    private String conceptoResumen = "";
    private String HQL = "";
    private int idExpediente = 0;

    /**
     * Creates new form RegistroGasto
     */
    public DiarioDeGasto() {
        initComponents();
        limpiarCampos();
        addActions();
        setEventoMouseClicked(jtExpedienteAsignar, "Asignar");
        //Inicializa calendarios
        inicializaCalendarios();
    }

    private void inicializaCalendarios() {
        jdcFinFinancieroResumen.setDate(new Date());
        jdcFechaGasolina.setDate(new Date());
        jdcFechaGastosPersonales.setDate(new Date());
        jdcInicioFinancieroResumen.setDate(new Date());
        jdcFechaLocal.setDate(new Date());
        jdcFechaMantenimientoV.setDate(new Date());
        jdcFechaOficinaBodega.setDate(new Date());
        jdcFechaSueldosPrestamos.setDate(new Date());
        jdcFechaInicio.setDate(new Date());
        jdcFechaFin.setDate(new Date());
        jdcInicioHistorial.setDate(new Date());
        jdcFinHistorial.setDate(new Date());
        jdcLocalInicio.setDate(new Date());
        jdcLocalFin.setDate(new Date());
        jdcGtoPersonalInicio.setDate(new Date());
        jdcGtoPersonalFin.setDate(new Date());
        jdcGasolinaInicio.setDate(new Date());
        jdcGasolinaFin.setDate(new Date());
        jdcMantenimientoInicio.setDate(new Date());
        jdcMantenimientoFin.setDate(new Date());
        jdcBodegaInicio.setDate(new Date());
        jdcBodegaFin.setDate(new Date());
    }

    //EVENTO DOBLE CLIC PARA REFRESCAR LAS TABLAS
    private void setEventoMouseClicked(JTable tabla, String esDiariocajaOVenta) {
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    if (esDiariocajaOVenta.equals("Gastosgenerales")) {
                        crearConsultaGlobal();
                        cargaTabla(tabla, HQL, "Gastosgenerales", 1);
                    }
                }
                if (e.getClickCount() == 1) {
                    if (esDiariocajaOVenta.equals("Asignar")) {
                        //BUSCAR LOS CONTENIDOS DE EXPEDIENTE
                        idExpediente = (int) jtExpedienteAsignar.getValueAt(jtExpedienteAsignar.getSelectedRow(), 0);
                        String Query = "FROM Contenidoexpediente ce WHERE ce.idExpediente="
                                + idExpediente
                                + " ORDER BY ce.folio";
                        cargaTabla(jtFoliosAsignados, Query, "ContenidoExpediente2", 1);

                    }

                }

            }
        });
    }

    private void llenarTabla(JTable jtTabla, String nombreTabla, String consulta) {
        AccesoBD acceso = new AccesoBD();
        jtTabla.setVisible(false);
        jtTabla.removeAll();
        //_________________________ NOMBRE DE LA TABLA, CONSULTA
        jtTabla.setModel(acceso.retornaModelo(nombreTabla, consulta));
        jtTabla.setVisible(true);
    }

    private void llenarCombo(JComboBox combo, String query, String tipoLista, boolean todas) {
        AccesoBD acceso = new AccesoBD();
        combo.removeAllItems();
        if (acceso.select(query) != null) {

            List listaItems = acceso.select(query);
            //combo = new JComboBox();
            if (listaItems.size() > 0) {
                try {
                    //Crea la lista de categorias
                    if (!todas) {
                        combo.addItem("Todos");
                    }
                    if (todas) {
                        combo.addItem("Todas");
                    }
                    for (int i = 0; i < listaItems.size(); i++) {
                        switch (tipoLista) {
                            case "concepto":
                                Linea concepto = (Linea) listaItems.get(i);
                                combo.addItem(concepto.getNombre());
                                break;
                            case "local":
                                Sucursal local = (Sucursal) listaItems.get(i);
                                combo.addItem(local.getNombre());
                                break;
                            case "proveedor":
                                Proveedores proveedor = (Proveedores) listaItems.get(i);
                                combo.addItem(proveedor.getNombre());
                                break;
                            case "cliente":
                                Clientes cliente = (Clientes) listaItems.get(i);
                                combo.addItem(cliente.getNombre());
                                break;
                            case "vehiculo":
                                Vehiculo vehiculo = (Vehiculo) listaItems.get(i);
                                combo.addItem(vehiculo.getVehiculo());
                                break;
                            case "mantenimiento":
                                Mantenimiento mantenimiento = (Mantenimiento) listaItems.get(i);
                                combo.addItem(mantenimiento.getMantenimiento());
                                break;
                        }

                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "El error es:" + "\n" + e, "Error", 0);
                }
            }
        }
    }

    public final void addActions() {

        final ItemListener changeClick = new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (cmbConceptoFinanciero.getItemCount() > 0) {
                    if (cmbConceptoFinanciero.getSelectedItem().equals(e.getItem())) {
                        crearConsultaGlobal();
//                        cargaTabla(jtTablaGastosTotales, HQL, "Gastosgenerales", 1);
                    }
                }

            }
        };
        final ItemListener changeClick3 = new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (cmbProveedorHistorial.getItemCount() > 0) {
                    if (cmbProveedorHistorial.getSelectedItem().equals(e.getItem())) {
                        lblNotaHistorial.setText("");
                        String Query = "FROM Contenidoexpediente ce WHERE ce.idExpediente= -1"
                                + " ORDER BY ce.folio";
                        cargaTabla(jtFoliosHistorial, Query, "ContenidoExpediente", 3);
                        Query = "FROM Abonoproveedor ap WHERE ap.idContenidoExpediente= -1 ORDER BY ap.fecha";
                        cargaTabla(jtAbonosHistorial, Query, "AbonoProveedores", 1);
                        String fechaInicio = FechaHerramienta.formatoYMD(jdcInicioHistorial.getDate());
                        String fechaFin = FechaHerramienta.formatoYMD(jdcFinHistorial.getDate());
                        if (!cmbProveedorHistorial.getSelectedItem().toString().equals("Todos")) {
                            Query = "FROM Expediente e WHERE e.nombreProveedor='"
                                    + cmbProveedorHistorial.getSelectedItem().toString()
                                    + "' AND e.fechaApertura BETWEEN '"
                                    + fechaInicio
                                    + "' AND '"
                                    + fechaFin + "'"
                                    + " ORDER BY e.nombreProveedor";
                            cargaTabla(jtExpedienteHistorial, Query, "Expediente", 2);
                        } else {
                            Query = "FROM Expediente e "
                                    + " WHERE e.fechaApertura BETWEEN '"
                                    + fechaInicio
                                    + "' AND '"
                                    + fechaFin + "'"
                                    + " ORDER BY e.nombreProveedor";
                            cargaTabla(jtExpedienteHistorial, Query, "Expediente", 2);
                        }
                    }//
                }

            }
        };
        this.cmbProveedorHistorial.addItemListener(changeClick3);
        final ItemListener changeClick2 = new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (cmbProveedorBuscar.getItemCount() > 0) {
                    if (cmbProveedorBuscar.getSelectedItem().equals(e.getItem())) {
                        String Query = "FROM Contenidoexpediente ce WHERE ce.idExpediente= -1"
                                + " ORDER BY ce.folio";
                        cargaTabla(jtFoliosAsignados, Query, "ContenidoExpediente2", 3);
                        String fechaInicio = FechaHerramienta.formatoYMD(jdcFechaInicio.getDate());
                        String fechaFin = FechaHerramienta.formatoYMD(jdcFechaFin.getDate());
                        if (!cmbProveedorBuscar.getSelectedItem().toString().equals("Todos")) {
                            Query = "FROM Expediente e WHERE e.nombreProveedor='"
                                    + cmbProveedorBuscar.getSelectedItem().toString()
                                    + "' AND e.fechaApertura BETWEEN '"
                                    + fechaInicio
                                    + "' AND '"
                                    + fechaFin + "'"
                                    + " ORDER BY e.nombreProveedor";
                            cargaTabla(jtExpedienteAsignar, Query, "Expediente", 2);
                        } else {
                            Query = "FROM Expediente e "
                                    + " WHERE e.fechaApertura BETWEEN '"
                                    + fechaInicio
                                    + "' AND '"
                                    + fechaFin + "'"
                                    + " ORDER BY e.nombreProveedor";
                            cargaTabla(jtExpedienteAsignar, Query, "Expediente", 2);
                        }
                    }//
                }

            }
        };

        this.cmbProveedorBuscar.addItemListener(changeClick2);

        ////////////////////////////////////////////////////////
        if (jdcGtoPersonalInicio.isVisible()) {
            jdcGtoPersonalInicio.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {

                public void propertyChange(PropertyChangeEvent e) {
                    String fechaInicio = FechaHerramienta.formatoYMD(jdcGtoPersonalInicio.getDate());
                    String fechaFin = FechaHerramienta.formatoYMD(jdcGtoPersonalFin.getDate());

                    if (!cmbGtoPerCon.getSelectedItem().toString().equals("Todos")) {
                        String Query = "FROM Gastospersonales gl WHERE gl.concepto= '"
                                + cmbGtoPerCon.getSelectedItem().toString() + "' AND gl.fecha BETWEEN '"
                                + fechaInicio
                                + "' AND '"
                                + fechaFin + "'"
                                + " ORDER BY gl.fecha";
                        cargaTabla(jtPersonalesR, Query, "GastosPersonales", 1);
                    } else {
                        String Query = "FROM Gastospersonales gl WHERE  gl.fecha BETWEEN '"
                                + fechaInicio
                                + "' AND '"
                                + fechaFin + "'"
                                + " ORDER BY gl.fecha";
                        cargaTabla(jtPersonalesR, Query, "GastosPersonales", 1);
                    }
                }
            });
        }
        if (jdcGtoPersonalFin.isVisible()) {
            jdcGtoPersonalFin.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {

                public void propertyChange(PropertyChangeEvent e) {
                    String fechaInicio = FechaHerramienta.formatoYMD(jdcGtoPersonalInicio.getDate());
                    String fechaFin = FechaHerramienta.formatoYMD(jdcGtoPersonalFin.getDate());

                    if (!cmbGtoPerCon.getSelectedItem().toString().equals("Todos")) {
                        String Query = "FROM Gastospersonales gl WHERE gl.concepto= '"
                                + cmbGtoPerCon.getSelectedItem().toString() + "' AND gl.fecha BETWEEN '"
                                + fechaInicio
                                + "' AND '"
                                + fechaFin + "'"
                                + " ORDER BY gl.fecha";
                        cargaTabla(jtPersonalesR, Query, "GastosPersonales", 1);
                    } else {
                        String Query = "FROM Gastospersonales gl WHERE  gl.fecha BETWEEN '"
                                + fechaInicio
                                + "' AND '"
                                + fechaFin + "'"
                                + " ORDER BY gl.fecha";
                        cargaTabla(jtPersonalesR, Query, "GastosPersonales", 1);
                    }
                }
            });
        }
        ////////////////////////////////////////////////////////
        if (jdcGasolinaInicio.isVisible()) {
            jdcGasolinaInicio.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {

                public void propertyChange(PropertyChangeEvent e) {
                    String fechaInicio = FechaHerramienta.formatoYMD(jdcGasolinaInicio.getDate());
                    String fechaFin = FechaHerramienta.formatoYMD(jdcGasolinaFin.getDate());

                    if (!cmbGasolinaResumen.getSelectedItem().toString().equals("Todos")) {
                        String Query = "FROM Gastogasolina gl WHERE gl.vehiculo= '"
                                + cmbGasolinaResumen.getSelectedItem().toString() + "' AND gl.fecha BETWEEN '"
                                + fechaInicio
                                + "' AND '"
                                + fechaFin + "'"
                                + " ORDER BY gl.fecha";
                        cargaTabla(jtGasolinaResumen, Query, "Gastogasolina", 1);
                    } else {
                        String Query = "FROM Gastogasolina gl WHERE  gl.fecha BETWEEN '"
                                + fechaInicio
                                + "' AND '"
                                + fechaFin + "'"
                                + " ORDER BY gl.fecha";
                        cargaTabla(jtGasolinaResumen, Query, "Gastogasolina", 1);
                    }
                }
            });
        }
        if (jdcGasolinaFin.isVisible()) {
            jdcGasolinaFin.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {

                public void propertyChange(PropertyChangeEvent e) {
                    String fechaInicio = FechaHerramienta.formatoYMD(jdcGasolinaInicio.getDate());
                    String fechaFin = FechaHerramienta.formatoYMD(jdcGasolinaFin.getDate());

                    if (!cmbGasolinaResumen.getSelectedItem().toString().equals("Todos")) {
                        String Query = "FROM Gastogasolina gl WHERE gl.vehiculo= '"
                                + cmbGasolinaResumen.getSelectedItem().toString() + "' AND gl.fecha BETWEEN '"
                                + fechaInicio
                                + "' AND '"
                                + fechaFin + "'"
                                + " ORDER BY gl.fecha";
                        cargaTabla(jtGasolinaResumen, Query, "Gastogasolina", 1);
                    } else {
                        String Query = "FROM Gastogasolina gl WHERE  gl.fecha BETWEEN '"
                                + fechaInicio
                                + "' AND '"
                                + fechaFin + "'"
                                + " ORDER BY gl.fecha";
                        cargaTabla(jtGasolinaResumen, Query, "Gastogasolina", 1);
                    }
                }
            });
        }

        ////////////////////////////////////////////////////////
        if (jdcLocalInicio.isVisible()) {
            jdcLocalInicio.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {

                public void propertyChange(PropertyChangeEvent e) {
                    String fechaInicio = FechaHerramienta.formatoYMD(jdcLocalInicio.getDate());
                    String fechaFin = FechaHerramienta.formatoYMD(jdcLocalFin.getDate());

                    if (!cmbLocalResumen.getSelectedItem().toString().equals("Todos")) {
                        String Query = "FROM Gastoslocales gl WHERE gl.local= '"
                                + cmbLocalResumen.getSelectedItem().toString() + "' AND gl.fecha BETWEEN '"
                                + fechaInicio
                                + "' AND '"
                                + fechaFin + "'"
                                + " ORDER BY gl.fecha";
                        cargaTabla(jtLocalesR, Query, "GastosLocales", 2);
                    } else {
                        String Query = "FROM Gastoslocales gl WHERE  gl.fecha BETWEEN '"
                                + fechaInicio
                                + "' AND '"
                                + fechaFin + "'"
                                + " ORDER BY gl.fecha";
                        cargaTabla(jtLocalesR, Query, "GastosLocales", 2);
                    }
                }
            });
        }
        if (jdcLocalFin.isVisible()) {
            jdcLocalFin.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {

                public void propertyChange(PropertyChangeEvent e) {
                    String fechaInicio = FechaHerramienta.formatoYMD(jdcLocalInicio.getDate());
                    String fechaFin = FechaHerramienta.formatoYMD(jdcLocalFin.getDate());

                    if (!cmbLocalResumen.getSelectedItem().toString().equals("Todos")) {
                        String Query = "FROM Gastoslocales gl WHERE gl.local= '"
                                + cmbLocalResumen.getSelectedItem().toString() + "' AND gl.fecha BETWEEN '"
                                + fechaInicio
                                + "' AND '"
                                + fechaFin + "'"
                                + " ORDER BY gl.fecha";
                        cargaTabla(jtLocalesR, Query, "GastosLocales", 2);
                    } else {
                        String Query = "FROM Gastoslocales gl WHERE  gl.fecha BETWEEN '"
                                + fechaInicio
                                + "' AND '"
                                + fechaFin + "'"
                                + " ORDER BY gl.fecha";
                        cargaTabla(jtLocalesR, Query, "GastosLocales", 2);
                    }
                }
            });
        }

        ///////////////////////////////////////////////////////
        if (jdcFechaInicio.isVisible()) {
            jdcFechaInicio.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {

                public void propertyChange(PropertyChangeEvent e) {
                    String fechaInicio = FechaHerramienta.formatoYMD(jdcFechaInicio.getDate());
                    String fechaFin = FechaHerramienta.formatoYMD(jdcFechaFin.getDate());
                    if (!cmbProveedorBuscar.getSelectedItem().toString().equals("Todos")) {
                        String Query = "FROM Expediente e WHERE e.nombreProveedor='"
                                + cmbProveedorBuscar.getSelectedItem().toString()
                                + "' AND e.fechaApertura BETWEEN '"
                                + fechaInicio
                                + "' AND '"
                                + fechaFin + "'"
                                + " ORDER BY e.nombreProveedor";
                        cargaTabla(jtExpedienteAsignar, Query, "Expediente", 2);
                    } else {
                        String Query = "FROM Expediente e "
                                + " WHERE e.fechaApertura BETWEEN '"
                                + fechaInicio
                                + "' AND '"
                                + fechaFin + "'"
                                + " ORDER BY e.nombreProveedor";
                        cargaTabla(jtExpedienteAsignar, Query, "Expediente", 2);
                    }
                }
            });
        }
        if (jdcFechaFin.isVisible()) {
            jdcFechaFin.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {

                public void propertyChange(PropertyChangeEvent e) {
                    String fechaInicio = FechaHerramienta.formatoYMD(jdcFechaInicio.getDate());
                    String fechaFin = FechaHerramienta.formatoYMD(jdcFechaFin.getDate());
                    if (!cmbProveedorBuscar.getSelectedItem().toString().equals("Todos")) {
                        String Query = "FROM Expediente e WHERE e.nombreProveedor='"
                                + cmbProveedorBuscar.getSelectedItem().toString()
                                + "' AND e.fechaApertura BETWEEN '"
                                + fechaInicio
                                + "' AND '"
                                + fechaFin + "'"
                                + " ORDER BY e.nombreProveedor";
                        cargaTabla(jtExpedienteAsignar, Query, "Expediente", 2);
                    } else {
                        String Query = "FROM Expediente e "
                                + " WHERE e.fechaApertura BETWEEN '"
                                + fechaInicio
                                + "' AND '"
                                + fechaFin + "' "
                                + " ORDER BY e.nombreProveedor";
                        cargaTabla(jtExpedienteAsignar, Query, "Expediente", 2);
                    }
                }
            });
        }
        //////////////////////////////////////////////
        if (jdcInicioHistorial.isVisible()) {
            jdcInicioHistorial.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {

                public void propertyChange(PropertyChangeEvent e) {
                    String fechaInicio = FechaHerramienta.formatoYMD(jdcInicioHistorial.getDate());
                    String fechaFin = FechaHerramienta.formatoYMD(jdcFinHistorial.getDate());
                    if (!cmbProveedorHistorial.getSelectedItem().toString().equals("Todos")) {
                        String Query = "FROM Expediente e WHERE e.nombreProveedor='"
                                + cmbProveedorHistorial.getSelectedItem().toString()
                                + "' AND e.fechaApertura BETWEEN '"
                                + fechaInicio
                                + "' AND '"
                                + fechaFin + "'"
                                + " ORDER BY e.nombreProveedor";
                        cargaTabla(jtExpedienteHistorial, Query, "Expediente", 2);
                    } else {
                        String Query = "FROM Expediente e "
                                + " WHERE e.fechaApertura BETWEEN '"
                                + fechaInicio
                                + "' AND '"
                                + fechaFin + "'"
                                + " ORDER BY e.nombreProveedor";
                        cargaTabla(jtExpedienteHistorial, Query, "Expediente", 2);
                    }
                }
            });
        }
        if (jdcFinHistorial.isVisible()) {
            jdcFinHistorial.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {

                public void propertyChange(PropertyChangeEvent e) {
                    String fechaInicio = FechaHerramienta.formatoYMD(jdcInicioHistorial.getDate());
                    String fechaFin = FechaHerramienta.formatoYMD(jdcFinHistorial.getDate());
                    if (!cmbProveedorHistorial.getSelectedItem().toString().equals("Todos")) {
                        String Query = "FROM Expediente e WHERE e.nombreProveedor='"
                                + cmbProveedorHistorial.getSelectedItem().toString()
                                + "' AND e.fechaApertura BETWEEN '"
                                + fechaInicio
                                + "' AND '"
                                + fechaFin + "'"
                                + " ORDER BY e.nombreProveedor";
                        cargaTabla(jtExpedienteHistorial, Query, "Expediente", 2);
                    } else {
                        String Query = "FROM Expediente e "
                                + " WHERE e.fechaApertura BETWEEN '"
                                + fechaInicio
                                + "' AND '"
                                + fechaFin + "' "
                                + " ORDER BY e.nombreProveedor";
                        cargaTabla(jtExpedienteHistorial, Query, "Expediente", 2);
                    }
                }
            });
        }
        /////////////////////////////////////////////

        //////////////////////////////////////////////
        if (jdcInicioFinancieroResumen.isVisible()) {
            jdcInicioFinancieroResumen.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {

                public void propertyChange(PropertyChangeEvent e) {
                    String fechaInicio = FechaHerramienta.formatoYMD(jdcInicioFinancieroResumen.getDate());
                    String fechaFin = FechaHerramienta.formatoYMD(jdcFinFinancieroResumen.getDate());
                    if (!cmbConceptoFinanciero.getSelectedItem().toString().equals("Todos")) {
                        String Query = "FROM Gastosfinancieros gf WHERE gf.concepto= '"
                                + cmbConceptoFinanciero.getSelectedItem().toString() + "' AND gf.fecha BETWEEN '"
                                + fechaInicio
                                + "' AND '"
                                + fechaFin + "'"
                                + " ORDER BY gf.fecha";
                        cargaTabla(jtFinancierosResumen, Query, "Gastofinanciero", 1);
                    } else {
                        String Query = "FROM Gastosfinancieros gf WHERE  gf.fecha BETWEEN '"
                                + fechaInicio
                                + "' AND '"
                                + fechaFin + "'"
                                + " ORDER BY gf.fecha";
                        cargaTabla(jtFinancierosResumen, Query, "Gastofinanciero", 1);
                    }
                }
            });
        }
        if (jdcFinFinancieroResumen.isVisible()) {
            jdcFinFinancieroResumen.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {

                public void propertyChange(PropertyChangeEvent e) {
                    String fechaInicio = FechaHerramienta.formatoYMD(jdcInicioFinancieroResumen.getDate());
                    String fechaFin = FechaHerramienta.formatoYMD(jdcFinFinancieroResumen.getDate());
                    if (!cmbConceptoFinanciero.getSelectedItem().toString().equals("Todos")) {
                        String Query = "FROM Gastosfinancieros gf WHERE gf.concepto= '"
                                + cmbConceptoFinanciero.getSelectedItem().toString() + "' AND gf.fecha BETWEEN '"
                                + fechaInicio
                                + "' AND '"
                                + fechaFin + "'"
                                + " ORDER BY gf.fecha";
                        cargaTabla(jtFinancierosResumen, Query, "Gastofinanciero", 1);
                    } else {
                        String Query = "FROM Gastosfinancieros gf WHERE  gf.fecha BETWEEN '"
                                + fechaInicio
                                + "' AND '"
                                + fechaFin + "'"
                                + " ORDER BY gf.fecha";
                        cargaTabla(jtFinancierosResumen, Query, "Gastofinanciero", 1);
                    }
                }
            });
        }
        /////////////////////////////////////////////
        if (jdcInicioFinancieroResumen.isVisible()) {
            jdcInicioFinancieroResumen.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
                public void propertyChange(PropertyChangeEvent e) {
                    crearConsultaGlobal();
//                    cargaTabla(jtTablaGastosTotales, HQL, "Gastosgenerales", 1);
                }
            });
            jdcFinFinancieroResumen.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
                public void propertyChange(PropertyChangeEvent e) {
                    crearConsultaGlobal();
//                    cargaTabla(jtTablaGastosTotales, HQL, "Gastosgenerales", 1);
                }
            });
        }

        this.cmbConceptoFinanciero.addItemListener(changeClick);
    }

    private void limpiarCampos() {
        //Limpia fechas
        /*jdcFechaConceptoTotalFin.setDate(new Date());
         jdcFechaGasolina.setDate(new Date());
         jdcFechaGastosPersonales.setDate(new Date());
         jdcFechaConceptoTotalInicio.setDate(new Date());
         jdcFechaLocal.setDate(new Date());
         jdcFechaMantenimientoV.setDate(new Date());
         jdcFechaOficinaBodega.setDate(new Date());
         jdcFechaProveedores.setDate(new Date());
         jdcFechaSueldosPrestamos.setDate(new Date());*/

        //Reinicio los combos
//        cmbConceptoGasolina.setText("");
//        cmbConceptoGastosPersonales.setText("");
//        cmbConceptoLocales.getText();
//        cmbConceptoMantenimiento.setText("");
//        cmbConceptoOficinaBodega.setText("");
//        cmbProveedor.setText("");
        cmbConceptoSueldoPrestamo.setSelectedIndex(0);
//        cmbSubConceptoLocales.setText("");
        cmbConceptoFinanciero.setSelectedIndex(0);

        //Reinicio todas las cajas de texto
        txtComentariosGasolina.setText("");
        txtComentarioGastosPersonales.setText("");
        txtComentarioLocales.setText("");
        txtComentarioMantenimientoV.setText("");
        txtComentariosOficinaBodega.setText("");
        txtImporteGasolina.setText("");
        txtComentariosSueldosPrestamos.setText("");

        txtImporteGasolina.setText("0");
        txtImporteLocales.setText("0");
        txtImporteGastosPersonales.setText("0");
        txtImporteMantenimientoV.setText("0");
        txtImporteOficinaBodega.setText("0");
        txtAbonoProveedores.setText("0");
        txtImporteSueldoPrestamos.setText("0");
        txtEmpleado.setText("");
    }

    private void guardarGastosLocales(JTable tabla) {
        AccesoBD controlBD = new AccesoBD();
        String fecha = "";
        for (int i = 0; i < tabla.getRowCount(); i++) {
            try {
                pojos.Gastoslocales conceptoGasto = new pojos.Gastoslocales();
                //FECHA
                fecha = tabla.getValueAt(i, 0).toString();
                conceptoGasto.setFecha(FechaHerramienta.convertirStringEnDate(fecha));
                //CLAVE TIENDA
                //conceptoGasto.setIdLocal(Integer.parseInt(tabla.getValueAt(i, 0).toString()));
                //NOMBRE TIENDA
                conceptoGasto.setLocal(tabla.getValueAt(i, 1).toString());

                //CONCEPTO
                conceptoGasto.setConcepto(tabla.getValueAt(i, 2).toString());
                //IMPORTE
                conceptoGasto.setImporte(Double.parseDouble(tabla.getValueAt(i, 3).toString()));
                //COMENTARIO
                conceptoGasto.setComentario(tabla.getValueAt(i, 4).toString());

                controlBD.add(conceptoGasto);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e, "Error", 0);
            }
        }
        limpiarCampos();
        limpiarTablas(tabla);
        JOptionPane.showMessageDialog(this, "Gastos registrados correctamente", "Datos registrados", 1);        // TODO add your handling code here:
    }

    private void guardarGastosPersonales(JTable tabla) {
        AccesoBD controlBD = new AccesoBD();
        String fecha = "";
        for (int i = 0; i < tabla.getRowCount(); i++) {
            try {
                pojos.Gastospersonales conceptoGasto = new pojos.Gastospersonales();
                //FECHA
                fecha = tabla.getValueAt(i, 0).toString();
                conceptoGasto.setFecha(FechaHerramienta.convertirStringEnDate(fecha));
                //CONCEPTO
                conceptoGasto.setConcepto(tabla.getValueAt(i, 1).toString());
                //IMPORTE
                conceptoGasto.setImporte(Double.parseDouble(tabla.getValueAt(i, 2).toString()));
                //COMENTARIO
                conceptoGasto.setComentarios(tabla.getValueAt(i, 3).toString());

                controlBD.add(conceptoGasto);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e, "Error", 0);
            }
        }
        limpiarCampos();
        limpiarTablas(tabla);
        JOptionPane.showMessageDialog(this, "Gastos registrados correctamente", "Datos registrados", 1);        // TODO add your handling code here:
    }

    private void guardarGastosBodega(JTable tabla) {
        AccesoBD controlBD = new AccesoBD();
        String fecha = "";
        for (int i = 0; i < tabla.getRowCount(); i++) {
            try {
                pojos.Gastosoficinabodega conceptoGasto = new pojos.Gastosoficinabodega();
                //FECHA
                fecha = tabla.getValueAt(i, 0).toString();
                conceptoGasto.setFecha(FechaHerramienta.convertirStringEnDate(fecha));
                //CONCEPTO
                conceptoGasto.setConcepto(tabla.getValueAt(i, 2).toString());
                //IMPORTE
                conceptoGasto.setImporte(Double.parseDouble(tabla.getValueAt(i, 3).toString()));
                //COMENTARIO
                conceptoGasto.setComentarios(tabla.getValueAt(i, 4).toString());

                controlBD.add(conceptoGasto);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e, "Error", 0);
            }
        }
        limpiarCampos();
        limpiarTablas(tabla);
        JOptionPane.showMessageDialog(this, "Gastos registrados correctamente", "Datos registrados", 1);        // TODO add your handling code here:
    }

    private void guardarGastosGasolina(JTable tabla) {
        AccesoBD controlBD = new AccesoBD();
        String fecha = "";
        for (int i = 0; i < tabla.getRowCount(); i++) {
            try {
                pojos.Gastogasolina conceptoGasolina = new pojos.Gastogasolina();
                //FECHA
                fecha = tabla.getValueAt(i, 0).toString();
                conceptoGasolina.setFecha(FechaHerramienta.convertirStringEnDate(fecha));
                //CONCEPTO
                conceptoGasolina.setVehiculo(tabla.getValueAt(i, 1).toString());
                //COMENTARIO
                conceptoGasolina.setComentario(tabla.getValueAt(i, 2).toString());
                //IMPORTE
                conceptoGasolina.setImporte(Double.parseDouble(tabla.getValueAt(i, 3).toString()));

                controlBD.add(conceptoGasolina);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e, "Error", 0);
            }
        }
        limpiarCampos();
        limpiarTablas(tabla);
        JOptionPane.showMessageDialog(this, "Gastos registrados correctamente", "Datos registrados", 1);        // TODO add your handling code here:
    }

    private void guardarGastosMantenimiento(JTable tabla) {
        AccesoBD controlBD = new AccesoBD();
        String fecha = "";
        for (int i = 0; i < tabla.getRowCount(); i++) {
            try {
                pojos.Gastomantenimientov gastoMantenimiento = new pojos.Gastomantenimientov();
                //FECHA
                fecha = tabla.getValueAt(i, 0).toString();
                gastoMantenimiento.setFecha(FechaHerramienta.convertirStringEnDate(fecha));
                //VEHICULO
                gastoMantenimiento.setVehiculo(tabla.getValueAt(i, 1).toString());
                //MANTENIMIENTO
                gastoMantenimiento.setMantenimiento(tabla.getValueAt(i, 2).toString());
                //COMENTARIO
                gastoMantenimiento.setComentario(tabla.getValueAt(i, 4).toString());
                //IMPORTE
                gastoMantenimiento.setImporte(Double.parseDouble(tabla.getValueAt(i, 3).toString()));

                controlBD.add(gastoMantenimiento);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e, "Error", 0);
            }
        }
        limpiarCampos();
        limpiarTablas(tabla);
        JOptionPane.showMessageDialog(this, "Gastos registrados correctamente", "Datos registrados", 1);        // TODO add your handling code here:
    }

    private void guardarConceptoLocal(JTable tabla, String concepto, String tienda) {
        AccesoBD controlBD = new AccesoBD();
        String fecha = "";
        for (int i = 0; i < tabla.getRowCount(); i++) {
            try {
                pojos.Gastosgenerales conceptoGasto = new pojos.Gastosgenerales();
                //FECHA
                fecha = tabla.getValueAt(i, 1).toString();
                conceptoGasto.setFecha(FechaHerramienta.convertirStringEnDate(fecha));
                //TIENDA
                conceptoGasto.setTienda(tienda);
                //CONCEPTO
                conceptoGasto.setConcepto(concepto);
                //IMPORTE
                String importe = tabla.getValueAt(i, 5).toString();
                conceptoGasto.setImporte(Double.parseDouble(importe));
                //OMENTARIO
                conceptoGasto.setComentario(tabla.getValueAt(i, 3).toString());
                controlBD.add(conceptoGasto);
                limpiarCampos();
                String Query = "FROM Contenidoexpediente ce WHERE ce.idExpediente="
                        + idExpediente
                        + " ORDER BY ce.folio";
                cargaTabla(jtFoliosAsignados, Query, "ContenidoExpediente2", 1);
                JOptionPane.showMessageDialog(this, "Gastos registrados correctamente", "Datos registrados", 1);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e, "Error", 0);
            }

            // TODO add your handling code here:
        }
    }

    private boolean abonarFolio(double abono) {

        AccesoBD acceso = new AccesoBD();
        int clave = (int) jtFoliosAsignados.getValueAt(jtFoliosAsignados.getSelectedRow(), 0);
        String Query = "FROM Contenidoexpediente ce WHERE ce.idContenidoExpediente="
                + clave
                + " ORDER BY ce.folio";
        Contenidoexpediente conExp = (Contenidoexpediente) acceso.select(Query).get(0);
        if (abono < conExp.getImporte()) {
            double abonoOriginal = conExp.getAbono();
            double nuevoAbono = abono + abonoOriginal;
            if (nuevoAbono <= conExp.getImporte()) {
                try {
                    conExp.setFormaDePago(cmbFormaDePago.getSelectedItem().toString());
                    conExp.setAbono(nuevoAbono);
                    acceso.Update(conExp);
                    Query = "FROM Contenidoexpediente ce WHERE ce.idExpediente="
                            + idExpediente
                            + " ORDER BY ce.folio";
                    cargaTabla(jtFoliosAsignados, Query, "ContenidoExpediente2", 1);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, e, "Error", 0);
                }
                return true;
            }
        }

        return false;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jpLocales = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtImporteLocales = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jdcFechaLocal = new com.toedter.calendar.JDateChooser();
        jLabel29 = new javax.swing.JLabel();
        btnAnotarLocales = new javax.swing.JButton();
        btnBorrarGasto = new javax.swing.JButton();
        btnRegistrarLocales = new javax.swing.JButton();
        txtComentarioLocales = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        jtLocales = new javax.swing.JTable();
        jLabel28 = new javax.swing.JLabel();
        cmbConceptoLocales = new javax.swing.JComboBox();
        cmbSubConceptoLocales = new javax.swing.JComboBox();
        jpLocalesR = new javax.swing.JPanel();
        jLabel64 = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        jdcLocalInicio = new com.toedter.calendar.JDateChooser();
        jLabel40 = new javax.swing.JLabel();
        jdcLocalFin = new com.toedter.calendar.JDateChooser();
        jLabel44 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        jtLocalesR = new javax.swing.JTable();
        Concepto1 = new javax.swing.JLabel();
        cmbLocalResumen = new javax.swing.JComboBox();
        btnBorrrar = new javax.swing.JButton();
        btnCorregir = new javax.swing.JButton();
        btnLocales = new javax.swing.JButton();
        jpPersonales = new javax.swing.JPanel();
        btnBorrarGasto1 = new javax.swing.JButton();
        btnRegistrarGastosPersonales = new javax.swing.JButton();
        jLabel45 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        btnAnotarGastosPersonales = new javax.swing.JButton();
        txtImporteGastosPersonales = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        txtComentarioGastosPersonales = new javax.swing.JTextField();
        jScrollPane12 = new javax.swing.JScrollPane();
        jtPersonales = new javax.swing.JTable();
        jdcFechaGastosPersonales = new com.toedter.calendar.JDateChooser();
        cmbConceptoGastosPersonales = new javax.swing.JComboBox();
        jpPersonalR = new javax.swing.JPanel();
        jLabel99 = new javax.swing.JLabel();
        jLabel100 = new javax.swing.JLabel();
        jdcGtoPersonalInicio = new com.toedter.calendar.JDateChooser();
        jLabel101 = new javax.swing.JLabel();
        jdcGtoPersonalFin = new com.toedter.calendar.JDateChooser();
        Concepto2 = new javax.swing.JLabel();
        jLabel102 = new javax.swing.JLabel();
        jScrollPane16 = new javax.swing.JScrollPane();
        jtPersonalesR = new javax.swing.JTable();
        btnBorrrar1 = new javax.swing.JButton();
        btnCorregir1 = new javax.swing.JButton();
        cmbGtoPerCon = new javax.swing.JComboBox();
        btnGenertales = new javax.swing.JButton();
        jpBodega = new javax.swing.JPanel();
        btnBorrarGasto2 = new javax.swing.JButton();
        btnRegistrarOficinaBodega = new javax.swing.JButton();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        btnAnotarOficinaBodega = new javax.swing.JButton();
        txtImporteOficinaBodega = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        txtComentariosOficinaBodega = new javax.swing.JTextField();
        jScrollPane13 = new javax.swing.JScrollPane();
        jtBodega = new javax.swing.JTable();
        jdcFechaOficinaBodega = new com.toedter.calendar.JDateChooser();
        cmbConceptoOficinaBodega = new javax.swing.JComboBox();
        jpBodegaR = new javax.swing.JPanel();
        btnBorrrarBodega = new javax.swing.JButton();
        btnCorregirBodega = new javax.swing.JButton();
        jScrollPane21 = new javax.swing.JScrollPane();
        jtBodegaR = new javax.swing.JTable();
        jLabel60 = new javax.swing.JLabel();
        jLabel120 = new javax.swing.JLabel();
        jdcBodegaInicio = new com.toedter.calendar.JDateChooser();
        jLabel41 = new javax.swing.JLabel();
        jdcBodegaFin = new com.toedter.calendar.JDateChooser();
        Concepto5 = new javax.swing.JLabel();
        cmbBodegaR = new javax.swing.JComboBox();
        jLabel68 = new javax.swing.JLabel();
        btnBodega = new javax.swing.JButton();
        jpGasolina = new javax.swing.JPanel();
        btnBorrarGasto3 = new javax.swing.JButton();
        btnRegistrarGasolina = new javax.swing.JButton();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        btnAnotarGasolina = new javax.swing.JButton();
        txtImporteGasolina = new javax.swing.JTextField();
        jLabel57 = new javax.swing.JLabel();
        txtComentariosGasolina = new javax.swing.JTextField();
        jScrollPane14 = new javax.swing.JScrollPane();
        jtTablaGasolina = new javax.swing.JTable();
        jdcFechaGasolina = new com.toedter.calendar.JDateChooser();
        cmbVehiculo = new javax.swing.JComboBox();
        jpGasolinaR = new javax.swing.JPanel();
        jLabel108 = new javax.swing.JLabel();
        jLabel109 = new javax.swing.JLabel();
        jdcGasolinaInicio = new com.toedter.calendar.JDateChooser();
        jLabel110 = new javax.swing.JLabel();
        jdcGasolinaFin = new com.toedter.calendar.JDateChooser();
        Concepto3 = new javax.swing.JLabel();
        jLabel111 = new javax.swing.JLabel();
        jScrollPane18 = new javax.swing.JScrollPane();
        jtGasolinaResumen = new javax.swing.JTable();
        cmbGasolinaResumen = new javax.swing.JComboBox();
        btnBorrrarGasolinaGasto = new javax.swing.JButton();
        btnCorregirGastoGasolina = new javax.swing.JButton();
        btnGasolina = new javax.swing.JButton();
        jpMantenimiento = new javax.swing.JPanel();
        btnBorrarGtoMantenimiento = new javax.swing.JButton();
        btnRegistrarMantenimientoV = new javax.swing.JButton();
        jLabel59 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        btnAnotarMantenimientoV = new javax.swing.JButton();
        txtImporteMantenimientoV = new javax.swing.JTextField();
        jLabel62 = new javax.swing.JLabel();
        txtComentarioMantenimientoV = new javax.swing.JTextField();
        jScrollPane15 = new javax.swing.JScrollPane();
        jtTablaMantenimientoV = new javax.swing.JTable();
        jdcFechaMantenimientoV = new com.toedter.calendar.JDateChooser();
        jLabel114 = new javax.swing.JLabel();
        jLabel115 = new javax.swing.JLabel();
        cmbVehiculoM = new javax.swing.JComboBox();
        cmbMantenimiento = new javax.swing.JComboBox();
        jpMantenimientoR = new javax.swing.JPanel();
        cmbVehiculoMResumen = new javax.swing.JComboBox();
        btnBorrrarMantenimientoGasto = new javax.swing.JButton();
        btnCorregirGastoMantenimiento = new javax.swing.JButton();
        jLabel116 = new javax.swing.JLabel();
        jLabel117 = new javax.swing.JLabel();
        jdcMantenimientoInicio = new com.toedter.calendar.JDateChooser();
        jLabel118 = new javax.swing.JLabel();
        jdcMantenimientoFin = new com.toedter.calendar.JDateChooser();
        Concepto4 = new javax.swing.JLabel();
        jLabel119 = new javax.swing.JLabel();
        jScrollPane20 = new javax.swing.JScrollPane();
        jtMantenimientoResumen = new javax.swing.JTable();
        btnMantenimiento = new javax.swing.JButton();
        jpProveedores = new javax.swing.JPanel();
        btnAbonarProveedores = new javax.swing.JButton();
        jLabel66 = new javax.swing.JLabel();
        txtAbonoProveedores = new javax.swing.JTextField();
        jLabel67 = new javax.swing.JLabel();
        cmbFormaDePago = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtExpedienteAsignar = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        jtFoliosAsignados = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jdcFechaFin = new com.toedter.calendar.JDateChooser();
        jLabel65 = new javax.swing.JLabel();
        cmbProveedorBuscar = new javax.swing.JComboBox();
        jLabel84 = new javax.swing.JLabel();
        jdcFechaInicio = new com.toedter.calendar.JDateChooser();
        jLabel92 = new javax.swing.JLabel();
        jpProveedoresR = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jtExpedienteHistorial = new javax.swing.JTable();
        jScrollPane9 = new javax.swing.JScrollPane();
        jtAbonosHistorial = new javax.swing.JTable();
        jScrollPane10 = new javax.swing.JScrollPane();
        jtFoliosHistorial = new javax.swing.JTable();
        jLabel97 = new javax.swing.JLabel();
        lblNotaHistorial = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel93 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        cmbProveedorHistorial = new javax.swing.JComboBox();
        jLabel95 = new javax.swing.JLabel();
        jdcInicioHistorial = new com.toedter.calendar.JDateChooser();
        jLabel96 = new javax.swing.JLabel();
        jdcFinHistorial = new com.toedter.calendar.JDateChooser();
        btnProveedores = new javax.swing.JButton();
        jpFinancieros = new javax.swing.JPanel();
        btnBorrarGasto6 = new javax.swing.JButton();
        btnRegistrarSueldoPrestamos = new javax.swing.JButton();
        cmbConceptoSueldoPrestamo = new javax.swing.JComboBox();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        btnAnotarSueldosPrestamos = new javax.swing.JButton();
        txtImporteSueldoPrestamos = new javax.swing.JTextField();
        jLabel72 = new javax.swing.JLabel();
        txtComentariosSueldosPrestamos = new javax.swing.JTextField();
        jScrollPane17 = new javax.swing.JScrollPane();
        jtGastosFinancieros = new javax.swing.JTable();
        jdcFechaSueldosPrestamos = new com.toedter.calendar.JDateChooser();
        jLabel79 = new javax.swing.JLabel();
        txtEmpleado = new javax.swing.JTextField();
        jpFinancierosR = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jtFinancierosResumen = new javax.swing.JTable();
        jLabel39 = new javax.swing.JLabel();
        btnCorregirConcepto = new javax.swing.JButton();
        btnEliminarConcepto = new javax.swing.JButton();
        Concepto = new javax.swing.JLabel();
        cmbConceptoFinanciero = new javax.swing.JComboBox();
        jdcFinFinancieroResumen = new com.toedter.calendar.JDateChooser();
        jdcInicioFinancieroResumen = new com.toedter.calendar.JDateChooser();
        jLabel37 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        btnFinancieros = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Registro de gastos generales");

        jPanel5.setBackground(new java.awt.Color(57, 105, 145));

        jLabel10.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("GASTOS GENERALES");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(421, 421, 421)
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                .addGap(389, 389, 389))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setForeground(new java.awt.Color(0, 102, 153));
        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jTabbedPane1.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jTabbedPane1.setOpaque(true);

        jpLocales.setBackground(new java.awt.Color(255, 255, 255));
        jpLocales.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jpLocalesComponentShown(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 102, 153));
        jLabel2.setText("CONCEPTO");

        jLabel5.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 102, 153));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("IMPORTE");

        txtImporteLocales.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtImporteLocalesKeyTyped(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 102, 153));
        jLabel6.setText("COMENTARIOS");

        jLabel29.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(0, 102, 153));
        jLabel29.setText("FECHA");

        btnAnotarLocales.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAnotarLocales.setForeground(new java.awt.Color(0, 153, 51));
        btnAnotarLocales.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/list.png"))); // NOI18N
        btnAnotarLocales.setText("Anotar");
        btnAnotarLocales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnotarLocalesActionPerformed(evt);
            }
        });

        btnBorrarGasto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnBorrarGasto.setForeground(new java.awt.Color(0, 153, 51));
        btnBorrarGasto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/menos.png"))); // NOI18N
        btnBorrarGasto.setText("Quitar");
        btnBorrarGasto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarGastoActionPerformed(evt);
            }
        });

        btnRegistrarLocales.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnRegistrarLocales.setForeground(new java.awt.Color(0, 153, 51));
        btnRegistrarLocales.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/mas.png"))); // NOI18N
        btnRegistrarLocales.setText("Registrar");
        btnRegistrarLocales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarLocalesActionPerformed(evt);
            }
        });

        jtLocales.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "FECHA", "TIENDA", "CONCEPTO", "IMPORTE", "COMENTARIO"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(jtLocales);

        jLabel28.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(0, 102, 153));
        jLabel28.setText("LOCAL");

        cmbConceptoLocales.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbConceptoLocales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbConceptoLocalesActionPerformed(evt);
            }
        });

        cmbSubConceptoLocales.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jpLocalesLayout = new javax.swing.GroupLayout(jpLocales);
        jpLocales.setLayout(jpLocalesLayout);
        jpLocalesLayout.setHorizontalGroup(
            jpLocalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpLocalesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpLocalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpLocalesLayout.createSequentialGroup()
                        .addGroup(jpLocalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jdcFechaLocal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpLocalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbConceptoLocales, 0, 122, Short.MAX_VALUE)
                            .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpLocalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbSubConceptoLocales, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpLocalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtImporteLocales, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpLocalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtComentarioLocales)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE))
                        .addGap(71, 71, 71)
                        .addComponent(btnAnotarLocales))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpLocalesLayout.createSequentialGroup()
                        .addComponent(btnBorrarGasto)
                        .addGap(18, 18, 18)
                        .addComponent(btnRegistrarLocales, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jpLocalesLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnBorrarGasto, btnRegistrarLocales});

        jpLocalesLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cmbConceptoLocales, cmbSubConceptoLocales, jLabel2, jLabel28, jLabel29, jLabel5, jLabel6, jdcFechaLocal, txtComentarioLocales, txtImporteLocales});

        jpLocalesLayout.setVerticalGroup(
            jpLocalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpLocalesLayout.createSequentialGroup()
                .addGroup(jpLocalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel29)
                    .addComponent(jLabel28)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpLocalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jdcFechaLocal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtImporteLocales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtComentarioLocales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbConceptoLocales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbSubConceptoLocales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAnotarLocales, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpLocalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBorrarGasto)
                    .addComponent(btnRegistrarLocales))
                .addGap(19, 19, 19))
        );

        jTabbedPane1.addTab("LOCALES", jpLocales);

        jpLocalesR.setBackground(new java.awt.Color(255, 255, 255));
        jpLocalesR.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jpLocalesRComponentShown(evt);
            }
        });

        jLabel64.setBackground(new java.awt.Color(0, 102, 153));
        jLabel64.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel64.setForeground(new java.awt.Color(255, 255, 255));
        jLabel64.setText("PERIODO TRABAJADO");
        jLabel64.setOpaque(true);

        jLabel98.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel98.setForeground(new java.awt.Color(0, 102, 153));
        jLabel98.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel98.setText("FECHA INICIO");

        jLabel40.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(0, 102, 153));
        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel40.setText("FECHA FIN");

        jLabel44.setBackground(new java.awt.Color(0, 102, 153));
        jLabel44.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(255, 255, 255));
        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel44.setText("RESULTADOS");
        jLabel44.setOpaque(true);

        jtLocalesR.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jtLocalesR.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtLocalesRMouseClicked(evt);
            }
        });
        jScrollPane11.setViewportView(jtLocalesR);

        Concepto1.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        Concepto1.setForeground(new java.awt.Color(0, 102, 153));
        Concepto1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Concepto1.setText("TIENDA");

        cmbLocalResumen.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        cmbLocalResumen.setForeground(new java.awt.Color(0, 102, 153));
        cmbLocalResumen.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TODOS", "LOCALES", "GASTOS PERSONALES", "OFICINA BODEGA", "GASOLINA", "MANTNIMIENTO DE VEHICULOS", "PROVEEDORES", "SUELDOS-PRESTAMOS" }));
        cmbLocalResumen.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbLocalResumenItemStateChanged(evt);
            }
        });

        btnBorrrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/Cancel.png"))); // NOI18N
        btnBorrrar.setText("Borrar");
        btnBorrrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrrarActionPerformed(evt);
            }
        });

        btnCorregir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/correct.png"))); // NOI18N
        btnCorregir.setText("Corregir");
        btnCorregir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCorregirActionPerformed(evt);
            }
        });

        btnLocales.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/pdf.png"))); // NOI18N
        btnLocales.setText("RESULTADOS");
        btnLocales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocalesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpLocalesRLayout = new javax.swing.GroupLayout(jpLocalesR);
        jpLocalesR.setLayout(jpLocalesRLayout);
        jpLocalesRLayout.setHorizontalGroup(
            jpLocalesRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpLocalesRLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpLocalesRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jpLocalesRLayout.createSequentialGroup()
                        .addComponent(jLabel64, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(3, 3, 3))
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 827, Short.MAX_VALUE)
                    .addGroup(jpLocalesRLayout.createSequentialGroup()
                        .addComponent(jLabel98, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jdcLocalInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jdcLocalFin, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Concepto1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cmbLocalResumen, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jpLocalesRLayout.createSequentialGroup()
                        .addComponent(btnBorrrar, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLocales)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCorregir)))
                .addGap(28, 28, 28))
        );

        jpLocalesRLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel40, jLabel98, jdcLocalFin, jdcLocalInicio});

        jpLocalesRLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnBorrrar, btnCorregir});

        jpLocalesRLayout.setVerticalGroup(
            jpLocalesRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpLocalesRLayout.createSequentialGroup()
                .addComponent(jLabel64)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpLocalesRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel98)
                    .addComponent(jdcLocalInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel40)
                    .addComponent(jdcLocalFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Concepto1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbLocalResumen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel44)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpLocalesRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBorrrar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCorregir)
                    .addComponent(btnLocales))
                .addGap(23, 23, 23))
        );

        jpLocalesRLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnBorrrar, btnCorregir});

        jTabbedPane1.addTab("R. LOCALES", jpLocalesR);

        jpPersonales.setBackground(new java.awt.Color(255, 255, 255));
        jpPersonales.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jpPersonalesComponentShown(evt);
            }
        });

        btnBorrarGasto1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnBorrarGasto1.setForeground(new java.awt.Color(0, 153, 51));
        btnBorrarGasto1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/menos.png"))); // NOI18N
        btnBorrarGasto1.setText("Quitar");
        btnBorrarGasto1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarGasto1ActionPerformed(evt);
            }
        });

        btnRegistrarGastosPersonales.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnRegistrarGastosPersonales.setForeground(new java.awt.Color(0, 153, 51));
        btnRegistrarGastosPersonales.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/mas.png"))); // NOI18N
        btnRegistrarGastosPersonales.setText("Registrar");
        btnRegistrarGastosPersonales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarGastosPersonalesActionPerformed(evt);
            }
        });

        jLabel45.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(0, 102, 153));
        jLabel45.setText("FECHA");

        jLabel27.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(0, 102, 153));
        jLabel27.setText("CONCEPTO");

        jLabel46.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(0, 102, 153));
        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel46.setText("IMPORTE");

        btnAnotarGastosPersonales.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAnotarGastosPersonales.setForeground(new java.awt.Color(0, 153, 51));
        btnAnotarGastosPersonales.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/list.png"))); // NOI18N
        btnAnotarGastosPersonales.setText("Anotar");
        btnAnotarGastosPersonales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnotarGastosPersonalesActionPerformed(evt);
            }
        });

        txtImporteGastosPersonales.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtImporteGastosPersonalesKeyTyped(evt);
            }
        });

        jLabel47.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(0, 102, 153));
        jLabel47.setText("COMENTARIOS");

        jtPersonales.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "FECHA", "CONCEPTO", "IMPORTE", "COMENTARIOS"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane12.setViewportView(jtPersonales);

        javax.swing.GroupLayout jpPersonalesLayout = new javax.swing.GroupLayout(jpPersonales);
        jpPersonales.setLayout(jpPersonalesLayout);
        jpPersonalesLayout.setHorizontalGroup(
            jpPersonalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpPersonalesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpPersonalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpPersonalesLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnBorrarGasto1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRegistrarGastosPersonales, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpPersonalesLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jpPersonalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel45, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jdcFechaGastosPersonales, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpPersonalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                            .addComponent(cmbConceptoGastosPersonales, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpPersonalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtImporteGastosPersonales, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpPersonalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel47)
                            .addComponent(txtComentarioGastosPersonales, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAnotarGastosPersonales, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(140, 140, 140))
        );

        jpPersonalesLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnAnotarGastosPersonales, jLabel27, jLabel45, jLabel46, jLabel47, jdcFechaGastosPersonales, txtComentarioGastosPersonales, txtImporteGastosPersonales});

        jpPersonalesLayout.setVerticalGroup(
            jpPersonalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPersonalesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpPersonalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpPersonalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jpPersonalesLayout.createSequentialGroup()
                            .addComponent(jLabel45)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jdcFechaGastosPersonales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpPersonalesLayout.createSequentialGroup()
                            .addGroup(jpPersonalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel46)
                                .addComponent(jLabel47))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jpPersonalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                .addComponent(txtComentarioGastosPersonales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtImporteGastosPersonales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cmbConceptoGastosPersonales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(btnAnotarGastosPersonales, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpPersonalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBorrarGasto1)
                    .addComponent(btnRegistrarGastosPersonales))
                .addContainerGap())
        );

        jTabbedPane1.addTab("GTO PERSONAL", jpPersonales);

        jpPersonalR.setBackground(new java.awt.Color(255, 255, 255));
        jpPersonalR.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jpPersonalRComponentShown(evt);
            }
        });

        jLabel99.setBackground(new java.awt.Color(0, 102, 153));
        jLabel99.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel99.setForeground(new java.awt.Color(255, 255, 255));
        jLabel99.setText("PERIODO TRABAJADO");
        jLabel99.setOpaque(true);

        jLabel100.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel100.setForeground(new java.awt.Color(0, 102, 153));
        jLabel100.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel100.setText("FECHA INICIO");

        jLabel101.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel101.setForeground(new java.awt.Color(0, 102, 153));
        jLabel101.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel101.setText("FECHA FIN");

        Concepto2.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        Concepto2.setForeground(new java.awt.Color(0, 102, 153));
        Concepto2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Concepto2.setText("CONCEPTO");

        jLabel102.setBackground(new java.awt.Color(0, 102, 153));
        jLabel102.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel102.setForeground(new java.awt.Color(255, 255, 255));
        jLabel102.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel102.setText("RESULTADOS");
        jLabel102.setOpaque(true);

        jtPersonalesR.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jtPersonalesR.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtPersonalesRMouseClicked(evt);
            }
        });
        jScrollPane16.setViewportView(jtPersonalesR);

        btnBorrrar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/Cancel.png"))); // NOI18N
        btnBorrrar1.setText("Borrar");
        btnBorrrar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrrar1ActionPerformed(evt);
            }
        });

        btnCorregir1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/correct.png"))); // NOI18N
        btnCorregir1.setText("Corregir");
        btnCorregir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCorregir1ActionPerformed(evt);
            }
        });

        cmbGtoPerCon.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbGtoPerCon.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbGtoPerConItemStateChanged(evt);
            }
        });

        btnGenertales.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/pdf.png"))); // NOI18N
        btnGenertales.setText("RESULTADOS");
        btnGenertales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenertalesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpPersonalRLayout = new javax.swing.GroupLayout(jpPersonalR);
        jpPersonalR.setLayout(jpPersonalRLayout);
        jpPersonalRLayout.setHorizontalGroup(
            jpPersonalRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPersonalRLayout.createSequentialGroup()
                .addGroup(jpPersonalRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpPersonalRLayout.createSequentialGroup()
                        .addComponent(jLabel100, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jdcGtoPersonalInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel101, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jdcGtoPersonalFin, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Concepto2, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cmbGtoPerCon, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpPersonalRLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jpPersonalRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane16, javax.swing.GroupLayout.DEFAULT_SIZE, 835, Short.MAX_VALUE)
                            .addGroup(jpPersonalRLayout.createSequentialGroup()
                                .addComponent(btnBorrrar1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnGenertales)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnCorregir1))))
                    .addComponent(jLabel102, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel99, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );

        jpPersonalRLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {Concepto2, jLabel100, jLabel101, jdcGtoPersonalFin, jdcGtoPersonalInicio});

        jpPersonalRLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnBorrrar1, btnCorregir1});

        jpPersonalRLayout.setVerticalGroup(
            jpPersonalRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPersonalRLayout.createSequentialGroup()
                .addComponent(jLabel99)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpPersonalRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel100)
                    .addComponent(jdcGtoPersonalInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel101)
                    .addComponent(jdcGtoPersonalFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Concepto2, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbGtoPerCon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel102)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane16, javax.swing.GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpPersonalRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBorrrar1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCorregir1)
                    .addComponent(btnGenertales))
                .addGap(12, 12, 12))
        );

        jpPersonalRLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnBorrrar1, btnCorregir1});

        jTabbedPane1.addTab("R. GTO PERSONAL", jpPersonalR);

        jpBodega.setBackground(new java.awt.Color(255, 255, 255));
        jpBodega.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jpBodegaComponentShown(evt);
            }
        });

        btnBorrarGasto2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnBorrarGasto2.setForeground(new java.awt.Color(0, 153, 51));
        btnBorrarGasto2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/menos.png"))); // NOI18N
        btnBorrarGasto2.setText("Quitar");
        btnBorrarGasto2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarGasto2ActionPerformed(evt);
            }
        });

        btnRegistrarOficinaBodega.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnRegistrarOficinaBodega.setForeground(new java.awt.Color(0, 153, 51));
        btnRegistrarOficinaBodega.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/mas.png"))); // NOI18N
        btnRegistrarOficinaBodega.setText("Registrar");
        btnRegistrarOficinaBodega.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarOficinaBodegaActionPerformed(evt);
            }
        });

        jLabel49.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(0, 102, 153));
        jLabel49.setText("FECHA");

        jLabel50.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(0, 102, 153));
        jLabel50.setText("CONCEPTO");

        jLabel51.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(0, 102, 153));
        jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel51.setText("IMPORTE");

        btnAnotarOficinaBodega.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAnotarOficinaBodega.setForeground(new java.awt.Color(0, 153, 51));
        btnAnotarOficinaBodega.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/list.png"))); // NOI18N
        btnAnotarOficinaBodega.setText("Anotar");
        btnAnotarOficinaBodega.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnotarOficinaBodegaActionPerformed(evt);
            }
        });

        txtImporteOficinaBodega.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtImporteOficinaBodegaKeyTyped(evt);
            }
        });

        jLabel52.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(0, 102, 153));
        jLabel52.setText("COMENTARIOS");

        jtBodega.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "FECHA", "GASTO DE", "CONCEPTO", "IMPORTE", "COMENTARIOS"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane13.setViewportView(jtBodega);

        cmbConceptoOficinaBodega.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jpBodegaLayout = new javax.swing.GroupLayout(jpBodega);
        jpBodega.setLayout(jpBodegaLayout);
        jpBodegaLayout.setHorizontalGroup(
            jpBodegaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpBodegaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpBodegaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane13)
                    .addGroup(jpBodegaLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jpBodegaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel49, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jdcFechaOficinaBodega, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpBodegaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel50, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                            .addComponent(cmbConceptoOficinaBodega, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpBodegaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel51, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtImporteOficinaBodega, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpBodegaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel52)
                            .addGroup(jpBodegaLayout.createSequentialGroup()
                                .addComponent(txtComentariosOficinaBodega, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAnotarOficinaBodega)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpBodegaLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnBorrarGasto2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRegistrarOficinaBodega, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(68, 68, 68))
        );

        jpBodegaLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnAnotarOficinaBodega, jLabel49, jLabel50, jLabel51, jLabel52, jdcFechaOficinaBodega, txtComentariosOficinaBodega, txtImporteOficinaBodega});

        jpBodegaLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnBorrarGasto2, btnRegistrarOficinaBodega});

        jpBodegaLayout.setVerticalGroup(
            jpBodegaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpBodegaLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jpBodegaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpBodegaLayout.createSequentialGroup()
                        .addComponent(jLabel49)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jdcFechaOficinaBodega, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpBodegaLayout.createSequentialGroup()
                        .addGroup(jpBodegaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel51)
                            .addComponent(jLabel52))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpBodegaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(txtComentariosOficinaBodega, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtImporteOficinaBodega, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAnotarOficinaBodega, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbConceptoOficinaBodega, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpBodegaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBorrarGasto2)
                    .addComponent(btnRegistrarOficinaBodega))
                .addGap(22, 22, 22))
        );

        jTabbedPane1.addTab("OFICINA BODEGA", jpBodega);

        jpBodegaR.setBackground(new java.awt.Color(255, 255, 255));
        jpBodegaR.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jpBodegaRComponentShown(evt);
            }
        });

        btnBorrrarBodega.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/Cancel.png"))); // NOI18N
        btnBorrrarBodega.setText("Borrar");
        btnBorrrarBodega.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrrarBodegaActionPerformed(evt);
            }
        });

        btnCorregirBodega.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/correct.png"))); // NOI18N
        btnCorregirBodega.setText("Corregir");
        btnCorregirBodega.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCorregirBodegaActionPerformed(evt);
            }
        });

        jtBodegaR.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jtBodegaR.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtBodegaRMouseClicked(evt);
            }
        });
        jScrollPane21.setViewportView(jtBodegaR);

        jLabel60.setBackground(new java.awt.Color(0, 102, 153));
        jLabel60.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(255, 255, 255));
        jLabel60.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel60.setText("RESULTADOS");
        jLabel60.setOpaque(true);

        jLabel120.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel120.setForeground(new java.awt.Color(0, 102, 153));
        jLabel120.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel120.setText("FECHA INICIO");

        jLabel41.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(0, 102, 153));
        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel41.setText("FECHA FIN");

        Concepto5.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        Concepto5.setForeground(new java.awt.Color(0, 102, 153));
        Concepto5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Concepto5.setText("CONCEPTO");

        cmbBodegaR.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        cmbBodegaR.setForeground(new java.awt.Color(0, 102, 153));
        cmbBodegaR.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TODOS", "LOCALES", "GASTOS PERSONALES", "OFICINA BODEGA", "GASOLINA", "MANTNIMIENTO DE VEHICULOS", "PROVEEDORES", "SUELDOS-PRESTAMOS" }));
        cmbBodegaR.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbBodegaRItemStateChanged(evt);
            }
        });

        jLabel68.setBackground(new java.awt.Color(0, 102, 153));
        jLabel68.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel68.setForeground(new java.awt.Color(255, 255, 255));
        jLabel68.setText("PERIODO TRABAJADO");
        jLabel68.setOpaque(true);

        btnBodega.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/pdf.png"))); // NOI18N
        btnBodega.setText("RESULTADOS");
        btnBodega.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBodegaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpBodegaRLayout = new javax.swing.GroupLayout(jpBodegaR);
        jpBodegaR.setLayout(jpBodegaRLayout);
        jpBodegaRLayout.setHorizontalGroup(
            jpBodegaRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpBodegaRLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpBodegaRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jpBodegaRLayout.createSequentialGroup()
                        .addComponent(jLabel68, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(3, 3, 3))
                    .addComponent(jScrollPane21, javax.swing.GroupLayout.DEFAULT_SIZE, 827, Short.MAX_VALUE)
                    .addGroup(jpBodegaRLayout.createSequentialGroup()
                        .addComponent(jLabel120, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jdcBodegaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jdcBodegaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Concepto5, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cmbBodegaR, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jpBodegaRLayout.createSequentialGroup()
                        .addComponent(btnBorrrarBodega, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBodega)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCorregirBodega)))
                .addGap(28, 28, 28))
        );

        jpBodegaRLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {Concepto5, jLabel120, jLabel41});

        jpBodegaRLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cmbBodegaR, jdcBodegaFin, jdcBodegaInicio});

        jpBodegaRLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnBorrrarBodega, btnCorregirBodega});

        jpBodegaRLayout.setVerticalGroup(
            jpBodegaRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpBodegaRLayout.createSequentialGroup()
                .addComponent(jLabel68)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpBodegaRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel120)
                    .addComponent(jdcBodegaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel41)
                    .addComponent(jdcBodegaFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Concepto5, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbBodegaR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel60)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane21, javax.swing.GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpBodegaRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBorrrarBodega, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCorregirBodega)
                    .addComponent(btnBodega))
                .addGap(23, 23, 23))
        );

        jTabbedPane1.addTab("R. BODEGA", jpBodegaR);

        jpGasolina.setBackground(new java.awt.Color(255, 255, 255));
        jpGasolina.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jpGasolinaComponentShown(evt);
            }
        });

        btnBorrarGasto3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnBorrarGasto3.setForeground(new java.awt.Color(0, 153, 51));
        btnBorrarGasto3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/menos.png"))); // NOI18N
        btnBorrarGasto3.setText("Quitar");
        btnBorrarGasto3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarGasto3ActionPerformed(evt);
            }
        });

        btnRegistrarGasolina.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnRegistrarGasolina.setForeground(new java.awt.Color(0, 153, 51));
        btnRegistrarGasolina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/mas.png"))); // NOI18N
        btnRegistrarGasolina.setText("Registrar");
        btnRegistrarGasolina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarGasolinaActionPerformed(evt);
            }
        });

        jLabel54.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(0, 102, 153));
        jLabel54.setText("FECHA");

        jLabel55.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(0, 102, 153));
        jLabel55.setText("VEHICULO");

        jLabel56.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(0, 102, 153));
        jLabel56.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel56.setText("IMPORTE");

        btnAnotarGasolina.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAnotarGasolina.setForeground(new java.awt.Color(0, 153, 51));
        btnAnotarGasolina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/list.png"))); // NOI18N
        btnAnotarGasolina.setText("Anotar");
        btnAnotarGasolina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnotarGasolinaActionPerformed(evt);
            }
        });

        txtImporteGasolina.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtImporteGasolinaKeyTyped(evt);
            }
        });

        jLabel57.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(0, 102, 153));
        jLabel57.setText("COMENTARIOS");

        jtTablaGasolina.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "FECHA", "VEHICULO", "COMENTARIOS", "IMPORTE"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane14.setViewportView(jtTablaGasolina);

        cmbVehiculo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jpGasolinaLayout = new javax.swing.GroupLayout(jpGasolina);
        jpGasolina.setLayout(jpGasolinaLayout);
        jpGasolinaLayout.setHorizontalGroup(
            jpGasolinaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpGasolinaLayout.createSequentialGroup()
                .addGroup(jpGasolinaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane14)
                    .addGroup(jpGasolinaLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(jpGasolinaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpGasolinaLayout.createSequentialGroup()
                                .addGroup(jpGasolinaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel54, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jdcFechaGasolina, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jpGasolinaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel55, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                                    .addComponent(cmbVehiculo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jpGasolinaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel57)
                                    .addComponent(txtComentariosGasolina, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jpGasolinaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel56, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtImporteGasolina, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnAnotarGasolina, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpGasolinaLayout.createSequentialGroup()
                                .addComponent(btnBorrarGasto3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnRegistrarGasolina)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jpGasolinaLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel54, jLabel55, jLabel56, jLabel57, jdcFechaGasolina, txtComentariosGasolina, txtImporteGasolina});

        jpGasolinaLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnBorrarGasto3, btnRegistrarGasolina});

        jpGasolinaLayout.setVerticalGroup(
            jpGasolinaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpGasolinaLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jpGasolinaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpGasolinaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jpGasolinaLayout.createSequentialGroup()
                            .addComponent(jLabel54)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jdcFechaGasolina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpGasolinaLayout.createSequentialGroup()
                            .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cmbVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jpGasolinaLayout.createSequentialGroup()
                        .addComponent(jLabel57)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtComentariosGasolina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpGasolinaLayout.createSequentialGroup()
                        .addComponent(jLabel56)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtImporteGasolina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnAnotarGasolina, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane14, javax.swing.GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpGasolinaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBorrarGasto3)
                    .addComponent(btnRegistrarGasolina))
                .addGap(24, 24, 24))
        );

        jTabbedPane1.addTab("GASOLINA", jpGasolina);

        jpGasolinaR.setBackground(new java.awt.Color(255, 255, 255));
        jpGasolinaR.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jpGasolinaRComponentShown(evt);
            }
        });

        jLabel108.setBackground(new java.awt.Color(0, 102, 153));
        jLabel108.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel108.setForeground(new java.awt.Color(255, 255, 255));
        jLabel108.setText("PERIODO TRABAJADO");
        jLabel108.setOpaque(true);

        jLabel109.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel109.setForeground(new java.awt.Color(0, 102, 153));
        jLabel109.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel109.setText("FECHA INICIO");

        jLabel110.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel110.setForeground(new java.awt.Color(0, 102, 153));
        jLabel110.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel110.setText("FECHA FIN");

        Concepto3.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        Concepto3.setForeground(new java.awt.Color(0, 102, 153));
        Concepto3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Concepto3.setText("CONCEPTO");

        jLabel111.setBackground(new java.awt.Color(0, 102, 153));
        jLabel111.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel111.setForeground(new java.awt.Color(255, 255, 255));
        jLabel111.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel111.setText("RESULTADOS");
        jLabel111.setOpaque(true);

        jtGasolinaResumen.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jtGasolinaResumen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtGasolinaResumenMouseClicked(evt);
            }
        });
        jScrollPane18.setViewportView(jtGasolinaResumen);

        cmbGasolinaResumen.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbGasolinaResumen.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbGasolinaResumenItemStateChanged(evt);
            }
        });

        btnBorrrarGasolinaGasto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/Cancel.png"))); // NOI18N
        btnBorrrarGasolinaGasto.setText("Borrar");
        btnBorrrarGasolinaGasto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrrarGasolinaGastoActionPerformed(evt);
            }
        });

        btnCorregirGastoGasolina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/correct.png"))); // NOI18N
        btnCorregirGastoGasolina.setText("Corregir");
        btnCorregirGastoGasolina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCorregirGastoGasolinaActionPerformed(evt);
            }
        });

        btnGasolina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/pdf.png"))); // NOI18N
        btnGasolina.setText("RESULTADOS");
        btnGasolina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGasolinaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpGasolinaRLayout = new javax.swing.GroupLayout(jpGasolinaR);
        jpGasolinaR.setLayout(jpGasolinaRLayout);
        jpGasolinaRLayout.setHorizontalGroup(
            jpGasolinaRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpGasolinaRLayout.createSequentialGroup()
                .addGroup(jpGasolinaRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpGasolinaRLayout.createSequentialGroup()
                        .addComponent(btnBorrrarGasolinaGasto, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGasolina)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCorregirGastoGasolina))
                    .addComponent(jLabel108, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpGasolinaRLayout.createSequentialGroup()
                        .addComponent(jLabel109, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jdcGasolinaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel110, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jdcGasolinaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Concepto3, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cmbGasolinaResumen, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel111, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 849, Short.MAX_VALUE))
                .addGap(16, 16, 16))
        );

        jpGasolinaRLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {Concepto3, jLabel109, jLabel110, jdcGasolinaFin, jdcGasolinaInicio});

        jpGasolinaRLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnBorrrarGasolinaGasto, btnCorregirGastoGasolina});

        jpGasolinaRLayout.setVerticalGroup(
            jpGasolinaRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpGasolinaRLayout.createSequentialGroup()
                .addComponent(jLabel108)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpGasolinaRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel109)
                    .addComponent(jdcGasolinaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel110)
                    .addComponent(jdcGasolinaFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Concepto3, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbGasolinaResumen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel111)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane18, javax.swing.GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpGasolinaRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBorrrarGasolinaGasto, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCorregirGastoGasolina)
                    .addComponent(btnGasolina))
                .addGap(15, 15, 15))
        );

        jTabbedPane1.addTab("R. GASOLINA", jpGasolinaR);

        jpMantenimiento.setBackground(new java.awt.Color(255, 255, 255));
        jpMantenimiento.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jpMantenimientoComponentShown(evt);
            }
        });

        btnBorrarGtoMantenimiento.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnBorrarGtoMantenimiento.setForeground(new java.awt.Color(0, 153, 51));
        btnBorrarGtoMantenimiento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/menos.png"))); // NOI18N
        btnBorrarGtoMantenimiento.setText("Quitar");
        btnBorrarGtoMantenimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarGtoMantenimientoActionPerformed(evt);
            }
        });

        btnRegistrarMantenimientoV.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnRegistrarMantenimientoV.setForeground(new java.awt.Color(0, 153, 51));
        btnRegistrarMantenimientoV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/mas.png"))); // NOI18N
        btnRegistrarMantenimientoV.setText("Registrar");
        btnRegistrarMantenimientoV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarMantenimientoVActionPerformed(evt);
            }
        });

        jLabel59.setBackground(new java.awt.Color(255, 255, 255));
        jLabel59.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(0, 102, 153));
        jLabel59.setText("FECHA");
        jLabel59.setOpaque(true);

        jLabel61.setBackground(new java.awt.Color(255, 255, 255));
        jLabel61.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(0, 102, 153));
        jLabel61.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel61.setText("IMPORTE");
        jLabel61.setOpaque(true);

        btnAnotarMantenimientoV.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAnotarMantenimientoV.setForeground(new java.awt.Color(0, 153, 51));
        btnAnotarMantenimientoV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/list.png"))); // NOI18N
        btnAnotarMantenimientoV.setText("Anotar");
        btnAnotarMantenimientoV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnotarMantenimientoVActionPerformed(evt);
            }
        });

        txtImporteMantenimientoV.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtImporteMantenimientoVKeyTyped(evt);
            }
        });

        jLabel62.setBackground(new java.awt.Color(255, 255, 255));
        jLabel62.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel62.setForeground(new java.awt.Color(0, 102, 153));
        jLabel62.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel62.setText("COMENTARIO");
        jLabel62.setOpaque(true);

        jtTablaMantenimientoV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "FECHA", "VEHICULO", "MANTENIMIENTO", "IMPORTE", "COMENTARIO"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane15.setViewportView(jtTablaMantenimientoV);

        jLabel114.setBackground(new java.awt.Color(255, 255, 255));
        jLabel114.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel114.setForeground(new java.awt.Color(0, 102, 153));
        jLabel114.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel114.setText("VEHICULO");
        jLabel114.setOpaque(true);

        jLabel115.setBackground(new java.awt.Color(255, 255, 255));
        jLabel115.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel115.setForeground(new java.awt.Color(0, 102, 153));
        jLabel115.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel115.setText("MATENIMIENTO");
        jLabel115.setOpaque(true);

        cmbVehiculoM.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmbMantenimiento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jpMantenimientoLayout = new javax.swing.GroupLayout(jpMantenimiento);
        jpMantenimiento.setLayout(jpMantenimientoLayout);
        jpMantenimientoLayout.setHorizontalGroup(
            jpMantenimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpMantenimientoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpMantenimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane15)
                    .addGroup(jpMantenimientoLayout.createSequentialGroup()
                        .addGroup(jpMantenimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel59, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jdcFechaMantenimientoV, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpMantenimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel114, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbVehiculoM, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpMantenimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel115, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbMantenimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpMantenimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel62)
                            .addComponent(txtComentarioMantenimientoV, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpMantenimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtImporteMantenimientoV, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
                            .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAnotarMantenimientoV, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 25, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpMantenimientoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnBorrarGtoMantenimiento)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRegistrarMantenimientoV, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jpMantenimientoLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel114, jLabel115, jLabel59, jLabel62, jdcFechaMantenimientoV, txtComentarioMantenimientoV, txtImporteMantenimientoV});

        jpMantenimientoLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnBorrarGtoMantenimiento, btnRegistrarMantenimientoV});

        jpMantenimientoLayout.setVerticalGroup(
            jpMantenimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpMantenimientoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpMantenimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpMantenimientoLayout.createSequentialGroup()
                        .addGroup(jpMantenimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel59)
                            .addComponent(jLabel114))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpMantenimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jdcFechaMantenimientoV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jpMantenimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cmbVehiculoM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cmbMantenimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jpMantenimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jpMantenimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpMantenimientoLayout.createSequentialGroup()
                                .addComponent(jLabel115)
                                .addGap(26, 26, 26))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpMantenimientoLayout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(btnAnotarMantenimientoV, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jpMantenimientoLayout.createSequentialGroup()
                            .addComponent(jLabel61)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtImporteMantenimientoV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpMantenimientoLayout.createSequentialGroup()
                            .addComponent(jLabel62)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtComentarioMantenimientoV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane15, javax.swing.GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jpMantenimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBorrarGtoMantenimiento)
                    .addComponent(btnRegistrarMantenimientoV))
                .addGap(23, 23, 23))
        );

        jTabbedPane1.addTab("MANTENIMIENTO", jpMantenimiento);

        jpMantenimientoR.setBackground(new java.awt.Color(255, 255, 255));
        jpMantenimientoR.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jpMantenimientoRComponentShown(evt);
            }
        });

        cmbVehiculoMResumen.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbVehiculoMResumen.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbVehiculoMResumenItemStateChanged(evt);
            }
        });

        btnBorrrarMantenimientoGasto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/Cancel.png"))); // NOI18N
        btnBorrrarMantenimientoGasto.setText("Borrar");
        btnBorrrarMantenimientoGasto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrrarMantenimientoGastoActionPerformed(evt);
            }
        });

        btnCorregirGastoMantenimiento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/correct.png"))); // NOI18N
        btnCorregirGastoMantenimiento.setText("Corregir");
        btnCorregirGastoMantenimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCorregirGastoMantenimientoActionPerformed(evt);
            }
        });

        jLabel116.setBackground(new java.awt.Color(0, 102, 153));
        jLabel116.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel116.setForeground(new java.awt.Color(255, 255, 255));
        jLabel116.setText("PERIODO TRABAJADO");
        jLabel116.setOpaque(true);

        jLabel117.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel117.setForeground(new java.awt.Color(0, 102, 153));
        jLabel117.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel117.setText("FECHA INICIO");

        jLabel118.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel118.setForeground(new java.awt.Color(0, 102, 153));
        jLabel118.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel118.setText("FECHA FIN");

        Concepto4.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        Concepto4.setForeground(new java.awt.Color(0, 102, 153));
        Concepto4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Concepto4.setText("VEHICULO");

        jLabel119.setBackground(new java.awt.Color(0, 102, 153));
        jLabel119.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel119.setForeground(new java.awt.Color(255, 255, 255));
        jLabel119.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel119.setText("RESULTADOS");
        jLabel119.setOpaque(true);

        jtMantenimientoResumen.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jtMantenimientoResumen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtMantenimientoResumenMouseClicked(evt);
            }
        });
        jScrollPane20.setViewportView(jtMantenimientoResumen);

        btnMantenimiento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/pdf.png"))); // NOI18N
        btnMantenimiento.setText("RESULTADOS");
        btnMantenimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMantenimientoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpMantenimientoRLayout = new javax.swing.GroupLayout(jpMantenimientoR);
        jpMantenimientoR.setLayout(jpMantenimientoRLayout);
        jpMantenimientoRLayout.setHorizontalGroup(
            jpMantenimientoRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpMantenimientoRLayout.createSequentialGroup()
                .addGroup(jpMantenimientoRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpMantenimientoRLayout.createSequentialGroup()
                        .addComponent(btnBorrrarMantenimientoGasto, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnMantenimiento)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCorregirGastoMantenimiento))
                    .addComponent(jLabel116, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpMantenimientoRLayout.createSequentialGroup()
                        .addComponent(jLabel117, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jdcMantenimientoInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel118, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jdcMantenimientoFin, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Concepto4, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cmbVehiculoMResumen, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel119, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 849, Short.MAX_VALUE))
                .addGap(16, 16, 16))
        );

        jpMantenimientoRLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cmbVehiculoMResumen, jdcMantenimientoFin, jdcMantenimientoInicio});

        jpMantenimientoRLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {Concepto4, jLabel117, jLabel118});

        jpMantenimientoRLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnBorrrarMantenimientoGasto, btnCorregirGastoMantenimiento});

        jpMantenimientoRLayout.setVerticalGroup(
            jpMantenimientoRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpMantenimientoRLayout.createSequentialGroup()
                .addComponent(jLabel116)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpMantenimientoRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel117)
                    .addComponent(jdcMantenimientoInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel118)
                    .addComponent(jdcMantenimientoFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Concepto4, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbVehiculoMResumen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel119)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane20, javax.swing.GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpMantenimientoRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBorrrarMantenimientoGasto, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCorregirGastoMantenimiento)
                    .addComponent(btnMantenimiento))
                .addGap(15, 15, 15))
        );

        jpMantenimientoRLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnBorrrarMantenimientoGasto, btnCorregirGastoMantenimiento});

        jTabbedPane1.addTab("R. MANTENIMIENTO", jpMantenimientoR);

        jpProveedores.setBackground(new java.awt.Color(255, 255, 255));
        jpProveedores.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jpProveedoresComponentShown(evt);
            }
        });

        btnAbonarProveedores.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAbonarProveedores.setForeground(new java.awt.Color(0, 153, 51));
        btnAbonarProveedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/dollar_1.png"))); // NOI18N
        btnAbonarProveedores.setText("Abonar");
        btnAbonarProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbonarProveedoresActionPerformed(evt);
            }
        });

        jLabel66.setBackground(new java.awt.Color(255, 255, 255));
        jLabel66.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(0, 102, 153));
        jLabel66.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel66.setText("ABONO");
        jLabel66.setOpaque(true);

        txtAbonoProveedores.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAbonoProveedoresKeyTyped(evt);
            }
        });

        jLabel67.setBackground(new java.awt.Color(255, 255, 255));
        jLabel67.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(0, 102, 153));
        jLabel67.setText("FORMA DE PAGO");
        jLabel67.setOpaque(true);

        cmbFormaDePago.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "EFECTIVO", "CHEQUE", "TRANSFERENCIA", "DEPOSITO", "CREDITO", "DEVOLUCION", "TRUEQUE", "CAMBIO DE MERCANCIA" }));

        jtExpedienteAsignar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "fecha", "folio", "importe General", "estado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtExpedienteAsignar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtExpedienteAsignarMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtExpedienteAsignar);

        jtFoliosAsignados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "fecha", "folio", "producto", "costo", "piezas", "importe", "forma de pago", "estado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtFoliosAsignados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtFoliosAsignadosMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(jtFoliosAsignados);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel65.setBackground(new java.awt.Color(255, 255, 255));
        jLabel65.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(0, 102, 153));
        jLabel65.setText("PROVEEDOR");
        jLabel65.setOpaque(true);

        cmbProveedorBuscar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel84.setBackground(new java.awt.Color(255, 255, 255));
        jLabel84.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel84.setForeground(new java.awt.Color(0, 102, 153));
        jLabel84.setText("INICIO");
        jLabel84.setOpaque(true);

        jLabel92.setBackground(new java.awt.Color(255, 255, 255));
        jLabel92.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel92.setForeground(new java.awt.Color(0, 102, 153));
        jLabel92.setText("FIN");
        jLabel92.setOpaque(true);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel65, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                    .addComponent(cmbProveedorBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel84)
                    .addComponent(jdcFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel92, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jdcFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cmbProveedorBuscar, jLabel65, jLabel84, jLabel92, jdcFechaFin, jdcFechaInicio});

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(jLabel84)
                            .addGap(6, 6, 6)
                            .addComponent(jdcFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(jLabel92)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jdcFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(cmbProveedorBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jpProveedoresLayout = new javax.swing.GroupLayout(jpProveedores);
        jpProveedores.setLayout(jpProveedoresLayout);
        jpProveedoresLayout.setHorizontalGroup(
            jpProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpProveedoresLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpProveedoresLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE)
                        .addGap(362, 362, 362))
                    .addGroup(jpProveedoresLayout.createSequentialGroup()
                        .addGroup(jpProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpProveedoresLayout.createSequentialGroup()
                                .addGroup(jpProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel66, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtAbonoProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jpProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel67, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmbFormaDePago, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnAbonarProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane6))
                        .addGap(7, 7, 7))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jpProveedoresLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cmbFormaDePago, jLabel66, jLabel67, txtAbonoProveedores});

        jpProveedoresLayout.setVerticalGroup(
            jpProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpProveedoresLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                .addGroup(jpProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpProveedoresLayout.createSequentialGroup()
                        .addGroup(jpProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel67)
                            .addComponent(jLabel66))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(txtAbonoProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbFormaDePago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jpProveedoresLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(btnAbonarProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27))
        );

        jTabbedPane1.addTab("PROVEEDORES", jpProveedores);

        jpProveedoresR.setBackground(new java.awt.Color(255, 255, 255));
        jpProveedoresR.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jpProveedoresRComponentShown(evt);
            }
        });

        jtExpedienteHistorial.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "fecha", "folio", "importe General", "estado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtExpedienteHistorial.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtExpedienteHistorialMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(jtExpedienteHistorial);

        jtAbonosHistorial.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Clave abono", "idConExp", "fecha", "folio", "cargo", "abono", "saldo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtAbonosHistorial.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtAbonosHistorialMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(jtAbonosHistorial);

        jtFoliosHistorial.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "fecha", "folio", "producto", "costo", "piezas", "importe", "forma de pago", "estado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtFoliosHistorial.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtFoliosHistorialMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(jtFoliosHistorial);

        jLabel97.setBackground(new java.awt.Color(255, 255, 255));
        jLabel97.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel97.setForeground(new java.awt.Color(153, 0, 0));
        jLabel97.setText("HISTORIAL DE ABONOS DE LA NOTA:");
        jLabel97.setOpaque(true);

        lblNotaHistorial.setBackground(new java.awt.Color(255, 255, 255));
        lblNotaHistorial.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lblNotaHistorial.setForeground(new java.awt.Color(0, 102, 153));
        lblNotaHistorial.setText("INICIO");
        lblNotaHistorial.setOpaque(true);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel93.setBackground(new java.awt.Color(255, 255, 255));
        jLabel93.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel93.setForeground(new java.awt.Color(0, 153, 0));
        jLabel93.setText("HISTORIAL DE ABONOS A COMPRAS QUE SE HICIERON A PROVEEDORES");
        jLabel93.setOpaque(true);

        jLabel94.setBackground(new java.awt.Color(255, 255, 255));
        jLabel94.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel94.setForeground(new java.awt.Color(0, 102, 153));
        jLabel94.setText("PROVEEDOR");
        jLabel94.setOpaque(true);

        cmbProveedorHistorial.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel95.setBackground(new java.awt.Color(255, 255, 255));
        jLabel95.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel95.setForeground(new java.awt.Color(0, 102, 153));
        jLabel95.setText("INICIO");
        jLabel95.setOpaque(true);

        jLabel96.setBackground(new java.awt.Color(255, 255, 255));
        jLabel96.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel96.setForeground(new java.awt.Color(0, 102, 153));
        jLabel96.setText("FIN");
        jLabel96.setOpaque(true);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel93, javax.swing.GroupLayout.PREFERRED_SIZE, 478, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel94, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbProveedorHistorial, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel95)
                            .addComponent(jdcInicioHistorial, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel96, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jdcFinHistorial, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 32, Short.MAX_VALUE))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cmbProveedorHistorial, jLabel94, jLabel95, jLabel96, jdcFinHistorial, jdcInicioHistorial});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel93, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel94, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel95)
                            .addComponent(jLabel96))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jdcInicioHistorial, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jdcFinHistorial, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(cmbProveedorHistorial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        btnProveedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/pdf.png"))); // NOI18N
        btnProveedores.setText("RESULTADOS");
        btnProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProveedoresActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpProveedoresRLayout = new javax.swing.GroupLayout(jpProveedoresR);
        jpProveedoresR.setLayout(jpProveedoresRLayout);
        jpProveedoresRLayout.setHorizontalGroup(
            jpProveedoresRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpProveedoresRLayout.createSequentialGroup()
                .addGroup(jpProveedoresRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpProveedoresRLayout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE))
                    .addGroup(jpProveedoresRLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel97, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblNotaHistorial, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane10)
                    .addComponent(jScrollPane9))
                .addGap(35, 35, 35))
            .addGroup(jpProveedoresRLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnProveedores)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpProveedoresRLayout.setVerticalGroup(
            jpProveedoresRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpProveedoresRLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpProveedoresRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpProveedoresRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel97, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNotaHistorial))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnProveedores))
        );

        jTabbedPane1.addTab("R. PROVEEDORES", jpProveedoresR);

        jpFinancieros.setBackground(new java.awt.Color(255, 255, 255));

        btnBorrarGasto6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnBorrarGasto6.setForeground(new java.awt.Color(0, 153, 51));
        btnBorrarGasto6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/menos.png"))); // NOI18N
        btnBorrarGasto6.setText("Quitar");
        btnBorrarGasto6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarGasto6ActionPerformed(evt);
            }
        });

        btnRegistrarSueldoPrestamos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnRegistrarSueldoPrestamos.setForeground(new java.awt.Color(0, 153, 51));
        btnRegistrarSueldoPrestamos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/mas.png"))); // NOI18N
        btnRegistrarSueldoPrestamos.setText("Registrar");
        btnRegistrarSueldoPrestamos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarSueldoPrestamosActionPerformed(evt);
            }
        });

        cmbConceptoSueldoPrestamo.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        cmbConceptoSueldoPrestamo.setForeground(new java.awt.Color(0, 102, 153));
        cmbConceptoSueldoPrestamo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "NOMINA", "PRESTAMO", "SUELDO", "DEPOSITO", " " }));

        jLabel69.setBackground(new java.awt.Color(255, 255, 255));
        jLabel69.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel69.setForeground(new java.awt.Color(0, 102, 153));
        jLabel69.setText("FECHA");
        jLabel69.setOpaque(true);

        jLabel70.setBackground(new java.awt.Color(255, 255, 255));
        jLabel70.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(0, 102, 153));
        jLabel70.setText("CONCEPTO");
        jLabel70.setOpaque(true);

        jLabel71.setBackground(new java.awt.Color(255, 255, 255));
        jLabel71.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel71.setForeground(new java.awt.Color(0, 102, 153));
        jLabel71.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel71.setText("IMPORTE");
        jLabel71.setOpaque(true);

        btnAnotarSueldosPrestamos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAnotarSueldosPrestamos.setForeground(new java.awt.Color(0, 153, 51));
        btnAnotarSueldosPrestamos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/list.png"))); // NOI18N
        btnAnotarSueldosPrestamos.setText("Anotar");
        btnAnotarSueldosPrestamos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnotarSueldosPrestamosActionPerformed(evt);
            }
        });

        txtImporteSueldoPrestamos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtImporteSueldoPrestamosKeyTyped(evt);
            }
        });

        jLabel72.setBackground(new java.awt.Color(255, 255, 255));
        jLabel72.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel72.setForeground(new java.awt.Color(0, 102, 153));
        jLabel72.setText("COMENTARIOS");
        jLabel72.setOpaque(true);

        jtGastosFinancieros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "FECHA", "CONCEPTO", "IMPORTE", "COMENTARIOS", "ACREEDOR"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane17.setViewportView(jtGastosFinancieros);

        jLabel79.setBackground(new java.awt.Color(0, 102, 153));
        jLabel79.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel79.setForeground(new java.awt.Color(255, 255, 255));
        jLabel79.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel79.setText("EMPLEADO");
        jLabel79.setOpaque(true);

        txtEmpleado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEmpleadoKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jpFinancierosLayout = new javax.swing.GroupLayout(jpFinancieros);
        jpFinancieros.setLayout(jpFinancierosLayout);
        jpFinancierosLayout.setHorizontalGroup(
            jpFinancierosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpFinancierosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpFinancierosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpFinancierosLayout.createSequentialGroup()
                        .addComponent(btnBorrarGasto6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRegistrarSueldoPrestamos, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpFinancierosLayout.createSequentialGroup()
                        .addGroup(jpFinancierosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpFinancierosLayout.createSequentialGroup()
                                .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtEmpleado))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpFinancierosLayout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(jpFinancierosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel69, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jdcFechaSueldosPrestamos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jpFinancierosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                    .addComponent(cmbConceptoSueldoPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel70, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jpFinancierosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel71, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtImporteSueldoPrestamos, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpFinancierosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel72)
                            .addGroup(jpFinancierosLayout.createSequentialGroup()
                                .addComponent(txtComentariosSueldosPrestamos, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAnotarSueldosPrestamos))))
                    .addComponent(jScrollPane17))
                .addGap(135, 135, 135))
        );

        jpFinancierosLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnAnotarSueldosPrestamos, cmbConceptoSueldoPrestamo, jLabel69, jLabel70, jLabel71, jLabel72, jdcFechaSueldosPrestamos, txtComentariosSueldosPrestamos, txtImporteSueldoPrestamos});

        jpFinancierosLayout.setVerticalGroup(
            jpFinancierosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpFinancierosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpFinancierosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpFinancierosLayout.createSequentialGroup()
                        .addComponent(jLabel69)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jdcFechaSueldosPrestamos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpFinancierosLayout.createSequentialGroup()
                        .addGroup(jpFinancierosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel71)
                            .addComponent(jLabel72))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpFinancierosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(txtComentariosSueldosPrestamos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtImporteSueldoPrestamos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbConceptoSueldoPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAnotarSueldosPrestamos, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpFinancierosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel79)
                    .addComponent(txtEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane17, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpFinancierosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBorrarGasto6)
                    .addComponent(btnRegistrarSueldoPrestamos))
                .addGap(14, 14, 14))
        );

        jTabbedPane1.addTab("FINANCIEROS", jpFinancieros);

        jpFinancierosR.setBackground(new java.awt.Color(255, 255, 255));
        jpFinancierosR.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jpFinancierosRComponentShown(evt);
            }
        });

        jtFinancierosResumen.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jtFinancierosResumen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtFinancierosResumenMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(jtFinancierosResumen);

        jLabel39.setBackground(new java.awt.Color(0, 102, 153));
        jLabel39.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(255, 255, 255));
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel39.setText("RESULTADOS");
        jLabel39.setOpaque(true);

        btnCorregirConcepto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnCorregirConcepto.setForeground(new java.awt.Color(0, 153, 51));
        btnCorregirConcepto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/correct.png"))); // NOI18N
        btnCorregirConcepto.setText("Corregir");
        btnCorregirConcepto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCorregirConceptoActionPerformed(evt);
            }
        });

        btnEliminarConcepto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/delete.png"))); // NOI18N
        btnEliminarConcepto.setText("Eliminar");
        btnEliminarConcepto.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnEliminarConcepto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarConceptoActionPerformed(evt);
            }
        });

        Concepto.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        Concepto.setForeground(new java.awt.Color(0, 102, 153));
        Concepto.setText("CONCEPTO");

        cmbConceptoFinanciero.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        cmbConceptoFinanciero.setForeground(new java.awt.Color(0, 102, 153));
        cmbConceptoFinanciero.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "NOMINA", "PRESTAMO", "SUELDO", "DEPOSITO", " " }));
        cmbConceptoFinanciero.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbConceptoFinancieroItemStateChanged(evt);
            }
        });

        jLabel37.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(0, 102, 153));
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel37.setText("FECHA FIN");

        jLabel53.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(0, 102, 153));
        jLabel53.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel53.setText("FECHA INICIO");

        jLabel58.setBackground(new java.awt.Color(0, 102, 153));
        jLabel58.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(255, 255, 255));
        jLabel58.setText("PERIODO TRABAJADO");
        jLabel58.setOpaque(true);

        btnFinancieros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/pdf.png"))); // NOI18N
        btnFinancieros.setText("RESULTADOS");
        btnFinancieros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinancierosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpFinancierosRLayout = new javax.swing.GroupLayout(jpFinancierosR);
        jpFinancierosR.setLayout(jpFinancierosRLayout);
        jpFinancierosRLayout.setHorizontalGroup(
            jpFinancierosRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpFinancierosRLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpFinancierosRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpFinancierosRLayout.createSequentialGroup()
                        .addComponent(Concepto, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbConceptoFinanciero, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jdcInicioFinancieroResumen, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jdcFinFinancieroResumen, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel39, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jpFinancierosRLayout.createSequentialGroup()
                        .addComponent(btnEliminarConcepto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnFinancieros)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCorregirConcepto))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 845, Short.MAX_VALUE)
                    .addComponent(jLabel58, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jpFinancierosRLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel37, jLabel53, jdcFinFinancieroResumen, jdcInicioFinancieroResumen});

        jpFinancierosRLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnCorregirConcepto, btnEliminarConcepto});

        jpFinancierosRLayout.setVerticalGroup(
            jpFinancierosRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpFinancierosRLayout.createSequentialGroup()
                .addComponent(jLabel58)
                .addGap(17, 17, 17)
                .addGroup(jpFinancierosRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpFinancierosRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Concepto, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmbConceptoFinanciero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpFinancierosRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(jLabel53)
                        .addComponent(jdcInicioFinancieroResumen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel37)
                        .addComponent(jdcFinFinancieroResumen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel39)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpFinancierosRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCorregirConcepto, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminarConcepto, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFinancieros))
                .addContainerGap())
        );

        jTabbedPane1.addTab("R. FINANCIEROS", jpFinancierosR);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtImporteSueldoPrestamosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtImporteSueldoPrestamosKeyTyped
        char key = evt.getKeyChar();
        if (((key < '0') || (key > '9')) && (key != evt.VK_BACK_SPACE)
                && (key != '.')) {
            evt.consume();
        }
        if (key == '.' && txtImporteSueldoPrestamos.getText().contains(".")) {
            evt.consume();
            JOptionPane.showMessageDialog(this, "Solo se permite un punto "
                    + "este campo", "Validacion decimal", 0);
        }
    }//GEN-LAST:event_txtImporteSueldoPrestamosKeyTyped

    private void btnAnotarSueldosPrestamosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnotarSueldosPrestamosActionPerformed
        anotarConcepto(jtGastosFinancieros,
                FechaHerramienta.formatoYMD(jdcFechaSueldosPrestamos.getDate()),
                cmbConceptoSueldoPrestamo.getSelectedItem().toString(),
                txtImporteSueldoPrestamos.getText(),
                txtComentariosSueldosPrestamos.getText(),
                txtEmpleado.getText());
    }//GEN-LAST:event_btnAnotarSueldosPrestamosActionPerformed

    private void btnRegistrarSueldoPrestamosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarSueldoPrestamosActionPerformed
        guardarGastosFinancieros(jtGastosFinancieros);
        limpiarCampos();
    }//GEN-LAST:event_btnRegistrarSueldoPrestamosActionPerformed
    private void guardarGastosFinancieros(JTable tabla) {
        AccesoBD controlBD = new AccesoBD();
        String fecha = "";
        for (int i = 0; i < tabla.getRowCount(); i++) {
            try {
                pojos.Gastosfinancieros conceptoGasto = new pojos.Gastosfinancieros();
                //FECHA
                fecha = tabla.getValueAt(i, 0).toString();
                conceptoGasto.setFecha(FechaHerramienta.convertirStringEnDate(fecha));
                //CONCEPTO
                conceptoGasto.setConcepto(tabla.getValueAt(i, 1).toString());
                //IMPORTE
                conceptoGasto.setImporte(Double.parseDouble(tabla.getValueAt(i, 2).toString()));
                //COMENTARIO
                conceptoGasto.setComentarios(tabla.getValueAt(i, 3).toString());
                //ACREEDOR
                conceptoGasto.setAcreedor(tabla.getValueAt(i, 4).toString());

                controlBD.add(conceptoGasto);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e, "Error", 0);
            }
        }
        limpiarCampos();
        limpiarTablas(tabla);
        JOptionPane.showMessageDialog(this, "Gastos registrados correctamente", "Datos registrados", 1);        // TODO add your handling code here:
    }

    private void btnBorrarGasto6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarGasto6ActionPerformed
        quitarFila(jtGastosFinancieros);
    }//GEN-LAST:event_btnBorrarGasto6ActionPerformed

    private void txtAbonoProveedoresKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAbonoProveedoresKeyTyped
        char key = evt.getKeyChar();
        if (((key < '0') || (key > '9')) && (key != evt.VK_BACK_SPACE)
                && (key != '.')) {
            evt.consume();
        }
        if (key == '.' && txtAbonoProveedores.getText().contains(".")) {
            evt.consume();
            JOptionPane.showMessageDialog(this, "Solo se permite un punto "
                    + "este campo", "Validacion decimal", 0);
        }
    }//GEN-LAST:event_txtAbonoProveedoresKeyTyped

    private void btnAbonarProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbonarProveedoresActionPerformed

// TODO add your handling code here:
        if (jtFoliosAsignados.getSelectedRow() != -1) {
            //REGISTRAR EL ABONO
            AccesoBD acceso = new AccesoBD();
            Abonoproveedor abono = new Abonoproveedor();
            //ID EXPEDIENTE
            int id = Integer.parseInt(jtFoliosAsignados.getValueAt(jtFoliosAsignados.getSelectedRow(), 0).toString());
            //VALIDACION DEL ABONO
            String query = "FROM Contenidoexpediente ce WHERE ce.idContenidoExpediente="
                    + id
                    + " ORDER BY ce.folio";
            Contenidoexpediente contenido = (Contenidoexpediente) acceso.select(query).get(0);
            double abonoAnterior = contenido.getAbono();
            double cargoActual = contenido.getImporte();
            double abonado = Double.parseDouble(txtAbonoProveedores.getText());
            double nuevoAbono = 0;
            if ((abonado + abonoAnterior) <= cargoActual) {
                nuevoAbono = abonado + abonoAnterior;

                ///////////////////////
                abono.setIdContenidoExpediente(id);
                //FECHA
                String fecha = jtFoliosAsignados.getValueAt(jtFoliosAsignados.getSelectedRow(), 1).toString();
                abono.setFecha(herramienta.FechaHerramienta.convertirStringEnDate(fecha));
                //FOLIO
                String folio = jtFoliosAsignados.getValueAt(jtFoliosAsignados.getSelectedRow(), 2).toString();
                abono.setFolio(folio);
                //CARGO
                double cargo = Double.parseDouble(jtFoliosAsignados.getValueAt(jtFoliosAsignados.getSelectedRow(), 4).toString());
                abono.setCargo(cargo);
                //ABONO
                abono.setAbono(nuevoAbono);
                //SALDO
                double saldo = cargo - nuevoAbono;
                abono.setSaldo(saldo);
                abono.setFormaDePago(cmbFormaDePago.getSelectedItem().toString());
                //abono.setEstatus();
                acceso.add(abono);

                //Actualizo el expediente
                contenido.setAbono(nuevoAbono);
                contenido.setSaldo(saldo);
                if ((abonado + abonoAnterior) == cargoActual) {
                    contenido.setEstadoIndividualFolio("PAGADO");
                }
                acceso.Update(contenido);

                String Query = "FROM Contenidoexpediente ce WHERE ce.idExpediente="
                        + idExpediente
                        + " ORDER BY ce.folio";
                cargaTabla(jtFoliosAsignados, Query, "ContenidoExpediente2", 1);
                limpiarCampos();

                //REINICIAR VALORES
                abonoAnterior = 0;
                cargoActual = 0;
                abonado = 0;
                nuevoAbono = 0;
            }
        }
    }//GEN-LAST:event_btnAbonarProveedoresActionPerformed

    private void txtImporteMantenimientoVKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtImporteMantenimientoVKeyTyped
        char key = evt.getKeyChar();
        if (((key < '0') || (key > '9')) && (key != evt.VK_BACK_SPACE)
                && (key != '.')) {
            evt.consume();
        }
        if (key == '.' && txtImporteMantenimientoV.getText().contains(".")) {
            evt.consume();
            JOptionPane.showMessageDialog(this, "Solo se permite un punto "
                    + "este campo", "Validacion decimal", 0);
        }
    }//GEN-LAST:event_txtImporteMantenimientoVKeyTyped

    private void btnAnotarMantenimientoVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnotarMantenimientoVActionPerformed
        anotarConcepto(
                jtTablaMantenimientoV,
                FechaHerramienta.formatoYMD(jdcFechaMantenimientoV.getDate()),
                cmbVehiculoM.getSelectedItem().toString(),
                cmbMantenimiento.getSelectedItem().toString(),
                txtImporteMantenimientoV.getText(),
                txtComentarioMantenimientoV.getText());
    }//GEN-LAST:event_btnAnotarMantenimientoVActionPerformed

    private void btnRegistrarMantenimientoVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarMantenimientoVActionPerformed
        guardarGastosMantenimiento(jtTablaMantenimientoV);
        limpiarCampos();
    }//GEN-LAST:event_btnRegistrarMantenimientoVActionPerformed

    private void btnBorrarGtoMantenimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarGtoMantenimientoActionPerformed
        quitarFila(jtTablaMantenimientoV);
    }//GEN-LAST:event_btnBorrarGtoMantenimientoActionPerformed

    private void txtImporteGasolinaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtImporteGasolinaKeyTyped
        char key = evt.getKeyChar();
        if (((key < '0') || (key > '9')) && (key != evt.VK_BACK_SPACE)
                && (key != '.')) {
            evt.consume();
        }
        if (key == '.' && txtImporteGasolina.getText().contains(".")) {
            evt.consume();
            JOptionPane.showMessageDialog(this, "Solo se permite un punto "
                    + "este campo", "Validacion decimal", 0);
        }
    }//GEN-LAST:event_txtImporteGasolinaKeyTyped

    private void btnAnotarGasolinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnotarGasolinaActionPerformed
        anotarConcepto(
                jtTablaGasolina,
                FechaHerramienta.formatoYMD(jdcFechaGasolina.getDate()),
                cmbVehiculo.getSelectedItem().toString(),
                txtComentariosGasolina.getText(),
                txtImporteGasolina.getText()
        );
    }//GEN-LAST:event_btnAnotarGasolinaActionPerformed

    private void btnRegistrarGasolinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarGasolinaActionPerformed
        guardarGastosGasolina(jtTablaGasolina);
        limpiarCampos();
    }//GEN-LAST:event_btnRegistrarGasolinaActionPerformed

    private void btnBorrarGasto3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarGasto3ActionPerformed
        quitarFila(jtTablaGasolina);
    }//GEN-LAST:event_btnBorrarGasto3ActionPerformed

    private void txtImporteOficinaBodegaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtImporteOficinaBodegaKeyTyped
        char key = evt.getKeyChar();
        if (((key < '0') || (key > '9')) && (key != evt.VK_BACK_SPACE)
                && (key != '.')) {
            evt.consume();
        }
        if (key == '.' && txtImporteOficinaBodega.getText().contains(".")) {
            evt.consume();
            JOptionPane.showMessageDialog(this, "Solo se permite un punto "
                    + "este campo", "Validacion decimal", 0);
        }
    }//GEN-LAST:event_txtImporteOficinaBodegaKeyTyped

    private void btnAnotarOficinaBodegaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnotarOficinaBodegaActionPerformed
        anotarConcepto(jtBodega,
                FechaHerramienta.formatoYMD(jdcFechaOficinaBodega.getDate()),
                "BODEGA",
                cmbConceptoOficinaBodega.getSelectedItem().toString(),
                txtImporteOficinaBodega.getText(),
                txtComentariosOficinaBodega.getText());
    }//GEN-LAST:event_btnAnotarOficinaBodegaActionPerformed

    private void btnRegistrarOficinaBodegaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarOficinaBodegaActionPerformed
        guardarGastosBodega(jtBodega);
        limpiarCampos();
    }//GEN-LAST:event_btnRegistrarOficinaBodegaActionPerformed

    private void btnBorrarGasto2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarGasto2ActionPerformed
        quitarFila(jtBodega);
    }//GEN-LAST:event_btnBorrarGasto2ActionPerformed

    private void txtImporteGastosPersonalesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtImporteGastosPersonalesKeyTyped
        char key = evt.getKeyChar();
        if (((key < '0') || (key > '9')) && (key != evt.VK_BACK_SPACE)
                && (key != '.')) {
            evt.consume();
        }
        if (key == '.' && txtImporteGastosPersonales.getText().contains(".")) {
            evt.consume();
            JOptionPane.showMessageDialog(this, "Solo se permite un punto "
                    + "este campo", "Validacion decimal", 0);
        }
    }//GEN-LAST:event_txtImporteGastosPersonalesKeyTyped

    private void btnAnotarGastosPersonalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnotarGastosPersonalesActionPerformed
        anotarConcepto(jtPersonales,
                FechaHerramienta.formatoYMD(jdcFechaGastosPersonales.getDate()),
                cmbConceptoGastosPersonales.getSelectedItem().toString(),
                txtImporteGastosPersonales.getText(),
                txtComentarioGastosPersonales.getText());
    }//GEN-LAST:event_btnAnotarGastosPersonalesActionPerformed

    private void btnRegistrarGastosPersonalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarGastosPersonalesActionPerformed
        guardarGastosPersonales(jtPersonales);
        limpiarCampos();
    }//GEN-LAST:event_btnRegistrarGastosPersonalesActionPerformed

    private void btnBorrarGasto1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarGasto1ActionPerformed
        quitarFila(jtPersonales);
    }//GEN-LAST:event_btnBorrarGasto1ActionPerformed

    private void btnRegistrarLocalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarLocalesActionPerformed
        guardarGastosLocales(jtLocales);
        limpiarCampos();
    }//GEN-LAST:event_btnRegistrarLocalesActionPerformed

    private void btnBorrarGastoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarGastoActionPerformed
        quitarFila(jtLocales);
    }//GEN-LAST:event_btnBorrarGastoActionPerformed
    private void anotarConcepto(JTable tabla, String fecha, String concepto, String subconcepto, String importe, String comentario) {
        if (!fecha.equals("") && !concepto.equals("") && !subconcepto.equals("") && !importe.equals("") && !comentario.equals("")) {
            DefaultTableModel model = (DefaultTableModel) tabla.getModel();
            try {
                model.addRow(new String[]{
                    fecha,
                    concepto,
                    subconcepto,
                    importe,
                    comentario
                });
                tabla.setModel(model);
                limpiarCampos();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e, "Error", 0);
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Falto de llenar un campo");
        }
    }

    private void anotarConceptoProveedor(JTable tabla, String fecha, String folio, String proveedor, String importe, String noPiezas, String formaDePago) {
        if (!fecha.equals("") && !proveedor.equals("") && !folio.equals("") && !importe.equals("") && !formaDePago.equals("")) {
            DefaultTableModel model = (DefaultTableModel) tabla.getModel();
            try {
                model.addRow(new String[]{
                    fecha,
                    folio,
                    proveedor,
                    importe,
                    noPiezas,
                    formaDePago
                });
                tabla.setModel(model);
                limpiarCampos();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e, "Error", 0);
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Falto de llenar un campo");
        }
    }

    private void anotarConcepto(JTable tabla, String fecha, String concepto, String importe, String comentario) {
        if (!fecha.equals("") && !concepto.equals("") && !importe.equals("") && !comentario.equals("")) {
            DefaultTableModel model = (DefaultTableModel) tabla.getModel();
            try {
                model.addRow(new String[]{
                    fecha,
                    concepto,
                    importe,
                    comentario
                });
                tabla.setModel(model);
                limpiarCampos();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e, "Error", 0);
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Falto de llenar un campo");
        }
    }

    private void btnAnotarLocalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnotarLocalesActionPerformed
        anotarConcepto(jtLocales,
                FechaHerramienta.formatoYMD(jdcFechaLocal.getDate()),
                cmbConceptoLocales.getSelectedItem().toString(),
                cmbSubConceptoLocales.getSelectedItem().toString(),
                txtImporteLocales.getText(),
                txtComentarioLocales.getText());
    }//GEN-LAST:event_btnAnotarLocalesActionPerformed
    private void salir() {
        try {
            this.setClosed(true);
            System.gc();
        } catch (PropertyVetoException ex) {
            this.dispose();
            System.gc();
        }
    }
    private void txtImporteLocalesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtImporteLocalesKeyTyped
        char key = evt.getKeyChar();
        if (((key < '0') || (key > '9')) && (key != evt.VK_BACK_SPACE)
                && (key != '.')) {
            evt.consume();
        }
        if (key == '.' && txtImporteLocales.getText().contains(".")) {
            evt.consume();
            JOptionPane.showMessageDialog(this, "Solo se permite un punto "
                    + "este campo", "Validacion decimal", 0);
        }
    }//GEN-LAST:event_txtImporteLocalesKeyTyped

    private void txtEmpleadoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmpleadoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmpleadoKeyTyped

    private void btnCorregirConceptoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCorregirConceptoActionPerformed
        if (jtFinancierosResumen.getSelectedRow() != -1) {
            String clave = jtFinancierosResumen.getValueAt(jtFinancierosResumen.getSelectedRow(), 0).toString();
            try {
                CorregirGastoFinanciero corregirGastoGeneral = new CorregirGastoFinanciero(clave);
                if (exist(corregirGastoGeneral) == false) {
                    CPanel.desktop.add(corregirGastoGeneral);
                    corregirGastoGeneral.setVisible(true);
                    corregirGastoGeneral.setLocation((CPanel.desktop.getWidth() - corregirGastoGeneral.getWidth()) / 2,
                            (CPanel.desktop.getHeight() - corregirGastoGeneral.getHeight()) / 2);
                } else {
                    corregirGastoGeneral.dispose();

                }
            } catch (Exception ex) {

            }
        }

    }//GEN-LAST:event_btnCorregirConceptoActionPerformed
    public boolean exist(JInternalFrame frame) throws Exception {

        try {
            JInternalFrame iframes[] = CPanel.desktop.getAllFrames();
            for (int i = 0; i < iframes.length; i++) {
                if (iframes[i].getTitle().equals(frame.getTitle())) {
                    iframes[i].moveToFront();
                    iframes[i].setSelected(true);
                    iframes[i].setLocation((CPanel.desktop.getWidth() - iframes[i].getWidth()) / 2,
                            (CPanel.desktop.getHeight() - iframes[i].getHeight()) / 2);
                    return true;
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return false;
    }

    private void btnEliminarConceptoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarConceptoActionPerformed
        // TODO add your handling code here:
        if (jtFinancierosResumen.getSelectedRow() != -1) {
            int respuesta = JOptionPane.showConfirmDialog(this, "¿Desea eliminar este gasto?", "Confirmar eliminación", 0, 3);
            if (respuesta == 0) {
                try {
                    AccesoBD acceso = new AccesoBD();
                    int clave = (int) jtFinancierosResumen.getValueAt(jtFinancierosResumen.getSelectedRow(), 0);
                    String HQL = "From Gastosfinancieros gl WHERE gl.idGastosFinancieros = '" + clave + "'";
                    Gastosfinancieros concepto = (Gastosfinancieros) acceso.select(HQL).get(0);
                    acceso.delete(concepto);

                    HQL = "From Gastosfinancieros gl";
                    cargaTabla(jtFinancierosResumen, HQL, "Gastofinanciero", 1);
                    JOptionPane.showMessageDialog(this, "Gasto eliminado correctamente", "Cancelado", 1);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Ha ocurrido un error: " + e, "Error", 0);
                    System.gc();
                }
            }
        }
    }//GEN-LAST:event_btnEliminarConceptoActionPerformed

    private void jpFinancierosRComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jpFinancierosRComponentShown

        String fechaInicio = FechaHerramienta.formatoYMD(jdcInicioFinancieroResumen.getDate());
        String fechaFin = FechaHerramienta.formatoYMD(jdcFinFinancieroResumen.getDate());

        String Query = "FROM Gastosfinancieros gf WHERE gf.concepto= '"
                + cmbConceptoFinanciero.getSelectedItem().toString() + "' AND gf.fecha BETWEEN '"
                + fechaInicio
                + "' AND '"
                + fechaFin + "'"
                + " ORDER BY gf.fecha";
        cargaTabla(jtFinancierosResumen, Query, "Gastofinanciero", 1);


    }//GEN-LAST:event_jpFinancierosRComponentShown

    private void jpPersonalesComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jpPersonalesComponentShown
        String query = "FROM Linea l ORDER BY l.nombre";
        llenarCombo(cmbConceptoGastosPersonales, query, "concepto", false);

    }//GEN-LAST:event_jpPersonalesComponentShown

    private void jpBodegaComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jpBodegaComponentShown
        String query = "FROM Linea l ORDER BY l.nombre";
        llenarCombo(cmbConceptoOficinaBodega, query, "concepto", false);
    }//GEN-LAST:event_jpBodegaComponentShown

    private void jpGasolinaComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jpGasolinaComponentShown
        String query = "FROM Vehiculo v ORDER BY v.vehiculo";
        llenarCombo(cmbVehiculo, query, "vehiculo", false);
    }//GEN-LAST:event_jpGasolinaComponentShown

    private void jpMantenimientoComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jpMantenimientoComponentShown
        String query = "FROM Mantenimiento m ORDER BY m.mantenimiento";
        llenarCombo(cmbMantenimiento, query, "mantenimiento", false);
        query = "FROM Vehiculo v ORDER BY v.vehiculo";
        llenarCombo(cmbVehiculoM, query, "vehiculo", false);
    }//GEN-LAST:event_jpMantenimientoComponentShown

    private void cmbConceptoLocalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbConceptoLocalesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbConceptoLocalesActionPerformed

    private void jpLocalesComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jpLocalesComponentShown
        String query = "FROM Sucursal s ORDER BY s.nombre";
        llenarCombo(cmbConceptoLocales, query, "local", true);
        query = "FROM Linea l ORDER BY l.nombre";
        llenarCombo(cmbSubConceptoLocales, query, "concepto", false);
    }//GEN-LAST:event_jpLocalesComponentShown

    private void jpProveedoresComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jpProveedoresComponentShown
//        jdcFechaInicio.setDate(new Date());
//        jdcFechaFin.setDate(new Date());
        String query = "FROM Proveedores p ORDER BY p.nombre";
        llenarCombo(cmbProveedorBuscar, query, "proveedor", false);
//        query = "FROM Expediente e WHERE e.nombreProveedor='"
//                + cmbProveedorBuscar.getSelectedItem().toString()
//                + "' AND e.fechaApertura BETWEEN '"
//                + fechaInicio
//                + "' AND '"
//                + fechaFin + "'"
//                + " ORDER BY e.nombreProveedor";
//        cargaTabla(jtExpedienteAsignar, query, "Expediente", 2);
    }//GEN-LAST:event_jpProveedoresComponentShown

    private void jtFoliosAsignadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtFoliosAsignadosMouseClicked
        if (evt.getClickCount() == 2) {
            String Query = "FROM Contenidoexpediente ce WHERE ce.idExpediente="
                    + idExpediente
                    + " ORDER BY ce.folio";
            cargaTabla(jtFoliosAsignados, Query, "ContenidoExpediente2", 1);
        }
    }//GEN-LAST:event_jtFoliosAsignadosMouseClicked

    private void jtExpedienteAsignarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtExpedienteAsignarMouseClicked
        //CARGA LOS FOLIOS
        idExpediente = (int) jtExpedienteAsignar.getValueAt(jtExpedienteAsignar.getSelectedRow(), 0);
        //CARGA LOS PROVEEDORES
        if (evt.getClickCount() == 2) {
            if (!cmbProveedorBuscar.getSelectedItem().toString().equals("Todos")) {
                String Query = "FROM Contenidoexpediente ce WHERE ce.idExpediente="
                        + idExpediente
                        + " ORDER BY ce.folio";
                cargaTabla(jtFoliosAsignados, Query, "ContenidoExpediente2", 1);
                Query = "FROM Expediente e WHERE e.nombreProveedor='"
                        + cmbProveedorBuscar.getSelectedItem().toString()
                        + "'ORDER BY e.nombreProveedor";
                cargaTabla(jtExpedienteAsignar, Query, "Expediente", 2);

            } else {

                String Query = "FROM Contenidoexpediente ce WHERE ce.idExpediente="
                        + idExpediente
                        + " ORDER BY ce.folio";
                cargaTabla(jtFoliosAsignados, Query, "ContenidoExpediente2", 1);
                Query = "FROM Expediente e ORDER BY e.nombreProveedor";
                cargaTabla(jtExpedienteAsignar, Query, "Expediente", 2);

            }
        }

    }//GEN-LAST:event_jtExpedienteAsignarMouseClicked

    private void jtExpedienteHistorialMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtExpedienteHistorialMouseClicked
        //CARGA LOS FOLIOS
        idExpediente = (int) jtExpedienteHistorial.getValueAt(jtExpedienteHistorial.getSelectedRow(), 0);
        //CARGA LOS PROVEEDORES
        if (evt.getClickCount() == 2) {
            if (!cmbProveedorHistorial.getSelectedItem().toString().equals("Todos")) {
                lblNotaHistorial.setText("");
                String Query = "FROM Contenidoexpediente ce WHERE ce.idExpediente= -1"
                        + " ORDER BY ce.folio";
                cargaTabla(jtFoliosHistorial, Query, "ContenidoExpediente", 3);
                Query = "FROM Contenidoexpediente ce WHERE ce.idExpediente="
                        + idExpediente
                        + " ORDER BY ce.folio";
                cargaTabla(jtFoliosHistorial, Query, "ContenidoExpediente2", 1);
                Query = "FROM Expediente e WHERE e.nombreProveedor='"
                        + cmbProveedorHistorial.getSelectedItem().toString()
                        + "'ORDER BY e.nombreProveedor";
                cargaTabla(jtExpedienteHistorial, Query, "Expediente", 2);

            } else {

                String Query = "FROM Contenidoexpediente ce WHERE ce.idExpediente="
                        + idExpediente
                        + " ORDER BY ce.folio";
                cargaTabla(jtFoliosHistorial, Query, "ContenidoExpediente2", 1);
                Query = "FROM Expediente e ORDER BY e.nombreProveedor";
                cargaTabla(jtExpedienteHistorial, Query, "Expediente", 2);

            }
        }

    }//GEN-LAST:event_jtExpedienteHistorialMouseClicked

    private void jtAbonosHistorialMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtAbonosHistorialMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jtAbonosHistorialMouseClicked

    private void jtFoliosHistorialMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtFoliosHistorialMouseClicked
        //CARGA LOS FOLIOS
        int idContenidoExpediente = Integer.parseInt(jtFoliosHistorial.getValueAt(jtFoliosHistorial.getSelectedRow(), 0).toString());
        lblNotaHistorial.setText(jtFoliosHistorial.getValueAt(jtFoliosHistorial.getSelectedRow(), 2).toString());
        //CARGA LOS PROVEEDORES
        if (evt.getClickCount() == 2) {

            String Query = "FROM Abonoproveedor ap WHERE ap.idContenidoExpediente="
                    + idContenidoExpediente
                    + " ORDER BY ap.fecha";
            cargaTabla(jtAbonosHistorial, Query, "AbonoProveedores", 1);

        }

    }//GEN-LAST:event_jtFoliosHistorialMouseClicked

    private void jpProveedoresRComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jpProveedoresRComponentShown
        String Query = "FROM Proveedores p ORDER BY p.nombre";
        llenarCombo(cmbProveedorHistorial, Query, "proveedor", false);

        Query = "FROM Expediente e ORDER BY e.nombreProveedor";
        cargaTabla(jtExpedienteHistorial, Query, "Expediente", 2);
    }//GEN-LAST:event_jpProveedoresRComponentShown

    private void btnCorregirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCorregirActionPerformed
        if (jtLocalesR.getSelectedRow() != -1) {
            String clave = jtLocalesR.getValueAt(jtLocalesR.getSelectedRow(), 0).toString();
            try {
                CorregirGastoLocal corrigeGastoLocal = new CorregirGastoLocal(clave);
                if (exist(corrigeGastoLocal) == false) {
                    CPanel.desktop.add(corrigeGastoLocal);
                    corrigeGastoLocal.setVisible(true);
                    corrigeGastoLocal.setLocation((CPanel.desktop.getWidth() - corrigeGastoLocal.getWidth()) / 2,
                            (CPanel.desktop.getHeight() - corrigeGastoLocal.getHeight()) / 2);
                } else {
                    corrigeGastoLocal.dispose();

                }
            } catch (Exception ex) {

            }
        }
    }//GEN-LAST:event_btnCorregirActionPerformed

    private void jpLocalesRComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jpLocalesRComponentShown
        String query = "From Sucursal s ORDER BY s.idSucursal";
        llenarCombo(cmbLocalResumen, query, "local", false);

        query = "FROM Gastoslocales gl ORDER BY gl.fecha";
        cargaTabla(jtLocalesR, query, "GastosLocales", 2);
    }//GEN-LAST:event_jpLocalesRComponentShown

    private void cmbLocalResumenItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbLocalResumenItemStateChanged
        if (cmbLocalResumen.getItemCount() > 0) {
            if (cmbLocalResumen.getSelectedItem().equals(evt.getItem())) {

                String fechaInicio = FechaHerramienta.formatoYMD(jdcLocalInicio.getDate());
                String fechaFin = FechaHerramienta.formatoYMD(jdcLocalFin.getDate());

                if (!cmbLocalResumen.getSelectedItem().toString().equals("Todos")) {
                    String Query = "FROM Gastoslocales gl WHERE gl.local= '"
                            + cmbLocalResumen.getSelectedItem().toString() + "' AND gl.fecha BETWEEN '"
                            + fechaInicio
                            + "' AND '"
                            + fechaFin + "'"
                            + " ORDER BY gl.fecha";
                    cargaTabla(jtLocalesR, Query, "GastosLocales", 2);
                } else {
                    String Query = "FROM Gastoslocales gl WHERE  gl.fecha BETWEEN '"
                            + fechaInicio
                            + "' AND '"
                            + fechaFin + "'"
                            + " ORDER BY gl.fecha";
                    cargaTabla(jtLocalesR, Query, "GastosLocales", 2);
                }
            }//
        }
    }//GEN-LAST:event_cmbLocalResumenItemStateChanged

    private void btnBorrrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrrarActionPerformed
        if (jtLocalesR.getSelectedRow() != -1) {
            int respuesta = JOptionPane.showConfirmDialog(this, "¿Desea eliminar este gasto?", "Confirmar eliminación", 0, 3);
            if (respuesta == 0) {
                try {
                    AccesoBD acceso = new AccesoBD();
                    int clave = (int) jtLocalesR.getValueAt(jtLocalesR.getSelectedRow(), 0);
                    String query = "FROM Gastoslocales gl WHERE gl.idGastosLocales = '" + clave + "'";
                    Gastoslocales concepto = (Gastoslocales) acceso.select(query).get(0);
                    acceso.delete(concepto);
                    query = "FROM Gastoslocales gl ORDER BY gl.fecha";
                    cargaTabla(jtLocalesR, query, "GastosLocales", 2);
                    JOptionPane.showMessageDialog(this, "Gasto eliminado correctamente", "Cancelado", 1);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Ha ocurrido un error: " + e, "Error", 0);
                    System.gc();
                }
            }
        }

    }//GEN-LAST:event_btnBorrrarActionPerformed

    private void jtLocalesRMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtLocalesRMouseClicked
        if (evt.getClickCount() == 2) {
            String fechaInicio = FechaHerramienta.formatoYMD(jdcLocalInicio.getDate());
            String fechaFin = FechaHerramienta.formatoYMD(jdcLocalFin.getDate());

            if (!cmbLocalResumen.getSelectedItem().toString().equals("Todos")) {
                String Query = "FROM Gastoslocales gl WHERE gl.local= '"
                        + cmbLocalResumen.getSelectedItem().toString() + "' AND gl.fecha BETWEEN '"
                        + fechaInicio
                        + "' AND '"
                        + fechaFin + "'"
                        + " ORDER BY gl.fecha";
                cargaTabla(jtLocalesR, Query, "GastosLocales", 2);
            } else {
                String Query = "FROM Gastoslocales gl WHERE  gl.fecha BETWEEN '"
                        + fechaInicio
                        + "' AND '"
                        + fechaFin + "'"
                        + " ORDER BY gl.fecha";
                cargaTabla(jtLocalesR, Query, "GastosLocales", 2);
            }
        }
    }//GEN-LAST:event_jtLocalesRMouseClicked

    private void jtPersonalesRMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtPersonalesRMouseClicked
        if (evt.getClickCount() == 2) {
            String fechaInicio = FechaHerramienta.formatoYMD(jdcGtoPersonalInicio.getDate());
            String fechaFin = FechaHerramienta.formatoYMD(jdcGtoPersonalFin.getDate());

            if (!cmbGtoPerCon.getSelectedItem().toString().equals("Todos")) {
                String Query = "FROM Gastospersonales gp WHERE gl.local= '"
                        + cmbGtoPerCon.getSelectedItem().toString() + "' AND gp.fecha BETWEEN '"
                        + fechaInicio
                        + "' AND '"
                        + fechaFin + "'"
                        + " ORDER BY gp.fecha";
                cargaTabla(jtPersonalesR, Query, "GastosPersonales", 1);
            } else {
                String Query = "FROM Gastospersonales gp WHERE  gp.fecha BETWEEN '"
                        + fechaInicio
                        + "' AND '"
                        + fechaFin + "'"
                        + " ORDER BY gp.fecha";
                cargaTabla(jtPersonalesR, Query, "GastosPersonales", 1);
            }
        }

    }//GEN-LAST:event_jtPersonalesRMouseClicked

    private void btnBorrrar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrrar1ActionPerformed
        if (jtPersonalesR.getSelectedRow() != -1) {
            int respuesta = JOptionPane.showConfirmDialog(this, "¿Desea eliminar este gasto?", "Confirmar eliminación", 0, 3);
            if (respuesta == 0) {
                try {
                    AccesoBD acceso = new AccesoBD();
                    int clave = (int) jtPersonalesR.getValueAt(jtPersonalesR.getSelectedRow(), 0);
                    String query = "From Gastospersonales gp WHERE gp.idGastosPersonales = '" + clave + "'";
                    Gastospersonales concepto = (Gastospersonales) acceso.select(query).get(0);
                    acceso.delete(concepto);
                    query = "FROM Gastospersonales gp ORDER BY gp.fecha";
                    cargaTabla(jtPersonalesR, query, "GastosPersonales", 1);
                    JOptionPane.showMessageDialog(this, "Gasto eliminado correctamente", "Cancelado", 1);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Ha ocurrido un error: " + e, "Error", 0);
                    System.gc();
                }
            }
        }
    }//GEN-LAST:event_btnBorrrar1ActionPerformed

    private void btnCorregir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCorregir1ActionPerformed
        if (jtPersonalesR.getSelectedRow() != -1) {
            String clave = jtPersonalesR.getValueAt(jtPersonalesR.getSelectedRow(), 0).toString();
            try {
                CorregirGastoPersonal corrigeGastoPersonal = new CorregirGastoPersonal(clave);
                if (exist(corrigeGastoPersonal) == false) {
                    CPanel.desktop.add(corrigeGastoPersonal);
                    corrigeGastoPersonal.setVisible(true);
                    corrigeGastoPersonal.setLocation((CPanel.desktop.getWidth() - corrigeGastoPersonal.getWidth()) / 2,
                            (CPanel.desktop.getHeight() - corrigeGastoPersonal.getHeight()) / 2);
                } else {
                    corrigeGastoPersonal.dispose();

                }
            } catch (Exception ex) {

            }
        }
    }//GEN-LAST:event_btnCorregir1ActionPerformed

    private void jpPersonalRComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jpPersonalRComponentShown
        String query = "FROM Gastospersonales gp ORDER BY gp.fecha";
        cargaTabla(jtPersonalesR, query, "GastosPersonales", 1);

        query = "FROM Linea l ORDER BY l.nombre";
        llenarCombo(cmbGtoPerCon, query, "concepto", false);
    }//GEN-LAST:event_jpPersonalRComponentShown

    private void cmbGtoPerConItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbGtoPerConItemStateChanged
        if (cmbGtoPerCon.getItemCount() > 0) {
            if (cmbGtoPerCon.getSelectedItem().equals(evt.getItem())) {

                String fechaInicio = FechaHerramienta.formatoYMD(jdcGtoPersonalInicio.getDate());
                String fechaFin = FechaHerramienta.formatoYMD(jdcGtoPersonalFin.getDate());

                if (!cmbGtoPerCon.getSelectedItem().toString().equals("Todos")) {
                    String Query = "FROM Gastospersonales gl WHERE gl.concepto= '"
                            + cmbGtoPerCon.getSelectedItem().toString() + "' AND gl.fecha BETWEEN '"
                            + fechaInicio
                            + "' AND '"
                            + fechaFin + "'"
                            + " ORDER BY gl.fecha";
                    cargaTabla(jtPersonalesR, Query, "GastosPersonales", 1);
                } else {
                    String Query = "FROM Gastospersonales gl WHERE  gl.fecha BETWEEN '"
                            + fechaInicio
                            + "' AND '"
                            + fechaFin + "'"
                            + " ORDER BY gl.fecha";
                    cargaTabla(jtPersonalesR, Query, "GastosPersonales", 1);
                }
            }//
        }

    }//GEN-LAST:event_cmbGtoPerConItemStateChanged

    private void jtGasolinaResumenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtGasolinaResumenMouseClicked
        if (evt.getClickCount() == 2) {
            String fechaInicio = FechaHerramienta.formatoYMD(jdcGasolinaInicio.getDate());
            String fechaFin = FechaHerramienta.formatoYMD(jdcGasolinaFin.getDate());

            if (!cmbGasolinaResumen.getSelectedItem().toString().equals("Todos")) {
                String Query = "FROM Gastogasolina gl WHERE gl.vehiculo= '"
                        + cmbGasolinaResumen.getSelectedItem().toString() + "' AND gl.fecha BETWEEN '"
                        + fechaInicio
                        + "' AND '"
                        + fechaFin + "'"
                        + " ORDER BY gl.fecha";
                cargaTabla(jtGasolinaResumen, Query, "Gastogasolina", 1);
            } else {
                String Query = "FROM Gastogasolina gl WHERE  gl.fecha BETWEEN '"
                        + fechaInicio
                        + "' AND '"
                        + fechaFin + "'"
                        + " ORDER BY gl.fecha";
                cargaTabla(jtGasolinaResumen, Query, "Gastogasolina", 1);
            }
        }
    }//GEN-LAST:event_jtGasolinaResumenMouseClicked

    private void jpGasolinaRComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jpGasolinaRComponentShown
        String query = "FROM Vehiculo g ORDER BY g.vehiculo";
        llenarCombo(cmbGasolinaResumen, query, "vehiculo", false);

        String fechaInicio = FechaHerramienta.formatoYMD(jdcGasolinaInicio.getDate());
        String fechaFin = FechaHerramienta.formatoYMD(jdcGasolinaFin.getDate());

        if (!cmbGasolinaResumen.getSelectedItem().toString().equals("Todos")) {
            String Query = "FROM Gastogasolina gl WHERE gl.vehiculo= '"
                    + cmbGasolinaResumen.getSelectedItem().toString() + "' AND gl.fecha BETWEEN '"
                    + fechaInicio
                    + "' AND '"
                    + fechaFin + "'"
                    + " ORDER BY gl.fecha";
            cargaTabla(jtGasolinaResumen, Query, "Gastogasolina", 1);
        } else {
            String Query = "FROM Gastogasolina gl WHERE  gl.fecha BETWEEN '"
                    + fechaInicio
                    + "' AND '"
                    + fechaFin + "'"
                    + " ORDER BY gl.fecha";
            cargaTabla(jtGasolinaResumen, Query, "Gastogasolina", 1);
        }
    }//GEN-LAST:event_jpGasolinaRComponentShown

    private void cmbGasolinaResumenItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbGasolinaResumenItemStateChanged
        if (cmbGasolinaResumen.getItemCount() > 0) {
            if (cmbGasolinaResumen.getSelectedItem().equals(evt.getItem())) {

                String fechaInicio = FechaHerramienta.formatoYMD(jdcGasolinaInicio.getDate());
                String fechaFin = FechaHerramienta.formatoYMD(jdcGasolinaFin.getDate());

                if (!cmbGasolinaResumen.getSelectedItem().toString().equals("Todos")) {
                    String Query = "FROM Gastogasolina gl WHERE gl.vehiculo= '"
                            + cmbGasolinaResumen.getSelectedItem().toString() + "' AND gl.fecha BETWEEN '"
                            + fechaInicio
                            + "' AND '"
                            + fechaFin + "'"
                            + " ORDER BY gl.fecha";
                    cargaTabla(jtGasolinaResumen, Query, "Gastogasolina", 1);
                } else {
                    String Query = "FROM Gastogasolina gl WHERE  gl.fecha BETWEEN '"
                            + fechaInicio
                            + "' AND '"
                            + fechaFin + "'"
                            + " ORDER BY gl.fecha";
                    cargaTabla(jtGasolinaResumen, Query, "Gastogasolina", 1);
                }
            }
        }
    }//GEN-LAST:event_cmbGasolinaResumenItemStateChanged

    private void btnBorrrarGasolinaGastoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrrarGasolinaGastoActionPerformed
        if (jtGasolinaResumen.getSelectedRow() != -1) {
            int respuesta = JOptionPane.showConfirmDialog(this, "¿Desea eliminar este gasto?", "Confirmar eliminación", 0, 3);
            if (respuesta == 0) {
                try {
                    AccesoBD acceso = new AccesoBD();
                    int clave = (int) jtGasolinaResumen.getValueAt(jtGasolinaResumen.getSelectedRow(), 0);
                    String query = "FROM Gastogasolina gl WHERE gl.idGastoGasolina = '" + clave + "'";
                    Gastogasolina concepto = (Gastogasolina) acceso.select(query).get(0);
                    acceso.delete(concepto);
                    query = "FROM Gastogasolina gl";
                    cargaTabla(jtGasolinaResumen, query, "Gastogasolina", 1);
                    JOptionPane.showMessageDialog(this, "Gasto eliminado correctamente", "Cancelado", 1);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Ha ocurrido un error: " + e, "Error", 0);
                    System.gc();
                }
            }
        }
    }//GEN-LAST:event_btnBorrrarGasolinaGastoActionPerformed

    private void btnCorregirGastoGasolinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCorregirGastoGasolinaActionPerformed
        if (jtGasolinaResumen.getSelectedRow() != -1) {
            String clave = jtGasolinaResumen.getValueAt(jtGasolinaResumen.getSelectedRow(), 0).toString();
            try {
                CorregirGastoGasolina corrigeGastoGasolina = new CorregirGastoGasolina(clave);
                if (exist(corrigeGastoGasolina) == false) {
                    CPanel.desktop.add(corrigeGastoGasolina);
                    corrigeGastoGasolina.setVisible(true);
                    corrigeGastoGasolina.setLocation((CPanel.desktop.getWidth() - corrigeGastoGasolina.getWidth()) / 2,
                            (CPanel.desktop.getHeight() - corrigeGastoGasolina.getHeight()) / 2);
                } else {
                    corrigeGastoGasolina.dispose();

                }
            } catch (Exception ex) {

            }
        }
    }//GEN-LAST:event_btnCorregirGastoGasolinaActionPerformed

    private void cmbVehiculoMResumenItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbVehiculoMResumenItemStateChanged
        if (cmbVehiculoMResumen.getItemCount() > 0) {
            if (cmbVehiculoMResumen.getSelectedItem().equals(evt.getItem())) {

                String fechaInicio = FechaHerramienta.formatoYMD(jdcMantenimientoInicio.getDate());
                String fechaFin = FechaHerramienta.formatoYMD(jdcMantenimientoFin.getDate());

                if (!cmbVehiculoMResumen.getSelectedItem().toString().equals("Todos")) {
                    String Query = "FROM Gastomantenimientov gl WHERE gl.vehiculo= '"
                            + cmbVehiculoMResumen.getSelectedItem().toString() + "' AND gl.fecha BETWEEN '"
                            + fechaInicio
                            + "' AND '"
                            + fechaFin + "'"
                            + " ORDER BY gl.fecha";
                    cargaTabla(jtMantenimientoResumen, Query, "Gastomantenimientov", 1);
                } else {
                    String Query = "FROM Gastomantenimientov gl WHERE  gl.fecha BETWEEN '"
                            + fechaInicio
                            + "' AND '"
                            + fechaFin + "'"
                            + " ORDER BY gl.fecha";
                    cargaTabla(jtMantenimientoResumen, Query, "Gastomantenimientov", 1);
                }

            }//
        }
    }//GEN-LAST:event_cmbVehiculoMResumenItemStateChanged

    private void btnBorrrarMantenimientoGastoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrrarMantenimientoGastoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBorrrarMantenimientoGastoActionPerformed

    private void btnCorregirGastoMantenimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCorregirGastoMantenimientoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCorregirGastoMantenimientoActionPerformed

    private void jtMantenimientoResumenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtMantenimientoResumenMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jtMantenimientoResumenMouseClicked

    private void jpMantenimientoRComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jpMantenimientoRComponentShown
        String query = "FROM Vehiculo v ORDER BY v.vehiculo";
        llenarCombo(cmbVehiculoMResumen, query, "vehiculo", false);
        String fechaInicio = FechaHerramienta.formatoYMD(jdcMantenimientoInicio.getDate());
        String fechaFin = FechaHerramienta.formatoYMD(jdcMantenimientoFin.getDate());

        if (!cmbVehiculoMResumen.getSelectedItem().toString().equals("Todos")) {
            String Query = "FROM Gastomantenimientov gl WHERE gl.vehiculo= '"
                    + cmbVehiculoMResumen.getSelectedItem().toString() + "' AND gl.fecha BETWEEN '"
                    + fechaInicio
                    + "' AND '"
                    + fechaFin + "'"
                    + " ORDER BY gl.fecha";
            cargaTabla(jtMantenimientoResumen, Query, "Gastomantenimientov", 1);
        } else {
            String Query = "FROM Gastomantenimientov gl WHERE  gl.fecha BETWEEN '"
                    + fechaInicio
                    + "' AND '"
                    + fechaFin + "'"
                    + " ORDER BY gl.fecha";
            cargaTabla(jtMantenimientoResumen, Query, "Gastomantenimientov", 1);
        }

    }//GEN-LAST:event_jpMantenimientoRComponentShown

    private void cmbConceptoFinancieroItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbConceptoFinancieroItemStateChanged
        if (cmbConceptoFinanciero.getItemCount() > 0) {
            if (cmbConceptoFinanciero.getSelectedItem().equals(evt.getItem())) {

                String fechaInicio = FechaHerramienta.formatoYMD(jdcInicioFinancieroResumen.getDate());
                String fechaFin = FechaHerramienta.formatoYMD(jdcFinFinancieroResumen.getDate());

                if (!cmbConceptoFinanciero.getSelectedItem().toString().equals("Todos")) {
                    String Query = "FROM Gastosfinancieros gf WHERE gf.concepto= '"
                            + cmbConceptoFinanciero.getSelectedItem().toString() + "' AND gf.fecha BETWEEN '"
                            + fechaInicio
                            + "' AND '"
                            + fechaFin + "'"
                            + " ORDER BY gf.fecha";
                    cargaTabla(jtFinancierosResumen, Query, "Gastofinanciero", 1);
                } else {
                    String Query = "FROM Gastosfinancieros gf WHERE  gf.fecha BETWEEN '"
                            + fechaInicio
                            + "' AND '"
                            + fechaFin + "'"
                            + " ORDER BY gf.fecha";
                    cargaTabla(jtFinancierosResumen, Query, "Gastofinanciero", 1);
                }

            }//
        }
    }//GEN-LAST:event_cmbConceptoFinancieroItemStateChanged

    private void jtFinancierosResumenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtFinancierosResumenMouseClicked
        if (evt.getClickCount() == 2) {

            String fechaInicio = FechaHerramienta.formatoYMD(jdcInicioFinancieroResumen.getDate());
            String fechaFin = FechaHerramienta.formatoYMD(jdcFinFinancieroResumen.getDate());

            if (!cmbConceptoFinanciero.getSelectedItem().toString().equals("Todos")) {
                String Query = "FROM Gastosfinancieros gf WHERE gf.concepto= '"
                        + cmbConceptoFinanciero.getSelectedItem().toString() + "' AND gf.fecha BETWEEN '"
                        + fechaInicio
                        + "' AND '"
                        + fechaFin + "'"
                        + " ORDER BY gf.fecha";
                cargaTabla(jtFinancierosResumen, Query, "Gastofinanciero", 1);
            } else {
                String Query = "FROM Gastosfinancieros gf WHERE  gf.fecha BETWEEN '"
                        + fechaInicio
                        + "' AND '"
                        + fechaFin + "'"
                        + " ORDER BY gf.fecha";
                cargaTabla(jtFinancierosResumen, Query, "Gastofinanciero", 1);
            }

        }
    }//GEN-LAST:event_jtFinancierosResumenMouseClicked

    private void btnBorrrarBodegaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrrarBodegaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBorrrarBodegaActionPerformed

    private void btnCorregirBodegaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCorregirBodegaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCorregirBodegaActionPerformed

    private void jtBodegaRMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtBodegaRMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jtBodegaRMouseClicked

    private void cmbBodegaRItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbBodegaRItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbBodegaRItemStateChanged

    private void btnLocalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocalesActionPerformed
        if (!cmbLocalResumen.getSelectedItem().toString().equals("Todos")) {
            if (jtLocalesR.getRowCount() > 0) {
                Map parametros = new HashMap();
                parametros.put("local", cmbLocalResumen.getSelectedItem().toString());
                java.sql.Date dtInicio = new java.sql.Date(jdcLocalInicio.getDate().getTime());
                parametros.put("dtInicio", dtInicio);
                java.sql.Date dtFin = new java.sql.Date(jdcLocalFin.getDate().getTime());
                parametros.put("dtFin", dtFin);
                reportMaker.ReportMaker reporte = new reportMaker.ReportMaker("FROM Gastoslocales", "GastosLocales", parametros, false);
            } else {
                JOptionPane.showMessageDialog(rootPane, "No hay resultados que imprimir");
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Selecciona una tienda");
        }

    }//GEN-LAST:event_btnLocalesActionPerformed

    private void btnGenertalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenertalesActionPerformed
        if (!cmbGtoPerCon.getSelectedItem().toString().equals("Todos")) {
            if (jtPersonalesR.getRowCount() > 0) {
                Map parametros = new HashMap();
                parametros.put("gasto", cmbGtoPerCon.getSelectedItem().toString());
                java.sql.Date dtInicio = new java.sql.Date(jdcGtoPersonalInicio.getDate().getTime());
                parametros.put("dtInicio", dtInicio);
                java.sql.Date dtFin = new java.sql.Date(jdcGtoPersonalFin.getDate().getTime());
                parametros.put("dtFin", dtFin);
                reportMaker.ReportMaker reporte = new reportMaker.ReportMaker("FROM Gastospersonales", "GastosPersonales", parametros, false);
            } else {
                JOptionPane.showMessageDialog(rootPane, "No hay resultados que imprimir");
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Selecciona un concepto");
        }
    }//GEN-LAST:event_btnGenertalesActionPerformed

    private void btnBodegaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBodegaActionPerformed
        if (!cmbBodegaR.getSelectedItem().toString().equals("Todos")) {
            if (jtBodegaR.getRowCount() > 0) {
                Map parametros = new HashMap();
                parametros.put("concepto", cmbBodegaR.getSelectedItem().toString());
                java.sql.Date dtInicio = new java.sql.Date(jdcBodegaInicio.getDate().getTime());
                parametros.put("dtInicio", dtInicio);
                java.sql.Date dtFin = new java.sql.Date(jdcBodegaFin.getDate().getTime());
                parametros.put("dtFin", dtFin);
                reportMaker.ReportMaker reporte = new reportMaker.ReportMaker("FROM Gastosoficinabodega", "GastosOficinaBodega", parametros, false);
            } else {
                JOptionPane.showMessageDialog(rootPane, "No hay resultados que imprimir");
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Selecciona un concepto");
        }
    }//GEN-LAST:event_btnBodegaActionPerformed

    private void btnGasolinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGasolinaActionPerformed
        if (!cmbGasolinaResumen.getSelectedItem().toString().equals("Todos")) {
            if (jtGasolinaResumen.getRowCount() > 0) {
                Map parametros = new HashMap();
                parametros.put("vehiculo", cmbGasolinaResumen.getSelectedItem().toString());
                java.sql.Date dtInicio = new java.sql.Date(jdcGasolinaInicio.getDate().getTime());
                parametros.put("dtInicio", dtInicio);
                java.sql.Date dtFin = new java.sql.Date(jdcGasolinaFin.getDate().getTime());
                parametros.put("dtFin", dtFin);
                reportMaker.ReportMaker reporte = new reportMaker.ReportMaker("FROM Gastogasolina", "GastoGasolina", parametros, false);
            } else {
                JOptionPane.showMessageDialog(rootPane, "No hay resultados que imprimir");
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Selecciona un concepto");
        }
    }//GEN-LAST:event_btnGasolinaActionPerformed

    private void btnMantenimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMantenimientoActionPerformed
        if (!cmbVehiculoMResumen.getSelectedItem().toString().equals("Todos")) {
            if (jtMantenimientoResumen.getRowCount() > 0) {
                Map parametros = new HashMap();
                parametros.put("vehiculo", cmbVehiculoMResumen.getSelectedItem().toString());
                java.sql.Date dtInicio = new java.sql.Date(jdcMantenimientoInicio.getDate().getTime());
                parametros.put("dtInicio", dtInicio);
                java.sql.Date dtFin = new java.sql.Date(jdcMantenimientoFin.getDate().getTime());
                parametros.put("dtFin", dtFin);
                reportMaker.ReportMaker reporte = new reportMaker.ReportMaker("FROM Mantenimiento", "GastoMantenimiento", parametros, false);
            } else {
                JOptionPane.showMessageDialog(rootPane, "No hay resultados que imprimir");
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Selecciona un concepto");
        }
    }//GEN-LAST:event_btnMantenimientoActionPerformed

    private void btnProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProveedoresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnProveedoresActionPerformed

    private void btnFinancierosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinancierosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnFinancierosActionPerformed

    private void jpBodegaRComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jpBodegaRComponentShown
        String query = "FROM Gastosoficinabodega go ORDER BY go.fecha";
        cargaTabla(jtBodegaR, query, "GastosOficinaBodega", 1);

        query = "FROM Linea l ORDER BY l.nombre";
        llenarCombo(cmbBodegaR, query, "concepto", false);
    }//GEN-LAST:event_jpBodegaRComponentShown

    private void quitarFila(JTable tabla) {
        if (tabla.getSelectedRow() > -1) {
            DefaultTableModel model = (DefaultTableModel) tabla.getModel();
            model.removeRow(tabla.getSelectedRow());
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona un registro de la "
                    + "lista para poder borrar.", "Registro no seleccionado", 0);
        }
    }

    private void limpiarTablas(JTable jtTabla) {
        jtTabla.setVisible(false);
        //Obtengo el modelo
        DefaultTableModel modelo = (DefaultTableModel) jtTabla.getModel();
        //Borro las todas las rows
        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
        jtTabla.setVisible(true);
    }

    private void cargaTabla(final JTable jtTabla, String HQL, String Encabezado, int NoColOcultar) {
        //Reviso si que la consulta no vaya vacia
        if (!HQL.isEmpty()) {
            AccesoBD acceso = new AccesoBD();
            jtTabla.setVisible(false);
            jtTabla.removeAll();
            jtTabla.setModel(acceso.retornaModelo(Encabezado, HQL));
            TableColumn column = null;
            for (int i = 0; i < NoColOcultar; i++) {
                column = jtTabla.getColumnModel().getColumn(i);
                column.setMaxWidth(10);
            }
            jtTabla.setVisible(true);
        }
    }

    private void crearConsultaGlobal() {
        fechaInicio = FechaHerramienta.formatoYMD(jdcInicioFinancieroResumen.getDate());
        fechaFin = FechaHerramienta.formatoYMD(jdcFinFinancieroResumen.getDate());
        conceptoResumen = cmbConceptoFinanciero.getSelectedItem().toString();
        if (conceptoResumen.equals("TODOS")) {
            HQL = "From Gastosgenerales g WHERE "
                    + "g.fecha BETWEEN '"
                    + fechaInicio + "' AND '"
                    + fechaFin + "' ORDER BY g.fecha";
        }
        if (!conceptoResumen.equals("TODOS")) {
            HQL = "From Gastosgenerales g WHERE "
                    + " g.concepto = '"
                    + conceptoResumen
                    + "' AND g.fecha BETWEEN '"
                    + fechaInicio + "' AND '"
                    + fechaFin + "' ORDER BY g.fecha";
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Concepto;
    private javax.swing.JLabel Concepto1;
    private javax.swing.JLabel Concepto2;
    private javax.swing.JLabel Concepto3;
    private javax.swing.JLabel Concepto4;
    private javax.swing.JLabel Concepto5;
    private javax.swing.JButton btnAbonarProveedores;
    private javax.swing.JButton btnAnotarGasolina;
    private javax.swing.JButton btnAnotarGastosPersonales;
    private javax.swing.JButton btnAnotarLocales;
    private javax.swing.JButton btnAnotarMantenimientoV;
    private javax.swing.JButton btnAnotarOficinaBodega;
    private javax.swing.JButton btnAnotarSueldosPrestamos;
    private javax.swing.JButton btnBodega;
    private javax.swing.JButton btnBorrarGasto;
    private javax.swing.JButton btnBorrarGasto1;
    private javax.swing.JButton btnBorrarGasto2;
    private javax.swing.JButton btnBorrarGasto3;
    private javax.swing.JButton btnBorrarGasto6;
    private javax.swing.JButton btnBorrarGtoMantenimiento;
    private javax.swing.JButton btnBorrrar;
    private javax.swing.JButton btnBorrrar1;
    private javax.swing.JButton btnBorrrarBodega;
    private javax.swing.JButton btnBorrrarGasolinaGasto;
    private javax.swing.JButton btnBorrrarMantenimientoGasto;
    private javax.swing.JButton btnCorregir;
    private javax.swing.JButton btnCorregir1;
    private javax.swing.JButton btnCorregirBodega;
    private javax.swing.JButton btnCorregirConcepto;
    private javax.swing.JButton btnCorregirGastoGasolina;
    private javax.swing.JButton btnCorregirGastoMantenimiento;
    private javax.swing.JButton btnEliminarConcepto;
    private javax.swing.JButton btnFinancieros;
    private javax.swing.JButton btnGasolina;
    private javax.swing.JButton btnGenertales;
    private javax.swing.JButton btnLocales;
    private javax.swing.JButton btnMantenimiento;
    private javax.swing.JButton btnProveedores;
    private javax.swing.JButton btnRegistrarGasolina;
    private javax.swing.JButton btnRegistrarGastosPersonales;
    private javax.swing.JButton btnRegistrarLocales;
    private javax.swing.JButton btnRegistrarMantenimientoV;
    private javax.swing.JButton btnRegistrarOficinaBodega;
    private javax.swing.JButton btnRegistrarSueldoPrestamos;
    private javax.swing.JComboBox cmbBodegaR;
    private javax.swing.JComboBox cmbConceptoFinanciero;
    private javax.swing.JComboBox cmbConceptoGastosPersonales;
    private javax.swing.JComboBox cmbConceptoLocales;
    private javax.swing.JComboBox cmbConceptoOficinaBodega;
    private javax.swing.JComboBox cmbConceptoSueldoPrestamo;
    private javax.swing.JComboBox cmbFormaDePago;
    private javax.swing.JComboBox cmbGasolinaResumen;
    private javax.swing.JComboBox cmbGtoPerCon;
    private javax.swing.JComboBox cmbLocalResumen;
    private javax.swing.JComboBox cmbMantenimiento;
    private javax.swing.JComboBox cmbProveedorBuscar;
    private javax.swing.JComboBox cmbProveedorHistorial;
    private javax.swing.JComboBox cmbSubConceptoLocales;
    private javax.swing.JComboBox cmbVehiculo;
    private javax.swing.JComboBox cmbVehiculoM;
    private javax.swing.JComboBox cmbVehiculoMResumen;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel116;
    private javax.swing.JLabel jLabel117;
    private javax.swing.JLabel jLabel118;
    private javax.swing.JLabel jLabel119;
    private javax.swing.JLabel jLabel120;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private com.toedter.calendar.JDateChooser jdcBodegaFin;
    private com.toedter.calendar.JDateChooser jdcBodegaInicio;
    private com.toedter.calendar.JDateChooser jdcFechaFin;
    private com.toedter.calendar.JDateChooser jdcFechaGasolina;
    private com.toedter.calendar.JDateChooser jdcFechaGastosPersonales;
    private com.toedter.calendar.JDateChooser jdcFechaInicio;
    private com.toedter.calendar.JDateChooser jdcFechaLocal;
    private com.toedter.calendar.JDateChooser jdcFechaMantenimientoV;
    private com.toedter.calendar.JDateChooser jdcFechaOficinaBodega;
    private com.toedter.calendar.JDateChooser jdcFechaSueldosPrestamos;
    private com.toedter.calendar.JDateChooser jdcFinFinancieroResumen;
    private com.toedter.calendar.JDateChooser jdcFinHistorial;
    private com.toedter.calendar.JDateChooser jdcGasolinaFin;
    private com.toedter.calendar.JDateChooser jdcGasolinaInicio;
    private com.toedter.calendar.JDateChooser jdcGtoPersonalFin;
    private com.toedter.calendar.JDateChooser jdcGtoPersonalInicio;
    private com.toedter.calendar.JDateChooser jdcInicioFinancieroResumen;
    private com.toedter.calendar.JDateChooser jdcInicioHistorial;
    private com.toedter.calendar.JDateChooser jdcLocalFin;
    private com.toedter.calendar.JDateChooser jdcLocalInicio;
    private com.toedter.calendar.JDateChooser jdcMantenimientoFin;
    private com.toedter.calendar.JDateChooser jdcMantenimientoInicio;
    private javax.swing.JPanel jpBodega;
    private javax.swing.JPanel jpBodegaR;
    private javax.swing.JPanel jpFinancieros;
    private javax.swing.JPanel jpFinancierosR;
    private javax.swing.JPanel jpGasolina;
    private javax.swing.JPanel jpGasolinaR;
    private javax.swing.JPanel jpLocales;
    private javax.swing.JPanel jpLocalesR;
    private javax.swing.JPanel jpMantenimiento;
    private javax.swing.JPanel jpMantenimientoR;
    private javax.swing.JPanel jpPersonalR;
    private javax.swing.JPanel jpPersonales;
    private javax.swing.JPanel jpProveedores;
    private javax.swing.JPanel jpProveedoresR;
    private javax.swing.JTable jtAbonosHistorial;
    private javax.swing.JTable jtBodega;
    private javax.swing.JTable jtBodegaR;
    private javax.swing.JTable jtExpedienteAsignar;
    private javax.swing.JTable jtExpedienteHistorial;
    private javax.swing.JTable jtFinancierosResumen;
    private javax.swing.JTable jtFoliosAsignados;
    private javax.swing.JTable jtFoliosHistorial;
    private javax.swing.JTable jtGasolinaResumen;
    private javax.swing.JTable jtGastosFinancieros;
    private javax.swing.JTable jtLocales;
    private javax.swing.JTable jtLocalesR;
    private javax.swing.JTable jtMantenimientoResumen;
    private javax.swing.JTable jtPersonales;
    private javax.swing.JTable jtPersonalesR;
    private javax.swing.JTable jtTablaGasolina;
    private javax.swing.JTable jtTablaMantenimientoV;
    private javax.swing.JLabel lblNotaHistorial;
    private javax.swing.JTextField txtAbonoProveedores;
    private javax.swing.JTextField txtComentarioGastosPersonales;
    private javax.swing.JTextField txtComentarioLocales;
    private javax.swing.JTextField txtComentarioMantenimientoV;
    private javax.swing.JTextField txtComentariosGasolina;
    private javax.swing.JTextField txtComentariosOficinaBodega;
    private javax.swing.JTextField txtComentariosSueldosPrestamos;
    private javax.swing.JTextField txtEmpleado;
    private javax.swing.JTextField txtImporteGasolina;
    private javax.swing.JTextField txtImporteGastosPersonales;
    private javax.swing.JTextField txtImporteLocales;
    private javax.swing.JTextField txtImporteMantenimientoV;
    private javax.swing.JTextField txtImporteOficinaBodega;
    private javax.swing.JTextField txtImporteSueldoPrestamos;
    // End of variables declaration//GEN-END:variables
}
