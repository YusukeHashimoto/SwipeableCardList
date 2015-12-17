package com.yhashi.swipeablecardlist;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.link519.swipeablecardlist.R;

public class SwipableItemViewHolder<T> extends RecyclerView.ViewHolder{
	private TextView textView;
	private View content;

    /**
     * デフォルトのカードレイアウトを使う場合
     * @param itemView
     */
	public SwipableItemViewHolder(View itemView) {
		super(itemView);
		textView = (TextView)itemView.findViewById(R.id.itemContentText);
		content = itemView.findViewById(R.id.itemContent);
	}

    /**
     * レイアウトを指定する場合
     * @param itemView
     * @param textViewId
     * @param contentId
     */
    public SwipableItemViewHolder(View itemView, int textViewId, int contentId) {
        super(itemView);
        textView = (TextView)itemView.findViewById(textViewId);
        content = itemView.findViewById(contentId);
    }
	
	public void bind(T data) {
		textView.setText(data.toString());
	}

	public View getContent() {
		return content;
	}

}
