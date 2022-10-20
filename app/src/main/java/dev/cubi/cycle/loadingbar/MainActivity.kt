package dev.cubi.cycle.loadingbar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ncorti.slidetoact.SlideToActView
import com.ncorti.slidetoact.SlideToActView.OnSlideCompleteListener


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var slider: SlideToActView = findViewById<SlideToActView>(R.id.slidetoact)
        var TAG = "sldier_"
        slider.onSlideCompleteListener = object : OnSlideCompleteListener {
            override fun onSlideComplete(view: SlideToActView) {}
        }


       /* slider.onSlideCompleteListener(object : OnSlideCompleteListener {
            override fun onSlideComplete(view: SlideToActView) {
                Log.d(TAG, "onSlideComplete: ")
            }
        })
        slide.setOnSlideResetListener(object : OnSlideResetListener {
            override fun onSlideReset(view: SlideToActView) {
                Log.d(TAG, "onSlideReset: ")
            }
        })
        slide.setOnSlideUserFailedListener(object : OnSlideUserFailedListener {
            override fun onSlideFailed(view: SlideToActView, isOutside: Boolean) {
                Log.d(TAG, "onSlideFailed: ")
            }
        })
        slide.setOnSlideToActAnimationEventListener(object : OnSlideToActAnimationEventListener {
            override fun onSlideCompleteAnimationStarted(view: SlideToActView, threshold: Float) {
                Log.d(TAG, "onSlideCompleteAnimationStarted: ")
            }

            override fun onSlideCompleteAnimationEnded(view: SlideToActView) {
                Log.d(TAG, "onSlideCompleteAnimationEnded: ")
            }

            override fun onSlideResetAnimationStarted(view: SlideToActView) {
                Log.d(TAG, "onSlideResetAnimationStarted: ")
            }

            override fun onSlideResetAnimationEnded(view: SlideToActView) {

            }
        })*/
    }
}