package com.example.smscapture;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_RECEIVE_SMS = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // verificar si el el permiso si esta concedido
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.RECEIVE_SMS)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.RECEIVE_SMS},
                        MY_PERMISSIONS_REQUEST_RECEIVE_SMS);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_RECEIVE_SMS: {
                // si es mas grande que 0 y si es = a permiso concedido
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // ahora el broadcastreceiver
                    Toast.makeText(this, "Gracias por permitir el permiso", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(this, "No se puede hasta que lo permitas", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
