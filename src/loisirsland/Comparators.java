package loisirsland;

import java.util.Comparator;

/**
 * Comparators est une classe comprenant les différents comparateurs utilisés
 * dans l'application
 *
 *
 * @author ROUSSEAU Arthur - THOMELIN Laurine
 * @version 2.0
 */
public class Comparators {
    
    /**
     * Constructeur privé de la classe Comparators
     */
    private Comparators(){
        
    }
    /**
     * Méthode permettant de récupérer le comparateur par défaut des animateurs
     * (nom, prenom).
     *
     * @return Comparateur par défaut des animateurs
     */
    public static Comparator<Animator> getAnimatorComparator() {
        Comparator<Animator> comparator = new Comparator<Animator>() {

            @Override
            public int compare(Animator a1, Animator a2) {
                String s1, s2;
                s1 = a1.getLastname() + " " + a1.getFirstname();
                s2 = a2.getLastname() + " " + a2.getFirstname();
                return s1.compareTo(s2);
            }
        };
        return comparator;
    }

    /**
     * Méthode permettant de récupérer le comparateur par défaut des enfants
     * (nom, prenom).
     *
     * @return Comparateur par défaut des enfants
     */
    public static Comparator<Children> getChildrenComparator() {
        Comparator<Children> comparator = new Comparator<Children>() {

            @Override
            public int compare(Children a1, Children a2) {
                String s1, s2;
                s1 = a1.getLastname() + " " + a1.getFirstname();
                s2 = a2.getLastname() + " " + a2.getFirstname();
                return s1.compareTo(s2);
            }
        };
        return comparator;
    }
}
