package loisirsland;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

/**
 * Loisirsland est la classe qui permet de matérialiser le centre de loisirs.
 *
 * @author ROUSSEAU Arthur - THOMELIN Laurine
 * @version 2.0
 */
public class Loisirsland {

    /**
     * Scanner utilisé tout au long de l'application pour récupérer la saisie de
     * l'utilisateur.
     */
    public static Scanner scanner;
    /**
     * Map permettant de stocker l'ensemble des animateurs du centre de loisirs.
     */
    public static Map<Integer, Animator> animators;
    /**
     * Map permettant de stocker l'ensemble des enfants du centre de loisirs.
     */
    public static Map<Integer, Children> childrens;
    /**
     * Map permettant de stocker l'ensemble des activités proposées par le
     * centre de loisirs.
     */
    public static Map<String, Activity> activities;
    /**
     * Map permettant de stocker l'ensemble des options disponibles pour un
     * panel donné.
     */
    public static Map<Integer, String> options;
    /**
     * Entier permettant de définir le panel dans lequel l'utilisateur est
     * actuellement.
     */
    public static int currentPanel = 0;
    /**
     * Activité permettant, lors de la modification, de savoir quelle est
     * l'activité à modifier.
     */
    public static Activity currentActivity = null;
    /**
     * Personne permettant, lors de l'affichage des activités, de savoir quel
     * est la personne à utiliser.
     */
    public static Person currentPerson = null;
    /**
     * Animateur permettant, lors de la modification, de savoir quel est
     * l'animateur à modifier.
     */
    public static Animator currentAnimator = null;
    /**
     * Enfant permettant, lors de la modification, de savoir quel est l'enfant à
     * modifier.
     */
    public static Children currentChildren = null;
    /**
     * Liste d'activités permettant, lors de la recherche d'activités, de
     * stocker les activités correspondantes au critère de recherche
     */
    public static List<Activity> currentActivities = null;
    /**
     * Comparateur utilisé pour trier les animateurs
     */
    public static Comparator<Animator> animatorsComparator = Comparators.getAnimatorComparator();
    /**
     * Comparateur utilisé pour trier les enfants
     */
    public static Comparator<Children> childrensComparator = Comparators.getChildrenComparator();
    // Constantes relatives aux panels de gestion d'objet
    private static final int ADD = 1;
    private static final int MODIFY = 2;
    private static final int REMOVE = 3;
    private static final int SHOW = 4;
    private static final int BACK = 9;
    // Constantes relatives aux changements de panel
    private static final int MENU_PANEL = 0;
    private static final int ACTIVITIES_PANEL = 1;
    private static final int ANIMATORS_PANEL = 2;
    private static final int CHILDRENS_PANEL = 3;
    private static final int SHOW_PANEL = 4;
    private static final int IMPORT_DATA_PANEL = 5;
    private static final int EDIT_ANIMATOR_PANEL = 6;
    private static final int EDIT_CHILDREN_PANEL = 7;
    private static final int ADD_CHILDREN_PANEL = 8;
    private static final int ADD_ANIMATOR_PANEL = 9;
    private static final int SHOW_BY_DATE_PANEL = 10;
    private static final int SHOW_BY_STATE_PANEL = 11;
    private static final int SHOW_RESULTS_PANEL = 12;
    // Constantes relatives à tous les panels
    private static final int HELP = 8;
    private static final int QUIT = 9;
    // Constantes des chaînes de caractères fréquemment utilisées
    private static final String CHOICE_NUMBER_SEPARATOR = ". ";
    private static final String STR_QUIT = "Quitter le programme";
    private static final String STR_BACK = "Retour au menu principal";
    private static final String STR_HELP = "Réafficher ce message";
    private static final String STR_ERROR = "Erreur : ";
    // Constantes liées au formattage des affichages
    private static final String FORMAT_5 = "%1$-5s";
    private static final String FORMAT_10 = "%1$-10s";
    private static final String FORMAT_20 = "%1$-20s";
    private static final String FORMAT_40 = "%1$-40s";
    // Constantes liées au formattage des entêtes
    private static final String STR_ACTIVITY_HEADER = String.format(FORMAT_10, "ID") + String.format(FORMAT_20, "Type") + String.format(FORMAT_40, "Description") + String.format(FORMAT_20, "Date") + String.format(FORMAT_5, "Passée");
    private static final String STR_ANIMATOR_HEADER = String.format(FORMAT_10, "ID") + String.format(FORMAT_10, "Nom") + String.format(FORMAT_10, "Prénom") + String.format(FORMAT_20, "Date de naissance");
    private static final String STR_CHILDREN_HEADER = String.format(FORMAT_10, "ID") + String.format(FORMAT_10, "Nom") + String.format(FORMAT_10, "Prénom") + String.format(FORMAT_20, "Date de naissance") + String.format(FORMAT_10, "Numéro de téléphone des parents");
    // Constantes liées au menu principal
    private static final int EXPORT_DATA = 6;
    private static final int EDIT_ACTIVITY = 5;
    // Constantes liées à l'import des données
    private static final int REMEMBER_YES = 1;
    private static final int REMEMBER_NO = 2;
    private static final int KEEP = 1;
    private static final int EDIT = 2;
    private static final int OVERWRITE = 3;
    // Constantes liées à la modification des activités
    private static final int EDIT_ACTIVITY_TYPE = 1;
    private static final int EDIT_ACTIVITY_DESCRIPTION = 2;
    private static final int EDIT_ACTIVITY_DATE = 3;
    private static final int EDIT_ACTIVITY_ADD_ANIMATORS = 4;
    private static final int EDIT_ACTIVITY_ADD_CHILDRENS = 5;
    private static final int EDIT_ACTIVITY_REM_ANIMATORS = 6;
    private static final int EDIT_ACTIVITY_REM_CHILDRENS = 7;
    private static final int EDIT_ACTIVITY_SHOW = 8;
    // Constantes liées à la modification des personnes
    private static final int EDIT_PERSON_FIRSTNAME = 1;
    private static final int EDIT_PERSON_LASTNAME = 2;
    private static final int EDIT_PERSON_BIRTHDATE = 3;
    // Constantes liées à la modification des animateurs
    private static final int EDIT_ANIMATOR_SHOW = 4;
    // Constantes liées à la modification des enfants
    private static final int EDIT_CHILDREN_PHONE = 4;
    private static final int EDIT_CHILDREN_SHOW = 5;
    // Constantes liées à l'ajout de personnes à une activité
    private static final int EDIT_ACTIVITY_ADD_NEW = 1;
    private static final int EDIT_ACTIVITY_ADD_EXISTING = 2;
    // Constantes liées à l'affichage des activités
    private static final int SHOW_BY_ANIMATOR = 1;
    private static final int SHOW_BY_CHILDREN = 2;
    private static final int SHOW_BY_DATE = 3;
    private static final int SHOW_BY_ID = 4;
    private static final int SHOW_BY_STATE = 5;
    // Constantes liées à l'affichage des activités par date
    private static final int SHOW_BY_DATE_EQUALS = 1;
    private static final int SHOW_BY_DATE_SUP = 2;
    private static final int SHOW_BY_DATE_INF = 3;
    // Constantes liées à l'affichage des activités par état
    private static final int SHOW_BY_STATE_ELAPSED = 1;
    private static final int SHOW_BY_STATE_COMING = 2;
    // Constantes liées à l'affichage des résultats de la recherche
    private static final int SHOW_RESULTS = 1;
    private static final int SHOW_SIMPLE_RESULTS = 2;
    private static final int SHOW_DETAILED_RESULTS = 3;

    /**
     * Méthode principale permettant d'exécuter l'application.
     *
     * @param args Arguments à utiliser au sein de l'application
     */
    public static void main(String[] args) {
        Loisirsland loisirsland = new Loisirsland();
        loisirsland.run();
    }

    /**
     * Méthode permettant d'instancier les objets nécessaires et de commencer
     * les traitements
     */
    public void run() {
        // Définition du panel actuel et des options disponibles
        currentPanel = MENU_PANEL;
        options = getOptions();

        // Instanciation des Maps
        activities = new TreeMap<>();
        animators = new HashMap<>();
        childrens = new HashMap<>();

        // Instanciation d'un nouveau scanner
        scanner = new Scanner(System.in);

        // Affichage de l'écran d'accueil
        System.out.println("Bienvenue dans l'application de gestion du centre de loisirs");
        boolean exit = false;
        boolean refreshPanel;
        int choice;

        // Affichage du menu de sélection
        show(options);

        do {
            // Récupération du choix
            choice = getChoice();

            // Si le choix fait n'est pas de quitter l'application...
            if (choice != QUIT) {
                // Alors on lance l'action liée au choix fait
                refreshPanel = action(choice);

                // Si il est nécessaire d'actualiser le panel (parcequ'on a changé d'écran par exemple...)
                if (refreshPanel) {
                    // On récupère les nouvelles options puis on les affiche
                    options = getOptions();
                    show(options);
                }
            } else {
                // Si le choix fait est de quitter l'application...
                // Si le panel actuel est différent du menu principal
                if (currentPanel != MENU_PANEL) {
                    // Alors on lance l'action liée au choix fait
                    refreshPanel = action(choice);

                    // Si il est nécessaire d'actualiser le panel (parcequ'on a fait un retour en arrière)
                    if (refreshPanel) {
                        // On récupère les nouvelles options puis on les affiche
                        options = getOptions();
                        show(options);
                    }
                } else {
                    // Sinon, c'est qu'on veut 'vraiment' quitter l'application
                    exit = true;
                }
            }
        } while (!exit);
        System.out.println("A bientôt! :)");
        // Tant qu'on ne veut pas 'vraiment' quitter l'application
    }

