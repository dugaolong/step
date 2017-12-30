package cn.dgl.www.step.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import cn.dgl.www.step.R;

/**
 * 加载提醒对话框
 */
public class CustomPrograssBar extends ProgressDialog
{
    private String isok;
    private TextView tv_msg;
    private ProgressBar pb_load;
    public CustomPrograssBar(Context context)
    {
        super(context);

    }

    public CustomPrograssBar(Context context, int theme,String isok)
    {
        super(context, theme);
        this.isok = isok;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        init(getContext());
    }

    private void init(Context context)
    {
        //设置不可取消，点击其他区域不能取消，实际中可以抽出去封装供外包设置
        setCancelable(false);
        setCanceledOnTouchOutside(false);

        setContentView(R.layout.progress_bar);
        tv_msg = (TextView) findViewById(R.id.tv_msg);
        pb_load = (ProgressBar) findViewById(R.id.pb_load);
        if(isok.equals("success"))
        {
            pb_load.setVisibility(View.GONE);
            tv_msg.setVisibility(View.VISIBLE);
            tv_msg.setText("定位成功");
        }else if(isok.equals("failed"))
        {
            pb_load.setVisibility(View.GONE);
            tv_msg.setVisibility(View.VISIBLE);
            tv_msg.setText("定位失败");
        }else if(isok.equals("loading"))
        {
            pb_load.setVisibility(View.VISIBLE);
            tv_msg.setVisibility(View.GONE);
        }
//        WindowManager.LayoutParams params = getWindow().getAttributes();
//        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
//        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
//        getWindow().setAttributes(params);
    }

    @Override
    public void show()
    {
        super.show();
    }
}