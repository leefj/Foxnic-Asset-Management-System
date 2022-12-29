package com.dt.platform.domain.hr.meta;

import com.github.foxnic.api.bean.BeanProperty;
import com.dt.platform.domain.hr.Person;
import java.util.Date;
import com.dt.platform.domain.hr.Position;
import com.dt.platform.domain.hr.Rank;
import com.dt.platform.domain.hr.ProfessionalLevel;
import org.github.foxnic.web.domain.system.DictItem;
import org.github.foxnic.web.domain.hrm.Employee;
import javax.persistence.Transient;



/**
 * @author 金杰 , maillank@qq.com
 * @since 2022-12-29 15:01:36
 * @sign 066E1222AF86221EBF502F2AEE28778D
 * 此文件由工具自动生成，请勿修改。若表结构或配置发生变动，请使用工具重新生成。
*/

public class PersonMeta {
	
	/**
	 * 主键 , 类型: java.lang.String
	*/
	public static final String ID="id";
	
	/**
	 * 主键 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.Person,java.lang.String> ID_PROP = new BeanProperty(com.dt.platform.domain.hr.Person.class ,ID, java.lang.String.class, "主键", "主键", java.lang.String.class, null);
	
	/**
	 * 工号 , 类型: java.lang.String
	*/
	public static final String JOB_NUMBER="jobNumber";
	
	/**
	 * 工号 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.Person,java.lang.String> JOB_NUMBER_PROP = new BeanProperty(com.dt.platform.domain.hr.Person.class ,JOB_NUMBER, java.lang.String.class, "工号", "工号", java.lang.String.class, null);
	
	/**
	 * 姓名 , 类型: java.lang.String
	*/
	public static final String NAME="name";
	
	/**
	 * 姓名 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.Person,java.lang.String> NAME_PROP = new BeanProperty(com.dt.platform.domain.hr.Person.class ,NAME, java.lang.String.class, "姓名", "姓名", java.lang.String.class, null);
	
	/**
	 * 性别 , 类型: java.lang.String
	*/
	public static final String SEX_CODE="sexCode";
	
	/**
	 * 性别 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.Person,java.lang.String> SEX_CODE_PROP = new BeanProperty(com.dt.platform.domain.hr.Person.class ,SEX_CODE, java.lang.String.class, "性别", "性别", java.lang.String.class, null);
	
	/**
	 * 出生日期 , 类型: java.util.Date
	*/
	public static final String BIRTHDAY="birthday";
	
	/**
	 * 出生日期 , 类型: java.util.Date
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.Person,java.util.Date> BIRTHDAY_PROP = new BeanProperty(com.dt.platform.domain.hr.Person.class ,BIRTHDAY, java.util.Date.class, "出生日期", "出生日期", java.util.Date.class, null);
	
	/**
	 * 籍贯 , 类型: java.lang.String
	*/
	public static final String NATIVE_PLACE_CODE="nativePlaceCode";
	
	/**
	 * 籍贯 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.Person,java.lang.String> NATIVE_PLACE_CODE_PROP = new BeanProperty(com.dt.platform.domain.hr.Person.class ,NATIVE_PLACE_CODE, java.lang.String.class, "籍贯", "籍贯", java.lang.String.class, null);
	
	/**
	 * 民族 , 类型: java.lang.String
	*/
	public static final String NATION_CODE="nationCode";
	
	/**
	 * 民族 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.Person,java.lang.String> NATION_CODE_PROP = new BeanProperty(com.dt.platform.domain.hr.Person.class ,NATION_CODE, java.lang.String.class, "民族", "民族", java.lang.String.class, null);
	
	/**
	 * 婚姻状况 , 类型: java.lang.String
	*/
	public static final String MARITAL_STATUS="maritalStatus";
	
	/**
	 * 婚姻状况 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.Person,java.lang.String> MARITAL_STATUS_PROP = new BeanProperty(com.dt.platform.domain.hr.Person.class ,MARITAL_STATUS, java.lang.String.class, "婚姻状况", "婚姻状况", java.lang.String.class, null);
	
	/**
	 * 员工状态 , 类型: java.lang.String
	*/
	public static final String EMPLOYEE_STATUS="employeeStatus";
	
	/**
	 * 员工状态 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.Person,java.lang.String> EMPLOYEE_STATUS_PROP = new BeanProperty(com.dt.platform.domain.hr.Person.class ,EMPLOYEE_STATUS, java.lang.String.class, "员工状态", "员工状态", java.lang.String.class, null);
	
	/**
	 * 人员类型 , 类型: java.lang.String
	*/
	public static final String EMPLOYEE_TYPE_CODE="employeeTypeCode";
	
	/**
	 * 人员类型 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.Person,java.lang.String> EMPLOYEE_TYPE_CODE_PROP = new BeanProperty(com.dt.platform.domain.hr.Person.class ,EMPLOYEE_TYPE_CODE, java.lang.String.class, "人员类型", "人员类型", java.lang.String.class, null);
	
	/**
	 * 身份证 , 类型: java.lang.String
	*/
	public static final String IDENTITY_CARD="identityCard";
	
	/**
	 * 身份证 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.Person,java.lang.String> IDENTITY_CARD_PROP = new BeanProperty(com.dt.platform.domain.hr.Person.class ,IDENTITY_CARD, java.lang.String.class, "身份证", "身份证", java.lang.String.class, null);
	
	/**
	 * 联系方式 , 类型: java.lang.String
	*/
	public static final String CONTACT_INFORMATION="contactInformation";
	
	/**
	 * 联系方式 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.Person,java.lang.String> CONTACT_INFORMATION_PROP = new BeanProperty(com.dt.platform.domain.hr.Person.class ,CONTACT_INFORMATION, java.lang.String.class, "联系方式", "联系方式", java.lang.String.class, null);
	
	/**
	 * 电子邮件 , 类型: java.lang.String
	*/
	public static final String EMAIL="email";
	
	/**
	 * 电子邮件 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.Person,java.lang.String> EMAIL_PROP = new BeanProperty(com.dt.platform.domain.hr.Person.class ,EMAIL, java.lang.String.class, "电子邮件", "电子邮件", java.lang.String.class, null);
	
	/**
	 * 紧急联系人 , 类型: java.lang.String
	*/
	public static final String EMERGENCY_CONTACT="emergencyContact";
	
	/**
	 * 紧急联系人 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.Person,java.lang.String> EMERGENCY_CONTACT_PROP = new BeanProperty(com.dt.platform.domain.hr.Person.class ,EMERGENCY_CONTACT, java.lang.String.class, "紧急联系人", "紧急联系人", java.lang.String.class, null);
	
	/**
	 * 紧急人电话 , 类型: java.lang.String
	*/
	public static final String EMERGENCY_CONTACT_NO="emergencyContactNo";
	
	/**
	 * 紧急人电话 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.Person,java.lang.String> EMERGENCY_CONTACT_NO_PROP = new BeanProperty(com.dt.platform.domain.hr.Person.class ,EMERGENCY_CONTACT_NO, java.lang.String.class, "紧急人电话", "紧急人电话", java.lang.String.class, null);
	
	/**
	 * 家庭地址 , 类型: java.lang.String
	*/
	public static final String HOME_ADDRESS="homeAddress";
	
