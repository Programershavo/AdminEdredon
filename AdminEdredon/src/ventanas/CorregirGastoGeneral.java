/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import controlBD.AccesoBD;
import herramienta.FechaHerramienta;
import java.beans.PropertyVetoException;
import javax.swing.JOptionPane;
import pojos.Diariocaja;
import pojos.Gastosgenerales;

/**
 *
 * @author Daniel
 */
public class CorregirGastoGeneral extends javax.swing.JInternalFrame {

    Gastosgenerales gastoGeneral;

    public CorregirGastoGeneral(String clave) {
        initComponents();
        cargaDatos(clave);
        seteaDatos();
    }

    private void cargaDatos(String clave) {
        //Reviso si que la consulta no vaya vacia
        if (!clave.isEmpty()) {
            AccesoBD acceso = new AccesoBD();
            gastoGeneral = new Gastosgenerales();
            String HQL = "From Gastosgenerales g WHERE g.idGastosGenerales = '" + clave + "'";
            gastoGeneral = (Gastosgenerales) acceso.select(HQL).get(0);
        }
    }

    private void seteaDatos() {
        lblTituloTiendaCorregir.setText("Gasto de caja de " + gastoGeneral.getConcepto());
        jdcFechaGasto.setDate(gastoGeneral.getFecha());
        txtConcepto.setText(gastoGeneral.getConcepto());
        txtSubConcepto.setText(String.valueOf(gastoGeneral.getSubconcepto()));
        txtMotivo.setText(String.valueOf(gastoGeneral.getAcreedor()));
        txtComentario.setText(String.valueOf(gastoGeneral.getComentario()));
        txtImporte.setText(String.valueOf(gastoGeneral.getImporte()));

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblTituloTiendaCorregir = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        txtConcepto = new javax.swing.JTextField();
        lblLocal = new javax.swing.JLabel();
        txtSubConcepto = new javax.swing.JTextField();
        lblLocal1 = new javax.swing.JLabel();
        txtMotivo = new javax.swing.JTextField();
        lblLocal3 = new javax.swing.JLabel();
        txtComentario = new javax.swing.JTextField();
        lblLocal4 = new javax.swing.JLabel();
        txtImporte = new javax.swing.JTextField();
        btnCorregir = new javax.swing.JButton();
        jdcFechaGasto = new com.toedter.calendar.JDateChooser();
        jLabel11 = new javax.swing.JLabel();

        setToolTipText("Corregir Diario de Caja");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lblTituloTiendaCorregir.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        lblTituloTiendaCorregir.setForeground(new java.awt.Color(0, 102, 153));
        lblTituloTiendaCorregir.setText("Diario de Caja de");

        jLabel35.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(0, 102, 153));
        jLabel35.setText("CONCEPTO");

        txtConcepto.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        txtConcepto.setForeground(new java.awt.Color(204, 0, 0));
        txtConcepto.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        lblLocal.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lblLocal.setForeground(new java.awt.Color(0, 102, 153));
        lblLocal.setText("SUBCONCEPTO");

        txtSubConcepto.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        txtSubConcepto.setForeground(new java.awt.Color(204, 0, 0));
        txtSubConcepto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSubConcepto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSubConceptoKeyTyped(evt);
            }
        });

        lblLocal1.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lblLocal1.setForeground(new java.awt.Color(0, 102, 153));
        lblLocal1.setText("RAZON");

        txtMotivo.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        txtMotivo.setForeground(new java.awt.Color(204, 0, 0));
        txtMotivo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMotivo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMotivoKeyTyped(evt);
            }
        });

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtConcepto, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblLocal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 165, Short.MAX_VALUE)
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
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(328, 328, 328)
                                .addComponent(txtMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(169, 169, 169)
                                .addComponent(txtSubConcepto, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.CENTER, jPanel1Layout.createSequentialGroup()
                                .addGap(241, 241, 241)
                                .addComponent(lblTituloTiendaCorregir, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(328, 328, 328)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                        .addComponent(jdcFechaGasto, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(lblLocal1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(351, 351, 351)
                .addComponent(btnCorregir)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel35, lblLocal, lblLocal1, lblLocal3, lblLocal4});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtComentario, txtConcepto, txtImporte, txtMotivo, txtSubConcepto});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel11, jdcFechaGasto});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lblTituloTiendaCorregir, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jdcFechaGasto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblLocal)
                            .addComponent(jLabel35))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtConcepto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblLocal1)
                            .addComponent(lblLocal3)
                            .addComponent(lblLocal4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSubConcepto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtComentario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtImporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(77, 77, 77)
                .addComponent(btnCorregir, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(110, 110, 110))
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

    private void txtSubConceptoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSubConceptoKeyTyped
//        char key = evt.getKeyChar();
//        if (((key < '0') || (key > '9')) && (key != evt.VK_BACK_SPACE)
//                && (key != '.')) {
//            evt.consume();
//        }
//        if (key == '.' && txtSubConcepto.getText().contains(".")) {
//            evt.consume();
//            JOptionPane.showMessageDialog(this, "Solo se permite un punto "
//                    + "este campo", "Validacion decimal", 0);
//        }
    }//GEN-LAST:event_txtSubConceptoKeyTyped

    private void txtMotivoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMotivoKeyTyped
//        char key = evt.getKeyChar();
//        if (((key < '0') || (key > '9')) && (key != evt.VK_BACK_SPACE)
//                && (key != '.')) {
//            evt.consume();
//        }
//        if (key == '.' && txtMotivo.getText().contains(".")) {
//            evt.consume();
//            JOptionPane.showMessageDialog(this, "Solo se permite un punto "
//                    + "este campo", "Validacion decimal", 0);
//        }
    }//GEN-LAST:event_txtMotivoKeyTyped

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
        if (!txtSubConcepto.getText().isEmpty()
                && !txtMotivo.getText().isEmpty()
                && !txtConcepto.getText().isEmpty()
                && !txtComentario.getText().isEmpty()
                && !txtImporte.getText().isEmpty()) {
            AccesoBD acceso = new AccesoBD();
            String fecha = FechaHerramienta.formatoYMD(jdcFechaGasto.getDate());
            gastoGeneral.setFecha(FechaHerramienta.convertirStringEnDate(fecha));
            gastoGeneral.setConcepto(txtConcepto.getText());
            gastoGeneral.setSubconcepto(txtSubConcepto.getText());
            gastoGeneral.setAcreedor(txtMotivo.getText());
            gastoGeneral.setImporte(Double.parseDouble(txtImporte.getText()));
            gastoGeneral.setComentario(txtComentario.getText());
            acceso.Update(gastoGeneral);
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCorregir;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JPanel jPanel1;
    private com.toedter.calendar.JDateChooser jdcFechaGasto;
    private javax.swing.JLabel lblLocal;
    private javax.swing.JLabel lblLocal1;
    private javax.swing.JLabel lblLocal3;
    private javax.swing.JLabel lblLocal4;
    private javax.swing.JLabel lblTituloTiendaCorregir;
    private javax.swing.JTextField txtComentario;
    private javax.swing.JTextField txtConcepto;
    private javax.swing.JTextField txtImporte;
    private javax.swing.JTextField txtMotivo;
    private javax.swing.JTextField txtSubConcepto;
    // End of variables declaration//GEN-END:variables
}
