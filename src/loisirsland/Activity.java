package loisirsland;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Activity est une classe permettant de représenter une activité proposée par
 * le centre de loisir.
 *
 * @see Person
 * @see Animator
 * @see Children
 *
 * @author ROUSSEAU Arthur - THOMELIN Laurine
 * @version 1.0
 */
public class Activity {

    private String id;
    private String type;
    private String description;
    private Date date;
    private boolean elapsed;
    private List<Children> childrens;
    private List<Animator> animators;

    /**
     * Constructeur permettant d'instancier un objet de type Activity
     */
    public Activity() {
        childrens = new ArrayList<>();
        animators = new ArrayList<>();
    }

    /**
     * Constructeur permettant d'instancier un objet de type Activity
     *
     * @param id L'identifiant de l'activité
     * @param type Le type de l'activité
     *
     * @see Activity#id
     * @see Activity#type
     */
    public Activity(String id, String type) {
        childrens = new ArrayList<>();
        animators = new ArrayList<>();
        this.id = id;
        this.type = type;
    }

    /**
     * Constructeur permettant d'instancier un objet de type Activity
     *
     * @param id L'identifiant de l'activité
     * @param type Le type de l'activité
     * @param date La date à laquelle a lieu l'activité
     *
     * @see Activity#id
     * @see Activity#type
     * @see Activity#date
     */
    public Activity(String id, String type, Date date) {
        childrens = new ArrayList<>();
        animators = new ArrayList<>();
        this.id = id;
        this.type = type;
        this.date = date;
        updateState();
    }

    /**
     * Retourne l'identifiant de l'activité.
     *
     * @return L'identifiant de l'activité
     */
    public String getId() {
        return id;
    }

    /**
     * Met à jour l'identifiant de l'activité
     *
     * @param id Le nouvel identifiant de l'activité
     * @throws MalformedObjectException Si l'identifiant passé en paramètre est
     * vide
     */
    public void setId(String id) throws MalformedObjectException {
        if (id.isEmpty()) {
            throw new MalformedObjectException("L'ID de l'activité doit être renseigné.");
        } else {
            this.id = id;

        }
    }

    /**
     * Retourne le type de l'activité.
     *
     * @return Le type de l'activité
     */
    public String getType() {
        return type;
    }

    /**
     * Met à jour le type de l'activité.
     *
     * @param type Le nouveau type de l'activité
     * @throws MalformedObjectException Si le type passé en paramètre est vide
     */
    public void setType(String type) throws MalformedObjectException {
        if (type.isEmpty()) {
            throw new MalformedObjectException("Le type d'activité doit être renseigné.");
        } else {
            this.type = type;
        }
    }

    /**
     * Retourne la description de l'activité.
     *
     * @return La description de l'activité
     */
    public String getDescription() {
        return description;
    }

    /**
     * Met à jour la description de l'activité
     *
     * @param description La nouvelle description de l'activité
     * @throws MalformedObjectException Si la description passée en paramètre
     */
    public void setDescription(String description) throws MalformedObjectException {
        if (description.isEmpty()) {
            throw new MalformedObjectException("La description de l'activité doit être renseignée.");
        } else {
            this.description = description;
        }
    }

    /**
     * Retourne la date à laquelle a lieu l'activité.
     *
     * @return La date à laquelle a lieu l'activité
     */
    public Date getDate() {
        return date;
    }

    /**
     * Met à jour l
     *
     * @param date La nouvelle date à laquelle a lieu l'activité
     * @throws MalformedObjectException Si la date passée en paramètre est nulle
     */
    public void setDate(Date date) throws MalformedObjectException {
        if (date == null) {
            throw new MalformedObjectException("La date de l'activité doit être renseigné.");
        } else {
            this.date = date;
            updateState();
        }
    }

