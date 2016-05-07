/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import controlBD.AccesoBD;
import java.beans.PropertyVetoException;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import pojos.Clientes;
import pojos.Linea;
import pojos.Proveedores;
import pojos.Sucursal;
import pojos.Vehiculo;

/**
 *
 * @author Daniel
 */
public class CorregirVehiculo extends javax.swing.JInternalFrame {

    private Vehiculo vehiculo;

    public CorregirVehiculo(String clave) {
        initComponents();
        cargaDatos(clave);
        seteaDatos();
    }


    private void cargaDatos(String clave) {
//        Reviso si que la consulta no vaya vacia
        if (!clave.isEmpty()) {
            AccesoBD acceso = new AccesoBD();
            vehiculo = new Vehiculo();
            String HQL = "From Vehiculo gl WHERE gl.idvehiculo = '" + clave + "'";
            vehiculo = (Vehiculo) acceso.select(HQL).get(0);
        }
    }

    private void seteaDatos() {
        txtvehiculo.setText(vehiculo.getVehiculo());
        txtDuenio.setText(vehiculo.getDuenio());
        txtColor.setText(vehiculo.getColor());
        txtAnio.setText(vehiculo.getAnio());
        txtPlacas.setText(vehiculo.getPlacas());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnCorregirConcepto = new javax.swing.JButton();
        jLabel91 = new javax.swing.JLabel();
        txtvehiculo = new javax.swing.JTextField();
        jLabel103 = new javax.swing.JLabel();
        txtDuenio = new javax.swing.JTextField();
        jLabel104 = new javax.swing.JLabel();
        txtColor = new javax.swing.JTextField();
        jLabel105 = new javax.swing.JLabel();
        txtAnio = new javax.swing.JTextField();
        jLabel106 = new javax.swing.JLabel();
        txtPlacas = new javax.swing.JTextField();

        setClosable(true);
        setTitle("Corregir datos del vehiculo");

        jPanel1.setBackground(new java.awt.Color(240, 79, 90));

        jLabel1.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Corregir datos del vehiculo");

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

        jLabel91.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel91.setForeground(new java.awt.Color(0, 102, 153));
        jLabel91.setText("VEHICULO");

        jLabel103.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel103.setForeground(new java.awt.Color(0, 102, 153));
        jLabel103.setText("DUEÑO");

        jLabel104.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel104.setForeground(new java.awt.Color(0, 102, 153));
        jLabel104.setText("COLOR");

        jLabel105.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel105.setForeground(new java.awt.Color(0, 102, 153));
        jLabel105.setText("AÑO");

        jLabel106.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel106.setForeground(new java.awt.Color(0, 102, 153));
        jLabel106.setText("PLACAS");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(btnCorregirConcepto, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(305, 305, 305))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel91, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtvehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel103, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDuenio, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel104, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtColor, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel105, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAnio, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtPlacas)
                            .addComponent(jLabel106, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34))))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel103, jLabel104, jLabel105, jLabel106, jLabel91, txtAnio, txtColor, txtDuenio, txtPlacas, txtvehiculo});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel91)
                    .addComponent(jLabel103)
                    .addComponent(jLabel104)
                    .addComponent(jLabel105)
                    .addComponent(jLabel106))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(txtvehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDuenio, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtColor, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAnio, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPlacas, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                .addComponent(btnCorregirConcepto, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtAnio, txtColor, txtDuenio, txtPlacas, txtvehiculo});

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

        AccesoBD acceso = new AccesoBD();
        vehiculo.setVehiculo(txtvehiculo.getText());
        vehiculo.setDuenio(txtDuenio.getText());
        vehiculo.setColor(txtColor.getText());
        vehiculo.setAnio(txtAnio.getText());
        vehiculo.setPlacas(txtPlacas.getText());
        acceso.Update(vehiculo);
        try {
            this.setClosed(true);
            System.gc();
        } catch (PropertyVetoException ex) {
            this.dispose();
            System.gc();
        }

    }//GEN-LAST:event_btnCorregirConceptoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCorregirConcepto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField txtAnio;
    private javax.swing.JTextField txtColor;
    private javax.swing.JTextField txtDuenio;
    private javax.swing.JTextField txtPlacas;
    private javax.swing.JTextField txtvehiculo;
    // End of variables declaration//GEN-END:variables
}
