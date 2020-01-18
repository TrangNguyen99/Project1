package controller;

import entity.BinaryTree;
import entity.Node;
import entity.Sales;
import utility.Utility;

public class SalesLogic {
    static void initInfo(String id, int batch, int quantity, float commission) {
        Sales sales = new Sales(batch, quantity);
        sales.setCommission(commission);
        Node node = Utility.findNode(id, BinaryTree.root);
        node.getContent().getListSales().add(sales);
    }

    public static void addInfo(String id, int batch, int quantity) {
        Sales sales = new Sales(batch, quantity);
        Node node = Utility.findNode(id, BinaryTree.root);
        node.getContent().getListSales().add(sales);
    }

    private static void computeCommission(Node node) {
        Sales sales = node.getContent().getListSales()
                .get(node.getContent().getListSales().size() - 1);

        if (node.getLeft() == null || node.getRight() == null) {
            sales.setCommission(sales.getQuantity() / 10f);
        } else {
            Sales salesLeft = node.getLeft().getContent().getListSales()
                    .get(node.getLeft().getContent().getListSales().size() - 1);
            Sales salesRight = node.getRight().getContent().getListSales()
                    .get(node.getRight().getContent().getListSales().size() - 1);

            int quantityLeft = salesLeft.getQuantity();
            int quantityRight = salesRight.getQuantity();
            float check1 = 1f * quantityLeft / quantityRight;
            float check2 = 1f * quantityRight / quantityLeft;

            if ((1 >= check1 && check1 >= 0.9) || (1 >= check2 && check2 >= 0.9)) {
                sales.setCommission(sales.getQuantity() / 10f +
                        salesLeft.getCommission() / 10 + salesRight.getCommission() / 10);
            } else {
                if (check1 > 1) {
                    sales.setCommission(sales.getQuantity() / 10f +
                            salesRight.getCommission() / 10);
                } else {
                    sales.setCommission(sales.getQuantity() / 10f +
                            salesLeft.getCommission() / 10);
                }
            }
        }
    }

    public static void computeAllCommission(Node node) {
        if (node != null) {
            computeAllCommission(node.getLeft());
            computeAllCommission(node.getRight());
            computeCommission(node);
        }
    }
}
