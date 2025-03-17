package ph.edu.usc.surigao_travel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class FlightDetailsActivity extends AppCompatActivity {
    private EditText etPassengerName;
    private Spinner seatSelection, baggageSelection, mealSelection;
    private Button btnConfirmBooking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_details);

        // Get flight details from intent
        Intent intent = getIntent();
        String airline = intent.getStringExtra("airline");
        String from = intent.getStringExtra("from");
        String to = intent.getStringExtra("to");
        String departure = intent.getStringExtra("departure");
        String returnDate = intent.getStringExtra("return");
        String price = intent.getStringExtra("price");

        // Initialize UI elements
        etPassengerName = findViewById(R.id.etPassengerName);
        seatSelection = findViewById(R.id.spinnerSeat);
        baggageSelection = findViewById(R.id.spinnerBaggage);
        mealSelection = findViewById(R.id.spinnerMeal);
        btnConfirmBooking = findViewById(R.id.btnConfirmBooking);

        // Populate spinners
        ArrayAdapter<CharSequence> seatAdapter = ArrayAdapter.createFromResource(this,
                R.array.seat_options, android.R.layout.simple_spinner_item);
        seatAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        seatSelection.setAdapter(seatAdapter);

        ArrayAdapter<CharSequence> baggageAdapter = ArrayAdapter.createFromResource(this,
                R.array.baggage_options, android.R.layout.simple_spinner_item);
        baggageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        baggageSelection.setAdapter(baggageAdapter);

        ArrayAdapter<CharSequence> mealAdapter = ArrayAdapter.createFromResource(this,
                R.array.meal_options, android.R.layout.simple_spinner_item);
        mealAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mealSelection.setAdapter(mealAdapter);

        // Confirm Booking button action
        btnConfirmBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String passengerName = etPassengerName.getText().toString();
                String seat = seatSelection.getSelectedItem().toString();
                String baggage = baggageSelection.getSelectedItem().toString();
                String meal = mealSelection.getSelectedItem().toString();

                // Proceed with booking confirmation (e.g., show a confirmation screen or save booking)
                // Example: Show a confirmation message
            }
        });
    }
}
