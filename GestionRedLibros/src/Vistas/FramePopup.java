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
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JDialog;

/**
 *
 * @author Jose Sanchis
 */
public class FramePopup extends javax.swing.JFrame {

    /**
     * Crea el nuevo formulario FramePopup. (No se cierra por si solo)
     */
    public FramePopup() {
        initComponents();
        panelButton.setVisible(false);
        pack();
        this.setLocationRelativeTo(null);
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
        this.setLocationRelativeTo(null);
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
        this.setLocationRelativeTo(null);
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
        this.setLocationRelativeTo(null);
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
        this.setLocationRelativeTo(null);
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
        this.setLocationRelativeTo(null);
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
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        text = new javax.swing.JLabel();
        panelButton = new javax.swing.JPanel();
        btnButton2 = new com.mommoo.flat.button.FlatButton();
        btnButton1 = new com.mommoo.flat.button.FlatButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setUndecorated(true);
        setType(java.awt.Window.Type.POPUP);

        jPanel2.setBackground(new java.awt.Color(66, 47, 44));

        text.setBackground(new java.awt.Color(239, 235, 233));
        text.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        text.setForeground(new java.awt.Color(51, 51, 51));
        text.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        text.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/load.gif"))); // NOI18N
        text.setText("Recuperando datos en la base de datos");
        text.setToolTipText("");
        text.setMaximumSize(new java.awt.Dimension(5000, 5000));
        text.setOpaque(true);

        panelButton.setBackground(new java.awt.Color(239, 235, 233));

        btnButton2.setBackground(Colores.accent);
        btnButton2.setForeground(Colores.fondo);
        btnButton2.setText("Aceptar");
        btnButton2.setCornerRound(10);
        btnButton2.setRequestFocusEnabled(false);
        btnButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnButton2ActionPerformed(evt);
            }
        });

        btnButton1.setBackground(Colores.accent);
        btnButton1.setForeground(Colores.fondo);
        btnButton1.setText("Button 1");
        btnButton1.setCornerRound(10);
        btnButton1.setPreferredSize(new java.awt.Dimension(79, 28));

        javax.swing.GroupLayout panelButtonLayout = new javax.swing.GroupLayout(panelButton);
        panelButton.setLayout(panelButtonLayout);
        panelButtonLayout.setHorizontalGroup(
            panelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelButtonLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelButtonLayout.setVerticalGroup(
            panelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelButtonLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(text, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
                    .addComponent(panelButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(text, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(panelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Acción del botón de confirmación
     */
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel panelButton;
    private javax.swing.JLabel text;
    // End of variables declaration//GEN-END:variables
}
