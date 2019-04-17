
/**
 * Core SWING Advanced Programming By Kim Topley ISBN: 0 13 083292 8 Publisher:
 * Prentice Hall
 */

import Utilidades.Imagenes;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;
import java.util.EventObject;

import javax.swing.CellEditor;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.EventListenerList;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class UpdatableHighlightCurrencyTable {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception evt) {
        }

        JFrame f = new JFrame("Updatable Highlighted Currency Table");

        JTable tbl = new JTable(new TestUpdatableCurrencyTableModel());
        tbl.setDefaultRenderer(java.lang.Number.class,
                new FractionCellRenderer(10, 3, SwingConstants.RIGHT));

        
        
        
        TableColumnModel tcm = tbl.getColumnModel();
        tcm.getColumn(0).setPreferredWidth(150);
        tcm.getColumn(0).setMinWidth(150);
        TextWithIconCellRenderer renderer = new TextWithIconCellRenderer();
        tcm.getColumn(0).setCellRenderer(renderer);
        tbl.setShowHorizontalLines(false);
        tbl.setIntercellSpacing(new Dimension(1, 0));

        // Add the stripe renderer in the leftmost four columns.
        StripedTableCellRenderer.installInColumn(tbl, 0, Color.lightGray,
                Color.white, null, null);
        StripedTableCellRenderer.installInColumn(tbl, 1, Color.lightGray,
                Color.white, null, null);
        StripedTableCellRenderer.installInColumn(tbl, 2, Color.lightGray,
                Color.white, null, null);
        StripedTableCellRenderer.installInColumn(tbl, 3, Color.lightGray,
                Color.white, null, null);

    // Add the highlight renderer to the difference column.
        // The following comparator makes it highlight
        // cells with negative numeric values.
        Comparator cmp = new Comparator() {
            public boolean shouldHighlight(JTable tbl, Object value, int row,
                    int column) {
                if (value instanceof Number) {
                    double columnValue = ((Number) value).doubleValue();
                    return columnValue < (double) 0.0;
                }
                return false;
            }
        };
        tcm.getColumn(3).setCellRenderer(
                new HighlightRenderer(cmp, null, Color.pink, Color.black,
                        Color.red, Color.white));

        // Install a button renderer in the last column
        
        //###CREAMOS LOS BOTONES####//
        ButtonRenderer buttonRenderer = new ButtonRenderer();
        buttonRenderer.setForeground(Color.blue);
        buttonRenderer.setBackground(Color.lightGray);
        tcm.getColumn(4).setCellRenderer(buttonRenderer);

        // Install a button editor in the last column
        TableCellEditor editor = new ButtonEditor(new JButton());
        tcm.getColumn(4).setCellEditor(editor);

        // Make the rows wide enough to take the buttons
        tbl.setRowHeight(20);

        tbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tbl.setPreferredScrollableViewportSize(tbl.getPreferredSize());

        JScrollPane sp = new JScrollPane(tbl);
        f.getContentPane().add(sp, "Center");
        f.pack();
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                System.exit(0);
            }
        });
        f.setVisible(true);
    }
}

class TestUpdatableCurrencyTableModel extends UpdatableCurrencyTableModel {

    public void updateTable(Object value, int row, int column) {
        System.out.println("Update for row " + row + " required.");
        System.out.println("Values are " + getValueAt(row, 1) + ", "
                + getValueAt(row, 2) + "; diff is " + getValueAt(row, 3));
    }
}

class FractionCellRenderer extends DefaultTableCellRenderer {

    public FractionCellRenderer(int integer, int fraction, int align) {
        this.integer = integer; // maximum integer digits
        this.fraction = fraction; // exact number of fraction digits
        this.align = align; // alignment (LEFT, CENTER, RIGHT)
    }

    protected void setValue(Object value) {
        if (value != null && value instanceof Number) {
            formatter.setMaximumIntegerDigits(integer);
            formatter.setMaximumFractionDigits(fraction);
            formatter.setMinimumFractionDigits(fraction);
            setText(formatter.format(((Number) value).doubleValue()));
        } else {
            super.setValue(value);
        }
        setHorizontalAlignment(align);
    }

    protected int integer;

    protected int fraction;

