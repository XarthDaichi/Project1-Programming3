package project.data;

import project.logic.Branch_Office;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Branch_Office_Dao {
    Database db;
    public Branch_Office_Dao() {
        db = Database.instance();
    }

    public void create(Branch_Office b) throws Exception {
        String sql = "insert into " +
                "Branch_Office " +
                "((code, reference, zonage_percentage, x, y) " +
                "values(?,?,?,?,?)";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, b.get_code());
        stm.setString(2, b.get_reference());
        stm.setDouble(3, b.get_zonage_percentage());
        stm.setInt(4, b.getX());
        stm.setInt(5, b.getY());

        db.executeUpdate(stm);
    }

    public Branch_Office read(String code) throws Exception {
        String sql = "select " +
                "* " +
                "from  Branch_Office b " +
                "where b.code=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, code);
        ResultSet rs = db.executeQuery(stm);
        if (rs.next()) {
            return from(rs, "b");
        } else {
            throw new Exception("BRANCH OFFICE DOES NOT EXIST");
        }
    }

    public void update(Branch_Office b) throws Exception {
        String sql = "update " +
                "Branch_Office " +
                "set reference=?, zonage_percentage=?, x=?, y=? " +
                "where code=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, b.get_reference());
        stm.setDouble(2, b.get_zonage_percentage());
        stm.setInt(3, b.getX());
        stm.setInt(4, b.getY());
        stm.setString(5, b.get_code());
        int count = db.executeUpdate(stm);
        if (count == 0) {
            throw new Exception("BRANCH OFFICE DOES NOT EXIST");
        }
    }

    public void delete(Branch_Office b) throws Exception {
        String sql = "delete " +
                "from Branch_Office " +
                "where code=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, b.get_code());
        int count = db.executeUpdate(stm);
        if (count == 0) {
            throw new Exception("BRANCH OFFICE DOES NOT EXIST");
        }
    }

    public ArrayList<Branch_Office> find_by_reference(String reference) throws Exception {
        ArrayList<Branch_Office> result = new ArrayList<Branch_Office>();
        String sql = "select * " +
                "from " +
                "branch_office s " +
                "where s.reference like ?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, "%" + reference + "%");
        ResultSet rs = db.executeQuery(stm);
        while (rs.next()) {
            result.add(from(rs, "s"));
        }
        return result;
    }

    public Branch_Office from(ResultSet rs, String alias) throws Exception {
        Branch_Office e = new Branch_Office();
        e.set_code(rs.getString(alias + ".code"));
        e.set_reference(rs.getString(alias + ".reference"));
        e.set_zonage_percentage(rs.getDouble(alias + ".zonage_percentage"));
        e.setX(rs.getInt(alias + ".x"));
        e.setY(rs.getInt(alias + ".y"));
        return e;
    }
}
