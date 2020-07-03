package com.guixiang.zhanjun.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;

public class WebUtils {


    /**
     * 将map中的键值对注入到bean中
     *
     * @param value map
     * @param bean  注入的对象
     */
    public static <T> T copyPararmToBean(Map value, T bean) {
        try {
            /*把所有的form表单信息注入userTest*/
            BeanUtils.populate(bean, value);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bean;
    }

    /**
     * 将字符串转成int
     * @param strInt
     * @param defaultValue
     * @return
     */
    public static int parseInt(String strInt, int defaultValue) {
        try {
            return Integer.parseInt(strInt);
        } catch (NumberFormatException e) {
            System.out.println("使用默认值");
        }
        return defaultValue;
    }


}
