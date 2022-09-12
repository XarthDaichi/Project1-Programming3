package project.logic;

public class Branch_Office {
    private String code;
    private String reference;
    private double zonage_percentage;
    private Direction direction;

    public Branch_Office(String code, String reference, double zonage_percentage, Direction direction) {
        this.code = code;
        this.reference = reference;
        this.zonage_percentage = zonage_percentage;
        this.direction = direction;
    }

    public Branch_Office() {
        this("","",0.0,new Direction());
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

    public Direction get_direction() {
        return direction;
    }

    public void set_direction(Direction direction) {
        this.direction = direction;
    }

    @Override
    public String toString() {
        return "Branch_Office{" +
                "code='" + code + '\'' +
                ", reference='" + reference + '\'' +
                ", zonage_percentage=" + zonage_percentage +
                ", direction=" + direction.toString() +
                '}';
    }
}
