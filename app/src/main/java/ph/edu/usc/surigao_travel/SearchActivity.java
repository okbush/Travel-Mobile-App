package ph.edu.usc.surigao_travel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private EditText etFrom, etTo, txtDepartureDate, txtReturnDate;
    private Button btnSearch;
    private ImageButton btnBack;
    private ListView listViewFlights;
    private FlightAdapter adapter;
    private List<Flight> flightList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        btnBack = findViewById(R.id.btnBack);
        etFrom = findViewById(R.id.etFrom);
        etTo = findViewById(R.id.etTo);
        txtDepartureDate = findViewById(R.id.txtDepartureDate);
        txtReturnDate = findViewById(R.id.txtReturnDate);
        btnSearch = findViewById(R.id.btnSearch);
        listViewFlights = findViewById(R.id.listViewFlights);

        flightList = new ArrayList<>();
        adapter = new FlightAdapter(this, flightList);
        listViewFlights.setAdapter(adapter);

        btnSearch.setOnClickListener(v -> searchFlights());

        btnBack.setOnClickListener(v -> startActivity(new Intent(SearchActivity.this, MainActivity.class)));
    }

    private void searchFlights() {
        flightList.clear();

        String from = etFrom.getText().toString().trim();
        String to = etTo.getText().toString().trim();
        String departureDate = txtDepartureDate.getText().toString().trim();
        String returnDate = txtReturnDate.getText().toString().trim();

        // Validate input
        if (from.isEmpty() || to.isEmpty()) {
            Toast.makeText(this, "Please enter both FROM and TO destinations", Toast.LENGTH_SHORT).show();
            return;
        }

        // Sample flight data based on the input
        // In a real application, you would fetch this data from a server or database
        flightList.add(new Flight("Airline A", "AA123", "08:00 AM", "12:00 PM", "4h", "Economy", "250"));
        flightList.add(new Flight("Airline B", "BB456", "10:00 AM", "02:00 PM", "4h", "Business", "450"));
        flightList.add(new Flight("Airline C", "CC789", "01:00 PM", "05:00 PM", "4h", "First Class", "800"));
        flightList.add(new Flight("Airline D", "DD101", "03:00 PM", "07:00 PM", "4h", "Economy", "300"));
        flightList.add(new Flight("Airline E", "EE202", "05:00 PM", "09:00 PM", "4h", "Business", "500"));

        // Notify the adapter that the data has changed
        adapter.notifyDataSetChanged();
    }
}