    protected int align;

    protected static NumberFormat formatter = NumberFormat.getInstance();
}

class TextWithIconCellRenderer extends DefaultTableCellRenderer {

    protected void setValue(Object value) {
        if (value instanceof DataWithIcon) {
            if (value != null) {
                DataWithIcon d = (DataWithIcon) value;
                Object dataValue = d.getData();

                setText(dataValue == null ? "" : dataValue.toString());
                setIcon(d.getIcon());
                setHorizontalTextPosition(SwingConstants.RIGHT);
                setVerticalTextPosition(SwingConstants.CENTER);
                setHorizontalAlignment(SwingConstants.LEFT);
                setVerticalAlignment(SwingConstants.CENTER);
            } else {
                setText("");
                setIcon(null);
            }
        } else {
            super.setValue(value);
        }
    }
}

class DataWithIcon {

    public DataWithIcon(Object data, Icon icon) {
        this.data = data;
        this.icon = icon;
    }

    public Icon getIcon() {
        return icon;
    }

    public Object getData() {
        return data;
    }

    public String toString() {
        return data.toString();
    }

    protected Icon icon;

    protected Object data;
}

class StripedTableCellRenderer implements TableCellRenderer {

    public StripedTableCellRenderer(TableCellRenderer targetRenderer,
            Color evenBack, Color evenFore, Color oddBack, Color oddFore) {
        this.targetRenderer = targetRenderer;
        this.evenBack = evenBack;
        this.evenFore = evenFore;
        this.oddBack = oddBack;
        this.oddFore = oddFore;
    }

    // Implementation of TableCellRenderer interface
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        TableCellRenderer renderer = targetRenderer;
        if (renderer == null) {
            // Get default renderer from the table
            renderer = table.getDefaultRenderer(table.getColumnClass(column));
        }

        // Let the real renderer create the component
        Component comp = renderer.getTableCellRendererComponent(table, value,
                isSelected, hasFocus, row, column);

        // Now apply the stripe effect
        if (isSelected == false && hasFocus == false) {
            if ((row & 1) == 0) {
                comp.setBackground(evenBack != null ? evenBack : table
                        .getBackground());
                comp.setForeground(evenFore != null ? evenFore : table
                        .getForeground());
            } else {
                comp.setBackground(oddBack != null ? oddBack : table
                        .getBackground());
                comp.setForeground(oddFore != null ? oddFore : table
                        .getForeground());
            }
        }

        return comp;
    }

    // Convenience method to apply this renderer to single column
    public static void installInColumn(JTable table, int columnIndex,
            Color evenBack, Color evenFore, Color oddBack, Color oddFore) {
        TableColumn tc = table.getColumnModel().getColumn(columnIndex);

        // Get the cell renderer for this column, if any
        TableCellRenderer targetRenderer = tc.getCellRenderer();

        // Create a new StripedTableCellRenderer and install it
        tc.setCellRenderer(new StripedTableCellRenderer(targetRenderer,
                evenBack, evenFore, oddBack, oddFore));
    }

    // Convenience method to apply this renderer to an entire table
    public static void installInTable(JTable table, Color evenBack,
            Color evenFore, Color oddBack, Color oddFore) {
        StripedTableCellRenderer sharedInstance = null;
        int columns = table.getColumnCount();
        for (int i = 0; i < columns; i++) {
            TableColumn tc = table.getColumnModel().getColumn(i);
            TableCellRenderer targetRenderer = tc.getCellRenderer();
            if (targetRenderer != null) {
                // This column has a specific renderer
                tc.setCellRenderer(new StripedTableCellRenderer(targetRenderer,
                        evenBack, evenFore, oddBack, oddFore));
            } else {
                // This column uses a class renderer - use a shared renderer
                if (sharedInstance == null) {
                    sharedInstance = new StripedTableCellRenderer(null,
                            evenBack, evenFore, oddBack, oddFore);
                }
                tc.setCellRenderer(sharedInstance);
            }
        }
    }

    protected TableCellRenderer targetRenderer;

    protected Color evenBack;

    protected Color evenFore;

    protected Color oddBack;

    protected Color oddFore;
}

class HighlightRenderer implements TableCellRenderer {

