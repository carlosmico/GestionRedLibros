/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Utilidades.*;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.SwingWorker;

/**
 *
 * @author Jose Sanchis
 */
public class Main extends javax.swing.JFrame {

    FrameDevoluciones frameDevoluciones;
    FrameEntrega frameEntrega;
    FrameLibro frameLibro;
    FrameOpciones frameOpciones;
    FramePopup frameCarga;
    FramePopup framePopup;
    FrameAyuda frameAyuda;

    public static GestorSesiones gestorSesiones;

    private boolean existeConexion;

    /**
     * Creates new form Main
     */
    public Main() throws IOException {
        initComponents();

        //Centramos la pestaña al centro de la pantalla
        //(Es irrelevante ya que la maximizamos)
        setLocationRelativeTo(null);

        //Maximizamos la pestaña
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        //Comprobamos la conexión al servidor
        //Configuramos la imagen de fondo de la pantalla principal
        //<editor-fold defaultstate="collapsed" desc="Set the wallpaper image">
        String icono = "";
        BufferedImage img = null;
        icono = "../Imagenes/wallpaper.jpg";
        try {
            img = ImageIO.read(new File(icono));
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        Image image = img.getScaledInstance(DimensionesFrame.width, DimensionesFrame.height, Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(image);
        wallpaper.setIcon(imageIcon);
        banner.setVisible(true);
//</editor-fold>

        //Carga de configuración inicial
        compruebaConexionBD(true, "Main");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        banner = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        flatButton1 = new com.mommoo.flat.button.FlatButton();
        jPanel1 = new javax.swing.JPanel();
        btnLibros = new com.mommoo.flat.button.FlatButton();
        btnEntrega = new com.mommoo.flat.button.FlatButton();
        btnDevoluciones = new com.mommoo.flat.button.FlatButton();
        btnAydua = new com.mommoo.flat.button.FlatButton();
        btnOpciones = new com.mommoo.flat.button.FlatButton();
        wallpaper = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gestión Red Libros");
        setMinimumSize(new java.awt.Dimension(960, 0));

        banner.setBackground(new java.awt.Color(58, 39, 35));
        banner.setLayout(new java.awt.GridLayout(2, 1));

        jPanel2.setBackground(Colores.accent);

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Gestión Red Libros");

        flatButton1.setBackground(Colores.buttons);
        flatButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons/help.png"))); // NOI18N
        flatButton1.setCornerRound(10);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 622, Short.MAX_VALUE)
                .addGap(91, 91, 91)
                .addComponent(flatButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(flatButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE))
                .addContainerGap())
        );

        banner.add(jPanel2);

        jPanel1.setBackground(new java.awt.Color(58, 39, 35));
        jPanel1.setPreferredSize(new java.awt.Dimension(500, 37));
        jPanel1.setLayout(new java.awt.GridLayout(1, 4, 5, 0));

