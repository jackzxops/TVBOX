package com.fongmi.android.tv.ui.activity;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.fongmi.android.tv.R;
import com.fongmi.android.tv.Setting;

/**
 * 数据源过期锁定界面 - 丞相添加
 * 公众号：博博哥哥爱搞笑
 */
public class LockActivity extends Activity {

    private TextView tvRemainingDays;
    private TextView tvExpireMessage;
    private TextView tvContactInfo;

    // 公众号名称
    private static final String WECHAT_PUBLIC_ACCOUNT = "博博哥哥爱搞笑";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock);
        
        initViews();
        updateUI();
    }

    private void initViews() {
        tvRemainingDays = findViewById(R.id.tv_remaining_days);
        tvExpireMessage = findViewById(R.id.tv_expire_message);
        tvContactInfo = findViewById(R.id.tv_contact_info);
    }

    private void updateUI() {
        int remainingDays = Setting.getRemainingDays();
        String accountGuide = "关注公众号【" + WECHAT_PUBLIC_ACCOUNT + "】，回复“更新”获取最新数据源";
        
        if (remainingDays <= 0) {
            tvRemainingDays.setText("配置已过期");
            tvExpireMessage.setText("当前数据源配置已超过有效期，请更新后继续使用");
        } else {
            tvRemainingDays.setText("剩余 " + remainingDays + " 天");
            tvExpireMessage.setText("数据源配置即将到期，建议提前获取最新配置");
        }
        
        tvContactInfo.setText(accountGuide);
    }

    /**
     * 刷新状态按钮点击
     */
    public void onRefreshClick(View view) {
        if (!Setting.isSourceExpired()) {
            finish(); // 如果未过期，关闭锁定界面
        } else {
            Toast.makeText(this, "配置仍已过期，请先获取并导入最新数据源", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 复制公众号名称
     */
    public void copyAccount(View view) {
        ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData data = ClipData.newPlainText("公众号", WECHAT_PUBLIC_ACCOUNT);
        cm.setPrimaryClip(data);
        Toast.makeText(this, "已复制公众号名称", Toast.LENGTH_SHORT).show();
    }

    /**
     * 打开微信
     */
    public void openWeChat(View view) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("weixin://"));
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, "未检测到微信", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 每次返回时重新检查状态
        if (!Setting.isSourceExpired()) {
            finish(); // 如果未过期，关闭锁定界面
        }
    }
}
