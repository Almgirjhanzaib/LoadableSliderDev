package dev.cubi.cycle.loadingbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;
import com.ncorti.slidetoact.LoadingSliderView;
import com.ncorti.slidetoact.SlideToActView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestActivity extends AppCompatActivity {
    String TAG = "sldier_";
    RequestQueue requestQueue2;
    Snackbar snackbar;

    private enum CALL_STATUS {
        ACCEPTED, ENROUTE, ARRIVED, WORKING, PAUSE, RESUME, FINISHED
    }

    CALL_STATUS selectedStatus = CALL_STATUS.ACCEPTED;
    List<CALL_STATUS> listOfCallBacks = new ArrayList<>();

    private void initList() {
        listOfCallBacks.add(CALL_STATUS.ACCEPTED);
        listOfCallBacks.add(CALL_STATUS.ENROUTE);
        listOfCallBacks.add(CALL_STATUS.ARRIVED);
        listOfCallBacks.add(CALL_STATUS.WORKING);
        listOfCallBacks.add(CALL_STATUS.PAUSE);
        listOfCallBacks.add(CALL_STATUS.RESUME);
        listOfCallBacks.add(CALL_STATUS.FINISHED);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initList();
        LoadingSliderView loadingSliderBtn = findViewById(R.id.loadingSlider);



        loadingSliderBtn.setOnSlideLoadingStartedListener(new LoadingSliderView.OnSlideLoadingStartedListener() {
            @Override
            public void onSlideLoadingStarted(@NonNull LoadingSliderView view) {
                view.setText("Loading data...");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (listOfCallBacks.size() > 0) {
                            selectedStatus = listOfCallBacks.get(0);
                            switch (selectedStatus) {
                                case ACCEPTED:
                                    STATUS_WEB_CALL("1", "STUDENT", "STATUS", view);
                                    break;
                                case ENROUTE:
                                    //ENROUTE callback here
                                    STATUS_WEB_CALL("1", "STUDENT", "STATUS", view);
                                    break;
                                case ARRIVED:
                                    STATUS_WEB_CALL("1", "STUDENT", "STATUS", view);
                                    break;
                                case PAUSE:
                                    //PAUSE callback here
                                    STATUS_WEB_CALL("1", "STUDENT", "STATUS", view);
                                    break;
                                case WORKING:
                                    STATUS_WEB_CALL("1", "STUDENT", "STATUS", view);
                                    break;
                                case RESUME:
                                    //RESUME callback here
                                    STATUS_WEB_CALL("1", "STUDENT", "STATUS", view);
                                    break;
                                case FINISHED:
                                    //FINISHED callback here
                                    STATUS_WEB_CALL("1", "STUDENT", "STATUS", view);
                                    break;
                                default:
                                    break;
                            }

                        } else {
                            Toast.makeText(TestActivity.this, "Your task finished", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, 2000);


            }
        });
        loadingSliderBtn.setOnSlideCompleteListener(new LoadingSliderView.OnSlideCompleteListener() {
            @Override
            public void onSlideComplete(@NonNull LoadingSliderView view) {
                view.resetSlider();

            }
        });
        loadingSliderBtn.setOnSlideResetListener(new LoadingSliderView.OnSlideResetListener() {
            @Override
            public void onSlideReset(@NonNull LoadingSliderView view) {
                if (listOfCallBacks.size() > 0) {
                    listOfCallBacks.remove(selectedStatus);
                    if (listOfCallBacks.size() > 0) {
                        selectedStatus = listOfCallBacks.get(0);
                        view.setOuterColor(getResources().getColor(R.color.red));
                        view.setText(selectedStatus.name().toString());
                    } else {
                        view.setText("Finished Task");
                    }
                }
            }
        });
        loadingSliderBtn.setOnSlideToActAnimationEventListener(new LoadingSliderView.OnSlideToActAnimationEventListener() {
            @Override
            public void onSlideCompleteAnimationStarted(@NonNull LoadingSliderView view, float threshold) {
                view.setOuterColor(getResources().getColor(R.color.green));
            }

            @Override
            public void onSlideCompleteAnimationEnded(@NonNull LoadingSliderView view) {
                view.setOuterColor(getResources().getColor(R.color.green));
            }

            @Override
            public void onSlideResetAnimationStarted(@NonNull LoadingSliderView view) {

            }

            @Override
            public void onSlideResetAnimationEnded(@NonNull LoadingSliderView view) {

            }
        });

     /*   slider.setOnSlideResetListener(new SlideToActView.OnSlideResetListener() {
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
        });*/


    }

    private void updateLoadingBar(LoadingSliderView view) {
        if (view.isLoadable()) {
            if (view.isAnimateCompletion()) {
                view.completeSlider();
                view.setOuterColor(getResources().getColor(R.color.red));
            }
        }
    }

    private void STATUS_WEB_CALL(String selectedStudent, String selectedRole, String updateStatus, LoadingSliderView view) {


        String HTTP_SERVER_URL = String.format("http://testaapi/postStatus?Studentid=%1$s&Role=%2$s&updateStatus=%3$s", selectedStudent, selectedRole, updateStatus);
        try {
            URLEncoder.encode(HTTP_SERVER_URL, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            Log.e("EncodingException", "UnsupportedEncodingException");
        }

        try {
            URLEncoder.encode(HTTP_SERVER_URL, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            Log.e("EncodingException", "UnsupportedEncodingException");
        }
        JsonArrayRequest jsArrRequest = new JsonArrayRequest
                (Request.Method.POST, HTTP_SERVER_URL, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        STATUS_UPDATE_PARSE(response);
                        requestQueue2 = null;
                        updateLoadingBar(view);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        updateLoadingBar(view);
                        Log.i("LOGMETHOD", "call Error");
                        snackbar = Snackbar.make(findViewById(android.R.id.content), "status update Failed", Snackbar.LENGTH_LONG);
                        snackbar.show();
                    }
                }) {

            //This is for Headers If You Needed
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/x-www-form-urlencoded");
                params.put("Authorization", "Bearer " + getFromSP("authtoken"));
                return params;
            }
        };

        if (requestQueue2 == null) {
            requestQueue2 = Volley.newRequestQueue(this);
            requestQueue2.add(jsArrRequest);
        } else {
            requestQueue2.add(jsArrRequest);
        }
    }

    public void STATUS_UPDATE_PARSE(JSONArray array) {

        for (int i = 0; i < array.length(); i++) {

            JSONObject json = null;
            try {
                json = array.getJSONObject(i);
                String success = json.getString("success");
                if (success.equals("true")) {
                    snackbar = Snackbar.make(findViewById(android.R.id.content), "status update Successful", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
                if (success.equals("false")) {
                    snackbar = Snackbar.make(findViewById(android.R.id.content), "status update Failed!", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
            } catch (JSONException e) {

                e.printStackTrace();
            }
        }
    }

    private String getFromSP(String key) {
        SharedPreferences preferences = this.getSharedPreferences("PROJECT_NAME", Context.MODE_PRIVATE);
        return preferences.getString(key, "");
    }
}