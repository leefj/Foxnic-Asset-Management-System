package com.dt.platform.domain.hr;

import com.github.foxnic.dao.entity.Entity;
import io.swagger.annotations.ApiModel;
import javax.persistence.Table;
import com.github.foxnic.sql.meta.DBTable;
import com.dt.platform.constants.db.HrTables.HR_PERSON;
import javax.persistence.Id;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import javax.persistence.Transient;
import com.github.foxnic.api.swagger.EnumFor;
import org.github.foxnic.web.domain.system.DictItem;
import org.github.foxnic.web.domain.hrm.Employee;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.foxnic.commons.lang.DataParser;
import java.util.Map;
import com.github.foxnic.dao.entity.EntityContext;
import com.dt.platform.domain.hr.meta.PersonMeta;
import com.github.foxnic.sql.data.ExprRcd;



/**
 * 人员信息
 * <p>人员信息 , 数据表 hr_person 的PO类型</p>
 * @author 金杰 , maillank@qq.com
 * @since 2022-12-29 15:01:36
 * @sign 066E1222AF86221EBF502F2AEE28778D
 * 此文件由工具自动生成，请勿修改。若表结构或配置发生变动，请使用工具重新生成。
*/

@Table(name = "hr_person")
@ApiModel(description = "人员信息 ; 人员信息 , 数据表 hr_person 的PO类型")
public class Person extends Entity {

	private static final long serialVersionUID = 1L;

	public static final DBTable TABLE =HR_PERSON.$TABLE;
	
	/**
	 * 主键：主键
	*/
	@Id
	@ApiModelProperty(required = true,value="主键" , notes = "主键" , example = "660858365366239232")
	private String id;
	
	/**
	 * 工号：工号
	*/
	@ApiModelProperty(required = false,value="工号" , notes = "工号")
	private String jobNumber;
	
	/**
	 * 姓名：姓名
	*/
	@ApiModelProperty(required = false,value="姓名" , notes = "姓名" , example = "1212")
	private String name;
	
	/**
	 * 性别：性别
	*/
	@ApiModelProperty(required = false,value="性别" , notes = "性别")
	private String sexCode;
	
	/**
	 * 出生日期：出生日期
	*/
	@ApiModelProperty(required = false,value="出生日期" , notes = "出生日期")
	private Date birthday;
	
	/**
	 * 籍贯：籍贯
	*/
	@ApiModelProperty(required = false,value="籍贯" , notes = "籍贯")
	private String nativePlaceCode;
	
	/**
	 * 民族：民族
	*/
	@ApiModelProperty(required = false,value="民族" , notes = "民族")
	private String nationCode;
	
	/**
	 * 婚姻状况：婚姻状况
	*/
	@ApiModelProperty(required = false,value="婚姻状况" , notes = "婚姻状况")
	private String maritalStatus;
	
	/**
	 * 员工状态：员工状态
	*/
	@ApiModelProperty(required = false,value="员工状态" , notes = "员工状态")
	private String employeeStatus;
	
	/**
	 * 人员类型：人员类型
	*/
	@ApiModelProperty(required = false,value="人员类型" , notes = "人员类型")
	private String employeeTypeCode;
	
	/**
	 * 身份证：身份证
	*/
	@ApiModelProperty(required = false,value="身份证" , notes = "身份证" , example = "1212")
	private String identityCard;
	
	/**
	 * 联系方式：联系方式
	*/
	@ApiModelProperty(required = false,value="联系方式" , notes = "联系方式")
	private String contactInformation;
	
	/**
	 * 电子邮件：电子邮件
	*/
	@ApiModelProperty(required = false,value="电子邮件" , notes = "电子邮件")
	private String email;
	
	/**
	 * 紧急联系人：紧急联系人
	*/
	@ApiModelProperty(required = false,value="紧急联系人" , notes = "紧急联系人")
	private String emergencyContact;
	
	/**
	 * 紧急人电话：紧急人电话
	*/
	@ApiModelProperty(required = false,value="紧急人电话" , notes = "紧急人电话")
	private String emergencyContactNo;
	
	/**
	 * 家庭地址：家庭地址
	*/
	@ApiModelProperty(required = false,value="家庭地址" , notes = "家庭地址")
	private String homeAddress;
	
	/**
	 * 微信号：微信号
	*/
	@ApiModelProperty(required = false,value="微信号" , notes = "微信号")
	private String weixinId;
	
	/**
	 * 学历：学历
	*/
	@ApiModelProperty(required = false,value="学历" , notes = "学历")
	private String educationCode;
	
	/**
	 * 毕业院校：毕业院校
	*/
	@ApiModelProperty(required = false,value="毕业院校" , notes = "毕业院校")
	private String graduationSchool;
	
	/**
	 * 所学专业：所学专业
	*/
	@ApiModelProperty(required = false,value="所学专业" , notes = "所学专业")
	private String major;
	
	/**
	 * 毕业时间：毕业时间
	*/
	@ApiModelProperty(required = false,value="毕业时间" , notes = "毕业时间")
	private Date graduationDate;
	
	/**
	 * 所学外语：所学外语
	*/
	@ApiModelProperty(required = false,value="所学外语" , notes = "所学外语")
	private String foreignLanguage;
	
	/**
	 * 外语证书：外语证书
	*/
	@ApiModelProperty(required = false,value="外语证书" , notes = "外语证书")
	private String foreignLanguageLevel;
	
	/**
	 * 计算机能力：计算机能力
	*/
	@ApiModelProperty(required = false,value="计算机能力" , notes = "计算机能力")
	private String computerAbility;
	
	/**
	 * 计算机等级：计算机等级
	*/
	@ApiModelProperty(required = false,value="计算机等级" , notes = "计算机等级")
	private String computerLevel;
	
	/**
	 * 政治面貌：政治面貌
	*/
	@ApiModelProperty(required = false,value="政治面貌" , notes = "政治面貌")
	private String politicCountenanceCode;
	
	/**
	 * 入党时间：入党时间
	*/
	@ApiModelProperty(required = false,value="入党时间" , notes = "入党时间")
	private Date joinPartDate;
	
	/**
	 * 血型：血型
	*/
	@ApiModelProperty(required = false,value="血型" , notes = "血型")
	private String bloodType;
	
	/**
	 * 身高：身高
	*/
	@ApiModelProperty(required = false,value="身高" , notes = "身高")
	private Integer bodyHeight;
	
	/**
	 * 体重：体重
	*/
	@ApiModelProperty(required = false,value="体重" , notes = "体重")
	private Integer bodyWeight;
	
	/**
	 * 入职日期：入职日期
	*/
	@ApiModelProperty(required = false,value="入职日期" , notes = "入职日期")
	private Date employmentDate;
	
	/**
	 * 转正日期：转正日期
	*/
	@ApiModelProperty(required = false,value="转正日期" , notes = "转正日期")
	private Date employmentConfirmDate;
	
	/**
	 * 初次日期：初次日期
	*/
	@ApiModelProperty(required = false,value="初次日期" , notes = "初次日期")
	private Date firstEmploymentDate;
	
	/**
	 * 参加工作时间：参加工作时间
	*/
	@ApiModelProperty(required = false,value="参加工作时间" , notes = "参加工作时间")
	private Date firstWorkDate;
	
	/**
	 * 员工部门：员工部门
	*/
	@ApiModelProperty(required = false,value="员工部门" , notes = "员工部门")
	private String orgId;
	
	/**
	 * 员工岗位：员工岗位
	*/
	@ApiModelProperty(required = false,value="员工岗位" , notes = "员工岗位" , example = "660760911509716992")
	private String positionCode;
	
	/**
	 * 员工职称：员工职称
	*/
	@ApiModelProperty(required = false,value="员工职称" , notes = "员工职称" , example = "660565317197496320")
	private String employeeTitleCode;
	
	/**
	 * 员工职级：员工职级
	*/
	@ApiModelProperty(required = false,value="员工职级" , notes = "员工职级" , example = "660564558007500800")
	private String rankCode;
	
	/**
	 * 员工工种：员工工种
	*/
	@ApiModelProperty(required = false,value="员工工种" , notes = "员工工种")
	private String workKindCode;
	
	/**
	 * 离职日期：离职日期
	*/
	@ApiModelProperty(required = false,value="离职日期" , notes = "离职日期")
	private Date leaveDate;
	
	/**
	 * 离职原因：离职原因
	*/
	@ApiModelProperty(required = false,value="离职原因" , notes = "离职原因")
	private String leaveReson;
	
