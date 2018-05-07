package de.curamatik.library.disclaimerviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.text.Html;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import de.curamatik.library.disclaimerviews.util.BottomAwareScrollView;

/**
 * The type Toggle view.
 */
public class ToggleView extends ConstraintLayout {

    private String toggleOnText;
    private String toggleOffText;
    private ImageView scrollIndicator;
    private View scrollIndicatorBackground;
    private boolean interactionEnabled;

    /**
     * Instantiates a new Toggle view.
     *
     * @param context the context
     */
    public ToggleView(Context context) {
        super(context);
        initView(null);
    }

    private void initView(@Nullable AttributeSet attrs) {
        LayoutInflater.from(getContext())
                      .inflate(R.layout.view_toggle, this, true);
        setToggleClickListener();
        extractAttributes(attrs);
    }

    private void setToggleClickListener() {
        findViewById(R.id.toggle_click_overlay).setOnClickListener(v -> {
            Switch toggleControl = findViewById(R.id.toggle_control);
            toggleControl.toggle();
            boolean toggleState = toggleControl.isChecked();
            setToggleButtonText(toggleState);
            setNavButtonState(toggleState);
        });
    }

    private void extractAttributes(@Nullable AttributeSet attrs) {
        TypedArray a = getContext().getTheme()
                                   .obtainStyledAttributes(attrs, R.styleable.ToggleView, 0, 0);

        try {
            setDisclaimerTitle(a.getString(R.styleable.ToggleView_pv_title));
            setDisclaimerBody(a.getString(R.styleable.ToggleView_pv_body));
            setSkipButtonText(a.getString(R.styleable.ToggleView_pv_skip_button_text));
            setContinueButtonText(a.getString(R.styleable.ToggleView_pv_continue_button_text));
            interactionEnabled = a.getBoolean(R.styleable.ToggleView_pv_interaction_enabled, true);
            setInteractionEnabled(interactionEnabled);
            toggleOffText = a.getString(R.styleable.ToggleView_pv_toggle_off_text);
            setToggleOffText(toggleOffText);
            toggleOnText = a.getString(R.styleable.ToggleView_pv_toggle_on_text);
            setToggleOnText(toggleOnText);
            setBottomIndicatorEnabled(a.getBoolean(R.styleable.ToggleView_pv_indicator_enabled, true));
        } finally {
            a.recycle();
        }
    }

    private void setToggleButtonText(boolean checked) {
        TextView toggleText = findViewById(R.id.toggle_text);
        if (checked) {
            setHtmlText(toggleText, toggleOnText);
        } else {
            setHtmlText(toggleText, toggleOffText);
        }
    }

    private void setNavButtonState(boolean continueButtonVisible) {
        if (interactionEnabled) {
            if (continueButtonVisible) {
                findViewById(R.id.skip_button).setVisibility(INVISIBLE);
                findViewById(R.id.continue_button).setVisibility(VISIBLE);
            } else {
                findViewById(R.id.continue_button).setVisibility(INVISIBLE);
                findViewById(R.id.skip_button).setVisibility(VISIBLE);
            }
        }
    }

    /**
     * Sets disclaimer title text. When null is passed, the title textfield is hidden
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
     * Sets disclaimer body text.
     *
     * @param disclaimerBody the disclaimer body
     */
    public void setDisclaimerBody(@Nullable String disclaimerBody) {
        TextView bodyView = findViewById(R.id.body);
        if (disclaimerBody != null) {
            setHtmlText(bodyView, disclaimerBody);
        } else {
            bodyView.setText(null);
        }
    }

    /**
     * Sets skip button text. When null is passed the button text is set to "Skip"
     *
     * @param skipButtonText the skip button text
     */
    public void setSkipButtonText(@Nullable String skipButtonText) {
        Button skipButton = findViewById(R.id.skip_button);
        if (skipButtonText != null) {
            skipButton.setText(skipButtonText);
        } else {
            skipButton.setText(getResources().getString(R.string.toggle_view_skip_button_default));
        }
    }

