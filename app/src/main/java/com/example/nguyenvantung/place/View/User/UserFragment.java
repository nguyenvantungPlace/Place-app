package com.example.nguyenvantung.place.View.User;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nguyenvantung.place.Adapter.User.AdapterListPlace;
import com.example.nguyenvantung.place.Adapter.User.UserPostAdapter;
import com.example.nguyenvantung.place.Common.Common;
import com.example.nguyenvantung.place.Model.ObjectClass.LoadMore;
import com.example.nguyenvantung.place.Model.ObjectModel.NewfeedModel;
import com.example.nguyenvantung.place.Model.ObjectModel.PlaceModel;
import com.example.nguyenvantung.place.Prescenter.User.PrescenterLogicUser;
import com.example.nguyenvantung.place.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment implements ViewUserFragment, View.OnClickListener {
    private View view;
    private TextView user_txt_user_name, user_size_post, user_edit_profile, user_txt_controll_place;
    private CircleImageView user_img_avatar;
    private RecyclerView user_recyclerview, user_rv_place;

    private PrescenterLogicUser prescenterLogicUser;
    private List<NewfeedModel> newfeedList;
    private UserPostAdapter userPostAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private int limit = 0;

    private AdapterListPlace adapterListPlace;
    private List<PlaceModel> listPlace;
    private LinearLayoutManager linearLayoutManagerRVPlace;


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
        user_txt_controll_place = view.findViewById(R.id.user_txt_controll_place);
        user_rv_place      = view.findViewById(R.id.user_rv_place);

        //init recyclerview
        newfeedList = new ArrayList<>();
        userPostAdapter = new UserPostAdapter(newfeedList);
        layoutManager = new GridLayoutManager(getContext(), 3);
        user_recyclerview.setLayoutManager(layoutManager);
        user_recyclerview.setAdapter(userPostAdapter);

        //fix hiện tượng scroll recyclerview bị chậm khi đặt trong nestedScroll
        ViewCompat.setNestedScrollingEnabled(user_recyclerview, false);


        //check xem người dùng có quản lí địa điểm nào k, nếu có thì ẩn nút quản lí đi
        prescenterLogicUser.checkPlaceInIDUser(Common.USER.getIdNguoiDung());
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

        //check người dùng có địa chỉ để quản lí không

    }

    private void addEvents() {
        user_recyclerview.addOnScrollListener(new LoadMore(layoutManager, this));
    }

    // khi bên prescenter lấy dữ liệu thành công thì nó sẽ gọi hàm này để add data vào list
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

    private void initRecyclerViewPlace() {
        listPlace = new ArrayList<>();
        prescenterLogicUser.getDataPlace(Common.USER.getIdNguoiDung(), 0); //lay du lieu ben prescenter
        adapterListPlace = new AdapterListPlace(listPlace);
        linearLayoutManagerRVPlace = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        user_rv_place.setLayoutManager(linearLayoutManagerRVPlace);
        user_rv_place.setHasFixedSize(true);
        user_rv_place.setAdapter(adapterListPlace);
    }

    @Override
    public void checkPlaceTrue() {
        user_txt_controll_place.setVisibility(View.GONE);
        user_rv_place.setVisibility(View.VISIBLE);
        initRecyclerViewPlace();
    }

    @Override
    public void checkPlaceFalse() {
        user_rv_place.setVisibility(View.GONE);
        user_txt_controll_place.setVisibility(View.VISIBLE);
    }

    @Override
    public void getDataPlaceSuccess(List<PlaceModel> data) {
        listPlace.addAll(data);
        Toast.makeText(getContext(), data.get(0).getTenDiaDiem(), Toast.LENGTH_SHORT).show();
        adapterListPlace.notifyDataSetChanged();
    }

    @Override
    public void getDtaPlaceFail() {
        Toast.makeText(getContext(), "Get data Fail, Please check internet", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {

    }
}
