package view;

import controller.NodeLogic;
import dao.DistributorDAO;
import entity.BinaryTree;
import entity.Node;
import utility.Utility;

import javax.swing.*;
import java.awt.*;

public class AddDistributorUI extends JFrame {
    private static AddDistributorUI frame;
    private JTextField textFieldID;
    private JTextField textFieldName;
    private JTextField textFieldParentID;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                frame = new AddDistributorUI();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private AddDistributorUI() {
        setTitle("Add Distributor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setLocationRelativeTo(null);

        setLocation(400, 100);

        var pane = getContentPane();
        var g = new GroupLayout(pane);
        pane.setLayout(g);

        g.setAutoCreateContainerGaps(true);
        g.setAutoCreateGaps(true);

        JLabel labelTitle = new JLabel("Please add information about the distributor!");
        JLabel labelID = new JLabel("ID");
        JLabel labelName = new JLabel("Name");
        JLabel labelParentID = new JLabel("ParentID");

        JSeparator separator = new JSeparator();

        textFieldID = new JTextField(10);
        textFieldName = new JTextField(10);
        textFieldParentID = new JTextField(10);

        JButton buttonAdd = new JButton("Add");
        buttonAdd.addActionListener(actionEvent -> {
            String id = textFieldID.getText();
            if (id.equals("")) {
                JOptionPane.showMessageDialog(AddDistributorUI.this, "Please enter ID!");
                return;
            }
            if (BinaryTree.root != null && Utility.findNode(id, BinaryTree.root) != null) {
                JOptionPane.showMessageDialog(AddDistributorUI.this, "The ID existed!");
                return;
            }

            String name = textFieldName.getText();
            if (name.equals("")) {
                JOptionPane.showMessageDialog(AddDistributorUI.this, "Please enter Name!");
                return;
            }

            String parentID = textFieldParentID.getText();

            Node newNode;
            if (BinaryTree.root == null) {
                JOptionPane.showMessageDialog(AddDistributorUI.this, "This is first distributor!");
                newNode = NodeLogic.createRoot(id, name);
                new BinaryTree(newNode);
            } else {
                newNode = NodeLogic.insert(id, name, parentID);
                if (newNode.getParent() == null) {
                    JOptionPane.showMessageDialog(AddDistributorUI.this, "The ParentID didn't exist!");
                    return;
                }
            }

            boolean status = DistributorDAO.insert(newNode);
            if (status) {
                JOptionPane.showMessageDialog(AddDistributorUI.this, "Distributor added successfully!");
                textFieldID.setText("");
                textFieldName.setText("");
                textFieldParentID.setText("");
            } else {
                JOptionPane.showMessageDialog(AddDistributorUI.this, "Sorry, unable to add!");
            }
        });

        JButton buttonBack = new JButton("Back");
        buttonBack.addActionListener(actionEvent -> {
            Menu.main(new String[]{});
            frame.dispose();
        });

        g.setHorizontalGroup(g.createParallelGroup()
                .addComponent(labelTitle)
                .addComponent(separator)
                .addGroup(g.createSequentialGroup()
                        .addGroup(g.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(labelID)
                                .addComponent(labelName)
                                .addComponent(labelParentID)
                        )
                        .addGroup(g.createParallelGroup()
                                .addComponent(textFieldID)
                                .addComponent(textFieldName)
                                .addComponent(textFieldParentID)
                        )
                )
                .addGroup(g.createSequentialGroup()
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE,
                                Short.MAX_VALUE)
                        .addComponent(buttonAdd)
                        .addComponent(buttonBack)
                )
        );

        g.setVerticalGroup(g.createSequentialGroup()
                .addComponent(labelTitle)
                .addComponent(separator)
                .addGroup(g.createSequentialGroup()
                        .addGroup(g.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(labelID)
                                .addComponent(textFieldID)
                        )
                        .addGroup(g.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(labelName)
                                .addComponent(textFieldName)
                        )
                        .addGroup(g.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(labelParentID)
                                .addComponent(textFieldParentID)
                        )
                )
                .addGroup(g.createParallelGroup()
                        .addComponent(buttonAdd)
                        .addComponent(buttonBack)
                )
        );

        pack();
    }
}