	/**
	 * 家庭地址 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.Person,java.lang.String> HOME_ADDRESS_PROP = new BeanProperty(com.dt.platform.domain.hr.Person.class ,HOME_ADDRESS, java.lang.String.class, "家庭地址", "家庭地址", java.lang.String.class, null);
	
	/**
	 * 微信号 , 类型: java.lang.String
	*/
	public static final String WEIXIN_ID="weixinId";
	
	/**
	 * 微信号 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.Person,java.lang.String> WEIXIN_ID_PROP = new BeanProperty(com.dt.platform.domain.hr.Person.class ,WEIXIN_ID, java.lang.String.class, "微信号", "微信号", java.lang.String.class, null);
	
	/**
	 * 学历 , 类型: java.lang.String
	*/
	public static final String EDUCATION_CODE="educationCode";
	
	/**
	 * 学历 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.Person,java.lang.String> EDUCATION_CODE_PROP = new BeanProperty(com.dt.platform.domain.hr.Person.class ,EDUCATION_CODE, java.lang.String.class, "学历", "学历", java.lang.String.class, null);
	
	/**
	 * 毕业院校 , 类型: java.lang.String
	*/
	public static final String GRADUATION_SCHOOL="graduationSchool";
	
	/**
	 * 毕业院校 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.Person,java.lang.String> GRADUATION_SCHOOL_PROP = new BeanProperty(com.dt.platform.domain.hr.Person.class ,GRADUATION_SCHOOL, java.lang.String.class, "毕业院校", "毕业院校", java.lang.String.class, null);
	
	/**
	 * 所学专业 , 类型: java.lang.String
	*/
	public static final String MAJOR="major";
	
	/**
	 * 所学专业 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.Person,java.lang.String> MAJOR_PROP = new BeanProperty(com.dt.platform.domain.hr.Person.class ,MAJOR, java.lang.String.class, "所学专业", "所学专业", java.lang.String.class, null);
	
	/**
	 * 毕业时间 , 类型: java.util.Date
	*/
	public static final String GRADUATION_DATE="graduationDate";
	
	/**
	 * 毕业时间 , 类型: java.util.Date
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.Person,java.util.Date> GRADUATION_DATE_PROP = new BeanProperty(com.dt.platform.domain.hr.Person.class ,GRADUATION_DATE, java.util.Date.class, "毕业时间", "毕业时间", java.util.Date.class, null);
	
	/**
	 * 所学外语 , 类型: java.lang.String
	*/
	public static final String FOREIGN_LANGUAGE="foreignLanguage";
	
	/**
	 * 所学外语 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.Person,java.lang.String> FOREIGN_LANGUAGE_PROP = new BeanProperty(com.dt.platform.domain.hr.Person.class ,FOREIGN_LANGUAGE, java.lang.String.class, "所学外语", "所学外语", java.lang.String.class, null);
	
	/**
	 * 外语证书 , 类型: java.lang.String
	*/
	public static final String FOREIGN_LANGUAGE_LEVEL="foreignLanguageLevel";
	
	/**
	 * 外语证书 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.Person,java.lang.String> FOREIGN_LANGUAGE_LEVEL_PROP = new BeanProperty(com.dt.platform.domain.hr.Person.class ,FOREIGN_LANGUAGE_LEVEL, java.lang.String.class, "外语证书", "外语证书", java.lang.String.class, null);
	
	/**
	 * 计算机能力 , 类型: java.lang.String
	*/
	public static final String COMPUTER_ABILITY="computerAbility";
	
	/**
	 * 计算机能力 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.Person,java.lang.String> COMPUTER_ABILITY_PROP = new BeanProperty(com.dt.platform.domain.hr.Person.class ,COMPUTER_ABILITY, java.lang.String.class, "计算机能力", "计算机能力", java.lang.String.class, null);
	
	/**
	 * 计算机等级 , 类型: java.lang.String
	*/
	public static final String COMPUTER_LEVEL="computerLevel";
	
	/**
	 * 计算机等级 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.Person,java.lang.String> COMPUTER_LEVEL_PROP = new BeanProperty(com.dt.platform.domain.hr.Person.class ,COMPUTER_LEVEL, java.lang.String.class, "计算机等级", "计算机等级", java.lang.String.class, null);
	
	/**
	 * 政治面貌 , 类型: java.lang.String
	*/
	public static final String POLITIC_COUNTENANCE_CODE="politicCountenanceCode";
	
	/**
	 * 政治面貌 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.Person,java.lang.String> POLITIC_COUNTENANCE_CODE_PROP = new BeanProperty(com.dt.platform.domain.hr.Person.class ,POLITIC_COUNTENANCE_CODE, java.lang.String.class, "政治面貌", "政治面貌", java.lang.String.class, null);
	
	/**
	 * 入党时间 , 类型: java.util.Date
	*/
	public static final String JOIN_PART_DATE="joinPartDate";
	
	/**
	 * 入党时间 , 类型: java.util.Date
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.Person,java.util.Date> JOIN_PART_DATE_PROP = new BeanProperty(com.dt.platform.domain.hr.Person.class ,JOIN_PART_DATE, java.util.Date.class, "入党时间", "入党时间", java.util.Date.class, null);
	
	/**
	 * 血型 , 类型: java.lang.String
	*/
	public static final String BLOOD_TYPE="bloodType";
	
	/**
	 * 血型 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.Person,java.lang.String> BLOOD_TYPE_PROP = new BeanProperty(com.dt.platform.domain.hr.Person.class ,BLOOD_TYPE, java.lang.String.class, "血型", "血型", java.lang.String.class, null);
	
	/**
	 * 身高 , 类型: java.lang.Integer
	*/
	public static final String BODY_HEIGHT="bodyHeight";
	
	/**
	 * 身高 , 类型: java.lang.Integer
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.Person,java.lang.Integer> BODY_HEIGHT_PROP = new BeanProperty(com.dt.platform.domain.hr.Person.class ,BODY_HEIGHT, java.lang.Integer.class, "身高", "身高", java.lang.Integer.class, null);
	
	/**
	 * 体重 , 类型: java.lang.Integer
	*/
	public static final String BODY_WEIGHT="bodyWeight";
	
	/**
	 * 体重 , 类型: java.lang.Integer
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.Person,java.lang.Integer> BODY_WEIGHT_PROP = new BeanProperty(com.dt.platform.domain.hr.Person.class ,BODY_WEIGHT, java.lang.Integer.class, "体重", "体重", java.lang.Integer.class, null);
	
	/**
	 * 入职日期 , 类型: java.util.Date
	*/
	public static final String EMPLOYMENT_DATE="employmentDate";
	
	/**
	 * 入职日期 , 类型: java.util.Date
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.Person,java.util.Date> EMPLOYMENT_DATE_PROP = new BeanProperty(com.dt.platform.domain.hr.Person.class ,EMPLOYMENT_DATE, java.util.Date.class, "入职日期", "入职日期", java.util.Date.class, null);
	
	/**
	 * 转正日期 , 类型: java.util.Date
	*/
	public static final String EMPLOYMENT_CONFIRM_DATE="employmentConfirmDate";
	
