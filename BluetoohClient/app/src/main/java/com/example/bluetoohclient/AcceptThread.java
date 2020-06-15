package com.example.bluetoohclient;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

import java.io.IOException;
import java.util.UUID;

import static android.content.ContentValues.TAG;

public class AcceptThread extends Thread {
    private final BluetoothServerSocket mmServerSocket;

    public UUID MY_UUID;
    public String NAME = "bluetooth";

    MainActivity mainActivity;

    public AcceptThread(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        // Use a temporary object that is later assigned to mmServerSocket
        // because mmServerSocket is final.
        BluetoothServerSocket tmp = null;
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        MY_UUID = UUID.fromString("5a064f1c-b88e-43c2-b8b8-b08cd8b851cb");


        if (bluetoothAdapter == null) {
            // Device doesn't support Bluetooth
        }
        try {
            // MY_UUID is the app's UUID string, also used by the client code.
            tmp = bluetoothAdapter.listenUsingRfcommWithServiceRecord(NAME, MY_UUID);
        } catch (IOException e) {
            Log.e(TAG, "Socket's listen() method failed", e);
        }
        mmServerSocket = tmp;
    }

    public void run() {
        BluetoothSocket socket = null;
        // Keep listening until exception occurs or a socket is returned.
        while (true) {
            try {
                socket = mmServerSocket.accept();
            } catch (IOException e) {
                Log.e(TAG, "Socket's accept() method failed", e);
                break;
            }

            if (socket != null) {
                // A connection was accepted. Perform work associated with
                // the connection in a separate thread.
                //manageMyConnectedSocket(socket);
                this.mainActivity.createConnectedThread();
            }
        }
    }

    // Closes the connect socket and causes the thread to finish.
    public void cancel() {
        try {
            mmServerSocket.close();
        } catch (IOException e) {
            Log.e(TAG, "Could not close the connect socket", e);
        }
    }
}
