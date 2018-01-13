package com.salvador.perez.crud.ui.userList.adapter;

import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import com.salvador.perez.crud.R;
import com.salvador.perez.crud.commons.BaseListViewHolder;
import com.salvador.perez.crud.commons.ItemClickListener;
import com.salvador.perez.crud.model.User;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Salva on 09/01/2018.
 */

public class UserListViewHolder extends BaseListViewHolder<User> {

    @BindView(R.id.cv_user)         CardView cv_user;
    @BindView(R.id.tx_name)         TextView tx_name;
    @BindView(R.id.tx_birthday)     TextView tx_birthday;

    @Override
    public void render(User item) {
        tx_name.setText(item.getName());
        tx_birthday.setText(item.getBirthdate().substring(0, item.getBirthdate().indexOf("T")));
    }

    @Override
    public void onClick(View view) {
        if (itemClickListener != null) {
            itemClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    public UserListViewHolder(View itemView, ItemClickListener itemClickListener) {
        super(itemView, itemClickListener);
        ButterKnife.bind(this, itemView);
    }
}
