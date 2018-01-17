package cn.dgl.www.step.lbs;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.sourceforge.pinyin4j.PinyinHelper;

import java.io.InputStream;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import cn.dgl.www.step.R;
import cn.dgl.www.step.bean.CityInfoModel;
import cn.dgl.www.step.bean.ProvinceInfoModel;
import cn.dgl.www.step.contact.ContactAdapter;
import cn.dgl.www.step.utils.AddrXmlParser;
import cn.dgl.www.step.utils.DialogUtil;
import cn.dgl.www.step.view.ClearEditText;
import cn.dgl.www.step.view.NoScrollListView;

/**
 * Created by dugaolong on 17/12/30.
 */

public class SelectCityActivity extends AppCompatActivity implements View.OnClickListener {

    protected String mCurrentProviceName;
    protected String mCurrentCityName;
    protected ArrayList<String> mProvinceDatas;
    protected List<String> mCityDatas = new ArrayList<String>();
    ;
    private ExpandableListView expandableListView;
    public ContactAdapter adapter;
    private HashMap<String, List<String>> map = new HashMap<>();
    //设置组视图的显示文字
    private List<String> generalsTypes = new ArrayList<String>();
    private ClearEditText et_search;
    private LinearLayout ll_hotCity;
    private NoScrollListView lv_search;
    ArrayAdapter<String> arrayAdapter;
    private List<String> listView_temp_data = new ArrayList<String>();
    private TextView beijing,shanghai,shenzhen,guangzhou,hangzhou,chengdu,chongqing,xian,nanjing,zhengzhou,tianjin,wuhan;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_city);
        DialogUtil.showProgressDialog(SelectCityActivity.this, "数据加载中....");
        readAddrDatas();
        initData();
        initView();
        adapter = new ContactAdapter(this, map, generalsTypes);
        expandableListView.setAdapter(adapter);
        expandGroup();

        arrayAdapter = new ArrayAdapter<String>(SelectCityActivity.this, android.R.layout.simple_list_item_1);
        initListView();
        // 设置字母导航触摸监听
//        sidebar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {
//
//            @Override
//            public void onTouchingLetterChanged(String s) {
//                // 该字母首次出现的位置
//                Log.e("aaaa","s="+s);
//                Log.e("aaaa","s.charAt(0)="+s.charAt(0));
//                int position = adapter.getPositionForSelection(s.charAt(0));
//                Log.e("position","position="+position);
//                if (position != -1) {
////                    expandableListView.setSelection(position);
//                    expandableListView.setSelectedGroup(position);
//                }
//            }
//        });
        et_search.setCallBackOnFocusChangeListener(new ClearEditText.CallBackOnFocusChangeListener() {
            @Override
            public void callBackOnFocusChangeListener(boolean visible) {
                if (visible) {
                    ll_hotCity.setVisibility(View.VISIBLE);
                } else {
                    ll_hotCity.setVisibility(View.GONE);
                }
            }

            @Override
            public void callBackOnTextChangeListener(String content) {
                if (mCityDatas == null) {
                    return;
                }
                if (!TextUtils.isEmpty(content)) {
                    lv_search.setVisibility(View.VISIBLE);
                    expandableListView.setVisibility(View.GONE);
                    ll_hotCity.setVisibility(View.GONE);
                    nodtifyListView(content);
                } else {
                    lv_search.setVisibility(View.GONE);
                    ll_hotCity.setVisibility(View.VISIBLE);
                    expandableListView.setVisibility(View.VISIBLE);
                }

            }
        });
        //设置item点击的监听器
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
//                Toast.makeText(SelectCityActivity.this, "你点击了" + adapter.getChild(groupPosition, childPosition),
//                        Toast.LENGTH_SHORT).show();
                //跳转到weathershow页面
                gotoShowWeather(adapter.getChild(groupPosition, childPosition)+"");

                return false;
            }
        });
        DialogUtil.closeProgressDialog();
    }

    private void initListView() {
        lv_search.setAdapter(arrayAdapter);
        lv_search.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String city_name = listView_temp_data.get(position);//选中的城市名称
                //跳转到weathershow页面
                gotoShowWeather(city_name);
            }
        });
    }

    private void gotoShowWeather(String name) {
        Intent intent = new Intent(SelectCityActivity.this, WeatherShowActivity.class);
        intent.putExtra("city", name);
        startActivity(intent);
        finish();
    }

    private void nodtifyListView(String content) {
        arrayAdapter.clear();
        // 添加数据
        for (int i = 0; i < mCityDatas.size(); i++) {
            if (mCityDatas.get(i).contains(content)) {
                listView_temp_data.add(mCityDatas.get(i));
                arrayAdapter.add(mCityDatas.get(i));
            }
        }
        arrayAdapter.notifyDataSetChanged();
    }


    //展开二级
    public void expandGroup() {
        for (int i = 0, length = generalsTypes.size(); i < length; i++) {
            expandableListView.expandGroup(i);
        }
    }

    private void initView() {
        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        et_search = (ClearEditText) findViewById(R.id.et_search);
        ll_hotCity = (LinearLayout) findViewById(R.id.ll_hotCity);
        lv_search = (NoScrollListView) findViewById(R.id.lv_search);
        //去掉箭头
        expandableListView.setGroupIndicator(null);
        //expandableListView父级不能点击
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                return true;
            }
        });
