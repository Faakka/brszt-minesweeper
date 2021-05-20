package brszta.minesweeper.network;


import brszta.minesweeper.backend.game.Game;

import java.io.*;
import java.net.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Objects;

public class UDPServer extends Thread{
    private DatagramSocket rx = null;
    private int portRx = 4000;
    private int portTx = 3000;
    private byte[] rxBuffer = new byte[4096];
    private DatagramPacket rxDataPacket = new DatagramPacket(rxBuffer, 4096);
    private SocketAddress remoteClientIP = null;
    private String rx_msg = null;

    private ByteArrayInputStream inputStream;
    private ObjectInputStream inputObject;
    private Game receivedGame;

    private ByteArrayOutputStream outputStream;
    private ObjectOutputStream outputObject;

    private static String conn_granted = "Connection_Granted";
    private static String conn_estblished = "Connection_established";
    private static String nullstring;
    private static String clientIpAddress = null;

    private boolean isConnected = false;

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

    public Game getInputGame(){
        return receivedGame;
    }

    public boolean getIsClientConnected(){
        return this.isConnected;
    }

    public String getClientIpAddress(){
        return clientIpAddress;
    }

    public UDPServer(){
    }

    public UDPServer(int rxport, int txport){
        this.portRx = rxport;
        this.portTx = txport;
    }

    public String getOwnIp(){
        String  ip = null;
        try(final DatagramSocket socket = new DatagramSocket()){
            socket.connect(InetAddress.getByName("8.8.8.8"), 10002); //doesn't make real connection just test the device what we gonna use
            ip = socket.getLocalAddress().getHostAddress();
        }catch (Exception e){ System.out.println(e);}
        return ip;
    }

    public DatagramPacket getRxData(){
        return this.rxDataPacket;
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
        }catch (Exception e){}
    }

    public boolean startHost() {
        setIsConnected(false);
        System.out.println(getRxMsg());
        setRxMsg(null);
        boolean first_receive = true;
        while (!isConnected) {
            //System.out.println(getRxMsg());
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(rx_msg != nullstring && first_receive) {
                clientIpAddress = rx_msg;
                System.out.println("Client ip:" + clientIpAddress);
                //System.out.println("ez fut1");
                udpSendString(conn_granted, clientIpAddress);
                first_receive  = false;
            }
            if(Objects.equals(rx_msg, conn_estblished)){
                setIsConnected(true);
            }
        }
        setRxMsg(null);
        return isConnected;
    }

    public void udpSendObject(Game object, String ipAddress) throws IOException {
        //Serialize
        System.out.println("Clinet ip: " + ipAddress);
        outputStream = new ByteArrayOutputStream();
        outputObject = new ObjectOutputStream(outputStream);
        outputObject.writeObject(object);
        outputObject.flush();
        //Datagram packet and udp send
        DatagramSocket ds = new DatagramSocket();
        InetAddress ip = InetAddress.getByName("192.168.1.108");
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
            if(!isConnected) {
                try {
                    rx.receive(rxDataPacket); //blocking statement
                    setRxMsg(new String(rxDataPacket.getData(), 0, rxDataPacket.getLength()));
                    //rx_msg = new String(rxDataPacket.getData(), 0, rxDataPacket.getLength());
                    //System.out.println(getRxMsg());
                } catch (Exception e) {
                }
            }
            else { //after connected receiver receives game object rather than string

                try{
                    //System.out.println("The server is: " + isConnected);
                    System.out.println("objectet varok");
                    rx.receive(rxDataPacket);
                    inputStream = new ByteArrayInputStream( rxDataPacket.getData());
                    inputObject = new ObjectInputStream(inputStream);
                    receivedGame = (Game) inputObject.readObject();

                    //TO DO Deserialiaztion
                    //setRxMsg(new String(rxDataPacket.getData(), 0, rxDataPacket.getLength()));
                    //System.out.println(getRxMsg());

                }catch (Exception e){}
            }
        }

    }

}