    public HighlightRenderer(Comparator cmp, TableCellRenderer targetRenderer,
            Color backColor, Color foreColor, Color highlightBack,
            Color highlightFore) {
        this.cmp = cmp;
        this.targetRenderer = targetRenderer;
        this.backColor = backColor;
        this.foreColor = foreColor;
        this.highlightBack = highlightBack;
        this.highlightFore = highlightFore;
    }

    public Component getTableCellRendererComponent(JTable tbl, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        TableCellRenderer renderer = targetRenderer;
        if (renderer == null) {
            renderer = tbl.getDefaultRenderer(tbl.getColumnClass(column));
        }
        Component comp = renderer.getTableCellRendererComponent(tbl, value,
                isSelected, hasFocus, row, column);
        if (isSelected == false && hasFocus == false && value != null) {
            if (cmp.shouldHighlight(tbl, value, row, column)) {
                comp.setForeground(highlightFore);
                comp.setBackground(highlightBack);
            } else {
                comp.setForeground(foreColor);
                comp.setBackground(backColor);
            }
        }

        return comp;
    }

    protected Comparator cmp;

    protected TableCellRenderer targetRenderer;

    protected Color backColor;

    protected Color foreColor;

    protected Color highlightBack;

    protected Color highlightFore;
}

interface Comparator {

    public abstract boolean shouldHighlight(JTable tbl, Object value, int row,
            int column);
}

abstract class UpdatableCurrencyTableModel extends EditableCurrencyTableModel {

    public int getColumnCount() {
        return super.getColumnCount() + 1;
    }

    public Object getValueAt(int row, int column) {
        if (column == BUTTON_COLUMN) {
            return "Update";
        }
        return super.getValueAt(row, column);
    }

    public Class getColumnClass(int column) {
        if (column == BUTTON_COLUMN) {
            return String.class;
        }
        return super.getColumnClass(column);
    }

    public String getColumnName(int column) {
        if (column == BUTTON_COLUMN) {
            return "";
        }
        return super.getColumnName(column);
    }

    public boolean isCellEditable(int row, int column) {
        return column == BUTTON_COLUMN || super.isCellEditable(row, column);
    }

    public void setValueAt(Object value, int row, int column) {
        if (column == BUTTON_COLUMN) {
            // Button press - do whatever is needed to update the table source
            updateTable(value, row, column);
            return;
        }

        // Other columns - use superclass
        super.setValueAt(value, row, column);
    }

    // Used to implement the table update
    protected abstract void updateTable(Object value, int row, int column);

    protected static final int BUTTON_COLUMN = 4;
}

class BasicCellEditor implements CellEditor, PropertyChangeListener {

    public BasicCellEditor() {
        this.editor = null;
    }

    public BasicCellEditor(JComponent editor) {
        this.editor = editor;
        editor.addPropertyChangeListener(this);
    }

    public Object getCellEditorValue() {
        return null;
    }

    public boolean isCellEditable(EventObject evt) {
        editingEvent = evt;
        return true;
    }

    public boolean shouldSelectCell(EventObject evt) {
        return true;
    }

    public boolean stopCellEditing() {
        fireEditingStopped();
        return true;
    }

    public void cancelCellEditing() {
        fireEditingCanceled();
    }

    public void addCellEditorListener(CellEditorListener l) {
        listeners.add(CellEditorListener.class, l);
    }

    public void removeCellEditorListener(CellEditorListener l) {
        listeners.remove(CellEditorListener.class, l);
    }

    // Returns the editing component
    public JComponent getComponent() {
        return editor;
    }

    // Sets the editing component
    public void setComponent(JComponent comp) {
        editor = comp;
    }

    // Returns the event that triggered the edit
    public EventObject getEditingEvent() {
        return editingEvent;
    }

  // Method invoked when the editor is installed in the table.
    // Overridden in derived classes to take any convenient
    // action.
    public void editingStarted(EventObject event) {
    }

    protected void fireEditingStopped() {
        Object[] l = listeners.getListenerList();
        for (int i = l.length - 2; i >= 0; i -= 2) {
            if (l[i] == CellEditorListener.class) {
                if (changeEvent == null) {
                    changeEvent = new ChangeEvent(this);
                }
                ((CellEditorListener) l[i + 1]).editingStopped(changeEvent);
            }
        }
    }