	/**
	 * 转正日期 , 类型: java.util.Date
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.Person,java.util.Date> EMPLOYMENT_CONFIRM_DATE_PROP = new BeanProperty(com.dt.platform.domain.hr.Person.class ,EMPLOYMENT_CONFIRM_DATE, java.util.Date.class, "转正日期", "转正日期", java.util.Date.class, null);
	
	/**
	 * 初次日期 , 类型: java.util.Date
	*/
	public static final String FIRST_EMPLOYMENT_DATE="firstEmploymentDate";
	
	/**
	 * 初次日期 , 类型: java.util.Date
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.Person,java.util.Date> FIRST_EMPLOYMENT_DATE_PROP = new BeanProperty(com.dt.platform.domain.hr.Person.class ,FIRST_EMPLOYMENT_DATE, java.util.Date.class, "初次日期", "初次日期", java.util.Date.class, null);
	
	/**
	 * 参加工作时间 , 类型: java.util.Date
	*/
	public static final String FIRST_WORK_DATE="firstWorkDate";
	
	/**
	 * 参加工作时间 , 类型: java.util.Date
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.Person,java.util.Date> FIRST_WORK_DATE_PROP = new BeanProperty(com.dt.platform.domain.hr.Person.class ,FIRST_WORK_DATE, java.util.Date.class, "参加工作时间", "参加工作时间", java.util.Date.class, null);
	
	/**
	 * 员工部门 , 类型: java.lang.String
	*/
	public static final String ORG_ID="orgId";
	
	/**
	 * 员工部门 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.Person,java.lang.String> ORG_ID_PROP = new BeanProperty(com.dt.platform.domain.hr.Person.class ,ORG_ID, java.lang.String.class, "员工部门", "员工部门", java.lang.String.class, null);
	
	/**
	 * 员工岗位 , 类型: java.lang.String
	*/
	public static final String POSITION_CODE="positionCode";
	
	/**
	 * 员工岗位 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.Person,java.lang.String> POSITION_CODE_PROP = new BeanProperty(com.dt.platform.domain.hr.Person.class ,POSITION_CODE, java.lang.String.class, "员工岗位", "员工岗位", java.lang.String.class, null);
	
	/**
	 * 员工职称 , 类型: java.lang.String
	*/
	public static final String EMPLOYEE_TITLE_CODE="employeeTitleCode";
	
	/**
	 * 员工职称 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.Person,java.lang.String> EMPLOYEE_TITLE_CODE_PROP = new BeanProperty(com.dt.platform.domain.hr.Person.class ,EMPLOYEE_TITLE_CODE, java.lang.String.class, "员工职称", "员工职称", java.lang.String.class, null);
	
	/**
	 * 员工职级 , 类型: java.lang.String
	*/
	public static final String RANK_CODE="rankCode";
	
	/**
	 * 员工职级 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.Person,java.lang.String> RANK_CODE_PROP = new BeanProperty(com.dt.platform.domain.hr.Person.class ,RANK_CODE, java.lang.String.class, "员工职级", "员工职级", java.lang.String.class, null);
	
	/**
	 * 员工工种 , 类型: java.lang.String
	*/
	public static final String WORK_KIND_CODE="workKindCode";
	
	/**
	 * 员工工种 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.Person,java.lang.String> WORK_KIND_CODE_PROP = new BeanProperty(com.dt.platform.domain.hr.Person.class ,WORK_KIND_CODE, java.lang.String.class, "员工工种", "员工工种", java.lang.String.class, null);
	
	/**
	 * 离职日期 , 类型: java.util.Date
	*/
	public static final String LEAVE_DATE="leaveDate";
	
	/**
	 * 离职日期 , 类型: java.util.Date
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.Person,java.util.Date> LEAVE_DATE_PROP = new BeanProperty(com.dt.platform.domain.hr.Person.class ,LEAVE_DATE, java.util.Date.class, "离职日期", "离职日期", java.util.Date.class, null);
	
	/**
	 * 离职原因 , 类型: java.lang.String
	*/
	public static final String LEAVE_RESON="leaveReson";
	
	/**
	 * 离职原因 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.Person,java.lang.String> LEAVE_RESON_PROP = new BeanProperty(com.dt.platform.domain.hr.Person.class ,LEAVE_RESON, java.lang.String.class, "离职原因", "离职原因", java.lang.String.class, null);
	
	/**
	 * 工资卡开户行 , 类型: java.lang.String
	*/
	public static final String PAYROLL_CARD_BANK_CODE="payrollCardBankCode";
	
	/**
	 * 工资卡开户行 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.Person,java.lang.String> PAYROLL_CARD_BANK_CODE_PROP = new BeanProperty(com.dt.platform.domain.hr.Person.class ,PAYROLL_CARD_BANK_CODE, java.lang.String.class, "工资卡开户行", "工资卡开户行", java.lang.String.class, null);
	
	/**
	 * 工资卡帐号 , 类型: java.lang.String
	*/
	public static final String PAYROLL_CARD="payrollCard";
	
	/**
	 * 工资卡帐号 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.Person,java.lang.String> PAYROLL_CARD_PROP = new BeanProperty(com.dt.platform.domain.hr.Person.class ,PAYROLL_CARD, java.lang.String.class, "工资卡帐号", "工资卡帐号", java.lang.String.class, null);
	
	/**
	 * 照片 , 类型: java.lang.String
	*/
	public static final String PERSON_PICTURE_ID="personPictureId";
	
	/**
	 * 照片 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.Person,java.lang.String> PERSON_PICTURE_ID_PROP = new BeanProperty(com.dt.platform.domain.hr.Person.class ,PERSON_PICTURE_ID, java.lang.String.class, "照片", "照片", java.lang.String.class, null);
	
	/**
	 * 员工 , 类型: java.lang.String
	*/
	public static final String EMPLOYEE_ID="employeeId";
	
	/**
	 * 员工 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.Person,java.lang.String> EMPLOYEE_ID_PROP = new BeanProperty(com.dt.platform.domain.hr.Person.class ,EMPLOYEE_ID, java.lang.String.class, "员工", "员工", java.lang.String.class, null);
	
	/**
	 * 附件 , 类型: java.lang.String
	*/
	public static final String FILE_ID="fileId";
	
	/**
	 * 附件 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.Person,java.lang.String> FILE_ID_PROP = new BeanProperty(com.dt.platform.domain.hr.Person.class ,FILE_ID, java.lang.String.class, "附件", "附件", java.lang.String.class, null);
	
	/**
	 * 备注 , 类型: java.lang.String
	*/
	public static final String NOTE="note";
	
	/**
	 * 备注 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.Person,java.lang.String> NOTE_PROP = new BeanProperty(com.dt.platform.domain.hr.Person.class ,NOTE, java.lang.String.class, "备注", "备注", java.lang.String.class, null);
	
	/**
	 * 创建人ID , 类型: java.lang.String
	*/
	public static final String CREATE_BY="createBy";
	
