package org.andrewgarner.netfun;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.andrewgarner.netfun.io.get_skier.GetSkierApi;
import org.andrewgarner.netfun.model.Skier;

public class MainActivity extends AppCompatActivity {

    private Button mRequestButton;
    private ProgressBar mProgressBar;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRequestButton = findViewById(R.id.make_request_button);
        mRequestButton.setOnClickListener(v -> makeCall());

        mProgressBar = findViewById(R.id.progressBar);

        mTextView = findViewById(R.id.textView);

        setLoading(false);
    }

    private void makeCall() {
        setLoading(true);

        GetSkierApi.getSkier(new GetSkierApi.Callbacks() {
            @Override
            public void onFailure() {
                mTextView.setText(R.string.skier_retrieval_failure);
                setLoading(false);
            }

            @Override
            public void onSuccess(@NonNull final Skier skier) {
                String displayText = getString(R.string.skier_retrieval_success,
                        skier.firstName, skier.lastName);
                mTextView.setText(displayText);
                setLoading(false);
            }
        });
    }

    private void setLoading(boolean isLoading) {
        mProgressBar.setIndeterminate(isLoading);
        mRequestButton.setEnabled(!isLoading);
    }


}
