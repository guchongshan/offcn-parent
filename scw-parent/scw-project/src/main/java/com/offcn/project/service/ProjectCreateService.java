package com.offcn.project.service;

import com.offcn.enums.ProjectStatusEnum;
import com.offcn.project.vo.req.ProjectRedisStorageVo;

/**
 * @author 胡长生
 * @create 2020-10-24 20:15
 */
public interface ProjectCreateService {

    /**
     * 初始化项目
     */
    public String initCreateProject(Integer memberId);

    /**
     * 保存项目信息
     * @param auth  项目状态信息
     * @param project  项目全部信息
     */
    public void saveProjectInfo(ProjectStatusEnum auth, ProjectRedisStorageVo project);

}


