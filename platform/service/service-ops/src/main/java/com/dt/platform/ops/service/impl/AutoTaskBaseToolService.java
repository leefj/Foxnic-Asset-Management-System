package com.dt.platform.ops.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dt.platform.constants.enums.common.StatusEnableEnum;
import com.dt.platform.constants.enums.ops.OpsAutoActionNodeNumberTypeEnum;
import com.dt.platform.constants.enums.ops.OpsAutoExecuteTypeEnum;
import com.dt.platform.constants.enums.ops.OpsAutoTaskResultStatusEnum;
import com.dt.platform.constants.enums.ops.OpsAutoTaskRunStatusEnum;
import com.dt.platform.domain.ops.*;
import com.dt.platform.domain.ops.meta.AutoNodeMeta;
import com.dt.platform.domain.ops.meta.AutoTaskMeta;
import com.dt.platform.ops.service.*;
import com.dt.platform.ops.service.impl.ops.Machine;
import com.dt.platform.ops.service.impl.ops.SftpClient;
import com.github.foxnic.api.error.ErrorDesc;
import com.github.foxnic.api.transter.Result;
import com.github.foxnic.commons.busi.id.IDGenerator;
import com.github.foxnic.commons.lang.StringUtil;
import com.github.foxnic.commons.log.Logger;
import com.github.foxnic.commons.environment.OSType;
import com.github.foxnic.dao.data.Rcd;
import com.github.foxnic.dao.data.RcdSet;
import com.github.foxnic.dao.data.SaveMode;
import com.github.foxnic.dao.spec.DAO;
import com.github.foxnic.sql.expr.ConditionExpr;
import org.github.foxnic.web.framework.dao.DBConfigs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.io.*;
import java.util.*;


/*
[#IP#]

* */

@Service("AutoTaskBaseToolService")
public class AutoTaskBaseToolService implements IAutoTaskToolService{

    @Value("${foxnic.storage.disk.location.windows}")
    private String windowsDir="";

    @Value("${foxnic.storage.disk.location.mac}")
    private String macDir="";

    @Value("${foxnic.storage.disk.location.linux}")
    private String linuxDir="";

    public static String METHOD_VIEW="view";

    public static String METHOD_CHECK="check";

    public static String METHOD_EXECUTE="execute";

    private String getStorageDir() {
        if(OSType.isWindows()) return windowsDir;
        else if(OSType.isLinux()) return linuxDir;
        else if(OSType.isMac()) return macDir;
        else {
            throw new RuntimeException("????????????????????????");
        }
    }

    @Autowired
    private IAutoTaskMLogService autoTaskMLogService;


    @Autowired
    private IAutoTaskLogService autoTaskLogService;

    @Autowired
    private IAutoTaskService autoTaskService;


    @Autowired
    private IAutoNodeService autoNodeService;


    /**
     * ??????DAO??????
     * */
    @Resource(name= DBConfigs.PRIMARY_DAO)
    private DAO dao=null;

    /**
     * ?????? DAO ??????
     * */
    public DAO dao() { return dao; }

    public Result recordTaskLogFailed(AutoTaskLog log, String message){
        log.setStatus(OpsAutoTaskResultStatusEnum.FAILED.code());
        log.setRecordContent(message);
        log.setEtime(new Date());
        autoTaskLogService.update(log,SaveMode.NOT_NULL_FIELDS);
        return ErrorDesc.success();
    }

    public Result recordTaskMLogFailed(AutoTaskMLog log, String message){

        log.setStatus(OpsAutoTaskResultStatusEnum.FAILED.code());
        log.setContent(message);
        log.setEtime(new Date());
        autoTaskMLogService.update(log,SaveMode.NOT_NULL_FIELDS);
        return ErrorDesc.success();
    }


