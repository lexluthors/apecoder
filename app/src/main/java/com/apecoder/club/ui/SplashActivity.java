package com.apecoder.club.ui;

import android.os.CountDownTimer;
import android.text.TextUtils;

import com.apecoder.apecoder.R;
import com.apecoder.club.MainActivity;
import com.apecoder.club.app.SharedPreferencesConfig;
import com.apecoder.club.base.BaseActivity;
import com.apecoder.club.ui.account.LoginActivity;
import com.apecoder.club.util.SharedPreferencesUtil;
import com.apecoder.club.util.ThreadUtils;

public class SplashActivity extends BaseActivity {

    private CountDownTimer mDownTimer;

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        mDownTimer = new CountDownTimer(1 * 1000, 1000) {
            @Override
            public void onTick(long l) {
            }

            @Override
            public void onFinish() {
                ThreadUtils.runOnSubThread(new Runnable() {
                    @Override
                    public void run() {
                        String accountString = (String) SharedPreferencesUtil.get(SplashActivity.this, SharedPreferencesConfig.PERSONAL_DATA, "");
                        if (TextUtils.isEmpty(accountString)) {
                            startActivity(LoginActivity.class);
                        } else {
                            startActivity(MainActivity.class);
                        }
                        finish();
                    }
                });
            }
        };
        mDownTimer.start();
    }
}
