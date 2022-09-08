package com.bats.admin.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bats.admin.R;


public class Contatos extends Fragment {

    TextView link1, link2, link3, link4;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        View view = inflater.inflate(R.layout.fragment_contatos, container, false);
        initComponents(view);
        linkActive();
        return view;
    }

    public void initComponents(View view) {
        link1 = view.findViewById(R.id.link1);
        link2 = view.findViewById(R.id.link2);
        link3 = view.findViewById(R.id.link3);
        link4 = view.findViewById(R.id.link4);
    }

    public void linkActive() {
        link1.setMovementMethod(LinkMovementMethod.getInstance());
        link2.setMovementMethod(LinkMovementMethod.getInstance());
        link3.setMovementMethod(LinkMovementMethod.getInstance());
        link4.setMovementMethod(LinkMovementMethod.getInstance());
    }
}