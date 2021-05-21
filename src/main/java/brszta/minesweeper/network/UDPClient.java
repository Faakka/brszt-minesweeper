package brszta.minesweeper.network;


import brszta.minesweeper.backend.game.Game;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Objects;

public class UDPClient extends Thread{
    private DatagramSocket rx;
    private int portRx = 3000;
    private int portTx = 4000;
    private byte[] rxBuffer = new byte[4096];
    private DatagramPacket rxDataPacket = new DatagramPacket(rxBuffer, 4096);
    private String rx_msg = null;
    private boolean isNewGame = false;

    private ByteArrayInputStream inputStream;
    private ObjectInputStream inputObject;
    private Game receivedGame;

    private ByteArrayOutputStream outputStream;
    private ObjectOutputStream outputObject;

    private static String conn_granted = "Connection_Granted";
    private static String conn_estblished = "Connection_established";
    private static String clientIpAddress = null;

    private boolean isConnected = false;
    private String hostIpAddress = null;

    public String getHostIpAddress() {
        return hostIpAddress;
    }

    public void setHostIpAddress(String hostIpAddress) {
        this.hostIpAddress = hostIpAddress;
    }

    private synchronized  void setRxMsg(String str){
        this.rx_msg = str;
    }
    private synchronized String getRxMsg(){
        return this.rx_msg;
    }
    private synchronized void setIsConnected(Boolean value){
        this.isConnected = value;
    }
    private synchronized boolean getIsConnected(){
        return this.isConnected;
    }

    public Game getInputGame()  {
        while(!isNewGame){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return receivedGame;
    }

    public boolean getIsServerConnected(){
        return this.isConnected;
    }

    public UDPClient(){
    }
    public UDPClient(int rxport, int txport){
        this.portRx = rxport;
        this.portTx = txport;
    }

    public String getClientIpAddress(){
        return this.clientIpAddress;
    }

    public String getOwnIp(){
        String  ip = null;
        try(final DatagramSocket socket = new DatagramSocket()){
            socket.connect(InetAddress.getByName("8.8.8.8"), 10002); //doesn't make real connection just test the device what we gonna use
            ip = socket.getLocalAddress().getHostAddress();
        }catch (Exception e){ System.out.println(e);}
        return ip;
    }

    public void udpSendString(String message, String ipAddress){
        try{
            DatagramSocket ds = new DatagramSocket();
            InetAddress ip = InetAddress.getByName(ipAddress);
            String msg = message;
            DatagramPacket dp = new DatagramPacket(msg.getBytes(), msg.length(), ip, portTx);
            ds.send(dp);
            ds.close();
            //System.out.println("Send succsessfully");
        }catch (Exception e){System.out.println("Cannot connect");}
    }

    public boolean connectToHost(String ipAddress){
        setHostIpAddress(ipAddress);
        isConnected = false;
        setRxMsg(null);
        clientIpAddress = getOwnIp();
        System.out.println("ez fut1" + rx_msg);
        System.out.println("Client ip: " + clientIpAddress);
        while (!isConnected){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("ez fut2" + rx_msg);
            udpSendString(clientIpAddress, ipAddress);
            if(Objects.equals(rx_msg, conn_granted)){
                System.out.println("ez fut3 " + rx_msg);
                udpSendString(conn_estblished, ipAddress);
                setIsConnected(true);
            }
        }
        setRxMsg(null);
        return isConnected;
    }

    public void udpSendObject(Game object, String ipAddress) throws IOException {
        //Serialize
        outputStream = new ByteArrayOutputStream();
        outputObject = new ObjectOutputStream(outputStream);
        outputObject.writeObject(object);
        outputObject.flush();
        //Datagram packet and udp send
        DatagramSocket ds = new DatagramSocket();
        InetAddress ip = InetAddress.getByName(ipAddress);
        DatagramPacket dp = new DatagramPacket(outputStream.toByteArray(), outputStream.size(), ip, portTx);
        ds.send(dp);
        ds.close();
    }

    public void udpDisconnect(){
        setIsConnected(false);
        rx.close();
    }

    public void run(){
        try {
            rx = new DatagramSocket(portRx);
        } catch (SocketException e) {
            e.printStackTrace();
        }
        while(true) {
            if (!isConnected) {
                try {
                    System.out.println("Stringet fogad");
                    rx.receive(rxDataPacket);
                    setRxMsg(new String(rxDataPacket.getData(), 0, rxDataPacket.getLength()));
                    //String str_msg = new String(rxDataPacket.getData(), 0, rxDataPacket.getLength());
                    //System.out.println(getRxMsg());
                } catch (Exception e) {
                }
            }
            else { //after connected receiver receives game object rather than string
                try{
                    //System.out.println("The cilent is: " + isConnected);
                    System.out.println("Objectet fogad");
                    rx.receive(rxDataPacket);
                    inputStream = new ByteArrayInputStream( rxDataPacket.getData());
                    inputObject = new ObjectInputStream(inputStream);
                    receivedGame = (Game) inputObject.readObject();
                    isNewGame = true;
                    /*
                    System.out.println(receivedGame.getLevel());
                    System.out.println("Objectet kaptam");
                    System.out.println(receivedGame.getBoard().getNumOfBombs());
                    System.out.println(receivedGame.getStartTime());
                    */
                    System.out.println(receivedGame.getBoard().getProgress());
                    //TO DO Deserialiaztion
                    //setRxMsg(new String(rxDataPacket.getData(), 0, rxDataPacket.getLength()));
                    //System.out.println(getRxMsg());

                }catch (Exception e){}
            }

        }
    }

}

