/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import controlBD.AccesoBD;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;
import java.util.Date;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import pojos.Mantenimiento;

/**
 *
 * @author Daniel
 */
public class CPanel extends javax.swing.JFrame {

    public Image imagen = new ImageIcon(getClass().getResource("/IconosEspecializados/iconCastillo.png")).getImage();

    /**
     * Creates new form CPanel
     */
    public CPanel() {
        initComponents();
        jmiGastosPersonales.setEnabled(true);
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setTitle("Colchas Castillo");
        this.setIconImage(imagen);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                mensajeSalir();
            }
        });

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpPrincipal = new javax.swing.JPanel();
        desktop = new JCDesktopPane.JCDesktopPane();
        menuBar = new javax.swing.JMenuBar();
        mnuPrograma = new javax.swing.JMenu();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        mnuProgramaCerrarSesion = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        mnuProgramaSalir = new javax.swing.JMenuItem();
        mnuProveedores = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();
        jmiDiarioDeCaja = new javax.swing.JMenuItem();
        jmiGastosPersonales = new javax.swing.JMenuItem();
        jmiResumen = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        jMenu3 = new javax.swing.JMenu();
        mnuProveedoresEliminarEditarProveedor = new javax.swing.JMenuItem();
        jmiCliente = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        jMenu4 = new javax.swing.JMenu();
        jmiProvedores = new javax.swing.JMenuItem();
        jmiClientes = new javax.swing.JMenuItem();
        jmiTiendas = new javax.swing.JMenuItem();
        jmiGastos = new javax.swing.JMenuItem();
        jmiVehiculos = new javax.swing.JMenuItem();
        jmiMtoAutos = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jmiEstadoDeResultados = new javax.swing.JMenuItem();
        mnuAcercaDe = new javax.swing.JMenu();
        mnuAcercaDeSalesTeDispatcher = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jpPrincipal.setBackground(new java.awt.Color(255, 255, 255));

        desktop.setBackground(new java.awt.Color(255, 255, 255));
        desktop.setImagenDeFondo(new javax.swing.ImageIcon(getClass().getResource("/IconosEspecializados/castilloLogo.png"))); // NOI18N
        desktop.setOpaque(false);

        javax.swing.GroupLayout jpPrincipalLayout = new javax.swing.GroupLayout(jpPrincipal);
        jpPrincipal.setLayout(jpPrincipalLayout);
        jpPrincipalLayout.setHorizontalGroup(
            jpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpPrincipalLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(desktop, javax.swing.GroupLayout.DEFAULT_SIZE, 910, Short.MAX_VALUE))
        );
        jpPrincipalLayout.setVerticalGroup(
            jpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktop, javax.swing.GroupLayout.DEFAULT_SIZE, 652, Short.MAX_VALUE)
        );

        menuBar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        mnuPrograma.setText("Programa");
        mnuPrograma.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        mnuPrograma.add(jSeparator2);
        mnuPrograma.add(jSeparator6);

        mnuProgramaCerrarSesion.setText("Cerrar sesion");
        mnuProgramaCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuProgramaCerrarSesionActionPerformed(evt);
            }
        });
        mnuPrograma.add(mnuProgramaCerrarSesion);
        mnuPrograma.add(jSeparator1);

        mnuProgramaSalir.setText("Salir");
        mnuProgramaSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuProgramaSalirActionPerformed(evt);
            }
        });
        mnuPrograma.add(mnuProgramaSalir);

        menuBar.add(mnuPrograma);

        mnuProveedores.setMnemonic('h');
        mnuProveedores.setText("Registros");
        mnuProveedores.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jMenu1.setText("Notas de venta/Gastos");

        jmiDiarioDeCaja.setText("Notas de venta");
        jmiDiarioDeCaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiDiarioDeCajaActionPerformed(evt);
            }
        });
        jMenu1.add(jmiDiarioDeCaja);

        jmiGastosPersonales.setMnemonic('c');
        jmiGastosPersonales.setText("Gastos");
        jmiGastosPersonales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiGastosPersonalesActionPerformed(evt);
            }
        });
        jMenu1.add(jmiGastosPersonales);

        jmiResumen.setText("Resumen general");
        jmiResumen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiResumenActionPerformed(evt);
            }
        });
        jMenu1.add(jmiResumen);

        mnuProveedores.add(jMenu1);
        mnuProveedores.add(jSeparator5);

        jMenu3.setText("Expedientes");

        mnuProveedoresEliminarEditarProveedor.setMnemonic('a');
        mnuProveedoresEliminarEditarProveedor.setText("Compras a proveedores");
        mnuProveedoresEliminarEditarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuProveedoresEliminarEditarProveedorActionPerformed(evt);
            }
        });
        jMenu3.add(mnuProveedoresEliminarEditarProveedor);

        jmiCliente.setText("Pagos de clientes");
        jmiCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiClienteActionPerformed(evt);
            }
        });
        jMenu3.add(jmiCliente);

        mnuProveedores.add(jMenu3);
        mnuProveedores.add(jSeparator3);

        jMenu4.setText("Conceptos");

        jmiProvedores.setText("Proveedores");
        jmiProvedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiProvedoresActionPerformed(evt);
            }
        });
        jMenu4.add(jmiProvedores);

        jmiClientes.setText("Clientes");
        jmiClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiClientesActionPerformed(evt);
            }
        });
        jMenu4.add(jmiClientes);

        jmiTiendas.setText("Tiendas");
        jmiTiendas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiTiendasActionPerformed(evt);
            }
        });
        jMenu4.add(jmiTiendas);

        jmiGastos.setText("Gastos");
        jmiGastos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiGastosActionPerformed(evt);
            }
        });
        jMenu4.add(jmiGastos);

        jmiVehiculos.setText("Vehiculos");
        jmiVehiculos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiVehiculosActionPerformed(evt);
            }
        });
        jMenu4.add(jmiVehiculos);

        jmiMtoAutos.setText("Mantenimiento de autos");
        jmiMtoAutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiMtoAutosActionPerformed(evt);
            }
        });
        jMenu4.add(jmiMtoAutos);

        mnuProveedores.add(jMenu4);

        menuBar.add(mnuProveedores);

        jMenu2.setText("Informe");

        jmiEstadoDeResultados.setText("Estado de Resultados");
        jmiEstadoDeResultados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiEstadoDeResultadosActionPerformed(evt);
            }
        });
        jMenu2.add(jmiEstadoDeResultados);

        menuBar.add(jMenu2);

        mnuAcercaDe.setText("Acerca de...");
        mnuAcercaDe.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        mnuAcercaDeSalesTeDispatcher.setText("AdminEdredon");
        mnuAcercaDeSalesTeDispatcher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuAcercaDeSalesTeDispatcherActionPerformed(evt);
            }
        });
        mnuAcercaDe.add(mnuAcercaDeSalesTeDispatcher);

        menuBar.add(mnuAcercaDe);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void mensajeSalir() {
        int res = JOptionPane.showConfirmDialog(this, "¿Estas seguro que quieres cerrar el programa?", "Salir", 0);
        if (res == 0) {
            Icon icono = new ImageIcon(getClass().getResource("/IconosEspecializados/castilloLogo.png"));
            JOptionPane.showMessageDialog(this, "Colchas Castillo te desea" + "\n" + "un excelente día.", "Gracias por tu trabajo", JOptionPane.PLAIN_MESSAGE, icono);
            System.exit(0);
        }
    }

    public boolean exist(JInternalFrame frame) throws Exception {
        try {
            JInternalFrame iframes[] = desktop.getAllFrames();
            for (int i = 0; i < iframes.length; i++) {
                if (iframes[i].getTitle().equals(frame.getTitle())) {
                    iframes[i].moveToFront();
                    iframes[i].setSelected(true);
                    iframes[i].setLocation((desktop.getWidth() - iframes[i].getWidth()) / 2,
                            (desktop.getHeight() - iframes[i].getHeight()) / 2);
                    return true;
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return false;
    }
    private void mnuAcercaDeSalesTeDispatcherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuAcercaDeSalesTeDispatcherActionPerformed
        try {
            AcercaDe ad = new AcercaDe();
            if (exist(ad) == false) {
                desktop.add(ad);
                ad.setVisible(true);
                ad.setLocation((desktop.getWidth() - ad.getWidth()) / 2,
                        (desktop.getHeight() - ad.getHeight()) / 2);
            } else {
                ad.dispose();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ha ocurrido un error: " + e, "Error", 0);
        }
    }//GEN-LAST:event_mnuAcercaDeSalesTeDispatcherActionPerformed

    private void jmiEstadoDeResultadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiEstadoDeResultadosActionPerformed
        try {
            EstadoDeResultados edoResultados = new EstadoDeResultados();
            if (exist(edoResultados) == false) {
                desktop.add(edoResultados);
                edoResultados.setVisible(true);
                edoResultados.setLocation((desktop.getWidth() - edoResultados.getWidth()) / 2, (desktop.getHeight() - edoResultados.getHeight()) / 2);
            } else {
                edoResultados.dispose();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ha ocurrido un error: " + e, "Error", 0);
        }
    }//GEN-LAST:event_jmiEstadoDeResultadosActionPerformed

    private void jmiProvedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiProvedoresActionPerformed
        try {
            CatalogoProveedores registroProveedores = new CatalogoProveedores();
            if (exist(registroProveedores) == false) {
                desktop.add(registroProveedores);
                registroProveedores.setVisible(true);
                registroProveedores.setLocation((desktop.getWidth() - registroProveedores.getWidth()) / 2, (desktop.getHeight() - registroProveedores.getHeight()) / 2);
            } else {
                registroProveedores.dispose();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ha ocurrido un error: " + e, "Error", 0);
        }
    }//GEN-LAST:event_jmiProvedoresActionPerformed

    private void jmiClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiClienteActionPerformed
//        try {
//            RegistroCliente registroCliente = new RegistroCliente();
//            if (exist(registroCliente) == false) {
//                desktop.add(registroCliente);
//                registroCliente.setVisible(true);
//                registroCliente.setLocation((desktop.getWidth() - registroCliente.getWidth()) / 2,
//                    (desktop.getHeight() - registroCliente.getHeight()) / 2);
//            } else {
//                registroCliente.dispose();
//            }
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(this, "Ha ocurrido un error: " + e, "Error", 0);
//        }
    }//GEN-LAST:event_jmiClienteActionPerformed

    private void mnuProveedoresEliminarEditarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuProveedoresEliminarEditarProveedorActionPerformed
        try {
            ExpedienteProveedor pagosProveedor = new ExpedienteProveedor();
            if (exist(pagosProveedor) == false) {
                desktop.add(pagosProveedor);
                pagosProveedor.setVisible(true);
                pagosProveedor.setLocation((desktop.getWidth() - pagosProveedor.getWidth()) / 2, (desktop.getHeight() - pagosProveedor.getHeight()) / 2);
            } else {
                pagosProveedor.dispose();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ha ocurrido un error: " + e, "Error", 0);
        }
    }//GEN-LAST:event_mnuProveedoresEliminarEditarProveedorActionPerformed

    private void jmiResumenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiResumenActionPerformed
        try {
            VentasGastosTotales resumenVentasGastos = new VentasGastosTotales();
            if (exist(resumenVentasGastos) == false) {
                desktop.add(resumenVentasGastos);
                resumenVentasGastos.setVisible(true);
                resumenVentasGastos.setLocation((desktop.getWidth() - resumenVentasGastos.getWidth()) / 2, (desktop.getHeight() - resumenVentasGastos.getHeight()) / 2);
            } else {
                resumenVentasGastos.dispose();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ha ocurrido un error: " + e, "Error", 0);
        }
    }//GEN-LAST:event_jmiResumenActionPerformed
    private int consultarVentasDeDiarioPorMes() {
        String consultaVentasHQL = "FROM Diariocaja d WHERE "
                + "d.fecha = '"
                + herramienta.FechaHerramienta.formatoYMD(new Date())
                + "'";
        AccesoBD acceso = new AccesoBD();
        return acceso.rowCount(consultaVentasHQL);
    }
    private void jmiGastosPersonalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiGastosPersonalesActionPerformed
        //Fecha actual desglosada:
//        Calendar fecha = Calendar.getInstance();
//        int año = fecha.get(Calendar.YEAR);
//        int mes = fecha.get(Calendar.MONTH) + 1;
//        if (consultarVentasDeDiarioPorMes() > 0) {
        try {
            DiarioDeGasto registroGasto = new DiarioDeGasto();
            if (exist(registroGasto) == false) {
                desktop.add(registroGasto);
                registroGasto.setVisible(true);
                registroGasto.setLocation((desktop.getWidth() - registroGasto.getWidth()) / 2, (desktop.getHeight() - registroGasto.getHeight()) / 2);
            } else {
                registroGasto.dispose();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ha ocurrido un error: " + e,
                    "Error", 0);
        }
//        } else {
//            JOptionPane.showMessageDialog(this, "Para registrar cualquier gasto:"
//                    + "\n-Debe haber una venta con fecha del dia de hoy: ", "Aviso", 1);
//        }

    }//GEN-LAST:event_jmiGastosPersonalesActionPerformed

    private void jmiDiarioDeCajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiDiarioDeCajaActionPerformed
        try {
            DiarioDeCaja diarioDeCaja = new DiarioDeCaja();
            if (exist(diarioDeCaja) == false) {
                desktop.add(diarioDeCaja);
                diarioDeCaja.setVisible(true);
                diarioDeCaja.setLocation((desktop.getWidth() - diarioDeCaja.getWidth()) / 2,
                        (desktop.getHeight() - diarioDeCaja.getHeight()) / 2);
            } else {
                diarioDeCaja.dispose();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ha ocurrido un error: " + e, "Error", 0);
        }
    }//GEN-LAST:event_jmiDiarioDeCajaActionPerformed

    private void mnuProgramaSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuProgramaSalirActionPerformed
        mensajeSalir();
    }//GEN-LAST:event_mnuProgramaSalirActionPerformed

    private void mnuProgramaCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuProgramaCerrarSesionActionPerformed
        Icon icono = new ImageIcon(getClass().getResource("/IconosEspecializados/iconCastillo.png"));
        JOptionPane.showMessageDialog(this, "AdminEdredon te desea" + "\n" + "Que tengas un excelente día.", "Gracias por tu trabajo", JOptionPane.PLAIN_MESSAGE, icono);
        Login log = new Login();
        log.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_mnuProgramaCerrarSesionActionPerformed

    private void jmiClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiClientesActionPerformed
        try {
            CatalogoClientes registroClientes = new CatalogoClientes();
            if (exist(registroClientes) == false) {
                desktop.add(registroClientes);
                registroClientes.setVisible(true);
                registroClientes.setLocation((desktop.getWidth() - registroClientes.getWidth()) / 2, (desktop.getHeight() - registroClientes.getHeight()) / 2);
            } else {
                registroClientes.dispose();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ha ocurrido un error: " + e, "Error", 0);
        }
    }//GEN-LAST:event_jmiClientesActionPerformed

    private void jmiGastosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiGastosActionPerformed
        try {
            CatalogoGastos registroGastos = new CatalogoGastos();
            if (exist(registroGastos) == false) {
                desktop.add(registroGastos);
                registroGastos.setVisible(true);
                registroGastos.setLocation((desktop.getWidth() - registroGastos.getWidth()) / 2, (desktop.getHeight() - registroGastos.getHeight()) / 2);
            } else {
                registroGastos.dispose();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ha ocurrido un error: " + e, "Error", 0);
        }
    }//GEN-LAST:event_jmiGastosActionPerformed

    private void jmiTiendasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiTiendasActionPerformed
        try {
            CatalogoTiendas registroTiendas = new CatalogoTiendas();
            if (exist(registroTiendas) == false) {
                desktop.add(registroTiendas);
                registroTiendas.setVisible(true);
                registroTiendas.setLocation((desktop.getWidth() - registroTiendas.getWidth()) / 2, (desktop.getHeight() - registroTiendas.getHeight()) / 2);
            } else {
                registroTiendas.dispose();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ha ocurrido un error: " + e, "Error", 0);
        }
    }//GEN-LAST:event_jmiTiendasActionPerformed

    private void jmiVehiculosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiVehiculosActionPerformed
        try {
            CatalogoVehiculos registroVehiculos = new CatalogoVehiculos();
            if (exist(registroVehiculos) == false) {
                desktop.add(registroVehiculos);
                registroVehiculos.setVisible(true);
                registroVehiculos.setLocation((desktop.getWidth() - registroVehiculos.getWidth()) / 2, (desktop.getHeight() - registroVehiculos.getHeight()) / 2);
            } else {
                registroVehiculos.dispose();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ha ocurrido un error: " + e, "Error", 0);
        }
    }//GEN-LAST:event_jmiVehiculosActionPerformed

    private void jmiMtoAutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiMtoAutosActionPerformed
        try {
            CatalogoMtoVehicular registroMantenimiento = new CatalogoMtoVehicular();
            if (exist(registroMantenimiento) == false) {
                desktop.add(registroMantenimiento);
                registroMantenimiento.setVisible(true);
                registroMantenimiento.setLocation((desktop.getWidth() - registroMantenimiento.getWidth()) / 2, (desktop.getHeight() - registroMantenimiento.getHeight()) / 2);
            } else {
                registroMantenimiento.dispose();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ha ocurrido un error: " + e, "Error", 0);
        }
    }//GEN-LAST:event_jmiMtoAutosActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CPanel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static JCDesktopPane.JCDesktopPane desktop;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JMenuItem jmiCliente;
    private javax.swing.JMenuItem jmiClientes;
    private javax.swing.JMenuItem jmiDiarioDeCaja;
    private javax.swing.JMenuItem jmiEstadoDeResultados;
    private javax.swing.JMenuItem jmiGastos;
    private javax.swing.JMenuItem jmiGastosPersonales;
    private javax.swing.JMenuItem jmiMtoAutos;
    private javax.swing.JMenuItem jmiProvedores;
    private javax.swing.JMenuItem jmiResumen;
    private javax.swing.JMenuItem jmiTiendas;
    private javax.swing.JMenuItem jmiVehiculos;
    private javax.swing.JPanel jpPrincipal;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu mnuAcercaDe;
    private javax.swing.JMenuItem mnuAcercaDeSalesTeDispatcher;
    private javax.swing.JMenu mnuPrograma;
    private javax.swing.JMenuItem mnuProgramaCerrarSesion;
    private javax.swing.JMenuItem mnuProgramaSalir;
    private javax.swing.JMenu mnuProveedores;
    private javax.swing.JMenuItem mnuProveedoresEliminarEditarProveedor;
    // End of variables declaration//GEN-END:variables

}
