package controller;

import entity.BinaryTree;
import entity.Distributor;
import entity.Node;
import entity.Sales;
import utility.Utility;

public class NodeLogic {
    public static Node createRoot(String id, String name) {
        Node node = new Node();
        node.setContent(new Distributor(id, name));
        node.setDepth(0);
        return node;
    }

    public static Node insert(String id, String name, String parentID) {
        Node node = new Node();
        node.setContent(new Distributor(id, name));
        Node tempParentNode = Utility.findNode(parentID, BinaryTree.root);
        node.setParent(findTrueParent(tempParentNode));
        if (node.getParent() != null) {
            if (node.getParent().getLeft() == null) {
                node.getParent().setLeft(node);
            } else {
                node.getParent().setRight(node);
            }
            node.setDepth(node.getParent().getDepth() + 1);
        }
        return node;
    }

    private static Node findTrueParent(Node tempParentNode) {
        if (tempParentNode == null) {
            return null;
        } else if (tempParentNode.getLeft() == null || tempParentNode.getRight() == null) {
            return tempParentNode;
        } else {
            Node leftResult = findTrueParent(tempParentNode.getLeft());
            Node rightResult = findTrueParent(tempParentNode.getRight());
            return leftResult.getDepth() <= rightResult.getDepth() ? leftResult : rightResult;
        }
    }

    public static void delete(Node node) {
        if (node.getLeft() == null && node.getRight() == null) {
            boolean isLeftChild = true;
            if (node.getParent().getRight() != null && node.getContent().getId()
                    .equals(node.getParent().getRight().getContent().getId())) {
                isLeftChild = false;
            }

            if (isLeftChild) {
                node.getParent().setLeft(null);
            } else {
                node.getParent().setRight(null);
            }
        } else if (node.getLeft() != null && node.getRight() == null) {
            node.setContent(node.getLeft().getContent());
            delete(node.getLeft());
        } else if (node.getLeft() == null && node.getRight() != null) {
            node.setContent(node.getRight().getContent());
            delete(node.getRight());
        } else {
            Node leftNode = node.getLeft();
            Sales leftSales = leftNode.getContent().getListSales()
                    .get(leftNode.getContent().getListSales().size() - 1);
            Node rightNode = node.getRight();
            Sales rightSales = rightNode.getContent().getListSales()
                    .get(rightNode.getContent().getListSales().size() - 1);
            boolean choseLeft = true;
            if (leftSales.getCommission() < rightSales.getCommission()) {
                choseLeft = false;
            } else if (leftSales.getCommission() == rightSales.getCommission()) {
                if (leftSales.getQuantity() < rightSales.getQuantity()) {
                    choseLeft = false;
                }
            }

            if (choseLeft) {
                node.setContent(node.getLeft().getContent());
                delete(node.getLeft());
            } else {
                node.setContent(node.getRight().getContent());
                delete(node.getRight());
            }
        }
    }
}
