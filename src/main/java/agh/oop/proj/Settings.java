package agh.oop.proj;

public class Settings {
    private final int mapWidth;
    private final int mapHeight;
    private final int startGrassQuantity;
    private final int eatingGrassEnergy;
    private final int grassPerDay;
    private final int startAnimalsQuantity;
    private final int startAnimalEnergy;
    private final int animalFullEnergy;
    private final int reproductionEnergy;
    private final int minimalMutationNumber;
    private final int maximalMutationNumber;
    private final int genLength;

    private final AbstractWorldMap map;
    private final IMove animalMoving;
    private final IGenome mutationVariant;

    public Settings(String[] config) throws Exception {
        mapWidth = Integer.parseInt(config[0]);
        mapHeight = Integer.parseInt(config[1]);
        startGrassQuantity = Integer.parseInt(config[2]);
        eatingGrassEnergy = Integer.parseInt(config[3]);
        grassPerDay = Integer.parseInt(config[4]);
        startAnimalsQuantity = Integer.parseInt(config[5]);
        startAnimalEnergy = Integer.parseInt(config[6]);
        animalFullEnergy = Integer.parseInt(config[7]);
        reproductionEnergy = Integer.parseInt(config[8]);
        minimalMutationNumber = Integer.parseInt(config[9]);
        maximalMutationNumber = Integer.parseInt(config[10]);
        genLength = Integer.parseInt(config[11]);

        switch (config[12]) {
            case "predestination" -> animalMoving = new FullPredestinationMove();
            case "craziness" -> animalMoving = new LittleCrazinessMove();
            default -> throw new Exception("wrong animalMoving configuration");
        }
        switch (config[13]) {
            case "correction" -> mutationVariant = new LittleCorrectionGens();
            case "random" -> mutationVariant = new FullRandomGens();
            default -> throw new Exception("wrong mutationVariant configuration");
        }
        IMoveAllowed movementDetails;
        switch (config[14]) {
            case "earth" -> movementDetails = new EarthMoveAllowed();
            case "portal" -> movementDetails = new PortalMoveAllowed();
            default -> throw new Exception("wrong movementDetails configuration");
        }
        switch (config[15]) {
            case "equators" -> map = new EquatorsMap(mapWidth, mapHeight, movementDetails, reproductionEnergy);
            case "corpses" -> map = new CorpsesMap(mapWidth, mapHeight, movementDetails, reproductionEnergy);
            default -> throw new Exception("wrong map configuration");
        }
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public int getMapHeight() {
        return mapHeight;
    }

    public int getStartGrassQuantity() {
        return startGrassQuantity;
    }

    public int getEatingGrassEnergy() {
        return eatingGrassEnergy;
    }

    public int getGrassPerDay() {
        return grassPerDay;
    }

    public int getStartAnimalsQuantity() {
        return startAnimalsQuantity;
    }

    public int getStartAnimalEnergy() {
        return startAnimalEnergy;
    }

    public int getAnimalFullEnergy() {
        return animalFullEnergy;
    }

    public int getReproductionEnergy() {
        return reproductionEnergy;
    }

    public int getMinimalMutationNumber() {
        return minimalMutationNumber;
    }

    public int getMaximalMutationNumber() {
        return maximalMutationNumber;
    }

    public int getGenLength() {
        return genLength;
    }

    public AbstractWorldMap getMap() {
        return map;
    }

    public IMove getAnimalMoving() {
        return animalMoving;
    }

    public IGenome getMutationVariant() {
        return mutationVariant;
    }
}
