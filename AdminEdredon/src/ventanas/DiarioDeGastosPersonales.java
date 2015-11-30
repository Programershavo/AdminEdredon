/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import controlBD.AccesoBD;
import herramienta.FechaHerramienta;
import herramienta.FormatoTabla;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import pojos.Compra;
import pojos.Proveedores;

/**
 *
 * @author Daniel
 */
public class DiarioDeGastosPersonales extends javax.swing.JInternalFrame {

    String factura = "";
    String fechacompra = "";
    String acreedor = "";
    String concepto = "";
    String piezas = "";
    String precioUnitario = "";
    String observacion = "";
    double importe = 0.0;
    private String HQL = "";

    public DiarioDeGastosPersonales() {
        initComponents();
        llenarCombos();
        jdcFecha.setDate(new Date());
        jdcFechaInicioResumen.setDate(new Date());
        jdcFechaFinResumen.setDate(new Date());
        addActions();
        jtTablaAcreedoresResumen.setDefaultRenderer(Object.class, new FormatoTabla());
        jtTablaConceptos.setDefaultRenderer(Object.class, new FormatoTabla());
        setEventoMouseClicked(jtTablaAcreedoresResumen);
    }

    private void setEventoMouseClicked(JTable tabla) {
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    crearConsultaGlobal();
                    cargaTabla(tabla, HQL, "Compra");
                    lblResumenTotalGastos.setText(String.valueOf(getTotalGastos()));
                    lblComprasRealizadas.setText(String.valueOf(getComprasRealizadas()));
                }

            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jtpComprasAPRoveedores = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jdcFecha = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        txtFactura = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtTablaConceptos = new javax.swing.JTable();
        btnAnotar = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        btnBorrarCredito = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        btnRegistrarCompras = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtImporte = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtAbonoInicial = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        cmbMetodoDePago = new javax.swing.JComboBox();
        txtAcreedor = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtObservacion = new javax.swing.JTextArea();
        cmbConcepto = new javax.swing.JComboBox();
        jpResumen = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jdcFechaFinResumen = new com.toedter.calendar.JDateChooser();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        lblComprasRealizadas = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        lblResumenTotalGastos = new javax.swing.JLabel();
        jdcFechaInicioResumen = new com.toedter.calendar.JDateChooser();
        btnSalirResumen = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtTablaAcreedoresResumen = new javax.swing.JTable();
        btnPDFGastos = new javax.swing.JButton();
        btnCorregirConcepto = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Diario de gastos personales");

        jPanel2.setBackground(new java.awt.Color(240, 79, 90));

