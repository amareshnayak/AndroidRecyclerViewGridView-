package com.peeparound.wizventure.peeparoundsecond;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

public class RecyclerViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView countryName;
    public ImageView countryPhoto;
    private Context ctx;
    String str[]={"atm","Hospital"};
    MainActivity main=new MainActivity();
    public RecyclerViewHolders(View itemView,Context ctx) {
        super(itemView);
        this.ctx=ctx;
        itemView.setOnClickListener(this);
        countryName = (TextView)itemView.findViewById(R.id.country_name);
        countryPhoto = (ImageView)itemView.findViewById(R.id.country_photo);

    }

    @Override
    public void onClick(View view) {
        Toast.makeText(view.getContext(), "getAdapterPosition = " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
        //latitude=location.getLatitude();
        //longitude=location.getLongitude();



            Intent inte=new Intent(ctx,ListActivity.class);
            inte.putExtra("position",getAdapterPosition());
            inte.putExtra("places",str[getAdapterPosition()]);
            ctx.startActivity(inte);



    }
    private boolean isGooglePlayServicesAvailable() {
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(ctx);
        if (ConnectionResult.SUCCESS == status) {
            return true;
        } else {
            GooglePlayServicesUtil.getErrorDialog(status, main, 0).show();
            return false;
        }
    }





}