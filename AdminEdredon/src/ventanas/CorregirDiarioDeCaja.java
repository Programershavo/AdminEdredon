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

/**
 *
 * @author Daniel
 */
public class CorregirDiarioDeCaja extends javax.swing.JInternalFrame {

    Diariocaja diarioDeCaja;

    public CorregirDiarioDeCaja(String clave) {
        initComponents();
        cargaDatos(clave);
        seteaDatos();
    }

    private void cargaDatos(String clave) {
        //Reviso si que la consulta no vaya vacia
        if (!clave.isEmpty()) {
            AccesoBD acceso = new AccesoBD();
            diarioDeCaja = new Diariocaja();
            String HQL = "FROM Diariocaja d WHERE d.idDiarioCaja = '" + clave + "'";
            diarioDeCaja = (Diariocaja) acceso.select(HQL).get(0);
        }
    }

    private void seteaDatos() {
        lblTituloTiendaCorregir.setText("Diario de Caja de " + diarioDeCaja.getLocal());
        jdcFechaCredito.setDate(diarioDeCaja.getFecha());
        txtNotas.setText(diarioDeCaja.getNotas());
        txtVentaConNota.setText(String.valueOf(diarioDeCaja.getVentaConNota()));
        txtVentaSinNota.setText(String.valueOf(diarioDeCaja.getVentaSinNota()));
        txtCredito.setText(String.valueOf(diarioDeCaja.getAbonoCredito()));
        txtGastosDeVenta.setText(String.valueOf(diarioDeCaja.getGastos()));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblTituloTiendaCorregir = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        txtNotas = new javax.swing.JTextField();
        lblLocal = new javax.swing.JLabel();
        txtVentaConNota = new javax.swing.JTextField();
        lblLocal1 = new javax.swing.JLabel();
        txtVentaSinNota = new javax.swing.JTextField();
        lblLocal3 = new javax.swing.JLabel();
        txtCredito = new javax.swing.JTextField();
        lblLocal4 = new javax.swing.JLabel();
        txtGastosDeVenta = new javax.swing.JTextField();
        btnCorregir = new javax.swing.JButton();
        jdcFechaCredito = new com.toedter.calendar.JDateChooser();
        jLabel11 = new javax.swing.JLabel();

        setToolTipText("Corregir Diario de Caja");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lblTituloTiendaCorregir.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        lblTituloTiendaCorregir.setForeground(new java.awt.Color(0, 102, 153));
        lblTituloTiendaCorregir.setText("Diario de Caja de");

        jLabel35.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(0, 102, 153));
        jLabel35.setText("NOTAS");

        txtNotas.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        txtNotas.setForeground(new java.awt.Color(204, 0, 0));
        txtNotas.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        lblLocal.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lblLocal.setForeground(new java.awt.Color(0, 102, 153));
        lblLocal.setText("VTAS CON NOTA");

