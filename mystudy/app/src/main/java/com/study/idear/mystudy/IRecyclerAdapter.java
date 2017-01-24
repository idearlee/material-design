package com.study.idear.mystudy;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by idear on 17-1-22.
 */
public class IRecyclerAdapter extends RecyclerView.Adapter<IRecyclerAdapter.IViewHolder> {
    private List<String> mDatas;
    private Context mContext;
    private LayoutInflater inflater;

    public IRecyclerAdapter(Context context, List<String> datas) {
        this.mContext = context;
        this.mDatas = datas;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getItemCount() {

        return mDatas.size();
    }

    public void setDatas(List<String> datas) {
        this.mDatas = datas;
    }


    @Override
    public void onBindViewHolder(IViewHolder holder, final int position) {

        holder.tv.setText(mDatas.get(position));
    }

    @Override
    public IViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.layout_recycle_item, parent, false);
        IViewHolder holder = new IViewHolder(view);
        return holder;
    }

    class IViewHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public IViewHolder(View view) {
            super(view);
            tv = (TextView) view.findViewById(R.id.recycle_item_tv);
        }
    }
}
