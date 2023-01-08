package agh.oop.proj;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ViewTest {
    @Test
    void testModel() {
        String viewWorksFineCheck = "viewWorksFine :)";
        String viewDoesNotWorkFineCheck = "viewDoesNotWorkFine :c";
        assertNotEquals(viewWorksFineCheck, viewDoesNotWorkFineCheck);
    }
}
