package org.example;

import org.example.pojos.Board;
import org.example.pojos.Player;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GameService {

    Map<Integer, Player> players = new HashMap<>();
    Board board = new Board();
    private int winSize = 0;
    private int totalChance = 0;
    GameService(int boardSize, int winSize) {
        board.setSize(boardSize);
        board.setCell(new int[boardSize][boardSize]);
        this.winSize = winSize;
    }

    public Player addPlayerAndSymbol(int playerId, String playerName, char symbol) {
        Player player = new Player();
        player.setPlayerId(playerId);
        player.setPlayerName(playerName);
        player.setPlayerSymbol(symbol);

        players.put(playerId, player);
        System.out.println("Added player " + player.getPlayerId());

        return player;
    }


    public void play() {
        Scanner sc = new Scanner(System.in);
        for(int[] b : board.getCell()) {
            Arrays.fill(b, -1);
        }

        System.out.println("Welcome to Tic Tac Toe!");
        while (true) {
            for(int i=1; i<=players.size(); i++) {
                int playerId = players.get(i).getPlayerId();
                System.out.println("Player " + playerId + " Chance");
                int x = sc.nextInt();
                int y = sc.nextInt();

                totalChance++;

                board.getCell()[x][y] = playerId;

                if(isWinner(playerId,x,y)) {
                    System.out.println("-------Player: " + players.get(i).getPlayerName() + " Won---------");
                    printBoard();
                    return;
                }

                if(totalChance== board.getSize()* board.getSize()) {
                    System.out.println("Its a Tie!");
                    return;
                }
                printBoard();
            }
        }
    }

    public void printBoard() {
        for(int i=0; i<board.getSize(); i++) {
            for(int j=0; j<board.getSize(); j++) {
                System.out.print(board.getCell()[i][j] + " ");
            }
            System.out.println();
        }
    }

    public boolean isWinner(int playerId, int x, int y) {

        //x Axis
        int count =1;
        int yy = y-1;
        while(yy>=0 && board.getCell()[x][yy--] == playerId) count++;
        yy = y+1;
        while(yy< board.getSize() && board.getCell()[x][yy++] == playerId)  count++;
        if(count >= winSize)    return true;


        //y Axis
        count = 1;
        int xx = x-1;
        while(xx>=0 && board.getCell()[xx--][y] == playerId) count++;
        xx = x+1;
        while(xx< board.getSize() && board.getCell()[xx++][y] == playerId)   count++;
        if(count >= winSize)    return true;

        //first diagonal
        count = 1;
        xx = x-1;   yy = y-1;
        while(xx>=0 && yy>=0 &&  board.getCell()[xx--][yy--] == playerId) count++;
        xx = x+1; yy = y+1;
        while(xx< board.getSize() && yy< board.getSize() && board.getCell()[xx++][yy++] == playerId)   count++;
        if(count >= winSize)    return true;

        //second diagonal
        count = 1;
        xx = x-1;   yy = y+1;
        while(xx>=0 && yy< board.getSize() && board.getCell()[xx--][yy++] == playerId) count++;
        xx = x+1; yy = y-1;
        while(xx< board.getSize() && yy>=0 && board.getCell()[xx++][yy--] == playerId)   count++;
        if(count >= winSize)    return true;

        return false;
    }
}
