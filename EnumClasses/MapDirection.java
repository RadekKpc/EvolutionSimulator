package agh.cs.po.EnumClasses;

import agh.cs.po.Classes.Vector2d;

public enum MapDirection {
    NORTH, SOUTH, WEST, EAST, NORTHWEST, SOUTHWEST, NORTHEAST, SOUTHEAST;

    public String toString() {

        switch (this) {
            case NORTH:
                return "↑";
            case SOUTH:
                return "↓";
            case WEST:
                return "←";
            case EAST:
                return "→";
            case NORTHWEST:
                return "↖";
            case SOUTHWEST:
                return "↙";
            case NORTHEAST:
                return "↗";
            case SOUTHEAST:
                return "↘";
            //no need for default
        }
        return null;
    }

    public MapDirection next() {
        switch (this) {
            case NORTH:
                return NORTHEAST;
            case SOUTH:
                return SOUTHWEST;
            case WEST:
                return NORTHWEST;
            case EAST:
                return SOUTHEAST;
            case NORTHWEST:
                return NORTH;
            case SOUTHWEST:
                return WEST;
            case NORTHEAST:
                return EAST;
            case SOUTHEAST:
                return SOUTH;
            //no need for default
        }
        return null;
    }

    public MapDirection previous() {
        switch (this) {
            case NORTH:
                return NORTHWEST;
            case SOUTH:
                return SOUTHEAST;
            case WEST:
                return SOUTHWEST;
            case EAST:
                return NORTHEAST;
            case NORTHWEST:
                return WEST;
            case SOUTHWEST:
                return SOUTH;
            case NORTHEAST:
                return NORTH;
            case SOUTHEAST:
                return EAST;
            //no need for default
        }
        return null;
    }

    public Vector2d toUnitVector() {
        switch (this) {
            case NORTH:
                return new Vector2d(0, 1);
            case SOUTH:
                return new Vector2d(0, -1);
            case WEST:
                return new Vector2d(-1, 0);
            case EAST:
                return new Vector2d(1, 0);
            case NORTHWEST:
                return new Vector2d(-1, 1);
            case SOUTHWEST:
                return new Vector2d(-1, -1);
            case NORTHEAST:
                return new Vector2d(1, 1);
            case SOUTHEAST:
                return new Vector2d(1, -1);
            //no need for default
        }
        return null;
    }
}
