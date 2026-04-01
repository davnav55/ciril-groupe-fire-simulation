package config;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.SimulationConfig;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Charge la configuration de la simulation depuis un fichier JSON.
 */
public class ConfigLoader {

    private final ObjectMapper objectMapper = new ObjectMapper();


    /**
     * Charge la configuration depuis le classpath (src/main/resources/).
     */
    public SimulationConfig charger(String nomFichier) throws IOException {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(nomFichier)) {
            if (is == null) {
                throw new IOException("Fichier introuvable dans le classpath : " + nomFichier);
            }
            ConfigDTO dto = objectMapper.readValue(is, ConfigDTO.class);
            return dto.toSimulationConfig();
        }
    }


    // -------------------------------------------------------------------------
    // DTO interne — sert uniquement à la désérialisation Jackson
    // On l'isole ici pour ne pas polluer SimulationConfig avec des annotations
    // -------------------------------------------------------------------------

    /**
     * DTO mappé directement depuis le JSON.
     */
    static class ConfigDTO {

        public int width;
        public int height;
        public double propagationProbability;
        public List<PositionDTO> initialFires;

        /**
         * Convertit ce DTO en objet métier {@link SimulationConfig}.
         */
        SimulationConfig toSimulationConfig() {
            // Conversion des positions {x, y} en tableaux [colonne, ligne]
            List<int[]> feux = initialFires.stream()
                    .map(pos -> new int[]{ pos.x, pos.y })
                    .toList();

            return new SimulationConfig(width, height, propagationProbability, feux);
        }
    }

    /**
     * Représente une position initiale en feu dans le fichier JSON.
     */
    static class PositionDTO {
        public int x;
        public int y;
    }
}
