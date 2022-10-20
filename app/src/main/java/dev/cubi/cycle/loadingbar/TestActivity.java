package dev.cubi.cycle.loadingbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.ncorti.slidetoact.LoadingSliderView;
import com.ncorti.slidetoact.SlideToActView;

public class TestActivity extends AppCompatActivity {
    String TAG = "sldier_";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SlideToActView slider = findViewById(R.id.slidetoact);
        LoadingSliderView loadingSliderBtn = findViewById(R.id.loadingSlider);
        loadingSliderBtn.setOnSlideLoadingStartedListener(new LoadingSliderView.OnSlideLoadingStartedListener() {
            @Override
            public void onSlideLoadingStarted(@NonNull LoadingSliderView view) {
                view.setText("Loading data...");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (view.isLoadable()) {
                            if (view.isAnimateCompletion()) {
                                view.completeSlider();
                            }
                        }
                    }
                }, 8000);
            }
        });

        slider.setOnSlideResetListener(new SlideToActView.OnSlideResetListener() {
            @Override
            public void onSlideReset(@NonNull SlideToActView view) {
                Log.d(TAG, "onSlideReset: ");
            }
        });
        slider.setOnSlideToActAnimationEventListener(new SlideToActView.OnSlideToActAnimationEventListener() {
            @Override
            public void onSlideCompleteAnimationStarted(@NonNull SlideToActView view, float threshold) {
                Log.d(TAG, "onSlideCompleteAnimationStarted: ");
            }

            @Override
            public void onSlideCompleteAnimationEnded(@NonNull SlideToActView view) {
                Log.d(TAG, "onSlideCompleteAnimationEnded: ");
            }

            @Override
            public void onSlideResetAnimationStarted(@NonNull SlideToActView view) {
                Log.d(TAG, "onSlideResetAnimationStarted: ");
            }

            @Override
            public void onSlideResetAnimationEnded(@NonNull SlideToActView view) {
                Log.d(TAG, "onSlideResetAnimationEnded: ");
            }
        });
        slider.setOnSlideUserFailedListener(new SlideToActView.OnSlideUserFailedListener() {
            @Override
            public void onSlideFailed(@NonNull SlideToActView view, boolean isOutside) {
                Log.d(TAG, "onSlideFailed: ");
            }
        });

       getDrawable(com.ncorti.slidetoact.R.drawable.slidetoact_ic_rotate_right);

    }
}