package ph.edu.usc.surigao_travel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    private EditText etFrom, etTo, etDeparture, etReturn;
    private ListView listView;
    ImageButton btnBack;
    private FlightAdapter flightAdapter;
    private List<Flight> flightList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        btnBack = findViewById(R.id.btnBack);

        View.OnClickListener goBack = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchActivity.this, MainActivity.class));
            }
        };

        btnBack.setOnClickListener(goBack);

        // Initialize UI elements
        etFrom = findViewById(R.id.etFrom);
        etTo = findViewById(R.id.etTo);
        etDeparture = findViewById(R.id.etDeparture);
        etReturn = findViewById(R.id.etReturn);
        Button btnSearch = findViewById(R.id.btnSearch);
        listView = findViewById(R.id.listView);

        flightList = new ArrayList<>();
        flightAdapter = new FlightAdapter(this, flightList);
        listView.setAdapter(flightAdapter);

        // Search Button Click Listener
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchFlights();
            }
        });
    }

    private void searchFlights() {
        // Get user input
        String from = etFrom.getText().toString();
        String to = etTo.getText().toString();
        String departure = etDeparture.getText().toString();
        String returnDate = etReturn.getText().toString();

        // Clear previous results
        flightList.clear();

        // Sample data for demonstration (different airlines, same user input)
        flightList.add(new Flight("Airline A", from, to, departure, returnDate, "$250"));
        flightList.add(new Flight("Airline B", from, to, departure, returnDate, "$220"));
        flightList.add(new Flight("Airline C", from, to, departure, returnDate, "$275"));

        // Notify adapter about data changes
        flightAdapter.notifyDataSetChanged();
    }
}