        jLabel7.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Diario de gastos personales");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jtpComprasAPRoveedores.setBackground(new java.awt.Color(255, 255, 255));
        jtpComprasAPRoveedores.setForeground(new java.awt.Color(255, 102, 0));
        jtpComprasAPRoveedores.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jtpComprasAPRoveedores.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 153));
        jLabel1.setText("Fecha");

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 102, 153));
        jLabel2.setText("Factura");

        txtFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFacturaActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 102, 153));
        jLabel3.setText("Acreedor");

        jtTablaConceptos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Factura", "Fecha Compra", "Acreedor", "Concepto", "Observacion", "Abono Inicial", "Forma Pago", "Importe"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jtTablaConceptos);

        btnAnotar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAnotar.setForeground(new java.awt.Color(0, 153, 51));
        btnAnotar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/list.png"))); // NOI18N
        btnAnotar.setText("Anotar");
        btnAnotar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnotarActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(0, 102, 153));
        jLabel22.setText("Quitar un gasto");

        btnBorrarCredito.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnBorrarCredito.setForeground(new java.awt.Color(0, 153, 51));
        btnBorrarCredito.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/menos.png"))); // NOI18N
        btnBorrarCredito.setText("Quitar");
        btnBorrarCredito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarCreditoActionPerformed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 102, 153));
        jLabel23.setText("Todas los gastos listados");

        btnRegistrarCompras.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnRegistrarCompras.setForeground(new java.awt.Color(0, 153, 51));
        btnRegistrarCompras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/mas.png"))); // NOI18N
        btnRegistrarCompras.setText("Registrar");
        btnRegistrarCompras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarComprasActionPerformed(evt);
            }
        });

        btnSalir.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSalir.setForeground(new java.awt.Color(0, 153, 51));
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/exit2.png"))); // NOI18N
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(0, 102, 153));
        jLabel24.setText("Salir");

        jLabel4.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 102, 153));
        jLabel4.setText("Concepto");

        jLabel5.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 102, 153));
        jLabel5.setText("Importe");

        txtImporte.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtImporteKeyTyped(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 102, 153));
        jLabel8.setText("Observaciones");

        jLabel9.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 102, 153));
        jLabel9.setText("Abono");

        txtAbonoInicial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAbonoInicialKeyTyped(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 102, 153));
        jLabel11.setText("Pagó con");

        cmbMetodoDePago.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        cmbMetodoDePago.setForeground(new java.awt.Color(0, 102, 153));
        cmbMetodoDePago.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "EFECTIVO", "TRANSFERENCIA BANCARIA", "DEVOLUCION", "NOTA DE CREDITO", "CHEQUE", " ", " " }));

        txtObservacion.setColumns(20);
        txtObservacion.setRows(5);
        jScrollPane3.setViewportView(txtObservacion);

        cmbConcepto.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        cmbConcepto.setForeground(new java.awt.Color(0, 102, 153));
        cmbConcepto.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "AGUA", "CERRO", "COLEGIO", "ELECTRICIDAD", "ESCOLARES", "FARMACIA", "LIC. VARGAS", "MEDICO", "NEXTEL", "SEGURO CARITO", "SEGURO MEDICO", "SKY", "TELÉFONO", "VARIOS", "VARIOS ARTURÍN", "VARIOS CARITO", "VARIOS FAMILIA", "VARIOS IYALI", " " }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 243, Short.MAX_VALUE)
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBorrarCredito)
                        .addGap(40, 40, 40)
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRegistrarCompras, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cmbMetodoDePago, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jdcFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbConcepto, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtAcreedor, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtImporte, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtAbonoInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAnotar)))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel1, jLabel2, jLabel4, jLabel5});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtAbonoInicial, txtFactura, txtImporte});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel3)
                            .addComponent(txtAcreedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(cmbConcepto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel2)
                            .addComponent(txtFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jdcFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel11)
                            .addComponent(cmbMetodoDePago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel5)
                            .addComponent(txtImporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(txtAbonoInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(btnAnotar, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel22)
                    .addComponent(btnBorrarCredito)
                    .addComponent(btnRegistrarCompras)
                    .addComponent(jLabel23)
                    .addComponent(btnSalir)
                    .addComponent(jLabel24))
                .addContainerGap())
        );

        jtpComprasAPRoveedores.addTab("Pagos", jPanel1);

        jpResumen.setBackground(new java.awt.Color(255, 255, 255));
        jpResumen.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jpResumenComponentShown(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(0, 102, 153));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel26.setText("Fin");

        jLabel31.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(0, 51, 102));
        jLabel31.setText("Establecer el periodo de tiempo trabajado");

        jLabel32.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(0, 51, 102));
        jLabel32.setText("Resultados");

        jLabel33.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(0, 102, 153));
        jLabel33.setText("Número de Gastos");

        lblComprasRealizadas.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lblComprasRealizadas.setForeground(new java.awt.Color(102, 102, 102));
        lblComprasRealizadas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblComprasRealizadas.setText("0");
        lblComprasRealizadas.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel35.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(0, 102, 153));
        jLabel35.setText("Total de Gastos");

        jLabel21.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 102, 153));
        jLabel21.setText("Inicio");

        lblResumenTotalGastos.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lblResumenTotalGastos.setForeground(new java.awt.Color(102, 102, 102));
        lblResumenTotalGastos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblResumenTotalGastos.setText("0");
        lblResumenTotalGastos.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnSalirResumen.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSalirResumen.setForeground(new java.awt.Color(0, 153, 51));
        btnSalirResumen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/exit2.png"))); // NOI18N
        btnSalirResumen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirResumenActionPerformed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(0, 102, 153));
        jLabel27.setText("Salir");

        jtTablaAcreedoresResumen.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(jtTablaAcreedoresResumen);

        btnPDFGastos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/pdf.png"))); // NOI18N
        btnPDFGastos.setText("Reporte");
        btnPDFGastos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPDFGastosActionPerformed(evt);
            }
        });

        btnCorregirConcepto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnCorregirConcepto.setForeground(new java.awt.Color(0, 153, 51));
        btnCorregirConcepto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/correct.png"))); // NOI18N
        btnCorregirConcepto.setText("Corregir");
        btnCorregirConcepto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCorregirConceptoActionPerformed(evt);
            }
        });

        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/delete.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpResumenLayout = new javax.swing.GroupLayout(jpResumen);
        jpResumen.setLayout(jpResumenLayout);
        jpResumenLayout.setHorizontalGroup(
            jpResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpResumenLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jpResumenLayout.createSequentialGroup()
                        .addGroup(jpResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel32)
                            .addGroup(jpResumenLayout.createSequentialGroup()
                                .addComponent(jLabel31)
                                .addGap(105, 105, 105)
                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jdcFechaInicioResumen, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jdcFechaFinResumen, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jpResumenLayout.createSequentialGroup()
                                .addGroup(jpResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel33)
                                    .addComponent(jLabel35))
                                .addGap(18, 18, 18)
                                .addGroup(jpResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblResumenTotalGastos, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                                    .addComponent(lblComprasRealizadas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jpResumenLayout.createSequentialGroup()
                                .addComponent(btnSalirResumen, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel27)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnPDFGastos)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnCorregirConcepto)
                                .addGap(18, 18, 18)
                                .addComponent(btnEliminar)))
                        .addGap(0, 119, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jpResumenLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel33, jLabel35});

        jpResumenLayout.setVerticalGroup(
            jpResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpResumenLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel21)
                    .addComponent(jdcFechaInicioResumen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26)
                    .addComponent(jdcFechaFinResumen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31))
                .addGap(21, 21, 21)
                .addComponent(jLabel32)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(lblComprasRealizadas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(lblResumenTotalGastos))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jpResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnCorregirConcepto, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPDFGastos, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27)
                    .addComponent(btnSalirResumen, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
        );

        jpResumenLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnCorregirConcepto, btnPDFGastos, btnSalirResumen, jLabel27});

        jtpComprasAPRoveedores.addTab("Resumen", jpResumen);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jtpComprasAPRoveedores)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jtpComprasAPRoveedores, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFacturaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFacturaActionPerformed
    private void limpiarCompras() {
        txtFactura.setText("");
        jdcFecha.setDate(new Date());
        cmbConcepto.setSelectedIndex(0);
        txtImporte.setText("");
        txtAbonoInicial.setText("");
        txtAcreedor.setText("");
    }
    private void btnAnotarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnotarActionPerformed
        if (!txtFactura.getText().isEmpty()) {
            DefaultTableModel model = (DefaultTableModel) jtTablaConceptos.getModel();
            try {
                String abonoInicial = "";
                if (txtAbonoInicial.getText().trim().isEmpty()) {
                    abonoInicial = "0";
                } else {
                    abonoInicial = txtAbonoInicial.getText().trim();
                }
                factura = txtFactura.getText();
                acreedor = txtAcreedor.getText();
                concepto = cmbConcepto.getSelectedItem().toString();
                observacion = txtObservacion.getText();
                importe = Double.parseDouble(txtImporte.getText());
                model.addRow(new String[]{
                    factura,
                    FechaHerramienta.formatoYMD(jdcFecha.getDate()),
                    acreedor,
                    concepto,
                    observacion,
                    abonoInicial,
                    cmbMetodoDePago.getSelectedItem().toString(),
                    String.valueOf(importe)});
                limpiarCompras();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e, "Error", 0);
            }
        } else {
            if (txtFactura.getText().equals("")) {
                JOptionPane.showMessageDialog(rootPane, "Falto de llenar la factura");
            }
            if (txtImporte.getText().equals("")) {
                JOptionPane.showMessageDialog(rootPane, "Falto de llenar el precio unitario");
            }
        }
    }//GEN-LAST:event_btnAnotarActionPerformed

    private void btnBorrarCreditoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarCreditoActionPerformed
        if (jtTablaConceptos.getSelectedRow() > -1) {
            DefaultTableModel model = (DefaultTableModel) jtTablaConceptos.getModel();
            model.removeRow(jtTablaConceptos.getSelectedRow());
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona una venta de la "
                    + "tabla para poder borrarla.", "Venta no seleccionada", 0);
        }
    }//GEN-LAST:event_btnBorrarCreditoActionPerformed

    private void llenarCombos() {
        //Controles para llenar los comoboBox
        AccesoBD accesoBD = new AccesoBD();
        List listaObjetos = accesoBD.select("From Proveedores");

        if (listaObjetos.size() > 0) {

            try {
                //Crea la lista de categorias
                for (Object listaObjeto : listaObjetos) {
                    Proveedores proveedor = (Proveedores) listaObjeto;

                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "El error es:" + "\n" + e, "Error", 0);
            }

        } else {

        }

    }

    private void btnRegistrarComprasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarComprasActionPerformed
        AccesoBD controlBD = new AccesoBD();
        Compra compra = new Compra();
        String fecha = "";
        txtImporte.setText("");
        for (int i = 0; i < jtTablaConceptos.getRowCount(); i++) {
            //NOTA
            compra.setNota(jtTablaConceptos.getValueAt(i, 0).toString());
            //FECHA
            fecha = jtTablaConceptos.getValueAt(i, 1).toString();
            compra.setFechaCompra(FechaHerramienta.convertirStringEnDate(fecha));
            //PROVEEDOR
            compra.setNombreProveedor(jtTablaConceptos.getValueAt(i, 2).toString());
            //CONCEPTO
            compra.setNombreProducto(jtTablaConceptos.getValueAt(i, 3).toString());
            //OBSERVACION
            compra.setObservacion(jtTablaConceptos.getValueAt(i, 4).toString());
            //PIEZAS
            compra.setNoPiezas(1);
            //ABONO INICIAL
            double abonoInicial = Double.parseDouble(jtTablaConceptos.getValueAt(i, 5).toString());
            compra.setAbono(abonoInicial);
            //FORMA DE PAGO
            compra.setMetodoPago(jtTablaConceptos.getValueAt(
                    i, 6).toString());
            //IMPORTE
            importe = Double.parseDouble(jtTablaConceptos.getValueAt(i, 7).toString());
            compra.setImporte(importe);
            //SALDO RESTANTE
            double saldoRestante = importe - abonoInicial;
            compra.setSaldoRestante(saldoRestante);
            //ESTATUS DE LA COMPRA
            if (saldoRestante > 0) {
                compra.setEstatus("Vigente");
            } else {
                compra.setEstatus("Saldado");
            }
            //LUGAR DEL GASTO Y SU CLASIFICACION
            compra.setNombreSucursal("Personal");
            compra.setTipoGasto("Gastos personales");

            controlBD.add(compra);
        }
        limpiarCompras();
        limpiarTablas(jtTablaConceptos);
        JOptionPane.showMessageDialog(this, "Ventas registradas correctamente", "Datos registrados", 1);
    }//GEN-LAST:event_btnRegistrarComprasActionPerformed
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
    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        try {
            this.setClosed(true);
            System.gc();
        } catch (PropertyVetoException ex) {
            this.dispose();
            System.gc();
        }          // TODO add your handling code here:
    }//GEN-LAST:event_btnSalirActionPerformed

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

    private void btnSalirResumenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirResumenActionPerformed
        try {
            this.setClosed(true);
            System.gc();
        } catch (PropertyVetoException ex) {
            this.dispose();
            System.gc();
        }
    }//GEN-LAST:event_btnSalirResumenActionPerformed

    private void jpResumenComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jpResumenComponentShown
        jdcFechaInicioResumen.setDate(new Date());
        jdcFechaFinResumen.setDate(new Date());
        lblResumenTotalGastos.setText(String.valueOf(getTotalGastos()));
        lblComprasRealizadas.setText(String.valueOf(getComprasRealizadas()));
        String fechaInicioResumen = FechaHerramienta.formatoYMD(jdcFechaInicioResumen.getDate());
        String fechaFinResumen = FechaHerramienta.formatoYMD(jdcFechaFinResumen.getDate());
        HQL = "From Compra c WHERE c.tipoGasto = 'Gastos personales' AND "
                + "c.nombreSucursal = 'Personal' AND c.fechaCompra BETWEEN '"
                + fechaInicioResumen + "' AND '"
                + fechaFinResumen + "'";
        cargaTabla(jtTablaAcreedoresResumen, HQL, "Compra");
    }//GEN-LAST:event_jpResumenComponentShown

    private void btnPDFGastosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPDFGastosActionPerformed
        Map parametros = new HashMap();
        if (jtTablaAcreedoresResumen.getRowCount() > 0) {
            crearConsultaGlobal();
            java.sql.Date fechaInicio = new java.sql.Date(jdcFechaInicioResumen.getDate().getTime());
            java.sql.Date fechaFin = new java.sql.Date(jdcFechaFinResumen.getDate().getTime());
            parametros.put("tipoGasto", "Gastos personales");
            parametros.put("fechaInicio", fechaInicio);
            parametros.put("fechaFin", fechaFin);

            try {
                reportMaker.ReportMaker reporte = new reportMaker.ReportMaker(HQL, "GastoTodos", parametros, false);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Ha ocurrido un error generando el reporte: " + e, "Error", 0);
            }

        } else {
            JOptionPane.showMessageDialog(rootPane, "No hay resultados que imprimir");
        }

    }//GEN-LAST:event_btnPDFGastosActionPerformed

    private void txtImporteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtImporteKeyTyped
        char key = evt.getKeyChar();
        if (((key < '0') || (key > '9')) && (key != KeyEvent.VK_BACK_SPACE)
                && (key != '.')) {
            evt.consume();
        }
        if (key == '.' && txtImporte.getText().contains(".")) {
            evt.consume();
            JOptionPane.showMessageDialog(this, "Solo se permite un punto "
                    + "este campo", "Validacion decimal", 0);
            // TODO add your handling code here:
        }        // TODO add your handling code here:
    }//GEN-LAST:event_txtImporteKeyTyped

    private void txtAbonoInicialKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAbonoInicialKeyTyped
        char key = evt.getKeyChar();
        if (((key < '0') || (key > '9')) && (key != KeyEvent.VK_BACK_SPACE)
                && (key != '.')) {
            evt.consume();
        }
        if (key == '.' && txtAbonoInicial.getText().contains(".")) {
            evt.consume();
            JOptionPane.showMessageDialog(this, "Solo se permite un punto "
                    + "este campo", "Validacion decimal", 0);
            // TODO add your handling code here:
        }             // TODO add your handling code here:
    }//GEN-LAST:event_txtAbonoInicialKeyTyped

    private void btnCorregirConceptoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCorregirConceptoActionPerformed
        if (jtTablaAcreedoresResumen.getSelectedRow() != -1) {
            String clave = jtTablaAcreedoresResumen.getValueAt(jtTablaAcreedoresResumen.getSelectedRow(), 0).toString();
            try {
                CorregirCompra corregirCompra = new CorregirCompra(clave);
                if (exist(corregirCompra) == false) {
                    CPanel.desktop.add(corregirCompra);
                    corregirCompra.setVisible(true);
                    corregirCompra.setLocation((CPanel.desktop.getWidth() - corregirCompra.getWidth()) / 2,
                            (CPanel.desktop.getHeight() - corregirCompra.getHeight()) / 2);
                } else {
                    corregirCompra.dispose();

                }        // TODO add your handling code here:
            } catch (Exception ex) {
                Logger.getLogger(RegistrarProducto.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        CorregirCompra corregirCompra = new CorregirCompra(title);
    }//GEN-LAST:event_btnCorregirConceptoActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        if (jtTablaAcreedoresResumen.getSelectedRow() != -1) {
            int respuesta = JOptionPane.showConfirmDialog(this, "¿Desea eliminar este gasto?", "Confirmar eliminación", 0, 3);
            if (respuesta == 0) {
                try {
                    AccesoBD acceso = new AccesoBD();
                    int clave = (int) jtTablaAcreedoresResumen.getValueAt(jtTablaAcreedoresResumen.getSelectedRow(), 0);
                    String query = "From Compra c WHERE c.idCompras = '" + clave + "'";
                    Compra compra = (Compra) acceso.select(query).get(0);
                    acceso.delete(compra);
                    crearConsultaGlobal();
                    cargaTabla(jtTablaAcreedoresResumen, HQL, "Compra");
                    lblResumenTotalGastos.setText(String.valueOf(getTotalGastos()));
                    lblComprasRealizadas.setText(String.valueOf(getComprasRealizadas()));
                    JOptionPane.showMessageDialog(this, "Venta eliminada correctamente", "Cancelado", 1);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Ha ocurrido un error: " + e, "Error", 0);
                    System.gc();
                }
            }
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private double getTotalGastos() {
        AccesoBD acceso = new AccesoBD();
        String fechaInicioResumen = FechaHerramienta.formatoYMD(jdcFechaInicioResumen.getDate());
        String fechaFinResumen = FechaHerramienta.formatoYMD(jdcFechaFinResumen.getDate());
        HQL = "Select SUM(c.importe) From Compra c WHERE c.tipoGasto = 'Gastos personales' AND "
                + "c.nombreSucursal = 'Personal' AND c.fechaCompra BETWEEN '"
                + fechaInicioResumen + "' AND '"
                + fechaFinResumen + "'";
        return acceso.sumRows(HQL);
    }

    private int getComprasRealizadas() {
        AccesoBD acceso = new AccesoBD();
        String fechaInicioResumen = FechaHerramienta.formatoYMD(jdcFechaInicioResumen.getDate());
        String fechaFinResumen = FechaHerramienta.formatoYMD(jdcFechaFinResumen.getDate());

        HQL = "Select COUNT(c.importe) From Compra c WHERE c.tipoGasto = 'Gastos personales' AND "
                + "c.nombreSucursal = 'Personal' AND c.fechaCompra BETWEEN '"
                + fechaInicioResumen + "' AND '"
                + fechaFinResumen + "'";
        return acceso.rowCount(HQL);

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

    public final void addActions() {

        if (jdcFechaInicioResumen.isVisible()) {
            jdcFechaInicioResumen.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
                public void propertyChange(PropertyChangeEvent e) {
                    crearConsultaGlobal();
                    cargaTabla(jtTablaAcreedoresResumen, HQL, "Compra");
                    lblResumenTotalGastos.setText(String.valueOf(getTotalGastos()));
                    lblComprasRealizadas.setText(String.valueOf(getComprasRealizadas()));
                }
            });
            jdcFechaFinResumen.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
                public void propertyChange(PropertyChangeEvent e) {
                    crearConsultaGlobal();
                    cargaTabla(jtTablaAcreedoresResumen, HQL, "Compra");
                    getComprasRealizadas();
                    getTotalGastos();
                }
            });
        }
    }

    private void crearConsultaGlobal() {
        String fechaInicioResumen = FechaHerramienta.formatoYMD(jdcFechaInicioResumen.getDate());
        String fechaFinResumen = FechaHerramienta.formatoYMD(jdcFechaFinResumen.getDate());
        HQL = "Select distinct c From Compra c WHERE c.tipoGasto = 'Gastos personales' AND "
                + "c.nombreSucursal = 'Personal' AND c.fechaCompra BETWEEN '"
                + fechaInicioResumen + "' AND '"
                + fechaFinResumen + "'";
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnotar;
    private javax.swing.JButton btnBorrarCredito;
    private javax.swing.JButton btnCorregirConcepto;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnPDFGastos;
    private javax.swing.JButton btnRegistrarCompras;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnSalirResumen;
    private javax.swing.JComboBox cmbConcepto;
    private javax.swing.JComboBox cmbMetodoDePago;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private com.toedter.calendar.JDateChooser jdcFecha;
    private com.toedter.calendar.JDateChooser jdcFechaFinResumen;
    private com.toedter.calendar.JDateChooser jdcFechaInicioResumen;
    private javax.swing.JPanel jpResumen;
    private javax.swing.JTable jtTablaAcreedoresResumen;
    private javax.swing.JTable jtTablaConceptos;
    private javax.swing.JTabbedPane jtpComprasAPRoveedores;
    private javax.swing.JLabel lblComprasRealizadas;
    private javax.swing.JLabel lblResumenTotalGastos;
    private javax.swing.JTextField txtAbonoInicial;
    private javax.swing.JTextField txtAcreedor;
    private javax.swing.JTextField txtFactura;
    private javax.swing.JTextField txtImporte;
    private javax.swing.JTextArea txtObservacion;
    // End of variables declaration//GEN-END:variables
}
