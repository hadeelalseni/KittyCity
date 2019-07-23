package hadeel.com.kittycity.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

import hadeel.com.kittycity.Common.Common;
import hadeel.com.kittycity.Fragment.KittyProfileFragment;
import hadeel.com.kittycity.Model.Kitty;
import hadeel.com.kittycity.R;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    Context context;
    private ArrayList<Kitty> kitties;

    public Adapter(Context context, ArrayList<Kitty> kitties) {
        this.context = context;
        this.kitties = kitties;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup, final int i) {
        Kitty kitty = kitties.get(i);
        View oneKitty = LayoutInflater.from(context)
                .inflate(R.layout.one_card_kitty, viewGroup, false);
        ViewHolder holder = new ViewHolder(oneKitty);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        viewHolder.kittyName.setText(kitties.get(i).getName());
        viewHolder.kittyImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AppCompatActivity appCompatActivity = (AppCompatActivity) view.getContext();
                Bundle b = new Bundle();
                b.putString("name",kitties.get(i).getName());
                b.putString("image",kitties.get(i).getImage());
                Common.choosedKitty = i;
                Fragment fragment = new KittyProfileFragment();
                fragment.setArguments(b);
                appCompatActivity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.framelayout, fragment).addToBackStack(null).commit();

            }
        });
        Picasso.get()
                .load(kitties.get(i).getImage())
                .placeholder(R.drawable.ic_cat)
                .into(viewHolder.kittyImage);
    }

    @Override
    public int getItemCount() {
        return kitties.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView kittyImage;
        TextView kittyName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            kittyImage = itemView.findViewById(R.id.one_card_kitty_iv);
            kittyName = itemView.findViewById(R.id.one_card_kitty_tv);
        }
    }
}
