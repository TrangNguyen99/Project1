package dao;

import entity.Node;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class SalesDAO {
    private static void _insert(Node node) {
        try {
            Connection con = ConnectDatabase.getConnection();

            PreparedStatement ps = con.prepareStatement("INSERT INTO Sales VALUES(?, ?, ?, ?)");
            ps.setString(1, node.getContent().getId());
            ps.setInt(2, node.getContent().getListSales()
                    .get(node.getContent().getListSales().size() - 1).getBatch());
            ps.setInt(3, node.getContent().getListSales()
                    .get(node.getContent().getListSales().size() - 1).getQuantity());
            ps.setFloat(4, node.getContent().getListSales()
                    .get(node.getContent().getListSales().size() - 1).getCommission());
            ps.executeUpdate();

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insert(Node node) {
        if (node != null) {
            insert(node.getLeft());
            insert(node.getRight());
            _insert(node);
        }
    }
}
