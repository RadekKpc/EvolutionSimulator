package agh.cs.po.Menu;

import javax.swing.*;

public class SettingsMenu {
    public JFrame menuFrame;

    public SettingsMenu(Integer[] defaultMapProperties) {

        menuFrame = new JFrame("Evolution Simulator (Settings)");
        menuFrame.setSize(500, 500);
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setLocationRelativeTo(null);
        menuFrame.add(new SettingsPanel(defaultMapProperties));
        menuFrame.setVisible(true);

    }
}
