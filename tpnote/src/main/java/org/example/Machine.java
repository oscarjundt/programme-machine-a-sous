package org.example;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

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
    public void start(){
        //System.out.println(Arrays.deepToString(loadMachine()));
        this.afficheResult(this.loadMachine());
        while (!this.colonne.checkColumn(2)) this.afficheResult(this.loadMachine());
    }
    /**
     * La fonction "loadMachine" renvoie une liste de listes de chaînes, obtenue en appelant la méthode "loadColumn" de
     * l'objet "colonne".
     *
     * @return Une liste de listes de chaînes est renvoyée.
     */
    private List<List<String>> loadMachine(){
        return this.colonne.loadColumn();
    }
    /**
     * La fonction afficheResult imprime les éléments d'une liste 2D sous forme de tableau puis vérifie une colonne
     * spécifique.
     *
     * @param columnRand Le paramètre `columnRand` est une liste de listes de chaînes. Chaque liste interne représente une
     * colonne de données et la liste externe représente une collection de colonnes.
     */
    private void afficheResult(List<List<String >> columnRand){
        for(int i=0;i<columnRand.size();i++){
            for (int j=0;j<columnRand.get(i).size();j++){
                System.out.print(" | "+columnRand.get(j).get(i)+" | ");
            }
            System.out.println();
        }
        System.out.println(this.colonne.checkColumn(1));
    }
}