    /**
     * Fonction qui permet : la définition du panel dans lequel nous sommes et
     * de l'action à effectuer, de gérer les changements de panel et les
     * traitements qui y sont associés.
     *
     * @param choice Choix qui a été fait par l'utilisateur
     * @return vrai si ce choix doit nous faire changer de panel, faux sinon
     */
    public boolean action(int choice) {
        switch (currentPanel) {
            // Si nous sommes actuellement dans le panel 'Menu principal'
            case MENU_PANEL: {
                return menuPanelSwitch(choice);
            }

            // Si nous sommes actuellement dans le panel 'Gestion des activités'
            case ACTIVITIES_PANEL: {
                return activitiesPanelSwitch(choice);
            }

            // Si nous sommes actuellement dans le panel 'Gestion des animateurs'
            case ANIMATORS_PANEL: {
                return animatorsPanelSwitch(choice);
            }

            // Si nous sommes actuellement dans le panel 'Gestion des enfants'
            case CHILDRENS_PANEL: {
                return childrensPanelSwitch(choice);
            }

            // Si nous sommes actuellement dans le panel 'Affichage des activités'
            case SHOW_PANEL: {
                return showPanelSwitch(choice);
            }
            // Si nous sommes actuellement dans le panel 'Modification d'une activité'
            case EDIT_ACTIVITY: {
                return modifyActivityPanelSwitch(choice);
            }

            // Si nous sommes actuellement dans le panel 'Modification d'un animateur'
            case EDIT_ANIMATOR_PANEL: {
                return modifyAnimatorPanelSwitch(choice);
            }

            // Si nous sommes actuellement dans le panel 'Modification d'un enfant'
            case EDIT_CHILDREN_PANEL: {
                return modifyChildrenPanelSwitch(choice);
            }

            // Si nous sommes actuellement dans le panel 'Ajouter un animateur à cette activité'
            case ADD_ANIMATOR_PANEL: {
                return addAnimatorPanelSwitch(choice);
            }

            // Si nous sommes actuellement dans le panel 'Inscrire un enfant à cette activité'
            case ADD_CHILDREN_PANEL: {
                return addChildrenPanelSwitch(choice);
            }
            case SHOW_BY_DATE_PANEL: {
                return showByDatePanelSwitch(choice);
            }
            case SHOW_BY_STATE_PANEL: {
                return showByStatePanelSwitch(choice);
            }
            case SHOW_RESULTS_PANEL: {
                return showResultsPanelSwitch(choice);
            }

            // Si nous sommes dans un panel indéterminable...
            default: {
                return false;
            }
        }
    }

    /**
     * Fonction permettant de lancer les actions liées au panel 'Menu
     * principal'.
     *
     * @param choice Choix qui a été fait par l'utilisateur
     * @return vrai si ce choix doit nous faire changer de panel, faux sinon
     * @see Loisirsland#action(int)
     */
    private boolean menuPanelSwitch(int choice) {
        switch (choice) {
            case ACTIVITIES_PANEL: {
                currentPanel = ACTIVITIES_PANEL;
                return true;
            }
            case ANIMATORS_PANEL: {
                currentPanel = ANIMATORS_PANEL;
                return true;
            }
            case CHILDRENS_PANEL: {
                currentPanel = CHILDRENS_PANEL;
                return true;
            }
            case SHOW_PANEL: {
                if (!activities.isEmpty()) {
                    currentPanel = SHOW_PANEL;
                    return true;
                } else {
                    System.out.println("La liste des activités est vide.");
                    return false;
                }
            }
            case IMPORT_DATA_PANEL: {
                importJSON();
                return false;
            }
            case EXPORT_DATA: {
                if (!activities.isEmpty()) {
                    exportJSON();
                } else {
                    System.out.println("Il n'y a rien à exporter pour l'instant.");
                    return false;
                }
                return false;
            }
            case HELP: {
                show(options);
                return false;
            }
            case QUIT: {
                return false;
            }
            default: {
                // Evolution possible : logger ce genre de cas
                return false;
            }
        }
    }

    /**
     * Fonction permettant de lancer les actions liées au panel 'Gestion des
     * activités'.
     *
     * @param choice Choix qui a été fait par l'utilisateur
     * @return vrai si ce choix doit nous faire changer de panel, faux sinon
     * @see Loisirsland#action(int)
     */
    private boolean activitiesPanelSwitch(int choice) {
        switch (choice) {
            case ADD: {
                addActivity();
                return false;
            }
            case MODIFY: {
                if (!activities.isEmpty()) {
                    currentPanel = EDIT_ACTIVITY;
                    currentActivity = getActivity();
                    return true;
                } else {
                    System.out.println("Il n'y a aucune activité à modifier.");
                    return false;
                }
            }
            case REMOVE: {
                if (!activities.isEmpty()) {
                    deleteActivity();
                } else {
                    System.out.println("Il n'y a aucune activité à supprimer.");
                }
                return false;
            }
            case SHOW: {
                if (!activities.isEmpty()) {
                    System.out.println(STR_ACTIVITY_HEADER);
                    for (Activity activity : activities.values()) {
                        System.out.println(activity.toString());
                    }
                } else {
                    System.out.println("Aucune activité à afficher pour l'instant...");
                }
                return false;
            }
            case HELP: {
                show(options);
                return false;
            }
            case BACK: {
                currentPanel = MENU_PANEL;
                return true;
            }
            default: {
                // Evolution possible : logger ce genre de cas
                return false;
            }
        }
    }

    /**
     * Fonction permettant de lancer les actions liées au panel 'Gestion des
     * animateurs'.
     *
     * @param choice Choix qui a été fait par l'utilisateur
     * @return vrai si ce choix doit nous faire changer de panel, faux sinon
     * @see Loisirsland#action(int)
     */
    private boolean animatorsPanelSwitch(int choice) {
        switch (choice) {
            case ADD: {
                addAnimator();
                return false;
            }
            case MODIFY: {
                if (!animators.isEmpty()) {
                    currentPanel = EDIT_ANIMATOR_PANEL;
                    currentAnimator = getAnimator();
                    return true;
                } else {
                    System.out.println("Il n'y a aucun animateur à modifier.");
                    return false;
                }

            }
            case REMOVE: {
                if (!animators.isEmpty()) {
                    deleteAnimator();
                } else {
                    System.out.println("Il n'y a aucun animateur à supprimer.");
                }
                return false;
            }
            case SHOW: {
                if (!animators.isEmpty()) {
                    System.out.println(STR_ANIMATOR_HEADER);
                    // Tri de la liste des animateurs
                    List<Animator> list = new ArrayList<>(animators.values());
                    Collections.sort(list, animatorsComparator);

                    for (Animator animator : list) {
                        System.out.println(animator.toString());
                    }
                } else {
                    System.out.println("Aucun animateur à afficher pour l'instant...");
                }
                return false;
            }
            case HELP: {
                show(options);
                return false;
            }
            case BACK: {
                currentPanel = MENU_PANEL;
                return true;
            }
            default: {
                // Evolution possible : logger ce genre de cas
                return false;
            }
        }
    }

    /**
     * Fonction permettant de lancer les actions liées au panel 'Gestion des
     * enfants'.
     *
     * @param choice Choix qui a été fait par l'utilisateur
     * @return vrai si ce choix doit nous faire changer de panel, faux sinon
     * @see Loisirsland#action(int)
     */
    private boolean childrensPanelSwitch(int choice) {
        switch (choice) {
            case ADD: {
                addChildren();
                return false;
            }
            case MODIFY: {
                if (!childrens.isEmpty()) {
                    currentPanel = EDIT_CHILDREN_PANEL;
                    currentChildren = getChildren();
                    return true;
                } else {
                    System.out.println("Il n'y a aucun enfant à modifier.");
                    return false;
                }

            }
            case REMOVE: {
                if (!childrens.isEmpty()) {
                    deleteChildren();
                } else {
                    System.out.println("Il n'y a aucun enfant à supprimer.");
                }
                return false;
            }
            case SHOW: {
                if (!childrens.isEmpty()) {
                    System.out.println(STR_CHILDREN_HEADER);
                    // Tri de la liste des enfants
                    List<Children> list = new ArrayList<>(childrens.values());
                    Collections.sort(list, childrensComparator);
                    for (Children children : list) {
                        System.out.println(children.toString());
                    }
                } else {
                    System.out.println("Aucun enfant à afficher pour l'instant...");
                }
                return false;
            }
            case HELP: {
                show(options);
                return false;
            }
            case BACK: {
                currentPanel = MENU_PANEL;
                return true;
            }
            default: {
                // Evolution possible : logger ce genre de cas
                return false;
            }
        }
    }

    /**
     * Fonction permettant de lancer les actions liées au panel 'Affichage des
     * activités'.
     *
     * @param choice Choix qui a été fait par l'utilisateur
     * @return vrai si ce choix doit nous faire changer de panel, faux sinon
     * @see Loisirsland#action(int)
     */
    private boolean showPanelSwitch(int choice) {
        switch (choice) {
            case SHOW_BY_ANIMATOR: {
                currentAnimator = getAnimator();
                currentActivities = getActivitiesByAnimator(currentAnimator);
                showResults();
                currentPanel = SHOW_RESULTS_PANEL;
                return true;
            }
            case SHOW_BY_CHILDREN: {
                currentChildren = getChildren();
                currentActivities = getActivitiesByChildren(currentChildren);
                showResults();
                currentPanel = SHOW_RESULTS_PANEL;
                return true;
            }
            case SHOW_BY_DATE: {
                currentPanel = SHOW_BY_DATE_PANEL;
                return true;
            }
            case SHOW_BY_ID: {
                currentActivity = getActivity();
                currentActivities = getActivitiesByActivity(currentActivity);
                showResults();
                currentPanel = SHOW_RESULTS_PANEL;
                return true;
            }
            case SHOW_BY_STATE: {
                currentPanel = SHOW_BY_STATE_PANEL;
                return true;
            }
            case HELP: {
                show(options);
                return false;
            }
            case QUIT: {
                currentPanel = MENU_PANEL;
                return true;
            }
            default: {
                // Evolution possible : logger ce genre de cas
                return false;
            }
        }
    }

