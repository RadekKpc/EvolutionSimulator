package agh.cs.po.DarwinWorld;

import agh.cs.po.EnumClasses.MapDirection;
import agh.cs.po.EnumClasses.MoveDirection;
import agh.cs.po.Classes.Vector2d;
import agh.cs.po.Classes.Animal;
import agh.cs.po.Interfaces.IWorldMap;

import java.util.Scanner;

public class World {
//DO ZROBIENIA SPRAWDZIC OBSERER W SteppeJugleMap podwaja czasami animale i nie zjada trawy!!!

    public static void wysietl(SteppeJungleMap map){
        System.out.print("\033[H\033[2J");
        System.out.println(map);
        System.out.println("grass : " + map.grass.values());
        System.out.println("grass : " + map.grass.keySet());
        System.out.println("hashmapa : " + map.animals.values());
        System.out.println("hashmapa : " + map.animals.keySet());
    }

    public static void main(String[] args) {

        try {

            SteppeJungleMap map = new SteppeJungleMap(30,30, 10,10,5,1,15);

            Animal a = new Animal(map,new Vector2d(2,2),100);
            map.place(a);

            Animal b = new Animal(map,new Vector2d(2,2),100);
            map.place(b);

            Animal c = new Animal(map,new Vector2d(2,2),100);
            map.place(c);

            Animal[] animals = new Animal[10];
            for(int i=0;i<10;i++)
            {
                animals[i]=new Animal(map,new Vector2d(i+3,i+3),3);
                map.place(animals[i]);
            }

            boolean exit = true;
            Scanner scanner = new Scanner(System.in);
            map.spawnGrass();
            map.spawnGrass();

            while(exit){

                wysietl(map);
                String energy = "";
                for(int i =0;i<10;i++){
                    energy += "[ " + animals[i].getPosition() + " " + animals[i].energy + "] ";
                }
                energy += "[A " + a.getPosition() + " " + a.energy + "] ";
                energy += "[B " + a.getPosition() + " " + b.energy + "] ";
                energy += "[C " + a.getPosition() + " " + c.energy + "] ";
                System.out.println(energy);
                map.removeDeadAnimals();
                for(int i =0;i<10;i++){
                    animals[i].rotate();
                    animals[i].move(MoveDirection.FORWARD);
                }
                map.eating();
                map.nextDay();
                map.spawnGrass();
                map.spawnGrass();

                String key = scanner.next();
                if(key.equals("e")){exit = false;}
                if(key.equals("w")){a.move(MoveDirection.FORWARD);}
                if(key.equals("s")){a.move(MoveDirection.BACKWARD);}
                if(key.equals("a")){a.move(MoveDirection.LEFT);}
                if(key.equals("d")){a.move(MoveDirection.RIGHT);}
                if(key.equals("e")){exit = false;}
                if(key.equals("i")){b.move(MoveDirection.FORWARD);}
                if(key.equals("k")){b.move(MoveDirection.BACKWARD);}
                if(key.equals("j")){b.move(MoveDirection.LEFT);}
                if(key.equals("l")){b.move(MoveDirection.RIGHT);}
                if(key.equals("c")){continue;}
            }

        }
        catch(IllegalArgumentException ex){
            System.out.println(ex);
            return;
        }


    }

}
