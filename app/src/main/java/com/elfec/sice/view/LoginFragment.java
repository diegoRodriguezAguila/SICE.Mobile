package com.elfec.sice.view;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.elfec.sice.R;
import com.elfec.sice.view.text.method.MetroPasswordTransformationMethod;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnLoginListener} interface
 * to handle interaction events.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends DialogFragment {

    private OnLoginListener mListener;

    @BindView(R.id.input_username)
    protected TextInputLayout mInputUsername;
    @BindView(R.id.txt_username)
    protected EditText mTxtUsername;
    @BindView(R.id.input_token)
    protected TextInputLayout mInputToken;
    @BindView(R.id.txt_token)
    protected EditText mTxtToken;
    @BindView(R.id.layout_login_form)
    protected LinearLayout mLayoutLoginForm;
    @BindView(R.id.layout_loading)
    protected LinearLayout mLayoutLoading;
    @BindView(R.id.txt_waiting_message)
    protected TextView mTxtWaitingMessage;
    @BindView(R.id.layout_errors)
    protected LinearLayout mLayoutErrors;
    @BindView(R.id.txt_error_message)
    protected TextView mTxtErrorMessage;
    private Animation slideLeftAnim;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment LoginFragment.
     */
    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onAuthenticated(null);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnLoginListener) {
            mListener = (OnLoginListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnLoginListener");
        }
    }

    @Override
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_login, null, false);
        ButterKnife.bind(this, view);
        slideLeftAnim = AnimationUtils.loadAnimation(getContext(), R.anim.slide_left_in);
        mTxtToken.setTransformationMethod(MetroPasswordTransformationMethod.getInstance());
        return new AlertDialog.Builder(getActivity(), R.style.Theme_Elfec_Sice_AlertDialog)
                .setTitle(R.string.title_login)
                .setView(view)
                .create();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     **/
    public interface OnLoginListener {
        void onAuthenticated(String companyName);
    }
}