        txtVentaConNota.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        txtVentaConNota.setForeground(new java.awt.Color(204, 0, 0));
        txtVentaConNota.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtVentaConNota.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtVentaConNotaKeyTyped(evt);
            }
        });

        lblLocal1.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lblLocal1.setForeground(new java.awt.Color(0, 102, 153));
        lblLocal1.setText("VTAS SIN NOTA");

        txtVentaSinNota.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        txtVentaSinNota.setForeground(new java.awt.Color(204, 0, 0));
        txtVentaSinNota.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtVentaSinNota.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtVentaSinNotaKeyTyped(evt);
            }
        });

        lblLocal3.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lblLocal3.setForeground(new java.awt.Color(0, 102, 153));
        lblLocal3.setText("AB. CREDITO");

        txtCredito.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        txtCredito.setForeground(new java.awt.Color(204, 0, 0));
        txtCredito.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCredito.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCreditoKeyTyped(evt);
            }
        });

        lblLocal4.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lblLocal4.setForeground(new java.awt.Color(0, 102, 153));
        lblLocal4.setText("GASTOS DE VENTA");

        txtGastosDeVenta.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        txtGastosDeVenta.setForeground(new java.awt.Color(204, 0, 0));
        txtGastosDeVenta.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtGastosDeVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtGastosDeVentaKeyTyped(evt);
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
                            .addComponent(txtNotas, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblLocal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 165, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblLocal3, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblLocal4, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtGastosDeVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(328, 328, 328)
                                .addComponent(txtVentaSinNota, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(169, 169, 169)
                                .addComponent(txtVentaConNota, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.CENTER, jPanel1Layout.createSequentialGroup()
                                .addGap(241, 241, 241)
                                .addComponent(lblTituloTiendaCorregir, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(328, 328, 328)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                        .addComponent(jdcFechaCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtCredito, txtGastosDeVenta, txtNotas, txtVentaConNota, txtVentaSinNota});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel11, jdcFechaCredito});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lblTituloTiendaCorregir, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jdcFechaCredito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblLocal)
                            .addComponent(jLabel35))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNotas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblLocal1)
                            .addComponent(lblLocal3)
                            .addComponent(lblLocal4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtVentaSinNota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtVentaConNota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCredito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtGastosDeVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
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

    private void txtVentaConNotaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtVentaConNotaKeyTyped
        char key = evt.getKeyChar();
        if (((key < '0') || (key > '9')) && (key != evt.VK_BACK_SPACE)
                && (key != '.')) {
            evt.consume();
        }
        if (key == '.' && txtVentaConNota.getText().contains(".")) {
            evt.consume();
            JOptionPane.showMessageDialog(this, "Solo se permite un punto "
                    + "este campo", "Validacion decimal", 0);
        }
    }//GEN-LAST:event_txtVentaConNotaKeyTyped

    private void txtVentaSinNotaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtVentaSinNotaKeyTyped
        char key = evt.getKeyChar();
        if (((key < '0') || (key > '9')) && (key != evt.VK_BACK_SPACE)
                && (key != '.')) {
            evt.consume();
        }
        if (key == '.' && txtVentaSinNota.getText().contains(".")) {
            evt.consume();
            JOptionPane.showMessageDialog(this, "Solo se permite un punto "
                    + "este campo", "Validacion decimal", 0);
        }
    }//GEN-LAST:event_txtVentaSinNotaKeyTyped

    private void txtCreditoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCreditoKeyTyped
        char key = evt.getKeyChar();
        if (((key < '0') || (key > '9')) && (key != evt.VK_BACK_SPACE)
                && (key != '.')) {
            evt.consume();
        }
        if (key == '.' && txtCredito.getText().contains(".")) {
            evt.consume();
            JOptionPane.showMessageDialog(this, "Solo se permite un punto "
                    + "este campo", "Validacion decimal", 0);
        }
    }//GEN-LAST:event_txtCreditoKeyTyped

    private void txtGastosDeVentaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGastosDeVentaKeyTyped
        char key = evt.getKeyChar();
        if (((key < '0') || (key > '9')) && (key != evt.VK_BACK_SPACE)
                && (key != '.')) {
            evt.consume();
        }
        if (key == '.' && txtGastosDeVenta.getText().contains(".")) {
            evt.consume();
            JOptionPane.showMessageDialog(this, "Solo se permite un punto "
                    + "este campo", "Validacion decimal", 0);
        }
    }//GEN-LAST:event_txtGastosDeVentaKeyTyped

    private void btnCorregirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCorregirActionPerformed
        if (!txtVentaConNota.getText().isEmpty()
                && !txtVentaSinNota.getText().isEmpty()
                && !txtNotas.getText().isEmpty()
                && !txtCredito.getText().isEmpty()
                && !txtGastosDeVenta.getText().isEmpty()) {
            AccesoBD acceso = new AccesoBD();
            diarioDeCaja.setNotas(txtNotas.getText());
            String fecha = FechaHerramienta.formatoYMD(jdcFechaCredito.getDate());
            diarioDeCaja.setFecha(FechaHerramienta.convertirStringEnDate(fecha));
            diarioDeCaja.setVentaConNota(Double.parseDouble(txtVentaConNota.getText()));
            diarioDeCaja.setVentaSinNota(Double.parseDouble(txtVentaSinNota.getText()));
            diarioDeCaja.setAbonoCredito(Double.parseDouble(txtCredito.getText()));
            diarioDeCaja.setGastos(Double.parseDouble(txtGastosDeVenta.getText()));
            acceso.Update(diarioDeCaja);
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
    private com.toedter.calendar.JDateChooser jdcFechaCredito;
    private javax.swing.JLabel lblLocal;
    private javax.swing.JLabel lblLocal1;
    private javax.swing.JLabel lblLocal3;
    private javax.swing.JLabel lblLocal4;
    private javax.swing.JLabel lblTituloTiendaCorregir;
    private javax.swing.JTextField txtCredito;
    private javax.swing.JTextField txtGastosDeVenta;
    private javax.swing.JTextField txtNotas;
    private javax.swing.JTextField txtVentaConNota;
    private javax.swing.JTextField txtVentaSinNota;
    // End of variables declaration//GEN-END:variables
}
