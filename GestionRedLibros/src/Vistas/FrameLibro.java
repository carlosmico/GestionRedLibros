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

import Daos.DaoCurso;
import Daos.DaoLibro;
import Pojos.Contenido;
import Pojos.Curso;
import Pojos.Libro;
import Renders.comboBoxRender;
import Utilidades.Colores;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.PersistenceException;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

/**
 *
 * @author Jose Sanchis
 */
public class FrameLibro extends javax.swing.JFrame {

    boolean modoEdicion = false;
    boolean busquedaPorCodigo = true;

    String placeHolderCodigo = "Introduce o escanea codigo...",
            placeHolderNombre = "Introduce nombre...";

    private FramePopup frameCarga = null;

    DaoLibro daoLibro;
    DaoCurso daoCurso;

    private Libro libro;

    private List<Libro> listaLibros;
    private List<Curso> listaCursos;
    private List<Contenido> listaContenido;

    /**
     * Inicializamos los componentes y cargamos los datos necesarios.
     */
    public FrameLibro() {
        initComponents();

        //Posicionamos la pestaña al centro de la pantalla
        this.setLocationRelativeTo(null);

        //Maximizamos la ventana
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        //Ponemos el foco en el textField del codigo
        textCodigoLibro.requestFocusInWindow();
        
        //Configuramos los PlaceHolders
        textNombreLibroBusqueda.setText(placeHolderNombre);
        textBusquedaCodigoLibro.setText(placeHolderCodigo);

        //Inicializamos los Daos
        daoCurso = new DaoCurso(Main.gestorSesiones.getSession());
        daoLibro = new DaoLibro(Main.gestorSesiones.getSession());

        //Configuramos la parte visual de los ComboBox
        //<editor-fold defaultstate="collapsed" desc="Configuración Combobox">
        cbCursoLibro.setEditable(false);
        cbCursoLibro.setUI(new comboBoxRender());
        cbCursoLibro.setRenderer(new DefaultListCellRenderer() {
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

        cbCursoBuscar.setEditable(false);
        cbCursoBuscar.setUI(new comboBoxRender());
        cbCursoBuscar.setRenderer(new DefaultListCellRenderer() {
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

        setEditMode(false);

        cargarDatos();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuOpcionesLibro = new javax.swing.JPopupMenu();
        btnImprimirEtiquetas = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        btnEditar = new javax.swing.JMenuItem();
        btnEliminar = new javax.swing.JMenuItem();
        jSplitPane1 = new javax.swing.JSplitPane();
        panelIzquierdo = new javax.swing.JPanel();
        panelGeneralIzquierdo = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        btnBuscar = new com.mommoo.flat.button.FlatButton();
        textBusquedaCodigoLibro = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        textNombreLibroBusqueda = new javax.swing.JTextField();
        cbCursoBuscar = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jlistLibros = new javax.swing.JList();
        panelTitulo1 = new javax.swing.JPanel();
        textTitulo1 = new javax.swing.JLabel();
        panelDerecho = new javax.swing.JPanel();
        jSplitPane2 = new javax.swing.JSplitPane();
        panelSuperiorDerecho = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        panelGeneralDerechoSuperior = new javax.swing.JPanel();
        panelTitulo = new javax.swing.JPanel();
        textTitulo = new javax.swing.JLabel();
        btnOpciones = new com.mommoo.flat.button.FlatButton();
        labelInfoEdicion = new javax.swing.JLabel();
        btnNewLibro = new com.mommoo.flat.button.FlatButton();
        panelCuerpo = new javax.swing.JPanel();
        panelCodigoLibro = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        textCodigoLibro = new javax.swing.JTextField();
        panelSuperior = new javax.swing.JPanel();
        panelNombreLibro = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        textNombreLibro = new javax.swing.JTextField();
        panelISBNLibro = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        textISBNLibro = new javax.swing.JTextField();
        panelMedio = new javax.swing.JPanel();
        panelCurso = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        cbCursoLibro = new javax.swing.JComboBox();
        panelAsignatura = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        cbAsignatura = new javax.swing.JComboBox();
        panelInferior = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        textUnidadesLibro = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        textPrecioLibro = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        chkObsoletoLibro = new javax.swing.JCheckBox();
        panelBotoneraEdicion = new javax.swing.JPanel();
        btnGuardar = new com.mommoo.flat.button.FlatButton();
        btnCancelar = new com.mommoo.flat.button.FlatButton();
        panelInferiorDerecho = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        panelGeneralDerechoInferior = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        flatButton5 = new com.mommoo.flat.button.FlatButton();
        flatButton6 = new com.mommoo.flat.button.FlatButton();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        flatButton3 = new com.mommoo.flat.button.FlatButton();
        panelEstadoParent15 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        panelEstado15 = new javax.swing.JPanel();
        btnBadStatus15 = new javax.swing.JLabel();
        btnRegularStatus15 = new javax.swing.JLabel();
        btnGoodStatus15 = new javax.swing.JLabel();
        panelPrestado = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        textTelefonoAlumno = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        textNIAAlumno = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        textNombreAlumno = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        textEmailAlumno = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        btnVerAlumno = new com.mommoo.flat.button.FlatButton();
        panelNoPrestado = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();

        menuOpcionesLibro.setBackground(new java.awt.Color(66, 47, 44));
        menuOpcionesLibro.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        menuOpcionesLibro.setForeground(new java.awt.Color(66, 47, 44));

        btnImprimirEtiquetas.setBackground(Colores.accent);
        btnImprimirEtiquetas.setFont(new java.awt.Font("Dialog", 1, 21)); // NOI18N
        btnImprimirEtiquetas.setForeground(Colores.accent);
        btnImprimirEtiquetas.setText("Imprimir Etiquetas");
        btnImprimirEtiquetas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirEtiquetasActionPerformed(evt);
            }
        });
        menuOpcionesLibro.add(btnImprimirEtiquetas);
        menuOpcionesLibro.add(jSeparator1);

        btnEditar.setBackground(new java.awt.Color(66, 47, 44));
        btnEditar.setFont(new java.awt.Font("Dialog", 1, 21)); // NOI18N
        btnEditar.setForeground(new java.awt.Color(66, 47, 44));
        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        menuOpcionesLibro.add(btnEditar);

        btnEliminar.setBackground(new java.awt.Color(66, 47, 44));
        btnEliminar.setFont(new java.awt.Font("Dialog", 1, 21)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(66, 47, 44));
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        menuOpcionesLibro.add(btnEliminar);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Libros");

        jSplitPane1.setBorder(null);

        panelGeneralIzquierdo.setBackground(new java.awt.Color(239, 235, 233));
        panelGeneralIzquierdo.setPreferredSize(new java.awt.Dimension(400, 600));
        panelGeneralIzquierdo.setRequestFocusEnabled(false);

        jPanel1.setBackground(Colores.fondo);

        btnBuscar.setBackground(Colores.buttons);
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons/magnify.png"))); // NOI18N
        btnBuscar.setCornerRound(10);
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        textBusquedaCodigoLibro.setBackground(Colores.fondo);
        textBusquedaCodigoLibro.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        textBusquedaCodigoLibro.setForeground(new java.awt.Color(153, 153, 153));
        textBusquedaCodigoLibro.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        textBusquedaCodigoLibro.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textBusquedaCodigoLibroFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                textBusquedaCodigoLibroFocusLost(evt);
            }
        });
        textBusquedaCodigoLibro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textBusquedaCodigoLibroKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(textBusquedaCodigoLibro)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(textBusquedaCodigoLibro))
                .addContainerGap())
        );

        jPanel2.setBackground(Colores.fondo);

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setText("Listado de libros:");

        textNombreLibroBusqueda.setBackground(Colores.fondo);
        textNombreLibroBusqueda.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        textNombreLibroBusqueda.setForeground(new java.awt.Color(153, 153, 153));
        textNombreLibroBusqueda.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        textNombreLibroBusqueda.setMinimumSize(new java.awt.Dimension(200, 32));
        textNombreLibroBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textNombreLibroBusquedaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textNombreLibroBusquedaKeyReleased(evt);
            }
        });

        cbCursoBuscar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        cbCursoBuscar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecciona curso" }));
        cbCursoBuscar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbCursoBuscarItemStateChanged(evt);
            }
        });

        jlistLibros.setBackground(Colores.fondo);
        jlistLibros.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jlistLibros.setForeground(Colores.accent);
        jlistLibros.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jlistLibros.setSelectionBackground(Colores.accent);
        jlistLibros.setSelectionForeground(Colores.fondo);
        jlistLibros.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jlistLibrosValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jlistLibros);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(textNombreLibroBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbCursoBuscar, 0, 220, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbCursoBuscar)
                    .addComponent(textNombreLibroBusqueda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelTitulo1.setBackground(Colores.accent);

        textTitulo1.setBackground(new java.awt.Color(239, 235, 233));
        textTitulo1.setFont(new java.awt.Font("Dialog", 3, 24)); // NOI18N
        textTitulo1.setForeground(new java.awt.Color(239, 235, 233));
        textTitulo1.setText("Buscar");

        javax.swing.GroupLayout panelTitulo1Layout = new javax.swing.GroupLayout(panelTitulo1);
        panelTitulo1.setLayout(panelTitulo1Layout);
        panelTitulo1Layout.setHorizontalGroup(
            panelTitulo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTitulo1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(textTitulo1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(250, Short.MAX_VALUE))
        );
        panelTitulo1Layout.setVerticalGroup(
            panelTitulo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTitulo1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(textTitulo1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelGeneralIzquierdoLayout = new javax.swing.GroupLayout(panelGeneralIzquierdo);
        panelGeneralIzquierdo.setLayout(panelGeneralIzquierdoLayout);
        panelGeneralIzquierdoLayout.setHorizontalGroup(
            panelGeneralIzquierdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGeneralIzquierdoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelGeneralIzquierdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(panelTitulo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelGeneralIzquierdoLayout.setVerticalGroup(
            panelGeneralIzquierdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelGeneralIzquierdoLayout.createSequentialGroup()
                .addComponent(panelTitulo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelIzquierdoLayout = new javax.swing.GroupLayout(panelIzquierdo);
        panelIzquierdo.setLayout(panelIzquierdoLayout);
        panelIzquierdoLayout.setHorizontalGroup(
            panelIzquierdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelGeneralIzquierdo, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
        );
        panelIzquierdoLayout.setVerticalGroup(
            panelIzquierdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelGeneralIzquierdo, javax.swing.GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE)
        );

        jSplitPane1.setLeftComponent(panelIzquierdo);

        panelDerecho.setPreferredSize(new java.awt.Dimension(100, 608));

        jSplitPane2.setBorder(null);
        jSplitPane2.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        panelSuperiorDerecho.setMinimumSize(new java.awt.Dimension(0, 410));
        panelSuperiorDerecho.setPreferredSize(new java.awt.Dimension(472, 300));

        jScrollPane2.setBorder(null);
        jScrollPane2.setMinimumSize(new java.awt.Dimension(20, 250));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(100, 500));

        panelGeneralDerechoSuperior.setBackground(Colores.fondo);
        panelGeneralDerechoSuperior.setPreferredSize(new java.awt.Dimension(750, 450));

        panelTitulo.setBackground(Colores.accent);

        textTitulo.setBackground(new java.awt.Color(239, 235, 233));
        textTitulo.setFont(new java.awt.Font("Dialog", 3, 24)); // NOI18N
        textTitulo.setForeground(new java.awt.Color(239, 235, 233));
        textTitulo.setText("Libro");

        btnOpciones.setBackground(Colores.accent);
        btnOpciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons/dots-vertical.png"))); // NOI18N
        btnOpciones.setCornerRound(10);
        btnOpciones.setPreferredSize(new java.awt.Dimension(48, 48));
        btnOpciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnOpcionesMouseReleased(evt);
            }
        });

        labelInfoEdicion.setBackground(Colores.accent);
        labelInfoEdicion.setFont(new java.awt.Font("Dialog", 3, 12)); // NOI18N
        labelInfoEdicion.setForeground(Colores.fondo);
        labelInfoEdicion.setText("modo edición");

        btnNewLibro.setBackground(Colores.accent);
        btnNewLibro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons/plus.png"))); // NOI18N
        btnNewLibro.setCornerRound(10);
        btnNewLibro.setPreferredSize(new java.awt.Dimension(48, 48));
        btnNewLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewLibroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelTituloLayout = new javax.swing.GroupLayout(panelTitulo);
        panelTitulo.setLayout(panelTituloLayout);
        panelTituloLayout.setHorizontalGroup(
            panelTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTituloLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(textTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelInfoEdicion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnNewLibro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelTituloLayout.setVerticalGroup(
            panelTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTituloLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNewLibro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(textTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelInfoEdicion)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelCuerpo.setBackground(Colores.fondo);
        panelCuerpo.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        panelCuerpo.setPreferredSize(new java.awt.Dimension(860, 500));

        panelCodigoLibro.setBackground(Colores.fondo);

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel3.setText("Código del libro:");

        textCodigoLibro.setBackground(Colores.fondo);
        textCodigoLibro.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        textCodigoLibro.setForeground(Colores.accent);
        textCodigoLibro.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout panelCodigoLibroLayout = new javax.swing.GroupLayout(panelCodigoLibro);
        panelCodigoLibro.setLayout(panelCodigoLibroLayout);
        panelCodigoLibroLayout.setHorizontalGroup(
            panelCodigoLibroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCodigoLibroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelCodigoLibroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCodigoLibroLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(textCodigoLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelCodigoLibroLayout.setVerticalGroup(
            panelCodigoLibroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCodigoLibroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textCodigoLibro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelSuperior.setBackground(Colores.fondo);
        panelSuperior.setLayout(new java.awt.GridLayout(1, 0));

        panelNombreLibro.setBackground(Colores.fondo);

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel4.setText("Nombre:");

        textNombreLibro.setBackground(Colores.fondo);
        textNombreLibro.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        textNombreLibro.setForeground(Colores.accent);
        textNombreLibro.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout panelNombreLibroLayout = new javax.swing.GroupLayout(panelNombreLibro);
        panelNombreLibro.setLayout(panelNombreLibroLayout);
        panelNombreLibroLayout.setHorizontalGroup(
            panelNombreLibroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNombreLibroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelNombreLibroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelNombreLibroLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(textNombreLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelNombreLibroLayout.setVerticalGroup(
            panelNombreLibroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNombreLibroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textNombreLibro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelSuperior.add(panelNombreLibro);

        panelISBNLibro.setBackground(Colores.fondo);

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel5.setText("ISBN:");

        textISBNLibro.setBackground(Colores.fondo);
        textISBNLibro.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        textISBNLibro.setForeground(Colores.accent);
        textISBNLibro.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout panelISBNLibroLayout = new javax.swing.GroupLayout(panelISBNLibro);
        panelISBNLibro.setLayout(panelISBNLibroLayout);
        panelISBNLibroLayout.setHorizontalGroup(
            panelISBNLibroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelISBNLibroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelISBNLibroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelISBNLibroLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(textISBNLibro, javax.swing.GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE))
                    .addGroup(panelISBNLibroLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelISBNLibroLayout.setVerticalGroup(
            panelISBNLibroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelISBNLibroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textISBNLibro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelSuperior.add(panelISBNLibro);

        panelMedio.setBackground(Colores.fondo);
        panelMedio.setLayout(new java.awt.GridLayout(1, 0));

        panelCurso.setBackground(Colores.fondo);

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel6.setText("Curso:");

        cbCursoLibro.setBackground(Colores.fondo);
        cbCursoLibro.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        cbCursoLibro.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbCursoLibroItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout panelCursoLayout = new javax.swing.GroupLayout(panelCurso);
        panelCurso.setLayout(panelCursoLayout);
        panelCursoLayout.setHorizontalGroup(
            panelCursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCursoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelCursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCursoLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(cbCursoLibro, 0, 439, Short.MAX_VALUE))
                    .addGroup(panelCursoLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelCursoLayout.setVerticalGroup(
            panelCursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCursoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbCursoLibro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelMedio.add(panelCurso);

        panelAsignatura.setBackground(Colores.fondo);

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel7.setText("Asignatura:");

        cbAsignatura.setBackground(Colores.fondo);
        cbAsignatura.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N

        javax.swing.GroupLayout panelAsignaturaLayout = new javax.swing.GroupLayout(panelAsignatura);
        panelAsignatura.setLayout(panelAsignaturaLayout);
        panelAsignaturaLayout.setHorizontalGroup(
            panelAsignaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAsignaturaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelAsignaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAsignaturaLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(cbAsignatura, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelAsignaturaLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(0, 344, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelAsignaturaLayout.setVerticalGroup(
            panelAsignaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAsignaturaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbAsignatura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelMedio.add(panelAsignatura);

        panelInferior.setBackground(Colores.fondo);
        panelInferior.setLayout(new java.awt.GridLayout(1, 0));

        jPanel3.setBackground(Colores.fondo);

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel8.setText("Unidades:");

        textUnidadesLibro.setBackground(Colores.fondo);
        textUnidadesLibro.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        textUnidadesLibro.setForeground(Colores.accent);
        textUnidadesLibro.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(textUnidadesLibro))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(0, 203, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textUnidadesLibro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelInferior.add(jPanel3);

        jPanel4.setBackground(Colores.fondo);

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel9.setText("Precio:");

        textPrecioLibro.setBackground(Colores.fondo);
        textPrecioLibro.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        textPrecioLibro.setForeground(Colores.accent);
        textPrecioLibro.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        textPrecioLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textPrecioLibroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(textPrecioLibro))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(0, 229, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textPrecioLibro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelInferior.add(jPanel4);

        jPanel5.setBackground(Colores.fondo);

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel10.setText("Estado del libro:");

        chkObsoletoLibro.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        chkObsoletoLibro.setText("Obsoleto");
        chkObsoletoLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkObsoletoLibroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(chkObsoletoLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(155, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkObsoletoLibro)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelInferior.add(jPanel5);

        javax.swing.GroupLayout panelCuerpoLayout = new javax.swing.GroupLayout(panelCuerpo);
        panelCuerpo.setLayout(panelCuerpoLayout);
        panelCuerpoLayout.setHorizontalGroup(
            panelCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCuerpoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelSuperior, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelMedio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelInferior, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelCuerpoLayout.createSequentialGroup()
                        .addComponent(panelCodigoLibro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        panelCuerpoLayout.setVerticalGroup(
            panelCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCuerpoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelCodigoLibro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelSuperior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelMedio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelInferior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        panelBotoneraEdicion.setBackground(Colores.fondo);

        btnGuardar.setBackground(Colores.buttons);
        btnGuardar.setText("Guardar");
        btnGuardar.setCornerRound(10);
        btnGuardar.setPreferredSize(new java.awt.Dimension(111, 32));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnCancelar.setBackground(Colores.buttons);
        btnCancelar.setText("Cancelar");
        btnCancelar.setCornerRound(10);
        btnCancelar.setPreferredSize(new java.awt.Dimension(111, 32));
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBotoneraEdicionLayout = new javax.swing.GroupLayout(panelBotoneraEdicion);
        panelBotoneraEdicion.setLayout(panelBotoneraEdicionLayout);
        panelBotoneraEdicionLayout.setHorizontalGroup(
            panelBotoneraEdicionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBotoneraEdicionLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelBotoneraEdicionLayout.setVerticalGroup(
            panelBotoneraEdicionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBotoneraEdicionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBotoneraEdicionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelGeneralDerechoSuperiorLayout = new javax.swing.GroupLayout(panelGeneralDerechoSuperior);
        panelGeneralDerechoSuperior.setLayout(panelGeneralDerechoSuperiorLayout);
        panelGeneralDerechoSuperiorLayout.setHorizontalGroup(
            panelGeneralDerechoSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelGeneralDerechoSuperiorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelGeneralDerechoSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelCuerpo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 922, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelGeneralDerechoSuperiorLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(panelBotoneraEdicion, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelGeneralDerechoSuperiorLayout.setVerticalGroup(
            panelGeneralDerechoSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGeneralDerechoSuperiorLayout.createSequentialGroup()
                .addComponent(panelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelCuerpo, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelBotoneraEdicion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(panelGeneralDerechoSuperior);

        javax.swing.GroupLayout panelSuperiorDerechoLayout = new javax.swing.GroupLayout(panelSuperiorDerecho);
        panelSuperiorDerecho.setLayout(panelSuperiorDerechoLayout);
        panelSuperiorDerechoLayout.setHorizontalGroup(
            panelSuperiorDerechoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 947, Short.MAX_VALUE)
        );
        panelSuperiorDerechoLayout.setVerticalGroup(
            panelSuperiorDerechoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 526, Short.MAX_VALUE)
        );

        jSplitPane2.setTopComponent(panelSuperiorDerecho);

        panelInferiorDerecho.setPreferredSize(new java.awt.Dimension(572, 450));

        jScrollPane3.setBorder(null);
        jScrollPane3.setMinimumSize(new java.awt.Dimension(20, 250));
        jScrollPane3.setPreferredSize(new java.awt.Dimension(400, 500));

        panelGeneralDerechoInferior.setPreferredSize(new java.awt.Dimension(1250, 450));

        jPanel8.setBackground(Colores.accent);
        jPanel8.setForeground(Colores.accent);
        jPanel8.setMinimumSize(new java.awt.Dimension(252, 60));
        jPanel8.setPreferredSize(new java.awt.Dimension(252, 60));

        jLabel1.setFont(new java.awt.Font("Dialog", 3, 24)); // NOI18N
        jLabel1.setForeground(Colores.fondo);
        jLabel1.setText("Ejemplares");

        jLabel12.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel12.setForeground(Colores.fondo);
        jLabel12.setText("1 de 25");

        flatButton5.setBackground(Colores.buttons);
        flatButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons/arrow-right.png"))); // NOI18N
        flatButton5.setCornerRound(10);

        flatButton6.setBackground(Colores.buttons);
        flatButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons/arrow-left.png"))); // NOI18N
        flatButton6.setCornerRound(10);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(flatButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(flatButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(flatButton5, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                    .addComponent(flatButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel9.setBackground(Colores.fondo);

        jPanel10.setBackground(new java.awt.Color(239, 235, 233));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel13.setBackground(Colores.accent);
        jLabel13.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setText("Codigo del libro:");

        jLabel14.setToolTipText("");

        flatButton3.setBackground(Colores.buttons);
        flatButton3.setText("Imprimir etiqueta");
        flatButton3.setCornerRound(10);
        flatButton3.setPreferredSize(new java.awt.Dimension(169, 32));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(flatButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(flatButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelEstadoParent15.setBackground(new java.awt.Color(239, 235, 233));
        panelEstadoParent15.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel29.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(51, 51, 51));
        jLabel29.setText("Estado:");

        panelEstado15.setBackground(new java.awt.Color(239, 235, 233));
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
                        .addComponent(panelEstado15, javax.swing.GroupLayout.DEFAULT_SIZE, 427, Short.MAX_VALUE))
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

        panelPrestado.setBackground(new java.awt.Color(239, 235, 233));
        panelPrestado.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel30.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(51, 51, 51));
        jLabel30.setText("Prestado a:");

        jPanel11.setBackground(new java.awt.Color(239, 235, 233));

        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/person-flat.png"))); // NOI18N

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel12.setBackground(new java.awt.Color(239, 235, 233));

        jPanel13.setBackground(new java.awt.Color(239, 235, 233));

        jLabel32.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(51, 51, 51));
        jLabel32.setText("Telefono:");

        textTelefonoAlumno.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        textTelefonoAlumno.setForeground(new java.awt.Color(51, 51, 51));
        textTelefonoAlumno.setText("+34 684 092 823");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(textTelefonoAlumno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel32)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel32)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textTelefonoAlumno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel14.setBackground(new java.awt.Color(239, 235, 233));

        jLabel33.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(51, 51, 51));
        jLabel33.setText("NIA:");

        textNIAAlumno.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        textNIAAlumno.setForeground(new java.awt.Color(51, 51, 51));
        textNIAAlumno.setText("10429497");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel33)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(textNIAAlumno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel33)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textNIAAlumno)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel15.setBackground(new java.awt.Color(239, 235, 233));

        jLabel34.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(51, 51, 51));
        jLabel34.setText("Nombre y Apellidos:");

        textNombreAlumno.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        textNombreAlumno.setForeground(new java.awt.Color(51, 51, 51));
        textNombreAlumno.setText("Jose Sanchis Belda");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(textNombreAlumno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel34)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel34)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textNombreAlumno)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel16.setBackground(new java.awt.Color(239, 235, 233));

        jLabel35.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(51, 51, 51));
        jLabel35.setText("Correo electronico:");

        textEmailAlumno.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        textEmailAlumno.setForeground(new java.awt.Color(51, 51, 51));
        textEmailAlumno.setText("sanchisbeldajose@gmail.com");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textEmailAlumno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel35)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel35)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textEmailAlumno)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(6, 6, 6))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel17.setBackground(Colores.fondo);

        btnVerAlumno.setBackground(Colores.buttons);
        btnVerAlumno.setText("Ver Alumno");
        btnVerAlumno.setCornerRound(10);
        btnVerAlumno.setPreferredSize(new java.awt.Dimension(111, 32));
        btnVerAlumno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerAlumnoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnVerAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
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
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelPrestadoLayout.createSequentialGroup()
                        .addComponent(jLabel30)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPrestadoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelPrestadoLayout.setVerticalGroup(
            panelPrestadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrestadoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel30)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelPrestadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelNoPrestado.setBackground(new java.awt.Color(239, 235, 233));
        panelNoPrestado.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel36.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(51, 51, 51));
        jLabel36.setText("Prestado a:");

        jPanel18.setBackground(new java.awt.Color(239, 235, 233));

        jLabel37.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(51, 51, 51));
        jLabel37.setText("Este libro no se encuentra prestado por el momento.");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel37)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
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
                        .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(panelEstadoParent15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelPrestado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelNoPrestado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(panelPrestado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelNoPrestado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelEstadoParent15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelGeneralDerechoInferiorLayout = new javax.swing.GroupLayout(panelGeneralDerechoInferior);
        panelGeneralDerechoInferior.setLayout(panelGeneralDerechoInferiorLayout);
        panelGeneralDerechoInferiorLayout.setHorizontalGroup(
            panelGeneralDerechoInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, 1250, Short.MAX_VALUE)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelGeneralDerechoInferiorLayout.setVerticalGroup(
            panelGeneralDerechoInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGeneralDerechoInferiorLayout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane3.setViewportView(panelGeneralDerechoInferior);

        javax.swing.GroupLayout panelInferiorDerechoLayout = new javax.swing.GroupLayout(panelInferiorDerecho);
        panelInferiorDerecho.setLayout(panelInferiorDerechoLayout);
        panelInferiorDerechoLayout.setHorizontalGroup(
            panelInferiorDerechoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 947, Short.MAX_VALUE)
        );
        panelInferiorDerechoLayout.setVerticalGroup(
            panelInferiorDerechoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
        );

        jSplitPane2.setBottomComponent(panelInferiorDerecho);

        javax.swing.GroupLayout panelDerechoLayout = new javax.swing.GroupLayout(panelDerecho);
        panelDerecho.setLayout(panelDerechoLayout);
        panelDerechoLayout.setHorizontalGroup(
            panelDerechoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 947, Short.MAX_VALUE)
        );
        panelDerechoLayout.setVerticalGroup(
            panelDerechoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 610, Short.MAX_VALUE)
        );

        jSplitPane1.setRightComponent(panelDerecho);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1407, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Mostramos el menú desplegable de opciones del libro.
     */
    private void btnOpcionesMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOpcionesMouseReleased
        //Mostramos el menu de opciones en el libro
        menuOpcionesLibro.show(evt.getComponent(), -157, 53);
    }//GEN-LAST:event_btnOpcionesMouseReleased

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        vaciarCursosYContenidos();

        if (libro != null) {
            daoLibro.borrar(libro);

            JOptionPane.showMessageDialog(this, "Libro eliminado correctamente.", "Información", JOptionPane.INFORMATION_MESSAGE);
            
            libro = null;
            
            rellenarCamposLibro();

            cargarDatos();
        }else{
            JOptionPane.showMessageDialog(this, "Selecciona un libro para poder eliminarlo.", "Eliminar", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    /**
     * Activamos los campos del Libro para poder editarlo.
     */
    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        rellenarCamposLibro();

        setEditMode(true);
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnVerAlumnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerAlumnoActionPerformed
        // TODO add your handling code here:
        /*if (frameAlumno == null){
         frameAlumno = new FrameAlumno(alumno.getNia());
         }
         frameAlumno.setVisible(true);*/
    }//GEN-LAST:event_btnVerAlumnoActionPerformed

    /**
     * Obtenemos el codigo del libro que se ha introducido y se lo pasamos al
     * metodo buscarLibro.
     */
    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        //Buscamos el libro cuando pulsamos el botón
        buscarLibro(textBusquedaCodigoLibro.getText());
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void chkObsoletoLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkObsoletoLibroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkObsoletoLibroActionPerformed

    private void textPrecioLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textPrecioLibroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textPrecioLibroActionPerformed

    /**
     * Cuando presionamos la tecla intro sobre el campo de busqueda por codigo
     * le pasamos al metodo buscarLibro el codigo introducido por el usuario.
     */
    private void textBusquedaCodigoLibroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textBusquedaCodigoLibroKeyPressed
        // TODO add your handling code here:
        if (!modoEdicion) {
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                buscarLibro(textBusquedaCodigoLibro.getText());
            }
        }
    }//GEN-LAST:event_textBusquedaCodigoLibroKeyPressed

    /**
     * Metodo para filtrar la lista de Libros al cambiar el Curso seleccionado_
     *
     * @param evt
     */
    private void cbCursoBuscarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbCursoBuscarItemStateChanged
        // TODO add your handling code here:
        filtroListaLibro(textNombreLibroBusqueda.getText(), cbCursoBuscar.getSelectedItem().toString());
    }//GEN-LAST:event_cbCursoBuscarItemStateChanged

    private void textNombreLibroBusquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textNombreLibroBusquedaKeyReleased
        // TODO add your handling code here:
        String nombreLibro = textNombreLibroBusqueda.getText();

        if (nombreLibro.length() == 0) {
            textNombreLibroBusqueda.setText(placeHolderNombre);
            textNombreLibroBusqueda.setForeground(new Color(102, 102, 102));
        }

        filtroListaLibro(textNombreLibroBusqueda.getText(), cbCursoBuscar.getSelectedItem().toString());
    }//GEN-LAST:event_textNombreLibroBusquedaKeyReleased

    private void textNombreLibroBusquedaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textNombreLibroBusquedaKeyPressed
        // TODO add your handling code here:
        String nombreLibro = textNombreLibroBusqueda.getText();

        if (nombreLibro.length() > 0 && nombreLibro.equals(placeHolderNombre)) {
            textNombreLibroBusqueda.setText("");
            textNombreLibroBusqueda.setForeground(new Color(51, 51, 51));
        }
    }//GEN-LAST:event_textNombreLibroBusquedaKeyPressed

    /**
     * Metodo para crear un nuevo libro, habilitamos el modo edición y vaciamos
     * los campos
     */
    private void btnNewLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewLibroActionPerformed
        if (!modoEdicion) {
            libro = null;

            rellenarCamposLibro();

            rellenaCursosLibro();

            setEditMode(true);
        }
    }//GEN-LAST:event_btnNewLibroActionPerformed

    /**
     * Metodo para guardar la acción actual, si el libro actual es null llamamos
     * al metodo grabar() del DaoLibro, en caso contrario llamamos al metodo
     * actualizar() del DaoLibro.
     */
    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
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
            double pre = Double.parseDouble(textPrecioLibro.getText());
            if (pre <= 0) {
                errores += "\n- El precio debe ser un valor positivo.";
            }
        } catch (Exception e) {
            errores += "\n- El precio debe ser un valor numérico.";
        }

        if (textCodigoLibro.getText().equals("")) {
            errores += "\n- El código del libro no puede estar vacío.";
        }

        if (cbAsignatura.getSelectedItem() == null) {
            errores += "\n- Debe seleccionar una asignatura válida.";
        } else if (cbAsignatura.getSelectedItem().toString().equals("Seleccione curso")) {
            errores += "\n- Debe seleccionar una asignatura válida.";
        }

        if (libro == null) {
            //Creacion de un nuevo libro

            if (errores.equals("")) {
                //Creamos el libro si el string de los errores esta vacío, es decir, si no hay errores
                Libro newLibro = new Libro();

                newLibro.setCodigo(textCodigoLibro.getText());
                newLibro.setISBN(textISBNLibro.getText());
                newLibro.setNombre(textNombreLibro.getText());
                newLibro.setObsoleto(chkObsoletoLibro.isSelected());
                newLibro.setPrecio(Double.parseDouble(textPrecioLibro.getText()));
                newLibro.setUnidades(Integer.parseInt(textUnidadesLibro.getText()));
                newLibro.setContenido((Contenido) cbAsignatura.getSelectedItem());

                try {
                    daoLibro.grabar(newLibro);

                    JOptionPane.showMessageDialog(this, "Libro añadido correctamente.",
                            "Información", JOptionPane.INFORMATION_MESSAGE);

                    cargarDatos();

                    setEditMode(false);

                    libro = newLibro;
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

                libro.setCodigo(textCodigoLibro.getText());
                libro.setISBN(textISBNLibro.getText());
                libro.setNombre(textNombreLibro.getText());
                libro.setObsoleto(chkObsoletoLibro.isSelected());
                libro.setPrecio(Double.parseDouble(textPrecioLibro.getText()));
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
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void cbCursoLibroItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbCursoLibroItemStateChanged
        // TODO add your handling code here:
        if (modoEdicion) {
            rellenarContenidosLibro();
        }
    }//GEN-LAST:event_cbCursoLibroItemStateChanged

    /**
     * Metodo para cancelar la acción actual, deshabilitamos el modo edición y
     * vaciamos los campos
     */
    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        libro = null;

        setEditMode(false);

        vaciarCursosYContenidos();

        rellenarCamposLibro();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnImprimirEtiquetasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirEtiquetasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnImprimirEtiquetasActionPerformed

    /**
     * Metodo para rellenar el ComboBox de Asignaturas cuando cambie el curso
     * seleccionado
     *
     * @param evt
     */
    private void jlistLibrosValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jlistLibrosValueChanged
        // TODO add your handling code here:
        if (!modoEdicion) {
            libro = (Libro) jlistLibros.getSelectedValue();

            rellenarCamposLibro();
        }
    }//GEN-LAST:event_jlistLibrosValueChanged

    /**
     * Metodo para simular el PlaceHolder del JTextField buscar por codigo
     * @param evt 
     */
    private void textBusquedaCodigoLibroFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textBusquedaCodigoLibroFocusGained
        if (textBusquedaCodigoLibro.getText().equals(placeHolderCodigo)) {
            textBusquedaCodigoLibro.setText("");
            textBusquedaCodigoLibro.setForeground(new Color(51, 51, 51));
        }
    }//GEN-LAST:event_textBusquedaCodigoLibroFocusGained

    /**
     * Metodo para simular el PlaceHolder del JTextField buscar por codigo
     * @param evt 
     */
    private void textBusquedaCodigoLibroFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textBusquedaCodigoLibroFocusLost
        // TODO add your handling code here:
        textBusquedaCodigoLibro.setForeground(new Color(102, 102, 102));
        textBusquedaCodigoLibro.setText(placeHolderCodigo);
    }//GEN-LAST:event_textBusquedaCodigoLibroFocusLost

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
            java.util.logging.Logger.getLogger(FrameLibro.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameLibro.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameLibro.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameLibro.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameLibro().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnBadStatus15;
    private com.mommoo.flat.button.FlatButton btnBuscar;
    private com.mommoo.flat.button.FlatButton btnCancelar;
    private javax.swing.JMenuItem btnEditar;
    private javax.swing.JMenuItem btnEliminar;
    private javax.swing.JLabel btnGoodStatus15;
    private com.mommoo.flat.button.FlatButton btnGuardar;
    private javax.swing.JMenuItem btnImprimirEtiquetas;
    private com.mommoo.flat.button.FlatButton btnNewLibro;
    private com.mommoo.flat.button.FlatButton btnOpciones;
    private javax.swing.JLabel btnRegularStatus15;
    private com.mommoo.flat.button.FlatButton btnVerAlumno;
    private javax.swing.JComboBox cbAsignatura;
    private javax.swing.JComboBox cbCursoBuscar;
    private javax.swing.JComboBox cbCursoLibro;
    private javax.swing.JCheckBox chkObsoletoLibro;
    private com.mommoo.flat.button.FlatButton flatButton3;
    private com.mommoo.flat.button.FlatButton flatButton5;
    private com.mommoo.flat.button.FlatButton flatButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JList jlistLibros;
    private javax.swing.JLabel labelInfoEdicion;
    private javax.swing.JPopupMenu menuOpcionesLibro;
    private javax.swing.JPanel panelAsignatura;
    private javax.swing.JPanel panelBotoneraEdicion;
    private javax.swing.JPanel panelCodigoLibro;
    private javax.swing.JPanel panelCuerpo;
    private javax.swing.JPanel panelCurso;
    private javax.swing.JPanel panelDerecho;
    private javax.swing.JPanel panelEstado15;
    private javax.swing.JPanel panelEstadoParent15;
    private javax.swing.JPanel panelGeneralDerechoInferior;
    private javax.swing.JPanel panelGeneralDerechoSuperior;
    private javax.swing.JPanel panelGeneralIzquierdo;
    private javax.swing.JPanel panelISBNLibro;
    private javax.swing.JPanel panelInferior;
    private javax.swing.JPanel panelInferiorDerecho;
    private javax.swing.JPanel panelIzquierdo;
    private javax.swing.JPanel panelMedio;
    private javax.swing.JPanel panelNoPrestado;
    private javax.swing.JPanel panelNombreLibro;
    private javax.swing.JPanel panelPrestado;
    private javax.swing.JPanel panelSuperior;
    private javax.swing.JPanel panelSuperiorDerecho;
    private javax.swing.JPanel panelTitulo;
    private javax.swing.JPanel panelTitulo1;
    private javax.swing.JTextField textBusquedaCodigoLibro;
    private javax.swing.JTextField textCodigoLibro;
    private javax.swing.JLabel textEmailAlumno;
    private javax.swing.JTextField textISBNLibro;
    private javax.swing.JLabel textNIAAlumno;
    private javax.swing.JLabel textNombreAlumno;
    private javax.swing.JTextField textNombreLibro;
    private javax.swing.JTextField textNombreLibroBusqueda;
    private javax.swing.JTextField textPrecioLibro;
    private javax.swing.JLabel textTelefonoAlumno;
    private javax.swing.JLabel textTitulo;
    private javax.swing.JLabel textTitulo1;
    private javax.swing.JTextField textUnidadesLibro;
    // End of variables declaration//GEN-END:variables

    /**
     * Cargamos las listas de Libros y Cursos y rellenamos los ComboBox
     * correspondientes.
     */
    public void cargarDatos() {
        SwingWorker<?, ?> worker = new SwingWorker<Void, Void>() {
            protected Void doInBackground() throws InterruptedException {
                //Cargamos los datos de los libros y ejemplares
                listaLibros = daoLibro.buscarTodos();
                listaCursos = daoCurso.buscarTodos();

                System.out.println("Libros cargados: " + listaLibros.size() + " Cursos cargados: " + listaCursos.size());
                return null;
            }

            protected void done() {
                //Aplicamos el filtro
                filtroListaLibro(textNombreLibro.getText(), cbCursoBuscar.getSelectedItem().toString());

                rellenaCursosBusqueda();

                frameCarga.dispose();
            }
        };
        worker.execute();
        if (frameCarga == null) {
            frameCarga = new FramePopup();
        }
        frameCarga.setVisible(true);
    }

    /**
     * Metodo para filtrar la lista de Libros (Panel Izquierdo)
     */
    private void filtroListaLibro(String textoNombre, String textoCurso) {
        //Creamos una lista temporal de los libros para realizar la busqueda
        List<Libro> listaFiltroLibros = listaLibros;

        //Creamos las variables del filtro
        String n, c, resFiltro = "";

        //Clasificamos el filtro
        //<editor-fold defaultstate="collapsed" desc="Clasificacion del filtro">
        if (textNombreLibroBusqueda.getText().equals(placeHolderNombre)) {
            n = "0";
        } else {
            n = "1";
        }

        if (cbCursoBuscar.getSelectedItem().toString().equals("Selecciona curso")) {
            c = "0";
        } else {
            c = "1";
        }

        resFiltro = n + c;
        //</editor-fold>

        //Si tenemos mas de 0 libros:
        if (listaLibros != null && listaLibros.size() > 0) {
            jlistLibros.setEnabled(true);
            switch (resFiltro) {
                case "00":
                    //Se muestran todos los libros de todos los cursos
                    asignarModeloToList(jlistLibros, listaFiltroLibros);
                    break;

                case "01":
                    //Se muestran los libros del curso seleccionado
                    listaFiltroLibros = listaLibros.stream().filter(libro -> (libro.getContenido().getCurso().getAbreviatura().toUpperCase() + " - " + libro.getContenido().getCurso().getNombre_cas().toUpperCase()).equals(textoCurso.toUpperCase())).collect(Collectors.toList());
                    break;

                case "10":
                    //Se seleccionan los libros del nombre escrito
                    listaFiltroLibros = listaLibros.stream().filter(libro -> libro.getNombre().toUpperCase().contains(textoNombre.toUpperCase())).collect(Collectors.toList());
                    break;

                case "11":
                    //se seleccionan los libros del curso seleccionado con el nombre escrito
                    listaFiltroLibros = listaLibros.stream().filter(libro -> libro.getNombre().toUpperCase().contains(textoNombre.toUpperCase())).collect(Collectors.toList());
                    listaFiltroLibros = listaFiltroLibros.stream().filter(libro -> (libro.getContenido().getCurso().getAbreviatura().toUpperCase() + " - " + libro.getContenido().getCurso().getNombre_cas().toUpperCase()).equals(textoCurso.toUpperCase())).collect(Collectors.toList());
                    break;
            }

            if (listaFiltroLibros.size() > 0) {
                asignarModeloToList(jlistLibros, listaFiltroLibros);
            } else {
                DefaultListModel listModel = new DefaultListModel();
                listModel.addElement("No existen libros con este nombre");
                jlistLibros.setModel(listModel);
                jlistLibros.setEnabled(false);
            }
        } else {
            DefaultListModel listModel = new DefaultListModel();
            listModel.addElement("No existen libros en la base de datos");
            jlistLibros.setModel(listModel);
            jlistLibros.setEnabled(false);
        }
    }

    /**
     * Metodo para asignar un modelo de datos a una JList
     */
    private void asignarModeloToList(JList jlist, List<Libro> lista) {
        DefaultListModel listModel = new DefaultListModel();
        for (int i = 0; i < lista.size(); i++) {
            listModel.addElement(lista.get(i));
        }
        jlist.setModel(listModel);
    }

    /**
     * Metodo para buscar un Libro mediante el codigo recibido
     */
    private void buscarLibro(String codigo) {
        if (!codigo.equals("")) {
            //Se ha insertado un codigo
            SwingWorker<?, ?> worker = new SwingWorker<Void, Integer>() {
                protected Void doInBackground() throws InterruptedException {
                    libro = daoLibro.buscar(codigo);
                    return null;
                }

                protected void process(List<Integer> chunks) {
                }

                protected void done() {
                    frameCarga.dispose();

                    if (libro != null) {
                        rellenarCamposLibro();
                    } else {
                        rellenarCamposLibro();

                        JOptionPane.showMessageDialog(FrameLibro.this,
                                "No existe ningún libro con este código.",
                                "Error de búsqueda", JOptionPane.ERROR_MESSAGE);
                    }

                }
            };
            worker.execute();
            if (frameCarga == null) {
                frameCarga = new FramePopup();
            }
            frameCarga.setVisible(true);
        } else {
            //No se ha insertado ningun valor en el campo de texto

            libro = null;

            rellenarCamposLibro();

            JOptionPane.showMessageDialog(FrameLibro.this,
                    "El código no puede ser un campo vacío.",
                    "Error de búsqueda", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Metodo para rellenar el campos con los datos de un Libro
     */
    private void rellenarCamposLibro() {
        if (libro == null) {
            textCodigoLibro.setText("");
            textNombreLibro.setText("");
            textISBNLibro.setText("");
            textUnidadesLibro.setText("");
            textPrecioLibro.setText("");
            chkObsoletoLibro.setSelected(false);
        } else {
            //Rellenamos los datos
            textCodigoLibro.setText(libro.getCodigo());
            textNombreLibro.setText(libro.getNombre());
            textISBNLibro.setText(libro.getISBN());

            rellenaCursosLibro();

            for (int i = 0; i < cbCursoLibro.getItemCount(); i++) {
                Curso c = (Curso) cbCursoLibro.getItemAt(i);

                if (libro.getContenido().getCurso().getId().equals(c.getId())) {
                    cbCursoLibro.setSelectedIndex(i);
                    break;
                }
            }

            rellenarContenidosLibro();

            for (int i = 0; i < cbAsignatura.getItemCount(); i++) {
                Contenido c = (Contenido) cbAsignatura.getItemAt(i);

                if (libro.getContenido().getId() == c.getId()) {
                    cbAsignatura.setSelectedIndex(i);
                }
            }

            cbAsignatura.addItem(libro.getContenido().getNombre_cas());
            textUnidadesLibro.setText(libro.getUnidades() + "");
            textPrecioLibro.setText(libro.getPrecio() + "");
            chkObsoletoLibro.setSelected(libro.getObsoleto());
        }
    }

    /**
     * Metodo para rellenar el ComboBox de los Cursos en pestaña Busqueda
     */
    private void rellenaCursosBusqueda() {
        if (listaCursos.size() > 0) {
            for (int i = 0; i < listaCursos.size(); i++) {
                cbCursoBuscar.addItem(listaCursos.get(i).getAbreviatura() + " - " + listaCursos.get(i).getNombre_cas());
            }
        } else {
            JOptionPane.showMessageDialog(FrameLibro.this,
                    "No hay cursos en la base de datos.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Metodo para rellenar el ComboBox de los Cursos en pestaña Libro
     */
    private void rellenaCursosLibro() {
        if (listaCursos.size() > 0) {

            cbCursoLibro.removeAllItems();

            for (int i = 0; i < listaCursos.size(); i++) {
                cbCursoLibro.addItem(listaCursos.get(i));
            }
        } else {
            JOptionPane.showMessageDialog(FrameLibro.this,
                    "No hay cursos en la base de datos.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Metodo para rellenar el ComboBox de los Contenidos en pestaña Libro
     */
    private void rellenarContenidosLibro() {
        Curso cursoSeleccionado = (Curso) cbCursoLibro.getSelectedItem();

        cbAsignatura.removeAllItems();

        if (cursoSeleccionado != null) {
            for (int i = 0; i < cursoSeleccionado.getContenidos().size(); i++) {
                cbAsignatura.addItem(cursoSeleccionado.getContenidos().get(i));
            }
        } else {
            System.out.println("No hay curso seleccionado");
        }
    }

    /**
     * Metodo para vaciar los ComboBox de Cursos y Contenidos
     */
    private void vaciarCursosYContenidos() {
        cbCursoLibro.removeAllItems();
        cbAsignatura.removeAllItems();
    }

    /**
     * Metodo para habilitar o deshabilitar la edición de un Libro
     */
    public void setEditMode(boolean editable) {
        modoEdicion = editable;

        textNombreLibro.setEnabled(editable);
        textISBNLibro.setEnabled(editable);
        cbCursoLibro.setEnabled(editable);
        cbAsignatura.setEnabled(editable);
        textUnidadesLibro.setEnabled(editable);
        chkObsoletoLibro.setEnabled(editable);
        //btnImprimirEtiquetas.setEnabled(editable);
        textPrecioLibro.setEnabled(editable);

        labelInfoEdicion.setVisible(editable);
        panelBotoneraEdicion.setVisible(editable);

        if (libro == null) {
            textCodigoLibro.setEnabled(editable);
        } else {
            textCodigoLibro.setEnabled(false);
        }
    }
}
