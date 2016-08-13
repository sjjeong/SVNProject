package com.googry.android.ballpuzzle;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.googry.android.ballpuzzle.ballview.BallView;
import com.googry.android.ballpuzzle.ballview.BallView.OnPuzzleListener;

public class MainActivity extends Activity {
	// BallView
	private BallView ballView;
	// GameLoop Variable
	private final long FRAME = 20;
	private long mMoveDelay = 1000 / FRAME;
	private long mLastMove = 0;
	// Game Clear Time Variable
	private long startTime, endTime, sleepTime;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ballView = (BallView) findViewById(R.id.ballView);
		// GameLoop
		mLastMove = System.currentTimeMillis();
		gameLoopUpdate();

		// Start AlearDialog
		new AlertDialog.Builder(MainActivity.this).setCancelable(false)
				.setTitle("Ball Puzzle").setMessage("Enjoy BallPuzzle")
				.setPositiveButton("Start", new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						startTime = System.currentTimeMillis();
						ballView.InitData();
					}
				}).show();

		// BallView Listener
		ballView.setOnPuzzleCompleted(new OnPuzzleListener() {

			@Override
			public void OnPuzzleCompleted() {
				endTime = System.currentTimeMillis();
				long resultTime = endTime - startTime;
				String strTime = null;
				if (resultTime <= 60000) {
					strTime = String.format("%d.%02d", (resultTime / 1000),
							(resultTime % 1000));
				} else if (resultTime > 60000) {
					strTime = String.format("%d:%02d.%02d",
							(resultTime / 60000),
							((resultTime % 60000) / 1000), (resultTime % 1000));
				}
				new AlertDialog.Builder(MainActivity.this).setCancelable(false)
						.setTitle("Ball Puzzle")
						.setMessage("Time : " + strTime)
						.setPositiveButton("Restart", new OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								startTime = System.currentTimeMillis();
								ballView.InitData();
							}
						}).setNegativeButton("Exit", new OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								finish();
							}
						}).show();
			}
		});
	}

	private RefreshHandler mRedrawHandler = new RefreshHandler();

	class RefreshHandler extends Handler {
		boolean flag = true;

		@Override
		public void handleMessage(Message msg) {
			if (flag) {
				gameLoopUpdate();
			}
		}

		public void sleep(long delayMillis) {
			this.removeMessages(0);
			sendMessageDelayed(obtainMessage(0), delayMillis);
		}

		public void startFlag() {
			flag = true;
		}

		public void stopFlag() {
			flag = false;
		}
	};

	public void gameLoopUpdate() {
		long now = System.currentTimeMillis();

		// GameLoop
		if (now - mLastMove > mMoveDelay) {
			// mLastMove = now - (now - mLastMove - mMoveDelay);
			mLastMove += mMoveDelay;
			ballView.invalidate();
		}
		// Handler Sleep
		mRedrawHandler.sleep(0);
	}

	@Override
	protected void onResume() {
		super.onResume();
		mRedrawHandler.startFlag();
	}

	@Override
	protected void onPause() {
		mRedrawHandler.stopFlag();
		sleepTime = System.currentTimeMillis();
		super.onPause();
	}

	@Override
	protected void onRestart() {
		gameLoopUpdate();
		startTime += System.currentTimeMillis() - sleepTime;
		super.onRestart();
	}

}
