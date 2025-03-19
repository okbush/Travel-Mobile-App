package ph.edu.usc.surigao_travel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TripsActivity extends AppCompatActivity {
    private TextView tvBookingDetails;
    private Button btnModify, btnCancel;
    private String passengerName, name, departure, arrival, travel, price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trips);

        tvBookingDetails = findViewById(R.id.tvBookingDetails);
        btnModify = findViewById(R.id.btnModify);
        btnCancel = findViewById(R.id.btnCancel);

        // Get booking details
        Intent intent = getIntent();
        passengerName = intent.getStringExtra("passengerName");
        name = intent.getStringExtra("name");
        departure = intent.getStringExtra("departure");
        arrival = intent.getStringExtra("arrival");
        travel = intent.getStringExtra("travel");
        price = intent.getStringExtra("price");

        updateBookingDetails();

        btnModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyBooking();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TripsActivity.this, "Booking canceled", Toast.LENGTH_SHORT).show();
                finish(); // Close booking details
            }
        });
    }

    private void updateBookingDetails() {
        String details = "Passenger: " + passengerName + "\nBus: " + name +
                "\nDeparture: " + departure + "\nArrival: " + arrival +
                "\nDate: " + travel + "\nPrice: " + price;
        tvBookingDetails.setText(details);
    }

    private void modifyBooking() {
        Intent modifyIntent = new Intent(TripsActivity.this, BusDetailsActivity.class);
        modifyIntent.putExtra("passengerName", passengerName);
        modifyIntent.putExtra("name", name);
        modifyIntent.putExtra("departure", departure);
        modifyIntent.putExtra("arrival", arrival);
        modifyIntent.putExtra("travel", travel);
        modifyIntent.putExtra("price", price);
        startActivity(modifyIntent);
        finish();
    }
}
