package com.yhashi.swipeablecardlist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import junit.framework.Assert;

import java.util.List;

public class SwipableAdapter<T> extends RecyclerView.Adapter<SwipableItemViewHolder<T>> {
	private List<T> list;
	private int itemResource;
    private int contentId;
    private int textId;
	
	public SwipableAdapter(List<T> list) {
		this.list = list;
		this.itemResource = R.layout.list_item;
	}

	public SwipableAdapter(List<T> list, int itemResource, int contentId, int textId) {
		this.list = list;
        this.itemResource = itemResource;
	}

	@Override
	public int getItemCount() {
		Assert.assertNotNull(list);
		return list.size();
	}

	@Override
	public void onBindViewHolder(SwipableItemViewHolder<T> viewHolder, int i) {
		viewHolder.bind(list.get(i));
	}

	@Override
	public SwipableItemViewHolder<T> onCreateViewHolder(ViewGroup viewGroup, int i) {
		View v = LayoutInflater.from(viewGroup.getContext()).inflate(itemResource, null, false);
        if(contentId == 0) {
            return new SwipableItemViewHolder<>(v);
        } else {
            return new SwipableItemViewHolder<>(v, textId, contentId);
        }
	}

	public void remove(int position) {
		if(position < list.size()) {
			list.remove(position);
			notifyItemRemoved(position);
		}
	}

	public void add(T item, int position) {
		list.add(position, item);
		notifyItemInserted(position);
	}
	
	public T getItem(int position) {
		Assert.assertNotNull(list.get(position));
		return list.get(position);
	}
}
/*
class SwipableItemViewHolder<T> extends RecyclerView.ViewHolder {
	private TextView textView;
	
	public SwipableItemViewHolder(View itemView) {
		super(itemView);
		textView = (TextView)itemView.findViewById(R.id.itemContentText);
	}
	
	public void bind(T data) {
		textView.setText(data.toString());
	}
}*/