    /**
     * Fonction permettant de lancer les actions liées au panel 'Affichage des
     * activités par date'.
     *
     * @param choice Choix qui a été fait par l'utilisateur
     * @return vrai si ce choix doit nous faire changer de panel, faux sinon
     * @see Loisirsland#action(int)
     */
    public boolean showByDatePanelSwitch(int choice) {
        switch (choice) {
            case SHOW_BY_DATE_EQUALS: {
                currentActivities = getActivitiesByDate(0);
                if (!currentActivities.isEmpty()) {
                    showResults();
                    currentPanel = SHOW_RESULTS_PANEL;
                    return true;
                } else {
                    return false;
                }
            }
            case SHOW_BY_DATE_SUP: {
                currentActivities = getActivitiesByDate(1);
                if (!currentActivities.isEmpty()) {
                    showResults();
                    currentPanel = SHOW_RESULTS_PANEL;
                    return true;
                } else {
                    return false;
                }
            }
            case SHOW_BY_DATE_INF: {
                currentActivities = getActivitiesByDate(-1);
                if (!currentActivities.isEmpty()) {
                    showResults();
                    currentPanel = SHOW_RESULTS_PANEL;
                    return true;
                } else {
                    return false;
                }
            }
            case HELP: {
                show(options);
                return false;
            }
            case BACK: {
                currentPanel = SHOW_PANEL;
                currentActivities = null;
                return true;
            }
            default: {
                return false;
            }
        }
    }

    /**
     * Fonction permettant de lancer les actions liées au panel 'Affichage des
     * activités par état'.
     *
     * @param choice Choix qui a été fait par l'utilisateur
     * @return vrai si ce choix doit nous faire changer de panel, faux sinon
     * @see Loisirsland#action(int)
     */
    public boolean showByStatePanelSwitch(int choice) {
        switch (choice) {
            case SHOW_BY_STATE_COMING: {
                currentActivities = getActivitiesByDate(0);
                if (!currentActivities.isEmpty()) {
                    currentActivities = getActivitiesByState(false);
                    showResults();
                    currentPanel = SHOW_RESULTS_PANEL;
                    return true;
                } else {
                    return false;
                }
            }
            case SHOW_BY_STATE_ELAPSED: {
                if (!currentActivities.isEmpty()) {
                    currentActivities = getActivitiesByState(true);
                    showResults();
                    currentPanel = SHOW_RESULTS_PANEL;
                } else {
                    return false;
                }
            }
            case HELP: {
                show(options);
                return false;
            }
            case BACK: {
                currentPanel = SHOW_PANEL;
                currentActivities = null;
                return true;
            }
            default: {
                return false;
            }

        }
    }

    /**
     * Fonction permettant de lancer les actions liées au panel 'Affichage des
     * resultats de la recherche d'activité'.
     *
     * @param choice Choix qui a été fait par l'utilisateur
     * @return vrai si ce choix doit nous faire changer de panel, faux sinon
     * @see Loisirsland#action(int)
     */
    public boolean showResultsPanelSwitch(int choice) {
        switch (choice) {
            case SHOW_RESULTS: {
                showResults();
                return false;
            }
            case SHOW_SIMPLE_RESULTS: {
                showSimpleResults();
                return false;
            }
            case SHOW_DETAILED_RESULTS: {
                showDetailedResults();
                return false;
            }
            case HELP: {
                show(options);
                return false;
            }
            case BACK: {
                currentPanel = SHOW_PANEL;
                currentActivities = null;
                currentActivity = null;
                currentAnimator = null;
                currentChildren = null;
                return true;
            }
            default: {
                return false;
            }
        }

    }

    /**
     * Fonction permettant de lancer les actions liées au panel 'Modifier une
     * activité'.
     *
     * @param choice Choix qui a été fait par l'utilisateur
     * @return vrai si ce choix doit nous faire changer de panel, faux sinon
     * @see Loisirsland#action(int)
     */
    private boolean modifyActivityPanelSwitch(int choice) {
        switch (choice) {
            case EDIT_ACTIVITY_TYPE: {
                setActivityType(currentActivity);
                return false;
            }
            case EDIT_ACTIVITY_DESCRIPTION: {
                setActivityDescription(currentActivity);
                return false;
            }
            case EDIT_ACTIVITY_DATE: {
                setActivityDate(currentActivity);
                return false;
            }
            case EDIT_ACTIVITY_ADD_ANIMATORS: {
                currentPanel = ADD_ANIMATOR_PANEL;
                return true;
            }
            case EDIT_ACTIVITY_ADD_CHILDRENS: {
                currentPanel = ADD_CHILDREN_PANEL;
                return true;
            }
            case EDIT_ACTIVITY_REM_ANIMATORS: {
                if (!currentActivity.getAnimators().isEmpty()) {
                    System.out.println("Liste des animateurs associés : ");
                    for (Animator animator : currentActivity.getAnimators()) {
                        System.out.println(animator.toString());
                    }
                    currentActivity.getAnimators().remove(getAnimator());

                } else {
                    System.out.println("Aucun animateur n'est actuellement associé à cette activité.");
                }
                return false;
            }
            case EDIT_ACTIVITY_REM_CHILDRENS: {
                if (!currentActivity.getChildrens().isEmpty()) {
                    System.out.println("Liste des enfants inscrits : ");
                    for (Children children : currentActivity.getChildrens()) {
                        System.out.println(children.toString());
                    }
                    currentActivity.getChildrens().remove(getChildren());
                } else {
                    System.out.println("Aucun enfant n'est actuellement inscrit à cette activité.");
                }
                return false;
            }
            case EDIT_ACTIVITY_SHOW: {
                System.out.println(getDetailedResults(currentActivity));
                return false;
            }

            // Le case précédent ecraserait celui du helper, on incrémente tous les case de 1 à partir d'ici
            // Le même traitement a été effectué du côté de la méthode action()
            case HELP + 1: {
                show(options);
                return false;
            }
            case BACK + 1: {
                currentPanel = ACTIVITIES_PANEL;
                currentActivity = null;
                return true;
            }
            default: {
                // Evolution possible : logger ce genre de cas
                return false;
            }
        }
    }

    /**
     * Fonction permettant de lancer les actions liées au panel 'Modifier un
     * animateur'.
     *
     * @param choice Choix qui a été fait par l'utilisateur
     * @return vrai si ce choix doit nous faire changer de panel, faux sinon
     * @see Loisirsland#action(int)
     */
    private boolean modifyAnimatorPanelSwitch(int choice) {
        switch (choice) {
            case EDIT_PERSON_FIRSTNAME: {
                setPersonFirstname(currentAnimator);
                return false;
            }
            case EDIT_PERSON_LASTNAME: {
                setPersonLastname(currentAnimator);
                return false;
            }
            case EDIT_PERSON_BIRTHDATE: {
                setPersonBirthdate(currentAnimator);
                return false;
            }
            case EDIT_ANIMATOR_SHOW: {
                System.out.println(currentAnimator.toString());
                return false;
            }
            case HELP: {
                show(options);
                return false;
            }
            case BACK: {
                currentPanel = ANIMATORS_PANEL;
                currentAnimator = null;
                return true;
            }
            default: {
                // Evolution possible : logger ce genre de cas
                return false;
            }
        }
    }

    /**
     * Fonction permettant de lancer les actions liées au panel 'Modifier un
     * enfant'.
     *
     * @param choice Choix qui a été fait par l'utilisateur
     * @return vrai si ce choix doit nous faire changer de panel, faux sinon
     * @see Loisirsland#action(int)
     */
    private boolean modifyChildrenPanelSwitch(int choice) {
        switch (choice) {
            case EDIT_PERSON_FIRSTNAME: {
                setPersonFirstname(currentChildren);
                return false;
            }
            case EDIT_PERSON_LASTNAME: {
                setPersonLastname(currentChildren);
                return false;
            }
            case EDIT_PERSON_BIRTHDATE: {
                setPersonBirthdate(currentChildren);
                return false;
            }
            case EDIT_CHILDREN_PHONE: {
                setParentsPhoneNumber(currentChildren);
                return false;
            }
            case EDIT_CHILDREN_SHOW: {
                System.out.println(currentChildren.toString());
                return false;
            }
            case HELP: {
                show(options);
                return false;
            }
            case BACK: {
                currentPanel = CHILDRENS_PANEL;
                currentChildren = null;
                return true;
            }
            default: {
                // Evolution possible : logger ce genre de cas
                return false;
            }
        }
    }

    /**
     * Fonction permettant de lancer les actions liées au panel 'Ajouter un
     * animateur à une activité'.
     *
     * @param choice Choix qui a été fait par l'utilisateur
     * @return vrai si ce choix doit nous faire changer de panel, faux sinon
     * @see Loisirsland#action(int)
     */
    private boolean addAnimatorPanelSwitch(int choice) {
        switch (choice) {
            case EDIT_ACTIVITY_ADD_EXISTING: {
                if (!animators.isEmpty()) {
                    System.out.println("Liste des animateurs : ");
                    // Tri de la liste des animateurs
                    List<Animator> list = new ArrayList<>(animators.values());
                    Collections.sort(list, animatorsComparator);

                    for (Animator animator : list) {
                        System.out.println(animator.toString());
                    }
                    Animator a = getAnimator();
                    if (!currentActivity.getAnimators().contains(a)) {
                        currentActivity.addAnimator(a);
                    } else {
                        System.out.println("Cet animateur participe déjà à cette activité");
                    }
                } else {
                    System.out.println("La liste des animateurs est vide.");
                }
                return false;
            }
            case EDIT_ACTIVITY_ADD_NEW: {
                Animator animator = addAnimator();
                currentActivity.addAnimator(animator);

                System.out.println(animator.getFirstname() + " " + animator.getLastname() + " est désormais affecté à l'activité '" + currentActivity.getId() + "'");
                return false;
            }
            case HELP: {
                show(options);
                return false;
            }
            case BACK: {
                currentPanel = EDIT_ACTIVITY;
                return true;
            }
            default: {
                // Evolution possible : logger ce genre de cas
                return false;
            }
        }
    }

