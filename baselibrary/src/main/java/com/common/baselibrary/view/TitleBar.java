package com.common.baselibrary.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.RequiresApi;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.baselibrary.R;
import com.common.baselibrary.interf.OnTitleBarListener;
import com.common.baselibrary.utils.TDevice;
import com.common.baselibrary.utils.UiUtil;


/**
 * @auther zhc
 * 描述： 目前支持左边图标 中间title 右边图标/文字
 * 其他标题样式请自定义布局
 */
public class TitleBar extends FrameLayout implements View.OnClickListener {
    private static int EXT_PADDING_TOP;
    private TextView mTitle;
    private TextView mTvRight;
    private TextView mDot;
    private ImageView mIcon;
    private ImageView mIconLeft;

    private OnTitleBarListener mOnTitleBarListener;

    public TitleBar(Context context) {
        super(context);
        init(null, 0, 0);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0, 0);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr, 0);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs, defStyleAttr, defStyleRes);
    }


    @SuppressLint("NewApi")
    private void init(AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        Context context = getContext();

        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.lay_title_bar, this, true);

        mTitle = (TextView) findViewById(R.id.tv_title);
        mDot = (TextView) findViewById(R.id.tv_dot_num);
        mIcon = (ImageView) findViewById(R.id.iv_icon);
        mIconLeft = (ImageView) findViewById(R.id.iv_icon_left);
        mTvRight = findViewById(R.id.tv_right);

        if (attrs != null) {
            // Load attributes
            final TypedArray a = context.obtainStyledAttributes(
                    attrs, R.styleable.TitleBar, defStyleAttr, defStyleRes);

            String title = a.getString(R.styleable.TitleBar_aTitle);
            String rightTitle = a.getString(R.styleable.TitleBar_aRight_text);
            Drawable drawable = a.getDrawable(R.styleable.TitleBar_aIcon);
            Drawable drawable_left = a.getDrawable(R.styleable.TitleBar_aIconleft);

            a.recycle();

            mTitle.setText(title);
            mIcon.setImageDrawable(drawable);
            mIconLeft.setBackground(drawable_left);
            mTvRight.setText(rightTitle);

            //如果右侧设置了文本标题 就不能显示icon了
            if (!TextUtils.isEmpty(rightTitle)) {
                mTvRight.setVisibility(View.VISIBLE);
                mIcon.setVisibility(GONE);
            }


        } else {
            mIcon.setVisibility(GONE);
            mIconLeft.setVisibility(GONE);
            mTvRight.setVisibility(GONE);
        }

        // Set Background
       // setBackgroundColor(getResources().getColor(R.color.white));

        // Init padding
//        setPadding(getLeft(), getTop() + UiUtil.getStatusBarHeight(getContext()), getRight(), getBottom());
        setPadding(getLeft(), getTop(), getRight(), getBottom());
    }

    private void addListener() {
        mTitle.setOnClickListener(this);
        mIconLeft.setOnClickListener(this);
        mIcon.setOnClickListener(this);
        mTvRight.setOnClickListener(this);

    }

    /**
     * 设置标题
     *
     * @param titleRes
     */
    @SuppressLint("ResourceType")
    public void setTitle(@StringRes int titleRes) {
        if (titleRes <= 0)
            return;
        mTitle.setText(titleRes);
    }

    /**
     * 设置右侧文字
     *
     * @param titleRes
     */
    @SuppressLint("ResourceType")
    public void setTitleRight(@StringRes int titleRes) {
        if (titleRes <= 0)
            return;
        mTvRight.setText(titleRes);
        mIcon.setVisibility(View.GONE);
    }

    /**
     * 设置右侧图标
     *
     * @param iconRes
     */
    @SuppressLint("ResourceType")
    public void setIconRight(@DrawableRes int iconRes) {
        if (iconRes <= 0) {
            mIcon.setVisibility(GONE);
            return;
        }
        mIcon.setImageResource(iconRes);
        mIcon.setVisibility(VISIBLE);
        mTvRight.setVisibility(GONE);
    }

    public void setDotIcon(int isVilb) {
        mDot.setVisibility(isVilb);
    }

    public void setDotText(String count) {
        mDot.setText(count);
    }

    public void setTitleColor(int titlecolor) {
        mTitle.setTextColor(titlecolor);
    }

    /**
     * 设置左侧图标
     *
     * @param iconRes
     */
    @SuppressLint("ResourceType")
    public void setIconLeft(@DrawableRes int iconRes) {
        if (iconRes <= 0) {
            mIconLeft.setVisibility(GONE);
            return;
        }
        mIconLeft.setImageResource(iconRes);
        mIconLeft.setVisibility(VISIBLE);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        float d = getResources().getDisplayMetrics().density;
        int minH = (int) (d * 36 + UiUtil.getStatusBarHeight(getContext()));

//        heightMeasureSpec = MeasureSpec.makeMeasureSpec(minH, MeasureSpec.EXACTLY);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public static int getExtPaddingTop(Resources resources) {
        if (EXT_PADDING_TOP > 0)
            return EXT_PADDING_TOP;

        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            EXT_PADDING_TOP = resources.getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
            return (int) TDevice.dp2px(25);
        }
        return EXT_PADDING_TOP;
    }


    /**
     * 设置titleBar的监听
     *
     * @param listener
     */
    public void setTitleBarListener(OnTitleBarListener listener) {
        mOnTitleBarListener = listener;
        addListener();
    }


    @Override
    public void onClick(View v) {
        if (mOnTitleBarListener == null) {
            return;
        }
        int i = v.getId();
        if (i == R.id.tv_title) {
            mOnTitleBarListener.onTitleClick(v);
        } else if (i == R.id.iv_icon_left) {
            mOnTitleBarListener.onLeftClick(v);
        } else if (i == R.id.iv_icon) {
            mOnTitleBarListener.onRightClick(v);
        } else if (i == R.id.tv_right) {
            mOnTitleBarListener.onRightClick(v);
        }
    }


}
