package agh.cs.po.Classes;

import java.util.Arrays;

//alias in comments: DNA
public class Genes {

    private int[] genes;
    private int size;
    private int numOfGenes;

    public Genes(int numOfGenes, int size) {
        genes = new int[size];
        this.size = size;
        this.numOfGenes = numOfGenes;
        fillRandom();
        makeProprielyGen();
    }

    public Genes(Genes g) {
        this(g.getNumOfGenes(), g.getSize());
        genes = Arrays.copyOf(g.getGenes(), size);
    }

    public Genes(Genes g1, Genes g2) {
        this(g1.getNumOfGenes(), g1.getSize());

        if (g1.getSize() != g2.getSize()) throw new IllegalArgumentException("Gens have different sizes");
        if (g1.getNumOfGenes() != g2.getNumOfGenes())
            throw new IllegalArgumentException("Gens have different range of values");

        //random places to div DNA
        int firstPlaceToDiv = (int) (Math.random() * (size - 1));
        int secondPlaceToDiv = firstPlaceToDiv;
        while (secondPlaceToDiv == firstPlaceToDiv) {
            secondPlaceToDiv = (int) (Math.random() * (size - 1));
        }
        if (firstPlaceToDiv > secondPlaceToDiv) {
            int tmp = firstPlaceToDiv;
            firstPlaceToDiv = secondPlaceToDiv;
            secondPlaceToDiv = tmp;
        }

        //FILLING GENES BY 2 PARTS OF FIRST PARENT'S DNA AND 1 PART OF SECOND PARENT'S DNA
        for (int i = 0; i <= firstPlaceToDiv; i++) {
            genes[i] = g1.getGenes()[i];
        }
        for (int i = firstPlaceToDiv + 1; i <= secondPlaceToDiv; i++) {
            genes[i] = g2.getGenes()[i];
        }
        for (int i = secondPlaceToDiv; i < size; i++) {
            genes[i] = g1.getGenes()[i];
        }

        //REPAIR GENES, THERE IS A CHANCE THAT SOME GENS FROM RANGE AREN'T EXIST IN CHILD GENES
        makeProprielyGen();

    }


    public int getNumOfGenes() {
        return this.numOfGenes;
    }

    public int getSize() {
        return this.size;
    }

    public int[] getGenes() {
        return genes;
    }

    private void fillRandom() {
        for (int i = 0; i < size; i++) {
            genes[i] = (int) (Math.random() * (numOfGenes));
        }
        Arrays.sort(genes);
    }

    private void makeProprielyGen() {
        boolean flag = true;
        while (flag) {
            flag = false;

            boolean[] isInGens = new boolean[numOfGenes + 1];

            for (int i = 0; i < numOfGenes; i++) {
                isInGens[i] = false;
            }
            for (int i = 0; i < size; i++) {
                isInGens[this.genes[i]] = true;
            }
            for (int i = 0; i < numOfGenes; i++) {
                if (!isInGens[i]) {
                    flag = true;
                }
            }

            if (flag) {
                for (int i = 0; i < numOfGenes; i++) {
                    if (!isInGens[i]) {
                        genes[(int) (Math.random() * (size))] = i;
                    }
                }
            }
        }

        Arrays.sort(genes);
    }

    public int returnRandomGen() {
        int rand = (int) (Math.random() * (size));
        return genes[rand];
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < size; i++) {
            result = result + " " + Integer.toString(genes[i]);
        }
        return result;
    }
}
