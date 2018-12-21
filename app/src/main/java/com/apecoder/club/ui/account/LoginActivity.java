package com.apecoder.club.ui.account;

import android.text.TextUtils;

import com.apecoder.apecoder.R;
import com.apecoder.club.MainActivity;
import com.apecoder.club.app.App;
import com.apecoder.club.app.SharedPreferencesConfig;
import com.apecoder.club.base.BaseActivity;
import com.apecoder.club.base.BaseBean;
import com.apecoder.club.bean.UserInfoBean;
import com.apecoder.club.http.ApiManager;
import com.apecoder.club.http.BaseObserver;
import com.apecoder.club.http.HttpUtils;
import com.apecoder.club.util.SharedPreferencesUtil;
import com.apecoder.club.util.ThreadUtils;
import com.apecoder.club.util.ToastUitl;
import com.apecoder.club.widget.ClearEditText;
import com.apecoder.club.widget.SuperButton;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity {

    @BindView(R.id.phone)
    ClearEditText phone;
    @BindView(R.id.password)
    ClearEditText password;
    @BindView(R.id.login_button)
    SuperButton loginButton;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        String mPhone = (String) SharedPreferencesUtil.get(LoginActivity.this, SharedPreferencesConfig.USERNAME, "");
        String mPassword = (String) SharedPreferencesUtil.get(LoginActivity.this, SharedPreferencesConfig.PASSWORD, "");
        phone.setText(mPhone);
        password.setText(mPassword);
    }

    @OnClick(R.id.login_button)
    public void onViewClicked() {
        if (TextUtils.isEmpty(phone.getText().toString())) {
            ToastUitl.showLong("账号不能为空");
            return;
        } else if (TextUtils.isEmpty(password.getText().toString())) {
            ToastUitl.showLong("密码不能为空");
            return;
        }
        login();
    }

    private void login() {
        startProgressDialog();
        Map<String, String> map = new HashMap<>();
        map.put("phone", phone.getText().toString());
        map.put("password", password.getText().toString());
        HttpUtils.APIFunction(ApiManager.mProfileApi.login(map), new BaseObserver<UserInfoBean>() {
            @Override
            protected void onSuccees(BaseBean<UserInfoBean> t) throws Exception {
                if (t.getCode() == 1) {
                    ThreadUtils.runOnSubThread(new Runnable() {
                        @Override
                        public void run() {
                            SharedPreferencesUtil.put(LoginActivity.this, SharedPreferencesConfig.USERNAME, phone.getText().toString());
                            SharedPreferencesUtil.put(LoginActivity.this, SharedPreferencesConfig.PASSWORD, password.getText().toString());
                            Gson gson = new Gson();
                            SharedPreferencesUtil.put(LoginActivity.this, SharedPreferencesConfig.PERSONAL_DATA, gson.toJson(t));
                            //初始化用户信息
                            App.setAccount(t);
                            startActivity(MainActivity.class);
                            finish();
                        }
                    });
                } else {
                    showLongToast(t.getMsg());
                }
            }

            @Override
            public void onComplete() {
                //终止加载框
                stopProgressDialog();
            }
        });
    }

    @Override
    protected void isButterKnife() {
        isUseKnife = true;
    }
}

