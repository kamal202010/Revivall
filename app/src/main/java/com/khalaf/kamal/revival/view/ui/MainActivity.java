package com.khalaf.kamal.revival.view.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.khalaf.kamal.revival.R;
import com.khalaf.kamal.revival.databinding.ActivityMainBinding;
import com.khalaf.kamal.revival.service.webApi.ApiClient;
import com.khalaf.kamal.revival.view.adapter.PeopleAdapter;
import com.khalaf.kamal.revival.viewmodel.MainViewModel;

import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity implements Observer {

    ActivityMainBinding peopleActivityBinding;
    private MainViewModel peopleViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDataBinding();
        setSupportActionBar(peopleActivityBinding.toolbar);
        setupListPeopleView(peopleActivityBinding.listEmployee);
        setupObserver(peopleViewModel);



    }

    private void initDataBinding() {

        peopleActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        peopleViewModel = new MainViewModel(this);
        peopleActivityBinding.setMainViewModel(peopleViewModel);
    }

    private void setupListPeopleView(RecyclerView listPeople) {
        PeopleAdapter adapter = new PeopleAdapter();
        listPeople.setAdapter(adapter);
        listPeople.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        peopleViewModel.reset();
    }

    @Override public void update(Observable observable, Object data) {
        if (observable instanceof MainViewModel) {
            PeopleAdapter peopleAdapter = (PeopleAdapter) peopleActivityBinding.listEmployee.getAdapter();
            MainViewModel peopleViewModel = (MainViewModel) observable;
            peopleAdapter.setPeopleList(peopleViewModel.getEmployeeList());
        }
    }

    private void startActivityActionView() {
//        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(ApiClient.PROJECT_URL)));
    }

    public void setupObserver(Observable observable) {
        observable.addObserver(this);
    }
}
