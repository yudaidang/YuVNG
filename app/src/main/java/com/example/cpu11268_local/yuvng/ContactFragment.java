package com.example.cpu11268_local.yuvng;


import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.cpu11268_local.yuvng.Adapter.ContactAdapter;
import com.example.cpu11268_local.yuvng.Model.Contact;

import java.util.ArrayList;
import java.util.List;
import static android.widget.LinearLayout.VERTICAL;

public class ContactFragment extends Fragment {
    RecyclerView recyclerView;
    List<Contact> contacts;
    ContactAdapter contactAdapter;
    TextView tbTitle;
    Typeface tf;
    public ContactFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {

        ContactsAsyncTask contactsAsyncTask = new ContactsAsyncTask();
        contactsAsyncTask.execute();

        super.onCreate(savedInstanceState);
    }

    @SuppressLint("RestrictedApi")
    private void setupToolbar(Toolbar tb){
        ((AppCompatActivity)getActivity()).setSupportActionBar(tb);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        tb.setNavigationIcon(R.drawable.ic_action_camera);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        setupToolbar(toolbar);
        tbTitle = (TextView) view.findViewById(R.id.toolbar_title);
        tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Audiowide.ttf" );
        tbTitle.setTypeface(tf);

        recyclerView = (RecyclerView) view.findViewById(R.id.list_contact);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity().getApplicationContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(llm);
        DividerItemDecoration itemDecor = new DividerItemDecoration(getActivity().getApplicationContext(), VERTICAL);
        itemDecor.setDrawable(ContextCompat.getDrawable(getActivity().getApplicationContext(), R.drawable.mydevide));
        recyclerView.addItemDecoration(itemDecor);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_contact, container, false);
        // Inflate the layout for this fragment
        return rootView;
    }

    private class ContactsAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            contacts = readContacs();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            contactAdapter = new ContactAdapter(getActivity().getApplicationContext(), contacts);
            contactAdapter.notifyDataSetChanged();
            recyclerView.setAdapter(contactAdapter);
        }
    }

    public ArrayList<Contact> readContacs(){
        ArrayList<Contact> contacts = new ArrayList<>();
        Uri uri = ContactsContract.Contacts.CONTENT_URI;
        Cursor conCursor = getActivity().getApplicationContext().getContentResolver().query(uri, null, null, null,
                ContactsContract.Contacts.DISPLAY_NAME + " ASC ");

        if(conCursor.moveToFirst()){
            do {
                long contactId = conCursor.getLong(conCursor
                        .getColumnIndex("_ID"));
                Uri dataUri = ContactsContract.Data.CONTENT_URI;
                Cursor dataCursor = getActivity().getApplicationContext().getContentResolver().query(
                        dataUri, null,
                        ContactsContract.Data.CONTACT_ID + " = " + contactId,
                        null, null);
                String ContactName = "";
                String ContactNumber = "";
                Uri ContactImage = null;
                String ContactDetail = "";
                if (dataCursor.moveToFirst()){
                    ContactName = dataCursor.getString(
                            dataCursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    do{
                        // Getting Phone numbers
                        if(dataCursor.getString(dataCursor.getColumnIndex("mimetype")).equals(
                                ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)){
                            ContactNumber = dataCursor.getString(dataCursor.getColumnIndex("data1"));
                        }

                        if(dataCursor.getString(dataCursor.getColumnIndex("mimetype")).equals(
                                ContactsContract.CommonDataKinds.Organization.CONTENT_ITEM_TYPE)){
                            ContactDetail = dataCursor.getString(dataCursor.getColumnIndex("data1"));
                        }

                        if (dataCursor
                                .getString(
                                        dataCursor.getColumnIndex("mimetype"))
                                .equals(ContactsContract.CommonDataKinds.Photo.CONTENT_ITEM_TYPE)){
                            Uri contactUri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, contactId);
                            ContactImage = Uri.withAppendedPath(contactUri, ContactsContract.Contacts.Photo.CONTENT_DIRECTORY);

                        }
                    }while (dataCursor.moveToNext());
                    contacts.add(new Contact(contactId, ContactName, ContactNumber, ContactImage, ContactDetail));
                }
                dataCursor.close();
            }while (conCursor.moveToNext());
            conCursor.close();
        }
        return contacts;
    }
}
