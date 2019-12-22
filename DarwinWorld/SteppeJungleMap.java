package agh.cs.po.DarwinWorld;

import agh.cs.po.Classes.Vector2d;
import agh.cs.po.Classes.Animal;
import agh.cs.po.EnumClasses.MoveDirection;
import agh.cs.po.Interfaces.IWorldMap;
import agh.cs.po.Classes.Grass;
import agh.cs.po.Interfaces.IMapElement;
import agh.cs.po.Interfaces.IPositionChangeObserver;

import java.util.*;

public class SteppeJungleMap implements IWorldMap, IPositionChangeObserver {

    //map properties

    //size
    private final Vector2d upperRight;
    private final Vector2d lowerLeft;
    private final Vector2d lowerLeftJungle;
    private final Vector2d upperRightJungle;
    public final int jungleWidth;
    public final int jungleHeight;
    public int width;
    public int height;

    //eating
    private int grassProfit;
    private int dayCost;
    private int startAnimalsEnergy;
    //copulation
    private final int energyLimitToCopulation;

    //map elements
    public Map<Vector2d, Grass> grass = new HashMap<>();
    public Map<Vector2d, LinkedList<Animal>> animals = new HashMap<>();
    public LinkedList<Animal> animalsList;
    public LinkedList<Grass> grassList;

    public SteppeJungleMap(int width, int height, int jungleWidth, int jungleHeight, int grassProfit, int dayCost, int energyLimitToCopulation, int startAnimalsEnergy) {
        this.startAnimalsEnergy = startAnimalsEnergy;
        this.grassList = new LinkedList<>();
        this.animalsList = new LinkedList<>();
        this.energyLimitToCopulation = energyLimitToCopulation;
        this.grassProfit = grassProfit;
        this.dayCost = (-1) * dayCost;
        this.lowerLeft = new Vector2d(0, 0);
        this.upperRight = new Vector2d(width - 1, height - 1);
        this.width = width;
        this.height = height;
        this.jungleWidth = jungleWidth;
        this.jungleHeight = jungleHeight;

        //default cords of jungle's corners
        int lljx = 0; //LOWER LEFT JUNGLE X
        int lljy = 0; //LOWER LEFT JUNGLE y
        int urjx = width - 1; //UPPER RIGHT JUNGLE x
        int urjy = height - 1; //UPPER RIGHT JUNGLE y


        //calculating jungle's position
        for (int i = 0; i < (width - jungleWidth); i++) {
            if (i % 2 == 0) {
                lljx++;
            } else {
                urjx--;
            }
        }

        for (int i = 0; i < (height - jungleHeight); i++) {
            if (i % 2 == 0) {
                lljy++;
            } else {
                urjy--;
            }
        }

        this.lowerLeftJungle = new Vector2d(lljx, lljy);
        this.upperRightJungle = new Vector2d(urjx, urjy);

    }


    public Vector2d toNoBoundedPosition(Vector2d position) {
        int newX;
        int newY;

        if (position.x < lowerLeft.x) {
            newX = (width - Math.abs(position.x % width)) % width;
        } else {
            newX = Math.abs(position.x % width);
        }
        if (position.y < lowerLeft.y) {
            newY = (height - Math.abs(position.y % height)) % height;
        } else {
            newY = Math.abs(position.y % height);
        }

        return new Vector2d(newX, newY);
    }

    @Override
    public boolean place(IMapElement e) {
        //we can place maximum 3 animals on one field, to change it, change this code
        Vector2d position = toNoBoundedPosition(e.getPosition());

        if (!canPlace(position)) {
            throw new IllegalArgumentException("Field " + e.getPosition() + " is full");

        } else {
            if (e instanceof Grass) {
                if (grass.get(position) == null)
                    grass.put(position, (Grass) e);
                grassList.add((Grass) e);
            }
            if (e instanceof Animal) {
                addAnimal((Animal) e, position);
                animalsList.add((Animal) e);
                e.addObserver(this);
            }

        }
        return true;
    }

    // removing from hashMap if you want definitely remove animal you should in addition remove its Observer, It is just use to moving animals
    private boolean removeAnimal(Animal a, Vector2d position2) {
        Vector2d position = toNoBoundedPosition(position2);
        LinkedList<Animal> l = animals.get(position);
        if (l == null)
            throw new IllegalArgumentException("Animal" + a.getPosition() + " -> " + position + " already not exist in the map");
        else if (l.size() == 0)
            throw new IllegalArgumentException("Animal" + a.getPosition() + " already not exist in the map empty list");
        else {
            l.remove(a);
            if (l.size() == 0) {
                animals.remove(position);
            }
        }
        return true;
    }

    // adding to hashMap if you want totally add animal you should add its Observer, It is just use to moving animals
    private boolean addAnimal(Animal a, Vector2d p) {
        if (a == null) return false;
        Vector2d pos = toNoBoundedPosition(p);
        LinkedList<Animal> l = animals.get(pos);
        if (l == null) {
            LinkedList<Animal> tmp = new LinkedList<>();
            tmp.add(a);
            animals.put(pos, tmp);

        } else if (l != null) {
            l.add(a);
        }
        return true;

    }


    @Override
    public boolean isOccupied(Vector2d position2) {
        return objectAt(position2) != null;
    }

    @Override
    public Object objectAt(Vector2d position2) {
        Vector2d position = toNoBoundedPosition(position2);
        LinkedList<Animal> l = animals.get(position);
        if (l == null) return grass.get(position);
        else if (l.size() == 0) return grass.get(position);
        else return l.getFirst();
    }

    @Override
    public boolean canMoveTo(Vector2d position2) {
        //checking place have there 2 animals already
        Vector2d position = toNoBoundedPosition(position2);
        if (animals.get(position) == null) return true;
        if (animals.get(position).size() < 2) return true;
        return false;
    }

