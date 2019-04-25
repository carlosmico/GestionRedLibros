package Renders;

import Daos.DaoCurso;
import Pojos.Curso;
import Pojos.Ejemplar;
import Pojos.Historial;
import Pojos.Matricula;
import Utilidades.Colores;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.*;

/**
 *
 * @author Jose Sanchis
 */
public class RemarcarCeldas extends JLabel implements TableCellRenderer {

    public DaoCurso daoCurso;

    public RemarcarCeldas(DaoCurso dao) {
        daoCurso = dao;
    }

    public Component getTableCellRendererComponent(JTable tbl, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        //TableCellRenderer renderer = tbl.getDefaultRenderer(tbl.getColumnClass(column));

        setOpaque(true);
        setFont(new Font("Dialog", Font.BOLD, 18));

        if (value instanceof Matricula) {
            Matricula matricula = (Matricula) value;

            Curso c = null;
            Curso cursoPendiente = null;
            
            switch (column) {
                case 0:
                    System.out.println("RemarcarCeldas: Contenido de Matricula: " + matricula.toString());
                    setText(matricula.getContenido().getNombre_cas());
                    break;

                case 1:
                    c = sustituirNombrePadre(matricula);
                    setText(c.getAbreviatura() + " - " + c.getNombre_padre());
                    setToolTipText(c.getAbreviatura() + " - " + c.getNombre_padre());
                    break;

                case 2:
                    if (matricula.getIdioma().equals(" ")) {
                        setText("Por defecto");
                    } else {
                        setText(matricula.getIdioma());
                    }
                    break;
                case 3:
                    if (!matricula.getCurso_pendiente().equals(" ")) {
                        c = obtenerCursoPendiente(matricula);
                        setText(c.getAbreviatura() + " - " + c.getNombre_padre());
                    } else {
                        setText("");
                    }
                    break;
                case 4:
                    cursoPendiente = sustituirNombrePadre(c);
                    setText(cursoPendiente.getAbreviatura() + " - " + cursoPendiente.getNombre_padre());
                    setToolTipText(cursoPendiente.getAbreviatura() + " - " + cursoPendiente.getNombre_padre());
                    break;
            }
            getColor(this, matricula);
        } else if (value instanceof Historial) {
            Historial historial = (Historial) value;
            Ejemplar ejemplar = historial.getEjemplar();
            Curso curso = ejemplar.getLibro().getContenido().getCurso();

            switch (column) {
                case 0:
                    setText(ejemplar.getCodigo());
                    break;
                case 1:
                    setText(ejemplar.getLibro().getNombre());
                    break;
                case 2:
                    curso = sustituirNombrePadre(curso);
                    setText(curso.getAbreviatura() + " - " + curso.getNombre_padre());
                    break;
                case 3:
                    setText(historial.getCurso_escolar() + "/" + ((historial.getCurso_escolar() + 1) + "").substring(2));
                    break;
            }
        }
        return this;
    }

    public void desconectarDao() {
        daoCurso.desconectar();
    }

    /**
     * Colorea la fila de la tabla la cual pertenece a un curso que no se
     * corresponde a este año escolar, es decir, que el alumno esta repitiendo
     *
     * @param label El JLabel el cual vamos a colorear
     * @param matricula La matricula de la cual sacaremos los datos
     */
    public void getColor(JLabel label, Matricula matricula) {
        if (matricula.getCurso_pendiente().equals(" ")) {
            label.setForeground(Colores.letraNormal);
            label.setBackground(Colores.fondo);
        } else {
            label.setForeground(Colores.letraNormal);
            label.setBackground(Colores.fondoOscuro);
        }
    }

    /**
     * Metodo para buscar el Padre de cada Curso y sustituir el atributo idPadre
     * por el nombre del Padre a partir de la matricula
     */
    private Curso sustituirNombrePadre(Matricula m) {

        Curso curso = daoCurso.buscar(m.getCurso());
        Curso cursoPadre = daoCurso.buscar(curso.getIdPadre());

        if (cursoPadre != null) {
            curso.setNombre_padre(daoCurso.buscar(curso.getIdPadre()).getNombre_cas());
        }
        return curso;
    }

    /**
     * Metodo para buscar el Padre de cada Curso y sustituir el atributo idPadre
     * por el nombre del Padre a partir del curso actual
     */
    private Curso sustituirNombrePadre(Curso curso) {
        Curso c = curso;
        Curso cursoPadre = daoCurso.buscar(c.getIdPadre());

        if (cursoPadre != null) {
            c.setNombre_padre(daoCurso.buscar(c.getIdPadre()).getNombre_cas());
        }
        return c;
    }

    /**
     * Metodo para saber el nombre de los cursos pendientes de una matricula
     *
     * @param m MAtricula de la cual queremos saber los cursos pendientes
     * @return Curso pedndiente (objeto)
     */
    private Curso obtenerCursoPendiente(Matricula m) {
        Curso cursoPendiente = daoCurso.buscar(m.getCurso_pendiente());
        Curso cursoPadre = daoCurso.buscar(cursoPendiente.getIdPadre());

        if (cursoPadre != null) {
            cursoPendiente.setNombre_padre(daoCurso.buscar(cursoPendiente.getIdPadre()).getNombre_cas());
        }
        
        return cursoPendiente;
    }
}
