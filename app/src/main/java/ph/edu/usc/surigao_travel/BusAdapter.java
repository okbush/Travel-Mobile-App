package ph.edu.usc.surigao_travel;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.List;

public class BusAdapter extends ArrayAdapter<Bus> {
    private int selectedPosition = -1; // Tracks which radio button is selected

    public BusAdapter(Context context, List<Bus> bus) {
        super(context, 0, bus);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.bus_item, parent, false);
        }

        Bus bus = getItem(position);

        // Bind UI elements
        TextView etDeparture = convertView.findViewById(R.id.etDeparture);
        TextView etArrival = convertView.findViewById(R.id.etArrival);
        TextView etTravel = convertView.findViewById(R.id.etTravel);
        TextView tvName = convertView.findViewById(R.id.tvName);
        TextView tvPrice = convertView.findViewById(R.id.tvPrice);
        RadioButton radioButton = convertView.findViewById(R.id.radioButton);
        Button btnBook = convertView.findViewById(R.id.btnBook); // The Book button

        // Set values
        etDeparture.setText(bus.getDeparture());
        etArrival.setText(bus.getArrival());
        etTravel.setText(bus.getTravel());
        tvName.setText(bus.getName());
        tvPrice.setText(bus.getPrice());

        // Show Book button only if this flight is selected
        btnBook.setVisibility(position == selectedPosition ? View.VISIBLE : View.GONE);

        // Handle radio button click
        radioButton.setOnClickListener(v -> {
            selectedPosition = position;
            notifyDataSetChanged(); // Refresh the list to show the Book button
        });

        // Handle Book button click (Navigate to FlightDetailsActivity)
        btnBook.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), BusDetailsActivity.class);
            intent.putExtra("name", bus.getName());
            intent.putExtra("departure", bus.getDeparture());
            intent.putExtra("arrival", bus.getArrival());
            intent.putExtra("travel", bus.getTravel());
            intent.putExtra("price", bus.getPrice());
            getContext().startActivity(intent);
        });

        return convertView;
    }
}
