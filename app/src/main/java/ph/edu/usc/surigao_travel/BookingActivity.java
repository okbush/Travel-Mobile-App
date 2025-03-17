package ph.edu.usc.surigao_travel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class BookingActivity extends AppCompatActivity {

    Button btnProceed, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        btnProceed = findViewById(R.id.btnProceed);
        btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(v -> startActivity(new Intent(BookingActivity.this, PaymentActivity.class)));
        btnProceed.setOnClickListener(v -> startActivity(new Intent(BookingActivity.this, ResultsActivity.class)));
    }
}