package com.example.bluetoohclient;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.Html;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bluetoohclient.databinding.ActivityMainBinding;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.UUID;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private final String TAG = MainActivity.class.getSimpleName();
    private Handler mHandler; // Our main handler that will receive callback notifications
    private ConnectedThread mConnectedThread; // bluetooth background worker thread to send and receive data
    private BluetoothSocket mBTSocket = null; // bi-directional client-to-client data path
    private BluetoothServerSocket mServerSocket;
    // #defines for identifying shared types between calling functions
    private final static int REQUEST_ENABLE_BT = 1; // used to identify adding bluetooth names
    public final static int MESSAGE_READ = 2; // used in bluetooth handler to identify message update
    private final static int CONNECTING_STATUS = 3; // used in bluetooth handler to identify message status

    BluetoothAdapter bluetoothAdapter;

    private interface MessageConstants {
        public static final int MESSAGE_READ = 0;
        public static final int MESSAGE_WRITE = 1;
        public static final int MESSAGE_TOAST = 2;
        public static final int CONNECTING_STATUS = 3;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //textView = findViewById(R.id.textView);
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (!bluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }

        Intent discoverableIntent = new
                Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 5);
        startActivity(discoverableIntent);

        mHandler = new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(Message msg){
                if(msg.what == MessageConstants.MESSAGE_READ){
                    String readMessage = null;

                    //readMessage = ;
                    try {
                        setValores(new String((byte[]) msg.obj, "UTF-8"));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
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

        AcceptThread();
        new Thread()
        {
            @Override
            public void run() {
                runClient();
            }
        }.start();
    }


    public void AcceptThread() {
        BluetoothServerSocket tmp = null;
        try {
            UUID YOUR_UUID = UUID.fromString("b166149a-1b1b-4b9f-a53f-fa11c915aea0");
            tmp = bluetoothAdapter.listenUsingRfcommWithServiceRecord("MYYAPP", YOUR_UUID);

        } catch (IOException e) { }
        mServerSocket = tmp;
    }

    public void runClient() {
        BluetoothSocket socket = null;
        while (true) {
            try {
                socket = mServerSocket.accept();

            } catch (IOException e) {
                break;
            }
            if (socket != null) {
                //changeT("doneeeee");
                try {
                    mServerSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mConnectedThread = new ConnectedThread(socket,mHandler);
                runConnectedThread();
                break;
            }
        }
    }


    public void setValores(String str) {
        String luz = str.substring(0, str.indexOf(';'));
        String ubicacion = str.substring(str.indexOf(";") + 1);

        binding.tvUbicacion.setText(Html.fromHtml(getString(R.string.tv_ubicacion, ubicacion)));
        binding.tvNivelLuz.setText(Html.fromHtml(getString(R.string.tv_nivel_luz, luz)));
    }

    public void setImagen(byte[] imagen) {
        //binding.ivPhoto.setImageBitmap(BitmapFactory.decodeByteArray(imagen, 0, imagen.length));
    }

    public void runConnectedThread(){
        new Thread()
        {
            @Override
            public void run() {
                mConnectedThread.run();
            }
        }.start();
    }
}