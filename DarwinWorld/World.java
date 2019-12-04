package agh.cs.po.DarwinWorld;

import agh.cs.po.Menu.SettingsMenu;
import agh.cs.po.Visualization.MapSimulation;

import java.util.ArrayList;

public class World {

    public static void main(String[] args) {

        try {

            Integer[] defaultMapProperties = {30, 30, 10, 10, 15, 1, 15, 40, 10, 20, 4};
            SettingsMenu menu = new SettingsMenu(defaultMapProperties);

        } catch (IllegalArgumentException ex) {
            System.out.println(ex);
            return;
        }


    }

}
