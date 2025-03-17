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

public class HotelAdapter extends ArrayAdapter<Hotel> {
    private int selectedPosition = -1;

    public HotelAdapter(Context context, List<Flight> flights) {
        super(context, 0, flights);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.hotel_item, parent, false);
        }

        Hotel hotel = getItem(position);

        // Bind UI elements
        TextView etFrom = convertView.findViewById(R.id.etFrom);
        TextView etTo = convertView.findViewById(R.id.etTo);
        TextView txtDepartureDate = convertView.findViewById(R.id.txtDepartureDate);
        TextView txtReturnDate = convertView.findViewById(R.id.txtReturnDate);
        TextView tvAirline = convertView.findViewById(R.id.tvAirline);
        TextView tvPrice = convertView.findViewById(R.id.tvPrice);
        RadioButton radioButton = convertView.findViewById(R.id.radioButton);
        Button btnBook = convertView.findViewById(R.id.btnBook); // The Book button

        // Set values
        etFrom.setText(hotel.getFrom());
        etTo.setText(hotel.getTo());
        txtDepartureDate.setText(hotel.getDepartureTime());
        txtReturnDate.setText(hotel.getReturnTime());
        tvAirline.setText(hotel.getAirline());
        tvPrice.setText(hotel.getPrice());

        // Show Book button only if this flight is selected
        btnBook.setVisibility(position == selectedPosition ? View.VISIBLE : View.GONE);

        // Handle radio button click
        radioButton.setOnClickListener(v -> {
            selectedPosition = position;
            notifyDataSetChanged(); // Refresh the list to show the Book button
        });

        // Handle Book button click (Navigate to FlightDetailsActivity)
        btnBook.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), FlightDetailsActivity.class);
            intent.putExtra("airline", hotel.getAirline());
            intent.putExtra("from", hotel.getFrom());
            intent.putExtra("to", hotel.getTo());
            intent.putExtra("departure", hotel.getDepartureTime());
            intent.putExtra("return", hotel.getReturnTime());
            intent.putExtra("price", hotel.getPrice());
            getContext().startActivity(intent);
        });

        return convertView;
    }
}
