package view;

import controller.Init;

import javax.swing.*;
import java.awt.*;

import static javax.swing.LayoutStyle.ComponentPlacement.RELATED;

public class Start extends JFrame {
    private static Start frame;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                frame = new Start();
                frame.setVisible(true);
                frame.setBounds(400, 100, 400, 200);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private Start() {
        setTitle("Start");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setLocationRelativeTo(null);

        var pane = getContentPane();
        var g = new GroupLayout(pane);
        pane.setLayout(g);

        g.setAutoCreateContainerGaps(true);
        g.setAutoCreateGaps(true);

        JLabel labelTittle = new JLabel("Multi-level Sales");

        JButton buttonStart = new JButton("Start");
        buttonStart.addActionListener(actionEvent -> {
            Init.init();
            Menu.main(new String[]{});
            frame.dispose();
        });

        g.setHorizontalGroup(g.createParallelGroup()
                .addComponent(labelTittle, GroupLayout.Alignment.CENTER)
                .addGroup(g.createSequentialGroup()
                        .addPreferredGap(RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonStart))
        );

        g.setVerticalGroup(g.createSequentialGroup()
                .addComponent(labelTittle)
                .addPreferredGap(RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonStart)
        );

        pack();
    }
}
