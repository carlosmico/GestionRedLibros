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

import Daos.*;
import Pojos.*;
import Utilidades.*;
import java.awt.Desktop;
import java.net.URI;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jose Sanchis
 */
public class FrameAlumno extends javax.swing.JFrame {

    /**
     * Creates new form FrameDevoluciones
     */
    private Alumno alumno;

    private FrameCarga frameCarga;

    private List<Historial> listaHistorial;

    DaoAlumno daoAlumno;

    public FrameAlumno(String niaAlumno) {
        initComponents();
        //<editor-fold defaultstate="collapsed" desc="Configuración inicial de la tabla">
        tablaHistorial.setFont(textNombreAlumno.getFont());
//</editor-fold>

        //this.alumno = alumno;
        //Deshabilitamos la tabla de ejemplares puesto que es de lectura
        tablaHistorial.setEnabled(false);

        this.setLocationRelativeTo(null);

        daoAlumno = new DaoAlumno(Main.gestorSesiones.getSession());

        SwingWorker<?, ?> worker = new SwingWorker<Void, Integer>() {
            protected Void doInBackground() throws InterruptedException {
                alumno = daoAlumno.buscar(niaAlumno);
                return null;
            }

            protected void process(List<Integer> chunks) {
            }

            protected void done() {
                listaHistorial = alumno.getHistoriales();
                rellenarCampos();
                frameCarga.dispose();
            }
        };
        worker.execute();
        if (frameCarga == null) {
            frameCarga = new FrameCarga();
        }
        frameCarga.setVisible(true);

        //Set imagen del libro
        try {
            int ran = (int) (Math.floor(Math.random() * 3) + 1);
            System.out.println("Resultado: " + ran);
            imgLibro.setIcon(new ImageIcon(getClass().getResource("/Imagenes/image" + ran + ".png")));
        } catch (Exception ex) {
            imgLibro.setText("\nNo se ha podido cargar la imagen del libro");
            Logger.getLogger(FrameAlumno.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void rellenarCampos() {
        //Rellenamos los datos
        textNIAAlumno.setText(alumno.getNia());
        textNombreAlumno.setText(alumno.getNombre() + " " + alumno.getApellido1() + " " + alumno.getApellido2());
        textTelefonoAlumno.setText(alumno.getTelefono1());
        textEmailAlumno.setText(alumno.getEmail1());
        //textCursoAlumno.setText(alumno.getCurso().getAbreviatura());

        //Refrescamos la tabla de ejemplares
        RefrescarTabla();
    }

    //Refrescamos los datos de la tabla recuperados de la BD
    private void RefrescarTabla() {

        if (listaHistorial.size() > 0) {
            DefaultTableModel tableModel = (DefaultTableModel) this.tablaHistorial.getModel();

            tableModel.setRowCount(0);

            for (int i = 0; i < listaHistorial.size(); i++) {
                Historial h = listaHistorial.get(i);
                String[] columna = new String[8];

                //Id ejemplar prestado
                columna[0] = h.getEjemplar().getCodigo();

                //Nombre ejemplar prestado
                columna[1] = h.getEjemplar().getLibro().getNombre();

                //Curso escolar
                columna[2] = h.getCurso_escolar() + "";

                //estado del libro
                //<editor-fold defaultstate="collapsed" desc="Estado inicial">
                switch (h.getEstado_inicial()) {
                    case Estado.deteriorado:
                        columna[3] = "Deteriorado";
                        break;

                    case Estado.usado:
                        columna[3] = "Usado";
                        break;

                    case Estado.nuevo:
                        columna[3] = "Nuevo";
                        break;
                }
//</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="Estado final">
                switch (h.getEstado_final()) {
                    case Estado.deteriorado:
                        columna[4] = "Deteriorado";
                        break;

                    case Estado.usado:
                        columna[4] = "Usado";
                        break;

                    case Estado.nuevo:
                        columna[4] = "Nuevo";
                        break;
                }
//</editor-fold>

                //Fechas
                columna[5] = h.getFecha_inicial() + "";
                columna[6] = h.getFecha_final() + "";

                columna[7] = h.getObservaciones();

                tableModel.addRow(columna);
            }

            tablaHistorial.setModel(tableModel);

            tablaHistorial.repaint();

        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        panelLibro = new javax.swing.JPanel();
        imgLibro = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaHistorial = new javax.swing.JTable();
        panelGeneralSuperior = new javax.swing.JPanel();
        panelSuperior = new javax.swing.JPanel();
        panelNIA = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        textNIAAlumno = new javax.swing.JLabel();
        panelNombreYApellidos = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        textNombreAlumno = new javax.swing.JLabel();
        panelTelefono = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        textTelefonoAlumno = new javax.swing.JLabel();
        panelMedio = new javax.swing.JPanel();
        panelAsignatura = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        textEmailAlumno = new javax.swing.JLabel();
        btnEmail = new com.mommoo.flat.button.FlatButton();
        panelTelefono1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        textCursoAlumno = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Libros");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(58, 39, 35));

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Alumno");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(239, 235, 233));

        panelLibro.setBackground(new java.awt.Color(255, 255, 255));
        panelLibro.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        imgLibro.setForeground(new java.awt.Color(51, 51, 51));
        imgLibro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imgLibro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/person-flat.png"))); // NOI18N
        imgLibro.setPreferredSize(new java.awt.Dimension(192, 192));

        javax.swing.GroupLayout panelLibroLayout = new javax.swing.GroupLayout(panelLibro);
        panelLibro.setLayout(panelLibroLayout);
        panelLibroLayout.setHorizontalGroup(
            panelLibroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLibroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imgLibro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelLibroLayout.setVerticalGroup(
            panelLibroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLibroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imgLibro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(239, 235, 233));

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setText("Ejemplares:");

        tablaHistorial.setBackground(new java.awt.Color(239, 235, 233));
        tablaHistorial.setForeground(new java.awt.Color(51, 51, 51));
        tablaHistorial.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Estado", "Prestado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaHistorial.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tablaHistorial.setFillsViewportHeight(true);
        jScrollPane1.setViewportView(tablaHistorial);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelGeneralSuperior.setBackground(new java.awt.Color(239, 235, 233));
        panelGeneralSuperior.setLayout(new java.awt.GridLayout(2, 1));

        panelSuperior.setBackground(new java.awt.Color(239, 235, 233));
        panelSuperior.setPreferredSize(new java.awt.Dimension(820, 77));

        panelNIA.setBackground(new java.awt.Color(239, 235, 233));
        panelNIA.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel2.setBackground(new java.awt.Color(241, 241, 241));
        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("NIA:");

        textNIAAlumno.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        textNIAAlumno.setForeground(new java.awt.Color(51, 51, 51));
        textNIAAlumno.setText("10429497");

        javax.swing.GroupLayout panelNIALayout = new javax.swing.GroupLayout(panelNIA);
        panelNIA.setLayout(panelNIALayout);
        panelNIALayout.setHorizontalGroup(
            panelNIALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNIALayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelNIALayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(textNIAAlumno, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelNIALayout.setVerticalGroup(
            panelNIALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNIALayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textNIAAlumno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelNombreYApellidos.setBackground(new java.awt.Color(239, 235, 233));
        panelNombreYApellidos.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel3.setBackground(new java.awt.Color(241, 241, 241));
        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Nombre y Apellidos:");

        textNombreAlumno.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        textNombreAlumno.setForeground(new java.awt.Color(51, 51, 51));
        textNombreAlumno.setText("Jose Sanchis Belda");

        javax.swing.GroupLayout panelNombreYApellidosLayout = new javax.swing.GroupLayout(panelNombreYApellidos);
        panelNombreYApellidos.setLayout(panelNombreYApellidosLayout);
        panelNombreYApellidosLayout.setHorizontalGroup(
            panelNombreYApellidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNombreYApellidosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelNombreYApellidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelNombreYApellidosLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(textNombreAlumno, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(panelNombreYApellidosLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        panelNombreYApellidosLayout.setVerticalGroup(
            panelNombreYApellidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNombreYApellidosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textNombreAlumno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelTelefono.setBackground(new java.awt.Color(239, 235, 233));
        panelTelefono.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel7.setBackground(new java.awt.Color(241, 241, 241));
        jLabel7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("Telefono:");

        textTelefonoAlumno.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        textTelefonoAlumno.setForeground(new java.awt.Color(51, 51, 51));
        textTelefonoAlumno.setText("684092823");

        javax.swing.GroupLayout panelTelefonoLayout = new javax.swing.GroupLayout(panelTelefono);
        panelTelefono.setLayout(panelTelefonoLayout);
        panelTelefonoLayout.setHorizontalGroup(
            panelTelefonoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTelefonoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTelefonoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTelefonoLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(textTelefonoAlumno, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(panelTelefonoLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        panelTelefonoLayout.setVerticalGroup(
            panelTelefonoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTelefonoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textTelefonoAlumno, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelSuperiorLayout = new javax.swing.GroupLayout(panelSuperior);
        panelSuperior.setLayout(panelSuperiorLayout);
        panelSuperiorLayout.setHorizontalGroup(
            panelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSuperiorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelNIA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelNombreYApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelTelefono, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelSuperiorLayout.setVerticalGroup(
            panelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSuperiorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelTelefono, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelNIA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelNombreYApellidos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        panelGeneralSuperior.add(panelSuperior);

        panelMedio.setBackground(new java.awt.Color(239, 235, 233));
        panelMedio.setPreferredSize(new java.awt.Dimension(820, 77));

        panelAsignatura.setBackground(new java.awt.Color(239, 235, 233));
        panelAsignatura.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel5.setBackground(new java.awt.Color(241, 241, 241));
        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setText("Correo electrónico:");

        textEmailAlumno.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        textEmailAlumno.setForeground(new java.awt.Color(51, 51, 51));
        textEmailAlumno.setText("sanchisbeldajose@gmail.com");

        btnEmail.setBackground(Colores.buttons);
        btnEmail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons/email-outline (1).png"))); // NOI18N
        btnEmail.setCornerRound(10);
        btnEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmailActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelAsignaturaLayout = new javax.swing.GroupLayout(panelAsignatura);
        panelAsignatura.setLayout(panelAsignaturaLayout);
        panelAsignaturaLayout.setHorizontalGroup(
            panelAsignaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAsignaturaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addContainerGap(255, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAsignaturaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(textEmailAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelAsignaturaLayout.setVerticalGroup(
            panelAsignaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAsignaturaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelAsignaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(textEmailAlumno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        panelTelefono1.setBackground(new java.awt.Color(239, 235, 233));
        panelTelefono1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel8.setBackground(new java.awt.Color(241, 241, 241));
        jLabel8.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("Curso:");

        textCursoAlumno.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        textCursoAlumno.setForeground(new java.awt.Color(51, 51, 51));
        textCursoAlumno.setText("DAM");

        javax.swing.GroupLayout panelTelefono1Layout = new javax.swing.GroupLayout(panelTelefono1);
        panelTelefono1.setLayout(panelTelefono1Layout);
        panelTelefono1Layout.setHorizontalGroup(
            panelTelefono1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTelefono1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTelefono1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTelefono1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(textCursoAlumno, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(panelTelefono1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        panelTelefono1Layout.setVerticalGroup(
            panelTelefono1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTelefono1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textCursoAlumno, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelMedioLayout = new javax.swing.GroupLayout(panelMedio);
        panelMedio.setLayout(panelMedioLayout);
        panelMedioLayout.setHorizontalGroup(
            panelMedioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMedioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelAsignatura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelTelefono1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelMedioLayout.setVerticalGroup(
            panelMedioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMedioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelMedioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelTelefono1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelAsignatura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        panelGeneralSuperior.add(panelMedio);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(panelLibro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelGeneralSuperior, javax.swing.GroupLayout.PREFERRED_SIZE, 656, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelLibro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelGeneralSuperior, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmailActionPerformed
        // TODO add your handling code here:
        try {
            if (alumno != null) {
                if (!textEmailAlumno.getText().equals("")) {
                    URI uri = new URI("mailto:" + textEmailAlumno.getText());
                    Desktop dt = Desktop.getDesktop();
                    dt.browse(uri);
                } else {
                    JOptionPane.showMessageDialog(this, "El correo del alumno no esta especificado.");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btnEmailActionPerformed

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
            java.util.logging.Logger.getLogger(FrameAlumno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameAlumno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameAlumno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameAlumno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameAlumno(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.mommoo.flat.button.FlatButton btnEmail;
    private javax.swing.JLabel imgLibro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelAsignatura;
    private javax.swing.JPanel panelGeneralSuperior;
    private javax.swing.JPanel panelLibro;
    private javax.swing.JPanel panelMedio;
    private javax.swing.JPanel panelNIA;
    private javax.swing.JPanel panelNombreYApellidos;
    private javax.swing.JPanel panelSuperior;
    private javax.swing.JPanel panelTelefono;
    private javax.swing.JPanel panelTelefono1;
    private javax.swing.JTable tablaHistorial;
    private javax.swing.JLabel textCursoAlumno;
    private javax.swing.JLabel textEmailAlumno;
    private javax.swing.JLabel textNIAAlumno;
    private javax.swing.JLabel textNombreAlumno;
    private javax.swing.JLabel textTelefonoAlumno;
    // End of variables declaration//GEN-END:variables
}