//        sidebar = (SideBar) findViewById(sidebar);
//        dialog = (TextView) findViewById(dialog);
//        sidebar.setTextView(dialog);
        beijing = (TextView) findViewById(R.id.beijing);
        shanghai = (TextView) findViewById(R.id.shanghai);
        shenzhen = (TextView) findViewById(R.id.shenzhen);
        guangzhou = (TextView) findViewById(R.id.guangzhou);
        hangzhou = (TextView) findViewById(R.id.hangzhou);
        chengdu = (TextView) findViewById(R.id.chengdu);
        chongqing = (TextView) findViewById(R.id.chongqing);
        xian = (TextView) findViewById(R.id.xian);
        nanjing = (TextView) findViewById(R.id.nanjing);
        zhengzhou = (TextView) findViewById(R.id.zhengzhou);
        tianjin = (TextView) findViewById(R.id.tianjin);
        wuhan = (TextView) findViewById(R.id.wuhan);

        beijing.setOnClickListener(this);
        shanghai.setOnClickListener(this);
        shenzhen.setOnClickListener(this);
        guangzhou.setOnClickListener(this);
        hangzhou.setOnClickListener(this);
        chengdu.setOnClickListener(this);
        chongqing.setOnClickListener(this);
        xian.setOnClickListener(this);
        nanjing.setOnClickListener(this);
        zhengzhou.setOnClickListener(this);
        tianjin.setOnClickListener(this);
        wuhan.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.beijing:
                gotoShowWeather("北京");
                break;
            case R.id.shanghai:
                gotoShowWeather("上海");
                break;
            case R.id.shenzhen:
                gotoShowWeather("深圳");
                break;
            case R.id.guangzhou:
                gotoShowWeather("广州");
                break;
            case R.id.hangzhou:
                gotoShowWeather("杭州");
                break;
            case R.id.chengdu:
                gotoShowWeather("成都");
                break;
            case R.id.chongqing:
                gotoShowWeather("重庆");
                break;
            case R.id.xian:
                gotoShowWeather("西安");
                break;
            case R.id.nanjing:
                gotoShowWeather("南京");
                break;
            case R.id.wuhan:
                gotoShowWeather("武汉");
                break;
            case R.id.zhengzhou:
                gotoShowWeather("郑州");
                break;
            case R.id.tianjin:
                gotoShowWeather("天津");
                break;
            default:
                break;
        }
    }

    /**
     * 读取地址数据，请使用线程进行调用
     *
     * @return
     */
    protected boolean readAddrDatas() {
        List<ProvinceInfoModel> provinceList = null;
        AssetManager asset = getAssets();
        try {
            InputStream input = asset.open("province_data.xml");
            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser parser = spf.newSAXParser();
            AddrXmlParser handler = new AddrXmlParser();
            parser.parse(input, handler);
            input.close();
            provinceList = handler.getDataList();
            if (provinceList != null && !provinceList.isEmpty()) {
                mCurrentProviceName = provinceList.get(0).getName();
                List<CityInfoModel> cityList = provinceList.get(0).getCityList();
                if (cityList != null && !cityList.isEmpty()) {
                    mCurrentCityName = cityList.get(0).getName();
                }
            }
            mProvinceDatas = new ArrayList<String>();
            for (int i = 0; i < provinceList.size(); i++) {
                mProvinceDatas.add(provinceList.get(i).getName());
                List<CityInfoModel> cityList = provinceList.get(i).getCityList();
                ArrayList<String> cityNames = new ArrayList<String>();
                for (int j = 0; j < cityList.size(); j++) {
                    mCityDatas.add(cityList.get(j).getName());
                }
//                mCitisDatasMap.put(provinceList.get(i).getName(), cityNames);
            }
            return true;
        } catch (Throwable e) {
            e.printStackTrace();
            return false;
        }
    }

    private void initData() {
        Collator cmp = Collator.getInstance(java.util.Locale.CHINA);
        Collections.sort(mCityDatas, cmp);
        for (String str : mCityDatas) {
            String sortKey = getPinYinFirstLetter(str);
            if (!generalsTypes.contains(sortKey)) {
                generalsTypes.add(sortKey);
            }
            Log.e("SSSSS", "sortKey:" + sortKey);
            List<String> list = map.get(sortKey);
            if (list == null) {
                list = new ArrayList<>();
                map.put(sortKey, list);
            }
            list.add(str);
        }
        map = sortMapByKey(map);
    }

    /**
     * 使用 Map按key进行排序
     *
     * @param map
     * @return
     */
    public static HashMap<String, List<String>> sortMapByKey(HashMap<String, List<String>> map) {
//        if (map == null || map.isEmpty()) {
//            return null;
//        }
//        Map<String, List<String>> sortMap = new TreeMap<String, List<String>>(new Comparator<String>() {
//            @Override
//            public int compare(String str1, String str2) {
//                return str1.compareTo(str2);
//            }
//        });
//        sortMap.putAll(map);
//        return sortMap;
        HashMap<String, List<String>> sortMap = new HashMap<>();
        Object[] key_arr = map.keySet().toArray();
        Arrays.sort(key_arr);
        for (Object key : key_arr) {
            Log.e("SSSSS", "key:" + key);
            List<String> value = map.get(key);
            sortMap.put((String) key, value);
        }
        return sortMap;
    }

    /**
     * 获取汉字字符串的第一个字母
     */
    public static String getPinYinFirstLetter(String str) {
        StringBuffer sb = new StringBuffer();
        char c = str.charAt(0);
        String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(c);
        if (pinyinArray != null) {
            sb.append(pinyinArray[0].charAt(0));
        } else {
            sb.append(c);
        }
        return sb.toString();
    }


}
