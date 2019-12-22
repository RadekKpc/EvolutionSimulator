package agh.cs.po.DarwinWorld;

import agh.cs.po.Menu.SettingsMenu;

import java.io.FileNotFoundException;

public class World {

    public static void main(String[] args) {

        try {

            PropertiesLoader properties = PropertiesLoader.loadPropFromFile();

            Integer[] defaultMapProperties = {
                    properties.getMapWidth(),
                    properties.getMapHeight(),
                    properties.getJungleWidth(),
                    properties.getJungleHeight(),
                    properties.getGrassEatingEnergyProfit(),
                    properties.getDayEnergyCost(),
                    properties.getCopulationMinimumEnergy(),
                    properties.getAnimalsStartEnergy(),
                    properties.getNumOfSpawnedAnimals(),
                    properties.getDelay(),
                    properties.getGrassSpawnedInEachDay()
            };
            //SettingsMenu constructor start animation
            SettingsMenu menu = new SettingsMenu();
            menu.startSimulation(defaultMapProperties);

        } catch (IllegalArgumentException ex) {
            System.out.println(ex);
            return;
        }
        catch (FileNotFoundException ex) {
            System.out.println(ex);
            return;
        }
    }

}
