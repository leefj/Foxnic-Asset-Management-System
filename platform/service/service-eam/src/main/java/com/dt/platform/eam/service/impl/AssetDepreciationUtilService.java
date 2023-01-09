package com.dt.platform.eam.service.impl;

import com.dt.platform.constants.enums.common.StatusEnableEnum;
import com.dt.platform.constants.enums.eam.*;
import com.dt.platform.domain.eam.*;
import com.dt.platform.domain.eam.meta.AssetDepreciationOperMeta;
import com.dt.platform.eam.service.*;
import com.github.foxnic.api.error.ErrorDesc;
import com.github.foxnic.api.transter.Result;
import com.github.foxnic.commons.bean.BeanUtil;
import com.github.foxnic.commons.lang.StringUtil;
import com.github.foxnic.commons.log.Logger;
import com.github.foxnic.sql.expr.ConditionExpr;
import org.apache.commons.jexl3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;


@Service("AssetDepreciationUtilService")
public class AssetDepreciationUtilService {


    /**
     * 月份是否一样
     * @param date1 日期1 2022-01
     * @param date2 日期2 2022-01
     * @return 1 相等,
     *         0 不相等
     * */
    public String equalMonth(Date date1, Date date2){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        String d1 = sdf.format(date1);
        String d2 = sdf.format(date2);
        if(d1.equals(d2)){
            return "1";
        }else{
            return "0";
        }
    }

    public BigDecimal roundHalfUp(Object value, int scale){
        Logger.info("class type:"+value.getClass());
        if(value.getClass().equals(BigDecimal.class)){
            return ((BigDecimal) value).setScale(scale,BigDecimal.ROUND_HALF_UP);
        }else if(value.getClass().equals(Integer.class)){
            return new BigDecimal((Integer) value);
        }else if(value.getClass().equals(Double.class)){
            return new BigDecimal((Double) value).setScale(scale,BigDecimal.ROUND_HALF_UP);
        }else if(value.getClass().equals(Float.class)){
            return new BigDecimal((Float) value).setScale(scale,BigDecimal.ROUND_HALF_UP);
        }else if(value.getClass().equals(String.class)){
            return new BigDecimal((String) value).setScale(scale,BigDecimal.ROUND_HALF_UP);
        }else{
            Logger.error("roundHalfUp error,current class type is "+value.getClass()+",please modify code.");
        }
        return null;
    }


    /**
     * 月份是否一样
     * @param  registerDate 入账日期 2022-01
     * @param  businessDate 入账日期 2022-01
     * @return 1
     * */
    public int usedLifeByAccountingPeriod(Date registerDate,Date businessDate){
        //按照计算规则，registerDate 不会和businessDate 相等，businessDate大于registerDate 至少一个月
        SimpleDateFormat sdfy = new SimpleDateFormat("yyyy");
        String d1y = sdfy.format(registerDate);
        String d2y = sdfy.format(businessDate);
        if(d2y.equals(d1y)){
            //资产入账日期，在本年度发生折旧
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(registerDate);
            int month1 = cal1.get(Calendar.MONTH) + 1;
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(businessDate);
            int month2 = cal2.get(Calendar.MONTH) + 1;
            return (month2-month1);
        }else{
            //超过了入账日期所在年，只需要计算获得当前月份
            Calendar cal = Calendar.getInstance();
            cal.setTime(businessDate);
            int month = cal.get(Calendar.MONTH) + 1;
            return month;
        }
    }


    /**
     * date1-date2
     * @param businessDate 日期1 2022-01
     * @param startDate 日期2 2022-01
     * @return 返回相减的月份
     * */
    public int usedLifeByAccountingPeriodForLast(Date startDate,Date businessDate){
        int value= usedLifeByAccountingPeriod(startDate,businessDate);
        if(value-1<0){
            return 0;
        }else{
            return value-1;
        }
    }


