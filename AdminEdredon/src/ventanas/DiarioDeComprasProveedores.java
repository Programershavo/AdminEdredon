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
import java.beans.PropertyVetoException;
import java.util.Date;
import java.util.List;
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
public class DiarioDeComprasProveedores extends javax.swing.JInternalFrame {

    String factura = "";
    String fechacompra = "";
    String proveedor = "";
    String modelo = "";
    String piezas = "";
    String precioUnitario = "";
    String observacion = "";
    double importe = 0.0;
    private String HQL = "";

    public DiarioDeComprasProveedores() {
        initComponents();
        llenarCombos();
        jdcFecha.setDate(new Date());
        jdcFechaInicioResumen.setDate(new Date());
        jdcFechaFinResumen.setDate(new Date());
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
        jtTablaProductos = new javax.swing.JTable();
        btnAnotar = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        btnBorrarCredito = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        btnRegistrarCompras = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        cmbProveedor = new javax.swing.JComboBox();
        btnRegistrarProveedor = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtPiezas = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtModelo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtCosto = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtObservacion = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtAbonoInicial = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        cmbMetodoDePago = new javax.swing.JComboBox();
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
        jLabel34 = new javax.swing.JLabel();
        cmbProveedoresResumen = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtTablaProveedoresResumen = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Compras a proveedores");

        jPanel2.setBackground(new java.awt.Color(240, 79, 90));

        jLabel7.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Compras a Proveedores");

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
        jLabel3.setText("Proveedor");

        jtTablaProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Factura", "Fecha Compra", "Proveedor", "Modelo", "Observacion", "Piezas", "Precio Unitario", "Abono Inicial", "Forma Pago", "Importe"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jtTablaProductos);

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
        jLabel22.setText("Quitar una compra");

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
        jLabel23.setText("Todas las compras listadas");

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

        btnRegistrarProveedor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnRegistrarProveedor.setForeground(new java.awt.Color(0, 153, 51));
        btnRegistrarProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/mas.png"))); // NOI18N
        btnRegistrarProveedor.setText("Nuevo");
        btnRegistrarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarProveedorActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 102, 153));
        jLabel6.setText("Piezas");

        jLabel4.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 102, 153));
        jLabel4.setText("Modelo");

        txtModelo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtModeloActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 102, 153));
        jLabel5.setText("Precio U.");

        jLabel8.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 102, 153));
        jLabel8.setText("Observ.");

        jLabel9.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 102, 153));
        jLabel9.setText("Abono");

        jLabel11.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 102, 153));
        jLabel11.setText("Pagó con");

        cmbMetodoDePago.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        cmbMetodoDePago.setForeground(new java.awt.Color(0, 102, 153));
        cmbMetodoDePago.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "EFECTIVO", "TRANSFERENCIA BANCARIA", "DEVOLUCION", "NOTA DE CREDITO", "CHEQUE", " ", " " }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBorrarCredito)
                        .addGap(40, 40, 40)
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRegistrarCompras, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1)
                    .addComponent(btnAnotar, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jdcFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRegistrarProveedor))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(94, 94, 94)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtObservacion, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtPiezas, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCosto, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtAbonoInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbMetodoDePago, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel1, jLabel2, jLabel4, jLabel5, jLabel6, jLabel8, jLabel9});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtAbonoInicial, txtCosto, txtFactura, txtPiezas});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel2)
                    .addComponent(txtFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jdcFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(cmbProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRegistrarProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel4)
                    .addComponent(txtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txtObservacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(cmbMetodoDePago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel6)
                    .addComponent(txtPiezas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtCosto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(txtAbonoInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAnotar, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
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
        jLabel33.setText("Número de Compras");

        lblComprasRealizadas.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lblComprasRealizadas.setForeground(new java.awt.Color(102, 102, 102));
        lblComprasRealizadas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblComprasRealizadas.setText("0");
        lblComprasRealizadas.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel35.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(0, 102, 153));
        jLabel35.setText("Total de compras");

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

        jLabel34.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(0, 102, 153));
        jLabel34.setText("Lista de proveedores");

        jtTablaProveedoresResumen.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(jtTablaProveedoresResumen);

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
                            .addGroup(jpResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpResumenLayout.createSequentialGroup()
                                    .addComponent(jLabel31)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jdcFechaInicioResumen, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(10, 10, 10)
                                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jdcFechaFinResumen, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpResumenLayout.createSequentialGroup()
                                    .addGroup(jpResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel33)
                                        .addComponent(jLabel35))
                                    .addGap(18, 18, 18)
                                    .addGroup(jpResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(lblResumenTotalGastos, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                                        .addComponent(lblComprasRealizadas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGap(35, 35, 35)
                                    .addComponent(jLabel34)
                                    .addGap(18, 18, 18)
                                    .addComponent(cmbProveedoresResumen, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jpResumenLayout.createSequentialGroup()
                                .addComponent(btnSalirResumen, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel27)))
                        .addGap(0, 112, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jpResumenLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel33, jLabel34, jLabel35});

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
                    .addComponent(lblComprasRealizadas)
                    .addComponent(jLabel34)
                    .addComponent(cmbProveedoresResumen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(lblResumenTotalGastos))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jpResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27)
                    .addComponent(btnSalirResumen, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );

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
        cmbProveedor.setSelectedIndex(0);
        txtModelo.setText("");
        txtPiezas.setText("");
        txtCosto.setText("");
        txtObservacion.setText("");
        txtAbonoInicial.setText("");
    }
    private void btnAnotarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnotarActionPerformed
        if (!txtFactura.getText().isEmpty()) {
            DefaultTableModel model = (DefaultTableModel) jtTablaProductos.getModel();
            try {
                String abonoInicial = "";
                if (txtAbonoInicial.getText().trim().isEmpty()) {
                    abonoInicial = "0";
                } else {
                    abonoInicial = txtAbonoInicial.getText().trim();
                }
                factura = txtFactura.getText();
                proveedor = cmbProveedor.getSelectedItem().toString();
                modelo = txtModelo.getText();
                piezas = txtPiezas.getText();
                precioUnitario = txtCosto.getText();
                observacion = txtObservacion.getText();
                importe = Double.parseDouble(piezas) * Double.parseDouble(precioUnitario);
                model.addRow(new String[]{
                    factura,
                    FechaHerramienta.formatoYMD(jdcFecha.getDate()),
                    proveedor,
                    modelo,
                    observacion,
                    piezas,
                    precioUnitario,
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
            if (txtModelo.getText().equals("")) {
                JOptionPane.showMessageDialog(rootPane, "Falto de llenar el modelo");
            }
            if (txtPiezas.getText().equals("")) {
                JOptionPane.showMessageDialog(rootPane, "Falto de llenar el número de piezas");
            }
            if (txtCosto.getText().equals("")) {
                JOptionPane.showMessageDialog(rootPane, "Falto de llenar el precio unitario");
            }
        }
    }//GEN-LAST:event_btnAnotarActionPerformed

    private void btnBorrarCreditoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarCreditoActionPerformed
        if (jtTablaProductos.getSelectedRow() > -1) {
            DefaultTableModel model = (DefaultTableModel) jtTablaProductos.getModel();
            model.removeRow(jtTablaProductos.getSelectedRow());
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona una venta de la "
                    + "tabla para poder borrarla.", "Venta no seleccionada", 0);
        }
    }//GEN-LAST:event_btnBorrarCreditoActionPerformed
    
    private void llenarCombos() {
        //Controles para llenar los comoboBox
        AccesoBD accesoBD = new AccesoBD();
        List listaObjetos = accesoBD.select("From Proveedores");
        cmbProveedor.removeAllItems();
        if (listaObjetos.size() > 0) {
            cmbProveedor.setEnabled(true);
            try {
                //Crea la lista de categorias
                for (Object listaObjeto : listaObjetos) {
                    Proveedores proveedor = (Proveedores) listaObjeto;
                    cmbProveedor.addItem(proveedor.getNombre());
                }
                cmbProveedor.setSelectedIndex(0);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "El error es:" + "\n" + e, "Error", 0);
            }

        } else {
            cmbProveedor.setEnabled(false);
        }

    }

    private void btnRegistrarComprasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarComprasActionPerformed
        AccesoBD controlBD = new AccesoBD();
        Compra compra = new Compra();
        String fecha = "";
        txtCosto.setText("");
        for (int i = 0; i < jtTablaProductos.getRowCount(); i++) {
            compra.setNota(jtTablaProductos.getValueAt(i, 0).toString());
            fecha = jtTablaProductos.getValueAt(i, 1).toString();
            compra.setFechaCompra(FechaHerramienta.convertirStringEnDate(fecha));
            compra.setNombreProveedor(jtTablaProductos.getValueAt(i, 2).toString());
            compra.setNombreProducto(jtTablaProductos.getValueAt(i, 3).toString());
            compra.setObservacion(jtTablaProductos.getValueAt(i, 4).toString());
            compra.setNoPiezas(Integer.parseInt(jtTablaProductos.getValueAt(i, 5).toString()));
            double precioU = Double.parseDouble(jtTablaProductos.getValueAt(i, 6).toString());
            compra.setPrecioUnitario(precioU);
            double abonoInicial = Double.parseDouble(jtTablaProductos.getValueAt(i, 7).toString());
            compra.setAbono(abonoInicial);
            compra.setMetodoPago(jtTablaProductos.getValueAt(
                    i, 8).toString());
            importe = Double.parseDouble(jtTablaProductos.getValueAt(i, 9).toString());
            compra.setImporte(importe);
            double saldoRestante = importe - abonoInicial;
            compra.setSaldoRestante(saldoRestante);
            compra.setNombreSucursal("Bodega");
            compra.setTipoGasto("Compras a proveedor");
            if (saldoRestante > 0) {
                compra.setEstatus("Vigente");
            } else {
                compra.setEstatus("Saldado");
            }
            controlBD.add(compra);
        }
        limpiarCompras();
        limpiarTablas(jtTablaProductos);
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

    private void btnRegistrarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarProveedorActionPerformed
        try {
            RegistroProveedor registroProveedor = new RegistroProveedor();
            if (exist(registroProveedor) == false) {
                CPanel.desktop.add(registroProveedor);
                registroProveedor.setVisible(true);
                registroProveedor.setLocation((CPanel.desktop.getWidth() - registroProveedor.getWidth()) / 2,
                        (CPanel.desktop.getHeight() - registroProveedor.getHeight()) / 2);
            } else {
                registroProveedor.dispose();
            }        // TODO add your handling code here:
        } catch (Exception ex) {
            Logger.getLogger(RegistrarProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnRegistrarProveedorActionPerformed

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

    private void txtModeloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtModeloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtModeloActionPerformed

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
        getProveedores();
        String fechaInicioResumen = FechaHerramienta.formatoYMD(jdcFechaInicioResumen.getDate());
        String fechaFinResumen = FechaHerramienta.formatoYMD(jdcFechaFinResumen.getDate());
        HQL = "From Compra c WHERE c.tipoGasto = 'Compras a proveedor' AND "
                + "c.nombreSucursal = 'Bodega' AND c.fechaCompra BETWEEN '"
                + fechaInicioResumen + "' AND '"
                + fechaFinResumen + "'";
        cargaTabla(jtTablaProveedoresResumen, HQL, "Compra");
    }//GEN-LAST:event_jpResumenComponentShown

    private double getTotalGastos() {
        AccesoBD acceso = new AccesoBD();
        String fechaInicioResumen = FechaHerramienta.formatoYMD(jdcFechaInicioResumen.getDate());
        String fechaFinResumen = FechaHerramienta.formatoYMD(jdcFechaFinResumen.getDate());
        HQL = "Select SUM(c.importe) From Compra c WHERE c.tipoGasto = 'Compras a proveedor' AND "
                + "c.nombreSucursal = 'Bodega' AND c.fechaCompra BETWEEN '"
                + fechaInicioResumen + "' AND '"
                + fechaFinResumen + "'";
        return acceso.sumRows(HQL);
    }

    private int getComprasRealizadas() {
        AccesoBD acceso = new AccesoBD();
        String fechaInicioResumen = FechaHerramienta.formatoYMD(jdcFechaInicioResumen.getDate());
        String fechaFinResumen = FechaHerramienta.formatoYMD(jdcFechaFinResumen.getDate());

        HQL = "Select COUNT(c.importe) From Compra c WHERE c.tipoGasto = 'Compras a proveedor' AND "
                + "c.nombreSucursal = 'Bodega' AND c.fechaCompra BETWEEN '"
                + fechaInicioResumen + "' AND '"
                + fechaFinResumen + "'";
        return acceso.rowCount(HQL);

    }

    private void getProveedores() {
        AccesoBD acceso = new AccesoBD();
        HQL = "Select COUNT(p.nombre) From Proveedores p";
        if (acceso.rowCount(HQL) > 0) {
            llenarCombosResumen();
        }
    }

    private void llenarCombosResumen() {
        //Controles para llenar los comoboBox
        AccesoBD accesoBD = new AccesoBD();
        crearConsultaGlobal();
        List listaProveedores = accesoBD.select(HQL);
        cmbProveedoresResumen.removeAllItems();
        if (listaProveedores.size() > 0) {
            try {
                for (Object listaObjeto : listaProveedores) {
                    cmbProveedoresResumen.addItem((String) listaObjeto);
                }
                cmbProveedoresResumen.setSelectedIndex(0);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "El error es:" + "\n" + e, "Error", 0);
            }
        } else {
            cmbProveedoresResumen.setEnabled(false);
        }
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

        final ItemListener changeClick = new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (cmbProveedoresResumen.getItemCount() > 0) {
                    if (cmbProveedoresResumen.getSelectedItem().equals(e.getItem())) {
                        crearConsultaGlobal();
                        cargaTabla(jtTablaProveedoresResumen, HQL, "Compra");
                    }
                }
            }
        };
        if (jdcFechaInicioResumen.isVisible()) {
            jdcFechaInicioResumen.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
                public void propertyChange(PropertyChangeEvent e) {
                    crearConsultaGlobal();
                    cargaTabla(jtTablaProveedoresResumen, HQL, "Compra");
                }
            });
            jdcFechaFinResumen.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
                public void propertyChange(PropertyChangeEvent e) {
                    crearConsultaGlobal();
                    cargaTabla(jtTablaProveedoresResumen, HQL, "Compra");
                }
            });
        }
        this.cmbProveedoresResumen.addItemListener(changeClick);
    }

    private void crearConsultaGlobal() {
        String fechaInicioResumen = FechaHerramienta.formatoYMD(jdcFechaInicioResumen.getDate());
        String fechaFinResumen = FechaHerramienta.formatoYMD(jdcFechaFinResumen.getDate());
        HQL = "Select distinct c.nombreProveedor From Compra c WHERE c.tipoGasto = 'Compras a proveedor' AND "
                + "c.nombreSucursal = 'Bodega' AND c.fechaCompra BETWEEN '"
                + fechaInicioResumen + "' AND '"
                + fechaFinResumen + "'";
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnotar;
    private javax.swing.JButton btnBorrarCredito;
    private javax.swing.JButton btnRegistrarCompras;
    private javax.swing.JButton btnRegistrarProveedor;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnSalirResumen;
    private javax.swing.JComboBox cmbMetodoDePago;
    private javax.swing.JComboBox cmbProveedor;
    private javax.swing.JComboBox cmbProveedoresResumen;
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
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private com.toedter.calendar.JDateChooser jdcFecha;
    private com.toedter.calendar.JDateChooser jdcFechaFinResumen;
    private com.toedter.calendar.JDateChooser jdcFechaInicioResumen;
    private javax.swing.JPanel jpResumen;
    private javax.swing.JTable jtTablaProductos;
    private javax.swing.JTable jtTablaProveedoresResumen;
    private javax.swing.JTabbedPane jtpComprasAPRoveedores;
    private javax.swing.JLabel lblComprasRealizadas;
    private javax.swing.JLabel lblResumenTotalGastos;
    private javax.swing.JTextField txtAbonoInicial;
    private javax.swing.JTextField txtCosto;
    private javax.swing.JTextField txtFactura;
    private javax.swing.JTextField txtModelo;
    private javax.swing.JTextField txtObservacion;
    private javax.swing.JTextField txtPiezas;
    // End of variables declaration//GEN-END:variables
}
