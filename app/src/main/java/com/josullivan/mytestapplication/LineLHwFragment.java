package com.josullivan.mytestapplication;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class LineLHwFragment extends Fragment {

    private PaintView paintView;
    public ArrayList<FingerPath> lineLoad;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.fragment_line_l_hw, container, false);

            return v;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        paintView = view.findViewById(R.id.lineLPaintView);
        loadData();
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        paintView.init(metrics);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.normal:
                paintView.normal();
                saveData();
                return true;

            case R.id.clear:
                paintView.clear();
                saveData();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void saveData() {
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("line test 1", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(paintView.getPaths());
        editor.putString("line test", json);
        editor.apply();
        Toast.makeText(this.getActivity(), "Data saved", Toast.LENGTH_SHORT).show();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("line test 1", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("line test", null);
        Type type = new TypeToken<ArrayList<FingerPath>>() {}.getType();

        lineLoad = gson.fromJson(json, type);
        if (lineLoad != null) {
            paintView.setPaths(lineLoad);
            Toast.makeText(this.getActivity(), "Data loaded", Toast.LENGTH_SHORT).show();
        }
    }






}
