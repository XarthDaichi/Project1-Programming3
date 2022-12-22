package project.data;

import project.logic.Employee;
import project.logic.Branch_Office;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Employee_Dao {
    Database db;
    public Employee_Dao() {
        db = Database.instance();
    }

    public void create(Employee e) throws Exception {
        String sql = "insert into " +
                "Employee " +
                "(id, employee_name, phone, base_salary, branch_office) " +
                "values(?,?,?,?,?)";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, e.get_id());
        stm.setString(2, e.get_name());
        stm.setString(3, e.get_phone());
        stm.setDouble(4, e.get_base_salary());
        stm.setString(5, e.get_work_place().get_code());

        db.executeUpdate(stm);
    }

    public Employee read(String id) throws Exception {
        Branch_Office_Dao branch_office_dao = new Branch_Office_Dao();
        String sql = "select " +
                "* " +
                "from Employee e " +
                "  inner join Branch_Office s on e.branch_office=s.code " +
                "where e.id=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, id);
        ResultSet rs = db.executeQuery(stm);
        Employee employee;
        if (rs.next()) {
            employee = from(rs, "e");
            employee.set_work_place(branch_office_dao.from(rs, "s"));
            return employee;
        } else {
            throw new Exception("EMPLOYEE DOES NOT EXIST");
        }
    }

    public void update(Employee e) throws Exception {
        String sql = "update " +
                "Employee " +
                "set employee_name=?, phone=?, base_salary=?, branch_office=? " +
                "where id=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, e.get_name());
        stm.setString(2, e.get_phone());
        stm.setDouble(3, e.get_base_salary());
        stm.setString(4, e.get_work_place().get_code());
        stm.setString(5, e.get_id());
        int count = db.executeUpdate(stm);
        if (count == 0) {
            throw new Exception("EMPLOYEE DOES NOT EXIST");
        }
    }

    public void delete(Employee e) throws Exception {
        String sql = "delete " +
                "from Employee " +
                "where id=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, e.get_id());
        int count = db.executeUpdate(stm);
        if (count == 0) {
            throw new Exception("EMPLOYEE DOES NOT EXIST");
        }
    }

    public ArrayList<Employee> find_by_name(String name) throws Exception {
        Branch_Office_Dao branch_office_dao = new Branch_Office_Dao();
        ArrayList<Employee> result = new ArrayList<Employee>();
        String sql = "select * " +
                "from " +
                "employee e " +
                "  inner join Branch_Office s on e.branch_office=s.code " +
                "where e.employee_name like ?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, "%" + name + "%");
        ResultSet rs = db.executeQuery(stm);
        Employee employee;
        while (rs.next()) {
            employee = from(rs, "e");
            employee.set_work_place(branch_office_dao.from(rs, "s"));
            result.add(employee);
        }
        return result;
    }
    public ArrayList<Employee> find_by_id(String id) throws Exception {
        Branch_Office_Dao branch_office_dao = new Branch_Office_Dao();
        ArrayList<Employee> result = new ArrayList<Employee>();
        String sql = "select * " +
                "from " +
                "employee e " +
                "  inner join Branch_Office s on e.branch_office=s.code " +
                "where e.id like ?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, "%" + id + "%");
        ResultSet rs = db.executeQuery(stm);
        Employee employee;
        while (rs.next()) {
            employee = from(rs, "e");
            employee.set_work_place(branch_office_dao.from(rs, "s"));
            result.add(employee);
        }
        return result;
    }

    public Employee from(ResultSet rs, String alias) throws Exception {
        Employee e = new Employee();
        e.set_id(rs.getString(alias + ".id"));
        e.set_name(rs.getString(alias + ".employee_name"));
        e.set_phone(rs.getString(alias + ".phone"));
        e.set_base_salary(rs.getDouble(alias + ".base_salary"));
        return e;
    }
}
