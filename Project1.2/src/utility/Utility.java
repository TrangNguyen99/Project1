package utility;

import entity.BinaryTree;
import entity.Node;

public class Utility {
    public static Node findNode(String id, Node root) {
        if (root == null) {
            return null;
        } else if (id.equals(root.getContent().getId())) {
            return root;
        } else {
            Node leftResult = findNode(id, root.getLeft());
            Node rightResult = findNode(id, root.getRight());
            if (leftResult != null) {
                return leftResult;
            }
            return rightResult;
        }
    }

    public static void computeIndexDraw(Node node) {
        if (node != null) {
            computeIndexDraw(node.getLeft());
            BinaryTree.countIndexDraw++;
            node.setIndexDraw(BinaryTree.countIndexDraw);
            computeIndexDraw(node.getRight());
        }
    }
}