	/**
	 * 工资卡开户行：工资卡开户行
	*/
	@ApiModelProperty(required = false,value="工资卡开户行" , notes = "工资卡开户行")
	private String payrollCardBankCode;
	
	/**
	 * 工资卡帐号：工资卡帐号
	*/
	@ApiModelProperty(required = false,value="工资卡帐号" , notes = "工资卡帐号")
	private String payrollCard;
	
	/**
	 * 照片：照片
	*/
	@ApiModelProperty(required = false,value="照片" , notes = "照片")
	private String personPictureId;
	
	/**
	 * 员工：员工
	*/
	@ApiModelProperty(required = false,value="员工" , notes = "员工")
	private String employeeId;
	
	/**
	 * 附件：附件
	*/
	@ApiModelProperty(required = false,value="附件" , notes = "附件")
	private String fileId;
	
	/**
	 * 备注：备注
	*/
	@ApiModelProperty(required = false,value="备注" , notes = "备注")
	private String note;
	
	/**
	 * 创建人ID：创建人ID
	*/
	@ApiModelProperty(required = false,value="创建人ID" , notes = "创建人ID" , example = "110588348101165911")
	private String createBy;
	
	/**
	 * 创建时间：创建时间
	*/
	@ApiModelProperty(required = false,value="创建时间" , notes = "创建时间" , example = "2022-12-29 02:55:12")
	private Date createTime;
	
	/**
	 * 修改人ID：修改人ID
	*/
	@ApiModelProperty(required = false,value="修改人ID" , notes = "修改人ID")
	private String updateBy;
	
	/**
	 * 修改时间：修改时间
	*/
	@ApiModelProperty(required = false,value="修改时间" , notes = "修改时间")
	private Date updateTime;
	
	/**
	 * 是否已删除：是否已删除
	*/
	@ApiModelProperty(required = true,value="是否已删除" , notes = "是否已删除" , example = "0")
	private Integer deleted;
	@Transient
	@EnumFor("deleted")
	private Boolean deletedBool;
	
	/**
	 * 删除人ID：删除人ID
	*/
	@ApiModelProperty(required = false,value="删除人ID" , notes = "删除人ID")
	private String deleteBy;
	
	/**
	 * 删除时间：删除时间
	*/
	@ApiModelProperty(required = false,value="删除时间" , notes = "删除时间")
	private Date deleteTime;
	
	/**
	 * version：version
	*/
	@ApiModelProperty(required = true,value="version" , notes = "version" , example = "1")
	private Integer version;
	
	/**
	 * 租户：租户
	*/
	@ApiModelProperty(required = false,value="租户" , notes = "租户" , example = "T001")
	private String tenantId;
	
	/**
	 * position：position
	*/
	@ApiModelProperty(required = false,value="position" , notes = "position")
	private Position position;
	
	/**
	 * rank：rank
	*/
	@ApiModelProperty(required = false,value="rank" , notes = "rank")
	private Rank rank;
	
	/**
	 * professionalLevel：professionalLevel
	*/
	@ApiModelProperty(required = false,value="professionalLevel" , notes = "professionalLevel")
	private ProfessionalLevel professionalLevel;
	
	/**
	 * bloodTypeDict：bloodTypeDict
	*/
	@ApiModelProperty(required = false,value="bloodTypeDict" , notes = "bloodTypeDict")
	private DictItem bloodTypeDict;
	
	/**
	 * sexDict：sexDict
	*/
	@ApiModelProperty(required = false,value="sexDict" , notes = "sexDict")
	private DictItem sexDict;
	
	/**
	 * maritalStatusDict：maritalStatusDict
	*/
	@ApiModelProperty(required = false,value="maritalStatusDict" , notes = "maritalStatusDict")
	private DictItem maritalStatusDict;
	
	/**
	 * employeeOwnerType：employeeOwnerType
	*/
	@ApiModelProperty(required = false,value="employeeOwnerType" , notes = "employeeOwnerType")
	private DictItem employeeOwnerTypeDict;
	
	/**
	 * employee：employee
	*/
	@ApiModelProperty(required = false,value="employee" , notes = "employee")
	private Employee employee;
	
	/**
	 * 获得 主键<br>
	 * 主键
	 * @return 主键
	*/
	public String getId() {
		return id;
	}
	
	/**
	 * 设置 主键
	 * @param id 主键
	 * @return 当前对象
	*/
	public Person setId(String id) {
		this.id=id;
		return this;
	}
	
	/**
	 * 获得 工号<br>
	 * 工号
	 * @return 工号
	*/
	public String getJobNumber() {
		return jobNumber;
	}
	
	/**
	 * 设置 工号
	 * @param jobNumber 工号
	 * @return 当前对象
	*/
	public Person setJobNumber(String jobNumber) {
		this.jobNumber=jobNumber;
		return this;
	}
	
	/**
	 * 获得 姓名<br>
	 * 姓名
	 * @return 姓名
	*/
	public String getName() {
		return name;
	}
	
	/**
	 * 设置 姓名
	 * @param name 姓名
	 * @return 当前对象
	*/
	public Person setName(String name) {
		this.name=name;
		return this;
	}
	
	/**
	 * 获得 性别<br>
	 * 性别
	 * @return 性别
	*/
	public String getSexCode() {
		return sexCode;
	}
	
	/**
	 * 设置 性别
	 * @param sexCode 性别
	 * @return 当前对象
	*/
	public Person setSexCode(String sexCode) {
		this.sexCode=sexCode;
		return this;
	}
	
	/**
	 * 获得 出生日期<br>
	 * 出生日期
	 * @return 出生日期
	*/
	public Date getBirthday() {
		return birthday;
	}
	
	/**
	 * 设置 出生日期
	 * @param birthday 出生日期
	 * @return 当前对象
	*/
	public Person setBirthday(Date birthday) {
		this.birthday=birthday;
		return this;
	}
	
	/**
	 * 获得 籍贯<br>
	 * 籍贯
	 * @return 籍贯
	*/
	public String getNativePlaceCode() {
		return nativePlaceCode;
	}
	
	/**
	 * 设置 籍贯
	 * @param nativePlaceCode 籍贯
	 * @return 当前对象
	*/
	public Person setNativePlaceCode(String nativePlaceCode) {
		this.nativePlaceCode=nativePlaceCode;
		return this;
	}
	
	/**
	 * 获得 民族<br>
	 * 民族
	 * @return 民族
	*/
	public String getNationCode() {
		return nationCode;
	}
	
	/**
	 * 设置 民族
	 * @param nationCode 民族
	 * @return 当前对象
	*/
	public Person setNationCode(String nationCode) {
		this.nationCode=nationCode;
		return this;
	}
	
	/**
	 * 获得 婚姻状况<br>
	 * 婚姻状况
	 * @return 婚姻状况
	*/
	public String getMaritalStatus() {
		return maritalStatus;
	}
	
	/**
	 * 设置 婚姻状况
	 * @param maritalStatus 婚姻状况
	 * @return 当前对象
	*/
	public Person setMaritalStatus(String maritalStatus) {
		this.maritalStatus=maritalStatus;
		return this;
	}
	
	/**
	 * 获得 员工状态<br>
	 * 员工状态
	 * @return 员工状态
	*/
	public String getEmployeeStatus() {
		return employeeStatus;
	}
	
	/**
	 * 设置 员工状态
	 * @param employeeStatus 员工状态
	 * @return 当前对象
	*/
	public Person setEmployeeStatus(String employeeStatus) {
		this.employeeStatus=employeeStatus;
		return this;
	}
	
	/**
	 * 获得 人员类型<br>
	 * 人员类型
	 * @return 人员类型
	*/
	public String getEmployeeTypeCode() {
		return employeeTypeCode;
	}
	
	/**
	 * 设置 人员类型
	 * @param employeeTypeCode 人员类型
	 * @return 当前对象
	*/
	public Person setEmployeeTypeCode(String employeeTypeCode) {
		this.employeeTypeCode=employeeTypeCode;
		return this;
	}
	
	/**
	 * 获得 身份证<br>
	 * 身份证
	 * @return 身份证
	*/
	public String getIdentityCard() {
		return identityCard;
	}
	
	/**
	 * 设置 身份证
	 * @param identityCard 身份证
	 * @return 当前对象
	*/
	public Person setIdentityCard(String identityCard) {
		this.identityCard=identityCard;
		return this;
	}
	
	/**
	 * 获得 联系方式<br>
	 * 联系方式
	 * @return 联系方式
	*/
	public String getContactInformation() {
		return contactInformation;
	}
	
	/**
	 * 设置 联系方式
	 * @param contactInformation 联系方式
	 * @return 当前对象
	*/
	public Person setContactInformation(String contactInformation) {
		this.contactInformation=contactInformation;
		return this;
	}
	
