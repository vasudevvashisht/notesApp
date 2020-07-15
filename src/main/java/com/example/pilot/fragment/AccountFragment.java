package com.example.pilot.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.pilot.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AccountFragment extends Fragment {

    private TextView name;
    private TextView email;
    private ImageView imageView;
    private GoogleSignInAccount account;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_account,null);
        initViews();
        Glide.with(this)
                .load(account.getPhotoUrl())
                .placeholder(R.drawable.placeholder_account_image)
                .into(imageView);
        name.setText(account.getDisplayName());
        email.setText(account.getEmail());
        return view;
    }

    private void initViews() {
        imageView = view.findViewById(R.id.account_photo);
        name = view.findViewById(R.id.user_name);
        email = view.findViewById(R.id.user_email);
        account = GoogleSignIn.getLastSignedInAccount(getContext());


    }

}