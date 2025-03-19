package ph.edu.usc.surigao_travel;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class TripsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TripAdapter tripAdapter;
    private ArrayList<BusBooking> bookingList;
    private SharedPreferences sharedPreferences;
    private Button btnBackToMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trips);

        btnBackToMain = findViewById(R.id.btnBackToMain); // ✅ Ensure this is initialized
        recyclerView = findViewById(R.id.recyclerViewTrips);
        bookingList = new ArrayList<>();
        sharedPreferences = getSharedPreferences("Bookings", Context.MODE_PRIVATE);

        loadBookings();

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

    private void loadBookings() {
        Set<String> savedBookings = sharedPreferences.getStringSet("bookings", new HashSet<>());
        bookingList.clear();

        for (String bookingString : savedBookings) {
            String[] parts = bookingString.split(";");
            if (parts.length == 6) {
                BusBooking booking = new BusBooking(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]);
                bookingList.add(booking);
            }
        }

        if (tripAdapter != null) {
            tripAdapter.notifyDataSetChanged();
        }
    }

    private void addBooking(Intent intent) {
        if (tripAdapter == null) return; // ✅ Prevent crashes

        BusBooking booking = new BusBooking(
                intent.getStringExtra("passengerName"),
                intent.getStringExtra("name"),
                intent.getStringExtra("departure"),
                intent.getStringExtra("arrival"),
                intent.getStringExtra("travel"),
                intent.getStringExtra("price")
        );

        bookingList.add(booking);
        saveBookings();

        if (tripAdapter != null) {
            tripAdapter.notifyDataSetChanged();
        }
    }

    private void modifyBooking(int position) {
        BusBooking booking = bookingList.get(position);
        Intent modifyIntent = new Intent(TripsActivity.this, BusDetailsActivity.class);
        modifyIntent.putExtra("passengerName", booking.getPassengerName());
        modifyIntent.putExtra("name", booking.getBusName());
        modifyIntent.putExtra("departure", booking.getDeparture());
        modifyIntent.putExtra("arrival", booking.getArrival());
        modifyIntent.putExtra("travel", booking.getTravelDate());
        modifyIntent.putExtra("price", booking.getPrice());

        bookingList.remove(position);
        saveBookings();
        tripAdapter.notifyDataSetChanged();

        startActivity(modifyIntent);
    }

    private void cancelBooking(int position) {
        bookingList.remove(position);
        saveBookings();
        tripAdapter.notifyDataSetChanged();
        Toast.makeText(this, "Booking canceled", Toast.LENGTH_SHORT).show();
    }

    private void saveBookings() {
        Set<String> savedBookings = new HashSet<>();
        for (BusBooking booking : bookingList) {
            savedBookings.add(booking.getPassengerName() + ";" + booking.getBusName() + ";" +
                    booking.getDeparture() + ";" + booking.getArrival() + ";" +
                    booking.getTravelDate() + ";" + booking.getPrice());
        }
        sharedPreferences.edit().putStringSet("bookings", savedBookings).apply();
    }
}
