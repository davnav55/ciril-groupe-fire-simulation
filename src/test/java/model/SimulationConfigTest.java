package model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SimulationConfigTest {

    @Test
    void constructeur_accepteUneConfigurationValide() {
        assertDoesNotThrow(() ->
                                   new SimulationConfig(10, 10, 0.6, List.of(new int[]{ 2, 3 }))
        );
    }

    @Test
    void constructeur_leveUneExceptionSiDimensionNegative() {
        assertThrows(IllegalArgumentException.class, () ->
                new SimulationConfig(-1, 10, 0.5, List.of())
        );
    }

    @Test
    void constructeur_leveUneExceptionSiProbabiliteSuperieure1() {
        assertThrows(IllegalArgumentException.class, () ->
                new SimulationConfig(10, 10, 1.5, List.of())
        );
    }

    @Test
    void constructeur_leveUneExceptionSiProbabiliteNegative() {
        assertThrows(IllegalArgumentException.class, () ->
                new SimulationConfig(10, 10, -0.1, List.of())
        );
    }
}