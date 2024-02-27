package org.example;

public class Main {
    public static void main(String[] args) {
        GameService game = new GameService(4, 3);
        game.addPlayerAndSymbol(1, "Arpit", '*');
        game.addPlayerAndSymbol(2, "Pankaj", 'o');

        game.play();
    }
}