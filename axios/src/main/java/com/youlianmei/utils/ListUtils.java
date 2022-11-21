package com.youlianmei.utils;

import com.youlianmei.model.GradeDict;

import java.util.ArrayList;
import java.util.List;

public class ListUtils {

    /**
     *返回树结构
     * @param list
     * @return
     */
    public static List<GradeDict> gradeDictTree(List<GradeDict> list) {

        List<GradeDict> result = new ArrayList<>();
        // 第一步遍历获取到的数据，将根节点数据存放 result 里
        for (GradeDict gradeDict: list) {
            if ( gradeDict.getParentId().intValue()==0 ) {
                result.add(gradeDict);
            }
        }
        // 根节点添加完就需要添加每个节点的子节点了，这里需要调用 递归方法 getChildren();
        // 遍历根节点数据，为其设置子节点集合
        for (GradeDict gradeDict: result) {
            // 获取子节点数据，传入 当前节点 id 和 所有 list
            List<GradeDict> childList = getChildren(gradeDict.getId(), list);
            // 将获取到的子节点集合添加到根节点里
            gradeDict.setChildren(childList);
        }
        // 将一层一层的树结构数据返回吧！
        return result;
    }

    /**
     * 获取子节点数据
     * @param id 父节点 ID
     * @param List<GradeDict> list 所有节点集合
     * @return 返回子节点列表
     */
    public static List<GradeDict> getChildren(Integer id, List<GradeDict> list) {
        // 存在子节点数据
        List<GradeDict> childList = new ArrayList<GradeDict>();
        // 遍历所有节点数据
        for (GradeDict item : list) {
            // 如果当前节点 ID 与父节点 ID 一致，表示当前数据是该节点的子节点
            if ( null!=item.getParentId() && item.getParentId().intValue()==id) {
                childList.add(item);
            }
        }
        // 重点来了，递归调用
        for (GradeDict item : childList) {
            // 调用自身方法，依次添加子节点数据
            item.setChildren(getChildren(item.getId(), list));
        }
        // 如果当前节点无子节点数据添加空数据，递归退出
        if (childList.size() == 0) {
            return new ArrayList<>();
        }
        // 返回最终的子节点数据
        return childList;
    }

}