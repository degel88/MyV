package me.jingyuan.myv.ui.adapter;


import android.content.Context;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import me.jingyuan.myv.R;
import me.jingyuan.myv.component.ImageLoader;
import me.jingyuan.myv.model.bean.VideoInfo;

/**
 * Description: 推荐
 * Creator: degel
 */
public class RelatedAdapter extends RecyclerArrayAdapter<VideoInfo> {

    public RelatedAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new RelatedViewHolder(parent);
    }

    class RelatedViewHolder extends BaseViewHolder<VideoInfo> {
        ImageView imgPicture;
        TextView tv_title;

        public RelatedViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_related);
            imgPicture = $(R.id.img_video);
            tv_title = $(R.id.tv_title);
            imgPicture.setScaleType(ImageView.ScaleType.FIT_XY);
        }

        @Override
        public void setData(VideoInfo data) {
            tv_title.setText(data.title);
            ViewGroup.LayoutParams params = imgPicture.getLayoutParams();

            DisplayMetrics dm = getContext().getResources().getDisplayMetrics();
            int width = dm.widthPixels / 3;//宽度为屏幕宽度一半
//        int height = data.getHeight()*width/data.getWidth();//计算View的高度

            params.height = (int) (width * 1.2);
            imgPicture.setLayoutParams(params);
            ImageLoader.load(getContext(), data.pic, imgPicture);
        }
    }
}
