package server;

import java.io.IOException;
import java.math.BigInteger;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Author: Svintenok Kate
 * Date: 03.12.2016
 * Group: 11-501
 * Project: influence
 */
public class Room implements Runnable{
    private Thread thread;
    private List<Player> players;
    private GraphGenerator graphGenerator;


    public Room(Socket socket1, Socket socket2) throws IOException {
        this.players = new ArrayList<>();
        players.add(new Player(socket1));
        players.add(new Player(socket2));

        System.out.println("Map generating..");
        graphGenerator = new GraphGenerator();
        graphGenerator.generate();

        this.thread = new Thread(this);
        this.thread.start();
    }

    @Override
    public void run() {

        sendMap();
        sendMoveOrder();

        boolean flag = true;

        while (flag) {
            if (!(move(players.get(0), players.get(1)) && move(players.get(1), players.get(0)))) {
                flag = false;
                closeRoom();
            }
        }
    }

    private void sendMap(){
        try {
            byte[] cells = graphGenerator.getByteCells();
            byte[] routes = graphGenerator.getByteRoutes();
            System.out.println("Sending map...");

            for (Player player: players) {
                player.getBufferedOutputStream().write(cells.length);
                player.getBufferedOutputStream().write(cells);
                player.getBufferedOutputStream().write(BigInteger.valueOf(routes.length).toByteArray());
                player.getBufferedOutputStream().write(routes);
                player.getBufferedOutputStream().flush();
            }

            System.out.println("Map sended");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void sendMoveOrder() {
        try {
            int i = 1;
            for (Player player: players){
                player.getBufferedOutputStream().write(i);
                player.getBufferedOutputStream().flush();
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private boolean move(Player movingPlayer, Player waitingPlayer){
        try {
            int action = movingPlayer.getBufferedInputStream().read();
            waitingPlayer.getBufferedOutputStream().write(action);
            waitingPlayer.getBufferedOutputStream().flush();
            if (action == 0)
                return false;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    private void closeRoom() {
        try {
            for (Player player: players)
                player.getSocket().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}