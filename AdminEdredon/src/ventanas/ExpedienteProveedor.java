/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import controlBD.AccesoBD;
import java.util.Date;
import javax.swing.JInternalFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import static ventanas.CPanel.desktop;

/**
 *
 * @author Daniel
 */
public class ExpedienteProveedor extends javax.swing.JInternalFrame {

    private String fechaInicio = "";
    private String fechaFin = "";
    private String HQL = "";
    private String folio = "";

    public ExpedienteProveedor() {
        initComponents();
        jdcFechaProveedores.setDate(new Date());
        jdcFechaExpedienteBuscar.setDate(new Date());
        jdcFechaAsignarFolio.setDate(new Date());
        crearConsultaGlobal();
    }

    private void crearConsultaGlobal() {
        if (folio.trim().equals("")) {
            HQL = "FROM Registrospagosproveedor p ";
        }
        if (!folio.equals("")) {
            HQL = "FROM Registrospagosproveedor p WHERE p.folio ='" + folio + "'";
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

    private void cargaTabla(final JTable jtTabla, String HQL, String Encabezado) {
        if (!HQL.isEmpty()) {
            AccesoBD acceso = new AccesoBD();
            jtTabla.setVisible(false);
            jtTabla.removeAll();
            jtTabla.setModel(acceso.retornaModelo(Encabezado, HQL));
            jtTabla.setVisible(true);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jtExpediente = new javax.swing.JTabbedPane();
        jpCrearExpediente = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cmbProveedor = new javax.swing.JComboBox();
        jLabel65 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jdcFechaProveedores = new com.toedter.calendar.JDateChooser();
        jLabel84 = new javax.swing.JLabel();
        txtFolio = new javax.swing.JTextField();
        jScrollPane16 = new javax.swing.JScrollPane();
        jtExpedientes = new javax.swing.JTable();
        btnAnotarProveedores = new javax.swing.JButton();
        txtFolio4 = new javax.swing.JTextField();
        jLabel70 = new javax.swing.JLabel();
        btnBorrarExpediente = new javax.swing.JButton();
        btnCorregirExpediente = new javax.swing.JButton();
        jpAsignarFoliosExpediente = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        txtCostoAsignar = new javax.swing.JTextField();
        jLabel67 = new javax.swing.JLabel();
        txtPiezasAsignar = new javax.swing.JTextField();
        jLabel69 = new javax.swing.JLabel();
        cmbFormaDePagoAsginar = new javax.swing.JComboBox();
        jLabel71 = new javax.swing.JLabel();
        cmbProveedorBuscar = new javax.swing.JComboBox();
        jLabel72 = new javax.swing.JLabel();
        jdcFechaExpedienteBuscar = new com.toedter.calendar.JDateChooser();
        jLabel85 = new javax.swing.JLabel();
        txtFolioExpedienteBuscar = new javax.swing.JTextField();
        btnBuscarExpediente = new javax.swing.JButton();
        btnAsignarFolio = new javax.swing.JButton();
        jLabel86 = new javax.swing.JLabel();
        txtFolioExpedienteAsignar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel73 = new javax.swing.JLabel();
        jdcFechaAsignarFolio = new com.toedter.calendar.JDateChooser();
        btnBorrarAsignarFolio = new javax.swing.JButton();
        btnCorregirAsignarFolio = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Expedientes de compras a proveedores");

        jtExpediente.setTabPlacement(javax.swing.JTabbedPane.LEFT);

        jpCrearExpediente.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 204));
        jLabel1.setText("CREACIÓN DE EXPEDIENTE DE PROVEEDOR");

        cmbProveedor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel65.setBackground(new java.awt.Color(255, 255, 255));
        jLabel65.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(0, 102, 153));
        jLabel65.setText("PROVEEDOR");
        jLabel65.setOpaque(true);

        jLabel64.setBackground(new java.awt.Color(255, 255, 255));
        jLabel64.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel64.setForeground(new java.awt.Color(0, 102, 153));
        jLabel64.setText("FECHA");
        jLabel64.setOpaque(true);

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

        jtExpedientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "FECHA", "FOLIO", "IMPORTE"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane16.setViewportView(jtExpedientes);

        btnAnotarProveedores.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAnotarProveedores.setForeground(new java.awt.Color(0, 153, 51));
        btnAnotarProveedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/list.png"))); // NOI18N
        btnAnotarProveedores.setText("CREAR");
        btnAnotarProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnotarProveedoresActionPerformed(evt);
            }
        });

        txtFolio4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtFolio4.setForeground(new java.awt.Color(153, 0, 0));
        txtFolio4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFolio4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFolio4ActionPerformed(evt);
            }
        });

        jLabel70.setBackground(new java.awt.Color(255, 255, 255));
        jLabel70.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(0, 102, 153));
        jLabel70.setText("IMPORTE");
        jLabel70.setOpaque(true);

        btnBorrarExpediente.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnBorrarExpediente.setForeground(new java.awt.Color(0, 153, 51));
        btnBorrarExpediente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/delete.png"))); // NOI18N
        btnBorrarExpediente.setText("BORRAR");
        btnBorrarExpediente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarExpedienteActionPerformed(evt);
            }
        });

        btnCorregirExpediente.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnCorregirExpediente.setForeground(new java.awt.Color(0, 153, 51));
        btnCorregirExpediente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/correct.png"))); // NOI18N
        btnCorregirExpediente.setText("CORREGIR");
        btnCorregirExpediente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCorregirExpedienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpCrearExpedienteLayout = new javax.swing.GroupLayout(jpCrearExpediente);
        jpCrearExpediente.setLayout(jpCrearExpedienteLayout);
        jpCrearExpedienteLayout.setHorizontalGroup(
            jpCrearExpedienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpCrearExpedienteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpCrearExpedienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane16)
                    .addGroup(jpCrearExpedienteLayout.createSequentialGroup()
                        .addGroup(jpCrearExpedienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel65, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpCrearExpedienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel64, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jdcFechaProveedores, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpCrearExpedienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel84, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtFolio, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpCrearExpedienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtFolio4, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                            .addComponent(jLabel70, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAnotarProveedores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(206, 206, 206))
                    .addGroup(jpCrearExpedienteLayout.createSequentialGroup()
                        .addComponent(btnBorrarExpediente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCorregirExpediente)))
                .addContainerGap())
        );
        jpCrearExpedienteLayout.setVerticalGroup(
            jpCrearExpedienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpCrearExpedienteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpCrearExpedienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpCrearExpedienteLayout.createSequentialGroup()
                        .addComponent(jLabel64)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jdcFechaProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpCrearExpedienteLayout.createSequentialGroup()
                        .addGroup(jpCrearExpedienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel84, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel70))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFolio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpCrearExpedienteLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jpCrearExpedienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jpCrearExpedienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtFolio4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnAnotarProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpCrearExpedienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBorrarExpediente, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCorregirExpediente, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jtExpediente.addTab("Crear Expediente", jpCrearExpediente);

        jpAsignarFoliosExpediente.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 102, 204));
        jLabel2.setText("ASIGNAR FOLIOS POR EXPEDIENTE");

        jLabel66.setBackground(new java.awt.Color(255, 255, 255));
        jLabel66.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(0, 102, 153));
        jLabel66.setText("COSTO");
        jLabel66.setOpaque(true);

        txtCostoAsignar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtCostoAsignar.setForeground(new java.awt.Color(153, 0, 0));
        txtCostoAsignar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCostoAsignar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCostoAsignarActionPerformed(evt);
            }
        });

        jLabel67.setBackground(new java.awt.Color(255, 255, 255));
        jLabel67.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(0, 102, 153));
        jLabel67.setText("PIEZAS");
        jLabel67.setOpaque(true);

        txtPiezasAsignar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtPiezasAsignar.setForeground(new java.awt.Color(153, 0, 0));
        txtPiezasAsignar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPiezasAsignar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPiezasAsignarActionPerformed(evt);
            }
        });

        jLabel69.setBackground(new java.awt.Color(255, 255, 255));
        jLabel69.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel69.setForeground(new java.awt.Color(0, 102, 153));
        jLabel69.setText("FORMA DE PAGO");
        jLabel69.setOpaque(true);

        cmbFormaDePagoAsginar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "EFECTIVO", "CHEQUE", "TRANSFERENCIA", "DEPOSITO", "CREDITO", "DEVOLUCION", "TRUEQUE", "CAMBIO DE MERCANCIA" }));

        jLabel71.setBackground(new java.awt.Color(255, 255, 255));
        jLabel71.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel71.setForeground(new java.awt.Color(0, 102, 153));
        jLabel71.setText("PROVEEDOR");
        jLabel71.setOpaque(true);

        cmbProveedorBuscar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel72.setBackground(new java.awt.Color(255, 255, 255));
        jLabel72.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel72.setForeground(new java.awt.Color(0, 102, 153));
        jLabel72.setText("FECHA");
        jLabel72.setOpaque(true);

        jLabel85.setBackground(new java.awt.Color(255, 255, 255));
        jLabel85.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel85.setForeground(new java.awt.Color(204, 0, 0));
        jLabel85.setText("FOLIO");
        jLabel85.setOpaque(true);

        txtFolioExpedienteBuscar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtFolioExpedienteBuscar.setForeground(new java.awt.Color(153, 0, 0));
        txtFolioExpedienteBuscar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFolioExpedienteBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFolioExpedienteBuscarActionPerformed(evt);
            }
        });

        btnBuscarExpediente.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnBuscarExpediente.setForeground(new java.awt.Color(0, 153, 51));
        btnBuscarExpediente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/Search.png"))); // NOI18N
        btnBuscarExpediente.setText("BUSCAR");
        btnBuscarExpediente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarExpedienteActionPerformed(evt);
            }
        });

        btnAsignarFolio.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAsignarFolio.setForeground(new java.awt.Color(0, 153, 51));
        btnAsignarFolio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/apply.png"))); // NOI18N
        btnAsignarFolio.setText("ASIGNAR");
        btnAsignarFolio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsignarFolioActionPerformed(evt);
            }
        });

        jLabel86.setBackground(new java.awt.Color(255, 255, 255));
        jLabel86.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel86.setForeground(new java.awt.Color(204, 0, 0));
        jLabel86.setText("FOLIO");
        jLabel86.setOpaque(true);

        txtFolioExpedienteAsignar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtFolioExpedienteAsignar.setForeground(new java.awt.Color(153, 0, 0));
        txtFolioExpedienteAsignar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFolioExpedienteAsignar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFolioExpedienteAsignarActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "fecha", "folio", "costo", "piezas", "forma de pago", "estado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jLabel73.setBackground(new java.awt.Color(255, 255, 255));
        jLabel73.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel73.setForeground(new java.awt.Color(0, 102, 153));
        jLabel73.setText("FECHA");
        jLabel73.setOpaque(true);

        btnBorrarAsignarFolio.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnBorrarAsignarFolio.setForeground(new java.awt.Color(0, 153, 51));
        btnBorrarAsignarFolio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/delete.png"))); // NOI18N
        btnBorrarAsignarFolio.setText("BORRAR");
        btnBorrarAsignarFolio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarAsignarFolioActionPerformed(evt);
            }
        });

        btnCorregirAsignarFolio.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnCorregirAsignarFolio.setForeground(new java.awt.Color(0, 153, 51));
        btnCorregirAsignarFolio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/correct.png"))); // NOI18N
        btnCorregirAsignarFolio.setText("CORREGIR");
        btnCorregirAsignarFolio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCorregirAsignarFolioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpAsignarFoliosExpedienteLayout = new javax.swing.GroupLayout(jpAsignarFoliosExpediente);
        jpAsignarFoliosExpediente.setLayout(jpAsignarFoliosExpedienteLayout);
        jpAsignarFoliosExpedienteLayout.setHorizontalGroup(
            jpAsignarFoliosExpedienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpAsignarFoliosExpedienteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpAsignarFoliosExpedienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)
                    .addGroup(jpAsignarFoliosExpedienteLayout.createSequentialGroup()
                        .addGroup(jpAsignarFoliosExpedienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpAsignarFoliosExpedienteLayout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 563, Short.MAX_VALUE)
                                .addGap(220, 220, 220))
                            .addGroup(jpAsignarFoliosExpedienteLayout.createSequentialGroup()
                                .addGroup(jpAsignarFoliosExpedienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel73, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jdcFechaAsignarFolio, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jpAsignarFoliosExpedienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel86, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtFolioExpedienteAsignar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jpAsignarFoliosExpedienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtCostoAsignar)
                                    .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jpAsignarFoliosExpedienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel67, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtPiezasAsignar, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jpAsignarFoliosExpedienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel69, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmbFormaDePagoAsginar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAsignarFolio)))
                        .addGap(99, 99, 99))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpAsignarFoliosExpedienteLayout.createSequentialGroup()
                        .addComponent(btnBorrarAsignarFolio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCorregirAsignarFolio))
                    .addGroup(jpAsignarFoliosExpedienteLayout.createSequentialGroup()
                        .addGroup(jpAsignarFoliosExpedienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel71, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbProveedorBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpAsignarFoliosExpedienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel72, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jdcFechaExpedienteBuscar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpAsignarFoliosExpedienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel85, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtFolioExpedienteBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscarExpediente)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jpAsignarFoliosExpedienteLayout.setVerticalGroup(
            jpAsignarFoliosExpedienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpAsignarFoliosExpedienteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpAsignarFoliosExpedienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpAsignarFoliosExpedienteLayout.createSequentialGroup()
                        .addComponent(jLabel72)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jdcFechaExpedienteBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpAsignarFoliosExpedienteLayout.createSequentialGroup()
                        .addComponent(jLabel85, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpAsignarFoliosExpedienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFolioExpedienteBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBuscarExpediente, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jpAsignarFoliosExpedienteLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(cmbProveedorBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpAsignarFoliosExpedienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpAsignarFoliosExpedienteLayout.createSequentialGroup()
                        .addComponent(jLabel66)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCostoAsignar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpAsignarFoliosExpedienteLayout.createSequentialGroup()
                        .addComponent(jLabel86, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFolioExpedienteAsignar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpAsignarFoliosExpedienteLayout.createSequentialGroup()
                        .addComponent(jLabel67)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPiezasAsignar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpAsignarFoliosExpedienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jpAsignarFoliosExpedienteLayout.createSequentialGroup()
                            .addComponent(jLabel69)
                            .addGap(26, 26, 26))
                        .addGroup(jpAsignarFoliosExpedienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbFormaDePagoAsginar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAsignarFolio, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jpAsignarFoliosExpedienteLayout.createSequentialGroup()
                        .addComponent(jLabel73)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jdcFechaAsignarFolio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpAsignarFoliosExpedienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBorrarAsignarFolio, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCorregirAsignarFolio, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13))
        );

        jtExpediente.addTab("Asignar Folios", jpAsignarFoliosExpediente);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jtExpediente)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jtExpediente)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtFolioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFolioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFolioActionPerformed

    private void txtCostoAsignarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCostoAsignarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCostoAsignarActionPerformed

    private void txtPiezasAsignarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPiezasAsignarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPiezasAsignarActionPerformed

    private void btnAnotarProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnotarProveedoresActionPerformed
        
    }//GEN-LAST:event_btnAnotarProveedoresActionPerformed

    private void txtFolio4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFolio4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFolio4ActionPerformed

    private void btnBorrarExpedienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarExpedienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBorrarExpedienteActionPerformed

    private void btnCorregirExpedienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCorregirExpedienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCorregirExpedienteActionPerformed

    private void txtFolioExpedienteBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFolioExpedienteBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFolioExpedienteBuscarActionPerformed

    private void btnBuscarExpedienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarExpedienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarExpedienteActionPerformed

    private void btnAsignarFolioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsignarFolioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAsignarFolioActionPerformed

    private void txtFolioExpedienteAsignarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFolioExpedienteAsignarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFolioExpedienteAsignarActionPerformed

    private void btnBorrarAsignarFolioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarAsignarFolioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBorrarAsignarFolioActionPerformed

    private void btnCorregirAsignarFolioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCorregirAsignarFolioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCorregirAsignarFolioActionPerformed
    public boolean exist(JInternalFrame frame) throws Exception {
        try {
            JInternalFrame iframes[] = desktop.getAllFrames();
            for (int i = 0; i < iframes.length; i++) {
                if (iframes[i].getTitle().equals(frame.getTitle())) {
                    iframes[i].moveToFront();
                    iframes[i].setSelected(true);
                    iframes[i].setLocation((desktop.getWidth() - iframes[i].getWidth()) / 2,
                            (desktop.getHeight() - iframes[i].getHeight()) / 2);
                    return true;
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return false;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnotarProveedores;
    private javax.swing.JButton btnAsignarFolio;
    private javax.swing.JButton btnBorrarAsignarFolio;
    private javax.swing.JButton btnBorrarExpediente;
    private javax.swing.JButton btnBuscarExpediente;
    private javax.swing.JButton btnCorregirAsignarFolio;
    private javax.swing.JButton btnCorregirExpediente;
    private javax.swing.JComboBox cmbFormaDePagoAsginar;
    private javax.swing.JComboBox cmbProveedor;
    private javax.swing.JComboBox cmbProveedorBuscar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private com.toedter.calendar.JDateChooser jdcFechaAsignarFolio;
    private com.toedter.calendar.JDateChooser jdcFechaExpedienteBuscar;
    private com.toedter.calendar.JDateChooser jdcFechaProveedores;
    private javax.swing.JPanel jpAsignarFoliosExpediente;
    private javax.swing.JPanel jpCrearExpediente;
    private javax.swing.JTabbedPane jtExpediente;
    private javax.swing.JTable jtExpedientes;
    private javax.swing.JTextField txtCostoAsignar;
    private javax.swing.JTextField txtFolio;
    private javax.swing.JTextField txtFolio4;
    private javax.swing.JTextField txtFolioExpedienteAsignar;
    private javax.swing.JTextField txtFolioExpedienteBuscar;
    private javax.swing.JTextField txtPiezasAsignar;
    // End of variables declaration//GEN-END:variables
}
