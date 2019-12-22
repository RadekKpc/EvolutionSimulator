package agh.cs.po.Classes;

import agh.cs.po.EnumClasses.MapDirection;
import agh.cs.po.EnumClasses.MoveDirection;
import agh.cs.po.Interfaces.IWorldMap;
import agh.cs.po.Interfaces.IMapElement;
import agh.cs.po.Interfaces.IPositionChangeObserver;

import java.awt.*;
import java.util.ArrayList;

public class Animal implements IMapElement {
    public MapDirection direction;
    public IWorldMap map;
    public int energy;
    public int startEnergy;
    public ArrayList<IPositionChangeObserver> observerlist = new ArrayList<>();
    public Genes genes;
    protected Vector2d position;

    //CONSTRUCTORS

    public Animal() {
        this.direction = MapDirection.NORTH;
        genes = new Genes(8, 32);
        position = new Vector2d(2, 2);
    }

    public Animal(IWorldMap map) {
        this();
        this.map = map;
    }

    public Animal(IWorldMap map, Vector2d initialPosition) {
        this(map);
        this.position = initialPosition;
    }

    public Animal(IWorldMap map, Vector2d initialPosition, int energy) {
        this(map, initialPosition);
        this.energy = energy;
        this.startEnergy = energy;
    }

    //ENERGY

    public boolean isDead() {
        return this.energy <= 0;
    }

    public void changeEnergy(int value) {
        this.energy = this.energy + value;
        if (this.energy < 0) {
            this.energy = 0;
        }
    }
    //MOVING

    public void move(MoveDirection d) {

        switch (d) {
            case LEFT:
                this.direction = this.direction.previous();
                return;
            case RIGHT:
                this.direction = this.direction.next();
                return;
            case FORWARD:
                if (map.canMoveTo(position.add(direction.toUnitVector()))) {
                    Vector2d old = new Vector2d(this.getPosition().x, this.getPosition().y);
                    this.position = position.add(direction.toUnitVector());
                    this.positionChanged(old, this.position, this);
                }
                return;
            case BACKWARD:
                if (map.canMoveTo(position.subtract(direction.toUnitVector()))) {
                    Vector2d old = new Vector2d(this.getPosition().x, this.getPosition().y);
                    position = position.subtract(direction.toUnitVector());
                    this.positionChanged(old, this.position, this);
                }
                return;
            //no need default
        }
    }

    public void rotate() {
        int numOfRotation = genes.returnRandomGen();
        for (int i = 0; i < numOfRotation; i++) {
            this.move(MoveDirection.RIGHT);
        }
    }

    @Override
    public Vector2d getPosition() {
        return this.position;
    }

    @Override
    public boolean isMovable() {
        return true;
    }

    //OBSERVERS

    @Override
    public void addObserver(IPositionChangeObserver observer) {
        observerlist.add(observer);
    }

    public void removeObserver(IPositionChangeObserver observer) {
        observerlist.remove(observer);
    }

    private void positionChanged(Vector2d old, Vector2d n, Object a) {
        for (IPositionChangeObserver o : observerlist) {
            o.positionChanged(old, n, a);
        }
    }

    //OTHER

    @Override
    public String toString() {
        return energy == 0 ? "X" : this.direction.toString();
    }


    //COPULATION

    public Animal copulation(Animal mother) {

        int childEnergy = (int) (0.25 * mother.energy) + (int) (this.energy * 0.25);
        mother.changeEnergy((int) -(0.25 * mother.energy));
        this.changeEnergy((int) -(this.energy * 0.25));

        Animal child = new Animal(map, mother.getPosition(), childEnergy);
        child.genes = new Genes(this.genes, mother.genes);

        return child;
    }

    @Override
    public Color toColor() {
        if (energy == 0) return new Color(222, 221, 224);
        if (energy < 0.2 * startEnergy) return new Color(224, 179, 173);
        if (energy < 0.4 * startEnergy) return new Color(224, 142, 127);
        if (energy < 0.6 * startEnergy) return new Color(201, 124, 110);
        if (energy < 0.8 * startEnergy) return new Color(182, 105, 91);
        if (energy < startEnergy) return new Color(164, 92, 82);
        if (energy < 2 * startEnergy) return new Color(146, 82, 73);
        if (energy < 4 * startEnergy) return new Color(128, 72, 64);
        if (energy < 6 * startEnergy) return new Color(119, 67, 59);
        if (energy < 8 * startEnergy) return new Color(88, 50, 44);
        if (energy < 10 * startEnergy) return new Color(74, 42, 37);
        return new Color(55, 31, 027);
    }


}
