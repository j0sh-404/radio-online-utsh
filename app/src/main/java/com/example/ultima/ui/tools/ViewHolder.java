package com.example.ultima.ui.tools;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ultima.R;
import com.squareup.picasso.Picasso;

public class ViewHolder extends RecyclerView.ViewHolder {
    View mView;
    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        mView=itemView;
    }
    public void setDetails(Context ctx,String title,String description, String image){

        TextView mTitleTv= mView.findViewById(R.id.rTitleTv);
        TextView mDetailTv= mView.findViewById(R.id.rDescriptionTv);
        ImageView mImageTv=mView.findViewById(R.id.rImagenView);
    mTitleTv.setText(title);
    mDetailTv.setText(description);
        Picasso.get().load(image).into(mImageTv);
    }
}
