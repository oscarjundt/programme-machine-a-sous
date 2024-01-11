package org.example;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Machine {
    private Column colonne;
    //private String[][] Matrice;
    // Le constructeur `public Machine()` initialise une nouvelle instance de la classe `Machine`.
    public Machine(){
        Gson gson = new Gson();
        this.colonne =  new Column(gson.fromJson("[\n" +
                "  [\"(7)\", \"(C)\", \"BAR\", \"(T)\", \"(P)\", \"(7)\", \"(C)\", \"BAR\", \"(T)\", \"(R)\", \"(7)\", \"(C)\", \"BAR\", \"(R)\", \"(P)\"],\n" +
                "  [\"(7)\", \"(P)\", \"(C)\", \"(T)\", \"(R)\", \"BAR\", \"(C)\", \"(T)\", \"(R)\", \"(C)\", \"BAR\", \"(P)\", \"(T)\", \"(C)\", \"(R)\"],\n" +
                "  [\"(7)\", \"BAR\", \"(T)\", \"(P)\", \"(C)\", \"(R)\", \"(T)\", \"(P)\", \"(C)\", \"(R)\", \"(T)\", \"(P)\", \"(C)\", \"(R)\", \"(T)\"]\n" +
                "]",String[][].class));

    }

    /**
     * La fonction start() permet à un joueur de jouer à un jeu de machine à sous en saisissant le nombre de jetons à
     * parier, en vérifiant si le joueur a suffisamment de jetons, puis en exécutant la machine à sous.
     *
     * un utilisateur lance la machine
     *
     *
     */
    public void start() throws InterruptedException, IOException {
        Gamer gamer = new Gamer();
        Scanner Input = new Scanner(System.in);
        String choice="next";
        //initalisation des colonne a l'alumage de la machine
        this.loadMachine(0,gamer);
        //tant que l'utilisateur veux jouer
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
            if(gamer.checkJeton(numberChoice)) {
                //si oui on supprimer le nombre de jeton choisie
                gamer.suppNbJeton(numberChoice);
                //le joueur lancer les colonne de la machine
                this.loadMachine(numberChoice,gamer);
                //a la fin du lancement les donnée statistique
                //du joueur sont envoyer dans un json
                gamer.jsonConvertState();
            }
            else{
                System.out.println("Plus d'argent");
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
    private void loadMachine(int numberChoice,Gamer gamer){
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
        } while (incr<=3 && !checkColumn && numberChoice!=0);
        //tant que les colonne non pas tourner 3 fois et que le joueur n'a pas gagner
        //si les colonne on tourner 5 fois et que le joueur na pas gagner
        // on lui annonce qu'il a perdu
        if(!checkColumn){
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
