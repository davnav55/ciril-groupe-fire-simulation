package model;

/**
 * Représente la grille de la forêt sous forme d'une matrice 2D de cases.
 * Convention : cells[ligne][colonne] où ligne = axe Y (de haut en bas)
 * et colonne = axe X (de gauche à droite).
 */
public class Grid {

    private final int width;        // Nombre de colonnes (axe X)
    private final int height;       // Nombre de lignes (axe Y)
    private final CellState[][] cells;


    /**
     * Crée une grille dont toutes les cases sont initialisées à ARBRE
     *
     * @param width  nombre de colonnes (doit être > 0)
     * @param height nombre de lignes (doit être > 0)
     */
    public Grid(int width, int height) {
        this.width = width;
        this.height = height;
        this.cells = new CellState[height][width];  // [ligne][colonne] = [y][x]

        // Par défaut, toutes les cases sont des arbres
        for (int ligne = 0; ligne < height; ligne++) {
            for (int colonne = 0; colonne < width; colonne++) {
                cells[ligne][colonne] = CellState.ARBRE;
            }
        }
    }

    /**
     * Retourne l'état de la case à la position (ligne, colonne).
     *
     * @param ligne   index de la ligne (Y), basé 0, depuis le haut
     * @param colonne index de la colonne (X), basé 0, depuis la gauche
     */
    public CellState getCell(int ligne, int colonne) {
        return cells[ligne][colonne];
    }

    /**
     * Modifie l'état de la case à la position (ligne, colonne).
     */
    public void setCell(int ligne, int colonne, CellState etat) {
        cells[ligne][colonne] = etat;
    }

    /**
     * Vérifie si la position (ligne, colonne) est dans les limites de la grille.
     * Utilisé par le moteur de simulation avant de propager le feu aux cases adjacentes.
     *
     * @return true si la position est valide, false sinon
     */
    public boolean isInBounds(int row, int col) {
        return row >= 0 && row < height && col >= 0 && col < width;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
