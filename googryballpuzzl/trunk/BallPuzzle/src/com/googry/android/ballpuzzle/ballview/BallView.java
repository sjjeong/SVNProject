package com.googry.android.ballpuzzle.ballview;

import java.util.Random;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class BallView extends View {
	private int ballViewWidth, ballViewHeight;
	private Bitmap ballBitmap;
	private Point ballPosition, ballVelocity;// Ball 
	private int ballRadius;
	private int[] ballDividePosition;
	private boolean[] redrawBallPosition;
	private final static int COUNTBITMAPCOLS = 4;//leftright 
	private final static int COUNTBITMAPROWS = 4;//topbottom
	private final static int COUNTPUZZLE = COUNTBITMAPROWS * COUNTBITMAPCOLS;
	private int selectPosition;

	public interface OnPuzzleListener {
		void OnPuzzleCompleted();
	}

	OnPuzzleListener onPuzzleListener = null;

	public void setOnPuzzleCompleted(OnPuzzleListener listener) {
		onPuzzleListener = listener;
	}

	public BallView(Context context, AttributeSet attrs) {
		super(context, attrs);
		ballViewWidth = 0;
		ballViewHeight = 0;
		//not select
		selectPosition = -1;
		ballDividePosition = new int[COUNTPUZZLE];
		redrawBallPosition = new boolean[COUNTPUZZLE];
		for (int i = 0; i < COUNTPUZZLE; i++) {
			ballDividePosition[i] = i;
			redrawBallPosition[i] = false;
		}
	}

	public void InitData() {
		ballViewWidth = 0;
		ballViewHeight = 0;
		selectPosition = -1;
		ballDividePosition = new int[COUNTPUZZLE];
		for (int i = 0; i < COUNTPUZZLE; i++) {
			ballDividePosition[i] = i;
		}
		Random rand = new Random();
		for (int i = 0; i < 1000; i++) {
			int num1 = rand.nextInt(COUNTPUZZLE);
			int num2 = rand.nextInt(COUNTPUZZLE);
			int temp = ballDividePosition[num1];
			ballDividePosition[num1] = ballDividePosition[num2];
			ballDividePosition[num2] = temp;
		}
	}

	@Override
	protected void onDraw(Canvas canvas) {
		if (ballViewWidth != getWidth() || ballViewHeight != getHeight()) {
			reinitData();
		}
		Canvas bmpCanvas = new Canvas();
		bmpCanvas.setBitmap(ballBitmap);

		moveBall();

		checkBallPosition();

		drawBall(bmpCanvas);

		drawCanvas(canvas);
	}

	// Initialize Data;
	private void reinitData() {
		ballViewWidth = getWidth();
		ballViewHeight = getHeight();
		ballBitmap = Bitmap.createBitmap(ballViewWidth, ballViewHeight,
				Config.ARGB_8888);
		ballRadius = ballViewWidth / 10;
		Random random = new Random();
		ballPosition = new Point(random.nextInt(ballViewWidth - ballRadius * 2)
				+ ballRadius, random.nextInt(ballViewHeight - ballRadius * 2)
				+ ballRadius);
		int velocityDivideNum = 10;
		switch (random.nextInt(4)) {
		case 0:
			ballVelocity = new Point(ballRadius / velocityDivideNum, ballRadius
					/ velocityDivideNum);
			break;
		case 1:
			ballVelocity = new Point(ballRadius / velocityDivideNum * -1,
					ballRadius / velocityDivideNum);
			break;
		case 2:
			ballVelocity = new Point(ballRadius / velocityDivideNum, ballRadius
					/ velocityDivideNum * -1);
			break;
		case 3:
			ballVelocity = new Point(ballRadius / velocityDivideNum * -1,
					ballRadius / velocityDivideNum * -1);
			break;
		}
	}

	private void moveBall() {
		if (ballPosition.x < 0 + ballRadius
				|| ballPosition.x > ballViewWidth - ballRadius)
			ballVelocity.x *= -1;
		if (ballPosition.y < 0 + ballRadius
				|| ballPosition.y > ballViewHeight - ballRadius)
			ballVelocity.y *= -1;
		ballPosition.x += ballVelocity.x;
		ballPosition.y += ballVelocity.y;
	}

	private void checkBallPosition() {
		int a = ballPosition.x - ballRadius;
		int b = ballPosition.x + ballRadius;
		int c = ballPosition.y - ballRadius;
		int d = ballPosition.y + ballRadius;
		if (b >= ballViewWidth)
			b = ballViewWidth - 1;
		if (d >= ballViewHeight)
			d = ballViewHeight - 1;

		int leftTop = a / (ballViewWidth / COUNTBITMAPCOLS) + c
				/ (ballViewHeight / COUNTBITMAPROWS) * COUNTBITMAPCOLS;
		int rightTop = b / (ballViewWidth / COUNTBITMAPCOLS) + c
				/ (ballViewHeight / COUNTBITMAPROWS) * COUNTBITMAPCOLS;
		int leftBotton = a / (ballViewWidth / COUNTBITMAPCOLS) + d
				/ (ballViewHeight / COUNTBITMAPROWS) * COUNTBITMAPCOLS;
		int rightBotton = b / (ballViewWidth / COUNTBITMAPCOLS) + d
				/ (ballViewHeight / COUNTBITMAPROWS) * COUNTBITMAPCOLS;
		redrawBallPosition[leftTop] = true;
		redrawBallPosition[rightTop] = true;
		redrawBallPosition[leftBotton] = true;
		redrawBallPosition[rightBotton] = true;
	}

	// Draw Ball
	private void drawBall(Canvas canvas) {
		canvas.drawColor(Color.WHITE);
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Color.BLACK);
		canvas.drawCircle(ballPosition.x, ballPosition.y, ballRadius, paint);
		paint.setColor(Color.YELLOW);
		canvas.drawCircle(ballPosition.x, ballPosition.y, ballRadius * 0.9f,
				paint);
	}

	private void drawCanvas(Canvas canvas) {
		int puzzleWidth = ballViewWidth / COUNTBITMAPCOLS;
		int puzzleHeight = ballViewHeight / COUNTBITMAPROWS;
		for (int i = 0; i < COUNTPUZZLE; i++) {
			if (redrawBallPosition[ballDividePosition[i]]) {
				canvas.drawBitmap(Bitmap.createBitmap(ballBitmap, puzzleWidth
						* (ballDividePosition[i] % COUNTBITMAPCOLS),
						puzzleHeight
								* (ballDividePosition[i] / COUNTBITMAPROWS),
						puzzleWidth, puzzleHeight), puzzleWidth
						* (i % COUNTBITMAPCOLS) - 1, puzzleHeight
						* (i / COUNTBITMAPROWS) - 1, null);
				redrawBallPosition[ballDividePosition[i]] = false;
			}
		}

		Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Color.BLACK);
		for (int y = 1; y < COUNTBITMAPROWS; y++) {
			canvas.drawLine(0, puzzleHeight * y, ballViewWidth, puzzleHeight
					* y, paint);
		}
		for (int x = 1; x < COUNTBITMAPCOLS; x++) {
			canvas.drawLine(puzzleWidth * x, 0, puzzleWidth * x,
					ballViewHeight, paint);
		}

		if (selectPosition != -1) {
			int left = selectPosition % COUNTBITMAPCOLS * puzzleWidth;
			int top = selectPosition / COUNTBITMAPROWS * puzzleHeight;
			int right = selectPosition % COUNTBITMAPCOLS * puzzleWidth
					+ puzzleWidth;
			int bottom = selectPosition / COUNTBITMAPROWS * puzzleHeight
					+ puzzleHeight;
			paint.setColor(Color.BLUE);
			paint.setStrokeWidth(10);
			canvas.drawLine(left, top, right, top, paint);// ��
			canvas.drawLine(right, top, right, bottom, paint);// ������
			canvas.drawLine(right, bottom, left, bottom, paint);// �Ʒ�
			canvas.drawLine(left, bottom, left, top, paint);// ����

		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		int action = event.getAction();
		if (action == MotionEvent.ACTION_DOWN) {
			int positionX = (int) event.getX();
			int positionY = (int) event.getY();
			int _selectPosition = positionX / (ballViewWidth / COUNTBITMAPCOLS)
					+ positionY / (ballViewHeight / COUNTBITMAPROWS)
					* COUNTBITMAPCOLS;
			if (selectPosition == -1) {
				selectPosition = _selectPosition;
			} else if (selectPosition != -1) {
				int temp = ballDividePosition[selectPosition];
				ballDividePosition[selectPosition] = ballDividePosition[_selectPosition];
				ballDividePosition[_selectPosition] = temp;
				selectPosition = -1;
				int loop = 0;
				for (; loop < COUNTPUZZLE; loop++) {
					if (ballDividePosition[loop] != loop)
						break;
				}
				if (loop == COUNTPUZZLE && onPuzzleListener != null) {
					onPuzzleListener.OnPuzzleCompleted();
				}
			} else if (selectPosition != _selectPosition) {
				selectPosition = -1;
			}
		}

		return super.onTouchEvent(event);
	}
}
