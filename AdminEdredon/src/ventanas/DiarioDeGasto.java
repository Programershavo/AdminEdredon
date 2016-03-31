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
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import pojos.Clientes;
import pojos.Gastosgenerales;
import pojos.Linea;
import pojos.Proveedores;
import pojos.Sucursal;

/**
 *
 * @author Daniel
 */
public class DiarioDeGasto extends javax.swing.JInternalFrame {

    private String fechaInicio = "";
    private String fechaFin = "";
    private String conceptoResumen = "";
    private String HQL = "";

    /**
     * Creates new form RegistroGasto
     */
    public DiarioDeGasto() {
        initComponents();
        limpiarCampos();
        addActions();
        //EVENTO DOBLE CLIC PARA REFRESCAR LAS TABLAS
        setEventoMouseClicked(jtTablaGastosTotales, "Gastosgenerales");
        jdcFechaConceptoTotalFin.setDate(new Date());
        jdcFechaGasolina.setDate(new Date());
        jdcFechaGastosPersonales.setDate(new Date());
        jdcFechaConceptoTotalInicio.setDate(new Date());
        jdcFechaLocal.setDate(new Date());
        jdcFechaMantenimientoV.setDate(new Date());
        jdcFechaOficinaBodega.setDate(new Date());
        jdcFechaProveedores.setDate(new Date());
        jdcFechaSueldosPrestamos.setDate(new Date());
    }

    //EVENTO DOBLE CLIC PARA REFRESCAR LAS TABLAS
    private void setEventoMouseClicked(JTable tabla, String esDiariocajaOVenta) {
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    if (esDiariocajaOVenta.equals("Gastosgenerales")) {
                        crearConsultaGlobal();
                        cargaTabla(tabla, HQL, "Gastosgenerales");
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

    private void llenarCombo(JComboBox combo, String query, String tipoLista) {
        AccesoBD acceso = new AccesoBD();
        combo.removeAllItems();
        List listaItems = acceso.select(query);
        //combo = new JComboBox();
        if (listaItems.size() > 0) {
            try {
                //Crea la lista de categorias
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
                    }

                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "El error es:" + "\n" + e, "Error", 0);
            }
        }
    }

    public final void addActions() {

        final ItemListener changeClick = new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (cmbConceptoTotal.getItemCount() > 0) {
                    if (cmbConceptoTotal.getSelectedItem().equals(e.getItem())) {
                        crearConsultaGlobal();
                        cargaTabla(jtTablaGastosTotales, HQL, "Gastosgenerales");
                    }
                }

            }
        };
        if (jdcFechaConceptoTotalInicio.isVisible()) {
            jdcFechaConceptoTotalInicio.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
                public void propertyChange(PropertyChangeEvent e) {
                    crearConsultaGlobal();
                    cargaTabla(jtTablaGastosTotales, HQL, "Gastosgenerales");
                }
            });
            jdcFechaConceptoTotalFin.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
                public void propertyChange(PropertyChangeEvent e) {
                    crearConsultaGlobal();
                    cargaTabla(jtTablaGastosTotales, HQL, "Gastosgenerales");
                }
            });
        }

        if (jdcFechaConceptoResumenInicio.isVisible()) {
            jdcFechaConceptoResumenInicio.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
                public void propertyChange(PropertyChangeEvent e) {
                    crearConsultaGlobal();
                    crearResumen();
                }
            });
            jdcFechaConceptoResumenFin.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
                public void propertyChange(PropertyChangeEvent e) {
                    crearConsultaGlobal();
                    crearResumen();
                }
            });
        }

        this.cmbConceptoTotal.addItemListener(changeClick);
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
        cmbConceptoTotal.setSelectedIndex(0);

        //Reinicio todas las cajas de texto
        txtComentariosGasolina.setText("");
        txtComentarioGastosPersonales.setText("");
        txtComentarioLocales.setText("");
        txtComentarioMantenimientoV.setText("");
        txtComentariosOficinaBodega.setText("");
//        cmbFormaDePago.setText("");
        txtComentariosSueldosPrestamos.setText("");
        txtFolio.setText("");
        txtProveedorNuevo.setText("");
        txtClienteNuevo.setText("");
