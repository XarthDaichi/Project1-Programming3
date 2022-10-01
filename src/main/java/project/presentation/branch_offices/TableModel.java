package project.presentation.branch_offices;

import project.logic.Branch_Office;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class TableModel extends AbstractTableModel implements javax.swing.table.TableModel {
    ArrayList<Branch_Office> rows;
    int[] columns;

    public TableModel(int[] columns, ArrayList<Branch_Office> rows) {
        initColumnNames();
        this.columns=columns;
        this.rows=rows;
    }

    public int getColumnCount() {
        return columns.length;
    }

    public String getColumnName(int column) {
        return columnNames[columns[column]];
    }

    public int getRowCount() {
        return rows.size();
    }

    public Object getValueAt(int row, int column) {
        Branch_Office branch_office = rows.get(row);
        switch(columns[column]) {
            case CODE:
                return branch_office.get_code();
            case REFERENCE:
                return branch_office.get_reference();
            case ZONAGEPERCENTAGE:
                return branch_office.get_zonage_percentage();
            case DIRECTION:
                return branch_office.get_reference();
            default:
                return "";
        }
    }

    public static final int CODE = 0;
    public static final int REFERENCE = 1;
    public static final int ZONAGEPERCENTAGE = 2;
    public static final int DIRECTION = 3;

    String[] columnNames = new String[4];

    private void initColumnNames() {
        columnNames[CODE] = "Code";
        columnNames[REFERENCE] = "Reference";
        columnNames[ZONAGEPERCENTAGE] = "Zonage Percentage";
        columnNames[DIRECTION] = "Direction";
    }
}
