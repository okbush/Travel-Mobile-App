package ph.edu.usc.surigao_travel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
    private FlightAdapter flightAdapter;
    private List<Flight> flightList;
    private ImageButton btnBack;

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

        // Set up back button
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchActivity.this, MainActivity.class));
            }
        });

        // Initialize flight list and adapter
        flightList = new ArrayList<>();
        flightAdapter = new FlightAdapter(this, flightList);
        listView.setAdapter(flightAdapter);

        // Search button click event
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchFlights();
            }
        });

        // Handle flight selection
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Flight selectedFlight = flightList.get(position);

                Intent intent = new Intent(SearchActivity.this, FlightDetailsActivity.class);
                intent.putExtra("airline", selectedFlight.getAirline());
                intent.putExtra("from", selectedFlight.getFrom());
                intent.putExtra("to", selectedFlight.getTo());
                intent.putExtra("departure", selectedFlight.getDepartureTime());
                intent.putExtra("return", selectedFlight.getReturnTime());
                intent.putExtra("price", selectedFlight.getPrice());
                startActivity(intent);
            }
        });
    }

    private void searchFlights() {
        String from = etFrom.getText().toString().trim();
        String to = etTo.getText().toString().trim();
        String departure = etDeparture.getText().toString().trim();
        String returnDate = etReturn.getText().toString().trim();

        // Clear old search results
        flightList.clear();

        // Add some sample flight results
        flightList.add(new Flight("Airline A", from, to, departure, returnDate, "$250"));
        flightList.add(new Flight("Airline B", from, to, departure, returnDate, "$220"));
        flightList.add(new Flight("Airline C", from, to, departure, returnDate, "$275"));

        // Notify adapter of data changes
        flightAdapter.notifyDataSetChanged();
    }
}
