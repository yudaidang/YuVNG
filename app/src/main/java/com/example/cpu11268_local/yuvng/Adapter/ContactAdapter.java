package com.example.cpu11268_local.yuvng.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.cpu11268_local.yuvng.MainActivity;
import com.example.cpu11268_local.yuvng.Model.Contact;
import com.example.cpu11268_local.yuvng.R;

import java.io.File;
import java.util.List;

/**
 * Created by cpu11268-local on 13/03/2018.
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    List<Contact> contactList;
    private Context context;

    public ContactAdapter(Context context, List<Contact> contact) {
        this.contactList = contact;
        this.context = context;

    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, parent, false);
        ContactViewHolder contactViewHolder = new ContactViewHolder(v);

        return contactViewHolder;
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, final int position) {
        holder.name.setText(contactList.get(position).getContactName());
        Uri imageUri = contactList.get(position).getContactImage();
        holder.number.setText(contactList.get(position).getContactNumber());
        Glide.with(context)
                .load(imageUri)
                .apply(RequestOptions.circleCropTransform())
                .into(holder.image);
        holder.itemView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View ew) {

                Toast.makeText(context, contactList.get(position).getContactName().toString(), Toast.LENGTH_SHORT).show();
            }

        });
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder{
        TextView name, number;
        ImageView image;

        ContactViewHolder(View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.name_contact);
            image = (ImageView)itemView.findViewById(R.id.image_contact);
            number = (TextView) itemView.findViewById(R.id.number_contact);
        }


    }
}
