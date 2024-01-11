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
        //initaliser un matrice
        //cette matrice et les resultat de la machine a sous
        List<List<String>> colomun = new ArrayList<>();
        //fait tourenr les colonne de la machine de maniere aleatoire
        this.c1  = (dataColumnRand(this.JsonColumn[0], new Random().nextInt(this.JsonColumn[0].length)));
        this.c2 = (dataColumnRand(this.JsonColumn[1], new Random().nextInt(this.JsonColumn[1].length)));
        this.c3 = (dataColumnRand(this.JsonColumn[2], new Random().nextInt(this.JsonColumn[2].length)));
        //remplis la matrice et l'envoi a la machine
        colomun.add(c1);
        colomun.add(c2);
        colomun.add(c3);
        return colomun;
    }

    /**
     * La fonction `checkColumn` vérifie si les colonnes d'un machine a sous ont le même symbole.
     * si c'est le cas, le joueur a gagner
     *
     * @param nbJeton Le paramètre nbJeton représente le nombre de lignes pour vérifier la correspondance des symboles.
     * @return La méthode renvoie une valeur booléenne. Il renvoie vrai si l’une des conditions est remplie, sinon il
     * renvoie faux.
     */
    public boolean checkColumn(int nbJeton){
        boolean statut = false;
        //si uniquement la ligne horizontale du milieu
        if(nbJeton==1){
                //check si la ligne horizontale du milieu a les même symbole
                if(this.c1.get(1).equals(this.c2.get(1)) && this.c1.get(1).equals(this.c3.get(1))){
                    statut = true;
                }
        }
        //si  les 3 lignes horizontales
        else if(nbJeton==2){
            //check si une des 3 ligne horizontales a les même symbole
            for(int i=0;i<3;i++){
                if(this.c1.get(i).equals(this.c2.get(i)) && this.c1.get(i).equals(this.c3.get(i))){
                    statut = true;
                }
            }
        }
        //si les 3 lignes horizontales + les 2 diagonales
        else if(nbJeton==3){
            //check si une des 3 ligne horizontales a les même symbole
            for(int i=0;i<3;i++){
                if(this.c1.get(i).equals(this.c2.get(i)) && this.c1.get(i).equals(this.c3.get(i))){
                    statut = true;
                }
            }
            //si une des 3 ligne horizontales n'a pas les même symbole
            if(!statut){
                //check si une des 2 diagonales a les même symbole
                if(this.c1.get(0).equals(this.c2.get(1)) && this.c1.get(0).equals(this.c3.get(2)) || this.c1.get(2).equals(this.c2.get(1)) && this.c1.get(2).equals(this.c3.get(0))){
                    statut = true;
                }
            }
        }
        //return true si une des condition est remplis, sinon false
        return statut;
    }
    /**
     * La fonction "checkSymboleColumn" recherche un symbole spécifique dans les colonne d'une machine casino
     * si le joueur a gagner
     *
     * @param nbjeton Le paramètre "nbjeton" représente le nombre de tokens dans une colonne.
     * @return La méthode renvoie une variable String nommée "Symbole".
     */
    public String checkSymboleColumn(int nbjeton){
        //recupere le symbole en fonction du type de mise
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
        else if(nbjeton==3){
            for(int i=0;i<3;i++){
                if(this.c1.get(i).equals(this.c2.get(i)) && this.c1.get(i).equals(this.c3.get(i))){
                    Symbole=this.c1.get(i);
                }
            }
            if(Symbole.equals("")){
                //check si une des 2 diagonales a les même symbole
                if(this.c1.get(0).equals(this.c2.get(1)) && this.c1.get(0).equals(this.c3.get(2)) || this.c1.get(2).equals(this.c2.get(1)) && this.c1.get(2).equals(this.c3.get(0))){
                    Symbole=this.c1.get(0);
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
