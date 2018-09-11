package com.example.nguyenvantung.place.View.Login.Fragment.PlaceLogin;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nguyenvantung.place.Common.Common;
import com.example.nguyenvantung.place.Model.ObjectModel.UserModel;
import com.example.nguyenvantung.place.Prescenter.Login.PlaceLogin.PrescenterLogicLoginPlace;
import com.example.nguyenvantung.place.R;
import com.example.nguyenvantung.place.View.Main.MainActivity;
import com.example.nguyenvantung.place.View.Register.RegisterActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class PlaceLoginFragment extends Fragment implements View.OnClickListener, ViewPlaceLoginFragment {

    private View view;
    private TextView placelogin_register, placelogin_login;
    private TextInputEditText placelogin_username, placelogin_password;

    private ProgressDialog progressDialog;

    private PrescenterLogicLoginPlace prescenterLogicLoginPlace;
    private UserModel userPlaceModel;

    public PlaceLoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_place_login, container, false);

        prescenterLogicLoginPlace = new PrescenterLogicLoginPlace(this);
        addControls();
        addEvents();
        return view;
    }

    private void addControls() {
        placelogin_register = view.findViewById(R.id.placelogin_register);
        placelogin_username = view.findViewById(R.id.placelogin_username);
        placelogin_password = view.findViewById(R.id.placelogin_password);
        placelogin_login    = view.findViewById(R.id.placelogin_login);
    }

    private void addEvents() {
        placelogin_register.setOnClickListener(this);
        placelogin_login.setOnClickListener(this);
    }

    private void showProgressbarDialog(){
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle(Common.LOGIN);
        progressDialog.setMessage(getResources().getString(R.string.requesting_to_server));
        progressDialog.show();
    }

    private void dimissProgressbarDialog(){
        progressDialog.dismiss();
    }

    @Override
    public void loginSuccess(UserModel userModel) {
        this.userPlaceModel = userPlaceModel;
        Common.USER = userModel;
        Intent iHome = new Intent(getActivity(), MainActivity.class);
        startActivity(iHome);
    }

    @Override
    public void loginFail() {
        placelogin_username.setError(getResources().getString(R.string.login_fail_username));
        placelogin_password.setError(getResources().getString(R.string.login_fail_password));
        dimissProgressbarDialog();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.placelogin_register:
                Intent iRegister = new Intent(getContext(), RegisterActivity.class);
                startActivity(iRegister);
                break;
            case R.id.placelogin_login:
                showProgressbarDialog();
                prescenterLogicLoginPlace.Login(placelogin_username.getText().toString(),
                        placelogin_password.getText().toString());
                break;
        }
    }
}
