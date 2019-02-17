package com.khalaf.kamal.revival.view.ui;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.khalaf.kamal.revival.R;
import com.khalaf.kamal.revival.databinding.ActivitySplashBinding;
import com.wang.avi.AVLoadingIndicatorView;

import static com.khalaf.kamal.revival.helper.Utilitess.isConnectedToInternet;

public class SplashActivity extends AppCompatActivity {


    private static final long delayTime = 3000;
    Handler handler = new Handler();
    private Context context;
    private AVLoadingIndicatorView avi;
    ActivitySplashBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);

        initViews();

    }

    private void initViews() {

        String indicator = getIntent().getStringExtra("indicator");
        binding.avi.setIndicator(indicator);

        this.context = SplashActivity.this;


        if (isConnectedToInternet(this)) {
            getLog();

        } else {
            binding.avi.smoothToHide();
            showSnackBar(getResources().getString(R.string.no_internet_conn), getString(R.string.retry), binding.activitySplash);
        }
    }


    private void getLog() {
        handler.postDelayed(postTask, delayTime);
    }

    @Override
    protected void onDestroy() {
        handler.removeCallbacks(postTask);
        super.onDestroy();
    }

    public void showSnackBar(final String stringMain, final String stringRetry, RelativeLayout linearLayout) {
        Snackbar.make(linearLayout, stringMain, Snackbar.LENGTH_INDEFINITE).
                setAction(stringRetry, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!isConnectedToInternet(SplashActivity.this)) {
                            showSnackBar(stringMain, stringRetry, binding.activitySplash);
                        } else getLog();
                    }
                }).show();
    }

    Runnable postTask = new Runnable() {
        @Override
        public void run() {
            startActivity(new Intent(context, MainActivity.class));
            overridePendingTransition(0, 0);
            binding.avi.smoothToHide();
            finish();
        }
    };
}
