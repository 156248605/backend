package com.elex.oa.util.util_shiyun;

/**
 * @Author:ShiYun;
 * @Description:分割获得城市的方法
 * @Date:Created in  14:33 2018\9\25 0025
 * @Modify By:
 */
public class GetProvinceMethod {

    /**
     *@Author:ShiYun;
     *@Description:获得其所在的省份/城市/区县(因为会报Java代码太长错误，所以要分割方法)
     *@Date: 14:56 2018\9\25 0025
     */
    public static String getProvinceInformationsByCode(String provinceCode,String cityCode,String countyCode){
        String returnValue = "";
        switch (provinceCode){
            //北京市的身份证前六位对应关系==============================================================================
            case "11":{
                returnValue = GetProvinceMethod.getProvinceNameByProvinceCode11(cityCode, countyCode);
            }break;
            //天津市的身份证前六位对应关系==============================================================================
            case "12":{
                returnValue = GetProvinceMethod.getProvinceNameByProvinceCode12(cityCode, countyCode);
            }break;
            //河北省的身份证前六位对应关系==============================================================================
            case "13":{
                returnValue = GetProvinceMethod.getProvinceNameByProvinceCode13(cityCode, countyCode);
            }break;
            //山西省的身份证前六位对应关系==============================================================================
            case "14":{
                returnValue = GetProvinceMethod.getProvinceNameByProvinceCode14(cityCode, countyCode);
            }break;
            //内蒙古自治区的身份证前六位对应关系==============================================================================
            case "15":{
                returnValue = GetProvinceMethod.getProvinceNameByProvinceCode15(cityCode, countyCode);
            }break;
            //辽宁省的身份证前六位对应关系==============================================================================
            case "21":{
                returnValue = GetProvinceMethod.getProvinceNameByProvinceCode21(cityCode, countyCode);
            }break;
            //吉林省的身份证前六位对应关系==============================================================================
            case "22":{
                returnValue = GetProvinceMethod.getProvinceNameByProvinceCode22(cityCode, countyCode);
            }break;
            //黑龙江省的身份证前六位对应关系==============================================================================
            case "23":{
                returnValue = GetProvinceMethod.getProvinceNameByProvinceCode23(cityCode, countyCode);
            }break;
            //上海市的身份证前六位对应关系==============================================================================
            case "31":{
                returnValue = GetProvinceMethod.getProvinceNameByProvinceCode31(cityCode, countyCode);
            }break;
            //江苏省的身份证前六位对应关系==============================================================================
            case "32":{
                returnValue = GetProvinceMethod.getProvinceNameByProvinceCode32(cityCode, countyCode);
            }break;
            //浙江省的身份证前六位对应关系==============================================================================
            case "33":{
                returnValue = GetProvinceMethod.getProvinceNameByProvinceCode33(cityCode, countyCode);
            }break;
            //安徽省的身份证前六位对应关系==============================================================================
            case "34":{
                returnValue = GetProvinceMethod.getProvinceNameByProvinceCode34(cityCode, countyCode);
            }break;
            //福建省的身份证前六位对应关系==============================================================================
            case "35":{
                returnValue = GetProvinceMethod.getProvinceNameByProvinceCode35(cityCode, countyCode);
            }break;
            //江西省的身份证前六位对应关系==============================================================================
            case "36":{
                returnValue = GetProvinceMethod.getProvinceNameByProvinceCode36(cityCode, countyCode);
            }break;
            //山东省的身份证前六位对应关系==============================================================================
            case "37":{
                returnValue = GetProvinceMethod.getProvinceNameByProvinceCode37(cityCode, countyCode);
            }break;
            //河南省的身份证前六位对应关系==============================================================================
            case "41":{
                returnValue = GetProvinceMethod.getProvinceNameByProvinceCode41(cityCode, countyCode);
            }break;
            //湖北省的身份证前六位对应关系==============================================================================
            case "42":{
                returnValue = GetProvinceMethod.getProvinceNameByProvinceCode42(cityCode, countyCode);
            }break;
            //湖南省的身份证前六位对应关系==============================================================================
            case "43":{
                returnValue = GetProvinceMethod.getProvinceNameByProvinceCode43(cityCode, countyCode);
            }break;
            //广东省的身份证前六位对应关系==============================================================================
            case "44":{
                returnValue = GetProvinceMethod.getProvinceNameByProvinceCode44(cityCode, countyCode);
            }break;
            //广西壮族自治区的身份证前六位对应关系==============================================================================
            case "45":{
                returnValue = GetProvinceMethod.getProvinceNameByProvinceCode45(cityCode, countyCode);
            }break;
            //海南省的身份证前六位对应关系==============================================================================
            case "46":{
                returnValue = GetProvinceMethod.getProvinceNameByProvinceCode46(cityCode, countyCode);
            }break;
            //重庆市的身份证前六位对应关系==============================================================================
            case "50":{
                returnValue = GetProvinceMethod.getProvinceNameByProvinceCode50(cityCode, countyCode);
            }break;
            //四川省的身份证前六位对应关系==============================================================================
            case "51":{
                returnValue = GetProvinceMethod.getProvinceNameByProvinceCode51(cityCode, countyCode);
            }break;
            //贵州省的身份证前六位对应关系==============================================================================
            case "52":{
                returnValue = GetProvinceMethod.getProvinceNameByProvinceCode52(cityCode, countyCode);
            }break;
            //云南省的身份证前六位对应关系==============================================================================
            case "53":{
                returnValue = GetProvinceMethod.getProvinceNameByProvinceCode53(cityCode, countyCode);
            }break;
            //西藏自治区的身份证前六位对应关系==============================================================================
            case "54":{
                returnValue = GetProvinceMethod.getProvinceNameByProvinceCode54(cityCode, countyCode);
            }break;
            //陕西省的身份证前六位对应关系==============================================================================
            case "61":{
                returnValue = GetProvinceMethod.getProvinceNameByProvinceCode61(cityCode, countyCode);
            }break;
            //甘肃省的身份证前六位对应关系==============================================================================
            case "62":{
                returnValue = GetProvinceMethod.getProvinceNameByProvinceCode62(cityCode, countyCode);
            }break;
            //青海省的身份证前六位对应关系==============================================================================
            case "63":{
                returnValue = GetProvinceMethod.getProvinceNameByProvinceCode63(cityCode, countyCode);
            }break;
            //宁夏回族自治区的身份证前六位对应关系==============================================================================
            case "64":{
                returnValue = GetProvinceMethod.getProvinceNameByProvinceCode64(cityCode, countyCode);
            }break;
            //新疆维吾尔自治区的身份证前六位对应关系==============================================================================
            case "65":{
                returnValue = GetProvinceMethod.getProvinceNameByProvinceCode65(cityCode, countyCode);
            }break;
            //台湾省的身份证前六位对应关系1==============================================================================
            case "71":{
                returnValue = GetProvinceMethod.getProvinceNameByProvinceCode83(cityCode, countyCode);
            }break;
            //香港特别行政区的身份证前六位对应关系==============================================================================
            case "81":{
                returnValue = GetProvinceMethod.getProvinceNameByProvinceCode81(cityCode, countyCode);
            }break;
            //澳门特别行政区的身份证前六位对应关系==============================================================================
            case "82":{
                returnValue = GetProvinceMethod.getProvinceNameByProvinceCode82(cityCode, countyCode);
            }break;
            //台湾省的身份证前六位对应关系2==============================================================================
            case "83":{
                returnValue = GetProvinceMethod.getProvinceNameByProvinceCode83(cityCode, countyCode);
            }break;
            default:{
              String  provinceName = "(身份证号码1-2位有误)";
              String  cityName = "--";
              String  countyName = "--";
              returnValue = provinceName+"/"+cityName+"/"+countyName;
            }break;
        }
        return returnValue;
    }

