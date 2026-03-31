package model;

import java.util.List;

/**
 * Contient les paramètres de configuration de la simulation.
 *
 * <p>Les valeurs sont validées dès la construction afin de détecter
 * toute erreur de configuration au démarrage du programme,
 * plutôt qu'en cours de simulation.</p>
 *
 * <p>Cette classe est immuable : une fois construite, ses valeurs ne peuvent plus être modifiées.</p>
 */
public class SimulationConfig {

    private final int width;
    private final int height;
    private final double propagationProbability;
    private final List<int[]> initialFires;         // chaque élément = [x, y]


    /**
     * Construit la configuration de la simulation.
     *
     * @param width
     * @param height
     * @param propagationProbability
     * @param initialFires
     */
    public SimulationConfig(int width,
                            int height,
                            double propagationProbability,
                            List<int[]> initialFires) {

        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Les dimensions de la grille doivent être positives");
        }
        if (propagationProbability < 0 || propagationProbability > 1) {
            throw new IllegalArgumentException("La probabilité doit être comprise entre 0 et 1");
        }

        this.width = width;
        this.height = height;
        this.propagationProbability = propagationProbability;
        this.initialFires = List.copyOf(initialFires); // copie défensive — rend la liste immuable
    }

    /**
     * Retourne le nombre de colonnes de la grille.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Retourne le nombre de lignes de la grille.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Retourne la probabilité qu'un feu se propage à une case adjacente.
     */
    public double getPropagationProbability() {
        return propagationProbability;
    }

    /**
     * Retourne la liste des positions initiales en feu.
     * La liste retournée est immuable.
     */
    public List<int[]> getInitialFires() {
        return initialFires;
    }

}
