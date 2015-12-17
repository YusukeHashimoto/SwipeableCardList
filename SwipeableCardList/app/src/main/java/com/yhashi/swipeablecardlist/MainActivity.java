package com.yhashi.swipeablecardlist;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
	private RecyclerView.LayoutManager layoutManager;
	private RecyclerView recyclerView;
	private SwipableAdapter<String> adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
		layoutManager = new LinearLayoutManager(this);
		recyclerView.setLayoutManager(layoutManager);
		
		List<String> list = new ArrayList<String>();
		for(int i = 0; i < 3; i++) {
			list.add("card" + i);
		}

        adapter = new SwipableAdapter<String>(list);
		recyclerView.setAdapter(adapter);
		recyclerView.setOnTouchListener(new SwipableItemTouchListener(recyclerView) {
			@Override
			void onSwipeLeft(int position) {
				Toast.makeText(MainActivity.this, "left", Toast.LENGTH_SHORT).show();
				Log.v("", "moved left");
				adapter.remove(position);
				if(adapter.getItemCount() == 0) Log.v("", "adapter is empty");
			}

			@Override
			void onSwipeRight(int position) {
				Toast.makeText(MainActivity.this, "right", Toast.LENGTH_SHORT).show();
				Log.v("", "moved right");
			}

			@Override
			void onSwipeUp(int position) {
				Toast.makeText(MainActivity.this, "up", Toast.LENGTH_SHORT).show();
				Log.v("", "moved up");
			}

			@Override
			void onSwipeDown(int position) {
				Toast.makeText(MainActivity.this, "down", Toast.LENGTH_SHORT).show();
				Log.v("", "moved down");
				if(position < adapter.getItemCount()) {
					String copiedItem = adapter.getItem(position);
					adapter.add(copiedItem, position + 1);
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
