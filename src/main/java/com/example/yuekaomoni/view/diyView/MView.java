package com.example.yuekaomoni.view.diyView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.yuekaomoni.R;

public class MView extends LinearLayout {

    private Button add;
    private Button delete;
    private TextView num;

    public MView(Context context) {
        this(context,null);
    }

    public MView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,0);
    }

    public MView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context,attrs,defStyleAttr,defStyleRes);
    }



    private void initView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        LayoutParams l_add = new LayoutParams(100, RelativeLayout.LayoutParams.WRAP_CONTENT);
        LayoutParams l_delete = new LayoutParams(100, RelativeLayout.LayoutParams.WRAP_CONTENT);
        LayoutParams l_num = new LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        add = new Button(context);
        add.setId(R.id.add);
        num = new TextView(context);
        num.setId(R.id.num);
        delete = new Button(context);
        delete.setId(R.id.delete);

        add.setText("+");
        delete.setText("-");
        num.setText("0");
        addView(add,l_add);
        addView(num,l_num);
        addView(delete ,l_delete);
    }
    public TextView getNum(){

        return num;
    }
    public Button getadd(){

        return add;
    }
    public Button getDelete(){

        return delete;
    }

}
