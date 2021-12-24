package com.example.blogpages;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class AccountFragment extends Fragment {

    SharedPreferences sp;
    TextView name,email,id;
    public AccountFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account,container,false);
        sp = getContext().getSharedPreferences("loginPreference",0);

        id = view.findViewById(R.id.userid);
        email = view.findViewById(R.id.email);
        name = view.findViewById(R.id.username);
        id.setText("User ID : " + sp.getString("userid",""));
        name.setText("Username : " + sp.getString("username",""));
        email.setText("User Email : " + sp.getString("email","No"));
        return view;
    }
}