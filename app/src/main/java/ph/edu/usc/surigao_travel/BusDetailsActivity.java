package ph.edu.usc.surigao_travel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BusDetailsActivity extends AppCompatActivity {
    private EditText etPassengerName;
    private Button btnConfirmBooking;
    private String name, departure, arrival, travel, price, passengerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_details);

        // Get bus details from intent
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        departure = intent.getStringExtra("departure");
        arrival = intent.getStringExtra("arrival");
        travel = intent.getStringExtra("travel");
        price = intent.getStringExtra("price");
        passengerName = intent.getStringExtra("passengerName");

        etPassengerName = findViewById(R.id.etPassengerName);
        btnConfirmBooking = findViewById(R.id.btnConfirmBooking);

        // Pre-fill name if modifying
        if (passengerName != null) {
            etPassengerName.setText(passengerName);
        }

        btnConfirmBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newPassengerName = etPassengerName.getText().toString().trim();
                if (newPassengerName.isEmpty()) {
                    Toast.makeText(BusDetailsActivity.this, "Please enter your name", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Pass updated data to BookingDetailsActivity
                Intent bookingIntent = new Intent(BusDetailsActivity.this, TripsActivity.class);
                bookingIntent.putExtra("passengerName", newPassengerName);
                bookingIntent.putExtra("name", name);
                bookingIntent.putExtra("departure", departure);
                bookingIntent.putExtra("arrival", arrival);
                bookingIntent.putExtra("travel", travel);
                bookingIntent.putExtra("price", price);
                startActivity(bookingIntent);
                finish();
            }
        });
    }
}