    /**
     * Sets continue button text. When null is passed the button text is set to "Continue"
     *
     * @param continueButtonText the continue button text
     */
    public void setContinueButtonText(@Nullable String continueButtonText) {
        Button continueButton = findViewById(R.id.continue_button);
        if (continueButtonText != null) {
            continueButton.setText(continueButtonText);
        } else {
            continueButton.setText(getResources().getString(R.string.toggle_view_continue_button_default));
        }
    }

    /**
     * Sets visibility status of the navigational buttons (skip and continue buttons)
     *
     * @param interactionEnabled show the navigational buttons when true, hides them when false
     */
    public void setInteractionEnabled(boolean interactionEnabled) {
        if (interactionEnabled) {
            setNavButtonState(((Switch) findViewById(R.id.toggle_control)).isChecked());
            findViewById(R.id.divider_toggle_bottom).setVisibility(VISIBLE);
        } else {
            findViewById(R.id.skip_button).setVisibility(GONE);
            findViewById(R.id.continue_button).setVisibility(GONE);
            findViewById(R.id.divider_toggle_bottom).setVisibility(INVISIBLE);

        }
    }

    /**
     * Sets the text, which is shown when the toggle is set to OFF
     *
     * @param toggleOffText toggle state OFF text
     */
    public void setToggleOffText(@Nullable String toggleOffText) {
        if (toggleOffText != null) {
            this.toggleOffText = toggleOffText;
        } else {
            this.toggleOffText = getResources().getString(R.string.disclaimer_view_toggle_off_default);
        }
        TextView toggleText = findViewById(R.id.toggle_text);
        if (!((Switch) this.findViewById(R.id.toggle_control)).isChecked()) {
            setHtmlText(toggleText, this.toggleOffText);
        }
    }

    /**
     * Sets the text, which is shown when the toggle is set to ON
     *
     * @param toggleOnText toggle state ON text
     */
    public void setToggleOnText(@Nullable String toggleOnText) {
        if (toggleOnText != null) {
            this.toggleOnText = toggleOnText;
        } else {
            this.toggleOnText = getResources().getString(R.string.disclaimer_view_toggle_on_default);
        }
        TextView toggleText = findViewById(R.id.toggle_text);
        if (((Switch) this.findViewById(R.id.toggle_control)).isChecked()) {
            setHtmlText(toggleText, this.toggleOnText);
        }
    }

    /**
     * Sets bottom indicator for the scroll view. The default behaviour of the indicator shows whether the scrollview
     * is scrollable or not. When the user has reached the bottom of the scrollview the indicator gets hidden
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

    private void setHtmlText(@NonNull TextView toggleText, @NonNull String text) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            toggleText.setText(Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY));
        } else {
            toggleText.setText(Html.fromHtml(text));
        }
    }

    private void setIndicatorVisibility(int visibility) {
        scrollIndicator.setVisibility(visibility);
        scrollIndicatorBackground.setVisibility(visibility);
    }

    /**
     * Instantiates a new Toggle view.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    public ToggleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(attrs);
    }

    /**
     * Instantiates a new Toggle view.
     *
     * @param context      the context
     * @param attrs        the attrs
     * @param defStyleAttr the def style attr
     */
    public ToggleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(attrs);
    }

    /**
     * Sets skip button listener.
     *
     * @param positiveButtonListener the positive button listener
     */
    public void setSkipButtonListener(@Nullable OnClickListener positiveButtonListener) {
        findViewById(R.id.skip_button).setOnClickListener(positiveButtonListener);
    }

    /**
     * Sets continue button listener.
     *
     * @param positiveButtonListener the positive button listener
     */
    public void setContinueButtonListener(@Nullable OnClickListener positiveButtonListener) {
        findViewById(R.id.continue_button).setOnClickListener(positiveButtonListener);
    }

}
