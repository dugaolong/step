package cn.dgl.www.step.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ExpandableListView;

/**
 * dgl
 *
 * @version V1.0
 * @date 2018-01-10 11:43:39
 */
public class NoScrollExpandListView extends ExpandableListView {
    public NoScrollExpandListView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public NoScrollExpandListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    public NoScrollExpandListView(Context context, AttributeSet attrs, int defStyle) {
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
        if (e.getAction() == MotionEvent.ACTION_MOVE) {
            //no eat event
            return false;
        }
        return super.onTouchEvent(e);
    }
}
