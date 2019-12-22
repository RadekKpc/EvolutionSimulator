package agh.cs.po.Visualization;

import agh.cs.po.DarwinWorld.SteppeJungleMap;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class PlotRenderPanel extends JPanel {

    private SteppeJungleMap map;
    private MapSimulation simulation;
    private int totalDays;
    ArrayList<Integer> animalsPopulation;
    ArrayList<Integer> grassPopulation;

    public PlotRenderPanel(SteppeJungleMap map, MapSimulation simulation) {
        animalsPopulation = new ArrayList<>();
        grassPopulation = new ArrayList<>();
        this.map = map;
        this.simulation = simulation;
        totalDays = 0;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setSize((int) (simulation.frame.getWidth() * 0.4), simulation.frame.getHeight() - 38); //38 is toolbar size
        this.setLocation(0, 0);
        int width = this.getWidth();
        int height = this.getHeight();
        totalDays++;
        g.drawString("Total days: " + totalDays, 10, height - 20);

        animalsPopulation.add(map.animalsList.size());
        grassPopulation.add(map.grassList.size());

        FunctionPlot p1 = new FunctionPlot(width - 10, height * 2 / 3 - 50, 5, height * 2 / 3 - 30);

        g.setColor(new Color(0, 0, 0));

        drawVerticalArrow(g, p1.cpx, p1.cpy, p1.cpx, p1.cpy - p1.height, "Population of grass and animals");
        drawHorizontalArrow(g, p1.cpx, p1.cpy, p1.cpx + p1.width, p1.cpy, totalDays, "Day");


        g.setColor(new Color(160, 67, 41));
        Integer[] animalPopulationAvg = new Integer[p1.width];
        Integer[] grassPopulationAvg = new Integer[p1.width];

        //calculate and scale animal population per day
        for (int i = 0; i < animalPopulationAvg.length; i++) {
            animalPopulationAvg[i] = 0;
        }

        int i = 0;
        for (int j : animalsPopulation) {
            animalPopulationAvg[i] = j;
            i++;
            if (i == animalPopulationAvg.length) break;
        }
        i = 0;
        for (int j : animalsPopulation) {
            animalPopulationAvg[i % animalPopulationAvg.length] += j;
            animalPopulationAvg[i % animalPopulationAvg.length] /= 2;
            i++;
        }
        double maxY = (double) Collections.max(Arrays.asList(animalPopulationAvg));
        double scale = p1.height / maxY;
        for (i = 0; i < animalPopulationAvg.length; i++) {
            animalPopulationAvg[i] = (int) (scale * animalPopulationAvg[i]);
        }
        //drawing
        for (i = 0; i < p1.width - 1; i++) {
            g.drawLine(i + p1.cpx + 1, p1.cpy - animalPopulationAvg[i], i + p1.cpx + 2, p1.cpy - animalPopulationAvg[i + 1]); //drawing point
        }

        //calculate and scale grass populate
        for (i = 0; i < grassPopulationAvg.length; i++) {
            grassPopulationAvg[i] = 0;
        }

        i = 0;
        for (int j : grassPopulation) {
            grassPopulationAvg[i] = j;
            i++;
            if (i == grassPopulationAvg.length) break;
        }
        i = 0;
        for (int j : grassPopulation) {
            grassPopulationAvg[i % grassPopulationAvg.length] += j;
            grassPopulationAvg[i % grassPopulationAvg.length] /= 2;
            i++;
        }
        maxY = (double) Collections.max(Arrays.asList(grassPopulationAvg));
        scale = p1.height / maxY;
        for (i = 0; i < grassPopulationAvg.length; i++) {
            grassPopulationAvg[i] = (int) (scale * grassPopulationAvg[i]);
        }
        g.setColor(new Color(0, 160, 7));
        for (i = 0; i < p1.width - 1; i++) {
            g.drawLine(i + p1.cpx + 1, p1.cpy - grassPopulationAvg[i], i + p1.cpx + 2, p1.cpy - grassPopulationAvg[i + 1]); //drawing point
        }

        //Legend
        g.setColor(new Color(160, 67, 41));
        g.fillRect(p1.cpx, p1.cpy + height / 30, width / 25, height / 50);
        g.drawString(" - Animal population plot", p1.cpx + width / 25, p1.cpy + height / 22);

        g.setColor(new Color(0, 160, 7));
        g.fillRect(p1.cpx, p1.cpy + height / 20, width / 25, height / 50);
        g.drawString(" - Grass population plot", p1.cpx + width / 25, p1.cpy + height / 15);

        g.setColor(new Color(0, 0, 0));
        g.drawString("Legend: ", p1.cpx, p1.cpy + height / 10);
        int yp = p1.cpy + height / 10;
        int a = height / 22;
        g.drawString("Steppe Field ", p1.cpx + width / 10, yp + a);
        g.drawString("Jungle Field ", p1.cpx + width / 10, yp + 2 * a);
        g.drawString("Animal (Changes color to darker when has more energy)", p1.cpx + width / 10, yp + 3 * a);
        g.drawString("Grass ", p1.cpx + width / 10, yp + 4 * a);

        g.setColor(new Color(170, 224, 103));
        g.fillRect(p1.cpx, yp + a - 10, width / 20, height / 40);

        g.setColor(new Color(0, 160, 7));
        g.fillRect(p1.cpx, yp + 2 * a - 10, width / 20, height / 40);

        g.setColor(new Color(146, 82, 73));
        g.fillOval(p1.cpx, yp + 3 * a - 10, width / 20, height / 40);

        g.setColor(new Color(67, 222, 31));
        g.fillRect(p1.cpx, yp + 4 * a - 10, width / 20, height / 40);


    }

    private void drawVerticalArrow(Graphics g, int x, int y, int x2, int y2, String title) {
        g.drawLine(x, y, x2, y2);
        g.drawLine(x2, y2, x2 - 5, y2 + 5);
        g.drawLine(x2, y2, x2 + 5, y2 + 5);
        g.drawString(title, x, y2 - 10);
    }

    private void drawHorizontalArrow(Graphics g, int x, int y, int x2, int y2, Integer max, String title) {
        g.drawLine(x, y, x2, y2);
        g.drawLine(x2, y2, x2 - 5, y2 + 5);
        g.drawLine(x2, y2, x2 - 5, y2 - 5);

        g.drawString(title, x2 - 50, y + 30);

//        g.drawLine(x2,y2+3,x2,y2-3);
//        g.drawString(max.toString(),x2-20,y+20);

        g.drawLine(x2 / 2, y2 + 3, x2 / 2, y2 - 3);
        g.drawString(Integer.toString(max / 2), x2 / 2 - 20, y + 20);

        g.drawLine(x2 / 4, y2 + 3, x2 / 4, y2 - 3);
        g.drawString(Integer.toString(max / 4), x2 / 4 - 20, y + 20);

        g.drawLine(x2 * 3 / 4, y2 + 3, x2 * 3 / 4, y2 - 3);
        g.drawString(Integer.toString(max * 3 / 4), x2 * 3 / 4 - 20, y + 20);

    }

}
