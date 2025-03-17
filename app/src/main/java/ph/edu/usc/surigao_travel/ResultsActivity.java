package ph.edu.usc.surigao_travel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class ResultsActivity extends AppCompatActivity {

    private Button btnSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        btnSelect = findViewById(R.id.btnSelect);
        btnSelect.setOnClickListener(v -> startActivity(new Intent(ResultsActivity.this, BookingActivity.class)));
    }
}