    /**
     * Met à jour l
     *
     * @param date La nuvelle date à laquelle a lieu l'activité
     * @throws MalformedObjectException Si la date passée en paramètre est nulle
     * ou qu'elle ne peut être résolue
     */
    public void setDate(String date) throws MalformedObjectException {
        if (date == null) {
            throw new MalformedObjectException("La date de l'activité doit être renseigné.");
        } else {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            try {
                Date d = dateFormat.parse(date);
                this.date = d;
                updateState();
            } catch (ParseException parseException) {
                throw new MalformedObjectException("Impossible de résoudre la date '" + date + "'.\nLe format attendu est dd/MM/yyyy.\nExemple : 11/10/2014");
            }
        }
    }

    /**
     * Retourne la liste des animateurs encadrants l'activité.
     *
     * @return La liste des animateurs encadrants l'activité
     */
    public List<Animator> getAnimators() {
        return animators;
    }

    /**
     * Met à jour la liste des animateurs encadrants l'activité.
     *
     * @param animators La nouvelle liste des animateurs encadrants l'activité
     */
    public void setAnimators(List<Animator> animators) {
        this.animators = animators;
    }

    /**
     * Ajoute un animateur à la liste des animateurs encadrants l'activité.
     *
     * @param animator Animateur à ajouter à la liste des animateurs encadrants
     * l'activité
     */
    public void addAnimator(Animator animator) {
        this.animators.add(animator);
    }

    /**
     * Supprime un animateur de la liste des animateurs encadrants l'activité.
     *
     * @param animator Animateur à supprimer de la liste des animateurs
     * encadrants l'activité
     */
    public void removeAnimator(Animator animator) {
        this.animators.remove(animator);
    }

    /**
     * Retourne la liste des enfants inscrits à l'activité.
     *
     * @return La liste des enfants inscrits à l'activité
     */
    public List<Children> getChildrens() {
        return childrens;
    }

    /**
     * Met à jour la liste des enfants inscrits à l'activité
     *
     * @param childrens La nouvelle liste des enfants inscrits à l'activité
     */
    public void setChildrens(List<Children> childrens) {
        this.childrens = childrens;
    }

    /**
     * Ajoute un enfant à la liste des enfants inscrits à l'activité
     *
     * @param children Enfant à ajouter à la liste des enfants inscrits à
     * l'activité
     */
    public void addChildren(Children children) {
        this.childrens.add(children);
    }

    /**
     * Supprime un enfant de la liste des enfants inscrits à l'activité
     *
     * @param children Enfant à supprimer de la liste des enfants inscrits à
     * l'activité
     */
    public void removeChildren(Children children) {
        this.childrens.remove(children);
    }

    /**
     * Retourne l'état de l'activité.
     *
     * @return vrai si l'activité est passée, faux sinon
     */
    public boolean isElapsed() {
        return elapsed;
    }

    /**
     * Met à jour l'état de l'activité
     *
     * @param elapsed Nouvel état de l'activité
     */
    public void setElapsed(boolean elapsed) {
        this.elapsed = elapsed;
    }

    /**
     * Retourne une représentation littérale de l'activité
     *
     * @return La représentation littérale de l'activité
     */
    @Override
    public String toString() {
        return String.format("%1$-10s", id) + String.format("%1$-20s", type) + String.format("%1$-40s", description) + String.format("%1$-20s", new SimpleDateFormat("dd/MM/yyyy").format(date)) + String.format("%1$-4s", elapsed);
    }

    /**
     * Détermine si deux activités sont identiques
     *
     * @param object objet avec lequel nous comparons cette activité
     * @return true si les deux activités ont un identifiant identique
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Activity)) {
            return false;
        }
        Activity activity = (Activity) object;
        return this.id.equals(activity.id);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.id);
        return hash;
    }

    /**
     *
     * Methode qui actualise la valeur de la variable 'state' Si la date de
     * l'activité est postérieure a celle du jour, on passe la valeur a vrai. Si
     * la date de l'activité est antérieure ou égale à celle du jour, on passe
     * la valeur à faux.
     */
    private void updateState() {
        if (date.compareTo(new Date()) == -1) {
            // Si la date de l'activité est antérieure à celle du jour
            elapsed = true;
        } else {
            // Sinon, c'est que la date de l'activité est postérieure à celle du jour
            elapsed = false;
        }
    }
}