    /**
     * Fonction permettant de lancer les actions liées au panel 'Inscrire un
     * enfant à une activité'.
     *
     * @param choice Choix qui a été fait par l'utilisateur
     * @return vrai si ce choix doit nous faire changer de panel, faux sinon
     * @see Loisirsland#action(int)
     */
    private boolean addChildrenPanelSwitch(int choice) {
        switch (choice) {
            case EDIT_ACTIVITY_ADD_EXISTING: {
                if (!childrens.isEmpty()) {
                    System.out.println("Liste des enfants : ");
                    // Tri de la liste des enfants
                    List<Children> list = new ArrayList<>(childrens.values());
                    Collections.sort(list, childrensComparator);
                    for (Children children : list) {
                        System.out.println(children.toString());
                    }
                    Children c = getChildren();
                    if (!currentActivity.getChildrens().contains(c)) {
                        currentActivity.addChildren(c);
                    } else {
                        System.out.println("Cet enfant participe déjà à cette activité");
                    }
                } else {
                    System.out.println("La liste des enfants est vide.");
                }
                return false;
            }
            case EDIT_ACTIVITY_ADD_NEW: {
                Children children = addChildren();
                currentActivity.addChildren(children);

                System.out.println(children.getFirstname() + " " + children.getLastname() + " est désormais inscrit à l'activité '" + currentActivity.getId() + "'");
                return false;
            }
            case HELP: {
                show(options);
                return false;
            }
            case BACK: {
                currentPanel = EDIT_ACTIVITY;
                return true;
            }
            default: {
                // Evolution possible : logger ce genre de cas
                return false;
            }
        }
    }

    /**
     * Méthode retournant une activité récupérée depuis la saisie de
     * l'utilisateur.
     *
     * @return activité récupérée depuis la saisie de l'utilisateur
     */
    private Activity getActivity() {
        String temp, error;
        boolean isValid;
        do {
            System.out.print("Identifiant de l'activité : ");
            temp = scanner.nextLine();

            // On vérifie qu'il n'y a pas déjà une activité qui a cet identifiant
            isValid = activities.containsKey(temp);

            // S'il y a déjà une activité qui correspond à cet identifiant, on 
            if (!isValid) {
                error = "Aucun activité ne correspond à cet identifiant";
                System.err.println(STR_ERROR + error);
            }
        } while (!isValid);
        return activities.get(temp);
    }

    /**
     * Méthode retournant un animateur récupéré depuis la saisie de
     * l'utilisateur.
     *
     * @return animateur récupéré depuis la saisie de l'utilisateur
     */
    private Animator getAnimator() {
        Integer temp = new Integer(0);
        String error;
        boolean isValid;

        do {
            System.out.print("Identifiant de l'animateur : ");
            try {
                temp = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
            }

            // On vérifie qu'il n'y a pas déjà une activité qui a cet identifiant
            isValid = animators.containsKey(temp);

            // S'il y a déjà une activité qui correspond à cet identifiant, on 
            if (!isValid) {
                error = "Aucun animateur ne correspond à cet identifiant";
                System.err.println(STR_ERROR + error);
            }
        } while (!isValid);
        return animators.get(temp);
    }

    /**
     * Méthode retournant un enfant récupéré depuis la saisie de l'utilisateur.
     *
     * @return enfant récupéré depuis la saisie de l'utilisateur
     */
    private Children getChildren() {
        Integer temp = new Integer(0);
        String error;
        boolean isValid;

        do {
            System.out.print("Identifiant de l'enfant : ");
            try {
                temp = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
            }

            // On vérifie qu'il n'y a pas déjà une activité qui a cet identifiant
            isValid = childrens.containsKey(temp);

            // S'il y a déjà une activité qui correspond à cet identifiant, on 
            if (!isValid) {
                error = "Aucun enfant ne correspond à cet identifiant";
                System.err.println(STR_ERROR + error);
            }
        } while (!isValid);
        return childrens.get(temp);
    }

    /**
     * Méthode permettant de récupérer le choix de l'utilisateur
     *
     * @return choix de l'utilisateur
     */
    private int getChoice() {
        Integer iInput;
        do {
            System.out.print("\nVotre choix (8 pour l'aide) : ");
            // Récupération de la saisie

            try {
                iInput = scanner.nextInt();
                scanner.nextLine();
                if (options.containsKey(iInput)) {
                    return iInput;
                }
            } catch (Exception exception) {
            }
            scanner.nextLine();
        } while (true);
    }

    /**
     * Méthode permettant de récupérer la liste des options disponibles
     * vis-à-vis du panel dans lequel l'utilisateur se trouve actuellement.
     *
     * @return Liste des options disponibles
     */
    private Map<Integer, String> getOptions() {
        TreeMap<Integer, String> o = new TreeMap<>();
        switch (currentPanel) {
            case MENU_PANEL: {
                o.put(ACTIVITIES_PANEL, "Gestion des activités");
                o.put(ANIMATORS_PANEL, "Gestion des animateurs");
                o.put(CHILDRENS_PANEL, "Gestion des enfants");
                o.put(SHOW_PANEL, "Affichage des activités");
                o.put(IMPORT_DATA_PANEL, "Importer des données");
                o.put(EXPORT_DATA, "Exporter des données");
                o.put(HELP, STR_HELP);
                o.put(QUIT, STR_QUIT);
                break;
            }
            case ACTIVITIES_PANEL: {
                o.put(ADD, "Ajouter une activité");
                o.put(MODIFY, "Modifier une activité");
                o.put(REMOVE, "Supprimer une activité");
                o.put(SHOW, "Afficher les activités");
                o.put(HELP, STR_HELP);
                o.put(BACK, STR_BACK);
                break;
            }
            case ANIMATORS_PANEL: {
                o.put(ADD, "Ajouter animateur");
                o.put(MODIFY, "Modifier un animateur");
                o.put(REMOVE, "Supprimer un animateur");
                o.put(SHOW, "Afficher les animateurs");
                o.put(HELP, STR_HELP);
                o.put(BACK, STR_BACK);
                break;
            }
            case CHILDRENS_PANEL: {
                o.put(ADD, "Ajouter un enfant");
                o.put(MODIFY, "Modifier un enfant");
                o.put(REMOVE, "Supprimer un enfant");
                o.put(SHOW, "Afficher les enfants");
                o.put(HELP, STR_HELP);
                o.put(BACK, STR_BACK);
                break;
            }
            case SHOW_PANEL: {
                o.put(1, "Afficher les activités par animateur");
                o.put(2, "Afficher les activités par enfant");
                o.put(3, "Afficher les activités par date");
                o.put(4, "Afficher les activités par identifiant");
                o.put(5, "Afficher les activités par état");
                o.put(HELP, STR_HELP);
                o.put(BACK, STR_BACK);
                break;
            }
            case EDIT_ACTIVITY: {
                o.put(EDIT_ACTIVITY_TYPE, "Modifier le type d'activité");
                o.put(EDIT_ACTIVITY_DESCRIPTION, "Modifier la description de l'activité");
                o.put(EDIT_ACTIVITY_DATE, "Modifier la date de l'activité");
                o.put(EDIT_ACTIVITY_ADD_ANIMATORS, "Associer un animateur à l'activité");
                o.put(EDIT_ACTIVITY_ADD_CHILDRENS, "Inscrire un enfant à l'activité");
                o.put(EDIT_ACTIVITY_REM_ANIMATORS, "Dissocier un animateur de l'activité");
                o.put(EDIT_ACTIVITY_REM_CHILDRENS, "Désinscrire un enfant de l'activité");
                o.put(EDIT_ACTIVITY_SHOW, "Afficher les informations de cette activité");
                o.put(HELP + 1, STR_HELP);
                o.put(BACK + 1, "Retour au menu de gestion des activités");
                break;
            }
            case EDIT_ANIMATOR_PANEL: {
                o.put(EDIT_PERSON_LASTNAME, "Modifier le nom de l'animateur");
                o.put(EDIT_PERSON_FIRSTNAME, "Modifier le prénom de l'animateur");
                o.put(EDIT_PERSON_BIRTHDATE, "Modifier la date de naissance de l'animateur");
                o.put(EDIT_ANIMATOR_SHOW, "Afficher les informations de cet animateur");
                o.put(HELP, STR_HELP);
                o.put(BACK, "Retour au menu de gestion des animateurs");
                break;
            }
            case EDIT_CHILDREN_PANEL: {
                o.put(EDIT_PERSON_LASTNAME, "Modifier le nom de l'enfant");
                o.put(EDIT_PERSON_FIRSTNAME, "Modifier le prénom de l'enfant");
                o.put(EDIT_PERSON_BIRTHDATE, "Modifier la date de naissance de l'enfant");
                o.put(EDIT_CHILDREN_PHONE, "Modifier le numéro de téléphone des parents");
                o.put(EDIT_CHILDREN_SHOW, "Afficher les informations de cet enfant");
                o.put(HELP, STR_HELP);
                o.put(BACK, "Retour au menu de gestion des enfants");
                break;
            }
            case ADD_ANIMATOR_PANEL: {
                o.put(EDIT_ACTIVITY_ADD_EXISTING, "Ajouter un animateur existant");
                o.put(EDIT_ACTIVITY_ADD_NEW, "Ajouter un nouvel animateur");
                o.put(HELP, STR_HELP);
                o.put(BACK, "Retour au menu de modification de l'activité");
                break;
            }
            case ADD_CHILDREN_PANEL: {
                o.put(EDIT_ACTIVITY_ADD_EXISTING, "Ajouter un enfant existant");
                o.put(EDIT_ACTIVITY_ADD_NEW, "Ajouter un nouvel enfant");
                o.put(HELP, STR_HELP);
                o.put(BACK, "Retour au menu de modification de l'activité");
                break;
            }
            case SHOW_BY_DATE_PANEL: {
                o.put(SHOW_BY_DATE_EQUALS, "Afficher les activités dont la date est égale à");
                o.put(SHOW_BY_DATE_SUP, "Afficher les activités dont la date est supérieure à...");
                o.put(SHOW_BY_DATE_INF, "Afficher les activités dont la date est inférieure à...");
                o.put(HELP, STR_HELP);
                o.put(BACK, "Retour au menu d'affichage des activités");
                break;
            }
            case SHOW_BY_STATE_PANEL: {
                o.put(SHOW_BY_STATE_COMING, "Afficher les activités à venir");
                o.put(SHOW_BY_STATE_ELAPSED, "Afficher les activités passées");
                o.put(HELP, STR_HELP);
                o.put(BACK, "Retour au menu d'affichage des activités");
                break;
            }
            case SHOW_RESULTS_PANEL: {
                o.put(SHOW_RESULTS, "Réafficher les résultats (activités uniquement)");
                o.put(SHOW_SIMPLE_RESULTS, "Réafficher les résultats (format simple)");
                o.put(SHOW_DETAILED_RESULTS, "Réafficher les résultats (format détaillé)");
                o.put(HELP, STR_HELP);
                o.put(BACK, "Retour au menu d'affichage des activités");
                break;
            }

            default: {
                break;
            }
        }
        return o;
    }

