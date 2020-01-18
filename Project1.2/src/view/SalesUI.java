package view;

import controller.SalesLogic;
import dao.SalesDAO;
import entity.BinaryTree;
import utility.Utility;

import javax.swing.*;
import java.awt.*;

public class SalesUI extends JFrame {
    private static SalesUI frame;
    private JTextField textFieldBatch;
    private JTextField textFieldID;
    private JTextField textFieldQuantity;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                frame = new SalesUI();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private SalesUI() {
        setTitle("Sales");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setLocationRelativeTo(null);

        setLocation(400, 100);

        var pane = getContentPane();
        var g = new GroupLayout(pane);
        pane.setLayout(g);

        g.setAutoCreateContainerGaps(true);
        g.setAutoCreateGaps(true);

        JLabel labelTitle = new JLabel("Please add sales information of the distributor!");
        JLabel labelBatch = new JLabel("Batch");
        JLabel labelID = new JLabel("ID");
        JLabel labelQuantity = new JLabel("Quantity");

        JSeparator separator = new JSeparator();

        textFieldBatch = new JTextField(10);
        textFieldID = new JTextField(10);
        textFieldQuantity = new JTextField(10);

        JButton buttonAdd = new JButton("Add");
        buttonAdd.addActionListener(actionEvent -> {
            int batch = Integer.parseInt(textFieldBatch.getText());

            String id = textFieldID.getText();
            if (id.equals("")) {
                JOptionPane.showMessageDialog(SalesUI.this, "Please enter ID!");
                return;
            }
            if (BinaryTree.root != null && Utility.findNode(id, BinaryTree.root) == null) {
                JOptionPane.showMessageDialog(SalesUI.this, "The ID didn't exist!");
                return;
            }

            int quantity = Integer.parseInt(textFieldQuantity.getText());
            if (quantity < 0) {
                JOptionPane.showMessageDialog(SalesUI.this, "Make sure that quantity >= 0!");
                return;
            }

            SalesLogic.addInfo(id, batch, quantity);

            textFieldID.setText("");
            textFieldQuantity.setText("");
        });

        JButton buttonOK = new JButton("OK");
        buttonOK.addActionListener(actionEvent -> {
            SalesLogic.computeAllCommission(BinaryTree.root);
            SalesDAO.insert(BinaryTree.root);
            Menu.main(new String[]{});
            frame.dispose();
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
                                .addComponent(labelBatch)
                                .addComponent(labelID)
                                .addComponent(labelQuantity)
                        )
                        .addGroup(g.createParallelGroup()
                                .addComponent(textFieldBatch)
                                .addComponent(textFieldID)
                                .addComponent(textFieldQuantity)
                        )
                )
                .addGroup(g.createSequentialGroup()
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE,
                                Short.MAX_VALUE)
                        .addComponent(buttonAdd)
                        .addComponent(buttonOK)
                        .addComponent(buttonBack)
                )
        );

        g.setVerticalGroup(g.createSequentialGroup()
                .addComponent(labelTitle)
                .addComponent(separator)
                .addGroup(g.createSequentialGroup()
                        .addGroup(g.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(labelBatch)
                                .addComponent(textFieldBatch)
                        )
                        .addGroup(g.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(labelID)
                                .addComponent(textFieldID)
                        )
                        .addGroup(g.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(labelQuantity)
                                .addComponent(textFieldQuantity)
                        )
                )
                .addGroup(g.createParallelGroup()
                        .addComponent(buttonAdd)
                        .addComponent(buttonOK)
                        .addComponent(buttonBack)
                )
        );

        pack();
    }
}
