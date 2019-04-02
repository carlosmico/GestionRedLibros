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
import Renders.comboBoxRender;
import excepciones.BusinessException;
import java.awt.Component;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.persistence.PersistenceException;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jose Sanchis
 */
public class FrameLibro extends javax.swing.JFrame {

    /**
     * Creates new form FrameDevoluciones
     */
    private boolean isEditMode;

    private static Libro libro = null;
    boolean isNewLibro;

    private FrameCarga c = new FrameCarga();

    FrameCarga frameCarga;
    List<Curso> listaCursos;
    List<Contenido> listaContenido;

    DaoCurso daoCurso;
    static DaoLibro daoLibro;
    DaoContenido daoContenido;

    public FrameLibro(Libro libro) {
        initComponents();

        //<editor-fold defaultstate="collapsed" desc="Configuracion inicial de los ComboBox">
        //Combo Box Curso
        cbCurso.setEditable(false);
        cbCurso.setUI(new comboBoxRender());
        cbCurso.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(
                    JList list, Object value, int index,
                    boolean isSelected, boolean hasFocus) {
                JLabel l = (JLabel) super.getListCellRendererComponent(
                        list, value, index, isSelected, hasFocus);
                if (isSelected) {
                    l.setForeground(Colores.fondo);
                    l.setBackground(Colores.buttons);
                } else {
                    l.setForeground(Colores.buttons);
                    l.setBackground(Colores.fondo);
                }
                return l;
            }
        });

        cbAsignatura.setEditable(false);
        cbAsignatura.setUI(new comboBoxRender());
        cbAsignatura.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(
                    JList list, Object value, int index,
                    boolean isSelected, boolean hasFocus) {
                JLabel l = (JLabel) super.getListCellRendererComponent(
                        list, value, index, isSelected, hasFocus);
                if (isSelected) {
                    l.setForeground(Colores.fondo);
                    l.setBackground(Colores.buttons);
                } else {
                    l.setForeground(Colores.buttons);
                    l.setBackground(Colores.fondo);
                }
                return l;
            }
        });
//</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Configuración inicial de la tabla">
        tableEjemplares.setFont(textNombreLibro.getFont());
