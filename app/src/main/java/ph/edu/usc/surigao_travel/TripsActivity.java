package ph.edu.usc.surigao_travel;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class TripsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TripAdapter tripAdapter;
    private ArrayList<TripItem> bookingList;
    private SharedPreferences busSharedPreferences;
    private Button btnBackToMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trips);

        btnBackToMain = findViewById(R.id.btnBackToMain);
        recyclerView = findViewById(R.id.recyclerViewTrips);
        bookingList = new ArrayList<>();

        busSharedPreferences = getSharedPreferences("BusBookings", Context.MODE_PRIVATE);

        // 🔥 TEMP FIX: Clear corrupt SharedPreferences data (REMOVE AFTER TESTING)
        busSharedPreferences.edit().clear().apply();
        Log.d("TripsActivity", "Cleared BusBookings SharedPreferences");

        loadBusBookings(); // ✅ Load bus bookings safely

        // ✅ Initialize Adapter properly
        tripAdapter = new TripAdapter(this, bookingList, new TripAdapter.TripClickListener() {
            @Override
            public void onModify(int position) {
                modifyBooking(position);
            }

            @Override
            public void onCancel(int position) {
                cancelBooking(position);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(tripAdapter);

        btnBackToMain.setOnClickListener(v -> {
            startActivity(new Intent(TripsActivity.this, MainActivity.class));
            finish();
        });

        Intent intent = getIntent();
        if (intent.hasExtra("passengerName")) {
            addBooking(intent);
        }
    }

    private void loadBusBookings() {
        bookingList.clear();

        Set<String> savedBookings = busSharedPreferences.getStringSet("bookings", new HashSet<>());
        if (savedBookings == null || savedBookings.isEmpty()) {
            Toast.makeText(this, "No bus bookings found", Toast.LENGTH_SHORT).show();
            return;
        }

        for (String bookingString : savedBookings) {
            try {
                JSONObject obj = new JSONObject(bookingString);

                String passengerName = obj.optString("passengerName", "Unknown");
                String busName = obj.optString("busName", "Unknown");
                String departure = obj.optString("departure", "Unknown");
                String arrival = obj.optString("arrival", "Unknown");
                String travelDate = obj.optString("travelDate", "Unknown");
                String price = obj.optString("price", "Unknown");

                bookingList.add(new TripItem(passengerName, busName, departure, arrival, travelDate, price));

            } catch (JSONException e) {
                Log.e("TripsActivity", "Error parsing JSON booking", e);
            }
        }

        if (tripAdapter != null) {
            tripAdapter.notifyDataSetChanged();
        }
    }

    private void addBooking(Intent intent) {
        if (tripAdapter == null) return;

        TripItem booking = new TripItem(
                intent.getStringExtra("passengerName"),
                intent.getStringExtra("name"),
                intent.getStringExtra("departure"),
                intent.getStringExtra("arrival"),
                intent.getStringExtra("travel"),
                intent.getStringExtra("price")
        );

        bookingList.add(booking);
        saveBusBookings();

        if (tripAdapter != null) {
            tripAdapter.notifyDataSetChanged();
        }
    }

    private void saveBusBookings() {
        Set<String> savedBookings = new HashSet<>();

        for (TripItem booking : bookingList) {
            JSONObject obj = new JSONObject();
            try {
                obj.put("passengerName", booking.getPassengerName());
                obj.put("busName", booking.getBusOrAirline());
                obj.put("departure", booking.getDeparture());
                obj.put("arrival", booking.getArrival());
                obj.put("travelDate", booking.getTravelDate());
                obj.put("price", booking.getPrice());

                savedBookings.add(obj.toString());
            } catch (JSONException e) {
                Log.e("TripsActivity", "Error saving JSON booking", e);
            }
        }

        busSharedPreferences.edit().putStringSet("bookings", savedBookings).apply();
    }

    private void cancelBooking(int position) {
        bookingList.remove(position);
        saveBusBookings();
        tripAdapter.notifyDataSetChanged();
        Toast.makeText(this, "Booking canceled", Toast.LENGTH_SHORT).show();
    }

    private void modifyBooking(int position) {
        TripItem booking = bookingList.get(position);
        Intent modifyIntent = new Intent(TripsActivity.this, BusDetailsActivity.class);

        modifyIntent.putExtra("passengerName", booking.getPassengerName());
        modifyIntent.putExtra("name", booking.getBusOrAirline());
        modifyIntent.putExtra("departure", booking.getDeparture());
        modifyIntent.putExtra("arrival", booking.getArrival());
        modifyIntent.putExtra("travel", booking.getTravelDate());
        modifyIntent.putExtra("price", booking.getPrice());

        bookingList.remove(position);
        saveBusBookings();
        tripAdapter.notifyDataSetChanged();

        startActivity(modifyIntent);
    }
}
