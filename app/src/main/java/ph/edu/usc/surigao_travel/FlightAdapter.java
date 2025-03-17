import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

        TextView tvAirline = convertView.findViewById(R.id.tvAirline);
        TextView tvFlightDetails = convertView.findViewById(R.id.tvFlightDetails);
        TextView tvPrice = convertView.findViewById(R.id.tvPrice);
        RadioGroup radioGroup = convertView.findViewById(R.id.radioGroup);
        Button btnBook = convertView.findViewById(R.id.btnBook);

        tvAirline.setText(flight.getAirline());
        tvFlightDetails.setText(flight.getFlightNumber() + " | " + flight.getDuration());
        tvPrice.setText("$" + flight.getPrice());

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            btnBook.setVisibility(View.VISIBLE);
        });

        return convertView;
    }
}