    @Override
    public Result executeNode(AutoNode node, AutoAction action,String taskId, String mLogId,String method) {


        AutoTaskLog log=new AutoTaskLog();
        log.setActionId(action.getId());
        log.setStatus(OpsAutoTaskResultStatusEnum.ACTING.code());
        log.setStime(new Date());
        log.setTaskId(taskId);
        log.setMLogId(mLogId);
        log.setNodeIp(node.getIp());
        log.setRecordContent("????????????");
        log.setContentDetail("");
        log.setNodeId(node.getId());
        log.setRecordTime(new Date());
        autoTaskLogService.insert(log);


        if(OpsAutoExecuteTypeEnum.OPS_TOOLS.code().equals(action.getExecuteTool())){
            Logger.info("????????????:"+OpsAutoExecuteTypeEnum.OPS_TOOLS.text());
        }else{
            String msg="?????????????????????:"+action.getExecuteTool()+",?????????";
            recordTaskLogFailed(log,msg);
            return ErrorDesc.failureMessage(msg);
        }


        //????????????
        Result checkActionResult= checkAction(action);
        if(!checkActionResult.isSuccess()){
            recordTaskLogFailed(log,checkActionResult.getMessage());
            return ErrorDesc.failureMessage(checkActionResult.getMessage());
        }

        //??????Node
        Result checkNodeResult=checkNode(node);
        if(!checkNodeResult.isSuccess()){
            recordTaskLogFailed(log,checkNodeResult.getMessage());
            return ErrorDesc.failureMessage(checkNodeResult.getMessage());
        }

        //??????????????????
        String actionConfContent=action.getConfContent();
        JSONObject actionConfContentObj=null;
        try{
            actionConfContentObj=JSONObject.parseObject(actionConfContent);
        }catch (com.alibaba.fastjson.JSONException eee) {
            recordTaskLogFailed(log,"??????????????????????????????");
            return ErrorDesc.failureMessage("??????????????????????????????");
        }

        HashMap<String,Object> vars=new HashMap<>();
        if(actionConfContentObj.containsKey("vars")){
            JSONArray varsArr=actionConfContentObj.getJSONArray("vars");
            if(varsArr!=null&&varsArr.size()>0){
                for(int i=0;i<varsArr.size();i++){
                    vars.put(varsArr.getJSONObject(i).getString("key"),varsArr.getJSONObject(i));
                }
            }
        }

        //??????????????????
        HashMap<String,String> currentNodeVars=new HashMap<>();
        AutoTask task=autoTaskService.getById(taskId);

        String taskConfContent=task.getConfContent();
        JSONArray taskConfContentArr=null;
        try{
            if(StringUtil.isBlank(taskConfContent)){
                taskConfContent="[]";
            }
            taskConfContentArr=JSONArray.parseArray(taskConfContent);
        }catch (com.alibaba.fastjson.JSONException eee) {
            recordTaskLogFailed(log,"????????????????????????????????????");
            return ErrorDesc.failureMessage("????????????????????????????????????");
        }

        if(taskConfContentArr!=null&&taskConfContentArr.size()>0){
            for(int i=0;i<taskConfContentArr.size();i++){
                JSONObject nodeConfObj=taskConfContentArr.getJSONObject(i);
                if(nodeConfObj.containsKey("ip")){
                    String nodeIp=nodeConfObj.getString("ip");
                    if(nodeConfObj.containsKey("vars")){
                       JSONArray nodeVarsArr= nodeConfObj.getJSONArray("vars");
                       for(int j=0;j<nodeVarsArr.size();j++){
                           JSONObject kv=nodeVarsArr.getJSONObject(j);
                           currentNodeVars.put(kv.getString("key"),kv.getString("value"));
                       }
                    }
                    if(nodeIp.equals(node.getIp())){
                        break;
                    }
                }
            }
        }



        //????????????
        Result checkConnectNodeResult=checkConnectNode(action);
        if(!checkConnectNodeResult.isSuccess()){
            recordTaskLogFailed(log,checkConnectNodeResult.getMessage());
            return ErrorDesc.failureMessage(checkConnectNodeResult.getMessage());
        }



       Logger.info("###########Action Start,ip:"+node.getIp()+" #########");
       Logger.info("method:"+method);
       Logger.info("taskId:"+taskId);
       Logger.info("mLogId:"+mLogId);
       Logger.info("nodeName:"+node.getName());
       Logger.info("nodeIp:"+node.getIp());
        //??????????????????
       Logger.info("globalVars:\n"+vars);
       Logger.info("currentNodeVars:\n"+currentNodeVars);
        String ct=action.getExecuteContent();
        for (String key : vars.keySet()) {
            JSONObject keyObject= (JSONObject) vars.get(key);
           Logger.info("keyObject:"+keyObject);
            String gValue=keyObject.getString("value");
            String value=currentNodeVars.getOrDefault(key,gValue);
           Logger.info("replace var,key:"+key+",value:"+value+",gvalue:"+gValue);
            String t=ct.replaceAll("<##"+key+"##>",value);
            ct=t;
        }

        String t= ct.replaceAll("<##ip##>",node.getIp());
        ct=t;
       Logger.info("replace var,key:ip,value:"+node.getIp());
       Logger.info("execute Content:\n"+ct);
        if(METHOD_EXECUTE.equals(method)){
            //#############create exe file #############
            String execFileName="ops_"+mLogId+"_"+node.getId();
            String execFileNameWithSuffix=execFileName+".sh";
            String execFileFullName="";
            File executeTmpFile=null;
            BufferedReader bufferedReader = null;
            BufferedWriter bufferedWriter = null;
            try {
               Logger.info("create temp main execute file,file:"+execFileNameWithSuffix);
                executeTmpFile = File.createTempFile(execFileName, ".sh");
                execFileFullName=executeTmpFile.getAbsolutePath();
                bufferedReader = new BufferedReader(new StringReader(ct));
                bufferedWriter = new BufferedWriter(new FileWriter(executeTmpFile));
                char buf[] = new char[1024]; //???????????????
                int len;
                while ((len = bufferedReader.read(buf)) != -1) {
                    bufferedWriter.write(buf, 0, len);
                }
                bufferedWriter.flush();
                bufferedReader.close();
                bufferedWriter.close();
               Logger.info("create temp main execute file success,file:"+execFileFullName);
            } catch (IOException e) {
                String msg="create temp main execute file failed,file:"+execFileFullName;
               Logger.info(msg);
                e.printStackTrace();
                recordTaskLogFailed(log,msg);
                return ErrorDesc.failureMessage(msg);
            }
            //#############upload exe file #############
            Machine m=new Machine();
            m.setHostname(node.getIp());
            m.setPort(node.getPort());
            m.setUsername(node.getUserName());
            m.setPassword(node.getPassword());
            SftpClient sftp = new SftpClient(m);
            sftp.connect();
            if(!sftp.isConnected()){
                String msg="Connect to host by sftp error!";
                recordTaskLogFailed(log,msg);
                return ErrorDesc.failureMessage(msg);
            }
            sftp.changeDirectory("/tmp");
            try {
                if("/tmp".equals(sftp.getCurrentCatalog())){
                    sftp.deleteFile(execFileNameWithSuffix);
                }
            } catch (IOException ee) {
               Logger.info("delete temp execute file error,detail:"+ee.getMessage());
            }
            Result uploadExeFileR= sftp.uploadFile(executeTmpFile, execFileNameWithSuffix, null);
            if(!uploadExeFileR.success()){
                recordTaskLogFailed(log,uploadExeFileR.message());
                return ErrorDesc.failureMessage(uploadExeFileR.message());
            }
            //#############upload script file #############
            String scriptSql="select\n" +
                    "a.script_id,\n" +
                    "c.file_name,\n" +
                    "d.location\n" +
                    "from \n" +
                    "ops_auto_action_s_script a,\n" +
                    "ops_auto_action b,\n" +
                    "ops_auto_action_script c,\n" +
                    "sys_file d\n" +
                    "where a.owner_id=b.id \n" +
                    "and c.id=a.script_id\n" +
                    "and d.id=c.file_id\n" +
                    "and a.deleted=0 \n" +
                    "and b.deleted=0\n" +
                    "and c.deleted=0\n"+
                    "and b.id=?";
            RcdSet scriptRS=dao.query(scriptSql,action.getId());
            for(Rcd rcd:scriptRS){
                String f=this.getStorageDir()+rcd.getString("location");
                String fn=rcd.getString("file_name");
                if(StringUtil.isBlank(fn)){
                    recordTaskLogFailed(log,"?????????????????????");
                    return ErrorDesc.failureMessage("?????????????????????");
                }
                File file=new File(f);
                if(!file.exists()){
                    String msg="script:"+f+" not exist";
                    recordTaskLogFailed(log,msg);
                    return ErrorDesc.failureMessage(msg);
                }
                try {
                    if("/tmp".equals(sftp.getCurrentCatalog())){
                        sftp.deleteFile(fn);
                    }
                } catch (IOException ee) {
                   Logger.info("delete script file error,detail:"+ee.getMessage());
                }

                Result uploadScritR=sftp.uploadFile(file, fn, null);
                if(!uploadScritR.isSuccess()){
                    recordTaskLogFailed(log,uploadScritR.message());
                    return ErrorDesc.failureMessage(uploadScritR.message());
                }

            }

            //#############upload file #############
            String fileSql="select\n" +
                    "a.file_id,\n" +
                    "c.file_name,\n" +
                    "d.location\n" +
                    "from \n" +
                    "ops_auto_action_s_file a,\n" +
                    "ops_auto_action b,\n" +
                    "ops_auto_action_file c,\n" +
                    "sys_file d\n" +
                    "where a.owner_id=b.id \n" +
                    "and c.id=a.file_id\n" +
                    "and d.id=c.file_id\n" +
                    "and a.deleted=0 \n" +
                    "and b.deleted=0\n" +
                    "and c.deleted=0\n"+
                    "and b.id=?";
            if(StatusEnableEnum.ENABLE.code().equals(action.getFileStatus())){
                RcdSet fileRS=dao.query(fileSql,action.getId());
                for(Rcd rcd:fileRS){
                    String f=this.getStorageDir()+rcd.getString("location");
                    String fn=rcd.getString("file_name");
                    if(StringUtil.isBlank(fn)){
                        recordTaskLogFailed(log,"?????????????????????");
                        return ErrorDesc.failureMessage("?????????????????????");
                    }
                    File file=new File(f);
                    if(!file.exists()){
                        String msg="file:"+f+" not exist";
                        recordTaskLogFailed(log,msg);
                        return ErrorDesc.failureMessage(msg);
                    }
                    try {
                        if("/tmp".equals(sftp.getCurrentCatalog())){
                            sftp.deleteFile(fn);
                        }
                    } catch (IOException ee) {
                       Logger.info("delete file error,detail:"+ee.getMessage());
                    }
                    Result uploadFileR=sftp.uploadFile(file, fn, null);
                    if(!uploadFileR.isSuccess()){
                        recordTaskLogFailed(log,uploadFileR.message());
                        return ErrorDesc.failureMessage(uploadFileR.message());
                    }

                }
            }else{
               Logger.info("file upload status:"+ action.getFileStatus());
            }
            //#############execute #############
            RemoteShellExecutor executor = new RemoteShellExecutor(node.getIp(),node.getUserName(),
                    node.getPassword(), node.getPort());

            ArrayList<String> exeCommands = new ArrayList<String>();
            exeCommands.add("sed -i \"s/^M//\" /tmp/"+execFileNameWithSuffix);
            exeCommands.add("sh /tmp/"+execFileNameWithSuffix);
            RemoteShellResult r2 = executor.exec(exeCommands);
            r2.print();
            if(r2.result.length()>=10000){
                log.setContentDetail(r2.result.substring(0,10000));
            }else{
                log.setContentDetail(r2.result);
            }
        }else if (METHOD_CHECK.equals(method)){
            RemoteShellExecutor executor = new RemoteShellExecutor(node.getIp(),node.getUserName(),
                    node.getPassword(), node.getPort());
            String cmds = "echo 'success!';date";
            RemoteShellResult r2 = executor.exec(cmds);
            r2.print();
            log.setContentDetail(r2.result);
            executor.close();
        }else if (METHOD_VIEW.equals(method)){

        }
       Logger.info("###########Action Finish,ip:"+node.getIp()+" #########");


        //??????
        log.setStatus(OpsAutoTaskResultStatusEnum.SUCCESS.code());
        log.setRecordContent("????????????");
        log.setEtime(new Date());
        autoTaskLogService.update(log,SaveMode.NOT_NULL_FIELDS);
        return ErrorDesc.success();
    }

