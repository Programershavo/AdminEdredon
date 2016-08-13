/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import controlBD.DAOUniversalHibernate;
import herramienta.FechaHerramienta;
import java.util.Date;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import pojos.Clientes;
import pojos.Linea;
import pojos.Proveedores;
import pojos.Sucursal;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.table.TableColumn;
import pojos.Contenidoexpediente;
import pojos.Expediente;
import static ventanas.CPanel.desktop;

/**
 *
 * @author Daniel
 */
public class ExpedienteProveedor extends javax.swing.JInternalFrame {

    private String fechaFin = "";
    private String HQL = "";
    private String folio = "";
    private int idExpediente = 0;
    boolean existenProveedores = false;

    public ExpedienteProveedor() {
        initComponents();
        jdcFechaInicio.setDate(new Date());
        jdcFechaFin.setDate(new Date());
        crearConsultaGlobal();
        String Query = "FROM Proveedores p ORDER BY p.nombre";
        llenarCombo(cmbProveedorBuscar, Query, "proveedor", true);
        //ASIGNA ACCIONES A LOS COMBOS Y CALENDARIOS
        DAOUniversalHibernate acceso = new DAOUniversalHibernate();
        if (acceso.select(Query) != null) {
            addActions();
        }
    }

    public final void addActions() {

        final ItemListener changeClick2 = new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (cmbProveedorBuscar.getItemCount() > 0) {
                    if (cmbProveedorBuscar.getSelectedItem().equals(e.getItem())) {
                        String Query = "FROM Contenidoexpediente ce WHERE ce.idExpediente= -1"
                                + " ORDER BY ce.folio";
                        cargaTabla(jtFoliosAsignados, Query, "ContenidoExpediente", 3);
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
                            Query = "FROM Contenidoexpediente ce"
                                    + " ORDER BY ce.folio";
                            cargaTabla(jtFoliosAsignados, Query, "ContenidoExpediente", 3);
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

        final ItemListener changeClick = new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (cmbProveedorResumen.getItemCount() > 0) {
                    if (cmbProveedorResumen.getSelectedItem().equals(e.getItem())) {
                        String Query = "FROM Contenidoexpediente ce WHERE ce.idExpediente= -1"
                                + " ORDER BY ce.folio";
                        cargaTabla(jtFoliosResumen, Query, "ContenidoExpediente", 3);
                        String fechaInicio = FechaHerramienta.formatoYMD(jdcInicioResumen.getDate());
                        String fechaFin = FechaHerramienta.formatoYMD(jdcFinResumen.getDate());
                        if (!cmbProveedorResumen.getSelectedItem().toString().equals("Todos")) {
                            Query = "FROM Expediente e WHERE e.nombreProveedor='"
                                    + cmbProveedorResumen.getSelectedItem().toString()
                                    + "' AND e.fechaApertura BETWEEN '"
                                    + fechaInicio
                                    + "' AND '"
                                    + fechaFin + "'"
                                    + " ORDER BY e.nombreProveedor";
                            cargaTabla(jtExpedienteResumen, Query, "Expediente", 2);
                            lblNoCompras.setText(String.valueOf(jtExpedienteResumen.getRowCount()));
                        } else {
                            Query = "FROM Contenidoexpediente ce"
                                    + " ORDER BY ce.folio";
                            cargaTabla(jtFoliosResumen, Query, "ContenidoExpediente", 1);
                            Query = "FROM Expediente e "
                                    + " WHERE e.fechaApertura BETWEEN '"
                                    + fechaInicio
                                    + "' AND '"
                                    + fechaFin + "'"
                                    + " ORDER BY e.nombreProveedor";
                            cargaTabla(jtExpedienteResumen, Query, "Expediente", 2);
                            lblNoCompras.setText(String.valueOf(jtExpedienteResumen.getRowCount()));
                        }
                    }//
                }

            }
        };

        this.cmbProveedorResumen.addItemListener(changeClick);

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
                        String Query = "FROM Contenidoexpediente ce"
                                + " ORDER BY ce.folio";
                        cargaTabla(jtFoliosAsignados, Query, "ContenidoExpediente", 1);
                        Query = "FROM Expediente e "
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
                        String Query = "FROM Contenidoexpediente ce"
                                + " ORDER BY ce.folio";
                        cargaTabla(jtFoliosAsignados, Query, "ContenidoExpediente", 1);
                        Query = "FROM Expediente e "
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

        if (jdcInicioResumen.isVisible()) {
            jdcInicioResumen.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {

                public void propertyChange(PropertyChangeEvent e) {
                    String fechaInicio = FechaHerramienta.formatoYMD(jdcInicioResumen.getDate());
                    String fechaFin = FechaHerramienta.formatoYMD(jdcFinResumen.getDate());
                    if (!cmbProveedorResumen.getSelectedItem().toString().equals("Todos")) {
                        String Query = "FROM Expediente e WHERE e.nombreProveedor='"
                                + cmbProveedorResumen.getSelectedItem().toString()
                                + "' AND e.fechaApertura BETWEEN '"
                                + fechaInicio
                                + "' AND '"
                                + fechaFin + "'"
                                + " ORDER BY e.nombreProveedor";
                        cargaTabla(jtExpedienteResumen, Query, "Expediente", 2);
                        lblNoCompras.setText(String.valueOf(jtExpedienteResumen.getRowCount()));
                    } else {
                        String Query = "FROM Contenidoexpediente ce"
                                + " ORDER BY ce.folio";
                        cargaTabla(jtFoliosResumen, Query, "ContenidoExpediente", 1);
                        Query = "FROM Expediente e "
                                + " WHERE e.fechaApertura BETWEEN '"
                                + fechaInicio
                                + "' AND '"
                                + fechaFin + "'"
                                + " ORDER BY e.nombreProveedor";
                        cargaTabla(jtExpedienteResumen, Query, "Expediente", 2);
                        lblNoCompras.setText(String.valueOf(jtExpedienteResumen.getRowCount()));
                    }
                }
            });
        }
        if (jdcFinResumen.isVisible()) {
            jdcFinResumen.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {

                public void propertyChange(PropertyChangeEvent e) {
                    String fechaInicio = FechaHerramienta.formatoYMD(jdcFechaInicio.getDate());
                    String fechaFin = FechaHerramienta.formatoYMD(jdcFinResumen.getDate());
                    if (!cmbProveedorResumen.getSelectedItem().toString().equals("Todos")) {
                        String Query = "FROM Expediente e WHERE e.nombreProveedor='"
                                + cmbProveedorResumen.getSelectedItem().toString()
                                + "' AND e.fechaApertura BETWEEN '"
                                + fechaInicio
                                + "' AND '"
                                + fechaFin + "'"
                                + " ORDER BY e.nombreProveedor";
                        cargaTabla(jtExpedienteResumen, Query, "Expediente", 2);
                        lblNoCompras.setText(String.valueOf(jtExpedienteResumen.getRowCount()));
                    } else {
                        String Query = "FROM Contenidoexpediente ce"
                                + " ORDER BY ce.folio";
                        cargaTabla(jtFoliosResumen, Query, "ContenidoExpediente", 1);
                        Query = "FROM Expediente e "
                                + " WHERE e.fechaApertura BETWEEN '"
                                + fechaInicio
                                + "' AND '"
                                + fechaFin + "' "
                                + " ORDER BY e.nombreProveedor";
                        cargaTabla(jtExpedienteResumen, Query, "Expediente", 2);
                        lblNoCompras.setText(String.valueOf(jtExpedienteResumen.getRowCount()));
                    }
                }
            });
        }
    }

    private void crearConsultaGlobal() {
        if (folio.trim().equals("")) {
            HQL = "FROM Registrospagosproveedor p ";
        }
        if (!folio.equals("")) {
            HQL = "FROM Registrospagosproveedor p WHERE p.folio ='" + folio + "'";
        }

    }

    private void cargaTabla(final JTable jtTabla, String HQL, String Encabezado, int NoColOcultar) {
        if (!HQL.isEmpty()) {
            DAOUniversalHibernate acceso = new DAOUniversalHibernate();
            jtTabla.setVisible(false);
            jtTabla.removeAll();
            jtTabla.setModel(acceso.retornaModelo(Encabezado, HQL));

            TableColumn column = null;
            for (int i = 0; i < NoColOcultar; i++) {
                column = jtTabla.getColumnModel().getColumn(i);
                column.setMaxWidth(1);
            }

            jtTabla.setVisible(true);
        }
    }

    private void cargaTabla2(final JTable jtTabla, String HQL, String Encabezado, int NoColOcultar) {
        if (!HQL.isEmpty()) {
            DAOUniversalHibernate acceso = new DAOUniversalHibernate();
            jtTabla.setVisible(false);
            jtTabla.removeAll();
            jtTabla.setModel(acceso.retornaModelo(Encabezado, HQL));

            TableColumn column = null;
            for (int i = 0; i < NoColOcultar; i++) {
                column = jtTabla.getColumnModel().getColumn(i);
                column.setMaxWidth(1);
            }
            if (NoColOcultar > 2) {
                column = jtTabla.getColumnModel().getColumn(5);
                column.setMaxWidth(1);
                column = jtTabla.getColumnModel().getColumn(6);
                column.setMaxWidth(1);
                column = jtTabla.getColumnModel().getColumn(8);
                column.setMaxWidth(1);
                column = jtTabla.getColumnModel().getColumn(10);
                column.setMaxWidth(1);
            }

            jtTabla.setVisible(true);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jtExpediente = new javax.swing.JTabbedPane();
        jpAsignarFoliosExpediente = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        txtImporteAsignar = new javax.swing.JTextField();
        jLabel67 = new javax.swing.JLabel();
        txtPiezasAsignar = new javax.swing.JTextField();
        jLabel71 = new javax.swing.JLabel();
        cmbProveedorBuscar = new javax.swing.JComboBox();
        jLabel72 = new javax.swing.JLabel();
        jdcFechaInicio = new com.toedter.calendar.JDateChooser();
        btnAsignarFolio = new javax.swing.JButton();
        jLabel86 = new javax.swing.JLabel();
        txtFolioExpedienteAsignar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtExpedienteAsignar = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtFoliosAsignados = new javax.swing.JTable();
        btnBorrarAsignarFolio = new javax.swing.JButton();
        btnCorregirAsignarFolio = new javax.swing.JButton();
        btnAnotarProveedores = new javax.swing.JButton();
        jLabel73 = new javax.swing.JLabel();
        jdcFechaFin = new com.toedter.calendar.JDateChooser();
        btnBorrarExpediente1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        cmbProveedorResumen = new javax.swing.JComboBox();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtFoliosResumen = new javax.swing.JTable();
        jLabel75 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jdcInicioResumen = new com.toedter.calendar.JDateChooser();
        jdcFinResumen = new com.toedter.calendar.JDateChooser();
        jScrollPane4 = new javax.swing.JScrollPane();
        jtExpedienteResumen = new javax.swing.JTable();
        jLabel77 = new javax.swing.JLabel();
        lblImporteResumen = new javax.swing.JLabel();
        lblPiezasResumen = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        lblEstadoResumen = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        lblNoCompras = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        lblNotasPagadas = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        lblNotasNoPagadas = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        lblAbonoTotal = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        lblSaldoResumen = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        lblNoNotas = new javax.swing.JLabel();
        jpProveedoresClientes = new javax.swing.JPanel();
        jsProvedorCliente = new javax.swing.JScrollPane();
        jtProveedor = new javax.swing.JTable();
        jLabel87 = new javax.swing.JLabel();
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
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Expedientes de compras a proveedores");

        jtExpediente.setTabPlacement(javax.swing.JTabbedPane.LEFT);

        jpAsignarFoliosExpediente.setBackground(new java.awt.Color(255, 255, 255));
        jpAsignarFoliosExpediente.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jpAsignarFoliosExpedienteComponentShown(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(0, 153, 204));
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("CREAR COMPRAS GENERALES");
        jLabel2.setOpaque(true);

        jLabel66.setBackground(new java.awt.Color(255, 255, 255));
        jLabel66.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(0, 102, 153));
        jLabel66.setText("IMPORTE");
        jLabel66.setOpaque(true);

        txtImporteAsignar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtImporteAsignar.setForeground(new java.awt.Color(153, 0, 0));
        txtImporteAsignar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtImporteAsignar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtImporteAsignarActionPerformed(evt);
            }
        });
        txtImporteAsignar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtImporteAsignarKeyTyped(evt);
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
        txtPiezasAsignar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPiezasAsignarKeyTyped(evt);
            }
        });

        jLabel71.setBackground(new java.awt.Color(255, 255, 255));
        jLabel71.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel71.setForeground(new java.awt.Color(0, 102, 153));
        jLabel71.setText("PROVEEDOR");
        jLabel71.setOpaque(true);

        cmbProveedorBuscar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel72.setBackground(new java.awt.Color(255, 255, 255));
        jLabel72.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel72.setForeground(new java.awt.Color(0, 102, 153));
        jLabel72.setText("INICIO");
        jLabel72.setOpaque(true);

        btnAsignarFolio.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAsignarFolio.setForeground(new java.awt.Color(0, 153, 51));
        btnAsignarFolio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/apply.png"))); // NOI18N
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

        jtExpedienteAsignar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Clave Expediente", "FECHA", "PROVEEDOR"
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
        jtExpedienteAsignar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtExpedienteAsignarMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtExpedienteAsignar);
        if (jtExpedienteAsignar.getColumnModel().getColumnCount() > 0) {
            jtExpedienteAsignar.getColumnModel().getColumn(0).setMinWidth(50);
            jtExpedienteAsignar.getColumnModel().getColumn(0).setPreferredWidth(50);
            jtExpedienteAsignar.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        jtFoliosAsignados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CLAVE FOLIO", "CLAVE EXP", "CLAVE PRO", "PROVEEDOR", "FECHA", "FOLIO", "PIEZAS", "IMPORTE", "FORMA DE PAGO", "ABONO", "ESTADO"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
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
        jScrollPane2.setViewportView(jtFoliosAsignados);

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

        btnAnotarProveedores.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAnotarProveedores.setForeground(new java.awt.Color(0, 153, 51));
        btnAnotarProveedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/mas.png"))); // NOI18N
        btnAnotarProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnotarProveedoresActionPerformed(evt);
            }
        });

        jLabel73.setBackground(new java.awt.Color(255, 255, 255));
        jLabel73.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel73.setForeground(new java.awt.Color(0, 102, 153));
        jLabel73.setText("FIN");
        jLabel73.setOpaque(true);

        btnBorrarExpediente1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnBorrarExpediente1.setForeground(new java.awt.Color(0, 153, 51));
        btnBorrarExpediente1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/delete.png"))); // NOI18N
        btnBorrarExpediente1.setText("BORRAR");
        btnBorrarExpediente1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarExpediente1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpAsignarFoliosExpedienteLayout = new javax.swing.GroupLayout(jpAsignarFoliosExpediente);
        jpAsignarFoliosExpediente.setLayout(jpAsignarFoliosExpedienteLayout);
        jpAsignarFoliosExpedienteLayout.setHorizontalGroup(
            jpAsignarFoliosExpedienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpAsignarFoliosExpedienteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpAsignarFoliosExpedienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpAsignarFoliosExpedienteLayout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jpAsignarFoliosExpedienteLayout.createSequentialGroup()
                        .addGroup(jpAsignarFoliosExpedienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(jpAsignarFoliosExpedienteLayout.createSequentialGroup()
                                .addComponent(jLabel71)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cmbProveedorBuscar, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpAsignarFoliosExpedienteLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jpAsignarFoliosExpedienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnBorrarExpediente1)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpAsignarFoliosExpedienteLayout.createSequentialGroup()
                                        .addGroup(jpAsignarFoliosExpedienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jdcFechaInicio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jpAsignarFoliosExpedienteLayout.createSequentialGroup()
                                                .addComponent(jLabel72)
                                                .addGap(91, 91, 91)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jpAsignarFoliosExpedienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jpAsignarFoliosExpedienteLayout.createSequentialGroup()
                                                .addComponent(jdcFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnAnotarProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpAsignarFoliosExpedienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpAsignarFoliosExpedienteLayout.createSequentialGroup()
                                .addComponent(btnBorrarAsignarFolio)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnCorregirAsignarFolio, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpAsignarFoliosExpedienteLayout.createSequentialGroup()
                                .addGroup(jpAsignarFoliosExpedienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpAsignarFoliosExpedienteLayout.createSequentialGroup()
                                        .addGroup(jpAsignarFoliosExpedienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtFolioExpedienteAsignar)
                                            .addComponent(jLabel86, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jpAsignarFoliosExpedienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtPiezasAsignar)
                                            .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jpAsignarFoliosExpedienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jpAsignarFoliosExpedienteLayout.createSequentialGroup()
                                                .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                            .addGroup(jpAsignarFoliosExpedienteLayout.createSequentialGroup()
                                                .addComponent(txtImporteAsignar, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnAsignarFolio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 523, Short.MAX_VALUE))
                                .addGap(14, 14, 14))))))
        );
        jpAsignarFoliosExpedienteLayout.setVerticalGroup(
            jpAsignarFoliosExpedienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpAsignarFoliosExpedienteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jpAsignarFoliosExpedienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpAsignarFoliosExpedienteLayout.createSequentialGroup()
                        .addGroup(jpAsignarFoliosExpedienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbProveedorBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpAsignarFoliosExpedienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jpAsignarFoliosExpedienteLayout.createSequentialGroup()
                                .addComponent(jLabel72)
                                .addGap(6, 6, 6)
                                .addComponent(jdcFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jpAsignarFoliosExpedienteLayout.createSequentialGroup()
                                .addComponent(jLabel73)
                                .addGap(6, 6, 6)
                                .addGroup(jpAsignarFoliosExpedienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jdcFechaFin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnAnotarProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))))
                    .addGroup(jpAsignarFoliosExpedienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jpAsignarFoliosExpedienteLayout.createSequentialGroup()
                            .addComponent(jLabel86, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(4, 4, 4)
                            .addComponent(txtFolioExpedienteAsignar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jpAsignarFoliosExpedienteLayout.createSequentialGroup()
                            .addGroup(jpAsignarFoliosExpedienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel67)
                                .addComponent(jLabel66))
                            .addGap(4, 4, 4)
                            .addGroup(jpAsignarFoliosExpedienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnAsignarFolio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addGroup(jpAsignarFoliosExpedienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtPiezasAsignar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtImporteAsignar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpAsignarFoliosExpedienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpAsignarFoliosExpedienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpAsignarFoliosExpedienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnBorrarAsignarFolio, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnBorrarExpediente1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnCorregirAsignarFolio, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jtExpediente.addTab("Expedientes", jpAsignarFoliosExpediente);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanel1ComponentShown(evt);
            }
        });

        jLabel3.setBackground(new java.awt.Color(0, 153, 204));
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("RESUMEN DE COMPRAS");
        jLabel3.setOpaque(true);

        jLabel74.setBackground(new java.awt.Color(255, 255, 255));
        jLabel74.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel74.setForeground(new java.awt.Color(0, 102, 153));
        jLabel74.setText("PROVEEDOR");
        jLabel74.setOpaque(true);

        cmbProveedorResumen.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jtFoliosResumen.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CLAVE FOLIO", "CLAVE EXP", "CLAVE PRO", "PROVEEDOR", "FECHA", "FOLIO", "PIEZAS", "IMPORTE", "FORMA DE PAGO", "ABONO", "ESTADO"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtFoliosResumen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtFoliosResumenMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jtFoliosResumen);

        jLabel75.setBackground(new java.awt.Color(255, 255, 255));
        jLabel75.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel75.setForeground(new java.awt.Color(0, 102, 153));
        jLabel75.setText("INICIO");
        jLabel75.setOpaque(true);

        jLabel76.setBackground(new java.awt.Color(255, 255, 255));
        jLabel76.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel76.setForeground(new java.awt.Color(0, 102, 153));
        jLabel76.setText("FIN");
        jLabel76.setOpaque(true);

        jtExpedienteResumen.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Clave Expediente", "FECHA", "PROVEEDOR"
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
        jtExpedienteResumen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtExpedienteResumenMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jtExpedienteResumen);
        if (jtExpedienteResumen.getColumnModel().getColumnCount() > 0) {
            jtExpedienteResumen.getColumnModel().getColumn(0).setMinWidth(50);
            jtExpedienteResumen.getColumnModel().getColumn(0).setPreferredWidth(50);
            jtExpedienteResumen.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        jLabel77.setBackground(new java.awt.Color(255, 255, 255));
        jLabel77.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel77.setForeground(new java.awt.Color(0, 102, 153));
        jLabel77.setText("IMPORTE TOTAL");
        jLabel77.setOpaque(true);

        lblImporteResumen.setBackground(new java.awt.Color(255, 255, 255));
        lblImporteResumen.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lblImporteResumen.setForeground(new java.awt.Color(204, 0, 0));
        lblImporteResumen.setText(".");
        lblImporteResumen.setOpaque(true);

        lblPiezasResumen.setBackground(new java.awt.Color(255, 255, 255));
        lblPiezasResumen.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lblPiezasResumen.setForeground(new java.awt.Color(204, 0, 0));
        lblPiezasResumen.setText(".");
        lblPiezasResumen.setOpaque(true);

        jLabel78.setBackground(new java.awt.Color(255, 255, 255));
        jLabel78.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel78.setForeground(new java.awt.Color(0, 102, 153));
        jLabel78.setText("PIEZAS TOTAL");
        jLabel78.setOpaque(true);

        jLabel80.setBackground(new java.awt.Color(255, 255, 255));
        jLabel80.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel80.setForeground(new java.awt.Color(0, 102, 153));
        jLabel80.setText("EDO GENERAL");
        jLabel80.setOpaque(true);

        lblEstadoResumen.setBackground(new java.awt.Color(255, 255, 255));
        lblEstadoResumen.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lblEstadoResumen.setForeground(new java.awt.Color(204, 0, 0));
        lblEstadoResumen.setText(".");
        lblEstadoResumen.setOpaque(true);

        jLabel81.setBackground(new java.awt.Color(255, 255, 255));
        jLabel81.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel81.setForeground(new java.awt.Color(0, 102, 153));
        jLabel81.setText("EXPEDIENTES");
        jLabel81.setOpaque(true);

        lblNoCompras.setBackground(new java.awt.Color(255, 255, 255));
        lblNoCompras.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lblNoCompras.setForeground(new java.awt.Color(204, 0, 0));
        lblNoCompras.setText(".");
        lblNoCompras.setOpaque(true);

        jLabel82.setBackground(new java.awt.Color(255, 255, 255));
        jLabel82.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel82.setForeground(new java.awt.Color(0, 102, 153));
        jLabel82.setText("PAGADOS");
        jLabel82.setOpaque(true);

        lblNotasPagadas.setBackground(new java.awt.Color(255, 255, 255));
        lblNotasPagadas.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lblNotasPagadas.setForeground(new java.awt.Color(204, 0, 0));
        lblNotasPagadas.setText(".");
        lblNotasPagadas.setOpaque(true);

        jLabel83.setBackground(new java.awt.Color(255, 255, 255));
        jLabel83.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel83.setForeground(new java.awt.Color(0, 102, 153));
        jLabel83.setText("NO PAGADOS");
        jLabel83.setOpaque(true);

        lblNotasNoPagadas.setBackground(new java.awt.Color(255, 255, 255));
        lblNotasNoPagadas.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lblNotasNoPagadas.setForeground(new java.awt.Color(204, 0, 0));
        lblNotasNoPagadas.setText(".");
        lblNotasNoPagadas.setOpaque(true);

        jLabel84.setBackground(new java.awt.Color(255, 255, 255));
        jLabel84.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel84.setForeground(new java.awt.Color(0, 102, 153));
        jLabel84.setText("ABONO TOTAL");
        jLabel84.setOpaque(true);

        lblAbonoTotal.setBackground(new java.awt.Color(255, 255, 255));
        lblAbonoTotal.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lblAbonoTotal.setForeground(new java.awt.Color(204, 0, 0));
        lblAbonoTotal.setText(".");
        lblAbonoTotal.setOpaque(true);

        jLabel85.setBackground(new java.awt.Color(255, 255, 255));
        jLabel85.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel85.setForeground(new java.awt.Color(0, 102, 153));
        jLabel85.setText("SALDO GRAL");
        jLabel85.setOpaque(true);

        lblSaldoResumen.setBackground(new java.awt.Color(255, 255, 255));
        lblSaldoResumen.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lblSaldoResumen.setForeground(new java.awt.Color(204, 0, 0));
        lblSaldoResumen.setText(".");
        lblSaldoResumen.setOpaque(true);

        jLabel79.setBackground(new java.awt.Color(255, 255, 255));
        jLabel79.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel79.setForeground(new java.awt.Color(0, 102, 153));
        jLabel79.setText("No NOTAS");
        jLabel79.setOpaque(true);

        lblNoNotas.setBackground(new java.awt.Color(255, 255, 255));
        lblNoNotas.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lblNoNotas.setForeground(new java.awt.Color(204, 0, 0));
        lblNoNotas.setText(".");
        lblNoNotas.setOpaque(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel74)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cmbProveedorResumen, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel81, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                        .addComponent(jLabel80)
                                        .addComponent(jLabel78)
                                        .addComponent(jLabel77)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                    .addComponent(lblNoCompras, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblEstadoResumen)
                                    .addComponent(lblPiezasResumen)
                                    .addComponent(lblImporteResumen)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel79)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblNoNotas)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel82)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblNotasPagadas))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel83)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblNotasNoPagadas))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel84)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblAbonoTotal))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel85)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblSaldoResumen)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel75)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jdcInicioResumen, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(187, 187, 187))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel76)
                        .addGap(18, 18, 18)
                        .addComponent(jdcFinResumen, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel77, jLabel78, jLabel79, jLabel80, jLabel82, jLabel83, jLabel84, jLabel85, lblAbonoTotal, lblEstadoResumen, lblImporteResumen, lblNoCompras, lblNoNotas, lblNotasNoPagadas, lblNotasPagadas, lblPiezasResumen, lblSaldoResumen});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmbProveedorResumen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel75)
                        .addComponent(jLabel76))
                    .addComponent(jdcInicioResumen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jdcFinResumen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel81)
                            .addComponent(lblNoCompras))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel80)
                            .addComponent(lblEstadoResumen)
                            .addComponent(jLabel82)
                            .addComponent(lblNotasPagadas))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel78)
                            .addComponent(lblPiezasResumen)
                            .addComponent(jLabel83)
                            .addComponent(lblNotasNoPagadas))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel84)
                                .addComponent(lblAbonoTotal))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel77)
                                .addComponent(lblImporteResumen)))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel79)
                                .addComponent(lblNoNotas))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel85)
                                .addComponent(lblSaldoResumen))))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel77, jLabel78, jLabel80, lblEstadoResumen, lblImporteResumen, lblPiezasResumen});

        jtExpediente.addTab("Resumen", jPanel1);

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

        jLabel87.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel87.setForeground(new java.awt.Color(0, 102, 153));
        jLabel87.setText("MIS PROVEEDORES");

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
        jLabel89.setText("ADMINISTRAS MIS PROVEEDORES");

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
        jLabel90.setText("ADMINISTRAS MIS CLIENTES");

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

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/Employee.png"))); // NOI18N

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
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel87, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44)))
                        .addComponent(jLabel90)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpProveedoresClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnBorrarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEditarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jpProveedoresClientesLayout.createSequentialGroup()
                        .addComponent(jLabel87)
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
                .addContainerGap(164, Short.MAX_VALUE))
        );

        jtExpediente.addTab("+PROVEEDORES", jpProveedoresClientes);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jtExpediente)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jtExpediente)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtImporteAsignarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtImporteAsignarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtImporteAsignarActionPerformed

    private void txtPiezasAsignarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPiezasAsignarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPiezasAsignarActionPerformed

    private void btnAnotarProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnotarProveedoresActionPerformed
        DAOUniversalHibernate control = new DAOUniversalHibernate();
        String Query = "";
        try {
            if (cmbProveedorBuscar.getItemCount() > 0) {
                if (!cmbProveedorBuscar.getSelectedItem().toString().equals("Todos")) {
                    String nombreProveedor = cmbProveedorBuscar.getSelectedItem().toString();
                    pojos.Expediente expediente = new pojos.Expediente();
                    //FECHA
                    expediente.setFechaApertura(jdcFechaInicio.getDate());
                    Query = "FROM Proveedores p WHERE p.nombre ='" + nombreProveedor + "'";
                    Proveedores proveedor = (Proveedores) control.select(Query).get(0);
                    //CLAVE PROVEEDOR
                    expediente.setIdProveedor(proveedor.getIdProveedor());
                    //NOMBRE PROVEEDOR
                    expediente.setNombreProveedor(proveedor.getNombre());
                    //ESTADO GENERAL
                    expediente.setEstadoGeneral("NO PAGADO");
                    control.add(expediente);

                    limpiarCampos();
                    JOptionPane.showMessageDialog(this, "Expediente creado correctamente", "Datos registrados", 1);

                    if (!cmbProveedorBuscar.getSelectedItem().toString().equals("Todos")) {
                        Query = "FROM Expediente e WHERE e.nombreProveedor='"
                                + cmbProveedorBuscar.getSelectedItem().toString()
                                + "'ORDER BY e.nombreProveedor";
                        cargaTabla(jtExpedienteAsignar, Query, "Expediente", 2);
                    } else {
                        Query = "FROM Contenidoexpediente ce"
                                + " ORDER BY ce.folio";
                        cargaTabla(jtFoliosAsignados, Query, "ContenidoExpediente", 1);
                        Query = "FROM Expediente e ORDER BY e.nombreProveedor";
                        cargaTabla(jtExpedienteAsignar, Query, "Expediente", 2);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "No existe el proveedor", "Datos incorrectos", 1);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Favor de llenar el campo Nombre", "Datos incompletos", 1);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e, "Error", 0);
        }
    }//GEN-LAST:event_btnAnotarProveedoresActionPerformed
    private void limpiarCampos() {
        txtFolioExpedienteAsignar.setText("");
        txtImporteAsignar.setText("");
        txtPiezasAsignar.setText("");
        txtImporteAsignar.setText("");
    }

    private void llenarCombo(JComboBox combo, String query, String tipoLista, boolean todas) {
        DAOUniversalHibernate acceso = new DAOUniversalHibernate();
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
                        }

                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "El error es:" + "\n" + e, "Error", 0);
                }
            } else {
                existenProveedores = false;
            }
        }
    }

    private void btnAsignarFolioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsignarFolioActionPerformed
        if (jtExpedienteAsignar.getSelectedRow() != -1) {
            DAOUniversalHibernate acceso = new DAOUniversalHibernate();
            String Query = "";
            try {

                Date fecha = herramienta.FechaHerramienta.convertirStringEnDate(
                        jtExpedienteAsignar.getValueAt(jtExpedienteAsignar.getSelectedRow(), 3).toString());

                String nombreProveedor = jtExpedienteAsignar.getValueAt(jtExpedienteAsignar.getSelectedRow(), 2).toString();
                pojos.Contenidoexpediente conExp = new pojos.Contenidoexpediente();

                String query = "From Proveedores p WHERE p.nombre = '" + nombreProveedor + "'";
                Proveedores proveedor = (Proveedores) acceso.select(query).get(0);

                //CLAVE EXPEDIENTE
                conExp.setIdExpediente(idExpediente);
                //CLAVE PROVEEDOR
                conExp.setIdProveedor(proveedor.getIdProveedor());
                //NOMBRE PROVEEDOR
                conExp.setProveedor(nombreProveedor);
                //FECHA
                conExp.setFecha(fecha);
                //FOLIO
                conExp.setFolio(txtFolioExpedienteAsignar.getText());
                //PIEZAS
                int piezas = Integer.parseInt(txtPiezasAsignar.getText());
                conExp.setPiezas(piezas);
                //IMPORTE
                double importe = Double.parseDouble(txtImporteAsignar.getText());
                conExp.setImporte(importe);
                //FORMA DE PAGO
                conExp.setFormaDePago("PENDIENTE");
                //ESTADO
                conExp.setEstadoIndividualFolio("NO PAGADO");
                //ABONO
                conExp.setAbono(0.0);
                //SALDO
                conExp.setSaldo(importe);

                acceso.add(conExp);

                Query = "FROM Contenidoexpediente ce WHERE ce.idExpediente ='" + idExpediente + "'";
                cargaTabla(jtFoliosAsignados, Query, "ContenidoExpediente", 1);

                limpiarCampos();
                JOptionPane.showMessageDialog(this, "Folio asignado correctamente", "Datos registrados", 1);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e, "Error", 0);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona un EXPEDIENTE de la "
                    + "lista para poder ASIGNAR.", "Registro no seleccionado", 0);
        }
    }//GEN-LAST:event_btnAsignarFolioActionPerformed

    private void txtFolioExpedienteAsignarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFolioExpedienteAsignarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFolioExpedienteAsignarActionPerformed

    private void btnBorrarAsignarFolioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarAsignarFolioActionPerformed
