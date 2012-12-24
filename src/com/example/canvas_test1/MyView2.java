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

		//��������ʼ��Path
		path = new Path();
		path.moveTo(0, 0);
		for (int i = 1; i <= 15; i++) {
			//����15���㣬Y����������ɣ�������������һ��Path
			path.lineTo(i * 20, (float) Math.random() * 60);
		}
		//��ʼ��7����ɫ
		colors = new int[] { Color.BLACK, Color.BLUE, Color.CYAN, Color.GREEN,
				Color.MAGENTA, Color.RED, Color.YELLOW };
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		canvas.drawColor(Color.WHITE);
		
		//---------------------��ʼ��7��·��Ч��------------------
		//������·��Ч��
		effects[0] = null;
		//ʹ��CornerPathEffectЧ��
		effects[1] = new CornerPathEffect(10);
		//��ʼ��DiscretePathEffect
		effects[2] = new DiscretePathEffect(3.0f, 5.0f);
		//��ʼ��DashPathEffect
		effects[3] = new DashPathEffect(new float[] { 20, 10, 5, 10 }, phase);

		//��ʼ��PathDashPathEffect
		Path p = new Path();
		p.addRect(0, 0, 8, 8, Path.Direction.CCW);
		effects[4] = new PathDashPathEffect(p, 12, phase,
				PathDashPathEffect.Style.ROTATE);
		//��ʼ��PathDashPathEffect
		effects[5] = new ComposePathEffect(effects[2], effects[4]);
		effects[6] = new SumPathEffect(effects[4], effects[3]);
		
		//����������8.8����ʼ�滭
		canvas.translate(8, 8);

		//����ʹ��7�ֲ�ͬ��·��Ч����7�ֲ�ͬ����ɫ������·��
		for (int i = 0; i < effects.length; i++) {
			paint.setPathEffect(effects[i]);
			paint.setColor(colors[i]);
			canvas.drawPath(path, paint);
			canvas.translate(0, 60);
			//�ı�phaseֵ�γɶ���Ч��
			phase += 1;
			invalidate();
		}
	}

}
