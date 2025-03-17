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
    private int selectedPosition = -1; // Tracks which radio button is selected

    public HotelAdapter(Context context, List<Hotel> hotels) {
        super(context, 0, hotels);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.hotel_item, parent, false);
        }

        Hotel hotel = getItem(position);

        // Bind UI elements with correct IDs
        TextView tvFrom = convertView.findViewById(R.id.tvFrom);
        TextView tvGuests = convertView.findViewById(R.id.tvGuests);
        TextView tvCheckIn = convertView.findViewById(R.id.tvCheckIn);
        TextView tvCheckOut = convertView.findViewById(R.id.tvCheckOut);
        TextView tvName = convertView.findViewById(R.id.tvName);
        TextView tvPrice = convertView.findViewById(R.id.tvPrice);
        RadioButton radioButton = convertView.findViewById(R.id.radioButton);
        Button btnBook = convertView.findViewById(R.id.btnBook);

        // Set values
        tvFrom.setText(hotel.getFrom());
        tvGuests.setText(hotel.getGuests());
        tvCheckIn.setText(hotel.getCheckin());
        tvCheckOut.setText(hotel.getCheckout());
        tvName.setText(hotel.getName());
        tvPrice.setText(hotel.getPrice());

        // Show Book button only if this hotel is selected
        btnBook.setVisibility(position == selectedPosition ? View.VISIBLE : View.GONE);

        // Handle radio button selection
        radioButton.setChecked(position == selectedPosition);
        radioButton.setOnClickListener(v -> {
            selectedPosition = position;
            notifyDataSetChanged(); // Refresh the list
        });

        btnBook.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), FlightDetailsActivity.class);
            intent.putExtra("name", hotel.getName());
            intent.putExtra("from", hotel.getFrom());
            intent.putExtra("guests", hotel.getGuests());
            intent.putExtra("checkin", hotel.getCheckin());
            intent.putExtra("checkout", hotel.getCheckout());
            intent.putExtra("price", hotel.getPrice());
            getContext().startActivity(intent);
        });

        return convertView;
    }
}