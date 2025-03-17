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

public class BusActivity extends AppCompatActivity {
    private EditText etDeparture, etArrival, etTravel;
    private ListView listView;
    ImageButton btnBack;
    private BusAdapter busAdapter;
    private List<Bus> busList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus);

        btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(v -> {
            startActivity(new Intent(BusActivity.this, MainActivity.class));
            finish();
        });


        // Initialize UI elements
        etDeparture = findViewById(R.id.etDeparture);
        etArrival = findViewById(R.id.etArrival);
        etTravel = findViewById(R.id.etTravel);
        Button btnSearch = findViewById(R.id.btnSearch);
        listView = findViewById(R.id.listView);

        busList = new ArrayList<>();
        busAdapter = new BusAdapter(this, busList);
        listView.setAdapter(busAdapter);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchBus();
            }
        });
    }

    private void searchBus() {
        // Get user input
        String departure = etDeparture.getText().toString().trim();
        String arrival = etArrival.getText().toString().trim();
        String travel = etTravel.getText().toString().trim();

        // Clear previous results
        busList.clear();

        // Add new results (You can replace this with real search logic)
        if (!departure.isEmpty() && !arrival.isEmpty() && !travel.isEmpty()) {
            busList.add(new Bus("Bus A", departure, arrival, travel, "$20"));
            busList.add(new Bus("Bus B", departure, arrival, travel, "$25"));
            busList.add(new Bus("Bus C", departure, arrival, travel, "$30"));
        } else {
            // Show an empty list if the search is invalid
        }

        // Notify the adapter of data changes
        busAdapter.notifyDataSetChanged();
    }
}