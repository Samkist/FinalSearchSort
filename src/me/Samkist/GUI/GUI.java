package me.Samkist.GUI;

import BreezySwing.GBFrame;

import javax.swing.*;


/**
 * Created by Samkist
 * https://github.com/Samkist
 */
public class GUI extends GBFrame {

    private static JFrame frame = new GUI();

    private JButton create = addButton("Create", 1, 1, 1,1);
    private JButton search = addButton("Search", 2, 1, 1,1);
    private JButton print = addButton("Print", 3, 1, 1,1);

    public static void main(String[] args) {

    }

    public GUI() {
        setSize(400, 400);
        setTitle("Final Search Sort");
        setVisible(true);
    }

    @Override
    public void buttonClicked(JButton jButton) {
        if(jButton.equals(create))
            new Create(frame);
        if(jButton.equals(search))
            new SearchGUI(frame);
        if(jButton.equals(print))
            new Print(frame);
    }
}
