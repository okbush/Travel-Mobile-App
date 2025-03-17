package ph.edu.usc.surigao_travel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.List;

public class FlightAdapter extends ArrayAdapter<Flight> {
    public FlightAdapter(Context context, List<Flight> flights) {
        super(context, 0, flights);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.flight_item, parent, false);
        }

        Flight flight = getItem(position);

        TextView etFrom = convertView.findViewById(R.id.etFrom);
        TextView etTo = convertView.findViewById(R.id.etTo);
        TextView txtDepartureDate = convertView.findViewById(R.id.txtDepartureDate);
        TextView txtReturnDate = convertView.findViewById(R.id.txtReturnDate);
        TextView tvAirline = convertView.findViewById(R.id.tvAirline);
        TextView tvPrice = convertView.findViewById(R.id.tvPrice); // You may need to add this ID in your layout
        RadioButton radioButton = convertView.findViewById(R.id.radioButton);

        // Set the values for the TextViews
        etFrom.setText(flight.getAirline()); // Assuming airline is the departure location
        etTo.setText("Destination"); // You may want to set this based on your Flight object
        txtDepartureDate.setText(flight.getDepartureTime()); // Assuming this is the departure time
        txtReturnDate.setText(flight.getArrivalTime()); // Assuming this is the arrival time
        tvPrice.setText("$" + flight.getPrice()); // Assuming you want to show the price

        // Set the visibility of the radio button based on your logic
        radioButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Handle the radio button check change
        });

        return convertView;
    }
}