    public boolean canPlace(Vector2d position2) {
        //checking place have there 3 animals already
        Vector2d position = toNoBoundedPosition(position2);
        if (animals.get(position) == null) return true;
        if (animals.get(position).size() < 3) return true;
        return false;
    }

    public void eating() {
        LinkedList<Grass> toRemoveAfterEating = new LinkedList<>();

        for (Grass food : grass.values()) {
            LinkedList<Animal> l = animals.get(food.getPosition());
            if (l != null) {
                if (l.size() > 0) {
                    for (Animal a : l) {
                        a.changeEnergy(grassProfit / l.size());
                        toRemoveAfterEating.add(food);
                    }
                }
            }
        }

        for (Grass g : toRemoveAfterEating) {
            grass.remove(g.getPosition());
            grassList.remove(g);

        }

    }

    public void moveRandomAllAnimals() {
        LinkedList<Animal> l = getAnimals();
        for (int i = 0; i < l.size(); i++) {
            animalsList.get(i).rotate();
            animalsList.get(i).move(MoveDirection.FORWARD);
        }
    }

    public void copulation() {
        for (LinkedList<Animal> animalList : animals.values()) {
            if (animalList != null) {
                if (animalList.size() == 2) {
                    Animal mother = animalList.get(0);
                    Animal father = animalList.get(1);
                    if (mother.energy >= energyLimitToCopulation)
                        if (father.energy >= energyLimitToCopulation) {
                            Animal child = father.copulation(mother);
                            place(child);
                        }
                }
            }
        }
    }

    public void nextDay() {
        for (LinkedList<Animal> animalList : animals.values()) {
            if (animalList != null) {
                if (animalList.size() > 0) {
                    for (Animal a : animalList) {
                        a.changeEnergy(dayCost);
                    }
                }
            }
        }
    }

    public void removeDeadAnimals() {
        LinkedList<Animal> l = getAnimals();
        for (int i = 0; i < l.size(); i++) {
            Animal a = animalsList.get(i);
            if (a.isDead()) {
                removeAnimal(a, a.getPosition());
                a.removeObserver(this);
                animalsList.remove(a);
            }
        }
    }

    public boolean addAnimalOnRandomField() {

        int toMuchTimes = 0;
        while (toMuchTimes < width * height * 2) {
            Vector2d position = new Vector2d((int) (Math.random() * (width) + lowerLeft.x), (int) (Math.random() * (height) + lowerLeft.y));
            if (canPlace(position)) {
                place(new Animal(this, position, startAnimalsEnergy));
                return true;
            }
            toMuchTimes++;
        }
        return false;
    }

    public boolean placeAnimalToRandomFieldInJungle() {
        int jungleSize = jungleWidth * jungleHeight;
        int mapSize = height * width;
        int steppeSize = mapSize - jungleSize;

        int toMuchTimes = 0;
        while ((double) toMuchTimes < (double) 2 * ((double) jungleSize / (double) steppeSize) * mapSize) {

            Vector2d position = new Vector2d((int) (Math.random() * (jungleWidth) + lowerLeftJungle.x), (int) (Math.random() * (jungleHeight) + lowerLeftJungle.y));
            if (canPlace(position)) {
                place(new Animal(this, position, startAnimalsEnergy));
                return true;
            }
            toMuchTimes++;
        }
        return false;
    }


    public boolean positionChanged(Vector2d oldPosition2, Vector2d newPosition2, Object a) {

        Vector2d oldPosition = toNoBoundedPosition(oldPosition2);
        Vector2d newPosition = toNoBoundedPosition(newPosition2);

        if (canMoveTo(newPosition)) {

            removeAnimal((Animal) a, oldPosition);
            addAnimal((Animal) a, newPosition);
            return true;
        }
        return false;
    }

    public void spawnGrass() {

        //For Jungle

        int jungleSize = jungleWidth * jungleHeight;
        int mapSize = height * width;
        int steppeSize = mapSize - jungleSize;
        int toMuchTimes = 0;
        // stop looking for free place for grass in jungle, following to uniform probability distribution: after (size of jungle) times we should find free position
        //but if we didn't we can stop and meaning that jungle fields are close to be full of grass.
        while (toMuchTimes < 2 * jungleSize) {

            //random position in jungle
            Vector2d newGrass = new Vector2d((int) (Math.random() * (jungleWidth) + lowerLeftJungle.x), (int) (Math.random() * (jungleHeight) + lowerLeftJungle.y));
            if (grass.get(newGrass) == null && canPlace(newGrass)) {
                place(new Grass(newGrass));
                break;
            }
            toMuchTimes++;
        }

        //For Steppe

        toMuchTimes = 0;
        // stop looking for free place for grass in steppe, following to uniform probability distribution: after (size of steppe) times we should find free position
        //but if we didn't we can stop and be sure that steppe fields are close to be full of grass.
        while ((double) toMuchTimes < (double) 2 * ((double) jungleSize / (double) steppeSize) * mapSize) {

            Vector2d newGrass = new Vector2d((int) (Math.random() * (width) + lowerLeft.x), (int) (Math.random() * (height) + lowerLeft.y));
            if (grass.get(newGrass) == null && canPlace(newGrass) && !(newGrass.follows(lowerLeftJungle) && newGrass.precedes(upperRightJungle))) {
                place(new Grass(newGrass));
                break;
            }
            toMuchTimes++;
        }
    }

    public String toString() {
        return "SteppeJungleMap";
    }


    public LinkedList<Animal> getAnimals() {
        return animalsList;
    }

    public LinkedList<Grass> getGrass() {
        return grassList;
    }

    public Vector2d getJungleLowerLeft() {
        return lowerLeftJungle;
    }

}
