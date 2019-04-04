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
    private boolean isEditMode;

    private Alumno alumno;

    private FrameCarga frameCarga;

    private List<Historial> listaHistorial;

    DaoAlumno daoAlumno;

    public FrameAlumno(Alumno alumno) {
        initComponents();

        this.alumno = alumno;
        //<editor-fold defaultstate="collapsed" desc="Configuración inicial de la tabla">
        tablaHistorial.setFont(textNombreAlumno.getFont());
//</editor-fold>

        //this.alumno = alumno;
        //Deshabilitamos la tabla de ejemplares puesto que es de lectura
        tablaHistorial.setEnabled(false);

        this.setLocationRelativeTo(null);

        daoAlumno = new DaoAlumno(Main.gestorSesiones.getSession());

        daoAlumno.session.beginTransaction();
        daoAlumno.session.getTransaction().commit();

        SwingWorker<?, ?> worker = new SwingWorker<Void, Integer>() {
            protected Void doInBackground() throws InterruptedException {
                setEnabled(false);
                listaHistorial = alumno.getHistoriales();
                return null;
            }

            protected void process(List<Integer> chunks) {
            }

            protected void done() {
                //Rellenamos la lista de los libros
                rellenarCampos();
                setEnabled(true);
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

        //Refrescamos la tabla de ejemplares
        RefrescarTabla();
    }

    //Refrescamos los datos de la tabla recuperados de la BD
    private void RefrescarTabla() {

        System.out.println("Ejemplares: " + listaHistorial.size());

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

        } else {
            frameCarga.dispose();
            JOptionPane.showMessageDialog(this, "No hay datos de historiales en la Base de Datos.");
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
        panelCodigoBarras = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaHistorial = new javax.swing.JTable();
        panelGeneralSuperior = new javax.swing.JPanel();
        panelSuperior = new javax.swing.JPanel();
        panelNombre = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        textNIAAlumno = new javax.swing.JLabel();
        panelISBN = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        textNombreAlumno = new javax.swing.JLabel();
        panelMedio = new javax.swing.JPanel();
        panelCurso = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        textTelefonoAlumno = new javax.swing.JLabel();
        panelAsignatura = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        textEmailAlumno = new javax.swing.JLabel();
        panelInferior = new javax.swing.JPanel();
        panelUnidades = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        textUnidadesLibro = new javax.swing.JTextField();
        panelPrecio = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        textPrecio = new javax.swing.JTextField();
        panelEstadoDelLibro = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        chkObsoleto = new com.mommoo.flat.select.FlatCheckBox();
        jLabel11 = new javax.swing.JLabel();

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

        panelCodigoBarras.setBackground(new java.awt.Color(239, 235, 233));
        panelCodigoBarras.setLayout(new java.awt.GridLayout(1, 2));

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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 835, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelGeneralSuperior.setBackground(new java.awt.Color(239, 235, 233));

        panelSuperior.setBackground(new java.awt.Color(239, 235, 233));
        panelSuperior.setPreferredSize(new java.awt.Dimension(820, 77));

        panelNombre.setBackground(new java.awt.Color(239, 235, 233));
        panelNombre.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel2.setBackground(new java.awt.Color(241, 241, 241));
        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("NIA:");

        textNIAAlumno.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        textNIAAlumno.setForeground(new java.awt.Color(51, 51, 51));
        textNIAAlumno.setText("10429497");

        javax.swing.GroupLayout panelNombreLayout = new javax.swing.GroupLayout(panelNombre);
        panelNombre.setLayout(panelNombreLayout);
        panelNombreLayout.setHorizontalGroup(
            panelNombreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNombreLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelNombreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelNombreLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(textNIAAlumno, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE))
                    .addGroup(panelNombreLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 160, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelNombreLayout.setVerticalGroup(
            panelNombreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNombreLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textNIAAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelISBN.setBackground(new java.awt.Color(239, 235, 233));
        panelISBN.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel3.setBackground(new java.awt.Color(241, 241, 241));
        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Nombre y Apellidos:");

        textNombreAlumno.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        textNombreAlumno.setForeground(new java.awt.Color(51, 51, 51));
        textNombreAlumno.setText("Jose Sanchis Belda");

        javax.swing.GroupLayout panelISBNLayout = new javax.swing.GroupLayout(panelISBN);
        panelISBN.setLayout(panelISBNLayout);
        panelISBNLayout.setHorizontalGroup(
            panelISBNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelISBNLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelISBNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelISBNLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(textNombreAlumno, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(panelISBNLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        panelISBNLayout.setVerticalGroup(
            panelISBNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelISBNLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textNombreAlumno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelSuperiorLayout = new javax.swing.GroupLayout(panelSuperior);
        panelSuperior.setLayout(panelSuperiorLayout);
        panelSuperiorLayout.setHorizontalGroup(
            panelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSuperiorLayout.createSequentialGroup()
                .addComponent(panelNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelISBN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelSuperiorLayout.setVerticalGroup(
            panelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSuperiorLayout.createSequentialGroup()
                .addGroup(panelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelISBN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(8, 8, 8))
        );

        panelMedio.setBackground(new java.awt.Color(239, 235, 233));
        panelMedio.setPreferredSize(new java.awt.Dimension(820, 77));

        panelCurso.setBackground(new java.awt.Color(239, 235, 233));
        panelCurso.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel7.setBackground(new java.awt.Color(241, 241, 241));
        jLabel7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("Telefono:");

        textTelefonoAlumno.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        textTelefonoAlumno.setForeground(new java.awt.Color(51, 51, 51));
        textTelefonoAlumno.setText("684092823");

        javax.swing.GroupLayout panelCursoLayout = new javax.swing.GroupLayout(panelCurso);
        panelCurso.setLayout(panelCursoLayout);
        panelCursoLayout.setHorizontalGroup(
            panelCursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCursoLayout.createSequentialGroup()
                .addGroup(panelCursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCursoLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelCursoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(textTelefonoAlumno, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelCursoLayout.setVerticalGroup(
            panelCursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCursoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textTelefonoAlumno, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelAsignatura.setBackground(new java.awt.Color(239, 235, 233));
        panelAsignatura.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel5.setBackground(new java.awt.Color(241, 241, 241));
        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setText("Correo electrónico:");

        textEmailAlumno.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        textEmailAlumno.setForeground(new java.awt.Color(51, 51, 51));
        textEmailAlumno.setText("sanchisbeldajose@gmail.com");

        javax.swing.GroupLayout panelAsignaturaLayout = new javax.swing.GroupLayout(panelAsignatura);
        panelAsignatura.setLayout(panelAsignaturaLayout);
        panelAsignaturaLayout.setHorizontalGroup(
            panelAsignaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAsignaturaLayout.createSequentialGroup()
                .addComponent(jLabel5)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(panelAsignaturaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(textEmailAlumno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelAsignaturaLayout.setVerticalGroup(
            panelAsignaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAsignaturaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textEmailAlumno, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelMedioLayout = new javax.swing.GroupLayout(panelMedio);
        panelMedio.setLayout(panelMedioLayout);
        panelMedioLayout.setHorizontalGroup(
            panelMedioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMedioLayout.createSequentialGroup()
                .addComponent(panelCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelAsignatura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelMedioLayout.setVerticalGroup(
            panelMedioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(panelAsignatura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        panelInferior.setBackground(new java.awt.Color(239, 235, 233));
        panelInferior.setPreferredSize(new java.awt.Dimension(820, 77));
        panelInferior.setLayout(new java.awt.GridLayout(1, 0));

        panelUnidades.setBackground(new java.awt.Color(239, 235, 233));

        jLabel4.setBackground(new java.awt.Color(241, 241, 241));
        jLabel4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("Unidades:");

        textUnidadesLibro.setBackground(new java.awt.Color(239, 235, 233));
        textUnidadesLibro.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        textUnidadesLibro.setForeground(new java.awt.Color(51, 51, 51));
        textUnidadesLibro.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        textUnidadesLibro.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout panelUnidadesLayout = new javax.swing.GroupLayout(panelUnidades);
        panelUnidades.setLayout(panelUnidadesLayout);
        panelUnidadesLayout.setHorizontalGroup(
            panelUnidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUnidadesLayout.createSequentialGroup()
                .addGroup(panelUnidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(panelUnidadesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(textUnidadesLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelUnidadesLayout.setVerticalGroup(
            panelUnidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUnidadesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textUnidadesLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelInferior.add(panelUnidades);

        panelPrecio.setBackground(new java.awt.Color(239, 235, 233));

        jLabel8.setBackground(new java.awt.Color(241, 241, 241));
        jLabel8.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("Precio:");

        textPrecio.setBackground(new java.awt.Color(239, 235, 233));
        textPrecio.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        textPrecio.setForeground(new java.awt.Color(51, 51, 51));
        textPrecio.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        textPrecio.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout panelPrecioLayout = new javax.swing.GroupLayout(panelPrecio);
        panelPrecio.setLayout(panelPrecioLayout);
        panelPrecioLayout.setHorizontalGroup(
            panelPrecioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrecioLayout.createSequentialGroup()
                .addGroup(panelPrecioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addGroup(panelPrecioLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(textPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelPrecioLayout.setVerticalGroup(
            panelPrecioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrecioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelInferior.add(panelPrecio);

        panelEstadoDelLibro.setBackground(new java.awt.Color(239, 235, 233));
        panelEstadoDelLibro.setPreferredSize(new java.awt.Dimension(350, 77));

        jLabel10.setBackground(new java.awt.Color(241, 241, 241));
        jLabel10.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("Estado del libro:");

        chkObsoleto.setBackground(new java.awt.Color(239, 235, 233));
        chkObsoleto.setForeground(new java.awt.Color(51, 51, 51));
        chkObsoleto.setToolTipText("");
        chkObsoleto.setCheckColor(new java.awt.Color(51, 51, 51));
        chkObsoleto.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        chkObsoleto.setOpaque(true);
        chkObsoleto.setPreferredSize(new java.awt.Dimension(338, 28));
        chkObsoleto.setTextColor(new java.awt.Color(51, 51, 51));

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setText("Obsoleto");

        javax.swing.GroupLayout panelEstadoDelLibroLayout = new javax.swing.GroupLayout(panelEstadoDelLibro);
        panelEstadoDelLibro.setLayout(panelEstadoDelLibroLayout);
        panelEstadoDelLibroLayout.setHorizontalGroup(
            panelEstadoDelLibroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEstadoDelLibroLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(panelEstadoDelLibroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelEstadoDelLibroLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelEstadoDelLibroLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(chkObsoleto, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelEstadoDelLibroLayout.setVerticalGroup(
            panelEstadoDelLibroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEstadoDelLibroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelEstadoDelLibroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkObsoleto, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        panelInferior.add(panelEstadoDelLibro);

        javax.swing.GroupLayout panelGeneralSuperiorLayout = new javax.swing.GroupLayout(panelGeneralSuperior);
        panelGeneralSuperior.setLayout(panelGeneralSuperiorLayout);
        panelGeneralSuperiorLayout.setHorizontalGroup(
            panelGeneralSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGeneralSuperiorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelGeneralSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelSuperior, javax.swing.GroupLayout.DEFAULT_SIZE, 615, Short.MAX_VALUE)
                    .addComponent(panelMedio, javax.swing.GroupLayout.DEFAULT_SIZE, 615, Short.MAX_VALUE)
                    .addComponent(panelInferior, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
        );
        panelGeneralSuperiorLayout.setVerticalGroup(
            panelGeneralSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGeneralSuperiorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelSuperior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelMedio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(89, 89, 89)
                .addComponent(panelInferior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 4, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelLibro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelGeneralSuperior, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(270, 270, 270)
                    .addComponent(panelCodigoBarras, javax.swing.GroupLayout.DEFAULT_SIZE, 561, Short.MAX_VALUE)
                    .addGap(16, 16, 16)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelGeneralSuperior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelLibro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(16, 16, 16)
                    .addComponent(panelCodigoBarras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(543, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
    private com.mommoo.flat.select.FlatCheckBox chkObsoleto;
    private javax.swing.JLabel imgLibro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
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
    private javax.swing.JPanel panelCodigoBarras;
    private javax.swing.JPanel panelCurso;
    private javax.swing.JPanel panelEstadoDelLibro;
    private javax.swing.JPanel panelGeneralSuperior;
    private javax.swing.JPanel panelISBN;
    private javax.swing.JPanel panelInferior;
    private javax.swing.JPanel panelLibro;
    private javax.swing.JPanel panelMedio;
    private javax.swing.JPanel panelNombre;
    private javax.swing.JPanel panelPrecio;
    private javax.swing.JPanel panelSuperior;
    private javax.swing.JPanel panelUnidades;
    private javax.swing.JTable tablaHistorial;
    private javax.swing.JLabel textEmailAlumno;
    private javax.swing.JLabel textNIAAlumno;
    private javax.swing.JLabel textNombreAlumno;
    private javax.swing.JTextField textPrecio;
    private javax.swing.JLabel textTelefonoAlumno;
    private javax.swing.JTextField textUnidadesLibro;
    // End of variables declaration//GEN-END:variables
}
