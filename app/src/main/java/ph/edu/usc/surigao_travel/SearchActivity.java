package ph.edu.usc.surigao_travel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class SearchActivity extends AppCompatActivity {

    private Button btnSearch, btnBookFlight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        btnSearch = findViewById(R.id.btnSearch);
        btnBookFlight = findViewById(R.id.btnBookFlight);

        btnSearch.setOnClickListener(v ->
                startActivity(new Intent(SearchActivity.this, ResultsActivity.class))
        );

        btnBookFlight.setOnClickListener(v ->
                startActivity(new Intent(SearchActivity.this, BookingActivity.class))
        );
    }
}