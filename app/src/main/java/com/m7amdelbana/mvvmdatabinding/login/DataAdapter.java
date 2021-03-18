package com.m7amdelbana.mvvmdatabinding.login;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.m7amdelbana.mvvmdatabinding.BR;
import com.m7amdelbana.mvvmdatabinding.R;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.CustomViewHolder>{

    private List<User> mUsers;

    public DataAdapter(List<User> mUsers) {
        this.mUsers = mUsers;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.layout_item_user,parent,false);
        return new CustomViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        ViewDataBinding viewDataBinding = holder.getViewDataBinding();
        viewDataBinding.setVariable(BR.user,mUsers.get(position));
    }

    @Override
    public int getItemCount() {
        return (null != mUsers ? mUsers.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder{
        private ViewDataBinding mViewDataBinding;


        public CustomViewHolder(@NonNull ViewDataBinding viewDataBinding) {
            super(viewDataBinding.getRoot());
            mViewDataBinding = viewDataBinding;
            mViewDataBinding.executePendingBindings();
        }

        public ViewDataBinding getViewDataBinding() {
            return mViewDataBinding;
        }
    }

    public void setData(List<User> mUsers) {
        this.mUsers = mUsers;
    }
}
