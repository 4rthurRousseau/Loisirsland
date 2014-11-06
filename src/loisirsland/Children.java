package loisirsland;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * Children est une classe, qui hérite des attributs et méthodes de la classe
 * Person, permettant de représenter un enfant du centre de loisirs.
 *
 * @see Person
 * @author ROUSSEAU Arthur - THOMELIN Laurine
 * @version 1.0
 */
public class Children extends Person {

    private String parentsPhoneNumber;

    /**
     * Constructeur permettant d'instancier un objet de type Children
     *
     */
    public Children() {
        super();
    }

    /**
     * Constructeur permettant d'instancier un objet de type Children
     *
     * @param lastname Le nom de l'enfant
     * @param firstname Le prénom de l'enfant
     *
     * @see Children#lastname
     * @see Children#firstname
     */
    public Children(String lastname, String firstname) {
        super(lastname, firstname);
    }

    /**
     * Constructeur permettant d'instancier un objet de type Children
     *
     * @param lastname Le nom de l'enfant
     * @param firstname Le prénom de l'enfant
     * @param birthdate La date de naissance de l'enfant
     *
     * @see Children#lastname
     * @see Children#firstname
     * @see Children#birthdate
     */
    public Children(String lastname, String firstname, Date birthdate) {
        super(lastname, firstname);
        this.birthdate = birthdate;
    }

    /**
     * Constructeur permettant d'instancier un objet de type Children
     *
     * @param lastname Le nom de l'enfant
     * @param firstname Le prénom de l'enfant
     * @param birthdate La date de naissance de l'enfant
     * @param parentsNumber Le numéro de téléphone des parents de l'enfant
     *
     * @see Children#lastname
     * @see Children#firstname
     * @see Children#birthdate
     * @see Children#parentsPhoneNumber
     */
    public Children(String lastname, String firstname, Date birthdate, String parentsNumber) {
        super(lastname, firstname, birthdate);
        this.parentsPhoneNumber = parentsNumber;
    }

    /**
     * Constructeur permettant d'instancier un objet de type Children
     *
     * @param id Identifiant de l'enfant
     * @param lastname Le nom de l'enfant
     * @param firstname Le prénom de l'enfant
     * @param birthdate La date de naissance de l'enfant
     * @param parentsNumber Le numéro de téléphone des parents de l'enfant
     *
     * @see Children#id
     * @see Children#lastname
     * @see Children#firstname
     * @see Children#birthdate
     * @see Children#parentsPhoneNumber
     */
    public Children(int id, String lastname, String firstname, Date birthdate, String parentsNumber) {
        super(id, lastname, firstname, birthdate);
        this.parentsPhoneNumber = parentsNumber;
    }

    /**
     * Retourne le numéro de téléphone des parents de l'enfant.
     *
     * @return Le numéro de téléphone des parents de l'enfant
     */
    public String getParentsPhoneNumber() {
        return parentsPhoneNumber;
    }

    /**
     * Met à jour le numéro de téléphone des parents de l'enfant.
     *
     * @param parentsPhoneNumber Le nouveau numéro de téléphone des parents de
     * l'enfant
     */
    public void setParentsPhoneNumber(String parentsPhoneNumber) {
        this.parentsPhoneNumber = parentsPhoneNumber;
    }

    /**
     * Détermine si deux enfants sont identiques
     *
     * @param object objet avec lequel nous comparons cet enfant
     * @return true si les deux enfants sont identiques, faux sinon
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Children)) {
            return false;
        }

        // Cast. de l'enfant à vérifier
        Children children = (Children) object;

        return children.getId() == id && children.getFirstname().equals(firstname) && children.getLastname().equals(lastname) && children.getParentsPhoneNumber().equals(parentsPhoneNumber);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.parentsPhoneNumber);
        return hash;
    }

    /**
     * Retourne une représentation littérale de l'enfant
     *
     * @return La représentation littérale de l'enfant
     */
    @Override
    public String toString() {
        String f = "%1$-10s";
        return String.format(f, id) + String.format(f, lastname) + String.format(f, firstname) + String.format("%1$-20s", new SimpleDateFormat("dd/MM/yyyy").format(birthdate)) + String.format(f, parentsPhoneNumber);
    }
} 
