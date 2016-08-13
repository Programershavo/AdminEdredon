/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import controlBD.DAOUniversalHibernate;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import pojos.Proveedores;
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

        jpLocales = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtLocal = new javax.swing.JTable();
        txtLocalNuevo = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel113 = new javax.swing.JLabel();
        jLabel114 = new javax.swing.JLabel();
        jLabel115 = new javax.swing.JLabel();
        jLabel116 = new javax.swing.JLabel();
        jLabel117 = new javax.swing.JLabel();
        jLabel112 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setTitle("Catálogo de Tiendas");

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
        jtLocal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtLocalKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(jtLocal);

        txtLocalNuevo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtLocalNuevoKeyPressed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosGenerales/tienda.png"))); // NOI18N

        jLabel113.setFont(new java.awt.Font("Comic Sans MS", 1, 10)); // NOI18N
        jLabel113.setForeground(new java.awt.Color(102, 102, 102));
        jLabel113.setText("TEXTO + ENTER = NUEVO");

        jLabel114.setFont(new java.awt.Font("Comic Sans MS", 1, 10)); // NOI18N
        jLabel114.setForeground(new java.awt.Color(102, 102, 102));
        jLabel114.setText("TABLA + ENTER = CAMBIAR");

        jLabel115.setFont(new java.awt.Font("Comic Sans MS", 1, 10)); // NOI18N
        jLabel115.setForeground(new java.awt.Color(102, 102, 102));
        jLabel115.setText("TABLA + SUPR   = BORRAR");

        jLabel116.setFont(new java.awt.Font("Comic Sans MS", 1, 10)); // NOI18N
        jLabel116.setForeground(new java.awt.Color(102, 102, 102));
        jLabel116.setText("<---------------------");

        jLabel117.setFont(new java.awt.Font("Comic Sans MS", 1, 10)); // NOI18N
        jLabel117.setForeground(new java.awt.Color(102, 102, 102));
        jLabel117.setText("<---------------------");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel114, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel115, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel116, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel117, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel113, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel9)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel113)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel117)
                .addGap(51, 51, 51)
                .addComponent(jLabel114)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel115)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel116)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 110, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addGap(25, 25, 25))
        );

        jLabel112.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel112.setForeground(new java.awt.Color(102, 102, 102));
        jLabel112.setText("Tiendas");

        javax.swing.GroupLayout jpLocalesLayout = new javax.swing.GroupLayout(jpLocales);
        jpLocales.setLayout(jpLocalesLayout);
        jpLocalesLayout.setHorizontalGroup(
            jpLocalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpLocalesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpLocalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel112, javax.swing.GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)
                    .addGroup(jpLocalesLayout.createSequentialGroup()
                        .addGroup(jpLocalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)
                            .addComponent(txtLocalNuevo))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpLocalesLayout.setVerticalGroup(
            jpLocalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpLocalesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel112)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtLocalNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpLocales, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpLocales, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void llenarTabla(JTable jtTabla, String nombreTabla, String consulta) {
        DAOUniversalHibernate acceso = new DAOUniversalHibernate();
        jtTabla.setVisible(false);
        jtTabla.removeAll();
        //_________________________ NOMBRE DE LA TABLA, CONSULTA
        jtTabla.setModel(acceso.retornaModelo(nombreTabla, consulta));
        jtTabla.setVisible(true);
    }    private void limpiarCampos() {

        txtLocalNuevo.setText("");

    }

    private void cargaTabla(final JTable jtTabla, String HQL, String Encabezado, int NoColOcultar) {
        //Reviso si que la consulta no vaya vacia
        if (!HQL.isEmpty()) {
            DAOUniversalHibernate acceso = new DAOUniversalHibernate();
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

    private void jpLocalesComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jpLocalesComponentShown
        String query = "";
        query = "From Sucursal s ORDER BY s.idSucursal";
        llenarTabla(jtLocal, "Sucursal", query);
    }//GEN-LAST:event_jpLocalesComponentShown

    private void txtLocalNuevoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLocalNuevoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            DAOUniversalHibernate control = new DAOUniversalHibernate();
            try {
                if (!txtLocalNuevo.getText().isEmpty()) {
                    pojos.Sucursal local = new pojos.Sucursal();
                    local.setNombre(txtLocalNuevo.getText());
                    control.add(local);

                    limpiarCampos();
                    String Query = "";
                    Query = "FROM Sucursal s ORDER BY s.nombre";
                    cargaTabla(jtLocal, Query, "Sucursal", 1);
                } else {
                    JOptionPane.showMessageDialog(this, "Favor de llenar el campo Nombre", "Datos incompletos", 1);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e, "Error", 0);
            }
        }
    }//GEN-LAST:event_txtLocalNuevoKeyPressed

    private void jtLocalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtLocalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (jtLocal.getSelectedRow() != -1) {
                int respuesta = JOptionPane.showConfirmDialog(this, "¿Desea editar este local?", "Confirmar editar", 0, 3);
                if (respuesta == 0) {
                    try {
                        DAOUniversalHibernate acceso = new DAOUniversalHibernate();
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
        }
//----------------------------------------------------------------------->
        if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
            if (jtLocal.getSelectedRow() != -1) {
                int respuesta = JOptionPane.showConfirmDialog(this, "¿Desea eliminar este local?", "Confirmar eliminación", 0, 3);
                if (respuesta == 0) {
                    try {
                        DAOUniversalHibernate acceso = new DAOUniversalHibernate();
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
        }
    }//GEN-LAST:event_jtLocalKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel116;
    private javax.swing.JLabel jLabel117;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel jpLocales;
    private javax.swing.JTable jtLocal;
    private javax.swing.JTextField txtLocalNuevo;
    // End of variables declaration//GEN-END:variables
}
