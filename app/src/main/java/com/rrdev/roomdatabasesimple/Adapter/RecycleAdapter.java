package com.rrdev.roomdatabasesimple.Adapter;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.rrdev.roomdatabasesimple.Activity.DetailActivity;
import com.rrdev.roomdatabasesimple.Activity.UpdateActivity;
import com.rrdev.roomdatabasesimple.R;
import com.rrdev.roomdatabasesimple.RoomDb.Mahasiswa;

import java.util.ArrayList;
import java.util.List;

import static com.rrdev.roomdatabasesimple.RoomDb.MyApp.db;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyViewHolder> {

    private Context mContext;
    private ArrayList<Mahasiswa> albumList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        //inisialisasi view
        TextView nama,nim,kejuruan,alamat;
        CardView cVmain;
        public MyViewHolder(View v) {
            super(v);

            nama = v.findViewById(R.id.tvNama);
            nim = v.findViewById(R.id.tvNim);
            kejuruan = v.findViewById(R.id.tvKejuruan);
            alamat = v.findViewById(R.id.tvAlamat);
            cVmain = v.findViewById(R.id.cv_main);
        }
    }

    public RecycleAdapter(Context mContext, ArrayList<Mahasiswa> albumList) {
        // inisialisasi data
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inisialisasi viewholder
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recyclerview, parent, false);
        // mengeset ukuran view, margin, padding, dan parameter layout lainnya
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        //menampilkan data pada view
        final Mahasiswa album = albumList.get(position);
        holder.nama.setText(album.getNama());
        holder.nim.setText(album.getNim());
        holder.kejuruan.setText(album.getKejuruan());
        holder.alamat.setText(album.getAlamat());

        holder.cVmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetailActivity.class);

                //passing data to detail act
                intent.putExtra("nim",albumList.get(position).getNim());
                intent.putExtra("nama",albumList.get(position).getNama());
                intent.putExtra("kej",albumList.get(position).getKejuruan());
                intent.putExtra("alam",albumList.get(position).getAlamat());

                mContext.startActivity(intent);
            }
        });
        holder.cVmain.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                final Dialog dialog = new Dialog(mContext);
                dialog.setContentView(R.layout.view_dialog);
                dialog.setTitle("pilih aksi");
                dialog.show();

                Button editButton = dialog.findViewById(R.id.bt_edit_data);
                Button delButton = dialog.findViewById(R.id.bt_delete_data);

                //apabila tombol edit diklik
                editButton.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                                onEditBarang(position);
                            }
                        }
                );

                //apabila tombol delete diklik
                delButton.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                                onDeteleBarang(position);
                            }
                        }
                );
                return true;
            }
        });
    }

    private void onDeteleBarang(int position){
        db.userDao().deleteUsers(albumList.get(position));
        albumList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeRemoved(position, albumList.size());
    }

    private void onEditBarang(int position){
        mContext.startActivity(UpdateActivity.getActIntent((Activity) mContext).putExtra("data", albumList.get(position)));
    }
    @Override
    public int getItemCount() {
        return albumList.size();
    }
}