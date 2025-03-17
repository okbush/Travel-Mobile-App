package ph.edu.usc.surigao_travel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ImageButton btnFlights, btnBus, btnTrain, btnHotels, btnTrips, btnSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnFlights = findViewById(R.id.btnFlights);
        btnBus = findViewById(R.id.btnBus);
        btnTrain = findViewById(R.id.btnTrain);
        btnHotels = findViewById(R.id.btnHotels);
        btnTrips = findViewById(R.id.btnTrips);
        btnSettings = findViewById(R.id.btnSettings);

        View.OnClickListener goToSearch = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SearchActivity.class));
            }
        };

        View.OnClickListener goToHotel = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, HotelActivity.class));
            }
        };

        btnFlights.setOnClickListener(goToSearch);
        btnBus.setOnClickListener(goToSearch);
        btnTrain.setOnClickListener(goToSearch);
        btnHotels.setOnClickListener(goToHotel);
        btnTrips.setOnClickListener(goToSearch);
        btnSettings.setOnClickListener(goToSearch);
    }
}