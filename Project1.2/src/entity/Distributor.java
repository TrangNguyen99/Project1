package entity;

import java.util.ArrayList;

public class Distributor {
    private String id;
    private String name;
    private ArrayList<Sales> listSales;

    public Distributor() {
    }

    public Distributor(String id, String name) {
        this.id = id;
        this.name = name;
        listSales = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Sales> getListSales() {
        return listSales;
    }

    public void setListSales(ArrayList<Sales> listSales) {
        this.listSales = listSales;
    }
}
