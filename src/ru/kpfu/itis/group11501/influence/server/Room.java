package ru.kpfu.itis.group11501.influence.server;

import java.io.IOException;
import java.math.BigInteger;
import java.net.Socket;
import java.util.*;

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
    private Random random;


    public Room(Player player1, Player player2) throws IOException {
        this.players = new ArrayList<>();
        players.add(player1);
        players.add(player2);

        System.out.println("GameMap generating..");
        graphGenerator = new GraphGenerator();
        graphGenerator.generate();

        this.random = new Random();
        this.thread = new Thread(this);
        this.thread.start();
    }

    @Override
    public void run() {

        sendMap();
        sendOrder();
        sendStartingCells();

        boolean flag = true;

        while (flag) {
            if (!(move(players.get(0), players.get(1)) && move(players.get(1), players.get(0))))
                flag = false;
        }

        closeRoom();
        System.out.println("Конец игры");
    }

    private void sendMap(){
        try {
            byte[] cells = graphGenerator.getByteCells();
            byte[] routes = graphGenerator.getByteRoutes();
            System.out.println("Sending map...");

            for (Player player: players) {
                player.getBufferedOutputStream().write(cells.length);
                System.out.println(cells.length);
                player.getBufferedOutputStream().write(cells);
                player.getBufferedOutputStream().write(BigInteger.valueOf(routes.length).toByteArray());
                System.out.println(routes.length);
                player.getBufferedOutputStream().write(routes);
                player.getBufferedOutputStream().flush();
            }

            System.out.println("GameMap sended");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendStartingCells() {
        Set<Integer> occupiedCells = new HashSet<>();

        for (int i = 0; i < players.size(); i++){
            int cellNum;
            do {
                cellNum = random.nextInt(graphGenerator.cellsMaxCount);
            } while (graphGenerator.getCell(cellNum) == null || occupiedCells.contains(cellNum));

            occupiedCells.add(cellNum);

            try {
                for (Player player: players){
                    player.getBufferedOutputStream().write(cellNum);
                    player.getBufferedOutputStream().flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Starting cells sended");
    }


    private void sendOrder() {
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
            System.out.println("Ходит игрок " + (players.indexOf(movingPlayer) + 1));


            while (movingPlayer.getBufferedInputStream().read() != 0){
                waitingPlayer.getBufferedOutputStream().write(1);
                waitingPlayer.getBufferedOutputStream().flush();

                byte[] moving = new byte[5];
                movingPlayer.getBufferedInputStream().read(moving);
                waitingPlayer.getBufferedOutputStream().write(moving);
                waitingPlayer.getBufferedOutputStream().flush();
            }

            int count = movingPlayer.getBufferedInputStream().read();


            if (count == 0) {
                movingPlayer.getBufferedOutputStream().write(0);
                waitingPlayer.getBufferedOutputStream().flush();
                waitingPlayer.getBufferedOutputStream().write(new byte[]{0, 0});
                waitingPlayer.getBufferedOutputStream().flush();
                return false;
            }


            waitingPlayer.getBufferedOutputStream().write(0);
            waitingPlayer.getBufferedOutputStream().flush();


            waitingPlayer.getBufferedOutputStream().write(count);
            waitingPlayer.getBufferedOutputStream().flush();
            for (int i = 0; i < count; i++){
                int cellNum = movingPlayer.getBufferedInputStream().read();
                waitingPlayer.getBufferedOutputStream().write(cellNum);
                waitingPlayer.getBufferedOutputStream().flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private void closeRoom() {
        try {
            for (Player player: players) {
                player.getSocket().close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}