package agh.cs.po.Visualization;

import agh.cs.po.Classes.Animal;
import agh.cs.po.Classes.Grass;
import agh.cs.po.DarwinWorld.SteppeJungleMap;

import javax.swing.*;
import java.awt.*;

public class RenderPanel extends JPanel {

    public SteppeJungleMap map;
    public MapSimulation simulation;

    public RenderPanel(SteppeJungleMap map, MapSimulation simulation) {
        this.map = map;
        this.simulation = simulation;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setSize((int) (simulation.frame.getWidth() * 0.6), simulation.frame.getHeight() - 38);
        this.setLocation((int) (0.4 * simulation.frame.getWidth()), 0);
        int width = this.getWidth();
        int height = this.getHeight(); //38 is toolbar size
        int widthScale = Math.round(width / map.width);
        int heightScale = height / map.height;

        //draw Steppe
        g.setColor(new Color(170, 224, 103));
        g.fillRect(0, 0, width, height);

        //draw Jungle
        g.setColor(new Color(0, 160, 7));
        g.fillRect(map.getJungleLowerLeft().x * widthScale,
                map.getJungleLowerLeft().y * heightScale,
                map.jungleWidth * widthScale,
                map.jungleHeight * heightScale);

        //draw Grass
        for (Grass grass : map.getGrass()) {
            g.setColor(grass.toColor());
            int y = map.toNoBoundedPosition(grass.getPosition()).y * heightScale;
            int x = map.toNoBoundedPosition(grass.getPosition()).x * widthScale;
            g.fillRect(x, y, widthScale, heightScale);
        }
        //draw Animals
        for (Animal a : map.getAnimals()) {
            g.setColor(a.toColor());
            int y = map.toNoBoundedPosition(a.getPosition()).y * heightScale;
            int x = map.toNoBoundedPosition(a.getPosition()).x * widthScale;
            g.fillOval(x, y, widthScale, heightScale);
        }
    }

}
