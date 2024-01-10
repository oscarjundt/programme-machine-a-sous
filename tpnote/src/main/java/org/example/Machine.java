package org.example;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Machine {
    private Column colonne;
    //private String[][] Matrice;
    public Machine(){
        Gson gson = new Gson();
        this.colonne =  new Column(gson.fromJson("[\n" +
                "  [\"(7)\", \"(C)\", \"BAR\", \"(T)\", \"(P)\", \"(7)\", \"(C)\", \"BAR\", \"(T)\", \"(R)\", \"(7)\", \"(C)\", \"BAR\", \"(R)\", \"(P)\"],\n" +
                "  [\"(7)\", \"(P)\", \"(C)\", \"(T)\", \"(R)\", \"BAR\", \"(C)\", \"(T)\", \"(R)\", \"(C)\", \"BAR\", \"(P)\", \"(T)\", \"(C)\", \"(R)\"],\n" +
                "  [\"(7)\", \"BAR\", \"(T)\", \"(P)\", \"(C)\", \"(R)\", \"(T)\", \"(P)\", \"(C)\", \"(R)\", \"(T)\", \"(P)\", \"(C)\", \"(R)\", \"(T)\"]\n" +
                "]",String[][].class));

    }
    public void start(){
        //System.out.println(Arrays.deepToString(loadMachine()));
        this.afficheResult(this.loadMachine());
        while (!this.colonne.checkColumn(2)) this.afficheResult(this.loadMachine());
    }
    private List<List<String>> loadMachine(){
        return this.colonne.loadColumn();
    }
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
