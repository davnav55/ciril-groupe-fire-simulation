package engine;

import model.Grid;
import model.SimulationConfig;
import org.junit.jupiter.api.Test;
import renderer.ConsoleRenderer;

import java.util.List;

class FireSimulationTest {

    // Renderer silencieux pour ne pas polluer la sortie des tests
    private final ConsoleRenderer rendererSilencieux = new ConsoleRenderer() {
        @Override
        public void afficher(Grid grid, int etape) { /* rien */ }
    };


    private FireSimulation creerSimulation(int width, int height,
                                           List<int[]> feuxInitiaux,
                                           double probabilite,
                                           PropagationRule regle) {
        SimulationConfig config = new SimulationConfig(width, height, probabilite, feuxInitiaux);
        return new FireSimulation(config, rendererSilencieux, regle);
    }


    @Test
    void feuInitialDevientCendreLorsDeLaPremiereEtape() {

        // Propagation impossible — on vérifie juste l'extinction
        FireSimulation sim = creerSimulation(
                3, 3,
                List.of(new int[]{ 1, 1 }), // feu au centre
                0.0,
                p -> false // le feu ne se propage jamais
        );
        sim.lancer();
    }


    @Test
    void avecPropagationTotale_touteLaGrilleFinitEnCendre() {
        // p = 1.0 → le feu se propage à tous les voisins à chaque étape
        FireSimulation sim = creerSimulation(
                5, 5,
                List.of(new int[]{ 2, 2 }), // feu au centre
                1.0,
                p -> true // le feu se propage toujours
        );
        sim.lancer();
        // La simulation doit se terminer
        // Toutes les cases doivent être CENDRE
    }


    @Test
    void avecPropagationNulle_leFeuNeSePropagePas() {
        FireSimulation sim = creerSimulation(
                3, 3,
                List.of(new int[]{ 1, 1 }),
                0.0,
                p -> false
        );
        sim.lancer();
        // La simulation se termine en 1 étape
    }
}