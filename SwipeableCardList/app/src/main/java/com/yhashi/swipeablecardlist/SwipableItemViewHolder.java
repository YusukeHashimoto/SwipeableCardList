package com.yhashi.swipeablecardlist;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class SwipableItemViewHolder<T> extends RecyclerView.ViewHolder{
	private TextView textView;
	private View content;
	
	public SwipableItemViewHolder(View itemView) {
		super(itemView);
		textView = (TextView)itemView.findViewById(R.id.itemContentText);
		content = itemView.findViewById(R.id.itemContent);
	}
	
	public void bind(T data) {
		textView.setText(data.toString());
	}
	
	public View getContent() {
		return content;
	}
}
