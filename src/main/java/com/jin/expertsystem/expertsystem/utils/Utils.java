package com.jin.expertsystem.expertsystem.utils;

import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

/**
 * @author GaoLiwei
 * @date 2019/4/18
 */
public class Utils {
    /**
     * 获得UUID
     * @return String
     */
    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    /**
     * 根据list集合中的类的某个属性将这个list集合进行分类
     *      list要进行分类的集合
     *      name属性名
     *      （因为需要实现排序，所以改为LinkedHashMap）
     * @author GaoLiWei
     * @date 14:47 2019/4/1
     **/
    public static LinkedHashMap<Object, List> groupListByName(List list, String name) {

        LinkedHashMap<Object, List> resultMap = new LinkedHashMap<>();
        if (list.size() > 0) {
            try {
                for (int i = 0; i < list.size(); i++) {
                    Class<?> aClass = list.get(0).getClass();
                    Field[] fields = FieldUtils.getAllFields(aClass);
                    Type type = null;
                    Method method = null;
                    for (int f = 0; f < fields.length; f++) {
                        if (name.equals(fields[f].getName())) {
                            type = fields[f].getType();
                            break;
                        }
                    }
                    //获得此类的get方法
                    String methodName = "get" + name.substring(0, 1).toUpperCase() + name.substring(1);
                    method = aClass.getMethod(methodName);
                    Object key = method.invoke(list.get(i));
                    //如果map中已经存在了key，则直接将这个数据归到这个分组下
                    if (resultMap.containsKey(key)) {
                        resultMap.get(key).add(list.get(i));
                    } else {//如果这个设备ID不再map的key中，则说明没有这个设备id的分组，新建一个分组，将数据放进去
                        List arrayList = new ArrayList<>();
                        arrayList.add(list.get(i));
                        resultMap.put(key, arrayList);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return resultMap;
    }
}
