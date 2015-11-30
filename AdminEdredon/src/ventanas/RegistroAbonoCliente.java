/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import controlBD.AccesoBD;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.util.Date;
import javax.swing.JOptionPane;
import pojos.Venta;

/**
 *
 * @author Daniel
 */
public class RegistroAbonoCliente extends javax.swing.JInternalFrame {

    private Venta venta;

    public RegistroAbonoCliente(String clave) {
        initComponents();
        cargaDatos(clave);
        seteaDatos();
    }

    private void cargaDatos(String clave) {
        //Reviso si que la consulta no vaya vacia
        if (!clave.isEmpty()) {
            AccesoBD acceso = new AccesoBD();
            venta = new Venta();
            String HQL = "From Venta v WHERE v.idVenta = '" + clave + "'";
            venta = (Venta) acceso.select(HQL).get(0);
        }
    }

    private void seteaDatos() {
        lblNota.setText(venta.getNota());
        lblNombreCliente.setText(venta.getNombreCliente());
        lblFechaVenta.setText(venta.getFechaVenta().toString());

        if (venta.getFechaVentaSaldada() != null) {
            lblFechaUltimoAbono.setText(venta.getFechaUltimoAbono().toString());
        } else {
            lblFechaUltimoAbono.setText("");
        }
        lblDescripcion.setText(venta.getDescripcion());
        lblMetodoPago.setText(venta.getMetodoPago());
        lblEstatusCompra.setText(venta.getEstatus());
        lblImporte.setText(venta.getImporte().toString());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnAbonarConcepto = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        lblNota = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblNombreCliente = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblFechaVenta = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblFechaUltimoAbono = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblDescripcion = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblCantidadAbono = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblMetodoPago = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lblEstatusCompra = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lblImporte = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel13 = new javax.swing.JLabel();
        txtAbono = new javax.swing.JTextField();

        setClosable(true);
        setTitle("Saldo del cliente");

        jPanel1.setBackground(new java.awt.Color(240, 79, 90));

        jLabel1.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Abono");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 0, 0));
        jLabel2.setText("Nota");

        btnAbonarConcepto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAbonarConcepto.setForeground(new java.awt.Color(0, 153, 51));
        btnAbonarConcepto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/apply.png"))); // NOI18N
        btnAbonarConcepto.setText("Abonar");
        btnAbonarConcepto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbonarConceptoActionPerformed(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(153, 0, 0));
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/Cancel.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        lblNota.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lblNota.setForeground(new java.awt.Color(255, 51, 51));
        lblNota.setText("Cliente");

        jLabel3.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 102, 153));
        jLabel3.setText("Cliente");

        lblNombreCliente.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lblNombreCliente.setForeground(new java.awt.Color(0, 102, 153));
        lblNombreCliente.setText("Cliente");

        jLabel4.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 102, 153));
        jLabel4.setText("Fecha de venta");

        lblFechaVenta.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lblFechaVenta.setForeground(new java.awt.Color(0, 102, 153));
        lblFechaVenta.setText("Cliente");

        jLabel6.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 102, 153));
        jLabel6.setText("Fecha del último abono");

        lblFechaUltimoAbono.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lblFechaUltimoAbono.setForeground(new java.awt.Color(0, 102, 153));
        lblFechaUltimoAbono.setText("Cliente");

        jLabel7.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 102, 153));
        jLabel7.setText("Descripción");

        lblDescripcion.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lblDescripcion.setForeground(new java.awt.Color(0, 102, 153));
        lblDescripcion.setText("Cliente");

        jLabel8.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 102, 153));
        jLabel8.setText("Abono");

        lblCantidadAbono.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lblCantidadAbono.setForeground(new java.awt.Color(0, 102, 153));
        lblCantidadAbono.setText("Cliente");

        jLabel10.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 102, 153));
        jLabel10.setText("Forma de pago");

        lblMetodoPago.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lblMetodoPago.setForeground(new java.awt.Color(0, 102, 153));
        lblMetodoPago.setText("Cliente");

        jLabel11.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 102, 153));
        jLabel11.setText("Estatus de la compra");

        lblEstatusCompra.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lblEstatusCompra.setForeground(new java.awt.Color(0, 102, 153));
        lblEstatusCompra.setText("Cliente");

        jLabel12.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setText("Importe");

        lblImporte.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        lblImporte.setForeground(new java.awt.Color(51, 51, 51));
        lblImporte.setText("Cliente");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel13.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 102, 51));
        jLabel13.setText("Nuevo Abono");

        txtAbono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAbonoKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnAbonarConcepto, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelar))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                        .addComponent(lblMetodoPago, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblEstatusCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblImporte, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblFechaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblFechaUltimoAbono, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblCantidadAbono, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(lblNota, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(txtAbono)
                                .addGap(139, 139, 139)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel10, jLabel11, jLabel12, jLabel2, jLabel3, jLabel4, jLabel6, jLabel7, jLabel8});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {lblCantidadAbono, lblDescripcion, lblEstatusCompra, lblFechaUltimoAbono, lblFechaVenta, lblImporte, lblMetodoPago, lblNombreCliente, lblNota});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel2)
                            .addComponent(lblNota))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel3)
                            .addComponent(lblNombreCliente))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel4)
                            .addComponent(lblFechaVenta))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel6)
                            .addComponent(lblFechaUltimoAbono))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel7)
                            .addComponent(lblDescripcion))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel8)
                            .addComponent(lblCantidadAbono))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel10)
                            .addComponent(lblMetodoPago))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel11)
                            .addComponent(lblEstatusCompra))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel12)
                            .addComponent(lblImporte))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(txtAbono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAbonarConcepto, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAbonarConceptoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbonarConceptoActionPerformed
        if (!txtAbono.getText().isEmpty() || !txtAbono.getText().equals("")) {
            AccesoBD acceso = new AccesoBD();

            if (venta.getAbono() != null) {
                double abonoAnterior = venta.getAbono();
                double abonoNuevo = Double.parseDouble(txtAbono.getText());
                double abonoSuma = abonoAnterior + abonoNuevo;
                if (abonoSuma > venta.getImporte()) {
                    JOptionPane.showMessageDialog(rootPane, "El abono excede el importe por: " + (abonoSuma - venta.getImporte()));
                } else {
                    if (abonoSuma >= venta.getImporte()) {
                        venta.setEstatus("Saldada");
                    }
                    venta.setAbono(abonoSuma);
                    venta.setFechaUltimoAbono(new Date());
                    try {
                        this.setClosed(true);
                        System.gc();
                    } catch (PropertyVetoException ex) {
                        this.dispose();
                        System.gc();
                    }
                }

            } else {
                venta.setAbono(Double.parseDouble(txtAbono.getText()));
                venta.setFechaUltimoAbono(new Date());
                try {
                    this.setClosed(true);
                    System.gc();
                } catch (PropertyVetoException ex) {
                    this.dispose();
                    System.gc();
                }
            }
            acceso.Update(venta);
        }

    }//GEN-LAST:event_btnAbonarConceptoActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        try {
            this.setClosed(true);
            System.gc();
        } catch (PropertyVetoException ex) {
            this.dispose();
            System.gc();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtAbonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAbonoKeyTyped
        char key = evt.getKeyChar();
        if (((key < '0') || (key > '9')) && (key != KeyEvent.VK_BACK_SPACE)
                && (key != '.')) {
            evt.consume();
        }
        if (key == '.' && txtAbono.getText().contains(".")) {
            evt.consume();
            JOptionPane.showMessageDialog(this, "Solo se permite un punto "
                    + "este campo", "Validacion decimal", 0);
            // TODO add your handling code here:
        }        // TODO add your handling code here:
    }//GEN-LAST:event_txtAbonoKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbonarConcepto;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblCantidadAbono;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblEstatusCompra;
    private javax.swing.JLabel lblFechaUltimoAbono;
    private javax.swing.JLabel lblFechaVenta;
    private javax.swing.JLabel lblImporte;
    private javax.swing.JLabel lblMetodoPago;
    private javax.swing.JLabel lblNombreCliente;
    private javax.swing.JLabel lblNota;
    private javax.swing.JTextField txtAbono;
    // End of variables declaration//GEN-END:variables
}
