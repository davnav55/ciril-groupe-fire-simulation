package engine;

import model.CellState;
import model.Grid;
import model.SimulationConfig;
import renderer.ConsoleRenderer;

import java.util.Random;

/**
 * Moteur de simulation de propagation du feu.
 *
 * <p>À chaque étape :</p>
 * <ol>
 *   <li>Snapshot des cases en feu au début de l'étape.</li>
 *   <li>Propagation vers les 4 voisins selon la {@link PropagationRule}.</li>
 *   <li>Extinction des cases en feu (→ cendre).</li>
 * </ol>
 */
public class FireSimulation {

    private final Grid grid;
    private final double probabilitePropagation;
    private final PropagationRule reglePropagation;
    private final ConsoleRenderer renderer;
    private final Random random;

    // Les 4 directions : haut, bas, gauche, droite
    private static final int[][] DIRECTIONS = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    /**
     * Constructeur par défaut — règle de propagation probabiliste.
     */
    public static FireSimulation creer(SimulationConfig config, ConsoleRenderer renderer) {
        Random random = new Random();
        return new FireSimulation(config, renderer, p -> random.nextDouble() < p);
    }

    /**
     * Constructeur complet — règle de propagation injectable (utile pour les tests).
     */
    public FireSimulation(SimulationConfig config,
                          ConsoleRenderer renderer,
                          PropagationRule reglePropagation) {
        this.probabilitePropagation = config.getPropagationProbability();
        this.renderer = renderer;
        this.reglePropagation = reglePropagation;
        this.random = new Random();
        this.grid = new Grid(config.getWidth(), config.getHeight());

        for (int[] pos : config.getInitialFires()) {
            int col = pos[0];
            int lig = pos[1];
            if (grid.isInBounds(lig, col)) {
                grid.setCell(lig, col, CellState.FEU);
            }
        }
    }

    /**
     * Lance la simulation jusqu'à extinction complète du feu.
     */
    public void lancer() {
        int etape = 0;
        while (ilResteUnFeu()) {
            renderer.afficher(grid, etape);
            etape();
            etape++;
        }
        renderer.afficher(grid, etape);     // état final
        System.out.println("Simulation terminée en " + etape + " étape(s).");
    }

    /**
     * Exécute une étape : propagation puis extinction.
     */
    private void etape() {
        boolean[][] enFeu = snapshot();

        for (int lig = 0; lig < grid.getHeight(); lig++) {
            for (int col = 0; col < grid.getWidth(); col++) {
                if (enFeu[lig][col]) {
                    propager(lig, col);
                    grid.setCell(lig, col, CellState.CENDRE);
                }
            }
        }
    }

    /**
     * Snapshot booléen des cases en feu en début d'étape.
     */
    private boolean[][] snapshot() {
        boolean[][] enFeu = new boolean[grid.getHeight()][grid.getWidth()];
        for (int lig = 0; lig < grid.getHeight(); lig++)
            for (int col = 0; col < grid.getWidth(); col++)
                enFeu[lig][col] = grid.getCell(lig, col) == CellState.FEU;
        return enFeu;
    }

    /**
     * Tente de propager le feu aux 4 voisins de (lig, col).
     */
    private void propager(int lig, int col) {
        for (int[] d : DIRECTIONS) {
            int vLig = lig + d[0];
            int vCol = col + d[1];
            if (grid.isInBounds(vLig, vCol)
                    && grid.getCell(vLig, vCol) == CellState.ARBRE
                    && reglePropagation.sePropageCetteEtape(probabilitePropagation)) {
                grid.setCell(vLig, vCol, CellState.FEU);
            }
        }
    }

    /**
     * Vérifie s'il reste au moins une case en feu.
     */
    private boolean ilResteUnFeu() {
        for (int lig = 0; lig < grid.getHeight(); lig++)
            for (int col = 0; col < grid.getWidth(); col++)
                if (grid.getCell(lig, col) == CellState.FEU)
                    return true;
        return false;
    }

}