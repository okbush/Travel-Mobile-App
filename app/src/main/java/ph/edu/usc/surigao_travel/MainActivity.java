package ph.edu.usc.surigao_travel;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etSearch;
    private ImageButton btnFlights, btnBus, btnTrain, btnHotels, btnTrips, btnSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etSearch = findViewById(R.id.etSearch);
        btnFlights = findViewById(R.id.btnFlights);
        btnBus = findViewById(R.id.btnBus);
        btnTrain = findViewById(R.id.btnTrain);
        btnHotels = findViewById(R.id.btnHotels);
        btnTrips = findViewById(R.id.btnTrips);
        btnSettings = findViewById(R.id.btnSettings);

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                filterButtons(charSequence.toString().trim());
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        btnFlights.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, SearchActivity.class)));
        btnBus.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, BusActivity.class)));
        btnTrain.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, TrainActivity.class)));
        btnHotels.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, HotelActivity.class)));
        btnTrips.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, TripsActivity.class)));
        btnSettings.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, SettingsActivity.class)));
    }

    private void filterButtons(String query) {
        int visibility = query.isEmpty() ? View.VISIBLE : View.GONE;

        btnFlights.setVisibility(query.contains("flights") ? View.VISIBLE : visibility);
        btnBus.setVisibility(query.contains("bus") ? View.VISIBLE : visibility);
        btnTrain.setVisibility(query.contains("train") ? View.VISIBLE : visibility);
        btnHotels.setVisibility(query.contains("hotel") ? View.VISIBLE : visibility);
        btnTrips.setVisibility(query.contains("trips") ? View.VISIBLE : visibility);
        btnSettings.setVisibility(query.contains("settings") ? View.VISIBLE : visibility);
    }
}
