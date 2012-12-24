package com.example.canvas_test1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.SumPathEffect;
import android.view.View;

public class MyView2 extends View {

	float phase;
	PathEffect[] effects = new PathEffect[7];
	int[] colors;
	Path path;
	Paint paint;

	public MyView2(Context context) {
		super(context);
		paint = new Paint();
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(4);

		//创建并初始化Path
		path = new Path();
		path.moveTo(0, 0);
		for (int i = 1; i <= 15; i++) {
			//生成15个点，Y坐标随机生成，并将它们连成一条Path
			path.lineTo(i * 20, (float) Math.random() * 60);
		}
		//初始化7个颜色
		colors = new int[] { Color.BLACK, Color.BLUE, Color.CYAN, Color.GREEN,
				Color.MAGENTA, Color.RED, Color.YELLOW };
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		canvas.drawColor(Color.WHITE);
		
		//---------------------初始化7种路径效果------------------
		//不适用路径效果
		effects[0] = null;
		//使用CornerPathEffect效果
		effects[1] = new CornerPathEffect(10);
		//初始化DiscretePathEffect
		effects[2] = new DiscretePathEffect(3.0f, 5.0f);
		//初始化DashPathEffect
		effects[3] = new DashPathEffect(new float[] { 20, 10, 5, 10 }, phase);

		//初始化PathDashPathEffect
		Path p = new Path();
		p.addRect(0, 0, 8, 8, Path.Direction.CCW);
		effects[4] = new PathDashPathEffect(p, 12, phase,
				PathDashPathEffect.Style.ROTATE);
		//初始化PathDashPathEffect
		effects[5] = new ComposePathEffect(effects[2], effects[4]);
		effects[6] = new SumPathEffect(effects[4], effects[3]);
		
		//将画布移至8.8处开始绘画
		canvas.translate(8, 8);

		//依次使用7种不同的路径效果，7种不同的颜色来绘制路径
		for (int i = 0; i < effects.length; i++) {
			paint.setPathEffect(effects[i]);
			paint.setColor(colors[i]);
			canvas.drawPath(path, paint);
			canvas.translate(0, 60);
			//改变phase值形成动画效果
			phase += 1;
			invalidate();
		}
	}

}
