package com.salvador.perez.crud.ui.addUser;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.salvador.perez.crud.R;
import com.salvador.perez.crud.commons.BaseActivity;

import org.joda.time.DateTime;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Salva on 12/01/2018.
 */

public class AddUserActivity extends BaseActivity implements AddUserContract.view {

    @BindView(R.id.et_til_name)         EditText et_til_name;
    @BindView(R.id.et_birthday)         EditText et_birthday;
    @BindView(R.id.btn_save_new_user)   Button btn_save_new_user;
    @BindView(R.id.ly_update_delete)    LinearLayout ly_update_delete;

    private Unbinder unbinder;
    private AddUserPresenter presenter;
    private DateTime userBithday;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        unbinder = ButterKnife.bind(this);

        setView();
        presenter = new AddUserPresenter(this, new AddUserModel());
    }

    private void setView(){
        ly_update_delete.setVisibility(View.GONE);
    }

    @OnClick(R.id.et_birthday)
    public void getBirthdayDate(){
        Calendar newCalendar = Calendar.getInstance();
        DatePickerDialog fromDatePickerDialog = new DatePickerDialog(this, (view, year, monthOfYear, dayOfMonth) -> {
            et_birthday.setText(String.valueOf(dayOfMonth) + "/" + String.valueOf(monthOfYear + 1) + "/" + String.valueOf(year));
            userBithday = new DateTime().withDate(year, monthOfYear + 1, dayOfMonth);
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        fromDatePickerDialog.show();
    }

    @OnClick(R.id.btn_save_new_user)
    public void saveNewUser(){
        presenter.saveUser(et_til_name.getText().toString(), userBithday);
    }

    @Override
    public void saveUserSuccess() {
        finishAffinity();
    }

    @Override
    public void showErrorBirthday() {
        Toast.makeText(this, getString(R.string.error_birthday), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorUserName() {
        Toast.makeText(this, getString(R.string.error_name), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorCreateUser() {
        Toast.makeText(this, getString(R.string.error_create_user), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorCode() {
        Toast.makeText(this, getString(R.string.common_error), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
