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

        TextView tvFrom = convertView.findViewById(R.id.tvFrom);
        TextView tvTo = convertView.findViewById(R.id.tvTo);
        TextView tvDepartureDate = convertView.findViewById(R.id.tvDepartureDate);
        TextView tvReturnDate = convertView.findViewById(R.id.tvReturnDate);
        TextView tvAirline = convertView.findViewById(R.id.tvAirline);
        TextView tvSeatClass = convertView.findViewById(R.id.tvSeatClass);
        TextView tvPrice = convertView.findViewById(R.id.tvPrice);
        RadioButton radioButton = convertView.findViewById(R.id.radioButton);

        // Display user input in the flight item
        tvFrom.setText(flight.getFrom());
        tvTo.setText(flight.getTo());
        tvDepartureDate.setText("Departure: " + flight.getDepartureDate());
        tvReturnDate.setText("Return: " + (flight.getReturnDate().isEmpty() ? "N/A" : flight.getReturnDate()));
        tvAirline.setText(flight.getAirline());
        tvSeatClass.setText("Class: " + flight.getSeatClass());
        tvPrice.setText("$" + flight.getPrice());

        return convertView;
    }
}