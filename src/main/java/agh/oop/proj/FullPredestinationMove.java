package agh.oop.proj;

public class FullPredestinationMove implements IMove{
    @Override
    public void moving(Animal animal) {
        animal.activeGenom = (animal.activeGenom + 1) % animal.getGenotype().length;
        int numberdirection = animal.getGenotype()[animal.activeGenom];
        for(int i = 0;i <= numberdirection;i++){
            animal.setOrientation(animal.getOrientation().next());
        }
        Vector2d oldPosition = animal.getPosition();
        animal.setPosition(oldPosition.add(animal.getOrientation().toUnitVector()));
    }
}
