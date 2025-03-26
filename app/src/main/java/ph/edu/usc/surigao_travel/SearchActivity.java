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

public class SearchActivity extends AppCompatActivity {
    private EditText etFrom, etTo, etDeparture, etReturn;
    private ListView listView;
    private FlightAdapter flightAdapter;
    private List<Flight> allFlights, filteredFlights;
    private ImageButton btnBack;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // Initialize UI components
        btnBack = findViewById(R.id.btnBack);
        etFrom = findViewById(R.id.etFrom);
        etTo = findViewById(R.id.etTo);
        etDeparture = findViewById(R.id.etDeparture);
        etReturn = findViewById(R.id.etReturn);
        Button btnSearch = findViewById(R.id.btnSearch);
        listView = findViewById(R.id.listView);

        // Back button event
        btnBack.setOnClickListener(v -> {
            startActivity(new Intent(SearchActivity.this, MainActivity.class));
            finish();
        });

        // Initialize flight list and adapter
        allFlights = new ArrayList<>();
        filteredFlights = new ArrayList<>();
        flightAdapter = new FlightAdapter(this, filteredFlights);
        listView.setAdapter(flightAdapter);

        sharedPreferences = getSharedPreferences("FlightSearch", Context.MODE_PRIVATE);
        loadFlightData(); // Load flight data but don't display it yet.

        // Search button event
        btnSearch.setOnClickListener(v -> searchFlights());
    }

    private void loadFlightData() {
        try {
            InputStream is = getAssets().open("flights.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String json = new String(buffer, "UTF-8");

            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                allFlights.add(new Flight(
                        obj.getString("airline"),
                        obj.getString("from"),
                        obj.getString("to"),
                        obj.getString("departureTime"),
                        obj.getString("returnTime"),
                        obj.getString("price")
                ));
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    private void searchFlights() {
        String from = etFrom.getText().toString().trim();
        String to = etTo.getText().toString().trim();
        String departure = etDeparture.getText().toString().trim();
        String returnDate = etReturn.getText().toString().trim();

        if (from.isEmpty() || to.isEmpty() || departure.isEmpty()) {
            Toast.makeText(this, "Please enter all required details", Toast.LENGTH_SHORT).show();
            return;
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("from", from);
        editor.putString("to", to);
        editor.putString("departure", departure);
        editor.putString("return", returnDate);
        editor.apply();

        filteredFlights.clear();

        for (Flight flight : allFlights) {
            if (flight.getFrom().equalsIgnoreCase(from) &&
                    flight.getTo().equalsIgnoreCase(to) &&
                    flight.getDepartureTime().equalsIgnoreCase(departure) &&
                    (returnDate.isEmpty() || flight.getReturnTime().equalsIgnoreCase(returnDate))) {
                filteredFlights.add(flight);
            }
        }

        if (filteredFlights.isEmpty()) {
            Toast.makeText(this, "No flights found for the selected route and date", Toast.LENGTH_SHORT).show();
        }

        flightAdapter.notifyDataSetChanged();
    }
}