    /**
     * Méthode permettant d'afficher les différentes options disponibles.
     *
     * @param options Options disponibles
     */
    private void show(Map<Integer, String> options) {
        StringBuilder s = new StringBuilder();
        for (Entry<Integer, String> option : options.entrySet()) {
            s.append("\n");
            s.append(option.getKey());
            s.append(CHOICE_NUMBER_SEPARATOR);
            s.append(option.getValue());
        }
        System.out.println(s.toString());
    }

    /**
     * Méthode permettant de récupérer la confirmation de suppression d'un
     * utilisateur
     *
     * @return vrai si l'utilisateur confirme la suppression, faux sinon
     */
    private boolean isSure() {
        char input;
        do {
            System.out.print("\nConfirmez vous la suppression? o/[n] ");

            try {
                input = scanner.nextLine().charAt(0);
                scanner.nextLine();
                if (input == 'o') {
                    return true;
                } else if (input == 'n') {
                    return false;
                }
            } catch (Exception exception) {
            }
            scanner.nextLine();
        } while (true);

    }

    /**
     * Méthode permettant à l'utilisateur de créer et d'ajouter une activité.
     *
     * @see Loisirsland#setActivityID(loisirsland.Activity)
     * @see Loisirsland#setActivityType(loisirsland.Activity)
     * @see Loisirsland#setActivityDescription(loisirsland.Activity)
     * @see Loisirsland#setActivityDescription(loisirsland.Activity)
     */
    private Activity addActivity() {
        Activity activity = new Activity();

        setActivityID(activity);
        setActivityType(activity);
        setActivityDescription(activity);
        setActivityDate(activity);

        activities.put(activity.getId(), activity);
        System.out.println("L'activité '" + activity.getId() + "' (" + activity.getDescription() + ") a été créée");
        return activity;
    }

    /**
     * Méthode permettant à l'utilisateur de supprimer une activité grâce à la
     * saisie de son identifiant.
     */
    private void deleteActivity() {
        boolean isValid = true;
        String temp;

        // Récupération de l'identifiant de l'activité à supprimer
        do {
            System.out.print("ID de l'activité à supprimer : ");
            temp = scanner.nextLine();

            // On vérifie que l'utilisateur ne veut pas annueler son choix
            if (!temp.equals(Integer.toString(BACK))) {

                // On vérifie l'existance de cette activité 
                if (activities.containsKey(temp)) {

                    // Si elle existe et que l'utilisateur confirme la suppression
                    if (isSure()) {
                        // On supprime l'activité
                        Activity a = activities.remove(temp);
                        System.out.println("L'activité '" + a.getId() + "' (" + a.getDescription() + ") a été supprimée");
                    }
                    isValid = true;
                } else {
                    // Sinon, on affiche un message comme quoi l'objet est introuvable
                    System.err.println("Aucune activité ne correspond à cet identifiant");
                    isValid = false;
                }
            }
            // Tant que ce qui est récupéré n'est pas valide ou qu'il n'est pas égal à l'option 'Retour'
        } while (!temp.equals(Integer.toString(BACK)) && !isValid);
    }

    /**
     * Méthode permettant à l'utilisateur de créer et d'ajouter un animateur.
     *
     * @see Loisirsland#setAnimatorID(loisirsland.Animator)
     * @see Loisirsland#setPersonFirstname(loisirsland.Person)
     * @see Loisirsland#setPersonLastname(loisirsland.Person)
     * @see Loisirsland#setPersonBirthdate(loisirsland.Person)
     */
    private Animator addAnimator() {
        Animator animator = new Animator();

        setAnimatorID(animator);
        setPersonFirstname(animator);
        setPersonLastname(animator);
        setPersonBirthdate(animator);

        animators.put(animator.getId(), animator);
        System.out.println("L'animateur '" + animator.getId() + "' (" + animator.getFirstname() + " " + animator.getLastname() + ") a été créé");
        return animator;
    }

    /**
     * Méthode permettant à l'utilisateur de supprimer un animateur grâce à la
     * saisie de son identifiant.
     */
    private void deleteAnimator() {
        boolean isValid = true;
        int temp;

        // Récupération de l'identifiant de l'animateur à supprimer
        do {
            System.out.print("ID de l'animateur à supprimer : ");
            temp = scanner.nextInt();
            scanner.nextLine();

            // On vérifie que l'utilisateur ne veut pas annueler son choix
            if (temp != BACK) {

                // On vérifie l'existance de cet animateur
                if (animators.containsKey(temp)) {

                    // S'il existe et que l'utilisateur confirme la suppression
                    if (isSure()) {
                        // On supprime l'animateur
                        Animator a = animators.remove(temp);
                        System.out.println("L'animateur '" + a.getId() + "' (" + a.getFirstname() + " " + a.getLastname() + ") a été supprimé");
                    }
                    isValid = true;
                } else {
                    // Sinon, on affiche un message comme quoi l'objet est introuvable
                    System.err.println("Aucun animateur ne correspond à cet identifiant");
                    isValid = false;
                }
            }
            // Tant que ce qui est récupéré n'est pas valide ou qu'il n'est pas égal à l'option 'Retour'
        } while (temp != BACK && !isValid);
    }

    /**
     * Méthode permettant à l'utilisateur de créer et d'ajouter un enfant.
     *
     * @see Loisirsland#setChildrenID(loisirsland.Children)
     * @see Loisirsland#setPersonFirstname(loisirsland.Person)
     * @see Loisirsland#setPersonLastname(loisirsland.Person)
     * @see Loisirsland#setPersonBirthdate(loisirsland.Person)
     * @see Loisirsland#setParentsPhoneNumber(loisirsland.Children)
     */
    private Children addChildren() {
        Children children = new Children();

        setChildrenID(children);
        setPersonFirstname(children);
        setPersonLastname(children);
        setPersonBirthdate(children);
        setParentsPhoneNumber(children);

        childrens.put(children.getId(), children);
        System.out.println("L'enfant '" + children.getId() + "' (" + children.getFirstname() + " " + children.getLastname() + ") a été créé");
        return children;

    }

    /**
     * Méthode permettant à l'utilisateur de supprimer un enfant grâce à la
     * saisie de son identifiant.
     */
    private void deleteChildren() {
        boolean isValid = true;
        int temp;

        // Récupération de l'identifiant de l'enfant à supprimer
        do {
            System.out.print("ID de l'enfant à supprimer : ");
            temp = scanner.nextInt();
            scanner.nextLine();

            // On vérifie que l'utilisateur ne veut pas annueler son choix
            if (temp != BACK) {

                // On vérifie l'existance de cet enfant
                if (childrens.containsKey(temp)) {

                    // S'il existe et que l'utilisateur confirme la suppression
                    if (isSure()) {
                        // On supprime l'enfant
                        Children c = childrens.remove(temp);
                        System.out.println("L'enfant '" + c.getId() + "' (" + c.getFirstname() + " " + c.getLastname() + ") a été supprimé");
                    }
                    isValid = true;
                } else {
                    // Sinon, on affiche un message comme quoi l'objet est introuvable
                    System.err.println("Aucun enfant ne correspond à cet identifiant");
                    isValid = false;
                }
            }
        } while (temp != BACK && !isValid);
        // Tant que ce qui est récupéré n'est pas valide ou qu'il n'est pas égal à l'option 'Retour'
    }

