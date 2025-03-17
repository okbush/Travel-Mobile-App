package ph.edu.usc.surigao_travel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class TrainDetailsActivity extends AppCompatActivity {
    private EditText etPassengerName;
    private Button btnConfirmBooking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_details);

        Intent intent = getIntent();
        String name = intent.getStringExtra("bus");
        String departure = intent.getStringExtra("departure");
        String arrival = intent.getStringExtra("arrival");
        String travel = intent.getStringExtra("travel");
        String price = intent.getStringExtra("price");

        etPassengerName = findViewById(R.id.etPassengerName);
        btnConfirmBooking = findViewById(R.id.btnConfirmBooking);

        btnConfirmBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String passengerName = etPassengerName.getText().toString();
            }
        });
    }
}
