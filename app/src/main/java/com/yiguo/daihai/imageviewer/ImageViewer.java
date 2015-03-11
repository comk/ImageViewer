package com.yiguo.daihai.imageviewer;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by daihai on 2015/3/11.
 */
public class ImageViewer extends LinearLayout{
    private static ImageViewer instance;

    private ImageViewConfig config;

    public ImageViewer(Context context) {
        super(context);
    }

    public ImageViewer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public static ImageViewer getInstance(Context context){
        if(instance == null) {
            if(context != null)
                instance = new ImageViewer(context);
            else
                throw new NullPointerException("Context can not be null");
        }
        return instance;
    }

    public void initImageViewer(ImageViewConfig config){
        this.config = config;
        if(LinearLayout.HORIZONTAL == config.direction){ //水平方向
            this.setOrientation(LinearLayout.HORIZONTAL);
            if(ThumbnailsPosition.POSITION_LEFT == config.thumbnailsPosition){
                RecyclerView recyclerView = new RecyclerView(getContext());
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(layoutManager);

                this.addView(recyclerView,new LayoutParams(0, LayoutParams.WRAP_CONTENT,config.thumbnailsPercentage));

                ViewPager viewPager = new ViewPager(getContext());
                this.addView(viewPager,new LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT,1f - config.thumbnailsPercentage));

            }else if(ThumbnailsPosition.POSITION_RIGHT == config.thumbnailsPosition){

                ViewPager viewPager = new ViewPager(getContext());
                this.addView(viewPager,new LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT,1f - config.thumbnailsPercentage));

                RecyclerView recyclerView = new RecyclerView(getContext());
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(layoutManager);

                this.addView(recyclerView,new LayoutParams(0, LayoutParams.WRAP_CONTENT,config.thumbnailsPercentage));

            }
        }else if(LinearLayout.VERTICAL == config.direction){//垂直方向
            this.setOrientation(LinearLayout.VERTICAL);
            if(ThumbnailsPosition.POSITION_TOP == config.thumbnailsPosition){
                RecyclerView recyclerView = new RecyclerView(getContext());
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(layoutManager);

                this.addView(recyclerView,new LayoutParams(0, LayoutParams.WRAP_CONTENT,config.thumbnailsPercentage));

                ViewPager viewPager = new ViewPager(getContext());
                this.addView(viewPager,new LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT,1f - config.thumbnailsPercentage));
            }else if(ThumbnailsPosition.POSITION_BOTTOM == config.thumbnailsPosition){
                ViewPager viewPager = new ViewPager(getContext());
                this.addView(viewPager,new LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT,1f - config.thumbnailsPercentage));

                RecyclerView recyclerView = new RecyclerView(getContext());
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(layoutManager);

                this.addView(recyclerView,new LayoutParams(0, LayoutParams.WRAP_CONTENT,config.thumbnailsPercentage));
            }
        }


    }

    public static class ThumbnailsPosition{
        /** 设置小图在上方 **/
        public static final int POSITION_TOP = 0;
        /** 设置小图在下方 **/
        public static final int POSITION_BOTTOM = 1;
        /** 设置小图在左方 **/
        public static final int POSITION_LEFT = 2;
        /** 设置小图在右方 **/
        public static final int POSITION_RIGHT = 3;

    }



    public class ImageViewConfig{
        /** 图片是否可伸缩 **/
        private boolean isScaleable = true;

        /** 图片是否可删除 **/
        private boolean isDeleteable = true;

        /** 是否显示缩略图 **/
        private boolean isShowThumbnail = true;

        /** 显示缩略图的位置 **/
        private int thumbnailsPosition = ThumbnailsPosition.POSITION_BOTTOM;

        private float thumbnailsPercentage;

        /** 大图和小图的布局方式 一种是垂直 一种是水平  LinearLayout.HORIZONTAL   LinearLayout.VERTICAL **/
        private int direction = LinearLayout.VERTICAL;

        public boolean isScaleable() {
            return isScaleable;
        }

        public float getThumbnailsPercentage() {
            return thumbnailsPercentage;
        }

        public ImageViewConfig setThumbnailsPercentage(float thumbnailsPercentage) {
            this.thumbnailsPercentage = thumbnailsPercentage;
            return this;
        }

        public ImageViewConfig setScaleable(boolean isScaleable) {
            this.isScaleable = isScaleable;
            return this;
        }

        public boolean isDeleteable() {
            return isDeleteable;
        }

        public ImageViewConfig setDeleteable(boolean isDeleteable) {
            this.isDeleteable = isDeleteable;
            return this;
        }

        public boolean isShowThumbnail() {
            return isShowThumbnail;
        }

        public ImageViewConfig setShowThumbnail(boolean isShowThumbnail) {
            this.isShowThumbnail = isShowThumbnail;
            return this;
        }

        public int getThumbnailsPosition() {
            return thumbnailsPosition;
        }

        public ImageViewConfig setThumbnailsPosition(int thumbnailsPosition) {
            this.thumbnailsPosition = thumbnailsPosition;
            return this;
        }

        public int getDirection() {
            return direction;
        }

        public ImageViewConfig setDirection(int direction) {
            this.direction = direction;
            return this;
        }
    }


}
