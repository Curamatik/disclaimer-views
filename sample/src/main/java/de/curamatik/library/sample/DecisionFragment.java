package de.curamatik.library.sample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import de.curamatik.library.disclaimerviews.DecisionView;

public class DecisionFragment extends Fragment {

    public DecisionFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static de.curamatik.library.sample.DecisionFragment newInstance() {
        de.curamatik.library.sample.DecisionFragment fragment = new de.curamatik.library.sample.DecisionFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_decision, container, false);
        DecisionView decisionView = rootView.findViewById(R.id.decision_view);
        decisionView.setPositiveButtonListener(
                v -> Toast.makeText(getContext(), R.string.decision_view_sample_positive_button_toast,
                                    Toast.LENGTH_SHORT)
                          .show());
        decisionView.setNegativeButtonListener(
                v -> Toast.makeText(getContext(), R.string.decision_view_sample_negative_button_toast,
                                    Toast.LENGTH_SHORT)
                          .show());
        return rootView;
    }

}
