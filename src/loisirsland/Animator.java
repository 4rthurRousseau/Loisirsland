package loisirsland;

import java.util.Date;

/**
 * Animator est une classe, qui hérite des attributs et méthodes de la classe
 * Person, permettant de représenter un animateur du centre de loisirs.
 *
 * @see Person
 * @author ROUSSEAU Arthur - THOMELIN Laurine
 * @version 1.0
 */
public class Animator extends Person {

    /**
     * Constructeur permettant d'instancier un objet de type Animator
     */
    public Animator() {
        super();
    }

    /**
     * Constructeur permettant d'instancier un objet de type Animator
     *
     * @param lastname Le nom de l'animateur
     * @param firstname Le prénom de l'animateur
     *
     * @see Animator#lastname
     * @see Animator#firstname
     */
    public Animator(String lastname, String firstname) {
        super(lastname, firstname);
    }

    /**
     * Constructeur permettant d'instancier un objet de type Animator
     *
     * @param lastname Le nom de l'animateur
     * @param firstname Le prénom de l'animateur
     * @param birthdate La date de naissance de l'animateur
     *
     * @see Animator#lastname
     * @see Animator#firstname
     * @see Animator#birthdate
     */
    public Animator(String lastname, String firstname, Date birthdate) {
        super(lastname, firstname, birthdate);
    }

    /**
     * Constructeur permettant d'instancier un objet de type Animator
     *
     * @param id Identifiant de l'animateur
     * @param lastname Le nom de l'animateur
     * @param firstname Le prénom de l'animateur
     * @param birthdate La date de naissance de l'animateur
     *
     * @see Animator#id
     * @see Animator#lastname
     * @see Animator#firstname
     * @see Animator#birthdate
     */
    public Animator(int id, String lastname, String firstname, Date birthdate) {
        super(id, lastname, firstname, birthdate);
    }

    /**
     * Détermine si deux animateurs sont identiques
     *
     * @param object objet avec lequel nous comparons cet animateur
     * @return true si les deux animateurs sont identiques, faux sinon
     */
    @Override
    public boolean equals(Object object) {
        // Si l'objet n'est pas une instance d'animateur, on renvoie false
        if (!(object instanceof Animator)) {
            return false;
        }

        // Cast. de l'animateur à vérifier
        Animator animator = (Animator) object;

        return animator.getId() == id && animator.getFirstname().equals(firstname) && animator.getLastname().equals(lastname);

    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }
}
