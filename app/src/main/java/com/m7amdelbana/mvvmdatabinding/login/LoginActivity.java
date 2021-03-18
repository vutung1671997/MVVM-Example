package com.m7amdelbana.mvvmdatabinding.login;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.m7amdelbana.mvvmdatabinding.R;
import com.m7amdelbana.mvvmdatabinding.databinding.ActivityLoginBinding;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    private ArrayList<User> mUsers;
    private DataAdapter adapter;
    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityLoginBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        LoginViewModel loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        binding.setViewModel(loginViewModel);
        binding.setLifecycleOwner(this);

        mUsers = new ArrayList<>();
        adapter = new DataAdapter(mUsers);
        binding.recyclerviewData.setAdapter(adapter);
        binding.recyclerviewData.setVisibility(View.VISIBLE);
        binding.recyclerviewData.setLayoutManager(new LinearLayoutManager(this));

        loginViewModel.getUser().observe(this, new Observer() {
            @Override
            public void onChanged(@Nullable Object o) {
                User user = (User) o;
                if (user != null)
                    if (user.getEmail().length() > 0 || user.getPassword().length() > 0) {
                        Toast.makeText(getApplicationContext(), "Email : " + user.getEmail() + " Password " + user.getPassword(), Toast.LENGTH_SHORT).show();
                        mUsers.add(user);
                        adapter.setData(mUsers);
                        adapter.notifyDataSetChanged();
                        Log.d("TAG", "onChanged: TungVD: notify ! ");
                    }
            }
        });




    }
}
