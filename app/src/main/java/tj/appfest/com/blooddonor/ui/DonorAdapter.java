package tj.appfest.com.blooddonor.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import tj.appfest.com.blooddonor.R;
import tj.appfest.com.blooddonor.UserProfile;

/**
 * Created by khalidparvez on 28/06/17.
 */

public class DonorAdapter extends RecyclerView.Adapter<DonorAdapter.MyViewHolder> {

    private List<UserProfile> moviesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year, genre;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.donor_title);
            genre = (TextView) view.findViewById(R.id.donor_mobilenumber);
            year = (TextView) view.findViewById(R.id.donor_bloodgroup);
        }
    }


    public DonorAdapter(List<UserProfile> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.donor_list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        UserProfile movie = moviesList.get(position);
        holder.title.setText(movie.getName());
        holder.genre.setText(movie.getEmail());
        holder.year.setText(movie.getBloodType());
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
