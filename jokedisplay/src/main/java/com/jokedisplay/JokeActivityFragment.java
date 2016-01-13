package com.jokedisplay;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Zennigan on 1/10/2016.
 */
public class JokeActivityFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_joke, container, false);
        Intent intent =getActivity().getIntent();

        if(intent != null){
            TextView textView =  (TextView) root.findViewById(R.id.joke);
            textView.setText(intent.getStringExtra("joke"));
        }
        return root;
    }
}