    /**
     *@Author:ShiYun;
     *@Description:获得北京市的方法
     *@Date: 14:34 2018\9\25 0025
     */
    private static String getProvinceNameByProvinceCode11(String cityCode,String countyCode){
        String provinceName = "北京市";
        String cityName = "";
        String countyName = "";
        switch (cityCode){
            case "01":{
                cityName="--";
                switch (countyCode){
                    case "01":countyName="东城区";  break;
                    case "02":countyName="西城区";  break;
                    case "03":countyName="崇文区";  break;
                    case "04":countyName="宣武区";  break;
                    case "05":countyName="朝阳区";  break;
                    case "06":countyName="丰台区";  break;
                    case "07":countyName="石景山区";break;
                    case "08":countyName="海淀区";  break;
                    case "09":countyName="门头沟区";break;
                    case "10":countyName="燕山区";  break;
                    case "11":countyName="房山区";  break;
                    case "12":countyName="通州区";  break;
                    case "13":countyName="顺义区";  break;
                    case "14":countyName="昌平区";  break;
                    case "15":countyName="大兴区";  break;
                    case "16":countyName="怀柔区";  break;
                    case "17":countyName="平谷区";  break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            case "02":{
                cityName="--";
                switch (countyCode){
                    case "21":countyName="昌平县";  break;
                    case "22":countyName="顺义县";  break;
                    case "23":countyName="通县";    break;
                    case "24":countyName="大兴县";  break;
                    case "25":countyName="房山县";  break;
                    case "26":countyName="平谷县";  break;
                    case "27":countyName="怀柔县";  break;
                    case "28":countyName="密云县";  break;
                    case "29":countyName="延庆县";  break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            default:{
                cityName="(身份证号码3-4位有误)";
                countyName="--";
            }break;
        }
        return provinceName+"/"+cityName+"/"+countyName;
    }

    /**
     *@Author:ShiYun;
     *@Description:获得天津市的方法
     *@Date: 14:43 2018\9\25 0025
     */
    private static String getProvinceNameByProvinceCode12(String cityCode,String countyCode){
        String provinceName = "天津市";
        String cityName = "";
        String countyName = "";
        switch (cityCode){
            case "01":{
                cityName="--";
                switch (countyCode){
                    case "01":countyName="和平区";break;
                    case "02":countyName="河东区";break;
                    case "03":countyName="河西区";break;
                    case "04":countyName="南开区";break;
                    case "05":countyName="河北区";break;
                    case "06":countyName="红桥区";break;
                    case "07":countyName="塘沽区";break;
                    case "08":countyName="汉沽区";break;
                    case "09":countyName="大港区";break;
                    case "10":countyName="东丽区";break;
                    case "11":countyName="西青区";break;
                    case "12":countyName="津南区";break;
                    case "13":countyName="北辰区";break;
                    case "14":countyName="武清区";break;
                    case "15":countyName="宝坻区";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            case "02":{
                cityName="--";
                switch (countyCode){
                    case "21":countyName="宁河县";break;
                    case "22":countyName="武清县";break;
                    case "23":countyName="静海县";break;
                    case "24":countyName="宝坻县";break;
                    case "25":countyName="蓟县";  break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            default:{
                cityName="(身份证号码3-4位有误)";
                countyName="--";
            }break;
        }
        return provinceName+"/"+cityName+"/"+countyName;
    }

    /**
     *@Author:ShiYun;
     *@Description:获得河北省的方法
     *@Date: 14:45 2018\9\25 0025
     */
    private static String getProvinceNameByProvinceCode13(String cityCode,String countyCode){
        String provinceName = "河北省";
        String cityName = "";
        String countyName = "";
        switch (cityCode){
            //河北省石家庄市的**********
            case "01":{
                cityName="石家庄市";
                switch (countyCode){
                    case "01":countyName="市辖区";break;
                    case "02":countyName="长安区";break;
                    case "03":countyName="桥东区";break;
                    case "04":countyName="桥西区";break;
                    case "05":countyName="新华区";break;
                    case "06":countyName="郊区";break;
                    case "07":countyName="井陉矿区";break;
                    case "08":countyName="裕华区";break;
                    case "21":countyName="井陉县";break;
                    case "22":countyName="获鹿县";break;
                    case "23":countyName="正定县";break;
                    case "24":countyName="栾城县";break;
                    case "25":countyName="行唐县";break;
                    case "26":countyName="灵寿县";break;
                    case "27":countyName="高邑县";break;
                    case "28":countyName="深泽县";break;
                    case "29":countyName="赞皇县";break;
                    case "30":countyName="无极限";break;
                    case "31":countyName="平山县";break;
                    case "32":countyName="元氏县";break;
                    case "33":countyName="赵县";break;
                    case "81":countyName="辛集县";break;
                    case "82":countyName="藁城市";break;
                    case "83":countyName="晋州市";break;
                    case "84":countyName="新乐市";break;
                    case "85":countyName="鹿泉市";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //河北省唐山市的**********
            case "02":{
                cityName="唐山市";
                switch (countyCode){
                    case "01":countyName="市辖市";break;
                    case "02":countyName="路南区";break;
                    case "03":countyName="路北区";break;
                    case "04":countyName="古冶区";break;
                    case "05":countyName="开平区";break;
                    case "06":countyName="新区";break;
                    case "07":countyName="丰南区";break;
                    case "08":countyName="丰润区";break;
                    case "21":countyName="丰润县";break;
                    case "22":countyName="丰南县";break;
                    case "23":countyName="滦县";break;
                    case "24":countyName="滦南县";break;
                    case "25":countyName="乐亭县";break;
                    case "26":countyName="迁安县";break;
                    case "27":countyName="迁西县";break;
                    case "28":countyName="遵化县";break;
                    case "29":countyName="玉田县";break;
                    case "30":countyName="唐海县";break;
                    case "81":countyName="遵化市";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //河北省秦皇岛市的**********
            case "03":{
                cityName="秦皇岛市";
                switch (countyCode){
                    case "01":countyName="市辖区";break;
                    case "02":countyName="海港区";break;
                    case "03":countyName="山海关区";break;
                    case "04":countyName="北戴河区";break;
                    case "21":countyName="青龙满族自治县";break;
                    case "22":countyName="昌黎县";break;
                    case "23":countyName="抚宁县";break;
                    case "24":countyName="卢龙县";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //河北省邯郸市的**********
            case "04":{
                cityName="邯郸市";
                switch (countyCode){
                    case "01":countyName="市辖区";break;
                    case "02":countyName="邯山区";break;
                    case "03":countyName="丛台区";break;
                    case "04":countyName="复兴区";break;
                    case "05":countyName="郊区";break;
                    case "06":countyName="峰峰矿区";break;
                    case "21":countyName="邯郸县";break;
                    case "22":countyName="武安县";break;
                    case "23":countyName="临漳县";break;
                    case "24":countyName="成安县";break;
                    case "25":countyName="大名县";break;
                    case "26":countyName="涉县";break;
                    case "27":countyName="磁县";break;
                    case "28":countyName="肥乡县";break;
                    case "29":countyName="永年县";break;
                    case "30":countyName="邱县";break;
                    case "31":countyName="鸡泽县";break;
                    case "32":countyName="广平县";break;
                    case "33":countyName="馆陶县";break;
                    case "34":countyName="魏县";break;
                    case "35":countyName="曲周县";break;
                    case "81":countyName="武安市";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //河北省邢台市的**********
            case "05":{
                cityName="邢台市";
                switch (countyCode){
                    case "01":countyName="市辖区";break;
                    case "02":countyName="桥东区";break;
                    case "03":countyName="桥西区";break;
                    case "21":countyName="邢台县";break;
                    case "22":countyName="临城县";break;
                    case "23":countyName="内丘县";break;
                    case "24":countyName="柏乡县";break;
                    case "25":countyName="隆尧县";break;
                    case "26":countyName="任县";break;
                    case "27":countyName="南和县";break;
                    case "28":countyName="宁晋县";break;
                    case "29":countyName="巨鹿县";break;
                    case "30":countyName="新和县";break;
                    case "31":countyName="广宗县";break;
                    case "32":countyName="平乡县";break;
                    case "33":countyName="威县";break;
                    case "34":countyName="清河县";break;
                    case "35":countyName="临西县";break;
                    case "81":countyName="南宫市";break;
                    case "82":countyName="沙河市";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //河北省保定市的**********
            case "06":{
                cityName="保定市";
                switch (countyCode){
                    case "01":countyName="市辖区";break;
                    case "02":countyName="新市区";break;
                    case "03":countyName="北市区";break;
                    case "04":countyName="南市区";break;
                    case "21":countyName="满城县";break;
                    case "22":countyName="清苑县";break;
                    case "23":countyName="涞水县";break;
                    case "24":countyName="阜平县";break;
                    case "25":countyName="徐水县";break;
                    case "26":countyName="定兴县";break;
                    case "27":countyName="唐县";break;
                    case "28":countyName="高阳县";break;
                    case "29":countyName="容城县";break;
                    case "30":countyName="涞源县";break;
                    case "31":countyName="望都县";break;
                    case "32":countyName="安新县";break;
                    case "33":countyName="易县";break;
                    case "34":countyName="曲阳县";break;
                    case "35":countyName="蠡县";break;
                    case "36":countyName="顺平县";break;
                    case "37":countyName="博野县";break;
                    case "38":countyName="雄县";break;
                    case "81":countyName="涿州市";break;
                    case "82":countyName="定州市";break;
                    case "83":countyName="安国市";break;
                    case "84":countyName="高碑店市";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //河北省张家口市的**********
            case "07":{
                cityName="张家口市";
                switch (countyCode){
                    case "01":countyName="市辖区";break;
                    case "02":countyName="桥东区";break;
                    case "03":countyName="桥西区";break;
                    case "05":countyName="宣化区";break;
                    case "06":countyName="下花园区";break;
                    case "21":countyName="宣化县";break;
                    case "22":countyName="张北县";break;
                    case "23":countyName="康保县";break;
                    case "24":countyName="沽源县";break;
                    case "25":countyName="尚义县";break;
                    case "26":countyName="蔚县";break;
                    case "27":countyName="阳原县";break;
                    case "28":countyName="怀安县";break;
                    case "29":countyName="万全县";break;
                    case "30":countyName="怀来县";break;
                    case "31":countyName="涿鹿县";break;
                    case "32":countyName="赤城县";break;
                    case "33":countyName="崇礼县";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //河北省承德市的**********
            case "08":{
                cityName="承德市";
                switch (countyCode){
                    case "01":countyName="市辖区";break;
                    case "02":countyName="双桥区";break;
                    case "03":countyName="双滦区";break;
                    case "04":countyName="鹰手营子矿区";break;
                    case "21":countyName="承德县";break;
                    case "22":countyName="兴隆县";break;
                    case "23":countyName="平原县";break;
                    case "24":countyName="滦平县";break;
                    case "25":countyName="隆化县";break;
                    case "26":countyName="丰宁满族自治县";break;
                    case "27":countyName="宽城满族自治县";break;
                    case "28":countyName="围场满族蒙古族自治县";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //河北省沧州市的**********
            case "09":{
                cityName="沧州市";
                switch (countyCode){
                    case "01":countyName="市辖区";break;
                    case "02":countyName="新华区";break;
                    case "03":countyName="运河区";break;
                    case "04":countyName="郊区";break;
                    case "21":countyName="沧县";break;
                    case "22":countyName="青县";break;
                    case "23":countyName="东光县";break;
                    case "24":countyName="海兴县";break;
                    case "25":countyName="盐山县";break;
                    case "26":countyName="肃宁县";break;
                    case "27":countyName="南皮县";break;
                    case "28":countyName="吴桥县";break;
                    case "29":countyName="献县";break;
                    case "30":countyName="孟村回族自治县";break;
                    case "81":countyName="泊头市";break;
                    case "82":countyName="任丘市";break;
                    case "83":countyName="黄骅市";break;
                    case "84":countyName="河间市";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //河北省廊坊市的**********
            case "10":{
                cityName="廊坊市";
                switch (countyCode){
                    case "01":countyName="市辖区";break;
                    case "02":countyName="安次区";break;
                    case "03":countyName="广阳区";break;
                    case "21":countyName="三河县";break;
                    case "22":countyName="固安县";break;
                    case "23":countyName="永清县";break;
                    case "24":countyName="香河县";break;
                    case "25":countyName="大城县";break;
                    case "26":countyName="文安县";break;
                    case "27":countyName="霸县";break;
                    case "28":countyName="大厂回族自治县";break;
                    case "29":countyName="献县";break;
                    case "81":countyName="霸州市";break;
                    case "82":countyName="三河市";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //河北省衡水市的**********
            case "11":{
                cityName="衡水市";
                switch (countyCode){
                    case "01":countyName="市辖区";break;
                    case "02":countyName="桃城区";break;
                    case "21":countyName="枣强县";break;
                    case "22":countyName="武邑县";break;
                    case "23":countyName="武强县";break;
                    case "24":countyName="饶阳县";break;
                    case "25":countyName="安平县";break;
                    case "26":countyName="故城县";break;
                    case "27":countyName="景县";break;
                    case "28":countyName="阜城县";break;
                    case "81":countyName="冀州市";break;
                    case "82":countyName="深州市";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //河北省邯郸地区的**********
            case "21":{
                cityName="邯郸地区";
                switch (countyCode){
                    case "01":countyName="邯郸市";break;
                    case "21":countyName="大名县";break;
                    case "22":countyName="魏县";break;
                    case "23":countyName="曲周县";break;
                    case "24":countyName="邱县";break;
                    case "25":countyName="鸡泽县";break;
                    case "26":countyName="肥乡县";break;
                    case "27":countyName="广平县";break;
                    case "28":countyName="成安县";break;
                    case "29":countyName="临漳县";break;
                    case "30":countyName="磁县";break;
                    case "31":countyName="武安县";break;
                    case "32":countyName="涉县";break;
                    case "33":countyName="永年县";break;
                    case "34":countyName="邯郸县";break;
                    case "35":countyName="馆陶县";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //河北省邢台地区的**********
            case "22":{
                cityName="邢台地区";
                switch (countyCode){
                    case "01":countyName="南宫市";break;
                    case "02":countyName="沙河市";break;
                    case "21":countyName="邢台县";break;
                    case "22":countyName="沙河县";break;
                    case "23":countyName="临城县";break;
                    case "24":countyName="内丘县";break;
                    case "25":countyName="柏乡县";break;
                    case "26":countyName="隆尧县";break;
                    case "27":countyName="任县";break;
                    case "28":countyName="南和县";break;
                    case "29":countyName="宁晋县";break;
                    case "30":countyName="南宫县";break;
                    case "31":countyName="巨鹿县";break;
                    case "32":countyName="新河县";break;
                    case "33":countyName="广宗县";break;
                    case "34":countyName="平乡县";break;
                    case "35":countyName="威县";break;
                    case "36":countyName="清河县";break;
                    case "37":countyName="临西县";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //河北省石家庄市的**********
            case "23":{
                cityName="石家庄市";
                switch (countyCode){
                    case "01":countyName="辛集市";break;
                    case "02":countyName="藁城市";break;
                    case "21":countyName="束鹿县";break;
                    case "22":countyName="晋县";break;
                    case "23":countyName="深泽县";break;
                    case "24":countyName="无极县";break;
                    case "25":countyName="藁城县";break;
                    case "26":countyName="赵县";break;
                    case "27":countyName="栾城县";break;
                    case "28":countyName="正定县";break;
                    case "29":countyName="新乐县";break;
                    case "30":countyName="高邑县";break;
                    case "31":countyName="元氏县";break;
                    case "32":countyName="赞皇县";break;
                    case "33":countyName="井陉县";break;
                    case "34":countyName="获鹿县";break;
                    case "35":countyName="平山县";break;
                    case "36":countyName="灵寿县";break;
                    case "37":countyName="行唐县";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //河北省保定地区的**********
            case "24":{
                cityName="保定地区";
                switch (countyCode){
                    case "01":countyName="定州市";break;
                    case "02":countyName="涿州市";break;
                    case "03":countyName="安国市";break;
                    case "04":countyName="高碑店市";break;
                    case "21":countyName="易县";break;
                    case "22":countyName="满城县";break;
                    case "23":countyName="徐水县";break;
                    case "24":countyName="涞源县";break;
                    case "25":countyName="定兴县";break;
                    case "26":countyName="完县";break;
                    case "27":countyName="唐县";break;
                    case "28":countyName="望都县";break;
                    case "29":countyName="涞水县";break;
                    case "30":countyName="涿县";break;
                    case "31":countyName="清苑县";break;
                    case "32":countyName="高阳县";break;
                    case "33":countyName="安新县";break;
                    case "34":countyName="雄县";break;
                    case "35":countyName="容城县";break;
                    case "36":countyName="新城县";break;
                    case "37":countyName="曲阳县";break;
                    case "38":countyName="阜平县";break;
                    case "39":countyName="定县";break;
                    case "40":countyName="安国县";break;
                    case "41":countyName="博野县";break;
                    case "42":countyName="蠡县";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //河北省张家口地区的**********
            case "25":{
                cityName="张家口地区";
                switch (countyCode){
                    case "01":countyName="张家口市";break;
                    case "21":countyName="张北市";break;
                    case "22":countyName="康保县";break;
                    case "23":countyName="沽源县";break;
                    case "24":countyName="尚义县";break;
                    case "25":countyName="蔚县";break;
                    case "26":countyName="阳原县";break;
                    case "27":countyName="怀安县";break;
                    case "28":countyName="万全县";break;
                    case "29":countyName="怀来县";break;
                    case "30":countyName="涿鹿县";break;
                    case "31":countyName="宣化县";break;
                    case "32":countyName="赤城县";break;
                    case "33":countyName="崇礼县";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //河北省承德地区的**********
            case "26":{
                cityName="承德地区";
                switch (countyCode){
                    case "01":countyName="承德市";break;
                    case "21":countyName="青龙县";break;
                    case "22":countyName="宽城满族自治县";break;
                    case "23":countyName="兴隆县";break;
                    case "24":countyName="平原县";break;
                    case "25":countyName="承德县";break;
                    case "26":countyName="滦平县";break;
                    case "27":countyName="丰宁满族自治县";break;
                    case "28":countyName="隆化县";break;
                    case "29":countyName="围场满族蒙古族自治县";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //河北省廊坊地区的**********
            case "28":{
                cityName="廊坊地区";
                switch (countyCode){
                    case "01":countyName="廊坊市";break;
                    case "21":countyName="三河县";break;
                    case "22":countyName="大厂回族自治县";break;
                    case "23":countyName="香河县";break;
                    case "25":countyName="永清县";break;
                    case "26":countyName="固安县";break;
                    case "27":countyName="霸县";break;
                    case "28":countyName="文安县";break;
                    case "29":countyName="大城县";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //河北省沧州地区的**********
            case "29":{
                cityName="沧州地区";
                switch (countyCode){
                    case "01":countyName="沧州市";break;
                    case "02":countyName="泊头市";break;
                    case "03":countyName="任丘市";break;
                    case "04":countyName="黄骅市";break;
                    case "05":countyName="河间市";break;
                    case "21":countyName="沧县";break;
                    case "22":countyName="河间县";break;
                    case "23":countyName="肃宁县";break;
                    case "24":countyName="献县";break;
                    case "25":countyName="交河县";break;
                    case "26":countyName="吴桥县";break;
                    case "27":countyName="东光县";break;
                    case "28":countyName="南皮县";break;
                    case "29":countyName="盐山县";break;
                    case "30":countyName="黄骅县";break;
                    case "31":countyName="孟村回族自治县";break;
                    case "32":countyName="青县";break;
                    case "33":countyName="任丘县";break;
                    case "34":countyName="海兴县";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //河北省衡水地区的**********
            case "30":{
                cityName="衡水地区";
                switch (countyCode){
                    case "01":countyName="衡水市";break;
                    case "02":countyName="冀州市";break;
                    case "21":countyName="衡水县";break;
                    case "22":countyName="冀县";break;
                    case "23":countyName="枣强县";break;
                    case "24":countyName="武邑县";break;
                    case "25":countyName="深县";break;
                    case "26":countyName="武强县";break;
                    case "27":countyName="饶阳县";break;
                    case "28":countyName="安平县";break;
                    case "29":countyName="故城县";break;
                    case "30":countyName="景县";break;
                    case "31":countyName="阜城县";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //河北省武安市的**********
            case "90":{
                cityName="--";
                if (countyCode.equals("01")) {
                    countyName="武安市";
                } else {
                    countyName="(身份证号码5-6位有误)";
                }
            }break;
            default:{
                cityName="(身份证号码3-4位有误)";
                countyName="--";
            }break;
        }
        return provinceName+"/"+cityName+"/"+countyName;
    }

    /**
     *@Author:ShiYun;
     *@Description:获得山西省的方法
     *@Date: 14:48 2018\9\25 0025
     */
    private static String getProvinceNameByProvinceCode14(String cityCode,String countyCode){
        String provinceName = "山西省";
        String cityName = "";
        String countyName = "";
        switch (cityCode){
            //山西省太原市的**********
            case "01":{
                cityName="太原市";
                switch (countyCode){
                    case "01":countyName="市辖区";break;
                    case "02":countyName="南城区";break;
                    case "03":countyName="北城区";break;
                    case "04":countyName="河西区";break;
                    case "05":countyName="小店区";break;
                    case "06":countyName="迎泽区";break;
                    case "07":countyName="杏花岭区";break;
                    case "08":countyName="尖草坪区";break;
                    case "09":countyName="万柏林区";break;
                    case "10":countyName="晋源区";break;
                    case "11":countyName="古交工矿区";break;
                    case "12":countyName="南郊区";break;
                    case "13":countyName="北郊区";break;
                    case "21":countyName="清徐县";break;
                    case "22":countyName="阳曲县";break;
                    case "23":countyName="娄烦县";break;
                    case "81":countyName="古交市";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //山西省大同市的**********
            case "02":{
                cityName="大同市";
                switch (countyCode){
                    case "01":countyName="市辖区";break;
                    case "02":countyName="城区";break;
                    case "03":countyName="矿区";break;
                    case "11":countyName="南郊区";break;
                    case "12":countyName="新荣区";break;
                    case "21":countyName="阳高区";break;
                    case "22":countyName="天镇县";break;
                    case "23":countyName="广灵县";break;
                    case "24":countyName="灵丘县";break;
                    case "25":countyName="浑源县";break;
                    case "26":countyName="左云县";break;
                    case "27":countyName="大同县";break;
                    case "30":countyName="左云县";break;
                    case "32":countyName="大同县";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //山西省阳泉市的**********
            case "03":{
                cityName="阳泉市";
                switch (countyCode){
                    case "01":countyName="市辖区";break;
                    case "02":countyName="城区";break;
                    case "03":countyName="矿区";break;
                    case "11":countyName="郊区";break;
                    case "21":countyName="平定县";break;
                    case "22":countyName="盂县";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //山西省长治市的**********
            case "04":{
                cityName="长治市";
                switch (countyCode){
                    case "01":countyName="市辖区";break;
                    case "02":countyName="城区";break;
                    case "11":countyName="郊区";break;
                    case "21":countyName="长治县";break;
                    case "23":countyName="襄垣县";break;
                    case "24":countyName="屯留县";break;
                    case "25":countyName="平顺县";break;
                    case "26":countyName="黎城县";break;
                    case "27":countyName="壶关县";break;
                    case "28":countyName="长子县";break;
                    case "29":countyName="武乡县";break;
                    case "30":countyName="沁县";break;
                    case "31":countyName="沁源县";break;
                    case "81":countyName="潞城市";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //山西省晋城市的**********
            case "05":{
                cityName="晋城市";
                switch (countyCode){
                    case "01":countyName="市辖区";break;
                    case "02":countyName="城区";break;
                    case "11":countyName="郊区";break;
                    case "21":countyName="沁水县";break;
                    case "22":countyName="阳城县";break;
                    case "23":countyName="高平县";break;
                    case "24":countyName="陵川县";break;
                    case "25":countyName="泽州县";break;
                    case "81":countyName="高平市";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //山西省朔州市的**********
            case "06":{
                cityName="朔州市";
                switch (countyCode){
                    case "01":countyName="市辖区";break;
                    case "02":countyName="朔城区";break;
                    case "03":countyName="平鲁区";break;
                    case "21":countyName="山阴县";break;
                    case "22":countyName="应县";break;
                    case "23":countyName="左玉县";break;
                    case "24":countyName="怀仁县";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //山西省晋中市的**********
            case "07":{
                cityName="晋中市";
                switch (countyCode){
                    case "01":countyName="市辖区";break;
                    case "02":countyName="榆次区";break;
                    case "21":countyName="榆社县";break;
                    case "22":countyName="左权县";break;
                    case "23":countyName="和顺县";break;
                    case "24":countyName="昔阳县";break;
                    case "25":countyName="寿阳县";break;
                    case "26":countyName="太谷县";break;
                    case "27":countyName="祁县";break;
                    case "28":countyName="平遥县";break;
                    case "29":countyName="灵石县";break;
                    case "81":countyName="介休县";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //山西省运城市的**********
            case "08":{
                cityName="运城市";
                switch (countyCode){
                    case "01":countyName="市辖区";break;
                    case "02":countyName="盐湖区";break;
                    case "21":countyName="临猗县";break;
                    case "22":countyName="万荣县";break;
                    case "23":countyName="闻喜县";break;
                    case "24":countyName="稷山县";break;
                    case "25":countyName="新绛县";break;
                    case "26":countyName="绛县";break;
                    case "27":countyName="垣曲县";break;
                    case "28":countyName="夏县";break;
                    case "29":countyName="平陆县";break;
                    case "30":countyName="芮城县";break;
                    case "81":countyName="永济市";break;
                    case "82":countyName="河津市";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //山西省忻州市的**********
            case "09":{
                cityName="忻州市";
                switch (countyCode){
                    case "01":countyName="市辖区";break;
                    case "02":countyName="忻府区";break;
                    case "21":countyName="定襄县";break;
                    case "22":countyName="五台县";break;
                    case "23":countyName="代县";break;
                    case "24":countyName="繁峙县";break;
                    case "25":countyName="宁武县";break;
                    case "26":countyName="静乐县";break;
                    case "27":countyName="神池县";break;
                    case "28":countyName="五寨县";break;
                    case "29":countyName="岢岚县";break;
                    case "30":countyName="河曲县";break;
                    case "31":countyName="保德县";break;
                    case "32":countyName="偏关县";break;
                    case "81":countyName="原平市";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //山西省临汾市的**********
            case "10":{
                cityName="临汾市";
                switch (countyCode){
                    case "01":countyName="市辖区";break;
                    case "02":countyName="尧都区";break;
                    case "21":countyName="曲沃县";break;
                    case "22":countyName="翼城县";break;
                    case "23":countyName="襄汾县";break;
                    case "24":countyName="洪洞县";break;
                    case "25":countyName="古县";break;
                    case "26":countyName="安泽县";break;
                    case "27":countyName="浮山县";break;
                    case "28":countyName="吉县";break;
                    case "29":countyName="乡宁县";break;
                    case "30":countyName="大宁县";break;
                    case "31":countyName="隰县";break;
                    case "32":countyName="永和县";break;
                    case "33":countyName="蒲县";break;
                    case "34":countyName="汾西县";break;
                    case "81":countyName="侯马市";break;
                    case "82":countyName="霍州市";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //山西省吕梁市的**********
            case "11":{
                cityName="吕梁市";
                switch (countyCode){
                    case "01":countyName="市辖区";break;
                    case "02":countyName="离石区";break;
                    case "21":countyName="文水县";break;
                    case "22":countyName="交城县";break;
                    case "23":countyName="兴县";break;
                    case "24":countyName="临县";break;
                    case "25":countyName="柳林县";break;
                    case "26":countyName="石楼县";break;
                    case "27":countyName="岚县";break;
                    case "28":countyName="方山县";break;
                    case "29":countyName="中阳县";break;
                    case "30":countyName="交口县";break;
                    case "81":countyName="孝义市";break;
                    case "82":countyName="汾阳市";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //山西省雁北地区的**********
            case "21":{
                cityName="雁北地区";
                switch (countyCode){
                    case "21":countyName="阳高县";break;
                    case "22":countyName="天镇县";break;
                    case "23":countyName="广灵县";break;
                    case "24":countyName="灵丘县";break;
                    case "25":countyName="浑源县";break;
                    case "26":countyName="应县";break;
                    case "27":countyName="山阴县";break;
                    case "28":countyName="朔县";break;
                    case "29":countyName="平鲁县";break;
                    case "30":countyName="左云县";break;
                    case "31":countyName="右玉县";break;
                    case "32":countyName="大同县";break;
                    case "33":countyName="怀仁县";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //山西省忻州地区的**********
            case "22":{
                cityName="忻州地区";
                switch (countyCode){
                    case "01":countyName="忻州市";break;
                    case "02":countyName="原平市";break;
                    case "22":countyName="定襄县";break;
                    case "23":countyName="五台县";break;
                    case "25":countyName="代县";break;
                    case "26":countyName="繁峙县";break;
                    case "27":countyName="宁武县";break;
                    case "28":countyName="静乐县";break;
                    case "29":countyName="神池县";break;
                    case "30":countyName="五寨县";break;
                    case "31":countyName="岢岚县";break;
                    case "32":countyName="河曲县";break;
                    case "33":countyName="保德县";break;
                    case "34":countyName="偏关县";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //山西省吕梁地区的**********
            case "23":{
                cityName="吕梁地区";
                switch (countyCode){
                    case "01":countyName="孝义市";break;
                    case "02":countyName="离石市";break;
                    case "03":countyName="汾阳市";break;
                    case "21":countyName="汾阳县";break;
                    case "22":countyName="文水县";break;
                    case "23":countyName="交城县";break;
                    case "25":countyName="兴县";break;
                    case "26":countyName="临县";break;
                    case "27":countyName="柳林县";break;
                    case "28":countyName="石楼县";break;
                    case "29":countyName="岚县";break;
                    case "30":countyName="方山县";break;
                    case "32":countyName="中阳县";break;
                    case "33":countyName="交口县";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //山西省晋中地区的**********
            case "24":{
                cityName="晋中地区";
                switch (countyCode){
                    case "01":countyName="榆次市";break;
                    case "02":countyName="介休市";break;
                    case "21":countyName="榆社县";break;
                    case "22":countyName="左权县";break;
                    case "23":countyName="和顺县";break;
                    case "24":countyName="昔阳县";break;
                    case "27":countyName="寿阳县";break;
                    case "29":countyName="太谷县";break;
                    case "30":countyName="祁县";break;
                    case "31":countyName="平遥县";break;
                    case "33":countyName="灵石县";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //山西省临汾地区的**********
            case "26":{
                cityName="临汾地区";
                switch (countyCode){
                    case "01":countyName="临汾市";break;
                    case "02":countyName="侯马市";break;
                    case "03":countyName="霍州市";break;
                    case "21":countyName="曲沃县";break;
                    case "22":countyName="翼城县";break;
                    case "23":countyName="襄汾县";break;
                    case "25":countyName="洪洞县";break;
                    case "27":countyName="古县";break;
                    case "28":countyName="安泽县";break;
                    case "29":countyName="浮山县";break;
                    case "30":countyName="吉县";break;
                    case "31":countyName="乡宁县";break;
                    case "32":countyName="蒲县";break;
                    case "33":countyName="大宁县";break;
                    case "34":countyName="永和县";break;
                    case "35":countyName="隰县";break;
                    case "36":countyName="汾西县";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //山西省运城地区的**********
            case "27":{
                cityName="运城地区";
                switch (countyCode){
                    case "01":countyName="运城市";break;
                    case "02":countyName="永济市";break;
                    case "03":countyName="河津市";break;
                    case "23":countyName="芮城县";break;
                    case "24":countyName="临猗县";break;
                    case "25":countyName="万荣县";break;
                    case "26":countyName="新绛县";break;
                    case "27":countyName="稷山县";break;
                    case "29":countyName="闻喜县";break;
                    case "30":countyName="夏县";break;
                    case "31":countyName="绛县";break;
                    case "32":countyName="平陆县";break;
                    case "33":countyName="垣曲县";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //山西省古交市的**********
            case "90":{
                cityName="--";
                if (countyCode.equals("01")) {
                    countyName="古交市";
                } else {
                    countyName="(身份证号码5-6位有误)";
                }
            }break;
            default:{
                cityName="(身份证号码3-4位有误)";
                countyName="--";
            }break;
        }
        return provinceName+"/"+cityName+"/"+countyName;
    }

    /**
     *@Author:ShiYun;
     *@Description:获得内蒙古自治区的方法
     *@Date: 14:50 2018\9\25 0025
     */
    private static String getProvinceNameByProvinceCode15(String cityCode,String countyCode){
        String provinceName = "内蒙古自治区";
        String cityName = "";
        String countyName = "";
        switch (cityCode){
            //内蒙古自治区--呼和浩特市**********
            case "01":{
                cityName="呼和浩特市";
                switch (countyCode){
                    case "01":countyName="市辖区";break;
                    case "02":countyName="新城区";break;
                    case "03":countyName="回民区";break;
                    case "04":countyName="玉泉区";break;
                    case "05":countyName="郊区";break;
                    case "21":countyName="土默特左旗";break;
                    case "22":countyName="托克托县";break;
                    case "23":countyName="和林格尔县";break;
                    case "24":countyName="清水河县";break;
                    case "25":countyName="武川县";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //内蒙古自治区--包头市**********
            case "02":{
                cityName="包头市";
                switch (countyCode){
                    case "01":countyName="市辖区";break;
                    case "02":countyName="东河区";break;
                    case "03":countyName="昆都仑区";break;
                    case "04":countyName="青山区";break;
                    case "05":countyName="石拐矿区";break;
                    case "06":countyName="白云矿区";break;
                    case "07":countyName="郊区";break;
                    case "21":countyName="土默特右旗";break;
                    case "22":countyName="固阳县";break;
                    case "23":countyName="达尔罕茂明安联合旗";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //内蒙古自治区--乌海市**********
            case "03":{
                cityName="乌海市";
                switch (countyCode){
                    case "01":countyName="市辖区";break;
                    case "02":countyName="海勃湾区";break;
                    case "03":countyName="海南区";break;
                    case "04":countyName="乌达区";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //内蒙古自治区--赤峰市**********
            case "04":{
                cityName="赤峰市";
                switch (countyCode){
                    case "01":countyName="市辖区";break;
                    case "02":countyName="红山区";break;
                    case "03":countyName="元宝山区";break;
                    case "04":countyName="松山区";break;
                    case "21":countyName="阿鲁科尔沁旗";break;
                    case "22":countyName="巴林左旗";break;
                    case "23":countyName="巴林右旗";break;
                    case "24":countyName="林西县";break;
                    case "25":countyName="克尔克腾旗";break;
                    case "26":countyName="翁牛特旗";break;
                    case "28":countyName="喀喇沁旗";break;
                    case "29":countyName="宁城县";break;
                    case "30":countyName="敖汉旗";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //内蒙古自治区--鄂尔多斯市**********
            case "05":{
                cityName="通辽市";
                switch (countyCode){
                    case "01":countyName="市辖区";break;
                    case "02":countyName="科尔沁区";break;
                    case "21":countyName="科尔沁左翼中旗";break;
                    case "22":countyName="科尔沁左翼后旗";break;
                    case "23":countyName="开鲁县";break;
                    case "24":countyName="库伦旗";break;
                    case "25":countyName="奈曼旗";break;
                    case "26":countyName="扎鲁特旗";break;
                    case "81":countyName="霍林郭勒市";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //内蒙古自治区--鄂尔多斯市**********
            case "06":{
                cityName="鄂尔多斯市";
                switch (countyCode){
                    case "01":countyName="鄂尔多斯市";break;
                    case "02":countyName="东胜区";break;
                    case "21":countyName="达拉特旗";break;
                    case "22":countyName="准格尔旗";break;
                    case "23":countyName="鄂托克前旗";break;
                    case "24":countyName="鄂托克旗";break;
                    case "25":countyName="杭锦旗";break;
                    case "26":countyName="乌审旗";break;
                    case "27":countyName="伊金霍洛旗";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //内蒙古自治区--呼伦贝尔市**********
            case "07":{
                cityName="呼伦贝尔市";
                switch (countyCode){
                    case "01":countyName="市辖区";break;
                    case "02":countyName="海拉尔区";break;
                    case "21":countyName="阿荣旗";break;
                    case "22":countyName="莫力达瓦达斡尔族自治旗";break;
                    case "23":countyName="鄂伦春自治旗";break;
                    case "24":countyName="鄂温克族自治旗";break;
                    case "25":countyName="陈巴尔虎旗";break;
                    case "26":countyName="新巴尔虎左旗";break;
                    case "27":countyName="新巴尔虎右旗";break;
                    case "81":countyName="满洲里市";break;
                    case "82":countyName="牙克石市";break;
                    case "83":countyName="扎兰屯市";break;
                    case "84":countyName="额尔古纳市";break;
                    case "85":countyName="根河市";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //内蒙古自治区--巴彦淖尔市**********
            case "08":{
                cityName="巴彦淖尔市";
                switch (countyCode){
                    case "01":countyName="市辖区";break;
                    case "02":countyName="临河区";break;
                    case "21":countyName="五原县";break;
                    case "22":countyName="磴口县";break;
                    case "23":countyName="乌拉特前旗";break;
                    case "24":countyName="乌拉特中旗";break;
                    case "25":countyName="乌拉特后旗";break;
                    case "26":countyName="杭锦后旗";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //内蒙古自治区--乌兰察布市**********
            case "09":{
                cityName="乌兰察布市";
                switch (countyCode){
                    case "01":countyName="市辖区";break;
                    case "02":countyName="集宁区";break;
                    case "21":countyName="卓资县";break;
                    case "22":countyName="化德县";break;
                    case "23":countyName="商都县";break;
                    case "24":countyName="兴和县";break;
                    case "25":countyName="凉城县";break;
                    case "26":countyName="察哈尔右翼前旗";break;
                    case "27":countyName="察哈尔右翼中旗";break;
                    case "28":countyName="察哈尔右翼后旗";break;
                    case "29":countyName="四子王旗";break;
                    case "81":countyName="丰镇市";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //内蒙古自治区--呼伦贝尔盟**********
            case "21":{
                cityName="呼伦贝尔盟";
                switch (countyCode){
                    case "01":countyName="海拉尔市";break;
                    case "02":countyName="满洲里市";break;
                    case "03":countyName="扎兰屯市";break;
                    case "04":countyName="牙克石市";break;
                    case "05":countyName="根河市";break;
                    case "06":countyName="额尔古纳市";break;
                    case "21":countyName="布特哈旗";break;
                    case "22":countyName="阿荣旗";break;
                    case "23":countyName="莫力达瓦达斡尔族自治旗";break;
                    case "24":countyName="喜桂图旗";break;
                    case "25":countyName="额尔古纳右旗";break;
                    case "26":countyName="额尔古纳左旗";break;
                    case "27":countyName="鄂伦春自治旗";break;
                    case "28":countyName="鄂温克族自治旗";break;
                    case "29":countyName="新巴尔虎右旗";break;
                    case "30":countyName="新巴尔虎左旗";break;
                    case "31":countyName="陈巴尔虎旗";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //内蒙古自治区--兴安盟**********
            case "22":{
                cityName="兴安盟";
                switch (countyCode){
                    case "01":countyName="乌兰浩特市";break;
                    case "02":countyName="阿尔山市";break;
                    case "21":countyName="科尔沁右翼前旗";break;
                    case "22":countyName="科尔沁右翼中旗";break;
                    case "23":countyName="扎赉特旗";break;
                    case "24":countyName="突泉县";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //内蒙古自治区--哲里木盟**********
            case "23":{
                cityName="哲里木盟";
                switch (countyCode){
                    case "01":countyName="通辽市";break;
                    case "02":countyName="霍林郭勒市";break;
                    case "21":countyName="通辽县";break;
                    case "22":countyName="科尔沁右翼中旗";break;
                    case "23":countyName="科尔沁右翼后旗";break;
                    case "24":countyName="开鲁县";break;
                    case "25":countyName="库伦旗";break;
                    case "26":countyName="奈曼旗";break;
                    case "27":countyName="扎鲁特旗";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //内蒙古自治区--锡林郭勒盟**********
            case "25":{
                cityName="锡林郭勒盟";
                switch (countyCode){
                    case "01":countyName="二连浩特市";break;
                    case "02":countyName="锡林浩特市";break;
                    case "22":countyName="阿巴嘎旗";break;
                    case "23":countyName="苏尼特左旗";break;
                    case "24":countyName="苏尼特右旗";break;
                    case "25":countyName="东乌珠穆沁旗";break;
                    case "26":countyName="西乌珠穆沁旗";break;
                    case "27":countyName="太仆寺旗";break;
                    case "28":countyName="镶黄旗";break;
                    case "29":countyName="正镶白旗";break;
                    case "30":countyName="正蓝旗";break;
                    case "31":countyName="多伦县";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //内蒙古自治区--乌兰察布盟**********
            case "26":{
                cityName="乌兰察布盟";
                switch (countyCode){
                    case "01":countyName="集宁市";break;
                    case "02":countyName="丰镇市";break;
                    case "21":countyName="武川县";break;
                    case "22":countyName="和林格尔县";break;
                    case "23":countyName="清水河县";break;
                    case "24":countyName="卓资县";break;
                    case "25":countyName="化德县";break;
                    case "26":countyName="商都县";break;
                    case "27":countyName="兴和县";break;
                    case "28":countyName="丰镇县";break;
                    case "29":countyName="凉城县";break;
                    case "30":countyName="察哈尔右翼前旗";break;
                    case "31":countyName="察哈尔右翼中旗";break;
                    case "32":countyName="察哈尔右翼后旗";break;
                    case "33":countyName="达尔罕茂明安联合旗";break;
                    case "34":countyName="四子王旗";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //内蒙古自治区--伊克昭盟**********
            case "27":{
                cityName="伊克昭盟";
                switch (countyCode){
                    case "01":countyName="东胜市";break;
                    case "21":countyName="东胜县";break;
                    case "22":countyName="达拉特旗";break;
                    case "23":countyName="准格尔旗";break;
                    case "24":countyName="鄂托克前旗";break;
                    case "25":countyName="鄂托克旗";break;
                    case "26":countyName="杭锦旗";break;
                    case "27":countyName="乌审旗";break;
                    case "28":countyName="伊金霍洛旗";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //内蒙古自治区--巴彦淖尔盟**********
            case "28":{
                cityName="巴彦淖尔盟";
                switch (countyCode){
                    case "01":countyName="临河市";break;
                    case "21":countyName="临河县";break;
                    case "22":countyName="五原县";break;
                    case "23":countyName="磴口市";break;
                    case "24":countyName="乌拉特前旗";break;
                    case "25":countyName="乌拉特中旗";break;
                    case "26":countyName="乌拉特后旗";break;
                    case "27":countyName="杭锦后旗";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //内蒙古自治区--阿拉善盟**********
            case "29":{
                cityName="阿拉善盟";
                switch (countyCode){
                    case "21":countyName="阿拉善左旗";break;
                    case "22":countyName="阿拉善右旗";break;
                    case "23":countyName="额济纳旗";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //********************************
            default:{
                cityName="(身份证号码3-4位有误)";
                countyName="--";
            }break;
        }
        return provinceName+"/"+cityName+"/"+countyName;
    }

    /**
     *@Author:ShiYun;
     *@Description:获得辽宁省的方法
     *@Date: 14:52 2018\9\25 0025
     */
    private static String getProvinceNameByProvinceCode21(String cityCode,String countyCode){
        String provinceName = "辽宁省";
        String cityName = "";
        String countyName = "";
        switch (cityCode){
            //辽宁省--沈阳市**********
            case "01":{
                cityName="沈阳市";
                switch (countyCode){
                    case "01":countyName="市辖区";break;
                    case "02":countyName="和平区";break;
                    case "03":countyName="沈河区";break;
                    case "04":countyName="大东区";break;
                    case "05":countyName="皇姑区";break;
                    case "06":countyName="铁西区";break;
                    case "11":countyName="苏家屯区";break;
                    case "12":countyName="东陵区";break;
                    case "13":countyName="沈北新区";break;
                    case "14":countyName="于洪区";break;
                    case "20":countyName="市区";break;
                    case "21":countyName="新民县";break;
                    case "22":countyName="辽中县";break;
                    case "23":countyName="康平县";break;
                    case "24":countyName="法库县";break;
                    case "81":countyName="新民市";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //辽宁省--大连市**********
            case "02":{
                cityName="大连市";
                switch (countyCode){
                    case "01":countyName="市辖区";break;
                    case "02":countyName="中山区";break;
                    case "03":countyName="西岗区";break;
                    case "04":countyName="沙河口区";break;
                    case "11":countyName="甘井子区";break;
                    case "12":countyName="旅顺口区";break;
                    case "13":countyName="金州区";break;
                    case "19":countyName="瓦房店市";break;
                    case "20":countyName="市区";break;
                    case "21":countyName="金县";break;
                    case "22":countyName="新金县";break;
                    case "23":countyName="复县";break;
                    case "24":countyName="长海县";break;
                    case "25":countyName="庄河县";break;
                    case "81":countyName="瓦房店市";break;
                    case "82":countyName="普兰店市";break;
                    case "83":countyName="庄河市";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //辽宁省--鞍山市**********
            case "03":{
                cityName="鞍山市";
                switch (countyCode){
                    case "01":countyName="市辖区";break;
                    case "02":countyName="铁东区";break;
                    case "03":countyName="铁西区";break;
                    case "04":countyName="立山区";break;
                    case "11":countyName="千山区";break;
                    case "19":countyName="开发区";break;
                    case "21":countyName="台安县";break;
                    case "23":countyName="岫岩满族自治县";break;
                    case "81":countyName="海城市";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //辽宁省--抚顺市**********
            case "04":{
                cityName="抚顺市";
                switch (countyCode){
                    case "01":countyName="市辖区";break;
                    case "02":countyName="新抚区";break;
                    case "03":countyName="东洲区";break;
                    case "04":countyName="望花区";break;
                    case "11":countyName="顺城区";break;
                    case "21":countyName="抚顺县";break;
                    case "22":countyName="新宾满族自治县";break;
                    case "23":countyName="青原满族自治县";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //辽宁省--本溪市**********
            case "05":{
                cityName="本溪市";
                switch (countyCode){
                    case "01":countyName="市辖区";break;
                    case "02":countyName="平山区";break;
                    case "03":countyName="溪湖区";break;
                    case "04":countyName="明山区";break;
                    case "05":countyName="南芬区";break;
                    case "11":countyName="南芬区";break;
                    case "21":countyName="本溪满族自治县";break;
                    case "22":countyName="桓仁满族自治县";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //辽宁省--丹东市**********
            case "06":{
                cityName="丹东市";
                switch (countyCode){
                    case "01":countyName="市辖区";break;
                    case "02":countyName="元宝区";break;
                    case "03":countyName="振兴区";break;
                    case "04":countyName="振安区";break;
                    case "20":countyName="市区";break;
                    case "21":countyName="凤城满族自治县";break;
                    case "22":countyName="岫岩满族自治县";break;
                    case "23":countyName="东沟县";break;
                    case "24":countyName="宽甸满族自治县";break;
                    case "81":countyName="东港市";break;
                    case "82":countyName="凤城市";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //辽宁省--锦州市**********
            case "07":{
                cityName="锦州市";
                switch (countyCode){
                    case "01":countyName="市辖区";break;
                    case "02":countyName="古塔区";break;
                    case "03":countyName="凌河区";break;
                    case "04":countyName="南票区";break;
                    case "05":countyName="葫芦岛区";break;
                    case "11":countyName="太和区";break;
                    case "19":countyName="锦西市";break;
                    case "20":countyName="市区";break;
                    case "21":countyName="锦西县";break;
                    case "22":countyName="兴城县";break;
                    case "23":countyName="绥中县";break;
                    case "24":countyName="锦县";break;
                    case "25":countyName="北镇满族自治县";break;
                    case "26":countyName="黑山县";break;
                    case "27":countyName="义县";break;
                    case "81":countyName="凌海市";break;
                    case "82":countyName="北镇市";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //辽宁省--营口市**********
            case "08":{
                cityName="营口市";
                switch (countyCode){
                    case "01":countyName="市辖区";break;
                    case "02":countyName="站前区";break;
                    case "03":countyName="西市区";break;
                    case "04":countyName="鲅鱼圈区";break;
                    case "11":countyName="老边区";break;
                    case "21":countyName="营口县";break;
                    case "24":countyName="盖县";break;
                    case "81":countyName="盖州市";break;
                    case "82":countyName="大石桥市";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //辽宁省--阜新市**********
            case "09":{
                cityName="阜新市";
                switch (countyCode){
                    case "01":countyName="市辖区";break;
                    case "02":countyName="海州区";break;
                    case "03":countyName="新邱区";break;
                    case "04":countyName="太平区";break;
                    case "05":countyName="清河门区";break;
                    case "11":countyName="细河区";break;
                    case "21":countyName="阜新蒙古族自治县";break;
                    case "22":countyName="彰武县";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //辽宁省--辽阳市**********
            case "10":{
                cityName="辽阳市";
                switch (countyCode){
                    case "01":countyName="市辖区";break;
                    case "02":countyName="白塔区";break;
                    case "03":countyName="文圣区";break;
                    case "04":countyName="宏伟区";break;
                    case "05":countyName="弓长岭区";break;
                    case "11":countyName="太子河区";break;
                    case "21":countyName="辽阳县";break;
                    case "22":countyName="灯塔县";break;
                    case "81":countyName="灯塔市";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //辽宁省--盘锦市**********
            case "11":{
                cityName="盘锦市";
                switch (countyCode){
                    case "01":countyName="市辖区";break;
                    case "02":countyName="双台子区";break;
                    case "03":countyName="兴隆台区";break;
                    case "11":countyName="郊区";break;
                    case "21":countyName="大洼县";break;
                    case "22":countyName="盘山县";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //辽宁省--铁岭市**********
            case "12":{
                cityName="铁岭市";
                switch (countyCode){
                    case "01":countyName="市辖区";break;
                    case "02":countyName="银州区";break;
                    case "03":countyName="铁法区";break;
                    case "11":countyName="清河区";break;
                    case "21":countyName="铁岭县";break;
                    case "22":countyName="开原县";break;
                    case "23":countyName="西丰县";break;
                    case "24":countyName="昌图县";break;
                    case "25":countyName="康平县";break;
                    case "26":countyName="法库县";break;
                    case "81":countyName="调兵山市";break;
                    case "82":countyName="开原市";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //辽宁省--朝阳市**********
            case "13":{
                cityName="朝阳市";
                switch (countyCode){
                    case "01":countyName="市辖区";break;
                    case "02":countyName="双塔区";break;
                    case "03":countyName="龙城区";break;
                    case "21":countyName="朝阳县";break;
                    case "22":countyName="建平县";break;
                    case "23":countyName="凌源市";break;
                    case "24":countyName="喀喇沁左翼蒙古族自治县";break;
                    case "25":countyName="建昌县";break;
                    case "26":countyName="北票县";break;
                    case "81":countyName="北票市";break;
                    case "82":countyName="凌源市";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //辽宁省--葫芦岛市**********
            case "14":{
                cityName="葫芦岛市";
                switch (countyCode){
                    case "01":countyName="市辖区";break;
                    case "02":countyName="连山区";break;
                    case "03":countyName="龙港区";break;
                    case "04":countyName="南票区";break;
                    case "21":countyName="绥中县";break;
                    case "22":countyName="建昌县";break;
                    case "81":countyName="兴城市";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //辽宁省--铁岭市**********
            case "21":{
                cityName="铁岭市";
                switch (countyCode){
                    case "01":countyName="铁岭市";break;
                    case "02":countyName="铁法市";break;
                    case "21":countyName="铁岭县";break;
                    case "22":countyName="开原县";break;
                    case "23":countyName="西丰县";break;
                    case "24":countyName="昌图县";break;
                    case "25":countyName="康平县";break;
                    case "26":countyName="法库县";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //辽宁省--朝阳市**********
            case "22":{
                cityName="朝阳市";
                switch (countyCode){
                    case "25":countyName="建昌县";break;
                    case "26":countyName="北票县";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //辽宁省--其它市********************************
            case "90":{
                cityName="--";
                switch (countyCode){
                    case "01":countyName="瓦房店市";break;
                    case "02":countyName="海城市";break;
                    case "03":countyName="锦西市";break;
                    case "04":countyName="兴城市";break;
                    case "05":countyName="铁法市";break;
                    case "06":countyName="北票市";break;
                    case "07":countyName="开原市";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //********************************
            default:{
                cityName="(身份证号码3-4位有误)";
                countyName="--";
            }break;
        }
        return provinceName+"/"+cityName+"/"+countyName;
    }

    /**
     *@Author:ShiYun;
     *@Description:获得吉林省的方法
     *@Date: 14:54 2018\9\25 0025
     */
    private static String getProvinceNameByProvinceCode22(String cityCode,String countyCode){
        String provinceName = "吉林省";
        String cityName = "";
        String countyName = "";
        switch (cityCode){
            //吉林省--长春市**********
            case "01":{
                cityName="长春市";
                switch (countyCode){
                    case "01":countyName="市辖区";break;
                    case "02":countyName="南关区";break;
                    case "03":countyName="宽城区";break;
                    case "04":countyName="朝阳区";break;
                    case "05":countyName="二道区";break;
                    case "06":countyName="绿园区";break;
                    case "11":countyName="郊区";break;
                    case "12":countyName="双阳区";break;
                    case "20":countyName="市区";break;
                    case "21":countyName="榆树县";break;
                    case "22":countyName="农安县";break;
                    case "23":countyName="九台县";break;
                    case "24":countyName="德惠县";break;
                    case "25":countyName="双阳县";break;
                    case "81":countyName="九台市";break;
                    case "82":countyName="榆树市";break;
                    case "83":countyName="德惠市";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //吉林省--吉林市**********
            case "02":{
                cityName="吉林市";
                switch (countyCode){
                    case "01":countyName="市辖区";break;
                    case "02":countyName="昌邑区";break;
                    case "03":countyName="龙潭区";break;
                    case "04":countyName="船营区";break;
                    case "11":countyName="丰满区";break;
                    case "20":countyName="市区";break;
                    case "21":countyName="永吉县";break;
                    case "22":countyName="舒兰县";break;
                    case "23":countyName="磐石县";break;
                    case "24":countyName="蛟河县";break;
                    case "25":countyName="桦甸县";break;
                    case "81":countyName="蛟河市";break;
                    case "82":countyName="桦甸市";break;
                    case "83":countyName="舒兰市";break;
                    case "84":countyName="磐石市";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //吉林省--四平市**********
            case "03":{
                cityName="四平市";
                switch (countyCode){
                    case "01":countyName="市辖区";break;
                    case "02":countyName="铁西区";break;
                    case "03":countyName="铁东区";break;
                    case "19":countyName="公主岭市";break;
                    case "21":countyName="怀德县";break;
                    case "22":countyName="梨树县";break;
                    case "23":countyName="伊通满族自治县";break;
                    case "24":countyName="双辽县";break;
                    case "81":countyName="公主岭市";break;
                    case "82":countyName="双辽市";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //吉林省--辽源市**********
            case "04":{
                cityName="辽源市";
                switch (countyCode){
                    case "01":countyName="市辖区";break;
                    case "02":countyName="龙山区";break;
                    case "03":countyName="西安区";break;
                    case "21":countyName="东丰县";break;
                    case "22":countyName="东辽县";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //吉林省--通化市**********
            case "05":{
                cityName="通化市";
                switch (countyCode){
                    case "01":countyName="市辖区";break;
                    case "02":countyName="东昌区";break;
                    case "03":countyName="二道江区";break;
                    case "19":countyName="梅河口市";break;
                    case "21":countyName="通化县";break;
                    case "22":countyName="集安县";break;
                    case "23":countyName="辉南县";break;
                    case "24":countyName="柳河县";break;
                    case "81":countyName="梅河口市";break;
                    case "82":countyName="集安市";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //吉林省--白山市**********
            case "06":{
                cityName="白山市";
                switch (countyCode){
                    case "01":countyName="市辖区";break;
                    case "02":countyName="八道江区";break;
                    case "03":countyName="三岔子区";break;
                    case "04":countyName="临江区";break;
                    case "21":countyName="抚松县";break;
                    case "22":countyName="靖宇县";break;
                    case "23":countyName="长白朝鲜族自治县";break;
                    case "25":countyName="江源县";break;
                    case "81":countyName="临江市";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //吉林省--松原市**********
            case "07":{
                cityName="松原市";
                switch (countyCode){
                    case "01":countyName="市辖区";break;
                    case "02":countyName="宁江区";break;
                    case "21":countyName="前郭尔罗斯蒙古族自治县";break;
                    case "22":countyName="长岭县";break;
                    case "23":countyName="乾安县";break;
                    case "24":countyName="扶余县";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //吉林省--白城市**********
            case "08":{
                cityName="白城市";
                switch (countyCode){
                    case "01":countyName="市辖区";break;
                    case "02":countyName="洮北区";break;
                    case "21":countyName="镇赉县";break;
                    case "22":countyName="通榆县";break;
                    case "81":countyName="洮南县";break;
                    case "82":countyName="大安市";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //吉林省--其它市1********************************
            case "21":{
                cityName="--";
                switch (countyCode){
                    case "01":countyName="四平市";break;
                    case "02":countyName="辽源市";break;
                    case "21":countyName="怀德县";break;
                    case "22":countyName="梨树县";break;
                    case "23":countyName="伊通满族自治县";break;
                    case "24":countyName="东丰县";break;
                    case "25":countyName="双辽县";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //吉林省--其它市2********************************
            case "22":{
                cityName="--";
                switch (countyCode){
                    case "01":countyName="通化市";break;
                    case "02":countyName="浑江市";break;
                    case "21":countyName="海龙县";break;
                    case "22":countyName="通化县";break;
                    case "23":countyName="柳河县";break;
                    case "24":countyName="辉南县";break;
                    case "25":countyName="集安县";break;
                    case "26":countyName="抚松县";break;
                    case "27":countyName="靖宇县";break;
                    case "28":countyName="长白朝鲜族自治县";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //吉林省--白城地区**********
            case "23":{
                cityName="白城地区";
                switch (countyCode){
                    case "01":countyName="白城市";break;
                    case "02":countyName="洮南市";break;
                    case "03":countyName="扶余市";break;
                    case "04":countyName="大安市";break;
                    case "21":countyName="扶余县";break;
                    case "22":countyName="洮安县";break;
                    case "23":countyName="长岭县";break;
                    case "24":countyName="前郭尔罗斯蒙古族自治县";break;
                    case "25":countyName="大安县";break;
                    case "26":countyName="镇赉县";break;
                    case "27":countyName="通榆县";break;
                    case "28":countyName="乾安县";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //吉林省--延边朝鲜族自治州**********
            case "24":{
                cityName="延边朝鲜族自治州";
                switch (countyCode){
                    case "01":countyName="延吉市";break;
                    case "02":countyName="图们市";break;
                    case "03":countyName="郭化市";break;
                    case "04":countyName="珲春市";break;
                    case "05":countyName="龙井市";break;
                    case "06":countyName="和龙市";break;
                    case "21":countyName="龙井县";break;
                    case "22":countyName="郭化县";break;
                    case "23":countyName="和龙县";break;
                    case "24":countyName="汪清县";break;
                    case "25":countyName="珲春县";break;
                    case "26":countyName="安图县";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //吉林省--其它市3********************************
            case "90":{
                cityName="--";
                switch (countyCode){
                    case "01":countyName="公主岭市";break;
                    case "02":countyName="梅河口市";break;
                    case "03":countyName="集安市";break;
                    case "04":countyName="桦甸市";break;
                    case "05":countyName="九台市";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //********************************
            default:{
                cityName="(身份证号码3-4位有误)";
                countyName="--";
            }break;
        }
        return provinceName+"/"+cityName+"/"+countyName;
    }

    /**
     *@Author:ShiYun;
     *@Description:获得黑龙江省的方法
     *@Date: 15:01 2018\9\25 0025
     */
    private static String getProvinceNameByProvinceCode23(String cityCode,String countyCode){
        String provinceName = "黑龙江省";
        String cityName = "";
        String countyName = "";
        switch (cityCode){
            //黑龙江省--哈尔滨市************************
            case "01":{
                cityName="哈尔滨市";
                switch (countyCode){
                    case "01":countyName="市辖区";break;
                    case "02":countyName="道里区";break;
                    case "03":countyName="南岗区";break;
                    case "04":countyName="道外区";break;
                    case "05":countyName="太平区";break;
                    case "06":countyName="香坊区";break;
                    case "07":countyName="动力区";break;
                    case "08":countyName="平房区";break;
                    case "09":countyName="松北区";break;
                    case "10":countyName="香坊区";break;
                    case "11":countyName="呼兰区";break;
                    case "12":countyName="阿城区";break;
                    case "19":countyName="阿城市";break;
                    case "21":countyName="呼兰县";break;
                    case "22":countyName="阿城县";break;
                    case "23":countyName="依兰县";break;
                    case "24":countyName="方正县";break;
                    case "25":countyName="宾县";break;
                    case "26":countyName="巴彦县";break;
                    case "27":countyName="木兰县";break;
                    case "28":countyName="通河县";break;
                    case "29":countyName="延寿县";break;
                    case "81":countyName="阿城市";break;
                    case "82":countyName="双城市";break;
                    case "83":countyName="尚志市";break;
                    case "84":countyName="五常市";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //黑龙江省--齐齐哈尔市************************
            case "02":{
                cityName="齐齐哈尔市";
                switch (countyCode){
                    case "01":countyName="市辖区";break;
                    case "02":countyName="龙沙区";break;
                    case "03":countyName="建华区";break;
                    case "04":countyName="铁锋区";break;
                    case "05":countyName="昂昂溪区";break;
                    case "06":countyName="富拉尔基区";break;
                    case "07":countyName="碾子山区";break;
                    case "08":countyName="梅里斯达翰尔族区";break;
                    case "21":countyName="龙江县";break;
                    case "22":countyName="讷河县";break;
                    case "23":countyName="依安县";break;
                    case "24":countyName="泰来县";break;
                    case "25":countyName="甘南县";break;
                    case "26":countyName="杜尔伯特蒙古族自治县";break;
                    case "27":countyName="富裕县";break;
                    case "28":countyName="林甸县";break;
                    case "29":countyName="克山县";break;
                    case "30":countyName="克东县";break;
                    case "31":countyName="拜泉县";break;
                    case "81":countyName="讷河市";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //黑龙江省--鸡西市************************
            case "03":{
                cityName="鸡西市";
                switch (countyCode){
                    case "01":countyName="市辖区";break;
                    case "02":countyName="鸡冠区";break;
                    case "03":countyName="恒山区";break;
                    case "04":countyName="滴道区";break;
                    case "05":countyName="梨树区";break;
                    case "06":countyName="城子河区";break;
                    case "07":countyName="麻山区";break;
                    case "21":countyName="鸡东县";break;
                    case "81":countyName="虎林市";break;
                    case "82":countyName="密山市";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //黑龙江省--鹤岗市************************
            case "04":{
                cityName="鹤岗市";
                switch (countyCode){
                    case "01":countyName="市辖区";break;
                    case "02":countyName="向阳区";break;
                    case "03":countyName="工农区";break;
                    case "04":countyName="南山区";break;
                    case "05":countyName="兴安区";break;
                    case "06":countyName="东山区";break;
                    case "07":countyName="兴山区";break;
                    case "21":countyName="萝北县";break;
                    case "22":countyName="绥滨县";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //黑龙江省--双鸭山市************************
            case "05":{
                cityName="双鸭山市";
                switch (countyCode){
                    case "01":countyName="市辖区";break;
                    case "02":countyName="尖山区";break;
                    case "03":countyName="岭东区";break;
                    case "04":countyName="岭西区";break;
                    case "05":countyName="四方台区";break;
                    case "06":countyName="宝山区";break;
                    case "21":countyName="集贤县";break;
                    case "22":countyName="友谊县";break;
                    case "23":countyName="宝清县";break;
                    case "24":countyName="饶河县";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //黑龙江省--大庆市************************
            case "06":{
                cityName="大庆市";
                switch (countyCode){
                    case "01":countyName="市辖区";break;
                    case "02":countyName="萨尔图区";break;
                    case "03":countyName="龙凤区";break;
                    case "04":countyName="让胡路区";break;
                    case "05":countyName="红岗区";break;
                    case "06":countyName="大同区";break;
                    case "21":countyName="肇州县";break;
                    case "22":countyName="肇源县";break;
                    case "23":countyName="林甸县";break;
                    case "24":countyName="杜尔伯特蒙古族自治县";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //黑龙江省--伊春市************************
            case "07":{
                cityName="伊春市";
                switch (countyCode){
                    case "01":countyName="市辖区";break;
                    case "02":countyName="伊春区";break;
                    case "03":countyName="南岔区";break;
                    case "04":countyName="友好区";break;
                    case "05":countyName="西林区";break;
                    case "06":countyName="翠峦区";break;
                    case "07":countyName="新青区";break;
                    case "08":countyName="美溪区";break;
                    case "09":countyName="金山屯区";break;
                    case "10":countyName="五营区";break;
                    case "11":countyName="乌马河区";break;
                    case "12":countyName="汤旺河区";break;
                    case "13":countyName="带岭区";break;
                    case "14":countyName="乌伊岭区";break;
                    case "15":countyName="红星区";break;
                    case "16":countyName="上甘岭区";break;
                    case "21":countyName="铁力县";break;
                    case "22":countyName="嘉荫县";break;
                    case "81":countyName="铁力市";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //黑龙江省--佳木斯市************************
            case "08":{
                cityName="佳木斯市";
                switch (countyCode){
                    case "01":countyName="市辖区";break;
                    case "02":countyName="永红区";break;
                    case "03":countyName="向阳区";break;
                    case "04":countyName="前进区";break;
                    case "05":countyName="东风区";break;
                    case "11":countyName="郊区";break;
                    case "21":countyName="富锦县";break;
                    case "22":countyName="桦南县";break;
                    case "23":countyName="依兰县";break;
                    case "25":countyName="集贤县";break;
                    case "26":countyName="桦川县";break;
                    case "27":countyName="宝清县";break;
                    case "28":countyName="汤原县";break;
                    case "29":countyName="绥滨县";break;
                    case "30":countyName="萝北县";break;
                    case "31":countyName="同江县";break;
                    case "32":countyName="饶河县";break;
                    case "33":countyName="抚远县";break;
                    case "34":countyName="友谊县";break;
                    case "81":countyName="同江市";break;
                    case "82":countyName="富锦市";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //黑龙江省--七台河市************************
            case "09":{
                cityName="七台河市";
                switch (countyCode){
                    case "01":countyName="市辖区";break;
                    case "02":countyName="新兴区";break;
                    case "03":countyName="桃山区";break;
                    case "04":countyName="茄子河区";break;
                    case "21":countyName="勃利县";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //黑龙江省--牡丹江市************************
            case "10":{
                cityName="牡丹江市";
                switch (countyCode){
                    case "01":countyName="市辖区";break;
                    case "02":countyName="东安区";break;
                    case "03":countyName="阳明区";break;
                    case "04":countyName="爱民区";break;
                    case "05":countyName="西安区";break;
                    case "11":countyName="郊区";break;
                    case "20":countyName="绥芬河市";break;
                    case "21":countyName="宁安县";break;
                    case "22":countyName="海林县";break;
                    case "23":countyName="穆棱县";break;
                    case "24":countyName="东宁县";break;
                    case "25":countyName="林口县";break;
                    case "26":countyName="密山县";break;
                    case "27":countyName="虎林县";break;
                    case "81":countyName="绥芬河市";break;
                    case "82":countyName="密山市";break;
                    case "83":countyName="海林市";break;
                    case "84":countyName="宁安市";break;
                    case "85":countyName="穆棱市";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //黑龙江省--黑河市************************
            case "11":{
                cityName="黑河市";
                switch (countyCode){
                    case "01":countyName="市辖区";break;
                    case "02":countyName="爱辉区";break;
                    case "21":countyName="嫩江县";break;
                    case "23":countyName="逊克县";break;
                    case "24":countyName="孙吴县";break;
                    case "81":countyName="北安市";break;
                    case "82":countyName="五大连池市";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //黑龙江省--绥化市************************
            case "12":{
                cityName="绥化市";
                switch (countyCode){
                    case "01":countyName="市辖区";break;
                    case "02":countyName="北林区";break;
                    case "21":countyName="望奎县";break;
                    case "22":countyName="兰西县";break;
                    case "23":countyName="青冈县";break;
                    case "24":countyName="庆安县";break;
                    case "25":countyName="明水县";break;
                    case "26":countyName="绥棱县";break;
                    case "81":countyName="安达市";break;
                    case "82":countyName="肇东市";break;
                    case "83":countyName="海伦市";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //黑龙江省--松花江地区************************
            case "21":{
                cityName="松花江地区";
                switch (countyCode){
                    case "01":countyName="双城市";break;
                    case "02":countyName="尚志市";break;
                    case "03":countyName="五常市";break;
                    case "21":countyName="阿城市";break;
                    case "22":countyName="宾县";break;
                    case "23":countyName="呼兰县";break;
                    case "24":countyName="双城市";break;
                    case "25":countyName="五常市";break;
                    case "26":countyName="巴彦县";break;
                    case "27":countyName="木兰县";break;
                    case "28":countyName="通河县";break;
                    case "29":countyName="尚志市";break;
                    case "30":countyName="方正县";break;
                    case "31":countyName="延寿县";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //黑龙江省--绥化地区************************
            case "23":{
                cityName="绥化地区";
                switch (countyCode){
                    case "01":countyName="绥化市";break;
                    case "02":countyName="安达市";break;
                    case "03":countyName="肇东市";break;
                    case "04":countyName="海伦市";break;
                    case "21":countyName="海伦县";break;
                    case "22":countyName="肇东县";break;
                    case "23":countyName="绥化县";break;
                    case "24":countyName="望奎县";break;
                    case "25":countyName="兰西县";break;
                    case "26":countyName="青冈县";break;
                    case "27":countyName="安达县";break;
                    case "28":countyName="肇源县";break;
                    case "29":countyName="肇州县";break;
                    case "30":countyName="庆安县";break;
                    case "31":countyName="明水县";break;
                    case "32":countyName="绥棱县";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //黑龙江省--佳木斯地区************************
            case "24":{
                cityName="佳木斯地区";
                switch (countyCode){
                    case "01":countyName="佳木斯市";break;
                    case "21":countyName="富锦县";break;
                    case "22":countyName="桦南县";break;
                    case "23":countyName="依兰县";break;
                    case "24":countyName="勃利县";break;
                    case "25":countyName="集贤县";break;
                    case "26":countyName="桦川县";break;
                    case "27":countyName="宝清县";break;
                    case "28":countyName="汤原县";break;
                    case "29":countyName="绥滨县";break;
                    case "30":countyName="萝北县";break;
                    case "31":countyName="同江县";break;
                    case "32":countyName="饶河县";break;
                    case "33":countyName="抚远县";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //黑龙江省--牡丹江地区************************
            case "25":{
                cityName="牡丹江地区";
                switch (countyCode){
                    case "01":countyName="牡丹江市";break;
                    case "02":countyName="绥芬河市";break;
                    case "21":countyName="宁安县";break;
                    case "22":countyName="海林县";break;
                    case "23":countyName="穆棱县";break;
                    case "24":countyName="东宁县";break;
                    case "25":countyName="林口县";break;
                    case "26":countyName="鸡东县";break;
                    case "27":countyName="密山县";break;
                    case "28":countyName="虎林县";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //黑龙江省--黑河地区************************
            case "26":{
                cityName="黑河地区";
                switch (countyCode){
                    case "01":countyName="黑河市";break;
                    case "02":countyName="北安市";break;
                    case "03":countyName="五大连池市";break;
                    case "21":countyName="北安县";break;
                    case "22":countyName="嫩江县";break;
                    case "23":countyName="德都县";break;
                    case "25":countyName="逊克县";break;
                    case "26":countyName="孙吴县";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //黑龙江省--大兴安岭地区************************
            case "27":{
                cityName="大兴安岭地区";
                switch (countyCode){
                    case "01":countyName="加格达奇区";break;
                    case "21":countyName="呼玛县";break;
                    case "22":countyName="塔河县";break;
                    case "23":countyName="漠河县";break;
                    case "24":countyName="漠河县";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //黑龙江省--其它市********************************
            case "90":{
                cityName="--";
                switch (countyCode){
                    case "01":countyName="绥芬河市";break;
                    case "02":countyName="阿城市";break;
                    case "03":countyName="同江市";break;
                    case "04":countyName="富锦市";break;
                    case "05":countyName="铁力市";break;
                    case "06":countyName="密山市";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            //********************************
            default:{
                cityName="(身份证号码3-4位有误)";
                countyName="--";
            }break;
        }
        return provinceName+"/"+cityName+"/"+countyName;
    }

    /**
     *@Author:ShiYun;
     *@Description:获得上海市的方法
     *@Date: 15:03 2018\9\25 0025
     */
    private static String getProvinceNameByProvinceCode31(String cityCode,String countyCode){
        String provinceName = "上海市";
        String cityName = "";
        String countyName = "";
        switch (cityCode){
            case "01":{
                cityName="--";
                switch (countyCode){
                    case "01":countyName="黄浦区";break;
                    case "02":countyName="南京区";break;
                    case "03":countyName="卢湾区";break;
                    case "04":countyName="徐汇区";break;
                    case "05":countyName="长宁区";break;
                    case "06":countyName="静安区";break;
                    case "07":countyName="普陀区";break;
                    case "08":countyName="闸北区";break;
                    case "09":countyName="虹口区";break;
                    case "10":countyName="杨浦区";break;
                    case "11":countyName="吴淞区";break;
                    case "12":countyName="闵行区";break;
                    case "13":countyName="宝山区";break;
                    case "14":countyName="嘉定区";break;
                    case "15":countyName="浦东新区";break;
                    case "16":countyName="金山区";break;
                    case "17":countyName="松江区";break;
                    case "18":countyName="青浦区";break;
                    case "19":countyName="南汇区";break;
                    case "20":countyName="奉贤区";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            case "02":{
                cityName="--";
                switch (countyCode){
                    case "21":countyName="上海县";break;
                    case "22":countyName="嘉定县";break;
                    case "23":countyName="宝山县";break;
                    case "24":countyName="川沙县";break;
                    case "25":countyName="南汇县";break;
                    case "26":countyName="奉贤县";break;
                    case "27":countyName="松江县";break;
                    case "28":countyName="金山县";break;
                    case "29":countyName="青浦县";break;
                    case "30":countyName="崇明县";break;
                    default:countyName="(身份证号码5-6位有误)";break;
                }
            }break;
            default:{
                cityName="身份证号码3-4位有误";
                countyName="--";
            }break;
        }
        return provinceName+"/"+cityName+"/"+countyName;
    }

    /**
     *@Author:ShiYun;
     *@Description:获得江苏省的方法
     *@Date: 15:04 2018\9\25 0025
     */
    private static String getProvinceNameByProvinceCode32(String cityCode,String countyCode){
        String provinceName = "江苏省";
        String cityName = "";
        String countyName = "";
        switch (cityCode){
            //江苏省--南京市************************
            case "01":{
                cityName="南京市";
                switch (countyCode){
                    case "01":countyName="市辖区";break;
                    case "02":countyName="玄武区";break;
                    case "03":countyName="白下区";break;
                    case "04":countyName="秦淮区";break;
                    case "05":countyName="建邺区";break;
                    case "06":countyName="鼓楼区";break;
                    case "07":countyName="下关区";break;
                    case "11":countyName="浦口区";break;
                    case "12":countyName="大厂区";break;
                    case "13":countyName="栖霞区";break;
                    case "14":countyName="雨花台区";break;
                    case "15":countyName="江宁区";break;
                    case "16":countyName="六合区";break;
                    case "20":countyName="市区";break;
                    case "21":countyName="江宁县";break;
                    case "22":countyName="江浦县";break;
                    case "23":countyName="六合县";break;
                    case "24":countyName="溧水县";break;
                    case "25":countyName="高淳县";break;
                    case "26":countyName="江都县";break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //江苏省--无锡市************************
            case "02":{
                cityName="无锡市";
                switch (countyCode){
                    case "01":countyName="市辖区";break;
                    case "02":countyName="崇安区";break;
                    case "03":countyName="南长区";break;
                    case "04":countyName="北塘区";break;
                    case "05":countyName="锡山区";break;
                    case "06":countyName="惠山区";break;
                    case "11":countyName="郊区";break;
                    case "12":countyName="马山区";break;
                    case "19":countyName="江阴县";break;
                    case "21":countyName="江阴县";break;
                    case "22":countyName="无锡县";break;
                    case "23":countyName="宜兴县";break;
                    case "81":countyName="江阴市";break;
                    case "82":countyName="宜兴市";break;
                    case "83":countyName="锡山市";break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //江苏省--徐州市************************
            case "03":{
                cityName="徐州市";
                switch (countyCode){
                    case "01":countyName="市辖区";break;
                    case "02":countyName="鼓楼区";break;
                    case "03":countyName="云龙区";break;
                    case "04":countyName="九里区";break;
                    case "05":countyName="贾汪区";break;
                    case "11":countyName="泉山区";break;
                    case "21":countyName="丰县";break;
                    case "22":countyName="沛县";break;
                    case "23":countyName="铜山县";break;
                    case "24":countyName="睢宁县";break;
                    case "25":countyName="邳县";break;
                    case "26":countyName="新沂县";break;
                    case "81":countyName="新沂市";break;
                    case "82":countyName="邳州市";break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //江苏省--常州市************************
            case "04":{
                cityName="常州市";
                switch (countyCode){
                    case "01":countyName="市辖区";break;
                    case "02":countyName="天宁区";break;
                    case "04":countyName="钟楼区";break;
                    case "05":countyName="戚墅堰区";break;
                    case "11":countyName="郊区";break;
                    case "12":countyName="武进区";break;
                    case "19":countyName="武进市";break;
                    case "21":countyName="武进县";break;
                    case "22":countyName="金坛县";break;
                    case "23":countyName="溧阳县";break;
                    case "81":countyName="溧阳市";break;
                    case "82":countyName="金坛市";break;
                    case "83":countyName="武进市";break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //江苏省--苏州市************************
            case "05":{
                cityName="苏州市";
                switch (countyCode){
                    case "01":countyName="市辖区";break;
                    case "02":countyName="沧浪区";break;
                    case "03":countyName="平江区";break;
                    case "04":countyName="金阊区";break;
                    case "05":countyName="虎丘区";break;
                    case "06":countyName="吴中区";break;
                    case "07":countyName="相城区";break;
                    case "11":countyName="郊区";break;
                    case "20":countyName="常熟市";break;
                    case "21":countyName="沙洲县";break;
                    case "22":countyName="太仓县";break;
                    case "23":countyName="昆山县";break;
                    case "24":countyName="吴县";break;
                    case "25":countyName="吴江县";break;
                    case "81":countyName="常熟市";break;
                    case "82":countyName="张家港市";break;
                    case "83":countyName="昆山市";break;
                    case "84":countyName="吴江市";break;
                    case "85":countyName="太仓市";break;
                    case "86":countyName="吴县市";break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //江苏省--南通市************************
            case "06":{
                cityName="南通市";
                switch (countyCode){
                    case "01":countyName="市辖区";break;
                    case "02":countyName="崇川区";break;
                    case "03":countyName="港闸区";break;
                    case "04":countyName="海安县";break;
                    case "05":countyName="如皋县";break;
                    case "06":countyName="如东县";break;
                    case "07":countyName="南通县";break;
                    case "11":countyName="海门县";break;
                    case "20":countyName="启东县";break;
                    case "21":countyName="启东市";break;
                    case "22":countyName="如皋市";break;
                    case "23":countyName="通州市";break;
                    case "24":countyName="海门市";break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //江苏省--连云港市************************
            case "07":{
                cityName="连云港市";
                switch (countyCode){
                    case "01":countyName="市辖区";break;
                    case "02":countyName="连云区";break;
                    case "03":countyName="云台区";break;
                    case "04":countyName="新浦区";break;
                    case "05":countyName="海州区";break;
                    case "06":countyName="赣榆县";break;
                    case "07":countyName="东海县";break;
                    case "11":countyName="灌云县";break;
                    case "20":countyName="灌南县";break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //江苏省--淮安市************************
            case "08":{
                cityName="淮安市";
                switch (countyCode){
                    case "01":countyName="市辖区";break;
                    case "02":countyName="清河区";break;
                    case "03":countyName="楚州区";break;
                    case "04":countyName="淮阴区";break;
                    case "11":countyName="清浦区";break;
                    case "19":countyName="宿迁市";break;
                    case "21":countyName="淮阴县";break;
                    case "22":countyName="灌南县";break;
                    case "23":countyName="沭阳县";break;
                    case "24":countyName="宿迁县";break;
                    case "25":countyName="泗阳县";break;
                    case "26":countyName="涟水县";break;
                    case "27":countyName="泗洪县";break;
                    case "28":countyName="淮安县";break;
                    case "29":countyName="洪泽县";break;
                    case "30":countyName="盱眙县";break;
                    case "31":countyName="金湖县";break;
                    case "81":countyName="宿迁市";break;
                    case "82":countyName="淮安市";break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //江苏省--盐城市************************
            case "09":{
                cityName="盐城市";
                switch (countyCode){
                    case "01":countyName="市辖区";break;
                    case "02":countyName="城区";break;
                    case "03":countyName="盐都区";break;
                    case "11":countyName="郊区";break;
                    case "19":countyName="东台市";break;
                    case "21":countyName="响水县";break;
                    case "22":countyName="滨海县";break;
                    case "23":countyName="阜宁县";break;
                    case "24":countyName="射阳县";break;
                    case "25":countyName="建湖县";break;
                    case "26":countyName="大丰县";break;
                    case "27":countyName="东台县";break;
                    case "28":countyName="盐都县";break;
                    case "81":countyName="东台市";break;
                    case "82":countyName="大丰市";break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //江苏省--扬州市************************
            case "10":{
                cityName="扬州市";
                switch (countyCode){
                    case "01":countyName="市辖区";break;
                    case "02":countyName="广陵区";break;
                    case "03":countyName="邗江区";break;
                    case "11":countyName="郊区";break;
                    case "19":countyName="仪征区";break;
                    case "20":countyName="泰州市";break;
                    case "21":countyName="兴化县";break;
                    case "22":countyName="高邮县";break;
                    case "23":countyName="宝应县";break;
                    case "24":countyName="靖江县";break;
                    case "25":countyName="泰兴县";break;
                    case "26":countyName="江都县";break;
                    case "27":countyName="邗江县";break;
                    case "28":countyName="泰县";break;
                    case "29":countyName="仪征县";break;
                    case "81":countyName="仪征市";break;
                    case "82":countyName="泰州市";break;
                    case "83":countyName="兴化市";break;
                    case "84":countyName="高邮市";break;
                    case "85":countyName="泰兴市";break;
                    case "86":countyName="靖江市";break;
                    case "87":countyName="江都市";break;
                    case "88":countyName="江都市";break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //江苏省--镇江市************************
            case "11":{
                cityName="镇江市";
                switch (countyCode){
                    case "01":countyName="市辖区";break;
                    case "02":countyName="京口区";break;
                    case "11":countyName="润州区";break;
                    case "12":countyName="丹徒区";break;
                    case "19":countyName="丹阳市";break;
                    case "21":countyName="丹徒县";break;
                    case "22":countyName="丹阳县";break;
                    case "23":countyName="句容县";break;
                    case "24":countyName="扬中县";break;
                    case "81":countyName="丹阳市";break;
                    case "82":countyName="扬中市";break;
                    case "83":countyName="句容市";break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //江苏省--泰州市************************
            case "12":{
                cityName="泰州市";
                switch (countyCode){
                    case "01":countyName="市辖区";break;
                    case "02":countyName="海陵区";break;
                    case "03":countyName="高港区";break;
                    case "81":countyName="兴化市";break;
                    case "82":countyName="靖江市";break;
                    case "83":countyName="泰兴市";break;
                    case "84":countyName="姜堰市";break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //江苏省--宿迁市************************
            case "13":{
                cityName="宿迁市";
                switch (countyCode){
                    case "01":countyName="市辖区";break;
                    case "02":countyName="宿城区";break;
                    case "11":countyName="宿豫区";break;
                    case "21":countyName="宿豫县";break;
                    case "22":countyName="沭阳县";break;
                    case "23":countyName="泗阳县";break;
                    case "24":countyName="泗洪县";break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //********************************
            default:{
                cityName="(身份证号码3-4位有误)";
                countyName="--";
            }break;
        }
        return provinceName+"/"+cityName+"/"+countyName;
    }

    /**
     *@Author:ShiYun;
     *@Description:获得浙江省的方法
     *@Date: 15:06 2018\9\25 0025
     */
    private static String getProvinceNameByProvinceCode33(String cityCode,String countyCode){
        String provinceName = "浙江省";
        String cityName = "";
        String countyName = "";
        switch (cityCode){
            //浙江省--杭州市************************
            case "01":{
                cityName="杭州市";
                switch (countyCode){
                    case "01":countyName="市辖区";break;
                    case "02":countyName="上城区";break;
                    case "03":countyName="下城区";break;
                    case "04":countyName="江干区";break;
                    case "05":countyName="拱墅区";break;
                    case "06":countyName="西湖区";break;
                    case "07":countyName="滨江区";break;
                    case "08":countyName="滨江区";break;
                    case "09":countyName="萧山区";break;
                    case "10":countyName="余杭区";break;
                    case "20":countyName="市区";break;
                    case "21":countyName="萧山县";break;
                    case "22":countyName="桐庐县";break;
                    case "23":countyName="富阳县";break;
                    case "24":countyName="临安县";break;
                    case "25":countyName="余杭县";break;
                    case "26":countyName="建德县";break;
                    case "27":countyName="淳安县";break;
                    case "81":countyName="萧山市";break;
                    case "82":countyName="建德市";break;
                    case "83":countyName="富阳市";break;
                    case "84":countyName="余杭市";break;
                    case "85":countyName="临安市";break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //浙江省--宁波市************************
            case "02":{
                cityName="宁波市";
                switch (countyCode){
                    case "01":countyName="市辖区";break;
                    case "02":countyName="镇明区";break;
                    case "03":countyName="海曙区";break;
                    case "04":countyName="江东区";break;
                    case "05":countyName="江北区";break;
                    case "06":countyName="北仑区";break;
                    case "07":countyName="鄞州区";break;
                    case "11":countyName="镇海区";break;
                    case "12":countyName="鄞州区";break;
                    case "19":countyName="余姚市";break;
                    case "21":countyName="镇海县";break;
                    case "22":countyName="慈溪县";break;
                    case "23":countyName="余姚县";break;
                    case "24":countyName="奉化县";break;
                    case "25":countyName="象山县";break;
                    case "26":countyName="宁海县";break;
                    case "27":countyName="鄞县";break;
                    case "81":countyName="余姚市";break;
                    case "82":countyName="慈溪市";break;
                    case "83":countyName="奉化市";break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //浙江省--温州市************************
            case "03":{
                cityName="温州市";
                switch (countyCode){
                    case "01":countyName="市辖区";break;
                    case "02":countyName="鹿城区";break;
                    case "03":countyName="龙湾区";break;
                    case "04":countyName="瓯海区";break;
                    case "21":countyName="瓯海县";break;
                    case "22":countyName="洞头县";break;
                    case "23":countyName="乐清县";break;
                    case "24":countyName="永嘉县";break;
                    case "25":countyName="瑞安县";break;
                    case "26":countyName="平阳县";break;
                    case "27":countyName="苍南县";break;
                    case "28":countyName="文成县";break;
                    case "29":countyName="泰顺县";break;
                    case "81":countyName="瑞安市";break;
                    case "82":countyName="乐清市";break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //浙江省--嘉兴市************************
            case "04":{
                cityName="嘉兴市";
                switch (countyCode){
                    case "01":countyName="市辖区";break;
                    case "02":countyName="秀城区";break;
                    case "11":countyName="郊区";break;
                    case "19":countyName="海宁市";break;
                    case "21":countyName="嘉善市";break;
                    case "22":countyName="平湖县";break;
                    case "23":countyName="海宁县";break;
                    case "24":countyName="海盐县";break;
                    case "25":countyName="桐乡县";break;
                    case "81":countyName="海宁市";break;
                    case "82":countyName="平湖市";break;
                    case "83":countyName="桐乡市";break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //浙江省--湖州市************************
            case "05":{
                cityName="湖州市";
                switch (countyCode){
                    case "01":countyName="市辖区";break;
                    case "02":countyName="吴兴区";break;
                    case "03":countyName="南浔区";break;
                    case "11":countyName="郊区";break;
                    case "21":countyName="德清县";break;
                    case "22":countyName="长兴县";break;
                    case "23":countyName="安吉县";break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //浙江省--绍兴市************************
            case "06":{
                cityName="绍兴市";
                switch (countyCode){
                    case "01":countyName="市辖区";break;
                    case "02":countyName="越城区";break;
                    case "21":countyName="绍兴县";break;
                    case "22":countyName="上虞县";break;
                    case "23":countyName="嵊县";break;
                    case "24":countyName="新昌县";break;
                    case "25":countyName="诸暨县";break;
                    case "81":countyName="诸暨市";break;
                    case "82":countyName="上虞市";break;
                    case "83":countyName="嵊州市";break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //浙江省--金华市************************
            case "07":{
                cityName="金华市";
                switch (countyCode){
                    case "01":countyName="市辖区";break;
                    case "02":countyName="婺城区";break;
                    case "03":countyName="金东区";break;
                    case "19":countyName="兰溪市";break;
                    case "21":countyName="金华县";break;
                    case "22":countyName="永康县";break;
                    case "23":countyName="武义县";break;
                    case "24":countyName="东阳县";break;
                    case "25":countyName="义乌县";break;
                    case "26":countyName="浦江县";break;
                    case "27":countyName="磐安县";break;
                    case "81":countyName="兰溪市";break;
                    case "82":countyName="义乌市";break;
                    case "83":countyName="东阳市";break;
                    case "84":countyName="永康市";break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //浙江省--衢州市************************
            case "08":{
                cityName="衢州市";
                switch (countyCode){
                    case "01":countyName="市辖区";break;
                    case "02":countyName="柯城区";break;
                    case "03":countyName="衢江区";break;
                    case "21":countyName="衢县";break;
                    case "22":countyName="常山县";break;
                    case "23":countyName="江山县";break;
                    case "24":countyName="开化县";break;
                    case "25":countyName="龙游县";break;
                    case "81":countyName="江山市";break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //浙江省--丹山市************************
            case "09":{
                cityName="丹山市";
                switch (countyCode){
                    case "01":countyName="市辖区";break;
                    case "02":countyName="定海区";break;
                    case "03":countyName="普陀区";break;
                    case "21":countyName="岱山县";break;
                    case "22":countyName="嵊泗县";break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //浙江省--台州市************************
            case "10":{
                cityName="台州市";
                switch (countyCode){
                    case "01":countyName="市辖区";break;
                    case "02":countyName="淑江区";break;
                    case "03":countyName="黄岩区";break;
                    case "04":countyName="路桥区";break;
                    case "21":countyName="玉环县";break;
                    case "22":countyName="三门县";break;
                    case "23":countyName="天台县";break;
                    case "24":countyName="仙居县";break;
                    case "81":countyName="温岭市";break;
                    case "82":countyName="临海市";break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //浙江省--丽水市************************
            case "11":{
                cityName="丽水市";
                switch (countyCode){
                    case "01":countyName="市辖区";break;
                    case "02":countyName="莲都区";break;
                    case "21":countyName="青田县";break;
                    case "22":countyName="缙云县";break;
                    case "23":countyName="遂昌县";break;
                    case "24":countyName="松阳县";break;
                    case "25":countyName="云和县";break;
                    case "26":countyName="庆元县";break;
                    case "27":countyName="景宁畲族自治县";break;
                    case "81":countyName="龙泉市";break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //浙江省--丽水地区************************
            case "25":{
                cityName="丽水地区";
                switch (countyCode){
                    case "01":countyName="丽水市";break;
                    case "02":countyName="龙泉市";break;
                    case "21":countyName="丽水县";break;
                    case "22":countyName="青田县";break;
                    case "23":countyName="云和县";break;
                    case "24":countyName="龙泉县";break;
                    case "25":countyName="庆元县";break;
                    case "26":countyName="缙云县";break;
                    case "27":countyName="遂昌县";break;
                    case "28":countyName="松阳县";break;
                    case "29":countyName="景宁畲族自治县";break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //浙江省--台州地区************************
            case "26":{
                cityName="台州地区";
                switch (countyCode){
                    case "01":countyName="椒江市";break;
                    case "02":countyName="临海市";break;
                    case "03":countyName="黄岩市";break;
                    case "21":countyName="临海县";break;
                    case "22":countyName="黄岩县";break;
                    case "23":countyName="温岭县";break;
                    case "24":countyName="仙居县";break;
                    case "25":countyName="天台县";break;
                    case "26":countyName="三门县";break;
                    case "27":countyName="玉环县";break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //浙江省--舟山地区************************
            case "27":{
                cityName="舟山地区";
                switch (countyCode){
                    case "21":countyName="定海县";break;
                    case "22":countyName="普陀县";break;
                    case "23":countyName="岱山县";break;
                    case "24":countyName="嵊泗县";break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //浙江省--其它市************************
            case "90":{
                cityName="--";
                switch (countyCode){
                    case "01":{countyName="余姚市";}break;
                    case "02":{countyName="海宁市";}break;
                    case "03":{countyName="兰溪市";}break;
                    case "04":{countyName="瑞安市";}break;
                    case "05":{countyName="萧山市";}break;
                    case "06":{countyName="江山市";}break;
                    case "07":{countyName="义乌市";}break;
                    case "08":{countyName="东阳市";}break;
                    case "09":{countyName="慈溪市";}break;
                    case "10":{countyName="奉化市";}break;
                    case "11":{countyName="诸暨市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //********************************
            default:{
                cityName="(身份证号码3-4位有误)";
                countyName="--";
            }break;
        }
        return provinceName+"/"+cityName+"/"+countyName;
    }

    /**
     *@Author:ShiYun;
     *@Description:获得安徽省的方法
     *@Date: 15:07 2018\9\25 0025
     */
    private static String getProvinceNameByProvinceCode34(String cityCode,String countyCode){
        String provinceName = "安徽省";
        String cityName = "";
        String countyName = "";
        switch (cityCode){
            //安徽省--合肥市************************
            case "01":{
                cityName="合肥市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="瑶海区";}break;
                    case "03":{countyName="庐阳区";}break;
                    case "04":{countyName="蜀山区";}break;
                    case "11":{countyName="包河区";}break;
                    case "21":{countyName="长丰县";}break;
                    case "22":{countyName="肥西县";}break;
                    case "23":{countyName="肥东县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //安徽省--芜湖市************************
            case "02":{
                cityName="芜湖市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="镜湖区";}break;
                    case "03":{countyName="弋江区";}break;
                    case "04":{countyName="新抚区";}break;
                    case "05":{countyName="裕溪口去";}break;
                    case "06":{countyName="四褐山区";}break;
                    case "07":{countyName="鸠江区";}break;
                    case "08":{countyName="三山区";}break;
                    case "11":{countyName="郊区";}break;
                    case "20":{countyName="市区";}break;
                    case "21":{countyName="芜湖县";}break;
                    case "22":{countyName="繁昌县";}break;
                    case "23":{countyName="南陵县";}break;
                    case "24":{countyName="青阳县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //安徽省--蚌阜市************************
            case "03":{
                cityName="蚌阜市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="龙子湖区";}break;
                    case "03":{countyName="蚌山区";}break;
                    case "04":{countyName="禹会区";}break;
                    case "11":{countyName="淮上区";}break;
                    case "21":{countyName="怀远区";}break;
                    case "22":{countyName="五河县";}break;
                    case "23":{countyName="固镇县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //安徽省--淮南市************************
            case "04":{
                cityName="淮南市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="大通区";}break;
                    case "03":{countyName="田家庵区";}break;
                    case "04":{countyName="谢家集区";}break;
                    case "05":{countyName="八公山区";}break;
                    case "06":{countyName="潘集区";}break;
                    case "21":{countyName="凤台区";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //安徽省--马鞍山市************************
            case "05":{
                cityName="马鞍山市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="金家庄区";}break;
                    case "03":{countyName="花山区";}break;
                    case "04":{countyName="雨山区";}break;
                    case "05":{countyName="向山区";}break;
                    case "21":{countyName="当涂县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //安徽省--淮北市************************
            case "06":{
                cityName="淮北市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="杜集区";}break;
                    case "03":{countyName="相山区";}break;
                    case "04":{countyName="烈山区";}break;
                    case "21":{countyName="濉溪县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //安徽省--铜陵市************************
            case "07":{
                cityName="铜陵市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="铜官山区";}break;
                    case "03":{countyName="狮子山区";}break;
                    case "11":{countyName="郊区";}break;
                    case "21":{countyName="铜陵县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //安徽省--安庆市************************
            case "08":{
                cityName="安庆市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="迎江区";}break;
                    case "03":{countyName="大观区";}break;
                    case "11":{countyName="郊区";}break;
                    case "21":{countyName="桐城县";}break;
                    case "22":{countyName="怀宁县";}break;
                    case "23":{countyName="枞阳县";}break;
                    case "24":{countyName="潜山县";}break;
                    case "25":{countyName="太湖县";}break;
                    case "26":{countyName="宿松县";}break;
                    case "27":{countyName="望江县";}break;
                    case "28":{countyName="岳西县";}break;
                    case "81":{countyName="桐城市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //安徽省--黄山市************************
            case "10":{
                cityName="黄山市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="屯溪区";}break;
                    case "03":{countyName="黄山区";}break;
                    case "04":{countyName="徽州区";}break;
                    case "21":{countyName="歙县";}break;
                    case "22":{countyName="休宁县";}break;
                    case "23":{countyName="黟县";}break;
                    case "24":{countyName="祁门县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //安徽省--滁州市************************
            case "11":{
                cityName="滁州市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="琅琊区";}break;
                    case "03":{countyName="南焦区";}break;
                    case "21":{countyName="天长县";}break;
                    case "22":{countyName="来安县";}break;
                    case "24":{countyName="全椒县";}break;
                    case "25":{countyName="定远县";}break;
                    case "26":{countyName="凤阳县";}break;
                    case "27":{countyName="嘉山县";}break;
                    case "81":{countyName="天长市";}break;
                    case "82":{countyName="明光市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //安徽省--阜阳市************************
            case "12":{
                cityName="阜阳市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="颍州区";}break;
                    case "03":{countyName="颍东区";}break;
                    case "04":{countyName="颍泉区";}break;
                    case "21":{countyName="临泉县";}break;
                    case "22":{countyName="太和县";}break;
                    case "23":{countyName="涡阳县";}break;
                    case "24":{countyName="蒙城县";}break;
                    case "25":{countyName="阜南县";}break;
                    case "86":{countyName="颍上县";}break;
                    case "87":{countyName="利辛县";}break;
                    case "81":{countyName="亳州市";}break;
                    case "82":{countyName="界首市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //安徽省--宿州市************************
            case "13":{
                cityName="宿州市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="墉桥区";}break;
                    case "21":{countyName="砀山县";}break;
                    case "22":{countyName="萧县";}break;
                    case "23":{countyName="灵璧县";}break;
                    case "24":{countyName="泗县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //安徽省--巢湖市************************
            case "14":{
                cityName="巢湖市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="居巢区";}break;
                    case "21":{countyName="庐江县";}break;
                    case "22":{countyName="无为县";}break;
                    case "23":{countyName="含山县";}break;
                    case "24":{countyName="和县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //安徽省--六安市************************
            case "15":{
                cityName="六安市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="金安区";}break;
                    case "03":{countyName="裕安区";}break;
                    case "21":{countyName="寿县";}break;
                    case "22":{countyName="霍邱县";}break;
                    case "23":{countyName="舒城县";}break;
                    case "24":{countyName="金寨县";}break;
                    case "25":{countyName="霍山县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //安徽省--亳州市************************
            case "16":{
                cityName="亳州市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="谯城区";}break;
                    case "21":{countyName="涡阳县";}break;
                    case "22":{countyName="蒙城县";}break;
                    case "23":{countyName="利辛县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //安徽省--池州市************************
            case "17":{
                cityName="池州市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="贵池区";}break;
                    case "21":{countyName="东至县";}break;
                    case "22":{countyName="石台县";}break;
                    case "23":{countyName="青阳县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //安徽省--宣城市************************
            case "18":{
                cityName="宣城市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="宣州区";}break;
                    case "21":{countyName="郎溪县";}break;
                    case "22":{countyName="广德县";}break;
                    case "23":{countyName="泾县";}break;
                    case "24":{countyName="绩溪县";}break;
                    case "25":{countyName="旌德县";}break;
                    case "81":{countyName="宁国市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //安徽省--阜阳地区************************
            case "21":{
                cityName="阜阳地区";
                switch (countyCode){
                    case "01":{countyName="阜阳市";}break;
                    case "21":{countyName="阜阳县";}break;
                    case "22":{countyName="临泉县";}break;
                    case "23":{countyName="太和县";}break;
                    case "24":{countyName="涡阳县";}break;
                    case "25":{countyName="蒙城县";}break;
                    case "26":{countyName="亳县";}break;
                    case "27":{countyName="阜南县";}break;
                    case "28":{countyName="颍上县";}break;
                    case "29":{countyName="界首县";}break;
                    case "30":{countyName="利辛县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //安徽省--宿县地区************************
            case "22":{
                cityName="宿县地区";
                switch (countyCode){
                    case "01":{countyName="宿州市";}break;
                    case "21":{countyName="砀山县";}break;
                    case "22":{countyName="萧县";}break;
                    case "23":{countyName="宿县";}break;
                    case "24":{countyName="灵璧县";}break;
                    case "25":{countyName="泗县";}break;
                    case "26":{countyName="怀远县";}break;
                    case "27":{countyName="五河县";}break;
                    case "28":{countyName="固镇县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //安徽省--滁县地区************************
            case "23":{
                cityName="滁县地区";
                switch (countyCode){
                    case "01":{countyName="滁州市";}break;
                    case "21":{countyName="天长县";}break;
                    case "22":{countyName="来安县";}break;
                    case "23":{countyName="滁县";}break;
                    case "24":{countyName="全椒县";}break;
                    case "25":{countyName="定远县";}break;
                    case "26":{countyName="凤阳县";}break;
                    case "27":{countyName="嘉山县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //安徽省--六安地区************************
            case "24":{
                cityName="六安地区";
                switch (countyCode){
                    case "01":{countyName="六安市";}break;
                    case "21":{countyName="六安县";}break;
                    case "22":{countyName="寿县";}break;
                    case "23":{countyName="霍邱县";}break;
                    case "24":{countyName="肥西县";}break;
                    case "25":{countyName="舒城县";}break;
                    case "26":{countyName="金寨县";}break;
                    case "27":{countyName="霍山县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //安徽省--宣城地区************************
            case "25":{
                cityName="宣城地区";
                switch (countyCode){
                    case "01":{countyName="宣州市";}break;
                    case "02":{countyName="宁国市";}break;
                    case "21":{countyName="宣州县";}break;
                    case "22":{countyName="郎溪县";}break;
                    case "23":{countyName="广德县";}break;
                    case "24":{countyName="宁国县";}break;
                    case "25":{countyName="当涂县";}break;
                    case "26":{countyName="繁昌县";}break;
                    case "27":{countyName="南陵县";}break;
                    case "28":{countyName="青阳县";}break;
                    case "29":{countyName="泾县";}break;
                    case "30":{countyName="旌德县";}break;
                    case "31":{countyName="绩溪县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //安徽省--巢湖地区************************
            case "26":{
                cityName="巢湖地区";
                switch (countyCode){
                    case "01":{countyName="巢湖市";}break;
                    case "21":{countyName="肥东县";}break;
                    case "22":{countyName="庐江县";}break;
                    case "23":{countyName="无为县";}break;
                    case "24":{countyName="巢县";}break;
                    case "25":{countyName="含山县";}break;
                    case "26":{countyName="和县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //安徽省--徽州地区************************
            case "27":{
                cityName="徽州地区";
                switch (countyCode){
                    case "01":{countyName="屯溪市";}break;
                    case "21":{countyName="绩溪县";}break;
                    case "22":{countyName="旌德县";}break;
                    case "23":{countyName="歙县";}break;
                    case "24":{countyName="休宁县";}break;
                    case "25":{countyName="黟县";}break;
                    case "26":{countyName="祁门县";}break;
                    case "27":{countyName="太平县";}break;
                    case "28":{countyName="石台县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //安徽省--安庆地区************************
            case "28":{
                cityName="安庆地区";
                switch (countyCode){
                    case "21":{countyName="怀宁县";}break;
                    case "22":{countyName="桐城县";}break;
                    case "23":{countyName="枞阳县";}break;
                    case "24":{countyName="潜山县";}break;
                    case "25":{countyName="太湖县";}break;
                    case "26":{countyName="宿松县";}break;
                    case "27":{countyName="望江县";}break;
                    case "28":{countyName="岳西县";}break;
                    case "29":{countyName="东至县";}break;
                    case "30":{countyName="贵池县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //安徽省--池州地区************************
            case "29":{
                cityName="池州地区";
                switch (countyCode){
                    case "01":{countyName="贵池市";}break;
                    case "21":{countyName="东至县";}break;
                    case "22":{countyName="石台县";}break;
                    case "23":{countyName="青阳县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //********************************
            default:{
                cityName="(身份证号码3-4位有误)";
                countyName="--";
            }break;
        }
        return provinceName+"/"+cityName+"/"+countyName;
    }

    /**
     *@Author:ShiYun;
     *@Description:获得福建省的方法
     *@Date: 15:08 2018\9\25 0025
     */
    private static String getProvinceNameByProvinceCode35(String cityCode,String countyCode){
        String provinceName = "福建省";
        String cityName = "";
        String countyName = "";
        switch (cityCode){
            //福建省--福州市************************
            case "01":{
                cityName="福州市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="鼓楼区";}break;
                    case "03":{countyName="台江区";}break;
                    case "04":{countyName="仓山区";}break;
                    case "05":{countyName="马尾区";}break;
                    case "11":{countyName="晋安区";}break;
                    case "20":{countyName="市区";}break;
                    case "21":{countyName="闽侯区";}break;
                    case "22":{countyName="连江县";}break;
                    case "23":{countyName="罗源县";}break;
                    case "24":{countyName="闽清县";}break;
                    case "25":{countyName="永泰县";}break;
                    case "26":{countyName="长乐县";}break;
                    case "27":{countyName="福清县";}break;
                    case "28":{countyName="平潭县";}break;
                    case "81":{countyName="福清市";}break;
                    case "82":{countyName="长乐市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //福建省--厦门市************************
            case "02":{
                cityName="厦门市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="鼓浪屿区";}break;
                    case "03":{countyName="思明区";}break;
                    case "04":{countyName="开元区";}break;
                    case "05":{countyName="海沧区";}break;
                    case "06":{countyName="湖里区";}break;
                    case "11":{countyName="集美区";}break;
                    case "12":{countyName="同安区";}break;
                    case "13":{countyName="翔安区";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //福建省--莆田市************************
            case "03":{
                cityName="莆田市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="城厢区";}break;
                    case "03":{countyName="涵江区";}break;
                    case "04":{countyName="荔城区";}break;
                    case "05":{countyName="秀屿区";}break;
                    case "21":{countyName="莆田县";}break;
                    case "22":{countyName="仙游县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //福建省--三明市************************
            case "04":{
                cityName="三明市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="梅列区";}break;
                    case "03":{countyName="三元区";}break;
                    case "04":{countyName="永安市";}break;
                    case "20":{countyName="永安市";}break;
                    case "21":{countyName="明溪县";}break;
                    case "22":{countyName="永安县";}break;
                    case "23":{countyName="清流县";}break;
                    case "24":{countyName="宁化县";}break;
                    case "25":{countyName="大田县";}break;
                    case "26":{countyName="龙溪县";}break;
                    case "27":{countyName="沙县";}break;
                    case "28":{countyName="将乐县";}break;
                    case "29":{countyName="泰宁县";}break;
                    case "30":{countyName="建宁县";}break;
                    case "81":{countyName="永安市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //福建省--泉州市************************
            case "05":{
                cityName="泉州市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="鲤城区";}break;
                    case "03":{countyName="丰泽区";}break;
                    case "04":{countyName="洛江区";}break;
                    case "05":{countyName="泉港区";}break;
                    case "21":{countyName="惠安县";}break;
                    case "22":{countyName="晋江县";}break;
                    case "23":{countyName="南安县";}break;
                    case "24":{countyName="安溪县";}break;
                    case "25":{countyName="永春县";}break;
                    case "26":{countyName="德化县";}break;
                    case "27":{countyName="金门县";}break;
                    case "81":{countyName="石狮市";}break;
                    case "82":{countyName="晋江市";}break;
                    case "83":{countyName="南安市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //福建省--漳州市************************
            case "06":{
                cityName="漳州市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="芗城县";}break;
                    case "03":{countyName="龙文区";}break;
                    case "21":{countyName="龙海县";}break;
                    case "22":{countyName="云霄县";}break;
                    case "23":{countyName="漳浦县";}break;
                    case "24":{countyName="诏安县";}break;
                    case "25":{countyName="长泰县";}break;
                    case "26":{countyName="东山县";}break;
                    case "27":{countyName="南靖县";}break;
                    case "28":{countyName="平和县";}break;
                    case "29":{countyName="华安县";}break;
                    case "81":{countyName="龙海市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //福建省--南平市************************
            case "07":{
                cityName="南平市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="延平区";}break;
                    case "21":{countyName="顺昌县";}break;
                    case "22":{countyName="浦城县";}break;
                    case "23":{countyName="光泽县";}break;
                    case "24":{countyName="松溪县";}break;
                    case "25":{countyName="政和县";}break;
                    case "81":{countyName="邵武县";}break;
                    case "82":{countyName="武夷山市";}break;
                    case "83":{countyName="建瓯市";}break;
                    case "84":{countyName="建阳市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //福建省--龙岩市************************
            case "08":{
                cityName="龙岩市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="新罗区";}break;
                    case "21":{countyName="长汀县";}break;
                    case "22":{countyName="永定县";}break;
                    case "23":{countyName="上杭县";}break;
                    case "24":{countyName="武平县";}break;
                    case "25":{countyName="连城县";}break;
                    case "81":{countyName="漳平市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //福建省--宁德市************************
            case "09":{
                cityName="宁德市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="蕉城区";}break;
                    case "21":{countyName="霞浦县";}break;
                    case "22":{countyName="古田县";}break;
                    case "23":{countyName="屏南县";}break;
                    case "24":{countyName="寿宁县";}break;
                    case "25":{countyName="周宁县";}break;
                    case "26":{countyName="拓荣县";}break;
                    case "81":{countyName="福安市";}break;
                    case "82":{countyName="福鼎市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //福建省--南平地区************************
            case "21":{
                cityName="南平地区";
                switch (countyCode){
                    case "01":{countyName="南平市";}break;
                    case "02":{countyName="邵武市";}break;
                    case "03":{countyName="武夷山市";}break;
                    case "04":{countyName="建瓯市";}break;
                    case "21":{countyName="顺昌县";}break;
                    case "22":{countyName="建阳县";}break;
                    case "23":{countyName="建瓯县";}break;
                    case "24":{countyName="浦城县";}break;
                    case "25":{countyName="邵武县";}break;
                    case "26":{countyName="崇安县";}break;
                    case "27":{countyName="光泽县";}break;
                    case "28":{countyName="松溪县";}break;
                    case "29":{countyName="政和县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //福建省--宁德地区************************
            case "22":{
                cityName="宁德地区";
                switch (countyCode){
                    case "01":{countyName="宁德市";}break;
                    case "02":{countyName="福安市";}break;
                    case "03":{countyName="福鼎市";}break;
                    case "21":{countyName="宁德县";}break;
                    case "22":{countyName="连江县";}break;
                    case "23":{countyName="罗源县";}break;
                    case "24":{countyName="福鼎县";}break;
                    case "25":{countyName="霞浦县";}break;
                    case "26":{countyName="福安县";}break;
                    case "27":{countyName="古田县";}break;
                    case "28":{countyName="屏南县";}break;
                    case "29":{countyName="寿宁县";}break;
                    case "30":{countyName="周宁县";}break;
                    case "31":{countyName="拓荣县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //福建省--龙岩地区************************
            case "26":{
                cityName="龙岩地区";
                switch (countyCode){
                    case "01":{countyName="龙岩市";}break;
                    case "02":{countyName="漳平市";}break;
                    case "22":{countyName="长汀县";}break;
                    case "23":{countyName="永定县";}break;
                    case "24":{countyName="上杭县";}break;
                    case "25":{countyName="武平县";}break;
                    case "26":{countyName="漳平县";}break;
                    case "27":{countyName="连城县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //福建省--三明市************************
            case "27":{
                cityName="三明市";
                switch (countyCode){
                    case "01":{countyName="三明市";}break;
                    case "21":{countyName="明溪县";}break;
                    case "22":{countyName="永安县";}break;
                    case "23":{countyName="清流县";}break;
                    case "24":{countyName="宁化县";}break;
                    case "25":{countyName="大田县";}break;
                    case "26":{countyName="龙溪县";}break;
                    case "27":{countyName="沙县";}break;
                    case "28":{countyName="将乐县";}break;
                    case "29":{countyName="泰宁县";}break;
                    case "30":{countyName="建宁县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //福建省--其它市************************
            case "90":{
                cityName="--";
                switch (countyCode){
                    case "01":{countyName="永安市";}break;
                    case "02":{countyName="石狮市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //********************************
            default:{
                cityName="(身份证号码3-4位有误)";
                countyName="--";
            }break;
        }
        return provinceName+"/"+cityName+"/"+countyName;
    }

    /**
     *@Author:ShiYun;
     *@Description:获得江西省的方法
     *@Date: 15:13 2018\9\25 0025
     */
    private static String getProvinceNameByProvinceCode36(String cityCode,String countyCode){
        String provinceName = "江西省";
        String cityName = "";
        String countyName = "";
        switch (cityCode){
            //江西省--南昌市************************
            case "01":{
                cityName="南昌市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="东湖区";}break;
                    case "03":{countyName="西湖区";}break;
                    case "04":{countyName="青云谱区";}break;
                    case "05":{countyName="湾里区";}break;
                    case "11":{countyName="郊区";}break;
                    case "21":{countyName="南昌县";}break;
                    case "22":{countyName="新建县";}break;
                    case "23":{countyName="安义县";}break;
                    case "24":{countyName="进贤县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //江西省--景德镇市************************
            case "02":{
                cityName="景德镇市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="昌江区";}break;
                    case "03":{countyName="珠山区";}break;
                    case "11":{countyName="鹅湖区";}break;
                    case "12":{countyName="蛟潭区";}break;
                    case "21":{countyName="乐平县";}break;
                    case "22":{countyName="浮梁县";}break;
                    case "81":{countyName="乐平市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //江西省--萍乡市************************
            case "03":{
                cityName="萍乡市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="安源区";}break;
                    case "11":{countyName="上栗区";}break;
                    case "12":{countyName="泸溪县";}break;
                    case "13":{countyName="湘东区";}break;
                    case "21":{countyName="莲花县";}break;
                    case "22":{countyName="上栗县";}break;
                    case "23":{countyName="泸溪县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //江西省--九江市************************
            case "04":{
                cityName="九江市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="庐山区";}break;
                    case "03":{countyName="浔阳区";}break;
                    case "21":{countyName="九江县";}break;
                    case "22":{countyName="瑞昌县";}break;
                    case "23":{countyName="武宁县";}break;
                    case "24":{countyName="修水县";}break;
                    case "25":{countyName="永修县";}break;
                    case "26":{countyName="德安县";}break;
                    case "27":{countyName="星子县";}break;
                    case "28":{countyName="都昌县";}break;
                    case "29":{countyName="湖口县";}break;
                    case "30":{countyName="彭泽县";}break;
                    case "81":{countyName="瑞昌市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //江西省--新余市************************
            case "05":{
                cityName="新余市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="渝水区";}break;
                    case "21":{countyName="分宜县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //江西省--鹰潭市************************
            case "06":{
                cityName="鹰潭市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="月湖区";}break;
                    case "21":{countyName="贵溪县";}break;
                    case "22":{countyName="余江县";}break;
                    case "81":{countyName="贵溪市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //江西省--赣州市************************
            case "07":{
                cityName="赣州市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="章贡区";}break;
                    case "21":{countyName="赣县";}break;
                    case "22":{countyName="信丰县";}break;
                    case "23":{countyName="大余县";}break;
                    case "24":{countyName="上犹县";}break;
                    case "25":{countyName="崇义县";}break;
                    case "26":{countyName="安远县";}break;
                    case "27":{countyName="龙南县";}break;
                    case "28":{countyName="定南县";}break;
                    case "29":{countyName="全南县";}break;
                    case "30":{countyName="宁都县";}break;
                    case "31":{countyName="于都县";}break;
                    case "32":{countyName="兴国县";}break;
                    case "33":{countyName="会昌县";}break;
                    case "34":{countyName="寻乌县";}break;
                    case "35":{countyName="石城县";}break;
                    case "81":{countyName="瑞金市";}break;
                    case "82":{countyName="南康市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //江西省--吉安市************************
            case "08":{
                cityName="吉安市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="吉州区";}break;
                    case "03":{countyName="青原区";}break;
                    case "21":{countyName="吉安县";}break;
                    case "22":{countyName="吉水县";}break;
                    case "23":{countyName="峡江县";}break;
                    case "24":{countyName="新干县";}break;
                    case "25":{countyName="永丰县";}break;
                    case "26":{countyName="泰和县";}break;
                    case "27":{countyName="遂川县";}break;
                    case "28":{countyName="万安县";}break;
                    case "29":{countyName="安福县";}break;
                    case "30":{countyName="永新县";}break;
                    case "81":{countyName="井冈山市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //江西省--宜春市************************
            case "09":{
                cityName="宜春市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="袁州区";}break;
                    case "21":{countyName="奉新县";}break;
                    case "22":{countyName="万载县";}break;
                    case "23":{countyName="上高县";}break;
                    case "24":{countyName="宜丰县";}break;
                    case "25":{countyName="靖安县";}break;
                    case "26":{countyName="铜鼓县";}break;
                    case "81":{countyName="丰城市";}break;
                    case "82":{countyName="樟树市";}break;
                    case "83":{countyName="高安市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //江西省--抚州市************************
            case "10":{
                cityName="抚州市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="临川区";}break;
                    case "21":{countyName="南城县";}break;
                    case "22":{countyName="黎川县";}break;
                    case "23":{countyName="南丰县";}break;
                    case "24":{countyName="崇仁县";}break;
                    case "25":{countyName="乐安县";}break;
                    case "26":{countyName="宜黄县";}break;
                    case "27":{countyName="金溪县";}break;
                    case "28":{countyName="资溪县";}break;
                    case "29":{countyName="东乡县";}break;
                    case "30":{countyName="广昌县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //江西省--上饶市************************
            case "11":{
                cityName="上饶市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="信州区";}break;
                    case "21":{countyName="上饶县";}break;
                    case "22":{countyName="广丰县";}break;
                    case "23":{countyName="玉山县";}break;
                    case "24":{countyName="铅山县";}break;
                    case "25":{countyName="横峰县";}break;
                    case "26":{countyName="弋阳县";}break;
                    case "27":{countyName="余干县";}break;
                    case "28":{countyName="鄱阳县";}break;
                    case "29":{countyName="万年县";}break;
                    case "30":{countyName="婺源县";}break;
                    case "81":{countyName="德兴市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //江西省--赣州地区************************
            case "21":{
                cityName="赣州地区";
                switch (countyCode){
                    case "01":{countyName="赣州市";}break;
                    case "02":{countyName="瑞金市";}break;
                    case "03":{countyName="南康市";}break;
                    case "21":{countyName="赣县";}break;
                    case "22":{countyName="南康市";}break;
                    case "23":{countyName="信丰市";}break;
                    case "24":{countyName="大余县";}break;
                    case "25":{countyName="上犹县";}break;
                    case "26":{countyName="崇义县";}break;
                    case "27":{countyName="安远县";}break;
                    case "28":{countyName="龙南县";}break;
                    case "29":{countyName="定南县";}break;
                    case "30":{countyName="全南县";}break;
                    case "31":{countyName="宁都县";}break;
                    case "32":{countyName="于都县";}break;
                    case "33":{countyName="兴国县";}break;
                    case "34":{countyName="瑞金市";}break;
                    case "35":{countyName="会昌县";}break;
                    case "36":{countyName="寻乌县";}break;
                    case "37":{countyName="石城县";}break;
                    case "38":{countyName="广昌县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //江西省--宜春地区************************
            case "22":{
                cityName="宜春地区";
                switch (countyCode){
                    case "01":{countyName="宜春市";}break;
                    case "02":{countyName="丰城市";}break;
                    case "03":{countyName="樟树市";}break;
                    case "04":{countyName="高安市";}break;
                    case "21":{countyName="丰城县";}break;
                    case "22":{countyName="高安县";}break;
                    case "23":{countyName="清江县";}break;
                    case "24":{countyName="新余县";}break;
                    case "25":{countyName="宜春县";}break;
                    case "26":{countyName="奉新县";}break;
                    case "27":{countyName="万载县";}break;
                    case "28":{countyName="上高县";}break;
                    case "29":{countyName="宜丰县";}break;
                    case "30":{countyName="分宜县";}break;
                    case "31":{countyName="安义县";}break;
                    case "32":{countyName="靖安县";}break;
                    case "33":{countyName="铜鼓县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //江西省--上饶地区************************
            case "23":{
                cityName="上饶地区";
                switch (countyCode){
                    case "01":{countyName="上饶市";}break;
                    case "02":{countyName="德兴市";}break;
                    case "21":{countyName="上饶县";}break;
                    case "22":{countyName="广丰县";}break;
                    case "23":{countyName="玉山县";}break;
                    case "24":{countyName="铅山县";}break;
                    case "25":{countyName="横峰县";}break;
                    case "26":{countyName="弋阳县";}break;
                    case "27":{countyName="贵溪县";}break;
                    case "28":{countyName="余江县";}break;
                    case "29":{countyName="余干县";}break;
                    case "30":{countyName="波阳县";}break;
                    case "31":{countyName="万年县";}break;
                    case "32":{countyName="乐平县";}break;
                    case "33":{countyName="德兴县";}break;
                    case "34":{countyName="婺源县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //江西省--吉安地区************************
            case "24":{
                cityName="吉安地区";
                switch (countyCode){
                    case "01":{countyName="吉安市";}break;
                    case "02":{countyName="井冈山市";}break;
                    case "21":{countyName="吉安县";}break;
                    case "22":{countyName="吉水县";}break;
                    case "23":{countyName="峡江县";}break;
                    case "24":{countyName="新干县";}break;
                    case "25":{countyName="永丰县";}break;
                    case "26":{countyName="泰和县";}break;
                    case "27":{countyName="遂川县";}break;
                    case "28":{countyName="万安县";}break;
                    case "29":{countyName="安福县";}break;
                    case "30":{countyName="永新县";}break;
                    case "31":{countyName="莲花县";}break;
                    case "32":{countyName="宁冈县";}break;
                    case "33":{countyName="井冈山县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //江西省--抚州地区************************
            case "25":{
                cityName="抚州地区";
                switch (countyCode){
                    case "01":{countyName="临川市";}break;
                    case "02":{countyName="临川市";}break;
                    case "21":{countyName="临川县";}break;
                    case "22":{countyName="南城县";}break;
                    case "23":{countyName="黎川县";}break;
                    case "24":{countyName="南丰县";}break;
                    case "25":{countyName="崇仁县";}break;
                    case "26":{countyName="乐安县";}break;
                    case "27":{countyName="宜黄县";}break;
                    case "28":{countyName="金溪县";}break;
                    case "29":{countyName="资溪县";}break;
                    case "30":{countyName="进贤县";}break;
                    case "31":{countyName="东乡县";}break;
                    case "32":{countyName="广昌县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //江西省--九江地区************************
            case "26":{
                cityName="九江地区";
                switch (countyCode){
                    case "21":{countyName="九江县";}break;
                    case "22":{countyName="瑞昌县";}break;
                    case "23":{countyName="武宁县";}break;
                    case "24":{countyName="修水县";}break;
                    case "25":{countyName="永修县";}break;
                    case "26":{countyName="德安县";}break;
                    case "27":{countyName="星子县";}break;
                    case "28":{countyName="都昌县";}break;
                    case "29":{countyName="湖口县";}break;
                    case "30":{countyName="彭泽县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //********************************
            default:{
                cityName="(身份证号码3-4位有误)";
                countyName="--";
            }break;
        }
        return provinceName+"/"+cityName+"/"+countyName;
    }

    /**
     *@Author:ShiYun;
     *@Description:获得山东省的方法
     *@Date: 15:15 2018\9\25 0025
     */
    private static String getProvinceNameByProvinceCode37(String cityCode,String countyCode){
        String provinceName = "山东省";
        String cityName = "";
        String countyName = "";
        switch (cityCode){
            //山东省--济南市************************
            case "01":{
                cityName="济南市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="历下区";}break;
                    case "03":{countyName="市中区";}break;
                    case "04":{countyName="槐荫区";}break;
                    case "05":{countyName="天桥区";}break;
                    case "11":{countyName="郊区";}break;
                    case "12":{countyName="历城区";}break;
                    case "13":{countyName="长清区";}break;
                    case "20":{countyName="市区";}break;
                    case "21":{countyName="历城县";}break;
                    case "22":{countyName="章丘县";}break;
                    case "23":{countyName="长清县";}break;
                    case "24":{countyName="平阴县";}break;
                    case "25":{countyName="济阳县";}break;
                    case "26":{countyName="商河县";}break;
                    case "81":{countyName="章丘县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //山东省--青岛市************************
            case "02":{
                cityName="青岛市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="市南区";}break;
                    case "03":{countyName="市北区";}break;
                    case "04":{countyName="台东区";}break;
                    case "05":{countyName="四方区";}break;
                    case "06":{countyName="沧口区";}break;
                    case "11":{countyName="黄岛区";}break;
                    case "12":{countyName="崂山区";}break;
                    case "13":{countyName="李沧区";}break;
                    case "14":{countyName="城阳区";}break;
                    case "20":{countyName="市区";}break;
                    case "21":{countyName="崂山县";}break;
                    case "22":{countyName="即墨县";}break;
                    case "23":{countyName="胶南县";}break;
                    case "24":{countyName="胶县";}break;
                    case "25":{countyName="莱西县";}break;
                    case "26":{countyName="平度县";}break;
                    case "81":{countyName="胶州市";}break;
                    case "82":{countyName="即墨市";}break;
                    case "83":{countyName="平度市";}break;
                    case "84":{countyName="胶南市";}break;
                    case "85":{countyName="莱西市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //山东省--淄博市************************
            case "03":{
                cityName="淄博市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="淄川区";}break;
                    case "03":{countyName="张店区";}break;
                    case "04":{countyName="博山区";}break;
                    case "05":{countyName="临淄区";}break;
                    case "06":{countyName="周村区";}break;
                    case "21":{countyName="桓台县";}break;
                    case "22":{countyName="高青县";}break;
                    case "23":{countyName="沂源县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //山东省--枣庄市************************
            case "04":{
                cityName="枣庄市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="市中区";}break;
                    case "03":{countyName="薛城区";}break;
                    case "04":{countyName="峄城区";}break;
                    case "05":{countyName="台儿庄区";}break;
                    case "06":{countyName="山亭区";}break;
                    case "20":{countyName="市区";}break;
                    case "21":{countyName="藤县";}break;
                    case "81":{countyName="滕州市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //山东省--东营市************************
            case "05":{
                cityName="东营市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="东营区";}break;
                    case "03":{countyName="河口区";}break;
                    case "21":{countyName="垦利区";}break;
                    case "22":{countyName="利津县";}break;
                    case "23":{countyName="广饶县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //山东省--烟台市************************
            case "06":{
                cityName="烟台市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="芝罘区";}break;
                    case "11":{countyName="福山区";}break;
                    case "12":{countyName="牟平区";}break;
                    case "13":{countyName="莱山区";}break;
                    case "19":{countyName="龙口市";}break;
                    case "20":{countyName="威海市";}break;
                    case "22":{countyName="蓬莱县";}break;
                    case "23":{countyName="黄县";}break;
                    case "24":{countyName="招远县";}break;
                    case "25":{countyName="掖县";}break;
                    case "27":{countyName="莱阳县";}break;
                    case "28":{countyName="栖霞县";}break;
                    case "29":{countyName="海阳县";}break;
                    case "31":{countyName="牟平县";}break;
                    case "32":{countyName="文登县";}break;
                    case "33":{countyName="容城县";}break;
                    case "34":{countyName="长岛县";}break;
                    case "81":{countyName="龙口市";}break;
                    case "82":{countyName="莱阳市";}break;
                    case "83":{countyName="莱州市";}break;
                    case "84":{countyName="蓬莱市";}break;
                    case "85":{countyName="招远市";}break;
                    case "86":{countyName="栖霞市";}break;
                    case "87":{countyName="海阳市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //山东省--潍坊市************************
            case "07":{
                cityName="潍坊市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="潍城区";}break;
                    case "03":{countyName="寒亭区";}break;
                    case "04":{countyName="坊子区";}break;
                    case "05":{countyName="奎文区";}break;
                    case "19":{countyName="青州市";}break;
                    case "21":{countyName="益都县";}break;
                    case "22":{countyName="安丘县";}break;
                    case "23":{countyName="寿光县";}break;
                    case "24":{countyName="临朐县";}break;
                    case "25":{countyName="昌乐县";}break;
                    case "26":{countyName="昌邑县";}break;
                    case "27":{countyName="高密县";}break;
                    case "28":{countyName="诸城县";}break;
                    case "29":{countyName="五莲县";}break;
                    case "81":{countyName="青州市";}break;
                    case "82":{countyName="诸城市";}break;
                    case "83":{countyName="寿光市";}break;
                    case "84":{countyName="安丘市";}break;
                    case "85":{countyName="高密市";}break;
                    case "86":{countyName="昌邑市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //山东省--济宁市************************
            case "08":{
                cityName="济宁市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="市中区";}break;
                    case "11":{countyName="任城区";}break;
                    case "19":{countyName="曲阜市";}break;
                    case "22":{countyName="衮州县";}break;
                    case "23":{countyName="曲阜县";}break;
                    case "25":{countyName="邹县";}break;
                    case "26":{countyName="微山县";}break;
                    case "27":{countyName="鱼台县";}break;
                    case "28":{countyName="金乡县";}break;
                    case "29":{countyName="嘉祥县";}break;
                    case "30":{countyName="汶上县";}break;
                    case "31":{countyName="泗水县";}break;
                    case "32":{countyName="梁山县";}break;
                    case "81":{countyName="曲阜市";}break;
                    case "82":{countyName="衮州市";}break;
                    case "83":{countyName="邹城市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //山东省--泰安市************************
            case "09":{
                cityName="泰安市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="泰山区";}break;
                    case "03":{countyName="岱岳区";}break;
                    case "11":{countyName="郊区";}break;
                    case "19":{countyName="莱芜市";}break;
                    case "20":{countyName="新泰市";}break;
                    case "21":{countyName="宁阳县";}break;
                    case "22":{countyName="肥城县";}break;
                    case "23":{countyName="东平县";}break;
                    case "81":{countyName="莱芜市";}break;
                    case "82":{countyName="新泰市";}break;
                    case "83":{countyName="肥城市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //山东省--威海市************************
            case "10":{
                cityName="威海市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="环翠区";}break;
                    case "21":{countyName="乳山县";}break;
                    case "81":{countyName="文登市";}break;
                    case "82":{countyName="荣成市";}break;
                    case "83":{countyName="乳山市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //山东省--日照市************************
            case "11":{
                cityName="日照市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="东港区";}break;
                    case "03":{countyName="岚山区";}break;
                    case "21":{countyName="五莲县";}break;
                    case "22":{countyName="莒县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //山东省--莱芜市************************
            case "12":{
                cityName="莱芜市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="莱城区";}break;
                    case "03":{countyName="钢城区";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //山东省--临沂市************************
            case "13":{
                cityName="临沂市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="兰山区";}break;
                    case "11":{countyName="罗庄区";}break;
                    case "12":{countyName="河东区";}break;
                    case "21":{countyName="沂南县";}break;
                    case "22":{countyName="郯城县";}break;
                    case "23":{countyName="沂水县";}break;
                    case "24":{countyName="苍山县";}break;
                    case "25":{countyName="费县";}break;
                    case "26":{countyName="平邑县";}break;
                    case "27":{countyName="莒南县";}break;
                    case "28":{countyName="蒙阴县";}break;
                    case "29":{countyName="临沭县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //山东省--德州市************************
            case "14":{
                cityName="德州市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="德城区";}break;
                    case "21":{countyName="陵县";}break;
                    case "22":{countyName="宁津县";}break;
                    case "23":{countyName="庆云县";}break;
                    case "24":{countyName="临邑县";}break;
                    case "25":{countyName="齐河县";}break;
                    case "26":{countyName="平原县";}break;
                    case "27":{countyName="夏津县";}break;
                    case "28":{countyName="武城县";}break;
                    case "81":{countyName="乐陵市";}break;
                    case "82":{countyName="禹城市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //山东省--聊城市************************
            case "15":{
                cityName="聊城市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="东昌府区";}break;
                    case "21":{countyName="阳谷县";}break;
                    case "22":{countyName="莘县";}break;
                    case "23":{countyName="茌平县";}break;
                    case "24":{countyName="东阿县";}break;
                    case "25":{countyName="冠县";}break;
                    case "26":{countyName="高唐县";}break;
                    case "81":{countyName="临清市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //山东省--滨州市************************
            case "16":{
                cityName="滨州市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="滨城区";}break;
                    case "21":{countyName="惠民县";}break;
                    case "22":{countyName="阳信县";}break;
                    case "23":{countyName="无棣县";}break;
                    case "24":{countyName="沾化县";}break;
                    case "25":{countyName="博兴县";}break;
                    case "26":{countyName="邹平县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //山东省--菏泽市************************
            case "17":{
                cityName="菏泽市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="牡丹区";}break;
                    case "21":{countyName="曹县";}break;
                    case "22":{countyName="单县";}break;
                    case "23":{countyName="成武县";}break;
                    case "24":{countyName="巨野县";}break;
                    case "25":{countyName="郓城县";}break;
                    case "26":{countyName="鄄城县";}break;
                    case "27":{countyName="定陶县";}break;
                    case "28":{countyName="东明县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //山东省--烟台地区************************
            case "21":{
                cityName="烟台地区";
                switch (countyCode){
                    case "01":{countyName="烟台市";}break;
                    case "02":{countyName="威海市";}break;
                    case "21":{countyName="福山县";}break;
                    case "22":{countyName="蓬莱县";}break;
                    case "24":{countyName="招远县";}break;
                    case "25":{countyName="掖县";}break;
                    case "26":{countyName="莱西县";}break;
                    case "27":{countyName="莱阳县";}break;
                    case "28":{countyName="栖霞县";}break;
                    case "29":{countyName="海阳县";}break;
                    case "30":{countyName="乳山县";}break;
                    case "31":{countyName="牟平县";}break;
                    case "32":{countyName="文登县";}break;
                    case "33":{countyName="容城县";}break;
                    case "34":{countyName="长岛县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //山东省--潍坊地区************************
            case "22":{
                cityName="潍坊地区";
                switch (countyCode){
                    case "01":{countyName="潍坊市";}break;
                    case "21":{countyName="益都县";}break;
                    case "22":{countyName="安丘县";}break;
                    case "23":{countyName="寿光县";}break;
                    case "24":{countyName="临朐县";}break;
                    case "25":{countyName="昌乐县";}break;
                    case "26":{countyName="昌邑县";}break;
                    case "27":{countyName="高密县";}break;
                    case "28":{countyName="诸城县";}break;
                    case "30":{countyName="平度县";}break;
                    case "31":{countyName="潍县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //山东省--滨州地区************************
            case "23":{
                cityName="滨州地区";
                switch (countyCode){
                    case "01":{countyName="滨州市";}break;
                    case "21":{countyName="惠民县";}break;
                    case "22":{countyName="滨县";}break;
                    case "23":{countyName="阳信县";}break;
                    case "24":{countyName="无棣县";}break;
                    case "25":{countyName="沾化县";}break;
                    case "26":{countyName="利津县";}break;
                    case "27":{countyName="广饶县";}break;
                    case "28":{countyName="博兴县";}break;
                    case "29":{countyName="桓台县";}break;
                    case "30":{countyName="邹平县";}break;
                    case "31":{countyName="高青县";}break;
                    case "32":{countyName="垦利县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //山东省--德州地区************************
            case "24":{
                cityName="德州地区";
                switch (countyCode){
                    case "01":{countyName="德州市";}break;
                    case "02":{countyName="乐陵市";}break;
                    case "03":{countyName="禹城市";}break;
                    case "21":{countyName="陵县";}break;
                    case "22":{countyName="平原县";}break;
                    case "23":{countyName="夏津县";}break;
                    case "24":{countyName="武城县";}break;
                    case "25":{countyName="齐河县";}break;
                    case "26":{countyName="禹城县";}break;
                    case "27":{countyName="乐陵县";}break;
                    case "28":{countyName="临邑县";}break;
                    case "29":{countyName="商河县";}break;
                    case "30":{countyName="济阳县";}break;
                    case "31":{countyName="宁津县";}break;
                    case "32":{countyName="庆云县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //山东省--聊城市************************
            case "25":{
                cityName="聊城市";
                switch (countyCode){
                    case "01":{countyName="聊城市";}break;
                    case "02":{countyName="东昌府区";}break;
                    case "21":{countyName="阳谷县";}break;
                    case "22":{countyName="莘县";}break;
                    case "23":{countyName="茌平县";}break;
                    case "24":{countyName="东阿县";}break;
                    case "25":{countyName="冠县";}break;
                    case "26":{countyName="高唐县";}break;
                    case "27":{countyName="高唐县";}break;
                    case "28":{countyName="临清县";}break;
                    case "81":{countyName="临清市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //山东省--泰安地区************************
            case "26":{
                cityName="泰安地区";
                switch (countyCode){
                    case "01":{countyName="泰安市";}break;
                    case "02":{countyName="莱芜市";}break;
                    case "03":{countyName="新泰市";}break;
                    case "22":{countyName="莱芜县";}break;
                    case "23":{countyName="新泰县";}break;
                    case "24":{countyName="宁阳县";}break;
                    case "25":{countyName="肥城县";}break;
                    case "26":{countyName="东平县";}break;
                    case "27":{countyName="平阴县";}break;
                    case "28":{countyName="新汶县";}break;
                    case "30":{countyName="泗水县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //山东省--济宁地区************************
            case "27":{
                cityName="济宁地区";
                switch (countyCode){
                    case "01":{countyName="济宁市";}break;
                    case "22":{countyName="衮州县";}break;
                    case "23":{countyName="曲阜县";}break;
                    case "24":{countyName="泗水县";}break;
                    case "25":{countyName="邹县";}break;
                    case "26":{countyName="微山县";}break;
                    case "27":{countyName="鱼台县";}break;
                    case "28":{countyName="金乡县";}break;
                    case "29":{countyName="嘉祥县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //山东省--临沂地区************************
            case "28":{
                cityName="临沂地区";
                switch (countyCode){
                    case "01":{countyName="临沂市";}break;
                    case "02":{countyName="日照市";}break;
                    case "21":{countyName="临沂县";}break;
                    case "22":{countyName="郯城县";}break;
                    case "23":{countyName="苍山县";}break;
                    case "24":{countyName="莒南县";}break;
                    case "25":{countyName="日照县";}break;
                    case "26":{countyName="莒县";}break;
                    case "27":{countyName="沂水县";}break;
                    case "28":{countyName="沂源县";}break;
                    case "29":{countyName="蒙阴县";}break;
                    case "30":{countyName="平邑县";}break;
                    case "31":{countyName="费县";}break;
                    case "32":{countyName="沂南县";}break;
                    case "33":{countyName="临沭县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //山东省--菏泽地区************************
            case "29":{
                cityName="菏泽地区";
                switch (countyCode){
                    case "01":{countyName="菏泽市";}break;
                    case "21":{countyName="菏泽县";}break;
                    case "22":{countyName="曹县";}break;
                    case "23":{countyName="定陶县";}break;
                    case "24":{countyName="成武县";}break;
                    case "25":{countyName="单县";}break;
                    case "26":{countyName="巨野县";}break;
                    case "27":{countyName="梁山县";}break;
                    case "28":{countyName="郓城县";}break;
                    case "29":{countyName="鄄城县";}break;
                    case "30":{countyName="东明县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //山东省--其它市************************
            case "90":{
                cityName="--";
                switch (countyCode){
                    case "01":{countyName="青州市";}break;
                    case "02":{countyName="龙口市";}break;
                    case "03":{countyName="曲阜市";}break;
                    case "04":{countyName="莱芜市";}break;
                    case "05":{countyName="新泰市";}break;
                    case "06":{countyName="胶州市";}break;
                    case "07":{countyName="诸城市";}break;
                    case "08":{countyName="莱阳市";}break;
                    case "09":{countyName="莱州市";}break;
                    case "10":{countyName="滕州市";}break;
                    case "11":{countyName="文登市";}break;
                    case "12":{countyName="荣成市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //********************************
            default:{
                cityName="(身份证号码3-4位有误)";
                countyName="--";
            }break;
        }
        return provinceName+"/"+cityName+"/"+countyName;
    }

    /**
     *@Author:ShiYun;
     *@Description:获得河南省的方法
     *@Date: 15:16 2018\9\25 0025
     */
    private static String getProvinceNameByProvinceCode41(String cityCode,String countyCode){
        String provinceName = "河南省";
        String cityName = "";
        String countyName = "";
        switch (cityCode){
            //河南省--郑州市************************
            case "01":{
                cityName="郑州市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="中原区";}break;
                    case "03":{countyName="二七区";}break;
                    case "04":{countyName="管城回族区";}break;
                    case "05":{countyName="金水区";}break;
                    case "06":{countyName="上街区";}break;
                    case "07":{countyName="新密区";}break;
                    case "08":{countyName="邙山区";}break;
                    case "11":{countyName="金海区";}break;
                    case "12":{countyName="郊区";}break;
                    case "20":{countyName="市区";}break;
                    case "21":{countyName="荥阳县";}break;
                    case "22":{countyName="中牟县";}break;
                    case "23":{countyName="新郑县";}break;
                    case "24":{countyName="巩县";}break;
                    case "25":{countyName="登封县";}break;
                    case "26":{countyName="密县";}break;
                    case "81":{countyName="巩义市";}break;
                    case "82":{countyName="荥阳市";}break;
                    case "83":{countyName="新密市";}break;
                    case "84":{countyName="新郑市";}break;
                    case "85":{countyName="登封市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //河南省--开封市************************
            case "02":{
                cityName="开封市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="龙亭区";}break;
                    case "03":{countyName="顺河回族区";}break;
                    case "04":{countyName="鼓楼区";}break;
                    case "05":{countyName="禹王台区";}break;
                    case "11":{countyName="金明区";}break;
                    case "21":{countyName="杞县";}break;
                    case "22":{countyName="通许县";}break;
                    case "23":{countyName="尉氏县";}break;
                    case "24":{countyName="开封县";}break;
                    case "25":{countyName="兰考县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //河南省--洛阳市************************
            case "03":{
                cityName="洛阳市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="老城区";}break;
                    case "03":{countyName="西工区";}break;
                    case "04":{countyName="瀍河回族区";}break;
                    case "05":{countyName="涧西区";}break;
                    case "06":{countyName="吉利区";}break;
                    case "07":{countyName="洛龙区";}break;
                    case "11":{countyName="郊区";}break;
                    case "21":{countyName="偃师县";}break;
                    case "22":{countyName="孟津县";}break;
                    case "23":{countyName="新安县";}break;
                    case "24":{countyName="栾川县";}break;
                    case "25":{countyName="嵩县";}break;
                    case "26":{countyName="汝阳县";}break;
                    case "27":{countyName="宜阳县";}break;
                    case "28":{countyName="洛宁县";}break;
                    case "29":{countyName="伊川县";}break;
                    case "81":{countyName="偃师市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //河南省--平顶山市************************
            case "04":{
                cityName="平顶山市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="新华区";}break;
                    case "03":{countyName="卫东区";}break;
                    case "04":{countyName="石龙区";}break;
                    case "11":{countyName="湛河区";}break;
                    case "12":{countyName="武钢区";}break;
                    case "21":{countyName="宝丰县";}break;
                    case "22":{countyName="叶县";}break;
                    case "23":{countyName="鲁山县";}break;
                    case "24":{countyName="临汝县";}break;
                    case "25":{countyName="郏县";}break;
                    case "26":{countyName="襄城县";}break;
                    case "81":{countyName="舞钢市";}break;
                    case "82":{countyName="汝州市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //河南省--安阳市************************
            case "05":{
                cityName="安阳市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="文峰区";}break;
                    case "03":{countyName="北关区";}break;
                    case "04":{countyName="铁西区";}break;
                    case "05":{countyName="殷都区";}break;
                    case "06":{countyName="龙安区";}break;
                    case "11":{countyName="郊区";}break;
                    case "21":{countyName="林县";}break;
                    case "22":{countyName="安阳县";}break;
                    case "23":{countyName="汤阴县";}break;
                    case "24":{countyName="淇县";}break;
                    case "25":{countyName="浚县";}break;
                    case "26":{countyName="滑县";}break;
                    case "27":{countyName="内黄县";}break;
                    case "81":{countyName="林州市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //河南省--鹤壁市************************
            case "06":{
                cityName="鹤壁市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="鹤山区";}break;
                    case "03":{countyName="山城区";}break;
                    case "11":{countyName="淇滨区";}break;
                    case "21":{countyName="浚县";}break;
                    case "22":{countyName="淇县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //河南省--新乡市************************
            case "07":{
                cityName="新乡市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="红旗区";}break;
                    case "03":{countyName="新华区";}break;
                    case "04":{countyName="北站区";}break;
                    case "11":{countyName="郊区";}break;
                    case "21":{countyName="新乡县";}break;
                    case "22":{countyName="汲县";}break;
                    case "23":{countyName="辉县";}break;
                    case "24":{countyName="获嘉县";}break;
                    case "25":{countyName="原阳县";}break;
                    case "26":{countyName="延津县";}break;
                    case "27":{countyName="封丘县";}break;
                    case "28":{countyName="长垣县";}break;
                    case "81":{countyName="卫辉市";}break;
                    case "82":{countyName="辉县市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //河南省--焦作市************************
            case "08":{
                cityName="焦作市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="解放区";}break;
                    case "03":{countyName="中站区";}break;
                    case "04":{countyName="马村区";}break;
                    case "11":{countyName="山阳区";}break;
                    case "21":{countyName="修武区";}break;
                    case "22":{countyName="博爱县";}break;
                    case "23":{countyName="武陟县";}break;
                    case "24":{countyName="沁阳县";}break;
                    case "25":{countyName="温县";}break;
                    case "26":{countyName="孟县";}break;
                    case "27":{countyName="济源县";}break;
                    case "81":{countyName="济源市";}break;
                    case "82":{countyName="沁阳市";}break;
                    case "83":{countyName="孟州市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //河南省--濮阳市************************
            case "09":{
                cityName="濮阳市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="市区";}break;
                    case "21":{countyName="滑县";}break;
                    case "22":{countyName="清丰县";}break;
                    case "23":{countyName="南乐县";}break;
                    case "24":{countyName="内黄县";}break;
                    case "25":{countyName="长垣县";}break;
                    case "26":{countyName="范县";}break;
                    case "27":{countyName="台前县";}break;
                    case "28":{countyName="濮阳县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //河南省--许昌市************************
            case "10":{
                cityName="许昌市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="魏都区";}break;
                    case "21":{countyName="禹县";}break;
                    case "22":{countyName="长葛县";}break;
                    case "23":{countyName="许昌县";}break;
                    case "24":{countyName="鄢陵县";}break;
                    case "25":{countyName="襄城县";}break;
                    case "81":{countyName="禹州市";}break;
                    case "82":{countyName="长葛市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //河南省--漯河市************************
            case "11":{
                cityName="漯河市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="源汇区";}break;
                    case "03":{countyName="郾城区";}break;
                    case "04":{countyName="召陵区";}break;
                    case "21":{countyName="舞阳县";}break;
                    case "22":{countyName="临颍县";}break;
                    case "23":{countyName="郾城县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //河南省--三门峡市************************
            case "12":{
                cityName="三门峡市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="湖滨区";}break;
                    case "21":{countyName="渑池县";}break;
                    case "22":{countyName="郏县";}break;
                    case "23":{countyName="灵宝县";}break;
                    case "24":{countyName="卢氏县";}break;
                    case "81":{countyName="义马市";}break;
                    case "82":{countyName="灵宝市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //河南省--南阳市************************
            case "13":{
                cityName="南阳市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="宛城区";}break;
                    case "03":{countyName="卧龙区";}break;
                    case "21":{countyName="南召县";}break;
                    case "22":{countyName="方城县";}break;
                    case "23":{countyName="西峡县";}break;
                    case "24":{countyName="镇平县";}break;
                    case "25":{countyName="内乡县";}break;
                    case "26":{countyName="淅川县";}break;
                    case "27":{countyName="社旗县";}break;
                    case "28":{countyName="唐河县";}break;
                    case "29":{countyName="新野县";}break;
                    case "30":{countyName="桐柏县";}break;
                    case "81":{countyName="邓州市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //河南省--商丘市************************
            case "14":{
                cityName="商丘市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="梁园区";}break;
                    case "03":{countyName="睢阳区";}break;
                    case "21":{countyName="民权县";}break;
                    case "22":{countyName="睢县";}break;
                    case "23":{countyName="宁陵县";}break;
                    case "24":{countyName="拓城县";}break;
                    case "25":{countyName="虞城县";}break;
                    case "26":{countyName="夏邑县";}break;
                    case "81":{countyName="永城市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //河南省--信阳市************************
            case "15":{
                cityName="信阳市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="师河区";}break;
                    case "03":{countyName="平桥区";}break;
                    case "21":{countyName="罗山县";}break;
                    case "22":{countyName="光山县";}break;
                    case "23":{countyName="新县";}break;
                    case "24":{countyName="商城县";}break;
                    case "25":{countyName="固始县";}break;
                    case "26":{countyName="潢川县";}break;
                    case "27":{countyName="淮滨县";}break;
                    case "28":{countyName="息县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //河南省--周口市************************
            case "16":{
                cityName="周口市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="川汇区";}break;
                    case "21":{countyName="扶沟县";}break;
                    case "22":{countyName="西华县";}break;
                    case "23":{countyName="商水县";}break;
                    case "24":{countyName="沈丘县";}break;
                    case "25":{countyName="郸城县";}break;
                    case "26":{countyName="淮阳县";}break;
                    case "27":{countyName="太康县";}break;
                    case "28":{countyName="鹿邑县";}break;
                    case "81":{countyName="项城市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //河南省--驻马店市************************
            case "17":{
                cityName="驻马店市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="驿城区";}break;
                    case "21":{countyName="西平县";}break;
                    case "22":{countyName="上蔡县";}break;
                    case "23":{countyName="平舆县";}break;
                    case "24":{countyName="正阳县";}break;
                    case "25":{countyName="确山县";}break;
                    case "26":{countyName="沁阳县";}break;
                    case "27":{countyName="汝南县";}break;
                    case "28":{countyName="遂平县";}break;
                    case "29":{countyName="新蔡县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //河南省--商丘地区************************
            case "23":{
                cityName="商丘地区";
                switch (countyCode){
                    case "01":{countyName="商丘市";}break;
                    case "02":{countyName="永城市";}break;
                    case "21":{countyName="虞城县";}break;
                    case "22":{countyName="商丘县";}break;
                    case "23":{countyName="民权县";}break;
                    case "24":{countyName="宁陵县";}break;
                    case "25":{countyName="睢县";}break;
                    case "26":{countyName="夏邑县";}break;
                    case "27":{countyName="拓城县";}break;
                    case "28":{countyName="永城县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //河南省--开封地区************************
            case "24":{
                cityName="开封地区";
                switch (countyCode){
                    case "21":{countyName="杞县";}break;
                    case "22":{countyName="通许县";}break;
                    case "23":{countyName="尉氏县";}break;
                    case "24":{countyName="开封县";}break;
                    case "25":{countyName="中牟县";}break;
                    case "26":{countyName="新郑县";}break;
                    case "27":{countyName="巩县";}break;
                    case "28":{countyName="登封县";}break;
                    case "29":{countyName="密县";}break;
                    case "30":{countyName="兰考县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //河南省--三门峡市************************
            case "25":{
                cityName="三门峡市";
                switch (countyCode){
                    case "01":{countyName="三门峡市";}break;
                    case "02":{countyName="义马市";}break;
                    case "21":{countyName="偃师县";}break;
                    case "22":{countyName="孟津县";}break;
                    case "23":{countyName="新安县";}break;
                    case "24":{countyName="渑池县";}break;
                    case "25":{countyName="郏县";}break;
                    case "26":{countyName="灵宝县";}break;
                    case "27":{countyName="伊川县";}break;
                    case "28":{countyName="汝阳县";}break;
                    case "29":{countyName="嵩县";}break;
                    case "30":{countyName="洛宁县";}break;
                    case "31":{countyName="卢氏县";}break;
                    case "32":{countyName="栾川县";}break;
                    case "33":{countyName="临汝县";}break;
                    case "34":{countyName="宜阳县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //河南省--其它市1************************
            case "26":{
                cityName="--";
                switch (countyCode){
                    case "01":{countyName="许昌市";}break;
                    case "02":{countyName="漯河市";}break;
                    case "21":{countyName="长葛县";}break;
                    case "22":{countyName="禹县";}break;
                    case "23":{countyName="鄢陵县";}break;
                    case "24":{countyName="许昌县";}break;
                    case "25":{countyName="郏县";}break;
                    case "26":{countyName="临颍县";}break;
                    case "27":{countyName="襄城县";}break;
                    case "28":{countyName="宝丰县";}break;
                    case "29":{countyName="郾城县";}break;
                    case "30":{countyName="叶县";}break;
                    case "31":{countyName="鲁山县";}break;
                    case "32":{countyName="舞阳县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //河南省--周口地区************************
            case "27":{
                cityName="周口地区";
                switch (countyCode){
                    case "01":{countyName="周口市";}break;
                    case "02":{countyName="项城市";}break;
                    case "21":{countyName="扶沟县";}break;
                    case "22":{countyName="西华县";}break;
                    case "23":{countyName="商水县";}break;
                    case "24":{countyName="太康县";}break;
                    case "25":{countyName="鹿邑县";}break;
                    case "26":{countyName="郸城县";}break;
                    case "27":{countyName="淮阳县";}break;
                    case "28":{countyName="沈丘县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //河南省--驻马店地区************************
            case "28":{
                cityName="驻马店地区";
                switch (countyCode){
                    case "01":{countyName="驻马店市";}break;
                    case "21":{countyName="确山县";}break;
                    case "22":{countyName="沁阳县";}break;
                    case "23":{countyName="遂平县";}break;
                    case "24":{countyName="西平县";}break;
                    case "25":{countyName="上蔡县";}break;
                    case "26":{countyName="汝南县";}break;
                    case "27":{countyName="平舆县";}break;
                    case "28":{countyName="新蔡县";}break;
                    case "29":{countyName="正阳县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //河南省--南阳市************************
            case "29":{
                cityName="南阳市";
                switch (countyCode){
                    case "01":{countyName="卧龙区";}break;
                    case "02":{countyName="邓州市";}break;
                    case "03":{countyName="宛城区";}break;
                    case "21":{countyName="南召县";}break;
                    case "22":{countyName="方城县";}break;
                    case "23":{countyName="西峡县";}break;
                    case "24":{countyName="南阳县";}break;
                    case "25":{countyName="镇平县";}break;
                    case "26":{countyName="内乡县";}break;
                    case "27":{countyName="淅川县";}break;
                    case "28":{countyName="社旗县";}break;
                    case "29":{countyName="唐河县";}break;
                    case "30":{countyName="邓县";}break;
                    case "31":{countyName="新野县";}break;
                    case "32":{countyName="桐柏县";}break;
                    case "33":{countyName="规划控制区";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //河南省--信阳地区************************
            case "30":{
                cityName="信阳地区";
                switch (countyCode){
                    case "01":{countyName="信阳市";}break;
                    case "21":{countyName="息县";}break;
                    case "22":{countyName="淮滨县";}break;
                    case "23":{countyName="平桥区";}break;
                    case "24":{countyName="潢川县";}break;
                    case "25":{countyName="光山县";}break;
                    case "26":{countyName="固始县";}break;
                    case "27":{countyName="商城县";}break;
                    case "28":{countyName="罗山县";}break;
                    case "29":{countyName="新县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //河南省--其它市2************************
            case "90":{
                cityName="--";
                switch (countyCode){
                    case "01":{countyName="义马市";}break;
                    case "02":{countyName="汝州市";}break;
                    case "03":{countyName="济源市";}break;
                    case "04":{countyName="禹州市";}break;
                    case "05":{countyName="卫辉市";}break;
                    case "06":{countyName="辉县市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //********************************
            default:{
                cityName="(身份证号码3-4位有误)";
                countyName="--";
            }break;
        }
        return provinceName+"/"+cityName+"/"+countyName;
    }

    /**
     *@Author:ShiYun;
     *@Description:获得湖北省的方法
     *@Date: 19:33 2018\9\25 0025
     */
    private static String getProvinceNameByProvinceCode42(String cityCode,String countyCode){
        String provinceName = "湖北省";
        String cityName = "";
        String countyName = "";
        switch (cityCode){
            //湖北省--武汉市************************
            case "01":{
                cityName="武汉市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="江岸区";}break;
                    case "03":{countyName="江汉区";}break;
                    case "04":{countyName="乔口区";}break;
                    case "05":{countyName="汉阳区";}break;
                    case "06":{countyName="武昌区";}break;
                    case "07":{countyName="青山区";}break;
                    case "09":{countyName="东湖管理分局";}break;
                    case "11":{countyName="洪山区";}break;
                    case "12":{countyName="东西湖区";}break;
                    case "13":{countyName="汉南区";}break;
                    case "14":{countyName="蔡甸区";}break;
                    case "15":{countyName="江夏区";}break;
                    case "16":{countyName="黄陂区";}break;
                    case "17":{countyName="新洲区";}break;
                    case "21":{countyName="蔡甸区";}break;
                    case "22":{countyName="江夏区";}break;
                    case "23":{countyName="黄陂区";}break;
                    case "24":{countyName="新洲县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //湖北省--黄石市************************
            case "02":{
                cityName="黄石市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="黄石港区";}break;
                    case "03":{countyName="石灰窑区";}break;
                    case "04":{countyName="下陆区";}break;
                    case "05":{countyName="铁山区";}break;
                    case "20":{countyName="市区";}break;
                    case "21":{countyName="大冶县";}break;
                    case "22":{countyName="阳新县";}break;
                    case "81":{countyName="大冶市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //湖北省--十堰市************************
            case "03":{
                cityName="十堰市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="茅箭区";}break;
                    case "03":{countyName="张湾区";}break;
                    case "21":{countyName="郧县";}break;
                    case "22":{countyName="郧西县";}break;
                    case "23":{countyName="竹山县";}break;
                    case "24":{countyName="竹溪县";}break;
                    case "25":{countyName="房县";}break;
                    case "81":{countyName="丹江口市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //湖北省--沙市************************
            case "04":{
                cityName="沙市";
                switch (countyCode){
                    case "00":{countyName="--";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //湖北省--宜昌市************************
            case "05":{
                cityName="宜昌市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="西陵区";}break;
                    case "03":{countyName="伍家岗区";}break;
                    case "04":{countyName="点军区";}break;
                    case "05":{countyName="猇亭区";}break;
                    case "06":{countyName="夷陵区";}break;
                    case "21":{countyName="宜昌县";}break;
                    case "23":{countyName="枝江县";}break;
                    case "25":{countyName="远安县";}break;
                    case "26":{countyName="兴山县";}break;
                    case "27":{countyName="秭归县";}break;
                    case "28":{countyName="长阳土家族自治县";}break;
                    case "29":{countyName="五峰土家族自治县";}break;
                    case "81":{countyName="宜都市";}break;
                    case "82":{countyName="当阳市";}break;
                    case "83":{countyName="枝江市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //湖北省--襄樊市************************
            case "06":{
                cityName="襄樊市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="襄城区";}break;
                    case "03":{countyName="樊东区";}break;
                    case "04":{countyName="樊西区";}break;
                    case "05":{countyName="郊区";}break;
                    case "06":{countyName="樊城区";}break;
                    case "07":{countyName="襄阳区";}break;
                    case "19":{countyName="随州市";}break;
                    case "20":{countyName="老河口市";}break;
                    case "21":{countyName="襄阳县";}break;
                    case "22":{countyName="枣阳县";}break;
                    case "23":{countyName="宜城县";}break;
                    case "24":{countyName="南漳县";}break;
                    case "25":{countyName="谷城县";}break;
                    case "26":{countyName="保康县";}break;
                    case "81":{countyName="随州市";}break;
                    case "82":{countyName="老河口市";}break;
                    case "83":{countyName="枣阳市";}break;
                    case "84":{countyName="宜城市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //湖北省--鄂州市************************
            case "07":{
                cityName="鄂州市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="梁子湖区";}break;
                    case "03":{countyName="华容区";}break;
                    case "04":{countyName="鄂城区";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //湖北省--荆门市************************
            case "08":{
                cityName="荆门市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="东宝区";}break;
                    case "03":{countyName="沙洋区";}break;
                    case "04":{countyName="掇刀区";}break;
                    case "21":{countyName="京山县";}break;
                    case "22":{countyName="沙洋县";}break;
                    case "81":{countyName="钟祥市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //湖北省--孝感市************************
            case "09":{
                cityName="孝感市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="孝南区";}break;
                    case "21":{countyName="孝昌县";}break;
                    case "22":{countyName="大悟县";}break;
                    case "23":{countyName="云梦县";}break;
                    case "81":{countyName="应城市";}break;
                    case "82":{countyName="安陆市";}break;
                    case "83":{countyName="广水市";}break;
                    case "84":{countyName="汉川市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //湖北省--荆州市************************
            case "10":{
                cityName="荆州市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="沙市区";}break;
                    case "03":{countyName="荆州区";}break;
                    case "04":{countyName="江陵区";}break;
                    case "21":{countyName="松滋县";}break;
                    case "22":{countyName="公安县";}break;
                    case "23":{countyName="监利县";}break;
                    case "24":{countyName="江陵县";}break;
                    case "81":{countyName="石首市";}break;
                    case "83":{countyName="洪湖市";}break;
                    case "87":{countyName="松滋市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //湖北省--黄冈市************************
            case "11":{
                cityName="黄冈市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="黄州区";}break;
                    case "03":{countyName="龙感湖管理区";}break;
                    case "21":{countyName="团风县";}break;
                    case "22":{countyName="红安县";}break;
                    case "23":{countyName="罗田县";}break;
                    case "24":{countyName="英山县";}break;
                    case "25":{countyName="浠水县";}break;
                    case "26":{countyName="蕲春县";}break;
                    case "27":{countyName="黄梅县";}break;
                    case "81":{countyName="麻城市";}break;
                    case "82":{countyName="武穴市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //湖北省--咸宁市************************
            case "12":{
                cityName="咸宁市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="咸安区";}break;
                    case "21":{countyName="嘉鱼县";}break;
                    case "22":{countyName="通城县";}break;
                    case "23":{countyName="崇阳县";}break;
                    case "24":{countyName="通山县";}break;
                    case "81":{countyName="赤壁市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //湖北省--随州市************************
            case "13":{
                cityName="随州市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="曾都区";}break;
                    case "81":{countyName="广水市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //湖北省--黄冈地区************************
            case "21":{
                cityName="黄冈地区";
                switch (countyCode){
                    case "01":{countyName="鄂州市";}break;
                    case "02":{countyName="武穴市";}break;
                    case "03":{countyName="黄州市";}break;
                    case "21":{countyName="黄冈县";}break;
                    case "22":{countyName="新洲县";}break;
                    case "23":{countyName="红安县";}break;
                    case "24":{countyName="麻城县";}break;
                    case "25":{countyName="罗田县";}break;
                    case "26":{countyName="英山县";}break;
                    case "27":{countyName="浠水县";}break;
                    case "28":{countyName="蕲春县";}break;
                    case "29":{countyName="广济县";}break;
                    case "30":{countyName="黄梅县";}break;
                    case "31":{countyName="鄂城县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //湖北省--孝感地区************************
            case "22":{
                cityName="孝感地区";
                switch (countyCode){
                    case "01":{countyName="孝感市";}break;
                    case "02":{countyName="应城市";}break;
                    case "03":{countyName="安陆市";}break;
                    case "04":{countyName="广水市";}break;
                    case "21":{countyName="孝感县";}break;
                    case "22":{countyName="黄陂县";}break;
                    case "23":{countyName="大悟县";}break;
                    case "24":{countyName="应山县";}break;
                    case "25":{countyName="安陆县";}break;
                    case "26":{countyName="云梦县";}break;
                    case "27":{countyName="应城县";}break;
                    case "28":{countyName="汉川县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //湖北省--咸宁地区************************
            case "23":{
                cityName="咸宁地区";
                switch (countyCode){
                    case "01":{countyName="咸宁市";}break;
                    case "02":{countyName="赤壁市";}break;
                    case "21":{countyName="咸宁县";}break;
                    case "22":{countyName="嘉鱼县";}break;
                    case "23":{countyName="蒲圻县";}break;
                    case "24":{countyName="通城县";}break;
                    case "25":{countyName="崇阳县";}break;
                    case "26":{countyName="通山县";}break;
                    case "27":{countyName="阳新县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //湖北省--荆州地区************************
            case "24":{
                cityName="荆州地区";
                switch (countyCode){
                    case "01":{countyName="仙桃市";}break;
                    case "02":{countyName="石首市";}break;
                    case "03":{countyName="洪湖市";}break;
                    case "04":{countyName="天门市";}break;
                    case "05":{countyName="潜江市";}break;
                    case "06":{countyName="钟祥市";}break;
                    case "21":{countyName="江陵县";}break;
                    case "22":{countyName="松滋县";}break;
                    case "23":{countyName="公安县";}break;
                    case "24":{countyName="石首县";}break;
                    case "25":{countyName="监利县";}break;
                    case "26":{countyName="洪湖县";}break;
                    case "27":{countyName="沔阳县";}break;
                    case "28":{countyName="天门县";}break;
                    case "29":{countyName="潜江县";}break;
                    case "30":{countyName="荆门县";}break;
                    case "31":{countyName="钟祥县";}break;
                    case "32":{countyName="京山县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //湖北省--随州地区************************
            case "25":{
                cityName="随州地区";
                switch (countyCode){
                    case "01":{countyName="随州市";}break;
                    case "02":{countyName="老河口市";}break;
                    case "21":{countyName="襄阳县";}break;
                    case "22":{countyName="枣阳县";}break;
                    case "24":{countyName="宜城县";}break;
                    case "25":{countyName="南漳县";}break;
                    case "27":{countyName="谷城县";}break;
                    case "28":{countyName="保康县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //湖北省--郧阳地区************************
            case "26":{
                cityName="郧阳地区";
                switch (countyCode){
                    case "01":{countyName="丹江口市";}break;
                    case "22":{countyName="郧县";}break;
                    case "23":{countyName="郧西县";}break;
                    case "24":{countyName="竹山县";}break;
                    case "25":{countyName="竹溪县";}break;
                    case "26":{countyName="房县";}break;
                    case "27":{countyName="神龙架林区";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //湖北省--宜昌地区************************
            case "27":{
                cityName="宜昌地区";
                switch (countyCode){
                    case "01":{countyName="枝城市";}break;
                    case "02":{countyName="当阳市";}break;
                    case "21":{countyName="宜昌县";}break;
                    case "22":{countyName="宜都县";}break;
                    case "23":{countyName="枝江县";}break;
                    case "24":{countyName="当阳县";}break;
                    case "25":{countyName="远安县";}break;
                    case "26":{countyName="兴山县";}break;
                    case "27":{countyName="秭归县";}break;
                    case "28":{countyName="长阳土家族自治县";}break;
                    case "29":{countyName="五峰土家族自治县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //湖北省--恩施土家族苗族自治州************************
            case "28":{
                cityName="恩施土家族苗族自治州";
                switch (countyCode){
                    case "01":{countyName="恩施市";}break;
                    case "02":{countyName="利川市";}break;
                    case "22":{countyName="建始县";}break;
                    case "23":{countyName="巴东县";}break;
                    case "25":{countyName="宣恩县";}break;
                    case "26":{countyName="咸丰县";}break;
                    case "27":{countyName="来凤县";}break;
                    case "28":{countyName="鹤峰县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //湖北省--省直辖行政单位************************
            case "29":{
                cityName="省直辖行政单位";
                switch (countyCode){
                    case "21":{countyName="神龙架林区";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //湖北省--沙洋监狱管理局************************
            case "30":{
                cityName="--";
                switch (countyCode){
                    case "22":{countyName="沙洋监狱管理局";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //湖北省--省直辖行政单位************************
            case "90":{
                cityName="省直辖行政单位";
                switch (countyCode){
                    case "01":{countyName="随州市";}break;
                    case "03":{countyName="枣阳市";}break;
                    case "04":{countyName="仙桃市";}break;
                    case "05":{countyName="潜江市";}break;
                    case "06":{countyName="天门市";}break;
                    case "21":{countyName="神龙架林区";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //**************************************
            default:{
                cityName="(身份证号码3-4位有误)";
                countyName="--";
            }break;
        }
        return provinceName+"/"+cityName+"/"+countyName;
    }

    /**
     *@Author:ShiYun;
     *@Description:获得湖南省的方法
     *@Date: 9:49 2018\9\26 0026
     */
    private static String getProvinceNameByProvinceCode43(String cityCode,String countyCode){
        String provinceName = "湖南省";
        String cityName = "";
        String countyName = "";
        switch (cityCode){
            //湖南省--长沙市************************
            case "01":{
                cityName="长沙市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="芙蓉区";}break;
                    case "03":{countyName="天心区";}break;
                    case "04":{countyName="岳麓区";}break;
                    case "05":{countyName="开福区";}break;
                    case "11":{countyName="雨花区";}break;
                    case "20":{countyName="市区";}break;
                    case "21":{countyName="长沙县";}break;
                    case "22":{countyName="望城县";}break;
                    case "23":{countyName="浏阳县";}break;
                    case "24":{countyName="宁乡县";}break;
                    case "81":{countyName="浏阳市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //湖南省--株洲市************************
            case "02":{
                cityName="株洲市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="荷塘区";}break;
                    case "03":{countyName="芦淞区";}break;
                    case "04":{countyName="石峰区";}break;
                    case "11":{countyName="天元区";}break;
                    case "19":{countyName="醴陵市";}break;
                    case "20":{countyName="市区";}break;
                    case "21":{countyName="株洲县";}break;
                    case "22":{countyName="醴陵县";}break;
                    case "23":{countyName="攸县";}break;
                    case "24":{countyName="茶陵县";}break;
                    case "25":{countyName="炎陵县";}break;
                    case "81":{countyName="醴陵市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //湖南省--湘潭市************************
            case "03":{
                cityName="湘潭市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="雨湖区";}break;
                    case "03":{countyName="湘江区";}break;
                    case "04":{countyName="岳塘区";}break;
                    case "05":{countyName="板塘区";}break;
                    case "06":{countyName="韶山区";}break;
                    case "11":{countyName="市区";}break;
                    case "12":{countyName="韶山区";}break;
                    case "21":{countyName="湘潭县";}break;
                    case "22":{countyName="湘乡县";}break;
                    case "81":{countyName="湘乡市";}break;
                    case "82":{countyName="韶山市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //湖南省--衡阳市************************
            case "04":{
                cityName="衡阳市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="江东区";}break;
                    case "03":{countyName="城南区";}break;
                    case "04":{countyName="城北区";}break;
                    case "05":{countyName="珠晖区";}break;
                    case "06":{countyName="雁峰区";}break;
                    case "07":{countyName="石鼓区";}break;
                    case "08":{countyName="蒸湘区";}break;
                    case "11":{countyName="郊区";}break;
                    case "12":{countyName="南岳区";}break;
                    case "19":{countyName="耒阳市";}break;
                    case "21":{countyName="衡阳县";}break;
                    case "22":{countyName="衡南县";}break;
                    case "23":{countyName="衡山县";}break;
                    case "24":{countyName="衡东县";}break;
                    case "25":{countyName="常宁县";}break;
                    case "26":{countyName="祁东县";}break;
                    case "27":{countyName="耒阳县";}break;
                    case "81":{countyName="耒阳市";}break;
                    case "82":{countyName="常宁市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //湖南省--邵阳市************************
            case "05":{
                cityName="邵阳市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="双清区";}break;
                    case "03":{countyName="大祥区";}break;
                    case "11":{countyName="北塔区";}break;
                    case "21":{countyName="邵东县";}break;
                    case "22":{countyName="新邵县";}break;
                    case "23":{countyName="邵阳县";}break;
                    case "24":{countyName="隆回县";}break;
                    case "25":{countyName="洞口县";}break;
                    case "26":{countyName="武冈县";}break;
                    case "27":{countyName="绥宁县";}break;
                    case "28":{countyName="新宁县";}break;
                    case "29":{countyName="城步苗族自治县";}break;
                    case "81":{countyName="武冈市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //湖南省--岳阳市************************
            case "06":{
                cityName="岳阳市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="岳阳楼区";}break;
                    case "03":{countyName="云溪区";}break;
                    case "11":{countyName="君山区";}break;
                    case "21":{countyName="岳阳县";}break;
                    case "22":{countyName="临湘县";}break;
                    case "23":{countyName="华容县";}break;
                    case "24":{countyName="湘阴县";}break;
                    case "25":{countyName="汨罗县";}break;
                    case "26":{countyName="平江县";}break;
                    case "81":{countyName="汨罗市";}break;
                    case "82":{countyName="临湘市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //湖南省--常德市************************
            case "07":{
                cityName="常德市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="武陵区";}break;
                    case "03":{countyName="鼎城区";}break;
                    case "21":{countyName="安乡县";}break;
                    case "22":{countyName="汉寿县";}break;
                    case "23":{countyName="澧县";}break;
                    case "24":{countyName="临澧县";}break;
                    case "25":{countyName="桃源县";}break;
                    case "26":{countyName="石门县";}break;
                    case "81":{countyName="津市市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //湖南省--张家界市************************
            case "08":{
                cityName="张家界市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="永定区";}break;
                    case "11":{countyName="武陵源区";}break;
                    case "21":{countyName="慈利县";}break;
                    case "22":{countyName="桑植县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //湖南省--益阳市************************
            case "09":{
                cityName="益阳市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="资阳区";}break;
                    case "03":{countyName="赫山区";}break;
                    case "11":{countyName="市区";}break;
                    case "21":{countyName="南县";}break;
                    case "22":{countyName="桃江县";}break;
                    case "23":{countyName="安化县";}break;
                    case "81":{countyName="沅江市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //湖南省--郴州市************************
            case "10":{
                cityName="郴州市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="北湖区";}break;
                    case "03":{countyName="苏仙区";}break;
                    case "21":{countyName="桂阳县";}break;
                    case "22":{countyName="宜章县";}break;
                    case "23":{countyName="永兴县";}break;
                    case "24":{countyName="嘉禾县";}break;
                    case "25":{countyName="临武县";}break;
                    case "26":{countyName="汝城县";}break;
                    case "27":{countyName="桂东县";}break;
                    case "28":{countyName="安仁县";}break;
                    case "81":{countyName="资兴市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //湖南省--永州市************************
            case "11":{
                cityName="永州市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="零陵区";}break;
                    case "03":{countyName="冷水滩区";}break;
                    case "21":{countyName="祁阳县";}break;
                    case "22":{countyName="东安县";}break;
                    case "23":{countyName="双牌县";}break;
                    case "24":{countyName="道县";}break;
                    case "25":{countyName="江永县";}break;
                    case "26":{countyName="宁远县";}break;
                    case "27":{countyName="蓝山县";}break;
                    case "28":{countyName="新田县";}break;
                    case "29":{countyName="江华瑶族自治县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //湖南省--怀化市************************
            case "12":{
                cityName="怀化市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="鹤城区";}break;
                    case "21":{countyName="中方县";}break;
                    case "22":{countyName="沅陵县";}break;
                    case "23":{countyName="辰溪县";}break;
                    case "24":{countyName="溆浦县";}break;
                    case "25":{countyName="会同县";}break;
                    case "26":{countyName="麻阳苗族自治县";}break;
                    case "27":{countyName="新晃侗族自治县";}break;
                    case "28":{countyName="芷江侗族自治县";}break;
                    case "29":{countyName="靖州苗族侗族自治县";}break;
                    case "30":{countyName="通道侗族自治县";}break;
                    case "81":{countyName="洪江市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //湖南省--娄底市************************
            case "13":{
                cityName="娄底市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="娄星区";}break;
                    case "21":{countyName="双峰县";}break;
                    case "22":{countyName="新化县";}break;
                    case "81":{countyName="冷水江市";}break;
                    case "82":{countyName="涟源市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //湖南省--湘潭地区************************
            case "21":{
                cityName="湘潭地区";
                switch (countyCode){
                    case "21":{countyName="湘潭县";}break;
                    case "22":{countyName="湘乡县";}break;
                    case "23":{countyName="醴陵县";}break;
                    case "24":{countyName="浏阳县";}break;
                    case "25":{countyName="攸县";}break;
                    case "26":{countyName="茶陵县";}break;
                    case "27":{countyName="酃县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //湖南省--岳阳地区************************
            case "22":{
                cityName="岳阳地区";
                switch (countyCode){
                    case "01":{countyName="岳阳市";}break;
                    case "22":{countyName="平江县";}break;
                    case "23":{countyName="湘阴县";}break;
                    case "24":{countyName="汨罗县";}break;
                    case "25":{countyName="临湘市";}break;
                    case "26":{countyName="华容县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //湖南省--益阳地区************************
            case "23":{
                cityName="益阳地区";
                switch (countyCode){
                    case "01":{countyName="益阳市";}break;
                    case "02":{countyName="沅江市";}break;
                    case "21":{countyName="宜阳县";}break;
                    case "22":{countyName="南县";}break;
                    case "23":{countyName="沅江县";}break;
                    case "24":{countyName="宁乡县";}break;
                    case "25":{countyName="桃江县";}break;
                    case "26":{countyName="安化县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //湖南省--常德地区************************
            case "24":{
                cityName="常德地区";
                switch (countyCode){
                    case "01":{countyName="常德市";}break;
                    case "02":{countyName="津市市";}break;
                    case "21":{countyName="常德县";}break;
                    case "22":{countyName="安乡县";}break;
                    case "23":{countyName="汉寿县";}break;
                    case "24":{countyName="澧县";}break;
                    case "25":{countyName="临澧县";}break;
                    case "26":{countyName="桃源县";}break;
                    case "27":{countyName="石门县";}break;
                    case "28":{countyName="慈利县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //湖南省--娄底地区************************
            case "25":{
                cityName="娄底地区";
                switch (countyCode){
                    case "01":{countyName="娄底市";}break;
                    case "02":{countyName="冷水江市";}break;
                    case "03":{countyName="涟源市";}break;
                    case "21":{countyName="涟源县";}break;
                    case "22":{countyName="双峰县";}break;
                    case "23":{countyName="邵东县";}break;
                    case "24":{countyName="新化县";}break;
                    case "25":{countyName="新邵县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //湖南省--邵阳地区************************
            case "26":{
                cityName="邵阳地区";
                switch (countyCode){
                    case "21":{countyName="邵阳县";}break;
                    case "22":{countyName="隆回县";}break;
                    case "23":{countyName="武冈县";}break;
                    case "24":{countyName="洞口县";}break;
                    case "25":{countyName="新宁县";}break;
                    case "26":{countyName="绥宁县";}break;
                    case "27":{countyName="城步苗族自治县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //湖南省--衡阳地区************************
            case "27":{
                cityName="衡阳地区";
                switch (countyCode){
                    case "21":{countyName="衡阳县";}break;
                    case "22":{countyName="衡南县";}break;
                    case "23":{countyName="衡山县";}break;
                    case "24":{countyName="衡东县";}break;
                    case "25":{countyName="常宁县";}break;
                    case "26":{countyName="祁东县";}break;
                    case "27":{countyName="祁阳县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //湖南省--株洲地区************************
            case "28":{
                cityName="株洲地区";
                switch (countyCode){
                    case "01":{countyName="株洲市";}break;
                    case "02":{countyName="资兴市";}break;
                    case "21":{countyName="郴县";}break;
                    case "22":{countyName="桂阳县";}break;
                    case "23":{countyName="永兴县";}break;
                    case "24":{countyName="宜章县";}break;
                    case "25":{countyName="资兴县";}break;
                    case "26":{countyName="嘉禾县";}break;
                    case "27":{countyName="临武县";}break;
                    case "28":{countyName="汝城县";}break;
                    case "29":{countyName="桂东县";}break;
                    case "30":{countyName="耒阳县";}break;
                    case "31":{countyName="安仁县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //湖南省--零陵地区************************
            case "29":{
                cityName="零陵地区";
                switch (countyCode){
                    case "01":{countyName="永州市";}break;
                    case "02":{countyName="冷水滩市";}break;
                    case "21":{countyName="零陵县";}break;
                    case "22":{countyName="东安县";}break;
                    case "23":{countyName="道县";}break;
                    case "24":{countyName="宁远县";}break;
                    case "25":{countyName="江永县";}break;
                    case "26":{countyName="江华瑶族自治县";}break;
                    case "27":{countyName="蓝山县";}break;
                    case "28":{countyName="新田县";}break;
                    case "29":{countyName="双牌县";}break;
                    case "30":{countyName="双牌县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //湖南省--怀化地区************************
            case "30":{
                cityName="怀化地区";
                switch (countyCode){
                    case "01":{countyName="怀化市";}break;
                    case "02":{countyName="洪江市";}break;
                    case "21":{countyName="黔阳县";}break;
                    case "22":{countyName="沅陵县";}break;
                    case "23":{countyName="辰溪县";}break;
                    case "24":{countyName="溆浦县";}break;
                    case "25":{countyName="麻阳县";}break;
                    case "26":{countyName="新晃侗族自治县";}break;
                    case "27":{countyName="芷江县";}break;
                    case "28":{countyName="怀化县";}break;
                    case "29":{countyName="会同县";}break;
                    case "30":{countyName="靖县";}break;
                    case "31":{countyName="通道侗族自治县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //湖南省--湘西土家族苗族自治州************************
            case "31":{
                cityName="湘西土家族苗族自治州";
                switch (countyCode){
                    case "01":{countyName="吉首市";}break;
                    case "21":{countyName="吉首县";}break;
                    case "22":{countyName="泸溪县";}break;
                    case "23":{countyName="凤凰县";}break;
                    case "24":{countyName="花垣县";}break;
                    case "25":{countyName="保靖县";}break;
                    case "26":{countyName="古丈县";}break;
                    case "27":{countyName="永顺县";}break;
                    case "28":{countyName="大庸县";}break;
                    case "29":{countyName="桑植县";}break;
                    case "30":{countyName="龙山县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //湖南省--其它市************************
            case "90":{
                cityName="--";
                switch (countyCode){
                    case "01":{cityName="醴陵市";}break;
                    case "02":{cityName="湘乡市";}break;
                    case "03":{cityName="耒阳市";}break;
                    case "04":{cityName="汨罗市";}break;
                    case "05":{cityName="津市市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //**************************************
            default:{
                cityName="(身份证号码3-4位有误)";
                countyName="--";
            }break;
        }
        return provinceName+"/"+cityName+"/"+countyName;
    }

    /**
     *@Author:ShiYun;
     *@Description:获得广东省的方法
     *@Date: 11:18 2018\9\26 0026
     */
    private static String getProvinceNameByProvinceCode44(String cityCode,String countyCode){
        String provinceName = "广东省";
        String cityName = "";
        String countyName = "";
        switch (cityCode){
            //广东省--广州市************************
            case "01":{
                cityName="广州市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="东山区";}break;
                    case "03":{countyName="荔湾区";}break;
                    case "04":{countyName="越秀区";}break;
                    case "05":{countyName="珠海区";}break;
                    case "06":{countyName="天河区";}break;
                    case "07":{countyName="芳村区";}break;
                    case "11":{countyName="白云区";}break;
                    case "12":{countyName="黄浦区";}break;
                    case "13":{countyName="番禺区";}break;
                    case "14":{countyName="花都区";}break;
                    case "15":{countyName="南沙区";}break;
                    case "16":{countyName="萝岗区";}break;
                    case "20":{countyName="市区";}break;
                    case "21":{countyName="花县";}break;
                    case "22":{countyName="从化县";}break;
                    case "23":{countyName="新丰县";}break;
                    case "24":{countyName="龙门县";}break;
                    case "25":{countyName="增城县";}break;
                    case "26":{countyName="番禺县";}break;
                    case "27":{countyName="清远县";}break;
                    case "28":{countyName="佛冈县";}break;
                    case "81":{countyName="番禺市";}break;
                    case "82":{countyName="花都市";}break;
                    case "83":{countyName="增城市";}break;
                    case "84":{countyName="从化市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //广东省--韶关市************************
            case "02":{
                cityName="韶关市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="北江区";}break;
                    case "03":{countyName="武江区";}break;
                    case "04":{countyName="浈江区";}break;
                    case "05":{countyName="曲江区";}break;
                    case "21":{countyName="曲江县";}break;
                    case "22":{countyName="始兴县";}break;
                    case "23":{countyName="南雄县";}break;
                    case "24":{countyName="仁化县";}break;
                    case "25":{countyName="乐昌县";}break;
                    case "26":{countyName="连县";}break;
                    case "27":{countyName="阳山县";}break;
                    case "28":{countyName="英德县";}break;
                    case "29":{countyName="翁源县";}break;
                    case "30":{countyName="连山壮族瑶族自治县";}break;
                    case "31":{countyName="连山瑶族自治县";}break;
                    case "32":{countyName="乳源瑶族自治县";}break;
                    case "33":{countyName="新丰县";}break;
                    case "81":{countyName="乐昌市";}break;
                    case "82":{countyName="南雄市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //广东省--深圳市************************
            case "03":{
                cityName="深圳市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="盐田区";}break;
                    case "03":{countyName="罗湖区";}break;
                    case "04":{countyName="福田区";}break;
                    case "05":{countyName="南山区";}break;
                    case "06":{countyName="宝安区";}break;
                    case "07":{countyName="龙岗区";}break;
                    case "08":{countyName="盐田区";}break;
                    case "20":{countyName="市区";}break;
                    case "21":{countyName="宝安县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //广东省--珠海市************************
            case "04":{
                cityName="珠海市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="香洲区";}break;
                    case "03":{countyName="斗门区";}break;
                    case "04":{countyName="金湾区";}break;
                    case "21":{countyName="斗门县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //广东省--汕头市************************
            case "05":{
                cityName="汕头市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="同平区";}break;
                    case "03":{countyName="安平区";}break;
                    case "04":{countyName="公园区";}break;
                    case "05":{countyName="金砂区";}break;
                    case "06":{countyName="达濠区";}break;
                    case "07":{countyName="龙湖区";}break;
                    case "08":{countyName="金园区";}break;
                    case "09":{countyName="升平区";}break;
                    case "10":{countyName="河浦区";}break;
                    case "11":{countyName="金平区";}break;
                    case "12":{countyName="濠江区";}break;
                    case "13":{countyName="潮阳区";}break;
                    case "14":{countyName="潮南区";}break;
                    case "15":{countyName="澄海区";}break;
                    case "20":{countyName="潮州市";}break;
                    case "21":{countyName="澄海县";}break;
                    case "22":{countyName="饶平县";}break;
                    case "23":{countyName="南澳县";}break;
                    case "24":{countyName="潮阳县";}break;
                    case "25":{countyName="揭阳县";}break;
                    case "26":{countyName="揭西县";}break;
                    case "27":{countyName="普宁县";}break;
                    case "28":{countyName="惠来县";}break;
                    case "81":{countyName="潮州市";}break;
                    case "82":{countyName="潮阳市";}break;
                    case "83":{countyName="澄海市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //广东省--佛山市************************
            case "06":{
                cityName="佛山市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="城区";}break;
                    case "03":{countyName="石湾区";}break;
                    case "04":{countyName="禅城区";}break;
                    case "05":{countyName="南海区";}break;
                    case "06":{countyName="顺德区";}break;
                    case "07":{countyName="三水区";}break;
                    case "08":{countyName="高明区";}break;
                    case "20":{countyName="中山市";}break;
                    case "21":{countyName="三水县";}break;
                    case "22":{countyName="南海县";}break;
                    case "23":{countyName="顺德县";}break;
                    case "24":{countyName="高明县";}break;
                    case "81":{countyName="顺德市";}break;
                    case "82":{countyName="南海市";}break;
                    case "83":{countyName="三水市";}break;
                    case "84":{countyName="高明市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //广东省--江门市************************
            case "07":{
                cityName="江门市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="城区";}break;
                    case "03":{countyName="蓬江区";}break;
                    case "04":{countyName="江海区";}break;
                    case "05":{countyName="新会区";}break;
                    case "11":{countyName="郊区";}break;
                    case "21":{countyName="新会县";}break;
                    case "22":{countyName="台山县";}break;
                    case "23":{countyName="恩平县";}break;
                    case "24":{countyName="开平县";}break;
                    case "25":{countyName="鹤山县";}break;
                    case "26":{countyName="阳江县";}break;
                    case "27":{countyName="阳春县";}break;
                    case "81":{countyName="台山市";}break;
                    case "82":{countyName="新会市";}break;
                    case "83":{countyName="开平市";}break;
                    case "84":{countyName="鹤山市";}break;
                    case "85":{countyName="恩平市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //广东省--湛江市************************
            case "08":{
                cityName="湛江市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="赤坎区";}break;
                    case "03":{countyName="霞山区";}break;
                    case "04":{countyName="坡头区";}break;
                    case "11":{countyName="麻章区";}break;
                    case "21":{countyName="吴川县";}break;
                    case "22":{countyName="麻江县";}break;
                    case "23":{countyName="遂溪县";}break;
                    case "24":{countyName="海康县";}break;
                    case "25":{countyName="徐闻县";}break;
                    case "81":{countyName="廉江市";}break;
                    case "82":{countyName="雷州市";}break;
                    case "83":{countyName="吴川市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //广东省--茂名市************************
            case "09":{
                cityName="茂名市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="茂南区";}break;
                    case "03":{countyName="茂港区";}break;
                    case "21":{countyName="信宜县";}break;
                    case "22":{countyName="高州县";}break;
                    case "23":{countyName="电白县";}break;
                    case "24":{countyName="化州县";}break;
                    case "81":{countyName="高州市";}break;
                    case "82":{countyName="化州市";}break;
                    case "83":{countyName="信宜市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //广东省--肇庆市************************
            case "12":{
                cityName="肇庆市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="端州区";}break;
                    case "03":{countyName="鼎湖区";}break;
                    case "21":{countyName="高要县";}break;
                    case "22":{countyName="四会县";}break;
                    case "23":{countyName="广宁县";}break;
                    case "24":{countyName="怀集县";}break;
                    case "25":{countyName="封开县";}break;
                    case "26":{countyName="德庆县";}break;
                    case "27":{countyName="云浮县";}break;
                    case "28":{countyName="新兴县";}break;
                    case "29":{countyName="郁南县";}break;
                    case "30":{countyName="罗定县";}break;
                    case "81":{countyName="云浮市";}break;
                    case "82":{countyName="罗定市";}break;
                    case "83":{countyName="高要市";}break;
                    case "84":{countyName="四会市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //广东省--惠州市************************
            case "13":{
                cityName="惠州市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="惠城区";}break;
                    case "03":{countyName="惠阳区";}break;
                    case "21":{countyName="惠阳县";}break;
                    case "22":{countyName="博罗县";}break;
                    case "23":{countyName="惠东县";}break;
                    case "24":{countyName="龙门县";}break;
                    case "81":{countyName="惠阳市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //广东省--梅州市************************
            case "14":{
                cityName="梅州市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="梅江区";}break;
                    case "21":{countyName="梅县";}break;
                    case "22":{countyName="大埔县";}break;
                    case "23":{countyName="丰顺县";}break;
                    case "24":{countyName="五华县";}break;
                    case "25":{countyName="兴宁县";}break;
                    case "26":{countyName="平远县";}break;
                    case "27":{countyName="蕉岭县";}break;
                    case "81":{countyName="兴宁市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //广东省--汕尾市************************
            case "15":{
                cityName="汕尾市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="城区";}break;
                    case "21":{countyName="海丰县";}break;
                    case "22":{countyName="陆丰县";}break;
                    case "23":{countyName="陆河县";}break;
                    case "81":{countyName="陆丰市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //广东省--河源市************************
            case "16":{
                cityName="河源市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="源城区";}break;
                    case "11":{countyName="郊区";}break;
                    case "21":{countyName="紫金县";}break;
                    case "22":{countyName="龙川县";}break;
                    case "23":{countyName="连平县";}break;
                    case "24":{countyName="和平县";}break;
                    case "25":{countyName="东源县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //广东省--阳江市************************
            case "17":{
                cityName="阳江市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="江城区";}break;
                    case "03":{countyName="阳东区";}break;
                    case "21":{countyName="阳西县";}break;
                    case "22":{countyName="阳春县";}break;
                    case "23":{countyName="阳东县";}break;
                    case "81":{countyName="阳春市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //广东省--清远市************************
            case "18":{
                cityName="清远市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="清城区";}break;
                    case "11":{countyName="清郊区";}break;
                    case "21":{countyName="佛冈去";}break;
                    case "22":{countyName="英德县";}break;
                    case "23":{countyName="阳山县";}break;
                    case "24":{countyName="连县";}break;
                    case "25":{countyName="连山壮族瑶族自治县";}break;
                    case "26":{countyName="连山瑶族自治县";}break;
                    case "27":{countyName="清新县";}break;
                    case "81":{countyName="英德市";}break;
                    case "82":{countyName="连州市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //广东省--东菀市************************
            case "19":{
                cityName="--";
                switch (countyCode){
                    case "00":{countyName="东菀市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //广东省--中山市************************
            case "20":{
                cityName="--";
                switch (countyCode){
                    case "00":{countyName="中山市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //广东省--其它市1************************
            case "25":{
                cityName="--";
                switch (countyCode){
                    case "01":{cityName="惠州市";}break;
                    case "02":{cityName="东莞市";}break;
                    case "21":{cityName="惠阳县";}break;
                    case "22":{cityName="紫金县";}break;
                    case "23":{cityName="和平县";}break;
                    case "24":{cityName="连平县";}break;
                    case "25":{cityName="河源县";}break;
                    case "26":{cityName="博罗县";}break;
                    case "27":{cityName="东莞县";}break;
                    case "28":{cityName="惠东县";}break;
                    case "29":{cityName="龙川县";}break;
                    case "30":{cityName="陆丰县";}break;
                    case "31":{cityName="海丰县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //广东省--其它市2************************
            case "26":{
                cityName="--";
                switch (countyCode){
                    case "21":{cityName="始兴县";}break;
                    case "22":{cityName="南雄县";}break;
                    case "23":{cityName="仁化县";}break;
                    case "24":{cityName="乐昌县";}break;
                    case "28":{cityName="清远县";}break;
                    case "30":{cityName="翁源县";}break;
                    case "31":{cityName="连山壮族瑶族自治县";}break;
                    case "32":{cityName="连山瑶族自治县";}break;
                    case "33":{cityName="乳源瑶族自治县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //广东省--佛山市************************
            case "27":{
                cityName="佛山市";
                switch (countyCode){
                    case "01":{cityName="佛山市";}break;
                    case "02":{cityName="江门市";}break;
                    case "21":{cityName="三水县";}break;
                    case "22":{cityName="南海县";}break;
                    case "23":{cityName="顺德县";}break;
                    case "24":{cityName="中山县";}break;
                    case "25":{cityName="斗门县";}break;
                    case "26":{cityName="新会县";}break;
                    case "27":{cityName="台山县";}break;
                    case "28":{cityName="恩平县";}break;
                    case "29":{cityName="开平县";}break;
                    case "31":{cityName="鹤山县";}break;
                    case "32":{cityName="高明县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //广东省--其它市3************************
            case "28":{
                cityName="--";
                switch (countyCode){
                    case "21":{cityName="高要县";}break;
                    case "22":{cityName="四会县";}break;
                    case "23":{cityName="广宁县";}break;
                    case "24":{cityName="怀集县";}break;
                    case "25":{cityName="封开县";}break;
                    case "26":{cityName="德庆县";}break;
                    case "27":{cityName="云浮县";}break;
                    case "28":{cityName="新兴县";}break;
                    case "29":{cityName="郁南县";}break;
                    case "30":{cityName="罗定县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //广东省--湛江市************************
            case "29":{
                cityName="湛江市";
                switch (countyCode){
                    case "01":{cityName="湛江市";}break;
                    case "02":{cityName="茂名市";}break;
                    case "21":{cityName="阳江县";}break;
                    case "22":{cityName="阳春县";}break;
                    case "23":{cityName="信宜县";}break;
                    case "24":{cityName="高州县";}break;
                    case "25":{cityName="电白县";}break;
                    case "26":{cityName="吴川县";}break;
                    case "27":{cityName="化州县";}break;
                    case "28":{cityName="廉江县";}break;
                    case "29":{cityName="遂溪县";}break;
                    case "30":{cityName="海康县";}break;
                    case "31":{cityName="徐闻县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //广东省--潮州市************************
            case "51":{
                cityName="潮州市";
                switch (countyCode){
                    case "01":{cityName="市辖区";}break;
                    case "02":{cityName="湘桥区";}break;
                    case "21":{cityName="潮安县";}break;
                    case "22":{cityName="饶平县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //广东省--揭阳市************************
            case "52":{
                cityName="揭阳市";
                switch (countyCode){
                    case "01":{cityName="市辖区";}break;
                    case "02":{cityName="榕城区";}break;
                    case "21":{cityName="揭东县";}break;
                    case "22":{cityName="揭西县";}break;
                    case "24":{cityName="惠来县";}break;
                    case "81":{cityName="普宁市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //广东省--云浮市************************
            case "53":{
                cityName="云浮市";
                switch (countyCode){
                    case "01":{cityName="市辖区";}break;
                    case "02":{cityName="云城区";}break;
                    case "21":{cityName="新兴县";}break;
                    case "22":{cityName="郁南县";}break;
                    case "23":{cityName="云安县";}break;
                    case "81":{cityName="罗定市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //**************************************
            default:{
                cityName="(身份证号码3-4位有误)";
                countyName="--";
            }break;
        }
        return provinceName+"/"+cityName+"/"+countyName;
    }

    /**
     *@Author:ShiYun;
     *@Description:获得广西壮族自治区的方法
     *@Date: 14:46 2018\9\26 0026
     */
    private static String getProvinceNameByProvinceCode45(String cityCode,String countyCode){
        String provinceName = "广西壮族自治区";
        String cityName = "";
        String countyName = "";
        switch (cityCode){
            //广西壮族自治区--南宁市************************
            case "01":{
                cityName="南宁市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="兴宁区";}break;
                    case "03":{countyName="青秀区";}break;
                    case "04":{countyName="城北区";}break;
                    case "05":{countyName="江南区";}break;
                    case "06":{countyName="永新区";}break;
                    case "07":{countyName="西乡塘区";}break;
                    case "08":{countyName="良庆区";}break;
                    case "09":{countyName="邕宁区";}break;
                    case "11":{countyName="郊区";}break;
                    case "21":{countyName="邕宁县";}break;
                    case "22":{countyName="武鸣县";}break;
                    case "23":{countyName="隆安县";}break;
                    case "24":{countyName="马山县";}break;
                    case "25":{countyName="上林县";}break;
                    case "26":{countyName="宾阳县";}break;
                    case "27":{countyName="横县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //广西壮族自治区--柳州市************************
            case "02":{
                cityName="柳州市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="城中区";}break;
                    case "03":{countyName="鱼峰区";}break;
                    case "04":{countyName="柳南区";}break;
                    case "05":{countyName="柳北区";}break;
                    case "11":{countyName="市郊区";}break;
                    case "21":{countyName="柳江县";}break;
                    case "22":{countyName="柳城县";}break;
                    case "23":{countyName="鹿寨县";}break;
                    case "24":{countyName="融安县";}break;
                    case "25":{countyName="融水苗族自治县";}break;
                    case "26":{countyName="三江侗族自治县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //广西壮族自治区--桂林市************************
            case "03":{
                cityName="桂林市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="秀峰区";}break;
                    case "03":{countyName="叠彩区";}break;
                    case "04":{countyName="象山区";}break;
                    case "05":{countyName="七星区";}break;
                    case "11":{countyName="雁山区";}break;
                    case "21":{countyName="阳朔县";}break;
                    case "22":{countyName="临桂县";}break;
                    case "23":{countyName="灵川县";}break;
                    case "24":{countyName="全州县";}break;
                    case "25":{countyName="兴安县";}break;
                    case "26":{countyName="永福县";}break;
                    case "27":{countyName="灌阳县";}break;
                    case "28":{countyName="龙胜各族自治县";}break;
                    case "29":{countyName="资源县";}break;
                    case "30":{countyName="平乐县";}break;
                    case "31":{countyName="荔浦县";}break;
                    case "32":{countyName="恭城瑶族自治县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //广西壮族自治区--梧州市************************
            case "04":{
                cityName="梧州市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="白云区";}break;
                    case "03":{countyName="万秀区";}break;
                    case "04":{countyName="蝶山区";}break;
                    case "05":{countyName="长洲区";}break;
                    case "11":{countyName="市郊区";}break;
                    case "21":{countyName="苍梧区";}break;
                    case "22":{countyName="藤县";}break;
                    case "23":{countyName="蒙山县";}break;
                    case "81":{countyName="岑溪县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //广西壮族自治区--北海市************************
            case "05":{
                cityName="北海市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="海城区";}break;
                    case "03":{countyName="银海区";}break;
                    case "12":{countyName="铁山港区";}break;
                    case "21":{countyName="合浦县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //广西壮族自治区--防城港市************************
            case "06":{
                cityName="防城港市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="港口区";}break;
                    case "03":{countyName="防城区";}break;
                    case "21":{countyName="上思县";}break;
                    case "81":{countyName="东兴市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //广西壮族自治区--钦州市************************
            case "07":{
                cityName="钦州市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="钦南区";}break;
                    case "03":{countyName="钦北区";}break;
                    case "21":{countyName="灵山县";}break;
                    case "22":{countyName="浦北县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //广西壮族自治区--贵港市************************
            case "08":{
                cityName="贵港市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="港北区";}break;
                    case "03":{countyName="港南区";}break;
                    case "04":{countyName="覃塘区";}break;
                    case "21":{countyName="平南县";}break;
                    case "81":{countyName="桂平市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //广西壮族自治区--玉林市************************
            case "09":{
                cityName="玉林市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="玉州区";}break;
                    case "21":{countyName="容县";}break;
                    case "22":{countyName="陆川县";}break;
                    case "23":{countyName="博白县";}break;
                    case "24":{countyName="兴业县";}break;
                    case "81":{countyName="北流市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //广西壮族自治区--百色市************************
            case "10":{
                cityName="百色市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="右江区";}break;
                    case "21":{countyName="田阳县";}break;
                    case "22":{countyName="田东县";}break;
                    case "23":{countyName="平果县";}break;
                    case "24":{countyName="德保县";}break;
                    case "25":{countyName="靖西县";}break;
                    case "26":{countyName="那坡县";}break;
                    case "27":{countyName="凌云县";}break;
                    case "28":{countyName="乐业县";}break;
                    case "29":{countyName="田林县";}break;
                    case "30":{countyName="西林县";}break;
                    case "31":{countyName="隆林各族自治县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //广西壮族自治区--贺州市************************
            case "11":{
                cityName="贺州市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="八步区";}break;
                    case "21":{countyName="昭平县";}break;
                    case "22":{countyName="钟山县";}break;
                    case "23":{countyName="富川瑶族自治县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //广西壮族自治区--河池市************************
            case "12":{
                cityName="河池市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="金城江区";}break;
                    case "21":{countyName="南丹县";}break;
                    case "22":{countyName="天峨县";}break;
                    case "23":{countyName="凤山县";}break;
                    case "24":{countyName="东兰县";}break;
                    case "25":{countyName="罗城仫佬族自治县";}break;
                    case "26":{countyName="环江毛南族自治县";}break;
                    case "27":{countyName="巴马瑶族自治县";}break;
                    case "28":{countyName="都安瑶族自治县";}break;
                    case "29":{countyName="大化瑶族自治县";}break;
                    case "81":{countyName="宜州市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //广西壮族自治区--来宾市************************
            case "13":{
                cityName="来宾市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="兴宾区";}break;
                    case "21":{countyName="忻城县";}break;
                    case "22":{countyName="象州县";}break;
                    case "23":{countyName="武宣县";}break;
                    case "24":{countyName="金秀瑶族自治县";}break;
                    case "81":{countyName="合山市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //广西壮族自治区--崇左市************************
            case "14":{
                cityName="崇左市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="江州区";}break;
                    case "21":{countyName="扶绥县";}break;
                    case "22":{countyName="宁明县";}break;
                    case "23":{countyName="龙州县";}break;
                    case "24":{countyName="大新县";}break;
                    case "25":{countyName="天等县";}break;
                    case "81":{countyName="凭祥市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //广西壮族自治区--南宁地区************************
            case "21":{
                cityName="南宁地区";
                switch (countyCode){
                    case "01":{countyName="凭祥市";}break;
                    case "22":{countyName="横县";}break;
                    case "23":{countyName="宾阳县";}break;
                    case "24":{countyName="上林县";}break;
                    case "26":{countyName="隆安县";}break;
                    case "27":{countyName="马山县";}break;
                    case "28":{countyName="扶绥县";}break;
                    case "29":{countyName="崇左县";}break;
                    case "30":{countyName="大新县";}break;
                    case "31":{countyName="天等县";}break;
                    case "32":{countyName="宁明县";}break;
                    case "33":{countyName="龙州县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //广西壮族自治区--柳州地区************************
            case "22":{
                cityName="柳州地区";
                switch (countyCode){
                    case "01":{countyName="合山市";}break;
                    case "23":{countyName="鹿寨县";}break;
                    case "24":{countyName="象州县";}break;
                    case "25":{countyName="武宣县";}break;
                    case "26":{countyName="来宾县";}break;
                    case "27":{countyName="融安县";}break;
                    case "28":{countyName="三江侗族自治县";}break;
                    case "29":{countyName="融水苗族自治县";}break;
                    case "30":{countyName="金秀苗族自治县";}break;
                    case "31":{countyName="忻城县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //广西壮族自治区--桂林地区************************
            case "23":{
                cityName="桂林地区";
                switch (countyCode){
                    case "21":{countyName="临桂县";}break;
                    case "22":{countyName="灵川县";}break;
                    case "23":{countyName="全州县";}break;
                    case "24":{countyName="兴安县";}break;
                    case "25":{countyName="永福县";}break;
                    case "27":{countyName="灌阳县";}break;
                    case "28":{countyName="龙胜各族自治县";}break;
                    case "29":{countyName="资源县";}break;
                    case "30":{countyName="平乐县";}break;
                    case "31":{countyName="荔浦县";}break;
                    case "32":{countyName="恭城瑶族自治县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //广西壮族自治区--贺州地区************************
            case "24":{
                cityName="贺州地区";
                switch (countyCode){
                    case "01":{countyName="岑溪市";}break;
                    case "02":{countyName="贺州市";}break;
                    case "21":{countyName="岑溪县";}break;
                    case "22":{countyName="苍梧县";}break;
                    case "23":{countyName="藤县";}break;
                    case "24":{countyName="昭平县";}break;
                    case "25":{countyName="蒙山县";}break;
                    case "26":{countyName="贺县";}break;
                    case "27":{countyName="钟山县";}break;
                    case "28":{countyName="富川瑶族自治县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //广西壮族自治区--玉林地区************************
            case "25":{
                cityName="玉林地区";
                switch (countyCode){
                    case "01":{countyName="玉林市";}break;
                    case "02":{countyName="贵港市";}break;
                    case "03":{countyName="北流市";}break;
                    case "04":{countyName="桂平市";}break;
                    case "21":{countyName="玉林县";}break;
                    case "22":{countyName="贵县";}break;
                    case "23":{countyName="桂平县";}break;
                    case "24":{countyName="平南县";}break;
                    case "25":{countyName="容县";}break;
                    case "26":{countyName="北流县";}break;
                    case "27":{countyName="陆川县";}break;
                    case "28":{countyName="博白县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //广西壮族自治区--百色地区************************
            case "26":{
                cityName="百色地区";
                switch (countyCode){
                    case "01":{countyName="百色市";}break;
                    case "21":{countyName="百色县";}break;
                    case "22":{countyName="田阳县";}break;
                    case "23":{countyName="田东县";}break;
                    case "24":{countyName="平果县";}break;
                    case "25":{countyName="德保县";}break;
                    case "26":{countyName="靖西县";}break;
                    case "27":{countyName="那坡县";}break;
                    case "28":{countyName="凌云县";}break;
                    case "29":{countyName="乐业县";}break;
                    case "30":{countyName="田林县";}break;
                    case "31":{countyName="隆林各族自治县";}break;
                    case "32":{countyName="西林县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //广西壮族自治区--河池地区************************
            case "27":{
                cityName="河池地区";
                switch (countyCode){
                    case "01":{countyName="河池市";}break;
                    case "02":{countyName="宜州市";}break;
                    case "21":{countyName="河池县";}break;
                    case "22":{countyName="宜山县";}break;
                    case "23":{countyName="罗城仫佬族自治县";}break;
                    case "24":{countyName="环江毛南族自治县";}break;
                    case "25":{countyName="南丹县";}break;
                    case "26":{countyName="天峨县";}break;
                    case "27":{countyName="凤山县";}break;
                    case "28":{countyName="东兰县";}break;
                    case "29":{countyName="巴马瑶族自治县";}break;
                    case "30":{countyName="都安瑶族自治县";}break;
                    case "31":{countyName="大化瑶族自治县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //广西壮族自治区--钦州地区************************
            case "28":{
                cityName="钦州地区";
                switch (countyCode){
                    case "01":{countyName="北海市";}break;
                    case "02":{countyName="钦州市";}break;
                    case "21":{countyName="上思县";}break;
                    case "22":{countyName="防城各族自治县";}break;
                    case "23":{countyName="钦州县";}break;
                    case "24":{countyName="灵山县";}break;
                    case "25":{countyName="合浦县";}break;
                    case "26":{countyName="浦北县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //**********************************************
            default:{
                cityName="(身份证号码3-4位有误)";
                countyName="--";
            }break;
        }
        return provinceName+"/"+cityName+"/"+countyName;
    }

    /**
     *@Author:ShiYun;
     *@Description:获得海南省的方法
     *@Date: 16:55 2018\9\26 0026
     */
    private static String getProvinceNameByProvinceCode46(String cityCode,String countyCode){
        String provinceName = "海南省";
        String cityName = "";
        String countyName = "";
        switch (cityCode){
            //海南省--其它市1************************
            case "00":{
                cityName="--";
                switch (countyCode){
                    case "01":{cityName="通什市";}break;
                    case "02":{cityName="琼海市";}break;
                    case "03":{cityName="瞻洲市";}break;
                    case "04":{cityName="琼山市";}break;
                    case "05":{cityName="文昌市";}break;
                    case "06":{cityName="万宁市";}break;
                    case "07":{cityName="东方市";}break;
                    case "21":{cityName="琼山县";}break;
                    case "22":{cityName="文昌县";}break;
                    case "23":{cityName="琼海县";}break;
                    case "24":{cityName="万宁县";}break;
                    case "25":{cityName="定安县";}break;
                    case "26":{cityName="屯昌县";}break;
                    case "27":{cityName="澄迈县";}break;
                    case "28":{cityName="临高县";}break;
                    case "29":{cityName="儋县";}break;
                    case "30":{cityName="白沙黎族自治县";}break;
                    case "31":{cityName="昌江黎族自治县";}break;
                    case "32":{cityName="东方黎族自治县";}break;
                    case "33":{cityName="乐东黎族自治县";}break;
                    case "34":{cityName="陵水黎族自治县";}break;
                    case "35":{cityName="保亭黎族苗族自治县";}break;
                    case "36":{cityName="琼中黎族苗族自治县";}break;
                    case "37":{cityName="西沙群岛";}break;
                    case "38":{cityName="南沙群岛";}break;
                    case "39":{cityName="中沙群岛的岛礁及其海域";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //海南省--海口市************************
            case "01":{
                cityName="海口市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="振东区";}break;
                    case "03":{countyName="新华区";}break;
                    case "04":{countyName="秀英区";}break;
                    case "05":{countyName="秀英区";}break;
                    case "06":{countyName="龙华区";}break;
                    case "07":{countyName="琼山区";}break;
                    case "08":{countyName="美兰区";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            case "02":{
                cityName="三亚市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //海南省--其它市2************************
            case "90":{
                cityName="--";
                switch (countyCode){
                    case "01":{cityName="五指山市";}break;
                    case "02":{cityName="琼海市";}break;
                    case "03":{cityName="儋州市";}break;
                    case "05":{cityName="文昌市";}break;
                    case "06":{cityName="万宁市";}break;
                    case "07":{cityName="东方市";}break;
                    case "25":{cityName="定安县";}break;
                    case "26":{cityName="屯昌县";}break;
                    case "27":{cityName="澄迈县";}break;
                    case "28":{cityName="临高县";}break;
                    case "30":{cityName="白沙黎族自治县";}break;
                    case "31":{cityName="昌江黎族自治县";}break;
                    case "33":{cityName="乐东黎族自治县";}break;
                    case "34":{cityName="陵水黎族自治县";}break;
                    case "35":{cityName="保亭黎族苗族自治县";}break;
                    case "36":{cityName="琼中黎族苗族自治县";}break;
                    case "37":{cityName="西沙群岛";}break;
                    case "38":{cityName="南沙群岛";}break;
                    case "39":{cityName="中沙群岛的岛礁及其海域";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //**********************************************
            default:{
                cityName="(身份证号码3-4位有误)";
                countyName="--";
            }break;
        }
        return provinceName+"/"+cityName+"/"+countyName;
    }

    /**
     *@Author:ShiYun;
     *@Description:获得重庆市的方法
     *@Date: 17:18 2018\9\26 0026
     */
    private static String getProvinceNameByProvinceCode50(String cityCode,String countyCode){
        String provinceName = "重庆市";
        String cityName = "";
        String countyName = "";
        switch (cityCode){
            //重庆市--其它市1************************
            case "01":{
                cityName="--";
                switch (countyCode){
                    case "01":{countyName="万州区";}break;
                    case "02":{countyName="涪陵区";}break;
                    case "03":{countyName="渝中区";}break;
                    case "04":{countyName="大渡口区";}break;
                    case "05":{countyName="江北区";}break;
                    case "06":{countyName="沙坪坝区";}break;
                    case "07":{countyName="九龙坡区";}break;
                    case "08":{countyName="南岸区";}break;
                    case "09":{countyName="北碚区";}break;
                    case "10":{countyName="万盛区";}break;
                    case "11":{countyName="双桥区";}break;
                    case "12":{countyName="渝北区";}break;
                    case "13":{countyName="巴南区";}break;
                    case "14":{countyName="黔江区";}break;
                    case "15":{countyName="长寿区";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //重庆市--其它市2************************
            case "02":{
                cityName="--";
                switch (countyCode){
                    case "01":{countyName="渝中区";}break;
                    case "11":{countyName="江北区";}break;
                    case "12":{countyName="沙坪坝区";}break;
                    case "13":{countyName="九龙坡区";}break;
                    case "14":{countyName="南岸区";}break;
                    case "15":{countyName="北碚区";}break;
                    case "16":{countyName="万盛区";}break;
                    case "17":{countyName="双桥区";}break;
                    case "21":{countyName="长寿县";}break;
                    case "22":{countyName="綦江县";}break;
                    case "23":{countyName="潼南县";}break;
                    case "24":{countyName="铜梁县";}break;
                    case "25":{countyName="大足县";}break;
                    case "26":{countyName="荣昌县";}break;
                    case "27":{countyName="璧山县";}break;
                    case "28":{countyName="梁平县";}break;
                    case "29":{countyName="城口县";}break;
                    case "30":{countyName="丰都县";}break;
                    case "31":{countyName="垫江县";}break;
                    case "32":{countyName="武隆县";}break;
                    case "33":{countyName="忠县";}break;
                    case "34":{countyName="开县";}break;
                    case "35":{countyName="云阳县";}break;
                    case "36":{countyName="奉节县";}break;
                    case "37":{countyName="巫山县";}break;
                    case "38":{countyName="巫溪县";}break;
                    case "39":{countyName="黔江土家族苗族自治县";}break;
                    case "40":{countyName="石柱土家族自治县";}break;
                    case "41":{countyName="秀山土家族苗族自治县";}break;
                    case "42":{countyName="酉阳土家族苗族自治县";}break;
                    case "43":{countyName="彭水苗族土家族自治县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //重庆市--其它市3************************
            case "03":{
                cityName="--";
                switch (countyCode){
                    case "81":{countyName="江津市";}break;
                    case "82":{countyName="合川市";}break;
                    case "83":{countyName="永川市";}break;
                    case "84":{countyName="南川市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //**********************************************
            default:{
                cityName="(身份证号码3-4位有误)";
                countyName="--";
            }break;
        }
        return provinceName+"/"+cityName+"/"+countyName;
    }

    /**
     *@Author:ShiYun;
     *@Description:获得四川省的方法
     *@Date: 17:47 2018\9\26 0026
     */
    private static String getProvinceNameByProvinceCode51(String cityCode,String countyCode){
        String provinceName = "四川省";
        String cityName = "";
        String countyName = "";
        switch (cityCode){
            //四川省--成都市************************
            case "01":{
                cityName="成都市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="东城区";}break;
<<<<<<< HEAD
                    case "03":{countyName="西城区";}break;
                    case "04":{countyName="锦江区";}break;
                    case "05":{countyName="青羊区";}break;
                    case "06":{countyName="金牛区";}break;
                    case "07":{countyName="武侯区";}break;
                    case "08":{countyName="成华区";}break;
                    case "11":{countyName="金牛区";}break;
                    case "12":{countyName="龙泉驿区";}break;
                    case "13":{countyName="青白江区";}break;
                    case "14":{countyName="新都区";}break;
                    case "20":{countyName="市区";}break;
                    case "21":{countyName="金堂县";}break;
                    case "22":{countyName="双流县";}break;
                    case "23":{countyName="温江县";}break;
                    case "24":{countyName="郫县";}break;
                    case "25":{countyName="新都县";}break;
                    case "26":{countyName="彭县";}break;
                    case "27":{countyName="灌县";}break;
                    case "28":{countyName="崇庆县";}break;
                    case "29":{countyName="大邑县";}break;
                    case "30":{countyName="邛峡县";}break;
                    case "31":{countyName="浦江县";}break;
                    case "32":{countyName="新津县";}break;
                    case "81":{countyName="都江堰市";}break;
                    case "82":{countyName="彭州市";}break;
                    case "83":{countyName="邛峡市";}break;
                    case "84":{countyName="崇州市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //四川省--重庆市************************
            case "02":{
                cityName="重庆市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="市中区";}break;
                    case "03":{countyName="大渡口区";}break;
                    case "11":{countyName="江北区";}break;
                    case "12":{countyName="沙坪坝区";}break;
                    case "13":{countyName="九龙坡区";}break;
                    case "14":{countyName="南岸区";}break;
                    case "15":{countyName="北碚区";}break;
                    case "16":{countyName="万盛区";}break;
                    case "17":{countyName="双桥区";}break;
                    case "18":{countyName="渝北区";}break;
                    case "19":{countyName="巴南区";}break;
                    case "20":{countyName="市区";}break;
                    case "21":{countyName="长寿区";}break;
                    case "22":{countyName="巴县";}break;
                    case "23":{countyName="綦江县";}break;
                    case "24":{countyName="江北县";}break;
                    case "25":{countyName="江津县";}break;
                    case "26":{countyName="合川县";}break;
                    case "27":{countyName="潼南县";}break;
                    case "28":{countyName="铜梁县";}break;
                    case "29":{countyName="永川县";}break;
                    case "30":{countyName="大足县";}break;
                    case "31":{countyName="北碚县";}break;
                    case "32":{countyName="璧山县";}break;
                    case "81":{countyName="永川县";}break;
                    case "82":{countyName="江津市";}break;
                    case "83":{countyName="合川市";}break;
=======
>>>>>>> 87009200724281031b917f7a83a5e48fc2288fa1
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //四川省--自贡市************************
            case "03":{
                cityName="自贡市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="自流井区";}break;
                    case "03":{countyName="贡井区";}break;
                    case "04":{countyName="大安区";}break;
                    case "11":{countyName="沿滩区";}break;
                    case "21":{countyName="容县";}break;
                    case "22":{countyName="富顺县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //四川省--攀枝花市************************
            case "04":{
                cityName="攀枝花市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="东区";}break;
                    case "03":{countyName="西区";}break;
                    case "11":{countyName="仁和区";}break;
                    case "21":{countyName="米易县";}break;
                    case "22":{countyName="盐边县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //四川省--泸州市************************
            case "05":{
                cityName="泸州市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="江阳区";}break;
                    case "03":{countyName="纳溪区";}break;
                    case "04":{countyName="龙马潭区";}break;
                    case "21":{countyName="泸县";}break;
                    case "22":{countyName="合江县";}break;
                    case "23":{countyName="纳溪县";}break;
                    case "24":{countyName="叙永县";}break;
                    case "25":{countyName="古蔺县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //四川省--德阳市************************
            case "06":{
                cityName="德阳市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="市中区";}break;
                    case "03":{countyName="旌阳区";}break;
                    case "22":{countyName="绵竹县";}break;
                    case "23":{countyName="中江县";}break;
                    case "24":{countyName="广汉县";}break;
                    case "25":{countyName="什邡县";}break;
                    case "26":{countyName="罗江县";}break;
                    case "81":{countyName="广汉市";}break;
                    case "82":{countyName="什邡市";}break;
                    case "83":{countyName="绵竹市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //四川省--绵阳市************************
            case "07":{
                cityName="绵阳市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="市中区";}break;
                    case "03":{countyName="涪城区";}break;
                    case "04":{countyName="游仙区";}break;
                    case "21":{countyName="江油县";}break;
                    case "22":{countyName="三台县";}break;
                    case "23":{countyName="盐亭县";}break;
                    case "24":{countyName="安县";}break;
                    case "25":{countyName="梓潼县";}break;
                    case "26":{countyName="北川县";}break;
                    case "27":{countyName="平武县";}break;
                    case "81":{countyName="江油市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //四川省--广元市************************
            case "08":{
                cityName="广元市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="市中区";}break;
                    case "11":{countyName="元坝区";}break;
                    case "12":{countyName="朝天区";}break;
                    case "21":{countyName="旺苍县";}break;
                    case "22":{countyName="青川县";}break;
                    case "23":{countyName="剑阁县";}break;
                    case "24":{countyName="苍溪县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //四川省--遂宁市************************
            case "09":{
                cityName="遂宁市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="市中区";}break;
                    case "03":{countyName="船山区";}break;
                    case "04":{countyName="安居区";}break;
                    case "21":{countyName="蓬溪县";}break;
                    case "22":{countyName="射洪县";}break;
                    case "23":{countyName="大英县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //四川省--内江市************************
            case "10":{
                cityName="内江市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="市中区";}break;
                    case "11":{countyName="东兴区";}break;
                    case "21":{countyName="内江县";}break;
                    case "22":{countyName="乐至县";}break;
                    case "23":{countyName="安岳县";}break;
                    case "24":{countyName="威远县";}break;
                    case "25":{countyName="资中县";}break;
                    case "26":{countyName="资阳县";}break;
                    case "27":{countyName="简阳县";}break;
                    case "28":{countyName="隆昌县";}break;
                    case "81":{countyName="资阳市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //四川省--乐山市************************
            case "11":{
                cityName="乐山市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="市中区";}break;
                    case "11":{countyName="沙湾区";}break;
                    case "12":{countyName="五通桥区";}break;
                    case "13":{countyName="金河口区";}break;
                    case "21":{countyName="仁寿县";}break;
                    case "22":{countyName="眉山县";}break;
                    case "23":{countyName="犍为县";}break;
                    case "24":{countyName="井研县";}break;
                    case "25":{countyName="峨眉县";}break;
                    case "26":{countyName="夹江县";}break;
                    case "27":{countyName="洪雅县";}break;
                    case "28":{countyName="彭山县";}break;
                    case "29":{countyName="沐川县";}break;
                    case "30":{countyName="青神县";}break;
                    case "31":{countyName="丹棱县";}break;
                    case "32":{countyName="峨边黎族自治县";}break;
                    case "33":{countyName="马边黎族自治县";}break;
                    case "81":{countyName="峨眉山市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //四川省--万县市************************
            case "12":{
                cityName="万县市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="龙宝区";}break;
                    case "03":{countyName="天成区";}break;
                    case "04":{countyName="五桥区";}break;
                    case "21":{countyName="开县";}break;
                    case "22":{countyName="忠县";}break;
                    case "23":{countyName="梁平县";}break;
                    case "24":{countyName="云阳县";}break;
                    case "25":{countyName="奉节县";}break;
                    case "26":{countyName="巫山县";}break;
                    case "27":{countyName="巫溪县";}break;
                    case "28":{countyName="城口县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //四川省--南充市************************
            case "13":{
                cityName="南充市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="顺庆区";}break;
                    case "03":{countyName="高坪区";}break;
                    case "04":{countyName="嘉陵区";}break;
                    case "21":{countyName="南部县";}break;
                    case "22":{countyName="营山县";}break;
                    case "23":{countyName="蓬安县";}break;
                    case "24":{countyName="仪陇县";}break;
                    case "25":{countyName="西充县";}break;
                    case "81":{countyName="阆中市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //四川省--眉山市************************
            case "14":{
                cityName="眉山市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="东坡区";}break;
                    case "21":{countyName="仁寿县";}break;
                    case "22":{countyName="彭山县";}break;
                    case "23":{countyName="洪雅县";}break;
                    case "24":{countyName="丹棱县";}break;
                    case "25":{countyName="青神县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //四川省--宜宾市************************
            case "15":{
                cityName="宜宾市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="翠屏区";}break;
                    case "21":{countyName="宜宾县";}break;
                    case "22":{countyName="南溪县";}break;
                    case "23":{countyName="江安县";}break;
                    case "24":{countyName="长宁县";}break;
                    case "25":{countyName="高县";}break;
                    case "26":{countyName="珙县";}break;
                    case "27":{countyName="筠连县";}break;
                    case "28":{countyName="兴文县";}break;
                    case "29":{countyName="屏山县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //四川省--广安市************************
            case "16":{
                cityName="广安市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="广安区";}break;
                    case "21":{countyName="岳池县";}break;
                    case "22":{countyName="武胜县";}break;
                    case "23":{countyName="邻水县";}break;
                    case "81":{countyName="华蓥市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //四川省--达州市************************
            case "17":{
                cityName="达州市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="通川区";}break;
                    case "21":{countyName="达县";}break;
                    case "22":{countyName="宣汉县";}break;
                    case "23":{countyName="开江县";}break;
                    case "24":{countyName="大竹县";}break;
                    case "25":{countyName="渠县";}break;
                    case "81":{countyName="万源市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //四川省--雅安市************************
            case "18":{
                cityName="雅安市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="雨城区";}break;
                    case "21":{countyName="名山县";}break;
                    case "22":{countyName="荥经县";}break;
                    case "23":{countyName="汉源县";}break;
                    case "24":{countyName="石棉县";}break;
                    case "25":{countyName="天全县";}break;
                    case "26":{countyName="芦山县";}break;
                    case "27":{countyName="宝兴县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //四川省--巴中市************************
            case "19":{
                cityName="巴中市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="巴州区";}break;
                    case "21":{countyName="通江县";}break;
                    case "22":{countyName="南江县";}break;
                    case "23":{countyName="平昌县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //四川省--资阳市************************
            case "20":{
                cityName="资阳市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="雁江区";}break;
                    case "21":{countyName="安岳县";}break;
                    case "22":{countyName="乐至县";}break;
                    case "81":{countyName="简阳市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //四川省--万县地区************************
            case "22":{
                cityName="万县地区";
                switch (countyCode){
                    case "01":{countyName="万县市";}break;
                    case "21":{countyName="万县";}break;
                    case "22":{countyName="开县";}break;
                    case "23":{countyName="忠县";}break;
                    case "24":{countyName="梁平县";}break;
                    case "25":{countyName="云阳县";}break;
                    case "26":{countyName="奉节县";}break;
                    case "27":{countyName="巫山县";}break;
                    case "28":{countyName="巫溪县";}break;
                    case "29":{countyName="城口县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //四川省--涪陵地区************************
            case "23":{
                cityName="涪陵地区";
                switch (countyCode){
                    case "01":{countyName="涪陵市";}break;
                    case "02":{countyName="南川市";}break;
                    case "21":{countyName="涪陵县";}break;
                    case "22":{countyName="垫江县";}break;
                    case "23":{countyName="南川县";}break;
                    case "24":{countyName="丰都县";}break;
                    case "25":{countyName="石柱县";}break;
                    case "26":{countyName="武隆县";}break;
                    case "27":{countyName="彭水县";}break;
                    case "28":{countyName="黔江县";}break;
                    case "29":{countyName="酉阳县";}break;
                    case "30":{countyName="秀山县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //四川省--宜宾地区************************
            case "25":{
                cityName="宜宾地区";
                switch (countyCode){
                    case "01":{countyName="宜宾市";}break;
                    case "27":{countyName="宜宾县";}break;
                    case "28":{countyName="南溪县";}break;
                    case "29":{countyName="江安县";}break;
                    case "30":{countyName="长宁县";}break;
                    case "31":{countyName="高县";}break;
                    case "32":{countyName="筠连县";}break;
                    case "33":{countyName="珙县";}break;
                    case "34":{countyName="兴文县";}break;
                    case "35":{countyName="屏山县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //四川省--南充地区************************
            case "29":{
                cityName="南充地区";
                switch (countyCode){
                    case "01":{countyName="南充市";}break;
                    case "02":{countyName="华蓥市";}break;
                    case "03":{countyName="阆中市";}break;
                    case "21":{countyName="南充县";}break;
                    case "22":{countyName="南部县";}break;
                    case "23":{countyName="岳池县";}break;
                    case "24":{countyName="营山县";}break;
                    case "25":{countyName="广安县";}break;
                    case "26":{countyName="蓬安县";}break;
                    case "27":{countyName="仪陇县";}break;
                    case "28":{countyName="武胜县";}break;
                    case "29":{countyName="西充县";}break;
                    case "30":{countyName="阆中县";}break;
                    case "31":{countyName="苍溪县";}break;
                    case "32":{countyName="华云工农区";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //四川省--达川地区************************
            case "30":{
                cityName="达川地区";
                switch (countyCode){
                    case "01":{countyName="达川市";}break;
                    case "02":{countyName="万源市";}break;
                    case "21":{countyName="达县";}break;
                    case "22":{countyName="宣汉县";}break;
                    case "23":{countyName="开江县";}break;
                    case "24":{countyName="万源县";}break;
                    case "25":{countyName="通江县";}break;
                    case "26":{countyName="南江县";}break;
                    case "27":{countyName="巴中县";}break;
                    case "28":{countyName="平昌县";}break;
                    case "29":{countyName="大竹县";}break;
                    case "30":{countyName="渠县";}break;
                    case "31":{countyName="邻水县";}break;
                    case "32":{countyName="白沙工农区";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //四川省--雅安地区************************
            case "31":{
                cityName="雅安地区";
                switch (countyCode){
                    case "01":{countyName="雅安市";}break;
                    case "22":{countyName="名山县";}break;
                    case "23":{countyName="荥经县";}break;
                    case "24":{countyName="汉源县";}break;
                    case "25":{countyName="石棉县";}break;
                    case "26":{countyName="天全县";}break;
                    case "27":{countyName="芦山县";}break;
                    case "28":{countyName="宝兴县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //四川省--阿坝藏族羌族自治州************************
            case "32":{
                cityName="阿坝藏族羌族自治州";
                switch (countyCode){
                    case "21":{countyName="汶川县";}break;
                    case "22":{countyName="理县";}break;
                    case "23":{countyName="茂县";}break;
                    case "24":{countyName="松潘县";}break;
                    case "25":{countyName="九寨沟县";}break;
                    case "26":{countyName="金川县";}break;
                    case "27":{countyName="小金县";}break;
                    case "28":{countyName="黑水县";}break;
                    case "29":{countyName="马尔康县";}break;
                    case "30":{countyName="壤塘县";}break;
                    case "31":{countyName="阿坝县";}break;
                    case "32":{countyName="若尔盖县";}break;
                    case "33":{countyName="红原县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //四川省--甘孜藏族自治州************************
            case "33":{
                cityName="甘孜藏族自治州";
                switch (countyCode){
                    case "21":{countyName="康定县";}break;
                    case "22":{countyName="泸定县";}break;
                    case "23":{countyName="丹巴县";}break;
                    case "24":{countyName="九龙县";}break;
                    case "25":{countyName="雅江县";}break;
                    case "26":{countyName="道孚县";}break;
                    case "27":{countyName="炉霍县";}break;
                    case "28":{countyName="甘孜县";}break;
                    case "29":{countyName="新龙县";}break;
                    case "30":{countyName="德格县";}break;
                    case "31":{countyName="白玉县";}break;
                    case "32":{countyName="石渠县";}break;
                    case "33":{countyName="色达县";}break;
                    case "34":{countyName="理塘县";}break;
                    case "35":{countyName="巴塘县";}break;
                    case "36":{countyName="乡城县";}break;
                    case "37":{countyName="稻城县";}break;
                    case "38":{countyName="得荣县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //四川省--凉山黎族自治州************************
            case "34":{
                cityName="凉山黎族自治州";
                switch (countyCode){
                    case "01":{countyName="西昌市";}break;
                    case "22":{countyName="木里藏族自治县";}break;
                    case "23":{countyName="盐源县";}break;
                    case "24":{countyName="德昌县";}break;
                    case "25":{countyName="会理县";}break;
                    case "26":{countyName="会东县";}break;
                    case "27":{countyName="宁南县";}break;
                    case "28":{countyName="普格县";}break;
                    case "29":{countyName="布拖县";}break;
                    case "30":{countyName="金阳县";}break;
                    case "31":{countyName="昭觉县";}break;
                    case "32":{countyName="喜德县";}break;
                    case "33":{countyName="冕宁县";}break;
                    case "34":{countyName="越西县";}break;
                    case "35":{countyName="甘洛县";}break;
                    case "36":{countyName="美姑县";}break;
                    case "37":{countyName="雷波县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //四川省--黔江地区************************
            case "35":{
                cityName="黔江地区";
                switch (countyCode){
                    case "21":{countyName="石柱土家族自治县";}break;
                    case "22":{countyName="秀山土家族苗族自治县";}break;
                    case "23":{countyName="黔江土家族苗族自治县";}break;
                    case "24":{countyName="酉阳土家族苗族自治县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //四川省--广安市************************
            case "36":{
                cityName="广安市";
                switch (countyCode){
                    case "01":{countyName="华蓥市";}break;
                    case "21":{countyName="岳池县";}break;
                    case "22":{countyName="广安县";}break;
                    case "23":{countyName="武胜县";}break;
                    case "24":{countyName="邻水县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //四川省--巴中地区************************
            case "37":{
                cityName="巴中地区";
                switch (countyCode){
                    case "01":{countyName="巴中市";}break;
                    case "21":{countyName="通江县";}break;
                    case "22":{countyName="南江县";}break;
                    case "23":{countyName="平昌县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //四川省--眉山地区************************
            case "38":{
                cityName="眉山地区";
                switch (countyCode){
                    case "21":{countyName="眉山县";}break;
                    case "22":{countyName="仁寿县";}break;
                    case "23":{countyName="彭山县";}break;
                    case "24":{countyName="洪雅县";}break;
                    case "25":{countyName="丹棱县";}break;
                    case "26":{countyName="青神县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //四川省--资阳地区************************
            case "39":{
                cityName="资阳地区";
                switch (countyCode){
                    case "01":{cityName="资阳市";}break;
                    case "02":{cityName="简阳市";}break;
                    case "21":{cityName="安岳县";}break;
                    case "22":{cityName="乐至县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //四川省--其它市************************
            case "90":{
                cityName="--";
                switch (countyCode){
                    case "01":{cityName="广汉市";}break;
                    case "02":{cityName="江油市";}break;
                    case "03":{cityName="都江堰市";}break;
                    case "04":{cityName="峨眉山市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //**********************************************
            default:{
                cityName="(身份证号码3-4位有误)";
                countyName="--";
            }break;
        }
        return provinceName+"/"+cityName+"/"+countyName;
    }

    /**
     *@Author:ShiYun;
     *@Description:获得贵州省的方法
     *@Date: 11:21 2018\9\27 0027
     */
    private static String getProvinceNameByProvinceCode52(String cityCode,String countyCode){
        String provinceName = "贵州省";
        String cityName = "";
        String countyName = "";
        switch (cityCode){
            //贵州省--贵阳市**********************************************
            case "01":{
                cityName="贵阳市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="南明区";}break;
                    case "03":{countyName="云岩区";}break;
                    case "11":{countyName="花溪区";}break;
                    case "12":{countyName="乌当区";}break;
                    case "13":{countyName="白云区";}break;
                    case "14":{countyName="小河区";}break;
                    case "21":{countyName="开阳县";}break;
                    case "22":{countyName="息烽县";}break;
                    case "23":{countyName="修文县";}break;
                    case "81":{countyName="清镇市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //贵州省--六盘水市**********************************************
            case "02":{
                cityName="六盘水市";
                switch (countyCode){
                    case "01":{countyName="钟山区";}break;
                    case "02":{countyName="盘县特区";}break;
                    case "03":{countyName="六枝特区";}break;
                    case "21":{countyName="水城县";}break;
                    case "22":{countyName="盘县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //贵州省--遵义市**********************************************
            case "03":{
                cityName="遵义市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="红花岗区";}break;
                    case "03":{countyName="汇川区";}break;
                    case "21":{countyName="遵义县";}break;
                    case "22":{countyName="桐梓县";}break;
                    case "23":{countyName="绥阳县";}break;
                    case "24":{countyName="正安县";}break;
                    case "25":{countyName="道真仡佬族苗族自治县";}break;
                    case "26":{countyName="务川仡佬族苗族自治县";}break;
                    case "27":{countyName="凤冈县";}break;
                    case "28":{countyName="湄潭县";}break;
                    case "29":{countyName="余庆县";}break;
                    case "30":{countyName="习水县";}break;
                    case "81":{countyName="赤水市";}break;
                    case "82":{countyName="仁怀市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //贵州省--安顺市**********************************************
            case "04":{
                cityName="安顺市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="西秀区";}break;
                    case "21":{countyName="平坝县";}break;
                    case "22":{countyName="普定县";}break;
                    case "23":{countyName="镇宁布衣族苗族自治县";}break;
                    case "24":{countyName="关岭布依族苗族自治县";}break;
                    case "25":{countyName="紫云苗族布依族自治县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //贵州省--遵义地区**********************************************
            case "21":{
                cityName="遵义地区";
                switch (countyCode){
                    case "01":{countyName="遵义市";}break;
                    case "02":{countyName="赤水市";}break;
                    case "03":{countyName="仁怀市";}break;
                    case "21":{countyName="遵义县";}break;
                    case "22":{countyName="桐梓县";}break;
                    case "23":{countyName="绥阳县";}break;
                    case "24":{countyName="正安县";}break;
                    case "25":{countyName="道真仡佬族苗族自治县";}break;
                    case "26":{countyName="务川仡佬族苗族自治县";}break;
                    case "27":{countyName="凤冈县";}break;
                    case "28":{countyName="湄潭县";}break;
                    case "29":{countyName="余庆县";}break;
                    case "30":{countyName="仁怀县";}break;
                    case "31":{countyName="赤水县";}break;
                    case "32":{countyName="习水县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //贵州省--铜仁地区**********************************************
            case "22":{
                cityName="铜仁地区";
                switch (countyCode){
                    case "01":{countyName="铜仁市";}break;
                    case "21":{countyName="铜仁县";}break;
                    case "22":{countyName="江口县";}break;
                    case "23":{countyName="玉屏侗族自治县";}break;
                    case "24":{countyName="石阡县";}break;
                    case "25":{countyName="思南县";}break;
                    case "26":{countyName="印江土家族苗族自治县";}break;
                    case "27":{countyName="德江县";}break;
                    case "28":{countyName="沿河土家族自治县";}break;
                    case "29":{countyName="松桃苗族自治县";}break;
                    case "30":{countyName="万山特区";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //贵州省--黔西南布衣族苗族自治州********************************
            case "23":{
                cityName="黔西南布衣族苗族自治州";
                switch (countyCode){
                    case "01":{countyName="兴义市";}break;
                    case "02":{countyName="赤壁市";}break;
                    case "21":{countyName="兴义县";}break;
                    case "22":{countyName="兴仁县";}break;
                    case "23":{countyName="普安县";}break;
                    case "24":{countyName="晴隆县";}break;
                    case "25":{countyName="贞丰县";}break;
                    case "26":{countyName="望谟县";}break;
                    case "27":{countyName="册亨县";}break;
                    case "28":{countyName="安龙县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //贵州省--毕节地区**********************************************
            case "24":{
                cityName="毕节地区";
                switch (countyCode){
                    case "01":{countyName="毕节市";}break;
                    case "21":{countyName="毕节县";}break;
                    case "22":{countyName="大方县";}break;
                    case "23":{countyName="黔西县";}break;
                    case "24":{countyName="金沙县";}break;
                    case "25":{countyName="织金县";}break;
                    case "26":{countyName="纳雍县";}break;
                    case "27":{countyName="威宁黎族回族苗族自治县";}break;
                    case "28":{countyName="赫章县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //贵州省--安顺地区**********************************************
            case "25":{
                cityName="安顺地区";
                switch (countyCode){
                    case "01":{countyName="安顺市";}break;
                    case "02":{countyName="清镇市";}break;
                    case "21":{countyName="安顺县";}break;
                    case "22":{countyName="开阳县";}break;
                    case "23":{countyName="息烽县";}break;
                    case "24":{countyName="修文县";}break;
                    case "25":{countyName="清镇县";}break;
                    case "26":{countyName="平坝县";}break;
                    case "27":{countyName="普定县";}break;
                    case "28":{countyName="关岭布依族苗族自治县";}break;
                    case "29":{countyName="镇宁布衣族苗族自治县";}break;
                    case "30":{countyName="紫云苗族布依族自治县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //贵州省--黔东南苗族侗族自治州**********************************
            case "26":{
                cityName="黔东南苗族侗族自治州";
                switch (countyCode){
                    case "01":{countyName="凯里市";}break;
                    case "22":{countyName="黄平县";}break;
                    case "23":{countyName="施秉县";}break;
                    case "24":{countyName="三穗县";}break;
                    case "25":{countyName="镇远县";}break;
                    case "26":{countyName="岑巩县";}break;
                    case "27":{countyName="天柱县";}break;
                    case "28":{countyName="锦屏县";}break;
                    case "29":{countyName="剑河县";}break;
                    case "30":{countyName="台江县";}break;
                    case "31":{countyName="黎平县";}break;
                    case "32":{countyName="榕江县";}break;
                    case "33":{countyName="从江县";}break;
                    case "34":{countyName="雷山县";}break;
                    case "35":{countyName="麻江县";}break;
                    case "36":{countyName="丹寨县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //贵州省--黔南布衣族苗族自治州**********************************
            case "27":{
                cityName="黔南布衣族苗族自治州";
                switch (countyCode){
                    case "01":{countyName="都匀市";}break;
                    case "02":{countyName="福泉市";}break;
                    case "22":{countyName="荔波县";}break;
                    case "23":{countyName="贵定县";}break;
                    case "24":{countyName="福泉县";}break;
                    case "25":{countyName="瓮安县";}break;
                    case "26":{countyName="独山县";}break;
                    case "27":{countyName="平塘县";}break;
                    case "28":{countyName="罗甸县";}break;
                    case "29":{countyName="长顺县";}break;
                    case "30":{countyName="龙里县";}break;
                    case "31":{countyName="惠水县";}break;
                    case "32":{countyName="三都水族自治县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //**********************************************
            default:{
                cityName="(身份证号码3-4位有误)";
                countyName="--";
            }break;
        }
        return provinceName+"/"+cityName+"/"+countyName;
    }

    /**
     *@Author:ShiYun;
     *@Description:获得云南省的方法
     *@Date: 12:04 2018\9\27 0027
     */
    private static String getProvinceNameByProvinceCode53(String cityCode,String countyCode){
        String provinceName = "云南省";
        String cityName = "";
        String countyName = "";
        switch (cityCode){
            //云南省--昆明市**********************************************
            case "01":{
                cityName="昆明市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="五华区";}break;
                    case "03":{countyName="盘龙区";}break;
                    case "11":{countyName="官渡区";}break;
                    case "12":{countyName="西山区";}break;
                    case "13":{countyName="东川区";}break;
                    case "21":{countyName="呈贡县";}break;
                    case "22":{countyName="晋宁县";}break;
                    case "23":{countyName="安宁县";}break;
                    case "24":{countyName="富民县";}break;
                    case "25":{countyName="宜良县";}break;
                    case "26":{countyName="石林黎族自治县";}break;
                    case "27":{countyName="崇明县";}break;
                    case "28":{countyName="禄劝黎族苗族自治县";}break;
                    case "29":{countyName="寻甸回族黎族自治县";}break;
                    case "81":{countyName="安宁市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //云南省--曲靖市**********************************************
            case "02":{
                cityName="曲靖市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="麒麟区";}break;
                    case "21":{countyName="马龙县";}break;
                    case "22":{countyName="陆良县";}break;
                    case "23":{countyName="师宗县";}break;
                    case "24":{countyName="罗平县";}break;
                    case "25":{countyName="富源县";}break;
                    case "26":{countyName="会泽县";}break;
                    case "28":{countyName="沾益县";}break;
                    case "81":{countyName="宣威市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //云南省--玉溪市**********************************************
            case "03":{
                cityName="玉溪市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="红塔区";}break;
                    case "21":{countyName="江川县";}break;
                    case "22":{countyName="澄江县";}break;
                    case "23":{countyName="通海县";}break;
                    case "24":{countyName="华宁县";}break;
                    case "25":{countyName="易门县";}break;
                    case "26":{countyName="峨山黎族自治县";}break;
                    case "27":{countyName="新平黎族傣族自治县";}break;
                    case "28":{countyName="元江哈尼族黎族自治县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //云南省--保山市**********************************************
            case "04":{
                cityName="保山市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="隆阳区";}break;
                    case "21":{countyName="施甸县";}break;
                    case "22":{countyName="腾冲县";}break;
                    case "23":{countyName="龙陵县";}break;
                    case "24":{countyName="昌宁县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //云南省--邵通市**********************************************
            case "06":{
                cityName="邵通市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="邵阳区";}break;
                    case "21":{countyName="鲁甸县";}break;
                    case "22":{countyName="巧家县";}break;
                    case "23":{countyName="盐津县";}break;
                    case "24":{countyName="大关县";}break;
                    case "25":{countyName="永善县";}break;
                    case "26":{countyName="绥江县";}break;
                    case "27":{countyName="镇雄县";}break;
                    case "28":{countyName="彝良县";}break;
                    case "29":{countyName="威信县";}break;
                    case "30":{countyName="水富县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //云南省--丽江市**********************************************
            case "07":{
                cityName="丽江市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="古城区";}break;
                    case "21":{countyName="玉龙纳西族自治县";}break;
                    case "22":{countyName="永胜县";}break;
                    case "23":{countyName="华坪县";}break;
                    case "24":{countyName="宁蒗彝族自治县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //云南省--思茅市**********************************************
            case "08":{
                cityName="思茅市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="翠云区";}break;
                    case "21":{countyName="普洱哈尼族彝族自治县";}break;
                    case "22":{countyName="墨江哈尼族自治县";}break;
                    case "23":{countyName="景东彝族自治县";}break;
                    case "24":{countyName="景谷傣族彝族自治县";}break;
                    case "25":{countyName="镇沅彝族哈尼族拉祜族自治县";}break;
                    case "26":{countyName="江城哈尼族彝族自治县";}break;
                    case "27":{countyName="孟连傣族拉祜族佤族自治县";}break;
                    case "28":{countyName="澜沧拉古族自治县";}break;
                    case "29":{countyName="西盟佤族自治县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //云南省--临沧市**********************************************
            case "09":{
                cityName="临沧市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="临翔区";}break;
                    case "21":{countyName="凤庆县";}break;
                    case "22":{countyName="云县";}break;
                    case "23":{countyName="永德县";}break;
                    case "24":{countyName="镇康县";}break;
                    case "25":{countyName="双江拉祜族佤族布朗族傣族自治县";}break;
                    case "26":{countyName="耿马傣族佤族自治县";}break;
                    case "27":{countyName="沧源佤族自治县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //云南省--邵通地区********************************************
            case "21":{
                cityName="邵通地区";
                switch (countyCode){
                    case "01":{countyName="邵通市";}break;
                    case "22":{countyName="鲁甸县";}break;
                    case "23":{countyName="巧家县";}break;
                    case "24":{countyName="盐津县";}break;
                    case "25":{countyName="大关县";}break;
                    case "26":{countyName="永善县";}break;
                    case "27":{countyName="绥江县";}break;
                    case "28":{countyName="镇雄县";}break;
                    case "29":{countyName="彝良县";}break;
                    case "30":{countyName="威信县";}break;
                    case "31":{countyName="水富县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //云南省--曲靖地区********************************************
            case "22":{
                cityName="曲靖地区";
                switch (countyCode){
                    case "01":{countyName="麒麟区";}break;
                    case "21":{countyName="曲靖县";}break;
                    case "22":{countyName="沾益县";}break;
                    case "23":{countyName="马龙县";}break;
                    case "24":{countyName="宣威县";}break;
                    case "25":{countyName="富源县";}break;
                    case "26":{countyName="罗平县";}break;
                    case "27":{countyName="师宗县";}break;
                    case "28":{countyName="陆良县";}break;
                    case "29":{countyName="宜良县";}break;
                    case "30":{countyName="路南彝族自治县";}break;
                    case "31":{countyName="寻甸回族彝族自治县";}break;
                    case "32":{countyName="嵩明县";}break;
                    case "33":{countyName="会泽县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //云南省--楚雄黎族自治州**************************************
            case "23":{
                cityName="楚雄黎族自治州";
                switch (countyCode){
                    case "01":{countyName="楚雄市";}break;
                    case "21":{countyName="楚雄县";}break;
                    case "22":{countyName="双柏县";}break;
                    case "23":{countyName="牟定县";}break;
                    case "24":{countyName="南华县";}break;
                    case "25":{countyName="姚安县";}break;
                    case "26":{countyName="大姚县";}break;
                    case "27":{countyName="永仁县";}break;
                    case "28":{countyName="元谋县";}break;
                    case "29":{countyName="武定县";}break;
                    case "30":{countyName="禄劝县";}break;
                    case "31":{countyName="禄丰县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //云南省--玉溪地区********************************************
            case "24":{
                cityName="玉溪地区";
                switch (countyCode){
                    case "01":{countyName="玉溪市";}break;
                    case "21":{countyName="玉溪县";}break;
                    case "22":{countyName="江川县";}break;
                    case "23":{countyName="澄江县";}break;
                    case "24":{countyName="通海县";}break;
                    case "25":{countyName="华宁县";}break;
                    case "26":{countyName="易门县";}break;
                    case "27":{countyName="峨山彝族自治县";}break;
                    case "28":{countyName="新平彝族傣族自治县";}break;
                    case "29":{countyName="元江哈尼族彝族傣族自治县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //云南省--红河哈尼族黎族自治州********************************
            case "25":{
                cityName="红河哈尼族黎族自治州";
                switch (countyCode){
                    case "01":{countyName="个旧市";}break;
                    case "02":{countyName="开远市";}break;
                    case "22":{countyName="蒙自县";}break;
                    case "23":{countyName="屏边苗族自治县";}break;
                    case "24":{countyName="建水县";}break;
                    case "25":{countyName="石屏县";}break;
                    case "26":{countyName="弥勒县";}break;
                    case "27":{countyName="泸西县";}break;
                    case "28":{countyName="元阳县";}break;
                    case "29":{countyName="红河县";}break;
                    case "30":{countyName="金平苗族瑶族傣族自治县";}break;
                    case "31":{countyName="绿春县";}break;
                    case "32":{countyName="河口瑶族自治县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //云南省--文山壮族苗族自治州**********************************
            case "26":{
                cityName="文山壮族苗族自治州";
                switch (countyCode){
                    case "21":{countyName="文山县";}break;
                    case "22":{countyName="砚山县";}break;
                    case "23":{countyName="西畴县";}break;
                    case "24":{countyName="麻栗坡县";}break;
                    case "25":{countyName="马关县";}break;
                    case "26":{countyName="丘北县";}break;
                    case "27":{countyName="广南县";}break;
                    case "28":{countyName="富宁县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //云南省--思茅地区********************************************
            case "27":{
                cityName="思茅地区";
                switch (countyCode){
                    case "01":{countyName="思茅市";}break;
                    case "22":{countyName="普洱哈尼族彝族自治县";}break;
                    case "23":{countyName="墨江哈尼族自治县";}break;
                    case "24":{countyName="景东彝族自治县";}break;
                    case "25":{countyName="景谷傣族彝族自治县";}break;
                    case "26":{countyName="镇沅彝族哈尼族拉祜族自治县";}break;
                    case "27":{countyName="江城哈尼族彝族自治县";}break;
                    case "28":{countyName="孟连傣族拉祜族佤族自治县";}break;
                    case "29":{countyName="澜沧拉古族自治县";}break;
                    case "30":{countyName="西盟佤族自治县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //云南省--西双版纳傣族自治州**********************************
            case "28":{
                cityName="西双版纳傣族自治州";
                switch (countyCode){
                    case "01":{countyName="景洪市";}break;
                    case "22":{countyName="勐海县";}break;
                    case "23":{countyName="勐腊县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //云南省--大理白族自治州**************************************
            case "29":{
                cityName="大理白族自治州";
                switch (countyCode){
                    case "01":{countyName="大理市";}break;
                    case "22":{countyName="漾濞彝族自治县";}break;
                    case "23":{countyName="祥云县";}break;
                    case "24":{countyName="宾川县";}break;
                    case "25":{countyName="弥渡县";}break;
                    case "26":{countyName="南涧彝族自治县";}break;
                    case "27":{countyName="巍山彝族回族自治县";}break;
                    case "28":{countyName="永平县";}break;
                    case "29":{countyName="云龙县";}break;
                    case "30":{countyName="洱源县";}break;
                    case "31":{countyName="剑川县";}break;
                    case "32":{countyName="鹤庆县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //云南省--保山地区********************************************
            case "30":{
                cityName="保山地区";
                switch (countyCode){
                    case "01":{countyName="保山市";}break;
                    case "22":{countyName="施甸县";}break;
                    case "23":{countyName="腾冲县";}break;
                    case "24":{countyName="龙陵县";}break;
                    case "25":{countyName="昌宁县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //云南省--德宏傣族景颇族自治州********************************
            case "31":{
                cityName="德宏傣族景颇族自治州";
                switch (countyCode){
                    case "01":{countyName="畹町市";}break;
                    case "02":{countyName="瑞丽市";}break;
                    case "03":{countyName="潞西市";}break;
                    case "21":{countyName="潞西县";}break;
                    case "22":{countyName="梁河县";}break;
                    case "23":{countyName="盈江县";}break;
                    case "24":{countyName="陇川县";}break;
                    case "25":{countyName="瑞丽县";}break;
                    case "26":{countyName="畹町镇";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //云南省--丽江地区********************************************
            case "32":{
                cityName="丽江地区";
                switch (countyCode){
                    case "21":{countyName="丽江纳西族自治县";}break;
                    case "22":{countyName="永胜县";}break;
                    case "23":{countyName="华坪县";}break;
                    case "24":{countyName="宁蒗彝族自治县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //云南省--怒江傈傈族自治州************************************
            case "33":{
                cityName="怒江傈傈族自治州";
                switch (countyCode){
                    case "21":{countyName="泸水县";}break;
                    case "22":{countyName="碧江县";}break;
                    case "23":{countyName="福贡县";}break;
                    case "24":{countyName="贡山独龙族怒族自治县";}break;
                    case "25":{countyName="兰坪白族普米族自治县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //云南省--迪庆藏族自治州**************************************
            case "34":{
                cityName="迪庆藏族自治州";
                switch (countyCode){
                    case "21":{countyName="香格里拉县";}break;
                    case "22":{countyName="德钦县";}break;
                    case "23":{countyName="维西傈傈族自治县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //云南省--临沧地区********************************************
            case "35":{
                cityName="临沧地区";
                switch (countyCode){
                    case "21":{countyName="临沧县";}break;
                    case "22":{countyName="凤庆县";}break;
                    case "23":{countyName="云县";}break;
                    case "24":{countyName="永德县";}break;
                    case "25":{countyName="彭水苗族土家族自治县";}break;
                    case "26":{countyName="双江拉祜族佤族布朗族自治县";}break;
                    case "27":{countyName="耿马傣族佤族自治县";}break;
                    case "28":{countyName="沧源佤族自治县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //************************************************************
            default:{
                cityName="(身份证号码3-4位有误)";
                countyName="--";
            }break;
        }
        return provinceName+"/"+cityName+"/"+countyName;
    }

    /**
     *@Author:ShiYun;
     *@Description:获得西藏自治区的方法
     *@Date: 15:16 2018\9\27 0027
     */
    private static String getProvinceNameByProvinceCode54(String cityCode,String countyCode){
        String provinceName = "西藏自治区";
        String cityName = "";
        String countyName = "";
        switch (cityCode){
            //西藏自治区--拉萨市**********************************************
            case "01":{
                cityName="拉萨市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="城关区";}break;
                    case "20":{countyName="市区";}break;
                    case "21":{countyName="林周县";}break;
                    case "22":{countyName="当雄县";}break;
                    case "23":{countyName="尼木县";}break;
                    case "24":{countyName="曲水县";}break;
                    case "25":{countyName="堆龙德庆县";}break;
                    case "26":{countyName="达孜县";}break;
                    case "27":{countyName="墨竹工卡县";}break;
                    case "28":{countyName="工布江达县";}break;
                    case "29":{countyName="林芝县";}break;
                    case "30":{countyName="米林县";}break;
                    case "31":{countyName="墨脱县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //西藏自治区--昌都地区**********************************************
            case "21":{
                cityName="昌都地区";
                switch (countyCode){
                    case "21":{countyName="昌都县";}break;
                    case "22":{countyName="江达县";}break;
                    case "23":{countyName="贡觉县";}break;
                    case "24":{countyName="类乌齐县";}break;
                    case "25":{countyName="丁青县";}break;
                    case "26":{countyName="察雅县";}break;
                    case "27":{countyName="八宿县";}break;
                    case "28":{countyName="左贡县";}break;
                    case "29":{countyName="芒康县";}break;
                    case "32":{countyName="洛隆县";}break;
                    case "33":{countyName="边坝县";}break;
                    case "34":{countyName="盐井县";}break;
                    case "35":{countyName="碧士县";}break;
                    case "36":{countyName="妥坝县";}break;
                    case "37":{countyName="生达县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //西藏自治区--山南地区**********************************************
            case "22":{
                cityName="山南地区";
                switch (countyCode){
                    case "21":{countyName="乃东县";}break;
                    case "22":{countyName="扎囊县";}break;
                    case "23":{countyName="贡嘎县";}break;
                    case "24":{countyName="桑日县";}break;
                    case "25":{countyName="琼结县";}break;
                    case "26":{countyName="曲松县";}break;
                    case "27":{countyName="措美县";}break;
                    case "28":{countyName="洛扎县";}break;
                    case "29":{countyName="加扎县";}break;
                    case "30":{countyName="朗县";}break;
                    case "31":{countyName="隆子县";}break;
                    case "32":{countyName="错那先";}break;
                    case "33":{countyName="浪卡子县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //西藏自治区--日喀则地区**********************************************
            case "23":{
                cityName="日喀则地区";
                switch (countyCode){
                    case "01":{countyName="日喀则市";}break;
                    case "21":{countyName="日喀则县";}break;
                    case "22":{countyName="南木林县";}break;
                    case "23":{countyName="江孜县";}break;
                    case "24":{countyName="定日县";}break;
                    case "25":{countyName="萨迦县";}break;
                    case "26":{countyName="拉孜县";}break;
                    case "27":{countyName="昂仁县";}break;
                    case "28":{countyName="谢通门县";}break;
                    case "29":{countyName="白朗县";}break;
                    case "30":{countyName="仁布县";}break;
                    case "31":{countyName="康马县";}break;
                    case "32":{countyName="定结县";}break;
                    case "33":{countyName="仲巴县";}break;
                    case "34":{countyName="亚东县";}break;
                    case "35":{countyName="吉隆县";}break;
                    case "36":{countyName="聂拉木县";}break;
                    case "37":{countyName="萨嘎县";}break;
                    case "38":{countyName="岗巴县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //西藏自治区--那曲地区**********************************************
            case "24":{
                cityName="那曲地区";
                switch (countyCode){
                    case "21":{countyName="那曲县";}break;
                    case "22":{countyName="嘉黎县";}break;
                    case "23":{countyName="比如县";}break;
                    case "24":{countyName="聂荣县";}break;
                    case "25":{countyName="安多县";}break;
                    case "26":{countyName="申扎县";}break;
                    case "27":{countyName="索县";}break;
                    case "28":{countyName="班戈县";}break;
                    case "29":{countyName="巴青县";}break;
                    case "30":{countyName="尼玛县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //西藏自治区--阿里地区**********************************************
            case "25":{
                cityName="阿里地区";
                switch (countyCode){
                    case "21":{countyName="普兰县";}break;
                    case "22":{countyName="札达县";}break;
                    case "23":{countyName="格尔县";}break;
                    case "24":{countyName="日土县";}break;
                    case "25":{countyName="革吉县";}break;
                    case "26":{countyName="改则县";}break;
                    case "27":{countyName="措勤县";}break;
                    case "28":{countyName="隆格尔县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //西藏自治区--林芝地区**********************************************
            case "26":{
                cityName="林芝地区";
                switch (countyCode){
                    case "21":{countyName="林芝县";}break;
                    case "22":{countyName="工布江达县";}break;
                    case "23":{countyName="米林县";}break;
                    case "24":{countyName="墨脱县";}break;
                    case "25":{countyName="波密县";}break;
                    case "26":{countyName="察隅县";}break;
                    case "27":{countyName="朗县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //西藏自治区--江孜地区**********************************************
            case "27":{
                cityName="江孜地区";
                switch (countyCode){
                    case "21":{countyName="江孜县";}break;
                    case "22":{countyName="浪卡子县";}break;
                    case "23":{countyName="白朗县";}break;
                    case "24":{countyName="仁布县";}break;
                    case "25":{countyName="康马县";}break;
                    case "26":{countyName="亚东县";}break;
                    case "27":{countyName="岗巴县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //************************************************************
            default:{
                cityName="(身份证号码3-4位有误)";
                countyName="--";
            }break;
        }
        return provinceName+"/"+cityName+"/"+countyName;
    }

    /**
     *@Author:ShiYun;
     *@Description:获得陕西省的方法
     *@Date: 15:50 2018\9\27 0027
     */
    private static String getProvinceNameByProvinceCode61(String cityCode,String countyCode){
        String provinceName = "陕西省";
        String cityName = "";
        String countyName = "";
        switch (cityCode){
            //陕西省--西安市**********************************************
            case "01":{
                cityName="西安市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="新城区";}break;
                    case "03":{countyName="碑林区";}break;
                    case "04":{countyName="莲湖区";}break;
                    case "06":{countyName="经济开发区";}break;
                    case "11":{countyName="灞桥区";}break;
                    case "12":{countyName="未央区";}break;
                    case "13":{countyName="雁塔区";}break;
                    case "14":{countyName="阎良区";}break;
                    case "15":{countyName="临潼区";}break;
                    case "16":{countyName="长安区";}break;
                    case "21":{countyName="长安县";}break;
                    case "22":{countyName="蓝田县";}break;
                    case "23":{countyName="临潼县";}break;
                    case "24":{countyName="周至县";}break;
                    case "25":{countyName="户县";}break;
                    case "26":{countyName="高陵县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //陕西省--铜川市**********************************************
            case "02":{
                cityName="铜川市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="城区";}break;
                    case "03":{countyName="郊区";}break;
                    case "04":{countyName="耀州区";}break;
                    case "20":{countyName="市区";}break;
                    case "21":{countyName="耀县";}break;
                    case "22":{countyName="宜君县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //陕西省--宝鸡市**********************************************
            case "03":{
                cityName="宝鸡市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="渭滨区";}break;
                    case "03":{countyName="金台区";}break;
                    case "04":{countyName="陈仓区";}break;
                    case "20":{countyName="市区";}break;
                    case "21":{countyName="宝鸡县";}break;
                    case "22":{countyName="凤翔县";}break;
                    case "23":{countyName="岐山县";}break;
                    case "24":{countyName="扶风县";}break;
                    case "25":{countyName="武功县";}break;
                    case "26":{countyName="眉县";}break;
                    case "27":{countyName="陇县";}break;
                    case "28":{countyName="千阳县";}break;
                    case "29":{countyName="麟游县";}break;
                    case "30":{countyName="凤县";}break;
                    case "31":{countyName="太白县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //陕西省--咸阳市**********************************************
            case "04":{
                cityName="咸阳市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="秦都区";}break;
                    case "03":{countyName="杨凌区";}break;
                    case "04":{countyName="渭城区";}break;
                    case "21":{countyName="兴平县";}break;
                    case "22":{countyName="三原县";}break;
                    case "23":{countyName="泾阳县";}break;
                    case "24":{countyName="乾县";}break;
                    case "25":{countyName="礼泉县";}break;
                    case "26":{countyName="永寿县";}break;
                    case "27":{countyName="彬县";}break;
                    case "28":{countyName="长武县";}break;
                    case "29":{countyName="旬邑县";}break;
                    case "30":{countyName="淳化县";}break;
                    case "31":{countyName="武功县";}break;
                    case "81":{countyName="兴平市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //陕西省--渭南市**********************************************
            case "05":{
                cityName="渭南市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="临渭区";}break;
                    case "21":{countyName="华县";}break;
                    case "22":{countyName="潼关县";}break;
                    case "23":{countyName="大荔县";}break;
                    case "24":{countyName="合阳县";}break;
                    case "25":{countyName="澄城县";}break;
                    case "26":{countyName="浦城县";}break;
                    case "27":{countyName="白水县";}break;
                    case "28":{countyName="富平县";}break;
                    case "81":{countyName="韩城市";}break;
                    case "82":{countyName="华阴市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //陕西省--延安市**********************************************
            case "06":{
                cityName="延安市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="宝塔区";}break;
                    case "21":{countyName="延长县";}break;
                    case "22":{countyName="延川县";}break;
                    case "23":{countyName="子长县";}break;
                    case "24":{countyName="安寨县";}break;
                    case "25":{countyName="志丹县";}break;
                    case "26":{countyName="吴起县";}break;
                    case "27":{countyName="甘泉县";}break;
                    case "28":{countyName="富县";}break;
                    case "29":{countyName="洛川县";}break;
                    case "30":{countyName="宜川县";}break;
                    case "31":{countyName="黄龙县";}break;
                    case "32":{countyName="黄陵县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //陕西省--汉中市**********************************************
            case "07":{
                cityName="汉中市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="汉台区";}break;
                    case "21":{countyName="南郑县";}break;
                    case "22":{countyName="城固县";}break;
                    case "23":{countyName="洋县";}break;
                    case "24":{countyName="西乡县";}break;
                    case "25":{countyName="勉县";}break;
                    case "26":{countyName="宁强县";}break;
                    case "27":{countyName="略阳县";}break;
                    case "28":{countyName="镇巴县";}break;
                    case "29":{countyName="留坝县";}break;
                    case "30":{countyName="佛坪县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //陕西省--榆林市**********************************************
            case "08":{
                cityName="榆林市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="榆阳区";}break;
                    case "21":{countyName="神木县";}break;
                    case "22":{countyName="府谷县";}break;
                    case "23":{countyName="横山县";}break;
                    case "24":{countyName="靖边县";}break;
                    case "25":{countyName="定边县";}break;
                    case "26":{countyName="绥德县";}break;
                    case "27":{countyName="米脂县";}break;
                    case "28":{countyName="佳县";}break;
                    case "29":{countyName="吴堡县";}break;
                    case "30":{countyName="清涧县";}break;
                    case "31":{countyName="子洲县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //陕西省--安康市**********************************************
            case "09":{
                cityName="安康市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="汉滨区";}break;
                    case "21":{countyName="汉阴县";}break;
                    case "22":{countyName="石泉县";}break;
                    case "23":{countyName="宁峡县";}break;
                    case "24":{countyName="紫阳县";}break;
                    case "25":{countyName="岚皋县";}break;
                    case "26":{countyName="平利县";}break;
                    case "27":{countyName="镇平县";}break;
                    case "28":{countyName="旬阳县";}break;
                    case "29":{countyName="白河县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //陕西省--商洛市**********************************************
            case "10":{
                cityName="商洛市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="商州区";}break;
                    case "21":{countyName="洛南县";}break;
                    case "22":{countyName="丹凤县";}break;
                    case "23":{countyName="商南县";}break;
                    case "24":{countyName="山阳县";}break;
                    case "25":{countyName="镇安县";}break;
                    case "26":{countyName="柞水县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //陕西省--渭南地区**********************************************
            case "21":{
                cityName="渭南地区";
                switch (countyCode){
                    case "01":{countyName="渭南市";}break;
                    case "02":{countyName="韩城市";}break;
                    case "03":{countyName="华阴市";}break;
                    case "21":{countyName="蓝田县";}break;
                    case "22":{countyName="临潼县";}break;
                    case "23":{countyName="渭南县";}break;
                    case "24":{countyName="华县";}break;
                    case "25":{countyName="华阴县";}break;
                    case "26":{countyName="潼关县";}break;
                    case "27":{countyName="大荔县";}break;
                    case "28":{countyName="浦城县";}break;
                    case "29":{countyName="澄城县";}break;
                    case "30":{countyName="白水县";}break;
                    case "31":{countyName="韩城县";}break;
                    case "32":{countyName="合阳县";}break;
                    case "33":{countyName="富平县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //陕西省--咸阳地区**********************************************
            case "22":{
                cityName="咸阳地区";
                switch (countyCode){
                    case "01":{countyName="咸阳市";}break;
                    case "21":{countyName="兴平县";}break;
                    case "22":{countyName="周至县";}break;
                    case "23":{countyName="户县";}break;
                    case "24":{countyName="三原县";}break;
                    case "25":{countyName="泾阳县";}break;
                    case "26":{countyName="高陵县";}break;
                    case "27":{countyName="乾县";}break;
                    case "28":{countyName="礼泉县";}break;
                    case "29":{countyName="永寿县";}break;
                    case "30":{countyName="彬县";}break;
                    case "31":{countyName="长武县";}break;
                    case "32":{countyName="旬邑县";}break;
                    case "33":{countyName="淳化县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //陕西省--汉中地区**********************************************
            case "23":{
                cityName="汉中地区";
                switch (countyCode){
                    case "01":{countyName="汉中市";}break;
                    case "21":{countyName="南郑县";}break;
                    case "22":{countyName="城固县";}break;
                    case "23":{countyName="洋县";}break;
                    case "24":{countyName="西乡县";}break;
                    case "25":{countyName="勉县";}break;
                    case "26":{countyName="宁强县";}break;
                    case "27":{countyName="略阳县";}break;
                    case "28":{countyName="镇巴县";}break;
                    case "29":{countyName="留坝县";}break;
                    case "30":{countyName="佛坪县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //陕西省--安康地区**********************************************
            case "24":{
                cityName="安康地区";
                switch (countyCode){
                    case "01":{countyName="安康市";}break;
                    case "21":{countyName="安康县";}break;
                    case "22":{countyName="汉阴县";}break;
                    case "23":{countyName="石泉县";}break;
                    case "24":{countyName="宁陜县";}break;
                    case "25":{countyName="紫阳县";}break;
                    case "26":{countyName="岚皋县";}break;
                    case "27":{countyName="平利县";}break;
                    case "28":{countyName="镇坪县";}break;
                    case "29":{countyName="旬阳县";}break;
                    case "30":{countyName="白河县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //陕西省--商洛地区**********************************************
            case "25":{
                cityName="商洛地区";
                switch (countyCode){
                    case "01":{countyName="商州市";}break;
                    case "22":{countyName="洛南县";}break;
                    case "23":{countyName="丹凤县";}break;
                    case "24":{countyName="商南县";}break;
                    case "25":{countyName="山阳县";}break;
                    case "26":{countyName="镇安县";}break;
                    case "27":{countyName="柞水县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //陕西省--延安地区**********************************************
            case "26":{
                cityName="延安地区";
                switch (countyCode){
                    case "01":{countyName="延安市";}break;
                    case "21":{countyName="延长县";}break;
                    case "22":{countyName="延川县";}break;
                    case "23":{countyName="子长县";}break;
                    case "24":{countyName="安寨县";}break;
                    case "25":{countyName="志丹县";}break;
                    case "26":{countyName="吴旗县";}break;
                    case "27":{countyName="甘泉县";}break;
                    case "28":{countyName="富县";}break;
                    case "29":{countyName="洛川县";}break;
                    case "30":{countyName="宜川县";}break;
                    case "31":{countyName="黄龙县";}break;
                    case "32":{countyName="黄陵县";}break;
                    case "33":{countyName="宜君县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //陕西省--榆林地区**********************************************
            case "27":{
                cityName="榆林地区";
                switch (countyCode){
                    case "01":{countyName="榆林市";}break;
                    case "21":{countyName="榆林县";}break;
                    case "22":{countyName="神木县";}break;
                    case "23":{countyName="府谷县";}break;
                    case "24":{countyName="横山县";}break;
                    case "25":{countyName="靖边县";}break;
                    case "26":{countyName="定边县";}break;
                    case "27":{countyName="绥德县";}break;
                    case "28":{countyName="米脂县";}break;
                    case "29":{countyName="佳县";}break;
                    case "30":{countyName="吴堡县";}break;
                    case "31":{countyName="清涧县";}break;
                    case "32":{countyName="子洲县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //************************************************************
            default:{
                cityName="(身份证号码3-4位有误)";
                countyName="--";
            }break;
        }
        return provinceName+"/"+cityName+"/"+countyName;
    }

    /**
     *@Author:ShiYun;
     *@Description:获得甘肃省的方法
     *@Date: 17:06 2018\9\27 0027
     */
    private static String getProvinceNameByProvinceCode62(String cityCode,String countyCode){
        String provinceName = "甘肃省";
        String cityName = "";
        String countyName = "";
        switch (cityCode){
            //甘肃省--兰州市**********************************************
            case "01":{
                cityName="兰州市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="城关区";}break;
                    case "03":{countyName="七里河区";}break;
                    case "04":{countyName="西固区";}break;
                    case "05":{countyName="安宁区";}break;
                    case "11":{countyName="红古区";}break;
                    case "21":{countyName="永登县";}break;
                    case "22":{countyName="皋兰县";}break;
                    case "23":{countyName="榆中县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //甘肃省--嘉峪关市**********************************************
            case "02":{
                cityName="嘉峪关市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="嘉峪关区";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //甘肃省--金昌市**********************************************
            case "03":{
                cityName="金昌市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="金川区";}break;
                    case "21":{countyName="永昌县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //甘肃省--白银市**********************************************
            case "04":{
                cityName="白银市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="白银区";}break;
                    case "03":{countyName="平川区";}break;
                    case "21":{countyName="靖远县";}break;
                    case "22":{countyName="会宁县";}break;
                    case "23":{countyName="景泰县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //甘肃省--天水市**********************************************
            case "05":{
                cityName="天水市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="秦州区";}break;
                    case "03":{countyName="麦积区";}break;
                    case "21":{countyName="清水县";}break;
                    case "22":{countyName="秦安县";}break;
                    case "23":{countyName="甘谷县";}break;
                    case "24":{countyName="武山县";}break;
                    case "25":{countyName="张家川回族自治县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //甘肃省--武威市**********************************************
            case "06":{
                cityName="武威市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="凉州区";}break;
                    case "21":{countyName="民勤县";}break;
                    case "22":{countyName="古浪县";}break;
                    case "23":{countyName="天祝藏族自治县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //甘肃省--张掖市**********************************************
            case "07":{
                cityName="张掖市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="甘州区";}break;
                    case "21":{countyName="肃南裕固族自治县";}break;
                    case "22":{countyName="民乐县";}break;
                    case "23":{countyName="临泽县";}break;
                    case "24":{countyName="高台县";}break;
                    case "25":{countyName="山丹县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //甘肃省--平凉市**********************************************
            case "08":{
                cityName="平凉市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="崆峒区";}break;
                    case "21":{countyName="泾川县";}break;
                    case "22":{countyName="灵台县";}break;
                    case "23":{countyName="崇信县";}break;
                    case "24":{countyName="华亭县";}break;
                    case "25":{countyName="庄浪县";}break;
                    case "26":{countyName="静宁县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //甘肃省--酒泉市**********************************************
            case "09":{
                cityName="酒泉市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="肃州区";}break;
                    case "21":{countyName="金塔县";}break;
                    case "22":{countyName="瓜州县";}break;
                    case "23":{countyName="肃北蒙古族自治县";}break;
                    case "24":{countyName="阿克塞哈萨克族自治县";}break;
                    case "81":{countyName="玉门市";}break;
                    case "82":{countyName="敦煌市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //甘肃省--庆阳市**********************************************
            case "10":{
                cityName="庆阳市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="西峰区";}break;
                    case "21":{countyName="庆城县";}break;
                    case "22":{countyName="环县";}break;
                    case "23":{countyName="华池县";}break;
                    case "24":{countyName="合水县";}break;
                    case "25":{countyName="正宁县";}break;
                    case "26":{countyName="宁县";}break;
                    case "27":{countyName="镇原县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //甘肃省--定西市**********************************************
            case "11":{
                cityName="定西市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="安定区";}break;
                    case "21":{countyName="通渭县";}break;
                    case "22":{countyName="陇西县";}break;
                    case "23":{countyName="渭源县";}break;
                    case "24":{countyName="临洮县";}break;
                    case "25":{countyName="漳县";}break;
                    case "26":{countyName="岷县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //甘肃省--陇南市**********************************************
            case "12":{
                cityName="陇南市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="武都区";}break;
                    case "21":{countyName="成县";}break;
                    case "22":{countyName="文县";}break;
                    case "23":{countyName="宕昌县";}break;
                    case "24":{countyName="康县";}break;
                    case "25":{countyName="西和县";}break;
                    case "26":{countyName="礼县";}break;
                    case "27":{countyName="徽县";}break;
                    case "28":{countyName="两当县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //甘肃省--酒泉地区**********************************************
            case "21":{
                cityName="酒泉地区";
                switch (countyCode){
                    case "01":{countyName="玉门市";}break;
                    case "02":{countyName="酒泉市";}break;
                    case "03":{countyName="敦煌市";}break;
                    case "23":{countyName="金塔县";}break;
                    case "24":{countyName="肃北蒙古族自治县";}break;
                    case "25":{countyName="阿克塞哈尼克族自治县";}break;
                    case "26":{countyName="安西县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //甘肃省--张掖地区**********************************************
            case "22":{
                cityName="张掖地区";
                switch (countyCode){
                    case "01":{countyName="张掖市";}break;
                    case "22":{countyName="肃南裕固族自治县";}break;
                    case "23":{countyName="民乐县";}break;
                    case "24":{countyName="临泽县";}break;
                    case "25":{countyName="高台县";}break;
                    case "26":{countyName="山丹县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //甘肃省--武威地区**********************************************
            case "23":{
                cityName="武威地区";
                switch (countyCode){
                    case "01":{countyName="武威市";}break;
                    case "22":{countyName="民勤县";}break;
                    case "23":{countyName="古浪县";}break;
                    case "26":{countyName="天祝藏族自治县";}break;
                    case "27":{countyName="连古城国家级自然保护区管理局";}break;
                    case "28":{countyName="石羊河林业总场";}break;
                    case "29":{countyName="苏武山林场";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //甘肃省--定西地区**********************************************
            case "24":{
                cityName="定西地区";
                switch (countyCode){
                    case "21":{countyName="定西县";}break;
                    case "24":{countyName="通渭县";}break;
                    case "25":{countyName="陇西县";}break;
                    case "26":{countyName="渭源县";}break;
                    case "27":{countyName="临洮县";}break;
                    case "28":{countyName="漳县";}break;
                    case "29":{countyName="岷县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //甘肃省--陇南地区**********************************************
            case "26":{
                cityName="陇南地区";
                switch (countyCode){
                    case "21":{countyName="武都县";}break;
                    case "23":{countyName="宕昌县";}break;
                    case "24":{countyName="成县";}break;
                    case "25":{countyName="康县";}break;
                    case "26":{countyName="文县";}break;
                    case "27":{countyName="西和县";}break;
                    case "28":{countyName="礼县";}break;
                    case "29":{countyName="两当县";}break;
                    case "30":{countyName="徽县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //甘肃省--平凉地区**********************************************
            case "27":{
                cityName="平凉地区";
                switch (countyCode){
                    case "01":{countyName="平凉市";}break;
                    case "22":{countyName="泾川县";}break;
                    case "23":{countyName="灵台县";}break;
                    case "24":{countyName="崇信县";}break;
                    case "25":{countyName="华亭县";}break;
                    case "26":{countyName="庄浪县";}break;
                    case "27":{countyName="静宁县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //甘肃省--庆阳地区**********************************************
            case "28":{
                cityName="庆阳地区";
                switch (countyCode){
                    case "01":{countyName="西峰市";}break;
                    case "21":{countyName="庆阳县";}break;
                    case "22":{countyName="环县";}break;
                    case "23":{countyName="华池县";}break;
                    case "24":{countyName="合水县";}break;
                    case "25":{countyName="正宁县";}break;
                    case "26":{countyName="宁县";}break;
                    case "27":{countyName="镇原县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //甘肃省--临夏回族自治州**********************************************
            case "29":{
                cityName="临夏回族自治州";
                switch (countyCode){
                    case "01":{countyName="临夏市";}break;
                    case "21":{countyName="临夏县";}break;
                    case "22":{countyName="康乐县";}break;
                    case "23":{countyName="永靖县";}break;
                    case "24":{countyName="广河县";}break;
                    case "25":{countyName="和政县";}break;
                    case "26":{countyName="东乡族自治县";}break;
                    case "27":{countyName="积石山保安族东乡族撒拉族自治县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //甘肃省--甘南藏族自治州**********************************************
            case "30":{
                cityName="甘南藏族自治州";
                switch (countyCode){
                    case "01":{countyName="合作市";}break;
                    case "21":{countyName="临潭县";}break;
                    case "22":{countyName="卓尼县";}break;
                    case "23":{countyName="舟曲县";}break;
                    case "24":{countyName="迭部县";}break;
                    case "25":{countyName="玛曲县";}break;
                    case "26":{countyName="碌曲县";}break;
                    case "27":{countyName="夏河县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //************************************************************
            default:{
                cityName="(身份证号码3-4位有误)";
                countyName="--";
            }break;
        }
        return provinceName+"/"+cityName+"/"+countyName;
    }

    /**
     *@Author:ShiYun;
     *@Description:获得青海省的方法
     *@Date: 18:04 2018\9\27 0027
     */
    private static String getProvinceNameByProvinceCode63(String cityCode,String countyCode){
        String provinceName = "青海省";
        String cityName = "";
        String countyName = "";
        switch (cityCode){
            //青海省--西宁市**********************************************
            case "01":{
                cityName="西宁市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="城东区";}break;
                    case "03":{countyName="城中区";}break;
                    case "04":{countyName="城西区";}break;
                    case "05":{countyName="城北区";}break;
                    case "21":{countyName="大通回族土族自治县";}break;
                    case "22":{countyName="湟中县";}break;
                    case "23":{countyName="湟源县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //青海省--海东地区**********************************************
            case "21":{
                cityName="海东地区";
                switch (countyCode){
                    case "21":{countyName="平安县";}break;
                    case "22":{countyName="民和回族土族自治县";}break;
                    case "23":{countyName="乐都县";}break;
                    case "24":{countyName="湟中县";}break;
                    case "25":{countyName="湟源县";}break;
                    case "26":{countyName="互助土族自治县";}break;
                    case "27":{countyName="化隆回族自治县";}break;
                    case "28":{countyName="循化撒拉族自治县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //青海省--海北藏族自治州****************************************
            case "22":{
                cityName="海北藏族自治州";
                switch (countyCode){
                    case "21":{countyName="门源回族自治县";}break;
                    case "22":{countyName="祁连县";}break;
                    case "23":{countyName="海晏县";}break;
                    case "24":{countyName="刚察县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //青海省--黄南藏族自治州****************************************
            case "23":{
                cityName="黄南藏族自治州";
                switch (countyCode){
                    case "21":{countyName="同仁县";}break;
                    case "22":{countyName="尖扎县";}break;
                    case "23":{countyName="泽库县";}break;
                    case "24":{countyName="河南蒙古族自治县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //青海省--海南藏族自治州****************************************
            case "25":{
                cityName="海南藏族自治州";
                switch (countyCode){
                    case "21":{countyName="共和县";}break;
                    case "22":{countyName="同德县";}break;
                    case "23":{countyName="贵德县";}break;
                    case "24":{countyName="兴海县";}break;
                    case "25":{countyName="贵南县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //青海省--果洛藏族自治州****************************************
            case "26":{
                cityName="果洛藏族自治州";
                switch (countyCode){
                    case "21":{countyName="玛沁县";}break;
                    case "22":{countyName="班玛县";}break;
                    case "23":{countyName="甘德县";}break;
                    case "24":{countyName="达日县";}break;
                    case "25":{countyName="久治县";}break;
                    case "26":{countyName="玛多县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //青海省--玉树藏族自治州****************************************
            case "27":{
                cityName="玉树藏族自治州";
                switch (countyCode){
                    case "21":{countyName="玉树县";}break;
                    case "22":{countyName="杂多县";}break;
                    case "23":{countyName="称多县";}break;
                    case "24":{countyName="治多县";}break;
                    case "25":{countyName="囊谦县";}break;
                    case "26":{countyName="曲麻莱县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //青海省--海西蒙古族藏族自治州**********************************
            case "28":{
                cityName="海西蒙古族藏族自治州";
                switch (countyCode){
                    case "01":{countyName="格尔木市";}break;
                    case "02":{countyName="德令哈市";}break;
                    case "21":{countyName="乌兰县";}break;
                    case "22":{countyName="都兰县";}break;
                    case "23":{countyName="天峻县";}break;
                    case "24":{countyName="大柴旦行委";}break;
                    case "25":{countyName="冷湖行委";}break;
                    case "26":{countyName="茫崖行委";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //**************************************************************
            default:{
                cityName="(身份证号码3-4位有误)";
                countyName="--";
            }break;
        }
        return provinceName+"/"+cityName+"/"+countyName;
    }

    /**
     *@Author:ShiYun;
     *@Description:获得宁夏回族自治区的方法
     *@Date: 9:52 2018\9\28 0028
     */
    private static String getProvinceNameByProvinceCode64(String cityCode,String countyCode){
        String provinceName = "宁夏回族自治区";
        String cityName = "";
        String countyName = "";
        switch (cityCode){
            //宁夏回族自治区--银川市**********************************************
            case "01":{
                cityName="银川市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="城区";}break;
                    case "03":{countyName="新城区";}break;
                    case "04":{countyName="兴庆区";}break;
                    case "05":{countyName="西夏区";}break;
                    case "06":{countyName="金凤区";}break;
                    case "11":{countyName="郊区";}break;
                    case "21":{countyName="永宁县";}break;
                    case "22":{countyName="贺兰县";}break;
                    case "81":{countyName="灵武市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //宁夏回族自治区--石嘴山市**********************************************
            case "02":{
                cityName="石嘴山市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="大武口区";}break;
                    case "03":{countyName="石嘴山区";}break;
                    case "04":{countyName="石炭井区";}break;
                    case "05":{countyName="惠农区";}break;
                    case "11":{countyName="郊区";}break;
                    case "20":{countyName="市区";}break;
                    case "21":{countyName="平罗县";}break;
                    case "22":{countyName="陶乐县";}break;
                    case "23":{countyName="惠农县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //宁夏回族自治区--吴忠市**********************************************
            case "03":{
                cityName="吴忠市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="利通区";}break;
                    case "21":{countyName="中卫县";}break;
                    case "22":{countyName="中宁县";}break;
                    case "23":{countyName="盐池县";}break;
                    case "24":{countyName="同心县";}break;
                    case "81":{countyName="青铜峡市";}break;
                    case "82":{countyName="灵武市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //宁夏回族自治区--固原市**********************************************
            case "04":{
                cityName="固原市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="原州区";}break;
                    case "21":{countyName="海原县";}break;
                    case "22":{countyName="西吉县";}break;
                    case "23":{countyName="隆德县";}break;
                    case "24":{countyName="泾源县";}break;
                    case "25":{countyName="彭阳县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //宁夏回族自治区--中卫市**********************************************
            case "05":{
                cityName="中卫市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="沙坡头区";}break;
                    case "21":{countyName="中宁县";}break;
                    case "22":{countyName="海原县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //宁夏回族自治区--吴忠市**********************************************
            case "21":{
                cityName="吴忠市";
                switch (countyCode){
                    case "01":{countyName="吴忠市";}break;
                    case "02":{countyName="青铜峡市";}break;
                    case "03":{countyName="灵武市";}break;
                    case "21":{countyName="吴忠县";}break;
                    case "22":{countyName="青铜峡县";}break;
                    case "23":{countyName="中卫县";}break;
                    case "24":{countyName="中宁县";}break;
                    case "25":{countyName="灵武县";}break;
                    case "26":{countyName="盐池县";}break;
                    case "27":{countyName="同心县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //宁夏回族自治区--固原地区**********************************************
            case "22":{
                cityName="固原地区";
                switch (countyCode){
                    case "21":{countyName="固原县";}break;
                    case "22":{countyName="海原县";}break;
                    case "23":{countyName="西吉县";}break;
                    case "24":{countyName="隆德县";}break;
                    case "25":{countyName="泾源县";}break;
                    case "26":{countyName="彭阳县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //**************************************************************
            default:{
                cityName="(身份证号码3-4位有误)";
                countyName="--";
            }break;
        }
        return provinceName+"/"+cityName+"/"+countyName;
    }

    /**
     *@Author:ShiYun;
     *@Description:获得新疆维吾尔自治区的方法
     *@Date: 10:19 2018\9\28 0028
     */
    private static String getProvinceNameByProvinceCode65(String cityCode,String countyCode){
        String provinceName = "新疆维吾尔自治区";
        String cityName = "";
        String countyName = "";
        switch (cityCode){
            //新疆维吾尔自治区--乌鲁木齐市*************************
            case "01":{
                cityName="乌鲁木齐市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="天山区";}break;
                    case "03":{countyName="沙依巴克区";}break;
                    case "04":{countyName="新市区";}break;
                    case "05":{countyName="水磨沟区";}break;
                    case "06":{countyName="头屯河区";}break;
                    case "07":{countyName="南山矿区";}break;
                    case "08":{countyName="东山矿区";}break;
                    case "21":{countyName="乌鲁木齐县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //新疆维吾尔自治区--克拉玛依市*************************
            case "02":{
                cityName="克拉玛依市";
                switch (countyCode){
                    case "01":{countyName="市辖区";}break;
                    case "02":{countyName="独山子区";}break;
                    case "03":{countyName="克拉玛依区";}break;
                    case "04":{countyName="白碱滩区";}break;
                    case "05":{countyName="乌尔禾区";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //新疆维吾尔自治区--吐鲁番地区*************************
            case "21":{
                cityName="吐鲁番地区";
                switch (countyCode){
                    case "01":{countyName="吐鲁番市";}break;
                    case "22":{countyName="鄯善县";}break;
                    case "23":{countyName="托克逊县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //新疆维吾尔自治区--哈密地区***************************
            case "22":{
                cityName="哈密地区";
                switch (countyCode){
                    case "01":{countyName="哈密市";}break;
                    case "22":{countyName="巴里坤哈萨克自治县";}break;
                    case "23":{countyName="伊吾县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //新疆维吾尔自治区--昌吉回族自治州*********************
            case "23":{
                cityName="昌吉回族自治州";
                switch (countyCode){
                    case "01":{countyName="昌吉市";}break;
                    case "02":{countyName="阜康市";}break;
                    case "03":{countyName="米泉市";}break;
                    case "22":{countyName="米泉县";}break;
                    case "23":{countyName="呼图壁县";}break;
                    case "24":{countyName="玛纳斯县";}break;
                    case "25":{countyName="奇台县";}break;
                    case "26":{countyName="阜康市";}break;
                    case "27":{countyName="吉木萨尔县";}break;
                    case "28":{countyName="木垒哈萨克自治县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //新疆维吾尔自治区--伊犁哈萨克自治州*******************
            case "24":{
                cityName="伊犁哈萨克自治州";
                switch (countyCode){
                    case "01":{countyName="伊宁市";}break;
                    case "02":{countyName="奎屯市";}break;
                    case "21":{countyName="伊宁县";}break;
                    case "22":{countyName="察布查尔锡伯自治县";}break;
                    case "23":{countyName="霍城县";}break;
                    case "24":{countyName="巩留县";}break;
                    case "25":{countyName="新源县";}break;
                    case "26":{countyName="昭苏县";}break;
                    case "27":{countyName="特克斯县";}break;
                    case "28":{countyName="尼勒克县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //新疆维吾尔自治区--塔城地区***************************
            case "25":{
                cityName="塔城地区";
                switch (countyCode){
                    case "21":{countyName="塔城县";}break;
                    case "22":{countyName="额敏县";}break;
                    case "23":{countyName="乌苏县";}break;
                    case "24":{countyName="沙湾县";}break;
                    case "25":{countyName="托里县";}break;
                    case "26":{countyName="裕民县";}break;
                    case "27":{countyName="和布克赛尔蒙古自治县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //新疆维吾尔自治区--阿勒泰地区*************************
            case "26":{
                cityName="阿勒泰地区";
                switch (countyCode){
                    case "21":{countyName="阿勒泰县";}break;
                    case "22":{countyName="布尔津县";}break;
                    case "23":{countyName="富蕴县";}break;
                    case "24":{countyName="福海县";}break;
                    case "25":{countyName="哈巴河县";}break;
                    case "26":{countyName="青河县";}break;
                    case "27":{countyName="吉木乃县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //新疆维吾尔自治区--博尔塔拉蒙古自治州*****************
            case "27":{
                cityName="博尔塔拉蒙古自治州";
                switch (countyCode){
                    case "01":{countyName="博乐市";}break;
                    case "21":{countyName="博乐县";}break;
                    case "22":{countyName="精河县";}break;
                    case "23":{countyName="温泉县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //新疆维吾尔自治区--巴音郭楞蒙古自治州*****************
            case "28":{
                cityName="巴音郭楞蒙古自治州";
                switch (countyCode){
                    case "01":{countyName="库尔勒市";}break;
                    case "21":{countyName="库尔勒县";}break;
                    case "22":{countyName="轮台县";}break;
                    case "23":{countyName="尉犁县";}break;
                    case "24":{countyName="若羌县";}break;
                    case "25":{countyName="且末县";}break;
                    case "26":{countyName="焉耆回族自治县";}break;
                    case "27":{countyName="和静县";}break;
                    case "28":{countyName="和硕县";}break;
                    case "29":{countyName="博湖县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //新疆维吾尔自治区--阿克苏地区*************************
            case "29":{
                cityName="阿克苏地区";
                switch (countyCode){
                    case "01":{countyName="阿克苏市";}break;
                    case "21":{countyName="阿克苏县";}break;
                    case "22":{countyName="温宿县";}break;
                    case "23":{countyName="库车县";}break;
                    case "24":{countyName="沙雅县";}break;
                    case "25":{countyName="新和县";}break;
                    case "26":{countyName="拜城县";}break;
                    case "27":{countyName="乌什县";}break;
                    case "28":{countyName="阿瓦提县";}break;
                    case "29":{countyName="柯坪县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //新疆维吾尔自治区--克孜勒苏柯尔克孜自治州*************
            case "30":{
                cityName="克孜勒苏柯尔克孜自治州";
                switch (countyCode){
                    case "01":{countyName="阿图什市";}break;
                    case "21":{countyName="阿图什县";}break;
                    case "22":{countyName="阿克陶县";}break;
                    case "23":{countyName="阿合奇县";}break;
                    case "24":{countyName="乌恰县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //新疆维吾尔自治区--喀什地区***************************
            case "31":{
                cityName="喀什地区";
                switch (countyCode){
                    case "01":{countyName="喀什市";}break;
                    case "21":{countyName="疏附县";}break;
                    case "22":{countyName="疏勒县";}break;
                    case "23":{countyName="英吉沙县";}break;
                    case "24":{countyName="泽普县";}break;
                    case "25":{countyName="莎车县";}break;
                    case "26":{countyName="叶城县";}break;
                    case "27":{countyName="麦盖提县";}break;
                    case "28":{countyName="岳普湖县";}break;
                    case "29":{countyName="伽师县";}break;
                    case "30":{countyName="巴楚县";}break;
                    case "31":{countyName="塔什库尔干塔吉克自治县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //新疆维吾尔自治区--和田地区***************************
            case "32":{
                cityName="和田地区";
                switch (countyCode){
                    case "01":{countyName="和田市";}break;
                    case "21":{countyName="和田县";}break;
                    case "22":{countyName="墨玉县";}break;
                    case "23":{countyName="皮山县";}break;
                    case "24":{countyName="洛浦县";}break;
                    case "25":{countyName="策勒县";}break;
                    case "26":{countyName="于田县";}break;
                    case "27":{countyName="民丰县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //新疆维吾尔自治区--伊犁哈萨克自治州*******************
            case "40":{
                cityName="伊犁哈萨克自治州";
                switch (countyCode){
                    case "01":{countyName="奎屯市";}break;
                    case "02":{countyName="伊宁市";}break;
                    case "03":{countyName="奎屯市";}break;
                    case "21":{countyName="伊宁县";}break;
                    case "22":{countyName="察布查尔锡伯自治县";}break;
                    case "23":{countyName="霍城县";}break;
                    case "24":{countyName="巩留县";}break;
                    case "25":{countyName="新源县";}break;
                    case "26":{countyName="昭苏县";}break;
                    case "27":{countyName="特克斯县";}break;
                    case "28":{countyName="尼勒克县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //新疆维吾尔自治区--伊犁地区***************************
            case "41":{
                cityName="伊犁地区";
                switch (countyCode){
                    case "01":{countyName="伊宁市";}break;
                    case "21":{countyName="伊宁县";}break;
                    case "22":{countyName="察布查尔锡伯自治县";}break;
                    case "23":{countyName="霍城县";}break;
                    case "24":{countyName="巩留县";}break;
                    case "25":{countyName="新源县";}break;
                    case "26":{countyName="昭苏县";}break;
                    case "27":{countyName="特克斯县";}break;
                    case "28":{countyName="尼勒克县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //新疆维吾尔自治区--塔城地区***************************
            case "42":{
                cityName="塔城地区";
                switch (countyCode){
                    case "01":{countyName="塔城市";}break;
                    case "02":{countyName="乌苏市";}break;
                    case "21":{countyName="额敏县";}break;
                    case "22":{countyName="乌苏县";}break;
                    case "23":{countyName="沙湾县";}break;
                    case "24":{countyName="托里县";}break;
                    case "25":{countyName="裕民县";}break;
                    case "26":{countyName="和布克赛尔蒙古自治县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //新疆维吾尔自治区--阿勒泰地区*************************
            case "43":{
                cityName="阿勒泰地区";
                switch (countyCode){
                    case "01":{countyName="阿勒泰市";}break;
                    case "21":{countyName="布尔津县";}break;
                    case "22":{countyName="富蕴县";}break;
                    case "23":{countyName="福海县";}break;
                    case "24":{countyName="哈巴河县";}break;
                    case "25":{countyName="青河县";}break;
                    case "26":{countyName="吉木乃县";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //新疆维吾尔自治区--省直辖行政单位*********************
            case "90":{
                cityName="省直辖行政单位";
                switch (countyCode){
                    case "01":{countyName="石河子市";}break;
                    case "02":{countyName="阿拉尔市";}break;
                    case "03":{countyName="图木舒克市";}break;
                    case "04":{countyName="五家渠市";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //*****************************************************
            default:{
                cityName="(身份证号码3-4位有误)";
                countyName="--";
            }break;
        }
        return provinceName+"/"+cityName+"/"+countyName;
    }

    /**
     *@Author:ShiYun;
     *@Description:获得香港特别行政区的方法
     *@Date: 11:57 2018\9\28 0028
     */
    private static String getProvinceNameByProvinceCode81(String cityCode,String countyCode){
        String provinceName = "香港特别行政区";
        String cityName = "";
        String countyName = "";
        switch (cityCode){
            //台湾省--*************************
            case "00":{
                cityName="--";
                switch (countyCode){
                    case "00":{countyName="--";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //*****************************************************
            default:{
                cityName="(身份证号码3-4位有误)";
                countyName="--";
            }break;
        }
        return provinceName+"/"+cityName+"/"+countyName;
    }

    /**
     *@Author:ShiYun;
     *@Description:获得澳门特别行政区的方法
     *@Date: 11:57 2018\9\28 0028
     */
    private static String getProvinceNameByProvinceCode82(String cityCode,String countyCode){
        String provinceName = "澳门特别行政区";
        String cityName = "";
        String countyName = "";
        switch (cityCode){
            //台湾省--*************************
            case "00":{
                cityName="--";
                switch (countyCode){
                    case "00":{countyName="--";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //*****************************************************
            default:{
                cityName="(身份证号码3-4位有误)";
                countyName="--";
            }break;
        }
        return provinceName+"/"+cityName+"/"+countyName;
    }

    /**
     *@Author:ShiYun;
     *@Description:获得台湾省的方法（有两个710000和830000）
     *@Date: 11:29 2018\9\28 0028
     */
    private static String getProvinceNameByProvinceCode83(String cityCode,String countyCode){
        String provinceName = "台湾省";
        String cityName = "";
        String countyName = "";
        switch (cityCode){
            //台湾省--*************************
            case "00":{
                cityName="--";
                switch (countyCode){
                    case "00":{countyName="--";}break;
                    default:countyName="身份证号码5-6位有误";break;
                }
            }break;
            //*****************************************************
            default:{
                cityName="(身份证号码3-4位有误)";
                countyName="--";
            }break;
        }
        return provinceName+"/"+cityName+"/"+countyName;
    }

}
