package com.inha.longstone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kakao.sdk.auth.model.OAuthToken;
import com.kakao.sdk.user.UserApiClient;
import com.kakao.sdk.user.model.User;


import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

public class StartActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private View loginButton, logoutButton;
    private TextView nickname;
    private ImageView profileImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        loginButton = findViewById(R.id.login);
        logoutButton = findViewById(R.id.logout);
        nickname = findViewById(R.id.nickname);
        profileImage = findViewById(R.id.profile);

        Function2<OAuthToken, Throwable, Unit> callback = new Function2<OAuthToken, Throwable, Unit>() {
            @Override
            public Unit invoke(OAuthToken oAuthToken, Throwable throwable) {
                if(oAuthToken != null){
                    // 로그인이 되었을 때
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
                if(throwable != null){
                    Log.w(TAG, "invoke: " + throwable.getLocalizedMessage());
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
                updateKakaoLoginUI();
                return null;
            }
        };

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 카카오톡이 설치 된 경우와 아닌 경우
                // LoginClient api가 적용이 안됨;;
                // 하..  LoginClient가 UserApiClient로 통합되었다네.
                if(UserApiClient.getInstance().isKakaoTalkLoginAvailable(StartActivity.this)){
                    UserApiClient.getInstance().loginWithKakaoTalk(StartActivity.this,callback);
                }
                else{
                    UserApiClient.getInstance().loginWithKakaoAccount(StartActivity.this,callback);
                }
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                UserApiClient.getInstance().logout(new Function1<Throwable, Unit>() {
                    @Override
                    public Unit invoke(Throwable throwable) {
                        updateKakaoLoginUI();
                        return null;
                    }
                });
            }
        });


        updateKakaoLoginUI();
    }

    // 카카오톡 계정으로 로그인이 되어있는지 확인
    // 되어있지 않다면 로그인 버튼을 보이고
    // 되어있다면 프로필 사진과 로그아웃 버튼을 보인다.
    private void updateKakaoLoginUI(){
        UserApiClient.getInstance().me(new Function2<User, Throwable, Unit>() {
            @Override
            public Unit invoke(User user, Throwable throwable) {
                if (user != null){
                    Log.e("1","Log1");
                    nickname.setText(user.getKakaoAccount().getProfile().getNickname());
                    Glide.with(profileImage).load(user.getKakaoAccount().getProfile().getThumbnailImageUrl()).circleCrop().into(profileImage);

                    loginButton.setVisibility(View.GONE);
                    logoutButton.setVisibility(View.VISIBLE);
                } else{
                    Log.e("2","Log2");
                    //nickname.setText(null);
                    //profileImage.setImageBitmap(null);

                    loginButton.setVisibility(View.VISIBLE);
                    logoutButton.setVisibility(View.GONE);
                }
                return null;
            }
        });
    }
}