	/**
	 * 创建人ID , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.Person,java.lang.String> CREATE_BY_PROP = new BeanProperty(com.dt.platform.domain.hr.Person.class ,CREATE_BY, java.lang.String.class, "创建人ID", "创建人ID", java.lang.String.class, null);
	
	/**
	 * 创建时间 , 类型: java.util.Date
	*/
	public static final String CREATE_TIME="createTime";
	
	/**
	 * 创建时间 , 类型: java.util.Date
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.Person,java.util.Date> CREATE_TIME_PROP = new BeanProperty(com.dt.platform.domain.hr.Person.class ,CREATE_TIME, java.util.Date.class, "创建时间", "创建时间", java.util.Date.class, null);
	
	/**
	 * 修改人ID , 类型: java.lang.String
	*/
	public static final String UPDATE_BY="updateBy";
	
	/**
	 * 修改人ID , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.Person,java.lang.String> UPDATE_BY_PROP = new BeanProperty(com.dt.platform.domain.hr.Person.class ,UPDATE_BY, java.lang.String.class, "修改人ID", "修改人ID", java.lang.String.class, null);
	
	/**
	 * 修改时间 , 类型: java.util.Date
	*/
	public static final String UPDATE_TIME="updateTime";
	
	/**
	 * 修改时间 , 类型: java.util.Date
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.Person,java.util.Date> UPDATE_TIME_PROP = new BeanProperty(com.dt.platform.domain.hr.Person.class ,UPDATE_TIME, java.util.Date.class, "修改时间", "修改时间", java.util.Date.class, null);
	
	/**
	 * 是否已删除 , 类型: java.lang.Integer
	*/
	public static final String DELETED="deleted";
	
	/**
	 * 是否已删除 , 类型: java.lang.Integer
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.Person,java.lang.Integer> DELETED_PROP = new BeanProperty(com.dt.platform.domain.hr.Person.class ,DELETED, java.lang.Integer.class, "是否已删除", "是否已删除", java.lang.Integer.class, null);
	
	/**
	 * 删除人ID , 类型: java.lang.String
	*/
	public static final String DELETE_BY="deleteBy";
	
	/**
	 * 删除人ID , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.Person,java.lang.String> DELETE_BY_PROP = new BeanProperty(com.dt.platform.domain.hr.Person.class ,DELETE_BY, java.lang.String.class, "删除人ID", "删除人ID", java.lang.String.class, null);
	
	/**
	 * 删除时间 , 类型: java.util.Date
	*/
	public static final String DELETE_TIME="deleteTime";
	
	/**
	 * 删除时间 , 类型: java.util.Date
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.Person,java.util.Date> DELETE_TIME_PROP = new BeanProperty(com.dt.platform.domain.hr.Person.class ,DELETE_TIME, java.util.Date.class, "删除时间", "删除时间", java.util.Date.class, null);
	
	/**
	 * version , 类型: java.lang.Integer
	*/
	public static final String VERSION="version";
	
	/**
	 * version , 类型: java.lang.Integer
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.Person,java.lang.Integer> VERSION_PROP = new BeanProperty(com.dt.platform.domain.hr.Person.class ,VERSION, java.lang.Integer.class, "version", "version", java.lang.Integer.class, null);
	
	/**
	 * 租户 , 类型: java.lang.String
	*/
	public static final String TENANT_ID="tenantId";
	
	/**
	 * 租户 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.Person,java.lang.String> TENANT_ID_PROP = new BeanProperty(com.dt.platform.domain.hr.Person.class ,TENANT_ID, java.lang.String.class, "租户", "租户", java.lang.String.class, null);
	
	/**
	 * position , 类型: com.dt.platform.domain.hr.Position
	*/
	public static final String POSITION="position";
	
	/**
	 * position , 类型: com.dt.platform.domain.hr.Position
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.Person,com.dt.platform.domain.hr.Position> POSITION_PROP = new BeanProperty(com.dt.platform.domain.hr.Person.class ,POSITION, com.dt.platform.domain.hr.Position.class, "position", "position", com.dt.platform.domain.hr.Position.class, null);
	
	/**
	 * rank , 类型: com.dt.platform.domain.hr.Rank
	*/
	public static final String RANK="rank";
	
	/**
	 * rank , 类型: com.dt.platform.domain.hr.Rank
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.Person,com.dt.platform.domain.hr.Rank> RANK_PROP = new BeanProperty(com.dt.platform.domain.hr.Person.class ,RANK, com.dt.platform.domain.hr.Rank.class, "rank", "rank", com.dt.platform.domain.hr.Rank.class, null);
	
	/**
	 * professionalLevel , 类型: com.dt.platform.domain.hr.ProfessionalLevel
	*/
	public static final String PROFESSIONAL_LEVEL="professionalLevel";
	
	/**
	 * professionalLevel , 类型: com.dt.platform.domain.hr.ProfessionalLevel
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.Person,com.dt.platform.domain.hr.ProfessionalLevel> PROFESSIONAL_LEVEL_PROP = new BeanProperty(com.dt.platform.domain.hr.Person.class ,PROFESSIONAL_LEVEL, com.dt.platform.domain.hr.ProfessionalLevel.class, "professionalLevel", "professionalLevel", com.dt.platform.domain.hr.ProfessionalLevel.class, null);
	
	/**
	 * bloodTypeDict , 类型: org.github.foxnic.web.domain.system.DictItem
	*/
	public static final String BLOOD_TYPE_DICT="bloodTypeDict";
	
	/**
	 * bloodTypeDict , 类型: org.github.foxnic.web.domain.system.DictItem
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.Person,org.github.foxnic.web.domain.system.DictItem> BLOOD_TYPE_DICT_PROP = new BeanProperty(com.dt.platform.domain.hr.Person.class ,BLOOD_TYPE_DICT, org.github.foxnic.web.domain.system.DictItem.class, "bloodTypeDict", "bloodTypeDict", org.github.foxnic.web.domain.system.DictItem.class, null);
	
	/**
	 * sexDict , 类型: org.github.foxnic.web.domain.system.DictItem
	*/
	public static final String SEX_DICT="sexDict";
	
	/**
	 * sexDict , 类型: org.github.foxnic.web.domain.system.DictItem
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.Person,org.github.foxnic.web.domain.system.DictItem> SEX_DICT_PROP = new BeanProperty(com.dt.platform.domain.hr.Person.class ,SEX_DICT, org.github.foxnic.web.domain.system.DictItem.class, "sexDict", "sexDict", org.github.foxnic.web.domain.system.DictItem.class, null);
	
	/**
	 * maritalStatusDict , 类型: org.github.foxnic.web.domain.system.DictItem
	*/
	public static final String MARITAL_STATUS_DICT="maritalStatusDict";
	
	/**
	 * maritalStatusDict , 类型: org.github.foxnic.web.domain.system.DictItem
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.Person,org.github.foxnic.web.domain.system.DictItem> MARITAL_STATUS_DICT_PROP = new BeanProperty(com.dt.platform.domain.hr.Person.class ,MARITAL_STATUS_DICT, org.github.foxnic.web.domain.system.DictItem.class, "maritalStatusDict", "maritalStatusDict", org.github.foxnic.web.domain.system.DictItem.class, null);
	
	/**
	 * employeeOwnerType , 类型: org.github.foxnic.web.domain.system.DictItem
	*/
	public static final String EMPLOYEE_OWNER_TYPE_DICT="employeeOwnerTypeDict";
	
