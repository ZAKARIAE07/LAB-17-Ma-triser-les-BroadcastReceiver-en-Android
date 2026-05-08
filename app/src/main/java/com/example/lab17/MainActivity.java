package com.example.lab17;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private AirplaneModeReceiver airplaneReceiver;
    private boolean isReceiverRegistered = false;
    private Button btnToggleAirplane, btnSendCustom;
    private TextView tvStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        airplaneReceiver = new AirplaneModeReceiver();
        tvStatus = findViewById(R.id.tvStatus);
        btnToggleAirplane = findViewById(R.id.btnToggleAirplane);
        btnSendCustom = findViewById(R.id.btnSendCustom);

        btnToggleAirplane.setOnClickListener(v -> toggleAirplaneReceiver());
        btnSendCustom.setOnClickListener(v -> sendCustomBroadcast());
    }

    private void toggleAirplaneReceiver() {
        if (!isReceiverRegistered) {
            // Création du filtre pour le receiver dynamique
            IntentFilter filter = new IntentFilter();
            filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
            
            // Enregistrement dynamique
            // Note: For Android 14+, exported or not must be specified for dynamic receivers
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                registerReceiver(airplaneReceiver, filter, Context.RECEIVER_NOT_EXPORTED);
            } else {
                registerReceiver(airplaneReceiver, filter);
            }
            
            isReceiverRegistered = true;
            tvStatus.setText("Receiver Mode Avion : ACTIVÉ (dynamique)");
            btnToggleAirplane.setText("Désactiver Receiver Avion");
        } else {
            unregisterReceiver(airplaneReceiver);
            isReceiverRegistered = false;
            tvStatus.setText("Receiver Mode Avion : DÉSACTIVÉ");
            btnToggleAirplane.setText("Activer Receiver Avion");
        }
    }

    private void sendCustomBroadcast() {
        Intent intent = new Intent("com.example.lab17.CUSTOM_EVENT");
        intent.putExtra("message", "Bonjour depuis le custom broadcast !");
        sendBroadcast(intent); // Broadcast implicite intra-app
        
        Toast.makeText(this, "Custom Broadcast envoyé !", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        if (isReceiverRegistered) {
            unregisterReceiver(airplaneReceiver);
        }
        super.onDestroy();
    }
}
