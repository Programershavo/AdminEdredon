/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import controlBD.DAOUniversalHibernate;
import herramienta.FechaHerramienta;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.util.Date;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import pojos.Clientes;
import pojos.Gastogasolina;
import pojos.Gastosfinancieros;
import pojos.Gastospersonales;
import pojos.Linea;
import pojos.Proveedores;
import pojos.Sucursal;
import pojos.Vehiculo;

/**
 *
 * @author Daniel
 */
public class CorregirGastoFinanciero extends javax.swing.JInternalFrame {

    private Gastosfinancieros gastoFinanciero;

    public CorregirGastoFinanciero(String clave) {
        initComponents();
        cargaDatos(clave);
        jdcGastoGasolina.setDate(new Date());
        seteaDatos();
    }
    
    private void cargaDatos(String clave) {
//        Reviso si que la consulta no vaya vacia
        if (!clave.isEmpty()) {
            DAOUniversalHibernate acceso = new DAOUniversalHibernate();
            gastoFinanciero = new Gastosfinancieros();
            String HQL = "From Gastosfinancieros gl WHERE gl.idGastosFinancieros = '" + clave + "'";
            gastoFinanciero = (Gastosfinancieros) acceso.select(HQL).get(0);
        }
    }

    private void seteaDatos() {
        txtImporteSueldoPrestamos.setText(gastoFinanciero.getImporte().toString());
        txtComentariosSueldosPrestamos.setText(gastoFinanciero.getComentarios());
        txtEmpleado.setText(gastoFinanciero.getAcreedor());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnCorregirConcepto = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jdcGastoGasolina = new com.toedter.calendar.JDateChooser();
        jLabel70 = new javax.swing.JLabel();
        cmbConceptoSueldoPrestamo = new javax.swing.JComboBox();
        jLabel71 = new javax.swing.JLabel();
        txtImporteSueldoPrestamos = new javax.swing.JTextField();
        jLabel72 = new javax.swing.JLabel();
        txtComentariosSueldosPrestamos = new javax.swing.JTextField();
        txtEmpleado = new javax.swing.JTextField();
        jLabel73 = new javax.swing.JLabel();

        setClosable(true);
        setTitle("Correciones de gastos financieros");

        jPanel1.setBackground(new java.awt.Color(240, 79, 90));

        jLabel1.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Corregir gasto");

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

        btnCorregirConcepto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnCorregirConcepto.setForeground(new java.awt.Color(0, 153, 51));
        btnCorregirConcepto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/correct.png"))); // NOI18N
        btnCorregirConcepto.setText("Corregir");
        btnCorregirConcepto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCorregirConceptoActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 102, 153));
        jLabel4.setText("FECHA");

        jLabel70.setBackground(new java.awt.Color(255, 255, 255));
        jLabel70.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(0, 102, 153));
        jLabel70.setText("CONCEPTO");
        jLabel70.setOpaque(true);

        cmbConceptoSueldoPrestamo.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        cmbConceptoSueldoPrestamo.setForeground(new java.awt.Color(0, 102, 153));
        cmbConceptoSueldoPrestamo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "NOMINA", "PRESTAMO", "SUELDO", "DEPOSITO", " " }));

        jLabel71.setBackground(new java.awt.Color(255, 255, 255));
        jLabel71.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel71.setForeground(new java.awt.Color(0, 102, 153));
        jLabel71.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel71.setText("IMPORTE");
        jLabel71.setOpaque(true);

        txtImporteSueldoPrestamos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtImporteSueldoPrestamosKeyTyped(evt);
            }
        });

        jLabel72.setBackground(new java.awt.Color(255, 255, 255));
        jLabel72.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel72.setForeground(new java.awt.Color(0, 102, 153));
        jLabel72.setText("COMENTARIOS");
        jLabel72.setOpaque(true);

        txtEmpleado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEmpleadoKeyTyped(evt);
            }
        });

        jLabel73.setBackground(new java.awt.Color(255, 255, 255));
        jLabel73.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel73.setForeground(new java.awt.Color(0, 102, 153));
        jLabel73.setText("ACREEDOR");
        jLabel73.setOpaque(true);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                    .addComponent(jdcGastoGasolina, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(cmbConceptoSueldoPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel70, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel71, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtImporteSueldoPrestamos, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel72)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel73, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtComentariosSueldosPrestamos, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(398, 398, 398)
                .addComponent(btnCorregirConcepto, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cmbConceptoSueldoPrestamo, jLabel4, jLabel70, jLabel71, jLabel72, txtComentariosSueldosPrestamos, txtImporteSueldoPrestamos});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel71)
                    .addComponent(jLabel4)
                    .addComponent(jLabel72)
                    .addComponent(jLabel73))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(cmbConceptoSueldoPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtImporteSueldoPrestamos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtComentariosSueldosPrestamos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jdcGastoGasolina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(btnCorregirConcepto, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(58, Short.MAX_VALUE))
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

    private void btnCorregirConceptoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCorregirConceptoActionPerformed

        DAOUniversalHibernate acceso = new DAOUniversalHibernate();
        gastoFinanciero.setConcepto(cmbConceptoSueldoPrestamo.getSelectedItem().toString());
        String fecha = FechaHerramienta.formatoYMD(jdcGastoGasolina.getDate());
        gastoFinanciero.setFecha(FechaHerramienta.convertirStringEnDate(fecha));
        gastoFinanciero.setComentarios(txtComentariosSueldosPrestamos.getText());
        gastoFinanciero.setConcepto(cmbConceptoSueldoPrestamo.getSelectedItem().toString());
        gastoFinanciero.setAcreedor(txtEmpleado.getText());
        gastoFinanciero.setImporte(Double.parseDouble(txtImporteSueldoPrestamos.getText()));
        acceso.Update(gastoFinanciero);
        try {
            this.setClosed(true);
            System.gc();
        } catch (PropertyVetoException ex) {
            this.dispose();
            System.gc();
        }

    }//GEN-LAST:event_btnCorregirConceptoActionPerformed

    private void txtImporteSueldoPrestamosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtImporteSueldoPrestamosKeyTyped
        char key = evt.getKeyChar();
        if (((key < '0') || (key > '9')) && (key != evt.VK_BACK_SPACE)
            && (key != '.')) {
            evt.consume();
        }
        if (key == '.' && txtImporteSueldoPrestamos.getText().contains(".")) {
            evt.consume();
            JOptionPane.showMessageDialog(this, "Solo se permite un punto "
                + "este campo", "Validacion decimal", 0);
        }
    }//GEN-LAST:event_txtImporteSueldoPrestamosKeyTyped

    private void txtEmpleadoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmpleadoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmpleadoKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCorregirConcepto;
    private javax.swing.JComboBox cmbConceptoSueldoPrestamo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private com.toedter.calendar.JDateChooser jdcGastoGasolina;
    private javax.swing.JTextField txtComentariosSueldosPrestamos;
    private javax.swing.JTextField txtEmpleado;
    private javax.swing.JTextField txtImporteSueldoPrestamos;
    // End of variables declaration//GEN-END:variables
}