	/**
	 * employeeOwnerType , 类型: org.github.foxnic.web.domain.system.DictItem
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.Person,org.github.foxnic.web.domain.system.DictItem> EMPLOYEE_OWNER_TYPE_DICT_PROP = new BeanProperty(com.dt.platform.domain.hr.Person.class ,EMPLOYEE_OWNER_TYPE_DICT, org.github.foxnic.web.domain.system.DictItem.class, "employeeOwnerType", "employeeOwnerType", org.github.foxnic.web.domain.system.DictItem.class, null);
	
	/**
	 * employee , 类型: org.github.foxnic.web.domain.hrm.Employee
	*/
	public static final String EMPLOYEE="employee";
	
	/**
	 * employee , 类型: org.github.foxnic.web.domain.hrm.Employee
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.Person,org.github.foxnic.web.domain.hrm.Employee> EMPLOYEE_PROP = new BeanProperty(com.dt.platform.domain.hr.Person.class ,EMPLOYEE, org.github.foxnic.web.domain.hrm.Employee.class, "employee", "employee", org.github.foxnic.web.domain.hrm.Employee.class, null);
	
	/**
	 * 全部属性清单
	*/
	public static final String[] $PROPS={ ID , JOB_NUMBER , NAME , SEX_CODE , BIRTHDAY , NATIVE_PLACE_CODE , NATION_CODE , MARITAL_STATUS , EMPLOYEE_STATUS , EMPLOYEE_TYPE_CODE , IDENTITY_CARD , CONTACT_INFORMATION , EMAIL , EMERGENCY_CONTACT , EMERGENCY_CONTACT_NO , HOME_ADDRESS , WEIXIN_ID , EDUCATION_CODE , GRADUATION_SCHOOL , MAJOR , GRADUATION_DATE , FOREIGN_LANGUAGE , FOREIGN_LANGUAGE_LEVEL , COMPUTER_ABILITY , COMPUTER_LEVEL , POLITIC_COUNTENANCE_CODE , JOIN_PART_DATE , BLOOD_TYPE , BODY_HEIGHT , BODY_WEIGHT , EMPLOYMENT_DATE , EMPLOYMENT_CONFIRM_DATE , FIRST_EMPLOYMENT_DATE , FIRST_WORK_DATE , ORG_ID , POSITION_CODE , EMPLOYEE_TITLE_CODE , RANK_CODE , WORK_KIND_CODE , LEAVE_DATE , LEAVE_RESON , PAYROLL_CARD_BANK_CODE , PAYROLL_CARD , PERSON_PICTURE_ID , EMPLOYEE_ID , FILE_ID , NOTE , CREATE_BY , CREATE_TIME , UPDATE_BY , UPDATE_TIME , DELETED , DELETE_BY , DELETE_TIME , VERSION , TENANT_ID , POSITION , RANK , PROFESSIONAL_LEVEL , BLOOD_TYPE_DICT , SEX_DICT , MARITAL_STATUS_DICT , EMPLOYEE_OWNER_TYPE_DICT , EMPLOYEE };
	
	/**
	 * 代理类
	*/
	public static class $$proxy$$ extends com.dt.platform.domain.hr.Person {

		private static final long serialVersionUID = 1L;

		
		/**
		 * 设置 主键
		 * @param id 主键
		 * @return 当前对象
		*/
		public Person setId(String id) {
			super.change(ID,super.getId(),id);
			super.setId(id);
			return this;
		}
		
		/**
		 * 设置 工号
		 * @param jobNumber 工号
		 * @return 当前对象
		*/
		public Person setJobNumber(String jobNumber) {
			super.change(JOB_NUMBER,super.getJobNumber(),jobNumber);
			super.setJobNumber(jobNumber);
			return this;
		}
		
		/**
		 * 设置 姓名
		 * @param name 姓名
		 * @return 当前对象
		*/
		public Person setName(String name) {
			super.change(NAME,super.getName(),name);
			super.setName(name);
			return this;
		}
		
		/**
		 * 设置 性别
		 * @param sexCode 性别
		 * @return 当前对象
		*/
		public Person setSexCode(String sexCode) {
			super.change(SEX_CODE,super.getSexCode(),sexCode);
			super.setSexCode(sexCode);
			return this;
		}
		
		/**
		 * 设置 出生日期
		 * @param birthday 出生日期
		 * @return 当前对象
		*/
		public Person setBirthday(Date birthday) {
			super.change(BIRTHDAY,super.getBirthday(),birthday);
			super.setBirthday(birthday);
			return this;
		}
		
		/**
		 * 设置 籍贯
		 * @param nativePlaceCode 籍贯
		 * @return 当前对象
		*/
		public Person setNativePlaceCode(String nativePlaceCode) {
			super.change(NATIVE_PLACE_CODE,super.getNativePlaceCode(),nativePlaceCode);
			super.setNativePlaceCode(nativePlaceCode);
			return this;
		}
		
		/**
		 * 设置 民族
		 * @param nationCode 民族
		 * @return 当前对象
		*/
		public Person setNationCode(String nationCode) {
			super.change(NATION_CODE,super.getNationCode(),nationCode);
			super.setNationCode(nationCode);
			return this;
		}
		
		/**
		 * 设置 婚姻状况
		 * @param maritalStatus 婚姻状况
		 * @return 当前对象
		*/
		public Person setMaritalStatus(String maritalStatus) {
			super.change(MARITAL_STATUS,super.getMaritalStatus(),maritalStatus);
			super.setMaritalStatus(maritalStatus);
			return this;
		}
		
		/**
		 * 设置 员工状态
		 * @param employeeStatus 员工状态
		 * @return 当前对象
		*/
		public Person setEmployeeStatus(String employeeStatus) {
			super.change(EMPLOYEE_STATUS,super.getEmployeeStatus(),employeeStatus);
			super.setEmployeeStatus(employeeStatus);
			return this;
		}
		
		/**
		 * 设置 人员类型
		 * @param employeeTypeCode 人员类型
		 * @return 当前对象
		*/
		public Person setEmployeeTypeCode(String employeeTypeCode) {
			super.change(EMPLOYEE_TYPE_CODE,super.getEmployeeTypeCode(),employeeTypeCode);
			super.setEmployeeTypeCode(employeeTypeCode);
			return this;
		}
		
		/**
		 * 设置 身份证
		 * @param identityCard 身份证
		 * @return 当前对象
		*/
		public Person setIdentityCard(String identityCard) {
			super.change(IDENTITY_CARD,super.getIdentityCard(),identityCard);
			super.setIdentityCard(identityCard);
			return this;
		}
		
		/**
		 * 设置 联系方式
		 * @param contactInformation 联系方式
		 * @return 当前对象
		*/
		public Person setContactInformation(String contactInformation) {
			super.change(CONTACT_INFORMATION,super.getContactInformation(),contactInformation);
			super.setContactInformation(contactInformation);
			return this;
		}
		
