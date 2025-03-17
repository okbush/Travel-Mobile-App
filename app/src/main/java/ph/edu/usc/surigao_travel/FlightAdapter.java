package ph.edu.usc.surigao_travel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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

        // Bind UI elements
        TextView etFrom = convertView.findViewById(R.id.etFrom);
        TextView etTo = convertView.findViewById(R.id.etTo);
        TextView txtDepartureDate = convertView.findViewById(R.id.txtDepartureDate);
        TextView txtReturnDate = convertView.findViewById(R.id.txtReturnDate);
        TextView tvAirline = convertView.findViewById(R.id.tvAirline);
        TextView tvPrice = convertView.findViewById(R.id.tvPrice);
        RadioButton radioButton = convertView.findViewById(R.id.radioButton);

        // Set values from the Flight object
        etFrom.setText(flight.getFrom());
        etTo.setText(flight.getTo());
        txtDepartureDate.setText(flight.getDepartureTime());
        txtReturnDate.setText(flight.getReturnTime());
        tvAirline.setText(flight.getAirline());
        tvPrice.setText(flight.getPrice());

        return convertView;
    }
}