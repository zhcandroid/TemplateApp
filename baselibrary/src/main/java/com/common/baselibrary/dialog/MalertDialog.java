package com.common.baselibrary.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.baselibrary.R;

/**
 * 系统风格的dialog
 */
public class MalertDialog {
    Context context;
    android.app.AlertDialog ad;
    TextView titleView;
    TextView messageView;
    LinearLayout buttonLayout;

    public MalertDialog(Context context) {
        // TODO Auto-generated constructor stub
        this.context = context;
        ad = new android.app.AlertDialog.Builder(context, R.style.Dialog_Transparent).create();
        ad.show();
        //关键在下面的两行,使用window.setContentView,替换整个对话框窗口的布局
        Window window = ad.getWindow();
        window.setContentView(R.layout.alertdialog);
        titleView = (TextView) window.findViewById(R.id.title);
        messageView = (TextView) window.findViewById(R.id.message);
        buttonLayout = (LinearLayout) window.findViewById(R.id.buttonLayout);
    }

    public void setTitle(int resId) {
        titleView.setText(resId);
    }

    public void setTitle(String title) {
        titleView.setText(title);
    }

    public void setMessage(int resId) {
        messageView.setText(resId);
    }

    public void setMessageGravity(int gravity) {
        messageView.setGravity(gravity);
    }

    public void setMessage(String message) {
        messageView.setText(message);
    }

    /**
     * 设置按钮
     *
     * @param text
     * @param listener
     */
    public void setPositiveButton(String text, final View.OnClickListener listener) {
        TextView textView = new TextView(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
        textView.setLayoutParams(params);
        textView.setGravity(Gravity.CENTER);
        textView.setText(text);
        textView.setTextColor(context.getResources().getColorStateList(R.color.main_back));
        textView.setTextSize(16);
        textView.setOnClickListener(listener);
        buttonLayout.addView(textView);
    }

    /**
     * 设置按钮
     *
     * @param text
     * @param listener
     */
    public void setNegativeButton(String text, final View.OnClickListener listener) {
        TextView textView = new TextView(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
        textView.setLayoutParams(params);
        textView.setGravity(Gravity.CENTER);
        textView.setText(text);
        textView.setTextColor(context.getResources().getColorStateList(R.color.main_back));
        textView.setTextSize(16);
        textView.setOnClickListener(listener);
        if (buttonLayout.getChildCount() > 0) {
            params.setMargins(20, 0, 0, 0);
            textView.setLayoutParams(params);
            buttonLayout.addView(textView, 1);
        } else {
            textView.setLayoutParams(params);
            buttonLayout.addView(textView);
        }

    }

    /**
     * 关闭对话框
     */
    public void dismiss() {
        ad.dismiss();
    }

}
