package project.presentation.employees;

import project.logic.Employee;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class TableModel extends AbstractTableModel implements javax.swing.table.TableModel {
    ArrayList<Employee> rows;
    int[] columns;

    public TableModel(int[] columns, ArrayList<Employee> rows) {
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

    public Class<?> getColumnClass(int column) {
        switch(columns[column]) {
            default: return super.getColumnClass(column);
        }
    }

    public int getRowCount() {
        return rows.size();
    }

    public Object getValueAt(int row, int column) {
        Employee employee = rows.get(row);
        switch (columns[column]) {
            case ID:
                return employee.get_id();
            case NAME:
                return employee.get_name();
            case PHONE:
                return employee.get_phone();
            case BASE_SALARY:
                return employee.get_base_salary();
            case WORKPLACE:
                return employee.get_work_place().get_reference();
            case ZONAGE:
                return employee.get_work_place().get_zonage_percentage();
            case TOTAL_SALARY:
                return employee.getTotal_salary();
            default:
                return "";
        }
    }

    public static final int ID = 0;
    public static final int NAME = 1;
    public static final int PHONE = 2;
    public static final int BASE_SALARY = 3;
    public static final int WORKPLACE = 4;
    public static final int ZONAGE = 5;
    public static final int TOTAL_SALARY = 6;

    String[] columnNames = new String[7];

    private void initColumnNames() {
        columnNames[ID] = "Id";
        columnNames[NAME] = "Name";
        columnNames[PHONE] = "Phone";
        columnNames[BASE_SALARY] = "Base_Salary";
        columnNames[WORKPLACE] = "Work Place";
        columnNames[ZONAGE] = "Zonage";
        columnNames[TOTAL_SALARY] = "Total Salary";
    }
}
