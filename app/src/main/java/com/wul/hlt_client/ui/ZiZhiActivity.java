package com.wul.hlt_client.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.wul.hlt_client.R;
import com.wul.hlt_client.base.BaseActivity;

import butterknife.BindView;

public class ZiZhiActivity extends BaseActivity {

    @BindView(R.id.view_pager)
    ViewPager viewPager;

    int[] res = new int[]{R.drawable.zizhi1, R.drawable.zizhi2, R.drawable.zizhi3, R.drawable.zizhi4, R.drawable.zizhi5};

    @Override
    protected int getLayout() {
        return R.layout.act_zizhi;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        goBack();
        setTitleText("我的资质");

        viewPager.setAdapter(new MyAdapter());
    }


    private class MyAdapter extends PagerAdapter {


        @Override
        public int getCount() {
            return res.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == o;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(ZiZhiActivity.this);
            imageView.setImageResource(res[position]);
            container.addView(imageView);
            //最后要返回的是控件本身
            return imageView;
        }

        //因为它默认是看三张图片，第四张图片的时候就会报错，还有就是不要返回父类的作用
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
            //         super.destroyItem(container, position, object);
        }

    }
}
