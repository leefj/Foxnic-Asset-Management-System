package com.dt.platform.generator.module.hr;


import com.dt.platform.constants.db.HrTables;
import com.dt.platform.domain.hr.AttendanceRecord;
import com.dt.platform.generator.config.Config;
import com.dt.platform.hr.page.AttendanceDataPageController;
import com.dt.platform.hr.page.AttendanceRecordPageController;
import com.dt.platform.proxy.hr.AttendanceDataServiceProxy;
import com.dt.platform.proxy.hr.AttendanceRecordServiceProxy;
import com.github.foxnic.generator.config.WriteMode;


public class HrmAttendanceRecordGtr extends BaseCodeGenerator {
    public HrmAttendanceRecordGtr() {
        super(HrTables.HR_ATTENDANCE_RECORD.$TABLE,"662281190966820864");
    }

    public void generateCode() throws Exception {
        System.out.println(this.getClass().getName());


        cfg.view().field(HrTables.HR_ATTENDANCE_RECORD.ID).basic().hidden(true);

        cfg.view().search().inputLayout(
                new Object[]{
                        HrTables.HR_ATTENDANCE_RECORD.EMPLOYEE_ID,
                        HrTables.HR_ATTENDANCE_RECORD.NOTES,
                }

        );

        cfg.view().search().labelWidth(1,Config.searchLabelWidth);
        cfg.view().search().labelWidth(2,Config.searchLabelWidth);
        cfg.view().search().labelWidth(3,Config.searchLabelWidth);
        cfg.view().search().labelWidth(4,Config.searchLabelWidth);
        cfg.view().search().inputWidth(Config.searchInputWidth);

        cfg.view().formWindow().width("65%");;
        cfg.view().formWindow().bottomSpace(20);
        cfg.view().form().addGroup(null,
                new Object[] {
                        HrTables.HR_ATTENDANCE_RECORD.EMPLOYEE_ID,
                        HrTables.HR_ATTENDANCE_RECORD.NOTES,
                }
        );




        //文件生成覆盖模式
        cfg.overrides()
                .setServiceIntfAnfImpl(WriteMode.COVER_EXISTS_FILE) //服务与接口
                .setControllerAndAgent(WriteMode.COVER_EXISTS_FILE) //Rest
                .setPageController(WriteMode.COVER_EXISTS_FILE) //页面控制器
                .setFormPage(WriteMode.COVER_EXISTS_FILE) //表单HTML页
                .setListPage(WriteMode.COVER_EXISTS_FILE) //列表HTML页
                .setExtendJsFile(WriteMode.COVER_EXISTS_FILE);
        //生成代码
        cfg.buildAll();
    }

    public static void main(String[] args) throws Exception {
        HrmAttendanceRecordGtr g=new HrmAttendanceRecordGtr();
        //生成代码
        g.generateCode();

        //移除之前生成的菜单，视情况执行
       // System.out.println("############"+g.getTablePrefix());
        //g.removeByBatchId("470160949404237824");
      //  //生成菜单
      // g.generateMenu(AttendanceRecordServiceProxy.class, AttendanceRecordPageController.class);

    }
}
