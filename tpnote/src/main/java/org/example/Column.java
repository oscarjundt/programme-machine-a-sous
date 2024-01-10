package org.example;

import java.util.*;

public class Column {
    private List<String> c1;
    private List<String> c2;
    private List<String> c3;
    private final String[][] JsonColumn;
    public Column(String[][] JsonColumn){
        this.JsonColumn = JsonColumn;
    }
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
