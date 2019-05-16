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
import Daos.DaoCurso;
import Daos.DaoEjemplar;
import Daos.DaoHistorial;
import Daos.DaoMatricula;
import Pojos.Alumno;
import Pojos.Curso;
import Pojos.Ejemplar;
import Pojos.Historial;
import Pojos.Matricula;
import Renders.RemarcarCeldas;
import Renders.comboBoxRender;
import Utilidades.Colores;
import Utilidades.Estado;
import Utilidades.Imagenes.Imagenes;
import Utilidades.StringsGlobales;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import org.hibernate.Session;

/**
 *
 * @author Jose Sanchis
 */
public class FrameEntrega extends javax.swing.JFrame {

    private Session session = Main.gestorSesiones.getSession();

    //Cremaos el frame de Cargar
    private FramePopup framePopup;

    //Creamos el DAO del Alumno y Matricula
    private DaoAlumno daoAlumno;
    private DaoMatricula daoMatricula;
    private DaoCurso daoCurso;
    private DaoCurso daoCursoTabla;
    private DaoEjemplar daoEjemplar;
    private DaoHistorial daoHistorial;

    public Alumno alumno;
    public Ejemplar ejemplar;

    //Matricula temporal
    public Matricula matriculaEntregada = null;
    public List<Ejemplar> listaEjemplaresEntregados = null;

    //Listas
    public List<Alumno> listaAlumnos;
    public List<Matricula> listaMatriculas;
    public List<Curso> listaCursos;

    private String campoBusquedaTemp = "";
    private String codigoTemp = "";

    //Cogemos el frame padre para trabajar con los dialogos
    private JFrame topFrame;

    /**
     * Creates new form FrameDevoluciones
     */
    public FrameEntrega() {
        initComponents();

        //<editor-fold defaultstate="collapsed" desc="Configuracion colores tabla">
        jScrollPane2.getViewport().setBackground(Colores.fondo);
        JTableHeader anHeader = tablaPendientes.getTableHeader();
        anHeader.setForeground(Colores.letraNormal);
        anHeader.setBackground(Colores.fondo);
//</editor-fold>

        topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);

        //<editor-fold defaultstate="collapsed" desc="Configuración combobox">
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
                    l.setForeground(Colores.letraBotones);
                    l.setBackground(Colores.accento);
                } else {
                    l.setForeground(Colores.letraNormal);
                    l.setBackground(Colores.fondo);
                }
                return l;
            }
        });

        cbFecha.setEditable(false);
        cbFecha.setUI(new comboBoxRender());
        cbFecha.setRenderer(new DefaultListCellRenderer() {
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

        //Inicializamos los DAO
        daoAlumno = new DaoAlumno(session);
        daoMatricula = new DaoMatricula(session);
        daoCurso = new DaoCurso(session);
        daoEjemplar = new DaoEjemplar(session);
        daoHistorial = new DaoHistorial(session);
        daoCursoTabla = new DaoCurso(session);

        //Deshabilitamos la edicion de las celdas en las tablas
        tablaPendientes.setDefaultEditor(Object.class, null);
        tablaPendientes.getTableHeader().setReorderingAllowed(false);

        //Inicializamos la lista de las matriculas entregadas
        listaEjemplaresEntregados = new ArrayList<>();

        //Centramos la pestaña al centro de la pantalla
        this.setLocationRelativeTo(null);

        //Maximizamos la pestaña
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        modoEdicion(false);

        cargarDatos();

        rellenarComboBoxFecha();

        //<editor-fold defaultstate="collapsed" desc="Click Tabla">
        tablaPendientes.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = tablaPendientes.rowAtPoint(evt.getPoint());

                rellenarEjemplarSugerido(listaMatriculas.get(row));
            }
        });
