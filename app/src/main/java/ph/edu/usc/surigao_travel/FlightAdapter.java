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

public class FlightAdapter extends ArrayAdapter<Flight> {
    private int selectedPosition = -1;

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
        TextView tvPrice = convertView.findViewById(R.id.tvPrice);
        RadioButton radioButton = convertView.findViewById(R.id.radioButton);
        Button btnBook = convertView.findViewById(R.id.btnBook); // The Book button

        etFrom.setText(flight.getFrom());
        etTo.setText(flight.getTo());
        txtDepartureDate.setText(flight.getDepartureTime());
        txtReturnDate.setText(flight.getReturnTime());
        tvAirline.setText(flight.getAirline());
        tvPrice.setText(flight.getPrice());

        btnBook.setVisibility(position == selectedPosition ? View.VISIBLE : View.GONE);

        radioButton.setOnClickListener(v -> {
            selectedPosition = position;
            notifyDataSetChanged();
        });

        btnBook.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), FlightDetailsActivity.class);
            intent.putExtra("airline", flight.getAirline());
            intent.putExtra("from", flight.getFrom());
            intent.putExtra("to", flight.getTo());
            intent.putExtra("departure", flight.getDepartureTime());
            intent.putExtra("return", flight.getReturnTime());
            intent.putExtra("price", flight.getPrice());
            getContext().startActivity(intent);
        });

        return convertView;
    }
}
