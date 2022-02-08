package com.dt.platform.ops.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dt.platform.constants.enums.ops.MonitorTopDataEnum;
import com.dt.platform.domain.ops.MonitorNode;
import com.dt.platform.ops.service.IMonitorStatisticalDataService;
import com.github.foxnic.api.transter.Result;
import com.github.foxnic.dao.entity.SuperService;
import com.github.foxnic.dao.spec.DAO;
import org.github.foxnic.web.framework.dao.DBConfigs;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("MonitorStatisticalDataService")
public class MonitorStatisticalDataServiceImpl extends SuperService<MonitorNode> implements IMonitorStatisticalDataService {

        /**
     * 注入DAO对象
     * */
    @Resource(name= DBConfigs.PRIMARY_DAO)
    private DAO dao=null;

    /**
     * 获得 DAO 对象
     * */
    public DAO dao() { return dao; }


    @Override
    public Result<JSONObject> queryNodeStatistics() {
        Result<JSONObject> result=new Result<>();
        JSONObject resultData=new JSONObject();
        //统计节点个数
        String sql="select a.name,a.code,ifnull(b.cnt,0) cnt from ops_monitor_node_type a left join (select type,count(1) cnt from ops_monitor_node where deleted=0 group by type) b on a.code=b.type";
        resultData.put("nodeStatistics",dao.query(sql).toJSONArrayWithJSONObject());
        return result.success(true).data(resultData);
    }

    @Override
    public Result<JSONObject> queryNodeHostResourceList() {

        String sql="select\n" +
                "(\n" +
                "select process_cnt\n" +
                "from ops_monitor_node_value t where \n" +
                "(node_id,indicator_code,record_time) in \n" +
                "(select node_id,indicator_code, max(record_time) max_record_time from ops_monitor_node_value group by node_id,indicator_code)\n" +
                "and result_status='sucess' and t.indicator_code='os.process_cnt' and t.node_id=end.id\n" +
                ") data_process_cnt,\n" +
                "(\n" +
                "select os_load\n" +
                "from ops_monitor_node_value t where \n" +
                "(node_id,indicator_code,record_time) in \n" +
                "(select node_id,indicator_code, max(record_time) max_record_time from ops_monitor_node_value group by node_id,indicator_code)\n" +
                "and result_status='sucess' and t.indicator_code='os.load' and t.node_id=end.id\n" +
                ") data_os_load,\n" +
                "(\n" +
                "select cpu_number\n" +
                "from ops_monitor_node_value t where \n" +
                "(node_id,indicator_code,record_time) in \n" +
                "(select node_id,indicator_code, max(record_time) max_record_time from ops_monitor_node_value group by node_id,indicator_code)\n" +
                "and result_status='sucess' and t.indicator_code='os.cpu_number' and t.node_id=end.id\n" +
                ") data_os_cpu_number,\n" +
                "(\n" +
                "select hostname\n" +
                "from ops_monitor_node_value t where \n" +
                "(node_id,indicator_code,record_time) in \n" +
                "(select node_id,indicator_code, max(record_time) max_record_time from ops_monitor_node_value group by node_id,indicator_code)\n" +
                "and result_status='sucess' and t.indicator_code='os.hostname' and t.node_id=end.id\n" +
                ") data_hostname,\n" +
                "\n" +
                "(\n" +
                "select p_memory_used\n" +
                "from ops_monitor_node_value t where \n" +
                "(node_id,indicator_code,record_time) in \n" +
                "(select node_id,indicator_code, max(record_time) max_record_time from ops_monitor_node_value group by node_id,indicator_code)\n" +
                "and result_status='sucess' and t.indicator_code='os.memory_used' and t.node_id=end.id\n" +
                ") data_memory_used,\n" +
                "(\n" +
                "select os_datetime\n" +
                "from ops_monitor_node_value t where \n" +
                "(node_id,indicator_code,record_time) in \n" +
                "(select node_id,indicator_code, max(record_time) max_record_time from ops_monitor_node_value group by node_id,indicator_code)\n" +
                "and result_status='sucess' and t.indicator_code='os_datetime' and t.node_id=end.id\n" +
                ") data_os_datetime,\n" +
                "\n" +
                "(\n" +
                "select max(list_value_number1) flow_up\n" +
                "from ops_monitor_node_value t where \n" +
                "(node_id,indicator_code,record_time) in \n" +
                "(select node_id,indicator_code, max(record_time) max_record_time from ops_monitor_node_value group by node_id,indicator_code)\n" +
                "and result_status='sucess' and t.indicator_code='os.net_interface_flow' and t.node_id=end.id\n" +
                ") os_net_interface_flow_up,\n" +
                "(\n" +
                "select max(list_value_number2) flow_down\n" +
                "from ops_monitor_node_value t where \n" +
                "(node_id,indicator_code,record_time) in \n" +
                "(select node_id,indicator_code, max(record_time) max_record_time from ops_monitor_node_value group by node_id,indicator_code)\n" +
                "and result_status='sucess' and t.indicator_code='os.net_interface_flow' and t.node_id=end.id\n" +
                ") os_net_interface_flow_down,\n" +
                "\n" +
                "end.*\n" +
                "from ops_monitor_node end where node_enabled='enable' and deleted='0'";
        System.out.println("nodeList Sql:\n"+sql);
        Result<JSONObject> result=new Result<>();
        JSONObject resultData=new JSONObject();
        //统计节点个数
        resultData.put("nodeHostList",dao.query(sql).toJSONArrayWithJSONObject());

        return result.success(true).data(resultData);


    }

