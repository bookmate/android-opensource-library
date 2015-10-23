package com.bookmate.libs.placeholders;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;


public class LoadingView extends LinearLayout {

    ProgressBar spinner;
    TextView loadingText;


    public LoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.LoadingView);
        @LayoutRes int spinnerResId = a.getResourceId(R.styleable.LoadingView_layoutLoadingText, R.layout.view_loading_spinner);
        @LayoutRes int loadingTextResId = a.getResourceId(R.styleable.LoadingView_layoutLoadingText, R.layout.view_loading_text);
        @StringRes final int captionLoadingResId = a.getResourceId(R.styleable.LoadingView_captionLoading, R.string.loading);
        a.recycle();

        spinner = (ProgressBar) inflate(getContext(), spinnerResId, this);
        loadingText = (TextView) inflate(getContext(), loadingTextResId, this);
        loadingText.setText(captionLoadingResId);

        addView(spinner);
        addView(loadingText);
    }

    public LoadingView(Context context) {
        super(context);
    }

    @SuppressWarnings("SameParameterValue")
    public void setLoadingMode(Mode mode) {
        switch (mode) {
            case SPINNER:
                spinner.setVisibility(VISIBLE);
                loadingText.setVisibility(GONE);
                break;
            case TEXT:
                spinner.setVisibility(GONE);
                loadingText.setVisibility(VISIBLE);
                break;
        }
    }

    public void setLoadingTextColor(int textColor) {
        loadingText.setTextColor(textColor);
    }

    enum Mode {
        SPINNER, TEXT
    }
}
