### Fire Simulation — Propagation d'un feu de forêt
Simulation de la propagation d'un feu de forêt sur une grille 2D, implémentée en Java avec Maven.

### Fonctionnement
La forêt est représentée par une grille de dimension hauteur x largeur. La simulation progresse pas à pas :

À l'étape t, chaque case en feu 🔥 :

    - s'éteint → devient cendre ⬛ (ne peut plus brûler)
    - tente de propager le feu à chacune de ses 4 cases adjacentes avec une probabilité p
    - La simulation s'arrête lorsqu'il n'y a plus aucune case en feu

### Configuration
Le fichier src/main/resources/config.json contient les paramètres de la simulation :

```json
{
  "width": 10,
  "height": 10,
  "propagationProbability": 0.6,
  "initialFires": [
    { "x": 5, "y": 5 },
    { "x": 2, "y": 3 }
  ]
}
```

| Paramètre | Type | Description |
|-----------|------|-------------|
| `width` | `int` | Nombre de colonnes de la grille |
| `height` | `int` | Nombre de lignes de la grille |
| `propagationProbability` | `double` | Probabilité de propagation (entre 0.0 et 1.0) |
| `initialFires` | `array` | Liste des positions `[x, y]` initiales en feu |

---

## Lancer la simulation
### Prérequis

- Java 17+
- Maven 3.8+

### Compilation
```bash
mvn clean package
```

### Exécution
```bash
# Avec le fichier de config par défaut (config.json dans le classpath)
java -jar target/fire-simulation.jar

# Avec un fichier de config personnalisé
java -jar target/fire-simulation.jar /chemin/vers/ma-config.json
```

### Tests

```bash
mvn test
```

---

## Exemple de sortie

```
=== Étape 0 ===
 T  T  T  T  T  T  T  T  T  T
 T  T  T  T  T  T  T  T  T  T
 T  T  F  T  T  T  T  T  T  T
 T  T  T  T  T  F  T  T  T  T
 T  T  T  T  T  T  T  T  T  T

=== Étape 1 ===
 T  T  T  T  T  T  T  T  T  T
 T  T  F  T  T  T  T  T  T  T
 T  F  .  F  T  T  T  T  T  T
 T  T  F  T  T  .  T  T  T  T
 T  T  T  T  F  T  F  T  T  T

Simulation terminée en 7 étape(s).
```

> Légende : `T` = arbre, `F` = feu, `.` = cendre

---
