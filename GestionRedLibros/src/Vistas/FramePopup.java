/*
 * Copyright (C) 2019 Jose Sanchis
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package Vistas;

import Utilidades.Colores;
import java.awt.Font;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 * @author Jose Sanchis
 */
public class FramePopup extends javax.swing.JDialog {

    /**
     * Crea el nuevo formulario FramePopup. (No se cierra por si solo)
     *
     * @param Padre, para recoger el foco
     */
    public FramePopup(JFrame parent) {
        super(parent, true);
        initComponents();
        rellenarDatos("", null);
        panelButton.setVisible(false);
        pack();
        setLocationRelativeTo(null);
    }

    /**
     * Crea el nuevo formulario FramePopup con texto personalizado. (No se
     * cierra por si solo)
     *
     * @param mensaje Es el texto que aparece en la ventana
     */
    public FramePopup(JFrame parent, String mensaje) {
        super(parent, true);
        initComponents();
        rellenarDatos(mensaje, null);
        panelButton.setVisible(false);
        pack();
        setLocationRelativeTo(null);
    }

    /**
     * Crea el nuevo formulario FramePopup con texto y icono personalizado. (No
     * se cierra por si solo)
     *
     * @param mensaje Es el texto que aparece en la ventana
     * @param icon Es la imagen que se muestra en la ventana
     */
    public FramePopup(JFrame parent, String mensaje, ImageIcon icon) {
        super(parent, true);
        initComponents();
        rellenarDatos(mensaje, icon);
        panelButton.setVisible(false);
        pack();
        setLocationRelativeTo(null);
    }

    /**
     * Crea el nuevo formulario FramePopup con texto, icono y botón
     * personalizado. (Si puede cerrarse desde el mismo formulario)
     *
     * @param mensaje Es el texto que aparece en la ventana
     * @param icon Es la imagen que se muestra en la ventana
     * @param buttonText Es el texto que contendra el Botón
     *
     */
    public FramePopup(JFrame parent, String mensaje, ImageIcon icon, String buttonText) {
        super(parent, true);
        initComponents();
        rellenarDatos(mensaje, icon);
        btnButton2.setText(buttonText);
        btnButton2.requestFocusInWindow();
        btnButton1.setVisible(false);
        pack();
        setLocationRelativeTo(null);
    }

    /**
     * Crea el nuevo formulario FramePopup con texto, icono y botón
     * personalizado con acción personalizada. (Si puede cerrarse desde el mismo
     * formulario)
     *
     * @param mensaje Es el texto que aparece en la ventana
     * @param icon Es la imagen que se muestra en la ventana
     * @param buttonText Es el texto que contendra el Botón
     * @param action Es la acción que realizará el Botón
     */
    public FramePopup(JFrame parent, String mensaje, ImageIcon icon, String buttonText, Action action) {
        super(parent, true);
        initComponents();
        rellenarDatos(mensaje, icon);
        btnButton2.setText(buttonText);
        btnButton2.addActionListener(action);
        btnButton1.setVisible(false);
        pack();
        setLocationRelativeTo(null);
    }

    /**
     * Crea el nuevo formulario FramePopup con texto, icono y botón
     * personalizado. (Si puede cerrarse desde el mismo formulario)
     *
     * @param mensaje Es el texto que aparece en la ventana
     * @param icon Es la imagen que se muestra en la ventana
     * @param action Es la acción que realizará el Botón
     */
    public FramePopup(JFrame parent, String mensaje, ImageIcon icon, Action action) {
        super(parent, true);
        initComponents();
        rellenarDatos(mensaje, icon);
        btnButton2.addActionListener(action);
        btnButton1.setVisible(false);
        pack();
        setLocationRelativeTo(null);
    }

    /**
     * Crea el nuevo formulario FramePopup con texto, icono y botón
     * personalizado. (Si puede cerrarse desde el mismo formulario)
     *
     * @param mensaje Es el texto que aparece en la ventana
     * @param icon Es la imagen que se muestra en la ventana
     * @param button1 El texto que se mostrará en el botón Izquierdo
     * @param button2 El texto que se mostrará en el botón Derecho
     * @param actionButton1 Acción que realizara el botón de la Izquierda
     * @param actionButton2 Acción que realizara el botón de la Derecha
     */
    public FramePopup(JFrame parent, String mensaje, ImageIcon icon, String button1, String button2, Action actionButton1, Action actionButton2) {
        super(parent, true);
        initComponents();
        rellenarDatos(mensaje, icon);
        btnButton1.setText(button1);
        btnButton1.addActionListener(actionButton1);
        btnButton2.setText(button2);
        btnButton2.addActionListener(actionButton2);
        pack();
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        text = new org.jdesktop.swingx.JXLabel();
        panelButton = new javax.swing.JPanel();
        btnButton2 = new com.mommoo.flat.button.FlatButton();
        btnButton1 = new com.mommoo.flat.button.FlatButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);
        setUndecorated(true);

        jPanel1.setBackground(Colores.fondoOscuro);

        jPanel3.setBackground(Colores.fondo);

        jPanel2.setBackground(Colores.fondo);

        text.setBackground(Colores.fondo);
        text.setForeground(Colores.letraNormal);
        text.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        text.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/load.gif"))); // NOI18N
        text.setText("Recuperando datos del servidor...");
        text.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        text.setLineWrap(true);
        text.setMinimumSize(new java.awt.Dimension(375, 36));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(text, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panelButton.setBackground(Colores.fondo);

        btnButton2.setBackground(Colores.botones);
        btnButton2.setForeground(Colores.letraBotones);
        btnButton2.setText("Aceptar");
        btnButton2.setCornerRound(10);
        btnButton2.setMaximumSize(new java.awt.Dimension(150, 32));
        btnButton2.setMinimumSize(new java.awt.Dimension(150, 32));
        btnButton2.setPreferredSize(new java.awt.Dimension(150, 32));
        btnButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnButton2ActionPerformed(evt);
            }
        });

        btnButton1.setBackground(Colores.botones);
        btnButton1.setForeground(Colores.letraBotones);
        btnButton1.setText("Button1");
        btnButton1.setCornerRound(10);
        btnButton1.setMaximumSize(new java.awt.Dimension(150, 32));
        btnButton1.setMinimumSize(new java.awt.Dimension(150, 32));
        btnButton1.setPreferredSize(new java.awt.Dimension(150, 32));

        javax.swing.GroupLayout panelButtonLayout = new javax.swing.GroupLayout(panelButton);
        panelButton.setLayout(panelButtonLayout);
        panelButtonLayout.setHorizontalGroup(
            panelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelButtonLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelButtonLayout.setVerticalGroup(
            panelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelButtonLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnButton2ActionPerformed
        dispose();
    }//GEN-LAST:event_btnButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(FramePopup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FramePopup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FramePopup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FramePopup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FramePopup dialog = new FramePopup(new javax.swing.JFrame());
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.mommoo.flat.button.FlatButton btnButton1;
    private com.mommoo.flat.button.FlatButton btnButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel panelButton;
    private org.jdesktop.swingx.JXLabel text;
    // End of variables declaration//GEN-END:variables

    private void rellenarDatos(String mensaje, ImageIcon icono) {
        text.setFont(new Font("Dialog", Font.BOLD, 18));

        if (mensaje.equals("")) {
            text.setText("Recuperando datos del servidor.");
            if (icono != null) {
                text.setIcon(icono);
            }
        } else {
            text.setText(mensaje);
            if (icono != null) {
                text.setIcon(icono);
            }
        }
    }
}
