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

            try{
                c = daoCurso.buscar(matricula.getCurso());
                cursoPendiente = daoCurso.buscar(matricula.getCurso_pendiente());
            }catch(Exception e){
                e.printStackTrace();
            }
            
            switch (column) {
                case 0:
                    System.out.println("RemarcarCeldas: Contenido de Matricula: " + matricula.toString());
                    setText(matricula.getContenido().getNombre_cas());
                    break;

                case 1:
                    setText(c.getAbreviatura() + " - " + c.getIdPadre());
                    setToolTipText(c.getAbreviatura() + " - " + c.getIdPadre());
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
                        setText(c.getAbreviatura() + " - " + c.getIdPadre());
                    } else {
                        setText("");
                    }
                    break;
                case 4:
                    setText(cursoPendiente.getAbreviatura() + " - " + cursoPendiente.getIdPadre());
                    setToolTipText(cursoPendiente.getAbreviatura() + " - " + cursoPendiente.getIdPadre());
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
                    setText(curso.getAbreviatura() + " - " + curso.getIdPadre());
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
}