    protected void fireEditingCanceled() {
        Object[] l = listeners.getListenerList();
        for (int i = l.length - 2; i >= 0; i -= 2) {
            if (l[i] == CellEditorListener.class) {
                if (changeEvent == null) {
                    changeEvent = new ChangeEvent(this);
                }
                ((CellEditorListener) l[i + 1]).editingCanceled(changeEvent);
            }
        }
    }

    // Implementation of the PropertyChangeListener interface
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("ancestor")
                && evt.getNewValue() != null) {
            // Added to table - notify the editor
            editingStarted(editingEvent);
        }
    }

    protected static JCheckBox checkBox = new JCheckBox();

    protected static ChangeEvent changeEvent;

    protected JComponent editor;

    protected EventListenerList listeners = new EventListenerList();

    protected EventObject editingEvent;
}

class ButtonEditor extends BasicCellEditor implements ActionListener,
        TableCellEditor {

    public ButtonEditor(JButton button) {
        super(button);
        button.addActionListener(this);
    }

    public void setForeground(Color foreground) {
        this.foreground = foreground;
        editor.setForeground(foreground);
    }

    public void setBackground(Color background) {
        this.background = background;
        editor.setBackground(background);
    }

    public void setFont(Font font) {
        this.font = font;
        editor.setFont(font);
    }

    public Object getCellEditorValue() {
        return value;
    }

    public void editingStarted(EventObject event) {
        // Edit starting - click the button if necessary
        if (!(event instanceof MouseEvent)) {
            // Keyboard event - click the button
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    ((JButton) editor).doClick();
                }
            });
        }
    }

    public Component getTableCellEditorComponent(JTable tbl, Object value,
            boolean isSelected, int row, int column) {
        editor.setForeground(foreground != null ? foreground : tbl
                .getForeground());
        editor.setBackground(background != null ? background : tbl
                .getBackground());
        editor.setFont(font != null ? font : tbl.getFont());

        this.value = value;
        setValue(value);
        return editor;
    }

    protected void setValue(Object value) {
        JButton button = (JButton) editor;
        if (value == null) {
            button.setText("");
            button.setIcon(null);
        } else if (value instanceof Icon) {
            button.setText("");
            button.setIcon((Icon) value);
        } else if (value instanceof DataWithIcon) {
            DataWithIcon d = (DataWithIcon) value;
            button.setText(d.toString());
            button.setIcon(d.getIcon());
        } else {
            button.setText(value.toString());
            button.setIcon(null);
        }
    }

    public void actionPerformed(ActionEvent evt) {
        // Button pressed - stop the edit
        stopCellEditing();
    }

    protected Object value;

    protected Color foreground;

    protected Color background;

    protected Font font;
}

class EditableCurrencyTableModel extends CurrencyTableModel {

    public boolean isCellEditable(int row, int column) {
        return column == OLD_RATE_COLUMN || column == NEW_RATE_COLUMN;
    }

    public void setValueAt(Object value, int row, int column) {
        try {
            if (column == OLD_RATE_COLUMN || column == NEW_RATE_COLUMN) {
                Double newObjectValue; // New value as an Object
                double newValue; // double, for validity checking

                if (value instanceof Number) {
                    // Convert Number to Double
                    newValue = ((Number) value).doubleValue();
                    newObjectValue = new Double(newValue);
                } else if (value instanceof String) {
                    // Convert a String to a Double
                    newObjectValue = new Double((String) value);
                    newValue = newObjectValue.doubleValue();
                } else {
                    // Unrecognized - ignore
                    return;
                }

                if (newValue > (double) 0.0) {
                    // Store new value, but reject zero or negative values
                    data[row][column] = newObjectValue;
                    data[row][DIFF_COLUMN] = new Double(
                            ((Double) data[row][NEW_RATE_COLUMN]).doubleValue()
                            - ((Double) data[row][OLD_RATE_COLUMN])
                            .doubleValue());

                    fireTableRowsUpdated(row, row);
                }
            }
        } catch (NumberFormatException e) {
            // Ignore a badly-formatted number
        }
    }
}

