package com.khalaf.kamal.revival.viewmodel;
/*
 *
 * Created by Kamal Khalaf on 2/17/2019.
 * Contact : kamal.khalaf56@gmail.com
 *
 */

import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.view.View;

import com.khalaf.kamal.revival.R;
import com.khalaf.kamal.revival.helper.RevivalApplication;
import com.khalaf.kamal.revival.service.Employee;
import com.khalaf.kamal.revival.service.webApi.ApiInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class MainViewModel extends Observable {


    public ObservableInt employeeProgress;
    public ObservableInt employeeRecycler;
    public ObservableInt peopleLabel;
    public ObservableField<String> messageLabel;

    private List<Employee.EmpsBean> employees;
    private Context context;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();


    public MainViewModel(@NonNull Context context) {

        this.context = context;
        this.employees = new ArrayList<>();
        employeeProgress = new ObservableInt(View.GONE);
        employeeRecycler = new ObservableInt(View.GONE);
        peopleLabel = new ObservableInt(View.VISIBLE);

        fetchData();

    }

    private void fetchData() {

        peopleLabel.set(View.GONE);
        employeeRecycler.set(View.GONE);
        employeeProgress.set(View.VISIBLE);

        RevivalApplication application = RevivalApplication.create(context);
        ApiInterface apiInterface = application.getApiInterface();

        Disposable disposable = apiInterface.fetchData()
                .subscribeOn(application.subscribeScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Employee>() {
                    @Override public void accept(Employee employee) throws Exception {
                        changeDataSet(employee.getEmps());
                        employeeProgress.set(View.GONE);
                        peopleLabel.set(View.GONE);
                        employeeRecycler.set(View.VISIBLE);
                    }
                }, new Consumer<Throwable>() {
                    @Override public void accept(Throwable throwable) throws Exception {
                        messageLabel.set(context.getString(R.string.error_loading_employee));
                        employeeProgress.set(View.GONE);
                        peopleLabel.set(View.VISIBLE);
                        employeeRecycler.set(View.GONE);
                    }
                });

        compositeDisposable.add(disposable);
    }

    private void changeDataSet(List<Employee.EmpsBean> peoples) {
        employees.addAll(peoples);
        setChanged();
        notifyObservers();
    }

    public List<Employee.EmpsBean> getEmployeeList() {
        return employees;
    }

    private void unSubscribeFromObservable() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }

    public void reset() {
        unSubscribeFromObservable();
        compositeDisposable = null;
        context = null;
    }
}
