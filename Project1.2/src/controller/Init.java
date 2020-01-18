package controller;

import dao.ConnectDatabase;
import entity.BinaryTree;
import entity.Node;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Init {
    public static void init() {
        try {
            Connection con = ConnectDatabase.getConnection();

            PreparedStatement ps = con.prepareStatement("SELECT * FROM Distributor WHERE depth = 0");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Node root = NodeLogic.createRoot(rs.getString("id"), rs.getString("name"));
                new BinaryTree(root);

                PreparedStatement ps1 = con.prepareStatement("SELECT * FROM Distributor WHERE depth > 0 ORDER BY depth");
                ResultSet rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    NodeLogic.insert(rs1.getString("id"), rs1.getString("name"), rs1.getString("parent_id"));
                }

                PreparedStatement ps2 = con.prepareStatement("SELECT * FROM Sales WHERE batch >= ALL " +
                        "(SELECT batch FROM Sales)");
                ResultSet rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    SalesLogic.initInfo(rs2.getString("id"), rs2.getInt("batch"), rs2.getInt("quantity"),
                            rs2.getFloat("commission"));
                }
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
