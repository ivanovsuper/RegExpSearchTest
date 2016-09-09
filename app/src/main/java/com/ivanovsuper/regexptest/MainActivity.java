package com.ivanovsuper.regexptest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private EditText mSubstring;
    private TextView mResult;
    private EditText mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSubstring = (EditText) findViewById(R.id.e_substring);
        mResult = (TextView) findViewById(R.id.e_result);
        mText = (EditText) findViewById(R.id.e_text);
        mText.setText(loadFile());
        mText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                updateResult();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        mSubstring.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                updateResult();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void updateResult() {
        String reg = String.format("(.*)(%s)(.*)", mSubstring.getText().toString());
        String[] mf = mText.getText().toString().split("\n");
        StringBuilder sb = new StringBuilder();
        Pattern pattern = Pattern.compile(reg);
        for (String str : mf) {
            if (pattern.matcher(str).matches()) {
                sb.append(str).append("\n");
            }
        }
        mResult.setText(sb.toString());
    }

    private String loadFile() {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(getAssets().open("text.txt")));
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }


}
