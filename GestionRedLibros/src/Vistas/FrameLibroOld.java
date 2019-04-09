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
import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.persistence.PersistenceException;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jose Sanchis
 */
public class FrameLibroOld extends javax.swing.JFrame {

    /**
     * Creates new form FrameDevoluciones
     */
    private boolean isEditMode;

    private static Libro libro = null;
    boolean isNewLibro;

    private FramePopup c = new FramePopup();

    FramePopup frameCarga;

    List<Curso> listaCursos;
    List<Contenido> listaContenido;

    DaoCurso daoCurso;
    DaoLibro daoLibro;
    DaoContenido daoContenido;

    public FrameLibroOld(String codigo) {
        initComponents();
        
        //Inicializamos los DAO obteniendo la sesion del Gestor del Main
        //daoCurso = new DaoCurso(Main.gestorSesiones.getSession());
        //daoLibro = new DaoLibro(Main.gestorSesiones.getSession());
        //daoContenido = new DaoContenido(Main.gestorSesiones.getSession());

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

        this.isNewLibro = codigo == null;
        
        if(!isNewLibro){
            this.libro = daoLibro.buscar(codigo);
        }

        btnDelete.setVisible(false);

        //Deshabilitamos la tabla de ejemplares puesto que es de lectura
        btnSave.setVisible(isNewLibro);

        this.setLocationRelativeTo(null);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);


        /*SwingWorker<?, ?> worker = new SwingWorker<Void, Integer>() {
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

                setEditMode(isNewLibro);
                btnEdit.setEnabled(!isNewLibro);

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
        */
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
            textPrecio.setText(libro.getPrecio() + "");

            textCodigoDeBarrasLibro.setText(libro.getCodigo());
            chkObsoleto.setChecked(libro.getObsoleto());

