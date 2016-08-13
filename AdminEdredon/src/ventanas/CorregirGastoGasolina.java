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
import pojos.Gastospersonales;
import pojos.Linea;
import pojos.Proveedores;
import pojos.Sucursal;
import pojos.Vehiculo;

/**
 *
 * @author Daniel
 */
public class CorregirGastoGasolina extends javax.swing.JInternalFrame {

    private Gastogasolina gastoGasolina;

    public CorregirGastoGasolina(String clave) {
        initComponents();
        cargaDatos(clave);
        jdcGastoGasolina.setDate(new Date());
        seteaDatos();
        String query = "FROM Vehiculo gl ORDER BY gl.vehiculo";
        llenarCombo(cmbVehiculo, query, "vehiculo", true);
    }

    private void llenarCombo(JComboBox combo, String query, String tipoLista, boolean todas) {
        DAOUniversalHibernate acceso = new DAOUniversalHibernate();
        combo.removeAllItems();
        if (acceso.select(query) != null) {

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
                            case "vehiculo":
                                Vehiculo vehiculo = (Vehiculo) listaItems.get(i);
                                combo.addItem(vehiculo.getVehiculo());
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
//        Reviso si que la consulta no vaya vacia
        if (!clave.isEmpty()) {
            DAOUniversalHibernate acceso = new DAOUniversalHibernate();
            gastoGasolina = new Gastogasolina();
            String HQL = "From Gastogasolina gl WHERE gl.idGastoGasolina = '" + clave + "'";
            gastoGasolina = (Gastogasolina) acceso.select(HQL).get(0);
        }
    }

    private void seteaDatos() {
        txtComentario.setText(gastoGasolina.getComentario());
        txtImporte.setText(gastoGasolina.getImporte().toString());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnCorregirConcepto = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jdcGastoGasolina = new com.toedter.calendar.JDateChooser();
        txtComentario = new javax.swing.JTextField();
        txtImporte = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cmbVehiculo = new javax.swing.JComboBox();

        setClosable(true);
        setTitle("Correciones de gastos en gasolina");

        jPanel1.setBackground(new java.awt.Color(240, 79, 90));

        jLabel1.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Corregir gasto de gasolina");

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

        jLabel7.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 102, 153));
        jLabel7.setText("COMENTARIOS");

        jLabel12.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setText("IMPORTE");

        txtComentario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtComentarioKeyTyped(evt);
            }
        });

        txtImporte.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtImporteKeyTyped(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 102, 153));
        jLabel2.setText("VEHICULO");

        cmbVehiculo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jdcGastoGasolina, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                            .addComponent(cmbVehiculo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 95, Short.MAX_VALUE)
                            .addComponent(txtComentario))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtImporte, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(20, 20, 20))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(btnCorregirConcepto, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(305, 305, 305))))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jdcGastoGasolina, txtComentario, txtImporte});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel12, jLabel2, jLabel4, jLabel7});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jdcGastoGasolina, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbVehiculo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtComentario, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtImporte, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(btnCorregirConcepto, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
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
        gastoGasolina.setVehiculo(cmbVehiculo.getSelectedItem().toString());
        String fecha = FechaHerramienta.formatoYMD(jdcGastoGasolina.getDate());
        gastoGasolina.setFecha(FechaHerramienta.convertirStringEnDate(fecha));
        gastoGasolina.setComentario(txtComentario.getText());
        gastoGasolina.setImporte(Double.parseDouble(txtImporte.getText()));
        acceso.Update(gastoGasolina);
        try {
            this.setClosed(true);
            System.gc();
        } catch (PropertyVetoException ex) {
            this.dispose();
            System.gc();
        }

    }//GEN-LAST:event_btnCorregirConceptoActionPerformed

    private void txtComentarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtComentarioKeyTyped

    }//GEN-LAST:event_txtComentarioKeyTyped

    private void txtImporteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtImporteKeyTyped
        char key = evt.getKeyChar();
        if (((key < '0') || (key > '9')) && (key != KeyEvent.VK_BACK_SPACE)
                && (key != '.')) {
            evt.consume();
        }
        if (key == '.' && txtImporte.getText().contains(".")) {
            evt.consume();
            JOptionPane.showMessageDialog(this, "Solo se permite un punto "
                    + "este campo", "Validacion decimal", 0);
            // TODO add your handling code here:
        }
    }//GEN-LAST:event_txtImporteKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCorregirConcepto;
    private javax.swing.JComboBox cmbVehiculo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private com.toedter.calendar.JDateChooser jdcGastoGasolina;
    private javax.swing.JTextField txtComentario;
    private javax.swing.JTextField txtImporte;
    // End of variables declaration//GEN-END:variables
}
