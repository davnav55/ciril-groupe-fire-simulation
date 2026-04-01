import config.ConfigLoader;
import engine.FireSimulation;
import model.SimulationConfig;
import renderer.ConsoleRenderer;

import java.io.IOException;

/**
 * Point d'entrée du programme.
 * Charge la configuration, initialise et lance la simulation.
 */
public class Application {

    public static void main(String[] args) {
        // Chemin du fichier de config passé en argument, "config.json" par défaut
        String cheminConfig = args.length > 0 ? args[0] : "config.json";

        try {
            SimulationConfig config = new ConfigLoader().charger(cheminConfig);
            ConsoleRenderer renderer = new ConsoleRenderer();
            FireSimulation.creer(config, renderer).lancer();
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier de configuration : " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Configuration invalide : " + e.getMessage());
        }
    }
}