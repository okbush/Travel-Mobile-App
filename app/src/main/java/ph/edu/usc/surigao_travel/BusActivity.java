package ph.edu.usc.surigao_travel;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class BusActivity extends AppCompatActivity {
    private EditText etDeparture, etArrival, etTravel;
    private ListView listView;
    private ImageButton btnBack;
    private BusAdapter busAdapter;
    private List<Bus> allBuses, filteredBuses;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus);

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> {
            startActivity(new Intent(BusActivity.this, MainActivity.class));
            finish();
        });

        etDeparture = findViewById(R.id.etDeparture);
        etArrival = findViewById(R.id.etArrival);
        etTravel = findViewById(R.id.etTravel);
        Button btnSearch = findViewById(R.id.btnSearch);
        listView = findViewById(R.id.listView);

        allBuses = new ArrayList<>();
        filteredBuses = new ArrayList<>();
        busAdapter = new BusAdapter(this, filteredBuses);
        listView.setAdapter(busAdapter);

        sharedPreferences = getSharedPreferences("BusSearch", Context.MODE_PRIVATE);
        loadBusData(); // Load all buses but don't display them yet.

        btnSearch.setOnClickListener(v -> searchBus());
    }

    private void loadBusData() {
        try {
            InputStream is = getAssets().open("buses.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String json = new String(buffer, "UTF-8");

            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                allBuses.add(new Bus(
                        obj.getString("name"),
                        obj.getString("departure"),
                        obj.getString("arrival"),
                        obj.getString("travel"),
                        obj.getString("price")
                ));
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    private void searchBus() {
        String departure = etDeparture.getText().toString().trim();
        String arrival = etArrival.getText().toString().trim();
        String travel = etTravel.getText().toString().trim();

        if (departure.isEmpty() || arrival.isEmpty() || travel.isEmpty()) {
            Toast.makeText(this, "Please enter all search details", Toast.LENGTH_SHORT).show();
            return;
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("departure", departure);
        editor.putString("arrival", arrival);
        editor.putString("travel", travel);
        editor.apply();

        filteredBuses.clear();

        for (Bus bus : allBuses) {
            if (bus.getDeparture().equalsIgnoreCase(departure) &&
                    bus.getArrival().equalsIgnoreCase(arrival) &&
                    bus.getTravel().equalsIgnoreCase(travel)) {
                filteredBuses.add(bus);
            }
        }

        if (filteredBuses.isEmpty()) {
            Toast.makeText(this, "No buses found for the selected route and date", Toast.LENGTH_SHORT).show();
        }

        busAdapter.notifyDataSetChanged();
    }
}
