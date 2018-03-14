package com.example.cpu11268_local.yuvng;


import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class SmsFragment extends Fragment {

    TextView tbTitle;
    Typeface tf;
    public SmsFragment() {
        // Required empty public constructor
    }

    @SuppressLint("RestrictedApi")
    private void setupToolbar(Toolbar tb){
        ((AppCompatActivity)getActivity()).setSupportActionBar(tb);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        tb.setNavigationIcon(R.drawable.ic_action_home);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        setupToolbar(toolbar);
        tbTitle = (TextView) view.findViewById(R.id.toolbar_title);
        tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Audiowide.ttf");
        tbTitle.setTypeface(tf);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sms, container, false);
    }

}
