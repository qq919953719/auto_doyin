package com.mcz.douyin.util;

import com.mcz.douyin.bean.AutoDataBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chuangcui  @time 2024/4/20 10:38 邮箱:919953719@qq.com
 * @name auto_mcz
 * @class name：com.mcz.douyin.util *
 * @class describe *
 */

public class DataUtils {
    private static DataUtils instance;
    private List<AutoDataBean> dataDTOList = new ArrayList<>();

    /**
     * // 私有化构造函数，防止外部直接创建实例
     */
    private DataUtils() {
    }

    /**
     * 提供一个静态方法，用于获取实例
     */
    public static synchronized DataUtils getInstance() {
        if (instance == null) {
            instance = new DataUtils();
        }
        return instance;
    }

    public List<AutoDataBean> getDataDTOList() {
        return dataDTOList;
    }


}
