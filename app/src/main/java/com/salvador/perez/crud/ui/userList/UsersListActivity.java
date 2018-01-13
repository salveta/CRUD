package com.salvador.perez.crud.ui.userList;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.salvador.perez.crud.R;
import com.salvador.perez.crud.commons.BaseActivity;
import com.salvador.perez.crud.commons.Event;
import com.salvador.perez.crud.model.User;
import com.salvador.perez.crud.ui.addUser.AddUserActivity;
import com.salvador.perez.crud.ui.userDetail.DetailUserActivity;
import com.salvador.perez.crud.ui.userList.adapter.UserListAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.greenrobot.event.EventBus;


/**
 * Created by Salva on 16/10/2017.
 */

public class UsersListActivity extends BaseActivity implements UsersListContract.view{

    @BindView(R.id.rv_list_users)   RecyclerView rv_list_users;
    @BindView(R.id.rv_empty_text)   RelativeLayout rv_empty_text;
    @BindView(R.id.progressBar)     ProgressBar progressBar;
    @BindView(R.id.fab_add_user)    FloatingActionButton fab_add_user;

    private Unbinder unbinder;
    private UsersListPresenter presenter;
    private UserListAdapter adapter;
    private List<User> mUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
        EventBus.getDefault().register(this);

        presenter = new UsersListPresenter(this, new UsersListModel());

        setRecyclerView();
        presenter.getUsers();

        adapter.setListener((view, position) -> {
            presenter.openDetail(position);
        });
    }

    private void setRecyclerView(){
        rv_list_users.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rv_list_users.setLayoutManager(layoutManager);

        adapter = new UserListAdapter();
        rv_list_users.setAdapter(adapter);
    }

    @Override
    public void onGetUsers(List<User> users) {
        mUser = users;
        adapter.addAll(mUser);
    }

    @Override
    public void openUserDetail(int userId) {
        Intent intent = new Intent(this, DetailUserActivity.class);
        intent.putExtra("userId", userId);
        startActivity(intent);
    }

    @OnClick(R.id.fab_add_user)
    public void addNewUser(){
        Intent intent = new Intent(this, AddUserActivity.class);
        startActivity(intent);
    }

    public void onEvent(Event event) {
        switch (event.type){
            case Event.UPDATE_USER_LIST:
                adapter.addItem(event.getUser());
                break;
            case Event.REMOVE_USER_LIST:
                adapter.removeItem(event.getId());
                break;
        }
    }

    @Override
    public void showLoader() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoader() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showEmptyScreen() {
        rv_empty_text.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }
}