    /**
     * date1-date2
     * @param businessDate 日期1 2022-01
     * @param startDate 日期2 2022-01
     * @param life 1
     * @return 返回相减的月份
     * */
    public int usedLifeByAccountingPeriodForFinish(Date startDate,Date businessDate,int life){
        SimpleDateFormat sdfy = new SimpleDateFormat("yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.MONTH,life);
        Date lastDate = calendar.getTime();
        String lastYear = sdfy.format(lastDate);
        String businessYear = sdfy.format(businessDate);
        String startYear = sdfy.format(startDate);
        int hd=0;
        if(businessYear.equals(startYear)){
            //计算月份-验收月份
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(startDate);
            int month1 = cal1.get(Calendar.MONTH) + 1;
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(businessDate);
            int month2 = cal2.get(Calendar.MONTH) + 1;
            hd= (month2-month1);
        }else {
            if(Integer.parseInt(businessYear) <= Integer.parseInt(lastYear) ) {
                Calendar cal2 = Calendar.getInstance();
                cal2.setTime(businessDate);
                hd = cal2.get(Calendar.MONTH) + 1;
            }else{
                hd=0;
            }
        }
        if(hd-1<0){
            return 0;
        }else{
            return hd-1;
        }
    }

    public int usedLifeByAccountingPeriodForFinish2(Date startDate,Date businessDate,int life){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.MONTH,life);
        Date lastDate = calendar.getTime();

        SimpleDateFormat sdfy = new SimpleDateFormat("yyyy");
        String d1y = sdfy.format(lastDate);
        String d2y = sdfy.format(businessDate);
        if(d1y.equals(d2y)){
            return 1;
        }else{
            return 0;
        }
    }


    /**
     * date1-date2
     * @param date1 日期1 2022-01
     * @param date2 日期2 2022-01
     * @return 返回相减的月份
     * */
    public String subtractMonth(Date date1, Date date2){
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        int year1 = cal1.get(Calendar.YEAR) ;
        int month1 = cal1.get(Calendar.MONTH) + 1;

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int year2 = cal2.get(Calendar.YEAR) ;
        int month2 = cal2.get(Calendar.MONTH) + 1;
        return (year1-year2)*12+(month1-month2)+"";
    }


    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH,2);
        Date lastDate = calendar.getTime();

        System.out.println(lastDate);

        Map<String, Object> map =new HashMap<>();
        map.put("a", new BigDecimal("1"));
        map.put("b", new BigDecimal("6"));
        map.put("c", new BigDecimal("100.01"));

        JexlBuilder jexlBuilder = new JexlBuilder();
        Map<String, Object> funcs =new HashMap<>();
        funcs.put("commonFunction",new AssetDepreciationUtilService());
        jexlBuilder.namespaces(funcs);

        JexlEngine jexlEngine = jexlBuilder.create();
        boolean bb=true;

        Date d1=new Date();
        Date d2=new Date();
        String expr="d1>d2";

        JexlContext jexlContext = new MapContext();
        jexlContext.set("bb",  bb);
        jexlContext.set("d1",  d1);
        jexlContext.set("d2",  d2);
        jexlContext.set("a",  new BigDecimal("1"));
        jexlContext.set("b",  new BigDecimal("1.0"));

        JexlExpression  expression = jexlEngine.createExpression(expr);
        Object r=expression.evaluate(jexlContext);

        System.out.println(r.getClass()+","+r);
        // 初始化Jexl构造器
//        JexlBuilder jexlBuilder = new JexlBuilder();
//        // 创建Jexl表达式引擎
//        JexlEngine jexlEngine = jexlBuilder.create();
//        // 创建Jexl表达式解析器
//        JexlScript jexlScript = jexlEngine.createScript("if(item.eRecoverableAmount>=25){grade=1;gradeName='有灾害';}else{grade=0;gradeName='无灾害';}");
//        // 创建Jexl表达式变量上下文
//        JexlContext jexlContext = new MapContext();
//
//
//        AssetDepreciationDetail d=new AssetDepreciationDetail();
//        d.setERecoverableAmount(new BigDecimal("1.01"));
//        jexlContext.set("item", d);
//        // 执行Jexl表达式，得到结果
//        jexlScript.execute(jexlContext);
//        System.out.println(jexlContext.get("grade"));
//        System.out.println(jexlContext.get("gradeName"));

