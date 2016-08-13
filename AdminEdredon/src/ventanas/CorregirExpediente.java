/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import controlBD.DAOUniversalHibernate;
import herramienta.FechaHerramienta;
import java.beans.PropertyVetoException;
import javax.swing.JOptionPane;
import pojos.Expediente;

/**
 *
 * @author Daniel
 */
public class CorregirExpediente extends javax.swing.JInternalFrame {

    Expediente expediente;

    public CorregirExpediente(String clave) {
        initComponents();
        cargaDatos(clave);
        seteaDatos();
    }

    private void cargaDatos(String clave) {
        //Reviso si que la consulta no vaya vacia
        if (!clave.isEmpty()) {
            DAOUniversalHibernate acceso = new DAOUniversalHibernate();
            expediente = new Expediente();
            String query = "FROM Expediente e WHERE e.idProveedor = '" + clave + "'";
            expediente = (Expediente) acceso.select(query).get(0);
        }
    }

    private void seteaDatos() {
        lblTituloTiendaCorregir.setText("Corregir expediente del proveedor " + expediente.getNombreProveedor());
        jdcFechaGasto.setDate(expediente.getFechaApertura());
//        txtFolio.setText(expediente.getFolio());
//        txtImporte.setText(String.valueOf(expediente.getImporteGeneral()));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblTituloTiendaCorregir = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        txtFolio = new javax.swing.JTextField();
        lblLocal4 = new javax.swing.JLabel();
        txtImporte = new javax.swing.JTextField();
        btnCorregir = new javax.swing.JButton();
        jdcFechaGasto = new com.toedter.calendar.JDateChooser();
        jLabel11 = new javax.swing.JLabel();

        setTitle("Corregir Expediente");
        setToolTipText("Corregir Diario de Caja");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lblTituloTiendaCorregir.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        lblTituloTiendaCorregir.setForeground(new java.awt.Color(0, 102, 153));
        lblTituloTiendaCorregir.setText("Corregir expediente del proveedor");

        jLabel35.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(0, 102, 153));
        jLabel35.setText("FOLIO");

        txtFolio.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        txtFolio.setForeground(new java.awt.Color(204, 0, 0));
        txtFolio.setHorizontalAlignment(javax.swing.JTextField.CENTER);

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
                        .addGap(351, 351, 351)
                        .addComponent(btnCorregir))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(165, 165, 165)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jdcFechaGasto, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFolio, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblLocal4, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtImporte, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addComponent(lblTituloTiendaCorregir, javax.swing.GroupLayout.PREFERRED_SIZE, 514, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(167, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel35, lblLocal4});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtFolio, txtImporte});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel11, jdcFechaGasto});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lblTituloTiendaCorregir, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel35)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFolio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblLocal4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtImporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jdcFechaGasto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 104, Short.MAX_VALUE)
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
        if (!txtFolio.getText().isEmpty()
                && !txtImporte.getText().isEmpty()) {
            DAOUniversalHibernate acceso = new DAOUniversalHibernate();
            String fecha = FechaHerramienta.formatoYMD(jdcFechaGasto.getDate());
            expediente.setFechaApertura(FechaHerramienta.convertirStringEnDate(fecha));
            expediente.setNombreProveedor(fecha);
            expediente.setIdProveedor(WIDTH);
//            expediente.setFolio(txtFolio.getText());
//            expediente.setImporteGeneral(Double.parseDouble(txtImporte.getText()));
            acceso.Update(expediente);
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
    private javax.swing.JLabel lblLocal4;
    private javax.swing.JLabel lblTituloTiendaCorregir;
    private javax.swing.JTextField txtFolio;
    private javax.swing.JTextField txtImporte;
    // End of variables declaration//GEN-END:variables
}
