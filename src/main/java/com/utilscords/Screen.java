package com.utilscords;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Paths;

/**
 * Create the swing screen
 *
 */
public class Screen {


    public static void createUIComponents(Finder finder) {
        //Custom component creation code here
        //Create and set up the window.
        JFrame frame = new JFrame("Finder");
        frame.setSize(500,400);
        frame.setLocationRelativeTo(null);


        JFrame frameResult = new JFrame("Result");
        frameResult.setSize(700,400);
        frameResult.setLocationRelativeTo(null);

        Integer screenY = 10;
        Integer fieldDistanceY = 45;


        JTextField textToFindField = new JTextField();
        textToFindField.setBounds(70, screenY, 200, 30);
        JLabel textToFindFieldLabel = new JLabel("Text");
        textToFindFieldLabel.setBounds(10, screenY, 30, 30);

        screenY += fieldDistanceY;
        JTextField directoryField = new JTextField();
        directoryField.setText("C:\\");
        directoryField.setBounds(70, screenY, 400, 30);
        JLabel directoryFieldLabel = new JLabel("Directory");
        directoryFieldLabel.setBounds(10, screenY, 60, 30);

        screenY += fieldDistanceY;
        JButton findButton = new JButton("Find");
        findButton.setBounds(70, screenY, 100, 30);



        screenY += fieldDistanceY;
        JTextArea resultArea = new JTextArea(10, 10);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        resultArea.setEditable(false);
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        resultArea.setTabSize(1);
        resultArea.setAutoscrolls(true);
        scrollPane.setBounds(70, screenY, 100, 300);

        JTextArea resultArea2 = new JTextArea(10, 10);
        resultArea2.setBounds(70, screenY, 100, 300);


        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String directory = directoryField.getText().trim();
                String textToFind = textToFindField.getText().trim().replace("\n", "").replace("\r", "");

                resultArea.setText("");

                finder.findList(directory, textToFind).forEach(line -> resultArea.append(line));

                frameResult.setVisible(true);
            }
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        frame.getContentPane().add(textToFindFieldLabel);
        frame.getContentPane().add(directoryFieldLabel);
        frame.getContentPane().add(textToFindField);
        frame.getContentPane().add(directoryField);
        frame.getContentPane().add(findButton);
        frame.getContentPane().add(resultArea2);
        frameResult.getContentPane().add(scrollPane);

        //Display the window.
        frame.setVisible(true);
    }
}
