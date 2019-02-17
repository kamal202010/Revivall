package com.khalaf.kamal.revival.viewmodel;
/*
 *
 * Created by Kamal Khalaf on 2/17/2019.
 * Contact : kamal.khalaf56@gmail.com
 *
 */

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.khalaf.kamal.revival.service.Employee;
import com.khalaf.kamal.revival.view.ui.EmployeeDetailActivityBinding;

public class ItemEmployeeViewModel extends BaseObservable {

    private Employee.EmpsBean people;
    private Context context;

    public ItemEmployeeViewModel(Employee.EmpsBean people, Context context) {
        this.people = people;
        this.context = context;
    }

    public String getFullName() {
        return people.getEmp_name();
    }

    public String getCell() {
        return people.getEmp_phone();
    }

    public Integer getFeedback() {
        return people.getFeed_back_number();
    }

    public String getPictureProfile() {
        return people.getEmp_pic_url();
    }

    @BindingAdapter("imageUrl") public static void setImageUrl(ImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }

    public void onItemClick(View view) {
//        context.startActivity(EmployeeDetailActivityBinding.launchDetail(view.getContext(), people));
    }

    public void setPeople(Employee.EmpsBean people) {
        this.people = people;
        notifyChange();
    }
}