	/**
	 * 获得 电子邮件<br>
	 * 电子邮件
	 * @return 电子邮件
	*/
	public String getEmail() {
		return email;
	}
	
	/**
	 * 设置 电子邮件
	 * @param email 电子邮件
	 * @return 当前对象
	*/
	public Person setEmail(String email) {
		this.email=email;
		return this;
	}
	
	/**
	 * 获得 紧急联系人<br>
	 * 紧急联系人
	 * @return 紧急联系人
	*/
	public String getEmergencyContact() {
		return emergencyContact;
	}
	
	/**
	 * 设置 紧急联系人
	 * @param emergencyContact 紧急联系人
	 * @return 当前对象
	*/
	public Person setEmergencyContact(String emergencyContact) {
		this.emergencyContact=emergencyContact;
		return this;
	}
	
	/**
	 * 获得 紧急人电话<br>
	 * 紧急人电话
	 * @return 紧急人电话
	*/
	public String getEmergencyContactNo() {
		return emergencyContactNo;
	}
	
	/**
	 * 设置 紧急人电话
	 * @param emergencyContactNo 紧急人电话
	 * @return 当前对象
	*/
	public Person setEmergencyContactNo(String emergencyContactNo) {
		this.emergencyContactNo=emergencyContactNo;
		return this;
	}
	
	/**
	 * 获得 家庭地址<br>
	 * 家庭地址
	 * @return 家庭地址
	*/
	public String getHomeAddress() {
		return homeAddress;
	}
	
	/**
	 * 设置 家庭地址
	 * @param homeAddress 家庭地址
	 * @return 当前对象
	*/
	public Person setHomeAddress(String homeAddress) {
		this.homeAddress=homeAddress;
		return this;
	}
	
	/**
	 * 获得 微信号<br>
	 * 微信号
	 * @return 微信号
	*/
	public String getWeixinId() {
		return weixinId;
	}
	
	/**
	 * 设置 微信号
	 * @param weixinId 微信号
	 * @return 当前对象
	*/
	public Person setWeixinId(String weixinId) {
		this.weixinId=weixinId;
		return this;
	}
	
	/**
	 * 获得 学历<br>
	 * 学历
	 * @return 学历
	*/
	public String getEducationCode() {
		return educationCode;
	}
	
	/**
	 * 设置 学历
	 * @param educationCode 学历
	 * @return 当前对象
	*/
	public Person setEducationCode(String educationCode) {
		this.educationCode=educationCode;
		return this;
	}
	
	/**
	 * 获得 毕业院校<br>
	 * 毕业院校
	 * @return 毕业院校
	*/
	public String getGraduationSchool() {
		return graduationSchool;
	}
	
	/**
	 * 设置 毕业院校
	 * @param graduationSchool 毕业院校
	 * @return 当前对象
	*/
	public Person setGraduationSchool(String graduationSchool) {
		this.graduationSchool=graduationSchool;
		return this;
	}
	
	/**
	 * 获得 所学专业<br>
	 * 所学专业
	 * @return 所学专业
	*/
	public String getMajor() {
		return major;
	}
	
	/**
	 * 设置 所学专业
	 * @param major 所学专业
	 * @return 当前对象
	*/
	public Person setMajor(String major) {
		this.major=major;
		return this;
	}
	
	/**
	 * 获得 毕业时间<br>
	 * 毕业时间
	 * @return 毕业时间
	*/
	public Date getGraduationDate() {
		return graduationDate;
	}
	
	/**
	 * 设置 毕业时间
	 * @param graduationDate 毕业时间
	 * @return 当前对象
	*/
	public Person setGraduationDate(Date graduationDate) {
		this.graduationDate=graduationDate;
		return this;
	}
	
	/**
	 * 获得 所学外语<br>
	 * 所学外语
	 * @return 所学外语
	*/
	public String getForeignLanguage() {
		return foreignLanguage;
	}
	
	/**
	 * 设置 所学外语
	 * @param foreignLanguage 所学外语
	 * @return 当前对象
	*/
	public Person setForeignLanguage(String foreignLanguage) {
		this.foreignLanguage=foreignLanguage;
		return this;
	}
	
	/**
	 * 获得 外语证书<br>
	 * 外语证书
	 * @return 外语证书
	*/
	public String getForeignLanguageLevel() {
		return foreignLanguageLevel;
	}
	
	/**
	 * 设置 外语证书
	 * @param foreignLanguageLevel 外语证书
	 * @return 当前对象
	*/
	public Person setForeignLanguageLevel(String foreignLanguageLevel) {
		this.foreignLanguageLevel=foreignLanguageLevel;
		return this;
	}
	
	/**
	 * 获得 计算机能力<br>
	 * 计算机能力
	 * @return 计算机能力
	*/
	public String getComputerAbility() {
		return computerAbility;
	}
	
	/**
	 * 设置 计算机能力
	 * @param computerAbility 计算机能力
	 * @return 当前对象
	*/
	public Person setComputerAbility(String computerAbility) {
		this.computerAbility=computerAbility;
		return this;
	}
	
	/**
	 * 获得 计算机等级<br>
	 * 计算机等级
	 * @return 计算机等级
	*/
	public String getComputerLevel() {
		return computerLevel;
	}
	
	/**
	 * 设置 计算机等级
	 * @param computerLevel 计算机等级
	 * @return 当前对象
	*/
	public Person setComputerLevel(String computerLevel) {
		this.computerLevel=computerLevel;
		return this;
	}
	
	/**
	 * 获得 政治面貌<br>
	 * 政治面貌
	 * @return 政治面貌
	*/
	public String getPoliticCountenanceCode() {
		return politicCountenanceCode;
	}
	
	/**
	 * 设置 政治面貌
	 * @param politicCountenanceCode 政治面貌
	 * @return 当前对象
	*/
	public Person setPoliticCountenanceCode(String politicCountenanceCode) {
		this.politicCountenanceCode=politicCountenanceCode;
		return this;
	}
	
	/**
	 * 获得 入党时间<br>
	 * 入党时间
	 * @return 入党时间
	*/
	public Date getJoinPartDate() {
		return joinPartDate;
	}
	
	/**
	 * 设置 入党时间
	 * @param joinPartDate 入党时间
	 * @return 当前对象
	*/
	public Person setJoinPartDate(Date joinPartDate) {
		this.joinPartDate=joinPartDate;
		return this;
	}
	
	/**
	 * 获得 血型<br>
	 * 血型
	 * @return 血型
	*/
	public String getBloodType() {
		return bloodType;
	}
	
	/**
	 * 设置 血型
	 * @param bloodType 血型
	 * @return 当前对象
	*/
	public Person setBloodType(String bloodType) {
		this.bloodType=bloodType;
		return this;
	}
	
	/**
	 * 获得 身高<br>
	 * 身高
	 * @return 身高
	*/
	public Integer getBodyHeight() {
		return bodyHeight;
	}
	
	/**
	 * 设置 身高
	 * @param bodyHeight 身高
	 * @return 当前对象
	*/
	public Person setBodyHeight(Integer bodyHeight) {
		this.bodyHeight=bodyHeight;
		return this;
	}
	
	/**
	 * 获得 体重<br>
	 * 体重
	 * @return 体重
	*/
	public Integer getBodyWeight() {
		return bodyWeight;
	}
	
	/**
	 * 设置 体重
	 * @param bodyWeight 体重
	 * @return 当前对象
	*/
	public Person setBodyWeight(Integer bodyWeight) {
		this.bodyWeight=bodyWeight;
		return this;
	}
	
	/**
	 * 获得 入职日期<br>
	 * 入职日期
	 * @return 入职日期
	*/
	public Date getEmploymentDate() {
		return employmentDate;
	}
	
	/**
	 * 设置 入职日期
	 * @param employmentDate 入职日期
	 * @return 当前对象
	*/
	public Person setEmploymentDate(Date employmentDate) {
		this.employmentDate=employmentDate;
		return this;
	}
	
	/**
	 * 获得 转正日期<br>
	 * 转正日期
	 * @return 转正日期
	*/
	public Date getEmploymentConfirmDate() {
		return employmentConfirmDate;
	}
	
	/**
	 * 设置 转正日期
	 * @param employmentConfirmDate 转正日期
	 * @return 当前对象
	*/
	public Person setEmploymentConfirmDate(Date employmentConfirmDate) {
		this.employmentConfirmDate=employmentConfirmDate;
		return this;
	}
	
