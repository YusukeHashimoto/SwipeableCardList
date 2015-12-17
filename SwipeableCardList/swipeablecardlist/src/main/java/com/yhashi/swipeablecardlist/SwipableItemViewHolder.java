package com.yhashi.swipeablecardlist;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SwipableItemViewHolder<T> extends RecyclerView.ViewHolder{
	private TextView textView;
	private View content;
	private List<View> backgroundViews = new ArrayList<View>();

    /**
     * デフォルトのカードレイアウトを使う場合
     * @param itemView
     */
	public SwipableItemViewHolder(View itemView) {
		super(itemView);
		textView = (TextView)itemView.findViewById(R.id.itemContentText);
		content = itemView.findViewById(R.id.itemContent);

		backgroundViews.add(itemView.findViewById(R.id.itemLeftOn));
		backgroundViews.add(itemView.findViewById(R.id.itemLeftOff));
		backgroundViews.add(itemView.findViewById(R.id.itemRightOn));
		backgroundViews.add(itemView.findViewById(R.id.itemRightOff));
		for(View view : backgroundViews) {
			view.setVisibility(View.INVISIBLE);
		}
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

	void setBackgroundView(int pos) {
		for(int i = 0; i < backgroundViews.size(); i++) {
			if(i == pos) {
				backgroundViews.get(i).setVisibility(View.VISIBLE);
			} else {
				backgroundViews.get(i).setVisibility(View.INVISIBLE);
			}
		}
	}
}
