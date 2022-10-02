package project.logic;

import jakarta.xml.bind.annotation.XmlID;
import jakarta.xml.bind.annotation.XmlIDREF;

public class Employee {
    @XmlID
    String id;
    String name;
    String phone;
    double base_salary;
    double total_salary;
    @XmlIDREF
    Branch_Office work_place;

    public Employee(String id, String name, String phone, double base_salary, double total_salary, Branch_Office work_place) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.base_salary = base_salary;
        this.total_salary = total_salary;
        this.work_place = work_place;
    }

    public Employee() {
        this("","","",0.0,0.00, new Branch_Office());
    }

    public double getTotal_salary() {
        return total_salary;
    }

    public void setTotal_salary(double total_salary) {
        this.total_salary = total_salary;
    }

    public String get_id() {
        return id;
    }

    public void set_id(String id) {
        this.id = id;
    }

    public String get_name() {
        return name;
    }

    public void set_name(String name) {
        this.name = name;
    }

    public String get_phone() {
        return phone;
    }

    public void set_phone(String phone) {
        this.phone = phone;
    }

    public double get_base_salary() {
        return base_salary;
    }

    public void set_base_salary(double base_salary) {
        this.base_salary = base_salary;
    }

    public Branch_Office get_work_place() {
        return work_place;
    }

    public void set_work_place(Branch_Office work_place) {
        this.work_place = work_place;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", base_salary=" + base_salary +
                ", work_place=" + work_place.get_reference() +
                '}';
    }
}
