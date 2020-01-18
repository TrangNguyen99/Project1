package dao;

import entity.Node;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class DistributorDAO {
    public static boolean insert(Node newNode) {
        int status = 0;
        try {
            Connection con = ConnectDatabase.getConnection();

            PreparedStatement ps = con.prepareStatement("INSERT INTO Distributor VALUES(?, ?, ?, ?)");
            ps.setString(1, newNode.getContent().getId());
            ps.setString(2, newNode.getContent().getName());
            if (newNode.getParent() != null) {
                ps.setString(3, newNode.getParent().getContent().getId());
            } else {
                ps.setString(3, "");
            }
            ps.setInt(4, newNode.getDepth());
            status = ps.executeUpdate();

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status != 0;
    }

    public static boolean delete(Node node) {
        int status = 0;
        try {
            Connection con = ConnectDatabase.getConnection();

            PreparedStatement ps = con.prepareStatement("UPDATE Distributor SET depth = -1 WHERE id = ?");
            ps.setString(1, node.getContent().getId());
            status = ps.executeUpdate();

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status != 0;
    }

    private static void _updateAfterDelete(Node node) {
        try {
            Connection con = ConnectDatabase.getConnection();

            PreparedStatement ps = con.prepareStatement("UPDATE Distributor SET parent_id = ?, depth = ? WHERE id = ?");
            if (node.getParent() != null) {
                ps.setString(1, node.getParent().getContent().getId());
            } else {
                ps.setString(1, "");
            }
            ps.setInt(2, node.getDepth());
            ps.setString(3, node.getContent().getId());
            ps.executeUpdate();

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateAfterDelete(Node node) {
        if (node != null) {
            _updateAfterDelete(node);
            updateAfterDelete(node.getLeft());
            updateAfterDelete(node.getRight());
        }
    }
}
