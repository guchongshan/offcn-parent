package com.offcn.order.service.impl;

import com.offcn.order.pojo.TReturn;
import com.offcn.order.service.ProjectServiceFeign;
import com.offcn.response.AppResponse;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 胡长生
 * @create 2020-10-26 15:41
 */
@Service
public class ProjectServiceFeignImpl implements ProjectServiceFeign {



    @Override
    public AppResponse<List<TReturn>> getReturnList(Integer projectId) {
        AppResponse<List<TReturn>> fail = AppResponse.fail(null);
        fail.setMsg("调用远程服务器失败[订单]");
        return fail;
    }
}
