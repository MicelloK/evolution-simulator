package agh.oop.proj;

public class FullPredestinationMove implements IMove{
    @Override
    public void moving(Animal animal) {
        animal.setActiveGenom((animal.getActiveGenom() + 1) % animal.getGenotype().length);
        changerposition(animal);
    }

    private void changerposition(Animal animal) {
        int numberdirection = animal.getGenotype()[animal.getActiveGenom()];
        for(int i = 0;i <= numberdirection;i++){
            animal.setOrientation(animal.getOrientation().next());
        }
        Vector2d oldPosition = animal.getPosition();
        animal.setPosition(oldPosition.add(animal.getOrientation().toUnitVector()));
        animal.positionChanged(oldPosition,animal.getPosition());
    }
}
