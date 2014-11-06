package loisirsland;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Person est la classe représentant une Personne du centre de loisirs.
 *
 * @author ROUSSEAU Arthur - THOMELIN Laurine
 * @version 1.0
 */
public abstract class Person {

    /**
     * ID de la personne
     */
    protected int id;
    /**
     * Nom de famille de la personne
     */
    protected String lastname;
    /**
     * Prénom de la personne
     */
    protected String firstname;
    /**
     * Date de naissance de la personne
     */
    protected Date birthdate;

    /**
     * Constructeur permettant d'instancier un objet de type Person.
     */
    public Person() {
    }

    /**
     * Constructeur permettant d'instancier un objet de type Person.
     *
     * @param lastname Le nom de la personne
     * @param firstname Le prénom de la personne
     *
     * @see Person#lastname
     * @see Person#firstname
     */
    public Person(String lastname, String firstname) {
        this.lastname = lastname;
        this.firstname = firstname;
    }

    /**
     * Constructeur permettant d'instancier un objet de type Person.
     *
     * @param lastname Le nom de la personne
     * @param firstname Le prénom de la personne
     * @param birthdate La date de naissance de la personne
     *
     * @see Person#lastname
     * @see Person#firstname
     * @see Person#birthdate
     */
    public Person(String lastname, String firstname, Date birthdate) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.birthdate = birthdate;
    }

    /**
     * Constructeur permettant d'instancier un objet de type Person.
     *
     * @param id Identifiant de la personne
     * @param lastname Le nom de la personne
     * @param firstname Le prénom de la personne
     * @param birthdate La date de naissance de la personne
     *
     * @see Person#id
     * @see Person#lastname
     * @see Person#firstname
     * @see Person#birthdate
     */
    public Person(int id, String lastname, String firstname, Date birthdate) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.birthdate = birthdate;
        this.id = id;
    }

    /**
     * Retourne l'identifiant de la personne.
     *
     * @return L'identifiant de la personne
     */
    public int getId() {
        return id;
    }

    /**
     * Met à jour l'identifiant de la personne.
     *
     * @param id Le nouvel identifiant de la personne
     * @throws MalformedObjectException Si l'identifiant de la personne est
     * inférieur ou égal à zéro
     * @see MalformedObjectException
     */
    public void setId(int id) throws MalformedObjectException {
        // Si l'identifiant est inférieur ou égal à zéro, on lève une exception
        if (id <= 0) {
            throw new MalformedObjectException("L'ID de cette personne doit être renseigné.");
        } else {
            // Sinon, on met à jour l'identifiant de la personne
            this.id = id;

        }
    }

    /**
     * Retourne le nom de la personne
     *
     * @return Le nom de la personne
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Met à jour le nom de la personne.
     *
     * @param lastname Le nouveau nom de la personne
     * @throws MalformedObjectException Si le nom de famille passé en paramètre
     * est vide
     * @see MalformedObjectException
     */
    public void setLastname(String lastname) throws MalformedObjectException {
        // Si la chaîne de caractères est vide, on lève une exception
        if (lastname.isEmpty()) {
            throw new MalformedObjectException("Le nom doit être renseigné.");
        } else {
            // Sinon, on met à jour le nom de famille de la personne
            this.lastname = lastname;
        }
    }

    /**
     * Retourne le prénom de la personne.
     *
     * @return Le prénom de la personne
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Met à jour le prénom de la personne.
     *
     * @param firstname Le nouveau prénom de la personne
     * @throws MalformedObjectException Si le prénom passé en paramètre est vide
     * @see MalformedObjectException
     */
    public void setFirstname(String firstname) throws MalformedObjectException {
        // Si la chaîne de caractères est vide, on lève une exception
        if (firstname.isEmpty()) {
            throw new MalformedObjectException("Le prénom doit être renseigné.");
        } else {
            // Sinon, on met à jour le prénom de la personne
            this.firstname = firstname;
        }
    }

    /**
     * Retourne la date de naissance de la personne.
     *
     * @return La date de naissance de la personne
     */
    public Date getBirthdate() {
        return birthdate;
    }

    /**
     * Met à jour la date de naissance de la personne.
     *
     * @param date La nouvelle date de naissance de la personne
     * @throws MalformedObjectException Si la date de naissance est nulle
     * @see MalformedObjectException
     */
    public void setBirthdate(Date date) throws MalformedObjectException {
        // Si la date est nulle, on lève une exception
        if (date == null) {
            throw new MalformedObjectException("La date de naissance doit être renseignée");
        } else {
            // Sinon, on met à jour la date de naissance de la personne
            this.birthdate = date;
        }
    }

    /**
     * Met à jour la date de naissance de la personne.
     *
     * @param date La nouvelle date de naissance de la personne
     * @throws MalformedObjectException Si la date de naissance est nulle ou
     * qu'elle ne peut être résolue
     * @see MalformedObjectException
     */
    public void setBirthdate(String date) throws MalformedObjectException {
        // Si la date est nulle, on lève une exception
        if (date == null) {
            throw new MalformedObjectException("La date de naissance doit être renseignée.");
        } else {
            // Sinon, on va essayer de la convertir
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            // Si on arrive à parser la date passée en paramètre
            try {
                Date d = dateFormat.parse(date);

                // Alors on met à jour la date de naissance de la personne
                this.birthdate = d;
            } catch (ParseException parseException) {
                // Sinon, on lève une exception
                throw new MalformedObjectException("Impossible de résoudre la date '" + date + "'.\nLe format attendu est dd/MM/yyyy.\nExemple : 11/10/2014");
            }
        }
    }

    /**
     * Retourne une représentation littérale de la personne
     *
     * @return La représentation littérale de la personne
     */
    @Override
    public String toString() {
        String f = "%1$-10s";
        return String.format(f, id) + String.format(f, lastname) + String.format(f, firstname) + String.format("%1$-20s", new SimpleDateFormat("dd/MM/yyyy").format(birthdate));
    }
} 