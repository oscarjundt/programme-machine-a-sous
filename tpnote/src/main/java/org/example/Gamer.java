package org.example;

public class Gamer {
    private int nbJeton;
    private int nbGains;
    public Gamer(){
        this.nbGains = 0;
        this.nbJeton = 0;
    }
    public void addNbGains(){
        this.nbGains++;
    }
    public void suppNbJeton(){
        if(this.nbJeton>0) {
            this.nbJeton--;
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
}
