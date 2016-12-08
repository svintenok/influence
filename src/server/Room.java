package server;

import java.io.IOException;
import java.net.Socket;

/**
 * Author: Svintenok Kate
 * Date: 03.12.2016
 * Group: 11-501
 * Project: influence
 */
public class Room implements Runnable{
    private Thread thread;
    private Player player1;
    private Player player2;


    public Room(Socket socket1, Socket socket2) throws IOException {
        this.player1 = new Player(socket1);
        this.player2 = new Player(socket2);
        this.thread = new Thread(this);
        this.thread.start();
        generateGameMap();
    }

    @Override
    public void run() {

        boolean flag = true;
        player1.getPrintWriter().println("1");
        player2.getPrintWriter().println("2");

        while (flag) {
            if (!(move(player1, player2) && move(player2, player1))) {
                flag = false;
                closeRoom();
            }
        }
    }

    private void closeRoom() {
        try {
            player1.getSocket().close();
            player2.getSocket().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean move(Player movingPlayer, Player waitingPlayer){
        try {
            String action = movingPlayer.getBufferedReader().readLine();
            waitingPlayer.getPrintWriter().println(action);
            if (action.equals("-1"))
                return false;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    private void generateGameMap(){
        GraphGenerator graphGenerator = new GraphGenerator();
        graphGenerator.generate();
    }
}