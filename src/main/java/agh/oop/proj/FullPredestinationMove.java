package agh.oop.proj;

public class FullPredestinationMove implements IMove{
    @Override
    public void moving(Animal animal) {
        animal.setActiveGenom((animal.getActiveGenom() + 1) % animal.getGenotype().length);
        animal.changerposition();
    }
}
