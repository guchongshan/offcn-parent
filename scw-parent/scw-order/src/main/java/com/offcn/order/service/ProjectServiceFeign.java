package com.offcn.order.service;

import com.offcn.order.pojo.TReturn;
import com.offcn.order.service.impl.ProjectServiceFeignImpl;
import com.offcn.response.AppResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author 胡长生
 * @create 2020-10-26 15:35
 */
@FeignClient(value = "SCW-PROJECT",fallback = ProjectServiceFeignImpl.class)
public interface ProjectServiceFeign {

    @GetMapping("/project/details/returns/{projectId}")
    public AppResponse<List<TReturn>> getReturnList(@PathVariable("projectId") Integer projectId);
}
