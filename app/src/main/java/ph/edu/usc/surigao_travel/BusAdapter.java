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

    public BusAdapter(Context context, List<Bus> busList) {
        super(context, 0, busList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.bus_item, parent, false);
        }

        Bus bus = getItem(position);
        if (bus == null) return convertView;

        // Bind UI elements
        TextView tvDeparture = convertView.findViewById(R.id.tvDeparture);
        TextView tvArrival = convertView.findViewById(R.id.tvArrival);
        TextView tvTravel = convertView.findViewById(R.id.tvTravel);
        TextView tvName = convertView.findViewById(R.id.tvName);
        TextView tvPrice = convertView.findViewById(R.id.tvPrice);
        RadioButton radioButton = convertView.findViewById(R.id.radioButton);
        Button btnBook = convertView.findViewById(R.id.btnBook);

        // Set values
        tvDeparture.setText(bus.getDeparture());
        tvArrival.setText(bus.getArrival());
        tvTravel.setText(bus.getTravel());
        tvName.setText(bus.getName());
        tvPrice.setText(bus.getPrice());

        // Show Book button only for selected item
        btnBook.setVisibility(position == selectedPosition ? View.VISIBLE : View.GONE);
        radioButton.setChecked(position == selectedPosition);

        // Handle radio button click
        radioButton.setOnClickListener(v -> {
            selectedPosition = position;
            notifyDataSetChanged(); // Refresh the list
        });

        // Handle Book button click
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