//        txtNoPiezas.setText("");
        txtLocalNuevo.setText("");
        txtConceptoNuevo.setText("");

        txtImporteGasolina.setText("0");
        txtImporteLocales.setText("0");
        txtImporteGastosPersonales.setText("0");
        txtImporteMantenimientoV.setText("0");
        txtImporteOficinaBodega.setText("0");
        txtImporteProveedores.setText("0");
        txtImporteSueldoPrestamos.setText("0");
        txtSubConceptoSueldosPrestamos.setText("0");
    }

    private void guardarConceptoSubconcepto(JTable tabla, String concepto) {
        AccesoBD controlBD = new AccesoBD();
        String fecha = "";
        for (int i = 0; i < tabla.getRowCount(); i++) {
            try {
                pojos.Gastosgenerales conceptoGasto = new pojos.Gastosgenerales();
                fecha = tabla.getValueAt(i, 0).toString();
                conceptoGasto.setFecha(FechaHerramienta.convertirStringEnDate(fecha));
                conceptoGasto.setConcepto(concepto);
                conceptoGasto.setAcreedor(tabla.getValueAt(i, 1).toString());
                conceptoGasto.setSubconcepto(tabla.getValueAt(i, 2).toString());
                conceptoGasto.setImporte(Double.parseDouble(tabla.getValueAt(i, 3).toString()));
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

    private void guardarConcepto(JTable tabla, String concepto) {
        AccesoBD controlBD = new AccesoBD();
        String fecha = "";
        for (int i = 0; i < tabla.getRowCount(); i++) {
            try {
                if (!concepto.equals("PROVEEDORES")) {
                    pojos.Gastosgenerales conceptoGasto = new pojos.Gastosgenerales();
                    //fecha
                    fecha = tabla.getValueAt(i, 0).toString();
                    conceptoGasto.setFecha(FechaHerramienta.convertirStringEnDate(fecha));
                    //proveedor, oficina, mantenimiento,
                    conceptoGasto.setConcepto(concepto);
                    conceptoGasto.setAcreedor(concepto);
                    conceptoGasto.setSubconcepto(tabla.getValueAt(i, 1).toString());
                    String importe = tabla.getValueAt(i, 2).toString();
                    //importe
                    conceptoGasto.setImporte(Double.parseDouble(importe));
                    //comentario
                    conceptoGasto.setComentario(tabla.getValueAt(i, 3).toString());
                    controlBD.add(conceptoGasto);
                }
                if (concepto.equals("PROVEEDORES")) {
                    pojos.Gastosgenerales conceptoGasto = new pojos.Gastosgenerales();
                    //fecha-0
                    fecha = tabla.getValueAt(i, 0).toString();
                    conceptoGasto.setFecha(FechaHerramienta.convertirStringEnDate(fecha));
                    //proveedor, oficina, mantenimiento,
                    conceptoGasto.setConcepto(concepto);
                    conceptoGasto.setAcreedor(concepto);
                    //Proveedor-2
                    conceptoGasto.setSubconcepto(tabla.getValueAt(i, 2).toString());
                    //importe-3
                    String importe = tabla.getValueAt(i, 3).toString();
                    conceptoGasto.setImporte(Double.parseDouble(importe));
                    //comentario-5
                    conceptoGasto.setComentario(tabla.getValueAt(i, 5).toString());
                    controlBD.add(conceptoGasto);

                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e, "Error", 0);
            }
        }
        limpiarCampos();
        limpiarTablas(tabla);
        JOptionPane.showMessageDialog(this, "Gastos registrados correctamente", "Datos registrados", 1);        // TODO add your handling code here:
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
        jpGastosLocales = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtImporteLocales = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jdcFechaLocal = new com.toedter.calendar.JDateChooser();
        jLabel29 = new javax.swing.JLabel();
        btnSalirRegistroGastos = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        btnAnotarLocales = new javax.swing.JButton();
        btnBorrarGasto = new javax.swing.JButton();
        btnRegistrarLocales = new javax.swing.JButton();
        txtComentarioLocales = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        jtTablaLocales = new javax.swing.JTable();
        jLabel28 = new javax.swing.JLabel();
        cmbConceptoLocales = new javax.swing.JComboBox();
        cmbSubConceptoLocales = new javax.swing.JComboBox();
        jpGastosLocales1 = new javax.swing.JPanel();
        btnSalirRegistroGastos1 = new javax.swing.JButton();
        jLabel32 = new javax.swing.JLabel();
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
        jtTablaGastosPersonales = new javax.swing.JTable();
        jdcFechaGastosPersonales = new com.toedter.calendar.JDateChooser();
        cmbConceptoGastosPersonales = new javax.swing.JComboBox();
        jpGastosLocales2 = new javax.swing.JPanel();
        btnSalirRegistroGastos2 = new javax.swing.JButton();
        jLabel34 = new javax.swing.JLabel();
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
        jtTablaOficinaBodega = new javax.swing.JTable();
        jdcFechaOficinaBodega = new com.toedter.calendar.JDateChooser();
        cmbConceptoOficinaBodega = new javax.swing.JComboBox();
        jpGastosLocales3 = new javax.swing.JPanel();
        btnSalirRegistroGastos3 = new javax.swing.JButton();
        jLabel36 = new javax.swing.JLabel();
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
        cmbConceptoGasolina = new javax.swing.JComboBox();
        jpGastosLocales4 = new javax.swing.JPanel();
        btnSalirRegistroGastos4 = new javax.swing.JButton();
        jLabel38 = new javax.swing.JLabel();
        btnBorrarGasto4 = new javax.swing.JButton();
        btnRegistrarMantenimientoV = new javax.swing.JButton();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        btnAnotarMantenimientoV = new javax.swing.JButton();
        txtImporteMantenimientoV = new javax.swing.JTextField();
        jLabel62 = new javax.swing.JLabel();
        txtComentarioMantenimientoV = new javax.swing.JTextField();
        jScrollPane15 = new javax.swing.JScrollPane();
        jtTablaMantenimientoV = new javax.swing.JTable();
        jdcFechaMantenimientoV = new com.toedter.calendar.JDateChooser();
        cmbConceptoMantenimiento = new javax.swing.JComboBox();
        jpGastosLocales5 = new javax.swing.JPanel();
        btnSalirRegistroGastos5 = new javax.swing.JButton();
        jLabel40 = new javax.swing.JLabel();
        btnBorrarGasto5 = new javax.swing.JButton();
        btnRegistrarProveedores = new javax.swing.JButton();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        btnAnotarProveedores = new javax.swing.JButton();
        txtImporteProveedores = new javax.swing.JTextField();
        jLabel67 = new javax.swing.JLabel();
        jScrollPane16 = new javax.swing.JScrollPane();
        jtTablaProveedores = new javax.swing.JTable();
        jdcFechaProveedores = new com.toedter.calendar.JDateChooser();
        jLabel84 = new javax.swing.JLabel();
        txtFolio = new javax.swing.JTextField();
        cmbProveedor = new javax.swing.JComboBox();
        cmbFormaDePago = new javax.swing.JComboBox();
        jpGastosLocales6 = new javax.swing.JPanel();
        btnSalirRegistroGastos6 = new javax.swing.JButton();
        jLabel42 = new javax.swing.JLabel();
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
        jtTablaSueldosPrestamos = new javax.swing.JTable();
        jdcFechaSueldosPrestamos = new com.toedter.calendar.JDateChooser();
        jLabel79 = new javax.swing.JLabel();
        txtSubConceptoSueldosPrestamos = new javax.swing.JTextField();
        jpGastosTotales = new javax.swing.JPanel();
        btnSalirResumen1 = new javax.swing.JButton();
        jLabel43 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jtTablaGastosTotales = new javax.swing.JTable();
        jLabel39 = new javax.swing.JLabel();
        btnCorregirConcepto = new javax.swing.JButton();
        btnEliminarConcepto = new javax.swing.JButton();
        Concepto = new javax.swing.JLabel();
        cmbConceptoTotal = new javax.swing.JComboBox();
        jdcFechaConceptoTotalFin = new com.toedter.calendar.JDateChooser();
        jdcFechaConceptoTotalInicio = new com.toedter.calendar.JDateChooser();
        jLabel37 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jpResumenConceptos = new javax.swing.JPanel();
        jLabel63 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jdcFechaConceptoResumenInicio = new com.toedter.calendar.JDateChooser();
        jLabel41 = new javax.swing.JLabel();
        jdcFechaConceptoResumenFin = new com.toedter.calendar.JDateChooser();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        lblTituloResultados = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lblConceptoLocal = new javax.swing.JLabel();
        lblConceptoGastoPersonal = new javax.swing.JLabel();
        lblConceptoOficinaBodega = new javax.swing.JLabel();
        lblConceptoGasolina = new javax.swing.JLabel();
        lblConceptoMantenimientoVehiculos = new javax.swing.JLabel();
        lblConceptoProveedores = new javax.swing.JLabel();
        lblConceptoSueldosPrestamos = new javax.swing.JLabel();
        lblTotalConceptoResumen = new javax.swing.JLabel();
        lblTotalProveedoresEntreTiendas = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel83 = new javax.swing.JLabel();
        lblTiendasAbiertas = new javax.swing.JLabel();
        jsConceptos = new javax.swing.JScrollPane();
        jpConceptos = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtLocal = new javax.swing.JTable();
        jLabel48 = new javax.swing.JLabel();
        btnCorregirConcepto1 = new javax.swing.JButton();
        btnEliminarLocal = new javax.swing.JButton();
        jLabel86 = new javax.swing.JLabel();
        btnNuevoLocal = new javax.swing.JButton();
        txtLocalNuevo = new javax.swing.JTextField();
        jLabel87 = new javax.swing.JLabel();
        btnNuevoConcepto = new javax.swing.JButton();
        txtConceptoNuevo = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtConceptos = new javax.swing.JTable();
        jLabel88 = new javax.swing.JLabel();
        btnEditarConceptos = new javax.swing.JButton();
        btnBorrarConcepto = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jpProveedoresClientes = new javax.swing.JPanel();
        jsProvedorCliente = new javax.swing.JScrollPane();
        jtProveedor = new javax.swing.JTable();
        jLabel85 = new javax.swing.JLabel();
        btnCorregirProveedor = new javax.swing.JButton();
        btnEliminarProveedor = new javax.swing.JButton();
        jLabel89 = new javax.swing.JLabel();
        btnNuevoProveedor = new javax.swing.JButton();
        txtProveedorNuevo = new javax.swing.JTextField();
        jLabel90 = new javax.swing.JLabel();
        btnNuevoCliente = new javax.swing.JButton();
        txtClienteNuevo = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        jtClientes = new javax.swing.JTable();
        jLabel91 = new javax.swing.JLabel();
        btnEditarCliente = new javax.swing.JButton();
        btnBorrarCliente = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Registro de gastos generales");

        jPanel5.setBackground(new java.awt.Color(57, 105, 145));

        jLabel10.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("GASTOS GENERALES");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(494, 494, 494)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(365, 365, 365))
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

        jpGastosLocales.setBackground(new java.awt.Color(255, 255, 255));
        jpGastosLocales.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jpGastosLocalesComponentShown(evt);
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

        btnSalirRegistroGastos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSalirRegistroGastos.setForeground(new java.awt.Color(0, 153, 51));
        btnSalirRegistroGastos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/exit2.png"))); // NOI18N
        btnSalirRegistroGastos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirRegistroGastosActionPerformed(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(0, 102, 153));
        jLabel30.setText("Salir");

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

        jtTablaLocales.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "FECHA", "CONCEPTO", "TIENDA", "IMPORTE", "COMENTARIOS"
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
        jScrollPane4.setViewportView(jtTablaLocales);

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

        javax.swing.GroupLayout jpGastosLocalesLayout = new javax.swing.GroupLayout(jpGastosLocales);
        jpGastosLocales.setLayout(jpGastosLocalesLayout);
        jpGastosLocalesLayout.setHorizontalGroup(
            jpGastosLocalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpGastosLocalesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpGastosLocalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpGastosLocalesLayout.createSequentialGroup()
                        .addComponent(btnSalirRegistroGastos, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel30)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBorrarGasto)
                        .addGap(18, 18, 18)
                        .addComponent(btnRegistrarLocales, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4)
                    .addGroup(jpGastosLocalesLayout.createSequentialGroup()
                        .addGroup(jpGastosLocalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jdcFechaLocal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpGastosLocalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbConceptoLocales, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpGastosLocalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                            .addComponent(cmbSubConceptoLocales, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpGastosLocalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtImporteLocales, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpGastosLocalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addGroup(jpGastosLocalesLayout.createSequentialGroup()
                                .addComponent(txtComentarioLocales, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAnotarLocales)))))
                .addGap(109, 109, 109))
        );

        jpGastosLocalesLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel2, jLabel6, txtComentarioLocales});

        jpGastosLocalesLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnBorrarGasto, btnRegistrarLocales});

        jpGastosLocalesLayout.setVerticalGroup(
            jpGastosLocalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpGastosLocalesLayout.createSequentialGroup()
                .addGroup(jpGastosLocalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel29)
                    .addComponent(jLabel28)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpGastosLocalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jdcFechaLocal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtImporteLocales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtComentarioLocales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbConceptoLocales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbSubConceptoLocales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 389, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpGastosLocalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSalirRegistroGastos, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpGastosLocalesLayout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(jpGastosLocalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpGastosLocalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnBorrarGasto)
                                .addComponent(btnRegistrarLocales))
                            .addComponent(jLabel30))))
                .addContainerGap())
            .addGroup(jpGastosLocalesLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(btnAnotarLocales, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(451, 451, 451))
        );

        jTabbedPane1.addTab("LOCALES", jpGastosLocales);

        jpGastosLocales1.setBackground(new java.awt.Color(255, 255, 255));
        jpGastosLocales1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jpGastosLocales1ComponentShown(evt);
            }
        });

        btnSalirRegistroGastos1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSalirRegistroGastos1.setForeground(new java.awt.Color(0, 153, 51));
        btnSalirRegistroGastos1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/exit2.png"))); // NOI18N
        btnSalirRegistroGastos1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirRegistroGastos1ActionPerformed(evt);
            }
        });

        jLabel32.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(0, 102, 153));
        jLabel32.setText("Salir");

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

        jtTablaGastosPersonales.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane12.setViewportView(jtTablaGastosPersonales);

        javax.swing.GroupLayout jpGastosLocales1Layout = new javax.swing.GroupLayout(jpGastosLocales1);
        jpGastosLocales1.setLayout(jpGastosLocales1Layout);
        jpGastosLocales1Layout.setHorizontalGroup(
            jpGastosLocales1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpGastosLocales1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpGastosLocales1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpGastosLocales1Layout.createSequentialGroup()
                        .addComponent(btnSalirRegistroGastos1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel32)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBorrarGasto1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRegistrarGastosPersonales, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane12)
                    .addGroup(jpGastosLocales1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jpGastosLocales1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel45, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jdcFechaGastosPersonales, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpGastosLocales1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                            .addComponent(cmbConceptoGastosPersonales, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpGastosLocales1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtImporteGastosPersonales, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpGastosLocales1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel47)
                            .addGroup(jpGastosLocales1Layout.createSequentialGroup()
                                .addComponent(txtComentarioGastosPersonales, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAnotarGastosPersonales, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(140, 140, 140))
        );

        jpGastosLocales1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnAnotarGastosPersonales, jLabel27, jLabel45, jLabel46, jLabel47, jdcFechaGastosPersonales, txtComentarioGastosPersonales, txtImporteGastosPersonales});

        jpGastosLocales1Layout.setVerticalGroup(
            jpGastosLocales1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpGastosLocales1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpGastosLocales1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpGastosLocales1Layout.createSequentialGroup()
                        .addComponent(jLabel45)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jdcFechaGastosPersonales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpGastosLocales1Layout.createSequentialGroup()
                        .addGroup(jpGastosLocales1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel46)
                            .addComponent(jLabel47))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpGastosLocales1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(txtComentarioGastosPersonales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtImporteGastosPersonales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAnotarGastosPersonales, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbConceptoGastosPersonales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpGastosLocales1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpGastosLocales1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel32)
                        .addComponent(btnBorrarGasto1)
                        .addComponent(btnRegistrarGastosPersonales))
                    .addComponent(btnSalirRegistroGastos1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("GASTOS PERSONALES", jpGastosLocales1);

        jpGastosLocales2.setBackground(new java.awt.Color(255, 255, 255));
        jpGastosLocales2.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jpGastosLocales2ComponentShown(evt);
            }
        });

        btnSalirRegistroGastos2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSalirRegistroGastos2.setForeground(new java.awt.Color(0, 153, 51));
        btnSalirRegistroGastos2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/exit2.png"))); // NOI18N
        btnSalirRegistroGastos2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirRegistroGastos2ActionPerformed(evt);
            }
        });

        jLabel34.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(0, 102, 153));
        jLabel34.setText("Salir");

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

        jtTablaOficinaBodega.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane13.setViewportView(jtTablaOficinaBodega);

        cmbConceptoOficinaBodega.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jpGastosLocales2Layout = new javax.swing.GroupLayout(jpGastosLocales2);
        jpGastosLocales2.setLayout(jpGastosLocales2Layout);
        jpGastosLocales2Layout.setHorizontalGroup(
            jpGastosLocales2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpGastosLocales2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpGastosLocales2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpGastosLocales2Layout.createSequentialGroup()
                        .addComponent(btnSalirRegistroGastos2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel34)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBorrarGasto2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRegistrarOficinaBodega, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane13)
                    .addGroup(jpGastosLocales2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jpGastosLocales2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel49, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jdcFechaOficinaBodega, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpGastosLocales2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel50, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                            .addComponent(cmbConceptoOficinaBodega, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpGastosLocales2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel51, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtImporteOficinaBodega, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpGastosLocales2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel52)
                            .addGroup(jpGastosLocales2Layout.createSequentialGroup()
                                .addComponent(txtComentariosOficinaBodega, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAnotarOficinaBodega)))))
                .addGap(140, 140, 140))
        );

        jpGastosLocales2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnAnotarOficinaBodega, jLabel49, jLabel50, jLabel51, jLabel52, jdcFechaOficinaBodega, txtComentariosOficinaBodega, txtImporteOficinaBodega});

        jpGastosLocales2Layout.setVerticalGroup(
            jpGastosLocales2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpGastosLocales2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jpGastosLocales2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpGastosLocales2Layout.createSequentialGroup()
                        .addComponent(jLabel49)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jdcFechaOficinaBodega, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpGastosLocales2Layout.createSequentialGroup()
                        .addGroup(jpGastosLocales2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel51)
                            .addComponent(jLabel52))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpGastosLocales2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(txtComentariosOficinaBodega, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtImporteOficinaBodega, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAnotarOficinaBodega, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbConceptoOficinaBodega, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpGastosLocales2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpGastosLocales2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel34)
                        .addComponent(btnBorrarGasto2)
                        .addComponent(btnRegistrarOficinaBodega))
                    .addComponent(btnSalirRegistroGastos2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("OFICINA BODEGA", jpGastosLocales2);

        jpGastosLocales3.setBackground(new java.awt.Color(255, 255, 255));
        jpGastosLocales3.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jpGastosLocales3ComponentShown(evt);
            }
        });

        btnSalirRegistroGastos3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSalirRegistroGastos3.setForeground(new java.awt.Color(0, 153, 51));
        btnSalirRegistroGastos3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/exit2.png"))); // NOI18N
        btnSalirRegistroGastos3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirRegistroGastos3ActionPerformed(evt);
            }
        });

        jLabel36.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(0, 102, 153));
        jLabel36.setText("Salir");

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
        jLabel55.setText("CONCEPTO");

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
        jScrollPane14.setViewportView(jtTablaGasolina);

        cmbConceptoGasolina.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jpGastosLocales3Layout = new javax.swing.GroupLayout(jpGastosLocales3);
        jpGastosLocales3.setLayout(jpGastosLocales3Layout);
        jpGastosLocales3Layout.setHorizontalGroup(
            jpGastosLocales3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpGastosLocales3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpGastosLocales3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpGastosLocales3Layout.createSequentialGroup()
                        .addComponent(btnSalirRegistroGastos3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel36)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBorrarGasto3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRegistrarGasolina, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane14)
                    .addGroup(jpGastosLocales3Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jpGastosLocales3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel54, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jdcFechaGasolina, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpGastosLocales3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel55, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                            .addComponent(cmbConceptoGasolina, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpGastosLocales3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel56, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtImporteGasolina, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpGastosLocales3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel57)
                            .addGroup(jpGastosLocales3Layout.createSequentialGroup()
                                .addComponent(txtComentariosGasolina, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAnotarGasolina)))))
                .addGap(140, 140, 140))
        );

        jpGastosLocales3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnAnotarGasolina, jLabel54, jLabel55, jLabel56, jLabel57, jdcFechaGasolina, txtComentariosGasolina, txtImporteGasolina});

        jpGastosLocales3Layout.setVerticalGroup(
            jpGastosLocales3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpGastosLocales3Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jpGastosLocales3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpGastosLocales3Layout.createSequentialGroup()
                        .addComponent(jLabel54)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jdcFechaGasolina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpGastosLocales3Layout.createSequentialGroup()
                        .addGroup(jpGastosLocales3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel56)
                            .addComponent(jLabel57))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpGastosLocales3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(txtComentariosGasolina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtImporteGasolina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAnotarGasolina, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbConceptoGasolina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane14, javax.swing.GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpGastosLocales3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpGastosLocales3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel36)
                        .addComponent(btnBorrarGasto3)
                        .addComponent(btnRegistrarGasolina))
                    .addComponent(btnSalirRegistroGastos3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("GASOLINA", jpGastosLocales3);

        jpGastosLocales4.setBackground(new java.awt.Color(255, 255, 255));
        jpGastosLocales4.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jpGastosLocales4ComponentShown(evt);
            }
        });

        btnSalirRegistroGastos4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSalirRegistroGastos4.setForeground(new java.awt.Color(0, 153, 51));
        btnSalirRegistroGastos4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/exit2.png"))); // NOI18N
        btnSalirRegistroGastos4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirRegistroGastos4ActionPerformed(evt);
            }
        });

        jLabel38.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(0, 102, 153));
        jLabel38.setText("Salir");

        btnBorrarGasto4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnBorrarGasto4.setForeground(new java.awt.Color(0, 153, 51));
        btnBorrarGasto4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/menos.png"))); // NOI18N
        btnBorrarGasto4.setText("Quitar");
        btnBorrarGasto4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarGasto4ActionPerformed(evt);
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

        jLabel60.setBackground(new java.awt.Color(255, 255, 255));
        jLabel60.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(0, 102, 153));
        jLabel60.setText("CONCEPTO");
        jLabel60.setOpaque(true);

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
        jLabel62.setText("COMENTARIOS");
        jLabel62.setOpaque(true);

        jtTablaMantenimientoV.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane15.setViewportView(jtTablaMantenimientoV);

        cmbConceptoMantenimiento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jpGastosLocales4Layout = new javax.swing.GroupLayout(jpGastosLocales4);
        jpGastosLocales4.setLayout(jpGastosLocales4Layout);
        jpGastosLocales4Layout.setHorizontalGroup(
            jpGastosLocales4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpGastosLocales4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpGastosLocales4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpGastosLocales4Layout.createSequentialGroup()
                        .addComponent(btnSalirRegistroGastos4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel38)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBorrarGasto4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRegistrarMantenimientoV, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane15)
                    .addGroup(jpGastosLocales4Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jpGastosLocales4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel59, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jdcFechaMantenimientoV, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpGastosLocales4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel60, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                            .addComponent(cmbConceptoMantenimiento, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpGastosLocales4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel61, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtImporteMantenimientoV, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpGastosLocales4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel62)
                            .addGroup(jpGastosLocales4Layout.createSequentialGroup()
                                .addComponent(txtComentarioMantenimientoV, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAnotarMantenimientoV)))))
                .addGap(140, 140, 140))
        );

        jpGastosLocales4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnAnotarMantenimientoV, jLabel59, jLabel60, jLabel61, jLabel62, jdcFechaMantenimientoV, txtComentarioMantenimientoV, txtImporteMantenimientoV});

        jpGastosLocales4Layout.setVerticalGroup(
            jpGastosLocales4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpGastosLocales4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpGastosLocales4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpGastosLocales4Layout.createSequentialGroup()
                        .addComponent(jLabel59)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jdcFechaMantenimientoV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpGastosLocales4Layout.createSequentialGroup()
                        .addGroup(jpGastosLocales4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel61)
                            .addComponent(jLabel62))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpGastosLocales4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(txtComentarioMantenimientoV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtImporteMantenimientoV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAnotarMantenimientoV, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbConceptoMantenimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane15, javax.swing.GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpGastosLocales4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpGastosLocales4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel38)
                        .addComponent(btnBorrarGasto4)
                        .addComponent(btnRegistrarMantenimientoV))
                    .addComponent(btnSalirRegistroGastos4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("MANTENIMIENTO V", jpGastosLocales4);

        jpGastosLocales5.setBackground(new java.awt.Color(255, 255, 255));
        jpGastosLocales5.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jpGastosLocales5ComponentShown(evt);
            }
        });

        btnSalirRegistroGastos5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSalirRegistroGastos5.setForeground(new java.awt.Color(0, 153, 51));
        btnSalirRegistroGastos5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/exit2.png"))); // NOI18N
        btnSalirRegistroGastos5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirRegistroGastos5ActionPerformed(evt);
            }
        });

        jLabel40.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(0, 102, 153));
        jLabel40.setText("Salir");

        btnBorrarGasto5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnBorrarGasto5.setForeground(new java.awt.Color(0, 153, 51));
        btnBorrarGasto5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/menos.png"))); // NOI18N
        btnBorrarGasto5.setText("Quitar");
        btnBorrarGasto5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarGasto5ActionPerformed(evt);
            }
        });

        btnRegistrarProveedores.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnRegistrarProveedores.setForeground(new java.awt.Color(0, 153, 51));
        btnRegistrarProveedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/mas.png"))); // NOI18N
        btnRegistrarProveedores.setText("Registrar");
        btnRegistrarProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarProveedoresActionPerformed(evt);
            }
        });

        jLabel64.setBackground(new java.awt.Color(255, 255, 255));
        jLabel64.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel64.setForeground(new java.awt.Color(0, 102, 153));
        jLabel64.setText("FECHA");
        jLabel64.setOpaque(true);

        jLabel65.setBackground(new java.awt.Color(255, 255, 255));
        jLabel65.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(0, 102, 153));
        jLabel65.setText("PROVEEDOR");
        jLabel65.setOpaque(true);

        jLabel66.setBackground(new java.awt.Color(255, 255, 255));
        jLabel66.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(0, 102, 153));
        jLabel66.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel66.setText("IMPORTE");
        jLabel66.setOpaque(true);

        btnAnotarProveedores.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAnotarProveedores.setForeground(new java.awt.Color(0, 153, 51));
        btnAnotarProveedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/list.png"))); // NOI18N
        btnAnotarProveedores.setText("Anotar");
        btnAnotarProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnotarProveedoresActionPerformed(evt);
            }
        });

        txtImporteProveedores.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtImporteProveedoresKeyTyped(evt);
            }
        });

        jLabel67.setBackground(new java.awt.Color(255, 255, 255));
        jLabel67.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(0, 102, 153));
        jLabel67.setText("FORMA DE PAGO");
        jLabel67.setOpaque(true);

        jtTablaProveedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "FECHA", "FOLIO", "PROVEEDOR", "IMPORTE", "NO PIEZAS", "FORMA DE PAGO"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane16.setViewportView(jtTablaProveedores);

        jLabel84.setBackground(new java.awt.Color(255, 255, 255));
        jLabel84.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel84.setForeground(new java.awt.Color(204, 0, 0));
        jLabel84.setText("FOLIO");
        jLabel84.setOpaque(true);

        txtFolio.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtFolio.setForeground(new java.awt.Color(153, 0, 0));
        txtFolio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFolio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFolioActionPerformed(evt);
            }
        });

        cmbProveedor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmbFormaDePago.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "EFECTIVO", "CHEQUE", "TRANSFERENCIA", "DEPOSITO", "CREDITO", "DEVOLUCION", "TRUEQUE", "CAMBIO DE MERCANCIA" }));

        javax.swing.GroupLayout jpGastosLocales5Layout = new javax.swing.GroupLayout(jpGastosLocales5);
        jpGastosLocales5.setLayout(jpGastosLocales5Layout);
        jpGastosLocales5Layout.setHorizontalGroup(
            jpGastosLocales5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpGastosLocales5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpGastosLocales5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane16)
                    .addGroup(jpGastosLocales5Layout.createSequentialGroup()
                        .addComponent(btnSalirRegistroGastos5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel40)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBorrarGasto5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRegistrarProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpGastosLocales5Layout.createSequentialGroup()
                        .addGroup(jpGastosLocales5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpGastosLocales5Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(jpGastosLocales5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel64, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jdcFechaProveedores, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jpGastosLocales5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel84, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtFolio, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jpGastosLocales5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel65, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                                    .addComponent(cmbProveedor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jpGastosLocales5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel66, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtImporteProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jpGastosLocales5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel67, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmbFormaDePago, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(btnAnotarProveedores))
                        .addGap(0, 130, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jpGastosLocales5Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnAnotarProveedores, jLabel64, jLabel65, jLabel66, jLabel67, jdcFechaProveedores, txtImporteProveedores});

        jpGastosLocales5Layout.setVerticalGroup(
            jpGastosLocales5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpGastosLocales5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpGastosLocales5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpGastosLocales5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jpGastosLocales5Layout.createSequentialGroup()
                            .addComponent(jLabel84, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jpGastosLocales5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtFolio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cmbProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jpGastosLocales5Layout.createSequentialGroup()
                            .addComponent(jLabel64)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jdcFechaProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpGastosLocales5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jpGastosLocales5Layout.createSequentialGroup()
                            .addComponent(jLabel67)
                            .addGap(26, 26, 26))
                        .addGroup(jpGastosLocales5Layout.createSequentialGroup()
                            .addComponent(jLabel66)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jpGastosLocales5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtImporteProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cmbFormaDePago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAnotarProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(jScrollPane16, javax.swing.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpGastosLocales5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpGastosLocales5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel40)
                        .addComponent(btnBorrarGasto5)
                        .addComponent(btnRegistrarProveedores))
                    .addComponent(btnSalirRegistroGastos5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("PROVEEDORES", jpGastosLocales5);

        jpGastosLocales6.setBackground(new java.awt.Color(255, 255, 255));

        btnSalirRegistroGastos6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSalirRegistroGastos6.setForeground(new java.awt.Color(0, 153, 51));
        btnSalirRegistroGastos6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/exit2.png"))); // NOI18N
        btnSalirRegistroGastos6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirRegistroGastos6ActionPerformed(evt);
            }
        });

        jLabel42.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(0, 102, 153));
        jLabel42.setText("Salir");

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

        jtTablaSueldosPrestamos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "FECHA", "CONCEPTO", "MOTIVO", "IMPORTE", "COMENTARIOS"
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
        jScrollPane17.setViewportView(jtTablaSueldosPrestamos);

        jLabel79.setBackground(new java.awt.Color(0, 102, 153));
        jLabel79.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel79.setForeground(new java.awt.Color(255, 255, 255));
        jLabel79.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel79.setText("NOMBRE");
        jLabel79.setOpaque(true);

        txtSubConceptoSueldosPrestamos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSubConceptoSueldosPrestamosKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jpGastosLocales6Layout = new javax.swing.GroupLayout(jpGastosLocales6);
        jpGastosLocales6.setLayout(jpGastosLocales6Layout);
        jpGastosLocales6Layout.setHorizontalGroup(
            jpGastosLocales6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpGastosLocales6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpGastosLocales6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpGastosLocales6Layout.createSequentialGroup()
                        .addComponent(btnSalirRegistroGastos6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel42)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBorrarGasto6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRegistrarSueldoPrestamos, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpGastosLocales6Layout.createSequentialGroup()
                        .addGroup(jpGastosLocales6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpGastosLocales6Layout.createSequentialGroup()
                                .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSubConceptoSueldosPrestamos))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpGastosLocales6Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(jpGastosLocales6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel69, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jdcFechaSueldosPrestamos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jpGastosLocales6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                    .addComponent(cmbConceptoSueldoPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel70, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jpGastosLocales6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel71, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtImporteSueldoPrestamos, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpGastosLocales6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel72)
                            .addGroup(jpGastosLocales6Layout.createSequentialGroup()
                                .addComponent(txtComentariosSueldosPrestamos, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAnotarSueldosPrestamos))))
                    .addComponent(jScrollPane17))
                .addGap(135, 135, 135))
        );

        jpGastosLocales6Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnAnotarSueldosPrestamos, cmbConceptoSueldoPrestamo, jLabel69, jLabel70, jLabel71, jLabel72, jdcFechaSueldosPrestamos, txtComentariosSueldosPrestamos, txtImporteSueldoPrestamos});

        jpGastosLocales6Layout.setVerticalGroup(
            jpGastosLocales6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpGastosLocales6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpGastosLocales6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpGastosLocales6Layout.createSequentialGroup()
                        .addComponent(jLabel69)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jdcFechaSueldosPrestamos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpGastosLocales6Layout.createSequentialGroup()
                        .addGroup(jpGastosLocales6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel71)
                            .addComponent(jLabel72))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpGastosLocales6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(txtComentariosSueldosPrestamos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtImporteSueldoPrestamos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbConceptoSueldoPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAnotarSueldosPrestamos, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpGastosLocales6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel79)
                    .addComponent(txtSubConceptoSueldosPrestamos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane17, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpGastosLocales6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpGastosLocales6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel42)
                        .addComponent(btnBorrarGasto6)
                        .addComponent(btnRegistrarSueldoPrestamos))
                    .addComponent(btnSalirRegistroGastos6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("SUEL-PREST-DEPO", jpGastosLocales6);

        jpGastosTotales.setBackground(new java.awt.Color(255, 255, 255));
        jpGastosTotales.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jpGastosTotalesComponentShown(evt);
            }
        });

        btnSalirResumen1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSalirResumen1.setForeground(new java.awt.Color(0, 153, 51));
        btnSalirResumen1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/exit2.png"))); // NOI18N
        btnSalirResumen1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirResumen1ActionPerformed(evt);
            }
        });

        jLabel43.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(0, 102, 153));
        jLabel43.setText("Salir");

        jtTablaGastosTotales.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane5.setViewportView(jtTablaGastosTotales);

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

        cmbConceptoTotal.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        cmbConceptoTotal.setForeground(new java.awt.Color(0, 102, 153));
        cmbConceptoTotal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TODOS", "LOCALES", "GASTOS PERSONALES", "OFICINA BODEGA", "GASOLINA", "MANTNIMIENTO DE VEHICULOS", "PROVEEDORES", "SUELDOS-PRESTAMOS" }));

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

        javax.swing.GroupLayout jpGastosTotalesLayout = new javax.swing.GroupLayout(jpGastosTotales);
        jpGastosTotales.setLayout(jpGastosTotalesLayout);
        jpGastosTotalesLayout.setHorizontalGroup(
            jpGastosTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpGastosTotalesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpGastosTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpGastosTotalesLayout.createSequentialGroup()
                        .addGroup(jpGastosTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jpGastosTotalesLayout.createSequentialGroup()
                                .addComponent(jLabel58, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(25, 25, 25)
                                .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jdcFechaConceptoTotalInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jdcFechaConceptoTotalFin, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jpGastosTotalesLayout.createSequentialGroup()
                                .addComponent(Concepto, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbConceptoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 702, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(1, 1, 1))
                    .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jpGastosTotalesLayout.createSequentialGroup()
                        .addComponent(btnSalirResumen1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel43)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEliminarConcepto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCorregirConcepto))
                    .addComponent(jScrollPane5))
                .addGap(137, 137, 137))
        );

        jpGastosTotalesLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel37, jLabel53, jdcFechaConceptoTotalFin, jdcFechaConceptoTotalInicio});

        jpGastosTotalesLayout.setVerticalGroup(
            jpGastosTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpGastosTotalesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpGastosTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel53)
                    .addComponent(jdcFechaConceptoTotalInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37)
                    .addComponent(jdcFechaConceptoTotalFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel58))
                .addGap(34, 34, 34)
                .addGroup(jpGastosTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Concepto, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbConceptoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel39)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpGastosTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnCorregirConcepto, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel43)
                    .addComponent(btnSalirResumen1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminarConcepto, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
        );

        jTabbedPane1.addTab("CORREGIR GASTOS", jpGastosTotales);

        jpResumenConceptos.setBackground(new java.awt.Color(255, 255, 255));
        jpResumenConceptos.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jpResumenConceptosComponentShown(evt);
            }
        });

        jLabel63.setBackground(new java.awt.Color(0, 102, 153));
        jLabel63.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(255, 255, 255));
        jLabel63.setText("PERIODO TRABAJADO");
        jLabel63.setOpaque(true);

        jLabel68.setBackground(new java.awt.Color(204, 204, 255));
        jLabel68.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel68.setForeground(new java.awt.Color(0, 102, 153));
        jLabel68.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel68.setText("FECHA INICIO");
        jLabel68.setOpaque(true);

        jLabel41.setBackground(new java.awt.Color(204, 204, 255));
        jLabel41.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(0, 102, 153));
        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel41.setText("FECHA FIN");
        jLabel41.setOpaque(true);

        jLabel73.setBackground(new java.awt.Color(255, 255, 255));
        jLabel73.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel73.setForeground(new java.awt.Color(0, 102, 102));
        jLabel73.setText("LOCALES");
        jLabel73.setOpaque(true);

        jLabel74.setBackground(new java.awt.Color(255, 255, 255));
        jLabel74.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel74.setForeground(new java.awt.Color(0, 102, 102));
        jLabel74.setText("GASTOS PERSONALES");
        jLabel74.setOpaque(true);

        jLabel75.setBackground(new java.awt.Color(255, 255, 255));
        jLabel75.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel75.setForeground(new java.awt.Color(0, 102, 102));
        jLabel75.setText("OFICINA BODEGA");
        jLabel75.setOpaque(true);

        jLabel76.setBackground(new java.awt.Color(255, 255, 255));
        jLabel76.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel76.setForeground(new java.awt.Color(0, 102, 102));
        jLabel76.setText("GASOLINA");
        jLabel76.setOpaque(true);

        jLabel77.setBackground(new java.awt.Color(255, 255, 255));
        jLabel77.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel77.setForeground(new java.awt.Color(0, 102, 102));
        jLabel77.setText("MANTENIMIENTO V");
        jLabel77.setOpaque(true);

        jLabel78.setBackground(new java.awt.Color(255, 255, 255));
        jLabel78.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel78.setForeground(new java.awt.Color(0, 102, 102));
        jLabel78.setText("PROVEEDORES");
        jLabel78.setOpaque(true);

        jLabel80.setBackground(new java.awt.Color(255, 255, 255));
        jLabel80.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel80.setForeground(new java.awt.Color(0, 102, 102));
        jLabel80.setText("SUELDOS/ PRESTAMOS");
        jLabel80.setOpaque(true);

        jLabel81.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel81.setForeground(new java.awt.Color(102, 0, 102));
        jLabel81.setText("GASTOS GENERALES");

        lblTituloResultados.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        lblTituloResultados.setForeground(new java.awt.Color(0, 51, 102));
        lblTituloResultados.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTituloResultados.setText("TOTAL DE CONCEPTOS");

        lblConceptoLocal.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lblConceptoLocal.setForeground(new java.awt.Color(102, 102, 102));
        lblConceptoLocal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblConceptoLocal.setText("0");
        lblConceptoLocal.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblConceptoGastoPersonal.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lblConceptoGastoPersonal.setForeground(new java.awt.Color(102, 102, 102));
        lblConceptoGastoPersonal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblConceptoGastoPersonal.setText("0");
        lblConceptoGastoPersonal.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblConceptoOficinaBodega.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lblConceptoOficinaBodega.setForeground(new java.awt.Color(102, 102, 102));
        lblConceptoOficinaBodega.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblConceptoOficinaBodega.setText("0");
        lblConceptoOficinaBodega.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblConceptoGasolina.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lblConceptoGasolina.setForeground(new java.awt.Color(102, 102, 102));
        lblConceptoGasolina.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblConceptoGasolina.setText("0");
        lblConceptoGasolina.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblConceptoMantenimientoVehiculos.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lblConceptoMantenimientoVehiculos.setForeground(new java.awt.Color(102, 102, 102));
        lblConceptoMantenimientoVehiculos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblConceptoMantenimientoVehiculos.setText("0");
        lblConceptoMantenimientoVehiculos.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblConceptoProveedores.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lblConceptoProveedores.setForeground(new java.awt.Color(102, 102, 102));
        lblConceptoProveedores.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblConceptoProveedores.setText("0");
        lblConceptoProveedores.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblConceptoSueldosPrestamos.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lblConceptoSueldosPrestamos.setForeground(new java.awt.Color(102, 102, 102));
        lblConceptoSueldosPrestamos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblConceptoSueldosPrestamos.setText("0");
        lblConceptoSueldosPrestamos.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblTotalConceptoResumen.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lblTotalConceptoResumen.setForeground(new java.awt.Color(102, 102, 102));
        lblTotalConceptoResumen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotalConceptoResumen.setText("0");
        lblTotalConceptoResumen.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblTotalProveedoresEntreTiendas.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lblTotalProveedoresEntreTiendas.setForeground(new java.awt.Color(102, 102, 102));
        lblTotalProveedoresEntreTiendas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotalProveedoresEntreTiendas.setText("0");
        lblTotalProveedoresEntreTiendas.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel82.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel82.setForeground(new java.awt.Color(102, 0, 102));
        jLabel82.setText("COMPRAS\n");

        jLabel83.setBackground(new java.awt.Color(255, 255, 255));
        jLabel83.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel83.setForeground(new java.awt.Color(0, 102, 102));
        jLabel83.setText("TIENDAS");
        jLabel83.setOpaque(true);

        lblTiendasAbiertas.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lblTiendasAbiertas.setForeground(new java.awt.Color(102, 102, 102));
        lblTiendasAbiertas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTiendasAbiertas.setText("0");
        lblTiendasAbiertas.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jpResumenConceptosLayout = new javax.swing.GroupLayout(jpResumenConceptos);
        jpResumenConceptos.setLayout(jpResumenConceptosLayout);
        jpResumenConceptosLayout.setHorizontalGroup(
            jpResumenConceptosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpResumenConceptosLayout.createSequentialGroup()
                .addGroup(jpResumenConceptosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpResumenConceptosLayout.createSequentialGroup()
                        .addGap(367, 367, 367)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE))
                    .addGroup(jpResumenConceptosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jpResumenConceptosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpResumenConceptosLayout.createSequentialGroup()
                                .addGroup(jpResumenConceptosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                    .addComponent(jLabel74)
                                    .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel76)
                                    .addComponent(jLabel77)
                                    .addComponent(jLabel80)
                                    .addComponent(jLabel81))
                                .addGroup(jpResumenConceptosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jpResumenConceptosLayout.createSequentialGroup()
                                        .addGap(40, 40, 40)
                                        .addGroup(jpResumenConceptosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(lblConceptoGastoPersonal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lblConceptoOficinaBodega, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lblConceptoGasolina, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lblConceptoMantenimientoVehiculos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lblConceptoSueldosPrestamos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpResumenConceptosLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblTotalConceptoResumen, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(53, 53, 53)
                        .addGroup(jpResumenConceptosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel78, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel83, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jpResumenConceptosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(lblConceptoLocal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblConceptoProveedores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTiendasAbiertas, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTotalProveedoresEntreTiendas, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jpResumenConceptosLayout.createSequentialGroup()
                        .addGap(367, 367, 367)
                        .addComponent(jLabel82, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(158, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpResumenConceptosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel63, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpResumenConceptosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpResumenConceptosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jdcFechaConceptoResumenFin, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jdcFechaConceptoResumenInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
            .addComponent(lblTituloResultados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jpResumenConceptosLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel41, jLabel68, jdcFechaConceptoResumenFin, jdcFechaConceptoResumenInicio});

        jpResumenConceptosLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel73, jLabel74, jLabel75, jLabel76, jLabel77, jLabel78, jLabel80, jLabel81, jLabel83});

        jpResumenConceptosLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {lblConceptoGasolina, lblConceptoGastoPersonal, lblConceptoLocal, lblConceptoMantenimientoVehiculos, lblConceptoOficinaBodega, lblConceptoProveedores, lblConceptoSueldosPrestamos, lblTotalConceptoResumen});

        jpResumenConceptosLayout.setVerticalGroup(
            jpResumenConceptosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpResumenConceptosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpResumenConceptosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel63)
                    .addComponent(jLabel68)
                    .addComponent(jdcFechaConceptoResumenInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpResumenConceptosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel41)
                    .addComponent(jdcFechaConceptoResumenFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(61, 61, 61)
                .addComponent(lblTituloResultados, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addGroup(jpResumenConceptosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel74)
                    .addComponent(lblConceptoGastoPersonal)
                    .addComponent(jLabel73)
                    .addComponent(lblConceptoLocal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpResumenConceptosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel75)
                    .addComponent(lblConceptoOficinaBodega))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpResumenConceptosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel76)
                    .addComponent(lblConceptoGasolina))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpResumenConceptosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel77)
                    .addComponent(lblConceptoMantenimientoVehiculos)
                    .addComponent(jLabel78)
                    .addComponent(lblConceptoProveedores))
                .addGroup(jpResumenConceptosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel80)
                    .addComponent(lblConceptoSueldosPrestamos)
                    .addComponent(jLabel83)
                    .addComponent(lblTiendasAbiertas))
                .addGroup(jpResumenConceptosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpResumenConceptosLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpResumenConceptosLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpResumenConceptosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel81)
                    .addComponent(lblTotalConceptoResumen)
                    .addComponent(jLabel82)
                    .addComponent(lblTotalProveedoresEntreTiendas))
                .addContainerGap(123, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("RESUMEN GASTOS", jpResumenConceptos);

        jsConceptos.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jsConceptosComponentShown(evt);
            }
        });

        jpConceptos.setBackground(new java.awt.Color(255, 255, 255));
        jpConceptos.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jpConceptosComponentShown(evt);
            }
        });

        jtLocal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Clave", "Local"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtLocal.setOpaque(false);
        jScrollPane2.setViewportView(jtLocal);
        if (jtLocal.getColumnModel().getColumnCount() > 0) {
            jtLocal.getColumnModel().getColumn(0).setMinWidth(50);
            jtLocal.getColumnModel().getColumn(0).setPreferredWidth(50);
            jtLocal.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        jLabel48.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(0, 102, 153));
        jLabel48.setText("MIS LOCALES");

        btnCorregirConcepto1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnCorregirConcepto1.setForeground(new java.awt.Color(0, 153, 51));
        btnCorregirConcepto1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/correct.png"))); // NOI18N
        btnCorregirConcepto1.setText("Corregir");
        btnCorregirConcepto1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCorregirConcepto1ActionPerformed(evt);
            }
        });

        btnEliminarLocal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnEliminarLocal.setForeground(new java.awt.Color(0, 153, 51));
        btnEliminarLocal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/delete.png"))); // NOI18N
        btnEliminarLocal.setText("Eliminar");
        btnEliminarLocal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnEliminarLocal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarLocalActionPerformed(evt);
            }
        });

        jLabel86.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel86.setForeground(new java.awt.Color(0, 102, 153));
        jLabel86.setText("ADMINISTRAS MIS LOCALES");

        btnNuevoLocal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnNuevoLocal.setForeground(new java.awt.Color(0, 153, 51));
        btnNuevoLocal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/New.png"))); // NOI18N
        btnNuevoLocal.setText("Nuevo");
        btnNuevoLocal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoLocalActionPerformed(evt);
            }
        });

        jLabel87.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel87.setForeground(new java.awt.Color(0, 153, 0));
        jLabel87.setText("ADMINISTRAS MIS CONCEPTOS");

        btnNuevoConcepto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnNuevoConcepto.setForeground(new java.awt.Color(0, 153, 51));
        btnNuevoConcepto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/New.png"))); // NOI18N
        btnNuevoConcepto.setText("Nuevo");
        btnNuevoConcepto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoConceptoActionPerformed(evt);
            }
        });

        txtConceptoNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtConceptoNuevoActionPerformed(evt);
            }
        });

        jtConceptos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Clave", "Concepto"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jtConceptos);
        if (jtConceptos.getColumnModel().getColumnCount() > 0) {
            jtConceptos.getColumnModel().getColumn(0).setMinWidth(50);
            jtConceptos.getColumnModel().getColumn(0).setPreferredWidth(50);
            jtConceptos.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        jLabel88.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel88.setForeground(new java.awt.Color(0, 153, 0));
        jLabel88.setText("MIS CONCEPTOS");

        btnEditarConceptos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnEditarConceptos.setForeground(new java.awt.Color(0, 153, 51));
        btnEditarConceptos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/correct.png"))); // NOI18N
        btnEditarConceptos.setText("Corregir");
        btnEditarConceptos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarConceptosActionPerformed(evt);
            }
        });

        btnBorrarConcepto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnBorrarConcepto.setForeground(new java.awt.Color(0, 153, 51));
        btnBorrarConcepto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/delete.png"))); // NOI18N
        btnBorrarConcepto.setText("Eliminar");
        btnBorrarConcepto.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnBorrarConcepto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarConceptoActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/warehouse.png"))); // NOI18N

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/shopcart.png"))); // NOI18N

        javax.swing.GroupLayout jpConceptosLayout = new javax.swing.GroupLayout(jpConceptos);
        jpConceptos.setLayout(jpConceptosLayout);
        jpConceptosLayout.setHorizontalGroup(
            jpConceptosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpConceptosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpConceptosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpConceptosLayout.createSequentialGroup()
                        .addGroup(jpConceptosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jpConceptosLayout.createSequentialGroup()
                                .addComponent(txtLocalNuevo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnNuevoLocal))
                            .addGroup(jpConceptosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel86, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jpConceptosLayout.createSequentialGroup()
                                    .addComponent(btnEliminarLocal, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnCorregirConcepto1))
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jpConceptosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpConceptosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jpConceptosLayout.createSequentialGroup()
                            .addComponent(txtConceptoNuevo)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnNuevoConcepto))
                        .addGroup(jpConceptosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel87, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jpConceptosLayout.createSequentialGroup()
                                .addComponent(btnBorrarConcepto, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEditarConceptos))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel88, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jpConceptosLayout.setVerticalGroup(
            jpConceptosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpConceptosLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jpConceptosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpConceptosLayout.createSequentialGroup()
                        .addComponent(jLabel88)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpConceptosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnNuevoConcepto, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtConceptoNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpConceptosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpConceptosLayout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpConceptosLayout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44)))
                        .addComponent(jLabel87)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpConceptosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnBorrarConcepto, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEditarConceptos, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jpConceptosLayout.createSequentialGroup()
                        .addComponent(jLabel48)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpConceptosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnNuevoLocal, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtLocalNuevo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpConceptosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpConceptosLayout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(46, 46, 46)))
                        .addComponent(jLabel86)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpConceptosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnEliminarLocal, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCorregirConcepto1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(71, Short.MAX_VALUE))
        );

        jsConceptos.setViewportView(jpConceptos);

        jTabbedPane1.addTab("+CONCEPTOS", jsConceptos);

        jpProveedoresClientes.setBackground(new java.awt.Color(255, 255, 255));
        jpProveedoresClientes.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jpProveedoresClientesComponentShown(evt);
            }
        });

        jtProveedor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Clave", "Local"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtProveedor.setOpaque(false);
        jsProvedorCliente.setViewportView(jtProveedor);
        if (jtProveedor.getColumnModel().getColumnCount() > 0) {
            jtProveedor.getColumnModel().getColumn(0).setMinWidth(50);
            jtProveedor.getColumnModel().getColumn(0).setPreferredWidth(50);
            jtProveedor.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        jLabel85.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel85.setForeground(new java.awt.Color(0, 102, 153));
        jLabel85.setText("MIS PROVEEDORES");

        btnCorregirProveedor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnCorregirProveedor.setForeground(new java.awt.Color(0, 153, 51));
        btnCorregirProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/correct.png"))); // NOI18N
        btnCorregirProveedor.setText("Corregir");
        btnCorregirProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCorregirProveedorActionPerformed(evt);
            }
        });

        btnEliminarProveedor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnEliminarProveedor.setForeground(new java.awt.Color(0, 153, 51));
        btnEliminarProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/delete.png"))); // NOI18N
        btnEliminarProveedor.setText("Eliminar");
        btnEliminarProveedor.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnEliminarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarProveedorActionPerformed(evt);
            }
        });

        jLabel89.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel89.setForeground(new java.awt.Color(0, 102, 153));
        jLabel89.setText("ADMINISTRAS MIS LOCALES");

        btnNuevoProveedor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnNuevoProveedor.setForeground(new java.awt.Color(0, 153, 51));
        btnNuevoProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/New.png"))); // NOI18N
        btnNuevoProveedor.setText("Nuevo");
        btnNuevoProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoProveedorActionPerformed(evt);
            }
        });

        jLabel90.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel90.setForeground(new java.awt.Color(0, 153, 0));
        jLabel90.setText("ADMINISTRAS MIS CONCEPTOS");

        btnNuevoCliente.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnNuevoCliente.setForeground(new java.awt.Color(0, 153, 51));
        btnNuevoCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/New.png"))); // NOI18N
        btnNuevoCliente.setText("Nuevo");
        btnNuevoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoClienteActionPerformed(evt);
            }
        });

        txtClienteNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtClienteNuevoActionPerformed(evt);
            }
        });

        jtClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Clave", "Concepto"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane7.setViewportView(jtClientes);
        if (jtClientes.getColumnModel().getColumnCount() > 0) {
            jtClientes.getColumnModel().getColumn(0).setMinWidth(50);
            jtClientes.getColumnModel().getColumn(0).setPreferredWidth(50);
            jtClientes.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        jLabel91.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel91.setForeground(new java.awt.Color(0, 153, 0));
        jLabel91.setText("MIS CLIENTES");

        btnEditarCliente.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnEditarCliente.setForeground(new java.awt.Color(0, 153, 51));
        btnEditarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/correct.png"))); // NOI18N
        btnEditarCliente.setText("Corregir");
        btnEditarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarClienteActionPerformed(evt);
            }
        });

        btnBorrarCliente.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnBorrarCliente.setForeground(new java.awt.Color(0, 153, 51));
        btnBorrarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/delete.png"))); // NOI18N
        btnBorrarCliente.setText("Eliminar");
        btnBorrarCliente.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnBorrarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarClienteActionPerformed(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/Employee.png"))); // NOI18N

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/provider.png"))); // NOI18N

        javax.swing.GroupLayout jpProveedoresClientesLayout = new javax.swing.GroupLayout(jpProveedoresClientes);
        jpProveedoresClientes.setLayout(jpProveedoresClientesLayout);
        jpProveedoresClientesLayout.setHorizontalGroup(
            jpProveedoresClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpProveedoresClientesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpProveedoresClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpProveedoresClientesLayout.createSequentialGroup()
                        .addGroup(jpProveedoresClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jpProveedoresClientesLayout.createSequentialGroup()
                                .addComponent(txtProveedorNuevo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnNuevoProveedor))
                            .addGroup(jpProveedoresClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel89, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jpProveedoresClientesLayout.createSequentialGroup()
                                    .addComponent(btnEliminarProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnCorregirProveedor))
                                .addComponent(jsProvedorCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel85, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addGroup(jpProveedoresClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpProveedoresClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jpProveedoresClientesLayout.createSequentialGroup()
                            .addComponent(txtClienteNuevo)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnNuevoCliente))
                        .addGroup(jpProveedoresClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel90, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jpProveedoresClientesLayout.createSequentialGroup()
                                .addComponent(btnBorrarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEditarCliente))
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel91, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jpProveedoresClientesLayout.setVerticalGroup(
            jpProveedoresClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpProveedoresClientesLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jpProveedoresClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpProveedoresClientesLayout.createSequentialGroup()
                        .addComponent(jLabel91)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpProveedoresClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnNuevoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtClienteNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpProveedoresClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpProveedoresClientesLayout.createSequentialGroup()
                                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpProveedoresClientesLayout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44)))
                        .addComponent(jLabel90)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpProveedoresClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnBorrarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEditarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jpProveedoresClientesLayout.createSequentialGroup()
                        .addComponent(jLabel85)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpProveedoresClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnNuevoProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtProveedorNuevo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpProveedoresClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jsProvedorCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpProveedoresClientesLayout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(46, 46, 46)))
                        .addComponent(jLabel89)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpProveedoresClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnEliminarProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCorregirProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("+PROVEEDORES", jpProveedoresClientes);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 503, Short.MAX_VALUE)
                .addGap(4, 4, 4))
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
        anotarConcepto(
                jtTablaSueldosPrestamos,
                FechaHerramienta.formatoYMD(jdcFechaSueldosPrestamos.getDate()),
                cmbConceptoSueldoPrestamo.getSelectedItem().toString(),
                txtSubConceptoSueldosPrestamos.getText(),
                txtImporteSueldoPrestamos.getText(),
                txtComentariosSueldosPrestamos.getText());
    }//GEN-LAST:event_btnAnotarSueldosPrestamosActionPerformed

    private void btnRegistrarSueldoPrestamosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarSueldoPrestamosActionPerformed
        guardarConceptoSubconcepto(jtTablaSueldosPrestamos, "SUELDOS PRESTAMOS");
        limpiarCampos();
    }//GEN-LAST:event_btnRegistrarSueldoPrestamosActionPerformed

    private void btnBorrarGasto6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarGasto6ActionPerformed
        quitarFila(jtTablaSueldosPrestamos);
    }//GEN-LAST:event_btnBorrarGasto6ActionPerformed

    private void btnSalirRegistroGastos6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirRegistroGastos6ActionPerformed
        salir();        // TODO add your handling code here:
    }//GEN-LAST:event_btnSalirRegistroGastos6ActionPerformed

    private void txtImporteProveedoresKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtImporteProveedoresKeyTyped
        char key = evt.getKeyChar();
        if (((key < '0') || (key > '9')) && (key != evt.VK_BACK_SPACE)
                && (key != '.')) {
            evt.consume();
        }
        if (key == '.' && txtImporteProveedores.getText().contains(".")) {
            evt.consume();
            JOptionPane.showMessageDialog(this, "Solo se permite un punto "
                    + "este campo", "Validacion decimal", 0);
        }
    }//GEN-LAST:event_txtImporteProveedoresKeyTyped

    private void btnAnotarProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnotarProveedoresActionPerformed
        anotarConceptoProveedor(
                jtTablaProveedores,
                FechaHerramienta.formatoYMD(jdcFechaProveedores.getDate()),
                txtFolio.getText(),
                cmbProveedor.getSelectedItem().toString(),
                txtImporteProveedores.getText(),
                "0",
                cmbFormaDePago.getSelectedItem().toString());
    }//GEN-LAST:event_btnAnotarProveedoresActionPerformed

    private void btnRegistrarProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarProveedoresActionPerformed
        guardarConcepto(jtTablaProveedores, "PROVEEDORES");
        limpiarCampos();
    }//GEN-LAST:event_btnRegistrarProveedoresActionPerformed

    private void btnBorrarGasto5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarGasto5ActionPerformed
        quitarFila(jtTablaProveedores);
    }//GEN-LAST:event_btnBorrarGasto5ActionPerformed

    private void btnSalirRegistroGastos5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirRegistroGastos5ActionPerformed
        salir();        // TODO add your handling code here:
    }//GEN-LAST:event_btnSalirRegistroGastos5ActionPerformed

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
                cmbConceptoMantenimiento.getSelectedItem().toString(),
                txtImporteMantenimientoV.getText(),
                txtComentarioMantenimientoV.getText());
    }//GEN-LAST:event_btnAnotarMantenimientoVActionPerformed

    private void btnRegistrarMantenimientoVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarMantenimientoVActionPerformed
        guardarConcepto(jtTablaMantenimientoV, "MANTENIMIENTO V");
        limpiarCampos();
    }//GEN-LAST:event_btnRegistrarMantenimientoVActionPerformed

    private void btnBorrarGasto4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarGasto4ActionPerformed
        quitarFila(jtTablaMantenimientoV);
    }//GEN-LAST:event_btnBorrarGasto4ActionPerformed

    private void btnSalirRegistroGastos4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirRegistroGastos4ActionPerformed
        salir();        // TODO add your handling code here:
    }//GEN-LAST:event_btnSalirRegistroGastos4ActionPerformed

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
                cmbConceptoGasolina.getSelectedItem().toString(),
                txtImporteGasolina.getText(),
                txtComentariosGasolina.getText());
    }//GEN-LAST:event_btnAnotarGasolinaActionPerformed

    private void btnRegistrarGasolinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarGasolinaActionPerformed
        guardarConcepto(jtTablaGasolina, "GASOLINA");
        limpiarCampos();
    }//GEN-LAST:event_btnRegistrarGasolinaActionPerformed

    private void btnBorrarGasto3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarGasto3ActionPerformed
        quitarFila(jtTablaGasolina);
    }//GEN-LAST:event_btnBorrarGasto3ActionPerformed

    private void btnSalirRegistroGastos3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirRegistroGastos3ActionPerformed
        salir();        // TODO add your handling code here:
    }//GEN-LAST:event_btnSalirRegistroGastos3ActionPerformed

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
        anotarConcepto(
                jtTablaOficinaBodega,
                FechaHerramienta.formatoYMD(jdcFechaOficinaBodega.getDate()),
                cmbConceptoOficinaBodega.getSelectedItem().toString(),
                txtImporteOficinaBodega.getText(),
                txtComentariosOficinaBodega.getText());
    }//GEN-LAST:event_btnAnotarOficinaBodegaActionPerformed

    private void btnRegistrarOficinaBodegaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarOficinaBodegaActionPerformed
        guardarConcepto(jtTablaOficinaBodega, "OFICINA BODEGA");
        limpiarCampos();
    }//GEN-LAST:event_btnRegistrarOficinaBodegaActionPerformed

    private void btnBorrarGasto2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarGasto2ActionPerformed
        quitarFila(jtTablaOficinaBodega);
    }//GEN-LAST:event_btnBorrarGasto2ActionPerformed

    private void btnSalirRegistroGastos2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirRegistroGastos2ActionPerformed
        salir();        // TODO add your handling code here:
    }//GEN-LAST:event_btnSalirRegistroGastos2ActionPerformed

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
        anotarConcepto(
                jtTablaGastosPersonales,
                FechaHerramienta.formatoYMD(jdcFechaGastosPersonales.getDate()),
                cmbConceptoGastosPersonales.getSelectedItem().toString(),
                txtImporteGastosPersonales.getText(),
                txtComentarioGastosPersonales.getText());
    }//GEN-LAST:event_btnAnotarGastosPersonalesActionPerformed

    private void btnRegistrarGastosPersonalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarGastosPersonalesActionPerformed
        guardarConcepto(jtTablaGastosPersonales, "GASTOS PERSONALES");
        limpiarCampos();
    }//GEN-LAST:event_btnRegistrarGastosPersonalesActionPerformed

    private void btnBorrarGasto1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarGasto1ActionPerformed
        quitarFila(jtTablaGastosPersonales);
    }//GEN-LAST:event_btnBorrarGasto1ActionPerformed

    private void btnSalirRegistroGastos1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirRegistroGastos1ActionPerformed
        salir();        // TODO add your handling code here:
    }//GEN-LAST:event_btnSalirRegistroGastos1ActionPerformed

    private void btnRegistrarLocalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarLocalesActionPerformed
        guardarConceptoSubconcepto(jtTablaLocales, "LOCALES");
        limpiarCampos();
    }//GEN-LAST:event_btnRegistrarLocalesActionPerformed

    private void btnBorrarGastoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarGastoActionPerformed
        quitarFila(jtTablaLocales);
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
        anotarConcepto(
                jtTablaLocales,
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
    private void btnSalirRegistroGastosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirRegistroGastosActionPerformed
        salir();
    }//GEN-LAST:event_btnSalirRegistroGastosActionPerformed

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

    private void txtSubConceptoSueldosPrestamosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSubConceptoSueldosPrestamosKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSubConceptoSueldosPrestamosKeyTyped

    private void btnSalirResumen1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirResumen1ActionPerformed
        salir();
    }//GEN-LAST:event_btnSalirResumen1ActionPerformed

    private void btnCorregirConceptoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCorregirConceptoActionPerformed
        if (jtTablaGastosTotales.getSelectedRow() != -1) {
            String clave = jtTablaGastosTotales.getValueAt(jtTablaGastosTotales.getSelectedRow(), 0).toString();
            try {
                CorregirGastoGeneral corregirGastoGeneral = new CorregirGastoGeneral(clave);
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
        if (jtTablaGastosTotales.getSelectedRow() != -1) {
            int respuesta = JOptionPane.showConfirmDialog(this, "¿Desea eliminar este gasto?", "Confirmar eliminación", 0, 3);
            if (respuesta == 0) {
                try {
                    AccesoBD acceso = new AccesoBD();
                    int clave = (int) jtTablaGastosTotales.getValueAt(jtTablaGastosTotales.getSelectedRow(), 0);
                    String query = "From Gastosgenerales g WHERE g.idGastosGenerales = '" + clave + "'";
                    Gastosgenerales concepto = (Gastosgenerales) acceso.select(query).get(0);
                    acceso.delete(concepto);
                    crearConsultaGlobal();
                    llenarTabla(jtTablaGastosTotales, "Gastosgenerales", HQL);
                    JOptionPane.showMessageDialog(this, "Venta eliminada correctamente", "Cancelado", 1);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Ha ocurrido un error: " + e, "Error", 0);
                    System.gc();
                }
            }
        }
    }//GEN-LAST:event_btnEliminarConceptoActionPerformed

    private void jpGastosTotalesComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jpGastosTotalesComponentShown
        crearConsultaGlobal();
        cargaTabla(jtTablaGastosTotales, HQL, "Gastosgenerales");
    }//GEN-LAST:event_jpGastosTotalesComponentShown

    private void jpResumenConceptosComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jpResumenConceptosComponentShown
        jdcFechaConceptoResumenInicio.setDate(new Date());
        jdcFechaConceptoResumenFin.setDate(new Date());
        crearConsultaGlobal();
        crearResumen();
    }//GEN-LAST:event_jpResumenConceptosComponentShown

    private void txtFolioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFolioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFolioActionPerformed

    private void btnCorregirConcepto1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCorregirConcepto1ActionPerformed
        if (jtLocal.getSelectedRow() != -1) {
            int respuesta = JOptionPane.showConfirmDialog(this, "¿Desea editar este local?", "Confirmar editar", 0, 3);
            if (respuesta == 0) {
                try {
                    AccesoBD acceso = new AccesoBD();
                    int clave = (int) jtLocal.getValueAt(jtLocal.getSelectedRow(), 0);
                    String query = "From Sucursal s WHERE s.idSucursal = '" + clave + "'";
                    Sucursal concepto = (Sucursal) acceso.select(query).get(0);
                    String nombre = JOptionPane.showInputDialog(this, "Nuevo nombre");
                    concepto.setNombre(nombre);
                    acceso.Update(concepto);
                    query = "From Sucursal s ORDER BY s.idSucursal";
                    llenarTabla(jtLocal, "Sucursal", query);
                    JOptionPane.showMessageDialog(this, "Sucursal editada correctamente", "Cancelado", 1);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Ha ocurrido un error: " + e, "Error", 0);
                    System.gc();
                }
            }
        }
    }//GEN-LAST:event_btnCorregirConcepto1ActionPerformed

    private void btnEliminarLocalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarLocalActionPerformed
        if (jtLocal.getSelectedRow() != -1) {
            int respuesta = JOptionPane.showConfirmDialog(this, "¿Desea eliminar este local?", "Confirmar eliminación", 0, 3);
            if (respuesta == 0) {
                try {
                    AccesoBD acceso = new AccesoBD();
                    int clave = (int) jtLocal.getValueAt(jtLocal.getSelectedRow(), 0);
                    String query = "From Sucursal s WHERE s.idSucursal = '" + clave + "'";
                    Gastosgenerales concepto = (Gastosgenerales) acceso.select(query).get(0);
                    acceso.delete(concepto);
                    crearConsultaGlobal();
                    llenarTabla(jtLocal, "Sucursal", query);
                    JOptionPane.showMessageDialog(this, "Sucursal eliminada correctamente", "Cancelado", 1);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Ha ocurrido un error: " + e, "Error", 0);
                    System.gc();
                }
            }
        }
    }//GEN-LAST:event_btnEliminarLocalActionPerformed

    private void btnNuevoLocalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoLocalActionPerformed
        AccesoBD control = new AccesoBD();
        try {
            if (!txtLocalNuevo.getText().isEmpty()) {
                pojos.Sucursal local = new pojos.Sucursal();
                local.setNombre(txtLocalNuevo.getText());
                control.add(local);

                limpiarCampos();
                JOptionPane.showMessageDialog(this, "Local registrado correctamente", "Datos registrados", 1);
                String Query = "";
                Query = "FROM Sucursal s ORDER BY s.nombre";
                cargaTabla(jtLocal, Query, "Sucursal");
            } else {
                JOptionPane.showMessageDialog(this, "Favor de llenar el campo Nombre", "Datos incompletos", 1);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e, "Error", 0);
        }
    }//GEN-LAST:event_btnNuevoLocalActionPerformed

    private void btnNuevoConceptoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoConceptoActionPerformed
        AccesoBD control = new AccesoBD();
        try {
            if (!txtConceptoNuevo.getText().isEmpty()) {
                pojos.Linea concepto = new pojos.Linea();
                concepto.setNombre(txtConceptoNuevo.getText());
                control.add(concepto);
                limpiarCampos();
                JOptionPane.showMessageDialog(this, "Concepto registrado correctamente", "Datos registrados", 1);
                String Query = "FROM Linea l ORDER BY l.nombre";
                cargaTabla(jtConceptos, Query, "Linea");
            } else {
                JOptionPane.showMessageDialog(this, "Favor de llenar el campo Nombre", "Datos incompletos", 1);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e, "Error", 0);
        }

    }//GEN-LAST:event_btnNuevoConceptoActionPerformed

    private void btnEditarConceptosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarConceptosActionPerformed
        if (jtConceptos.getSelectedRow() != -1) {
            int respuesta = JOptionPane.showConfirmDialog(this, "¿Desea editar este concepto?", "Confirmar editar", 0, 3);
            if (respuesta == 0) {
                try {
                    AccesoBD acceso = new AccesoBD();
                    int clave = (int) jtConceptos.getValueAt(jtConceptos.getSelectedRow(), 0);
                    String query = "From Linea l WHERE l.idLinea = '" + clave + "'";
                    Linea concepto = (Linea) acceso.select(query).get(0);
                    String nombre = JOptionPane.showInputDialog(this, "Nuevo nombre");
                    concepto.setNombre(nombre);
                    acceso.Update(concepto);
                    query = "From Linea s ORDER BY s.idLinea";
                    llenarTabla(jtConceptos, "Linea", query);
                    JOptionPane.showMessageDialog(this, "Concepto editado correctamente", "Cancelado", 1);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Ha ocurrido un error: " + e, "Error", 0);
                    System.gc();
                }
            }
        }
    }//GEN-LAST:event_btnEditarConceptosActionPerformed

    private void btnBorrarConceptoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarConceptoActionPerformed
        if (jtConceptos.getSelectedRow() != -1) {
            int respuesta = JOptionPane.showConfirmDialog(this, "¿Desea eliminar este concepto?", "Confirmar eliminación", 0, 3);
            if (respuesta == 0) {
                try {
                    AccesoBD acceso = new AccesoBD();
                    int clave = (int) jtConceptos.getValueAt(jtConceptos.getSelectedRow(), 0);
                    String query = "From Linea l WHERE l.idLinea = '" + clave + "'";
                    Gastosgenerales concepto = (Gastosgenerales) acceso.select(query).get(0);
                    acceso.delete(concepto);
                    crearConsultaGlobal();
                    llenarTabla(jtLocal, "Linea", query);
                    JOptionPane.showMessageDialog(this, "Concepto eliminado correctamente", "Cancelado", 1);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Ha ocurrido un error: " + e, "Error", 0);
                    System.gc();
                }
            }
        }
    }//GEN-LAST:event_btnBorrarConceptoActionPerformed

    private void jsConceptosComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jsConceptosComponentShown
        String Query = "FROM Linea l ORDER BY l.nombre";
        cargaTabla(jtConceptos, Query, "Linea");
        Query = "FROM Sucursal s ORDER BY s.nombre";
        cargaTabla(jtLocal, Query, "Sucursal");
    }//GEN-LAST:event_jsConceptosComponentShown

    private void txtConceptoNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtConceptoNuevoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtConceptoNuevoActionPerformed

    private void jpGastosLocales1ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jpGastosLocales1ComponentShown
        String query = "FROM Linea l ORDER BY l.nombre";
        llenarCombo(cmbConceptoGastosPersonales, query, "concepto");

    }//GEN-LAST:event_jpGastosLocales1ComponentShown

    private void jpGastosLocales2ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jpGastosLocales2ComponentShown
        String query = "FROM Linea l ORDER BY l.nombre";
        llenarCombo(cmbConceptoOficinaBodega, query, "concepto");
    }//GEN-LAST:event_jpGastosLocales2ComponentShown

    private void jpGastosLocales3ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jpGastosLocales3ComponentShown
        String query = "FROM Linea l ORDER BY l.nombre";
        llenarCombo(cmbConceptoGasolina, query, "concepto");
    }//GEN-LAST:event_jpGastosLocales3ComponentShown

    private void jpGastosLocales4ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jpGastosLocales4ComponentShown
        String query = "FROM Linea l ORDER BY l.nombre";
        llenarCombo(cmbConceptoMantenimiento, query, "concepto");
    }//GEN-LAST:event_jpGastosLocales4ComponentShown

    private void cmbConceptoLocalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbConceptoLocalesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbConceptoLocalesActionPerformed

    private void jpGastosLocalesComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jpGastosLocalesComponentShown
        String query = "FROM Sucursal s ORDER BY s.nombre";
        llenarCombo(cmbConceptoLocales, query, "local");
        query = "FROM Linea l ORDER BY l.nombre";
        llenarCombo(cmbSubConceptoLocales, query, "concepto");
    }//GEN-LAST:event_jpGastosLocalesComponentShown

    private void btnCorregirProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCorregirProveedorActionPerformed
        if (jtProveedor.getSelectedRow() != -1) {
            int respuesta = JOptionPane.showConfirmDialog(this, "¿Desea editar este proveedor?", "Confirmar editar", 0, 3);
            if (respuesta == 0) {
                try {
                    AccesoBD acceso = new AccesoBD();
                    int clave = (int) jtProveedor.getValueAt(jtProveedor.getSelectedRow(), 0);
                    String query = "From Proveedores s WHERE s.idProveedor = '" + clave + "'";
                    Proveedores proveedor = (Proveedores) acceso.select(query).get(0);
                    String nombre = JOptionPane.showInputDialog(this, "Nuevo nombre");
                    proveedor.setNombre(nombre);
                    acceso.Update(proveedor);
                    query = "From Proveedores s ORDER BY s.idProveedor";
                    llenarTabla(jtProveedor, "Proveedor", query);
                    JOptionPane.showMessageDialog(this, "Proveedor editada correctamente", "Cancelado", 1);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Ha ocurrido un error: " + e, "Error", 0);
                    System.gc();
                }
            }
        }
    }//GEN-LAST:event_btnCorregirProveedorActionPerformed

    private void btnEliminarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarProveedorActionPerformed
        if (jtProveedor.getSelectedRow() != -1) {
            int respuesta = JOptionPane.showConfirmDialog(this, "¿Desea eliminar este proveedor?", "Confirmar eliminación", 0, 3);
            if (respuesta == 0) {
                try {
                    AccesoBD acceso = new AccesoBD();
                    int clave = (int) jtProveedor.getValueAt(jtProveedor.getSelectedRow(), 0);
                    String query = "From Proveedores s WHERE s.idProveedor = '" + clave + "'";
                    Proveedores proveedor = (Proveedores) acceso.select(query).get(0);
                    acceso.delete(proveedor);
                    crearConsultaGlobal();
                    llenarTabla(jtProveedor, "Proveedor", query);
                    JOptionPane.showMessageDialog(this, "Proveedor eliminada correctamente", "Cancelado", 1);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Ha ocurrido un error: " + e, "Error", 0);
                    System.gc();
                }
            }
        }
    }//GEN-LAST:event_btnEliminarProveedorActionPerformed

    private void btnNuevoProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoProveedorActionPerformed
        AccesoBD control = new AccesoBD();
        try {
            if (!txtProveedorNuevo.getText().isEmpty()) {
                pojos.Proveedores proveedor = new pojos.Proveedores();
                proveedor.setNombre(txtProveedorNuevo.getText());
                control.add(proveedor);

                limpiarCampos();
                JOptionPane.showMessageDialog(this, "Proveedor registrado correctamente", "Datos registrados", 1);
                String Query = "";
                Query = "FROM Proveedores p ORDER BY p.nombre";
                cargaTabla(jtProveedor, Query, "Proveedor");
            } else {
                JOptionPane.showMessageDialog(this, "Favor de llenar el campo Nombre", "Datos incompletos", 1);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e, "Error", 0);
        }
    }//GEN-LAST:event_btnNuevoProveedorActionPerformed

    private void btnNuevoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoClienteActionPerformed
        AccesoBD control = new AccesoBD();
        try {
            if (!txtClienteNuevo.getText().isEmpty()) {
                pojos.Clientes cliente = new pojos.Clientes();
                cliente.setNombre(txtClienteNuevo.getText());
                control.add(cliente);

                limpiarCampos();
                JOptionPane.showMessageDialog(this, "Cliente registrado correctamente", "Datos registrados", 1);
                String Query = "";
                Query = "FROM Clientes c ORDER BY c.nombre";
                cargaTabla(jtClientes, Query, "Cliente");
            } else {
                JOptionPane.showMessageDialog(this, "Favor de llenar el campo Nombre", "Datos incompletos", 1);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e, "Error", 0);
        }
    }//GEN-LAST:event_btnNuevoClienteActionPerformed

    private void txtClienteNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtClienteNuevoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtClienteNuevoActionPerformed

    private void btnEditarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarClienteActionPerformed
        if (jtClientes.getSelectedRow() != -1) {
            int respuesta = JOptionPane.showConfirmDialog(this, "¿Desea editar este cliente?", "Confirmar editar", 0, 3);
            if (respuesta == 0) {
                try {
                    AccesoBD acceso = new AccesoBD();
                    int clave = (int) jtClientes.getValueAt(jtClientes.getSelectedRow(), 0);
                    String query = "From Clientes s WHERE s.idCliente = '" + clave + "'";
                    Clientes cliente = (Clientes) acceso.select(query).get(0);
                    String nombre = JOptionPane.showInputDialog(this, "Nuevo nombre");
                    cliente.setNombre(nombre);
                    acceso.Update(cliente);
                    query = "From Clientes c ORDER BY c.idCliente";
                    llenarTabla(jtClientes, "Cliente", query);
                    JOptionPane.showMessageDialog(this, "Cliente editada correctamente", "Cancelado", 1);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Ha ocurrido un error: " + e, "Error", 0);
                    System.gc();
                }
            }
        }
    }//GEN-LAST:event_btnEditarClienteActionPerformed

    private void btnBorrarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarClienteActionPerformed
        if (jtClientes.getSelectedRow() != -1) {
            int respuesta = JOptionPane.showConfirmDialog(this, "¿Desea eliminar este cliente?", "Confirmar eliminación", 0, 3);
            if (respuesta == 0) {
                try {
                    AccesoBD acceso = new AccesoBD();
                    int clave = (int) jtClientes.getValueAt(jtClientes.getSelectedRow(), 0);
                    String query = "From Clientes c WHERE c.idCliente = '" + clave + "'";
                    Clientes cliente = (Clientes) acceso.select(query).get(0);
                    acceso.delete(cliente);
                    crearConsultaGlobal();
                    llenarTabla(jtClientes, "Cliente", query);
                    JOptionPane.showMessageDialog(this, "Cliente eliminada correctamente", "Cancelado", 1);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Ha ocurrido un error: " + e, "Error", 0);
                    System.gc();
                }
            }
        }
    }//GEN-LAST:event_btnBorrarClienteActionPerformed

    private void jpProveedoresClientesComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jpProveedoresClientesComponentShown
        String Query = "";
        Query = "FROM Proveedores p ORDER BY p.nombre";
        cargaTabla(jtProveedor, Query, "Proveedor");
        Query = "FROM Clientes c ORDER BY c.nombre";
        cargaTabla(jtClientes, Query, "Cliente");
    }//GEN-LAST:event_jpProveedoresClientesComponentShown

    private void jpGastosLocales5ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jpGastosLocales5ComponentShown
        String query = "FROM Proveedores p ORDER BY p.nombre";
        llenarCombo(cmbProveedor, query, "proveedor");
    }//GEN-LAST:event_jpGastosLocales5ComponentShown

    private void jpConceptosComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jpConceptosComponentShown
        String query = "";
        query = "From Linea s ORDER BY s.idLinea";
        llenarTabla(jtConceptos, "Linea", query);
        query = "From Sucursal s ORDER BY s.idSucursal";
        llenarTabla(jtLocal, "Sucursal", query);
    }//GEN-LAST:event_jpConceptosComponentShown

    private void crearResumen() {
        inicializarConceptosResumen();
        double locales = getLocales();
        lblConceptoLocal.setText(String.valueOf(locales));
        double gastoPersonales = getGastosPersonales();
        lblConceptoGastoPersonal.setText(String.valueOf(gastoPersonales));
        double oficinaBodega = getOficinaBodega();
        lblConceptoOficinaBodega.setText(String.valueOf(oficinaBodega));
        double gasolina = getGasolina();
        lblConceptoGasolina.setText(String.valueOf(gasolina));
        double mantenimientoV = getMantenimientoV();
        lblConceptoMantenimientoVehiculos.setText(String.valueOf(mantenimientoV));
        double sueldosPrestamos = getSueldoPrestamos();
        lblConceptoSueldosPrestamos.setText(String.valueOf(sueldosPrestamos));
        double totalGastosGenerales = gastoPersonales + oficinaBodega + gasolina + mantenimientoV + sueldosPrestamos;
        lblTotalConceptoResumen.setText(String.valueOf(totalGastosGenerales));
        double proveedores = getProveedores();
        lblConceptoProveedores.setText(String.valueOf(proveedores));
        int tiendasAbiertas = getTiendasAbiertas();
        lblTiendasAbiertas.setText(String.valueOf(tiendasAbiertas));
        if (tiendasAbiertas > 0) {
            double proveedoresEntreTiendas = proveedores / tiendasAbiertas;
            lblTotalProveedoresEntreTiendas.setText(String.valueOf(proveedoresEntreTiendas));
        } else {
            double proveedoresEntreTiendas = proveedores;
            lblTotalProveedoresEntreTiendas.setText(String.valueOf(proveedoresEntreTiendas));
        }

    }

    private int getTiendasAbiertas() {
        AccesoBD acceso = new AccesoBD();
        String fechaInicioResumen = FechaHerramienta.formatoYMD(jdcFechaConceptoResumenInicio.getDate());
        String fechaFinResumen = FechaHerramienta.formatoYMD(jdcFechaConceptoResumenFin.getDate());
        HQL = "SELECT COUNT(DISTINCT d.local) FROM Diariocaja d WHERE "
                + "d.fecha BETWEEN '"
                + fechaInicioResumen + "' AND '"
                + fechaFinResumen + "'";
        return acceso.rowCount(HQL);
    }

    private double getLocales() {
        AccesoBD acceso = new AccesoBD();
        String fechaInicioResumen = FechaHerramienta.formatoYMD(jdcFechaConceptoResumenInicio.getDate());
        String fechaFinResumen = FechaHerramienta.formatoYMD(jdcFechaConceptoResumenFin.getDate());
        HQL = "Select SUM(g.importe) FROM Gastosgenerales g WHERE "
                + " g.concepto = 'LOCALES' AND"
                + " (g.fecha BETWEEN '"
                + fechaInicioResumen + "' AND '"
                + fechaFinResumen + "')";
        return acceso.sumRows(HQL);
    }

    private double getGastosPersonales() {
        AccesoBD acceso = new AccesoBD();
        String fechaInicioResumen = FechaHerramienta.formatoYMD(jdcFechaConceptoResumenInicio.getDate());
        String fechaFinResumen = FechaHerramienta.formatoYMD(jdcFechaConceptoResumenFin.getDate());
        HQL = "Select SUM(g.importe) FROM Gastosgenerales g WHERE "
                + " g.concepto = 'GASTOS PERSONALES' AND"
                + " (g.fecha BETWEEN '"
                + fechaInicioResumen + "' AND '"
                + fechaFinResumen + "')";
        return acceso.sumRows(HQL);
    }

    private double getOficinaBodega() {
        AccesoBD acceso = new AccesoBD();
        String fechaInicioResumen = FechaHerramienta.formatoYMD(jdcFechaConceptoResumenInicio.getDate());
        String fechaFinResumen = FechaHerramienta.formatoYMD(jdcFechaConceptoResumenFin.getDate());
        HQL = "Select SUM(g.importe) FROM Gastosgenerales g WHERE "
                + " g.concepto = 'OFICINA BODEGA' AND"
                + " (g.fecha BETWEEN '"
                + fechaInicioResumen + "' AND '"
                + fechaFinResumen + "')";
        return acceso.sumRows(HQL);
    }

    private double getGasolina() {
        AccesoBD acceso = new AccesoBD();
        String fechaInicioResumen = FechaHerramienta.formatoYMD(jdcFechaConceptoResumenInicio.getDate());
        String fechaFinResumen = FechaHerramienta.formatoYMD(jdcFechaConceptoResumenFin.getDate());
        HQL = "Select SUM(g.importe) FROM Gastosgenerales g WHERE "
                + " g.concepto = 'GASOLINA' AND"
                + " (g.fecha BETWEEN '"
                + fechaInicioResumen + "' AND '"
                + fechaFinResumen + "')";
        return acceso.sumRows(HQL);
    }

    private double getMantenimientoV() {
        AccesoBD acceso = new AccesoBD();
        String fechaInicioResumen = FechaHerramienta.formatoYMD(jdcFechaConceptoResumenInicio.getDate());
        String fechaFinResumen = FechaHerramienta.formatoYMD(jdcFechaConceptoResumenFin.getDate());
        HQL = "Select SUM(g.importe) FROM Gastosgenerales g WHERE "
                + " g.concepto = 'MANTENIMIENTO V' AND"
                + " (g.fecha BETWEEN '"
                + fechaInicioResumen + "' AND '"
                + fechaFinResumen + "')";
        return acceso.sumRows(HQL);
    }

    private double getSueldoPrestamos() {
        AccesoBD acceso = new AccesoBD();
        String fechaInicioResumen = FechaHerramienta.formatoYMD(jdcFechaConceptoResumenInicio.getDate());
        String fechaFinResumen = FechaHerramienta.formatoYMD(jdcFechaConceptoResumenFin.getDate());
        HQL = "Select SUM(g.importe) FROM Gastosgenerales g WHERE "
                + " g.concepto = 'SUELDOS PRESTAMOS' AND"
                + " (g.fecha BETWEEN '"
                + fechaInicioResumen + "' AND '"
                + fechaFinResumen + "')";
        return acceso.sumRows(HQL);
    }

    private double getProveedores() {
        AccesoBD acceso = new AccesoBD();
        String fechaInicioResumen = FechaHerramienta.formatoYMD(jdcFechaConceptoResumenInicio.getDate());
        String fechaFinResumen = FechaHerramienta.formatoYMD(jdcFechaConceptoResumenFin.getDate());
        HQL = "Select SUM(g.importe) FROM Gastosgenerales g WHERE "
                + " g.concepto = 'PROVEEDORES' AND"
                + " (g.fecha BETWEEN '"
                + fechaInicioResumen + "' AND '"
                + fechaFinResumen + "')";
        return acceso.sumRows(HQL);
    }

    private void quitarFila(JTable tabla) {
        if (tabla.getSelectedRow() > -1) {
            DefaultTableModel model = (DefaultTableModel) tabla.getModel();
            model.removeRow(tabla.getSelectedRow());
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona un registro de la "
                    + "lista para poder borrar.", "Registro no seleccionado", 0);
        }
    }

    private void inicializarConceptosResumen() {
        lblConceptoLocal.setText("0");
        lblConceptoGastoPersonal.setText("0");
        lblConceptoOficinaBodega.setText("0");
        lblConceptoGasolina.setText("0");
        lblConceptoMantenimientoVehiculos.setText("0");
        lblConceptoSueldosPrestamos.setText("0");
        lblConceptoProveedores.setText("0");
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

    private void cargaTabla(final JTable jtTabla, String HQL, String Encabezado) {
        //Reviso si que la consulta no vaya vacia
        if (!HQL.isEmpty()) {
            AccesoBD acceso = new AccesoBD();
            jtTabla.setVisible(false);
            jtTabla.removeAll();
            jtTabla.setModel(acceso.retornaModelo(Encabezado, HQL));
            jtTabla.setVisible(true);
        }
    }

    private void crearConsultaGlobal() {
        fechaInicio = FechaHerramienta.formatoYMD(jdcFechaConceptoTotalInicio.getDate());
        fechaFin = FechaHerramienta.formatoYMD(jdcFechaConceptoTotalFin.getDate());
        conceptoResumen = cmbConceptoTotal.getSelectedItem().toString();
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
    private javax.swing.JButton btnAnotarGasolina;
    private javax.swing.JButton btnAnotarGastosPersonales;
    private javax.swing.JButton btnAnotarLocales;
    private javax.swing.JButton btnAnotarMantenimientoV;
    private javax.swing.JButton btnAnotarOficinaBodega;
    private javax.swing.JButton btnAnotarProveedores;
    private javax.swing.JButton btnAnotarSueldosPrestamos;
    private javax.swing.JButton btnBorrarCliente;
    private javax.swing.JButton btnBorrarConcepto;
    private javax.swing.JButton btnBorrarGasto;
    private javax.swing.JButton btnBorrarGasto1;
    private javax.swing.JButton btnBorrarGasto2;
    private javax.swing.JButton btnBorrarGasto3;
    private javax.swing.JButton btnBorrarGasto4;
    private javax.swing.JButton btnBorrarGasto5;
    private javax.swing.JButton btnBorrarGasto6;
    private javax.swing.JButton btnCorregirConcepto;
    private javax.swing.JButton btnCorregirConcepto1;
    private javax.swing.JButton btnCorregirProveedor;
    private javax.swing.JButton btnEditarCliente;
    private javax.swing.JButton btnEditarConceptos;
    private javax.swing.JButton btnEliminarConcepto;
    private javax.swing.JButton btnEliminarLocal;
    private javax.swing.JButton btnEliminarProveedor;
    private javax.swing.JButton btnNuevoCliente;
    private javax.swing.JButton btnNuevoConcepto;
    private javax.swing.JButton btnNuevoLocal;
    private javax.swing.JButton btnNuevoProveedor;
    private javax.swing.JButton btnRegistrarGasolina;
    private javax.swing.JButton btnRegistrarGastosPersonales;
    private javax.swing.JButton btnRegistrarLocales;
    private javax.swing.JButton btnRegistrarMantenimientoV;
    private javax.swing.JButton btnRegistrarOficinaBodega;
    private javax.swing.JButton btnRegistrarProveedores;
    private javax.swing.JButton btnRegistrarSueldoPrestamos;
    private javax.swing.JButton btnSalirRegistroGastos;
    private javax.swing.JButton btnSalirRegistroGastos1;
    private javax.swing.JButton btnSalirRegistroGastos2;
    private javax.swing.JButton btnSalirRegistroGastos3;
    private javax.swing.JButton btnSalirRegistroGastos4;
    private javax.swing.JButton btnSalirRegistroGastos5;
    private javax.swing.JButton btnSalirRegistroGastos6;
    private javax.swing.JButton btnSalirResumen1;
    private javax.swing.JComboBox cmbConceptoGasolina;
    private javax.swing.JComboBox cmbConceptoGastosPersonales;
    private javax.swing.JComboBox cmbConceptoLocales;
    private javax.swing.JComboBox cmbConceptoMantenimiento;
    private javax.swing.JComboBox cmbConceptoOficinaBodega;
    private javax.swing.JComboBox cmbConceptoSueldoPrestamo;
    private javax.swing.JComboBox cmbConceptoTotal;
    private javax.swing.JComboBox cmbFormaDePago;
    private javax.swing.JComboBox cmbProveedor;
    private javax.swing.JComboBox cmbSubConceptoLocales;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
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
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private com.toedter.calendar.JDateChooser jdcFechaConceptoResumenFin;
    private com.toedter.calendar.JDateChooser jdcFechaConceptoResumenInicio;
    private com.toedter.calendar.JDateChooser jdcFechaConceptoTotalFin;
    private com.toedter.calendar.JDateChooser jdcFechaConceptoTotalInicio;
    private com.toedter.calendar.JDateChooser jdcFechaGasolina;
    private com.toedter.calendar.JDateChooser jdcFechaGastosPersonales;
    private com.toedter.calendar.JDateChooser jdcFechaLocal;
    private com.toedter.calendar.JDateChooser jdcFechaMantenimientoV;
    private com.toedter.calendar.JDateChooser jdcFechaOficinaBodega;
    private com.toedter.calendar.JDateChooser jdcFechaProveedores;
    private com.toedter.calendar.JDateChooser jdcFechaSueldosPrestamos;
    private javax.swing.JPanel jpConceptos;
    private javax.swing.JPanel jpGastosLocales;
    private javax.swing.JPanel jpGastosLocales1;
    private javax.swing.JPanel jpGastosLocales2;
    private javax.swing.JPanel jpGastosLocales3;
    private javax.swing.JPanel jpGastosLocales4;
    private javax.swing.JPanel jpGastosLocales5;
    private javax.swing.JPanel jpGastosLocales6;
    private javax.swing.JPanel jpGastosTotales;
    private javax.swing.JPanel jpProveedoresClientes;
    private javax.swing.JPanel jpResumenConceptos;
    private javax.swing.JScrollPane jsConceptos;
    private javax.swing.JScrollPane jsProvedorCliente;
    private javax.swing.JTable jtClientes;
    private javax.swing.JTable jtConceptos;
    private javax.swing.JTable jtLocal;
    private javax.swing.JTable jtProveedor;
    private javax.swing.JTable jtTablaGasolina;
    private javax.swing.JTable jtTablaGastosPersonales;
    private javax.swing.JTable jtTablaGastosTotales;
    private javax.swing.JTable jtTablaLocales;
    private javax.swing.JTable jtTablaMantenimientoV;
    private javax.swing.JTable jtTablaOficinaBodega;
    private javax.swing.JTable jtTablaProveedores;
    private javax.swing.JTable jtTablaSueldosPrestamos;
    private javax.swing.JLabel lblConceptoGasolina;
    private javax.swing.JLabel lblConceptoGastoPersonal;
    private javax.swing.JLabel lblConceptoLocal;
    private javax.swing.JLabel lblConceptoMantenimientoVehiculos;
    private javax.swing.JLabel lblConceptoOficinaBodega;
    private javax.swing.JLabel lblConceptoProveedores;
    private javax.swing.JLabel lblConceptoSueldosPrestamos;
    private javax.swing.JLabel lblTiendasAbiertas;
    private javax.swing.JLabel lblTituloResultados;
    private javax.swing.JLabel lblTotalConceptoResumen;
    private javax.swing.JLabel lblTotalProveedoresEntreTiendas;
    private javax.swing.JTextField txtClienteNuevo;
    private javax.swing.JTextField txtComentarioGastosPersonales;
    private javax.swing.JTextField txtComentarioLocales;
    private javax.swing.JTextField txtComentarioMantenimientoV;
    private javax.swing.JTextField txtComentariosGasolina;
    private javax.swing.JTextField txtComentariosOficinaBodega;
    private javax.swing.JTextField txtComentariosSueldosPrestamos;
    private javax.swing.JTextField txtConceptoNuevo;
    private javax.swing.JTextField txtFolio;
    private javax.swing.JTextField txtImporteGasolina;
    private javax.swing.JTextField txtImporteGastosPersonales;
    private javax.swing.JTextField txtImporteLocales;
    private javax.swing.JTextField txtImporteMantenimientoV;
    private javax.swing.JTextField txtImporteOficinaBodega;
    private javax.swing.JTextField txtImporteProveedores;
    private javax.swing.JTextField txtImporteSueldoPrestamos;
    private javax.swing.JTextField txtLocalNuevo;
    private javax.swing.JTextField txtProveedorNuevo;
    private javax.swing.JTextField txtSubConceptoSueldosPrestamos;
    // End of variables declaration//GEN-END:variables
}