class CurrencyTableModel extends AbstractTableModel {

    protected String[] columnNames = {"Currency", "Yesterday", "Today",
        "Change"};

    // Constructor: calculate currency change to create the last column
    public CurrencyTableModel() {
        for (int i = 0; i < data.length; i++) {
            data[i][DIFF_COLUMN] = new Double(
                    ((Double) data[i][NEW_RATE_COLUMN]).doubleValue()
                    - ((Double) data[i][OLD_RATE_COLUMN]).doubleValue());
        }
    }

    // Implementation of TableModel interface
    public int getRowCount() {
        return data.length;
    }

    public int getColumnCount() {
        return COLUMN_COUNT;
    }

    public Object getValueAt(int row, int column) {
        return data[row][column];
    }

    public Class getColumnClass(int column) {
        return (data[0][column]).getClass();
    }

    public String getColumnName(int column) {
        return columnNames[column];
    }

    protected static final int OLD_RATE_COLUMN = 1;

    protected static final int NEW_RATE_COLUMN = 2;

    protected static final int DIFF_COLUMN = 3;

    protected static final int COLUMN_COUNT = 4;

    protected static final Class thisClass = CurrencyTableModel.class;

    protected Object[][] data = new Object[][]{
        {
            new DataWithIcon("British Pound", Imagenes.getImagen(new JFrame(), "alert-black.png")),
            new Double(37.6460110), new Double(37.6508921), null},
        {
            new DataWithIcon("British Pound", Imagenes.getImagen(new JFrame(), "alert-black.png")),
            new Double(0.6213051), new Double(0.6104102), null},
        {
            new DataWithIcon("British Pound", Imagenes.getImagen(new JFrame(), "alert-black.png")),
            new Double(1.4651209), new Double(1.5011104), null},
        {
            new DataWithIcon("British Pound", Imagenes.getImagen(new JFrame(), "alert-black.png")),
            new Double(6.1060001), new Double(6.0100101), null},
        {
            new DataWithIcon("British Pound", Imagenes.getImagen(new JFrame(), "alert-black.png")),
            new Double(1181.3668977), new Double(1182.104), null},
        {
            new DataWithIcon("British Pound", Imagenes.getImagen(new JFrame(), "alert-black.png")),
            new Double(1.8191804), new Double(1.8223421), null},
        {
            new DataWithIcon("British Pound", Imagenes.getImagen(new JFrame(), "alert-black.png")),
            new Double(141.0815412), new Double(121.0040432), null}};
}

class ButtonRenderer extends JButton implements TableCellRenderer {

    public ButtonRenderer() {
        this.border = getBorder();
        this.setOpaque(true);
    }

    public void setForeground(Color foreground) {
        this.foreground = foreground;
        super.setForeground(foreground);
    }

    public void setBackground(Color background) {
        this.background = background;
        super.setBackground(background);
    }

    public void setFont(Font font) {
        this.font = font;
        super.setFont(font);
    }

    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        Color cellForeground = foreground != null ? foreground : table
                .getForeground();
        Color cellBackground = background != null ? background : table
                .getBackground();

        setFont(font != null ? font : table.getFont());

        if (hasFocus) {
            setBorder(UIManager.getBorder("Table.focusCellHighlightBorder"));
            if (table.isCellEditable(row, column)) {
                cellForeground = UIManager
                        .getColor("Table.focusCellForeground");
                cellBackground = UIManager
                        .getColor("Table.focusCellBackground");
            }
        } else {
            setBorder(border);
        }

        super.setForeground(cellForeground);
        super.setBackground(cellBackground);

        // Customize the component's appearance
        setValue(value);

        return this;
    }

    protected void setValue(Object value) {
        if (value == null) {
            setText("");
            setIcon(null);
        } else if (value instanceof Icon) {
            setText("");
            setIcon((Icon) value);
        } else if (value instanceof DataWithIcon) {
            DataWithIcon d = (DataWithIcon) value;
            setText(d.toString());
            setIcon(d.getIcon());
        } else {
            setText(value.toString());
            setIcon(null);
        }
    }

    protected Color foreground;

    protected Color background;

    protected Font font;

    protected Border border;
}