		/**
		 * 设置 电子邮件
		 * @param email 电子邮件
		 * @return 当前对象
		*/
		public Person setEmail(String email) {
			super.change(EMAIL,super.getEmail(),email);
			super.setEmail(email);
			return this;
		}
		
		/**
		 * 设置 紧急联系人
		 * @param emergencyContact 紧急联系人
		 * @return 当前对象
		*/
		public Person setEmergencyContact(String emergencyContact) {
			super.change(EMERGENCY_CONTACT,super.getEmergencyContact(),emergencyContact);
			super.setEmergencyContact(emergencyContact);
			return this;
		}
		
		/**
		 * 设置 紧急人电话
		 * @param emergencyContactNo 紧急人电话
		 * @return 当前对象
		*/
		public Person setEmergencyContactNo(String emergencyContactNo) {
			super.change(EMERGENCY_CONTACT_NO,super.getEmergencyContactNo(),emergencyContactNo);
			super.setEmergencyContactNo(emergencyContactNo);
			return this;
		}
		
		/**
		 * 设置 家庭地址
		 * @param homeAddress 家庭地址
		 * @return 当前对象
		*/
		public Person setHomeAddress(String homeAddress) {
			super.change(HOME_ADDRESS,super.getHomeAddress(),homeAddress);
			super.setHomeAddress(homeAddress);
			return this;
		}
		
		/**
		 * 设置 微信号
		 * @param weixinId 微信号
		 * @return 当前对象
		*/
		public Person setWeixinId(String weixinId) {
			super.change(WEIXIN_ID,super.getWeixinId(),weixinId);
			super.setWeixinId(weixinId);
			return this;
		}
		
		/**
		 * 设置 学历
		 * @param educationCode 学历
		 * @return 当前对象
		*/
		public Person setEducationCode(String educationCode) {
			super.change(EDUCATION_CODE,super.getEducationCode(),educationCode);
			super.setEducationCode(educationCode);
			return this;
		}
		
		/**
		 * 设置 毕业院校
		 * @param graduationSchool 毕业院校
		 * @return 当前对象
		*/
		public Person setGraduationSchool(String graduationSchool) {
			super.change(GRADUATION_SCHOOL,super.getGraduationSchool(),graduationSchool);
			super.setGraduationSchool(graduationSchool);
			return this;
		}
		
		/**
		 * 设置 所学专业
		 * @param major 所学专业
		 * @return 当前对象
		*/
		public Person setMajor(String major) {
			super.change(MAJOR,super.getMajor(),major);
			super.setMajor(major);
			return this;
		}
		
		/**
		 * 设置 毕业时间
		 * @param graduationDate 毕业时间
		 * @return 当前对象
		*/
		public Person setGraduationDate(Date graduationDate) {
			super.change(GRADUATION_DATE,super.getGraduationDate(),graduationDate);
			super.setGraduationDate(graduationDate);
			return this;
		}
		
		/**
		 * 设置 所学外语
		 * @param foreignLanguage 所学外语
		 * @return 当前对象
		*/
		public Person setForeignLanguage(String foreignLanguage) {
			super.change(FOREIGN_LANGUAGE,super.getForeignLanguage(),foreignLanguage);
			super.setForeignLanguage(foreignLanguage);
			return this;
		}
		
		/**
		 * 设置 外语证书
		 * @param foreignLanguageLevel 外语证书
		 * @return 当前对象
		*/
		public Person setForeignLanguageLevel(String foreignLanguageLevel) {
			super.change(FOREIGN_LANGUAGE_LEVEL,super.getForeignLanguageLevel(),foreignLanguageLevel);
			super.setForeignLanguageLevel(foreignLanguageLevel);
			return this;
		}
		
		/**
		 * 设置 计算机能力
		 * @param computerAbility 计算机能力
		 * @return 当前对象
		*/
		public Person setComputerAbility(String computerAbility) {
			super.change(COMPUTER_ABILITY,super.getComputerAbility(),computerAbility);
			super.setComputerAbility(computerAbility);
			return this;
		}
		
		/**
		 * 设置 计算机等级
		 * @param computerLevel 计算机等级
		 * @return 当前对象
		*/
		public Person setComputerLevel(String computerLevel) {
			super.change(COMPUTER_LEVEL,super.getComputerLevel(),computerLevel);
			super.setComputerLevel(computerLevel);
			return this;
		}
		
		/**
		 * 设置 政治面貌
		 * @param politicCountenanceCode 政治面貌
		 * @return 当前对象
		*/
		public Person setPoliticCountenanceCode(String politicCountenanceCode) {
			super.change(POLITIC_COUNTENANCE_CODE,super.getPoliticCountenanceCode(),politicCountenanceCode);
			super.setPoliticCountenanceCode(politicCountenanceCode);
			return this;
		}
		
		/**
		 * 设置 入党时间
		 * @param joinPartDate 入党时间
		 * @return 当前对象
		*/
		public Person setJoinPartDate(Date joinPartDate) {
			super.change(JOIN_PART_DATE,super.getJoinPartDate(),joinPartDate);
			super.setJoinPartDate(joinPartDate);
			return this;
		}
		
		/**
		 * 设置 血型
		 * @param bloodType 血型
		 * @return 当前对象
		*/
		public Person setBloodType(String bloodType) {
			super.change(BLOOD_TYPE,super.getBloodType(),bloodType);
			super.setBloodType(bloodType);
			return this;
		}
		
		/**
		 * 设置 身高
		 * @param bodyHeight 身高
		 * @return 当前对象
		*/
		public Person setBodyHeight(Integer bodyHeight) {
			super.change(BODY_HEIGHT,super.getBodyHeight(),bodyHeight);
			super.setBodyHeight(bodyHeight);
			return this;
		}
		
		/**
		 * 设置 体重
		 * @param bodyWeight 体重
		 * @return 当前对象
		*/
		public Person setBodyWeight(Integer bodyWeight) {
			super.change(BODY_WEIGHT,super.getBodyWeight(),bodyWeight);
			super.setBodyWeight(bodyWeight);
			return this;
		}
		
		/**
		 * 设置 入职日期
		 * @param employmentDate 入职日期
		 * @return 当前对象
		*/
		public Person setEmploymentDate(Date employmentDate) {
			super.change(EMPLOYMENT_DATE,super.getEmploymentDate(),employmentDate);
			super.setEmploymentDate(employmentDate);
			return this;
		}
		
		/**
		 * 设置 转正日期
		 * @param employmentConfirmDate 转正日期
		 * @return 当前对象
		*/
		public Person setEmploymentConfirmDate(Date employmentConfirmDate) {
			super.change(EMPLOYMENT_CONFIRM_DATE,super.getEmploymentConfirmDate(),employmentConfirmDate);
			super.setEmploymentConfirmDate(employmentConfirmDate);
			return this;
		}
		
		/**
		 * 设置 初次日期
		 * @param firstEmploymentDate 初次日期
		 * @return 当前对象
		*/
		public Person setFirstEmploymentDate(Date firstEmploymentDate) {
			super.change(FIRST_EMPLOYMENT_DATE,super.getFirstEmploymentDate(),firstEmploymentDate);
			super.setFirstEmploymentDate(firstEmploymentDate);
			return this;
		}
		
