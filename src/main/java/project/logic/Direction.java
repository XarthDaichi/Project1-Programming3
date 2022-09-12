package project.logic;

public class Direction {
    private String province;
    private String region;
    private String district;
    private String extra_information;

    public Direction(String province, String region, String district, String extra_information) {
        this.province = province;
        this.region = region;
        this.district = district;
        this.extra_information = extra_information;
    }

    public Direction() {
        this("","","","");
    }

    public String get_province() {
        return province;
    }

    public void set_province(String province) {
        this.province = province;
    }

    public String get_region() {
        return region;
    }

    public void set_region(String region) {
        this.region = region;
    }

    public String get_district() {
        return district;
    }

    public void set_district(String district) {
        this.district = district;
    }

    public String get_extra_information() {
        return extra_information;
    }

    public void set_extra_information(String extra_information) {
        this.extra_information = extra_information;
    }

    @Override
    public String toString() {
        return "Direction{" +
                "province='" + province + '\'' +
                ", region='" + region + '\'' +
                ", district='" + district + '\'' +
                ", extra_information='" + extra_information + '\'' +
                '}';
    }
}
