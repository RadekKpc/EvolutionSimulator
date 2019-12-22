package agh.cs.po.Visualization;

import agh.cs.po.DarwinWorld.SteppeJungleMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MapSimulation implements ActionListener {

    //simulation options:
    public final int delay;
    public SteppeJungleMap map;
    public int startNumOfAnimals;
    public int grassSpawnedInEachDay;

    //simulation necessary:
    public JFrame frame;
    public RenderPanel renderPanel;
    public PlotRenderPanel plotRenderPanel;
    public Timer timer;


    public MapSimulation(SteppeJungleMap map, int delay, int startNumOfAnimals, int grassSpawnedInEachDay) {

        this.map = map;
        this.delay = delay;
        this.startNumOfAnimals = startNumOfAnimals;
        this.grassSpawnedInEachDay = grassSpawnedInEachDay;

        timer = new Timer(delay, this);

        frame = new JFrame("Evolution Simulator");
        frame.setSize(1000, 1000);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        renderPanel = new RenderPanel(map, this);
        renderPanel.setSize(new Dimension(1, 1));

        plotRenderPanel = new PlotRenderPanel(map, this);
        plotRenderPanel.setSize(1, 1);

        frame.add(renderPanel);
        frame.add(plotRenderPanel);

    }

    public void startSimulation() {

        for (int i = 0; i < startNumOfAnimals; i++) {
            map.addAnimalOnRandomField();
            map.placeAnimalToRandomFieldInJungle();
        }
        timer.start();

    }

    @Override
    //It will executed when timer finished counting
    public void actionPerformed(ActionEvent e) {
        plotRenderPanel.repaint();
        renderPanel.repaint();

        map.removeDeadAnimals();
        map.moveRandomAllAnimals();
        map.eating();
        map.nextDay();
        map.copulation();
        for (int i = 0; i < grassSpawnedInEachDay / 2; i++) {
            map.spawnGrass();
        }

    }

}