		/**
		 * 设置 参加工作时间
		 * @param firstWorkDate 参加工作时间
		 * @return 当前对象
		*/
		public Person setFirstWorkDate(Date firstWorkDate) {
			super.change(FIRST_WORK_DATE,super.getFirstWorkDate(),firstWorkDate);
			super.setFirstWorkDate(firstWorkDate);
			return this;
		}
		
		/**
		 * 设置 员工部门
		 * @param orgId 员工部门
		 * @return 当前对象
		*/
		public Person setOrgId(String orgId) {
			super.change(ORG_ID,super.getOrgId(),orgId);
			super.setOrgId(orgId);
			return this;
		}
		
		/**
		 * 设置 员工岗位
		 * @param positionCode 员工岗位
		 * @return 当前对象
		*/
		public Person setPositionCode(String positionCode) {
			super.change(POSITION_CODE,super.getPositionCode(),positionCode);
			super.setPositionCode(positionCode);
			return this;
		}
		
		/**
		 * 设置 员工职称
		 * @param employeeTitleCode 员工职称
		 * @return 当前对象
		*/
		public Person setEmployeeTitleCode(String employeeTitleCode) {
			super.change(EMPLOYEE_TITLE_CODE,super.getEmployeeTitleCode(),employeeTitleCode);
			super.setEmployeeTitleCode(employeeTitleCode);
			return this;
		}
		
		/**
		 * 设置 员工职级
		 * @param rankCode 员工职级
		 * @return 当前对象
		*/
		public Person setRankCode(String rankCode) {
			super.change(RANK_CODE,super.getRankCode(),rankCode);
			super.setRankCode(rankCode);
			return this;
		}
		
		/**
		 * 设置 员工工种
		 * @param workKindCode 员工工种
		 * @return 当前对象
		*/
		public Person setWorkKindCode(String workKindCode) {
			super.change(WORK_KIND_CODE,super.getWorkKindCode(),workKindCode);
			super.setWorkKindCode(workKindCode);
			return this;
		}
		
		/**
		 * 设置 离职日期
		 * @param leaveDate 离职日期
		 * @return 当前对象
		*/
		public Person setLeaveDate(Date leaveDate) {
			super.change(LEAVE_DATE,super.getLeaveDate(),leaveDate);
			super.setLeaveDate(leaveDate);
			return this;
		}
		
		/**
		 * 设置 离职原因
		 * @param leaveReson 离职原因
		 * @return 当前对象
		*/
		public Person setLeaveReson(String leaveReson) {
			super.change(LEAVE_RESON,super.getLeaveReson(),leaveReson);
			super.setLeaveReson(leaveReson);
			return this;
		}
		
		/**
		 * 设置 工资卡开户行
		 * @param payrollCardBankCode 工资卡开户行
		 * @return 当前对象
		*/
		public Person setPayrollCardBankCode(String payrollCardBankCode) {
			super.change(PAYROLL_CARD_BANK_CODE,super.getPayrollCardBankCode(),payrollCardBankCode);
			super.setPayrollCardBankCode(payrollCardBankCode);
			return this;
		}
		
		/**
		 * 设置 工资卡帐号
		 * @param payrollCard 工资卡帐号
		 * @return 当前对象
		*/
		public Person setPayrollCard(String payrollCard) {
			super.change(PAYROLL_CARD,super.getPayrollCard(),payrollCard);
			super.setPayrollCard(payrollCard);
			return this;
		}
		
		/**
		 * 设置 照片
		 * @param personPictureId 照片
		 * @return 当前对象
		*/
		public Person setPersonPictureId(String personPictureId) {
			super.change(PERSON_PICTURE_ID,super.getPersonPictureId(),personPictureId);
			super.setPersonPictureId(personPictureId);
			return this;
		}
		
		/**
		 * 设置 员工
		 * @param employeeId 员工
		 * @return 当前对象
		*/
		public Person setEmployeeId(String employeeId) {
			super.change(EMPLOYEE_ID,super.getEmployeeId(),employeeId);
			super.setEmployeeId(employeeId);
			return this;
		}
		
		/**
		 * 设置 附件
		 * @param fileId 附件
		 * @return 当前对象
		*/
		public Person setFileId(String fileId) {
			super.change(FILE_ID,super.getFileId(),fileId);
			super.setFileId(fileId);
			return this;
		}
		
		/**
		 * 设置 备注
		 * @param note 备注
		 * @return 当前对象
		*/
		public Person setNote(String note) {
			super.change(NOTE,super.getNote(),note);
			super.setNote(note);
			return this;
		}
		
		/**
		 * 设置 创建人ID
		 * @param createBy 创建人ID
		 * @return 当前对象
		*/
		public Person setCreateBy(String createBy) {
			super.change(CREATE_BY,super.getCreateBy(),createBy);
			super.setCreateBy(createBy);
			return this;
		}
		
		/**
		 * 设置 创建时间
		 * @param createTime 创建时间
		 * @return 当前对象
		*/
		public Person setCreateTime(Date createTime) {
			super.change(CREATE_TIME,super.getCreateTime(),createTime);
			super.setCreateTime(createTime);
			return this;
		}
		
		/**
		 * 设置 修改人ID
		 * @param updateBy 修改人ID
		 * @return 当前对象
		*/
		public Person setUpdateBy(String updateBy) {
			super.change(UPDATE_BY,super.getUpdateBy(),updateBy);
			super.setUpdateBy(updateBy);
			return this;
		}
		
		/**
		 * 设置 修改时间
		 * @param updateTime 修改时间
		 * @return 当前对象
		*/
		public Person setUpdateTime(Date updateTime) {
			super.change(UPDATE_TIME,super.getUpdateTime(),updateTime);
			super.setUpdateTime(updateTime);
			return this;
		}
		
		/**
		 * 设置 是否已删除
		 * @param deleted 是否已删除
		 * @return 当前对象
		*/
		public Person setDeleted(Integer deleted) {
			super.change(DELETED,super.getDeleted(),deleted);
			super.setDeleted(deleted);
			return this;
		}
		
		/**
		 * 设置 删除人ID
		 * @param deleteBy 删除人ID
		 * @return 当前对象
		*/
		public Person setDeleteBy(String deleteBy) {
			super.change(DELETE_BY,super.getDeleteBy(),deleteBy);
			super.setDeleteBy(deleteBy);
			return this;
		}
		
		/**
		 * 设置 删除时间
		 * @param deleteTime 删除时间
		 * @return 当前对象
		*/
		public Person setDeleteTime(Date deleteTime) {
			super.change(DELETE_TIME,super.getDeleteTime(),deleteTime);
			super.setDeleteTime(deleteTime);
			return this;
		}
		
		/**
		 * 设置 version
		 * @param version version
		 * @return 当前对象
		*/
		public Person setVersion(Integer version) {
			super.change(VERSION,super.getVersion(),version);
			super.setVersion(version);
			return this;
		}
		