    /**
     * Méthode permettant à l'utilisateur de saisir l'identifiant d'une
     * activité.
     *
     * @param activity Activité à laquelle il faut affecter un identifiant
     */
    private void setActivityID(Activity activity) {
        String temp;
        String error = new String();
        boolean isValid;

        // Récupération de la saisie utilisateur
        do {
            System.out.print("Identifiant de l'activité : ");
            temp = scanner.nextLine();

            // On vérifie qu'il n'y a pas déjà une activité qui a cet identifiant
            isValid = !activities.containsKey(temp);

            // S'il y a déjà une activité qui correspond à cet identifiant on prépare une erreur
            if (!isValid) {
                error = "Une activité correspond déjà à cet identifiant";
            } else {
                // On vérifie que la saisie n'est pas invalide
                try {
                    activity.setId(temp);
                    isValid = true;
                } catch (MalformedObjectException e) {
                    error = e.getMessage();
                    isValid = false;

                }
            }
            // Si la saisie est invalide, on affiche la raison
            if (!isValid) {
                System.err.println(STR_ERROR + error);
            }
        } while (!isValid);
        // Tant que la saisie n'est pas valide
    }

    /**
     * Méthode permettant à l'utilisateur de saisir le type d'une activité.
     *
     * @param activity Activité à laquelle il faut affecter un type.
     */
    private void setActivityType(Activity activity) {
        String temp;
        String error = new String();
        boolean isValid;

        // Récupération de la saisie utilisateur
        do {
            System.out.print("Type de l'activité : ");
            temp = scanner.nextLine();

            // On vérifie que la saisie n'est pas invalide
            try {
                activity.setType(temp);
                isValid = true;
            } catch (MalformedObjectException e) {
                error = e.getMessage();
                isValid = false;
            }
            // Si la saisie est invalide, on affiche la raison
            if (!isValid) {
                System.err.println(STR_ERROR + error);
            }
        } while (!isValid);
        // Tant que la saisie n'est pas valide
    }

    /**
     * Méthode permettant à l'utilisateur de saisir la description d'une
     * activité.
     *
     * @param activity Activité à laquelle il faut affecter une description
     */
    private void setActivityDescription(Activity activity) {
        String temp;
        String error = new String();
        boolean isValid;

        // Récupération de la saisie utilisateur
        do {
            System.out.print("Description de l'activité : ");
            temp = scanner.nextLine();

            // On vérifie que la saisie n'est pas invalide
            try {
                activity.setDescription(temp);
                isValid = true;
            } catch (MalformedObjectException e) {
                error = e.getMessage();
                isValid = false;
            }
            // Si la saisie est invalide, on affiche la raison
            if (!isValid) {
                System.err.println(STR_ERROR + error);
            }
        } while (!isValid);
        // Tant que la saisie n'est pas valide
    }

    /**
     * Méthode permettant à l'utilisateur de saisir la date à laquelle a lieu
     * une activité.
     *
     * @param activity Activité à laquelle il faut affecter une date d'exécution
     */
    private void setActivityDate(Activity activity) {
        String temp;
        String error = new String();
        boolean isValid;

        // Récupération de la saisie utilisateur
        do {
            System.out.print("Date de l'activité : ");
            temp = scanner.nextLine();

            // On vérifie que la saisie n'est pas invalide
            try {
                activity.setDate(temp);
                isValid = true;
            } catch (MalformedObjectException e) {
                error = e.getMessage();
                isValid = false;
            }
            // Si la saisie est invalide, on affiche la raison
            if (!isValid) {
                System.err.println(STR_ERROR + error);
            }
        } while (!isValid);
        // Tant que la saisie n'est pas valide
    }

    /**
     * Méthode permettant à l'utilisateur de saisir l'identifiant d'un
     * animateur.
     *
     * @param animator Animateur auquel il faut affecter un identifiant
     */
    private void setAnimatorID(Animator animator) {
        Integer temp = new Integer(0);
        String error = new String();
        boolean isValid;

        // Récupération de la saisie utilisateur
        do {
            System.out.print("Identifiant de l'animateur : ");
            try {
                temp = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
            }

            // On vérifie qu'il n'y a pas déjà un animateur qui a cet identifiant
            isValid = !animators.containsKey(temp);

            // S'il y a déjà un animateur qui correspond à cet identifiant, on prépare une erreur
            if (!isValid) {
                error = "Un animateur correspond déjà à cet identifiant";
            } else {
                // On vérifie que la saisie n'est pas invalide
                try {
                    animator.setId(temp);
                    isValid = true;
                } catch (MalformedObjectException e) {
                    error = e.getMessage();
                    isValid = false;

                }
            }
            // Si la saisie est invalide, on affiche la raison
            if (!isValid) {
                System.err.println(STR_ERROR + error);
            }
        } while (!isValid);
        // Tant que la saisie n'est pas valide
    }

    /**
     * Méthode permettant à l'utilisateur de saisir l'identifiant d'un enfant.
     *
     * @param children Enfant auquel il faut affecter un identifiant
     */
    private void setChildrenID(Children children) {
        Integer temp = new Integer(0);
        String error = new String();
        boolean isValid;

        // Récupération de la saisie utilisateur
        do {
            System.out.print("Identifiant de l'enfant : ");
            try {
                temp = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
            }

            // On vérifie qu'il n'y a pas déjà un enfant qui a cet identifiant
            isValid = !childrens.containsKey(temp);

            // S'il y a déjà un enfant qui correspond à cet identifiant, on prépare une erreur
            if (!isValid) {
                error = "Un enfant correspond déjà à cet identifiant";
            } else {
                // On vérifie que la saisie n'est pas invalide
                try {
                    children.setId(temp);
                    isValid = true;
                } catch (MalformedObjectException e) {
                    error = e.getMessage();
                    isValid = false;

                }
            }
            // Si la saisie est invalide, on affiche la raison
            if (!isValid) {
                System.err.println(STR_ERROR + error);
            }
        } while (!isValid);
        // Tant que la saisie n'est pas valide
    }

    /**
     * Méthode permettant à l'utilisateur de saisir la date de naissance d'une
     * personne.
     *
     * @param person Personne à laquelle il faut affecter une date de naissance
     */
    private void setPersonBirthdate(Person person) {
        String temp;
        String error = new String();
        boolean isValid;

        // Récupération de la saisie utilisateur
        do {
            System.out.print("Date de naissance : ");
            temp = scanner.nextLine();

            // On vérifie que la saisie n'est pas invalide
            try {
                person.setBirthdate(temp);
                isValid = true;
            } catch (MalformedObjectException e) {
                error = e.getMessage();
                isValid = false;
            }
            // Si la saisie est invalide, on affiche la raison
            if (!isValid) {
                System.err.println(STR_ERROR + error);
            }
        } while (!isValid);
        // Tant que la saisie n'est pas valide
    }

    /**
     * Méthode permettant à l'utilisateur de saisir le prénom d'une personne.
     *
     * @param person Personne à laquelle il faut affecter un prénom
     */
    private void setPersonFirstname(Person person) {
        String temp;
        String error = new String();
        boolean isValid;

        // Récupération de la saisie utilisateur
        do {
            System.out.print("Prénom : ");
            temp = scanner.nextLine();

            // On vérifie que la saisie n'est pas invalide
            try {
                person.setFirstname(temp);
                isValid = true;
            } catch (MalformedObjectException e) {
                error = e.getMessage();
                isValid = false;
            }
            // Si la saisie est invalide, on affiche la raison
            if (!isValid) {
                System.err.println(STR_ERROR + error);
            }
        } while (!isValid);
        // Tant que la saisie n'est pas valide
    }

    /**
     * Méthode permettant à l'utilisateur de saisir le nom de famille d'une
     * personne.
     *
     * @param person Personne à laquelle il faut affecter un nom de famille
     */
    private void setPersonLastname(Person person) {
        String temp;
        String error = new String();
        boolean isValid;

        // Récupération de la saisie utilisateur
        do {
            System.out.print("Nom de famille : ");
            temp = scanner.nextLine();

            // On vérifie que la saisie n'est pas invalide
            try {
                person.setLastname(temp);
                isValid = true;
            } catch (MalformedObjectException e) {
                error = e.getMessage();
                isValid = false;
            }
            // Si la saisie est invalide, on affiche la raison
            if (!isValid) {
                System.err.println(STR_ERROR + error);
            }
        } while (!isValid);
        // Tant que la saisie n'est pas valide
    }

    /**
     * Méthode permettant à l'utilisateur de saisir le numéro de téléphone des
     * parents d'un enfant.
     *
     * @param children Enfant auquel il faut affecter le numéro de téléphone des
     * parents
     */
    private void setParentsPhoneNumber(Children children) {
        String temp;
        int telephone;
        String error = new String();
        boolean isValid;

        // Récupération de la saisie utilisateur
        do {
            System.out.print("Numéro de téléphone des parents : ");
            temp = scanner.nextLine();

            // On vérifie que la saisie n'est pas invalide
            try {
                telephone = Integer.parseInt(temp);
                // Si la longueur du numéro est suprérieure à 10 caractères, on prépare une erreur
                if (temp.length() > 10) {
                    error = "Le taille du numéro de téléphone ne peut excéder dix caractères.";
                    isValid = false;
                } else {
                    children.setParentsPhoneNumber("" + telephone);
                    isValid = true;
                }
            } catch (NumberFormatException e) {
                // Si le numéro de téléphone ne peut être résolu, on prépare une erreur
                error = "Impossible de convertir '" + temp + "' en numéro de téléphone.";
                isValid = false;
            }
            // Si la saisie est invalide, on affiche la raison
            if (!isValid) {
                System.err.println(STR_ERROR + error);
            }
        } while (!isValid);
        // Tant que la saisie n'est pas valide
    }

