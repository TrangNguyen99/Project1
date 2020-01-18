package view;

import controller.NodeLogic;
import dao.DistributorDAO;
import entity.BinaryTree;
import entity.Node;
import entity.Sales;
import utility.Utility;

import javax.swing.*;
import java.awt.*;

public class DeleteDistributorUI extends JFrame {
    private static DeleteDistributorUI frame;
    private JTextField textFieldID;
    private JTextField textFieldName;
    private JTextField textFieldParentID;
    private JTextField textFieldQuantity;
    private JTextField textFieldCommission;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                frame = new DeleteDistributorUI();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private DeleteDistributorUI() {
        setTitle("Delete Distributor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setLocationRelativeTo(null);

        setLocation(400, 100);

        var pane = getContentPane();
        var g = new GroupLayout(pane);
        pane.setLayout(g);

        g.setAutoCreateContainerGaps(true);
        g.setAutoCreateGaps(true);

        JLabel labelTitle = new JLabel("Please enter the distributor id you want to delete!");
        JLabel labelID = new JLabel("ID");
        JLabel labelName = new JLabel("Name");
        JLabel labelParentID = new JLabel("ParentID");
        JLabel labelQuantity = new JLabel("Quantity");
        JLabel labelCommission = new JLabel("Commission");

        JSeparator separator = new JSeparator();

        textFieldID = new JTextField(10);
        textFieldName = new JTextField(10);
        textFieldName.setEditable(false);
        textFieldParentID = new JTextField(10);
        textFieldParentID.setEditable(false);
        textFieldQuantity = new JTextField(10);
        textFieldQuantity.setEditable(false);
        textFieldCommission = new JTextField(10);
        textFieldCommission.setEditable(false);

        JButton buttonViewInfo = new JButton("View Info");
        buttonViewInfo.addActionListener(actionEvent -> {
            String id = textFieldID.getText();
            if (id.equals("")) {
                JOptionPane.showMessageDialog(DeleteDistributorUI.this, "Please enter ID!");
                return;
            }

            Node node = Utility.findNode(id, BinaryTree.root);
            if (BinaryTree.root != null && node == null) {
                JOptionPane.showMessageDialog(DeleteDistributorUI.this, "The ID didn't exist!");
                return;
            }

            Sales sales = node.getContent().getListSales()
                    .get(node.getContent().getListSales().size() - 1);
            if (node.getParent() != null) {
                textFieldParentID.setText(node.getParent().getContent().getId());
            } else {
                textFieldParentID.setText("");
            }
            textFieldName.setText(node.getContent().getName());
            textFieldQuantity.setText(String.valueOf(sales.getQuantity()));
            textFieldCommission.setText(String.valueOf(sales.getCommission()));
        });

        JButton buttonDelete = new JButton("Delete");
        buttonDelete.addActionListener(actionEvent -> {
            String id = textFieldID.getText();
            if (id.equals("")) {
                JOptionPane.showMessageDialog(DeleteDistributorUI.this, "Please enter ID!");
                return;
            }

            Node node = Utility.findNode(id, BinaryTree.root);
            if (BinaryTree.root != null && node == null) {
                JOptionPane.showMessageDialog(DeleteDistributorUI.this, "The ID didn't exist!");
                return;
            }

            boolean status = DistributorDAO.delete(node);
            if (status) {
                JOptionPane.showMessageDialog(DeleteDistributorUI.this, "Distributor deleted successfully!");
                textFieldID.setText("");
                textFieldName.setText("");
                textFieldParentID.setText("");
                textFieldQuantity.setText("");
                textFieldCommission.setText("");
            } else {
                JOptionPane.showMessageDialog(DeleteDistributorUI.this, "Sorry, unable to delete!");
            }

            if (node.getParent() == null && node.getLeft() == null && node.getRight() == null) {
                node = null;
            } else {
                NodeLogic.delete(node);
            }

            DistributorDAO.updateAfterDelete(BinaryTree.root);
        });

        JButton buttonBack = new JButton("Back");
        buttonBack.addActionListener(actionEvent -> {
            Menu.main(new String[]{});
            frame.dispose();
        });

        g.setHorizontalGroup(g.createParallelGroup()
                .addComponent(labelTitle)
                .addGroup(g.createSequentialGroup()
                        .addComponent(labelID)
                        .addComponent(textFieldID)
                        .addComponent(buttonViewInfo)
                )
                .addComponent(separator)
                .addGroup(g.createSequentialGroup()
                        .addGroup(g.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(labelName)
                                .addComponent(labelParentID)
                                .addComponent(labelQuantity)
                                .addComponent(labelCommission)
                        )
                        .addGroup(g.createParallelGroup()
                                .addComponent(textFieldName)
                                .addComponent(textFieldParentID)
                                .addComponent(textFieldQuantity)
                                .addComponent(textFieldCommission)
                        )
                )
                .addGroup(g.createSequentialGroup()
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE,
                                Short.MAX_VALUE)
                        .addComponent(buttonDelete)
                        .addComponent(buttonBack)
                )
        );

        g.setVerticalGroup(g.createSequentialGroup()
                .addComponent(labelTitle)
                .addGroup(g.createParallelGroup()
                        .addComponent(labelID)
                        .addComponent(textFieldID)
                        .addComponent(buttonViewInfo)
                )
                .addComponent(separator)
                .addGroup(g.createSequentialGroup()
                        .addGroup(g.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(labelName)
                                .addComponent(textFieldName)
                        )
                        .addGroup(g.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(labelParentID)
                                .addComponent(textFieldParentID)
                        )
                        .addGroup(g.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(labelQuantity)
                                .addComponent(textFieldQuantity)
                        )
                        .addGroup(g.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(labelCommission)
                                .addComponent(textFieldCommission)
                        )
                )
                .addGroup(g.createParallelGroup()
                        .addComponent(buttonDelete)
                        .addComponent(buttonBack)
                )
        );

        pack();
    }
}
