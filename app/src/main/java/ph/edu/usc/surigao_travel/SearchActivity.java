package ph.edu.usc.surigao_travel;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private EditText etFrom, etTo, txtDepartureDate, btnReturnDate;
    private Button btnSearch;
    private ListView listViewFlights;
    private FlightAdapter adapter;
    private List<Flight> flightList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        etFrom = findViewById(R.id.etFrom);
        etTo = findViewById(R.id.etTo);
        txtDepartureDate = findViewById(R.id.txtDepartureDate);
        btnReturnDate = findViewById(R.id.btnReturnDate);
        btnSearch = findViewById(R.id.btnSearch);
        listViewFlights = findViewById(R.id.listViewFlights);

        flightList = new ArrayList<>();
        adapter = new FlightAdapter(this, flightList);
        listViewFlights.setAdapter(adapter);

        btnSearch.setOnClickListener(v -> searchFlights());
    }

    private void searchFlights() {
        flightList.clear();

        String from = etFrom.getText().toString();
        String to = etTo.getText().toString();
        String departureDate = txtDepartureDate.getText().toString();
        String returnDate = btnReturnDate.getText().toString();

        flightList.add(new Flight("Airline A", "AA123", "08:00 AM", "12:00 PM", "4h", "Economy", "250"));
        flightList.add(new Flight("Airline B", "BB456", "10:00 AM", "02:00 PM", "4h", "Business", "450"));
        flightList.add(new Flight("Airline C", "CC789", "01:00 PM", "05:00 PM", "4h", "First Class", "800"));

        adapter.notifyDataSetChanged();
    }
}