    /**
     * Méthode permettant de récupérer les activités antérieures, postérieures
     * ou égales à une date saisie par l'utilisateur
     *
     * @param value -1 si la date de l'activité doit être antérieure à celle
     * saisie par l'utilisateur, 0 si elle doit être égale, 1 si elle doit être
     * postérieure
     * @return Liste des activités correspondantes au critère de recherche
     */
    public List<Activity> getActivitiesByDate(int value) {
        ArrayList<Activity> foundActivities = new ArrayList<>();
        String temp;
        String error = new String();
        boolean isValid;

        // Récupération de la saisie utilisateur
        do {
            System.out.print("Date recherchée : ");
            temp = scanner.nextLine();

            // On essaye de parser la date saisie
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

            // Si on arrive à parser la date saisie
            try {
                Date d = dateFormat.parse(temp);
                isValid = true;
                for (Activity activity : activities.values()) {
                    if (activity.getDate().compareTo(d) == value) {
                        foundActivities.add(activity);
                    }
                }
            } catch (ParseException e) {
                error = "Impossible de résoudre la date '" + temp + "'.\nLe format attendu est dd/MM/yyyy.\nExemple : 11/10/2014";
                isValid = false;
            }
            // Si la saisie est invalide, on affiche la raison
            if (!isValid) {
                System.err.println(STR_ERROR + error);
            }
        } while (!isValid);
        return foundActivities;
        // Tant que la saisie n'est pas valide
    }

    /**
     * Méthode permettant de récupérer la liste des activités d'un animateur.
     *
     * @param animator Animateur pour lequel nous voulons récupérer la liste
     * d'activités
     * @return Liste des activités correspondantes au critère de recherche
     */
    public List<Activity> getActivitiesByAnimator(Animator animator) {
        ArrayList<Activity> foundActivities = new ArrayList<>();

        // On parcours toutes les activités
        for (Activity activity : activities.values()) {
            // Si l'animateur est associé à cette activité
            if (activity.getAnimators().contains(animator)) {
                // On l'ajoute à la liste à retourner
                foundActivities.add(activity);
            }
        }

        return foundActivities;
    }

    /**
     * Méthode permettant de récupérer la liste des activités d'un enfant.
     *
     * @param children Enfant pour lequel nous voulons récupérer la liste
     * d'activités
     * @return Liste des activités correspondantes au critère de recherche
     */
    public List<Activity> getActivitiesByChildren(Children children) {
        ArrayList<Activity> foundActivities = new ArrayList<>();

        // On parcours toutes les activités
        for (Activity activity : activities.values()) {
            // Si l'enfant est inscrit à cette activité
            if (activity.getChildrens().contains(children)) {
                // On l'ajoute à la liste à retourner
                foundActivities.add(activity);
            }
        }

        return foundActivities;
    }

    /**
     * Méthode permettant de retourner une liste d'activité contenant uniquement
     * l'activité envoyée en paramètre.
     *
     * @param activity Unique activité qui sera ajoutée à la liste des activités
     * récupérée
     * @return Liste des activités correspondantes au critère de recherche
     */
    public List<Activity> getActivitiesByActivity(Activity activity) {
        ArrayList<Activity> foundActivities = new ArrayList<>();
        foundActivities.add(activity);
        return foundActivities;
    }

    /**
     * Méthode permettant de récupérer les activités dont l'activité est
     * l'activité envoyée en paramètre.
     *
     * @param elapsed Etat dans lequel les activités doivent être pour être
     * récupérées
     * @return Liste des activités correspondantes au critère de recherche
     */
    public List<Activity> getActivitiesByState(boolean elapsed) {
        ArrayList<Activity> foundActivities = new ArrayList<>();

        for (Activity activity : activities.values()) {
            if (activity.isElapsed() == elapsed) {
                foundActivities.add(activity);
            }
        }

        return foundActivities;
    }

    /**
     * Méthode permettant d'afficher les résultats d'une recherche d'activité de
     * façon détaillée
     */
    public void showDetailedResults() {
        StringBuilder sb = new StringBuilder();
        sb.append(currentActivities.size());
        sb.append(" activité(s) correspondante(s) au critère de recherche\n");
        boolean firstTimeActivity;
        firstTimeActivity = true;
        for (Activity activity : currentActivities) {
            if (firstTimeActivity) {
                sb.append("\n");
                sb.append(STR_ACTIVITY_HEADER);
                firstTimeActivity = false;
            }
            
            // Récupération des informations détaillées
            sb.append(getDetailedResults(activity));
        }
        System.out.println(sb.toString());
    }

    /**
     * Méthode qui prend en paramètre une activité et qui retourne ses informations détaillées
     * @param activity Activité dont les informations sont à renvoyer
     * @return Informations détaillées de l'utilisateur
     */
    public String getDetailedResults(Activity activity) {
        StringBuilder sb = new StringBuilder();
        boolean firstTimeAnimator, firstTimeChildren;
        firstTimeAnimator = firstTimeChildren = true;
        
        // Affichage des informations de l'activité
        sb.append("\n");
        sb.append(activity.toString());
        sb.append("\n\n");
        sb.append(activity.getAnimators().size());
        sb.append(" animateur(s) associé(s)");

        // Affichage des informations de tous les animateurs associés
        for (Animator animator : activity.getAnimators()) {
            if (firstTimeAnimator) {
                sb.append("\n\t");
                sb.append(STR_ANIMATOR_HEADER);
                firstTimeAnimator = false;
            }
            sb.append("\n\t");
            sb.append(animator.toString());
        }

        sb.append("\n\n");
        sb.append(activity.getChildrens().size());
        sb.append(" enfant(s) inscrit(s)");

        // Affichage des informations de tous les enfants associés
        for (Children children : activity.getChildrens()) {
            if (firstTimeChildren) {
                sb.append("\n\t");
                sb.append(STR_CHILDREN_HEADER);
                firstTimeChildren = false;
            }
            sb.append("\n\t");
            sb.append(children.toString());
        }
        
        return sb.toString();
    }

    /**
     * Méthode permettant d'afficher les résultats d'une recherche d'activité de
     * façon simple
     */
    public void showSimpleResults() {
        StringBuilder sb = new StringBuilder();
        sb.append(currentActivities.size());
        sb.append(" activité(s) correspondante(s) au critère de recherche\n");
        boolean firstTimeActivity;
        firstTimeActivity = true;
        for (Activity activity : currentActivities) {
            if (firstTimeActivity) {
                sb.append("\n");
                sb.append(STR_ACTIVITY_HEADER);
                firstTimeActivity = false;
            }
            sb.append("\n");
            sb.append(activity.toString());
            sb.append("\n");
            sb.append(activity.getAnimators().size());
            sb.append(" animateur(s) associé(s)");
            sb.append("\n");
            sb.append(activity.getChildrens().size());
            sb.append(" enfant(s) inscrit(s)");
        }
        System.out.println(sb.toString());
    }

    /**
     * Méthode permettant d'afficher les résultats d'une recherche d'activité de
     * façon succinte
     */
    public void showResults() {
        StringBuilder sb = new StringBuilder();
        sb.append(currentActivities.size());
        sb.append(" activité(s) correspondante(s) au critère de recherche\n");
        boolean firstTimeActivity = true;
        for (Activity activity : currentActivities) {
            if (firstTimeActivity) {
                sb.append("\n");
                sb.append(STR_ACTIVITY_HEADER);
                firstTimeActivity = false;
            }
            sb.append("\n");
            sb.append(activity.toString());
        }
        System.out.println(sb.toString());
    }

    /*
     * Méthodes utilisées pour l'import / export des données
     */
    /**
     * Méthode permettant d'exporter les données du centre de loisirs dans le
     * fichier "activities.json" présent à la racine de notre projet. Les
     * animateurs et enfants présents dans les Maps animators et childrens ne
     * seront pas sauvegardés s'il ne sont pas associés à une ou plusieurs
     * activités.
     *
     */
    public void exportJSON() {
        // Instanciation d'un nouvel objet Gson
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // Création du type de données à exporter
        Type type = new TypeToken<Collection<Activity>>() {
        }.getType();

        // Génération du contenu à exporter
        String json = gson.toJson(activities.values(), type);

        // Ecriture du contenu généré dans le fichier "activities.json"
        try (FileWriter writer = new FileWriter("activities.json")) {
            writer.write(json);
            System.out.println("Export des données effectué vers le fichier 'activities.json'");
        } catch (IOException e) {
            System.out.println("Impossible d'exporter les informations...");
        }
    }

    /**
     * Méthode permettant de charger le fichier "activities.json" présent à la
     * racine de notre projet
     */
    public void importJSON() {
        Gson gson = new Gson();
        int choice = new Integer(0);
        boolean rememberMyChoice = false;

        try {
            BufferedReader br = new BufferedReader(new FileReader("activities.json"));
            Type typeOfHashMap = new TypeToken<Collection<Activity>>() {
            }.getType();
            Collection<Activity> data = gson.fromJson(br, typeOfHashMap);

            // On parcours toutes les activités qu'on vient de récupérer
            for (Activity activity : data) {
                // Si une activité a déjà cet ID la...
                if (activities.containsKey(activity.getId())) {
                    // Si on effectue les vérifications à chaque conflit...
                    if (!rememberMyChoice) {
                        choice = manageConflict(activity);
                        switch (choice) {
                            case KEEP: {
                                rememberMyChoice = skipValidations();
                                break;
                            }
                            case EDIT:
                                setActivityID(activity);
                                activities.put(activity.getId(), activity);

                                loadAnimators(activity);
                                loadChildrens(activity);
                                break;
                            case OVERWRITE: {
                                rememberMyChoice = skipValidations();
                                activities.remove(activity.getId());
                                activities.put(activity.getId(), activity);

                                loadAnimators(activity);
                                loadChildrens(activity);
                                break;
                            }
                            default: {
                                break;
                            }
                        }
                    } else if (choice == OVERWRITE) {
                        activities.remove(activity.getId());
                        activities.put(activity.getId(), activity);
                    }
                } else {
                    // Si l'activité n'existe pas
                    // Ajout de l'activité
                    activities.put(activity.getId(), activity);

                    loadAnimators(activity);
                    loadChildrens(activity);
                }
            }
            System.out.println("Import des données effectué");
        } catch (Exception e) {
            System.out.println("Impossible de trouver le fichier 'activities.json' ...");
        }
        show(options);

    }

