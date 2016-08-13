/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import controlBD.DAOUniversalHibernate;
import herramienta.FechaHerramienta;
import java.beans.PropertyVetoException;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import pojos.Clientes;
import pojos.Contenidoexpediente;
import pojos.Expediente;
import pojos.Linea;
import pojos.Proveedores;
import pojos.Sucursal;

/**
 *
 * @author Daniel
 */
public class CorregirContenidoExpediente extends javax.swing.JInternalFrame {

    Contenidoexpediente conExpediente;

    public CorregirContenidoExpediente(String clave) {
        initComponents();
        cargaDatos(clave);
        seteaDatos();
        String Query = "FROM Proveedores p ORDER BY p.nombre";
        llenarCombo(cmbProveedorBuscar, Query, "proveedor", true);
    }

    private void llenarCombo(JComboBox combo, String query, String tipoLista, boolean todas) {
        DAOUniversalHibernate acceso = new DAOUniversalHibernate();
        combo.removeAllItems();
        List listaItems = acceso.select(query);
        //combo = new JComboBox();
        if (listaItems.size() > 0) {
            try {
               
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

    private void cargaDatos(String clave) {
        //Reviso si que la consulta no vaya vacia
        if (!clave.isEmpty()) {
            DAOUniversalHibernate acceso = new DAOUniversalHibernate();
            conExpediente = new Contenidoexpediente();
            String query = "FROM Contenidoexpediente ce WHERE ce.idContenidoExpediente = '" + clave + "'";
            conExpediente = (Contenidoexpediente) acceso.select(query).get(0);
        }
    }

    private void seteaDatos() {
        lblTituloTiendaCorregir.setText("Corregir Contenido del expediente con folio " + conExpediente.getFolio());
        jdcFechaAsignarFolio.setDate(conExpediente.getFecha());
        txtFolioExpedienteAsignar.setText(conExpediente.getFolio());
        txtImporte.setText(String.valueOf(conExpediente.getImporte()));
        txtPiezasAsignar.setText(String.valueOf(conExpediente.getPiezas()));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblTituloTiendaCorregir = new javax.swing.JLabel();
        btnCorregir = new javax.swing.JButton();
        jLabel73 = new javax.swing.JLabel();
        jdcFechaAsignarFolio = new com.toedter.calendar.JDateChooser();
        jLabel86 = new javax.swing.JLabel();
        txtFolioExpedienteAsignar = new javax.swing.JTextField();
        jLabel66 = new javax.swing.JLabel();
        txtImporte = new javax.swing.JTextField();
        jLabel67 = new javax.swing.JLabel();
        txtPiezasAsignar = new javax.swing.JTextField();
        jLabel71 = new javax.swing.JLabel();
        cmbProveedorBuscar = new javax.swing.JComboBox();

        setClosable(true);
        setToolTipText("Corregir Diario de Caja");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lblTituloTiendaCorregir.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        lblTituloTiendaCorregir.setForeground(new java.awt.Color(0, 102, 153));
        lblTituloTiendaCorregir.setText("Corregir expediente del proveedor");

        btnCorregir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/correct.png"))); // NOI18N
        btnCorregir.setText("Corregir");
        btnCorregir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCorregirActionPerformed(evt);
            }
        });

        jLabel73.setBackground(new java.awt.Color(255, 255, 255));
        jLabel73.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel73.setForeground(new java.awt.Color(0, 102, 153));
        jLabel73.setText("FECHA");
        jLabel73.setOpaque(true);

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

        jLabel66.setBackground(new java.awt.Color(255, 255, 255));
        jLabel66.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(0, 102, 153));
        jLabel66.setText("IMPORTE");
        jLabel66.setOpaque(true);

        txtImporte.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtImporte.setForeground(new java.awt.Color(153, 0, 0));
        txtImporte.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtImporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtImporteActionPerformed(evt);
            }
        });
        txtImporte.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtImporteKeyTyped(evt);
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addComponent(lblTituloTiendaCorregir, javax.swing.GroupLayout.PREFERRED_SIZE, 655, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(351, 351, 351)
                        .addComponent(btnCorregir)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel71)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel73, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jdcFechaAsignarFolio, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel86, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtFolioExpedienteAsignar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel67, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtPiezasAsignar, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtImporte)
                                .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(cmbProveedorBuscar, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(131, 131, 131))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lblTituloTiendaCorregir, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbProveedorBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel66)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtImporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel67)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtPiezasAsignar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel86, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtFolioExpedienteAsignar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel73)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jdcFechaAsignarFolio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(80, 80, 80)
                .addComponent(btnCorregir, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCorregirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCorregirActionPerformed
        if (!txtFolioExpedienteAsignar.getText().isEmpty()
                && !txtImporte.getText().isEmpty()
                && !txtPiezasAsignar.getText().isEmpty()) {
            DAOUniversalHibernate acceso = new DAOUniversalHibernate();

            conExpediente.setFecha(jdcFechaAsignarFolio.getDate());
            conExpediente.setFolio(txtFolioExpedienteAsignar.getText());
            double importe = Double.parseDouble(txtImporte.getText());
            int piezas = Integer.parseInt(txtPiezasAsignar.getText());
            conExpediente.setPiezas(piezas);
            conExpediente.setImporte(importe);
            acceso.Update(conExpediente);
            try {
                this.setClosed(true);
                System.gc();
            } catch (PropertyVetoException ex) {
                this.dispose();
                System.gc();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Hay campos vacios por favor llenalos todos", "Campos vacios", 1);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCorregirActionPerformed

    private void txtFolioExpedienteAsignarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFolioExpedienteAsignarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFolioExpedienteAsignarActionPerformed

    private void txtImporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtImporteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtImporteActionPerformed

    private void txtPiezasAsignarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPiezasAsignarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPiezasAsignarActionPerformed

    private void txtImporteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtImporteKeyTyped
        char key = evt.getKeyChar();
        if (((key < '0') || (key > '9')) && (key != evt.VK_BACK_SPACE)
                && (key != '.')) {
            evt.consume();
        }
        if (key == '.' && txtImporte.getText().contains(".")) {
            evt.consume();
            JOptionPane.showMessageDialog(this, "Solo se permite un punto "
                    + "este campo", "Validacion decimal", 0);
        }
    }//GEN-LAST:event_txtImporteKeyTyped

    private void txtPiezasAsignarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPiezasAsignarKeyTyped
        char key = evt.getKeyChar();
        if (((key < '0') || (key > '9')) && (key != evt.VK_BACK_SPACE)
                && (key != '.')) {
            evt.consume();
        }
        if (key == '.' && txtPiezasAsignar.getText().contains(".")) {
            evt.consume();
            JOptionPane.showMessageDialog(this, "Solo se permite un punto "
                    + "este campo", "Validacion decimal", 0);
        }
    }//GEN-LAST:event_txtPiezasAsignarKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCorregir;
    private javax.swing.JComboBox cmbProveedorBuscar;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JPanel jPanel1;
    private com.toedter.calendar.JDateChooser jdcFechaAsignarFolio;
    private javax.swing.JLabel lblTituloTiendaCorregir;
    private javax.swing.JTextField txtFolioExpedienteAsignar;
    private javax.swing.JTextField txtImporte;
    private javax.swing.JTextField txtPiezasAsignar;
    // End of variables declaration//GEN-END:variables
}