	/**
	 * 获得 初次日期<br>
	 * 初次日期
	 * @return 初次日期
	*/
	public Date getFirstEmploymentDate() {
		return firstEmploymentDate;
	}
	
	/**
	 * 设置 初次日期
	 * @param firstEmploymentDate 初次日期
	 * @return 当前对象
	*/
	public Person setFirstEmploymentDate(Date firstEmploymentDate) {
		this.firstEmploymentDate=firstEmploymentDate;
		return this;
	}
	
	/**
	 * 获得 参加工作时间<br>
	 * 参加工作时间
	 * @return 参加工作时间
	*/
	public Date getFirstWorkDate() {
		return firstWorkDate;
	}
	
	/**
	 * 设置 参加工作时间
	 * @param firstWorkDate 参加工作时间
	 * @return 当前对象
	*/
	public Person setFirstWorkDate(Date firstWorkDate) {
		this.firstWorkDate=firstWorkDate;
		return this;
	}
	
	/**
	 * 获得 员工部门<br>
	 * 员工部门
	 * @return 员工部门
	*/
	public String getOrgId() {
		return orgId;
	}
	
	/**
	 * 设置 员工部门
	 * @param orgId 员工部门
	 * @return 当前对象
	*/
	public Person setOrgId(String orgId) {
		this.orgId=orgId;
		return this;
	}
	
	/**
	 * 获得 员工岗位<br>
	 * 员工岗位
	 * @return 员工岗位
	*/
	public String getPositionCode() {
		return positionCode;
	}
	
	/**
	 * 设置 员工岗位
	 * @param positionCode 员工岗位
	 * @return 当前对象
	*/
	public Person setPositionCode(String positionCode) {
		this.positionCode=positionCode;
		return this;
	}
	
	/**
	 * 获得 员工职称<br>
	 * 员工职称
	 * @return 员工职称
	*/
	public String getEmployeeTitleCode() {
		return employeeTitleCode;
	}
	
	/**
	 * 设置 员工职称
	 * @param employeeTitleCode 员工职称
	 * @return 当前对象
	*/
	public Person setEmployeeTitleCode(String employeeTitleCode) {
		this.employeeTitleCode=employeeTitleCode;
		return this;
	}
	
	/**
	 * 获得 员工职级<br>
	 * 员工职级
	 * @return 员工职级
	*/
	public String getRankCode() {
		return rankCode;
	}
	
	/**
	 * 设置 员工职级
	 * @param rankCode 员工职级
	 * @return 当前对象
	*/
	public Person setRankCode(String rankCode) {
		this.rankCode=rankCode;
		return this;
	}
	
	/**
	 * 获得 员工工种<br>
	 * 员工工种
	 * @return 员工工种
	*/
	public String getWorkKindCode() {
		return workKindCode;
	}
	
	/**
	 * 设置 员工工种
	 * @param workKindCode 员工工种
	 * @return 当前对象
	*/
	public Person setWorkKindCode(String workKindCode) {
		this.workKindCode=workKindCode;
		return this;
	}
	
	/**
	 * 获得 离职日期<br>
	 * 离职日期
	 * @return 离职日期
	*/
	public Date getLeaveDate() {
		return leaveDate;
	}
	
	/**
	 * 设置 离职日期
	 * @param leaveDate 离职日期
	 * @return 当前对象
	*/
	public Person setLeaveDate(Date leaveDate) {
		this.leaveDate=leaveDate;
		return this;
	}
	
	/**
	 * 获得 离职原因<br>
	 * 离职原因
	 * @return 离职原因
	*/
	public String getLeaveReson() {
		return leaveReson;
	}
	
	/**
	 * 设置 离职原因
	 * @param leaveReson 离职原因
	 * @return 当前对象
	*/
	public Person setLeaveReson(String leaveReson) {
		this.leaveReson=leaveReson;
		return this;
	}
	
	/**
	 * 获得 工资卡开户行<br>
	 * 工资卡开户行
	 * @return 工资卡开户行
	*/
	public String getPayrollCardBankCode() {
		return payrollCardBankCode;
	}
	
	/**
	 * 设置 工资卡开户行
	 * @param payrollCardBankCode 工资卡开户行
	 * @return 当前对象
	*/
	public Person setPayrollCardBankCode(String payrollCardBankCode) {
		this.payrollCardBankCode=payrollCardBankCode;
		return this;
	}
	
	/**
	 * 获得 工资卡帐号<br>
	 * 工资卡帐号
	 * @return 工资卡帐号
	*/
	public String getPayrollCard() {
		return payrollCard;
	}
	
	/**
	 * 设置 工资卡帐号
	 * @param payrollCard 工资卡帐号
	 * @return 当前对象
	*/
	public Person setPayrollCard(String payrollCard) {
		this.payrollCard=payrollCard;
		return this;
	}
	
	/**
	 * 获得 照片<br>
	 * 照片
	 * @return 照片
	*/
	public String getPersonPictureId() {
		return personPictureId;
	}
	
	/**
	 * 设置 照片
	 * @param personPictureId 照片
	 * @return 当前对象
	*/
	public Person setPersonPictureId(String personPictureId) {
		this.personPictureId=personPictureId;
		return this;
	}
	
	/**
	 * 获得 员工<br>
	 * 员工
	 * @return 员工
	*/
	public String getEmployeeId() {
		return employeeId;
	}
	
	/**
	 * 设置 员工
	 * @param employeeId 员工
	 * @return 当前对象
	*/
	public Person setEmployeeId(String employeeId) {
		this.employeeId=employeeId;
		return this;
	}
	
	/**
	 * 获得 附件<br>
	 * 附件
	 * @return 附件
	*/
	public String getFileId() {
		return fileId;
	}
	
	/**
	 * 设置 附件
	 * @param fileId 附件
	 * @return 当前对象
	*/
	public Person setFileId(String fileId) {
		this.fileId=fileId;
		return this;
	}
	
	/**
	 * 获得 备注<br>
	 * 备注
	 * @return 备注
	*/
	public String getNote() {
		return note;
	}
	
	/**
	 * 设置 备注
	 * @param note 备注
	 * @return 当前对象
	*/
	public Person setNote(String note) {
		this.note=note;
		return this;
	}
	
	/**
	 * 获得 创建人ID<br>
	 * 创建人ID
	 * @return 创建人ID
	*/
	public String getCreateBy() {
		return createBy;
	}
	
	/**
	 * 设置 创建人ID
	 * @param createBy 创建人ID
	 * @return 当前对象
	*/
	public Person setCreateBy(String createBy) {
		this.createBy=createBy;
		return this;
	}
	
	/**
	 * 获得 创建时间<br>
	 * 创建时间
	 * @return 创建时间
	*/
	public Date getCreateTime() {
		return createTime;
	}
	
	/**
	 * 设置 创建时间
	 * @param createTime 创建时间
	 * @return 当前对象
	*/
	public Person setCreateTime(Date createTime) {
		this.createTime=createTime;
		return this;
	}
	
	/**
	 * 获得 修改人ID<br>
	 * 修改人ID
	 * @return 修改人ID
	*/
	public String getUpdateBy() {
		return updateBy;
	}
	
	/**
	 * 设置 修改人ID
	 * @param updateBy 修改人ID
	 * @return 当前对象
	*/
	public Person setUpdateBy(String updateBy) {
		this.updateBy=updateBy;
		return this;
	}
	
	/**
	 * 获得 修改时间<br>
	 * 修改时间
	 * @return 修改时间
	*/
	public Date getUpdateTime() {
		return updateTime;
	}
	
	/**
	 * 设置 修改时间
	 * @param updateTime 修改时间
	 * @return 当前对象
	*/
	public Person setUpdateTime(Date updateTime) {
		this.updateTime=updateTime;
		return this;
	}
	
	/**
	 * 获得 是否已删除<br>
	 * 是否已删除
	 * @return 是否已删除
	*/
	public Integer getDeleted() {
		return deleted;
	}
	
	/**
	 * 获得 是否已删除 的投影属性<br>
	 * 等价于 getDeleted 方法，获得对应的枚举类型
	 * @return 是否已删除
	*/
	@Transient
	public Boolean isDeleted() {
		if(this.deletedBool==null) {
			this.deletedBool=DataParser.parseBoolean(deleted);
		}
		return this.deletedBool ;
	}
	
	/**
	 * 设置 是否已删除
	 * @param deleted 是否已删除
	 * @return 当前对象
	*/
	@JsonProperty("deleted")
	public Person setDeleted(Integer deleted) {
		this.deleted=deleted;
		this.deletedBool=DataParser.parseBoolean(deleted);
		return this;
	}
	
