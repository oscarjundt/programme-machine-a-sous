package org.example;

import com.google.gson.Gson;

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
     * La fonction start() imprime le résultat du chargement d'une machine et continue de le faire jusqu'à ce qu'une
     * certaine condition soit remplie.
     */
    public void start() throws InterruptedException {
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
                //on lance la machine
                this.loadMachine(numberChoice,gamer);
            }
            else{
                System.out.println("Plus d'argent");
            }
        }
    }
    /**
     * La fonction "loadMachine" renvoie une liste de listes de chaînes, obtenue en appelant la méthode "loadColumn" de
     * l'objet "colonne".
     *
     * @return Une liste de listes de chaînes est renvoyée.
     */
    private void loadMachine(int numberChoice,Gamer gamer){
        boolean checkColumn = false;
        do {
            //il fait tourner les colonne
            this.afficheResult(this.colonne.loadColumn());
            //il check si le client a gagner en fonction de ce qu'il a miser
            checkColumn = this.colonne.checkColumn(numberChoice);
            //si il a gagenr, on lui annonce qu'il a gagner et on ajout ce qu'il a gagner dans c'est gains
            if (checkColumn) {
                System.out.println("Gagner");
                gamer.addNbGains(this.colonne.checkSymboleColumn(numberChoice));
            }
        } while (!checkColumn && numberChoice!=0);
    }

    /**
     * Affiche les colonne de la machine a sous
     *
     * @param columnRand Le paramètre `columnRand` est une liste de listes de chaînes. Il représente un tableau dans lequel
     * chaque liste interne représente une colonne et chaque chaîne représente une valeur dans cette colonne.
     */
    private void afficheResult(List<List<String >> columnRand){
        for(int i=0;i<columnRand.size();i++){
            for (int j=0;j<columnRand.get(i).size();j++){
                System.out.print(" | "+columnRand.get(j).get(i)+" | ");
            }
            System.out.println();
        }
        System.out.println("\n\n");
    }
}
