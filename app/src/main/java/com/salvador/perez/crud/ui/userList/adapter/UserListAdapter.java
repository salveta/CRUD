package com.salvador.perez.crud.ui.userList.adapter;

import android.view.ViewGroup;

import com.salvador.perez.crud.commons.BaseAdapterList;
import com.salvador.perez.crud.commons.BaseListViewHolder;
import com.salvador.perez.crud.R;
import com.salvador.perez.crud.model.User;

import java.util.List;

/**
 * Created by Salva on 09/01/2018.
 */

public class UserListAdapter extends BaseAdapterList<User> {

    @Override
    public BaseListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new UserListViewHolder(inflate(viewGroup, i), itemClickListener);
    }

    @Override
    public void onBindViewHolder(BaseListViewHolder holder, final int position) {
        holder.render(data.get(position));
    }

    @Override
    public User getItem(int position) {
        return data.get(position);
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.item_user;
    }

    @Override
    public List<User> getItems() {
        return data;
    }

    @Override
    public void set(List<User> user) {
        validateCollection(user);
        notifyDataSetChanged();
    }
}