        btnLibros.setBackground(new java.awt.Color(66, 47, 44));
        btnLibros.setForeground(new java.awt.Color(204, 204, 204));
        btnLibros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons/book.png"))); // NOI18N
        btnLibros.setText("Libros");
        btnLibros.setCornerRound(10);
        btnLibros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLibrosActionPerformed(evt);
            }
        });
        jPanel1.add(btnLibros);

        btnEntrega.setBackground(new java.awt.Color(66, 47, 44));
        btnEntrega.setForeground(new java.awt.Color(204, 204, 204));
        btnEntrega.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons/clipboard-arrow-up.png"))); // NOI18N
        btnEntrega.setText("Entregas");
        btnEntrega.setCornerRound(10);
        btnEntrega.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntregaActionPerformed(evt);
            }
        });
        jPanel1.add(btnEntrega);

        btnDevoluciones.setBackground(new java.awt.Color(66, 47, 44));
        btnDevoluciones.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(0, 0, 0)));
        btnDevoluciones.setForeground(new java.awt.Color(204, 204, 204));
        btnDevoluciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons/clipboard-arrow-down.png"))); // NOI18N
        btnDevoluciones.setText("Devoluciones");
        btnDevoluciones.setCornerRound(10);
        btnDevoluciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDevolucionesActionPerformed(evt);
            }
        });
        jPanel1.add(btnDevoluciones);

        btnAydua.setBackground(new java.awt.Color(66, 47, 44));
        btnAydua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons/calendar-search.png"))); // NOI18N
        btnAydua.setText("Historial");
        btnAydua.setCornerRound(10);
        btnAydua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAyduaActionPerformed(evt);
            }
        });
        jPanel1.add(btnAydua);

        btnOpciones.setBackground(new java.awt.Color(66, 47, 44));
        btnOpciones.setForeground(new java.awt.Color(204, 204, 204));
        btnOpciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons/ballot.png"))); // NOI18N
        btnOpciones.setText("Opciones");
        btnOpciones.setCornerRound(10);
        btnOpciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpcionesActionPerformed(evt);
            }
        });
        jPanel1.add(btnOpciones);

        banner.add(jPanel1);

        wallpaper.setMinimumSize(new java.awt.Dimension(1, 1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(wallpaper, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(banner, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(banner, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(wallpaper, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     *
     * Este botón lo utilizamos para mostrar la pestaña de Devoluciones
     */
    private void btnDevolucionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDevolucionesActionPerformed
        //Acción del botón de 'Devoluciones'

        if (existeConexion) {
            if (frameDevoluciones == null) {
                //Si no existe la ventana la creamos
                frameDevoluciones = new FrameDevoluciones();
            } else {
                if (!frameDevoluciones.isVisible()) {
                    //Si existe la ventana, y la hemos cerrado
                    //limpiamos la variable y creamos una ventana nueva
                    frameDevoluciones = null;
                    frameDevoluciones = new FrameDevoluciones();
                } else {
                    //Si la ventana esta abierta y queremos abrir otra
                    //creamos una nueva ventana
                    frameDevoluciones = new FrameDevoluciones();
                }
            }
        }else{
            compruebaConexionBD(false, "FrameDevoluciones");
        }
    }//GEN-LAST:event_btnDevolucionesActionPerformed

    /**
     *
     * Este botón lo utilizamos para mostrar la pestaña de Entregas
     */
    private void btnEntregaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntregaActionPerformed
        if (existeConexion) {
            if (frameEntrega == null) {
                frameEntrega = new FrameEntrega();
            } else {
                if (!frameEntrega.isVisible()) {
                    frameEntrega.alumno = null;
                    frameEntrega = null;
                    frameEntrega = new FrameEntrega();
                } else {
                    frameEntrega = new FrameEntrega();
                }
            }
            frameEntrega.setVisible(true);
        } else {
            compruebaConexionBD(false, "FrameEntrega");
        }
    }//GEN-LAST:event_btnEntregaActionPerformed

    /**
     *
     * Este botón lo utilizamos para mostrar la pestaña de Opciones
     */
    private void btnOpcionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpcionesActionPerformed
        if (existeConexion) {
            if (frameOpciones == null) {
                frameOpciones = new FrameOpciones();
            } else {
                if (!frameOpciones.isVisible()) {
                    frameOpciones = null;
                    frameOpciones = new FrameOpciones();
                }
            }
            frameOpciones.setVisible(true);
        }
    }//GEN-LAST:event_btnOpcionesActionPerformed

    /**
     *
     * Este botón lo utilizamos para mostrar la pestaña de Libros
     */
    private void btnLibrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLibrosActionPerformed
        if (existeConexion) {
            if (frameLibro == null) {
                frameLibro = new FrameLibro();
            } else {
                if (!frameLibro.isVisible()) {
                    frameLibro = null;
                    frameLibro = new FrameLibro();
                } else {
                    frameLibro = new FrameLibro();
                }
            }
            frameLibro.setVisible(true);
        }else{
            compruebaConexionBD(false, "FrameLibro");
        }
    }//GEN-LAST:event_btnLibrosActionPerformed

    /**
     *
     * Este botón lo utilizamos para mostrar la pestaña de Ayuda
     */
    private void btnAyduaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAyduaActionPerformed
        //compruebaConexionBD();
        if (frameAyuda == null) {
            frameAyuda = new FrameAyuda();
        } else {
            if (!frameAyuda.isVisible()) {
                frameAyuda = null;
                frameAyuda = new FrameAyuda();
            } else {
                frameAyuda = new FrameAyuda();
            }
        }
        frameAyuda.setVisible(true);
    }//GEN-LAST:event_btnAyduaActionPerformed

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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Main().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    private void compruebaConexionBD(boolean main, String nombreFrame) {
        if (framePopup == null) {
            SwingWorker<?, ?> worker = new SwingWorker<Void, Void>() {
                protected Void doInBackground() throws InterruptedException {
                    String ip, usuario, password;
                    int puerto;

                    try {
                        ip = Configuracion.getIp();
                        puerto = Integer.parseInt(Configuracion.getPuerto());
                        usuario = Configuracion.getUsuario();
                        password = Configuracion.getPassword();

                        ComprobarConexion.comprobarConexion(
                                ip,
                                puerto,
                                "institut",
                                usuario,
                                password);

                        gestorSesiones = new GestorSesiones();

                        gestorSesiones.configurarPropiedades(ip, puerto, usuario, password);

                        existeConexion = true;
                    } catch (Exception e) {
                        e.printStackTrace();
                        existeConexion = false;
                    }

                    return null;
                }

                protected void done() {
                    if (existeConexion) {
                        System.out.println("Conexión con el servidor correcta!");

                        frameCarga.dispose();

                        JFrame frame = null;

                        if (!main) {
                            switch (nombreFrame) {
                                case "FrameLibro":
                                    if (frameLibro == null) {
                                        frame = new FrameLibro();
                                    }
                                    break;

                                case "FrameEntrega":
                                    if (frameEntrega == null) {
                                        frame = new FrameEntrega();
                                    }
                                    break;

                                case "FrameDevoluciones":
                                    if (frameDevoluciones == null) {
                                        frame = new FrameDevoluciones();
                                    }
                                    break;
                            }

                            frame.setVisible(true);
                        }
                    } else {
                        frameCarga.dispose();

                        Action abrirOpciones = new AbstractAction() {
                            public void actionPerformed(ActionEvent e) {
                                if (frameOpciones == null) {
                                    frameOpciones = new FrameOpciones();
                                } else {
                                    if (!frameOpciones.isVisible()) {
                                        frameOpciones = null;
                                        frameOpciones = new FrameOpciones();
                                    }
                                }

                                frameOpciones.setVisible(true);
                                frameOpciones.setFocusable(true);
                                frameOpciones.setAlwaysOnTop(true);

                                framePopup = null;
                            }
                        };

                        if (framePopup == null) {
                            framePopup = new FramePopup("No hay conexión al servidor, revise la configuración de red.",
                                    new ImageIcon(getClass().getResource("/Imagenes/icons/alert-black.png")),
                                    abrirOpciones
                            );

                            framePopup.setVisible(true);
                        }
                    }
                }
            };
            worker.execute();
            if (frameCarga == null) {
                frameCarga = new FramePopup("Comprobando conexión con el servidor");
            }
            frameCarga.setVisible(true);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel banner;
    private com.mommoo.flat.button.FlatButton btnAydua;
    private com.mommoo.flat.button.FlatButton btnDevoluciones;
    private com.mommoo.flat.button.FlatButton btnEntrega;
    private com.mommoo.flat.button.FlatButton btnLibros;
    private com.mommoo.flat.button.FlatButton btnOpciones;
    private com.mommoo.flat.button.FlatButton flatButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel wallpaper;
    // End of variables declaration//GEN-END:variables
}
