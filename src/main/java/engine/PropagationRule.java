package engine;

/**
 * Définit la règle de propagation du feu à une case voisine.
 *
 */
@FunctionalInterface
public interface PropagationRule {
    /**
     * Détermine si le feu se propage lors d'une tentative.
     *
     * @param probabilite la probabilité de propagation (entre 0.0 et 1.0)
     * @return true si le feu se propage, false sinon
     */
    boolean sePropageCetteEtape(double probabilite);
}