package agh.oop.proj;

public class FullPredestination implements IMove{
    @Override
    public void moving(Animal animal) {
        animal.activeGenom = (animal.activeGenom + 1) % animal.getGenotype().length;
        int numberdirection = animal.getGenotype()[animal.activeGenom];
        for(int i = 0;i <= numberdirection;i++){
            animal.orientation = animal.orientation.next();
        }
        Vector2d oldPosition = animal.position;
        animal.position = oldPosition.add(animal.orientation.toUnitVector());
    }
}