    @Override
    public Result<JSONObject> queryNodeHostTopData(List<String> topList,int top,int day) {
        Result<JSONObject> result=new Result<>();
        JSONObject resultData=new JSONObject();
        if(topList!=null&&topList.size()>0){
            for(String topValue : topList){
                if(MonitorTopDataEnum.OS_CPU_USED.code().equals(topValue)){
                    resultData.put("osCpuUsed",this.queryNodeHostTopDataOsCpuUsed(top,day));
                }else if(MonitorTopDataEnum.OS_FS_USED.code().equals(topValue)){
                    resultData.put("osFs",this.queryNodeHostTopDataOsFsUsed(top,day));
                }else if(MonitorTopDataEnum.OS_FS_INODE_USED.code().equals(topValue)){
                    resultData.put("osFsInodeUsed",this.queryNodeHostTopDataOsFsInodeUsed(top,day));
                }else if(MonitorTopDataEnum.OS_LOAD.code().equals(topValue)){
                    resultData.put("osLoad",this.queryNodeHostTopDataOsLoad(top,day));
                }else if(MonitorTopDataEnum.OS_MEMORY_USED.code().equals(topValue)){
                    resultData.put("osMemoryUsed",this.queryNodeHostTopDataOsMemoryUsed(top,day));
                }else if(MonitorTopDataEnum.OS_VMEMORY_USED.code().equals(topValue)){
                    resultData.put("osVmemoryUsed",this.queryNodeHostTopDataOsVmemoryUsed(top,day));
                }else if(MonitorTopDataEnum.OS_NET_FLOW_UP.code().equals(topValue)){
                    resultData.put("osNetFlowUp",this.queryNodeHostTopDataOsNetFlowUp(top,day));
                }else if(MonitorTopDataEnum.OS_NET_FLOW_DOWN.code().equals(topValue)){
                    resultData.put("osNetFlowDown",this.queryNodeHostTopDataOsNetFlowDown(top,day));
                }
            }
        }
        return result.success(true).data(resultData);
    }

    private JSONArray queryNodeHostTopDataOsFsUsed(int top,int day){
        String sql="";
        return dao.query(sql).toJSONArrayWithJSONObject();
    }
    private JSONArray queryNodeHostTopDataOsFsInodeUsed(int top,int day){
        String sql="";
        return dao.query(sql).toJSONArrayWithJSONObject();
    }