		/**
		 * 设置 租户
		 * @param tenantId 租户
		 * @return 当前对象
		*/
		public Person setTenantId(String tenantId) {
			super.change(TENANT_ID,super.getTenantId(),tenantId);
			super.setTenantId(tenantId);
			return this;
		}
		
		/**
		 * 设置 position
		 * @param position position
		 * @return 当前对象
		*/
		public Person setPosition(Position position) {
			super.change(POSITION,super.getPosition(),position);
			super.setPosition(position);
			return this;
		}
		
		/**
		 * 设置 rank
		 * @param rank rank
		 * @return 当前对象
		*/
		public Person setRank(Rank rank) {
			super.change(RANK,super.getRank(),rank);
			super.setRank(rank);
			return this;
		}
		
		/**
		 * 设置 professionalLevel
		 * @param professionalLevel professionalLevel
		 * @return 当前对象
		*/
		public Person setProfessionalLevel(ProfessionalLevel professionalLevel) {
			super.change(PROFESSIONAL_LEVEL,super.getProfessionalLevel(),professionalLevel);
			super.setProfessionalLevel(professionalLevel);
			return this;
		}
		
		/**
		 * 设置 bloodTypeDict
		 * @param bloodTypeDict bloodTypeDict
		 * @return 当前对象
		*/
		public Person setBloodTypeDict(DictItem bloodTypeDict) {
			super.change(BLOOD_TYPE_DICT,super.getBloodTypeDict(),bloodTypeDict);
			super.setBloodTypeDict(bloodTypeDict);
			return this;
		}
		
		/**
		 * 设置 sexDict
		 * @param sexDict sexDict
		 * @return 当前对象
		*/
		public Person setSexDict(DictItem sexDict) {
			super.change(SEX_DICT,super.getSexDict(),sexDict);
			super.setSexDict(sexDict);
			return this;
		}
		
		/**
		 * 设置 maritalStatusDict
		 * @param maritalStatusDict maritalStatusDict
		 * @return 当前对象
		*/
		public Person setMaritalStatusDict(DictItem maritalStatusDict) {
			super.change(MARITAL_STATUS_DICT,super.getMaritalStatusDict(),maritalStatusDict);
			super.setMaritalStatusDict(maritalStatusDict);
			return this;
		}
		
		/**
		 * 设置 employeeOwnerType
		 * @param employeeOwnerTypeDict employeeOwnerType
		 * @return 当前对象
		*/
		public Person setEmployeeOwnerTypeDict(DictItem employeeOwnerTypeDict) {
			super.change(EMPLOYEE_OWNER_TYPE_DICT,super.getEmployeeOwnerTypeDict(),employeeOwnerTypeDict);
			super.setEmployeeOwnerTypeDict(employeeOwnerTypeDict);
			return this;
		}
		
		/**
		 * 设置 employee
		 * @param employee employee
		 * @return 当前对象
		*/
		public Person setEmployee(Employee employee) {
			super.change(EMPLOYEE,super.getEmployee(),employee);
			super.setEmployee(employee);
			return this;
		}

		/**
		 * 克隆当前对象
		*/
		@Transient
		public Person clone() {
			return duplicate(true);
		}

		/**
		 * 复制当前对象
		 * @param all 是否复制全部属性，当 false 时，仅复制来自数据表的属性
		*/
		@Transient
		public Person duplicate(boolean all) {
			$$proxy$$ inst=new $$proxy$$();
			inst.setRankCode(this.getRankCode());
			inst.setEmergencyContactNo(this.getEmergencyContactNo());
			inst.setEmploymentConfirmDate(this.getEmploymentConfirmDate());
			inst.setNativePlaceCode(this.getNativePlaceCode());
			inst.setMajor(this.getMajor());
			inst.setComputerLevel(this.getComputerLevel());
			inst.setWorkKindCode(this.getWorkKindCode());
			inst.setId(this.getId());
			inst.setPayrollCardBankCode(this.getPayrollCardBankCode());
			inst.setHomeAddress(this.getHomeAddress());
			inst.setEmployeeTitleCode(this.getEmployeeTitleCode());
			inst.setForeignLanguageLevel(this.getForeignLanguageLevel());
			inst.setEmergencyContact(this.getEmergencyContact());
			inst.setGraduationSchool(this.getGraduationSchool());
			inst.setEmploymentDate(this.getEmploymentDate());
			inst.setIdentityCard(this.getIdentityCard());
			inst.setVersion(this.getVersion());
			inst.setDeleteTime(this.getDeleteTime());
			inst.setName(this.getName());
			inst.setForeignLanguage(this.getForeignLanguage());
			inst.setFirstEmploymentDate(this.getFirstEmploymentDate());
			inst.setDeleteBy(this.getDeleteBy());
			inst.setJobNumber(this.getJobNumber());
			inst.setMaritalStatus(this.getMaritalStatus());
			inst.setFileId(this.getFileId());
			inst.setBirthday(this.getBirthday());
			inst.setNote(this.getNote());
			inst.setBodyHeight(this.getBodyHeight());
			inst.setPositionCode(this.getPositionCode());
			inst.setEducationCode(this.getEducationCode());
			inst.setComputerAbility(this.getComputerAbility());
			inst.setBloodType(this.getBloodType());
			inst.setEmployeeStatus(this.getEmployeeStatus());
			inst.setOrgId(this.getOrgId());
			inst.setUpdateBy(this.getUpdateBy());
			inst.setPersonPictureId(this.getPersonPictureId());
			inst.setEmail(this.getEmail());
			inst.setNationCode(this.getNationCode());
			inst.setFirstWorkDate(this.getFirstWorkDate());
			inst.setPayrollCard(this.getPayrollCard());
			inst.setWeixinId(this.getWeixinId());
			inst.setEmployeeId(this.getEmployeeId());
			inst.setUpdateTime(this.getUpdateTime());
			inst.setJoinPartDate(this.getJoinPartDate());
			inst.setLeaveReson(this.getLeaveReson());
			inst.setSexCode(this.getSexCode());
			inst.setCreateBy(this.getCreateBy());
			inst.setContactInformation(this.getContactInformation());
			inst.setDeleted(this.getDeleted());
			inst.setGraduationDate(this.getGraduationDate());
			inst.setCreateTime(this.getCreateTime());
			inst.setEmployeeTypeCode(this.getEmployeeTypeCode());
			inst.setLeaveDate(this.getLeaveDate());
			inst.setTenantId(this.getTenantId());
			inst.setPoliticCountenanceCode(this.getPoliticCountenanceCode());
			inst.setBodyWeight(this.getBodyWeight());
			if(all) {
				inst.setBloodTypeDict(this.getBloodTypeDict());
				inst.setProfessionalLevel(this.getProfessionalLevel());
				inst.setRank(this.getRank());
				inst.setEmployeeOwnerTypeDict(this.getEmployeeOwnerTypeDict());
				inst.setPosition(this.getPosition());
				inst.setEmployee(this.getEmployee());
				inst.setSexDict(this.getSexDict());
				inst.setMaritalStatusDict(this.getMaritalStatusDict());
			}
			inst.clearModifies();
			return inst;
		}

	}
}