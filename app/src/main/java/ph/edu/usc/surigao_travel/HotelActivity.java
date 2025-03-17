package ph.edu.usc.surigao_travel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class HotelActivity extends AppCompatActivity {
    private EditText etFrom, etGuests, etCheckIn, etCheckOut;
    private ListView listView;
    ImageButton btnBack;
    private HotelAdapter hotelAdapter;
    private List<Hotel> hotelList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel);

        btnBack = findViewById(R.id.btnBack);

//        View.OnClickListener goBack = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent( HotelActivity.this, MainActivity.class));
//            }
//        };
//        btnBack.setOnClickListener(goBack);

        btnBack.setOnClickListener(v -> {
            startActivity(new Intent(HotelActivity.this, MainActivity.class));
            finish();
        });


        // Initialize UI elements
        etFrom = findViewById(R.id.etFrom);
        etGuests = findViewById(R.id.etGuests);
        etCheckIn = findViewById(R.id.etCheckIn);
        etCheckOut = findViewById(R.id.etCheckOut);
        Button btnSearch = findViewById(R.id.btnSearch);
        listView = findViewById(R.id.listView);

        hotelList = new ArrayList<>();
        hotelAdapter = new HotelAdapter(this, hotelList);
        listView.setAdapter(hotelAdapter);



        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchHotels();
            }
        });
    }

    private void searchHotels() {
        // Get user input
        String from = etFrom.getText().toString().trim();
        String guests = etGuests.getText().toString().trim();
        String checkin = etCheckIn.getText().toString().trim();
        String checkout = etCheckOut.getText().toString().trim();

        // Clear previous results
        hotelList.clear();

        // Add new results (You can replace this with real search logic)
        if (!from.isEmpty() && !guests.isEmpty() && !checkin.isEmpty() && !checkout.isEmpty()) {
            hotelList.add(new Hotel("Hotel A", from, guests, checkin, checkout, "$250"));
            hotelList.add(new Hotel("Hotel B", from, guests, checkin, checkout, "$220"));
            hotelList.add(new Hotel("Hotel C", from, guests, checkin, checkout, "$275"));
        } else {
            // Show an empty list if the search is invalid
        }

        // Notify the adapter of data changes
        hotelAdapter.notifyDataSetChanged();
    }
}