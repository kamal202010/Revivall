package com.khalaf.kamal.revival.view.adapter;
/*
 *
 * Created by Kamal Khalaf on 2/17/2019.
 * Contact : kamal.khalaf56@gmail.com
 *
 */

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.khalaf.kamal.revival.R;
import com.khalaf.kamal.revival.databinding.ItemEmployeeBinding;
import com.khalaf.kamal.revival.service.Employee;
import com.khalaf.kamal.revival.viewmodel.ItemEmployeeViewModel;

import java.util.Collections;
import java.util.List;

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.PeopleAdapterViewHolder> {

    private List<Employee.EmpsBean> peopleList;

    public PeopleAdapter() {
        this.peopleList = Collections.emptyList();
    }

    @Override public PeopleAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemEmployeeBinding itemPeopleBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_employee,
                        parent, false);
        return new PeopleAdapterViewHolder(itemPeopleBinding);
    }

    @Override public void onBindViewHolder(PeopleAdapterViewHolder holder, int position) {
        holder.bindPeople(peopleList.get(position));
    }

    @Override public int getItemCount() {
        return peopleList.size();
    }

    public void setPeopleList(List<Employee.EmpsBean> peopleList) {
        this.peopleList = peopleList;
        notifyDataSetChanged();
    }

    public static class PeopleAdapterViewHolder extends RecyclerView.ViewHolder {
        ItemEmployeeBinding mItemPeopleBinding;

        public PeopleAdapterViewHolder(ItemEmployeeBinding itemPeopleBinding) {
            super(itemPeopleBinding.itemPeople);
            this.mItemPeopleBinding = itemPeopleBinding;
        }

        void bindPeople(Employee.EmpsBean people) {
            if (mItemPeopleBinding.getPeopleViewModel() == null) {
                mItemPeopleBinding.setPeopleViewModel(
                        new ItemEmployeeViewModel(people, itemView.getContext()));
            } else {
                mItemPeopleBinding.getPeopleViewModel().setPeople(people);
            }
        }
    }
}
