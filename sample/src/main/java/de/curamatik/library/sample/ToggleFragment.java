package de.curamatik.library.sample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import de.curamatik.library.disclaimerviews.ToggleView;

public class ToggleFragment extends Fragment {
    public ToggleFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static ToggleFragment newInstance() {
        ToggleFragment fragment = new ToggleFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_toggle, container, false);
        ToggleView toggleView = rootView.findViewById(R.id.toggle_view);
        toggleView.setSkipButtonListener(
                v -> Toast.makeText(getContext(), R.string.decision_view_sample_skip_button_toast, Toast.LENGTH_SHORT)
                          .show());
        toggleView.setContinueButtonListener(
                v -> Toast.makeText(getContext(), R.string.decision_view_sample_continue_button_toast,
                                    Toast.LENGTH_SHORT)
                          .show());
        return rootView;
    }

}
