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

import Daos.DaoAlumno;
import Pojos.Alumno;
import Pojos.Matricula;
import Utilidades.Colores;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JFrame;

/**
 *
 * @author Jose Sanchis
 */
public class FrameEntrega extends javax.swing.JFrame {

    //Creamos el la pestaña InputAlumno
    private FrameInputAlumno frameInputAlumno;

    //Creamos el DAO del Alumno
    private DaoAlumno daoAlumno;

    //Creamos el Alumno
    public static Alumno alumno;

    /**
     * Creates new form FrameDevoluciones
     */
    public FrameEntrega() {
        initComponents();

        //Centramos la pestaña al centro de la pantalla
        this.setLocationRelativeTo(null);

        //Maximizamos la pestaña
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        //Inicializamos el DaoAlumno
        daoAlumno = new DaoAlumno(Main.gestorSesiones.getSession());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelTitulo = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnBuscarAlumno = new com.mommoo.flat.button.FlatButton();
        panelCuerpo = new javax.swing.JPanel();
        panelNoAlumno = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        panelSiAlumno = new javax.swing.JPanel();
        panelInfoGeneral = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        textCursoEscolar = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        textNIAAlumno = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        textNivel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Entregas");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        panelTitulo.setBackground(new java.awt.Color(58, 39, 35));

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Entregas");

        btnBuscarAlumno.setBackground(Colores.buttons);
        btnBuscarAlumno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons/account-search.png"))); // NOI18N
        btnBuscarAlumno.setCornerRound(10);
        btnBuscarAlumno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarAlumnoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelTituloLayout = new javax.swing.GroupLayout(panelTitulo);
        panelTitulo.setLayout(panelTituloLayout);
        panelTituloLayout.setHorizontalGroup(
            panelTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTituloLayout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 968, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBuscarAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelTituloLayout.setVerticalGroup(
            panelTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelTituloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnBuscarAlumno, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelCuerpo.setBackground(Colores.fondo);

        panelNoAlumno.setBackground(Colores.fondo);
        panelNoAlumno.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel3.setBackground(new java.awt.Color(153, 153, 153));
        jLabel3.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(153, 153, 153));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Busca un alumno para realizar una nueva entrega");

        javax.swing.GroupLayout panelNoAlumnoLayout = new javax.swing.GroupLayout(panelNoAlumno);
        panelNoAlumno.setLayout(panelNoAlumnoLayout);
        panelNoAlumnoLayout.setHorizontalGroup(
            panelNoAlumnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNoAlumnoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelNoAlumnoLayout.setVerticalGroup(
            panelNoAlumnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNoAlumnoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelInfoGeneral.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Curso escolar:");

        textCursoEscolar.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        textCursoEscolar.setForeground(new java.awt.Color(51, 51, 51));
        textCursoEscolar.setText("018");

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("NIA del Alumno:");

        textNIAAlumno.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        textNIAAlumno.setForeground(new java.awt.Color(51, 51, 51));
        textNIAAlumno.setText("null");

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("Nivel:");

        textNivel.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        textNivel.setForeground(new java.awt.Color(51, 51, 51));
        textNivel.setText("jLabel9");

        javax.swing.GroupLayout panelInfoGeneralLayout = new javax.swing.GroupLayout(panelInfoGeneral);
        panelInfoGeneral.setLayout(panelInfoGeneralLayout);
        panelInfoGeneralLayout.setHorizontalGroup(
            panelInfoGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInfoGeneralLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelInfoGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelInfoGeneralLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textNivel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelInfoGeneralLayout.createSequentialGroup()
                        .addGroup(panelInfoGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelInfoGeneralLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textCursoEscolar))
                            .addGroup(panelInfoGeneralLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textNIAAlumno)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelInfoGeneralLayout.setVerticalGroup(
            panelInfoGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInfoGeneralLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelInfoGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(textCursoEscolar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelInfoGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(textNIAAlumno))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelInfoGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(textNivel))
                .addContainerGap(440, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelSiAlumnoLayout = new javax.swing.GroupLayout(panelSiAlumno);
        panelSiAlumno.setLayout(panelSiAlumnoLayout);
        panelSiAlumnoLayout.setHorizontalGroup(
            panelSiAlumnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSiAlumnoLayout.createSequentialGroup()
                .addComponent(panelInfoGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelSiAlumnoLayout.setVerticalGroup(
            panelSiAlumnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelInfoGeneral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelCuerpoLayout = new javax.swing.GroupLayout(panelCuerpo);
        panelCuerpo.setLayout(panelCuerpoLayout);
        panelCuerpoLayout.setHorizontalGroup(
            panelCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCuerpoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelNoAlumno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelSiAlumno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelCuerpoLayout.setVerticalGroup(
            panelCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCuerpoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelNoAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelSiAlumno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelCuerpo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelCuerpo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarAlumnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarAlumnoActionPerformed
        //Acción del botón de 'Devoluciones'
        if (frameInputAlumno == null) {
            //Si no existe la ventana la creamos
            frameInputAlumno = new FrameInputAlumno();
        } else {
            if (!frameInputAlumno.isVisible()) {
                //Si existe la ventana, y la hemos cerrado
                //limpiamos la variable y creamos una ventana nueva
                frameInputAlumno = null;
                frameInputAlumno = new FrameInputAlumno();
            }
        }
        //Hacemos visible la ventana creada anteriormente
        frameInputAlumno.setVisible(true);
    }//GEN-LAST:event_btnBuscarAlumnoActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        //<editor-fold defaultstate="collapsed" desc="Paneles">
        panelNoAlumno.setVisible(alumno == null);
        panelSiAlumno.setVisible(alumno != null);
//</editor-fold>

        //Si el alumno que hemos comprobado anteriormente existe:
        if (alumno != null) {
            //Conseguimos el año actual para comprobar la matricula
            LocalDate localDate = LocalDate.now();
            String date = DateTimeFormatter.ofPattern("yyyy").format(localDate);

            /*
             BORRAR
             */
            int fechaPruebas = Integer.parseInt(date) - 1;
            String date1 = fechaPruebas + "";
            /*
             BORRAR
             */

            //Buscamos el alumno con el nia que hemos encontrado
            //(Tenemos que volverlo a buscar por las relaciones (matriculas, historial))
            alumno = daoAlumno.buscar(alumno.getNia());

            //Guardamos sus matriculas en una nueva lista por comodidad
            List<Matricula> listaMatriculas = alumno.getMatriculas();

            if (listaMatriculas.size() > 0) {
                //Si la lista tiene mas de una matricula:
                List<Matricula> listaMatriculasCursoEscolar = listaMatriculas.stream().filter(matriculaTemp -> matriculaTemp.getCurso_escolar() == Integer.parseInt(date1)).collect(Collectors.toList());

                if (listaMatriculasCursoEscolar.size() > 0) {
                    //Si tiene matriculas de este año:
                    textCursoEscolar.setText(listaMatriculasCursoEscolar.get(0).getCurso_escolar() + "");
                    textNIAAlumno.setText(alumno.getNia());
                    textNivel.setText(alumno.getCurso().getAbreviatura());
                } else {
                    //Si la matricula no es de este año:
                    new FramePopup("Este alumno no esta matriculado en este curso escolar.");
                }
            } else {
                //Si la lista no tiene matriculas:
                new FramePopup("Este alumno no tiene matriculas.");
            }
        } else {
            //No se ha buscado ningun alumno
            System.out.println("El alumno continua siendo Null");
        }
    }//GEN-LAST:event_formWindowActivated

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
            java.util.logging.Logger.getLogger(FrameEntrega.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameEntrega.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameEntrega.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameEntrega.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameEntrega().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.mommoo.flat.button.FlatButton btnBuscarAlumno;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel panelCuerpo;
    private javax.swing.JPanel panelInfoGeneral;
    private javax.swing.JPanel panelNoAlumno;
    private javax.swing.JPanel panelSiAlumno;
    private javax.swing.JPanel panelTitulo;
    private javax.swing.JLabel textCursoEscolar;
    private javax.swing.JLabel textNIAAlumno;
    private javax.swing.JLabel textNivel;
    // End of variables declaration//GEN-END:variables
}
