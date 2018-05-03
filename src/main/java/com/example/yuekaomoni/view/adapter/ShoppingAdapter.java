package com.example.yuekaomoni.view.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yuekaomoni.R;
import com.example.yuekaomoni.model.bean.JsonDataBean;
import com.example.yuekaomoni.view.diyView.MView;


import java.util.ArrayList;
import java.util.List;

public class ShoppingAdapter extends BaseExpandableListAdapter implements View.OnClickListener {

    private Context context;
    private List<JsonDataBean.ContentBean> list = new ArrayList<>();
    private CheckBox ck;
    private CallBack callBack;
    private TextView textView;
    public ShoppingAdapter(Context context, List<JsonDataBean.ContentBean> list) {
        this.context = context;
        this.list = list;
    }
    public void setCallBack(CallBack callBack){
        this.callBack = callBack;

    }

    @Override
    public int getGroupCount() {
        return list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return list.get(groupPosition).getGoodDetail().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return list.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return list.get(groupPosition).getGoodDetail().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
       MyGViewHolder myGViewHolder;
       if (convertView==null){
           convertView= LayoutInflater.from(context).inflate(R.layout.group_item,null);
           myGViewHolder=new MyGViewHolder(convertView);
           convertView.setTag(myGViewHolder);
       }else{

           myGViewHolder= (MyGViewHolder) convertView.getTag();
       }
        myGViewHolder.g_ck.setTag(groupPosition);
        myGViewHolder.g_ck.setOnClickListener(this);
        myGViewHolder.g_ck.setChecked(list.get(groupPosition).isIsSelected());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        MyCViewHolder myCViewHolder;
        if (convertView==null){
            convertView=LayoutInflater.from(context).inflate(R.layout.child_item,null);
            myCViewHolder=new MyCViewHolder(convertView);
            convertView.setTag(myCViewHolder);
        }else{
            myCViewHolder= (MyCViewHolder) convertView.getTag();
        }

        String price = list.get(groupPosition).getGoodDetail().get(childPosition).getPrice();
        myCViewHolder.c_ck.setTag(myCViewHolder.mView.getNum());
        myCViewHolder.c_ck.setChecked(list.get(groupPosition).getGoodDetail().get(childPosition).isIsSelected());
        myCViewHolder.c_ck.setOnClickListener(this);
        myCViewHolder.c_price.setText(price);
        myCViewHolder.c_title.setText(list.get(groupPosition).getGoodDetail().get(childPosition).getName());
        myCViewHolder.mView.getNum().setTag(price+"#"+groupPosition+"#"+childPosition);
        myCViewHolder.mView.getadd().setOnClickListener(this);
        myCViewHolder.mView.getDelete().setOnClickListener(this);

        myCViewHolder.mView.getadd().setTag(myCViewHolder.mView.getNum());
        myCViewHolder.mView.getDelete().setTag(myCViewHolder.mView.getNum());


        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.child_ck:
                child_ck(v);

                ;break;
            case R.id.group_ck:
                group_ck(v);
                ;break;
            case R.id.add:
                add(v);
                ;break;
            case R.id.delete:
                delete(v);

                ;break;
            case R.id.num:

                ;break;



        }

    }

    public class MyGViewHolder{
        private CheckBox g_ck ;

        public MyGViewHolder(View v) {
            g_ck=v.findViewById(R.id.group_ck);
        }
    }
    public class MyCViewHolder{
        private CheckBox c_ck;
        private ImageView c_img;
        private TextView c_title;
        private TextView  c_price;
        private MView  mView;

        public MyCViewHolder(View view) {
            c_ck=view.findViewById(R.id.child_ck);
            c_img=view.findViewById(R.id.child_img);
            c_title=view.findViewById(R.id.child_title);
            c_price=view.findViewById(R.id.child_price);
            mView=view.findViewById(R.id.mView);
        }
    }
    public void group_ck(View v){
        CheckBox checkBox = (CheckBox) v;

        int tag = (int) checkBox.getTag();
        boolean checked = checkBox.isChecked();
        list.get(tag).setIsSelected(checked);
        if (checked){

        }else{

        }

            for (int i = 0; i < list.get(tag).getGoodDetail().size(); i++) {
                list.get(tag).getGoodDetail().get(i).setIsSelected(checked);

            }
        IsSelectedAll();
        notifyDataSetChanged();

    }
    public void child_ck(View v){
        CheckBox checkBox = (CheckBox) v;
        TextView t = (TextView) checkBox.getTag();
        String tag = (String) t.getTag();
        String[] split = tag.split("#");
        String s1 = split[1];
        String s2 = split[2];
        Integer g = Integer.valueOf(s1);
        Integer c = Integer.valueOf(s2);
        boolean checked = checkBox.isChecked();
        list.get(g).getGoodDetail().get(c).setIsSelected(checked);
        boolean flag = true;
        for (int i = 0; i <  list.get(g).getGoodDetail().size(); i++) {
            if (!list.get(g).getGoodDetail().get(i).isIsSelected()){
                flag=false;
            }

        }
        list.get(g).setIsSelected(flag);
        IsSelectedAll();
        if (checked){
            child_ckPrice(t);
        }else{
            child_FckPrice(t);
        }
        notifyDataSetChanged();


    }
    public void IsSelectedAll() {

        boolean flag = true;
        for (int i = 0; i < list.size(); i++) {

           if (!list.get(i).isIsSelected()){
               flag=false;
           }
        }
        callBack.selectAll(flag);

    }
    public interface CallBack{
        public void selectAll(boolean b);
    }
    public void add(View view){

        Button add = (Button) view;
        TextView num = (TextView) add.getTag();
        String  arry = (String) num.getTag();
        String[] split = arry.split("#");
        String price = split[0];
        Integer p = Integer.valueOf(price);
        String gindex = split[1];
        Integer g = Integer.valueOf(gindex);
        String cindex = split[2];
        Integer c = Integer.valueOf(cindex);
        String s = num.getText().toString();
        Integer integer = Integer.valueOf(s);


        String s1 = textView.getText().toString();
        Integer zp = Integer.valueOf(s1);
        if (list.get(g).getGoodDetail().get(c).isIsSelected()){
            textView.setText(zp-p*integer+p*(integer+1)+"");
        }

        num.setText((integer+1)+"");
    }
    public void setTextprice(TextView textView){
        this.textView = textView;

    }
    public void delete(View view){
        Button add = (Button) view;
        TextView num = (TextView) add.getTag();
        String  arry = (String) num.getTag();
        String[] split = arry.split("#");
        String price = split[0];
        Integer p = Integer.valueOf(price);
        String gindex = split[1];
        Integer g = Integer.valueOf(gindex);
        String cindex = split[2];
        Integer c = Integer.valueOf(cindex);
        String s = num.getText().toString();
        Integer integer = Integer.valueOf(s);

        String s1 = textView.getText().toString();
        if (integer-1<0){
            integer=0;
            num.setText(integer+"");
        }else{
            integer--;
            num.setText(integer+"");
            Integer zp = Integer.valueOf(s1);
            if (list.get(g).getGoodDetail().get(c).isIsSelected()){
                textView.setText(zp-p*(integer+1)+p*integer+"");
            }
        }


    }
    public  void child_ckPrice(View view){
        TextView num = (TextView) view;
        String  arry = (String) num.getTag();
        String[] split = arry.split("#");
        String price = split[0];
        Integer p = Integer.valueOf(price);
        String gindex = split[1];
        Integer g = Integer.valueOf(gindex);
        String cindex = split[2];
        Integer c = Integer.valueOf(cindex);
        String s = num.getText().toString();
        Integer integer = Integer.valueOf(s);

        String s1 = textView.getText().toString();
        Integer zp = Integer.valueOf(s1);
        if (list.get(g).getGoodDetail().get(c).isIsSelected()){
            textView.setText(zp+p*integer+"");
        }
        num.setText(integer+"");

    }
    public  void child_FckPrice(View view){
        TextView num = (TextView) view;
        String  arry = (String) num.getTag();
        String[] split = arry.split("#");
        String price = split[0];
        Integer p = Integer.valueOf(price);
        String gindex = split[1];
        Integer g = Integer.valueOf(gindex);
        String cindex = split[2];
        Integer c = Integer.valueOf(cindex);
        String s = num.getText().toString();
        Integer integer = Integer.valueOf(s);

        String s1 = textView.getText().toString();
        Integer zp = Integer.valueOf(s1);
        if (!list.get(g).getGoodDetail().get(c).isIsSelected()){
            textView.setText(zp-p*integer+"");
        }
        num.setText(integer+"");

    }
}
