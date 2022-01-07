package com.dt.platform.job.contract;

import com.alibaba.fastjson.JSONObject;
import com.github.foxnic.api.error.ErrorDesc;
import com.github.foxnic.api.transter.Result;
import com.github.foxnic.commons.log.Logger;
import org.github.foxnic.web.domain.job.Job;
import org.github.foxnic.web.domain.job.JobExecutor;
import org.springframework.stereotype.Component;

@Component
public class ContractJobExecutor implements JobExecutor {
    @Override
    public String getName() {
        return "ContractJobExecutor";
    }

    @Override
    public Result execute(Object o, Job job, JSONObject jsonObject) {
        Logger.info("ContractJobExecutor 执行 Job ");
        return ErrorDesc.success();
    }
}
