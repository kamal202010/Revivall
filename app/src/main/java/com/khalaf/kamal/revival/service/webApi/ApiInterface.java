package com.khalaf.kamal.revival.service.webApi;
/*
 *
 * Created by Kamal Khalaf on 2/17/2019.
 * Contact : kamal.khalaf56@gmail.com
 *
 */

import com.khalaf.kamal.revival.service.Employee;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ApiInterface {

    @GET("employee/getall")
    Observable<Employee> fetchData();

}
