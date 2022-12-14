package com.youlianmei.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.youlianmei.dao.GradeQuestionDao;
import com.youlianmei.model.GradeDict;
import com.youlianmei.model.GradeQuestion;

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



    /**
     * 判断是否为空
     * 空为true 否为false
     * **/
    public static Boolean isEmpty(List list){
        if(null==list || list.size()==0){
            return true;
        }
        return false;
    }

    public static void gradeQuestionWhere(QueryWrapper<GradeQuestion> wrapper, GradeQuestionDao dao){
        //多条件组合查询
        if(StringUtils.isNotEmpty(dao.getId())){
            wrapper.eq("id",dao.getId());
        }
        if(StringUtils.isNotEmpty(dao.getDictTypePath())){
            wrapper.like("dict_type_path",dao.getDictTypePath());
        }
        if(StringUtils.isNotEmpty(dao.getDictSourcePath())){
            wrapper.like("dict_source_path",dao.getDictSourcePath());
        }
        if(StringUtils.isNotEmpty(dao.getDictGradePath())){
            wrapper.like("dict_grade_path",dao.getDictGradePath());
        }
        if(StringUtils.isNotEmpty(dao.getQuestion())){
            wrapper.like("question",dao.getQuestion());
        }
        if (StringUtils.isNotEmpty(dao.getAnswers())){
            wrapper.like("answers",dao.getAnswers());
        }
        if (StringUtils.isNotEmpty(dao.getAnswerRight())){
            wrapper.like("answerRight",dao.getAnswerRight());
        }
        if (null!=dao.getCreateTimeStart()){
            //大于等于
            wrapper.ge("create_time",dao.getCreateTimeStart());
        }
        if (null!=dao.getCreateTimeEnd()){
            dao.setCreateTimeEnd(DateUtils.dateAddOneDay(dao.getCreateTimeEnd()));
            //小于
            wrapper.lt("create_time", dao.getCreateTimeEnd());
        }
        if (null!=dao.getUpdateTimeStart()){
            //大于等于
            wrapper.ge("update_time",dao.getUpdateTimeStart());
        }
        if (null!=dao.getUpdateTimeEnd()){
            dao.setUpdateTimeEnd(DateUtils.dateAddOneDay(dao.getUpdateTimeEnd()));
            //小于
            wrapper.lt("update_time",dao.getUpdateTimeEnd());
        }
        if (!StringUtils.isEmpty(dao.getRemarks())){
            wrapper.like("remarks",dao.getRemarks());
        }
    }

}