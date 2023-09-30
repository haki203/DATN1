package com.example.ttdn1.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.ttdn1.R;
import com.example.ttdn1.model.Home;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {
    private List<Home> list;

    public HomeAdapter(List<Home> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public HomeAdapter.HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home, parent, false);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.HomeViewHolder holder, int position) {
        Home home = list.get(position);
        if (home == null){
            return;
        }
        holder.txtname.setText(home.getName());
        holder.txtmusic.setText(home.getMusic());
        holder.img.setImageResource(home.getResource());
    }

    @Override
    public int getItemCount() {
        if (list != null){
            return list.size();
        }else {
            return 0;
        }
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder{
        private ImageView img;
        private TextView txtname, txtmusic;
        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imgView);
            txtname = itemView.findViewById(R.id.txtnamemusic);
            txtmusic = itemView.findViewById(R.id.txtname);

        }
    }
}
