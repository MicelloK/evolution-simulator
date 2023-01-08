package agh.oop.proj;

public class FullPredestinationMove implements IMove {
    @Override
    public void moving(Animal animal) {
        animal.setActiveGenomeIdx((animal.getActiveGenomeIdx() + 1) % animal.getGenotype().length);
        animal.changerPosition();
    }
}
