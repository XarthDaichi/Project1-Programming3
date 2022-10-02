package project.logic;
import jakarta.xml.bind.annotation.XmlID;

import java.awt.Point;
import javax.swing.*;

public class Branch_Office {
    @XmlID
    private String code;
    private String reference;
    private double zonage_percentage;
    private int x;
    private int y;
    //private Direction direction;

    public Branch_Office(String code, String reference, double zonage_percentage, int x, int y) {
        this.code = code;
        this.reference = reference;
        this.zonage_percentage = zonage_percentage;
        this.x = x;
        this.y = y;
    }

    public Branch_Office() {
        this("","",0.0,0,0);
    }

    public Branch_Office(int x, int y) {
        this("","",0.0,x,y);
    }

    public String get_code() {
        return code;
    }

    public void set_code(String code) {
        this.code = code;
    }

    public String get_reference() {
        return reference;
    }

    public void set_reference(String reference) {
        this.reference = reference;
    }

    public double get_zonage_percentage() {
        return zonage_percentage;
    }

    public void set_zonage_percentage(double zonage_percentage) {
        this.zonage_percentage = zonage_percentage;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Branch_Office{" +
                "code='" + code + '\'' +
                ", reference='" + reference + '\'' +
                ", zonage_percentage=" + zonage_percentage +
                ", direction=" + "(" + x + ", " + y + ")" +
                '}';
    }
}
