package ph.edu.usc.surigao_travel;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class TripsActivity extends AppCompatActivity {

    Button btnBackHome, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trips);

        btnBackHome = findViewById(R.id.btnBackHome);
        btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(v -> startActivity(new Intent(TripsActivity.this, PaymentActivity.class)));
        btnBackHome.setOnClickListener(v -> startActivity(new Intent(TripsActivity.this, MainActivity.class)));

    }





}