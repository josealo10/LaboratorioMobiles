package com.example.bluetoohclient;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    public AcceptThread acceptThread;

    private static final UUID BT_MODULE_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"); // "random" unique identifier
    private final String TAG = MainActivity.class.getSimpleName();

    TextView textView;

    private Handler mHandler; // Our main handler that will receive callback notifications
    private ConnectedThread mConnectedThread; // bluetooth background worker thread to send and receive data
    private BluetoothSocket mBTSocket = null; // bi-directional client-to-client data path


    private interface MessageConstants {
        public static final int MESSAGE_READ = 0;
        public static final int MESSAGE_WRITE = 1;
        public static final int MESSAGE_TOAST = 2;
        public static final int CONNECTING_STATUS = 3;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);

        mHandler = new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(Message msg){
                if(msg.what == MessageConstants.MESSAGE_READ){
                    String readMessage = null;
                    try {
                        readMessage = new String((byte[]) msg.obj, "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    //mReadBuffer.setText(readMessage);
                }

                if(msg.what == MessageConstants.CONNECTING_STATUS){
                    if(msg.arg1 == 1) {
                        Toast.makeText(getApplicationContext(),"Connected to Device: " + msg.obj,Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Connection Failed",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        };
        // Spawn a new thread to avoid blocking the GUI one
        final MainActivity main = this;
        new Thread()
        {
            @Override
            public void run() {
                boolean fail = false;

                acceptThread = new AcceptThread(main);
                // Establish the Bluetooth socket connection.
                acceptThread.run();

                    //mConnectedThread.write("luz=" + MainActivity.LuzStatic+";ubicacion="+MainActivity.UbicacionStatic);

            }
        }.start();
        //acceptThread = new AcceptThread();
    }

    public void createConnectedThread(){
        mConnectedThread = new ConnectedThread(mBTSocket, mHandler);
        mConnectedThread.start();

        mHandler.obtainMessage(MessageConstants.CONNECTING_STATUS, 1, -1)
                .sendToTarget();
    }

}