    private JSONArray queryNodeHostTopDataOsNetFlowUp(int top,int day){
        String sql="select * from (       \n" +
                "select b.node_ip,b.node_name_show,a.list_value_str1 fs_namae,a.list_value_number1 up_flow from ops_monitor_node_value a,ops_monitor_node b\n" +
                "where a.node_id=b.id \n" +
                "and b.type='os' \n" +
                "and b.node_enabled='enable' \n" +
                "and b.status='online' \n" +
                "and (node_id,indicator_code,list_value_str1,record_time) \n" +
                "in (select node_id,indicator_code,list_value_str1,max(record_time) max_record_time from (select * from ops_monitor_node_value where indicator_code='os.net_interface_flow') t group by node_id,indicator_code,list_value_str1)\n" +
                "order by a.list_value_number1 desc)end limit "+top;
        return dao.query(sql).toJSONArrayWithJSONObject();
    }
    private JSONArray queryNodeHostTopDataOsNetFlowDown(int top,int day){
        String sql="select * from (       \n" +
                "select b.node_ip,b.node_name_show,a.list_value_str1 fs_namae,a.list_value_number2 down_flow from ops_monitor_node_value a,ops_monitor_node b\n" +
                "where a.node_id=b.id \n" +
                "and b.type='os' \n" +
                "and b.node_enabled='enable' \n" +
                "and b.status='online' \n" +
                "and (node_id,indicator_code,list_value_str1,record_time) \n" +
                "in (select node_id,indicator_code,list_value_str1,max(record_time) max_record_time from (select * from ops_monitor_node_value where indicator_code='os.net_interface_flow') t group by node_id,indicator_code,list_value_str1)\n" +
                "order by a.list_value_number2 desc)end limit "+top;
        return dao.query(sql).toJSONArrayWithJSONObject();
    }

    private JSONArray queryNodeHostTopDataOsCpuUsed(int top,int day){
        String sql="select * from (       \n" +
                "select b.node_ip,b.node_name_show,a.cpu_used from ops_monitor_node_value a,ops_monitor_node b\n" +
                "where a.node_id=b.id \n" +
                "and b.type='os' \n" +
                "and b.node_enabled='enable' \n" +
                "and b.status='online' \n" +
                "and (node_id,indicator_code,record_time) \n" +
                "in (select node_id,indicator_code,max(record_time) max_record_time from (select * from ops_monitor_node_value where indicator_code='os.cpu') t group by node_id,indicator_code)\n" +
                "order by cpu_used desc)end limit "+top;
        return dao.query(sql).toJSONArrayWithJSONObject();
    }

    private JSONArray queryNodeHostTopDataOsLoad(int top,int day){
        String sql="select * from (       \n" +
                "select b.node_ip,b.node_name_show,a.os_load from ops_monitor_node_value a,ops_monitor_node b\n" +
                "where a.node_id=b.id \n" +
                "and b.type='os' \n" +
                "and b.node_enabled='enable' \n" +
                "and b.status='online' \n" +
                "and (node_id,indicator_code,record_time) \n" +
                "in (select node_id,indicator_code,max(record_time) max_record_time from (select * from ops_monitor_node_value where indicator_code='os.load') t group by node_id,indicator_code)\n" +
                "order by os_load desc)end limit "+top;
        return dao.query(sql).toJSONArrayWithJSONObject();
    }

    private JSONArray queryNodeHostTopDataOsMemoryUsed(int top,int day){
        String sql="select b.node_ip,b.node_name_show,a.p_memory_used from ops_monitor_node_value a,ops_monitor_node b\n" +
                "where a.node_id=b.id \n" +
                "and b.type='os' \n" +
                "and b.node_enabled='enable' \n" +
                "and b.status='online' \n" +
                "and (node_id,indicator_code,record_time) \n" +
                "in (select node_id,indicator_code,max(record_time) max_record_time from (select * from ops_monitor_node_value where indicator_code='os.memory_used') t group by node_id,indicator_code)\n" +
                "order by p_memory_used desc)end limit "+top;
        return dao.query(sql).toJSONArrayWithJSONObject();
    }

    private JSONArray queryNodeHostTopDataOsVmemoryUsed(int top,int day){
        String sql="";
        return dao.query(sql).toJSONArrayWithJSONObject();
    }

}