            //Refrescamos la tabla de ejemplares
            //RefrescarTabla();
        }
    }

    //Refrescamos los datos de la tabla recuperados de la BD
    /*private void RefrescarTabla() {
        List<Ejemplar> ejemplares = libro.getEjemplares();

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
            frameCarga.dispose();
            btnImprimirEtiquetas.setVisible(false);
            JOptionPane.showMessageDialog(null, "No hay datos de ejemplares de este libro.");
        }
    }*/

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jSplitPane1 = new javax.swing.JSplitPane();
        panelDerecho = new javax.swing.JPanel();
        panelDerechoSuperior = new javax.swing.JPanel();
        panelTituloLibro = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnEdit = new com.mommoo.flat.button.FlatButton();
        btnSave = new com.mommoo.flat.button.FlatButton();
        btnDelete = new com.mommoo.flat.button.FlatButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        panelGeneralSuperior1 = new javax.swing.JPanel();
        panelCodigoDeBarras2 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        textCodigoDeBarrasLibro = new javax.swing.JTextField();
        panelSuperior1 = new javax.swing.JPanel();
        panelNombre1 = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        textNombreLibro = new javax.swing.JTextField();
        panelISBN1 = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        textISBNLibro = new javax.swing.JTextField();
        panelMedio1 = new javax.swing.JPanel();
        panelCurso1 = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        cbCurso = new javax.swing.JComboBox();
        panelAsignatura1 = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        cbAsignatura = new javax.swing.JComboBox();
        panelInferior1 = new javax.swing.JPanel();
        panelUnidades1 = new javax.swing.JPanel();
        jLabel43 = new javax.swing.JLabel();
        textUnidadesLibro = new javax.swing.JTextField();
        panelPrecio1 = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        textPrecio = new javax.swing.JTextField();
        panelEstadoDelLibro1 = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        chkObsoleto = new com.mommoo.flat.select.FlatCheckBox();
        jLabel46 = new javax.swing.JLabel();
        panelDerechoInferior = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        btnEjemplarSiguiente = new com.mommoo.flat.button.FlatButton();
        btnEjemplarAnterior = new com.mommoo.flat.button.FlatButton();
        panelTitulo = new javax.swing.JPanel();
        textTitulo = new javax.swing.JLabel();
        textEjemplarIndice = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jPanel14 = new javax.swing.JPanel();
        panelCodigo1 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        textCodigoEjemplar1 = new javax.swing.JLabel();
        flatButton4 = new com.mommoo.flat.button.FlatButton();
        panelEstadoParent15 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        panelEstado15 = new javax.swing.JPanel();
        btnBadStatus15 = new javax.swing.JLabel();
        btnRegularStatus15 = new javax.swing.JLabel();
        btnGoodStatus15 = new javax.swing.JLabel();
        panelNoPrestado = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        panelPrestado = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        textTelefonoAlumno = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        textNIAAlumno = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        textNombreAlumno = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        textEmailAlumno = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        btnVerAlumno = new com.mommoo.flat.button.FlatButton();
        panelIzquierdo = new javax.swing.JPanel();
        bodyListado = new javax.swing.JPanel();
        textTitle1 = new javax.swing.JLabel();
        textNombreLibro1 = new javax.swing.JTextField();
        cbCurso1 = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        jlistResultadoLibros = new javax.swing.JList();

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
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jSplitPane1.setMaximumSize(new java.awt.Dimension(1920, 1080));
        jSplitPane1.setMinimumSize(new java.awt.Dimension(0, 0));
        jSplitPane1.setPreferredSize(new java.awt.Dimension(1920, 1080));

        panelDerecho.setMaximumSize(new java.awt.Dimension(1920, 1080));
        panelDerecho.setPreferredSize(new java.awt.Dimension(1920, 1080));

        panelDerechoSuperior.setMaximumSize(new java.awt.Dimension(1920, 1080));
        panelDerechoSuperior.setPreferredSize(new java.awt.Dimension(1321, 416));

        panelTituloLibro.setBackground(new java.awt.Color(58, 39, 35));
        panelTituloLibro.setPreferredSize(new java.awt.Dimension(320, 75));

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Libros");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        btnEdit.setBackground(new java.awt.Color(66, 47, 44));
        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons/pencil.png"))); // NOI18N
        btnEdit.setCornerRound(10);
        btnEdit.setMaximumSize(new java.awt.Dimension(63, 63));
        btnEdit.setPreferredSize(new java.awt.Dimension(63, 63));
        btnEdit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnEditMouseReleased(evt);
            }
        });

        btnSave.setBackground(new java.awt.Color(102, 225, 115));
        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons/check.png"))); // NOI18N
        btnSave.setCornerRound(10);
        btnSave.setMaximumSize(new java.awt.Dimension(63, 63));
        btnSave.setPreferredSize(new java.awt.Dimension(63, 63));
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnDelete.setBackground(new java.awt.Color(255, 66, 62));
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons/delete.png"))); // NOI18N
        btnDelete.setCornerRound(10);
        btnDelete.setMaximumSize(new java.awt.Dimension(63, 63));
        btnDelete.setPreferredSize(new java.awt.Dimension(63, 63));
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelTituloLibroLayout = new javax.swing.GroupLayout(panelTituloLibro);
        panelTituloLibro.setLayout(panelTituloLibroLayout);
        panelTituloLibroLayout.setHorizontalGroup(
            panelTituloLibroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTituloLibroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(131, 131, 131)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 707, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 276, Short.MAX_VALUE)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelTituloLibroLayout.setVerticalGroup(
            panelTituloLibroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTituloLibroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTituloLibroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelTituloLibroLayout.createSequentialGroup()
                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jScrollPane4.setMinimumSize(new java.awt.Dimension(0, 0));

        panelGeneralSuperior1.setBackground(new java.awt.Color(239, 235, 233));
        panelGeneralSuperior1.setPreferredSize(new java.awt.Dimension(1036, 350));

        panelCodigoDeBarras2.setBackground(new java.awt.Color(239, 235, 233));
        panelCodigoDeBarras2.setPreferredSize(new java.awt.Dimension(350, 77));

        jLabel38.setBackground(new java.awt.Color(241, 241, 241));
        jLabel38.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(51, 51, 51));
        jLabel38.setText("Código de barras:");

        textCodigoDeBarrasLibro.setBackground(new java.awt.Color(239, 235, 233));
        textCodigoDeBarrasLibro.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        textCodigoDeBarrasLibro.setForeground(new java.awt.Color(51, 51, 51));
        textCodigoDeBarrasLibro.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        textCodigoDeBarrasLibro.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout panelCodigoDeBarras2Layout = new javax.swing.GroupLayout(panelCodigoDeBarras2);
        panelCodigoDeBarras2.setLayout(panelCodigoDeBarras2Layout);
        panelCodigoDeBarras2Layout.setHorizontalGroup(
            panelCodigoDeBarras2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCodigoDeBarras2Layout.createSequentialGroup()
                .addComponent(jLabel38)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(panelCodigoDeBarras2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(textCodigoDeBarrasLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelCodigoDeBarras2Layout.setVerticalGroup(
            panelCodigoDeBarras2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCodigoDeBarras2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel38)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textCodigoDeBarrasLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelSuperior1.setBackground(new java.awt.Color(239, 235, 233));
        panelSuperior1.setPreferredSize(new java.awt.Dimension(820, 77));
        panelSuperior1.setLayout(new java.awt.GridLayout(1, 2));

        panelNombre1.setBackground(new java.awt.Color(239, 235, 233));

        jLabel39.setBackground(new java.awt.Color(241, 241, 241));
        jLabel39.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(51, 51, 51));
        jLabel39.setText("Nombre:");

        textNombreLibro.setBackground(new java.awt.Color(239, 235, 233));
        textNombreLibro.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        textNombreLibro.setForeground(new java.awt.Color(51, 51, 51));
        textNombreLibro.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        textNombreLibro.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout panelNombre1Layout = new javax.swing.GroupLayout(panelNombre1);
        panelNombre1.setLayout(panelNombre1Layout);
        panelNombre1Layout.setHorizontalGroup(
            panelNombre1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNombre1Layout.createSequentialGroup()
                .addGroup(panelNombre1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel39)
                    .addGroup(panelNombre1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(textNombreLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelNombre1Layout.setVerticalGroup(
            panelNombre1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNombre1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel39)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textNombreLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelSuperior1.add(panelNombre1);

        panelISBN1.setBackground(new java.awt.Color(239, 235, 233));

        jLabel40.setBackground(new java.awt.Color(241, 241, 241));
        jLabel40.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(51, 51, 51));
        jLabel40.setText("ISBN:");

        textISBNLibro.setBackground(new java.awt.Color(239, 235, 233));
        textISBNLibro.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        textISBNLibro.setForeground(new java.awt.Color(51, 51, 51));
        textISBNLibro.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        textISBNLibro.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout panelISBN1Layout = new javax.swing.GroupLayout(panelISBN1);
        panelISBN1.setLayout(panelISBN1Layout);
        panelISBN1Layout.setHorizontalGroup(
            panelISBN1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelISBN1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelISBN1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelISBN1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(textISBNLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel40))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelISBN1Layout.setVerticalGroup(
            panelISBN1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelISBN1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel40)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textISBNLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelSuperior1.add(panelISBN1);

        panelMedio1.setBackground(new java.awt.Color(239, 235, 233));
        panelMedio1.setPreferredSize(new java.awt.Dimension(820, 77));
        panelMedio1.setLayout(new java.awt.GridLayout(1, 2));

        panelCurso1.setBackground(new java.awt.Color(239, 235, 233));

        jLabel41.setBackground(new java.awt.Color(241, 241, 241));
        jLabel41.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(51, 51, 51));
        jLabel41.setText("Curso:");

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

        javax.swing.GroupLayout panelCurso1Layout = new javax.swing.GroupLayout(panelCurso1);
        panelCurso1.setLayout(panelCurso1Layout);
        panelCurso1Layout.setHorizontalGroup(
            panelCurso1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCurso1Layout.createSequentialGroup()
                .addGroup(panelCurso1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCurso1Layout.createSequentialGroup()
                        .addComponent(jLabel41)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelCurso1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(cbCurso, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelCurso1Layout.setVerticalGroup(
            panelCurso1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCurso1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel41)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbCurso)
                .addContainerGap())
        );

        panelMedio1.add(panelCurso1);

        panelAsignatura1.setBackground(new java.awt.Color(239, 235, 233));

        jLabel42.setBackground(new java.awt.Color(241, 241, 241));
        jLabel42.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(51, 51, 51));
        jLabel42.setText("Asignatura:");

        cbAsignatura.setBackground(new java.awt.Color(239, 235, 233));
        cbAsignatura.setEditable(true);
        cbAsignatura.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        cbAsignatura.setForeground(new java.awt.Color(51, 51, 51));
        cbAsignatura.setToolTipText("");
        cbAsignatura.setBorder(null);

        javax.swing.GroupLayout panelAsignatura1Layout = new javax.swing.GroupLayout(panelAsignatura1);
        panelAsignatura1.setLayout(panelAsignatura1Layout);
        panelAsignatura1Layout.setHorizontalGroup(
            panelAsignatura1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAsignatura1Layout.createSequentialGroup()
                .addComponent(jLabel42)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(panelAsignatura1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbAsignatura, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelAsignatura1Layout.setVerticalGroup(
            panelAsignatura1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAsignatura1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel42)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbAsignatura)
                .addContainerGap())
        );

        panelMedio1.add(panelAsignatura1);

        panelInferior1.setBackground(new java.awt.Color(239, 235, 233));
        panelInferior1.setPreferredSize(new java.awt.Dimension(820, 77));
        panelInferior1.setLayout(new java.awt.GridLayout(1, 0));

        panelUnidades1.setBackground(new java.awt.Color(239, 235, 233));

        jLabel43.setBackground(new java.awt.Color(241, 241, 241));
        jLabel43.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(51, 51, 51));
        jLabel43.setText("Unidades:");

        textUnidadesLibro.setBackground(new java.awt.Color(239, 235, 233));
        textUnidadesLibro.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        textUnidadesLibro.setForeground(new java.awt.Color(51, 51, 51));
        textUnidadesLibro.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        textUnidadesLibro.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout panelUnidades1Layout = new javax.swing.GroupLayout(panelUnidades1);
        panelUnidades1.setLayout(panelUnidades1Layout);
        panelUnidades1Layout.setHorizontalGroup(
            panelUnidades1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUnidades1Layout.createSequentialGroup()
                .addGroup(panelUnidades1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel43)
                    .addGroup(panelUnidades1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(textUnidadesLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelUnidades1Layout.setVerticalGroup(
            panelUnidades1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUnidades1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel43)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textUnidadesLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelInferior1.add(panelUnidades1);

        panelPrecio1.setBackground(new java.awt.Color(239, 235, 233));

        jLabel44.setBackground(new java.awt.Color(241, 241, 241));
        jLabel44.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(51, 51, 51));
        jLabel44.setText("Precio:");

        textPrecio.setBackground(new java.awt.Color(239, 235, 233));
        textPrecio.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        textPrecio.setForeground(new java.awt.Color(51, 51, 51));
        textPrecio.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        textPrecio.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout panelPrecio1Layout = new javax.swing.GroupLayout(panelPrecio1);
        panelPrecio1.setLayout(panelPrecio1Layout);
        panelPrecio1Layout.setHorizontalGroup(
            panelPrecio1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrecio1Layout.createSequentialGroup()
                .addGroup(panelPrecio1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel44)
                    .addGroup(panelPrecio1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(textPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelPrecio1Layout.setVerticalGroup(
            panelPrecio1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrecio1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel44)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelInferior1.add(panelPrecio1);

        panelEstadoDelLibro1.setBackground(new java.awt.Color(239, 235, 233));
        panelEstadoDelLibro1.setPreferredSize(new java.awt.Dimension(350, 77));

        jLabel45.setBackground(new java.awt.Color(241, 241, 241));
        jLabel45.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(51, 51, 51));
        jLabel45.setText("Estado del libro:");

        chkObsoleto.setBackground(new java.awt.Color(239, 235, 233));
        chkObsoleto.setForeground(new java.awt.Color(51, 51, 51));
        chkObsoleto.setToolTipText("");
        chkObsoleto.setCheckColor(new java.awt.Color(51, 51, 51));
        chkObsoleto.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        chkObsoleto.setOpaque(true);
        chkObsoleto.setPreferredSize(new java.awt.Dimension(338, 28));
        chkObsoleto.setTextColor(new java.awt.Color(51, 51, 51));

        jLabel46.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(51, 51, 51));
        jLabel46.setText("Obsoleto");

        javax.swing.GroupLayout panelEstadoDelLibro1Layout = new javax.swing.GroupLayout(panelEstadoDelLibro1);
        panelEstadoDelLibro1.setLayout(panelEstadoDelLibro1Layout);
        panelEstadoDelLibro1Layout.setHorizontalGroup(
            panelEstadoDelLibro1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEstadoDelLibro1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(panelEstadoDelLibro1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelEstadoDelLibro1Layout.createSequentialGroup()
                        .addComponent(jLabel45)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelEstadoDelLibro1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(chkObsoleto, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelEstadoDelLibro1Layout.setVerticalGroup(
            panelEstadoDelLibro1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEstadoDelLibro1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel45)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelEstadoDelLibro1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkObsoleto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        panelInferior1.add(panelEstadoDelLibro1);

        javax.swing.GroupLayout panelGeneralSuperior1Layout = new javax.swing.GroupLayout(panelGeneralSuperior1);
        panelGeneralSuperior1.setLayout(panelGeneralSuperior1Layout);
        panelGeneralSuperior1Layout.setHorizontalGroup(
            panelGeneralSuperior1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGeneralSuperior1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelGeneralSuperior1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelCodigoDeBarras2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelSuperior1, javax.swing.GroupLayout.DEFAULT_SIZE, 1024, Short.MAX_VALUE)
                    .addComponent(panelMedio1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelInferior1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelGeneralSuperior1Layout.setVerticalGroup(
            panelGeneralSuperior1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGeneralSuperior1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelCodigoDeBarras2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelSuperior1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelMedio1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelInferior1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane4.setViewportView(panelGeneralSuperior1);

        javax.swing.GroupLayout panelDerechoSuperiorLayout = new javax.swing.GroupLayout(panelDerechoSuperior);
        panelDerechoSuperior.setLayout(panelDerechoSuperiorLayout);
        panelDerechoSuperiorLayout.setHorizontalGroup(
            panelDerechoSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelTituloLibro, javax.swing.GroupLayout.DEFAULT_SIZE, 1321, Short.MAX_VALUE)
        );
        panelDerechoSuperiorLayout.setVerticalGroup(
            panelDerechoSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDerechoSuperiorLayout.createSequentialGroup()
                .addComponent(panelTituloLibro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        jPanel3.setBackground(new java.awt.Color(255, 0, 102));

        jPanel1.setBackground(new java.awt.Color(58, 39, 35));

        btnEjemplarSiguiente.setBackground(Colores.buttons);
        btnEjemplarSiguiente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons/arrow-right.png"))); // NOI18N
        btnEjemplarSiguiente.setCornerRound(10);
        btnEjemplarSiguiente.setPreferredSize(new java.awt.Dimension(63, 63));
        btnEjemplarSiguiente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEjemplarSiguienteMouseClicked(evt);
            }
        });

        btnEjemplarAnterior.setBackground(Colores.buttons);
        btnEjemplarAnterior.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons/arrow-left.png"))); // NOI18N
        btnEjemplarAnterior.setCornerRound(10);
        btnEjemplarAnterior.setPreferredSize(new java.awt.Dimension(63, 63));
        btnEjemplarAnterior.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEjemplarAnteriorMouseClicked(evt);
            }
        });

        panelTitulo.setBackground(new java.awt.Color(58, 39, 35));
        panelTitulo.setLayout(new java.awt.GridLayout(2, 1));

        textTitulo.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        textTitulo.setForeground(new java.awt.Color(204, 204, 204));
        textTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        textTitulo.setText("Ejemplares");
        panelTitulo.add(textTitulo);

        textEjemplarIndice.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        textEjemplarIndice.setForeground(new java.awt.Color(204, 204, 204));
        textEjemplarIndice.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        textEjemplarIndice.setText("Ejemplar 1 de 25");
        panelTitulo.add(textEjemplarIndice);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnEjemplarAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEjemplarSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEjemplarAnterior, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEjemplarSiguiente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        panelCodigo1.setBackground(new java.awt.Color(239, 235, 233));

        jLabel13.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setText("Codigo del ejemplar:");

        textCodigoEjemplar1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        textCodigoEjemplar1.setForeground(new java.awt.Color(51, 51, 51));
        textCodigoEjemplar1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        flatButton4.setBackground(Colores.buttons);
        flatButton4.setText("Imprimir etiqueta");
        flatButton4.setCornerRound(10);
        flatButton4.setPreferredSize(new java.awt.Dimension(111, 32));

        javax.swing.GroupLayout panelCodigo1Layout = new javax.swing.GroupLayout(panelCodigo1);
        panelCodigo1.setLayout(panelCodigo1Layout);
        panelCodigo1Layout.setHorizontalGroup(
            panelCodigo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCodigo1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelCodigo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCodigo1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(flatButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelCodigo1Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(textCodigoEjemplar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelCodigo1Layout.setVerticalGroup(
            panelCodigo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCodigo1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textCodigoEjemplar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(flatButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panelEstadoParent15.setBackground(new java.awt.Color(239, 235, 233));

        jLabel29.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(51, 51, 51));
        jLabel29.setText("Estado:");

        panelEstado15.setBackground(new java.awt.Color(239, 235, 233));
        panelEstado15.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        panelEstado15.setLayout(new java.awt.GridLayout(1, 0, 5, 5));

        btnBadStatus15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnBadStatus15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/bad_disabled.png"))); // NOI18N
        panelEstado15.add(btnBadStatus15);

        btnRegularStatus15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnRegularStatus15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/regular_disabled.png"))); // NOI18N
        panelEstado15.add(btnRegularStatus15);

        btnGoodStatus15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnGoodStatus15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/good.png"))); // NOI18N
        panelEstado15.add(btnGoodStatus15);

        javax.swing.GroupLayout panelEstadoParent15Layout = new javax.swing.GroupLayout(panelEstadoParent15);
        panelEstadoParent15.setLayout(panelEstadoParent15Layout);
        panelEstadoParent15Layout.setHorizontalGroup(
            panelEstadoParent15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEstadoParent15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelEstadoParent15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelEstadoParent15Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(panelEstado15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelEstadoParent15Layout.createSequentialGroup()
                        .addComponent(jLabel29)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelEstadoParent15Layout.setVerticalGroup(
            panelEstadoParent15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEstadoParent15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelEstado15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelNoPrestado.setBackground(new java.awt.Color(239, 235, 233));

        jLabel36.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(51, 51, 51));
        jLabel36.setText("Prestado a:");

        jPanel13.setBackground(new java.awt.Color(239, 235, 233));
        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel37.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(51, 51, 51));
        jLabel37.setText("Este libro no se encuentra prestado por el momento.");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel37)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel37)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelNoPrestadoLayout = new javax.swing.GroupLayout(panelNoPrestado);
        panelNoPrestado.setLayout(panelNoPrestadoLayout);
        panelNoPrestadoLayout.setHorizontalGroup(
            panelNoPrestadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNoPrestadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelNoPrestadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelNoPrestadoLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelNoPrestadoLayout.createSequentialGroup()
                        .addComponent(jLabel36)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        panelNoPrestadoLayout.setVerticalGroup(
            panelNoPrestadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNoPrestadoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel36)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelPrestado.setBackground(new java.awt.Color(239, 235, 233));

        jLabel30.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(51, 51, 51));
        jLabel30.setText("Prestado a:");

        jPanel6.setBackground(new java.awt.Color(239, 235, 233));

        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/person-flat.png"))); // NOI18N
        jLabel31.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel7.setBackground(new java.awt.Color(239, 235, 233));

        jPanel8.setBackground(new java.awt.Color(239, 235, 233));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel32.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(51, 51, 51));
        jLabel32.setText("Telefono:");

        textTelefonoAlumno.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        textTelefonoAlumno.setForeground(new java.awt.Color(51, 51, 51));
        textTelefonoAlumno.setText("+34 684 092 823");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(textTelefonoAlumno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel32)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel32)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textTelefonoAlumno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel9.setBackground(new java.awt.Color(239, 235, 233));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel33.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(51, 51, 51));
        jLabel33.setText("NIA:");

        textNIAAlumno.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        textNIAAlumno.setForeground(new java.awt.Color(51, 51, 51));
        textNIAAlumno.setText("10429497");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel33)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(textNIAAlumno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel33)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textNIAAlumno)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel10.setBackground(new java.awt.Color(239, 235, 233));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel34.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(51, 51, 51));
        jLabel34.setText("Nombre y Apellidos:");

        textNombreAlumno.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        textNombreAlumno.setForeground(new java.awt.Color(51, 51, 51));
        textNombreAlumno.setText("Jose Sanchis Belda");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(textNombreAlumno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel34)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel34)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textNombreAlumno)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel11.setBackground(new java.awt.Color(239, 235, 233));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel35.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(51, 51, 51));
        jLabel35.setText("Correo electronico:");

        textEmailAlumno.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        textEmailAlumno.setForeground(new java.awt.Color(51, 51, 51));
        textEmailAlumno.setText("sanchisbeldajose@gmail.com");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textEmailAlumno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel35)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel35)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textEmailAlumno)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(6, 6, 6))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel12.setBackground(Colores.fondo);

        btnVerAlumno.setBackground(Colores.buttons);
        btnVerAlumno.setText("Ver Alumno");
        btnVerAlumno.setCornerRound(10);
        btnVerAlumno.setPreferredSize(new java.awt.Dimension(111, 32));
        btnVerAlumno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerAlumnoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnVerAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnVerAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelPrestadoLayout = new javax.swing.GroupLayout(panelPrestado);
        panelPrestado.setLayout(panelPrestadoLayout);
        panelPrestadoLayout.setHorizontalGroup(
            panelPrestadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrestadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelPrestadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPrestadoLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelPrestadoLayout.createSequentialGroup()
                        .addComponent(jLabel30)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPrestadoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelPrestadoLayout.setVerticalGroup(
            panelPrestadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrestadoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel30)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelPrestadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(panelEstadoParent15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelCodigo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelPrestado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelNoPrestado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(panelPrestado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelNoPrestado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(panelCodigo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelEstadoParent15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jScrollPane5.setViewportView(jPanel14);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 188, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelDerechoInferiorLayout = new javax.swing.GroupLayout(panelDerechoInferior);
        panelDerechoInferior.setLayout(panelDerechoInferiorLayout);
        panelDerechoInferiorLayout.setHorizontalGroup(
            panelDerechoInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDerechoInferiorLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelDerechoInferiorLayout.setVerticalGroup(
            panelDerechoInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDerechoInferiorLayout.createSequentialGroup()
                .addContainerGap(226, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout panelDerechoLayout = new javax.swing.GroupLayout(panelDerecho);
        panelDerecho.setLayout(panelDerechoLayout);
        panelDerechoLayout.setHorizontalGroup(
            panelDerechoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDerechoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDerechoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelDerechoSuperior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelDerechoInferior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelDerechoLayout.setVerticalGroup(
            panelDerechoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDerechoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelDerechoSuperior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelDerechoInferior, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jSplitPane1.setRightComponent(panelDerecho);

        panelIzquierdo.setAutoscrolls(true);
        panelIzquierdo.setMaximumSize(new java.awt.Dimension(1920, 1080));
        panelIzquierdo.setPreferredSize(new java.awt.Dimension(1920, 1080));

        bodyListado.setBackground(new java.awt.Color(239, 235, 233));
        bodyListado.setMaximumSize(new java.awt.Dimension(1080, 402));
        bodyListado.setPreferredSize(new java.awt.Dimension(583, 625));

        textTitle1.setBackground(new java.awt.Color(51, 51, 51));
        textTitle1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        textTitle1.setForeground(new java.awt.Color(51, 51, 51));
        textTitle1.setText("Listado de libros:");
        textTitle1.setToolTipText("");

        textNombreLibro1.setBackground(new java.awt.Color(239, 235, 233));
        textNombreLibro1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        textNombreLibro1.setForeground(new java.awt.Color(102, 102, 102));
        textNombreLibro1.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        textNombreLibro1.setText("Escribe nombre del libro...");
        textNombreLibro1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(51, 51, 51)));
        textNombreLibro1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textNombreLibro1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textNombreLibro1KeyReleased(evt);
            }
        });

        cbCurso1.setBackground(new java.awt.Color(51, 51, 51));
        cbCurso1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        cbCurso1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar curso" }));
        cbCurso1.setMaximumSize(new java.awt.Dimension(187, 34));
        cbCurso1.setPreferredSize(new java.awt.Dimension(250, 34));
        cbCurso1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbCurso1ItemStateChanged(evt);
            }
        });

        jScrollPane2.setMinimumSize(new java.awt.Dimension(0, 0));

        jlistResultadoLibros.setBackground(new java.awt.Color(239, 235, 233));
        jlistResultadoLibros.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jlistResultadoLibros.setForeground(new java.awt.Color(51, 51, 51));
        jlistResultadoLibros.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "mates", "castellano", "valencia" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jlistResultadoLibros.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jlistResultadoLibros.setMaximumSize(new java.awt.Dimension(1080, 78));
        jlistResultadoLibros.setMinimumSize(new java.awt.Dimension(0, 0));
        jlistResultadoLibros.setSelectionBackground(Colores.buttons);
        jlistResultadoLibros.setSelectionForeground(Colores.fondo);
        jlistResultadoLibros.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlistResultadoLibrosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jlistResultadoLibros);

        javax.swing.GroupLayout bodyListadoLayout = new javax.swing.GroupLayout(bodyListado);
        bodyListado.setLayout(bodyListadoLayout);
        bodyListadoLayout.setHorizontalGroup(
            bodyListadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyListadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bodyListadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(bodyListadoLayout.createSequentialGroup()
                        .addComponent(textTitle1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(bodyListadoLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(textNombreLibro1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbCurso1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        bodyListadoLayout.setVerticalGroup(
            bodyListadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bodyListadoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(textTitle1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bodyListadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textNombreLibro1, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(cbCurso1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 537, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelIzquierdoLayout = new javax.swing.GroupLayout(panelIzquierdo);
        panelIzquierdo.setLayout(panelIzquierdoLayout);
        panelIzquierdoLayout.setHorizontalGroup(
            panelIzquierdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bodyListado, javax.swing.GroupLayout.PREFERRED_SIZE, 574, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        panelIzquierdoLayout.setVerticalGroup(
            panelIzquierdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelIzquierdoLayout.createSequentialGroup()
                .addComponent(bodyListado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 453, Short.MAX_VALUE))
        );

        jSplitPane1.setLeftComponent(panelIzquierdo);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1721, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
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

        if (isNewLibro) {
            //Creacion de un nuevo libro

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

                    isNewLibro = false;
                    btnEdit.setEnabled(true);
                    
                    libro = daoLibro.buscar(newLibro.getCodigo());

                    //RefrescarTabla();
                } catch (PersistenceException e) {

                    JOptionPane.showMessageDialog(this,
                            "El libro ya existe en la Base de Datos.", "Error",
                            JOptionPane.ERROR_MESSAGE);

                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this,
                            "Error al crear el libro.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this,
                        "Revise los siguientes errores: \n" + errores, "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

        } else {
            //Modificacion de un libro existente

            if (errores.equals("")) {
                //Creamos el libro si el string de los errores esta vacío, es decir, si no hay errores

                int unidadesOld = libro.getUnidades();
                
                libro.setCodigo(textCodigoDeBarrasLibro.getText());
                libro.setISBN(textISBNLibro.getText());
                libro.setNombre(textNombreLibro.getText());
                libro.setObsoleto(chkObsoleto.isChecked());
                libro.setPrecio(Double.parseDouble(textPrecio.getText()));
                libro.setUnidades(Integer.parseInt(textUnidadesLibro.getText()));

                for (int i = 0; i < listaContenido.size(); i++) {
                    if (listaContenido.get(i).getNombre_cas().equals(cbAsignatura.getSelectedItem().toString())) {
                        libro.setContenido(listaContenido.get(i));
                        break;
                    }
                }

                try {
                    daoLibro.actualizar(unidadesOld, libro);

                    JOptionPane.showMessageDialog(this,
                            "Libro actualizado correctamente.", "Información",
                            JOptionPane.INFORMATION_MESSAGE);

                    setEditMode(false);
                    
                    libro = daoLibro.buscar(libro.getCodigo());

                    //RefrescarTabla();
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
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        try {
            daoContenido.desconectar();
            daoCurso.desconectar();
            daoLibro.desconectar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_formWindowClosed

    private void btnEjemplarSiguienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEjemplarSiguienteMouseClicked
        // TODO add your handling code here:
        /*if ((ejemplarActual + 1) > listaEjemplares.size() - 1) {
            ejemplarActual = 0;
        } else {
            ejemplarActual++;
        }
        rellenarDatosEjemplar();*/
    }//GEN-LAST:event_btnEjemplarSiguienteMouseClicked

    private void btnEjemplarAnteriorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEjemplarAnteriorMouseClicked
        // TODO add your handling code here:
        /*if ((ejemplarActual - 1) < 0) {
            ejemplarActual = listaEjemplares.size() - 1;
        } else {
            ejemplarActual--;
        }
        rellenarDatosEjemplar();*/
    }//GEN-LAST:event_btnEjemplarAnteriorMouseClicked

    private void btnVerAlumnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerAlumnoActionPerformed
        // TODO add your handling code here:
        /*if (frameAlumno == null){
            frameAlumno = new FrameAlumno(alumno.getNia());
        }
        frameAlumno.setVisible(true);*/
    }//GEN-LAST:event_btnVerAlumnoActionPerformed

    private void textNombreLibro1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textNombreLibro1KeyPressed
        //Metodo del placeholder en la busqueda del listado
        String nombreLibro = textNombreLibro.getText();

        if (nombreLibro.length() > 0 && nombreLibro.equals("Escribe nombre del libro...")) {
            textNombreLibro.setText("");
            textNombreLibro.setForeground(new Color(51, 51, 51));
        }
    }//GEN-LAST:event_textNombreLibro1KeyPressed

    private void textNombreLibro1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textNombreLibro1KeyReleased
        //Metodo del placeholder en la busqueda del listado
        String nombreLibro = textNombreLibro.getText();

        if (nombreLibro.length() == 0) {
            textNombreLibro.setText("Escribe nombre del libro...");
            textNombreLibro.setForeground(new Color(102, 102, 102));
        }

//        filtroListaLibro(textNombreLibro.getText(), cbCurso.getSelectedItem().toString());
    }//GEN-LAST:event_textNombreLibro1KeyReleased

    private void cbCurso1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbCurso1ItemStateChanged
        // TODO add your handling code here:
        filtroListaLibro(textNombreLibro.getText(), cbCurso.getSelectedItem().toString());
    }//GEN-LAST:event_cbCurso1ItemStateChanged

    private void jlistResultadoLibrosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlistResultadoLibrosMouseClicked
        // TODO add your handling code here:
        String nombreLibro = jlistResultadoLibros.getSelectedValue().toString();
        //Libro libroSelect = listaLibros.stream().filter(libro -> libro.getNombre().equals(nombreLibro)).collect(Collectors.toList()).get(0);
        //buscarLibro(libroSelect.getCodigo());
    }//GEN-LAST:event_jlistResultadoLibrosMouseClicked

    private void cbCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCursoActionPerformed
        // TODO add your handling code here:
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
            java.util.logging.Logger.getLogger(FrameLibroOld.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameLibroOld.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameLibroOld.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameLibroOld.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new FrameLibroOld(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bodyListado;
    private javax.swing.JLabel btnBadStatus15;
    private com.mommoo.flat.button.FlatButton btnDelete;
    private com.mommoo.flat.button.FlatButton btnEdit;
    private com.mommoo.flat.button.FlatButton btnEjemplarAnterior;
    private com.mommoo.flat.button.FlatButton btnEjemplarSiguiente;
    private javax.swing.JLabel btnGoodStatus15;
    private javax.swing.JLabel btnRegularStatus15;
    private com.mommoo.flat.button.FlatButton btnSave;
    private com.mommoo.flat.button.FlatButton btnVerAlumno;
    private javax.swing.JComboBox cbAsignatura;
    private javax.swing.JComboBox cbCurso;
    private javax.swing.JComboBox cbCurso1;
    private com.mommoo.flat.select.FlatCheckBox chkObsoleto;
    private com.mommoo.flat.button.FlatButton flatButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JList jlistResultadoLibros;
    private javax.swing.JPanel panelAsignatura1;
    private javax.swing.JPanel panelCodigo1;
    private javax.swing.JPanel panelCodigoDeBarras2;
    private javax.swing.JPanel panelCurso1;
    private javax.swing.JPanel panelDerecho;
    private javax.swing.JPanel panelDerechoInferior;
    private javax.swing.JPanel panelDerechoSuperior;
    private javax.swing.JPanel panelEstado15;
    private javax.swing.JPanel panelEstadoDelLibro1;
    private javax.swing.JPanel panelEstadoParent15;
    private javax.swing.JPanel panelGeneralSuperior1;
    private javax.swing.JPanel panelISBN1;
    private javax.swing.JPanel panelInferior1;
    private javax.swing.JPanel panelIzquierdo;
    private javax.swing.JPanel panelMedio1;
    private javax.swing.JPanel panelNoPrestado;
    private javax.swing.JPanel panelNombre1;
    private javax.swing.JPanel panelPrecio1;
    private javax.swing.JPanel panelPrestado;
    private javax.swing.JPanel panelSuperior1;
    private javax.swing.JPanel panelTitulo;
    private javax.swing.JPanel panelTituloLibro;
    private javax.swing.JPanel panelUnidades1;
    private javax.swing.JTextField textCodigoDeBarrasLibro;
    private javax.swing.JLabel textCodigoEjemplar1;
    private javax.swing.JLabel textEjemplarIndice;
    private javax.swing.JLabel textEmailAlumno;
    private javax.swing.JTextField textISBNLibro;
    private javax.swing.JLabel textNIAAlumno;
    private javax.swing.JLabel textNombreAlumno;
    private javax.swing.JTextField textNombreLibro;
    private javax.swing.JTextField textNombreLibro1;
    private javax.swing.JTextField textPrecio;
    private javax.swing.JLabel textTelefonoAlumno;
    private javax.swing.JLabel textTitle1;
    private javax.swing.JLabel textTitulo;
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
        //btnImprimirEtiquetas.setEnabled(editable);
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
