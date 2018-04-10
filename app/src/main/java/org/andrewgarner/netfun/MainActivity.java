package org.andrewgarner.netfun;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.ProgressBar;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private Button mRequestButton;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRequestButton = findViewById(R.id.make_request_button);
        mRequestButton.setOnClickListener(v -> makeCall());

        mProgressBar = findViewById(R.id.progressBar);

        setLoading(false);
    }

    private void makeCall() {
        OkHttpClient client = new OkHttpClient();

        final Request request = new Request.Builder().url(BuildConfig.HOST + "/hello").build();

        setLoading(true);

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull final Call call, @NonNull final IOException e) {
                Log.e(LOG_TAG, "Request failed: " + e);
                runOnUiThread(() -> setLoading(false));
            }

            @Override
            public void onResponse(@NonNull final Call call, @NonNull final Response response) throws IOException {
                Log.d(LOG_TAG, "Response: " + response.toString());
                if (response.isSuccessful()) {
                    ResponseBody body = response.body();
                    if (body != null) {
                        Log.d(LOG_TAG, "Response Body: " + body.toString());
                    }
                } else {
                    Log.e(LOG_TAG, "Response unsuccessful");
                }
                runOnUiThread(() -> setLoading(false));
            }
        });
    }

    private void setLoading(boolean isLoading) {

        mProgressBar.setIndeterminate(isLoading);
        mRequestButton.setEnabled(!isLoading);
    }


}
