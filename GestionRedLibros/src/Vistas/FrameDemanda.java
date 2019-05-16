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

import Daos.DaoContenido;
import Daos.DaoCurso;
import Daos.DaoLibro;
import Daos.DaoMatricula;
import Pojos.Contenido;
import Pojos.Curso;
import Pojos.Libro;
import Pojos.Matricula;
import Renders.comboBoxRender;
import Utilidades.Colores;
import Utilidades.Imagenes.Imagenes;
import java.awt.Component;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import org.hibernate.Session;

/**
 *
 * @author Jose Sanchis
 */
public class FrameDemanda extends javax.swing.JFrame {

    private Session session = Main.gestorSesiones.getSession();

    FramePopup frameCarga;

    private DaoCurso daoCurso;
    private DaoMatricula daoMatricula;
    private DaoContenido daoContenido;
    private DaoLibro daoLibro;

    private List<Curso> listaCursos;
    private List<Matricula> listaMatriculas;

    /**
     * Creates new form FrameDemanda
     */
    public FrameDemanda() {
        initComponents();

        //<editor-fold defaultstate="collapsed" desc="Configuracion colores tabla">
        jScrollPane1.getViewport().setBackground(Colores.fondo);
        JTableHeader anHeader = tabla.getTableHeader();
        anHeader.setForeground(Colores.letraNormal);
        anHeader.setBackground(Colores.fondo);

        //Deshabilitamos la edicion de las celdas en las tablas
        tabla.setDefaultEditor(Object.class, null);
        tabla.getTableHeader().setReorderingAllowed(false);
//</editor-fold>

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
//</editor-fold>

        this.setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        daoCurso = new DaoCurso(session);
        daoMatricula = new DaoMatricula(session);
        daoContenido = new DaoContenido(session);
        daoLibro = new DaoLibro(session);

        vaciarTabla();

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

        panelTitulo = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        panelContainer = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        cbCurso = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        cbCursoEscolar = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Stock demanda");
        setMinimumSize(new java.awt.Dimension(1024, 768));

        panelTitulo.setBackground(Colores.fondoOscuro);
        panelTitulo.setPreferredSize(new java.awt.Dimension(119, 75));

        jLabel1.setBackground(Colores.accento);
        jLabel1.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel1.setForeground(Colores.letraTitulo);
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Stock de Libros");

        javax.swing.GroupLayout panelTituloLayout = new javax.swing.GroupLayout(panelTitulo);
        panelTitulo.setLayout(panelTituloLayout);
        panelTituloLayout.setHorizontalGroup(
            panelTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelTituloLayout.setVerticalGroup(
            panelTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
        );

        panelContainer.setBackground(Colores.fondo);

        jPanel1.setBackground(Colores.fondo);
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        cbCurso.setBackground(Colores.fondo);
        cbCurso.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        cbCurso.setForeground(Colores.letraNormal);
        cbCurso.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbCurso.setPreferredSize(new java.awt.Dimension(65, 32));
        cbCurso.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbCursoItemStateChanged(evt);
            }
        });

        tabla.setBackground(Colores.fondo);
        tabla.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        tabla.setForeground(Colores.letraNormal);
        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Asignatura", "Libros actuales", "Demanda", "Libros a comprar"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla.setRowHeight(32);
        tabla.setSelectionBackground(Colores.accento);
        tabla.setSelectionForeground(Colores.letraBotones);
        jScrollPane1.setViewportView(tabla);

        cbCursoEscolar.setBackground(Colores.fondo);
        cbCursoEscolar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        cbCursoEscolar.setForeground(Colores.letraNormal);
        cbCursoEscolar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbCursoEscolar.setPreferredSize(new java.awt.Dimension(65, 32));
        cbCursoEscolar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbCursoEscolarItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cbCursoEscolar, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 948, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbCursoEscolar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 511, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout panelContainerLayout = new javax.swing.GroupLayout(panelContainer);
        panelContainer.setLayout(panelContainerLayout);
        panelContainerLayout.setHorizontalGroup(
            panelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelContainerLayout.setVerticalGroup(
            panelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 974, Short.MAX_VALUE)
            .addComponent(panelContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbCursoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbCursoItemStateChanged
        // TODO add your handling code here:

        //Si el indice seleccionado es el 0 significa que el curso seleccionado
        //es Todos
        Curso cursoSeleccionado = (Curso) cbCurso.getSelectedItem();

        if (evt.getStateChange() == evt.SELECTED) {
            if (cursoSeleccionado != null && !cursoSeleccionado.getCodigo().equals("Selecciona curso")) {
                rellenarTabla((Curso) cbCurso.getSelectedItem());
            } else {
                vaciarTabla();
            }
        }
    }//GEN-LAST:event_cbCursoItemStateChanged

    private void cbCursoEscolarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbCursoEscolarItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbCursoEscolarItemStateChanged

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
            java.util.logging.Logger.getLogger(FrameDemanda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameDemanda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameDemanda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameDemanda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameDemanda().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cbCurso;
    private javax.swing.JComboBox cbCursoEscolar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelContainer;
    private javax.swing.JPanel panelTitulo;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables

    /**
     * Metodo para cargar y rellenar los cursos
     */
    private void cargarDatos() {
        SwingWorker<?, ?> worker = new SwingWorker<Void, Void>() {
            protected Void doInBackground() throws InterruptedException {
                listaCursos = daoCurso.buscarTodos();
                return null;
            }

            protected void done() {
                sustituirPadresCursos();

                rellenarCursos();
                frameCarga.dispose();
            }
        };
        worker.execute();
        if (frameCarga == null) {
            frameCarga = new FramePopup(this);
        }
        frameCarga.setVisible(true);
    }

    private void rellenarCursos() {
        System.out.println("Rellenando cursos...");

        cbCurso.removeAllItems();

        cbCurso.addItem(new Curso("Selecciona curso", "Selecciona curso", "Selecciona curso", "Selecciona curso", "Selecciona curso", " "));

        if (listaCursos.size() > 0) {
            for (int i = 0; i < listaCursos.size(); i++) {
                cbCurso.addItem(listaCursos.get(i));
            }
        } else {
            new FramePopup(this, "No hay cursos en la base de datos.",
                    Imagenes.getImagen("alert-black.png"),
                    "Aceptar").setVisible(true);
        }
    }

    private void rellenarTabla(Curso curso) {
        SwingWorker<?, ?> worker = new SwingWorker<Void, Void>() {
            protected Void doInBackground() throws InterruptedException {
                listaMatriculas = daoMatricula.buscarPendientesPorCurso(getFechaCursoEscolar(), curso);
                return null;
            }

            protected void done() {
                frameCarga.dispose();

                HashMap<String, Integer> contenidos = new HashMap<String, Integer>();

                for (int i = 0; i < listaMatriculas.size(); i++) {
                    Matricula m = listaMatriculas.get(i);

                    //Si ya existe incrementamos la cantidad de ejemplares demandados
                    String key = m.getContenido().getCodigo();

                    if (contenidos.containsKey(key)) {
                        Integer librosDemandados = contenidos.get(key);

                        librosDemandados++;

                        contenidos.replace(key, librosDemandados);
                    } else {
                        contenidos.put(key, 1);
                    }
                }

                //Rellenamos la tabla con los datos obtenidos
                DefaultTableModel tableModel;

                if (contenidos.size() > 0) {
                    Object[][] contenidoTabla = new Object[contenidos.size()][4];

                    int it = 0;

                    for (Map.Entry<String, Integer> entry : contenidos.entrySet()) {
                        Contenido contenido = daoContenido.buscarPorCodigo(entry.getKey());
                        List<Libro> librosContenido = daoLibro.buscarPorContenido(contenido);

                        int stock = 0;

                        for (int i = 0; i < librosContenido.size(); i++) {
                            Libro libro = librosContenido.get(i);

                            stock += libro.getEjemplaresDisponibles().size();
                        }

                        contenidoTabla[it][0] = contenido.getNombre_cas();
                        contenidoTabla[it][1] = stock;
                        contenidoTabla[it][2] = entry.getValue();

                        if ((entry.getValue() - stock) < 0) {
                            contenidoTabla[it][3] = 0;
                        } else {
                            contenidoTabla[it][3] = entry.getValue() - stock;
                        }

                        it++;
                    }

                    tableModel = new DefaultTableModel(contenidoTabla,
                            new Object[]{"Asignatura", "Ejemplares en stock", "Ejemplares necesarios", "Ejemplares a comprar"}) {
                                @Override
                                public boolean isCellEditable(int row, int column) {
                                    return false;
                                }
                            };

                    tabla.setModel(tableModel);
                } else {
                    vaciarTabla();
                }
            }
        };
        worker.execute();
        frameCarga = new FramePopup(this, "Recuperando datos de la BD...");
        frameCarga.setVisible(true);
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
     * Metodo para vaciar la tabla de stock/demanda
     */
    private void vaciarTabla() {
        DefaultTableModel model = new DefaultTableModel(null, new Object[]{"Asignatura", "Ejemplares en stock", "Ejemplares necesarios", "Ejemplares a comprar"});
        tabla.setModel(model);
    }

    /**
     * Este metodo se utiliza para conseguir la fecha del curso escolar
     *
     * @return Devuelve un 'int' con la año actual
     */
    private int getFechaCursoEscolar() {
        LocalDate localDate = LocalDate.now();
        String date = DateTimeFormatter.ofPattern("yyyy").format(localDate);
        int fecha = 0;
        try {
            fecha = Integer.parseInt(date);
        } catch (NumberFormatException e) {
            new FramePopup(this, "No se ha podido conseguir el curso escolar",
                    Imagenes.getImagen("/Imagenes/icons/alert-black.png"),
                    "Aceptar").setVisible(true);
        }
        return fecha;
    }
}
