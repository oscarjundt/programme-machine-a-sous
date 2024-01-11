package org.example;

import com.google.gson.Gson;

import javax.swing.text.StyledEditorKit;
import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Gamer{
    //nombre de jeton a parier du joueur
    private int nbJeton;
    //nombre de jeton gagner du joueur
    private int nbGains;
    //probabiliter du joueur
    private State proba;
    public Gamer(){
        this.nbGains = 0;
        this.nbJeton = 100;
        this.proba = new State();
    }
    /**
     * La fonction « addNbGains » ajoute le nombre de gains en fonction du symbole fourni et met à jour le nombre total de
     * pièces. si le joueur a gagner
     *
     * @param symbole Le paramètre "symbole" représente le symbole transmis à la méthode. C'est une chaîne qui indique le
     * symbole que le joueur a gagné dans une partie.
     */
    public void addNbGains(String symbole){
        symbole = symbole.replace("(","");
        symbole = symbole.replace(")","");
        //ajout des les state qu'il a gagner une partie
        this.proba.addGames_won();
        //ajout des jeton gagner en fonction du symbole
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
        //ajout de la nouelle somme des jeton
        this.proba.setCoins(this.nbGains+this.nbJeton);
    }
    /**
     * La fonction `suppNbJeton` soustrait le nombre de jetons Parier du nombre total de jetons et met à jour l'état en
     * conséquence.
     *
     * @param nbJeton Le paramètre "nbJeton" représente le nombre de jetons à supprimer du nombre actuel de jetons.
     */
    public void suppNbJeton(int nbJeton){
        //si les nombre de jeton a parier n'est pas a 0
        if(this.nbJeton>0) {
            //on supprimer les jeton parier
            this.nbJeton-=nbJeton;
            //on ajoute dans les state les nombre de jeton parier
            this.proba.AddCoins_spent(nbJeton);
            //on ajoute une partie lancer dans les state
            this.proba.addGames_played();
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
    /**
     * La fonction vérifie si le nombre de jetons donné est valide.
     *
     * @param nbJeton Le paramètre "nbJeton" représente le nombre de jetons en cours de vérification.
     * @return La méthode renvoie une valeur booléenne, qui indique si le nombre de jetons donné (nbJeton) est valide ou
     * non.
     */
    public boolean checkJeton(int nbJeton){
        boolean stateCheck = false;
        if(nbJeton<=this.nbJeton && nbJeton!=0){
            stateCheck = true;
        }
        return stateCheck;
    }
    public void afficherState() throws IOException {
        System.out.println("jetons Actuel : "+this.proba.getCoins());
        System.out.println("nombre de partie gagner : "+this.proba.getGames_won());
        System.out.println("nombre de jeton depanser : "+this.proba.getCoins_spent());
        jsonConvertState();
    }
    /**
     * La fonction `jsonConvertState()` utilise la bibliothèque Gson pour convertir un objet au format JSON et l'écrit dans
     * un fichier nommé « stat.json ».
     * les donner envoyer en format json dans le fichier stat.json sont les statistique du joueur
     */
    public void jsonConvertState() throws IOException {
        //utilisation de la bilioteque gson
        //pour convertir un objet en json
        Gson gson = new Gson();
        Writer writer = Files.newBufferedWriter(Paths.get("stat.json"));
        //convertion de l'objet proba en json et ajout dans le fichier stat.json
        gson.toJson(this.proba,writer);
        writer.close();
    }
}