    @Override
    public Result executeByTaskId(String taskId, String method){

        AutoTask task=autoTaskService.getById(taskId);
        autoTaskService.dao().fill(task)
                .with(AutoTaskMeta.BATCH)
                .with(AutoTaskMeta.ACTION)
                .execute();


        //??????????????????
        AutoTask updateTask=new AutoTask();
        updateTask.setId((task.getId()));
        updateTask.setRunStatus(OpsAutoTaskRunStatusEnum.RUNNING.code());
        autoTaskService.update(updateTask,SaveMode.NOT_NULL_FIELDS);


        //???????????????
        AutoTaskMLog taskMainLog=new AutoTaskMLog();
        String id= IDGenerator.getSnowflakeIdString();
        taskMainLog.setId(id);
        taskMainLog.setTaskId(taskId);
        taskMainLog.setStime(new Date());
        taskMainLog.setStatus(OpsAutoTaskResultStatusEnum.ACTING.code());
        if(task.getAction()!=null){
            taskMainLog.setActionId(task.getAction().getId());
        }
        autoTaskMLogService.insert(taskMainLog);



        //??????nodeList
        List<AutoNode> nodeList= new ArrayList<>();
        String sql="select b.node_id from ops_auto_task a,ops_auto_node_select b where a.id=b.owner_id and a.deleted=0 and b.deleted=0 and a.status='enable' and a.id='"+taskId+"'";
        String nodeSql="";
        //????????????
        if(StringUtil.isBlank(task.getBatchId())){
            nodeSql=sql;
        }else{
            String sql2="select b.node_id from ops_auto_batch a,ops_auto_node_select b where a.id=b.owner_id and a.deleted=0 and b.deleted=0 and a.status='enable' and a.id='"+task.getBatchId()+"'";
            nodeSql=sql+" union all "+sql2;
        }

        AutoNode nodeQuery=new AutoNode();
        nodeQuery.setStatus(StatusEnableEnum.ENABLE.code());
        ConditionExpr expr=new ConditionExpr();
        expr.and("id in ("+nodeSql+")");
        nodeList=autoNodeService.queryList(nodeQuery,expr);

        //??????????????????
        if(nodeList!=null){
            autoNodeService.dao().fill(nodeList)
                    .with(AutoNodeMeta.VOUCHER)
                    .execute();
        }

        //??????action
        AutoAction action=task.getAction();
        Result checkActionResult=checkAction(action);
        if(!checkActionResult.success()){
            recordTaskMLogFailed(taskMainLog,checkActionResult.getMessage());
            return ErrorDesc.failureMessage(checkActionResult.getMessage());
        }


        //??????method
        if(method.equals(METHOD_EXECUTE) || method.equals(METHOD_VIEW)||method.equals(METHOD_CHECK)){
           Logger.info("success");
        }else{
            String msg="???????????????????????????";
            recordTaskMLogFailed(taskMainLog,msg);
            return ErrorDesc.failureMessage(msg);
        }

        //??????????????????
        if(nodeList==null|| nodeList.size()==0){
            String msg="????????????????????????";
            recordTaskMLogFailed(taskMainLog,msg);
            return ErrorDesc.failureMessage(msg);
        }


        //????????????????????????????????????
        if(OpsAutoActionNodeNumberTypeEnum.TWO.code().equals(action.getNodeNumberType())){
            if(nodeList.size()!=2){
                String msg="??????????????????,????????????2?????????";
                recordTaskMLogFailed(taskMainLog,msg);
                return ErrorDesc.failureMessage(msg);
            }
        }else if (OpsAutoActionNodeNumberTypeEnum.THREE.code().equals(action.getNodeNumberType())){
            if(nodeList.size()!=3){
                String msg="??????????????????,????????????3?????????";
                recordTaskMLogFailed(taskMainLog,msg);
                return ErrorDesc.failureMessage(msg);
            }
        }


        StringBuilder nodeResult=new StringBuilder();
        nodeResult.append("????????????:"+nodeList.size()+"\n");
        Map<AutoNode,String> nodeMap=new HashMap<AutoNode, String>();
        for(AutoNode n:nodeList){
            Logger.info("task:"+task.getName()+",node:"+n.getName()+",action:"+action.getName());
            Result eRes=executeNode(n,action,task.getId(),id,method);
            if(!eRes.isSuccess()){
                nodeMap.put(n,eRes.getMessage());
            }
        }
        nodeResult.append("??????????????????:"+nodeMap.size()+"\n");
        Iterator<Map.Entry<AutoNode,String>> iterator=nodeMap.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<AutoNode,String> entry=   iterator.next();
            AutoNode node=entry.getKey();
            String msg=entry.getValue();
            nodeResult.append("??????:"+node.getIp()+"\n");
            nodeResult.append("????????????:"+msg+"\n");
            nodeResult.append("\n\n\n");
        }
        taskMainLog.setEtime(new Date());
        taskMainLog.setStatus(OpsAutoTaskResultStatusEnum.SUCCESS.code());
        taskMainLog.setContent(nodeResult.toString());
        autoTaskMLogService.update(taskMainLog,SaveMode.NOT_NULL_FIELDS);
        //??????????????????
        updateTask.setRunStatus(OpsAutoTaskRunStatusEnum.FINISH.code());
        autoTaskService.update(updateTask,SaveMode.NOT_NULL_FIELDS);
        return ErrorDesc.success();
    }






    public Result checkConnectNode(AutoAction node){
        return ErrorDesc.success();
    }

    public Result checkAction(AutoAction action){

        if(action==null){
            return ErrorDesc.failureMessage("??????????????????");
        }

        if(!StatusEnableEnum.ENABLE.code().equals(action.getStatus())){
            return ErrorDesc.failureMessage("???????????????????????????????????????");
        }

        String conf=action.getConfContent();
        String content=action.getExecuteContent();


        if(StringUtil.isBlank(conf)){
            return ErrorDesc.failureMessage("????????????????????????");
        }

//        JSONObject confObject= JSONObject.parseObject(conf);
//        if(confObject==null){
//            return ErrorDesc.failureMessage("????????????????????????");
//        }

        if(StringUtil.isBlank(content)){
            return ErrorDesc.failureMessage("??????????????????");
        }

        return ErrorDesc.success();
    }

    public Result checkNode(AutoNode node){

        String ip=node.getIp();
        int port=node.getPort();

        //??????IP
        if(StringUtil.isBlank(ip)){
            return ErrorDesc.failureMessage("??????????????????");
        }

        //????????????
        if(port<=0||port>=65536){
            return ErrorDesc.failureMessage("???????????????");
        }


        //????????????
        AutoVoucher voucher = node.getVoucher();
        if(voucher==null){
            //?????????????????????????????????
            if(StringUtil.isBlank(node.getUserName())){
                return ErrorDesc.failureMessage("????????????????????????");
            }
            if(StringUtil.isBlank(node.getPassword())){
                return ErrorDesc.failureMessage("????????????????????????");
            }

        }else{
            String accountStr=voucher.getAccount();
            String voucherStr=voucher.getVoucher();
            if(StringUtil.isBlank(accountStr)){
                return ErrorDesc.failureMessage("????????????????????????");
            }
            if(StringUtil.isBlank(voucherStr)){
                return ErrorDesc.failureMessage("????????????????????????");
            }
            node.setUserName(accountStr);
            node.setPassword(voucherStr);
        }


        return ErrorDesc.success();
    }



}
