package de.curamatik.library.disclaimerviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import de.curamatik.library.disclaimerviews.util.BottomAwareScrollView;

/**
 * The type Decision view.
 */
public class DecisionView extends ConstraintLayout {


    private ImageView scrollIndicator;
    private View scrollIndicatorBackground;

    /**
     * Instantiates a new Decision view.
     *
     * @param context the context
     */
    public DecisionView(Context context) {
        super(context);
        initView(null);
    }

    private void initView(@Nullable AttributeSet attrs) {
        LayoutInflater.from(getContext())
                      .inflate(R.layout.view_decision, this, true);
        extractAttributes(attrs);
    }

    private void extractAttributes(@Nullable AttributeSet attrs) {
        TypedArray a = getContext().getTheme()
                                   .obtainStyledAttributes(attrs, R.styleable.DecisionView, 0, 0);

        try {
            setDisclaimerTitle(a.getString(R.styleable.DecisionView_pv_title));
            setDisclaimerBody(a.getString(R.styleable.DecisionView_pv_body));
            setNegativeButtonText(a.getString(R.styleable.DecisionView_pv_negative_button_text));
            setPositiveButtonText(a.getString(R.styleable.DecisionView_pv_positive_button_text));
            setInteractionEnabled(a.getBoolean(R.styleable.DecisionView_pv_interaction_enabled, true));
            setBottomIndicatorEnabled(a.getBoolean(R.styleable.DecisionView_pv_indicator_enabled, true));
        } finally {
            a.recycle();
        }
    }

    /**
     * Sets disclaimer title.
     *
     * @param disclaimerTitle the disclaimer title
     */
    public void setDisclaimerTitle(@Nullable String disclaimerTitle) {
        TextView titleView = findViewById(R.id.title);
        if (disclaimerTitle == null) {
            titleView.setVisibility(GONE);
        } else {
            titleView.setText(disclaimerTitle);
            titleView.setVisibility(VISIBLE);
        }
    }

    /**
     * Sets disclaimer body.
     *
     * @param disclaimerBody the disclaimer body
     */
    public void setDisclaimerBody(@Nullable String disclaimerBody) {
        TextView bodyView = findViewById(R.id.body);
        bodyView.setText(disclaimerBody);
    }

    /**
     * Sets negative button text. When null is passed the text is set to "Decline".
     *
     * @param negativeButtonText the negative button text
     */
    public void setNegativeButtonText(@Nullable String negativeButtonText) {
        Button button = findViewById(R.id.negative_button);
        if (negativeButtonText != null) {
            button.setText(negativeButtonText);
        } else {
            button.setText(getResources().getString(R.string.disclaimer_view_negative_button_default));
        }
    }

    /**
     * Sets positive button text. When null is passed the text is set to "Accept".
     *
     * @param positiveButtonText the positive button text
     */
    public void setPositiveButtonText(@Nullable String positiveButtonText) {
        Button button = findViewById(R.id.positive_button);
        if (positiveButtonText != null) {
            button.setText(positiveButtonText);
        } else {
            button.setText(getResources().getString(R.string.disclaimer_view_positive_button_default));
        }
    }

    /**
     * Sets visibility status of the navigational buttons (positive and negative buttons)
     *
     * @param interactionEnabled show the navigational buttons when true, hides them when false
     */
    public void setInteractionEnabled(boolean interactionEnabled) {
        if (interactionEnabled) {
            findViewById(R.id.positive_button).setVisibility(VISIBLE);
            findViewById(R.id.negative_button).setVisibility(VISIBLE);
        } else {
            findViewById(R.id.positive_button).setVisibility(GONE);
            findViewById(R.id.negative_button).setVisibility(GONE);
        }
    }

    /**
     * Sets bottom indicator for the scroll view. The default behaviour of the indicator shows whether the scrollview
     * is scrollable or not. When the user has reached the bottom of the scrollview the indicator gets hidden.
     *
     * @param enabled whether the indicator should be shown or not
     */
    public void setBottomIndicatorEnabled(boolean enabled) {
        BottomAwareScrollView scrollView = findViewById(R.id.scroll_view);
        scrollIndicator = findViewById(R.id.scroll_indicator_img);
        scrollIndicatorBackground = findViewById(R.id.scroll_indicator_background);
        if (enabled) {
            scrollView.setOnBottomReachedListener(() -> setIndicatorVisibility(INVISIBLE));
            scrollView.setScrollFromBottomListener(() -> setIndicatorVisibility(VISIBLE));
        } else {
            scrollIndicator.setVisibility(INVISIBLE);
            scrollIndicatorBackground.setVisibility(INVISIBLE);
        }
    }

    private void setIndicatorVisibility(int visibility) {
        scrollIndicator.setVisibility(visibility);
        scrollIndicatorBackground.setVisibility(visibility);
    }

    /**
     * Instantiates a new Decision view.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    public DecisionView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(attrs);
    }

    /**
     * Instantiates a new Decision view.
     *
     * @param context      the context
     * @param attrs        the attrs
     * @param defStyleAttr the def style attr
     */
    public DecisionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(attrs);
    }

    /**
     * Sets positive button listener.
     *
     * @param positiveButtonListener the positive button listener
     */
    public void setPositiveButtonListener(@Nullable OnClickListener positiveButtonListener) {
        findViewById(R.id.positive_button).setOnClickListener(positiveButtonListener);
    }

    /**
     * Sets negative button listener.
     *
     * @param negativeButtonListener the negative button listener
     */
    public void setNegativeButtonListener(@Nullable OnClickListener negativeButtonListener) {
        findViewById(R.id.negative_button).setOnClickListener(negativeButtonListener);
    }
}
