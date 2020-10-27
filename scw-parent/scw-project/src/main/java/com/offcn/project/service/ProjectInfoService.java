package com.offcn.project.service;

import com.offcn.project.pojo.*;


import java.util.List;

/**
 * @author 胡长生
 * @create 2020-10-26 15:20
 */
public interface ProjectInfoService {
    /**
     * 获取项目回报列表
     */
    public List<TReturn> getReturnList(Integer projectId);

    /**
     *获取所有项目
     */
    public List<TProject> findAllProject();

    /**
     * 获取项目图片
     */
    public List<TProjectImages> getProjectImages(Integer id);

    /**
     * 获取项目详情
     */
    public TProject findProjectInfo(Integer projectId);

    /**
     * 获取项目标签
     */
    public List<TTag> findAllTag();

    /**
     * 获取项目分类
     */
    public List<TType> getProjectTypes();

    /**
     * 获取回报信息
     */
    public TReturn getReturnInfo(Integer returnId);
}
