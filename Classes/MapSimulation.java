package agh.cs.po.Classes;

import java.util.HashMap;
import java.util.LinkedList;

public class MapSimulation {

    public static void main(String[] args){
        HashMap<Integer,Integer> h = new HashMap<>();
        System.out.println(h.get(1));
        h.put(1,1);
        System.out.println(h.get(1));
        h.put(1,2);
        System.out.println(h.get(1));
        h.put(1,1);
        System.out.println(h.get(1));
        LinkedList<Integer> l;
        l = new LinkedList<>();
        System.out.println(l);
        l.add(1);
        System.out.println(l);
        l.add(2);
        System.out.println(l);
        l.add(3);
        System.out.println(l);
        System.out.println(l.getFirst());

    }
}
