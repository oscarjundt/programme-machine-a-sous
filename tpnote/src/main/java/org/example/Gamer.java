package org.example;

import javax.swing.text.StyledEditorKit;

public class Gamer {
    private int nbJeton;
    private int nbGains;
    public Gamer(){
        this.nbGains = 0;
        this.nbJeton = 100;
    }
    public void addNbGains(String symbole){
        symbole = symbole.replace("(","");
        symbole = symbole.replace(")","");
        switch (symbole){
            case "7": this.nbGains+=300;
            break;
            case "BAR" : this.nbGains+=100;
            break;
            case "R" : this.nbGains+=15;
            break;
            case "P" : this.nbGains+=15;
            break;
            case "T" : this.nbGains+=15;
            break;
            case "C" : this.nbGains+=15;
            break;
        }
    }
    public void suppNbJeton(int nbJeton){
        if(this.nbJeton>0) {
            this.nbJeton-=nbJeton;
        }
    }
    public int getNbGains() {
        return nbGains;
    }

    public int getNbJeton() {
        return nbJeton;
    }

    public void setNbGains(int nbGains) {
        this.nbGains = nbGains;
    }

    public void setNbJeton(int nbJeton) {
        this.nbJeton = nbJeton;
    }
    public boolean checkJeton(int nbJeton){
        boolean stateCheck = false;
        if(nbJeton<=this.nbJeton && nbJeton!=0){
            stateCheck = true;
        }
        return stateCheck;
    }
}
