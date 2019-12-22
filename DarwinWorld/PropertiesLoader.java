package agh.cs.po.DarwinWorld;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class PropertiesLoader {

    private int mapWidth;
    private int mapHeight;
    private int jungleWidth;
    private int jungleHeight;
    private int grassEatingEnergyProfit;
    private int dayEnergyCost;
    private int copulationMinimumEnergy;
    private int animalsStartEnergy;
    private int numOfSpawnedAnimals;
    private int delay;
    private int grassSpawnedInEachDay;


    static public PropertiesLoader loadPropFromFile() throws FileNotFoundException,IllegalArgumentException {
        Gson gson = new Gson();
        File f = new File("");
        System.out.println(f.getAbsolutePath());
        PropertiesLoader properties =  (PropertiesLoader)gson.fromJson(new FileReader("src\\main\\agh\\cs\\po\\DarwinWorld\\config.json"), PropertiesLoader.class);
        properties.validate();
        return properties;
    }

    public void validate() throws IllegalArgumentException{
        if(this.mapWidth <= 0){ throw new IllegalArgumentException("Invalid map width");}
        if(this.mapHeight <= 0){ throw new IllegalArgumentException("Invalid map height");}
        if(this.jungleHeight <= 0){ throw new IllegalArgumentException("Invalid jungle width");}
        if(this.jungleWidth<= 0){ throw new IllegalArgumentException("Invalid jungle height");}
        if(this.copulationMinimumEnergy <= 0){ throw new IllegalArgumentException("Invalid copulationMinimumEnergy");}
        if(this.animalsStartEnergy < 0){ throw new IllegalArgumentException("Invalid animalsStartEnergy");}
        if(this.numOfSpawnedAnimals < 0){ throw new IllegalArgumentException("Invalid numOfSpawnedAnimals");}
        if(this.grassSpawnedInEachDay < 0){ throw new IllegalArgumentException("Invalid grassSpawnedInEachDay");}

    }

    public int getMapWidth() {
        return mapWidth;
    }

    public void setMapWidth(int mapWidth) {
        this.mapWidth = mapWidth;
    }

    public int getMapHeight() {
        return mapHeight;
    }

    public void setMapHeight(int mapHeight) {
        this.mapHeight = mapHeight;
    }

    public int getJungleWidth() {
        return jungleWidth;
    }

    public void setJungleWidth(int jungleWidth) {
        this.jungleWidth = jungleWidth;
    }

    public int getJungleHeight() {
        return jungleHeight;
    }

    public void setJungleHeight(int jungleHeight) {
        this.jungleHeight = jungleHeight;
    }

    public int getGrassEatingEnergyProfit() {
        return grassEatingEnergyProfit;
    }

    public void setGrassEatingEnergyProfit(int grassEatingEnergyProfit) {
        this.grassEatingEnergyProfit = grassEatingEnergyProfit;
    }

    public int getDayEnergyCost() {
        return dayEnergyCost;
    }

    public void setDayEnergyCost(int dayEnergyCost) {
        this.dayEnergyCost = dayEnergyCost;
    }

    public int getCopulationMinimumEnergy() {
        return copulationMinimumEnergy;
    }

    public void setCopulationMinimumEnergy(int copulationMinimumEnergy) {
        this.copulationMinimumEnergy = copulationMinimumEnergy;
    }

    public int getAnimalsStartEnergy() {
        return animalsStartEnergy;
    }

    public void setAnimalsStartEnergy(int animalsStartEnergy) {
        this.animalsStartEnergy = animalsStartEnergy;
    }

    public int getNumOfSpawnedAnimals() {
        return numOfSpawnedAnimals;
    }

    public void setNumOfSpawnedAnimals(int numOfSpawnedAnimals) {
        this.numOfSpawnedAnimals = numOfSpawnedAnimals;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public int getGrassSpawnedInEachDay() {
        return grassSpawnedInEachDay;
    }

    public void setGrassSpawnedInEachDay(int grassSpawnedInEachDay) {
        this.grassSpawnedInEachDay = grassSpawnedInEachDay;
    }
}
