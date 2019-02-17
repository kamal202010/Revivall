package com.khalaf.kamal.revival.view.ui;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.khalaf.kamal.revival.R;
import com.khalaf.kamal.revival.databinding.ActivityEmployeeDetailBindingBinding;
import com.khalaf.kamal.revival.service.Employee;
import com.khalaf.kamal.revival.viewmodel.PeopleDetailViewModel;

public class EmployeeDetailActivityBinding extends AppCompatActivity {

    private static final String EXTRA_PEOPLE = "EXTRA_PEOPLE";
    private ActivityEmployeeDetailBindingBinding peopleDetailActivityBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_detail_binding);

        peopleDetailActivityBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_employee_detail_binding);
        setSupportActionBar(peopleDetailActivityBinding.toolbar);
        displayHomeAsUpEnabled();
        getExtrasFromIntent();
    }


    public static Intent launchDetail(Context context, Employee.EmpsBean empsBean) {
        Intent intent = new Intent(context, EmployeeDetailActivityBinding.class);
        intent.putExtra(EXTRA_PEOPLE, empsBean);
        return intent;
    }


    private void displayHomeAsUpEnabled() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void getExtrasFromIntent() {
        Employee.EmpsBean people = (Employee.EmpsBean) getIntent().getSerializableExtra(EXTRA_PEOPLE);
        PeopleDetailViewModel peopleDetailViewModel = new PeopleDetailViewModel(people);
        peopleDetailActivityBinding.setPeopleDetailViewModel(peopleDetailViewModel);
        setTitle(people.getEmp_name());
    }
}