    /**
     * Méthode qui permet de charger et de gérer les éventuels conflits relatifs
     * à tous les animateurs importés en même temps qu'une activité.
     *
     * @param activity Activité importée depuis laquelle il faut charger les
     * animateurs
     *
     */
    public void loadAnimators(Activity activity) {
        boolean rememberMyChoice = false;
        int choice = new Integer(0);
        // Pour chaque animateur de cette activité...
        for (Animator animator : activity.getAnimators()) {

            // Si un animateur a déjà cet ID la, mais que ce ne sont pas les mêmes...
            if (animators.containsKey(animator.getId()) && !animators.get(animator.getId()).equals(animator)) {
                // Si on n'a pas défini d'action a faire pour chaque conflit d'animateur...
                if (!rememberMyChoice) {
                    // On récupère le choix de l'utilisateur
                    choice = manageConflict(animator);

                    // On switch sur le résultat
                    switch (choice) {
                        // Si l'utilisateur décide de skipper cet animateur
                        case KEEP: {
                            // On lui demande s'il veut agir ainsi pour tous les animateurs
                            rememberMyChoice = skipValidations();
                            break;
                        }
                        case EDIT:
                            // Si l'utilisateur décide de modifier l'ID de cet animateur avant de l'importer
                            setAnimatorID(animator);
                            animators.put(animator.getId(), animator);
                            break;
                        case OVERWRITE: {
                            // Si l'utilisateur décide d'écraser l'ancien animateur

                            // On lui demande s'il veut agir ainsi pour tous les animateurs
                            rememberMyChoice = skipValidations();

                            // On parcours toutes les activités
                            overwriteAnimator(animator);
                            break;
                        }
                        default: {
                            break;
                        }
                    }
                } else if (choice == OVERWRITE) {
                    overwriteAnimator(animator);
                }
            } else {
                // Si aucun animateur n'a déjà cet ID la...
                // On ajoute notre animateur à la liste des animateurs
                animators.put(animator.getId(), animator);

            }
        }
    }

    /**
     * Méthode qui permet de charger et de gérer les éventuels conflits relatifs
     * à tous les enfants importés en même temps qu'une activité.
     *
     * @param activity Activité importée depuis laquelle il faut charger les
     * enfants
     */
    public void loadChildrens(Activity activity) {
        boolean rememberMyChoice = false;
        int choice = new Integer(0);
        // Pour chaque enfant de cette activité...
        for (Children children : activity.getChildrens()) {

            // Si un enfant a déjà cet ID la, mais que ce ne sont pas les mêmes...
            if (childrens.containsKey(children.getId()) && !childrens.get(children.getId()).equals(children)) {
                // Si on n'a pas défini d'action a faire pour chaque conflit d'enfant...
                if (!rememberMyChoice) {
                    // On récupère le choix de l'utilisateur
                    choice = manageConflict(children);

                    // On switch sur le résultat
                    switch (choice) {
                        // Si l'utilisateur décide de skipper cet enfant
                        case KEEP: {
                            // On lui demande s'il veut agir ainsi pour tous les enfants
                            rememberMyChoice = skipValidations();
                            break;
                        }
                        case EDIT:
                            // Si l'utilisateur décide de modifier l'ID de cet enfant avant de l'importer
                            setChildrenID(children);
                            childrens.put(children.getId(), children);
                            break;
                        case OVERWRITE: {
                            // Si l'utilisateur décide d'écraser l'ancien enfant

                            // On lui demande s'il veut agir ainsi pour tous les enfants
                            rememberMyChoice = skipValidations();

                            // On parcours toutes les activités
                            overwriteChildren(children);
                            break;
                        }
                        default: {
                            break;
                        }
                    }
                } else if (choice == OVERWRITE) {
                    overwriteChildren(children);
                }
            } else {
                // Si aucun enfant n'a déjà cet ID la...
                // On ajoute notre enfant à la liste des enfants
                childrens.put(children.getId(), children);

            }
        }
    }

    /**
     * Méthode qui permet d'effacer les activités auxquelles un animateur était
     * affecté pour le remplacer par un animateur venant d'être importé en ayant
     * le même identifiant.
     *
     * @param animator Animateur qui doit remplacer l'animateur déjà existant
     */
    public void overwriteAnimator(Animator animator) {
        // On parcours toutes les activités
        for (Activity act : activities.values()) {
            // On parcours tous les animateurs de cette activité
            for (Animator ani : act.getAnimators()) {
                // Si l'ancien animateur était enregistré sur cette activité
                if (ani.getId() == animator.getId()) {
                    // On supprime l'ancien animateur
                    act.removeAnimator(ani);

                    // On ajoute le nouvel animateur
                    act.removeAnimator(animator);
                }
            }
        }
    }

    /**
     * Méthode qui permet d'effacer les activités auxquelles un enfant était
     * inscrit pour le remplacer par un enfant venant d'être importé en ayant le
     * même identifiant.
     *
     * @param children Enfant qui doit remplacer l'enfant déjà existant
     */
    public void overwriteChildren(Children children) {
        // On parcours toutes les activités
        for (Activity act : activities.values()) {
            // On parcours tous les enfants de cette activité
            for (Children chi : act.getChildrens()) {
                // Si l'ancien enfant était enregistré sur cette activité
                if (chi.getId() == children.getId()) {
                    // On supprime l'ancien enfant
                    act.removeChildren(chi);

                    // On ajoute le nouvel enfant
                    act.addChildren(children);
                }
            }
        }
    }

    /**
     * Fonction qui permet de déterminer quelle action devra être effectuée pour
     * résoudre le conflit.
     *
     * @param object Objet sur lequel il y a conflit
     * @return Action qui devra être effectuée pour résoudre le conflit
     */
    public int manageConflict(Object object) {
        Integer temp = new Integer(0);

        String type;

        if (object instanceof Activity) {
            type = "activité";
        } else if (object instanceof Animator) {
            type = "animateur";
        } else if (object instanceof Children) {
            type = "enfant";
        } else {
            type = "Erreur interne au programme";
        }

        // Ajout des options disponibles
        TreeMap<Integer, String> opt = new TreeMap<>();
        opt.put(1, "Conserver l'" + type + " existant(e).");
        opt.put(2, "Modifier l'identifiant de l'" + type + " à importer.");
        opt.put(3, "Ecraser l'" + type + " existant(e).");
        opt.put(HELP, STR_HELP);

        System.out.println("Oups... L'" + type + " semble déjà exister (identifiant déjà utilisé)\nQue voulez-vous faire?");

        if (object instanceof Activity) {
            Activity importing = (Activity) object;
            Activity current = activities.get(importing.getId());

            System.out.println("\nActivité présente : " + current.toString() + "\nActivité en cours d'import : " + importing.toString() + "\n");
        } else if (object instanceof Animator) {
            Animator importing = (Animator) object;
            Animator current = animators.get(importing.getId());
            System.out.println("\nAnimateur présent : " + current.toString() + "\nAnimateur en cours d'import : " + importing.toString() + "\n");
        } else if (object instanceof Children) {
            Children importing = (Children) object;
            Children current = childrens.get(importing.getId());
            System.out.println("\nEnfant présent : " + current.toString() + "\nEnfant en cours d'import : " + importing.toString() + "\n");
        }

        show(opt);
        do {
            System.out.print("Votre choix (8 pour l'aide) : ");
            try {

                while (!scanner.hasNextInt() || !opt.containsKey(temp = scanner.nextInt())) {
                    System.out.print("Votre choix (8 pour l'aide) : ");
                    scanner.nextLine();
                }

                if (opt.containsKey(temp)) {
                    if (temp != HELP) {
                        return temp;
                    } else {
                        temp = 0;
                        show(opt);
                    }

                }
            } catch (Exception e) {
            }
        } while (true);

    }

    /**
     * Méthode qui renvoie un booléen en fonction de ce qu'a saisi l'utilisateur
     * ce booléen représente le fait d'utiliser tout le temps le même mode de
     * résolution de conflit.
     *
     * @return vrai si l'utilisateur veut utiliser tout le temps le même mode de
     * résolution de conflit, faux sinon
     * @see Loisirsland#importJSON()
     * @see Loisirsland#loadAnimators(loisirsland.Activity)
     * @see Loisirsland#loadChildrens(loisirsland.Activity)
     * @see Loisirsland#manageConflict(java.lang.Object)
     *
     */
    public boolean skipValidations() {
        Integer temp = new Integer(0);

        // Ajout des options disponibles
        TreeMap<Integer, String> opt = new TreeMap<>();
        opt.put(REMEMBER_YES, "Oui");
        opt.put(REMEMBER_NO, "Non");
        opt.put(HELP, STR_HELP);

        System.out.println("Souhaitez vous effectuer cette action pour chaque conflit futur (de ce type) pour cet import?");
        show(opt);

        do {
            System.out.print("Votre choix (8 pour l'aide) : ");
            try {

                while (!scanner.hasNextInt() || !opt.containsKey(temp = scanner.nextInt())) {
                    System.out.print("Votre choix (8 pour l'aide) : ");
                    scanner.nextLine();
                }

                if (opt.containsKey(temp)) {
                    if (temp == REMEMBER_YES) {
                        return true;
                    } else if (temp == REMEMBER_NO) {
                        return false;
                    } else if (temp == HELP) {
                        temp = 0;
                        show(opt);
                    }

                }
            } catch (Exception e) {
            }
        } while (true);
    }
}