	/**
	 * 设置 是否已删除的投影属性，等同于设置 是否已删除
	 * @param deletedBool 是否已删除
	 * @return 当前对象
	*/
	@Transient
	public Person setDeleted(Boolean deletedBool) {
		if(deletedBool==null) {
			this.deleted=null;
		} else {
			this.deleted=deletedBool?1:0;
		}
		this.deletedBool=deletedBool;
		return this;
	}
	
	/**
	 * 获得 删除人ID<br>
	 * 删除人ID
	 * @return 删除人ID
	*/
	public String getDeleteBy() {
		return deleteBy;
	}
	
	/**
	 * 设置 删除人ID
	 * @param deleteBy 删除人ID
	 * @return 当前对象
	*/
	public Person setDeleteBy(String deleteBy) {
		this.deleteBy=deleteBy;
		return this;
	}
	
	/**
	 * 获得 删除时间<br>
	 * 删除时间
	 * @return 删除时间
	*/
	public Date getDeleteTime() {
		return deleteTime;
	}
	
	/**
	 * 设置 删除时间
	 * @param deleteTime 删除时间
	 * @return 当前对象
	*/
	public Person setDeleteTime(Date deleteTime) {
		this.deleteTime=deleteTime;
		return this;
	}
	
	/**
	 * 获得 version<br>
	 * version
	 * @return version
	*/
	public Integer getVersion() {
		return version;
	}
	
	/**
	 * 设置 version
	 * @param version version
	 * @return 当前对象
	*/
	public Person setVersion(Integer version) {
		this.version=version;
		return this;
	}
	
	/**
	 * 获得 租户<br>
	 * 租户
	 * @return 租户
	*/
	public String getTenantId() {
		return tenantId;
	}
	
	/**
	 * 设置 租户
	 * @param tenantId 租户
	 * @return 当前对象
	*/
	public Person setTenantId(String tenantId) {
		this.tenantId=tenantId;
		return this;
	}
	
	/**
	 * 获得 position<br>
	 * position
	 * @return position
	*/
	public Position getPosition() {
		return position;
	}
	
	/**
	 * 设置 position
	 * @param position position
	 * @return 当前对象
	*/
	public Person setPosition(Position position) {
		this.position=position;
		return this;
	}
	
	/**
	 * 获得 rank<br>
	 * rank
	 * @return rank
	*/
	public Rank getRank() {
		return rank;
	}
	
	/**
	 * 设置 rank
	 * @param rank rank
	 * @return 当前对象
	*/
	public Person setRank(Rank rank) {
		this.rank=rank;
		return this;
	}
	
	/**
	 * 获得 professionalLevel<br>
	 * professionalLevel
	 * @return professionalLevel
	*/
	public ProfessionalLevel getProfessionalLevel() {
		return professionalLevel;
	}
	
	/**
	 * 设置 professionalLevel
	 * @param professionalLevel professionalLevel
	 * @return 当前对象
	*/
	public Person setProfessionalLevel(ProfessionalLevel professionalLevel) {
		this.professionalLevel=professionalLevel;
		return this;
	}
	
	/**
	 * 获得 bloodTypeDict<br>
	 * bloodTypeDict
	 * @return bloodTypeDict
	*/
	public DictItem getBloodTypeDict() {
		return bloodTypeDict;
	}
	
	/**
	 * 设置 bloodTypeDict
	 * @param bloodTypeDict bloodTypeDict
	 * @return 当前对象
	*/
	public Person setBloodTypeDict(DictItem bloodTypeDict) {
		this.bloodTypeDict=bloodTypeDict;
		return this;
	}
	
	/**
	 * 获得 sexDict<br>
	 * sexDict
	 * @return sexDict
	*/
	public DictItem getSexDict() {
		return sexDict;
	}
	
	/**
	 * 设置 sexDict
	 * @param sexDict sexDict
	 * @return 当前对象
	*/
	public Person setSexDict(DictItem sexDict) {
		this.sexDict=sexDict;
		return this;
	}
	
	/**
	 * 获得 maritalStatusDict<br>
	 * maritalStatusDict
	 * @return maritalStatusDict
	*/
	public DictItem getMaritalStatusDict() {
		return maritalStatusDict;
	}
	
	/**
	 * 设置 maritalStatusDict
	 * @param maritalStatusDict maritalStatusDict
	 * @return 当前对象
	*/
	public Person setMaritalStatusDict(DictItem maritalStatusDict) {
		this.maritalStatusDict=maritalStatusDict;
		return this;
	}
	
	/**
	 * 获得 employeeOwnerType<br>
	 * employeeOwnerType
	 * @return employeeOwnerType
	*/
	public DictItem getEmployeeOwnerTypeDict() {
		return employeeOwnerTypeDict;
	}
	
	/**
	 * 设置 employeeOwnerType
	 * @param employeeOwnerTypeDict employeeOwnerType
	 * @return 当前对象
	*/
	public Person setEmployeeOwnerTypeDict(DictItem employeeOwnerTypeDict) {
		this.employeeOwnerTypeDict=employeeOwnerTypeDict;
		return this;
	}
	
	/**
	 * 获得 employee<br>
	 * employee
	 * @return employee
	*/
	public Employee getEmployee() {
		return employee;
	}
	
	/**
	 * 设置 employee
	 * @param employee employee
	 * @return 当前对象
	*/
	public Person setEmployee(Employee employee) {
		this.employee=employee;
		return this;
	}

	/**
	 * 将自己转换成指定类型的PO
	 * @param poType  PO类型
	 * @return Person , 转换好的 Person 对象
	*/
	@Transient
	public <T extends Entity> T toPO(Class<T> poType) {
		return EntityContext.create(poType, this);
	}

