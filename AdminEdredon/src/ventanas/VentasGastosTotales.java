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
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import pojos.Diariocaja;
import reportMaker.ReportMakerNDB;

/**
 *
 * @author Daniel
 */
public class VentasGastosTotales extends javax.swing.JInternalFrame {

    String HQL = "";
    String tiendasHQL = "";
    private String[] cuentaMensual = new String[13];
    double enero = 0;
    double febrero = 0;
    double marzo = 0;
    double abril = 0;
    double mayo = 0;
    double junio = 0;
    double julio = 0;
    double agosto = 0;
    double septiembre = 0;
    double octubre = 0;
    double noviembre = 0;
    double diciembre = 0;
    double year = 0;

    public VentasGastosTotales() {
        initComponents();
        jdcFechaConceptoResumenInicio.setDate(new Date());
        jdcFechaConceptoResumenFin.setDate(new Date());
    }

    private void inializarMeses() {
        enero = 0;
        febrero = 0;
        marzo = 0;
        abril = 0;
        mayo = 0;
        junio = 0;
        julio = 0;
        agosto = 0;
        septiembre = 0;
        octubre = 0;
        noviembre = 0;
        diciembre = 0;
        year = 0;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jtpVentasGastosTotales = new javax.swing.JTabbedPane();
        jsResumenVentas = new javax.swing.JScrollPane();
        jpResumenGeneral = new javax.swing.JPanel();
        cmbTiendas = new javax.swing.JComboBox();
        lblResumenTitulo = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jtResumenGeneral = new javax.swing.JTable();
        jLabel63 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jdcFechaConceptoResumenInicio = new com.toedter.calendar.JDateChooser();
        jdcFechaConceptoResumenFin = new com.toedter.calendar.JDateChooser();
        lblVentasResumen = new javax.swing.JLabel();
        lblGastosResumen = new javax.swing.JLabel();
        lblResultadosResumen = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblVentasConNota = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblVentasSinNota = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblAbonoCredito = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblTotalDeVentas = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblGastosDeVenta = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblGastosGenerales = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblCompras = new javax.swing.JLabel();
        lblResultadoTexto = new javax.swing.JLabel();
        lblResultado = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblTotalDeGastos = new javax.swing.JLabel();
        btnImprimirResumenGeneral = new javax.swing.JButton();
        jsVentasTotales = new javax.swing.JScrollPane();
        jpVentasTotales = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtVentasTotales = new javax.swing.JTable();
        lblTituloVentas = new javax.swing.JLabel();
        cmbVentas = new javax.swing.JComboBox();
        jLabel65 = new javax.swing.JLabel();
        lblVentasResumen10 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        lblEneroVentas = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        lblFebreroVentas = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        lblMarzoVentas = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        lblAbrilVentas = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        lblMayoVentas = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        lblJunioVentas = new javax.swing.JLabel();
        lblVentasResumen11 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        lblNoviembreVentas = new javax.swing.JLabel();
        lblDiciembreVentas = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        lblJulioVentas = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        lblAgostoVentas = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        lblSeptiembreVentas = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        lblOctubreVentas = new javax.swing.JLabel();
        lblVentasResumen12 = new javax.swing.JLabel();
        lblYearTituloVentas = new javax.swing.JLabel();
        lblYearVentas = new javax.swing.JLabel();
        btnImprimirVentas = new javax.swing.JButton();
        jsGastosDeVenta = new javax.swing.JScrollPane();
        jpGastosDeVenta = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jtGastosDeVenta = new javax.swing.JTable();
        lblTituloGastosDeVenta = new javax.swing.JLabel();
        cmbGastosDeVenta = new javax.swing.JComboBox();
        jLabel69 = new javax.swing.JLabel();
        lblVentasResumen7 = new javax.swing.JLabel();
        lblYearTituloGastosVenta = new javax.swing.JLabel();
        lblYearGastoDeVenta = new javax.swing.JLabel();
        lblAbrilGastoDeVenta = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        lblMayoGastoDeVenta = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        lblJunioGastoDeVenta = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        lblJulioGastoDeVenta = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        lblAgostoGastoDeVenta = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        lblSeptiembreGastoDeVenta = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        lblOctubreGastoDeVenta = new javax.swing.JLabel();
        lblVentasResumen8 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        lblVentasResumen9 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        lblNoviembreGastoDeVenta = new javax.swing.JLabel();
        lblDiciembreGastoDeVenta = new javax.swing.JLabel();
        lblEneroGastoDeVenta = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        lblFebreroGastoDeVenta = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        lblMarzoGastoDeVenta = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        btnImprimirGastosDeVenta = new javax.swing.JButton();
        jsGastosGenerales = new javax.swing.JScrollPane();
        jpGastosGenerales = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtGastosGenerales = new javax.swing.JTable();
        jLabel66 = new javax.swing.JLabel();
        cmbGastosGenerales = new javax.swing.JComboBox();
        lblTituloGastosGenerales = new javax.swing.JLabel();
        lblVentasResumen1 = new javax.swing.JLabel();
        lblVentasResumen2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblEneroGasto = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblFebreroGasto = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lblMarzoGasto = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lblAbrilGasto = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lblMayoGasto = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lblJunioGasto = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lblJulioGasto = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        lblAgostoGasto = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lblSeptiembreGasto = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        lblOctubreGasto = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        lblNoviembreGasto = new javax.swing.JLabel();
        lblDiciembreGasto = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        lblVentasResumen3 = new javax.swing.JLabel();
        lblYearGasto = new javax.swing.JLabel();
        lblYearGastoGeneral = new javax.swing.JLabel();
        btnImprimirGastosGenerales = new javax.swing.JButton();
        jsCompras = new javax.swing.JScrollPane();
        jpCompras = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jtCompras = new javax.swing.JTable();
        jLabel67 = new javax.swing.JLabel();
        cmbCompras = new javax.swing.JComboBox();
        lblTituloCompras = new javax.swing.JLabel();
        lblVentasResumen4 = new javax.swing.JLabel();
        lblVentasResumen5 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        lblEneroCompras = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        lblFebreroCompras = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        lblMarzoCompras = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        lblAbrilCompras = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        lblMayoCompras = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        lblJunioCompras = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        lblJulioCompras = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        lblAgostoCompras = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        lblSeptiembreCompras = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        lblOctubreCompras = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        lblNoviembreCompras = new javax.swing.JLabel();
        lblDiciembreCompras = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        lblVentasResumen6 = new javax.swing.JLabel();
        lblYearCompras = new javax.swing.JLabel();
        lblYearComprasAnio = new javax.swing.JLabel();
        btnImprimirCompras = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Ventas y gastos totales");

        jtpVentasGastosTotales.setTabPlacement(javax.swing.JTabbedPane.LEFT);

        jsResumenVentas.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jsResumenVentasComponentShown(evt);
            }
        });

        jpResumenGeneral.setBackground(new java.awt.Color(255, 255, 255));

        cmbTiendas.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        cmbTiendas.setForeground(new java.awt.Color(0, 102, 153));
        cmbTiendas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Todas", "Cuaracurio 2", "La Comadre", "La Flor", "Local 2", "Local B3", "Local D7", "Local E15", "Moroleón", " " }));

        lblResumenTitulo.setBackground(new java.awt.Color(0, 153, 204));
        lblResumenTitulo.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        lblResumenTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblResumenTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblResumenTitulo.setText("Tienda");
        lblResumenTitulo.setOpaque(true);

        jtResumenGeneral.setFont(new java.awt.Font("Cambria", 0, 12)); // NOI18N
        jtResumenGeneral.setForeground(new java.awt.Color(0, 51, 102));
        jtResumenGeneral.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Fecha", "Local", "Notas", "V con nota", "V sin nota", "Abono credito", "Gastos Venta", "No T.", "Tiendas", "Gastos generales", "Compras", "Total del dia"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, true, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(jtResumenGeneral);
        if (jtResumenGeneral.getColumnModel().getColumnCount() > 0) {
            jtResumenGeneral.getColumnModel().getColumn(0).setMinWidth(80);
            jtResumenGeneral.getColumnModel().getColumn(0).setMaxWidth(80);
            jtResumenGeneral.getColumnModel().getColumn(3).setMinWidth(100);
            jtResumenGeneral.getColumnModel().getColumn(3).setMaxWidth(100);
            jtResumenGeneral.getColumnModel().getColumn(4).setMinWidth(100);
            jtResumenGeneral.getColumnModel().getColumn(4).setMaxWidth(100);
            jtResumenGeneral.getColumnModel().getColumn(5).setMinWidth(100);
            jtResumenGeneral.getColumnModel().getColumn(5).setMaxWidth(100);
            jtResumenGeneral.getColumnModel().getColumn(6).setMinWidth(100);
            jtResumenGeneral.getColumnModel().getColumn(6).setMaxWidth(100);
            jtResumenGeneral.getColumnModel().getColumn(7).setMinWidth(40);
            jtResumenGeneral.getColumnModel().getColumn(7).setMaxWidth(40);
        }

        jLabel63.setBackground(new java.awt.Color(0, 102, 153));
        jLabel63.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(255, 255, 255));
        jLabel63.setText("PERIODO TRABAJADO");
        jLabel63.setOpaque(true);

        jLabel68.setBackground(new java.awt.Color(153, 255, 255));
        jLabel68.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel68.setForeground(new java.awt.Color(0, 102, 153));
        jLabel68.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel68.setText("FECHA INICIO");
        jLabel68.setOpaque(true);

        jLabel41.setBackground(new java.awt.Color(153, 255, 255));
        jLabel41.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(0, 102, 153));
        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel41.setText("FECHA FIN");
        jLabel41.setOpaque(true);

        lblVentasResumen.setBackground(new java.awt.Color(0, 102, 153));
        lblVentasResumen.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lblVentasResumen.setForeground(new java.awt.Color(255, 255, 255));
        lblVentasResumen.setText("VENTAS");
        lblVentasResumen.setOpaque(true);

        lblGastosResumen.setBackground(new java.awt.Color(0, 102, 153));
        lblGastosResumen.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lblGastosResumen.setForeground(new java.awt.Color(255, 255, 255));
        lblGastosResumen.setText("GASTOS");
        lblGastosResumen.setOpaque(true);

        lblResultadosResumen.setBackground(new java.awt.Color(0, 102, 153));
        lblResultadosResumen.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lblResultadosResumen.setForeground(new java.awt.Color(255, 255, 255));
        lblResultadosResumen.setText("RESULTADOS");
        lblResultadosResumen.setOpaque(true);

        jLabel64.setBackground(new java.awt.Color(0, 102, 153));
        jLabel64.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel64.setForeground(new java.awt.Color(255, 255, 255));
        jLabel64.setText("MIS TIENDAS");
        jLabel64.setOpaque(true);

        jLabel1.setBackground(new java.awt.Color(0, 153, 204));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Ventas con Nota");
        jLabel1.setOpaque(true);

        lblVentasConNota.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        lblVentasConNota.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblVentasConNota.setText("0");
        lblVentasConNota.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setBackground(new java.awt.Color(0, 153, 204));
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Ventas sin Nota");
        jLabel3.setOpaque(true);

        lblVentasSinNota.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        lblVentasSinNota.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblVentasSinNota.setText("0");
        lblVentasSinNota.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel5.setBackground(new java.awt.Color(0, 153, 204));
        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Abono credito");
        jLabel5.setOpaque(true);

        lblAbonoCredito.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        lblAbonoCredito.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAbonoCredito.setText("0");
        lblAbonoCredito.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel7.setBackground(new java.awt.Color(0, 153, 204));
        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Total ventas");
        jLabel7.setOpaque(true);

        lblTotalDeVentas.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        lblTotalDeVentas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotalDeVentas.setText("0");
        lblTotalDeVentas.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setBackground(new java.awt.Color(0, 153, 204));
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Gastos de venta");
        jLabel2.setOpaque(true);

        lblGastosDeVenta.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        lblGastosDeVenta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblGastosDeVenta.setText("0");
        lblGastosDeVenta.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setBackground(new java.awt.Color(0, 153, 204));
        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Gastos Generales");
        jLabel4.setOpaque(true);

        lblGastosGenerales.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        lblGastosGenerales.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblGastosGenerales.setText("0");
        lblGastosGenerales.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel6.setBackground(new java.awt.Color(0, 153, 204));
        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Compras");
        jLabel6.setOpaque(true);

        lblCompras.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        lblCompras.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCompras.setText("0");
        lblCompras.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblResultadoTexto.setBackground(new java.awt.Color(0, 153, 204));
        lblResultadoTexto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblResultadoTexto.setForeground(new java.awt.Color(255, 255, 255));
        lblResultadoTexto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblResultadoTexto.setText("Resultado");
        lblResultadoTexto.setOpaque(true);

        lblResultado.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        lblResultado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblResultado.setText("0");
        lblResultado.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel8.setBackground(new java.awt.Color(0, 153, 204));
        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Total gastos");
        jLabel8.setOpaque(true);

        lblTotalDeGastos.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        lblTotalDeGastos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotalDeGastos.setText("0");
        lblTotalDeGastos.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnImprimirResumenGeneral.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/pdf.png"))); // NOI18N
        btnImprimirResumenGeneral.setText("Imprimir resultados");
        btnImprimirResumenGeneral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirResumenGeneralActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpResumenGeneralLayout = new javax.swing.GroupLayout(jpResumenGeneral);
        jpResumenGeneral.setLayout(jpResumenGeneralLayout);
        jpResumenGeneralLayout.setHorizontalGroup(
            jpResumenGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cmbTiendas, 0, 848, Short.MAX_VALUE)
            .addComponent(jLabel64, javax.swing.GroupLayout.DEFAULT_SIZE, 848, Short.MAX_VALUE)
            .addGroup(jpResumenGeneralLayout.createSequentialGroup()
                .addComponent(jLabel68, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jdcFechaConceptoResumenInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jdcFechaConceptoResumenFin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(119, 119, 119))
            .addComponent(jLabel63, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblResumenTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane4)
            .addComponent(lblVentasResumen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblGastosResumen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblResultadosResumen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jpResumenGeneralLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpResumenGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpResumenGeneralLayout.createSequentialGroup()
                        .addGroup(jpResumenGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblVentasConNota, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpResumenGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblVentasSinNota, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpResumenGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblAbonoCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpResumenGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTotalDeVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jpResumenGeneralLayout.createSequentialGroup()
                        .addGroup(jpResumenGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblGastosDeVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpResumenGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblGastosGenerales, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpResumenGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblCompras, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpResumenGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTotalDeGastos, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jpResumenGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lblResultadoTexto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblResultado, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpResumenGeneralLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnImprimirResumenGeneral)
                .addContainerGap())
        );
        jpResumenGeneralLayout.setVerticalGroup(
            jpResumenGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpResumenGeneralLayout.createSequentialGroup()
                .addComponent(jLabel64)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbTiendas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel63)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpResumenGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel68)
                    .addComponent(jdcFechaConceptoResumenInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel41)
                    .addComponent(jdcFechaConceptoResumenFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblResumenTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblVentasResumen)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpResumenGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpResumenGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jpResumenGeneralLayout.createSequentialGroup()
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblVentasConNota))
                        .addGroup(jpResumenGeneralLayout.createSequentialGroup()
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblVentasSinNota))
                        .addGroup(jpResumenGeneralLayout.createSequentialGroup()
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblAbonoCredito)))
                    .addGroup(jpResumenGeneralLayout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTotalDeVentas)))
                .addGap(19, 19, 19)
                .addComponent(lblGastosResumen)
                .addGap(8, 8, 8)
                .addGroup(jpResumenGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpResumenGeneralLayout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblGastosDeVenta))
                    .addGroup(jpResumenGeneralLayout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblGastosGenerales))
                    .addGroup(jpResumenGeneralLayout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCompras))
                    .addGroup(jpResumenGeneralLayout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTotalDeGastos)))
                .addGap(18, 18, 18)
                .addComponent(lblResultadosResumen)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblResultadoTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblResultado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(btnImprimirResumenGeneral)
                .addGap(23, 23, 23))
        );

        jsResumenVentas.setViewportView(jpResumenGeneral);

        jtpVentasGastosTotales.addTab("Resumen general", jsResumenVentas);

        jsVentasTotales.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jsVentasTotalesComponentShown(evt);
            }
        });

        jpVentasTotales.setBackground(new java.awt.Color(255, 255, 255));

        jtVentasTotales.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Tienda", "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jtVentasTotales);
        if (jtVentasTotales.getColumnModel().getColumnCount() > 0) {
            jtVentasTotales.getColumnModel().getColumn(0).setMinWidth(70);
            jtVentasTotales.getColumnModel().getColumn(0).setMaxWidth(70);
        }

        lblTituloVentas.setBackground(new java.awt.Color(0, 204, 102));
        lblTituloVentas.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        lblTituloVentas.setForeground(new java.awt.Color(255, 255, 255));
        lblTituloVentas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTituloVentas.setText("Ventas");
        lblTituloVentas.setOpaque(true);

        cmbVentas.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        cmbVentas.setForeground(new java.awt.Color(0, 102, 153));
        cmbVentas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020" }));

        jLabel65.setBackground(new java.awt.Color(0, 102, 153));
        jLabel65.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(255, 255, 255));
        jLabel65.setText("AÑO TRABAJADO");
        jLabel65.setOpaque(true);

        lblVentasResumen10.setBackground(new java.awt.Color(0, 102, 153));
        lblVentasResumen10.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lblVentasResumen10.setForeground(new java.awt.Color(255, 255, 255));
        lblVentasResumen10.setText("1ER SEMESTRE");
        lblVentasResumen10.setOpaque(true);

        jLabel48.setBackground(new java.awt.Color(0, 153, 204));
        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(255, 255, 255));
        jLabel48.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel48.setText("Enero");
        jLabel48.setOpaque(true);

        lblEneroVentas.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        lblEneroVentas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEneroVentas.setText("0");
        lblEneroVentas.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel49.setBackground(new java.awt.Color(0, 153, 204));
        jLabel49.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(255, 255, 255));
        jLabel49.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel49.setText("Febrero");
        jLabel49.setOpaque(true);

        lblFebreroVentas.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        lblFebreroVentas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFebreroVentas.setText("0");
        lblFebreroVentas.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel50.setBackground(new java.awt.Color(0, 153, 204));
        jLabel50.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(255, 255, 255));
        jLabel50.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel50.setText("Marzo");
        jLabel50.setOpaque(true);

        lblMarzoVentas.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        lblMarzoVentas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMarzoVentas.setText("0");
        lblMarzoVentas.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel51.setBackground(new java.awt.Color(0, 153, 204));
        jLabel51.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(255, 255, 255));
        jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel51.setText("Abril");
        jLabel51.setOpaque(true);

        lblAbrilVentas.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        lblAbrilVentas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAbrilVentas.setText("0");
        lblAbrilVentas.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel52.setBackground(new java.awt.Color(0, 153, 204));
        jLabel52.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(255, 255, 255));
        jLabel52.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel52.setText("Mayo");
        jLabel52.setOpaque(true);

        lblMayoVentas.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        lblMayoVentas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMayoVentas.setText("0");
        lblMayoVentas.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel53.setBackground(new java.awt.Color(0, 153, 204));
        jLabel53.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(255, 255, 255));
        jLabel53.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel53.setText("Junio");
        jLabel53.setOpaque(true);

        lblJunioVentas.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        lblJunioVentas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblJunioVentas.setText("0");
        lblJunioVentas.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblVentasResumen11.setBackground(new java.awt.Color(0, 102, 153));
        lblVentasResumen11.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lblVentasResumen11.setForeground(new java.awt.Color(255, 255, 255));
        lblVentasResumen11.setText("2DO SEMESTRE");
        lblVentasResumen11.setOpaque(true);

        jLabel54.setBackground(new java.awt.Color(0, 153, 204));
        jLabel54.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(255, 255, 255));
        jLabel54.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel54.setText("Noviembre");
        jLabel54.setOpaque(true);

        lblNoviembreVentas.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        lblNoviembreVentas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNoviembreVentas.setText("0");
        lblNoviembreVentas.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblDiciembreVentas.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        lblDiciembreVentas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDiciembreVentas.setText("0");
        lblDiciembreVentas.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel55.setBackground(new java.awt.Color(0, 153, 204));
        jLabel55.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(255, 255, 255));
        jLabel55.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel55.setText("Diciembre");
        jLabel55.setOpaque(true);

        jLabel56.setBackground(new java.awt.Color(0, 153, 204));
        jLabel56.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(255, 255, 255));
        jLabel56.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel56.setText("Julio");
        jLabel56.setOpaque(true);

        lblJulioVentas.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        lblJulioVentas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblJulioVentas.setText("0");
        lblJulioVentas.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel57.setBackground(new java.awt.Color(0, 153, 204));
        jLabel57.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(255, 255, 255));
        jLabel57.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel57.setText("Agosto");
        jLabel57.setOpaque(true);

        lblAgostoVentas.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        lblAgostoVentas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAgostoVentas.setText("0");
        lblAgostoVentas.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel58.setBackground(new java.awt.Color(0, 153, 204));
        jLabel58.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(255, 255, 255));
        jLabel58.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel58.setText("Septiembre");
        jLabel58.setOpaque(true);

        lblSeptiembreVentas.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        lblSeptiembreVentas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSeptiembreVentas.setText("0");
        lblSeptiembreVentas.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel59.setBackground(new java.awt.Color(0, 153, 204));
        jLabel59.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(255, 255, 255));
        jLabel59.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel59.setText("Octubre");
        jLabel59.setOpaque(true);

        lblOctubreVentas.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        lblOctubreVentas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblOctubreVentas.setText("0");
        lblOctubreVentas.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblVentasResumen12.setBackground(new java.awt.Color(0, 102, 153));
        lblVentasResumen12.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lblVentasResumen12.setForeground(new java.awt.Color(255, 255, 255));
        lblVentasResumen12.setText("RESULTADO ANUAL");
        lblVentasResumen12.setOpaque(true);

        lblYearTituloVentas.setBackground(new java.awt.Color(0, 153, 204));
        lblYearTituloVentas.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblYearTituloVentas.setForeground(new java.awt.Color(255, 255, 255));
        lblYearTituloVentas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblYearTituloVentas.setText("2015");
        lblYearTituloVentas.setOpaque(true);

        lblYearVentas.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        lblYearVentas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblYearVentas.setText("0");
        lblYearVentas.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnImprimirVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/pdf.png"))); // NOI18N
        btnImprimirVentas.setText("Imprimir resultados");
        btnImprimirVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirVentasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpVentasTotalesLayout = new javax.swing.GroupLayout(jpVentasTotales);
        jpVentasTotales.setLayout(jpVentasTotalesLayout);
        jpVentasTotalesLayout.setHorizontalGroup(
            jpVentasTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpVentasTotalesLayout.createSequentialGroup()
                .addGroup(jpVentasTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 855, Short.MAX_VALUE)
                    .addComponent(lblTituloVentas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbVentas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel65, javax.swing.GroupLayout.DEFAULT_SIZE, 855, Short.MAX_VALUE)
                    .addComponent(lblVentasResumen10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblVentasResumen11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jpVentasTotalesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jpVentasTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpVentasTotalesLayout.createSequentialGroup()
                                .addGroup(jpVentasTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblYearTituloVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblYearVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 734, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jpVentasTotalesLayout.createSequentialGroup()
                                .addGroup(jpVentasTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jpVentasTotalesLayout.createSequentialGroup()
                                        .addGroup(jpVentasTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblEneroVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jpVentasTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblFebreroVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jpVentasTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblMarzoVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jpVentasTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblAbrilVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jpVentasTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblMayoVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jpVentasTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblJunioVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jpVentasTotalesLayout.createSequentialGroup()
                                        .addGroup(jpVentasTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblJulioVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jpVentasTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblAgostoVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jpVentasTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblSeptiembreVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jpVentasTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblOctubreVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jpVentasTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblNoviembreVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jpVentasTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblDiciembreVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addComponent(lblVentasResumen12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpVentasTotalesLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnImprimirVentas)
                .addGap(19, 19, 19))
        );
        jpVentasTotalesLayout.setVerticalGroup(
            jpVentasTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpVentasTotalesLayout.createSequentialGroup()
                .addComponent(jLabel65)
                .addGap(8, 8, 8)
                .addComponent(cmbVentas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTituloVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblVentasResumen10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpVentasTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpVentasTotalesLayout.createSequentialGroup()
                        .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblEneroVentas))
                    .addGroup(jpVentasTotalesLayout.createSequentialGroup()
                        .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblFebreroVentas))
                    .addGroup(jpVentasTotalesLayout.createSequentialGroup()
                        .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMarzoVentas))
                    .addGroup(jpVentasTotalesLayout.createSequentialGroup()
                        .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblAbrilVentas))
                    .addGroup(jpVentasTotalesLayout.createSequentialGroup()
                        .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMayoVentas))
                    .addGroup(jpVentasTotalesLayout.createSequentialGroup()
                        .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblJunioVentas)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblVentasResumen11)
                .addGap(13, 13, 13)
                .addGroup(jpVentasTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpVentasTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jpVentasTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jpVentasTotalesLayout.createSequentialGroup()
                                .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblJulioVentas))
                            .addGroup(jpVentasTotalesLayout.createSequentialGroup()
                                .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblAgostoVentas)))
                        .addGroup(jpVentasTotalesLayout.createSequentialGroup()
                            .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblSeptiembreVentas))
                        .addGroup(jpVentasTotalesLayout.createSequentialGroup()
                            .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblOctubreVentas))
                        .addGroup(jpVentasTotalesLayout.createSequentialGroup()
                            .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblNoviembreVentas)))
                    .addGroup(jpVentasTotalesLayout.createSequentialGroup()
                        .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblDiciembreVentas)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblVentasResumen12)
                .addGap(18, 18, 18)
                .addComponent(lblYearTituloVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblYearVentas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnImprimirVentas)
                .addContainerGap())
        );

        jsVentasTotales.setViewportView(jpVentasTotales);

        jtpVentasGastosTotales.addTab("Ventas", jsVentasTotales);

        jsGastosDeVenta.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jsGastosDeVentaComponentShown(evt);
            }
        });

        jpGastosDeVenta.setBackground(new java.awt.Color(255, 255, 255));

        jtGastosDeVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Tienda", "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(jtGastosDeVenta);
        if (jtGastosDeVenta.getColumnModel().getColumnCount() > 0) {
            jtGastosDeVenta.getColumnModel().getColumn(0).setMinWidth(70);
            jtGastosDeVenta.getColumnModel().getColumn(0).setMaxWidth(70);
        }

        lblTituloGastosDeVenta.setBackground(new java.awt.Color(0, 204, 102));
        lblTituloGastosDeVenta.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        lblTituloGastosDeVenta.setForeground(new java.awt.Color(255, 255, 255));
        lblTituloGastosDeVenta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTituloGastosDeVenta.setText("Gastos de venta");
        lblTituloGastosDeVenta.setOpaque(true);

        cmbGastosDeVenta.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        cmbGastosDeVenta.setForeground(new java.awt.Color(0, 102, 153));
        cmbGastosDeVenta.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020" }));

        jLabel69.setBackground(new java.awt.Color(0, 102, 153));
        jLabel69.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel69.setForeground(new java.awt.Color(255, 255, 255));
        jLabel69.setText("AÑO TRABAJADO");
        jLabel69.setOpaque(true);

        lblVentasResumen7.setBackground(new java.awt.Color(0, 102, 153));
        lblVentasResumen7.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lblVentasResumen7.setForeground(new java.awt.Color(255, 255, 255));
        lblVentasResumen7.setText("RESULTADO ANUAL");
        lblVentasResumen7.setOpaque(true);

        lblYearTituloGastosVenta.setBackground(new java.awt.Color(0, 153, 204));
        lblYearTituloGastosVenta.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblYearTituloGastosVenta.setForeground(new java.awt.Color(255, 255, 255));
        lblYearTituloGastosVenta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblYearTituloGastosVenta.setText("2015");
        lblYearTituloGastosVenta.setOpaque(true);

        lblYearGastoDeVenta.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        lblYearGastoDeVenta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblYearGastoDeVenta.setText("0");
        lblYearGastoDeVenta.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblAbrilGastoDeVenta.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        lblAbrilGastoDeVenta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAbrilGastoDeVenta.setText("0");
        lblAbrilGastoDeVenta.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel35.setBackground(new java.awt.Color(0, 153, 204));
        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setText("Mayo");
        jLabel35.setOpaque(true);

        lblMayoGastoDeVenta.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        lblMayoGastoDeVenta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMayoGastoDeVenta.setText("0");
        lblMayoGastoDeVenta.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel36.setBackground(new java.awt.Color(0, 153, 204));
        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel36.setText("Junio");
        jLabel36.setOpaque(true);

        lblJunioGastoDeVenta.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        lblJunioGastoDeVenta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblJunioGastoDeVenta.setText("0");
        lblJunioGastoDeVenta.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel37.setBackground(new java.awt.Color(0, 153, 204));
        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel37.setText("Julio");
        jLabel37.setOpaque(true);

        lblJulioGastoDeVenta.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        lblJulioGastoDeVenta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblJulioGastoDeVenta.setText("0");
        lblJulioGastoDeVenta.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel38.setBackground(new java.awt.Color(0, 153, 204));
        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(255, 255, 255));
        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel38.setText("Agosto");
        jLabel38.setOpaque(true);

        lblAgostoGastoDeVenta.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        lblAgostoGastoDeVenta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAgostoGastoDeVenta.setText("0");
        lblAgostoGastoDeVenta.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel39.setBackground(new java.awt.Color(0, 153, 204));
        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(255, 255, 255));
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel39.setText("Septiembre");
        jLabel39.setOpaque(true);

        lblSeptiembreGastoDeVenta.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        lblSeptiembreGastoDeVenta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSeptiembreGastoDeVenta.setText("0");
        lblSeptiembreGastoDeVenta.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel40.setBackground(new java.awt.Color(0, 153, 204));
        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(255, 255, 255));
        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel40.setText("Octubre");
        jLabel40.setOpaque(true);

        lblOctubreGastoDeVenta.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        lblOctubreGastoDeVenta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblOctubreGastoDeVenta.setText("0");
        lblOctubreGastoDeVenta.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblVentasResumen8.setBackground(new java.awt.Color(0, 102, 153));
        lblVentasResumen8.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lblVentasResumen8.setForeground(new java.awt.Color(255, 255, 255));
        lblVentasResumen8.setText("1ER SEMESTRE");
        lblVentasResumen8.setOpaque(true);

        jLabel42.setBackground(new java.awt.Color(0, 153, 204));
        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(255, 255, 255));
        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel42.setText("Noviembre");
        jLabel42.setOpaque(true);

        lblVentasResumen9.setBackground(new java.awt.Color(0, 102, 153));
        lblVentasResumen9.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lblVentasResumen9.setForeground(new java.awt.Color(255, 255, 255));
        lblVentasResumen9.setText("2DO SEMESTRE");
        lblVentasResumen9.setOpaque(true);

        jLabel43.setBackground(new java.awt.Color(0, 153, 204));
        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(255, 255, 255));
        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel43.setText("Enero");
        jLabel43.setOpaque(true);

        lblNoviembreGastoDeVenta.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        lblNoviembreGastoDeVenta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNoviembreGastoDeVenta.setText("0");
        lblNoviembreGastoDeVenta.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblDiciembreGastoDeVenta.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        lblDiciembreGastoDeVenta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDiciembreGastoDeVenta.setText("0");
        lblDiciembreGastoDeVenta.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblEneroGastoDeVenta.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        lblEneroGastoDeVenta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEneroGastoDeVenta.setText("0");
        lblEneroGastoDeVenta.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel44.setBackground(new java.awt.Color(0, 153, 204));
        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(255, 255, 255));
        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel44.setText("Diciembre");
        jLabel44.setOpaque(true);

        jLabel45.setBackground(new java.awt.Color(0, 153, 204));
        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(255, 255, 255));
        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel45.setText("Febrero");
        jLabel45.setOpaque(true);

        lblFebreroGastoDeVenta.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        lblFebreroGastoDeVenta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFebreroGastoDeVenta.setText("0");
        lblFebreroGastoDeVenta.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel46.setBackground(new java.awt.Color(0, 153, 204));
        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(255, 255, 255));
        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel46.setText("Marzo");
        jLabel46.setOpaque(true);

        lblMarzoGastoDeVenta.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        lblMarzoGastoDeVenta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMarzoGastoDeVenta.setText("0");
        lblMarzoGastoDeVenta.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel47.setBackground(new java.awt.Color(0, 153, 204));
        jLabel47.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(255, 255, 255));
        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel47.setText("Abril");
        jLabel47.setOpaque(true);

        btnImprimirGastosDeVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/pdf.png"))); // NOI18N
        btnImprimirGastosDeVenta.setText("Imprimir resultados");
        btnImprimirGastosDeVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirGastosDeVentaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpGastosDeVentaLayout = new javax.swing.GroupLayout(jpGastosDeVenta);
        jpGastosDeVenta.setLayout(jpGastosDeVentaLayout);
        jpGastosDeVentaLayout.setHorizontalGroup(
            jpGastosDeVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpGastosDeVentaLayout.createSequentialGroup()
                .addGroup(jpGastosDeVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 845, Short.MAX_VALUE)
                    .addComponent(lblTituloGastosDeVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbGastosDeVenta, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel69, javax.swing.GroupLayout.DEFAULT_SIZE, 845, Short.MAX_VALUE)
                    .addComponent(lblVentasResumen7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblVentasResumen8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblVentasResumen9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jpGastosDeVentaLayout.createSequentialGroup()
                        .addGroup(jpGastosDeVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblEneroGastoDeVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpGastosDeVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblFebreroGastoDeVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpGastosDeVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMarzoGastoDeVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpGastosDeVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblAbrilGastoDeVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpGastosDeVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMayoGastoDeVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpGastosDeVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblJunioGastoDeVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jpGastosDeVentaLayout.createSequentialGroup()
                        .addGroup(jpGastosDeVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblJulioGastoDeVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpGastosDeVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblAgostoGastoDeVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpGastosDeVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSeptiembreGastoDeVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpGastosDeVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblOctubreGastoDeVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpGastosDeVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNoviembreGastoDeVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpGastosDeVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDiciembreGastoDeVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jpGastosDeVentaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jpGastosDeVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpGastosDeVentaLayout.createSequentialGroup()
                                .addComponent(lblYearTituloGastosVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jpGastosDeVentaLayout.createSequentialGroup()
                                .addComponent(lblYearGastoDeVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnImprimirGastosDeVenta)))))
                .addContainerGap())
        );
        jpGastosDeVentaLayout.setVerticalGroup(
            jpGastosDeVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpGastosDeVentaLayout.createSequentialGroup()
                .addComponent(jLabel69)
                .addGap(8, 8, 8)
                .addComponent(cmbGastosDeVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTituloGastosDeVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblVentasResumen8)
                .addGap(18, 18, 18)
                .addGroup(jpGastosDeVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpGastosDeVentaLayout.createSequentialGroup()
                        .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblEneroGastoDeVenta))
                    .addGroup(jpGastosDeVentaLayout.createSequentialGroup()
                        .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblFebreroGastoDeVenta))
                    .addGroup(jpGastosDeVentaLayout.createSequentialGroup()
                        .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMarzoGastoDeVenta))
                    .addGroup(jpGastosDeVentaLayout.createSequentialGroup()
                        .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblAbrilGastoDeVenta))
                    .addGroup(jpGastosDeVentaLayout.createSequentialGroup()
                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMayoGastoDeVenta))
                    .addGroup(jpGastosDeVentaLayout.createSequentialGroup()
                        .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblJunioGastoDeVenta)))
                .addGap(22, 22, 22)
                .addComponent(lblVentasResumen9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpGastosDeVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpGastosDeVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jpGastosDeVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jpGastosDeVentaLayout.createSequentialGroup()
                                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblJulioGastoDeVenta))
                            .addGroup(jpGastosDeVentaLayout.createSequentialGroup()
                                .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblAgostoGastoDeVenta)))
                        .addGroup(jpGastosDeVentaLayout.createSequentialGroup()
                            .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblSeptiembreGastoDeVenta))
                        .addGroup(jpGastosDeVentaLayout.createSequentialGroup()
                            .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblOctubreGastoDeVenta))
                        .addGroup(jpGastosDeVentaLayout.createSequentialGroup()
                            .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblNoviembreGastoDeVenta)))
                    .addGroup(jpGastosDeVentaLayout.createSequentialGroup()
                        .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblDiciembreGastoDeVenta)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addComponent(lblVentasResumen7)
                .addGap(18, 18, 18)
                .addGroup(jpGastosDeVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpGastosDeVentaLayout.createSequentialGroup()
                        .addComponent(lblYearTituloGastosVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblYearGastoDeVenta)
                        .addGap(39, 39, 39))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpGastosDeVentaLayout.createSequentialGroup()
                        .addComponent(btnImprimirGastosDeVenta)
                        .addContainerGap())))
        );

        jsGastosDeVenta.setViewportView(jpGastosDeVenta);

        jtpVentasGastosTotales.addTab("Gastos de Venta", jsGastosDeVenta);

        jsGastosGenerales.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jsGastosGeneralesComponentShown(evt);
            }
        });

        jpGastosGenerales.setBackground(new java.awt.Color(255, 255, 255));

        jtGastosGenerales.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Tienda", "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jtGastosGenerales);
        if (jtGastosGenerales.getColumnModel().getColumnCount() > 0) {
            jtGastosGenerales.getColumnModel().getColumn(2).setMinWidth(100);
            jtGastosGenerales.getColumnModel().getColumn(2).setMaxWidth(100);
            jtGastosGenerales.getColumnModel().getColumn(3).setMinWidth(100);
            jtGastosGenerales.getColumnModel().getColumn(3).setMaxWidth(100);
            jtGastosGenerales.getColumnModel().getColumn(4).setMinWidth(100);
            jtGastosGenerales.getColumnModel().getColumn(4).setMaxWidth(100);
            jtGastosGenerales.getColumnModel().getColumn(5).setMinWidth(100);
            jtGastosGenerales.getColumnModel().getColumn(5).setMaxWidth(100);
        }

        jLabel66.setBackground(new java.awt.Color(0, 102, 153));
        jLabel66.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(255, 255, 255));
        jLabel66.setText("AÑO TRABAJADO");
        jLabel66.setOpaque(true);

        cmbGastosGenerales.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        cmbGastosGenerales.setForeground(new java.awt.Color(0, 102, 153));
        cmbGastosGenerales.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020" }));

        lblTituloGastosGenerales.setBackground(new java.awt.Color(0, 204, 102));
        lblTituloGastosGenerales.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        lblTituloGastosGenerales.setForeground(new java.awt.Color(255, 255, 255));
        lblTituloGastosGenerales.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTituloGastosGenerales.setText("Gastos generales");
        lblTituloGastosGenerales.setOpaque(true);

        lblVentasResumen1.setBackground(new java.awt.Color(0, 102, 153));
        lblVentasResumen1.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lblVentasResumen1.setForeground(new java.awt.Color(255, 255, 255));
        lblVentasResumen1.setText("1ER SEMESTRE");
        lblVentasResumen1.setOpaque(true);

        lblVentasResumen2.setBackground(new java.awt.Color(0, 102, 153));
        lblVentasResumen2.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lblVentasResumen2.setForeground(new java.awt.Color(255, 255, 255));
        lblVentasResumen2.setText("2DO SEMESTRE");
        lblVentasResumen2.setOpaque(true);

        jLabel9.setBackground(new java.awt.Color(0, 153, 204));
        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Enero");
        jLabel9.setOpaque(true);

        lblEneroGasto.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        lblEneroGasto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEneroGasto.setText("0");
        lblEneroGasto.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel10.setBackground(new java.awt.Color(0, 153, 204));
        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Febrero");
        jLabel10.setOpaque(true);

        lblFebreroGasto.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        lblFebreroGasto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFebreroGasto.setText("0");
        lblFebreroGasto.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel11.setBackground(new java.awt.Color(0, 153, 204));
        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Marzo");
        jLabel11.setOpaque(true);

        lblMarzoGasto.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        lblMarzoGasto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMarzoGasto.setText("0");
        lblMarzoGasto.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel12.setBackground(new java.awt.Color(0, 153, 204));
        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Abril");
        jLabel12.setOpaque(true);

        lblAbrilGasto.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        lblAbrilGasto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAbrilGasto.setText("0");
        lblAbrilGasto.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel13.setBackground(new java.awt.Color(0, 153, 204));
        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Mayo");
        jLabel13.setOpaque(true);

        lblMayoGasto.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        lblMayoGasto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMayoGasto.setText("0");
        lblMayoGasto.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel14.setBackground(new java.awt.Color(0, 153, 204));
        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Junio");
        jLabel14.setOpaque(true);

        lblJunioGasto.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        lblJunioGasto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblJunioGasto.setText("0");
        lblJunioGasto.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel15.setBackground(new java.awt.Color(0, 153, 204));
        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Julio");
        jLabel15.setOpaque(true);

        lblJulioGasto.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        lblJulioGasto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblJulioGasto.setText("0");
        lblJulioGasto.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel16.setBackground(new java.awt.Color(0, 153, 204));
        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Agosto");
        jLabel16.setOpaque(true);

        lblAgostoGasto.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        lblAgostoGasto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAgostoGasto.setText("0");
        lblAgostoGasto.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel17.setBackground(new java.awt.Color(0, 153, 204));
        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Septiembre");
        jLabel17.setOpaque(true);

        lblSeptiembreGasto.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        lblSeptiembreGasto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSeptiembreGasto.setText("0");
        lblSeptiembreGasto.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel18.setBackground(new java.awt.Color(0, 153, 204));
        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Octubre");
        jLabel18.setOpaque(true);

        lblOctubreGasto.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        lblOctubreGasto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblOctubreGasto.setText("0");
        lblOctubreGasto.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel19.setBackground(new java.awt.Color(0, 153, 204));
        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Noviembre");
        jLabel19.setOpaque(true);

        lblNoviembreGasto.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        lblNoviembreGasto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNoviembreGasto.setText("0");
        lblNoviembreGasto.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblDiciembreGasto.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        lblDiciembreGasto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDiciembreGasto.setText("0");
        lblDiciembreGasto.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel20.setBackground(new java.awt.Color(0, 153, 204));
        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Diciembre");
        jLabel20.setOpaque(true);

        lblVentasResumen3.setBackground(new java.awt.Color(0, 102, 153));
        lblVentasResumen3.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lblVentasResumen3.setForeground(new java.awt.Color(255, 255, 255));
        lblVentasResumen3.setText("RESULTADO ANUAL");
        lblVentasResumen3.setOpaque(true);

        lblYearGasto.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        lblYearGasto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblYearGasto.setText("0");
        lblYearGasto.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblYearGastoGeneral.setBackground(new java.awt.Color(0, 153, 204));
        lblYearGastoGeneral.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblYearGastoGeneral.setForeground(new java.awt.Color(255, 255, 255));
        lblYearGastoGeneral.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblYearGastoGeneral.setText("2015");
        lblYearGastoGeneral.setOpaque(true);

        btnImprimirGastosGenerales.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/pdf.png"))); // NOI18N
        btnImprimirGastosGenerales.setText("Imprimir resultados");
        btnImprimirGastosGenerales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirGastosGeneralesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpGastosGeneralesLayout = new javax.swing.GroupLayout(jpGastosGenerales);
        jpGastosGenerales.setLayout(jpGastosGeneralesLayout);
        jpGastosGeneralesLayout.setHorizontalGroup(
            jpGastosGeneralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
            .addComponent(lblTituloGastosGenerales, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(cmbGastosGenerales, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel66, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblVentasResumen1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblVentasResumen2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblVentasResumen3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jpGastosGeneralesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpGastosGeneralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpGastosGeneralesLayout.createSequentialGroup()
                        .addGroup(jpGastosGeneralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblEneroGasto, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpGastosGeneralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblFebreroGasto, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpGastosGeneralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMarzoGasto, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpGastosGeneralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblAbrilGasto, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpGastosGeneralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMayoGasto, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpGastosGeneralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblJunioGasto, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jpGastosGeneralesLayout.createSequentialGroup()
                        .addGroup(jpGastosGeneralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblJulioGasto, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpGastosGeneralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblAgostoGasto, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpGastosGeneralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSeptiembreGasto, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpGastosGeneralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblOctubreGasto, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpGastosGeneralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNoviembreGasto, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpGastosGeneralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDiciembreGasto, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblYearGastoGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblYearGasto, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpGastosGeneralesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnImprimirGastosGenerales)
                .addContainerGap())
        );

        jpGastosGeneralesLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel10, jLabel11, jLabel12, jLabel13, jLabel14, jLabel9, lblAbrilGasto, lblEneroGasto, lblFebreroGasto, lblJunioGasto, lblMarzoGasto, lblMayoGasto});

        jpGastosGeneralesLayout.setVerticalGroup(
            jpGastosGeneralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpGastosGeneralesLayout.createSequentialGroup()
                .addComponent(jLabel66)
                .addGap(8, 8, 8)
                .addComponent(cmbGastosGenerales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTituloGastosGenerales, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblVentasResumen1)
                .addGap(18, 18, 18)
                .addGroup(jpGastosGeneralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpGastosGeneralesLayout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblEneroGasto))
                    .addGroup(jpGastosGeneralesLayout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblFebreroGasto))
                    .addGroup(jpGastosGeneralesLayout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMarzoGasto))
                    .addGroup(jpGastosGeneralesLayout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblAbrilGasto))
                    .addGroup(jpGastosGeneralesLayout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMayoGasto))
                    .addGroup(jpGastosGeneralesLayout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblJunioGasto)))
                .addGap(22, 22, 22)
                .addComponent(lblVentasResumen2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpGastosGeneralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpGastosGeneralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jpGastosGeneralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jpGastosGeneralesLayout.createSequentialGroup()
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblJulioGasto))
                            .addGroup(jpGastosGeneralesLayout.createSequentialGroup()
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblAgostoGasto)))
                        .addGroup(jpGastosGeneralesLayout.createSequentialGroup()
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblSeptiembreGasto))
                        .addGroup(jpGastosGeneralesLayout.createSequentialGroup()
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblOctubreGasto))
                        .addGroup(jpGastosGeneralesLayout.createSequentialGroup()
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblNoviembreGasto)))
                    .addGroup(jpGastosGeneralesLayout.createSequentialGroup()
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblDiciembreGasto)))
                .addGap(25, 25, 25)
                .addComponent(lblVentasResumen3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblYearGastoGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblYearGasto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(btnImprimirGastosGenerales)
                .addContainerGap())
        );

        jsGastosGenerales.setViewportView(jpGastosGenerales);

        jtpVentasGastosTotales.addTab("Gastos generales", jsGastosGenerales);

        jsCompras.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                jsComprasComponentResized(evt);
            }
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jsComprasComponentShown(evt);
            }
        });

        jpCompras.setBackground(new java.awt.Color(255, 255, 255));

        jtCompras.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Tienda", "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(jtCompras);
        if (jtCompras.getColumnModel().getColumnCount() > 0) {
            jtCompras.getColumnModel().getColumn(2).setMinWidth(100);
            jtCompras.getColumnModel().getColumn(2).setMaxWidth(100);
            jtCompras.getColumnModel().getColumn(3).setMinWidth(100);
            jtCompras.getColumnModel().getColumn(3).setMaxWidth(100);
            jtCompras.getColumnModel().getColumn(4).setMinWidth(100);
            jtCompras.getColumnModel().getColumn(4).setMaxWidth(100);
            jtCompras.getColumnModel().getColumn(5).setMinWidth(100);
            jtCompras.getColumnModel().getColumn(5).setMaxWidth(100);
        }

        jLabel67.setBackground(new java.awt.Color(0, 102, 153));
        jLabel67.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(255, 255, 255));
        jLabel67.setText("AÑO TRABAJADO");
        jLabel67.setOpaque(true);

        cmbCompras.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        cmbCompras.setForeground(new java.awt.Color(0, 102, 153));
        cmbCompras.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020" }));

        lblTituloCompras.setBackground(new java.awt.Color(0, 204, 102));
        lblTituloCompras.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        lblTituloCompras.setForeground(new java.awt.Color(255, 255, 255));
        lblTituloCompras.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTituloCompras.setText("Compras");
        lblTituloCompras.setOpaque(true);

        lblVentasResumen4.setBackground(new java.awt.Color(0, 102, 153));
        lblVentasResumen4.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lblVentasResumen4.setForeground(new java.awt.Color(255, 255, 255));
        lblVentasResumen4.setText("1ER SEMESTRE");
        lblVentasResumen4.setOpaque(true);

        lblVentasResumen5.setBackground(new java.awt.Color(0, 102, 153));
        lblVentasResumen5.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lblVentasResumen5.setForeground(new java.awt.Color(255, 255, 255));
        lblVentasResumen5.setText("2DO SEMESTRE");
        lblVentasResumen5.setOpaque(true);

        jLabel22.setBackground(new java.awt.Color(0, 153, 204));
        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Enero");
        jLabel22.setOpaque(true);

        lblEneroCompras.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        lblEneroCompras.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEneroCompras.setText("0");
        lblEneroCompras.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel23.setBackground(new java.awt.Color(0, 153, 204));
        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("Febrero");
        jLabel23.setOpaque(true);

        lblFebreroCompras.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        lblFebreroCompras.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFebreroCompras.setText("0");
        lblFebreroCompras.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel24.setBackground(new java.awt.Color(0, 153, 204));
        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("Marzo");
        jLabel24.setOpaque(true);

        lblMarzoCompras.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        lblMarzoCompras.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMarzoCompras.setText("0");
        lblMarzoCompras.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel25.setBackground(new java.awt.Color(0, 153, 204));
        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("Abril");
        jLabel25.setOpaque(true);

        lblAbrilCompras.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        lblAbrilCompras.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAbrilCompras.setText("0");
        lblAbrilCompras.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel26.setBackground(new java.awt.Color(0, 153, 204));
        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("Mayo");
        jLabel26.setOpaque(true);

        lblMayoCompras.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        lblMayoCompras.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMayoCompras.setText("0");
        lblMayoCompras.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel27.setBackground(new java.awt.Color(0, 153, 204));
        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("Junio");
        jLabel27.setOpaque(true);

        lblJunioCompras.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        lblJunioCompras.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblJunioCompras.setText("0");
        lblJunioCompras.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel28.setBackground(new java.awt.Color(0, 153, 204));
        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("Julio");
        jLabel28.setOpaque(true);

        lblJulioCompras.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        lblJulioCompras.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblJulioCompras.setText("0");
        lblJulioCompras.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel29.setBackground(new java.awt.Color(0, 153, 204));
        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("Agosto");
        jLabel29.setOpaque(true);

        lblAgostoCompras.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        lblAgostoCompras.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAgostoCompras.setText("0");
        lblAgostoCompras.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel30.setBackground(new java.awt.Color(0, 153, 204));
        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("Septiembre");
        jLabel30.setOpaque(true);

        lblSeptiembreCompras.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        lblSeptiembreCompras.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSeptiembreCompras.setText("0");
        lblSeptiembreCompras.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel31.setBackground(new java.awt.Color(0, 153, 204));
        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText("Octubre");
        jLabel31.setOpaque(true);

        lblOctubreCompras.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        lblOctubreCompras.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblOctubreCompras.setText("0");
        lblOctubreCompras.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel32.setBackground(new java.awt.Color(0, 153, 204));
        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setText("Noviembre");
        jLabel32.setOpaque(true);

        lblNoviembreCompras.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        lblNoviembreCompras.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNoviembreCompras.setText("0");
        lblNoviembreCompras.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblDiciembreCompras.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        lblDiciembreCompras.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDiciembreCompras.setText("0");
        lblDiciembreCompras.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel33.setBackground(new java.awt.Color(0, 153, 204));
        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setText("Diciembre");
        jLabel33.setOpaque(true);

        lblVentasResumen6.setBackground(new java.awt.Color(0, 102, 153));
        lblVentasResumen6.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lblVentasResumen6.setForeground(new java.awt.Color(255, 255, 255));
        lblVentasResumen6.setText("RESULTADO ANUAL");
        lblVentasResumen6.setOpaque(true);

        lblYearCompras.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        lblYearCompras.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblYearCompras.setText("0");
        lblYearCompras.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblYearComprasAnio.setBackground(new java.awt.Color(0, 153, 204));
        lblYearComprasAnio.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblYearComprasAnio.setForeground(new java.awt.Color(255, 255, 255));
        lblYearComprasAnio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblYearComprasAnio.setText("2015");
        lblYearComprasAnio.setOpaque(true);

        btnImprimirCompras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/pdf.png"))); // NOI18N
        btnImprimirCompras.setText("Imprimir resultados");
        btnImprimirCompras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirComprasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpComprasLayout = new javax.swing.GroupLayout(jpCompras);
        jpCompras.setLayout(jpComprasLayout);
        jpComprasLayout.setHorizontalGroup(
            jpComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5)
            .addComponent(lblTituloCompras, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(cmbCompras, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel67, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblVentasResumen4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblVentasResumen5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblVentasResumen6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jpComprasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpComprasLayout.createSequentialGroup()
                        .addGroup(jpComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblEneroCompras, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblFebreroCompras, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMarzoCompras, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblAbrilCompras, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMayoCompras, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblJunioCompras, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jpComprasLayout.createSequentialGroup()
                        .addGroup(jpComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblJulioCompras, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblAgostoCompras, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSeptiembreCompras, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblOctubreCompras, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNoviembreCompras, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDiciembreCompras, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblYearComprasAnio, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblYearCompras, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpComprasLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnImprimirCompras)
                .addContainerGap())
        );
        jpComprasLayout.setVerticalGroup(
            jpComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpComprasLayout.createSequentialGroup()
                .addComponent(jLabel67)
                .addGap(8, 8, 8)
                .addComponent(cmbCompras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTituloCompras, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblVentasResumen4)
                .addGap(18, 18, 18)
                .addGroup(jpComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpComprasLayout.createSequentialGroup()
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblEneroCompras))
                    .addGroup(jpComprasLayout.createSequentialGroup()
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblFebreroCompras))
                    .addGroup(jpComprasLayout.createSequentialGroup()
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMarzoCompras))
                    .addGroup(jpComprasLayout.createSequentialGroup()
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblAbrilCompras))
                    .addGroup(jpComprasLayout.createSequentialGroup()
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMayoCompras))
                    .addGroup(jpComprasLayout.createSequentialGroup()
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblJunioCompras)))
                .addGap(22, 22, 22)
                .addComponent(lblVentasResumen5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jpComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jpComprasLayout.createSequentialGroup()
                                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblJulioCompras))
                            .addGroup(jpComprasLayout.createSequentialGroup()
                                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblAgostoCompras)))
                        .addGroup(jpComprasLayout.createSequentialGroup()
                            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblSeptiembreCompras))
                        .addGroup(jpComprasLayout.createSequentialGroup()
                            .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblOctubreCompras))
                        .addGroup(jpComprasLayout.createSequentialGroup()
                            .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblNoviembreCompras)))
                    .addGroup(jpComprasLayout.createSequentialGroup()
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblDiciembreCompras)))
                .addGap(25, 25, 25)
                .addComponent(lblVentasResumen6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblYearComprasAnio, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblYearCompras)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(btnImprimirCompras)
                .addContainerGap())
        );

        jsCompras.setViewportView(jpCompras);

        jtpVentasGastosTotales.addTab("Compras", jsCompras);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jtpVentasGastosTotales)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jtpVentasGastosTotales, javax.swing.GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jsResumenVentasComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jsResumenVentasComponentShown
        addActions();
        llenaResumen();
    }//GEN-LAST:event_jsResumenVentasComponentShown
    private void llenaResumen() {
        limpiarTablas(jtResumenGeneral);
        AccesoBD acceso = new AccesoBD();
        consultarVentasDeDiario();
        List<Object> listaDeDiarioDeVentas = acceso.select(tiendasHQL);
        double totalVentasDelDia = 0;
        double totalVentas = 0;
        double totalVentasConNota = 0;
        double totalVentasSinNota = 0;
        double totalAbonoCredito = 0;
        double totalGastosDeVenta = 0;
        double totalGastosGenerales = 0;
        double gastosGeneralesDelDia = 0;
        double compras = 0;
        double totalCompras = 0;
        int tiendasAbiertas = 0;
        Map parametros = new HashMap();
        Map parametrosCompras = new HashMap();
        for (Iterator<Object> iterator = listaDeDiarioDeVentas.iterator(); iterator.hasNext();) {
            Diariocaja venta = (Diariocaja) iterator.next();
            Date fecha = venta.getFecha();
            String tiendas = getNombreTiendasAbiertas(fecha);
            tiendasAbiertas = getNoTiendasAbiertas(fecha);
            double gastosPersonales = getGastosPersonales(fecha);
            double gasolina = getGasolina(fecha);
            double oficinaBodega = getOficinaBodega(fecha);
            double mantenimientoV = getMantenimientoV(fecha);
            double prestamos = getSueldoPrestamos(fecha);
            gastosGeneralesDelDia = gastosPersonales + gasolina + mantenimientoV + oficinaBodega + prestamos;
            compras = getCompras(fecha);
            totalVentasConNota = totalVentasConNota + venta.getVentaConNota();
            totalVentasSinNota = totalVentasSinNota + venta.getVentaSinNota();
            totalAbonoCredito = totalAbonoCredito + venta.getAbonoCredito();
            totalGastosDeVenta = totalGastosDeVenta + venta.getGastos();
            totalVentasDelDia = venta.getVentaConNota() + venta.getVentaSinNota() + venta.getAbonoCredito();
            totalVentas = totalVentas + totalVentasDelDia;
            //COMPRUEBO QUE LA FECHA DEL GASTO NO ESTE REPETIDA
            if (!parametros.containsKey(fecha)) {
                parametros.put(fecha, venta.getLocal());
                if (cmbTiendas.getSelectedItem().toString().equals("Todas")) {
                    totalGastosGenerales = totalGastosGenerales + gastosGeneralesDelDia;
                } else {
                    totalGastosGenerales = totalGastosGenerales + (gastosGeneralesDelDia / tiendasAbiertas);
                }
            }
            if (!parametrosCompras.containsKey(fecha)) {
                parametrosCompras.put(fecha, venta.getLocal());
                if (cmbTiendas.getSelectedItem().toString().equals("Todas")) {
                    totalCompras = totalCompras + compras;
                } else {
                    totalCompras = totalCompras + (compras / tiendasAbiertas);
                }
            }
            DefaultTableModel model = (DefaultTableModel) jtResumenGeneral.getModel();
            model.addRow(new String[]{
                fecha.toString(),
                venta.getLocal(),
                venta.getNotas(),
                String.valueOf(venta.getVentaConNota()),
                String.valueOf(venta.getVentaSinNota()),
                String.valueOf(venta.getAbonoCredito()),
                String.valueOf(venta.getGastos()),
                String.valueOf(tiendasAbiertas),
                String.valueOf(tiendas),
                String.valueOf(gastosGeneralesDelDia / tiendasAbiertas),
                String.valueOf(compras / tiendasAbiertas),
                String.valueOf(totalVentasDelDia)
            });
            jtResumenGeneral.setModel(model);
        }
        //VENTAS
        lblVentasConNota.setText(String.valueOf(totalVentasConNota));
        lblVentasSinNota.setText(String.valueOf(totalVentasSinNota));
        lblAbonoCredito.setText(String.valueOf(totalAbonoCredito));
        lblTotalDeVentas.setText(String.valueOf(totalVentas));
        //GASTOS
        lblGastosDeVenta.setText(String.valueOf(totalGastosDeVenta));
        lblGastosGenerales.setText(String.valueOf(totalGastosGenerales));
        lblCompras.setText(String.valueOf(totalCompras));
        lblTotalDeGastos.setText(String.valueOf(totalGastosDeVenta + totalGastosGenerales + totalCompras));
        //RESULTADO
        double resultado = totalVentas - (totalGastosDeVenta + totalGastosGenerales + totalCompras);

        if (resultado > 0) {
            lblResultadoTexto.setText("Utilidad");
        } else {
            lblResultadoTexto.setText("Perdida");
        }
        if (resultado == 0) {
            lblResultadoTexto.setText("Neutro");
        }
        lblResultado.setText(String.valueOf(resultado));
    }
    private void jsVentasTotalesComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jsVentasTotalesComponentShown
        llenarVentas();
    }//GEN-LAST:event_jsVentasTotalesComponentShown
    private void llenarVentas() {
        String anioVenta = cmbVentas.getSelectedItem().toString();
        lblTituloVentas.setText("Ventas de: " + anioVenta);
        lblYearTituloVentas.setText(anioVenta);
        limpiarTablas(jtVentasTotales);
        List<Object> listaDeTiendas = getNombreTiendasAbiertas();
        double sumaGastosDeVentaTotal = 0;
        inializarMeses();
        for (Iterator<Object> iterator = listaDeTiendas.iterator(); iterator.hasNext();) {
            String tienda = (String) iterator.next();
            cuentaMensual = new String[14];
            double totalAnualPorTienda = 0;
            double sumaVentas = 0;
            for (int i = 0; i < 13; i++) {
                sumaVentas = crearConsultaSumaVentas(i, tienda, anioVenta);
                filtrarGastosPorMes(tienda, i, sumaVentas);
                totalAnualPorTienda = totalAnualPorTienda + sumaVentas;
            }
            sumaGastosDeVentaTotal = sumaGastosDeVentaTotal + totalAnualPorTienda;
            cuentaMensual[13] = String.valueOf(totalAnualPorTienda);
            DefaultTableModel model = (DefaultTableModel) jtVentasTotales.getModel();
            model.addRow(cuentaMensual);
            jtVentasTotales.setModel(model);
        }
        lblYearGastoDeVenta.setText(String.valueOf(sumaGastosDeVentaTotal));
        lblEneroVentas.setText(String.valueOf(enero));
        lblFebreroVentas.setText(String.valueOf(febrero));
        lblMarzoVentas.setText(String.valueOf(marzo));
        lblAbrilVentas.setText(String.valueOf(abril));
        lblMayoVentas.setText(String.valueOf(mayo));
        lblJunioVentas.setText(String.valueOf(junio));
        lblJulioVentas.setText(String.valueOf(julio));
        lblAgostoVentas.setText(String.valueOf(agosto));
        lblSeptiembreVentas.setText(String.valueOf(septiembre));
        lblOctubreVentas.setText(String.valueOf(octubre));
        lblNoviembreVentas.setText(String.valueOf(noviembre));
        lblDiciembreVentas.setText(String.valueOf(diciembre));
        lblYearVentas.setText(String.valueOf(year));
    }
    private void jsGastosGeneralesComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jsGastosGeneralesComponentShown
        llenarGastosGenerales();
    }//GEN-LAST:event_jsGastosGeneralesComponentShown
    private void llenarGastosGenerales() {
        String anioGastoGeneral = cmbGastosGenerales.getSelectedItem().toString();
        lblTituloGastosGenerales.setText("Gastos generales de: " + anioGastoGeneral);
        lblYearGastoGeneral.setText(anioGastoGeneral);
        limpiarTablas(jtGastosGenerales);
        AccesoBD acceso = new AccesoBD();
        DefaultTableModel model = (DefaultTableModel) jtGastosGenerales.getModel();
        List<Object> listaNombreTiendas = getNombreTiendasAbiertas();
        inializarMeses();
        for (Iterator<Object> iter = listaNombreTiendas.iterator(); iter.hasNext();) {
            String nombreTienda = (String) iter.next();
            double gastosGeneralesDelDia = 0;
            double gastosGeneralesDelMes = 0;
            double gastosGeneralesAnualesPorTienda = 0;
            cuentaMensual = new String[14];
            for (int i = 0; i < 13; i++) {
                consultarVentasDeDiarioPorMes(i, nombreTienda, anioGastoGeneral);
                List<Object> listaDeDiarioDeVentas = acceso.select(tiendasHQL);
                Map parametros = new HashMap();
                for (Iterator<Object> iterator = listaDeDiarioDeVentas.iterator(); iterator.hasNext();) {
                    Diariocaja venta = (Diariocaja) iterator.next();
                    Date fecha = venta.getFecha();
                    int tiendasAbiertas = getNoTiendasAbiertas(fecha);
                    double gastosPersonales = getGastosPersonales(fecha);
                    double gasolina = getGasolina(fecha);
                    double oficinaBodega = getOficinaBodega(fecha);
                    double mantenimientoV = getMantenimientoV(fecha);
                    double prestamos = getSueldoPrestamos(fecha);
                    //COMPRUEBO QUE LA FECHA DEL GASTO NO ESTE REPETIDA
                    if (!parametros.containsKey(fecha)) {
                        parametros.put(fecha, nombreTienda);
                        gastosGeneralesDelDia = (gastosPersonales + gasolina + mantenimientoV + oficinaBodega + prestamos) / tiendasAbiertas;
                        gastosGeneralesDelMes = gastosGeneralesDelMes + gastosGeneralesDelDia;
                    }
                }
                filtrarGastosPorMes(nombreTienda, i, gastosGeneralesDelMes);
                gastosGeneralesAnualesPorTienda = gastosGeneralesAnualesPorTienda + gastosGeneralesDelMes;
            }
            cuentaMensual[13] = String.valueOf(gastosGeneralesAnualesPorTienda);
            //ARREGLO QUE CONTIENE LOS MESES Y SU DINERO
            model.addRow(cuentaMensual);
        }
        jtGastosGenerales.setModel(model);
        lblEneroGasto.setText(String.valueOf(enero));
        lblFebreroGasto.setText(String.valueOf(febrero));
        lblMarzoGasto.setText(String.valueOf(marzo));
        lblAbrilGasto.setText(String.valueOf(abril));
        lblMayoGasto.setText(String.valueOf(mayo));
        lblJunioGasto.setText(String.valueOf(junio));
        lblJulioGasto.setText(String.valueOf(julio));
        lblAgostoGasto.setText(String.valueOf(agosto));
        lblSeptiembreGasto.setText(String.valueOf(septiembre));
        lblOctubreGasto.setText(String.valueOf(octubre));
        lblNoviembreGasto.setText(String.valueOf(noviembre));
        lblDiciembreGasto.setText(String.valueOf(diciembre));
        lblYearGasto.setText(String.valueOf(year));
    }
    private void jsComprasComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jsComprasComponentShown
        llenarCompras();
    }//GEN-LAST:event_jsComprasComponentShown
    private void llenarCompras() {
        String anioGDV = cmbCompras.getSelectedItem().toString();
        lblTituloCompras.setText("Compras de: " + anioGDV);
        lblYearComprasAnio.setText(anioGDV);
        limpiarTablas(jtCompras);
        AccesoBD acceso = new AccesoBD();
        DefaultTableModel model = (DefaultTableModel) jtCompras.getModel();
        List<Object> listaNombreTiendas = getNombreTiendasAbiertas();
        inializarMeses();
        for (Iterator<Object> iter = listaNombreTiendas.iterator(); iter.hasNext();) {
            String nombreTienda = (String) iter.next();
            double comprasDelDia = 0;
            double comprasDelMes = 0;
            double comprasAnualesPorTienda = 0;
            cuentaMensual = new String[14];
            for (int i = 0; i < 13; i++) {
                consultarVentasDeDiarioPorMes(i, nombreTienda, anioGDV);
                List<Object> listaDeDiarioDeVentas = acceso.select(tiendasHQL);
                Map parametros = new HashMap();
                for (Iterator<Object> iterator = listaDeDiarioDeVentas.iterator(); iterator.hasNext();) {
                    Diariocaja venta = (Diariocaja) iterator.next();
                    Date fecha = venta.getFecha();
                    int tiendasAbiertas = getNoTiendasAbiertas(fecha);
                    double compras = getCompras(fecha);
                    //COMPRUEBO QUE LA FECHA DEL GASTO NO ESTE REPETIDA
                    if (!parametros.containsKey(fecha)) {
                        parametros.put(fecha, nombreTienda);
                        comprasDelDia = (compras) / tiendasAbiertas;
                        comprasDelMes = comprasDelMes + comprasDelDia;
                    }
                }
                filtrarGastosPorMes(nombreTienda, i, comprasDelMes);
                comprasAnualesPorTienda = comprasAnualesPorTienda + comprasDelMes;
            }
            cuentaMensual[13] = String.valueOf(comprasAnualesPorTienda);
            //ARREGLO QUE CONTIENE LOS MESES Y SU DINERO
            model.addRow(cuentaMensual);
        }
        jtCompras.setModel(model);
        lblEneroCompras.setText(String.valueOf(enero));
        lblFebreroCompras.setText(String.valueOf(febrero));
        lblMarzoCompras.setText(String.valueOf(marzo));
        lblAbrilCompras.setText(String.valueOf(abril));
        lblMayoCompras.setText(String.valueOf(mayo));
        lblJunioCompras.setText(String.valueOf(junio));
        lblJulioCompras.setText(String.valueOf(julio));
        lblAgostoCompras.setText(String.valueOf(agosto));
        lblSeptiembreCompras.setText(String.valueOf(septiembre));
        lblOctubreCompras.setText(String.valueOf(octubre));
        lblNoviembreCompras.setText(String.valueOf(noviembre));
        lblDiciembreCompras.setText(String.valueOf(diciembre));
        lblYearCompras.setText(String.valueOf(year));
    }
    private void jsComprasComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jsComprasComponentResized
        // TODO add your handling code here:
    }//GEN-LAST:event_jsComprasComponentResized

    private void jsGastosDeVentaComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jsGastosDeVentaComponentShown
        llenarGastosDeVenta();
    }//GEN-LAST:event_jsGastosDeVentaComponentShown

    private void btnImprimirResumenGeneralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirResumenGeneralActionPerformed
        if (jtResumenGeneral.getRowCount() > 0) {
            Map parametros = new HashMap();
            parametros.put("tiendas", cmbTiendas.getSelectedItem().toString());
            parametros.put("ventasConNota", lblVentasConNota.getText());
            parametros.put("ventasSinNota", lblVentasSinNota.getText());
            parametros.put("abonoCredito", lblAbonoCredito.getText());
            parametros.put("totalVentas", lblTotalDeVentas.getText());
            parametros.put("gastosDeVenta", lblGastosDeVenta.getText());
            parametros.put("gastosGenerales", lblGastosGenerales.getText());
            parametros.put("compras", lblCompras.getText());
            parametros.put("totalGastos", lblTotalDeGastos.getText());
            parametros.put("resultado", lblResultado.getText());
            parametros.put("fechaInicio", jdcFechaConceptoResumenInicio.getDate().toString());
            parametros.put("fechaFin", jdcFechaConceptoResumenFin.getDate().toString());
            ReportMakerNDB reporte = new ReportMakerNDB(jtResumenGeneral, "resumenGeneral", parametros, false);
        } else {
            JOptionPane.showMessageDialog(rootPane, "No hay resultados que imprimir");
        }
    }//GEN-LAST:event_btnImprimirResumenGeneralActionPerformed

    private void btnImprimirVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirVentasActionPerformed
        if (jtVentasTotales.getRowCount() > 0) {
            Map parametros = new HashMap();
            parametros.put("razonDelResumen", "Ventas " + cmbVentas.getSelectedItem().toString());
            parametros.put("enero", lblEneroVentas.getText());
            parametros.put("febrero", lblFebreroVentas.getText());
            parametros.put("marzo", lblMarzoVentas.getText());
            parametros.put("abril", lblAbrilVentas.getText());
            parametros.put("mayo", lblMayoVentas.getText());
            parametros.put("junio", lblJunioVentas.getText());
            parametros.put("julio", lblJulioVentas.getText());
            parametros.put("agosto", lblAgostoVentas.getText());
            parametros.put("septiembre", lblSeptiembreVentas.getText());
            parametros.put("octubre", lblOctubreVentas.getText());
            parametros.put("noviembre", lblNoviembreVentas.getText());
            parametros.put("diciembre", lblDiciembreVentas.getText());
            parametros.put("totalAnual", lblYearVentas.getText());
            ReportMakerNDB reporte = new ReportMakerNDB(jtVentasTotales, "resumenVentasOGastos", parametros, false);
        } else {
            JOptionPane.showMessageDialog(rootPane, "No hay resultados que imprimir");
        }
    }//GEN-LAST:event_btnImprimirVentasActionPerformed

    private void btnImprimirGastosDeVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirGastosDeVentaActionPerformed
        if (jtGastosDeVenta.getRowCount() > 0) {
            Map parametros = new HashMap();
            parametros.put("razonDelResumen", "Gastos de ventas de " + cmbGastosDeVenta.getSelectedItem().toString());
            parametros.put("enero", lblEneroGastoDeVenta.getText());
            parametros.put("febrero", lblFebreroGastoDeVenta.getText());
            parametros.put("marzo", lblMarzoGastoDeVenta.getText());
            parametros.put("abril", lblAbrilGastoDeVenta.getText());
            parametros.put("mayo", lblMayoGastoDeVenta.getText());
            parametros.put("junio", lblJunioGastoDeVenta.getText());
            parametros.put("julio", lblJulioGastoDeVenta.getText());
            parametros.put("agosto", lblAgostoGastoDeVenta.getText());
            parametros.put("septiembre", lblSeptiembreGastoDeVenta.getText());
            parametros.put("octubre", lblOctubreGastoDeVenta.getText());
            parametros.put("noviembre", lblNoviembreGastoDeVenta.getText());
            parametros.put("diciembre", lblDiciembreGastoDeVenta.getText());
            parametros.put("totalAnual", lblYearGastoDeVenta.getText());
            ReportMakerNDB reporte = new ReportMakerNDB(jtGastosDeVenta, "resumenVentasOGastos", parametros, false);
        } else {
            JOptionPane.showMessageDialog(rootPane, "No hay resultados que imprimir");
        }
    }//GEN-LAST:event_btnImprimirGastosDeVentaActionPerformed

    private void btnImprimirGastosGeneralesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirGastosGeneralesActionPerformed
        if (jtGastosGenerales.getRowCount() > 0) {
            Map parametros = new HashMap();
            parametros.put("razonDelResumen", "Gastos Generales de " + cmbGastosGenerales.getSelectedItem().toString());
            parametros.put("enero", lblEneroGasto.getText());
            parametros.put("febrero", lblFebreroGasto.getText());
            parametros.put("marzo", lblMarzoGasto.getText());
            parametros.put("abril", lblAbrilGasto.getText());
            parametros.put("mayo", lblMayoGasto.getText());
            parametros.put("junio", lblJunioGasto.getText());
            parametros.put("julio", lblJulioGasto.getText());
            parametros.put("agosto", lblAgostoGasto.getText());
            parametros.put("septiembre", lblSeptiembreGasto.getText());
            parametros.put("octubre", lblOctubreGasto.getText());
            parametros.put("noviembre", lblNoviembreGasto.getText());
            parametros.put("diciembre", lblDiciembreGasto.getText());
            parametros.put("totalAnual", lblYearGastoGeneral.getText());
            ReportMakerNDB reporte = new ReportMakerNDB(jtGastosGenerales, "resumenVentasOGastos", parametros, false);
        } else {
            JOptionPane.showMessageDialog(rootPane, "No hay resultados que imprimir");
        }
    }//GEN-LAST:event_btnImprimirGastosGeneralesActionPerformed

    private void btnImprimirComprasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirComprasActionPerformed
        if (jtCompras.getRowCount() > 0) {
            Map parametros = new HashMap();
            parametros.put("razonDelResumen", "Compras de " + cmbCompras.getSelectedItem().toString());
            parametros.put("enero", lblEneroCompras.getText());
            parametros.put("febrero", lblFebreroCompras.getText());
            parametros.put("marzo", lblMarzoCompras.getText());
            parametros.put("abril", lblAbrilCompras.getText());
            parametros.put("mayo", lblMayoCompras.getText());
            parametros.put("junio", lblJunioCompras.getText());
            parametros.put("julio", lblJulioCompras.getText());
            parametros.put("agosto", lblAgostoCompras.getText());
            parametros.put("septiembre", lblSeptiembreCompras.getText());
            parametros.put("octubre", lblOctubreCompras.getText());
            parametros.put("noviembre", lblNoviembreCompras.getText());
            parametros.put("diciembre", lblDiciembreCompras.getText());
            parametros.put("totalAnual", lblYearCompras.getText());
            ReportMakerNDB reporte = new ReportMakerNDB(jtCompras, "resumenVentasOGastos", parametros, false);
        } else {
            JOptionPane.showMessageDialog(rootPane, "No hay resultados que imprimir");
        }
    }//GEN-LAST:event_btnImprimirComprasActionPerformed

    private void llenarGastosDeVenta() {
        String anioGDV = cmbGastosDeVenta.getSelectedItem().toString();
        lblTituloGastosDeVenta.setText("Gastos de venta de: " + anioGDV);
        lblYearTituloGastosVenta.setText(anioGDV);
        limpiarTablas(jtGastosDeVenta);
        List<Object> listaDeTiendas = getNombreTiendasAbiertas();
        double sumaGastosDeVentaTotal = 0;
        inializarMeses();
        for (Iterator<Object> iterator = listaDeTiendas.iterator(); iterator.hasNext();) {
            String tienda = (String) iterator.next();
            //REDIMENSIONO EL ARRAY DE MESES Y DINERO
            double totalAnualPorTienda = 0;
            double sumaGastosDeVenta = 0;
            cuentaMensual = new String[14];
            for (int i = 0; i < 13; i++) {
                sumaGastosDeVenta = crearConsultaSumaGastosDeVenta(i, tienda, anioGDV);
                filtrarGastosPorMes(tienda, i, sumaGastosDeVenta);
                totalAnualPorTienda = totalAnualPorTienda + sumaGastosDeVenta;
            }
            sumaGastosDeVentaTotal = sumaGastosDeVentaTotal + totalAnualPorTienda;
            cuentaMensual[13] = String.valueOf(totalAnualPorTienda);
            DefaultTableModel model = (DefaultTableModel) jtGastosDeVenta.getModel();
            model.addRow(cuentaMensual);
            jtGastosDeVenta.setModel(model);
        }
        lblYearGasto.setText(String.valueOf(sumaGastosDeVentaTotal));
        lblEneroGastoDeVenta.setText(String.valueOf(enero));
        lblFebreroGastoDeVenta.setText(String.valueOf(febrero));
        lblMarzoGastoDeVenta.setText(String.valueOf(marzo));
        lblAbrilGastoDeVenta.setText(String.valueOf(abril));
        lblMayoGastoDeVenta.setText(String.valueOf(mayo));
        lblJunioGastoDeVenta.setText(String.valueOf(junio));
        lblJulioGastoDeVenta.setText(String.valueOf(julio));
        lblAgostoGastoDeVenta.setText(String.valueOf(agosto));
        lblSeptiembreGastoDeVenta.setText(String.valueOf(septiembre));
        lblOctubreGastoDeVenta.setText(String.valueOf(octubre));
        lblNoviembreGastoDeVenta.setText(String.valueOf(noviembre));
        lblDiciembreGastoDeVenta.setText(String.valueOf(diciembre));
        lblYearGastoDeVenta.setText(String.valueOf(year));
    }

    private String[] filtrarGastosPorMes(String tienda, int mes, double dinero) {
        switch (mes) {
            case 0:
                cuentaMensual[mes] = tienda;
                break;
            case 1:
                cuentaMensual[mes] = String.valueOf(dinero);
                enero = enero + dinero;
                break;
            case 2:
                cuentaMensual[mes] = String.valueOf(dinero);
                febrero = febrero + dinero;
                break;
            case 3:
                cuentaMensual[mes] = String.valueOf(dinero);
                marzo = marzo + dinero;
                break;
            case 4:
                cuentaMensual[mes] = String.valueOf(dinero);
                abril = abril + dinero;
                break;
            case 5:
                cuentaMensual[mes] = String.valueOf(dinero);
                mayo = mayo + dinero;
                break;
            case 6:
                cuentaMensual[mes] = String.valueOf(dinero);
                junio = junio + dinero;
                break;
            case 7:
                cuentaMensual[mes] = String.valueOf(dinero);
                julio = julio + dinero;
                break;
            case 8:
                cuentaMensual[mes] = String.valueOf(dinero);
                agosto = agosto + dinero;
                break;
            case 9:
                cuentaMensual[mes] = String.valueOf(dinero);
                septiembre = septiembre + dinero;
                break;
            case 10:
                cuentaMensual[mes] = String.valueOf(dinero);
                octubre = octubre + dinero;
                break;
            case 11:
                cuentaMensual[mes] = String.valueOf(dinero);
                noviembre = noviembre + dinero;
                break;
            case 12:
                cuentaMensual[mes] = String.valueOf(dinero);
                diciembre = diciembre + dinero;
                break;
        }
        year = year + dinero;
        return cuentaMensual;
    }

    private double crearConsultaSumaVentas(int mes, String local, String anio) {
        HQL = "SELECT ("
                + " SUM(d.ventaConNota)+"
                + " SUM(d.ventaSinNota)+"
                + " SUM(d.abonoCredito)) as totalVenta"
                + " FROM Diariocaja d"
                + " WHERE MONTH(d.fecha) = "
                + mes
                + " AND d.local = '"
                + local
                + "'"
                + " AND YEAR(d.fecha) ="
                + anio;
        AccesoBD acceso = new AccesoBD();
        if (acceso.select(HQL).get(0) != null) {
            return (double) acceso.select(HQL).get(0);
        } else {
            return 0;
        }

    }

    private double crearConsultaSumaGastosDeVenta(int mes, String local, String year) {
        HQL = "SELECT "
                + " SUM(d.gastos)"
                + " FROM Diariocaja d"
                + " WHERE MONTH(d.fecha) = "
                + mes
                + " AND d.local = '"
                + local
                + "'"
                + " AND YEAR(d.fecha) ="
                + year;
        AccesoBD acceso = new AccesoBD();
        if (acceso.select(HQL).get(0) != null) {
            return (double) acceso.select(HQL).get(0);
        } else {
            return 0;
        }

    }

    private void consultarVentasDeDiario() {
        String fechaInicioResumen = FechaHerramienta.formatoYMD(jdcFechaConceptoResumenInicio.getDate());
        String fechaFinResumen = FechaHerramienta.formatoYMD(jdcFechaConceptoResumenFin.getDate());

        if (cmbTiendas.getSelectedItem().toString().equals("Todas")) {
            tiendasHQL = "FROM Diariocaja d WHERE "
                    + "d.fecha BETWEEN '"
                    + fechaInicioResumen + "' AND '"
                    + fechaFinResumen + "'";
            lblResumenTitulo.setText("TODAS LAS TIENDAS");
        } else {
            tiendasHQL = "FROM Diariocaja d WHERE "
                    + " d.local = '"
                    + cmbTiendas.getSelectedItem().toString()
                    + "' AND"
                    + " d.fecha BETWEEN '"
                    + fechaInicioResumen + "' AND '"
                    + fechaFinResumen + "'";
            lblResumenTitulo.setText(cmbTiendas.getSelectedItem().toString().toUpperCase());
        }
    }

    private void consultarVentasDeDiarioPorMes(int mes, String local, String anio) {
        tiendasHQL = "FROM Diariocaja d WHERE "
                + " MONTH(d.fecha) = "
                + mes
                + " AND d.local = '"
                + local
                + "'"
                + " AND YEAR(d.fecha) ="
                + anio;
    }

    private int getNoTiendasAbiertas(Date fecha) {
        AccesoBD acceso = new AccesoBD();
        HQL = "SELECT COUNT(DISTINCT d.local) FROM Diariocaja d WHERE "
                + " d.fecha = '"
                + fecha.toString()
                + "'";
        if (acceso.rowCount(HQL) > 0) {
            return acceso.rowCount(HQL);
        } else {
            return 1;
        }
    }

    private String getNombreTiendasAbiertas(Date fecha) {
        AccesoBD acceso = new AccesoBD();
        String nombreTiendaHQL = "SELECT DISTINCT d.local FROM Diariocaja d WHERE "
                + " d.fecha = '"
                + fecha.toString()
                + "'";
        String nombresTiendas = "";
        List<Object> listaDeDiarioDeVentas = acceso.select(nombreTiendaHQL);
        for (Iterator<Object> iterator = listaDeDiarioDeVentas.iterator(); iterator.hasNext();) {
            Object next = iterator.next();
            nombresTiendas = nombresTiendas + next.toString() + "-";
        }
        return nombresTiendas;
    }

    private List<Object> getNombreTiendasAbiertas() {
        AccesoBD acceso = new AccesoBD();
        String nombreTiendaHQL = "SELECT DISTINCT d.local FROM Diariocaja d ";
        List<Object> listaDeDiarioDeVentas = acceso.select(nombreTiendaHQL);
        return listaDeDiarioDeVentas;
    }

    private double getLocales(Date fecha) {
        AccesoBD acceso = new AccesoBD();
        HQL = "Select SUM(g.importe) FROM Gastosgenerales g WHERE "
                + " g.concepto = 'LOCALES' AND"
                + " (g.fecha BETWEEN '"
                + fecha + "' AND '"
                + fecha + "')";
        return acceso.sumRows(HQL);
    }

    private double getGastosPersonales(Date fecha) {
        AccesoBD acceso = new AccesoBD();
        HQL = "Select SUM(g.importe) FROM Gastosgenerales g WHERE "
                + " g.concepto = 'GASTOS PERSONALES' AND"
                + " (g.fecha BETWEEN '"
                + fecha + "' AND '"
                + fecha + "')";
        return acceso.sumRows(HQL);
    }

    private double getOficinaBodega(Date fecha) {
        AccesoBD acceso = new AccesoBD();
        HQL = "Select SUM(g.importe) FROM Gastosgenerales g WHERE "
                + " g.concepto = 'OFICINA BODEGA' AND"
                + " (g.fecha BETWEEN '"
                + fecha + "' AND '"
                + fecha + "')";
        return acceso.sumRows(HQL);
    }

    private double getGasolina(Date fecha) {
        AccesoBD acceso = new AccesoBD();
        HQL = "Select SUM(g.importe) FROM Gastosgenerales g WHERE "
                + " g.concepto = 'GASOLINA' AND"
                + " (g.fecha BETWEEN '"
                + fecha + "' AND '"
                + fecha + "')";
        return acceso.sumRows(HQL);
    }

    private double getMantenimientoV(Date fecha) {
        AccesoBD acceso = new AccesoBD();
        HQL = "Select SUM(g.importe) FROM Gastosgenerales g WHERE "
                + " g.concepto = 'MANTENIMIENTO V' AND"
                + " (g.fecha BETWEEN '"
                + fecha + "' AND '"
                + fecha + "')";
        return acceso.sumRows(HQL);
    }

    private double getSueldoPrestamos(Date fecha) {
        AccesoBD acceso = new AccesoBD();
        HQL = "Select SUM(g.importe) FROM Gastosgenerales g WHERE "
                + " g.concepto = 'SUELDOS PRESTAMOS' AND"
                + " (g.fecha BETWEEN '"
                + fecha + "' AND '"
                + fecha + "')";
        return acceso.sumRows(HQL);
    }

    private double getCompras(Date fecha) {
        AccesoBD acceso = new AccesoBD();
        HQL = "Select SUM(g.importe) FROM Gastosgenerales g WHERE "
                + " g.concepto = 'PROVEEDORES' AND"
                + " (g.fecha BETWEEN '"
                + fecha + "' AND '"
                + fecha + "')";
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

    public final void addActions() {

        final ItemListener changeClick = new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (cmbTiendas.getItemCount() > 0) {
                    if (cmbTiendas.getSelectedItem().equals(e.getItem())) {
                        llenaResumen();
                    }
                }
                if (cmbVentas.getItemCount() > 0) {
                    if (cmbVentas.getSelectedItem().equals(e.getItem())) {
                        llenarVentas();
                    }
                }
                if (cmbGastosDeVenta.getItemCount() > 0) {
                    if (cmbGastosDeVenta.getSelectedItem().equals(e.getItem())) {
                        llenarGastosDeVenta();
                    }
                }
                if (cmbGastosGenerales.getItemCount() > 0) {
                    if (cmbGastosGenerales.getSelectedItem().equals(e.getItem())) {
                        llenarGastosGenerales();
                    }
                }
                if (cmbCompras.getItemCount() > 0) {
                    if (cmbCompras.getSelectedItem().equals(e.getItem())) {
                        llenarCompras();
                    }
                }
            }
        };
        if (jdcFechaConceptoResumenInicio.isVisible()) {
            jdcFechaConceptoResumenInicio.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
                public void propertyChange(PropertyChangeEvent e) {
                    llenaResumen();
                }
            });
            jdcFechaConceptoResumenFin.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
                public void propertyChange(PropertyChangeEvent e) {
                    llenaResumen();
                }
            });
        }
        this.cmbTiendas.addItemListener(changeClick);
        this.cmbGastosDeVenta.addItemListener(changeClick);
        this.cmbGastosGenerales.addItemListener(changeClick);
        this.cmbCompras.addItemListener(changeClick);
        this.cmbVentas.addItemListener(changeClick);
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
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnImprimirCompras;
    private javax.swing.JButton btnImprimirGastosDeVenta;
    private javax.swing.JButton btnImprimirGastosGenerales;
    private javax.swing.JButton btnImprimirResumenGeneral;
    private javax.swing.JButton btnImprimirVentas;
    private javax.swing.JComboBox cmbCompras;
    private javax.swing.JComboBox cmbGastosDeVenta;
    private javax.swing.JComboBox cmbGastosGenerales;
    private javax.swing.JComboBox cmbTiendas;
    private javax.swing.JComboBox cmbVentas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
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
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private com.toedter.calendar.JDateChooser jdcFechaConceptoResumenFin;
    private com.toedter.calendar.JDateChooser jdcFechaConceptoResumenInicio;
    private javax.swing.JPanel jpCompras;
    private javax.swing.JPanel jpGastosDeVenta;
    private javax.swing.JPanel jpGastosGenerales;
    private javax.swing.JPanel jpResumenGeneral;
    private javax.swing.JPanel jpVentasTotales;
    private javax.swing.JScrollPane jsCompras;
    private javax.swing.JScrollPane jsGastosDeVenta;
    private javax.swing.JScrollPane jsGastosGenerales;
    private javax.swing.JScrollPane jsResumenVentas;
    private javax.swing.JScrollPane jsVentasTotales;
    private javax.swing.JTable jtCompras;
    private javax.swing.JTable jtGastosDeVenta;
    private javax.swing.JTable jtGastosGenerales;
    private javax.swing.JTable jtResumenGeneral;
    private javax.swing.JTable jtVentasTotales;
    private javax.swing.JTabbedPane jtpVentasGastosTotales;
    private javax.swing.JLabel lblAbonoCredito;
    private javax.swing.JLabel lblAbrilCompras;
    private javax.swing.JLabel lblAbrilGasto;
    private javax.swing.JLabel lblAbrilGastoDeVenta;
    private javax.swing.JLabel lblAbrilVentas;
    private javax.swing.JLabel lblAgostoCompras;
    private javax.swing.JLabel lblAgostoGasto;
    private javax.swing.JLabel lblAgostoGastoDeVenta;
    private javax.swing.JLabel lblAgostoVentas;
    private javax.swing.JLabel lblCompras;
    private javax.swing.JLabel lblDiciembreCompras;
    private javax.swing.JLabel lblDiciembreGasto;
    private javax.swing.JLabel lblDiciembreGastoDeVenta;
    private javax.swing.JLabel lblDiciembreVentas;
    private javax.swing.JLabel lblEneroCompras;
    private javax.swing.JLabel lblEneroGasto;
    private javax.swing.JLabel lblEneroGastoDeVenta;
    private javax.swing.JLabel lblEneroVentas;
    private javax.swing.JLabel lblFebreroCompras;
    private javax.swing.JLabel lblFebreroGasto;
    private javax.swing.JLabel lblFebreroGastoDeVenta;
    private javax.swing.JLabel lblFebreroVentas;
    private javax.swing.JLabel lblGastosDeVenta;
    private javax.swing.JLabel lblGastosGenerales;
    private javax.swing.JLabel lblGastosResumen;
    private javax.swing.JLabel lblJulioCompras;
    private javax.swing.JLabel lblJulioGasto;
    private javax.swing.JLabel lblJulioGastoDeVenta;
    private javax.swing.JLabel lblJulioVentas;
    private javax.swing.JLabel lblJunioCompras;
    private javax.swing.JLabel lblJunioGasto;
    private javax.swing.JLabel lblJunioGastoDeVenta;
    private javax.swing.JLabel lblJunioVentas;
    private javax.swing.JLabel lblMarzoCompras;
    private javax.swing.JLabel lblMarzoGasto;
    private javax.swing.JLabel lblMarzoGastoDeVenta;
    private javax.swing.JLabel lblMarzoVentas;
    private javax.swing.JLabel lblMayoCompras;
    private javax.swing.JLabel lblMayoGasto;
    private javax.swing.JLabel lblMayoGastoDeVenta;
    private javax.swing.JLabel lblMayoVentas;
    private javax.swing.JLabel lblNoviembreCompras;
    private javax.swing.JLabel lblNoviembreGasto;
    private javax.swing.JLabel lblNoviembreGastoDeVenta;
    private javax.swing.JLabel lblNoviembreVentas;
    private javax.swing.JLabel lblOctubreCompras;
    private javax.swing.JLabel lblOctubreGasto;
    private javax.swing.JLabel lblOctubreGastoDeVenta;
    private javax.swing.JLabel lblOctubreVentas;
    private javax.swing.JLabel lblResultado;
    private javax.swing.JLabel lblResultadoTexto;
    private javax.swing.JLabel lblResultadosResumen;
    private javax.swing.JLabel lblResumenTitulo;
    private javax.swing.JLabel lblSeptiembreCompras;
    private javax.swing.JLabel lblSeptiembreGasto;
    private javax.swing.JLabel lblSeptiembreGastoDeVenta;
    private javax.swing.JLabel lblSeptiembreVentas;
    private javax.swing.JLabel lblTituloCompras;
    private javax.swing.JLabel lblTituloGastosDeVenta;
    private javax.swing.JLabel lblTituloGastosGenerales;
    private javax.swing.JLabel lblTituloVentas;
    private javax.swing.JLabel lblTotalDeGastos;
    private javax.swing.JLabel lblTotalDeVentas;
    private javax.swing.JLabel lblVentasConNota;
    private javax.swing.JLabel lblVentasResumen;
    private javax.swing.JLabel lblVentasResumen1;
    private javax.swing.JLabel lblVentasResumen10;
    private javax.swing.JLabel lblVentasResumen11;
    private javax.swing.JLabel lblVentasResumen12;
    private javax.swing.JLabel lblVentasResumen2;
    private javax.swing.JLabel lblVentasResumen3;
    private javax.swing.JLabel lblVentasResumen4;
    private javax.swing.JLabel lblVentasResumen5;
    private javax.swing.JLabel lblVentasResumen6;
    private javax.swing.JLabel lblVentasResumen7;
    private javax.swing.JLabel lblVentasResumen8;
    private javax.swing.JLabel lblVentasResumen9;
    private javax.swing.JLabel lblVentasSinNota;
    private javax.swing.JLabel lblYearCompras;
    private javax.swing.JLabel lblYearComprasAnio;
    private javax.swing.JLabel lblYearGasto;
    private javax.swing.JLabel lblYearGastoDeVenta;
    private javax.swing.JLabel lblYearGastoGeneral;
    private javax.swing.JLabel lblYearTituloGastosVenta;
    private javax.swing.JLabel lblYearTituloVentas;
    private javax.swing.JLabel lblYearVentas;
    // End of variables declaration//GEN-END:variables
}
