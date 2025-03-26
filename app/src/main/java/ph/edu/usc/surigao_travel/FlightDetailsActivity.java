package ph.edu.usc.surigao_travel;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashSet;
import java.util.Set;

public class FlightDetailsActivity extends AppCompatActivity {
    private TextView tvAirline, tvFrom, tvTo, tvDeparture, tvReturn, tvPrice;
    private EditText etPassengerName;
    private Button btnConfirmBooking;
    private SharedPreferences sharedPreferences;
    private String airline, from, to, departure, returnDate, price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_details);

        // Initialize UI components
        tvAirline = findViewById(R.id.tvAirline);
        tvFrom = findViewById(R.id.tvFrom);
        tvTo = findViewById(R.id.tvTo);
        tvDeparture = findViewById(R.id.tvDeparture);
        tvReturn = findViewById(R.id.tvReturn);
        tvPrice = findViewById(R.id.tvPrice);
        etPassengerName = findViewById(R.id.etPassengerName);
        btnConfirmBooking = findViewById(R.id.btnConfirmBooking);

        sharedPreferences = getSharedPreferences("FlightBookings", Context.MODE_PRIVATE);

        // Retrieve flight details from intent
        Intent intent = getIntent();
        airline = intent.getStringExtra("airline");
        from = intent.getStringExtra("from");
        to = intent.getStringExtra("to");
        departure = intent.getStringExtra("departure");
        returnDate = intent.getStringExtra("return");
        price = intent.getStringExtra("price");

        // Display flight details
        tvAirline.setText("Airline: " + airline);
        tvFrom.setText("From: " + from);
        tvTo.setText("To: " + to);
        tvDeparture.setText("Departure: " + departure);
        tvReturn.setText("Return: " + (returnDate.isEmpty() ? "One-way" : returnDate));
        tvPrice.setText("Price: " + price);

        // Confirm booking button event
        btnConfirmBooking.setOnClickListener(v -> saveBooking());
    }

    private void saveBooking() {
        String passengerName = etPassengerName.getText().toString().trim();
        if (passengerName.isEmpty()) {
            Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show();
            return;
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        Set<String> bookings = sharedPreferences.getStringSet("bookings", new HashSet<>());

        JSONObject booking = new JSONObject();
        try {
            booking.put("passengerName", passengerName);
            booking.put("airline", airline);
            booking.put("from", from);
            booking.put("to", to);
            booking.put("departure", departure);
            booking.put("return", returnDate);
            booking.put("price", price);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        bookings.add(booking.toString());
        editor.putStringSet("bookings", bookings);
        editor.apply();

        Toast.makeText(this, "Booking Confirmed!", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(FlightDetailsActivity.this, TripsActivity.class));
        finish();
    }
}
