package me.jingyuan.myv.ui.adapter;


import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.jingyuan.myv.R;
import me.jingyuan.myv.component.ImageLoader;
import me.jingyuan.myv.model.bean.VideoInfo;
import me.jingyuan.myv.model.bean.VideoType;
import me.jingyuan.myv.ui.activitys.VideoInfoActivity;
import me.jingyuan.myv.widget.theme.RoundedImageView;

/**
 * Description: SwipeDeckAdapter
 * Creator: degel
 */
public class SwipeDeckAdapter extends BaseAdapter {

    private List<VideoType> data;
    private Context context;
    private LayoutInflater inflater;
    private VideoInfo videoInfo;

    public SwipeDeckAdapter(List<VideoType> data, Context context) {
        this.data = data;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void refresh(List<VideoType> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.card_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ImageLoader.load(context, data.get(position).pic, holder.offerImage);
        String intro = "\t\t\t" + data.get(position).description;
        holder.tvIntroduction.setText(intro);
        holder.tv_title.setText(data.get(position).title);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchData(data.get(position));
                VideoInfoActivity.start(context, videoInfo);
            }
        });
        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.offer_image)
        RoundedImageView offerImage;
        @BindView(R.id.tv_introduction)
        TextView tvIntroduction;
        @BindView(R.id.card_view)
        CardView cardView;
        @BindView(R.id.tv_title)
        TextView tv_title;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    private void switchData(VideoType videoType) {
        if (videoInfo == null) {
            videoInfo = new VideoInfo();
        }
        videoInfo.title = videoType.title;
        videoInfo.dataId = videoType.dataId;
        videoInfo.pic = videoType.pic;
        videoInfo.airTime = videoType.airTime;
        videoInfo.score = videoType.score;
    }

}