//        AssetDepreciationUtilService a=new AssetDepreciationUtilService();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Date date11 = null;
//        Date date22 =null;
//        try {
//              date11 = sdf.parse("2022-01-01");
//              date22 = sdf.parse("2023-12-05");
//            String t=a.usedLifeByAccountingPeriod(date11,date22);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
    }

    /**
     *  支持条件表达式计算
     * @param eval 表达式不能有汉字（支持条件语句的表达式）
     * @param map
     * @param clazz
     * @Description: 根据表达式和参数集合进行计算
     * @Author: chenhf
     * @Date: 2021/6/22 16:12
     * @return: java.lang.Object
     */
    private static Object exeScriptExpression(String eval, Map<String, Object> map,Class<?>... clazz) {
        JexlEngine engine = new JexlBuilder().create();
        JexlScript script = engine.createScript(eval);
        JexlContext context = new MapContext();
        Arrays.asList(clazz).forEach(cal -> {
            context.set(cal.getSimpleName(),cal);
        });
        if (Objects.nonNull(map)) {
            map.forEach(context::set);
        }
        return script.execute(context);
    }


    /**
     * 日期是否一样
     * @param date1 日期1 2022-01-02,
     * @param date2 日期2 2022-01-02
     * @return 0 date1=date2,
     *         1 date1>date2,
     *        -1 date1<date2
     * */
    public String compareDate(Date date1, Date date2) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String d1 = sdf.format(date1);
        String d2 = sdf.format(date2);
        Logger.info("compareDate d1:"+d1+",d2:"+d2);
        Date date11 = null;
        Date date22 = null;
        int v=0;
        try {
            date11 = sdf.parse(d1);
            date22 = sdf.parse(d2);
            v=date11.compareTo(date22);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Logger.info("compareDate reuslt:"+v);
        return v+"";
    }


    /*
     * 执行规则集
     * */
    public Result calRules(AssetDepreciationDetail assetDepreciationDetail,List<AssetDepreciationCalRule> ruleList){
        Logger.info("calRules,actionCode:"+assetDepreciationDetail.getActionCode());
        for(AssetDepreciationCalRule rule:ruleList){
            //先设置成功
            assetDepreciationDetail.setResultStatus(AssetDetailDepreciationResultStatusEnum.SUCCESS.code());
            // 规则是否启用
            String partMsg="动作:"+rule.getActionCode()+",规则编号:"+rule.getRuleNumber()+",单据:"+assetDepreciationDetail.getOperId()+",资产编号:"+assetDepreciationDetail.getAssetCode();
            // 执行规则
            Result calRuleResult=calRuleContent(assetDepreciationDetail,rule);
            assetDepreciationDetail.setResultDetail(calRuleResult.getMessage());
            //输出
            if(calRuleResult.isSuccess()){
                assetDepreciationDetail.setResultStatus(AssetDetailDepreciationResultStatusEnum.SUCCESS.code());
                Logger.info(partMsg+",规则执行成功");
            }else{
                assetDepreciationDetail.setResultStatus(AssetDetailDepreciationResultStatusEnum.FAILED.code());
                Logger.info(partMsg+",规则执行失败");
                break;
            }
            Logger.info("\n");
        }
        Logger.info("\n");
        return ErrorDesc.success();
    }

    /*
     *  执行规则集中的一条规则
     * */
    public Result calRuleContent(AssetDepreciationDetail assetDepreciationDetail,AssetDepreciationCalRule rule){
        String colValue=rule.getColumnValue();
        String calType=rule.getCalculationType();
        String ruleContent=rule.getMethodContent();
        String partMsg="动作:"+rule.getActionCode()+",规则编号:"+rule.getRuleNumber()+",单据:"+assetDepreciationDetail.getOperId()+",列名:"+rule.getColumnName();
        Logger.info(partMsg+",动作:本规则计算开始,资产编号:"+assetDepreciationDetail.getAssetCode()+",列值:"+rule.getColumnValue()+",信息:"+rule.getMethodContentInfo());
        //上一起折旧数据
        AssetDepreciationDetail lastAssetDepreciationDetail=null;
        if(assetDepreciationDetail.getLastAssetDepreciationDetail()!=null){
            lastAssetDepreciationDetail=assetDepreciationDetail.getLastAssetDepreciationDetail();
        }
        //资产原始数据
        Asset assetOriginalData=assetDepreciationDetail.getAsset();
        if(AssetDepreciationCalculationMethodTypeEnum.JEXL_EXPRESSION.code().equals(calType)){
            //JEXL_EXPRESSION 表达式计算
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("asset", assetDepreciationDetail);
            map.put("last", lastAssetDepreciationDetail);
            map.put("assetOriginalData", assetOriginalData);
            Logger.info(partMsg+",字段:"+rule.getColumnValue() +",表达式:"+ruleContent);

            if(StringUtil.isBlank(ruleContent)){
                return ErrorDesc.failureMessage(partMsg+",计算表达式为空");
            }
            Logger.info(partMsg+",rule:"+ruleContent);
            Object result = calculationValue(ruleContent,map);
            Logger.info(partMsg+",计算结果:"+result);
            if(StringUtil.isBlank(result)||result.toString().startsWith("err")){
                return ErrorDesc.failureMessage(partMsg+",计算结果:"+result);
            }
            //返回
            if(AssetDepreciationCalculationReturnTypeEnum.TYPE_DOUBLE.code().equals(rule.getReturnType())){
                //double 类型
                //BigDecimal.ROUND_DOWN 截取
                //BigDecimal.ROUND_HALF_UP 进行四舍五入
                BigDecimal bResult = new BigDecimal(result.toString()).setScale(2,BigDecimal.ROUND_HALF_UP);
                BeanUtil.setFieldValue(assetDepreciationDetail,colValue,bResult);
                Logger.info(partMsg+",设置值:"+BeanUtil.getFieldValue(assetDepreciationDetail,colValue,BigDecimal.class).toString());
            }else if(AssetDepreciationCalculationReturnTypeEnum.TYPE_STRING.code().equals(rule.getReturnType())){
                //string 类型
                BeanUtil.setFieldValue(assetDepreciationDetail,colValue,result.toString());
                Logger.info(partMsg+",设置值:"+BeanUtil.getFieldValue(assetDepreciationDetail,colValue,String.class));
            }else if(AssetDepreciationCalculationReturnTypeEnum.TYPE_BOOLEAN.code().equals(rule.getReturnType())){
                //string 类型
                if(result.getClass().equals(Double.class)){

                }else{
                    return ErrorDesc.failureMessage(partMsg+",当前计算表达式范围类型错误，返回类型为:"+result.getClass()+",正常应返回为:Boolean");
                }
            }
        }
        else if(AssetDepreciationCalculationMethodTypeEnum.NOT_NULL.code().equals(calType)){
            //NOT NULL 方式
            Boolean notNUll=true;
            Logger.info("assetDepreciationDetail:"+assetDepreciationDetail+",colValue:"+colValue);
            Logger.info(partMsg+",计算结果:"+BeanUtil.getFieldValue(assetDepreciationDetail,colValue));
            if(BeanUtil.getFieldValue(assetDepreciationDetail,colValue)==null){
                notNUll=false;
            }
            Logger.info(partMsg+",计算结果:"+notNUll);
            if(!notNUll){
                return ErrorDesc.failureMessage("本规则计算未匹配,规则编号:"+rule.getRuleNumber()+","+rule.getColumnName()+"该值不能为空");
            }
        } else if(AssetDepreciationCalculationMethodTypeEnum.SCRIPT.code().equals(calType)){
            //SCRIPT 脚本，暂时未实现
            Logger.info(partMsg+",本规则未实现");

            return ErrorDesc.failureMessage("本规则计算类型没有实现,Rule:"+rule.getRuleNumber());
        }else{
            Logger.info(partMsg+",计算规则配置未正确配置");
            return ErrorDesc.failureMessage("本规则计算类型设置有误,Rule:"+rule.getRuleNumber());
        }
        return ErrorDesc.success();
    }

    private String calculationValue(String jexlExp, Map<String, Object> map){
        JexlBuilder jb=new JexlBuilder();
        Map<String, Object> funcs =new HashMap<>();
        funcs.put("commonFunction",new AssetDepreciationUtilService());
        jb.namespaces(funcs);
        JexlEngine jexl =jb.create();
        JexlExpression  expression = jexl.createExpression(jexlExp);
        JexlContext jc = new MapContext();
        for (String key : map.keySet()) {
            jc.set(key, map.get(key));
        }
        Object r=expression.evaluate(jc);
        if (null ==r) {
            return "";
        }
        return r.toString();
    }

}