//</editor-fold>

        this.libro = libro;
        this.isNewLibro = this.libro == null;

        btnDelete.setVisible(false);

        //Deshabilitamos la tabla de ejemplares puesto que es de lectura
        tableEjemplares.setEnabled(false);
        btnSave.setVisible(isNewLibro);

        this.setLocationRelativeTo(null);

        daoCurso = new DaoCurso();
        daoLibro = new DaoLibro();
        daoContenido = new DaoContenido();

        SwingWorker<?, ?> worker = new SwingWorker<Void, Integer>() {
            protected Void doInBackground() throws InterruptedException {
                setEnabled(false);
                listaCursos = daoCurso.buscarTodos();
                listaContenido = daoContenido.buscarTodos();
                return null;
            }

            protected void process(List<Integer> chunks) {
            }

            protected void done() {
                //Rellenamos la lista de los libros
                //<editor-fold defaultstate="collapsed" desc="Rellenamos los datos en el caso de que consultemos algun libro o no">

                setEditMode(isNewLibro);

                btnEdit.setEnabled(!isNewLibro);

                //</editor-fold>
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
            int ran = (int) (Math.floor(Math.random() * 4));
            System.out.println("Resultado: " + ran);
            imgLibro.setIcon(new ImageIcon(getClass().getResource("/Imagenes/image" + ran + ".png")));
        } catch (Exception ex) {
            imgLibro.setText("\nNo se ha podido cargar la imagen del libro");
            Logger.getLogger(FrameLibro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void rellenarCampos() {

        if (listaCursos.size() > 0) {
            for (int i = 0; i < listaCursos.size(); i++) {
                cbCurso.addItem(listaCursos.get(i).getAbreviatura());
            }
        }

        if (isNewLibro) {
            textNombreLibro.setText("");
            textISBNLibro.setText("");

            textUnidadesLibro.setText("");

            textCodigoDeBarrasLibro.setText("");
            chkObsoleto.setChecked(!isNewLibro);
        } else {
            //Rellenamos los datos
            textNombreLibro.setText(libro.getNombre());
            textISBNLibro.setText(libro.getISBN());

            for (int i = 0; i < cbCurso.getItemCount(); i++) {
                if (libro.getContenido().getCurso().getAbreviatura().equals(cbCurso.getItemAt(i).toString())) {
                    cbCurso.setSelectedIndex(i);
                    break;
                }
            }

            cbAsignatura.addItem(libro.getContenido().getNombre_cas());
            textUnidadesLibro.setText(libro.getUnidades() + "");

            textCodigoDeBarrasLibro.setText(libro.getCodigo());
            chkObsoleto.setChecked(libro.getObsoleto());

            //Refrescamos la tabla de ejemplares
            RefrescarTabla();
        }
    }

    //Refrescamos los datos de la tabla recuperados de la BD
    private void RefrescarTabla() {
        List<Ejemplar> ejemplares = libro.getEjemplares();

        System.out.println("Ejemplares: " + libro.getEjemplares().size());

        if (ejemplares.size() > 0) {
            DefaultTableModel tableModel = (DefaultTableModel) this.tableEjemplares.getModel();

            tableModel.setRowCount(0);

            for (int i = 0; i < ejemplares.size(); i++) {
                Ejemplar ejemplar = ejemplares.get(i);
                String[] fila = new String[13];

                fila[0] = ejemplar.getCodigo();

                switch (ejemplar.getEstado()) {
                    case Estado.deteriorado:
                        fila[1] = "Deteriorado";
                        break;

                    case Estado.usado:
                        fila[1] = "Usado";
                        break;

                    case Estado.nuevo:
                        fila[1] = "Nuevo";
                        break;
                }

                if (ejemplar.isPrestado()) {
                    fila[2] = "Si";
                } else {
                    fila[2] = "No";
                }

                tableModel.addRow(fila);
            }

            tableEjemplares.setModel(tableModel);

            tableEjemplares.repaint();

        } else {
            JOptionPane.showMessageDialog(this, "No hay datos de ejemplares en la Base de Datos.");
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
        btnEdit = new com.mommoo.flat.button.FlatButton();
        btnSave = new com.mommoo.flat.button.FlatButton();
        btnDelete = new com.mommoo.flat.button.FlatButton();
        jPanel3 = new javax.swing.JPanel();
        panelLibro = new javax.swing.JPanel();
        imgLibro = new javax.swing.JLabel();
        panelCodigoBarras = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableEjemplares = new javax.swing.JTable();
        btnImprimirEtiquetas = new com.mommoo.flat.button.FlatButton();
        panelGeneralSuperior = new javax.swing.JPanel();
        panelCodigoDeBarras1 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        textCodigoDeBarrasLibro = new javax.swing.JTextField();
        panelSuperior = new javax.swing.JPanel();
        panelNombre = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        textNombreLibro = new javax.swing.JTextField();
        panelISBN = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        textISBNLibro = new javax.swing.JTextField();
        panelMedio = new javax.swing.JPanel();
        panelCurso = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        cbCurso = new javax.swing.JComboBox();
        panelAsignatura = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        cbAsignatura = new javax.swing.JComboBox();
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
        jLabel1.setText("Libros");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        btnEdit.setBackground(new java.awt.Color(66, 47, 44));
        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons/pencil.png"))); // NOI18N
        btnEdit.setCornerRound(10);
        btnEdit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnEditMouseReleased(evt);
            }
        });

        btnSave.setBackground(new java.awt.Color(102, 225, 115));
        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons/check.png"))); // NOI18N
        btnSave.setCornerRound(10);
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnDelete.setBackground(new java.awt.Color(255, 66, 62));
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons/delete-empty.png"))); // NOI18N
        btnDelete.setCornerRound(10);
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(81, 81, 81)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 766, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSave, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(239, 235, 233));

        panelLibro.setBackground(new java.awt.Color(255, 255, 255));
        panelLibro.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        imgLibro.setForeground(new java.awt.Color(51, 51, 51));
        imgLibro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/image1.png"))); // NOI18N

        javax.swing.GroupLayout panelLibroLayout = new javax.swing.GroupLayout(panelLibro);
        panelLibro.setLayout(panelLibroLayout);
        panelLibroLayout.setHorizontalGroup(
            panelLibroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLibroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imgLibro)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelLibroLayout.setVerticalGroup(
            panelLibroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLibroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imgLibro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelCodigoBarras.setBackground(new java.awt.Color(239, 235, 233));
        panelCodigoBarras.setLayout(new java.awt.GridLayout(1, 2));

        jPanel2.setBackground(new java.awt.Color(239, 235, 233));

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setText("Ejemplares:");

        tableEjemplares.setBackground(new java.awt.Color(239, 235, 233));
        tableEjemplares.setForeground(new java.awt.Color(51, 51, 51));
        tableEjemplares.setModel(new javax.swing.table.DefaultTableModel(
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
        tableEjemplares.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tableEjemplares.setFillsViewportHeight(true);
        jScrollPane1.setViewportView(tableEjemplares);

        btnImprimirEtiquetas.setBackground(Colores.buttons);
        btnImprimirEtiquetas.setText("Imprimir etiquetas");
        btnImprimirEtiquetas.setCornerRound(10);
        btnImprimirEtiquetas.setPreferredSize(new java.awt.Dimension(111, 32));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnImprimirEtiquetas, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnImprimirEtiquetas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelGeneralSuperior.setBackground(new java.awt.Color(239, 235, 233));

        panelCodigoDeBarras1.setBackground(new java.awt.Color(239, 235, 233));
        panelCodigoDeBarras1.setPreferredSize(new java.awt.Dimension(350, 77));

        jLabel12.setBackground(new java.awt.Color(241, 241, 241));
        jLabel12.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setText("Código de barras:");

        textCodigoDeBarrasLibro.setBackground(new java.awt.Color(239, 235, 233));
        textCodigoDeBarrasLibro.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        textCodigoDeBarrasLibro.setForeground(new java.awt.Color(51, 51, 51));
        textCodigoDeBarrasLibro.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        textCodigoDeBarrasLibro.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout panelCodigoDeBarras1Layout = new javax.swing.GroupLayout(panelCodigoDeBarras1);
        panelCodigoDeBarras1.setLayout(panelCodigoDeBarras1Layout);
        panelCodigoDeBarras1Layout.setHorizontalGroup(
            panelCodigoDeBarras1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCodigoDeBarras1Layout.createSequentialGroup()
                .addComponent(jLabel12)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(panelCodigoDeBarras1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(textCodigoDeBarrasLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelCodigoDeBarras1Layout.setVerticalGroup(
            panelCodigoDeBarras1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCodigoDeBarras1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textCodigoDeBarrasLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelSuperior.setBackground(new java.awt.Color(239, 235, 233));
        panelSuperior.setPreferredSize(new java.awt.Dimension(820, 77));
        panelSuperior.setLayout(new java.awt.GridLayout(1, 2));

        panelNombre.setBackground(new java.awt.Color(239, 235, 233));

        jLabel2.setBackground(new java.awt.Color(241, 241, 241));
        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Nombre:");

        textNombreLibro.setBackground(new java.awt.Color(239, 235, 233));
        textNombreLibro.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        textNombreLibro.setForeground(new java.awt.Color(51, 51, 51));
        textNombreLibro.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        textNombreLibro.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout panelNombreLayout = new javax.swing.GroupLayout(panelNombre);
        panelNombre.setLayout(panelNombreLayout);
        panelNombreLayout.setHorizontalGroup(
            panelNombreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNombreLayout.createSequentialGroup()
                .addGroup(panelNombreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelNombreLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 307, Short.MAX_VALUE))
                    .addGroup(panelNombreLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(textNombreLibro)))
                .addContainerGap())
        );
        panelNombreLayout.setVerticalGroup(
            panelNombreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNombreLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textNombreLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelSuperior.add(panelNombre);

        panelISBN.setBackground(new java.awt.Color(239, 235, 233));

        jLabel3.setBackground(new java.awt.Color(241, 241, 241));
        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("ISBN:");

        textISBNLibro.setBackground(new java.awt.Color(239, 235, 233));
        textISBNLibro.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        textISBNLibro.setForeground(new java.awt.Color(51, 51, 51));
        textISBNLibro.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        textISBNLibro.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout panelISBNLayout = new javax.swing.GroupLayout(panelISBN);
        panelISBN.setLayout(panelISBNLayout);
        panelISBNLayout.setHorizontalGroup(
            panelISBNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelISBNLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelISBNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelISBNLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(textISBNLibro, javax.swing.GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE))
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
                .addComponent(textISBNLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelSuperior.add(panelISBN);

        panelMedio.setBackground(new java.awt.Color(239, 235, 233));
        panelMedio.setPreferredSize(new java.awt.Dimension(820, 77));
        panelMedio.setLayout(new java.awt.GridLayout(1, 2));

        panelCurso.setBackground(new java.awt.Color(239, 235, 233));

        jLabel7.setBackground(new java.awt.Color(241, 241, 241));
        jLabel7.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("Curso:");

        cbCurso.setBackground(new java.awt.Color(239, 235, 233));
        cbCurso.setEditable(true);
        cbCurso.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        cbCurso.setForeground(new java.awt.Color(51, 51, 51));
        cbCurso.setBorder(null);
        cbCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCursoActionPerformed(evt);
            }
        });

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
                        .addComponent(cbCurso, 0, 378, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelCursoLayout.setVerticalGroup(
            panelCursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCursoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbCurso)
                .addContainerGap())
        );

        panelMedio.add(panelCurso);

        panelAsignatura.setBackground(new java.awt.Color(239, 235, 233));

        jLabel5.setBackground(new java.awt.Color(241, 241, 241));
        jLabel5.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setText("Asignatura:");

        cbAsignatura.setBackground(new java.awt.Color(239, 235, 233));
        cbAsignatura.setEditable(true);
        cbAsignatura.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        cbAsignatura.setForeground(new java.awt.Color(51, 51, 51));
        cbAsignatura.setToolTipText("");
        cbAsignatura.setBorder(null);

        javax.swing.GroupLayout panelAsignaturaLayout = new javax.swing.GroupLayout(panelAsignatura);
        panelAsignatura.setLayout(panelAsignaturaLayout);
        panelAsignaturaLayout.setHorizontalGroup(
            panelAsignaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAsignaturaLayout.createSequentialGroup()
                .addComponent(jLabel5)
                .addGap(0, 289, Short.MAX_VALUE))
            .addGroup(panelAsignaturaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbAsignatura, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelAsignaturaLayout.setVerticalGroup(
            panelAsignaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAsignaturaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbAsignatura, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelMedio.add(panelAsignatura);

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
                .addContainerGap(54, Short.MAX_VALUE))
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
                .addContainerGap(54, Short.MAX_VALUE))
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
                        .addGap(0, 93, Short.MAX_VALUE))
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
                    .addGroup(panelGeneralSuperiorLayout.createSequentialGroup()
                        .addComponent(panelCodigoDeBarras1, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(panelSuperior, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(panelMedio, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(panelInferior, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelGeneralSuperiorLayout.setVerticalGroup(
            panelGeneralSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGeneralSuperiorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelCodigoDeBarras1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelSuperior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelMedio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelInferior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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
                    .addComponent(panelCodigoBarras, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                    .addGap(16, 16, 16)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelGeneralSuperior, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelLibro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void btnEditMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditMouseReleased
        // TODO add your handling code here:
        if (btnEdit.isEnabled()) {
            isEditMode = !isEditMode;
            setEditMode(isEditMode);
            if (isEditMode) {
                btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons/close.png")));
                textCodigoDeBarrasLibro.setEditable(false);
            } else {
                rellenarCampos();
                btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons/pencil.png")));
            }

            btnDelete.setVisible(isEditMode);
            btnSave.setVisible(isEditMode);
        }
    }//GEN-LAST:event_btnEditMouseReleased

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        setEnabled(false);
        int resp = JOptionPane.showConfirmDialog(this, "Realmente deseas eliminar este libro?"
                + "\nEsta operación es irreversible.", "Información", 0, JOptionPane.INFORMATION_MESSAGE,
                null);

        if (resp == JOptionPane.YES_OPTION) {
            try {
                daoLibro.borrar(libro);

                JOptionPane.showMessageDialog(this, "Libro eliminado correctamente.", "Información", JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al eliminar el libro.", "Error", JOptionPane.ERROR_MESSAGE);
            }

            this.dispose();
        }

        setEnabled(true);
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:

        String errores = "";

        if (isNewLibro) {
            //Creacion de un nuevo libro
            if (textNombreLibro.getText().equals("")) {
                errores += "\n- El nombre no puede estar vacío.";
            }

            if (textISBNLibro.getText().equals("")) {
                errores += "\n- El ISBN no puede estar vacío.";
            }

            if (textUnidadesLibro.getText().equals("")) {
                errores += "\n- El campo de las unidades no puede estar vacío.";
            }

            try {
                int un = Integer.parseInt(textUnidadesLibro.getText());
                if (un <= 0) {
                    errores += "\n- El valor de las unidades debe ser un valor positivo.";
                }
            } catch (Exception e) {
                errores += "\n- El valor de las unidades debe ser un valor numérico.";
            }

            try {
                double pre = Double.parseDouble(textPrecio.getText());
                if (pre <= 0) {
                    errores += "\n- El precio debe ser un valor positivo.";
                }
            } catch (Exception e) {
                errores += "\n- El precio debe ser un valor numérico.";
            }

            if (textCodigoDeBarrasLibro.getText().equals("")) {
                errores += "\n- El código del libro no puede estar vacío.";
            }

            if (cbAsignatura.getSelectedItem().toString().equals("Seleccione curso")) {
                errores += "\n- Debe seleccionar una asignatura válida.";
            }

            if (errores.equals("")) {
                //Creamos el libro si el string de los errores esta vacío, es decir, si no hay errores
                Libro newLibro = new Libro();

                newLibro.setCodigo(textCodigoDeBarrasLibro.getText());
                newLibro.setISBN(textISBNLibro.getText());
                newLibro.setNombre(textNombreLibro.getText());
                newLibro.setObsoleto(chkObsoleto.isChecked());
                newLibro.setPrecio(Double.parseDouble(textPrecio.getText()));
                newLibro.setUnidades(Integer.parseInt(textUnidadesLibro.getText()));

                for (int i = 0; i < listaContenido.size(); i++) {
                    if (listaContenido.get(i).getNombre_cas().equals(cbAsignatura.getSelectedItem().toString())) {
                        newLibro.setContenido(listaContenido.get(i));
                        break;
                    }
                }

                try {
                    daoLibro.grabar(newLibro);

                    JOptionPane.showMessageDialog(this, "Libro añadido correctamente.",
                            "Información", JOptionPane.INFORMATION_MESSAGE);

                    setEditMode(false);

                    libro = newLibro;

                    isNewLibro = false;

                } catch (PersistenceException e) {

                    JOptionPane.showMessageDialog(this,
                            "El libro ya existe en la Base de Datos.", "Error",
                            JOptionPane.ERROR_MESSAGE);

                } catch (Exception e) {

                    JOptionPane.showMessageDialog(this,
                            "Error al actualizar el libro.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this,
                        "Revise los siguientes errores: \n" + errores, "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

        } else {
            //Modificacion de un libro existente

            if (textNombreLibro.getText().equals("")) {
                errores += "\n- El nombre no puede estar vacío.";
            }

            if (textISBNLibro.getText().equals("")) {
                errores += "\n- El ISBN no puede estar vacío.";
            }

            try {
                if (textUnidadesLibro.getText().equals("")) {
                    errores += "\n- El campo de las unidades no puede estar vacío.";
                } else {
                    int un = Integer.parseInt(textUnidadesLibro.getText());
                    if (un <= 0) {
                        errores += "\n- El valor de las unidades debe ser un valor positivo.";
                    }
                }
            } catch (Exception e) {
                errores += "\n- El valor de las unidades debe ser un valor númerico.";
            }

            if (textCodigoDeBarrasLibro.getText().equals("")) {
                errores += "\n- El código del libro no puede estar vacío.";
            }

            if (cbAsignatura.getSelectedItem().toString().equals("Seleccione curso")) {
                errores += "\n- Debe seleccionar una asignatura válida.";
            }

            if (errores.equals("")) {
                //Creamos el libro si el string de los errores esta vacío, es decir, si no hay errores

                libro.setCodigo(textCodigoDeBarrasLibro.getText());
                libro.setISBN(textISBNLibro.getText());
                libro.setNombre(textNombreLibro.getText());
                libro.setObsoleto(chkObsoleto.isChecked());
                libro.setUnidades(Integer.parseInt(textUnidadesLibro.getText()));

                for (int i = 0; i < listaContenido.size(); i++) {
                    if (listaContenido.get(i).getNombre_cas().equals(cbAsignatura.getSelectedItem().toString())) {
                        libro.setContenido(listaContenido.get(i));
                        break;
                    }
                }

                try {
                    daoLibro.actualizar(libro);

                    JOptionPane.showMessageDialog(this,
                            "Libro actualizado correctamente.", "Información",
                            JOptionPane.INFORMATION_MESSAGE);

                    setEditMode(false);
                } catch (PersistenceException e) {
                    JOptionPane.showMessageDialog(this,
                            "El libro ya existe en la Base de Datos.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this,
                            "Error al crear el libro.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this,
                        "Revise los siguientes errores: \n" + errores, "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        RefrescarTabla();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void cbCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCursoActionPerformed
        // TODO add your handling code here:
        if (isNewLibro || isEditMode) {
            List<Contenido> listaContenidoTemp = listaContenido;

            listaContenidoTemp = listaContenido.stream().filter(a -> a.getCurso().getAbreviatura().equals(cbCurso.getSelectedItem().toString())).collect(Collectors.toList());
            System.out.println("Size: " + listaContenidoTemp.size());

            System.out.println("Curso seleccionado: " + cbCurso.getSelectedItem().toString());
            System.out.println("----------------------");
            for (int i = 0; i < listaContenidoTemp.size(); i++) {
                if (listaContenidoTemp.get(i).getCurso().getAbreviatura().equals(listaContenidoTemp.get(i).getNombre_cas())) {
                    System.out.println("\tContenido: " + listaContenidoTemp.get(i).getNombre_cas());
                    System.out.println("\tCurso: " + listaContenidoTemp.get(i).getCurso().getAbreviatura());
                    System.out.println("----------------------");
                }
            }

            cbAsignatura.removeAllItems();
            if (listaContenidoTemp.size() > 0) {
                for (int i = 0; i < listaContenidoTemp.size(); i++) {
                    cbAsignatura.addItem(listaContenidoTemp.get(i).getNombre_cas());
                }
            } else {
                cbAsignatura.addItem("Seleccione curso");
            }
        }
    }//GEN-LAST:event_cbCursoActionPerformed

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
            java.util.logging.Logger.getLogger(FrameLibro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameLibro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameLibro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameLibro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameLibro(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.mommoo.flat.button.FlatButton btnDelete;
    private com.mommoo.flat.button.FlatButton btnEdit;
    private com.mommoo.flat.button.FlatButton btnImprimirEtiquetas;
    private com.mommoo.flat.button.FlatButton btnSave;
    private javax.swing.JComboBox cbAsignatura;
    private javax.swing.JComboBox cbCurso;
    private com.mommoo.flat.select.FlatCheckBox chkObsoleto;
    private javax.swing.JLabel imgLibro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JPanel panelCodigoDeBarras1;
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
    private javax.swing.JTable tableEjemplares;
    private javax.swing.JTextField textCodigoDeBarrasLibro;
    private javax.swing.JTextField textISBNLibro;
    private javax.swing.JTextField textNombreLibro;
    private javax.swing.JTextField textPrecio;
    private javax.swing.JTextField textUnidadesLibro;
    // End of variables declaration//GEN-END:variables

    public void setEditMode(boolean editable) {
        textNombreLibro.setEditable(editable);
        textISBNLibro.setEditable(editable);
        cbCurso.setEditable(editable);
        cbCurso.setEnabled(editable);
        cbAsignatura.setEditable(editable);
        cbAsignatura.setEnabled(editable);
        textUnidadesLibro.setEditable(editable);
        chkObsoleto.setEnabled(editable);
        btnImprimirEtiquetas.setEnabled(editable);
        textPrecio.setEnabled(editable);
        btnSave.setVisible(editable);

        if (isNewLibro) {
            btnDelete.setVisible(false);
            textCodigoDeBarrasLibro.setEditable(editable);
        } else {
            btnDelete.setVisible(editable);
            textCodigoDeBarrasLibro.setEditable(false);
        }
    }
}
