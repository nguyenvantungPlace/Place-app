package com.example.nguyenvantung.place.View.User;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nguyenvantung.place.Common.Common;
import com.example.nguyenvantung.place.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment {
    private View view;
    private TextView user_txt_user_name, user_size_post, user_edit_profile;
    private CircleImageView user_img_avatar;


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
        user_img_avatar    = view.findViewById(R.id.user_img_avatar);
        user_txt_user_name = view.findViewById(R.id.user_txt_user_name);
        user_size_post     = view.findViewById(R.id.user_size_post);
        user_edit_profile  = view.findViewById(R.id.user_edit_profile);
    }

    private void initData() {
        Picasso.get().load(Common.BASE_URL_USER_AVATAR_PLACE + Common.USER.getAvatar())
                .resize(350, 350)
                .centerCrop()
                .into(user_img_avatar);

        user_txt_user_name.setText(Common.USER.getTenNguoiDung());
    }

    private void addEvents() {

    }

}
