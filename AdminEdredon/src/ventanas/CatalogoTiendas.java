/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import controlBD.AccesoBD;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import pojos.Sucursal;

/**
 *
 * @author Daniel
 */
public class CatalogoTiendas extends javax.swing.JInternalFrame {

    String HQL = "";

    /**
     * Creates new form CatalogoTiendas
     */
    public CatalogoTiendas() {
        initComponents();
        String query = "";
        query = "From Sucursal s ORDER BY s.idSucursal";
        llenarTabla(jtLocal, "Sucursal", query);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jpLocales = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtLocal = new javax.swing.JTable();
        jLabel48 = new javax.swing.JLabel();
        btnCorregirConcepto1 = new javax.swing.JButton();
        btnEliminarLocal = new javax.swing.JButton();
        jLabel86 = new javax.swing.JLabel();
        btnNuevoLocal = new javax.swing.JButton();
        txtLocalNuevo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setTitle("Catálogo de Tiendas");

        jPanel1.setBackground(new java.awt.Color(240, 79, 90));

        jLabel1.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Cátalogo de Tiendas");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(558, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        jpLocales.setBackground(new java.awt.Color(255, 255, 255));
        jpLocales.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jpLocalesComponentShown(evt);
            }
        });

        jtLocal.setModel(new javax.swing.table.DefaultTableModel(
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
        jtLocal.setOpaque(false);
        jScrollPane2.setViewportView(jtLocal);

        jLabel48.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(0, 102, 153));
        jLabel48.setText("MIS LOCALES");

        btnCorregirConcepto1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnCorregirConcepto1.setForeground(new java.awt.Color(0, 153, 51));
        btnCorregirConcepto1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/correct.png"))); // NOI18N
        btnCorregirConcepto1.setText("Corregir");
        btnCorregirConcepto1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCorregirConcepto1ActionPerformed(evt);
            }
        });

        btnEliminarLocal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnEliminarLocal.setForeground(new java.awt.Color(0, 153, 51));
        btnEliminarLocal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/delete.png"))); // NOI18N
        btnEliminarLocal.setText("Eliminar");
        btnEliminarLocal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnEliminarLocal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarLocalActionPerformed(evt);
            }
        });

        jLabel86.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel86.setForeground(new java.awt.Color(0, 102, 153));
        jLabel86.setText("ADMINISTRAS MIS LOCALES");

        btnNuevoLocal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnNuevoLocal.setForeground(new java.awt.Color(0, 153, 51));
        btnNuevoLocal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/New.png"))); // NOI18N
        btnNuevoLocal.setText("Nuevo");
        btnNuevoLocal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoLocalActionPerformed(evt);
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/shopcart.png"))); // NOI18N

        javax.swing.GroupLayout jpLocalesLayout = new javax.swing.GroupLayout(jpLocales);
        jpLocales.setLayout(jpLocalesLayout);
        jpLocalesLayout.setHorizontalGroup(
            jpLocalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpLocalesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpLocalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpLocalesLayout.createSequentialGroup()
                        .addGroup(jpLocalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jpLocalesLayout.createSequentialGroup()
                                .addComponent(btnEliminarLocal, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(48, 48, 48)
                                .addComponent(jLabel86, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnCorregirConcepto1))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpLocalesLayout.createSequentialGroup()
                                .addComponent(txtLocalNuevo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnNuevoLocal))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpLocalesLayout.setVerticalGroup(
            jpLocalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpLocalesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel48)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpLocalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnNuevoLocal, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLocalNuevo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpLocalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpLocalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCorregirConcepto1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminarLocal, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel86))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpLocales, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpLocales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCorregirConcepto1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCorregirConcepto1ActionPerformed
        if (jtLocal.getSelectedRow() != -1) {
            int respuesta = JOptionPane.showConfirmDialog(this, "¿Desea editar este local?", "Confirmar editar", 0, 3);
            if (respuesta == 0) {
                try {
                    AccesoBD acceso = new AccesoBD();
                    int clave = (int) jtLocal.getValueAt(jtLocal.getSelectedRow(), 0);
                    String query = "From Sucursal s WHERE s.idSucursal = '" + clave + "'";
                    Sucursal concepto = (Sucursal) acceso.select(query).get(0);
                    String nombre = JOptionPane.showInputDialog(this, "Nuevo nombre");
                    concepto.setNombre(nombre);
                    acceso.Update(concepto);
                    query = "From Sucursal s ORDER BY s.idSucursal";
                    llenarTabla(jtLocal, "Sucursal", query);
                    JOptionPane.showMessageDialog(this, "Sucursal editada correctamente", "Cancelado", 1);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Ha ocurrido un error: " + e, "Error", 0);
                    System.gc();
                }
            }
        }
    }//GEN-LAST:event_btnCorregirConcepto1ActionPerformed
    private void llenarTabla(JTable jtTabla, String nombreTabla, String consulta) {
        AccesoBD acceso = new AccesoBD();
        jtTabla.setVisible(false);
        jtTabla.removeAll();
        //_________________________ NOMBRE DE LA TABLA, CONSULTA
        jtTabla.setModel(acceso.retornaModelo(nombreTabla, consulta));
        jtTabla.setVisible(true);
    }
    private void btnEliminarLocalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarLocalActionPerformed
        if (jtLocal.getSelectedRow() != -1) {
            int respuesta = JOptionPane.showConfirmDialog(this, "¿Desea eliminar este local?", "Confirmar eliminación", 0, 3);
            if (respuesta == 0) {
                try {
                    AccesoBD acceso = new AccesoBD();
                    int clave = (int) jtLocal.getValueAt(jtLocal.getSelectedRow(), 0);
                    String query = "From Sucursal s WHERE s.idSucursal = '" + clave + "'";
                    Sucursal concepto = (Sucursal) acceso.select(query).get(0);
                    acceso.delete(concepto);
                    query = "From Sucursal s";
                    llenarTabla(jtLocal, "Sucursal", query);
                    JOptionPane.showMessageDialog(this, "Sucursal eliminada correctamente", "Cancelado", 1);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Ha ocurrido un error: " + e, "Error", 0);
                    System.gc();
                }
            }
        }
    }//GEN-LAST:event_btnEliminarLocalActionPerformed
    private void limpiarCampos() {

        txtLocalNuevo.setText("");

    }

    private void cargaTabla(final JTable jtTabla, String HQL, String Encabezado, int NoColOcultar) {
        //Reviso si que la consulta no vaya vacia
        if (!HQL.isEmpty()) {
            AccesoBD acceso = new AccesoBD();
            jtTabla.setVisible(false);
            jtTabla.removeAll();
            jtTabla.setModel(acceso.retornaModelo(Encabezado, HQL));
            TableColumn column = null;
            for (int i = 0; i < NoColOcultar; i++) {
                column = jtTabla.getColumnModel().getColumn(i);
                column.setMaxWidth(10);
            }
            jtTabla.setVisible(true);
        }
    }

    private void btnNuevoLocalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoLocalActionPerformed
        AccesoBD control = new AccesoBD();
        try {
            if (!txtLocalNuevo.getText().isEmpty()) {
                pojos.Sucursal local = new pojos.Sucursal();
                local.setNombre(txtLocalNuevo.getText());
                control.add(local);

                limpiarCampos();
                JOptionPane.showMessageDialog(this, "Local registrado correctamente", "Datos registrados", 1);
                String Query = "";
                Query = "FROM Sucursal s ORDER BY s.nombre";
                cargaTabla(jtLocal, Query, "Sucursal", 1);
            } else {
                JOptionPane.showMessageDialog(this, "Favor de llenar el campo Nombre", "Datos incompletos", 1);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e, "Error", 0);
        }
    }//GEN-LAST:event_btnNuevoLocalActionPerformed

    private void jpLocalesComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jpLocalesComponentShown
        String query = "";
        query = "From Sucursal s ORDER BY s.idSucursal";
        llenarTabla(jtLocal, "Sucursal", query);
    }//GEN-LAST:event_jpLocalesComponentShown


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCorregirConcepto1;
    private javax.swing.JButton btnEliminarLocal;
    private javax.swing.JButton btnNuevoLocal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel jpLocales;
    private javax.swing.JTable jtLocal;
    private javax.swing.JTextField txtLocalNuevo;
    // End of variables declaration//GEN-END:variables
}
