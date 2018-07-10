package com.example.nguyenvantung.place.View.User;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nguyenvantung.place.Adapter.User.UserPostAdapter;
import com.example.nguyenvantung.place.Common.Common;
import com.example.nguyenvantung.place.Model.ObjectClass.LoadMore;
import com.example.nguyenvantung.place.Model.ObjectModel.NewfeedModel;
import com.example.nguyenvantung.place.Prescenter.User.PrescenterLogicUser;
import com.example.nguyenvantung.place.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment implements ViewUserFragment {
    private View view;
    private TextView user_txt_user_name, user_size_post, user_edit_profile;
    private CircleImageView user_img_avatar;
    private RecyclerView user_recyclerview;

    private PrescenterLogicUser prescenterLogicUser;
    private List<NewfeedModel> newfeedList;
    private UserPostAdapter userPostAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private int limit = 0;


    public UserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_user, container, false);
        addControlls();
        initData();
        addEvents();
        return view;
    }

    private void addControlls() {
        prescenterLogicUser = new PrescenterLogicUser(this);

        user_img_avatar    = view.findViewById(R.id.user_img_avatar);
        user_txt_user_name = view.findViewById(R.id.user_txt_user_name);
        user_size_post     = view.findViewById(R.id.user_size_post);
        user_edit_profile  = view.findViewById(R.id.user_edit_profile);
        user_recyclerview  = view.findViewById(R.id.user_recyclerview);

        //init recyclerview
        newfeedList = new ArrayList<>();
        userPostAdapter = new UserPostAdapter(newfeedList);
        layoutManager = new GridLayoutManager(getContext(), 3);
        user_recyclerview.setLayoutManager(layoutManager);
        user_recyclerview.setAdapter(userPostAdapter);
    }

    private void initData() {
        //init data info user
        Picasso.get().load(Common.BASE_URL_USER_AVATAR_PLACE + Common.USER.getAvatar())
                .resize(350, 350)
                .centerCrop()
                .into(user_img_avatar);
        user_txt_user_name.setText(Common.USER.getTenNguoiDung());

        //init data recyclerview
        prescenterLogicUser.getData(limit);
    }

    private void addEvents() {
        user_recyclerview.addOnScrollListener(new LoadMore(layoutManager, this));
    }

    @Override
    public void addDataToRecyclerView(List<NewfeedModel> list) {
        newfeedList.addAll(list);
//        Log.d("kiemtra", newfeedList.get(1).getAnh());
        userPostAdapter.notifyDataSetChanged();
    }

    @Override
    public void addDataLoadMore() {
        limit += 15;
        prescenterLogicUser.getData(limit);
    }
}
