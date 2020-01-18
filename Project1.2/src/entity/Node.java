package entity;

public class Node {
    private Distributor content;
    private Node parent, left, right;
    private int depth;
    private int indexDraw;

    public Distributor getContent() {
        return content;
    }

    public void setContent(Distributor content) {
        this.content = content;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getIndexDraw() {
        return indexDraw;
    }

    public void setIndexDraw(int indexDraw) {
        this.indexDraw = indexDraw;
    }
}
