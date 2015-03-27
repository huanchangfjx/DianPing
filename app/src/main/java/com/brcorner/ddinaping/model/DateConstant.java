package com.brcorner.ddinaping.model;

import com.brcorner.ddinaping.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DateConstant {
    public static List<CityBean> getCitys() {
        List<CityBean> list = new ArrayList<>();
        String cityJson = "{'cities':['上海','北京','杭州','广州','南京','苏州','深圳','成都','重庆','天津','宁波','扬州','无锡','福州','厦门','武汉','西安','沈阳','大连','青岛','济南','海口','石家庄','唐山','秦皇岛','邯郸','邢台','保定','张家口','承德','沧州','廊坊','衡水','太原','大同','阳泉','长治','晋城','朔州','晋中','运城','忻州','临汾','吕梁','呼和浩特','包头','乌海','赤峰','通辽','鄂尔多斯','呼伦贝尔','兴安盟','锡林郭勒','乌兰察布','巴彦淖尔','阿拉善','鞍山','抚顺','本溪','丹东','锦州','营口','阜新','辽阳','盘锦','铁岭','朝阳','葫芦岛','长春','吉林','四平','辽源','通化','白山','松原','白城','延边','哈尔滨','齐齐哈尔','鸡西','鹤岗','双鸭山','大庆','伊春','佳木斯','七台河','牡丹江','黑河','绥化','大兴安岭','徐州','常州','南通','连云港','淮安','盐城','镇江','泰州','宿迁','温州','嘉兴','湖州','绍兴','金华','衢州','舟山','台州','丽水','合肥','芜湖','蚌埠','淮南','马鞍山','淮北','铜陵','安庆','黄山','滁州','阜阳','宿州','六安','亳州','池州','宣城','莆田','三明','泉州','漳州','南平','龙岩','宁德','南昌','景德镇','萍乡','九江','新余','鹰潭','赣州','吉安','宜春','抚州','上饶','淄博','枣庄','东营','烟台','潍坊','济宁','泰安','威海','日照','莱芜','临沂','德州','聊城','滨州','菏泽','郑州','开封','洛阳','平顶山','安阳','鹤壁','新乡','焦作','濮阳','许昌','漯河','三门峡','南阳','商丘','信阳','周口','驻马店','黄石','十堰','宜昌','襄阳','鄂州','荆门','孝感','荆州','黄冈','咸宁','随州','恩施州','仙桃','潜江','天门','株洲','湘潭','衡阳','邵阳','岳阳','常德','张家界','益阳','郴州','永州','怀化','娄底','湘西','韶关','珠海','汕头','佛山','江门','湛江','茂名','肇庆','惠州','梅州','汕尾','河源','阳江','清远','东莞','中山','潮州','揭阳','云浮','南宁','柳州','桂林','梧州','北海','防城港','钦州','贵港','玉林','百色','贺州','河池','自贡','攀枝花','泸州','德阳','绵阳','广元','遂宁','内江','乐山','南充','眉山','宜宾','广安','达州','雅安','巴中','资阳','阿坝','甘孜州','凉山','贵阳','六盘水','遵义','安顺','铜仁地区','黔西南','毕节地区','黔东南','黔南','昆明','曲靖','玉溪','保山','昭通','楚雄州','红河','文山州','普洱','西双版纳','大理州','德宏','丽江','怒江','迪庆','临沧','拉萨','昌都地区','山南','日喀则地区','那曲','阿里','林芝地区','铜川','宝鸡','咸阳','渭南','延安','汉中','榆林','安康','商洛','兰州','嘉峪关','金昌','白银','天水','武威','张掖','平凉','酒泉','庆阳','定西','陇南','临夏州','甘南','西宁','海东','海北','黄南','果洛','玉树','海西','银川','石嘴山','吴忠','固原','乌鲁木齐','克拉玛依','吐鲁番地区','哈密地区','昌吉州','博尔塔拉','巴音郭楞','阿克苏地区','克孜勒苏','喀什地区','和田地区','伊犁','塔城地区','阿勒泰地区','石河子','香港','澳门','长沙','三亚','中卫','儋州','保亭','昌江','澄迈县','崇左','定安县','东方','济源','来宾','乐东','陵水','琼海','神农架林区','图木舒克','屯昌县','万宁','文昌','海南州']}";
        try {
            JSONObject jo1 = new JSONObject(cityJson);

            JSONArray ja1 = jo1.getJSONArray("cities");

            for (int i = 0; i < ja1.length(); i++) {
                String cityName = ja1.getString(i);
                CityBean cityBean = new CityBean();
                cityBean.setCityName(cityName);
                list.add(cityBean);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<CityBean> getForeignCitys() {
        List<CityBean> list = new ArrayList<>();
        String cityJson = "{'cities':['维也纳','札幌','卡莱达','汉堡','加布罗沃','莱比锡','好莱坞','戛纳','筑波','巴黎','雷克雅未克','昆明','瓦杜兹','底特律市','里约热内卢','华沙','赞比亚卢萨卡','德黑兰','慕尼黑','伯尔尼','蒲甘','拉斯维加斯','利马','威尼斯','乞拉朋齐','华盛顿','伦敦','罗马','牛津','开罗','加德满都','科伦坡','墨西哥','雅典','耶路撒冷','基隆','西宁','曼谷']}";
        try {
            JSONObject jo1 = new JSONObject(cityJson);

            JSONArray ja1 = jo1.getJSONArray("cities");

            for (int i = 0; i < ja1.length(); i++) {
                String cityName = ja1.getString(i);
                CityBean cityBean = new CityBean();
                cityBean.setCityName(cityName);
                list.add(cityBean);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }


    // 第一个listview的图片数据数组
// 第一个listview的图片数据数组
    public static int[] LISTVIEWIMG = new int[]{
            R.drawable.ic_category_2147483648, R.drawable.ic_category_10,
            R.drawable.ic_category_20, R.drawable.ic_category_30,
            R.drawable.ic_category_45, R.drawable.ic_category_50,
            R.drawable.ic_category_55, R.drawable.ic_category_60,
            R.drawable.ic_category_65, R.drawable.ic_category_70,
            R.drawable.ic_category_80, R.drawable.ic_category_none};
    // 第一个listview的文本数据数组
    public static String[] LISTVIEWTXT = new String[]{"热门分类", "美食", "购物",
            "休闲娱乐", "运动健身", "丽人", "结婚", "酒店", "爱车", "亲子", "生活服务", "家装"};
    // 第二个listview的文本数据
    public static String[][] MORELISTTXT = {
            {"全部分类", "小吃快餐", "咖啡厅", "电影院", "KTV", "茶馆", "足疗按摩", "超市/便利店",
                    "银行", "经济型酒店", "景点/郊游", "公园", "美发"},
            {"全部美食", "小吃快餐", "西餐", "火锅", "北京菜", "川菜", "日本", "面包甜点", "粤菜",
                    "韩国料理", "自助餐", "浙江菜", "云南菜", "湘菜", "东南亚菜", "西北菜", "鲁菜",
                    "东北菜", "素菜", "新疆菜", "海鲜", "清真菜", "贵州菜", "湖北菜", "其他"},
            {"全部购物", "综合商场", "服饰鞋包", "超市/便利店", "特色集市", "品牌折扣店", "眼镜店", "珠宝饰品",
                    "化妆品", "运动户外", "食品茶酒", "书店", "数码产品", "药店", "京味儿购物", "亲子购物",
                    "花店", "家具建材", "更多购物场所"},
            {"全部休闲娱乐", "咖啡厅", "KTV", "景点/郊游", "电影院", "酒吧", "公园", "温泉", "文化艺术",
                    "足疗按摩", "洗浴", "茶馆", "游乐游艺", "密室", "采摘/农家乐", "桌面游戏", "台球馆",
                    "DIY手工坊", "休闲网吧", "真人CS", "棋牌室", "轰趴馆", "私人影院", "更多休闲娱乐"},
            {"全部运动健身", "健身中心", "游泳馆", "瑜伽", "羽毛球馆", "台球馆", "舞蹈", "体育场馆",
                    "高尔夫场", "网球场", "武术场馆", "篮球场", "保龄球馆", "足球场", "乒乓球馆",
                    "更多体育运动"},
            {"全部丽人", "美发", "美容/SPA", "齿科", "美甲", "化妆品", "瑜伽", "瘦身纤体", "舞蹈",
                    "个性写真", "整形"},
            {"全部结婚", "婚纱摄影", "婚宴酒店", "婚纱礼服", "婚庆公司", "婚戒首饰", "个性写真", "彩妆造型",
                    "婚礼小礼品", "婚礼跟拍", "婚车租赁", "司仪主持", "婚房装修", "更多婚礼服务"},
            {"全部酒店", "经济型酒店", "五星级酒店", "度假村", "四星级酒店", "三星级酒店", "农家院",
                    "公寓式酒店", "青年旅社", "精品酒店", "更多酒店住宿"},
            {"全部爱车", "维修保养", "驾校", "停车场", "4S店/汽车销售", "加油站", "配件/车饰", "汽车租赁",
                    "汽车保险"},
            {"全部亲子", "亲子摄影", "幼儿教育", "亲子游乐", "孕产护理", "亲子购物", "更多亲子服务"},
            {"全部生活服务", "医院", "银行", "齿科", "宠物", "培训", "快照/冲印", "学校", "旅行社",
                    "购物网站", "干洗店", "家政", "奢侈品护理", "商务楼", "小区", "更多生活服务"},
            {"全部家装", "家具家装", "家用电器", "建材", "家装卖场", "装修设计"}};
    // shoplist中toplist文本
    public static String[] SHOPLIST_TOPLIST = new String[]{"全部商户", "团购商户",
            "可预订商户", "会员卡商户", "优惠券商户", "新增商户"};
    // shoplist中排序文本
    public static String[] SHOPLIST_THREELIST = {"默认排序", "距离最近", "人气最高",
            "评价最好", "口味最佳", "环境最雅", "服务最好", "费用最低", "费用最高"};
    // shoplist中商区文本
    public static String[] SHOPLIST_PLACE = new String[]{"附近", "全城热门商区",
            "道里区", "道外区", "南岗区", "香坊区", "平房区", "松北区", "呼兰区", "近郊"};
    // 美食全部地区数组2
    public static String[][] SHOPLIST_PLACESTREET = new String[][]{
            {"500米", "1000米", "2000米", "5000米"},
            {"全部商区", "中央大街", "开发区", "秋林", "哈工大", "菜艺街", "爱建社区", "芦家街/宣化街",
                    "新阳路", "学府路", "三大动力路", "革新街", "江畔景区", "南极区", "和兴路沿线",
                    "哈尔滨东站", "群力地区", "军工院", "阿城区",},
            {"全部道里区", "中央大街", "爱建社区", "新阳路", "群力地区", "顾乡"},
            {"全部道外区", "江畔景区", "南极街", "哈尔滨东站", "太平桥", "靖宇街沿线", "宏伟路"},
            {"全部南岗区", "开发区", "秋林", "哈工大", "芦家街/宣化街", "学府路", "革新街", "和兴路沿线",
                    "军工院", "哈尔滨站", "哈西大街",},
            {"全部香坊区", "菜艺街", "三大动力路", "民生路", "木材街", "安埠街",},
            {"全部平房区", "新疆大街",},
            {"全部松北区", "太阳岛", "世茂大道", "中源大道",},
            {"全部呼兰区", "学院路",},
            {"全部近郊", "阿城区", "尚志市", "五常市", "宾县", "方正县", "延寿县", "双城市", "通河县",
                    "巴彦县", "木兰县", "依兰县",}};
    // 排行榜中toplist数据
    public static String[] PAIHANGBANG_TOPLIST = new String[]{"餐厅排行榜",
            "休闲娱乐排行榜", "购物排行榜", "丽人排行榜", "运动健身排行榜", "酒店排行榜", "生活服务排行榜"};

    public static List<ItemBean> getGoodsList() {
        List<ItemBean> lst = new ArrayList<ItemBean>();
        ItemBean itemBean = new ItemBean();
        itemBean.setItemImgId(R.drawable.item_img1);
        itemBean.setItemName("咖啡陪你Caffe Bene");
        itemBean.setItemContent("[16店通用]代金券 可叠加，不限时段通用，免费wifi");
        itemBean.setItemPrice("￥35");
        itemBean.setSelledNum("已售27522");
        lst.add(itemBean);
        ItemBean itemBean2 = new ItemBean();
        itemBean2.setItemImgId(R.drawable.item_img2);
        itemBean2.setItemName("感智盲人保健会所");
        itemBean2.setItemContent("[7店通用] 韩蒸按摩套餐 7店通用，畅快淋漓的汗蒸，全身与颈椎的按摩");
        itemBean2.setItemPrice("￥19");
        itemBean2.setSelledNum("已售9265");
        lst.add(itemBean2);
        ItemBean itemBean3 = new ItemBean();
        itemBean3.setItemImgId(R.drawable.item_img3);
        itemBean3.setItemName("肯打鸡");
        itemBean3.setItemContent("[249店通用] 上海肯打鸡代金券 不限时段通用");
        itemBean3.setItemPrice("￥18");
        itemBean3.setSelledNum("已售27563");
        lst.add(itemBean3);
        ItemBean itemBean4 = new ItemBean();
        itemBean4.setItemImgId(R.drawable.item_img4);
        itemBean4.setItemName("今钰点心");
        itemBean4.setItemContent("[塘桥] 一个人的营养餐");
        itemBean4.setItemPrice("￥19");
        itemBean4.setSelledNum("已售181");
        lst.add(itemBean4);
        return lst;
    }
}
