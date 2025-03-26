package ph.edu.usc.surigao_travel;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashSet;
import java.util.Set;

public class BusDetailsActivity extends AppCompatActivity {
    private EditText etPassengerName;
    private Button btnConfirmBooking;
    private String name, departure, arrival, travel, price, passengerName;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_details);

        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        departure = intent.getStringExtra("departure");
        arrival = intent.getStringExtra("arrival");
        travel = intent.getStringExtra("travel");
        price = intent.getStringExtra("price");
        passengerName = intent.getStringExtra("passengerName");

        etPassengerName = findViewById(R.id.etPassengerName);
        btnConfirmBooking = findViewById(R.id.btnConfirmBooking);
        sharedPreferences = getSharedPreferences("BusBookings", Context.MODE_PRIVATE);

        if (passengerName != null) {
            etPassengerName.setText(passengerName);
        }

        btnConfirmBooking.setOnClickListener(v -> saveBooking());
    }

    private void saveBooking() {
        String newPassengerName = etPassengerName.getText().toString().trim();
        if (newPassengerName.isEmpty()) {
            Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show();
            return;
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        Set<String> bookings = sharedPreferences.getStringSet("bookings", new HashSet<>());

        JSONObject booking = new JSONObject();
        try {
            booking.put("passengerName", newPassengerName);
            booking.put("busName", name);
            booking.put("departure", departure);
            booking.put("arrival", arrival);
            booking.put("travelDate", travel);
            booking.put("price", price);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        bookings.add(booking.toString());
        editor.putStringSet("bookings", bookings);
        editor.apply();

        Toast.makeText(this, "Booking Confirmed!", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(BusDetailsActivity.this, TripsActivity.class));
        finish();
    }
}
