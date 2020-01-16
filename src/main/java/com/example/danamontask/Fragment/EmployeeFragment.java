package com.example.danamontask.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.danamontask.Adapter.EmployeeAdapter;
import com.example.danamontask.Model.DataApi;
import com.example.danamontask.R;

import java.util.List;

public class EmployeeFragment extends Fragment {

    private RecyclerView mList;
    private EmployeeAdapter employeeAdapter;
    private List<DataApi> dataApiList;
    private DividerItemDecoration dividerItemDecoration;


    //    private LinearLayoutManager linearLayoutManager;
    private RecyclerView.Adapter adapter;

    public EmployeeFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_main, container, false);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);

        dividerItemDecoration = new DividerItemDecoration(mList.getContext(), LinearLayoutManager.HORIZONTAL);

        mList = v.findViewById(R.id.recyclerContiner);
        mList.setHasFixedSize(true);
        mList.setLayoutManager(layoutManager);

        /*ArrayList<DataApi> dataFoods = getData();

        employeeAdapter = new EmployeeAdapter(dataFoods, getActivity());
        mList.setAdapter(employeeAdapter);*/

        return v;

    }



}
