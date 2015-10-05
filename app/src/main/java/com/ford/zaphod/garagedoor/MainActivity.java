package com.ford.zaphod.garagedoor;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class MainActivity extends Activity implements View.OnClickListener {

    private final static String MESSAGE = "31337 activate";
    private final static String IPADDRESS = "172.17.10.25";
    private final int PORT = 6736;
    private Button mButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = (Button)findViewById(R.id.garagebutton);
        mButton.setOnClickListener(this);
    }

    @Override
    public void onClick (View v) {
        try {
            InetAddress serverAddr = InetAddress.getByName(IPADDRESS);
            DatagramSocket clientSocket = new DatagramSocket();
            byte[] sendData = new byte[1024];
            sendData = MESSAGE.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddr, PORT);
            clientSocket.send(sendPacket);

            clientSocket.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
