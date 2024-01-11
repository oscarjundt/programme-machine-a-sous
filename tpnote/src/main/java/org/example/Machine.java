package org.example;

import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;

import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Machine {
    private Column colonne;
    // Le constructeur `public Machine()` initialise une nouvelle instance de la classe `Machine`.
    public Machine(){
        Gson gson = new Gson();
        this.colonne =  new Column(gson.fromJson("[\n" +
                "  [\"(7)\", \"(C)\", \"BAR\", \"(T)\", \"(P)\", \"(7)\", \"(C)\", \"BAR\", \"(T)\", \"(R)\", \"(7)\", \"(C)\", \"BAR\", \"(R)\", \"(P)\"],\n" +
                "  [\"(7)\", \"(P)\", \"(C)\", \"(T)\", \"(R)\", \"BAR\", \"(C)\", \"(T)\", \"(R)\", \"(C)\", \"BAR\", \"(P)\", \"(T)\", \"(C)\", \"(R)\"],\n" +
                "  [\"(7)\", \"BAR\", \"(T)\", \"(P)\", \"(C)\", \"(R)\", \"(T)\", \"(P)\", \"(C)\", \"(R)\", \"(T)\", \"(P)\", \"(C)\", \"(R)\", \"(T)\"]\n" +
                "]",String[][].class));

    }

    public void setColonne(Column colonne) {
        this.colonne = colonne;
    }

    /**
     * La fonction "welcomeInterface" imprime un message de bienvenue sur la console.
     */
    private void welcomeInterface(){
        System.out.println("°º¤ø,¸¸,ø¤º°`°º¤ø,¸,ø¤°º¤ø,¸¸,ø¤º°`");
        System.out.println("Bienvenue au Casino de Céladopole !");
        System.out.println("°º¤ø,¸¸,ø¤º°`°º¤ø,¸,ø¤°º¤ø,¸¸,ø¤º°`");
        System.out.println("\n");
    }

    /**
     * La fonction start() initialise un objet Gamer, affiche une interface de bienvenue, charge une machine et affiche un
     * menu et une interface d'action avec lesquels l'utilisateur peut interagir.
     *
     * elle lance et initialise la machine a sous
     *
     *
     */
    public void start() throws InterruptedException, IOException {
        Gamer gamer = new Gamer();
        String choice="next";
        //initalisation de la machine
        this.welcomeInterface();
        this.loadMachine(0,gamer);
        this.menuAndActionInterface(gamer);
        //tant que l'utilisateur veux jouer

    }
    /**
     * La fonction "menuAndActionInterface" permet à un joueur de choisir le nombre de jetons à miser, vérifie si le joueur
     * a suffisamment de jetons, soustrait le nombre de jetons choisi du total du joueur, charge la machine avec le nombre
     * de jetons choisi et convertit le nombre de jetons du joueur. statistiques au format JSON.
     *
     *
     * @param gamer Le paramètre "gamer" est un objet de la classe "Gamer".
     */
    private void menuAndActionInterface(Gamer gamer) throws InterruptedException, IOException {
        Scanner Input = new Scanner(System.in);
        String choice="next";
        while(!choice.isEmpty()) {
            //on lui annonce c'est gains
            System.out.println("Gains : "+gamer.getNbGains());
            //on lui annonce c'est Jetons
            System.out.println("Jetons possédés : "+gamer.getNbJeton());
            //on lui demande de mettre le nombre choisie
            System.out.print("Combien de jetons ? (1, 2 ou 3) : ");
            choice=Input.next();
            int numberChoice = Integer.parseInt(choice);
            //On check si il peux miser les nombre saisie
            if(gamer.checkJeton(numberChoice) && StringUtils.containsAny(choice, '1', '2', '3')) {
                //si oui on supprimer le nombre de jeton choisie
                gamer.suppNbJeton(numberChoice);
                //le joueur lancer les colonne de la machine
                this.loadMachine(numberChoice,gamer);
                //a la fin du lancement les donnée statistique
                //du joueur sont envoyer dans un json
                gamer.jsonConvertState();
            }
            else{
                if(!gamer.checkJeton(numberChoice)) {
                    System.out.println("Plus d'argent");
                }
                else if(!StringUtils.containsAny(choice, '1', '2', '3')){
                    System.out.println("Erreur de saisi");
                }
            }
        }
    }
    /**
     * La fonction "loadMachine" prend un choix de nombre et un joueur en entrée, et fait tourner les colonnes d'une
     * machine à sous à plusieurs reprises jusqu'à ce que le joueur gagne ou que les colonnes aient tourné 3 fois.
     *
     *
     * @param numberChoice Le numéro choisi par le joueur pour jouer au jeu.
     * @param gamer Le paramètre "gamer" est un objet de la classe "Gamer".
     */
    private void loadMachine(int numberChoice,Gamer gamer) throws InterruptedException {
        boolean checkColumn = false;
        int incr=0;
        do {
            //il fait tourner les colonne
            this.afficheResult(this.colonne.loadColumn());
            //il check si le client a gagner en fonction de ce qu'il a miser
            checkColumn = this.colonne.checkColumn(numberChoice);
            //si il a gagenr, on lui annonce qu'il a gagner et on ajout ce qu'il a gagner dans c'est gains
            if (checkColumn) {
                System.out.println("Gagner");
                //on ajoute ce qu'il a gagner au gains d'avant
                gamer.addNbGains(this.colonne.checkSymboleColumn(numberChoice));
            }
            incr++;
            if(numberChoice!=0) {
                TimeUnit.SECONDS.sleep(1);
            }
        } while (incr<=3 && !checkColumn && numberChoice!=0);
        //tant que les colonne non pas tourner 3 fois et que le joueur n'a pas gagner
        //si les colonne on tourner 5 fois et que le joueur na pas gagner
        // on lui annonce qu'il a perdu
        if(!checkColumn && numberChoice!=0){
            System.out.println("Perdu");
        }

    }

    /**
     * Affiche les colonne de la machine a sous
     *
     * @param columnRand Le paramètre `columnRand` est une liste de listes de chaînes. Il représente un tableau dans lequel
     * chaque liste interne représente une colonne et chaque chaîne représente une valeur dans cette colonne.
     */
    private void afficheResult(List<List<String >> columnRand){
        //boucle sur les colonne de la machine a sous pour les afficher en console
        for(int i=0;i<columnRand.size();i++){
            for (int j=0;j<columnRand.get(i).size();j++){
                System.out.print(" | "+columnRand.get(j).get(i)+" | ");
            }
            System.out.println();
        }
        System.out.println("\n\n");
    }
}