	/**
	 * 将自己转换成任意指定类型
	 * @param pojoType  Pojo类型
	 * @return Person , 转换好的 PoJo 对象
	*/
	@Transient
	public <T> T toPojo(Class<T> pojoType) {
		if(Entity.class.isAssignableFrom(pojoType)) {
			return (T)this.toPO((Class<Entity>)pojoType);
		}
		try {
			T pojo=pojoType.newInstance();
			EntityContext.copyProperties(pojo, this);
			return pojo;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
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
		com.dt.platform.domain.hr.meta.PersonMeta.$$proxy$$ inst = new com.dt.platform.domain.hr.meta.PersonMeta.$$proxy$$();
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

	/**
	 * 克隆当前对象
	*/
	@Transient
	public Person clone(boolean deep) {
		return EntityContext.clone(Person.class,this,deep);
	}

	/**
	 * 将 Map 转换成 Person
	 * @param personMap 包含实体信息的 Map 对象
	 * @return Person , 转换好的的 Person 对象
	*/
	@Transient
	public static Person createFrom(Map<String,Object> personMap) {
		if(personMap==null) return null;
		Person po = create();
		EntityContext.copyProperties(po,personMap);
		po.clearModifies();
		return po;
	}

	/**
	 * 将 Pojo 转换成 Person
	 * @param pojo 包含实体信息的 Pojo 对象
	 * @return Person , 转换好的的 Person 对象
	*/
	@Transient
	public static Person createFrom(Object pojo) {
		if(pojo==null) return null;
		Person po = create();
		EntityContext.copyProperties(po,pojo);
		po.clearModifies();
		return po;
	}

	/**
	 * 创建一个 Person，等同于 new
	 * @return Person 对象
	*/
	@Transient
	public static Person create() {
		return new com.dt.platform.domain.hr.meta.PersonMeta.$$proxy$$();
	}

	/**
	 * 从 Map 读取
	 * @param map 记录数据
	 * @param cast 是否用 DataParser 进行类型转换
	 * @return  是否读取成功
	*/
	public boolean read(Map<String, Object> map,boolean cast) {
		if(map==null) return false;
		if(cast) {
			this.setRankCode(DataParser.parse(String.class, map.get(PersonMeta.RANK_CODE)));
			this.setEmergencyContactNo(DataParser.parse(String.class, map.get(PersonMeta.EMERGENCY_CONTACT_NO)));
			this.setEmploymentConfirmDate(DataParser.parse(Date.class, map.get(PersonMeta.EMPLOYMENT_CONFIRM_DATE)));
			this.setNativePlaceCode(DataParser.parse(String.class, map.get(PersonMeta.NATIVE_PLACE_CODE)));
			this.setMajor(DataParser.parse(String.class, map.get(PersonMeta.MAJOR)));
			this.setComputerLevel(DataParser.parse(String.class, map.get(PersonMeta.COMPUTER_LEVEL)));
			this.setWorkKindCode(DataParser.parse(String.class, map.get(PersonMeta.WORK_KIND_CODE)));
			this.setId(DataParser.parse(String.class, map.get(PersonMeta.ID)));
			this.setPayrollCardBankCode(DataParser.parse(String.class, map.get(PersonMeta.PAYROLL_CARD_BANK_CODE)));
			this.setHomeAddress(DataParser.parse(String.class, map.get(PersonMeta.HOME_ADDRESS)));
			this.setEmployeeTitleCode(DataParser.parse(String.class, map.get(PersonMeta.EMPLOYEE_TITLE_CODE)));
			this.setForeignLanguageLevel(DataParser.parse(String.class, map.get(PersonMeta.FOREIGN_LANGUAGE_LEVEL)));
			this.setEmergencyContact(DataParser.parse(String.class, map.get(PersonMeta.EMERGENCY_CONTACT)));
			this.setGraduationSchool(DataParser.parse(String.class, map.get(PersonMeta.GRADUATION_SCHOOL)));
			this.setEmploymentDate(DataParser.parse(Date.class, map.get(PersonMeta.EMPLOYMENT_DATE)));
			this.setIdentityCard(DataParser.parse(String.class, map.get(PersonMeta.IDENTITY_CARD)));
			this.setVersion(DataParser.parse(Integer.class, map.get(PersonMeta.VERSION)));
			this.setDeleteTime(DataParser.parse(Date.class, map.get(PersonMeta.DELETE_TIME)));
			this.setName(DataParser.parse(String.class, map.get(PersonMeta.NAME)));
			this.setForeignLanguage(DataParser.parse(String.class, map.get(PersonMeta.FOREIGN_LANGUAGE)));
			this.setFirstEmploymentDate(DataParser.parse(Date.class, map.get(PersonMeta.FIRST_EMPLOYMENT_DATE)));
			this.setDeleteBy(DataParser.parse(String.class, map.get(PersonMeta.DELETE_BY)));
			this.setJobNumber(DataParser.parse(String.class, map.get(PersonMeta.JOB_NUMBER)));
			this.setMaritalStatus(DataParser.parse(String.class, map.get(PersonMeta.MARITAL_STATUS)));
			this.setFileId(DataParser.parse(String.class, map.get(PersonMeta.FILE_ID)));
			this.setBirthday(DataParser.parse(Date.class, map.get(PersonMeta.BIRTHDAY)));
			this.setNote(DataParser.parse(String.class, map.get(PersonMeta.NOTE)));
			this.setBodyHeight(DataParser.parse(Integer.class, map.get(PersonMeta.BODY_HEIGHT)));
			this.setPositionCode(DataParser.parse(String.class, map.get(PersonMeta.POSITION_CODE)));
			this.setEducationCode(DataParser.parse(String.class, map.get(PersonMeta.EDUCATION_CODE)));
			this.setComputerAbility(DataParser.parse(String.class, map.get(PersonMeta.COMPUTER_ABILITY)));
			this.setBloodType(DataParser.parse(String.class, map.get(PersonMeta.BLOOD_TYPE)));
			this.setEmployeeStatus(DataParser.parse(String.class, map.get(PersonMeta.EMPLOYEE_STATUS)));
			this.setOrgId(DataParser.parse(String.class, map.get(PersonMeta.ORG_ID)));
			this.setUpdateBy(DataParser.parse(String.class, map.get(PersonMeta.UPDATE_BY)));
			this.setPersonPictureId(DataParser.parse(String.class, map.get(PersonMeta.PERSON_PICTURE_ID)));
			this.setEmail(DataParser.parse(String.class, map.get(PersonMeta.EMAIL)));
			this.setNationCode(DataParser.parse(String.class, map.get(PersonMeta.NATION_CODE)));
			this.setFirstWorkDate(DataParser.parse(Date.class, map.get(PersonMeta.FIRST_WORK_DATE)));
			this.setPayrollCard(DataParser.parse(String.class, map.get(PersonMeta.PAYROLL_CARD)));
			this.setWeixinId(DataParser.parse(String.class, map.get(PersonMeta.WEIXIN_ID)));
			this.setEmployeeId(DataParser.parse(String.class, map.get(PersonMeta.EMPLOYEE_ID)));
			this.setUpdateTime(DataParser.parse(Date.class, map.get(PersonMeta.UPDATE_TIME)));
			this.setJoinPartDate(DataParser.parse(Date.class, map.get(PersonMeta.JOIN_PART_DATE)));
			this.setLeaveReson(DataParser.parse(String.class, map.get(PersonMeta.LEAVE_RESON)));
			this.setSexCode(DataParser.parse(String.class, map.get(PersonMeta.SEX_CODE)));
			this.setCreateBy(DataParser.parse(String.class, map.get(PersonMeta.CREATE_BY)));
			this.setContactInformation(DataParser.parse(String.class, map.get(PersonMeta.CONTACT_INFORMATION)));
			this.setDeleted(DataParser.parse(Integer.class, map.get(PersonMeta.DELETED)));
			this.setGraduationDate(DataParser.parse(Date.class, map.get(PersonMeta.GRADUATION_DATE)));
			this.setCreateTime(DataParser.parse(Date.class, map.get(PersonMeta.CREATE_TIME)));
			this.setEmployeeTypeCode(DataParser.parse(String.class, map.get(PersonMeta.EMPLOYEE_TYPE_CODE)));
			this.setLeaveDate(DataParser.parse(Date.class, map.get(PersonMeta.LEAVE_DATE)));
			this.setTenantId(DataParser.parse(String.class, map.get(PersonMeta.TENANT_ID)));
			this.setPoliticCountenanceCode(DataParser.parse(String.class, map.get(PersonMeta.POLITIC_COUNTENANCE_CODE)));
			this.setBodyWeight(DataParser.parse(Integer.class, map.get(PersonMeta.BODY_WEIGHT)));
			// others
			this.setBloodTypeDict(DataParser.parse(DictItem.class, map.get(PersonMeta.BLOOD_TYPE_DICT)));
			this.setProfessionalLevel(DataParser.parse(ProfessionalLevel.class, map.get(PersonMeta.PROFESSIONAL_LEVEL)));
			this.setRank(DataParser.parse(Rank.class, map.get(PersonMeta.RANK)));
			this.setEmployeeOwnerTypeDict(DataParser.parse(DictItem.class, map.get(PersonMeta.EMPLOYEE_OWNER_TYPE_DICT)));
			this.setPosition(DataParser.parse(Position.class, map.get(PersonMeta.POSITION)));
			this.setEmployee(DataParser.parse(Employee.class, map.get(PersonMeta.EMPLOYEE)));
			this.setSexDict(DataParser.parse(DictItem.class, map.get(PersonMeta.SEX_DICT)));
			this.setMaritalStatusDict(DataParser.parse(DictItem.class, map.get(PersonMeta.MARITAL_STATUS_DICT)));
			return true;
		} else {
			try {
				this.setRankCode( (String)map.get(PersonMeta.RANK_CODE));
				this.setEmergencyContactNo( (String)map.get(PersonMeta.EMERGENCY_CONTACT_NO));
				this.setEmploymentConfirmDate( (Date)map.get(PersonMeta.EMPLOYMENT_CONFIRM_DATE));
				this.setNativePlaceCode( (String)map.get(PersonMeta.NATIVE_PLACE_CODE));
				this.setMajor( (String)map.get(PersonMeta.MAJOR));
				this.setComputerLevel( (String)map.get(PersonMeta.COMPUTER_LEVEL));
				this.setWorkKindCode( (String)map.get(PersonMeta.WORK_KIND_CODE));
				this.setId( (String)map.get(PersonMeta.ID));
				this.setPayrollCardBankCode( (String)map.get(PersonMeta.PAYROLL_CARD_BANK_CODE));
				this.setHomeAddress( (String)map.get(PersonMeta.HOME_ADDRESS));
				this.setEmployeeTitleCode( (String)map.get(PersonMeta.EMPLOYEE_TITLE_CODE));
				this.setForeignLanguageLevel( (String)map.get(PersonMeta.FOREIGN_LANGUAGE_LEVEL));
				this.setEmergencyContact( (String)map.get(PersonMeta.EMERGENCY_CONTACT));
				this.setGraduationSchool( (String)map.get(PersonMeta.GRADUATION_SCHOOL));
				this.setEmploymentDate( (Date)map.get(PersonMeta.EMPLOYMENT_DATE));
				this.setIdentityCard( (String)map.get(PersonMeta.IDENTITY_CARD));
				this.setVersion( (Integer)map.get(PersonMeta.VERSION));
				this.setDeleteTime( (Date)map.get(PersonMeta.DELETE_TIME));
				this.setName( (String)map.get(PersonMeta.NAME));
				this.setForeignLanguage( (String)map.get(PersonMeta.FOREIGN_LANGUAGE));
				this.setFirstEmploymentDate( (Date)map.get(PersonMeta.FIRST_EMPLOYMENT_DATE));
				this.setDeleteBy( (String)map.get(PersonMeta.DELETE_BY));
				this.setJobNumber( (String)map.get(PersonMeta.JOB_NUMBER));
				this.setMaritalStatus( (String)map.get(PersonMeta.MARITAL_STATUS));
				this.setFileId( (String)map.get(PersonMeta.FILE_ID));
				this.setBirthday( (Date)map.get(PersonMeta.BIRTHDAY));
				this.setNote( (String)map.get(PersonMeta.NOTE));
				this.setBodyHeight( (Integer)map.get(PersonMeta.BODY_HEIGHT));
				this.setPositionCode( (String)map.get(PersonMeta.POSITION_CODE));
				this.setEducationCode( (String)map.get(PersonMeta.EDUCATION_CODE));
				this.setComputerAbility( (String)map.get(PersonMeta.COMPUTER_ABILITY));
				this.setBloodType( (String)map.get(PersonMeta.BLOOD_TYPE));
				this.setEmployeeStatus( (String)map.get(PersonMeta.EMPLOYEE_STATUS));
				this.setOrgId( (String)map.get(PersonMeta.ORG_ID));
				this.setUpdateBy( (String)map.get(PersonMeta.UPDATE_BY));
				this.setPersonPictureId( (String)map.get(PersonMeta.PERSON_PICTURE_ID));
				this.setEmail( (String)map.get(PersonMeta.EMAIL));
				this.setNationCode( (String)map.get(PersonMeta.NATION_CODE));
				this.setFirstWorkDate( (Date)map.get(PersonMeta.FIRST_WORK_DATE));
				this.setPayrollCard( (String)map.get(PersonMeta.PAYROLL_CARD));
				this.setWeixinId( (String)map.get(PersonMeta.WEIXIN_ID));
				this.setEmployeeId( (String)map.get(PersonMeta.EMPLOYEE_ID));
				this.setUpdateTime( (Date)map.get(PersonMeta.UPDATE_TIME));
				this.setJoinPartDate( (Date)map.get(PersonMeta.JOIN_PART_DATE));
				this.setLeaveReson( (String)map.get(PersonMeta.LEAVE_RESON));
				this.setSexCode( (String)map.get(PersonMeta.SEX_CODE));
				this.setCreateBy( (String)map.get(PersonMeta.CREATE_BY));
				this.setContactInformation( (String)map.get(PersonMeta.CONTACT_INFORMATION));
				this.setDeleted( (Integer)map.get(PersonMeta.DELETED));
				this.setGraduationDate( (Date)map.get(PersonMeta.GRADUATION_DATE));
				this.setCreateTime( (Date)map.get(PersonMeta.CREATE_TIME));
				this.setEmployeeTypeCode( (String)map.get(PersonMeta.EMPLOYEE_TYPE_CODE));
				this.setLeaveDate( (Date)map.get(PersonMeta.LEAVE_DATE));
				this.setTenantId( (String)map.get(PersonMeta.TENANT_ID));
				this.setPoliticCountenanceCode( (String)map.get(PersonMeta.POLITIC_COUNTENANCE_CODE));
				this.setBodyWeight( (Integer)map.get(PersonMeta.BODY_WEIGHT));
				// others
				this.setBloodTypeDict( (DictItem)map.get(PersonMeta.BLOOD_TYPE_DICT));
				this.setProfessionalLevel( (ProfessionalLevel)map.get(PersonMeta.PROFESSIONAL_LEVEL));
				this.setRank( (Rank)map.get(PersonMeta.RANK));
				this.setEmployeeOwnerTypeDict( (DictItem)map.get(PersonMeta.EMPLOYEE_OWNER_TYPE_DICT));
				this.setPosition( (Position)map.get(PersonMeta.POSITION));
				this.setEmployee( (Employee)map.get(PersonMeta.EMPLOYEE));
				this.setSexDict( (DictItem)map.get(PersonMeta.SEX_DICT));
				this.setMaritalStatusDict( (DictItem)map.get(PersonMeta.MARITAL_STATUS_DICT));
				return true;
			} catch (Exception e) {
				return false;
			}
		}
	}

	/**
	 * 从 Map 读取
	 * @param r 记录数据
	 * @param cast 是否用 DataParser 进行类型转换
	 * @return  是否读取成功
	*/
	public boolean read(ExprRcd r,boolean cast) {
		if(r==null) return false;
		if(cast) {
			this.setRankCode(DataParser.parse(String.class, r.getValue(PersonMeta.RANK_CODE)));
			this.setEmergencyContactNo(DataParser.parse(String.class, r.getValue(PersonMeta.EMERGENCY_CONTACT_NO)));
			this.setEmploymentConfirmDate(DataParser.parse(Date.class, r.getValue(PersonMeta.EMPLOYMENT_CONFIRM_DATE)));
			this.setNativePlaceCode(DataParser.parse(String.class, r.getValue(PersonMeta.NATIVE_PLACE_CODE)));
			this.setMajor(DataParser.parse(String.class, r.getValue(PersonMeta.MAJOR)));
			this.setComputerLevel(DataParser.parse(String.class, r.getValue(PersonMeta.COMPUTER_LEVEL)));
			this.setWorkKindCode(DataParser.parse(String.class, r.getValue(PersonMeta.WORK_KIND_CODE)));
			this.setId(DataParser.parse(String.class, r.getValue(PersonMeta.ID)));
			this.setPayrollCardBankCode(DataParser.parse(String.class, r.getValue(PersonMeta.PAYROLL_CARD_BANK_CODE)));
			this.setHomeAddress(DataParser.parse(String.class, r.getValue(PersonMeta.HOME_ADDRESS)));
			this.setEmployeeTitleCode(DataParser.parse(String.class, r.getValue(PersonMeta.EMPLOYEE_TITLE_CODE)));
			this.setForeignLanguageLevel(DataParser.parse(String.class, r.getValue(PersonMeta.FOREIGN_LANGUAGE_LEVEL)));
			this.setEmergencyContact(DataParser.parse(String.class, r.getValue(PersonMeta.EMERGENCY_CONTACT)));
			this.setGraduationSchool(DataParser.parse(String.class, r.getValue(PersonMeta.GRADUATION_SCHOOL)));
			this.setEmploymentDate(DataParser.parse(Date.class, r.getValue(PersonMeta.EMPLOYMENT_DATE)));
			this.setIdentityCard(DataParser.parse(String.class, r.getValue(PersonMeta.IDENTITY_CARD)));
			this.setVersion(DataParser.parse(Integer.class, r.getValue(PersonMeta.VERSION)));
			this.setDeleteTime(DataParser.parse(Date.class, r.getValue(PersonMeta.DELETE_TIME)));
			this.setName(DataParser.parse(String.class, r.getValue(PersonMeta.NAME)));
			this.setForeignLanguage(DataParser.parse(String.class, r.getValue(PersonMeta.FOREIGN_LANGUAGE)));
			this.setFirstEmploymentDate(DataParser.parse(Date.class, r.getValue(PersonMeta.FIRST_EMPLOYMENT_DATE)));
			this.setDeleteBy(DataParser.parse(String.class, r.getValue(PersonMeta.DELETE_BY)));
			this.setJobNumber(DataParser.parse(String.class, r.getValue(PersonMeta.JOB_NUMBER)));
			this.setMaritalStatus(DataParser.parse(String.class, r.getValue(PersonMeta.MARITAL_STATUS)));
			this.setFileId(DataParser.parse(String.class, r.getValue(PersonMeta.FILE_ID)));
			this.setBirthday(DataParser.parse(Date.class, r.getValue(PersonMeta.BIRTHDAY)));
			this.setNote(DataParser.parse(String.class, r.getValue(PersonMeta.NOTE)));
			this.setBodyHeight(DataParser.parse(Integer.class, r.getValue(PersonMeta.BODY_HEIGHT)));
			this.setPositionCode(DataParser.parse(String.class, r.getValue(PersonMeta.POSITION_CODE)));
			this.setEducationCode(DataParser.parse(String.class, r.getValue(PersonMeta.EDUCATION_CODE)));
			this.setComputerAbility(DataParser.parse(String.class, r.getValue(PersonMeta.COMPUTER_ABILITY)));
			this.setBloodType(DataParser.parse(String.class, r.getValue(PersonMeta.BLOOD_TYPE)));
			this.setEmployeeStatus(DataParser.parse(String.class, r.getValue(PersonMeta.EMPLOYEE_STATUS)));
			this.setOrgId(DataParser.parse(String.class, r.getValue(PersonMeta.ORG_ID)));
			this.setUpdateBy(DataParser.parse(String.class, r.getValue(PersonMeta.UPDATE_BY)));
			this.setPersonPictureId(DataParser.parse(String.class, r.getValue(PersonMeta.PERSON_PICTURE_ID)));
			this.setEmail(DataParser.parse(String.class, r.getValue(PersonMeta.EMAIL)));
			this.setNationCode(DataParser.parse(String.class, r.getValue(PersonMeta.NATION_CODE)));
			this.setFirstWorkDate(DataParser.parse(Date.class, r.getValue(PersonMeta.FIRST_WORK_DATE)));
			this.setPayrollCard(DataParser.parse(String.class, r.getValue(PersonMeta.PAYROLL_CARD)));
			this.setWeixinId(DataParser.parse(String.class, r.getValue(PersonMeta.WEIXIN_ID)));
			this.setEmployeeId(DataParser.parse(String.class, r.getValue(PersonMeta.EMPLOYEE_ID)));
			this.setUpdateTime(DataParser.parse(Date.class, r.getValue(PersonMeta.UPDATE_TIME)));
			this.setJoinPartDate(DataParser.parse(Date.class, r.getValue(PersonMeta.JOIN_PART_DATE)));
			this.setLeaveReson(DataParser.parse(String.class, r.getValue(PersonMeta.LEAVE_RESON)));
			this.setSexCode(DataParser.parse(String.class, r.getValue(PersonMeta.SEX_CODE)));
			this.setCreateBy(DataParser.parse(String.class, r.getValue(PersonMeta.CREATE_BY)));
			this.setContactInformation(DataParser.parse(String.class, r.getValue(PersonMeta.CONTACT_INFORMATION)));
			this.setDeleted(DataParser.parse(Integer.class, r.getValue(PersonMeta.DELETED)));
			this.setGraduationDate(DataParser.parse(Date.class, r.getValue(PersonMeta.GRADUATION_DATE)));
			this.setCreateTime(DataParser.parse(Date.class, r.getValue(PersonMeta.CREATE_TIME)));
			this.setEmployeeTypeCode(DataParser.parse(String.class, r.getValue(PersonMeta.EMPLOYEE_TYPE_CODE)));
			this.setLeaveDate(DataParser.parse(Date.class, r.getValue(PersonMeta.LEAVE_DATE)));
			this.setTenantId(DataParser.parse(String.class, r.getValue(PersonMeta.TENANT_ID)));
			this.setPoliticCountenanceCode(DataParser.parse(String.class, r.getValue(PersonMeta.POLITIC_COUNTENANCE_CODE)));
			this.setBodyWeight(DataParser.parse(Integer.class, r.getValue(PersonMeta.BODY_WEIGHT)));
			return true;
		} else {
			try {
				this.setRankCode( (String)r.getValue(PersonMeta.RANK_CODE));
				this.setEmergencyContactNo( (String)r.getValue(PersonMeta.EMERGENCY_CONTACT_NO));
				this.setEmploymentConfirmDate( (Date)r.getValue(PersonMeta.EMPLOYMENT_CONFIRM_DATE));
				this.setNativePlaceCode( (String)r.getValue(PersonMeta.NATIVE_PLACE_CODE));
				this.setMajor( (String)r.getValue(PersonMeta.MAJOR));
				this.setComputerLevel( (String)r.getValue(PersonMeta.COMPUTER_LEVEL));
				this.setWorkKindCode( (String)r.getValue(PersonMeta.WORK_KIND_CODE));
				this.setId( (String)r.getValue(PersonMeta.ID));
				this.setPayrollCardBankCode( (String)r.getValue(PersonMeta.PAYROLL_CARD_BANK_CODE));
				this.setHomeAddress( (String)r.getValue(PersonMeta.HOME_ADDRESS));
				this.setEmployeeTitleCode( (String)r.getValue(PersonMeta.EMPLOYEE_TITLE_CODE));
				this.setForeignLanguageLevel( (String)r.getValue(PersonMeta.FOREIGN_LANGUAGE_LEVEL));
				this.setEmergencyContact( (String)r.getValue(PersonMeta.EMERGENCY_CONTACT));
				this.setGraduationSchool( (String)r.getValue(PersonMeta.GRADUATION_SCHOOL));
				this.setEmploymentDate( (Date)r.getValue(PersonMeta.EMPLOYMENT_DATE));
				this.setIdentityCard( (String)r.getValue(PersonMeta.IDENTITY_CARD));
				this.setVersion( (Integer)r.getValue(PersonMeta.VERSION));
				this.setDeleteTime( (Date)r.getValue(PersonMeta.DELETE_TIME));
				this.setName( (String)r.getValue(PersonMeta.NAME));
				this.setForeignLanguage( (String)r.getValue(PersonMeta.FOREIGN_LANGUAGE));
				this.setFirstEmploymentDate( (Date)r.getValue(PersonMeta.FIRST_EMPLOYMENT_DATE));
				this.setDeleteBy( (String)r.getValue(PersonMeta.DELETE_BY));
				this.setJobNumber( (String)r.getValue(PersonMeta.JOB_NUMBER));
				this.setMaritalStatus( (String)r.getValue(PersonMeta.MARITAL_STATUS));
				this.setFileId( (String)r.getValue(PersonMeta.FILE_ID));
				this.setBirthday( (Date)r.getValue(PersonMeta.BIRTHDAY));
				this.setNote( (String)r.getValue(PersonMeta.NOTE));
				this.setBodyHeight( (Integer)r.getValue(PersonMeta.BODY_HEIGHT));
				this.setPositionCode( (String)r.getValue(PersonMeta.POSITION_CODE));
				this.setEducationCode( (String)r.getValue(PersonMeta.EDUCATION_CODE));
				this.setComputerAbility( (String)r.getValue(PersonMeta.COMPUTER_ABILITY));
				this.setBloodType( (String)r.getValue(PersonMeta.BLOOD_TYPE));
				this.setEmployeeStatus( (String)r.getValue(PersonMeta.EMPLOYEE_STATUS));
				this.setOrgId( (String)r.getValue(PersonMeta.ORG_ID));
				this.setUpdateBy( (String)r.getValue(PersonMeta.UPDATE_BY));
				this.setPersonPictureId( (String)r.getValue(PersonMeta.PERSON_PICTURE_ID));
				this.setEmail( (String)r.getValue(PersonMeta.EMAIL));
				this.setNationCode( (String)r.getValue(PersonMeta.NATION_CODE));
				this.setFirstWorkDate( (Date)r.getValue(PersonMeta.FIRST_WORK_DATE));
				this.setPayrollCard( (String)r.getValue(PersonMeta.PAYROLL_CARD));
				this.setWeixinId( (String)r.getValue(PersonMeta.WEIXIN_ID));
				this.setEmployeeId( (String)r.getValue(PersonMeta.EMPLOYEE_ID));
				this.setUpdateTime( (Date)r.getValue(PersonMeta.UPDATE_TIME));
				this.setJoinPartDate( (Date)r.getValue(PersonMeta.JOIN_PART_DATE));
				this.setLeaveReson( (String)r.getValue(PersonMeta.LEAVE_RESON));
				this.setSexCode( (String)r.getValue(PersonMeta.SEX_CODE));
				this.setCreateBy( (String)r.getValue(PersonMeta.CREATE_BY));
				this.setContactInformation( (String)r.getValue(PersonMeta.CONTACT_INFORMATION));
				this.setDeleted( (Integer)r.getValue(PersonMeta.DELETED));
				this.setGraduationDate( (Date)r.getValue(PersonMeta.GRADUATION_DATE));
				this.setCreateTime( (Date)r.getValue(PersonMeta.CREATE_TIME));
				this.setEmployeeTypeCode( (String)r.getValue(PersonMeta.EMPLOYEE_TYPE_CODE));
				this.setLeaveDate( (Date)r.getValue(PersonMeta.LEAVE_DATE));
				this.setTenantId( (String)r.getValue(PersonMeta.TENANT_ID));
				this.setPoliticCountenanceCode( (String)r.getValue(PersonMeta.POLITIC_COUNTENANCE_CODE));
				this.setBodyWeight( (Integer)r.getValue(PersonMeta.BODY_WEIGHT));
				return true;
			} catch (Exception e) {
				return false;
			}
		}
	}
}