// TODO add your handling code here:
        if (jtExpedienteAsignar.getSelectedRow() != -1) {
            int respuesta = JOptionPane.showConfirmDialog(this, "¿Desea eliminar este folio?", "Confirmar eliminación", 0, 3);
            if (respuesta == 0) {
                try {
                    DAOUniversalHibernate acceso = new DAOUniversalHibernate();

                    int clave = (int) jtExpedienteAsignar.getValueAt(jtExpedienteAsignar.getSelectedRow(), 0);
                    String query = "FROM Contenidoexpediente e WHERE e.idExpediente = '" + clave + "'";
                    Contenidoexpediente expediente = (Contenidoexpediente) acceso.select(query).get(0);

                    acceso.delete(expediente);

                    idExpediente = (int) jtExpedienteAsignar.getValueAt(jtExpedienteAsignar.getSelectedRow(), 0);
                    query = "FROM Contenidoexpediente ce WHERE ce.idExpediente ='" + idExpediente + "'";
                    cargaTabla(jtFoliosAsignados, query, "ContenidoExpediente", 1);

                    JOptionPane.showMessageDialog(this, "Expediente eliminado correctamente", "Cancelado", 1);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Ha ocurrido un error: " + e, "Error", 0);
                    System.gc();
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona un registro de la "
                    + "lista para poder borrar.", "Registro no seleccionado", 0);
        }

    }//GEN-LAST:event_btnBorrarAsignarFolioActionPerformed

    private void btnCorregirAsignarFolioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCorregirAsignarFolioActionPerformed
        if (jtFoliosAsignados.getSelectedRow() != -1) {

            String clave = jtFoliosAsignados.getValueAt(jtFoliosAsignados.getSelectedRow(), 0).toString();
            try {
                CorregirContenidoExpediente corregirConExpediente = new CorregirContenidoExpediente(clave);
                if (exist(corregirConExpediente) == false) {
                    CPanel.desktop.add(corregirConExpediente);
                    corregirConExpediente.setVisible(true);
                    corregirConExpediente.setLocation((CPanel.desktop.getWidth() - corregirConExpediente.getWidth()) / 2,
                            (CPanel.desktop.getHeight() - corregirConExpediente.getHeight()) / 2);
                } else {
                    corregirConExpediente.dispose();
                }
            } catch (Exception ex) {

            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona un registro de la "
                    + "lista para poder corregir.", "Registro no seleccionado", 0);
        }
    }//GEN-LAST:event_btnCorregirAsignarFolioActionPerformed

    private void jpAsignarFoliosExpedienteComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jpAsignarFoliosExpedienteComponentShown
        String Query = "FROM Proveedores p ORDER BY p.nombre";
        llenarCombo(cmbProveedorBuscar, Query, "proveedor", false);

        Query = "FROM Expediente e ORDER BY e.nombreProveedor";
        cargaTabla(jtExpedienteAsignar, Query, "Expediente", 2);


    }//GEN-LAST:event_jpAsignarFoliosExpedienteComponentShown

    private void txtImporteAsignarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtImporteAsignarKeyTyped
        char key = evt.getKeyChar();
        if (((key < '0') || (key > '9')) && (key != evt.VK_BACK_SPACE)
                && (key != '.')) {
            evt.consume();
        }
        if (key == '.' && txtImporteAsignar.getText().contains(".")) {
            evt.consume();
            JOptionPane.showMessageDialog(this, "Solo se permite un punto "
                    + "este campo", "Validacion decimal", 0);
        }
    }//GEN-LAST:event_txtImporteAsignarKeyTyped

    private void txtPiezasAsignarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPiezasAsignarKeyTyped
        char c = evt.getKeyChar();

        if (Character.isLetter(c)) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtPiezasAsignarKeyTyped

    private void jtFoliosAsignadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtFoliosAsignadosMouseClicked
        if (evt.getClickCount() == 2) {
            String Query = "FROM Contenidoexpediente ce WHERE ce.idExpediente="
                    + idExpediente
                    + " ORDER BY ce.folio";
            cargaTabla(jtFoliosAsignados, Query, "ContenidoExpediente", 1);
        }
    }//GEN-LAST:event_jtFoliosAsignadosMouseClicked

    private void btnCorregirProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCorregirProveedorActionPerformed
        if (jtProveedor.getSelectedRow() != -1) {
            int respuesta = JOptionPane.showConfirmDialog(this, "¿Desea editar este proveedor?", "Confirmar editar", 0, 3);
            if (respuesta == 0) {
                try {
                    DAOUniversalHibernate acceso = new DAOUniversalHibernate();
                    int clave = (int) jtProveedor.getValueAt(jtProveedor.getSelectedRow(), 0);
                    String query = "From Proveedores s WHERE s.idProveedor = '" + clave + "'";
                    Proveedores proveedor = (Proveedores) acceso.select(query).get(0);
                    String nombre = JOptionPane.showInputDialog(this, "Nuevo nombre");
                    proveedor.setNombre(nombre);
                    acceso.Update(proveedor);
                    query = "From Proveedores s ORDER BY s.idProveedor";
                    cargaTabla(jtClientes, query, "Proveedor", 1);
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
                    DAOUniversalHibernate acceso = new DAOUniversalHibernate();
                    int clave = (int) jtProveedor.getValueAt(jtProveedor.getSelectedRow(), 0);
                    String query = "From Proveedores s WHERE s.idProveedor = '" + clave + "'";
                    Proveedores proveedor = (Proveedores) acceso.select(query).get(0);
                    acceso.delete(proveedor);
                    crearConsultaGlobal();
                    cargaTabla(jtClientes, query, "Proveedor", 1);
                    JOptionPane.showMessageDialog(this, "Proveedor eliminada correctamente", "Cancelado", 1);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Ha ocurrido un error: " + e, "Error", 0);
                    System.gc();
                }
            }
        }
    }//GEN-LAST:event_btnEliminarProveedorActionPerformed

    private void btnNuevoProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoProveedorActionPerformed
        DAOUniversalHibernate control = new DAOUniversalHibernate();
        try {
            if (!txtProveedorNuevo.getText().isEmpty()) {
                pojos.Proveedores proveedor = new pojos.Proveedores();
                proveedor.setNombre(txtProveedorNuevo.getText());
                control.add(proveedor);

                limpiarCampos();
                JOptionPane.showMessageDialog(this, "Proveedor registrado correctamente", "Datos registrados", 1);
                String Query = "";
                Query = "FROM Proveedores p ORDER BY p.nombre";
                cargaTabla(jtProveedor, Query, "Proveedor", 0);
            } else {
                JOptionPane.showMessageDialog(this, "Favor de llenar el campo Nombre", "Datos incompletos", 1);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e, "Error", 0);
        }
    }//GEN-LAST:event_btnNuevoProveedorActionPerformed

    private void btnNuevoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoClienteActionPerformed
        DAOUniversalHibernate control = new DAOUniversalHibernate();
        try {
            if (!txtClienteNuevo.getText().isEmpty()) {
                pojos.Clientes cliente = new pojos.Clientes();
                cliente.setNombre(txtClienteNuevo.getText());
                control.add(cliente);

                limpiarCampos();
                JOptionPane.showMessageDialog(this, "Cliente registrado correctamente", "Datos registrados", 1);
                String Query = "";
                Query = "FROM Clientes c ORDER BY c.nombre";
                cargaTabla(jtClientes, Query, "Cliente", 0);
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
                    DAOUniversalHibernate acceso = new DAOUniversalHibernate();
                    int clave = (int) jtClientes.getValueAt(jtClientes.getSelectedRow(), 0);
                    String query = "From Clientes s WHERE s.idCliente = '" + clave + "'";
                    Clientes cliente = (Clientes) acceso.select(query).get(0);
                    String nombre = JOptionPane.showInputDialog(this, "Nuevo nombre");
                    cliente.setNombre(nombre);
                    acceso.Update(cliente);
                    query = "From Clientes c ORDER BY c.idCliente";
                    cargaTabla(jtClientes, query, "Cliente", 1);
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
                    DAOUniversalHibernate acceso = new DAOUniversalHibernate();
                    int clave = (int) jtClientes.getValueAt(jtClientes.getSelectedRow(), 0);
                    String query = "From Clientes c WHERE c.idCliente = '" + clave + "'";
                    Clientes cliente = (Clientes) acceso.select(query).get(0);
                    acceso.delete(cliente);
                    crearConsultaGlobal();
                    cargaTabla(jtClientes, query, "Cliente", 1);
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
        cargaTabla(jtProveedor, Query, "Proveedor", 1);
        Query = "FROM Clientes c ORDER BY c.nombre";
        cargaTabla(jtClientes, Query, "Cliente", 1);
    }//GEN-LAST:event_jpProveedoresClientesComponentShown

    private void btnBorrarExpediente1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarExpediente1ActionPerformed
        // TODO add your handling code here:
        if (jtExpedienteAsignar.getSelectedRow() != -1) {
            int respuesta = JOptionPane.showConfirmDialog(this, "¿Desea eliminar este expediente?", "Confirmar eliminación", 0, 3);
            if (respuesta == 0) {
                try {
                    DAOUniversalHibernate acceso = new DAOUniversalHibernate();
                    int clave = (int) jtExpedienteAsignar.getValueAt(jtExpedienteAsignar.getSelectedRow(), 1);
                    String query = "FROM Expediente e WHERE e.idProveedor = '" + clave + "'";
                    Expediente expediente = (Expediente) acceso.select(query).get(0);
                    acceso.delete(expediente);
                    String Query = "";
                    if (!cmbProveedorBuscar.getSelectedItem().toString().equals("Todos")) {
                        Query = "FROM Expediente e WHERE e.nombreProveedor='"
                                + cmbProveedorBuscar.getSelectedItem().toString()
                                + "'ORDER BY e.nombreProveedor";
                        cargaTabla(jtExpedienteAsignar, Query, "Expediente", 2);
                    } else {
                        Query = "FROM Expediente e ORDER BY e.nombreProveedor";
                        cargaTabla(jtExpedienteAsignar, Query, "Expediente", 2);
                    }
                    //////////
                    try {
                        query = "FROM Contenidoexpediente e WHERE e.idExpediente = '" + clave + "'";
                        Contenidoexpediente conExpediente = (Contenidoexpediente) acceso.select(query).get(0);
                        acceso.delete(conExpediente);

                        query = "FROM Contenidoexpediente ce WHERE ce.idExpediente ='" + -1 + "'";
                        cargaTabla(jtFoliosAsignados, query, "ContenidoExpediente", 1);

                        JOptionPane.showMessageDialog(this, "Notas eliminadas correctamente", "Cancelado", 1);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, "Ha ocurrido un error: " + e, "Error", 0);
                        System.gc();
                    }
                    ////////////
                    JOptionPane.showMessageDialog(this, "Expediente eliminado correctamente", "Cancelado", 1);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Ha ocurrido un error: " + e, "Error", 0);
                    System.gc();
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona un registro de la "
                    + "lista para poder borrar.", "Registro no seleccionado", 0);
        }

    }//GEN-LAST:event_btnBorrarExpediente1ActionPerformed

    private void jtExpedienteAsignarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtExpedienteAsignarMouseClicked
//CARGA LOS FOLIOS
        idExpediente = (int) jtExpedienteAsignar.getValueAt(jtExpedienteAsignar.getSelectedRow(), 0);
        //CARGA LOS PROVEEDORES
        if (evt.getClickCount() == 2) {
            if (!cmbProveedorBuscar.getSelectedItem().toString().equals("Todos")) {
                String Query = "FROM Contenidoexpediente ce WHERE ce.idExpediente="
                        + idExpediente
                        + " ORDER BY ce.folio";
                cargaTabla(jtFoliosAsignados, Query, "ContenidoExpediente", 1);
                Query = "FROM Expediente e WHERE e.nombreProveedor='"
                        + cmbProveedorBuscar.getSelectedItem().toString()
                        + "'ORDER BY e.nombreProveedor";
                cargaTabla(jtExpedienteAsignar, Query, "Expediente", 2);
                double suma = 0;
                for (int i = 0; i < jtFoliosAsignados.getRowCount(); i++) {
                    suma = suma + Double.parseDouble(jtFoliosAsignados.getValueAt(i, 7).toString());
                }
                
            } else {

                String Query = "FROM Contenidoexpediente ce WHERE ce.idExpediente="
                        + idExpediente
                        + " ORDER BY ce.folio";
                cargaTabla(jtFoliosAsignados, Query, "ContenidoExpediente", 1);
                Query = "FROM Expediente e ORDER BY e.nombreProveedor";
                cargaTabla(jtExpedienteAsignar, Query, "Expediente", 2);
                double suma = 0;
                for (int i = 0; i < jtFoliosAsignados.getRowCount(); i++) {
                    suma = suma + Double.parseDouble(jtFoliosAsignados.getValueAt(i, 5).toString());
                }
                

            }
        }
    }//GEN-LAST:event_jtExpedienteAsignarMouseClicked

    private void jtFoliosResumenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtFoliosResumenMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jtFoliosResumenMouseClicked

    private void jtExpedienteResumenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtExpedienteResumenMouseClicked
