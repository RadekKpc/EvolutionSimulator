package agh.cs.po.Menu;

import agh.cs.po.DarwinWorld.SteppeJungleMap;
import agh.cs.po.Visualization.MapSimulation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsPanel extends JPanel implements ActionListener {
    //
    public static final int HEIGHT = 600;
    public static final int WIDTH = 600;
    //Fields for data entry
    private JTextField delay;
    private JTextField animalsStartEnergy;
    private JTextField copulationMinimumEnergy;
    private JTextField numOfSpawnedAnimals;
    private JTextField grassEatingEnergyProfit;
    private JTextField mapWidth;
    private JTextField mapHeight;
    private JTextField jungleWidth;
    private JTextField jungleHeight;
    private JTextField dayEnergyCost;
    private JTextField grassSpawnedInEachDay;
    //Labels to identify the fields
    private JLabel delayLabel;
    private JLabel animalsStartEnergyLabel;
    private JLabel copulationMinimumEnergyLabel;
    private JLabel numOfSpawnedAnimalsLabel;
    private JLabel grassEatingEnergyProfitLabel;
    private JLabel mapWidthLabel;
    private JLabel mapHeightLabel;
    private JLabel jungleWidthLabel;
    private JLabel jungleHeightLabel;
    private JLabel dayEnergyCostLabel;
    private JLabel grassSpawnedInEachDayLabel;
    //button
    private JButton startButton;

    public SettingsPanel(Integer[] defaultMapProperties) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        startButton = new JButton("Start Simulation");
        startButton.addActionListener(this);

        //LABELS
        delayLabel = new JLabel("Real refresh time (ms):           ");
        animalsStartEnergyLabel = new JLabel("Animal start energy:              ");
        copulationMinimumEnergyLabel = new JLabel("Minimum energy to copulation:     ");
        numOfSpawnedAnimalsLabel = new JLabel("Animals spawning at the start:    ");
        grassEatingEnergyProfitLabel = new JLabel("Grass energy profit:              ");
        mapHeightLabel = new JLabel("Map height:                       ");
        mapWidthLabel = new JLabel("Map width:                        ");
        jungleWidthLabel = new JLabel("Jungle width:                     ");
        jungleHeightLabel = new JLabel("Jungle height:                    ");
        dayEnergyCostLabel = new JLabel("Daily energy cost:                ");
        grassSpawnedInEachDayLabel = new JLabel("Grass spawned in each day         ");
        //TEXT FIELDS
        int a = 10;
        delay = new JTextField();
        delay.setColumns(a);
        delay.setText(defaultMapProperties[9].toString());

        animalsStartEnergy = new JTextField();
        animalsStartEnergy.setColumns(a);
        animalsStartEnergy.setText(defaultMapProperties[7].toString());

        copulationMinimumEnergy = new JTextField();
        copulationMinimumEnergy.setColumns(a);
        copulationMinimumEnergy.setText(defaultMapProperties[6].toString());

        numOfSpawnedAnimals = new JTextField();
        numOfSpawnedAnimals.setColumns(a);
        numOfSpawnedAnimals.setText(defaultMapProperties[8].toString());

        grassEatingEnergyProfit = new JTextField();
        grassEatingEnergyProfit.setColumns(a);
        grassEatingEnergyProfit.setText(defaultMapProperties[4].toString());

        mapHeight = new JTextField();
        mapHeight.setColumns(a);
        mapHeight.setText(defaultMapProperties[1].toString());

        mapWidth = new JTextField();
        mapWidth.setColumns(a);
        mapWidth.setText(defaultMapProperties[0].toString());

        jungleWidth = new JTextField();
        jungleWidth.setColumns(a);
        jungleWidth.setText(defaultMapProperties[2].toString());

        jungleHeight = new JTextField();
        jungleHeight.setColumns(a);
        jungleHeight.setText(defaultMapProperties[3].toString());

        dayEnergyCost = new JTextField();
        dayEnergyCost.setColumns(a);
        dayEnergyCost.setText(defaultMapProperties[5].toString());

        grassSpawnedInEachDay = new JTextField();
        grassSpawnedInEachDay.setColumns(a);
        grassSpawnedInEachDay.setText(defaultMapProperties[10].toString());

        //Labels to text fields
        delayLabel.setLabelFor(delay);
        animalsStartEnergyLabel.setLabelFor(animalsStartEnergy);
        copulationMinimumEnergyLabel.setLabelFor(copulationMinimumEnergy);
        numOfSpawnedAnimalsLabel.setLabelFor(numOfSpawnedAnimals);
        grassEatingEnergyProfitLabel.setLabelFor(grassEatingEnergyProfit);
        mapHeightLabel.setLabelFor(mapHeight);
        mapWidthLabel.setLabelFor(mapWidth);
        jungleWidthLabel.setLabelFor(jungleWidth);
        jungleHeightLabel.setLabelFor(jungleHeight);
        dayEnergyCostLabel.setLabelFor(dayEnergyCost);
        grassSpawnedInEachDayLabel.setLabelFor(grassSpawnedInEachDay);

        JPanel l1 = new JPanel();
        JPanel l2 = new JPanel();
        JPanel l3 = new JPanel();
        JPanel l4 = new JPanel();
        JPanel l5 = new JPanel();
        JPanel l6 = new JPanel();
        JPanel l7 = new JPanel();
        JPanel l8 = new JPanel();
        JPanel l9 = new JPanel();
        JPanel l10 = new JPanel();
        JPanel l11 = new JPanel();


        l1.add(delayLabel);
        l2.add(animalsStartEnergyLabel);
        l3.add(copulationMinimumEnergyLabel);
        l4.add(numOfSpawnedAnimalsLabel);
        l5.add(grassEatingEnergyProfitLabel);
        l6.add(mapHeightLabel);
        l7.add(mapWidthLabel);
        l8.add(jungleWidthLabel);
        l9.add(jungleHeightLabel);
        l10.add(dayEnergyCostLabel);
        l11.add(grassSpawnedInEachDayLabel);

        l1.add(delay);
        l2.add(animalsStartEnergy);
        l3.add(copulationMinimumEnergy);
        l4.add(numOfSpawnedAnimals);
        l5.add(grassEatingEnergyProfit);
        l6.add(mapHeight);
        l7.add(mapWidth);
        l8.add(jungleWidth);
        l9.add(jungleHeight);
        l10.add(dayEnergyCost);
        l11.add(grassSpawnedInEachDay);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);

        add(new JLabel("Map properties"));
        add(l6);
        add(l7);
        add(l8);
        add(l9);
        add(new JLabel("Energy properties"));
        add(l5);
        add(l3);
        add(l2);
        add(l10);

        add(new JLabel("Spawning properties"));
        add(l4);
        add(l11);
        add(new JLabel("Others"));
        add(l1);

        add(buttonPanel);

    }

    @Override
    public void actionPerformed(ActionEvent e) {


        SteppeJungleMap map = new SteppeJungleMap(
                Integer.parseInt(mapWidth.getText()),
                Integer.parseInt(mapHeight.getText()),
                Integer.parseInt(jungleWidth.getText()),
                Integer.parseInt(jungleHeight.getText()),
                Integer.parseInt(grassEatingEnergyProfit.getText()),
                Integer.parseInt(dayEnergyCost.getText()),
                Integer.parseInt(copulationMinimumEnergy.getText()),
                Integer.parseInt(animalsStartEnergy.getText())
        );
        MapSimulation simulation = new MapSimulation(
                map, Integer.parseInt(delay.getText()),
                Integer.parseInt(numOfSpawnedAnimals.getText()),
                Integer.parseInt(grassSpawnedInEachDay.getText()));
        simulation.startSimulation();

    }
}

