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
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Action;
import javax.swing.ImageIcon;

/**
 *
 * @author Jose Sanchis
 */
public class FramePopup extends javax.swing.JDialog {

    /**
     * Crea el nuevo formulario FramePopup. (No se cierra por si solo)
     */
    public FramePopup() {
        initComponents();
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
    public FramePopup(String mensaje) {
        initComponents();
        text.setText(mensaje);
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
    public FramePopup(String mensaje, ImageIcon icon) {
        initComponents();
        text.setText(mensaje);
        text.setIcon(icon);
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
    public FramePopup(String mensaje, ImageIcon icon, String buttonText) {
        initComponents();
        text.setText(mensaje);
        text.setIcon(icon);
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
    public FramePopup(String mensaje, ImageIcon icon, String buttonText, Action action) {
        initComponents();
        text.setText(mensaje);
        text.setIcon(icon);
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
    public FramePopup(String mensaje, ImageIcon icon, Action action) {
        initComponents();
        text.setText(mensaje);
        text.setIcon(icon);
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
    public FramePopup(String mensaje, ImageIcon icon, String button1, String button2, Action actionButton1, Action actionButton2) {
        initComponents();
        text.setText(mensaje);
        text.setIcon(icon);
        btnButton1.setText(button1);
        btnButton1.addActionListener(actionButton1);
        btnButton2.setText(button2);
        btnButton2.addActionListener(actionButton2);
        pack();
        setLocationRelativeTo(null);
    }

    /**
     * _
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        text = new org.jdesktop.swingx.JXLabel();
        jPanel4 = new javax.swing.JPanel();
        panelButton = new javax.swing.JPanel();
        btnButton1 = new com.mommoo.flat.button.FlatButton();
        btnButton2 = new com.mommoo.flat.button.FlatButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setModal(true);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(487, 150));
        setResizable(false);
        setType(java.awt.Window.Type.POPUP);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(Colores.accento);
        jPanel1.setForeground(Colores.accento);

        jPanel2.setBackground(Colores.fondo);

        jPanel3.setBackground(Colores.fondo);

        text.setForeground(Colores.letraNormal);
        text.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        text.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/load.gif"))); // NOI18N
        text.setText("Recuperando datos del servidor...");
        text.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        text.setMaximumSize(new java.awt.Dimension(32767, 32767));
        text.setMinimumSize(new java.awt.Dimension(0, 0));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(text, javax.swing.GroupLayout.DEFAULT_SIZE, 451, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(text, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBackground(Colores.fondo);

        panelButton.setBackground(Colores.fondo);
        panelButton.setLayout(new java.awt.GridLayout(1, 2, 10, 0));

        btnButton1.setBackground(Colores.accento);
        btnButton1.setForeground(Colores.letraBotones);
        btnButton1.setText("Button 1");
        btnButton1.setCornerRound(10);
        btnButton1.setMaximumSize(new java.awt.Dimension(86, 32));
        btnButton1.setMinimumSize(null);
        btnButton1.setPreferredSize(new java.awt.Dimension(192, 32));
        panelButton.add(btnButton1);

        btnButton2.setBackground(Colores.accento);
        btnButton2.setForeground(Colores.letraBotones);
        btnButton2.setText("Aceptar");
        btnButton2.setCornerRound(10);
        btnButton2.setFocusPainted(true);
        btnButton2.setMinimumSize(null);
        btnButton2.setPreferredSize(new java.awt.Dimension(192, 32));
        btnButton2.setRequestFocusEnabled(false);
        btnButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnButton2ActionPerformed(evt);
            }
        });
        panelButton.add(btnButton2);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(6, 6, 6))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Acción del botón de confirmación
     */
    private void btnButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnButton2ActionPerformed
        dispose();
    }//GEN-LAST:event_btnButton2ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

    }//GEN-LAST:event_formWindowOpened

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated

    }//GEN-LAST:event_formWindowActivated

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        resize();
        repaint();
    }//GEN-LAST:event_formWindowGainedFocus

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
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FramePopup().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.mommoo.flat.button.FlatButton btnButton1;
    private com.mommoo.flat.button.FlatButton btnButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel panelButton;
    private org.jdesktop.swingx.JXLabel text;
    // End of variables declaration//GEN-END:variables

    private void resize() {
        this.setSize(new Dimension(jPanel1.getWidth(), (jPanel1.getHeight())));
    }
}
