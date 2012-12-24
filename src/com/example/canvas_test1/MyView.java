package com.example.canvas_test1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class MyView extends View {

	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		//画布设置成白色的
		canvas.drawColor(Color.WHITE);
		//创建画笔
		Paint paint = new Paint();
		//去掉锯齿
		paint.setAntiAlias(true);
		paint.setColor(Color.RED);
		paint.setStyle(Paint.Style.FILL);
		paint.setStrokeWidth(2);
		paint.setShadowLayer(45, 10, 10, Color.BLACK);
		
		canvas.drawCircle(40, 40, 30, paint);
		
		canvas.drawRect(10, 80, 70, 140, paint);
		
		canvas.drawRect(10, 150, 100, 210, paint);
		
		RectF rectF1 = new RectF(10, 220, 100, 280);
		canvas.drawRoundRect(rectF1, 10, 10, paint);
		
		RectF rectF2 = new RectF(10, 290, 100, 350);
		canvas.drawOval(rectF2, paint);
		
		
		Path path1 = new Path();
		path1.moveTo(10, 360);
		path1.lineTo(100, 360);
		path1.lineTo(55, 420);
		path1.close();
		canvas.drawPath(path1, paint);
		
	}

}
