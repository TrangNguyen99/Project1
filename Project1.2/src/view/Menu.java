package view;

import javax.swing.*;
import java.awt.*;

public class Menu extends JFrame {
    private static Menu frame;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                frame = new Menu();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private Menu() {
        setTitle("Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setLocationRelativeTo(null);

        setLocation(400, 100);

        var pane = getContentPane();
        var g = new GroupLayout(pane);
        pane.setLayout(g);

        g.setAutoCreateContainerGaps(true);
        g.setAutoCreateGaps(true);

        JLabel labelTittle = new JLabel("Menu");

        JSeparator separator = new JSeparator();

        JButton buttonAddDistributor = new JButton("Add Distributor");
        buttonAddDistributor.addActionListener(actionEvent -> {
            AddDistributorUI.main(new String[]{});
            frame.dispose();
        });

        JButton buttonDeleteDistributor = new JButton("Delete Distributor");
        buttonDeleteDistributor.addActionListener(actionEvent -> {
            DeleteDistributorUI.main(new String[]{});
            frame.dispose();
        });

        JButton buttonSales = new JButton("Sales");
        buttonSales.addActionListener(actionEvent -> {
            SalesUI.main(new String[]{});
            frame.dispose();
        });

        JButton buttonView = new JButton("Visual view");
        buttonView.addActionListener(actionEvent -> {
            VisualView.main(new String[]{});
            //frame.dispose();
        });

        g.setHorizontalGroup(g.createParallelGroup()
                .addComponent(labelTittle)
                .addComponent(separator)
                .addGroup(g.createParallelGroup()
                        .addComponent(buttonAddDistributor)
                        .addComponent(buttonDeleteDistributor)
                        .addComponent(buttonSales)
                        .addComponent(buttonView)
                )
        );

        g.setVerticalGroup(g.createSequentialGroup()
                .addComponent(labelTittle)
                .addComponent(separator)
                .addComponent(buttonAddDistributor)
                .addComponent(buttonDeleteDistributor)
                .addComponent(buttonSales)
                .addComponent(buttonView)
        );

        g.linkSize(SwingConstants.HORIZONTAL, buttonAddDistributor, buttonDeleteDistributor, buttonSales,
                buttonView);

        pack();
    }
}