//CARGA LOS FOLIOS
        idExpediente = (int) jtExpedienteResumen.getValueAt(jtExpedienteResumen.getSelectedRow(), 0);
        //CARGA LOS PROVEEDORES
        if (evt.getClickCount() == 2) {
            
            if (!cmbProveedorResumen.getSelectedItem().toString().equals("Todos")) {
                String Query = "FROM Contenidoexpediente ce WHERE ce.idExpediente="
                        + idExpediente
                        + " ORDER BY ce.folio";
                cargaTabla(jtFoliosResumen, Query, "ContenidoExpediente2", 1);
                Query = "FROM Expediente e WHERE e.nombreProveedor='"
                        + cmbProveedorResumen.getSelectedItem().toString()
                        + "'ORDER BY e.nombreProveedor";
                cargaTabla(jtExpedienteResumen, Query, "Expediente", 2);
                double sumaImporte = 0;
                double sumaAbono = 0;
                double sumaPiezas = 0;
                int sumaPagados = 0;
                int sumaNoPagados = 0;

                for (int i = 0; i < jtFoliosResumen.getRowCount(); i++) {
                    sumaImporte = sumaImporte + Double.parseDouble(jtFoliosResumen.getValueAt(i, 7).toString());
                    sumaPiezas = sumaPiezas + Double.parseDouble(jtFoliosResumen.getValueAt(i, 6).toString());
                    sumaAbono = sumaAbono + Double.parseDouble(jtFoliosResumen.getValueAt(i, 5).toString());
                    String pagado = jtFoliosResumen.getValueAt(i, 7).toString();
                    if (pagado.equals("PAGADO")) {
                        sumaPagados = sumaPagados + 1;
                    } else {
                        sumaNoPagados = sumaNoPagados + 1;
                    }
                }
                double saldo = sumaImporte - sumaAbono;
                lblSaldoResumen.setText(String.valueOf(saldo));
                lblImporteResumen.setText(String.valueOf(sumaImporte));
                lblPiezasResumen.setText(String.valueOf(sumaPiezas));
                lblNotasPagadas.setText(String.valueOf(sumaPagados));
                lblNotasNoPagadas.setText(String.valueOf(sumaNoPagados));
                lblAbonoTotal.setText(String.valueOf(sumaAbono));
                if (sumaPagados > sumaNoPagados) {
                    lblEstadoResumen.setText("NO PAGADO");
                } else {
                    lblEstadoResumen.setText("PAGADO");
                }
            } else {

                String Query = "FROM Contenidoexpediente ce WHERE ce.idExpediente="
                        + idExpediente
                        + " ORDER BY ce.folio";
                cargaTabla(jtFoliosResumen, Query, "ContenidoExpediente2", 1);
                Query = "FROM Expediente e ORDER BY e.nombreProveedor";
                cargaTabla(jtExpedienteResumen, Query, "Expediente", 2);
                double sumaImporte = 0;
                double sumaAbono = 0;
                double sumaPiezas = 0;
                int sumaPagados = 0;
                int sumaNoPagados = 0;

                for (int i = 0; i < jtFoliosResumen.getRowCount(); i++) {
                    sumaImporte = sumaImporte + Double.parseDouble(jtFoliosResumen.getValueAt(i, 4).toString());
                    sumaPiezas = sumaPiezas + Double.parseDouble(jtFoliosResumen.getValueAt(i, 3).toString());
                    sumaAbono = sumaAbono + Double.parseDouble(jtFoliosResumen.getValueAt(i, 5).toString());
                    String pagado = jtFoliosResumen.getValueAt(i, 7).toString();
                    if (pagado.equals("PAGADO")) {
                        sumaPagados = sumaPagados + 1;
                    } else {
                        sumaNoPagados = sumaNoPagados + 1;
                    }
                }
                double saldo = sumaImporte - sumaAbono;
                lblSaldoResumen.setText(String.valueOf(saldo));
                lblImporteResumen.setText(String.valueOf(sumaImporte));
                lblPiezasResumen.setText(String.valueOf(sumaPiezas));
                lblNotasPagadas.setText(String.valueOf(sumaPagados));
                lblNotasNoPagadas.setText(String.valueOf(sumaNoPagados));
                lblAbonoTotal.setText(String.valueOf(sumaAbono));
                if (sumaPagados > sumaNoPagados) {
                    lblEstadoResumen.setText("PAGADO");
                } else {
                    lblEstadoResumen.setText("NO PAGADO");
                }
            }
            int noNotas = jtFoliosResumen.getRowCount();
            lblNoNotas.setText(String.valueOf(noNotas));
        }
    }//GEN-LAST:event_jtExpedienteResumenMouseClicked

    private void jPanel1ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel1ComponentShown
        jdcInicioResumen.setDate(new Date());
        jdcFinResumen.setDate(new Date());
        String Query = "FROM Proveedores p ORDER BY p.nombre";
        llenarCombo(cmbProveedorResumen, Query, "proveedor", false);

        Query = "FROM Expediente e ORDER BY e.nombreProveedor";
        cargaTabla(jtExpedienteResumen, Query, "Expediente", 2);

        lblNoCompras.setText(String.valueOf(jtExpedienteResumen.getRowCount()));
    }//GEN-LAST:event_jPanel1ComponentShown
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
    private javax.swing.JButton btnBorrarCliente;
    private javax.swing.JButton btnBorrarExpediente1;
    private javax.swing.JButton btnCorregirAsignarFolio;
    private javax.swing.JButton btnCorregirProveedor;
    private javax.swing.JButton btnEditarCliente;
    private javax.swing.JButton btnEliminarProveedor;
    private javax.swing.JButton btnNuevoCliente;
    private javax.swing.JButton btnNuevoProveedor;
    private javax.swing.JComboBox cmbProveedorBuscar;
    private javax.swing.JComboBox cmbProveedorResumen;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel7;
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
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane7;
    private com.toedter.calendar.JDateChooser jdcFechaFin;
    private com.toedter.calendar.JDateChooser jdcFechaInicio;
    private com.toedter.calendar.JDateChooser jdcFinResumen;
    private com.toedter.calendar.JDateChooser jdcInicioResumen;
    private javax.swing.JPanel jpAsignarFoliosExpediente;
    private javax.swing.JPanel jpProveedoresClientes;
    private javax.swing.JScrollPane jsProvedorCliente;
    private javax.swing.JTable jtClientes;
    private javax.swing.JTabbedPane jtExpediente;
    private javax.swing.JTable jtExpedienteAsignar;
    private javax.swing.JTable jtExpedienteResumen;
    private javax.swing.JTable jtFoliosAsignados;
    private javax.swing.JTable jtFoliosResumen;
    private javax.swing.JTable jtProveedor;
    private javax.swing.JLabel lblAbonoTotal;
    private javax.swing.JLabel lblEstadoResumen;
    private javax.swing.JLabel lblImporteResumen;
    private javax.swing.JLabel lblNoCompras;
    private javax.swing.JLabel lblNoNotas;
    private javax.swing.JLabel lblNotasNoPagadas;
    private javax.swing.JLabel lblNotasPagadas;
    private javax.swing.JLabel lblPiezasResumen;
    private javax.swing.JLabel lblSaldoResumen;
    private javax.swing.JTextField txtClienteNuevo;
    private javax.swing.JTextField txtFolioExpedienteAsignar;
    private javax.swing.JTextField txtImporteAsignar;
    private javax.swing.JTextField txtPiezasAsignar;
    private javax.swing.JTextField txtProveedorNuevo;
    // End of variables declaration//GEN-END:variables
}
