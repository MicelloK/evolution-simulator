package agh.oop.proj;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ModelTest {
    @Test
    void testModel() {
        String modelWorksFineCheck = "modelWorksFine :)";
        String modelDoesNotWorkFineCheck = "modelDoesNotWorkFine :c";
        assertNotEquals(modelWorksFineCheck, modelDoesNotWorkFineCheck);
    }
}
