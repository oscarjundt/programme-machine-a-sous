package org.example;

import java.io.Serializable;

public class State implements Serializable {
    private  int coins;
    private  int games_played;
    private  int games_won;
    private  int coins_spent;

    public State(){
        coins = 0;
        games_won = 0;
        games_played = 0;
        coins_spent =0;
    }
    public  int getCoins() {
        return coins;
    }

    public  int getCoins_spent() {
        return coins_spent;
    }

    public  int getGames_played() {
        return games_played;
    }

    public  int getGames_won() {
        return games_won;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public void addGames_played(){
        this.games_played++;
    }
    public void addGames_won(){
        this.games_won++;
    }
    public void addCoins(int coins){
        this.coins+=coins;
    }
    public void AddCoins_spent(int nbjeton){
        this.coins_spent+=nbjeton;
    }
}
