package com.example.comp1011assignment3_200465333.adapter;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import com.example.comp1011assignment3_200465333.R;

import java.util.ArrayList;

public class ItemAdapter extends ArrayAdapter<String> {

    ArrayList<String> adapterVehicleList;
    ArrayList<Bitmap> adapterImageLayoutList;
    Context adapterContext;

    int thumbnailWidth = 150;
    int thumbnailHeight = 150;

    public ItemAdapter(@NonNull Context context,  ArrayList<String> vehicleList, ArrayList<Bitmap> imageLayoutList) {
        super(context, R.layout.listview_item);
        this.adapterVehicleList = vehicleList;
        this.adapterImageLayoutList = imageLayoutList;
        this.adapterContext = context;
    }

    @Override
    public int getCount() {
        return adapterVehicleList.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if(convertView == null) {
            LayoutInflater adapterInflater = (LayoutInflater) adapterContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = adapterInflater.inflate(R.layout.listview_item, parent, false);
            viewHolder.imageView = convertView.findViewById(R.id.imageView_item);
            viewHolder.textView = convertView.findViewById(R.id.textView_item);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Resize the bitmap to the thumbnail dimensions
        Bitmap thumbnailBitmap = Bitmap.createScaledBitmap(adapterImageLayoutList.get(position), thumbnailWidth, thumbnailHeight, false);
        viewHolder.imageView.setImageBitmap(thumbnailBitmap);

        // Reset text color to default before applying changes
        viewHolder.textView.setTextColor(ContextCompat.getColor(adapterContext, android.R.color.primary_text_light));

        if(adapterVehicleList.get(position).toString().startsWith(" SOLD\n")){
            viewHolder.textView.setTextColor(Color.RED);
        };
        viewHolder.textView.setText(adapterVehicleList.get(position));

        return convertView;
    }

    static class ViewHolder {
        ImageView imageView;
        TextView textView;
    }

    @Override
    public void notifyDataSetChanged() {
        // Adapter data has changed
        super.notifyDataSetChanged();
    }
}

