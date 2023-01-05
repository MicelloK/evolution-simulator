package agh.oop.proj;

public class Settings {
    private final String name;
    private final int mapWidth;
    private final int mapHeight;
    private final int startGrassQuantity;
    private final int eatingGrassEnergy;
    private final int grassPerDay;
    private final int startAnimalsQuantity;
    private final int startAnimalEnergy;
    private final int animalFullEnergy;
    private final int reproductionLostEnergy;
    private final int genLength;

    private final AbstractWorldMap map;
    private final IMove animalMoving;
    private final IGenome mutationVariant;

    public Settings(String configName, String[] config) throws Exception {
        name = configName;
        mapWidth = Integer.parseInt(config[0]);
        mapHeight = Integer.parseInt(config[1]);
        startGrassQuantity = Integer.parseInt(config[2]);
        eatingGrassEnergy = Integer.parseInt(config[3]);
        grassPerDay = Integer.parseInt(config[4]);
        startAnimalsQuantity = Integer.parseInt(config[5]);
        startAnimalEnergy = Integer.parseInt(config[6]);
        animalFullEnergy = Integer.parseInt(config[7]);
        reproductionLostEnergy = Integer.parseInt(config[8]);
        int minimalMutationNumber = Integer.parseInt(config[9]);
        int maximalMutationNumber = Integer.parseInt(config[10]);
        genLength = Integer.parseInt(config[11]);

        if (mapWidth <= 0 || mapHeight <= 0) {
            throw new Exception("wrong map dimension");
        }
        if (startGrassQuantity < 0 || startGrassQuantity > mapWidth * mapHeight) {
            throw new Exception("wrong startGrassQuantity config");
        }
        if (eatingGrassEnergy < 0) {
            throw new Exception("wrong eatingGrassEnergy config");
        }
        if (grassPerDay < 0) {
            throw new Exception("wrong grassPerDay config");
        }
        if (startAnimalsQuantity <= 0 || startAnimalEnergy <= 0) {
            throw new Exception("wrong animal start config");
        }
        if (animalFullEnergy < 0 || reproductionLostEnergy < 0) {
            throw new Exception("wrong reproductionLostEnergy/animalFullEnergy config");
        }
        if (minimalMutationNumber < 0 || minimalMutationNumber > maximalMutationNumber || genLength <= 0) {
            throw new Exception("wrong gen/mutation config");
        }

        switch (config[12]) {
            case "Predestination" -> animalMoving = new FullPredestinationMove();
            case "Craziness" -> animalMoving = new LittleCrazinessMove();
            default -> throw new Exception("wrong animalMoving configuration");
        }
        switch (config[13]) {
            case "Correction" -> mutationVariant = new LittleCorrectionGens();
            case "Random" -> mutationVariant = new FullRandomGens(maximalMutationNumber, minimalMutationNumber);
            default -> throw new Exception("wrong mutationVariant configuration");
        }
        IMoveAllowed movementDetails;
        switch (config[14]) {
            case "Earth" -> movementDetails = new EarthMoveAllowed();
            case "Portal" -> movementDetails = new PortalMoveAllowed();
            default -> throw new Exception("wrong movementDetails configuration");
        }
        switch (config[15]) {
            case "Equators" -> map = new EquatorsMap(mapWidth, mapHeight, movementDetails, reproductionLostEnergy);
            case "Corpses" -> map = new CorpsesMap(mapWidth, mapHeight, movementDetails, reproductionLostEnergy);
            default -> throw new Exception("wrong map configuration");
        }
    }

    public String getName() {
        return name;
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

    public int getReproductionLostEnergy() {
        return reproductionLostEnergy;
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
