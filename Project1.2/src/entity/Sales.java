package entity;

public class Sales {
    private int batch;
    private int quantity;
    private float commission;

    public Sales() {
    }

    public Sales(int batch, int quantity) {
        this.batch = batch;
        this.quantity = quantity;
    }

    public int getBatch() {
        return batch;
    }

    public void setBatch(int batch) {
        this.batch = batch;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getCommission() {
        return commission;
    }

    public void setCommission(float commission) {
        this.commission = commission;
    }
}
