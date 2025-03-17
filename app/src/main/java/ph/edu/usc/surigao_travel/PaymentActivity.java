package ph.edu.usc.surigao_travel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class PaymentActivity extends AppCompatActivity {

    private Button btnConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        btnConfirm = findViewById(R.id.btnConfirm);
        btnConfirm.setOnClickListener(v -> startActivity(new Intent(PaymentActivity.this, TripsActivity.class)));
    }
}