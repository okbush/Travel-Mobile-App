package ph.edu.usc.surigao_travel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TripAdapter extends RecyclerView.Adapter<TripAdapter.TripViewHolder> {
    private Context context;
    private List<TripItem> tripList;
    private TripClickListener listener;

    public interface TripClickListener {
        void onModify(int position);
        void onCancel(int position);
    }

    public TripAdapter(Context context, List<TripItem> tripList, TripClickListener listener) {
        this.context = context;
        this.tripList = tripList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TripViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.trip_item, parent, false);
        return new TripViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TripViewHolder holder, int position) {
        TripItem trip = tripList.get(position);

        holder.tvPassengerName.setText("Passenger: " + trip.getPassengerName());
        holder.tvBusOrAirline.setText("Bus Name: " + trip.getBusOrAirline()); // ✅ Fixed
        holder.tvDeparture.setText("From: " + trip.getDeparture());
        holder.tvArrival.setText("To: " + trip.getArrival());
        holder.tvTravelDate.setText("Date: " + trip.getTravelDate());
        holder.tvPrice.setText("Price: " + trip.getPrice());

        holder.btnModify.setOnClickListener(v -> listener.onModify(position));
        holder.btnCancel.setOnClickListener(v -> listener.onCancel(position));
    }

    @Override
    public int getItemCount() {
        return tripList.size();
    }

    public static class TripViewHolder extends RecyclerView.ViewHolder {
        TextView tvPassengerName, tvBusOrAirline, tvDeparture, tvArrival, tvTravelDate, tvPrice;
        Button btnModify, btnCancel;

        public TripViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPassengerName = itemView.findViewById(R.id.tvPassengerName);
            tvDeparture = itemView.findViewById(R.id.tvDeparture);
            tvArrival = itemView.findViewById(R.id.tvArrival);
            tvTravelDate = itemView.findViewById(R.id.tvTravelDate);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            btnModify = itemView.findViewById(R.id.btnModify);
            btnCancel = itemView.findViewById(R.id.btnCancel);
        }
    }
}