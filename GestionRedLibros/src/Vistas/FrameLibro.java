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

import Utilidades.Imagenes.Imagenes;
import Daos.*;
import Pojos.*;
import Renders.comboBoxRender;
import Utilidades.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.persistence.PersistenceException;
import javax.swing.*;
import org.hibernate.Session;

/**
 *
 * @author Jose Sanchis
 */
public class FrameLibro extends javax.swing.JFrame {
    
    private Session session = Main.gestorSesiones.getSession();
    
    boolean modoEdicion = false;
    boolean primeraEjecucion = true;
    
    public int contadorEjemplar;
    
    private Curso cursoSeleccionado = null;
    
    private String campoBusquedaTemp = "";
    
    private FramePopup frameCarga = null,
            popupConfirmacion = null;
    
    private Boolean accionRealizada = false;
    public static int nEjemplaresAdd = -1;
    
    private DaoLibro daoLibro;
    private DaoCurso daoCurso;
    private DaoHistorial daoHistorial;
    
    public Thread animacion = null;
    private boolean boolAnimacion = false;
    
    public static Libro libro;
    
    private List<Libro> listaLibros;
    private List<Curso> listaCursos;
    private List<Ejemplar> listaEjemplares;

    //Cogemos el frame padre para trabajar con los dialogos
    private JFrame topFrame;
    
    private FrameDemanda stock;

    /**
     * Inicializamos los componentes y cargamos los datos necesarios.
     */
    public FrameLibro() {
        initComponents();
        
        topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);

        //Posicionamos la pestaña al centro de la pantalla
        this.setLocationRelativeTo(null);

        //Maximizamos la ventana
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        //Ponemos el foco en el textField del codigo
        textCodigoLibro.requestFocusInWindow();

        //Ocultamos la flecha de mostrar la busqueda
        btnMostrarBuscar.setVisible(false);

        //Configuramos los PlaceHolders
        textNombreLibroBusqueda.setText(StringsGlobales.placeHolder_nombre);
        textBusquedaCodigoLibro.setText(StringsGlobales.placeHolder_codigo);

        //Inicializamos los Daos
        daoCurso = new DaoCurso(session);
        daoLibro = new DaoLibro(session);
        daoHistorial = new DaoHistorial(session);

        //Configuramos la parte visual de los ComboBox
        UIManager.put("ComboBox.disabledBackground", Colores.fondo);
        UIManager.put("ComboBox.disabledForeground", Colores.campoTextSinFocus);

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
                    l.setForeground(Colores.letraBotones);
                    l.setBackground(Colores.accento);
                } else {
                    l.setForeground(Colores.letraNormal);
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
                    l.setForeground(Colores.letraBotones);
                    l.setBackground(Colores.accento);
                } else {
                    l.setForeground(Colores.letraNormal);
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
                    l.setForeground(Colores.letraBotones);
                    l.setBackground(Colores.accento);
                } else {
                    l.setForeground(Colores.letraNormal);
                    l.setBackground(Colores.fondo);
                }
                return l;
            }
        });
//</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Configuración lista desplegable menupopup">
        UIManager.put("MenuItem.selectionBackground", Colores.accento);
        UIManager.put("MenuItem.selectionForeground", Colores.letraBotones);
