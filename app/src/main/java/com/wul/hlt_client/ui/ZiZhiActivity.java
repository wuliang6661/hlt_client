package com.wul.hlt_client.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.blankj.utilcode.util.LogUtils;
import com.bumptech.glide.Glide;
import com.wul.hlt_client.R;
import com.wul.hlt_client.base.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;

public class ZiZhiActivity extends BaseActivity {

    @BindView(R.id.view_pager)
    ViewPager viewPager;


    private ArrayList<ImageView> viewList;

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

        viewList = new ArrayList<>();
        viewList.add(new ImageView(this));
        viewList.add(new ImageView(this));
        viewList.add(new ImageView(this));
        viewList.add(new ImageView(this));
        viewList.add(new ImageView(this));

//        viewPager.setOffscreenPageLimit(5);
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
            //图片压缩
            BitmapFactory.Options opts = new BitmapFactory.Options();
            opts.inJustDecodeBounds = true;
            BitmapFactory.decodeResource(getResources(), res[position], opts);
            DisplayMetrics outMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
            int x = opts.outWidth / outMetrics.widthPixels;
            int y = opts.outHeight / outMetrics.heightPixels;
            if (x > y && x > 1) {
                opts.inSampleSize = x;
            } else if (y > x && y > 1) {
                opts.inSampleSize = y;
            }
            opts.inJustDecodeBounds = false;
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), res[position], opts);
            //加载图片
            ImageView imageView = viewList.get(position);
            imageView.setImageBitmap(bitmap);
            //加载页面
            ((ViewPager) container).addView(imageView);
            return viewList.get(position);

        }

        //因为它默认是看三张图片，第四张图片的时候就会报错，还有就是不要返回父类的作用
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            container.removeView((View) object);
            //回收图片
            ImageView imageView = viewList.get(position);
            BitmapDrawable bitmapDrawable = (BitmapDrawable) imageView.getDrawable();
            if (bitmapDrawable != null) {
                Bitmap bm = bitmapDrawable.getBitmap();
                if (bm != null && !bm.isRecycled()) {
                    Log.d("...desimg..", "被回收了" + bm.getByteCount());
                    imageView.setImageResource(0);
                    bm.recycle();
                }
            }
            imageView.setImageBitmap(null);
            releaseImageViewResouce(imageView);
            //移除页面
            ((ViewPager) container).removeView(imageView);
        }


        /**
         * 释放图片资源的方法
         *
         * @param imageView
         */
        public void releaseImageViewResouce(ImageView imageView) {
            if (imageView == null) return;
            Drawable drawable = imageView.getDrawable();
            if (drawable != null && drawable instanceof BitmapDrawable) {
                BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
                Bitmap bitmap = bitmapDrawable.getBitmap();
                if (bitmap != null && !bitmap.isRecycled()) {
                    bitmap.recycle();
                    bitmap = null;
                    LogUtils.e("图片回收啦");
                }
            }
            System.gc();

        }

    }
}
