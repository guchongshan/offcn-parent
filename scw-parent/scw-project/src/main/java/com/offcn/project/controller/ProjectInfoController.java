package com.offcn.project.controller;

import com.offcn.project.pojo.*;
import com.offcn.project.service.ProjectInfoService;
import com.offcn.project.vo.response.ProjectDetailVo;
import com.offcn.project.vo.response.ProjectVo;
import com.offcn.response.AppResponse;
import com.offcn.util.OssTemplate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 胡长生
 * @create 2020-10-24 17:02
 */
@Api(tags = "项目基本功能模块（文件上传、项目信息获取等）")
@Slf4j
@RequestMapping("/project")
@RestController
public class ProjectInfoController {

    @Autowired
    private OssTemplate ossTemplate;

    @Autowired
    private ProjectInfoService projectInfoService;

    @ApiOperation("文件上传功能")
    @PostMapping("/upload")
    public AppResponse<Map<String, Object>> upload(@RequestParam("file") MultipartFile[] files) throws IOException {
        Map<String, Object> map = new HashMap<>();
        List<String> list = new ArrayList<>();
        if (files != null && files.length > 0) {
            for (MultipartFile item : files) {
                if (!item.isEmpty()) {
                    String upload = ossTemplate.upload(item.getInputStream(), item.getOriginalFilename());
                    list.add(upload);
                }
            }
        }
        map.put("urls", list);
        log.debug("ossTemplate信息：{},文件上传成功访问路径{}", ossTemplate, list);
        return AppResponse.ok(map);
    }

    @ApiOperation("获取项目回报列表")
    @GetMapping("/details/returns/{projectId}")
    public AppResponse<List<TReturn>> detailsReturn(@PathVariable("projectId") Integer projectId) {

        List<TReturn> returns = projectInfoService.getReturnList(projectId);
        return AppResponse.ok(returns);
    }

    @ApiOperation("获取系统所有的项目")
    @GetMapping("/all")
    public AppResponse<List<ProjectVo>> getAllProject() {
        //1.创建集合储存全部项目的vo
        List<ProjectVo> vo = new ArrayList<>();
        //2.查询所有项目
        List<TProject> allProject = projectInfoService.findAllProject();
        //3.遍历项目集合
        for (TProject project : allProject) {
            //获取项目编号
            Integer id = project.getId();
            //根据项目编号获取项目配图集合
            List<TProjectImages> projectImages = projectInfoService.getProjectImages(id);
            ProjectVo projectVo = new ProjectVo();
            BeanUtils.copyProperties(project, projectVo);
            //遍历图片集合
            for (TProjectImages projectImage : projectImages) {
                //如果图片类型是头部图片，则设置头部图片路径到项目vo
                if (projectImage.getImgtype() == 0) {
                    projectVo.setHeaderImage(projectImage.getImgurl());
                }
            }
            //把项目vo添加到项目vo集合上
            vo.add(projectVo);
        }
        return AppResponse.ok(vo);
    }

    @ApiOperation("获取项目信息详情")
    @GetMapping("/details/info/{projectId}")
    public AppResponse<ProjectDetailVo> deatailsInfo(@PathVariable("projectId") Integer projectId) {
        TProject projectInfo = projectInfoService.findProjectInfo(projectId);
        ProjectDetailVo projectVo = new ProjectDetailVo();
        //1.查出这个项目的所有图片
        List<TProjectImages> projectImages = projectInfoService.getProjectImages(projectInfo.getId());
        List<String> detailsImage = projectVo.getDetailsImage();
        if (detailsImage == null) {
            detailsImage = new ArrayList<>();
        }
        for (TProjectImages projectImage : projectImages) {
            if (projectImage.getImgtype() == 0) {
                projectVo.setHeaderImage(projectImage.getImgurl());
            } else {
                detailsImage.add(projectImage.getImgurl());
            }
        }
        projectVo.setDetailsImage(detailsImage);

        //2.获取项目的所有支持回报
        List<TReturn> returnList = projectInfoService.getReturnList(projectInfo.getId());
        projectVo.setProjectReturns(returnList);
        BeanUtils.copyProperties(projectInfo, projectVo);
        return AppResponse.ok(projectVo);
    }

    @ApiOperation("获取系统所有的项目标签")
    @GetMapping("/tags")
    public AppResponse<List<TTag>> tags() {
        List<TTag> allTag = projectInfoService.findAllTag();
        return AppResponse.ok(allTag);
    }

    @ApiOperation("获取系统所有的项目分类")
    @GetMapping("/types")
    public AppResponse<List<TType>> types() {
        List<TType> projectTypes = projectInfoService.getProjectTypes();
        return AppResponse.ok(projectTypes);
    }

    @ApiOperation("获取项目详细的回报信息")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "回报id",name = "returnId",required = true)
    })
    @GetMapping("/returns/info/{returnId}")
    public AppResponse<TReturn> getTReturn(@PathVariable("returnId")Integer returnId){
        TReturn returnInfo = projectInfoService.getReturnInfo(returnId);
        return AppResponse.ok(returnInfo);
    }
}

