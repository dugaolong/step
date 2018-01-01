/**
 *Copyright (C) 2014 Guangzhou QTONE Technologies Ltd.
 *
 * 本代码版权归广州全通教育股份有限公司所有，且受到相关的法律保护。没有经过版权所有者的书面同意，
 * 任何其他个人或组织均不得以任何形式将本文件或本文件的部分代码用于其他商业用途。
 * @date 2014-12-10 下午2:43:39 
 * @version V1.0
 */
package cn.dgl.www.step.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

/** 
 *
 *
 * @ClassName NoScrollGridView 
 * @author 黄培桂
 * @date 2014-12-10 下午2:43:39  
 */
public class NoScrollListView extends ListView {
	public NoScrollListView(Context context) { 
		super(context); 
		// TODO Auto-generated constructor stub 
	} 
	public NoScrollListView(Context context, AttributeSet attrs) { 
		super(context, attrs); 
		// TODO Auto-generated constructor stub 
	} 
	public NoScrollListView(Context context, AttributeSet attrs, int defStyle) { 
		super(context, attrs, defStyle); 
		// TODO Auto-generated constructor stub 
	} 
	@Override 
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) { 
		// TODO Auto-generated method stub 
		int expandSpec = MeasureSpec.makeMeasureSpec(  
				Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);  

		super.onMeasure(widthMeasureSpec, expandSpec); 
	} 

	//解决此View在ScrollView中仍然可以拉动问题
	@Override
	public boolean onTouchEvent(MotionEvent e) {
		if(e.getAction()==MotionEvent.ACTION_MOVE){
			return false;
		}
		return super.onTouchEvent(e);
	}
}
