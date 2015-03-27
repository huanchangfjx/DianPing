package com.brcorner.ddinaping.utils;

import com.brcorner.ddinaping.model.CityBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Auser on 2015/3/23.
 */
public class SortUtils {

    public static List<CityBean> change2EnOrderList(
            List<CityBean> arr1) {
        //转换为只有英文字符串，如果是中文  只有首字母
        List<CityBean> arrlist = new ArrayList<CityBean>();
        for (int i = 0; i < arr1.size(); i++) {
            String s = arr1.get(i).getCityName();
            if (s == null) {
                s = arr1.get(i).getIndex();
            }
            char ch = s.charAt(0);
            if (PinyinConv.isChinese(ch)) {
                s = PinyinConv.cn2py(s);
            }
            ch = s.charAt(0);
            if (!((ch > 64 && ch < 91) || (ch > 96 && ch < 123))) {
                s = (char) 124 + s;
            }
            CityBean invBean = new CityBean();
            invBean.setCityName(s);
            arrlist.add(invBean);
        }

        //进行排序 按字母顺序
        for (int i = 1; i < arrlist.size(); i++) {
            CityBean cityEn = arrlist.get(i);
            CityBean cityNormal = arr1.get(i);
            for (int j = i - 1; j >= 0
                    && scompareTo(cityEn.getCityName().toLowerCase(), arrlist
                    .get(j).getCityName().toLowerCase()); j--) {
                arrlist.set(j + 1, arrlist.get(j));
                arrlist.set(j, cityEn);
                arr1.set(j + 1, arr1.get(j));
                arr1.set(j, cityNormal);
            }
        }
        return arrlist;
    }

    //比较 s1与s2的ASC码值
    private static Boolean scompareTo(String s1, String s2) {
        int i = s1.compareTo(s2);
        if (i > 0)
            return false;
        else
            return true;
    }
}
