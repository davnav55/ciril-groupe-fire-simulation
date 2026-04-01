package renderer;

import model.Grid;

/**
 * Affiche la grille dans la console à chaque étape de la simulation.
 *
 * <p>Responsabilité unique : rendu visuel. Complètement découplé
 * du moteur — on pourrait le remplacer par un renderer graphique
 * sans toucher à {@link engine.FireSimulation}.</p>
 */
public class ConsoleRenderer {

    /**
     * Affiche l'état courant de la grille avec le numéro d'étape.
     *
     * @param grid  la grille à afficher
     * @param etape le numéro de l'étape courante
     */
    public void afficher(Grid grid, int etape) {
        System.out.println("=== Étape " + etape + " ===");
        for (int lig = 0; lig < grid.getHeight(); lig++) {
            for (int col = 0; col < grid.getWidth(); col++) {
                System.out.print(switch (grid.getCell(lig, col)) {
                    case ARBRE -> "🌲";
                    case FEU -> "🔥";
                    case CENDRE -> "⬛";
                });
            }
            System.out.println();
        }
        System.out.println();
    }
}