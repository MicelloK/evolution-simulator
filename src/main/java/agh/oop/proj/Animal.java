package agh.oop.proj;

import java.util.Arrays;
import java.util.Random;

public class Animal implements IMapElement {

    private MoveDirection[] orientations = MoveDirection.values();
    private final IWorldMap map;
    private int energy;
    private final int[] genotype;
    private int life = 0;
    private int children = 0;
    public Vector2d position;
    private final IGenom mutation;
    public MoveDirection orientation;

    private final IMove movingAnimal;
    public int activeGenom = 0;
    int deathDay = 0;
    private final Random random = new Random();

    // zwierzak powstający bez podania orientacji i bez rozmnarzania
    public Animal(IWorldMap map, Vector2d position, int startEnergy, int lengthGenom, IGenom mutation, IMove movingAnimal) {
        this.map = map;
        this.position = position;
        this.activeGenom = random.nextInt(lengthGenom);
        this.movingAnimal = movingAnimal;
        int[] genotype = new int[lengthGenom];
        for (int i = 0; i < lengthGenom; i++) {
            genotype[i] = random.nextInt(7) + 1;
        }
        mutation.genomMutation(genotype);
        this.genotype = genotype;
        this.mutation = mutation;
        this.orientation = orientations[random.nextInt(orientations.length-1)];
    }

    // rozmnażanie
    public Animal(IWorldMap map, Animal parentTwo, Animal parentOne, IGenom mutation, int bornEenergy, IMove movingAnimal) {
        this.mutation = mutation;
        this.activeGenom = random.nextInt(parentOne.getGenotype().length);
        this.movingAnimal = movingAnimal;
        this.orientation = orientations[random.nextInt(orientations.length - 1)];
        this.map = map;
        this.position = parentOne.getPosition();
        this.genotype = makingParentsGenom(parentOne,parentTwo,bornEenergy);


    }

    private int[] makingParentsGenom(Animal parentOne,Animal parentTwo,int bornEenergy) {
        // usunięcie enregi rodzicą
        parentOne.loseEnergy(bornEenergy);
        parentTwo.loseEnergy(bornEenergy);
        //dodanie dzieci
        parentTwo.newChildren();
        parentOne.newChildren();
        this.energy = bornEenergy * 2;

        int whichSide = (random.nextInt() % 2); //0 - left, 1 - right

        Animal dominant = parentTwo;
        Animal lessDominant = parentOne;
        // wybranie które ma więcej energi
        if (parentOne.energy > parentTwo.energy) {
            dominant = parentOne;
            lessDominant = parentTwo;
        }

        // znalezienie ilość genów jednego rodzica i drugiego rodzica
        int cut = dominant.energy / (dominant.energy + lessDominant.energy) * dominant.getGenotype().length;

        // dwie połówki genów
        int[] firstPiece, secondPiece;
        int n = dominant.getGenotype().length;//długość genotypu
        // wybieramy i łączymy genomy
        if (whichSide == 0) {
            firstPiece = Arrays.copyOfRange(dominant.getGenotype(), 0, cut);
            secondPiece = Arrays.copyOfRange(lessDominant.getGenotype(), cut, n);
        } else {
            firstPiece = Arrays.copyOfRange(dominant.getGenotype(), n - cut, n);
            secondPiece = Arrays.copyOfRange(lessDominant.getGenotype(), 0, n - cut);
        }

        // połączenie i mutacja
        int[] res = Arrays.copyOf(secondPiece, n);
        System.arraycopy(firstPiece, 0, res, secondPiece.length, firstPiece.length);
        this.mutation.genomMutation(res);
        return res;

    }
    public int getGenom(){
        return this.activeGenom;
    }

    public void move(MoveDirection direction) {
        this.life++;
        movingAnimal.moving(this);
    }

    public void loseEnergy(int energy) {
        this.energy -= energy;
    }

    public void increaseEnergy(int energy) {
        this.energy += energy;
    }

    public int getEnergy() {
        return this.energy;
    }

    public int getLife() {
        return this.life;
    }

    public int getChildren() {
        return this.children;
    }

    public void newChildren() {
        this.children++;
    }

    public int[] getGenotype() {
        return this.genotype;
    }

    @Override
    public boolean isAnimal() {
        return true;
    }

    public boolean isDead(int day) {
        if (energy <= 0) {
            deathDay = day;
            return true;
        }
        return false;
    }

    @Override
    public Vector2d getPosition() {
        return this.position;
    }

    @Override
    public MoveDirection getOrientation() {
        return this.orientation;
    }
}