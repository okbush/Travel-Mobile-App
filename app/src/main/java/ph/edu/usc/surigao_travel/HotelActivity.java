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

import ph.edu.usc.surigao_travel.HotelAdapter;

public class HotelActivty extends AppCompatActivity {
    private EditText etCity, etGuests, etCheckIn, etCheckOut;
    private ListView listView;
    ImageButton btnBack;
    private HotelAdapter hotelAdapter;
    private List<Hotel> hotelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel);

        btnBack = findViewById(R.id.btnBack);

        View.OnClickListener goBack = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent( HotelActivty.this, MainActivity.class));
            }
        };

        btnBack.setOnClickListener(goBack);

        // Initialize UI elements
        etCity = findViewById(R.id.etCity);
        etGuests = findViewById(R.id.etGuests);
        etCheckIn = findViewById(R.id.etCheckIn);
        etCheckOut = findViewById(R.id.etCheckOut);
        Button btnSearch = findViewById(R.id.btnSearch);
        listView = findViewById(R.id.listView);

        hotelList = new ArrayList<>();
        hotelAdapter = new HotelAdapter(this, hotelList);
        listView.setAdapter(HotelAdapter);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchHotels();
            }
        });
    }

    private void searchHotels() {
        // Get user input
        String from = etCity.getText().toString();
        String to = etGuests.getText().toString();
        String departure = etCheckIn.getText().toString();
        String returnDate = etCheckOut.getText().toString();

        // Clear previous results
        hotelList.clear();

        // Sample data for demonstration (different airlines, same user input)
        hotelList.add(new Flight("Airline A", from, to, departure, returnDate, "$250"));
        hotelList.add(new Flight("Airline B", from, to, departure, returnDate, "$220"));
        hotelList.add(new Flight("Airline C", from, to, departure, returnDate, "$275"));

        // Notify adapter about data changes
        hotelAdapter.notifyDataSetChanged();
    }
}