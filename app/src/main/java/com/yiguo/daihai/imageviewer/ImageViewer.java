package com.yiguo.daihai.imageviewer;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * Created by daihai on 2015/3/11.
 */
public class ImageViewer extends LinearLayout implements ViewPager.OnPageChangeListener,BigImageViewPagerAdapter.OnItemOptionListener{
    private ImageViewConfig config;
    private FixedViewPager viewPager;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private int lastItem = 0;

    @Override
    public void onItemDelete(int pos) {
        linearLayoutManager.removeViewAt(pos);
        ((ThumbnailsRecyclerAdapter)recyclerView.getAdapter()).remove(pos);
        recyclerView.getAdapter().notifyItemRemoved(pos);
    }

    @Override
    public void onItemLike(int pos) {

    }

    @Override
    public void onItemComment(int pos) {

    }

    @Override
    public void onItemSave(int pos) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if(linearLayoutManager != null && recyclerView != null){
            if(lastItem > position){
                int firstVisibleItem = linearLayoutManager.findFirstCompletelyVisibleItemPosition();
                if(firstVisibleItem == position)
                    return ;
                recyclerView.smoothScrollBy(-recyclerView.getChildAt(0).getWidth()*(firstVisibleItem - position),0);
            }else{
                int lastVisibleItem = linearLayoutManager.findLastCompletelyVisibleItemPosition();
                if( lastVisibleItem == position)
                    return;
                recyclerView.smoothScrollBy(recyclerView.getChildAt(0).getWidth()*(position - lastVisibleItem),0);
            }
        }
        lastItem = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public ImageViewer(Context context) {
        super(context);
    }

    public ImageViewer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }



    public void initImageViewer(ImageViewConfig config){
        this.config = config;
        if(LinearLayout.HORIZONTAL == config.direction){ //水平方向
            this.setOrientation(LinearLayout.HORIZONTAL);
            if(ThumbnailsPosition.POSITION_LEFT == config.thumbnailsPosition){
                recyclerView = new RecyclerView(getContext());
                linearLayoutManager = new LinearLayoutManager(getContext());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setHasFixedSize(true);
                this.addView(recyclerView,new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT,config.thumbnailsPercentage));

                viewPager = new FixedViewPager(getContext());
                viewPager.setAdapter(new BigImageViewPagerAdapter(getContext(),config.bigImages,config.isScaleable,this));
                viewPager.setOnPageChangeListener(this);
                this.addView(viewPager,new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT,1f - config.thumbnailsPercentage));

                recyclerView.setAdapter(new ThumbnailsRecyclerAdapter(getContext(),config.getThumbnails(),new ThumbnailsRecyclerAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        viewPager.setCurrentItem(position,true);
                    }
                }));

            }else if(ThumbnailsPosition.POSITION_RIGHT == config.thumbnailsPosition){

                viewPager = new FixedViewPager(getContext());
                viewPager.setAdapter(new BigImageViewPagerAdapter(getContext(),config.bigImages,config.isScaleable,this));
                viewPager.setOnPageChangeListener(this);
                this.addView(viewPager,new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT,1f - config.thumbnailsPercentage));

                recyclerView = new RecyclerView(getContext());
                linearLayoutManager = new LinearLayoutManager(getContext());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setHasFixedSize(true);
                this.addView(recyclerView,new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT,config.thumbnailsPercentage));
                recyclerView.setAdapter(new ThumbnailsRecyclerAdapter(getContext(),config.getThumbnails(),new ThumbnailsRecyclerAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        viewPager.setCurrentItem(position,true);
                    }
                }));
            }
        }else if(LinearLayout.VERTICAL == config.direction){//垂直方向
            this.setOrientation(LinearLayout.VERTICAL);
            if(ThumbnailsPosition.POSITION_TOP == config.thumbnailsPosition){
                recyclerView = new RecyclerView(getContext());
                linearLayoutManager = new LinearLayoutManager(getContext());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setHasFixedSize(true);
                this.addView(recyclerView,new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT,config.thumbnailsPercentage));

                viewPager = new FixedViewPager(getContext());
                viewPager.setAdapter(new BigImageViewPagerAdapter(getContext(),config.bigImages,config.isScaleable,this));
                viewPager.setOnPageChangeListener(this);
                this.addView(viewPager,new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT,1f - config.thumbnailsPercentage));
                recyclerView.setAdapter(new ThumbnailsRecyclerAdapter(getContext(),config.getThumbnails(),new ThumbnailsRecyclerAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        viewPager.setCurrentItem(position,true);
                    }
                }));
            }else if(ThumbnailsPosition.POSITION_BOTTOM == config.thumbnailsPosition){
                viewPager = new FixedViewPager(getContext());
                viewPager.setAdapter(new BigImageViewPagerAdapter(getContext(), config.bigImages,config.isScaleable,this));
                viewPager.setOnPageChangeListener(this);
                this.addView(viewPager,new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT,config.thumbnailsPercentage));

                recyclerView = new RecyclerView(getContext());
                linearLayoutManager = new LinearLayoutManager(getContext());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setHasFixedSize(true);
                this.addView(recyclerView,new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT,1f - config.thumbnailsPercentage));
                recyclerView.setAdapter(new ThumbnailsRecyclerAdapter(getContext(),config.getThumbnails(),new ThumbnailsRecyclerAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        viewPager.setCurrentItem(position,true);
                    }
                }));
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
        /** 缩略图图片地址 **/
        private ArrayList<String> thumbnails;
        /** 大图图片地址 **/
        private ArrayList<String> bigImages;
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

        public ArrayList<String> getThumbnails() {
            return thumbnails;
        }

        public ImageViewConfig setThumbnails(ArrayList<String> thumbnails) {
            this.thumbnails = thumbnails;
            return this;
        }

        public ArrayList<String> getBigImages() {
            return bigImages;
        }

        public ImageViewConfig setBigImages(ArrayList<String> bigImages) {
            this.bigImages = bigImages;
            return this;
        }

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
