package com.salvador.perez.crud.ui.userDetail;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.salvador.perez.crud.R;
import com.salvador.perez.crud.commons.BaseActivity;
import com.salvador.perez.crud.utils.StringUtil;

import org.joda.time.DateTime;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Salva on 13/01/2018.
 */

public class DetailUserActivity extends BaseActivity implements DetailUserContract.view{

    @BindView(R.id.et_til_name)         EditText et_til_name;
    @BindView(R.id.et_birthday)         EditText et_birthday;
    @BindView(R.id.btn_save_new_user)   Button btn_save_new_user;
    @BindView(R.id.btn_delete_user)     Button btn_delete_user;
    @BindView(R.id.btn_update_user)     Button btn_update_user;

    private Unbinder unbinder;
    private DetailUserPresenter presenter;
    private DateTime userBirthday;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        unbinder = ButterKnife.bind(this);

        presenter = new DetailUserPresenter(this, new DetailUserModel());

        getExtras(getIntent().getExtras());
        setView();
    }

    private void getExtras(Bundle bundle){
        userId = bundle.getInt("userId");
        presenter.getUser(userId);
    }

    private void setView(){
        btn_save_new_user.setVisibility(View.GONE);
    }

    @OnClick(R.id.btn_update_user)
    public void updateUser(){

        if(userBirthday == null && StringUtil.isNull(et_birthday.getText().toString())){
            Toast.makeText(this, getString(R.string.error_birthday), Toast.LENGTH_SHORT).show();
            return;
        }

        if(StringUtil.isNull(et_til_name.getText().toString())){
            Toast.makeText(this, getString(R.string.error_name), Toast.LENGTH_SHORT).show();
            return;
        }

        presenter.updateUser(et_til_name.getText().toString(), et_birthday.getText().toString());
    }

    @OnClick(R.id.btn_delete_user)
    public void deleteUser(){
        presenter.deleteUser(userId);
    }

    @Override
    public void deleteUserDone() {
        this.finishAffinity();
    }

    @OnClick(R.id.et_birthday)
    public void getBirthdayDate(){
        Calendar newCalendar = Calendar.getInstance();
        DatePickerDialog fromDatePickerDialog = new DatePickerDialog(this, (view, year, monthOfYear, dayOfMonth) -> {
            et_birthday.setText(String.valueOf(dayOfMonth) + "/" + String.valueOf(monthOfYear + 1) + "/" + String.valueOf(year));
            userBirthday = new DateTime().withDate(year, monthOfYear + 1, dayOfMonth);
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        fromDatePickerDialog.show();
    }

    @Override
    public void setUserName(String name) {
        et_til_name.setText(name);
    }

    @Override
    public void setUserBirthday(String birthday) {
        et_birthday.setText(birthday);
    }

    @Override
    public void showErrorMethodNotAllowed() {
        Toast.makeText(this, getString(R.string.common_error), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void errorLoadUser() {
        Toast.makeText(this, getString(R.string.common_error), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
