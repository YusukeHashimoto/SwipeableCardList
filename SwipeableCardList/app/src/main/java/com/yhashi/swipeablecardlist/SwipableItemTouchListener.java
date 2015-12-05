package com.yhashi.swipeablecardlist;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

abstract class SwipableItemTouchListener implements View.OnTouchListener {
	private Point mDownPos = new Point();
	private boolean mSwiping = false;
	private int mSwitchState = NUTRAL;
	private RecyclerView recyclerView;
	//private View touchedView;
	private SwipableItemViewHolder touchedView;
	private int touchedPos;
	private long animationTime;
	
	private static final int NUTRAL = 0;
	private static final int UP = 1;
	private static final int DOWN = 2;
	private static final int LEFT = 3;
	private static final int RIGHT = 4;
	
	public SwipableItemTouchListener(RecyclerView recyclerView) {
		this.recyclerView = recyclerView;
		animationTime = recyclerView.getContext().getResources().getInteger(android.R.integer.config_shortAnimTime);
	}
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch(event.getActionMasked()) {
		case MotionEvent.ACTION_DOWN:
			Rect rect = new Rect();
			int[] listViewCoords = new int[2];
			recyclerView.getLocationOnScreen(listViewCoords);
			int x = (int)event.getRawX() - listViewCoords[0];
			int y = (int)event.getRawY() - listViewCoords[1];
			View child;
			for(int i = 0; i < recyclerView.getChildCount(); i++) {
				child = recyclerView.getChildAt(i);
				child.getHitRect(rect);
				if(rect.contains(x, y)) {
					touchedPos = i;
					ViewHolder vh = recyclerView.getChildViewHolder(child);
					if(vh instanceof SwipableItemViewHolder)
						touchedView = (SwipableItemViewHolder<String>)vh;
					Log.v("", "You touched " + child.toString());
				}
			}
			
			mSwiping = false;
			mDownPos.set((int)event.getRawX(), (int)event.getRawY());
			break;
		case MotionEvent.ACTION_UP:
			mSwiping = false;
			touchedView.getContent().animate().translationX(0)
			.setDuration(animationTime).setListener(new AnimatorListenerAdapter() {
				@Override
				public void onAnimationEnd(Animator animator) {
					Log.v("", "animation end");
				}
			});
			switch(mSwitchState) {
			case UP:
				onSwipeUp(touchedPos);
				break;
			case DOWN:
				onSwipeDown(touchedPos);
				break;
			case RIGHT:
				onSwipeRight(touchedPos);
				break;
			case LEFT:
				onSwipeLeft(touchedPos);
				break;
			}
			mDownPos.set(0, 0);
			break;
		case MotionEvent.ACTION_MOVE:
			float deltaX = event.getRawX() - mDownPos.x;
			float deltaY = event.getRawY() - mDownPos.y;
			if(mSwiping) {
				if(Math.abs(deltaX) > Math.abs(deltaY)) {
					mSwitchState = (deltaX < 0) ? LEFT : RIGHT;
				} else {
					mSwitchState = (deltaY > 0) ? DOWN : UP;
				}
				onSwiping();
				touchedView.getContent().setTranslationX(deltaX);
			} else {
				mSwiping = true;
			}
			break;
		case MotionEvent.ACTION_CANCEL:
			mDownPos.set(0, 0);
			break;
		}
		return false;
	}
	
	private void onSwiping() {
		switch(mSwitchState) {
		case UP:
			onSwipingUp();
			break;
		case DOWN:
			onSwipingDown();
			break;
		case LEFT:
			onSwipingLeft();
			break;
		case RIGHT:
			onSwipingRight();
			break;
		}
	}

	abstract void onSwipeLeft(int position);
	abstract void onSwipeRight(int position);
	abstract void onSwipeUp(int position);
	abstract void onSwipeDown(int position);
	
	public void onSwipingUp() {}
	public void onSwipingDown() {}
	public void onSwipingLeft() {}
	public void onSwipingRight() {}
}
