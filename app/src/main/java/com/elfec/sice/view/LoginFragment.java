package com.elfec.sice.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.elfec.sice.R;
import com.elfec.sice.helpers.ui.ButtonClicksHelper;
import com.elfec.sice.helpers.ui.KeyboardHelper;
import com.elfec.sice.presenter.LoginPresenter;
import com.elfec.sice.presenter.views.ILoginView;
import com.elfec.sice.view.text.method.MetroPasswordTransformationMethod;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnLoginListener} interface
 * to handle interaction events.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends DialogFragment implements ILoginView {

    private LoginPresenter mPresenter;
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

    /**
     * Click for logIn button
     *
     * @param v view
     */
    @OnClick(R.id.btn_login)
    public void btnLoginClick(View v) {
        if (ButtonClicksHelper.canClickButton()) {
            KeyboardHelper.hideKeyboard(getDialog().getCurrentFocus());
            mPresenter.logIn();
        }
    }

    /**
     * Click for clear errors button
     *
     * @param v view
     */
    @OnClick(R.id.btn_clear_errors)
    public void btnClearErrorsClick(View v) {
        mLayoutErrors.setVisibility(View.GONE);
        mTxtToken.setText(null);
        mLayoutLoginForm.setVisibility(View.VISIBLE);
        mLayoutLoginForm.startAnimation(slideLeftAnim);
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
        mPresenter = new LoginPresenter(this);
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

    private void checkUsername(String username) {
        String error = null;
        if (TextUtils.isEmpty(username)) {
            error = getString(R.string.error_username_empty);
        }
        mInputUsername.setError(error);
    }

    private void checkToken(String token) {
        String error = null;
        if (TextUtils.isEmpty(token)) {
            error = getString(R.string.error_reg_token_empty);
        }
        mInputToken.setError(error);
    }

    //region View Interface methods

    @Override
    public String getUsername() {
        String username = mTxtUsername.getText().toString().trim()
                .toLowerCase(Locale.getDefault());
        checkUsername(username);
        return username;
    }

    @Override
    public String getRegToken() {
        String token = mTxtToken.getText().toString().trim();
        checkToken(token);
        return token;
    }

    @Override
    public void onProcessing(@StringRes int message) {
        if (mLayoutLoading.getVisibility() != View.VISIBLE) {
            mLayoutLoginForm.setVisibility(View.GONE);
            mLayoutLoading.setVisibility(View.VISIBLE);
            mLayoutLoading.startAnimation(slideLeftAnim);
        }
        mTxtWaitingMessage.setText(message);
    }

    @Override
    public void onError(Throwable error) {
        mLayoutLoading.clearAnimation();
        mLayoutLoading.setVisibility(View.GONE);
        mTxtErrorMessage.setText(error.getMessage());
        mLayoutErrors.setVisibility(View.VISIBLE);
        mLayoutErrors.startAnimation(slideLeftAnim);
    }

    @Override
    public void onSuccess(String username) {
        if (mListener != null)
            mListener.onAuthenticated(username);
    }

    //endregion

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
