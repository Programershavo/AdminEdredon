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
import pojos.Gastoslocales;
import pojos.Linea;
import pojos.Proveedores;
import pojos.Sucursal;

/**
 *
 * @author Daniel
 */
public class CorregirGastoLocal extends javax.swing.JInternalFrame {

    Gastoslocales gastoLocal;

    public CorregirGastoLocal(String clave) {
        initComponents();
        cargaDatos(clave);
        seteaDatos();
        String query = "FROM Sucursal s ORDER BY s.nombre";
        llenarCombo(cmbLocal, query, "local", true);
        query = "FROM Linea l ORDER BY l.nombre";
        llenarCombo(cmbConcepto, query, "concepto", true);
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
//                    if (!todas) {
//                        combo.addItem("Todos");
//                    }
//                    if (todas) {
//                        combo.addItem("Todas");
//                    }
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
    }

    private void cargaDatos(String clave) {
        //Reviso si que la consulta no vaya vacia
        if (!clave.isEmpty()) {
            DAOUniversalHibernate acceso = new DAOUniversalHibernate();
            gastoLocal = new Gastoslocales();
            String query = "FROM Gastoslocales gl WHERE gl.idGastosLocales = '" + clave + "'";
            gastoLocal = (Gastoslocales) acceso.select(query).get(0);
        }
    }

    private void seteaDatos() {
        lblTituloTiendaCorregir.setText("Gasto de caja de " + gastoLocal.getConcepto());
        jdcFechaGasto.setDate(gastoLocal.getFecha());
        txtComentario.setText(String.valueOf(gastoLocal.getComentario()));
        txtImporte.setText(String.valueOf(gastoLocal.getImporte()));

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblTituloTiendaCorregir = new javax.swing.JLabel();
        lblLocal3 = new javax.swing.JLabel();
        txtComentario = new javax.swing.JTextField();
        lblLocal4 = new javax.swing.JLabel();
        txtImporte = new javax.swing.JTextField();
        btnCorregir = new javax.swing.JButton();
        jdcFechaGasto = new com.toedter.calendar.JDateChooser();
        jLabel11 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        cmbLocal = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        cmbConcepto = new javax.swing.JComboBox();

        setClosable(true);
        setIconifiable(true);
        setTitle("CORREGIR GASTOS LOCALES");
        setToolTipText("Corregir Diario de Caja");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lblTituloTiendaCorregir.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        lblTituloTiendaCorregir.setForeground(new java.awt.Color(0, 102, 153));
        lblTituloTiendaCorregir.setText("Diario de Caja de");

        lblLocal3.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lblLocal3.setForeground(new java.awt.Color(0, 102, 153));
        lblLocal3.setText("COMENTARIO");

        txtComentario.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        txtComentario.setForeground(new java.awt.Color(204, 0, 0));
        txtComentario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtComentario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtComentarioKeyTyped(evt);
            }
        });

        lblLocal4.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lblLocal4.setForeground(new java.awt.Color(0, 102, 153));
        lblLocal4.setText("IMPORTE");

        txtImporte.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        txtImporte.setForeground(new java.awt.Color(204, 0, 0));
        txtImporte.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtImporte.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtImporteKeyTyped(evt);
            }
        });

        btnCorregir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/correct.png"))); // NOI18N
        btnCorregir.setText("Corregir");
        btnCorregir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCorregirActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setText("FECHA");

        jLabel28.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(0, 102, 153));
        jLabel28.setText("LOCAL");

        cmbLocal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbLocal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbLocalActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 102, 153));
        jLabel2.setText("CONCEPTO");

        cmbConcepto.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jdcFechaGasto, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbLocal, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbConcepto, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblLocal3, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblLocal4, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtComentario, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtImporte, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(241, 241, 241)
                        .addComponent(lblTituloTiendaCorregir, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(351, 351, 351)
                .addComponent(btnCorregir)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {lblLocal3, lblLocal4});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtComentario, txtImporte});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel11, jdcFechaGasto});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lblTituloTiendaCorregir, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblLocal3)
                            .addComponent(lblLocal4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtComentario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtImporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jdcFechaGasto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel28)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(cmbLocal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbConcepto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(56, 56, 56)
                .addComponent(btnCorregir, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68))
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

    private void txtComentarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtComentarioKeyTyped
//        char key = evt.getKeyChar();
//        if (((key < '0') || (key > '9')) && (key != evt.VK_BACK_SPACE)
//                && (key != '.')) {
//            evt.consume();
//        }
//        if (key == '.' && txtComentario.getText().contains(".")) {
//            evt.consume();
//            JOptionPane.showMessageDialog(this, "Solo se permite un punto "
//                    + "este campo", "Validacion decimal", 0);
//        }
    }//GEN-LAST:event_txtComentarioKeyTyped

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

    private void btnCorregirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCorregirActionPerformed
        if (!txtComentario.getText().isEmpty()
                && !txtImporte.getText().isEmpty()) {
            DAOUniversalHibernate acceso = new DAOUniversalHibernate();
            String fecha = FechaHerramienta.formatoYMD(jdcFechaGasto.getDate());
            gastoLocal.setFecha(FechaHerramienta.convertirStringEnDate(fecha));
            gastoLocal.setConcepto(cmbConcepto.getSelectedItem().toString());
            gastoLocal.setLocal(cmbLocal.getSelectedItem().toString());
            gastoLocal.setImporte(Double.parseDouble(txtImporte.getText()));
            gastoLocal.setComentario(txtComentario.getText());
            acceso.Update(gastoLocal);
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

    private void cmbLocalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbLocalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbLocalActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCorregir;
    private javax.swing.JComboBox cmbConcepto;
    private javax.swing.JComboBox cmbLocal;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JPanel jPanel1;
    private com.toedter.calendar.JDateChooser jdcFechaGasto;
    private javax.swing.JLabel lblLocal3;
    private javax.swing.JLabel lblLocal4;
    private javax.swing.JLabel lblTituloTiendaCorregir;
    private javax.swing.JTextField txtComentario;
    private javax.swing.JTextField txtImporte;
    // End of variables declaration//GEN-END:variables
}
