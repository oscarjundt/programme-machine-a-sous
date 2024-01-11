package org.example;

import java.util.*;

public class Column {
    private List<String> c1;
    private List<String> c2;
    private List<String> c3;
    private final String[][] JsonColumn;
    // Le code `public Column(String[][] JsonColumn){ this.JsonColumn = JsonColumn; }` est un constructeur pour la classe
    // `Column`. Il prend un tableau 2D de chaînes `JsonColumn` comme paramètre et l'assigne à la variable d'instance
    // `JsonColumn` de la classe. Cela permet d'accéder et d'utiliser les données `JsonColumn` dans les méthodes de la
    // classe `Column`.
    public Column(String[][] JsonColumn){
        this.JsonColumn = JsonColumn;
    }
    /**
     * La fonction "loadColumn" renvoie une liste de listes contenant des éléments sélectionnés aléatoirement dans trois
     * colonnes différentes.
     *
     * @return La méthode loadColumn() renvoie une liste de listes de chaînes.
     */
    public List<List<String>> loadColumn(){
        List<List<String>> colomun = new ArrayList<>();
        this.c1  = (dataColumnRand(this.JsonColumn[0], new Random().nextInt(this.JsonColumn[0].length)));
        this.c2 = (dataColumnRand(this.JsonColumn[1], new Random().nextInt(this.JsonColumn[1].length)));
        this.c3 = (dataColumnRand(this.JsonColumn[2], new Random().nextInt(this.JsonColumn[2].length)));
        colomun.add(c1);
        colomun.add(c2);
        colomun.add(c3);
        return colomun;
    }
    /**
     * La fonction vérifie si une colonne spécifiée dans une grille 2D contient la même valeur dans toutes ses cellules.
     *
     * @param nbJeton Le paramètre nbJeton représente le numéro de colonne à vérifier. Il peut avoir les valeurs 1, 2 ou 3.
     * @return La méthode renvoie une valeur booléenne, qui indique si une certaine condition est vraie ou fausse.
     */
    public boolean checkColumn(int nbJeton){
        boolean statut = false;
        if(nbJeton==1){
                if(this.c1.get(1).equals(this.c2.get(1)) && this.c1.get(1).equals(this.c3.get(1))){
                    statut = true;
                }
        }
        else if(nbJeton==2){
            for(int i=0;i<3;i++){
                if(this.c1.get(i).equals(this.c2.get(i)) && this.c1.get(i).equals(this.c3.get(i))){
                    statut = true;
                }
            }
        }
        else if(nbJeton==3){
            statut = true;
        }
        return statut;
    }
    public String checkSymboleColumn(int nbjeton){
        String Symbole = "";
        if(nbjeton==1){
            Symbole = this.c1.get(1);
        }
        else if(nbjeton==2){
            for(int i=0;i<3;i++){
                if(this.c1.get(i).equals(this.c2.get(i)) && this.c1.get(i).equals(this.c3.get(i))){
                    Symbole=this.c1.get(i);
                }
            }
        }
        return Symbole;
    }
    /**
     * La fonction prend un tableau de chaînes et un index, et renvoie une ArrayList contenant trois éléments du tableau à
     * partir de l'index donné.
     *
     * @param col Un tableau de chaînes représentant une colonne de données.
     * @param index Le paramètre index est un entier qui représente l'index de départ dans le tableau col.
     * @return La méthode renvoie une ArrayList de chaînes.
     */
    private ArrayList<String> dataColumnRand(String[] col, int index){
        ArrayList<String> colonne = new ArrayList<>();
        for(int i=0;i<3;i++){
            if(index>=col.length){
                 index=0;
            }
            colonne.add(col[index]);
            index++;
        }
        return colonne;
    }
}