//</editor-fold>
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
        panelCuerpo = new javax.swing.JPanel();
        jSplitPane1 = new javax.swing.JSplitPane();
        panelLista = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        panelBusquedaNIA = new javax.swing.JPanel();
        textBusquedaNIA = new javax.swing.JTextField();
        btnBusquedaNIA = new com.mommoo.flat.button.FlatButton();
        panelBusquedaLista = new javax.swing.JPanel();
        cbCurso = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jlistAlumnos = new javax.swing.JList();
        jLabel8 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        panelInformacion2 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        panelInfoGeneral2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        textNIAAlumno = new javax.swing.JLabel();
        textNombreAlumno = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        imgSexo = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cbFecha = new javax.swing.JComboBox();
        panelGestionAsignaturas = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaPendientes = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        panelEjemplaresEntregados = new javax.swing.JPanel();
        panelCodigo = new javax.swing.JPanel();
        textCodigoEjemplar = new javax.swing.JTextField();
        btnCodigoEjemplar = new com.mommoo.flat.button.FlatButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jlistEjemplares = new javax.swing.JList();
        jLabel10 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Entregas");
        setMinimumSize(new java.awt.Dimension(1024, 768));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        panelTitulo.setBackground(Colores.fondoOscuro);

        jLabel1.setBackground(Colores.accento);
        jLabel1.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel1.setForeground(Colores.letraTitulo);
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Entregas");

        javax.swing.GroupLayout panelTituloLayout = new javax.swing.GroupLayout(panelTitulo);
        panelTitulo.setLayout(panelTituloLayout);
        panelTituloLayout.setHorizontalGroup(
            panelTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelTituloLayout.setVerticalGroup(
            panelTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
        );

        panelCuerpo.setBackground(Colores.fondo);

        panelLista.setBackground(Colores.fondo);
        panelLista.setMaximumSize(new java.awt.Dimension(450, 32767));
        panelLista.setMinimumSize(new java.awt.Dimension(400, 0));

        jPanel5.setBackground(Colores.fondo);
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        panelBusquedaNIA.setBackground(Colores.fondo);

        textBusquedaNIA.setBackground(Colores.fondo);
        textBusquedaNIA.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        textBusquedaNIA.setForeground(Colores.letraNormal);
        textBusquedaNIA.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        textBusquedaNIA.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textBusquedaNIAFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                textBusquedaNIAFocusLost(evt);
            }
        });
        textBusquedaNIA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textBusquedaNIAKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textBusquedaNIAKeyReleased(evt);
            }
        });

        btnBusquedaNIA.setBackground(Colores.botones);
        btnBusquedaNIA.setForeground(Colores.letraBotones);
        btnBusquedaNIA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons/magnify.png"))); // NOI18N
        btnBusquedaNIA.setCornerRound(10);
        btnBusquedaNIA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBusquedaNIAActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBusquedaNIALayout = new javax.swing.GroupLayout(panelBusquedaNIA);
        panelBusquedaNIA.setLayout(panelBusquedaNIALayout);
        panelBusquedaNIALayout.setHorizontalGroup(
            panelBusquedaNIALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBusquedaNIALayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(textBusquedaNIA)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBusquedaNIA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelBusquedaNIALayout.setVerticalGroup(
            panelBusquedaNIALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBusquedaNIALayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBusquedaNIALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnBusquedaNIA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(textBusquedaNIA))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelBusquedaLista.setBackground(Colores.fondo);

        cbCurso.setBackground(Colores.fondo);
        cbCurso.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        cbCurso.setForeground(Colores.letraNormal);
        cbCurso.setPreferredSize(new java.awt.Dimension(374, 34));
        cbCurso.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbCursoItemStateChanged(evt);
            }
        });
        cbCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCursoActionPerformed(evt);
            }
        });

        jlistAlumnos.setBackground(Colores.fondo);
        jlistAlumnos.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jlistAlumnos.setForeground(Colores.letraNormal);
        jlistAlumnos.setSelectionBackground(Colores.accento);
        jlistAlumnos.setSelectionForeground(Colores.letraBotones);
        jlistAlumnos.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jlistAlumnosValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jlistAlumnos);

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel8.setForeground(Colores.letraNormal);
        jLabel8.setText("Listado de alumnos:");

        javax.swing.GroupLayout panelBusquedaListaLayout = new javax.swing.GroupLayout(panelBusquedaLista);
        panelBusquedaLista.setLayout(panelBusquedaListaLayout);
        panelBusquedaListaLayout.setHorizontalGroup(
            panelBusquedaListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBusquedaListaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBusquedaListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)
                    .addComponent(cbCurso, 0, 0, Short.MAX_VALUE)
                    .addGroup(panelBusquedaListaLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelBusquedaListaLayout.setVerticalGroup(
            panelBusquedaListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBusquedaListaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 487, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel8.getAccessibleContext().setAccessibleName("L");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator2)
                    .addComponent(panelBusquedaNIA, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelBusquedaLista, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(panelBusquedaNIA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelBusquedaLista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelListaLayout = new javax.swing.GroupLayout(panelLista);
        panelLista.setLayout(panelListaLayout);
        panelListaLayout.setHorizontalGroup(
            panelListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelListaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelListaLayout.setVerticalGroup(
            panelListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelListaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jSplitPane1.setLeftComponent(panelLista);

        jScrollPane4.setBorder(null);
        jScrollPane4.setMinimumSize(new java.awt.Dimension(512, 18));
        jScrollPane4.setPreferredSize(new java.awt.Dimension(512, 642));

        panelInformacion2.setBackground(Colores.fondo);
        panelInformacion2.setMinimumSize(new java.awt.Dimension(720, 642));
        panelInformacion2.setPreferredSize(new java.awt.Dimension(720, 642));

        jPanel18.setBackground(Colores.fondo);
        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel18.setMinimumSize(new java.awt.Dimension(620, 450));
        jPanel18.setPreferredSize(new java.awt.Dimension(1211, 706));

        panelInfoGeneral2.setBackground(Colores.fondo);
        panelInfoGeneral2.setMaximumSize(new java.awt.Dimension(300, 32767));
        panelInfoGeneral2.setPreferredSize(new java.awt.Dimension(922, 170));

        jPanel3.setBackground(Colores.fondo);
        jPanel3.setForeground(Colores.letraNormal);
        jPanel3.setLayout(new java.awt.GridLayout(1, 0));

        jLabel15.setBackground(Colores.fondo);
        jLabel15.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel15.setForeground(Colores.letraNormal);
        jLabel15.setText("Información del Alumno:");
        jLabel15.setOpaque(true);
        jPanel3.add(jLabel15);

        jLabel18.setBackground(Colores.fondo);
        jLabel18.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel18.setForeground(Colores.letraNormal);
        jLabel18.setText("Información curso:");
        jLabel18.setOpaque(true);
        jPanel3.add(jLabel18);

        jPanel17.setBackground(Colores.fondo);
        jPanel17.setLayout(new java.awt.GridLayout(1, 2));

        jPanel16.setBackground(Colores.fondo);

        jLabel16.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel16.setForeground(Colores.letraNormal);
        jLabel16.setText("NIA:");

        textNIAAlumno.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        textNIAAlumno.setForeground(Colores.letraNormal);
        textNIAAlumno.setPreferredSize(new java.awt.Dimension(32, 32));

        textNombreAlumno.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        textNombreAlumno.setForeground(Colores.letraNormal);
        textNombreAlumno.setPreferredSize(new java.awt.Dimension(32, 32));

        jLabel17.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel17.setForeground(Colores.letraNormal);
        jLabel17.setText("Nombre:");

        jPanel10.setBackground(Colores.fondo);
        jPanel10.setForeground(Colores.fondo);
        jPanel10.setPreferredSize(new java.awt.Dimension(128, 128));

        imgSexo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imgSexo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/person-flat.png"))); // NOI18N

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imgSexo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imgSexo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textNIAAlumno, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(textNombreAlumno, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE))
                        .addGap(728, 728, 728))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textNIAAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textNombreAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(26, Short.MAX_VALUE))
                    .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)))
        );

        jPanel17.add(jPanel16);

        jPanel2.setBackground(Colores.fondo);

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setForeground(Colores.letraNormal);
        jLabel2.setText("Curso escolar:");

        cbFecha.setBackground(Colores.fondo);
        cbFecha.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        cbFecha.setForeground(Colores.letraNormal);
        cbFecha.setMinimumSize(new java.awt.Dimension(65, 32));
        cbFecha.setPreferredSize(new java.awt.Dimension(65, 32));
        cbFecha.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbFechaItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(cbFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(91, Short.MAX_VALUE))
        );

        jPanel17.add(jPanel2);

        javax.swing.GroupLayout panelInfoGeneral2Layout = new javax.swing.GroupLayout(panelInfoGeneral2);
        panelInfoGeneral2.setLayout(panelInfoGeneral2Layout);
        panelInfoGeneral2Layout.setHorizontalGroup(
            panelInfoGeneral2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInfoGeneral2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelInfoGeneral2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelInfoGeneral2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelInfoGeneral2Layout.setVerticalGroup(
            panelInfoGeneral2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInfoGeneral2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelGestionAsignaturas.setBackground(Colores.fondo);

        jLabel5.setBackground(Colores.fondo);
        jLabel5.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel5.setForeground(Colores.letraNormal);
        jLabel5.setText("Asignaturas");
        jLabel5.setOpaque(true);

        jPanel4.setBackground(Colores.fondo);

        jPanel6.setBackground(Colores.fondo);

        jScrollPane2.setBackground(Colores.fondo);
        jScrollPane2.setForeground(Colores.fondo);
        jScrollPane2.setOpaque(false);

        tablaPendientes.setAutoCreateRowSorter(true);
        tablaPendientes.setBackground(Colores.fondo);
        tablaPendientes.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        tablaPendientes.setForeground(Colores.accento);
        tablaPendientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Asignatura", "Curso", "Idioma", "Curso pendiente"
            }
        ));
        tablaPendientes.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tablaPendientes.setRowHeight(32);
        tablaPendientes.setRowSelectionAllowed(false);
        tablaPendientes.setSelectionBackground(Colores.accento);
        tablaPendientes.setSelectionForeground(Colores.letraBotones);
        jScrollPane2.setViewportView(tablaPendientes);

        jPanel7.setBackground(Colores.fondo);
        jPanel7.setForeground(Colores.fondo);

        jPanel8.setBackground(Colores.fondo);
        jPanel8.setForeground(Colores.fondo);

        jPanel9.setBackground(Colores.accento);
        jPanel9.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 32, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel9.setForeground(Colores.letraNormal);
        jLabel9.setText("Curso pendiente");
        jLabel9.setToolTipText("");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panelEjemplaresEntregados.setBackground(Colores.fondo);

        panelCodigo.setBackground(Colores.fondo);

        textCodigoEjemplar.setBackground(Colores.fondo);
        textCodigoEjemplar.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        textCodigoEjemplar.setForeground(Colores.campoTextSinFocus);
        textCodigoEjemplar.setText("Escanee código ejemplar...");
        textCodigoEjemplar.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        textCodigoEjemplar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textCodigoEjemplarFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                textCodigoEjemplarFocusLost(evt);
            }
        });
        textCodigoEjemplar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textCodigoEjemplarKeyPressed(evt);
            }
        });

        btnCodigoEjemplar.setBackground(Colores.botones);
        btnCodigoEjemplar.setForeground(Colores.letraBotones);
        btnCodigoEjemplar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons/clipboard-arrow-up.png"))); // NOI18N
        btnCodigoEjemplar.setCornerRound(10);
        btnCodigoEjemplar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCodigoEjemplarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelCodigoLayout = new javax.swing.GroupLayout(panelCodigo);
        panelCodigo.setLayout(panelCodigoLayout);
        panelCodigoLayout.setHorizontalGroup(
            panelCodigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCodigoLayout.createSequentialGroup()
                .addComponent(textCodigoEjemplar, javax.swing.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCodigoEjemplar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelCodigoLayout.setVerticalGroup(
            panelCodigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(textCodigoEjemplar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnCodigoEjemplar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jlistEjemplares.setBackground(Colores.fondo);
        jlistEjemplares.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jlistEjemplares.setForeground(Colores.letraNormal);
        jlistEjemplares.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jlistEjemplares.setSelectionBackground(Colores.accento);
        jlistEjemplares.setSelectionForeground(Colores.letraBotones);
        jScrollPane3.setViewportView(jlistEjemplares);

        javax.swing.GroupLayout panelEjemplaresEntregadosLayout = new javax.swing.GroupLayout(panelEjemplaresEntregados);
        panelEjemplaresEntregados.setLayout(panelEjemplaresEntregadosLayout);
        panelEjemplaresEntregadosLayout.setHorizontalGroup(
            panelEjemplaresEntregadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelCodigo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        panelEjemplaresEntregadosLayout.setVerticalGroup(
            panelEjemplaresEntregadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEjemplaresEntregadosLayout.createSequentialGroup()
                .addComponent(panelCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel10.setForeground(Colores.letraNormal);
        jLabel10.setText("Ejemplares entregados:");
        jLabel10.setPreferredSize(new java.awt.Dimension(167, 32));

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel6.setForeground(Colores.letraNormal);
        jLabel6.setText("Pendientes de entregar:");
        jLabel6.setPreferredSize(new java.awt.Dimension(171, 32));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(6, 6, 6))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelEjemplaresEntregados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelEjemplaresEntregados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout panelGestionAsignaturasLayout = new javax.swing.GroupLayout(panelGestionAsignaturas);
        panelGestionAsignaturas.setLayout(panelGestionAsignaturasLayout);
        panelGestionAsignaturasLayout.setHorizontalGroup(
            panelGestionAsignaturasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGestionAsignaturasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelGestionAsignaturasLayout.setVerticalGroup(
            panelGestionAsignaturasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGestionAsignaturasLayout.createSequentialGroup()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jSeparator1.setPreferredSize(new java.awt.Dimension(922, 2));

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelInfoGeneral2, javax.swing.GroupLayout.DEFAULT_SIZE, 1031, Short.MAX_VALUE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1031, Short.MAX_VALUE)
                    .addComponent(panelGestionAsignaturas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelInfoGeneral2, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelGestionAsignaturas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelInformacion2Layout = new javax.swing.GroupLayout(panelInformacion2);
        panelInformacion2.setLayout(panelInformacion2Layout);
        panelInformacion2Layout.setHorizontalGroup(
            panelInformacion2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInformacion2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, 1045, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelInformacion2Layout.setVerticalGroup(
            panelInformacion2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInformacion2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, 648, Short.MAX_VALUE)
                .addContainerGap())
        );

        jScrollPane4.setViewportView(panelInformacion2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1057, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1057, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 660, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 660, Short.MAX_VALUE))
        );

        jSplitPane1.setRightComponent(jPanel1);

        javax.swing.GroupLayout panelCuerpoLayout = new javax.swing.GroupLayout(panelCuerpo);
        panelCuerpo.setLayout(panelCuerpoLayout);
        panelCuerpoLayout.setHorizontalGroup(
            panelCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        panelCuerpoLayout.setVerticalGroup(
            panelCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelCuerpo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    /**
     * Controlamos cuando cerrmaos
     *
     * @param evt
     */
    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        daoAlumno.desconectar();
        daoMatricula.desconectar();
        daoCurso.desconectar();
        daoCursoTabla.desconectar();
        daoEjemplar.desconectar();
        daoHistorial.desconectar();
    }//GEN-LAST:event_formWindowClosed

    /**
     * Metodo que utilizamos para controlar cuando pulsamos la tecla intro desde
     * el TextField de busqueda NIA
     *
     * @param evt
     */
    private void textBusquedaNIAKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textBusquedaNIAKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            buscarAlumno(textBusquedaNIA.getText());
        }
    }//GEN-LAST:event_textBusquedaNIAKeyPressed

    private void cbCursoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbCursoItemStateChanged
        // TODO add your handling code here:
        rellenarLista((Curso) cbCurso.getSelectedItem());
    }//GEN-LAST:event_cbCursoItemStateChanged

    private void jlistAlumnosValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jlistAlumnosValueChanged
        // TODO add your handling code here:
        alumno = (Alumno) jlistAlumnos.getSelectedValue();

        cargarDatosAlumno();
    }//GEN-LAST:event_jlistAlumnosValueChanged

    private void textBusquedaNIAFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textBusquedaNIAFocusGained
        // TODO add your handling code here:
        textBusquedaNIA.setText("");
        textBusquedaNIA.setForeground(Colores.letraNormal);
    }//GEN-LAST:event_textBusquedaNIAFocusGained

    private void textBusquedaNIAFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textBusquedaNIAFocusLost
        // TODO add your handling code here:
        textBusquedaNIA.setText(StringsGlobales.placeHolder_nia);
        textBusquedaNIA.setForeground(Colores.campoTextSinFocus);
    }//GEN-LAST:event_textBusquedaNIAFocusLost

    private void btnBusquedaNIAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBusquedaNIAActionPerformed
        // TODO add your handling code here:
        buscarAlumno(campoBusquedaTemp);
        campoBusquedaTemp = "";
    }//GEN-LAST:event_btnBusquedaNIAActionPerformed

    private void cbCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCursoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbCursoActionPerformed

    private void textBusquedaNIAKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textBusquedaNIAKeyReleased
        // TODO add your handling code here:
        campoBusquedaTemp = textBusquedaNIA.getText();
    }//GEN-LAST:event_textBusquedaNIAKeyReleased

    private void textCodigoEjemplarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textCodigoEjemplarKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String codigo = textCodigoEjemplar.getText();
            buscarEjemplar(codigo.toUpperCase());
        }
    }//GEN-LAST:event_textCodigoEjemplarKeyPressed

    private void textCodigoEjemplarFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textCodigoEjemplarFocusLost
        // TODO add your handling code here:
        codigoTemp = textCodigoEjemplar.getText();
        textCodigoEjemplar.setText("Escanee código ejemplar...");
        textCodigoEjemplar.setForeground(Colores.campoTextSinFocus);
    }//GEN-LAST:event_textCodigoEjemplarFocusLost

    private void textCodigoEjemplarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textCodigoEjemplarFocusGained
        // TODO add your handling code here:
        codigoTemp = "";
        textCodigoEjemplar.setText("");
        textCodigoEjemplar.setForeground(Colores.letraNormal);
    }//GEN-LAST:event_textCodigoEjemplarFocusGained

    private void btnCodigoEjemplarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCodigoEjemplarActionPerformed
        buscarEjemplar(codigoTemp.toUpperCase());
        codigoTemp = "";
        textCodigoEjemplar.setText("");
    }//GEN-LAST:event_btnCodigoEjemplarActionPerformed

    private void cbFechaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbFechaItemStateChanged
        if (evt.getStateChange() == evt.SELECTED) {
            cargarDatosAlumno();
            System.out.println("mec");
        }
    }//GEN-LAST:event_cbFechaItemStateChanged

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
            java.util.logging.Logger.getLogger(FrameEntrega.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameEntrega.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameEntrega.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameEntrega.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
    private com.mommoo.flat.button.FlatButton btnBusquedaNIA;
    private com.mommoo.flat.button.FlatButton btnCodigoEjemplar;
    private javax.swing.JComboBox cbCurso;
    private javax.swing.JComboBox cbFecha;
    private javax.swing.JLabel imgSexo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JList jlistAlumnos;
    private javax.swing.JList jlistEjemplares;
    private javax.swing.JPanel panelBusquedaLista;
    private javax.swing.JPanel panelBusquedaNIA;
    private javax.swing.JPanel panelCodigo;
    private javax.swing.JPanel panelCuerpo;
    private javax.swing.JPanel panelEjemplaresEntregados;
    private javax.swing.JPanel panelGestionAsignaturas;
    private javax.swing.JPanel panelInfoGeneral2;
    private javax.swing.JPanel panelInformacion2;
    private javax.swing.JPanel panelLista;
    private javax.swing.JPanel panelTitulo;
    public static javax.swing.JTable tablaPendientes;
    private javax.swing.JTextField textBusquedaNIA;
    private javax.swing.JTextField textCodigoEjemplar;
    private javax.swing.JLabel textNIAAlumno;
    private javax.swing.JLabel textNombreAlumno;
    // End of variables declaration//GEN-END:variables

    /**
     * Metodo que utilizamos para rellenar la tabla de los libros pendientes de
     * entrega
     *
     * @param listaMatriculas Es la lista de matriculas que debemos recuperar
     * del archivo xml previamente importado
     */
    private void rellenarTablaPendiente(List<Matricula> lista) {
        Object[][] contenidoTabla = new Object[lista.size()][4];

        for (int i = 0; i < lista.size(); i++) {
            contenidoTabla[i][0] = lista.get(i);
            contenidoTabla[i][1] = lista.get(i);
            contenidoTabla[i][2] = lista.get(i);
            contenidoTabla[i][3] = lista.get(i);
        }

        DefaultTableModel tableModel = new DefaultTableModel(contenidoTabla,
                new Object[]{"Asignaturas", "Curso", "Idioma", "Curso Pendiente"});

        tablaPendientes.setModel(tableModel);

        TableColumnModel tcm = tablaPendientes.getColumnModel();
        tcm.getColumn(0).setMinWidth(75);

        tcm.getColumn(1).setMinWidth(75);
        tcm.getColumn(1).setMaxWidth(500);
        tcm.getColumn(1).setPreferredWidth(350);

        tcm.getColumn(2).setMinWidth(75);
        tcm.getColumn(2).setMaxWidth(200);
        tcm.getColumn(2).setPreferredWidth(125);

        tcm.getColumn(3).setMinWidth(75);
        tcm.getColumn(3).setMaxWidth(350);
        tcm.getColumn(3).setPreferredWidth(100);

        RemarcarCeldas remarcarCeldas = new RemarcarCeldas(daoCursoTabla);

        for (int i = 0; i < tableModel.getColumnCount(); i++) {
            tcm.getColumn(i).setCellRenderer(remarcarCeldas);
        }
    }

    /**
     * Cargamos los datos iniciales, como los alumnos o los cursos
     */
    public void cargarDatos() {
        SwingWorker<?, ?> worker = new SwingWorker<Void, Void>() {

            protected Void doInBackground() throws InterruptedException {
                listaAlumnos = daoAlumno.buscarTodos();
                listaCursos = daoCurso.buscarTodos();
                return null;
            }

            protected void done() {
                sustituirPadresCursos();
                rellenarCursos();

                framePopup.dispose();
            }
        };
        worker.execute();
        if (framePopup == null) {
            framePopup = new FramePopup(this);
        }
        framePopup.setVisible(true);
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
     * Metodo para rellenar los cursos del combobox
     */
    public void rellenarCursos() {

        cbCurso.removeAllItems();

        //Rellenamos los cursos
        if (listaCursos.size() > 0) {
            for (int i = 0; i < listaCursos.size(); i++) {
                cbCurso.addItem(listaCursos.get(i));
            }
        } else {
            new FramePopup(this, "No hay cursos en la base de datos.",
                    Imagenes.getImagen("alert-black.png"),
                    "Aceptar").setVisible(true);
        }
        rellenarLista((Curso) cbCurso.getSelectedItem());
    }

    /**
     * Este metodo se utiliza para cargar los datos de el alumno encontrado
     * anteriormente.
     */
    private void cargarDatosAlumno() {
        vaciarCampos();

        if (alumno == null) {
            modoEdicion(false);
        } else {
            SwingWorker<?, ?> worker = new SwingWorker<Void, Void>() {
                protected Void doInBackground() throws InterruptedException {
                    listaMatriculas = daoMatricula.buscarPendientes(alumno, Integer.parseInt(cbFecha.getSelectedItem().toString()));
                    return null;
                }

                protected void done() {
                    listaCursos.size();

                    listaEjemplaresEntregados = new ArrayList<Ejemplar>();

                    if (alumno.getSexo().equals("H")) {
                        imgSexo.setIcon(Imagenes.getImageFromImagenes("person-flat.png"));
                    } else {
                        imgSexo.setIcon(Imagenes.getImageFromImagenes("person-girl-flat.png"));
                    }

                    textNIAAlumno.setText(alumno.getNia());
                    textNombreAlumno.setText(alumno.getNombre() + " " + alumno.getApellido1());
                    textNombreAlumno.setToolTipText(alumno.getNombre() + " " + alumno.getApellido1());

                    if (listaMatriculas != null && listaMatriculas.size() > 0) {
                        rellenarTablaPendiente(listaMatriculas);
                    } else {
                        new FramePopup(topFrame, "El alumno no está matriculado en este curso escolar",
                                Imagenes.getImagen("alert-black.png"),
                                "Aceptar").setVisible(true);
                    }
                    modoEdicion(true);
                    framePopup.dispose();
                }
            };
            worker.execute();
            if (framePopup == null) {
                framePopup = new FramePopup(this);
            }
            framePopup.setVisible(true);
        }
    }

    private void vaciarCampos() {
        textNIAAlumno.setText("");
        textNombreAlumno.setText("");
        textNombreAlumno.setToolTipText("");

        DefaultTableModel model = new DefaultTableModel(null, new Object[]{"Asignatura", "Curso", "Idioma", "Curso Pendiente"});
        tablaPendientes.setModel(model);

        DefaultListModel listModel = new DefaultListModel();
        jlistEjemplares.setModel(listModel);

        listaEjemplaresEntregados = new ArrayList<Ejemplar>();
    }

    /**
     * Este metodo se utiliza para conseguir la fecha del curso escolar
     *
     * @return Devuelve un 'int' con la año actual
     */
    private int getFecha() {
        LocalDate localDate = LocalDate.now();
        String dateActual = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(localDate);
        String year = DateTimeFormatter.ofPattern("yyyy").format(localDate);
        String dateLimit = year + "-08-31";
        System.out.print("Fecha limite: " + dateLimit + "\nFecha actual: " + dateActual + "\nEs Mayor: ");

        int fecha = 0;
        try {
            if (dateActual.compareTo(dateLimit) <= 0) {
                fecha = Integer.parseInt(year) - 1;
            } else {
                fecha = Integer.parseInt(year);
            }

        } catch (NumberFormatException e) {
            Action aceptar = new AbstractAction() {
                public void actionPerformed(ActionEvent e) {
                    FrameEntrega.this.dispose();
                }
            };

            new FramePopup(this, "No se ha podido conseguir el curso escolar",
                    Imagenes.getImagen("alert-black.png"),
                    "Aceptar", aceptar).setVisible(true);
        }
        return fecha;
    }

    /**
     * Metodo que utilizamos para buscar un alumno en la base de datos
     *
     * @param nia Deberemos pasarle el NIA del alumno
     */
    public void buscarAlumno(String nia) {
        if (!nia.equals("") || nia.equals(StringsGlobales.placeHolder_nia)) {
            //Se ha insertado un codigo
            SwingWorker<?, ?> worker = new SwingWorker<Void, Void>() {

                RemarcarCeldas remarcarCeldas = new RemarcarCeldas(daoCursoTabla);

                protected Void doInBackground() throws InterruptedException {
                    alumno = daoAlumno.buscar(nia.toUpperCase());
                    return null;
                }

                protected void done() {
                    framePopup.dispose();

                    if (alumno != null) {
                        //Seleccionamos el curso del alumno en el ComboBox de cursos
                        for (int i = 0; i < cbCurso.getItemCount(); i++) {
                            Curso c = (Curso) cbCurso.getItemAt(i);

                            if (c.getCodigo().equals(alumno.getCurso().getCodigo())) {
                                cbCurso.setSelectedIndex(i);
                                cbCurso.setSelectedItem(c);
                            }
                        }

                        //Seleccionamos el alumno en la lista lateral izquierda
                        for (int i = 0; i < jlistAlumnos.getModel().getSize(); i++) {
                            Alumno alumnot = (Alumno) jlistAlumnos.getModel().getElementAt(i);
                            if (alumnot.getNia().equals(nia)) {
                                jlistAlumnos.setSelectedIndex(i);
                            }
                        }
                    } else {
                        new FramePopup(topFrame, "No existe ningún alumno con el NIA introducido.",
                                Imagenes.getImagen("alert-black.png"),
                                "Aceptar").setVisible(true);

                        vaciarCampos();
                        modoEdicion(false);
                    }

                }
            };
            worker.execute();
            framePopup = new FramePopup(this, "Cargando datos...");
            framePopup.setVisible(true);

        } else {
            //No se ha insertado ningun valor en el campo de texto
            new FramePopup(this, "El NIA no puede ser un campo vacío.",
                    Imagenes.getImagen("alert-black.png"),
                    "Aceptar").setVisible(true);
        }

    }

    /**
     * Metodo que busca el Ejemplar en la BD y realiza la gestión de la entrega
     * del mismo.
     *
     * @param codigo
     */
    public void buscarEjemplar(String codigo) {
        if (!codigo.equals("")) {
            //Se ha insertado un codigo
            SwingWorker<?, ?> worker = new SwingWorker<Void, Void>() {
                protected Void doInBackground() throws InterruptedException {
                    ejemplar = daoEjemplar.buscar(codigo);
                    return null;
                }

                protected void done() {
                    framePopup.dispose();

                    if (ejemplar != null) {
                        if (ejemplar.isPrestado()) {
                            new FramePopup(topFrame, "El ejemplar con código "
                                    + ejemplar.getCodigo() + " ya está prestado.",
                                    Imagenes.getImagen("alert-black.png"),
                                    "Aceptar").setVisible(true);
                        } else if (ejemplar.getLibro().getObsoleto()) {
                            new FramePopup(topFrame, "El ejemplar con código "
                                    + ejemplar.getCodigo() + " corresponde a un libro obsoleto.",
                                    Imagenes.getImagen("alert-black.png"),
                                    "Aceptar").setVisible(true);
                        } else if (ejemplar.getEstado() == Estado.deteriorado) {
                            new FramePopup(topFrame, "El ejemplar con código "
                                    + ejemplar.getCodigo() + " está deteriorado.",
                                    Imagenes.getImagen("alert-black.png"),
                                    "Aceptar").setVisible(true);
                        } else {
                            try {
                                ejemplar.setPrestado(true);

                                Historial historial = new Historial(
                                        ejemplar,
                                        alumno,
                                        Integer.parseInt(cbFecha.getSelectedItem().toString()),
                                        ejemplar
                                        .getEstado(),
                                        -1,
                                        new Date(),
                                        null,
                                        ""
                                );

                                daoHistorial.grabar(historial);

                                listaEjemplaresEntregados.add(historial.getEjemplar());

                                DefaultListModel model = new DefaultListModel();

                                for (int i = 0; i < listaEjemplaresEntregados.size(); i++) {
                                    model.add(i, listaEjemplaresEntregados.get(i));
                                }

                                jlistEjemplares.setModel(model);

                                DefaultTableModel modeloTabla = (DefaultTableModel) tablaPendientes.getModel();

                                for (int i = 0; i < modeloTabla.getRowCount(); i++) {
                                    Matricula m = (Matricula) modeloTabla.getValueAt(i, 0);

                                    if (m.getContenido().getCodigo().equals(ejemplar.getLibro().getContenido().getCodigo())) {
                                        modeloTabla.removeRow(i);
                                        listaMatriculas.remove(m);
                                    }
                                }

                                rellenarTablaPendiente(listaMatriculas);
                                framePopup.dispose();
                            } catch (Exception e) {
                                e.printStackTrace();

                                new FramePopup(topFrame, "Error al generar el historial del ejemplar "
                                        + ejemplar.getCodigo() + ".",
                                        Imagenes.getImagen("alert-black.png"),
                                        "Aceptar").setVisible(true);
                            }
                        }
                    } else {
                        new FramePopup(topFrame, "No existe ningún ejemplar con el código introducido.",
                                Imagenes.getImagen("alert-black.png"),
                                "Aceptar").setVisible(true);
                    }

                }
            };
            worker.execute();
            framePopup = new FramePopup(this, "Cargando datos...");

            framePopup.setVisible(
                    true);

        } else {
            //No se ha insertado ningun valor en el campo de texto
            new FramePopup(this, "El código no puede ser un campo vacío.",
                    Imagenes.getImagen("alert-black.png"),
                    "Aceptar").setVisible(true);
        }

    }

    /**
     * Al pulsar sobre una fila de una tabla esté metodo nos rellenara el campo
     * de buscar Ejemplar con el código del primer ejemplar de ese contenido que
     * no esté prestado.
     *
     * @param m
     */
    private void rellenarEjemplarSugerido(Matricula m) {
        Ejemplar ejemplarSugerido = daoEjemplar.buscarLibre(m.getContenido());

        if (ejemplarSugerido != null) {
            codigoTemp = ejemplarSugerido.getCodigo();
            textCodigoEjemplar.setText(ejemplarSugerido.getCodigo());
        } else {
            textCodigoEjemplar.setText("");

            new FramePopup(this, "No se han encontrado libros disponibles "
                    + "correspondientes a la asignatura seleccionada.",
                    Imagenes.getImagen("alert-black.png"), "Aceptar").setVisible(true);
        }
    }

    /**
     * Metodo que utilizamos para rellenar la lista de alumnos dependiendo del
     * curso seleccionado
     *
     * @param curso Curso seleccionado en el comboBox
     */
    private void rellenarLista(Curso curso) {
        List<Alumno> listaAlumnosCurso = listaAlumnos.stream().filter(a -> a.getCurso().equals(curso)).collect(Collectors.toList());
        DefaultListModel listModel = new DefaultListModel();
        for (int i = 0; i < listaAlumnosCurso.size(); i++) {
            listModel.addElement(listaAlumnosCurso.get(i));
        }
        jlistAlumnos.setModel(listModel);
    }

    private void modoEdicion(boolean b) {
        textCodigoEjemplar.setEnabled(b);
        jlistEjemplares.setEnabled(b);
        cbFecha.setEnabled(b);
    }

    /**
     * Metodo que rellena el combo box para seleccionar el curso escolar
     */
    private void rellenarComboBoxFecha() {
        int fecha = getFecha();
        int diferencia = 5;
        for (int i = (fecha - diferencia); i < (fecha + diferencia); i++) {
            cbFecha.addItem(i);
        }
        cbFecha.setSelectedIndex(diferencia);
    }
}
