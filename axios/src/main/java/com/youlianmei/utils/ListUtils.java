package com.youlianmei.utils;

import com.youlianmei.model.GradeDict;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListUtils {


    /**
     *返回树结构
     * @param list
     * @return
     */
    public static List<GradeDict> gradeDictTree(List<GradeDict> list){
        //获取最高层类
        Integer parentId = getMinParentId(list);
        //开始处理数据
        List<GradeDict> tree=new ArrayList<GradeDict>();
        for(GradeDict node:list){
            if(Objects.equals(node.getParentId(), parentId)){
                tree.add(findChild(node, list));
            }
        }
        return tree;
    }
    /**
     * 获取子节点数据
     * @param gradeDict
     * @param list 所有节点集合
     * @return 返回子节点列表
     */
    private static GradeDict findChild(GradeDict gradeDict, List<GradeDict> list){
        for(GradeDict n:list){
            if(Objects.equals(n.getParentId(), gradeDict.getId())){
                gradeDictChildren(gradeDict);
                gradeDict.getChildren().add(findChild(n,list));
            }
        }
        gradeDictChildren(gradeDict);
        return gradeDict;
    }

    /**
     * gradeDict赋值
     * **/
    private static void gradeDictChildren(GradeDict gradeDict){
        if(null==gradeDict.getChildren()){
            gradeDict.setChildren(new ArrayList<GradeDict>());
        }
    }
    /**
     * 获取parentId最小值
     * @param list 所有节点集合
     * @return
     */
    private static Integer getMinParentId(List<GradeDict> list){
        int minValue = -1;
        for(GradeDict gradeDict : list){
            minValue = minValue==-1 ? gradeDict.getParentId() : ( minValue > gradeDict.getParentId() ? gradeDict.getParentId() : minValue );
        }
        return minValue;
    }

}