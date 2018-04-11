package org.andrewgarner.netfun.io.get_skier;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.Log;

import org.andrewgarner.netfun.BuildConfig;
import org.andrewgarner.netfun.model.Skier;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Api for retrieving a {@link Skier}.
 * Created by andrewgarner on 4/10/18.
 */
public class GetSkierApi {

    private static final String LOG_TAG = GetSkierApi.class.getSimpleName();

    private static final String URL_PATH = "/skier";

    public interface Callbacks {
        void onFailure();
        void onSuccess(@NonNull final Skier skier);
    }

    private GetSkierApi() {
        throw new IllegalAccessError("Class is fully static!");
    }

    public static void getSkier(@NonNull final Callbacks callbacks) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url(getUrl()).build();

        Handler handler = new Handler();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull final Call call, @NonNull final IOException e) {
                Log.e(LOG_TAG, "Request failed: " + e);
                handler.post(callbacks::onFailure);
            }

            @Override
            public void onResponse(@NonNull final Call call, @NonNull final Response response) throws IOException {
                Log.d(LOG_TAG, "Response: " + response.toString());
                if (response.isSuccessful()) {
                    ResponseBody body = response.body();
                    if (body != null) {
                        String bodyString = body.string();
                        Log.d(LOG_TAG, "Response Body: " + bodyString);
                        Skier skier = GetSkierApiParser.parse(bodyString);
                        if (skier != null) {
                            Log.d(LOG_TAG, "Parsed Skier: " + skier);
                            handler.post(() -> callbacks.onSuccess(skier));
                            return; //All other paths lead fallthrough to failure.
                        }
                    }
                } else {
                    Log.e(LOG_TAG, "Response unsuccessful.");
                }
                //Catch all failures here
                handler.post(callbacks::onFailure);
            }
        });
    }

    @NonNull
    private static String getUrl() {
        return BuildConfig.HOST + URL_PATH;
    }
}
