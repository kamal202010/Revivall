package com.khalaf.kamal.revival.viewmodel;
/*
 *
 * Created by Kamal Khalaf on 2/17/2019.
 * Contact : kamal.khalaf56@gmail.com
 *
 */

import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.khalaf.kamal.revival.service.Employee;

public class PeopleDetailViewModel {
    private Employee.EmpsBean people;

    public PeopleDetailViewModel(Employee.EmpsBean people) {
        this.people = people;
    }

    public String getFullUserName() {
        return people.getEmp_name();
    }

    public String getUserName() {
        return people.getEmp_name();
    }

    public String getCell() {
        return people.getEmp_phone();
    }



    public String getAddress() {
        return people.getCity_name()
                + " "
                + people.getEmp_lat()
                + " "
                + people.getEmp_lng();
    }

    public String getFirebaseToken() {
        return people.getEmp_firebase_token();
    }

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext()).load(imageUrl).into(view);
    }
}