//</editor-fold>

        setEditMode(false);
        
        mostrarElementosEjemplares(libro != null);
        
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
        btnAnadirEjemplares = new javax.swing.JMenuItem();
        btnEditar = new javax.swing.JMenuItem();
        btnEliminar = new javax.swing.JMenuItem();
        jSplitPane1 = new javax.swing.JSplitPane();
        panelIzquierdo = new javax.swing.JPanel();
        panelGeneralIzquierdo = new javax.swing.JPanel();
        panelTitulo1 = new javax.swing.JPanel();
        textTitulo1 = new javax.swing.JLabel();
        btnOcultarBuscar = new com.mommoo.flat.button.FlatButton();
        panelBusqueda = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        btnBuscar = new com.mommoo.flat.button.FlatButton();
        textBusquedaCodigoLibro = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        textNombreLibroBusqueda = new javax.swing.JTextField();
        cbCursoBuscar = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jlistLibros = new javax.swing.JList();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane4 = new javax.swing.JScrollPane();
        panelDerecho = new javax.swing.JPanel();
        jSplitPane2 = new javax.swing.JSplitPane();
        panelSuperiorDerecho = new javax.swing.JPanel();
        panelGeneralDerechoSuperior = new javax.swing.JPanel();
        panelTituloLibro = new javax.swing.JPanel();
        textTitulo = new javax.swing.JLabel();
        btnOpciones = new com.mommoo.flat.button.FlatButton();
        labelInfoEdicion = new javax.swing.JLabel();
        btnNewLibro = new com.mommoo.flat.button.FlatButton();
        btnMostrarBuscar = new com.mommoo.flat.button.FlatButton();
        jScrollPane2 = new javax.swing.JScrollPane();
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
        textUnidadesTotalesLibro = new javax.swing.JTextField();
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
        panelGeneralDerechoInferior = new javax.swing.JPanel();
        panelTituloEjemplar = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        textTotalEjemplares = new javax.swing.JLabel();
        btnSiguiente = new com.mommoo.flat.button.FlatButton();
        btnAnterior = new com.mommoo.flat.button.FlatButton();
        textEjemplaresDisponibles = new javax.swing.JLabel();
        panelEjemplarNoSeleccionado = new javax.swing.JPanel();
        textEjemplarNotificacion = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        panelEjemplarPrestado = new javax.swing.JPanel();
        panelEstadoParent15 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        panelEstado15 = new javax.swing.JPanel();
        btnBadStatus = new javax.swing.JLabel();
        btnRegularStatus = new javax.swing.JLabel();
        btnGoodStatus = new javax.swing.JLabel();
        panelPrestado = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        sexoAlumno = new javax.swing.JLabel();
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
        panelNoPrestado = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        textNoPrestado = new javax.swing.JLabel();
        panelCodigoEjemplar = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        imgCodigo = new javax.swing.JLabel();
        btnImprimirEtiquetaEjemplar = new com.mommoo.flat.button.FlatButton();
        textCodigo = new javax.swing.JLabel();
        textTituloLibro = new javax.swing.JLabel();

        menuOpcionesLibro.setBackground(new java.awt.Color(66, 47, 44));
        menuOpcionesLibro.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        menuOpcionesLibro.setForeground(new java.awt.Color(66, 47, 44));

        btnImprimirEtiquetas.setBackground(Colores.fondo);
        btnImprimirEtiquetas.setFont(new java.awt.Font("Dialog", 1, 21)); // NOI18N
        btnImprimirEtiquetas.setForeground(Colores.letraNormal);
        btnImprimirEtiquetas.setText("Imprimir Etiquetas");
        btnImprimirEtiquetas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirEtiquetasActionPerformed(evt);
            }
        });
        menuOpcionesLibro.add(btnImprimirEtiquetas);
        menuOpcionesLibro.add(jSeparator1);

        btnAnadirEjemplares.setBackground(Colores.fondo);
        btnAnadirEjemplares.setFont(new java.awt.Font("Dialog", 1, 21)); // NOI18N
        btnAnadirEjemplares.setForeground(Colores.letraNormal);
        btnAnadirEjemplares.setText("Añadir ejemplares");
        btnAnadirEjemplares.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnadirEjemplaresActionPerformed(evt);
            }
        });
        menuOpcionesLibro.add(btnAnadirEjemplares);

        btnEditar.setBackground(Colores.fondo);
        btnEditar.setFont(new java.awt.Font("Dialog", 1, 21)); // NOI18N
        btnEditar.setForeground(Colores.letraNormal);
        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        menuOpcionesLibro.add(btnEditar);

        btnEliminar.setBackground(Colores.fondo);
        btnEliminar.setFont(new java.awt.Font("Dialog", 1, 21)); // NOI18N
        btnEliminar.setForeground(Colores.letraNormal);
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        menuOpcionesLibro.add(btnEliminar);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Libros");
        setMinimumSize(new java.awt.Dimension(1024, 768));
        setPreferredSize(new java.awt.Dimension(1024, 768));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jSplitPane1.setBorder(null);

        panelIzquierdo.setMinimumSize(new java.awt.Dimension(400, 0));
        panelIzquierdo.setPreferredSize(new java.awt.Dimension(400, 930));

        panelGeneralIzquierdo.setBackground(Colores.fondo);
        panelGeneralIzquierdo.setPreferredSize(new java.awt.Dimension(450, 600));
        panelGeneralIzquierdo.setRequestFocusEnabled(false);

        panelTitulo1.setBackground(Colores.fondoOscuro);

        textTitulo1.setBackground(new java.awt.Color(239, 235, 233));
        textTitulo1.setFont(new java.awt.Font("Dialog", 3, 24)); // NOI18N
        textTitulo1.setForeground(Colores.letraTitulo);
        textTitulo1.setText("Buscar");

        btnOcultarBuscar.setBackground(Colores.botones);
        btnOcultarBuscar.setForeground(Colores.letraBotones);
        btnOcultarBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons/chevron-left.png"))); // NOI18N
        btnOcultarBuscar.setCornerRound(10);
        btnOcultarBuscar.setPreferredSize(new java.awt.Dimension(48, 48));
        btnOcultarBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOcultarBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelTitulo1Layout = new javax.swing.GroupLayout(panelTitulo1);
        panelTitulo1.setLayout(panelTitulo1Layout);
        panelTitulo1Layout.setHorizontalGroup(
            panelTitulo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTitulo1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(textTitulo1, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                .addGap(196, 196, 196)
                .addComponent(btnOcultarBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelTitulo1Layout.setVerticalGroup(
            panelTitulo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTitulo1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTitulo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnOcultarBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(textTitulo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        panelBusqueda.setBackground(Colores.fondo);
        panelBusqueda.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        panelBusqueda.setPreferredSize(new java.awt.Dimension(450, 858));

        jPanel1.setBackground(Colores.fondo);

        btnBuscar.setBackground(Colores.botones);
        btnBuscar.setForeground(Colores.letraBotones);
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons/magnify.png"))); // NOI18N
        btnBuscar.setCornerRound(10);
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        textBusquedaCodigoLibro.setBackground(Colores.fondo);
        textBusquedaCodigoLibro.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        textBusquedaCodigoLibro.setForeground(Colores.campoTextSinFocus);
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
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textBusquedaCodigoLibroKeyReleased(evt);
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
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(textBusquedaCodigoLibro))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(Colores.fondo);

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setForeground(Colores.letraNormal);
        jLabel2.setText("Listado de libros:");

        textNombreLibroBusqueda.setBackground(Colores.fondo);
        textNombreLibroBusqueda.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        textNombreLibroBusqueda.setForeground(Colores.campoTextSinFocus);
        textNombreLibroBusqueda.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        textNombreLibroBusqueda.setMinimumSize(new java.awt.Dimension(200, 32));
        textNombreLibroBusqueda.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textNombreLibroBusquedaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                textNombreLibroBusquedaFocusLost(evt);
            }
        });
        textNombreLibroBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textNombreLibroBusquedaKeyReleased(evt);
            }
        });

        cbCursoBuscar.setBackground(Colores.fondo);
        cbCursoBuscar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        cbCursoBuscar.setForeground(Colores.letraNormal);
        cbCursoBuscar.setMinimumSize(new java.awt.Dimension(0, 0));
        cbCursoBuscar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbCursoBuscarItemStateChanged(evt);
            }
        });

        jlistLibros.setBackground(Colores.fondo);
        jlistLibros.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jlistLibros.setForeground(Colores.letraNormal);
        jlistLibros.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jlistLibros.setSelectionBackground(Colores.accento);
        jlistLibros.setSelectionForeground(Colores.letraBotones);
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
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1)
                    .addComponent(cbCursoBuscar, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(textNombreLibroBusqueda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textNombreLibroBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbCursoBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 649, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelBusquedaLayout = new javax.swing.GroupLayout(panelBusqueda);
        panelBusqueda.setLayout(panelBusquedaLayout);
        panelBusquedaLayout.setHorizontalGroup(
            panelBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2)
            .addGroup(panelBusquedaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelBusquedaLayout.setVerticalGroup(
            panelBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBusquedaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelGeneralIzquierdoLayout = new javax.swing.GroupLayout(panelGeneralIzquierdo);
        panelGeneralIzquierdo.setLayout(panelGeneralIzquierdoLayout);
        panelGeneralIzquierdoLayout.setHorizontalGroup(
            panelGeneralIzquierdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelTitulo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelGeneralIzquierdoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelBusqueda, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelGeneralIzquierdoLayout.setVerticalGroup(
            panelGeneralIzquierdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGeneralIzquierdoLayout.createSequentialGroup()
                .addComponent(panelTitulo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelBusqueda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelIzquierdoLayout = new javax.swing.GroupLayout(panelIzquierdo);
        panelIzquierdo.setLayout(panelIzquierdoLayout);
        panelIzquierdoLayout.setHorizontalGroup(
            panelIzquierdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelGeneralIzquierdo, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        panelIzquierdoLayout.setVerticalGroup(
            panelIzquierdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelGeneralIzquierdo, javax.swing.GroupLayout.DEFAULT_SIZE, 930, Short.MAX_VALUE)
        );

        jSplitPane1.setLeftComponent(panelIzquierdo);

        jScrollPane4.setMinimumSize(new java.awt.Dimension(400, 20));

        panelDerecho.setMinimumSize(new java.awt.Dimension(905, 0));
        panelDerecho.setPreferredSize(new java.awt.Dimension(905, 608));

        jSplitPane2.setBorder(null);
        jSplitPane2.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        panelSuperiorDerecho.setMinimumSize(new java.awt.Dimension(800, 420));
        panelSuperiorDerecho.setPreferredSize(new java.awt.Dimension(800, 420));

        panelGeneralDerechoSuperior.setBackground(Colores.fondo);
        panelGeneralDerechoSuperior.setPreferredSize(new java.awt.Dimension(750, 400));

        panelTituloLibro.setBackground(Colores.fondoOscuro);

        textTitulo.setBackground(new java.awt.Color(239, 235, 233));
        textTitulo.setFont(new java.awt.Font("Dialog", 3, 24)); // NOI18N
        textTitulo.setForeground(Colores.letraTitulo);
        textTitulo.setText("Libro");

        btnOpciones.setBackground(Colores.botones);
        btnOpciones.setForeground(Colores.letraBotones);
        btnOpciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons/dots-vertical.png"))); // NOI18N
        btnOpciones.setCornerRound(10);
        btnOpciones.setPreferredSize(new java.awt.Dimension(48, 48));
        btnOpciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnOpcionesMouseReleased(evt);
            }
        });

        labelInfoEdicion.setBackground(Colores.accento);
        labelInfoEdicion.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        labelInfoEdicion.setForeground(Colores.letraTitulo);
        labelInfoEdicion.setText("modo edición");

        btnNewLibro.setBackground(Colores.botones);
        btnNewLibro.setForeground(Colores.letraBotones);
        btnNewLibro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons/plus.png"))); // NOI18N
        btnNewLibro.setCornerRound(10);
        btnNewLibro.setPreferredSize(new java.awt.Dimension(48, 48));
        btnNewLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewLibroActionPerformed(evt);
            }
        });

        btnMostrarBuscar.setBackground(Colores.botones);
        btnMostrarBuscar.setForeground(Colores.letraBotones);
        btnMostrarBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons/chevron-right.png"))); // NOI18N
        btnMostrarBuscar.setCornerRound(10);
        btnMostrarBuscar.setPreferredSize(new java.awt.Dimension(48, 48));
        btnMostrarBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelTituloLibroLayout = new javax.swing.GroupLayout(panelTituloLibro);
        panelTituloLibro.setLayout(panelTituloLibroLayout);
        panelTituloLibroLayout.setHorizontalGroup(
            panelTituloLibroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTituloLibroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnMostrarBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(textTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelInfoEdicion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1042, Short.MAX_VALUE)
                .addComponent(btnNewLibro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelTituloLibroLayout.setVerticalGroup(
            panelTituloLibroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTituloLibroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTituloLibroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnNewLibro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnOpciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelInfoEdicion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(textTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnMostrarBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane2.setBackground(Colores.fondo);
        jScrollPane2.setBorder(null);
        jScrollPane2.setMinimumSize(new java.awt.Dimension(860, 20));

        panelCuerpo.setBackground(Colores.fondo);
        panelCuerpo.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        panelCuerpo.setMinimumSize(new java.awt.Dimension(860, 340));
        panelCuerpo.setPreferredSize(new java.awt.Dimension(860, 320));

        panelCodigoLibro.setBackground(Colores.fondo);
        panelCodigoLibro.setForeground(Colores.letraNormal);

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setForeground(Colores.letraNormal);
        jLabel3.setText("Código del libro:");

        textCodigoLibro.setBackground(Colores.fondo);
        textCodigoLibro.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        textCodigoLibro.setForeground(Colores.letraNormal);
        textCodigoLibro.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        textCodigoLibro.setDisabledTextColor(Colores.campoTextSinFocus);

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
        panelSuperior.setForeground(Colores.letraNormal);
        panelSuperior.setLayout(new java.awt.GridLayout(1, 0));

        panelNombreLibro.setBackground(Colores.fondo);
        panelNombreLibro.setForeground(Colores.letraNormal);

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setForeground(Colores.letraNormal);
        jLabel4.setText("Nombre:");

        textNombreLibro.setBackground(Colores.fondo);
        textNombreLibro.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        textNombreLibro.setForeground(Colores.letraNormal);
        textNombreLibro.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        textNombreLibro.setDisabledTextColor(Colores.campoTextSinFocus);

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
        panelISBNLibro.setForeground(Colores.letraNormal);

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setForeground(Colores.letraNormal);
        jLabel5.setText("ISBN:");

        textISBNLibro.setBackground(Colores.fondo);
        textISBNLibro.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        textISBNLibro.setForeground(Colores.letraNormal);
        textISBNLibro.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        textISBNLibro.setDisabledTextColor(Colores.campoTextSinFocus);

        javax.swing.GroupLayout panelISBNLibroLayout = new javax.swing.GroupLayout(panelISBNLibro);
        panelISBNLibro.setLayout(panelISBNLibroLayout);
        panelISBNLibroLayout.setHorizontalGroup(
            panelISBNLibroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelISBNLibroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelISBNLibroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelISBNLibroLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(textISBNLibro))
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
        panelMedio.setForeground(Colores.letraNormal);
        panelMedio.setLayout(new java.awt.GridLayout(1, 0));

        panelCurso.setBackground(Colores.fondo);
        panelCurso.setForeground(Colores.letraNormal);

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel6.setForeground(Colores.letraNormal);
        jLabel6.setText("Curso:");

        cbCursoLibro.setBackground(Colores.fondo);
        cbCursoLibro.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        cbCursoLibro.setForeground(Colores.letraNormal);
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
                        .addComponent(cbCursoLibro, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        panelAsignatura.setForeground(Colores.letraNormal);

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel7.setForeground(Colores.letraNormal);
        jLabel7.setText("Asignatura:");

        cbAsignatura.setBackground(Colores.fondo);
        cbAsignatura.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        cbAsignatura.setForeground(Colores.letraNormal);

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
                        .addGap(0, 0, Short.MAX_VALUE)))
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
        panelInferior.setForeground(Colores.letraNormal);
        panelInferior.setLayout(new java.awt.GridLayout(1, 0));

        jPanel3.setBackground(Colores.fondo);
        jPanel3.setForeground(Colores.letraNormal);

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel8.setForeground(Colores.letraNormal);
        jLabel8.setText("Unidades Totales:");

        textUnidadesTotalesLibro.setBackground(Colores.fondo);
        textUnidadesTotalesLibro.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        textUnidadesTotalesLibro.setForeground(Colores.letraNormal);
        textUnidadesTotalesLibro.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        textUnidadesTotalesLibro.setDisabledTextColor(Colores.campoTextSinFocus);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(textUnidadesTotalesLibro))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textUnidadesTotalesLibro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelInferior.add(jPanel3);

        jPanel4.setBackground(Colores.fondo);
        jPanel4.setForeground(Colores.letraNormal);

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel9.setForeground(Colores.letraNormal);
        jLabel9.setText("Precio:");

        textPrecioLibro.setBackground(Colores.fondo);
        textPrecioLibro.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        textPrecioLibro.setForeground(Colores.letraNormal);
        textPrecioLibro.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        textPrecioLibro.setDisabledTextColor(Colores.campoTextSinFocus);
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
                        .addGap(0, 0, Short.MAX_VALUE)))
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
        jPanel5.setForeground(Colores.letraNormal);

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel10.setForeground(Colores.letraNormal);
        jLabel10.setText("Estado del libro:");

        chkObsoletoLibro.setBackground(Colores.fondo);
        chkObsoletoLibro.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        chkObsoletoLibro.setForeground(Colores.letraNormal);
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        panelBotoneraEdicion.setBackground(Colores.fondo);
        panelBotoneraEdicion.setForeground(Colores.letraNormal);

        btnGuardar.setBackground(Colores.botones);
        btnGuardar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnGuardar.setForeground(Colores.letraBotones);
        btnGuardar.setText("Guardar");
        btnGuardar.setCornerRound(10);
        btnGuardar.setPreferredSize(new java.awt.Dimension(128, 32));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnCancelar.setBackground(Colores.botones);
        btnCancelar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnCancelar.setForeground(Colores.letraBotones);
        btnCancelar.setText("Cancelar");
        btnCancelar.setCornerRound(10);
        btnCancelar.setPreferredSize(new java.awt.Dimension(128, 32));
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBotoneraEdicionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBotoneraEdicionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout panelCuerpoLayout = new javax.swing.GroupLayout(panelCuerpo);
        panelCuerpo.setLayout(panelCuerpoLayout);
        panelCuerpoLayout.setHorizontalGroup(
            panelCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCuerpoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelSuperior, javax.swing.GroupLayout.DEFAULT_SIZE, 1387, Short.MAX_VALUE)
                    .addComponent(panelMedio, javax.swing.GroupLayout.DEFAULT_SIZE, 1387, Short.MAX_VALUE)
                    .addComponent(panelInferior, javax.swing.GroupLayout.DEFAULT_SIZE, 1387, Short.MAX_VALUE)
                    .addGroup(panelCuerpoLayout.createSequentialGroup()
                        .addComponent(panelCodigoLibro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCuerpoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelBotoneraEdicion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelBotoneraEdicion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(panelCuerpo);

        javax.swing.GroupLayout panelGeneralDerechoSuperiorLayout = new javax.swing.GroupLayout(panelGeneralDerechoSuperior);
        panelGeneralDerechoSuperior.setLayout(panelGeneralDerechoSuperiorLayout);
        panelGeneralDerechoSuperiorLayout.setHorizontalGroup(
            panelGeneralDerechoSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelTituloLibro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelGeneralDerechoSuperiorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1395, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelGeneralDerechoSuperiorLayout.setVerticalGroup(
            panelGeneralDerechoSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGeneralDerechoSuperiorLayout.createSequentialGroup()
                .addComponent(panelTituloLibro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelSuperiorDerechoLayout = new javax.swing.GroupLayout(panelSuperiorDerecho);
        panelSuperiorDerecho.setLayout(panelSuperiorDerechoLayout);
        panelSuperiorDerechoLayout.setHorizontalGroup(
            panelSuperiorDerechoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelGeneralDerechoSuperior, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1407, Short.MAX_VALUE)
        );
        panelSuperiorDerechoLayout.setVerticalGroup(
            panelSuperiorDerechoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelGeneralDerechoSuperior, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
        );

        jSplitPane2.setLeftComponent(panelSuperiorDerecho);

        panelInferiorDerecho.setMinimumSize(new java.awt.Dimension(905, 300));
        panelInferiorDerecho.setPreferredSize(new java.awt.Dimension(750, 450));

        panelGeneralDerechoInferior.setBackground(Colores.fondo);
        panelGeneralDerechoInferior.setPreferredSize(new java.awt.Dimension(750, 400));

        panelTituloEjemplar.setBackground(Colores.fondoOscuro);
        panelTituloEjemplar.setForeground(Colores.accento);
        panelTituloEjemplar.setMinimumSize(new java.awt.Dimension(0, 60));
        panelTituloEjemplar.setPreferredSize(new java.awt.Dimension(250, 60));

        jLabel1.setFont(new java.awt.Font("Dialog", 3, 24)); // NOI18N
        jLabel1.setForeground(Colores.letraTitulo);
        jLabel1.setText("Ejemplares");

        textTotalEjemplares.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        textTotalEjemplares.setForeground(Colores.letraTitulo);
        textTotalEjemplares.setText("1 de 25");

        btnSiguiente.setBackground(Colores.botones);
        btnSiguiente.setForeground(Colores.letraBotones);
        btnSiguiente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons/arrow-right.png"))); // NOI18N
        btnSiguiente.setCornerRound(10);
        btnSiguiente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSiguienteMouseClicked(evt);
            }
        });

        btnAnterior.setBackground(Colores.botones);
        btnAnterior.setForeground(Colores.letraBotones);
        btnAnterior.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons/arrow-left.png"))); // NOI18N
        btnAnterior.setCornerRound(10);
        btnAnterior.setPreferredSize(new java.awt.Dimension(48, 48));
        btnAnterior.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAnteriorMouseClicked(evt);
            }
        });

        textEjemplaresDisponibles.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        textEjemplaresDisponibles.setForeground(Colores.letraTitulo);
        textEjemplaresDisponibles.setText("(Disponibles: 20)");

        javax.swing.GroupLayout panelTituloEjemplarLayout = new javax.swing.GroupLayout(panelTituloEjemplar);
        panelTituloEjemplar.setLayout(panelTituloEjemplarLayout);
        panelTituloEjemplarLayout.setHorizontalGroup(
            panelTituloEjemplarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTituloEjemplarLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textTotalEjemplares)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textEjemplaresDisponibles)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 424, Short.MAX_VALUE)
                .addComponent(btnAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelTituloEjemplarLayout.setVerticalGroup(
            panelTituloEjemplarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTituloEjemplarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTituloEjemplarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(textEjemplaresDisponibles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSiguiente, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAnterior, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(textTotalEjemplares, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        panelEjemplarNoSeleccionado.setBackground(Colores.fondo);
        panelEjemplarNoSeleccionado.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        panelEjemplarNoSeleccionado.setPreferredSize(new java.awt.Dimension(1040, 38));

        textEjemplarNotificacion.setFont(new java.awt.Font("Verdana", 3, 24)); // NOI18N
        textEjemplarNotificacion.setForeground(new java.awt.Color(153, 153, 153));
        textEjemplarNotificacion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        textEjemplarNotificacion.setText("Selecciona un libro para ver sus ejemplares");
        textEjemplarNotificacion.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout panelEjemplarNoSeleccionadoLayout = new javax.swing.GroupLayout(panelEjemplarNoSeleccionado);
        panelEjemplarNoSeleccionado.setLayout(panelEjemplarNoSeleccionadoLayout);
        panelEjemplarNoSeleccionadoLayout.setHorizontalGroup(
            panelEjemplarNoSeleccionadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEjemplarNoSeleccionadoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(textEjemplarNotificacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelEjemplarNoSeleccionadoLayout.setVerticalGroup(
            panelEjemplarNoSeleccionadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEjemplarNoSeleccionadoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(textEjemplarNotificacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jScrollPane3.setBackground(Colores.fondo);
        jScrollPane3.setBorder(null);
        jScrollPane3.setMinimumSize(new java.awt.Dimension(400, 0));
        jScrollPane3.setPreferredSize(new java.awt.Dimension(100, 403));

        panelEjemplarPrestado.setBackground(Colores.fondo);
        panelEjemplarPrestado.setMinimumSize(new java.awt.Dimension(750, 456));
        panelEjemplarPrestado.setPreferredSize(new java.awt.Dimension(750, 456));

        panelEstadoParent15.setBackground(Colores.fondo);
        panelEstadoParent15.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel29.setBackground(Colores.letraNormal);
        jLabel29.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel29.setForeground(Colores.letraNormal);
        jLabel29.setText("Estado");

        panelEstado15.setBackground(Colores.fondo);
        panelEstado15.setLayout(new java.awt.GridLayout(1, 0, 5, 5));

        btnBadStatus.setBackground(Colores.letraNormal);
        btnBadStatus.setForeground(Colores.letraNormal);
        btnBadStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnBadStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/bad_disabled.png"))); // NOI18N
        btnBadStatus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBadStatusMouseClicked(evt);
            }
        });
        panelEstado15.add(btnBadStatus);

        btnRegularStatus.setBackground(Colores.letraNormal);
        btnRegularStatus.setForeground(Colores.letraNormal);
        btnRegularStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnRegularStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/regular_disabled.png"))); // NOI18N
        btnRegularStatus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRegularStatusMouseClicked(evt);
            }
        });
        panelEstado15.add(btnRegularStatus);

        btnGoodStatus.setBackground(Colores.letraNormal);
        btnGoodStatus.setForeground(Colores.letraNormal);
        btnGoodStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnGoodStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/good.png"))); // NOI18N
        btnGoodStatus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnGoodStatusMouseClicked(evt);
            }
        });
        panelEstado15.add(btnGoodStatus);

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

        panelPrestado.setBackground(Colores.fondo);
        panelPrestado.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel30.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel30.setForeground(Colores.letraNormal);
        jLabel30.setText("Prestado a");

        jPanel11.setBackground(Colores.fondo);

        sexoAlumno.setForeground(Colores.letraNormal);
        sexoAlumno.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sexoAlumno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/person-flat.png"))); // NOI18N

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sexoAlumno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sexoAlumno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel12.setBackground(Colores.fondo);

        jPanel13.setBackground(Colores.fondo);

        jLabel32.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel32.setForeground(Colores.letraNormal);
        jLabel32.setText("Telefono:");

        textTelefonoAlumno.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        textTelefonoAlumno.setForeground(Colores.letraNormal);
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

        jPanel14.setBackground(Colores.fondo);

        jLabel33.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel33.setForeground(Colores.letraNormal);
        jLabel33.setText("NIA:");

        textNIAAlumno.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        textNIAAlumno.setForeground(Colores.letraNormal);
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

        jPanel15.setBackground(Colores.fondo);

        jLabel34.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel34.setForeground(Colores.letraNormal);
        jLabel34.setText("Nombre y Apellidos:");

        textNombreAlumno.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        textNombreAlumno.setForeground(Colores.letraNormal);
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

        jPanel16.setBackground(Colores.fondo);

        jLabel35.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel35.setForeground(Colores.letraNormal);
        jLabel35.setText("Correo electronico:");

        textEmailAlumno.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        textEmailAlumno.setForeground(Colores.letraNormal);
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
                        .addGap(0, 0, Short.MAX_VALUE)))
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelNoPrestado.setBackground(Colores.fondo);
        panelNoPrestado.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel36.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel36.setForeground(Colores.letraNormal);
        jLabel36.setText("Prestado a");

        jPanel18.setBackground(Colores.fondo);

        textNoPrestado.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        textNoPrestado.setForeground(Colores.letraNormal);
        textNoPrestado.setText("Este libro no se encuentra prestado por el momento.");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(textNoPrestado)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(textNoPrestado)
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

        panelCodigoEjemplar.setBackground(Colores.fondo);
        panelCodigoEjemplar.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel15.setBackground(Colores.fondo);
        jLabel15.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel15.setForeground(Colores.letraNormal);
        jLabel15.setText("Codigo del libro");

        imgCodigo.setBackground(Colores.fondo);
        imgCodigo.setForeground(Colores.letraNormal);
        imgCodigo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imgCodigo.setToolTipText("");

        btnImprimirEtiquetaEjemplar.setBackground(Colores.botones);
        btnImprimirEtiquetaEjemplar.setForeground(Colores.letraBotones);
        btnImprimirEtiquetaEjemplar.setText("Imprimir etiqueta");
        btnImprimirEtiquetaEjemplar.setCornerRound(10);
        btnImprimirEtiquetaEjemplar.setPreferredSize(new java.awt.Dimension(169, 32));
        btnImprimirEtiquetaEjemplar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirEtiquetaEjemplarActionPerformed(evt);
            }
        });

        textCodigo.setBackground(Colores.fondo);
        textCodigo.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        textCodigo.setForeground(Colores.letraNormal);
        textCodigo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        textTituloLibro.setBackground(Colores.fondo);
        textTituloLibro.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        textTituloLibro.setForeground(Colores.letraNormal);
        textTituloLibro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout panelCodigoEjemplarLayout = new javax.swing.GroupLayout(panelCodigoEjemplar);
        panelCodigoEjemplar.setLayout(panelCodigoEjemplarLayout);
        panelCodigoEjemplarLayout.setHorizontalGroup(
            panelCodigoEjemplarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCodigoEjemplarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelCodigoEjemplarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCodigoEjemplarLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnImprimirEtiquetaEjemplar, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelCodigoEjemplarLayout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelCodigoEjemplarLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(panelCodigoEjemplarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textCodigo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(imgCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textTituloLibro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        panelCodigoEjemplarLayout.setVerticalGroup(
            panelCodigoEjemplarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCodigoEjemplarLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textTituloLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(imgCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnImprimirEtiquetaEjemplar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelEjemplarPrestadoLayout = new javax.swing.GroupLayout(panelEjemplarPrestado);
        panelEjemplarPrestado.setLayout(panelEjemplarPrestadoLayout);
        panelEjemplarPrestadoLayout.setHorizontalGroup(
            panelEjemplarPrestadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEjemplarPrestadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelEjemplarPrestadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelEstadoParent15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelCodigoEjemplar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelEjemplarPrestadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelPrestado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelNoPrestado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelEjemplarPrestadoLayout.setVerticalGroup(
            panelEjemplarPrestadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEjemplarPrestadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelEjemplarPrestadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelEjemplarPrestadoLayout.createSequentialGroup()
                        .addComponent(panelPrestado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelNoPrestado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelEjemplarPrestadoLayout.createSequentialGroup()
                        .addComponent(panelCodigoEjemplar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelEstadoParent15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane3.setViewportView(panelEjemplarPrestado);

        javax.swing.GroupLayout panelGeneralDerechoInferiorLayout = new javax.swing.GroupLayout(panelGeneralDerechoInferior);
        panelGeneralDerechoInferior.setLayout(panelGeneralDerechoInferiorLayout);
        panelGeneralDerechoInferiorLayout.setHorizontalGroup(
            panelGeneralDerechoInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelTituloEjemplar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1407, Short.MAX_VALUE)
            .addGroup(panelGeneralDerechoInferiorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelEjemplarNoSeleccionado, javax.swing.GroupLayout.DEFAULT_SIZE, 1395, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelGeneralDerechoInferiorLayout.setVerticalGroup(
            panelGeneralDerechoInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGeneralDerechoInferiorLayout.createSequentialGroup()
                .addComponent(panelTituloEjemplar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelEjemplarNoSeleccionado, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelInferiorDerechoLayout = new javax.swing.GroupLayout(panelInferiorDerecho);
        panelInferiorDerecho.setLayout(panelInferiorDerechoLayout);
        panelInferiorDerechoLayout.setHorizontalGroup(
            panelInferiorDerechoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelGeneralDerechoInferior, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1407, Short.MAX_VALUE)
        );
        panelInferiorDerechoLayout.setVerticalGroup(
            panelInferiorDerechoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelGeneralDerechoInferior, javax.swing.GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE)
        );

        jSplitPane2.setRightComponent(panelInferiorDerecho);

        javax.swing.GroupLayout panelDerechoLayout = new javax.swing.GroupLayout(panelDerecho);
        panelDerecho.setLayout(panelDerechoLayout);
        panelDerechoLayout.setHorizontalGroup(
            panelDerechoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1407, Short.MAX_VALUE)
        );
        panelDerechoLayout.setVerticalGroup(
            panelDerechoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 927, Short.MAX_VALUE)
        );

        jScrollPane4.setViewportView(panelDerecho);

        jSplitPane1.setRightComponent(jScrollPane4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1820, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        Action confirmacion = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                SwingWorker<?, ?> worker = new SwingWorker<Void, Void>() {
                    protected Void doInBackground() throws InterruptedException {
                        //Cargamos los datos de los libros y ejemplares
                        try {
                            daoLibro.borrar(libro);
                            accionRealizada = true;
                        } catch (Exception e) {
                            new FramePopup(topFrame, "El libro no se ha podido eliminar.",
                                    new ImageIcon(getClass().getResource("/Imagenes/icons/alert-black.png")),
                                    "Aceptar").setVisible(true);
                        }
                        return null;
                    }
                    
                    protected void done() {
                        if (accionRealizada) {
                            vaciarCursosYContenidos();
                            popupConfirmacion.dispose();
                            
                            libro = null;
                            
                            rellenarCamposLibro();
                            
                            cargarDatos();
                            
                            new FramePopup(topFrame, "El libro se ha eliminado correctamente!",
                                    new ImageIcon(getClass().getResource("/Imagenes/icons/alert-black.png")),
                                    "Aceptar").setVisible(true);
                        }
                        frameCarga.dispose();
                    }
                };
                worker.execute();
                frameCarga = new FramePopup(topFrame, "Eliminando libro, espere...");
                frameCarga.setVisible(true);
            }
        };
        
        Action cancelar = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                popupConfirmacion.dispose();
            }
        };
        
        if (libro != null) {
            popupConfirmacion = new FramePopup(this,
                    "Esta seguro que desea eliminar este libro definitivamente?",
                    new ImageIcon(getClass().getResource("/Imagenes/icons/alert-black.png")),
                    "Eliminar",
                    "Cancelar",
                    confirmacion,
                    cancelar);
            popupConfirmacion.setVisible(true);
        } else {
            new FramePopup(this, "Selecciona un libro para poder eliminarlo.",
                    new ImageIcon(getClass().getResource("/Imagenes/icons/alert-black.png")),
                    cancelar).setVisible(true);
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    /**
     * Activamos los campos del Libro para poder editarlo.
     */
    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        setEditMode(true);

        //Deshabilitamos el campo de las unidades ya que solo se pueden añadir 
        //desde la funcionalidad para ello
        textUnidadesTotalesLibro.setEnabled(false);
    }//GEN-LAST:event_btnEditarActionPerformed

    /**
     * Obtenemos el codigo del libro que se ha introducido y se lo pasamos al
     * metodo buscarLibro.
     */
    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        //Buscamos el libro cuando pulsamos el botón
        if (!modoEdicion) {
            buscarLibro(campoBusquedaTemp.toUpperCase());
            campoBusquedaTemp = "";
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    /**
     * Cuando presionamos la tecla intro sobre el campo de busqueda por codigo
     * le pasamos al metodo buscarLibro el codigo introducido por el usuario.
     */
    private void textBusquedaCodigoLibroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textBusquedaCodigoLibroKeyPressed
        // TODO add your handling code here:

        if (!modoEdicion) {
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                String codigo = textBusquedaCodigoLibro.getText();
                buscarLibro(codigo.toUpperCase());
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
        filtroListaLibro(textNombreLibroBusqueda.getText(), cbCursoBuscar.getSelectedIndex());
        
        rellenarCamposLibro();
    }//GEN-LAST:event_cbCursoBuscarItemStateChanged

    private void btnImprimirEtiquetasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirEtiquetasActionPerformed
        
        new FrameEtiquetasPopup(this).setVisible(true);
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
            showEjemplarPanel(libro != null);
            libro = (Libro) jlistLibros.getSelectedValue();
            
            rellenarCamposLibro();
        }
    }//GEN-LAST:event_jlistLibrosValueChanged

    /**
     * Metodo para simular el PlaceHolder del JTextField buscar por codigo
     *
     * @param evt
     */
    private void textBusquedaCodigoLibroFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textBusquedaCodigoLibroFocusGained
        if (textBusquedaCodigoLibro.getText().equals(StringsGlobales.placeHolder_codigo)) {
            textBusquedaCodigoLibro.setText("");
            textBusquedaCodigoLibro.setForeground(Colores.letraNormal);
            campoBusquedaTemp = "";
        }
    }//GEN-LAST:event_textBusquedaCodigoLibroFocusGained

    /**
     * Metodo para simular el PlaceHolder del JTextField buscar por codigo
     *
     * @param evt
     */
    private void textBusquedaCodigoLibroFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textBusquedaCodigoLibroFocusLost
        // TODO add your handling code here:
        textBusquedaCodigoLibro.setForeground(Colores.campoTextSinFocus);
        textBusquedaCodigoLibro.setText(StringsGlobales.placeHolder_codigo);
    }//GEN-LAST:event_textBusquedaCodigoLibroFocusLost

    private void btnImprimirEtiquetaEjemplarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirEtiquetaEjemplarActionPerformed
        // TODO add your handling code here:
        Ejemplar ejemplarActual = listaEjemplares.get(contadorEjemplar - 1);
        
        new FrameEtiquetasPopup(topFrame, ejemplarActual).setVisible(true);
    }//GEN-LAST:event_btnImprimirEtiquetaEjemplarActionPerformed

    private void btnAnteriorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAnteriorMouseClicked
        if (contadorEjemplar < 2) {
            contadorEjemplar = listaEjemplares.size();
        } else {
            contadorEjemplar--;
        }
        rellenarCamposEjemplares();
    }//GEN-LAST:event_btnAnteriorMouseClicked

    private void btnSiguienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSiguienteMouseClicked
        // TODO add your handling code here:
        if (contadorEjemplar == listaEjemplares.size()) {
            contadorEjemplar = 1;
        } else {
            contadorEjemplar++;
        }
        rellenarCamposEjemplares();
    }//GEN-LAST:event_btnSiguienteMouseClicked

    /**
     * Metodo para cancelar la acción actual, deshabilitamos el modo edición y
     * vaciamos los campos
     */
    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        
        try {
            boolAnimacion = false;
            if (animacion != null) {
                animacion.join();
            }
            showEjemplarPanel(false);
            
        } catch (InterruptedException ex) {
            Logger.getLogger(FrameLibro.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        
        setEditMode(false);
        
        vaciarCursosYContenidos();
        
        rellenarCamposLibro();
    }//GEN-LAST:event_btnCancelarActionPerformed

    /**
     * Metodo para guardar la acción actual, si el libro actual es null llamamos
     * al metodo grabar() del DaoLibro, en caso contrario llamamos al metodo
     * actualizar() del DaoLibro.
     */
    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        try {
            //Matar thread
            boolAnimacion = false;
            
            if (animacion != null) {
                animacion.join();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(FrameLibro.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        
        String errores = "";
        
        if (textCodigoLibro.getText().equals("")) {
            errores += "- El código del libro no puede estar vacío.\n";
        } else if (textCodigoLibro.getText().length() > 13) {
            errores += "- El código del libro no puede contener más de 13 carácteres.\n";
        }
        
        if (textNombreLibro.getText().equals("")) {
            errores += "- El nombre no puede estar vacío.\n";
        }
        
        if (textISBNLibro.getText().equals("")) {
            errores += "- El ISBN no puede estar vacío.\n";
        }
        
        if (textUnidadesTotalesLibro.getText().equals("")) {
            errores += "- El campo de las unidades no puede estar vacío.\n";
        } else if (textUnidadesTotalesLibro.getText().length() > 3) {
            errores += "- El campo de las unidades no puede contener más de 3 carácteres.\n";
        }
        
        try {
            int un = Integer.parseInt(textUnidadesTotalesLibro.getText());
            if (un <= 0) {
                errores += "- El valor de las unidades debe ser un valor positivo.\n";
            }
        } catch (Exception e) {
            errores += "- El valor de las unidades debe ser un valor numérico.\n";
        }
        
        try {
            double pre = Double.parseDouble(textPrecioLibro.getText());
            if (pre <= 0) {
                errores += "- El precio debe ser un valor positivo.\n";
            }
        } catch (Exception e) {
            errores += "- El precio debe ser un valor numérico.\n";
        }
        
        if (cbAsignatura.getSelectedItem() == null) {
            errores += "- Debe seleccionar una asignatura válida.\n";
        } else if (cbAsignatura.getSelectedItem().toString().equals("Seleccione curso")) {
            errores += "- Debe seleccionar una asignatura válida.\n";
        }
        
        if (libro == null) {
            //Creacion de un nuevo libro

            Libro libroDuplicado = null;
            
            try {
                libroDuplicado = daoLibro.buscar(textCodigoLibro.getText().toUpperCase());
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error al comprobar libro duplicado.");
            }
            
            if (libroDuplicado != null) {
                errores += "- Ya existe un libro con este código en la base de datos.\n";
            }
            
            if (errores.equals("")) {
                //Creamos el libro si el string de los errores esta vacío, es decir, si no hay errores

                libro = new Libro();
                
                libro.setCodigo(textCodigoLibro.getText().toUpperCase());
                libro.setISBN(textISBNLibro.getText());
                libro.setNombre(textNombreLibro.getText());
                libro.setObsoleto(chkObsoletoLibro.isSelected());
                libro.setPrecio(Double.parseDouble(textPrecioLibro.getText()));
                libro.setUnidades(Integer.parseInt(textUnidadesTotalesLibro.getText()));
                libro.setContenido((Contenido) cbAsignatura.getSelectedItem());

                //<editor-fold defaultstate="collapsed" desc="Guardar libro">
                SwingWorker<?, ?> worker = new SwingWorker<Void, Void>() {
                    protected Void doInBackground() throws InterruptedException {
                        daoLibro.grabar(libro);
                        return null;
                    }
                    
                    protected void done() {
                        
                        try {
                            cargarDatos();
                            
                            setEditMode(false);
                            
                            rellenarCamposLibro();
                            
                            showEjemplarPanel(true);
                            contadorEjemplar = 1;
                            rellenarCamposEjemplares();
                            
                            new FramePopup(topFrame, "Libro añadido correctamente.",
                                    Imagenes.getImagen("check-black.png"),
                                    "Aceptar").setVisible(true);
                        } catch (PersistenceException e) {
                            new FramePopup(topFrame, "El libro ya existe en la Base de Datos.",
                                    Imagenes.getImagen("alert-black.png"),
                                    "Aceptar").setVisible(true);
                        } catch (Exception e) {
                            e.printStackTrace();
                            new FramePopup(topFrame, "Error al crear el libro.",
                                    Imagenes.getImagen("alert-black.png"),
                                    "Aceptar").setVisible(true);
                        }
                        
                        frameCarga.dispose();
                    }
                };
                worker.execute();
                frameCarga.setVisible(true);
                //</editor-fold>
            } else {
                new FramePopup(this, "Revise los siguientes errores: \n"
                        + errores,
                        Imagenes.getImagen("alert-black.png"),
                        "Aceptar").setVisible(true);
            }
        } else {
            //Modificacion de un libro existente

            if (errores.equals("")) {
                //Creamos el libro si el string de los errores esta vacío, es decir, si no hay errores
                libro.setCodigo(textCodigoLibro.getText());
                libro.setISBN(textISBNLibro.getText());
                libro.setNombre(textNombreLibro.getText());
                libro.setObsoleto(chkObsoletoLibro.isSelected());
                libro.setPrecio(Double.parseDouble(textPrecioLibro.getText()));
                libro.setContenido((Contenido) cbAsignatura.getSelectedItem());
                
                SwingWorker<?, ?> worker = new SwingWorker<Void, Void>() {
                    protected Void doInBackground() throws InterruptedException {
                        try {
                            daoLibro.actualizar(libro);
                        } catch (PersistenceException e) {
                            new FramePopup(topFrame, "Error al actualizar el libro",
                                    Imagenes.getImagen("alert-black.png"),
                                    "Aceptar").setVisible(true);
                            e.printStackTrace();
                        }
                        return null;
                    }
                    
                    protected void done() {
                        try {
                            cargarDatos();
                            
                            setEditMode(false);
                            
                            rellenarCamposLibro();
                            
                            contadorEjemplar = 1;
                            rellenarCamposEjemplares();
                            
                            showEjemplarPanel(true);
                            
                            new FramePopup(topFrame, "Libro actualizado correctamente.",
                                    Imagenes.getImagen("check-black.png"),
                                    "Aceptar").setVisible(true);
                        } catch (Exception e) {
                            new FramePopup(topFrame, "Error al actualizar el libro: " + e.getMessage(),
                                    Imagenes.getImagen("alert-black.png"),
                                    "Aceptar").setVisible(true);
                        }
                        
                        frameCarga.dispose();
                    }
                };
                worker.execute();
                if (frameCarga == null) {
                    frameCarga = new FramePopup(this);
                }
                frameCarga.setVisible(true);
                
            } else {
                new FramePopup(this, "Revise los siguientes errores: \n"
                        + errores,
                        Imagenes.getImagen("alert-black.png"),
                        "Aceptar").setVisible(true);
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void chkObsoletoLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkObsoletoLibroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkObsoletoLibroActionPerformed

    private void textPrecioLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textPrecioLibroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textPrecioLibroActionPerformed

    private void cbCursoLibroItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbCursoLibroItemStateChanged
        // TODO add your handling code here:
        if (modoEdicion) {
            rellenarContenidosLibro();
        }
    }//GEN-LAST:event_cbCursoLibroItemStateChanged

    /**
     * Metodo para crear un nuevo libro, habilitamos el modo edición y vaciamos
     * los campos
     */
    private void btnNewLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewLibroActionPerformed
        if (!modoEdicion) {
            
            jlistLibros.clearSelection();
            
            libro = null;
            
            showEjemplarPanel(libro != null);
            
            boolAnimacion = true;
            animacion = new Thread(new Runnable() {
                
                int intFrase = 0;
                
                @Override
                public void run() {
                    String[] not = {
                        "Creando nuevo libro   ",
                        "Creando nuevo libro.  ",
                        "Creando nuevo libro.. ",
                        "Creando nuevo libro...",
                        "Creando nuevo libro.. ",
                        "Creando nuevo libro.  "};
                    
                    while (boolAnimacion) {
                        intFrase++;
                        
                        if (intFrase == not.length * 2) {
                            intFrase = 0;
                        }
                        
                        textEjemplarNotificacion.setText(not[intFrase % not.length]);
                        
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(FrameLibro.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });
            animacion.start();
            
            rellenarCamposLibro();
            
            rellenaCursosLibro();
            
            setEditMode(true);
        }
    }//GEN-LAST:event_btnNewLibroActionPerformed

    /**
     * Mostramos el menú desplegable de opciones del libro.
     */
    private void btnOpcionesMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOpcionesMouseReleased
        //Mostramos el menu de opciones en el libro
        if (libro != null) {
            menuOpcionesLibro.show(evt.getComponent(), -163, 60);
        } else {
            new FramePopup(this, "Selecciona un libro para poder gestionarlo.",
                    Imagenes.getImagen("alert-black.png"),
                    "Aceptar").setVisible(true);
        }
    }//GEN-LAST:event_btnOpcionesMouseReleased

    private void btnOcultarBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOcultarBuscarActionPerformed
        showPanelBusqueda(false);
    }//GEN-LAST:event_btnOcultarBuscarActionPerformed

    private void btnMostrarBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarBuscarActionPerformed
        showPanelBusqueda(true);
    }//GEN-LAST:event_btnMostrarBuscarActionPerformed

    private void textBusquedaCodigoLibroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textBusquedaCodigoLibroKeyReleased
        // TODO add your handling code here:
        campoBusquedaTemp = textBusquedaCodigoLibro.getText();
    }//GEN-LAST:event_textBusquedaCodigoLibroKeyReleased

    private void textNombreLibroBusquedaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textNombreLibroBusquedaFocusGained
        // TODO add your handling code here:
        textNombreLibroBusqueda.setText("");
        textNombreLibroBusqueda.setForeground(Colores.letraNormal);
    }//GEN-LAST:event_textNombreLibroBusquedaFocusGained

    private void textNombreLibroBusquedaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textNombreLibroBusquedaFocusLost
        // TODO add your handling code here:
        textNombreLibroBusqueda.setText(StringsGlobales.placeHolder_nombre);
        textNombreLibroBusqueda.setForeground(Colores.campoTextSinFocus);
    }//GEN-LAST:event_textNombreLibroBusquedaFocusLost

    private void textNombreLibroBusquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textNombreLibroBusquedaKeyReleased
        filtroListaLibro(textNombreLibroBusqueda.getText(), cbCursoBuscar.getSelectedIndex());
    }//GEN-LAST:event_textNombreLibroBusquedaKeyReleased

    private void btnAnadirEjemplaresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnadirEjemplaresActionPerformed
        new FrameInputPopup(this).setVisible(true);
    }//GEN-LAST:event_btnAnadirEjemplaresActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        if (libro != null) {
            textUnidadesTotalesLibro.setText(libro.getUnidades() + "");
        }
    }//GEN-LAST:event_formWindowActivated

    private void btnBadStatusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBadStatusMouseClicked
        // TODO add your handling code here:
        modificarEjemplar(Estado.deteriorado, contadorEjemplar - 1);
        
        setEstado(Estado.deteriorado);
    }//GEN-LAST:event_btnBadStatusMouseClicked

    private void btnRegularStatusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegularStatusMouseClicked
        // TODO add your handling code here:
        modificarEjemplar(Estado.usado, contadorEjemplar - 1);
        
        setEstado(Estado.usado);
    }//GEN-LAST:event_btnRegularStatusMouseClicked

    private void btnGoodStatusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGoodStatusMouseClicked
        // TODO add your handling code here:
        modificarEjemplar(Estado.nuevo, contadorEjemplar - 1);
        
        setEstado(Estado.nuevo);
    }//GEN-LAST:event_btnGoodStatusMouseClicked

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
    private javax.swing.JMenuItem btnAnadirEjemplares;
    private com.mommoo.flat.button.FlatButton btnAnterior;
    private javax.swing.JLabel btnBadStatus;
    private com.mommoo.flat.button.FlatButton btnBuscar;
    private com.mommoo.flat.button.FlatButton btnCancelar;
    private javax.swing.JMenuItem btnEditar;
    private javax.swing.JMenuItem btnEliminar;
    private javax.swing.JLabel btnGoodStatus;
    private com.mommoo.flat.button.FlatButton btnGuardar;
    private com.mommoo.flat.button.FlatButton btnImprimirEtiquetaEjemplar;
    private javax.swing.JMenuItem btnImprimirEtiquetas;
    private com.mommoo.flat.button.FlatButton btnMostrarBuscar;
    private com.mommoo.flat.button.FlatButton btnNewLibro;
    private com.mommoo.flat.button.FlatButton btnOcultarBuscar;
    private com.mommoo.flat.button.FlatButton btnOpciones;
    private javax.swing.JLabel btnRegularStatus;
    private com.mommoo.flat.button.FlatButton btnSiguiente;
    private javax.swing.JComboBox cbAsignatura;
    private javax.swing.JComboBox cbCursoBuscar;
    private javax.swing.JComboBox cbCursoLibro;
    private javax.swing.JCheckBox chkObsoletoLibro;
    private javax.swing.JLabel imgCodigo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JList jlistLibros;
    private javax.swing.JLabel labelInfoEdicion;
    private javax.swing.JPopupMenu menuOpcionesLibro;
    private javax.swing.JPanel panelAsignatura;
    private javax.swing.JPanel panelBotoneraEdicion;
    private javax.swing.JPanel panelBusqueda;
    private javax.swing.JPanel panelCodigoEjemplar;
    private javax.swing.JPanel panelCodigoLibro;
    private javax.swing.JPanel panelCuerpo;
    private javax.swing.JPanel panelCurso;
    private javax.swing.JPanel panelDerecho;
    private javax.swing.JPanel panelEjemplarNoSeleccionado;
    private javax.swing.JPanel panelEjemplarPrestado;
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
    private javax.swing.JPanel panelTitulo1;
    private javax.swing.JPanel panelTituloEjemplar;
    private javax.swing.JPanel panelTituloLibro;
    private javax.swing.JLabel sexoAlumno;
    private javax.swing.JTextField textBusquedaCodigoLibro;
    private javax.swing.JLabel textCodigo;
    private javax.swing.JTextField textCodigoLibro;
    private javax.swing.JLabel textEjemplarNotificacion;
    private javax.swing.JLabel textEjemplaresDisponibles;
    private javax.swing.JLabel textEmailAlumno;
    private javax.swing.JTextField textISBNLibro;
    private javax.swing.JLabel textNIAAlumno;
    private javax.swing.JLabel textNoPrestado;
    private javax.swing.JLabel textNombreAlumno;
    private javax.swing.JTextField textNombreLibro;
    private javax.swing.JTextField textNombreLibroBusqueda;
    private javax.swing.JTextField textPrecioLibro;
    private javax.swing.JLabel textTelefonoAlumno;
    private javax.swing.JLabel textTitulo;
    private javax.swing.JLabel textTitulo1;
    private javax.swing.JLabel textTituloLibro;
    private javax.swing.JLabel textTotalEjemplares;
    private javax.swing.JTextField textUnidadesTotalesLibro;
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
                
                sustituirPadresCursos();
                
                System.out.println("Libros cargados: " + listaLibros.size() + " Cursos cargados: " + listaCursos.size());
                return null;
            }
            
            protected void done() {
                //Aplicamos el filtro
                if (libro == null);

                //sustituirPadresCursos();
                if (primeraEjecucion) {
                    rellenaCursosBusqueda();
                    
                    primeraEjecucion = false;
                }
                
                filtroListaLibro(textNombreLibroBusqueda.getText(), cbCursoBuscar.getSelectedIndex());
                
                showEjemplarPanel(libro != null);
                
                frameCarga.dispose();
            }
        };
        worker.execute();
        if (frameCarga == null) {
            frameCarga = new FramePopup(this);
        }
        frameCarga.setVisible(true);
    }

    /**
     * Metodo para filtrar la lista de Libros (Panel Izquierdo)
     */
    private void filtroListaLibro(String textoNombre, int indexCurso) {
        //Creamos una lista temporal de los libros para realizar la busqueda
        List<Libro> listaFiltroLibros = listaLibros;

        //Creamos las variables del filtro
        String n, c, resFiltro = "";

        //Clasificamos el filtro
        //<editor-fold defaultstate="collapsed" desc="Clasificacion del filtro">
        if (textNombreLibroBusqueda.getText().equals(StringsGlobales.placeHolder_nombre)) {
            n = "0";
        } else {
            n = "1";
        }
        
        if (indexCurso != 0) {
            cursoSeleccionado = listaCursos.get(indexCurso - 1);
        }
        
        if (indexCurso == 0) {
            c = "0";
        } else {
            c = "1";
        }
        
        resFiltro = n + c;
        
        System.out.println("ResFiltro: " + resFiltro);
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
                    listaFiltroLibros = listaLibros.stream().filter(libro -> (libro.getContenido().getCurso().getId().equals(cursoSeleccionado.getId()))).collect(Collectors.toList());
                    break;
                
                case "10":
                    //Se seleccionan los libros del nombre escrito
                    listaFiltroLibros = listaLibros.stream().filter(libro -> libro.getNombre().toUpperCase().contains(textoNombre.toUpperCase())).collect(Collectors.toList());
                    break;
                
                case "11":
                    //se seleccionan los libros del curso seleccionado con el nombre escrito
                    listaFiltroLibros = listaLibros.stream().filter(libro -> libro.getNombre().toUpperCase().contains(textoNombre.toUpperCase())).collect(Collectors.toList());
                    listaFiltroLibros = listaFiltroLibros.stream().filter(libro -> (libro.getContenido().getCurso().getId().equals(cursoSeleccionado.getId()))).collect(Collectors.toList());
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
                    
                    if (libro == null) {
                        new FramePopup(topFrame, "No existe ningún libro con este código.",
                                new ImageIcon(getClass().getResource("/Imagenes/icons/alert-black.png")),
                                "Aceptar").setVisible(true);
                    } else {
                        seleccionaLibroEnLista(libro);
                        rellenarCamposLibro();
                    }
                    
                }
            };
            worker.execute();
            if (frameCarga == null) {
                frameCarga = new FramePopup(this);
            }
            frameCarga.setVisible(true);
        } else {
            new FramePopup(this, "El código no puede ser un campo vacío.",
                    Imagenes.getImagen("alert-black.png"),
                    "Aceptar").setVisible(true);
        }
    }
    
    private void seleccionaLibroEnLista(Libro libro) {
        for (int i = 0; i < jlistLibros.getModel().getSize(); i++) {
            Libro l = (Libro) jlistLibros.getModel().getElementAt(i);
            
            if (l.getCodigo().equals(libro.getCodigo())) {
                jlistLibros.setSelectedIndex(i);
            }
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
            textUnidadesTotalesLibro.setText("");
            textPrecioLibro.setText("");
            chkObsoletoLibro.setSelected(false);
            
            cbCursoLibro.removeAllItems();
            cbAsignatura.removeAllItems();
            
            showEjemplarPanel(false);
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
            
            cbAsignatura.addItem(libro.getContenido());
            textUnidadesTotalesLibro.setText(libro.getUnidades() + "");
            textPrecioLibro.setText(libro.getPrecio() + "");
            chkObsoletoLibro.setSelected(libro.getObsoleto());
            
            contadorEjemplar = 1;
            
            rellenarCamposEjemplares();
            
            showEjemplarPanel(true);
        }
    }
    
    private void rellenarCamposEjemplares() {
        listaEjemplares = libro.getEjemplares();
        mostrarElementosEjemplares(true);
        
        textTotalEjemplares.setText(contadorEjemplar + " de " + listaEjemplares.size()
        );
        
        textEjemplaresDisponibles.setText("(Disponibles: " + libro.getEjemplaresDisponibles().size() + ")");

        //Panel del codigo
        Ejemplar ejemplarActual = listaEjemplares.get(contadorEjemplar - 1);
        textCodigo.setText(ejemplarActual.getCodigo());
        textTituloLibro.setText(ejemplarActual.getLibro().getNombre());
        
        try {
            CodigoBarras cb = new CodigoBarras();
            ImageIcon icon = cb.getImage(cb.generarCodigoIndividual(ejemplarActual.getCodigo()), 320, 120);
            imgCodigo.setIcon(icon);
        } catch (Exception e) {
            new FramePopup(this, "Error al generar el codigo de barras de los ejemplares",
                    new ImageIcon(getClass().getResource("/Imagenes/icons/alert-black.png")),
                    "Aceptar").setVisible(true);
        }

        //panel del alumno
        if (panelPrestado.isVisible()) {
            
            SwingWorker<?, ?> worker = new SwingWorker<Void, Void>() {
                
                Historial historialEjemplar = null;
                
                protected Void doInBackground() throws InterruptedException {
                    historialEjemplar = daoHistorial.buscarEjemplarPrestadoA(ejemplarActual);
                    return null;
                }
                
                protected void done() {
                    if (ejemplarActual.isPrestado() == true && historialEjemplar != null) {
                        Alumno alumnoActual = historialEjemplar.getAlumno();
                        
                        try {
                            if (alumnoActual.getSexo().equals("H")) {
                                sexoAlumno.setIcon(Imagenes.getImageFromImagenes("person-flat.png"));
                            } else {
                                sexoAlumno.setIcon(Imagenes.getImageFromImagenes("person-girl-flat.png"));
                            }
                        } catch (Exception e) {
                            System.out.println("Error al encontrar la imagen del alumno.");
                            e.printStackTrace();
                        }
                        
                        textNombreAlumno.setText(alumnoActual.getNombre() + " "
                                + alumnoActual.getApellido1() + " "
                                + alumnoActual.getApellido2());
                        
                        textNIAAlumno.setText(alumnoActual.getNia());
                        textTelefonoAlumno.setText(alumnoActual.getTelefono1());
                        textEmailAlumno.setText(alumnoActual.getEmail1());
                        System.out.println("Ejemplar prestado a: " + historialEjemplar.getAlumno().toString());
                    } else {
                        System.out.println("Ejemplar no prestado");
                    }
                    frameCarga.dispose();
                }
            };
            worker.execute();
            frameCarga = new FramePopup(this);
            frameCarga.setVisible(true);
        }

        //Panel del estado
        setEstado(ejemplarActual.getEstado());
    }

    /**
     * Metodo para rellenar el ComboBox de los Cursos en pestaña Busqueda
     */
    private void rellenaCursosBusqueda() {
        cbCursoBuscar.removeAllItems();
        
        cbCursoBuscar.addItem(new Curso("Todos", "Todos", "Todos", "Todos", "Todos", " "));
        
        if (listaCursos.size() > 0) {
            for (int i = 0; i < listaCursos.size(); i++) {
                cbCursoBuscar.addItem(listaCursos.get(i));
            }
        } else {
            new FramePopup(this, "No hay cursos en la base de datos.",
                    Imagenes.getImagen("alert-black.png"),
                    "Aceptar").setVisible(true);
        }
    }

    /**
     * Metodo para buscar el Padre de cada Curso y sustituir el atributo idPadre
     * por el nombre del Padre
     */
    private void sustituirPadresCursos() {
        for (int i = 0; i < listaCursos.size(); i++) {
            Curso curso = listaCursos.get(i);
            Curso cursoPadre = daoCurso.buscar(curso.getIdPadre());
            
            if (cursoPadre != null) {
                curso.setNombre_padre(daoCurso.buscar(curso.getIdPadre()).getNombre_cas());
            }
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
            
            rellenarContenidosLibro();
        } else {
            new FramePopup(this, "No hay cursos en la base de datos.",
                    Imagenes.getImagen("alert-black.png"),
                    "Aceptar").setVisible(true);
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
        textUnidadesTotalesLibro.setEnabled(editable);
        chkObsoletoLibro.setEnabled(editable);
        //btnImprimirEtiquetas.setEnabled(editable);
        textPrecioLibro.setEnabled(editable);
        btnAnadirEjemplares.setVisible(editable);
        
        labelInfoEdicion.setVisible(editable);
        panelBotoneraEdicion.setVisible(editable);
        
        if (libro == null) {
            textCodigoLibro.setEnabled(editable);
        } else {
            textCodigoLibro.setEnabled(false);
        }
    }
    
    private void mostrarElementosEjemplares(boolean b) {
        btnSiguiente.setVisible(b);
        btnAnterior.setVisible(b);
        textTotalEjemplares.setVisible(b);
        btnImprimirEtiquetaEjemplar.setVisible(b);
        textEjemplaresDisponibles.setVisible(b);
        setVisiblePanelAlumno(b);
        
        if (libro == null) {
            textNoPrestado.setText("Selecciona un libro para ver sus ejemplares.");
            setEstado(-1);
        } else if (listaEjemplares != null) {
            setVisiblePanelAlumno(listaEjemplares.get(contadorEjemplar - 1).isPrestado());
            if (!listaEjemplares.get(contadorEjemplar - 1).isPrestado()) {
                textNoPrestado.setText("Este libro no se encuentra prestado por el momento.");
            }
        }
    }
    
    public void setEstado(int estado) {
        
        switch (estado) {
            case Estado.deteriorado:
                //El libro se encuentra en mal estado

                btnBadStatus.setIcon(new ImageIcon(
                        getClass().getResource("/Imagenes/bad.png")));               //Bad face
                btnRegularStatus.setIcon(new ImageIcon(
                        getClass().getResource("/Imagenes/regular_disabled.png")));  //Regular face
                btnGoodStatus.setIcon(new ImageIcon(
                        getClass().getResource("/Imagenes/good_disabled.png")));     //Good face
                break;
            
            case Estado.usado:
                //El libro se encuentra en mal estado

                btnBadStatus.setIcon(new ImageIcon(
                        getClass().getResource("/Imagenes/bad_disabled.png")));     //Bad face
                btnRegularStatus.setIcon(new ImageIcon(
                        getClass().getResource("/Imagenes/regular.png")));          //Regular face
                btnGoodStatus.setIcon(new ImageIcon(
                        getClass().getResource("/Imagenes/good_disabled.png")));    //Good face
                break;
            
            case Estado.nuevo:
                //El libro se encuentra en mal estado

                btnBadStatus.setIcon(new ImageIcon(
                        getClass().getResource("/Imagenes/bad_disabled.png")));     //Bad face
                btnRegularStatus.setIcon(new ImageIcon(
                        getClass().getResource("/Imagenes/regular_disabled.png"))); //Regular face
                btnGoodStatus.setIcon(new ImageIcon(
                        getClass().getResource("/Imagenes/good.png")));             //Good face
                break;
            default:
                btnBadStatus.setIcon(new ImageIcon(
                        getClass().getResource("/Imagenes/bad_disabled.png")));     //Bad face
                btnRegularStatus.setIcon(new ImageIcon(
                        getClass().getResource("/Imagenes/regular_disabled.png"))); //Regular face
                btnGoodStatus.setIcon(new ImageIcon(
                        getClass().getResource("/Imagenes/good_disabled.png")));             //Good face
        }
    }
    
    private void setVisiblePanelAlumno(boolean visible) {
        panelPrestado.setVisible(visible);
        panelNoPrestado.setVisible(!visible);
    }
    
    private void showEjemplarPanel(boolean b) {
        textTotalEjemplares.setVisible(b);
        btnSiguiente.setVisible(b);
        btnAnterior.setVisible(b);
        panelEjemplarNoSeleccionado.setVisible(!b);
        jScrollPane3.setVisible(b);
        
        if (!b) {
            textEjemplarNotificacion.setText("Selecciona un libro para ver sus ejemplares");
        }
    }
    
    private void showPanelBusqueda(boolean show) {
        panelIzquierdo.setVisible(show);
        jSplitPane1.setLeftComponent(panelIzquierdo);
        btnMostrarBuscar.setVisible(!show);
        btnOcultarBuscar.setVisible(show);
    }
    
    public static void actualizarEjemplaresLibro(int nEjemplares) {
        libro.setUnidades(libro.getUnidades() + nEjemplares);
    }
    
    private void modificarEjemplar(int estado, int nEjemplar) {
        if (libro != null) {
            List<Ejemplar> ejemplares = libro.getEjemplares();
            
            Ejemplar e = ejemplares.get(nEjemplar);
            
            e.setEstado(estado);
            
            libro.setEjemplares(ejemplares);
            
            daoLibro.actualizar(libro);
        }
    }
}
