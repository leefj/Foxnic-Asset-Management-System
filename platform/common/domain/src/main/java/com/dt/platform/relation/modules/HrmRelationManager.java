package com.dt.platform.relation.modules;

 
import com.dt.platform.constants.db.EAMTables;
import com.dt.platform.constants.db.HrTables;
import com.dt.platform.constants.db.KnTables;
import com.dt.platform.domain.eam.meta.MaintainTaskProjectMeta;
import com.dt.platform.domain.eam.meta.RepairRuleMeta;
import com.dt.platform.domain.hr.PersonContract;
import com.dt.platform.domain.hr.Position;
import com.dt.platform.domain.hr.meta.PersonContractMeta;
import com.dt.platform.domain.hr.meta.PersonMeta;
import com.dt.platform.domain.hr.meta.PositionMeta;
import com.dt.platform.domain.knowledgebase.meta.ContentMeta;
import com.github.foxnic.dao.relation.RelationManager;
import com.github.foxnic.sql.meta.DBField;
import org.github.foxnic.web.constants.db.FoxnicWeb;
import org.github.foxnic.web.domain.oauth.Menu;
import org.github.foxnic.web.domain.oauth.Role;
import org.github.foxnic.web.domain.oauth.RoleMenu;
import org.github.foxnic.web.domain.oauth.User;
import org.github.foxnic.web.domain.system.meta.DictItemMeta;

public class HrmRelationManager extends RelationManager {

	@Override
	protected void config() {
		this.setupRelations();
		this.setupProperties();

		this.setupPosition();

		this.setupPerson();

		this.setupPersonContract();

	}
 
	public void setupProperties() {

	}
	
	private void setupRelations() {

	}

	private void setupPosition() {
		this.property(PositionMeta.POSITION_TYPE_PROP)
				.using(HrTables.HR_POSITION.TYPE).join(HrTables.HR_POSITION_TYPE.ID);

	}

	private void setupPersonContract() {

		this.property(PersonContractMeta.CONTRACT_DURATION_DICT_PROP)
				.using(HrTables.HR_PERSON_CONTRACT.CONTRACT_DURATION).join(FoxnicWeb.SYS_DICT_ITEM.CODE)
				.condition("dict_code='hr_contract_duration'");


	}

	private void setupPerson() {

		this.property(PersonMeta.RANK_PROP)
				.using(HrTables.HR_PERSON.RANK_CODE).join(HrTables.HR_RANK.ID);

		this.property(PersonMeta.POSITION_PROP)
				.using(HrTables.HR_PERSON.POSITION_CODE).join(HrTables.HR_POSITION.ID);

		this.property(PersonMeta.PROFESSIONAL_LEVEL_PROP)
				.using(HrTables.HR_PERSON.EMPLOYEE_TITLE_CODE).join(HrTables.HR_PROFESSIONAL_LEVEL.ID);

		this.property(PersonMeta.BLOOD_TYPE_DICT_PROP)
				.using(HrTables.HR_PERSON.BLOOD_TYPE).join(FoxnicWeb.SYS_DICT_ITEM.CODE)
				.condition("dict_code='hr_blood_type'");

		this.property(PersonMeta.SEX_DICT_PROP)
				.using(HrTables.HR_PERSON.SEX_CODE).join(FoxnicWeb.SYS_DICT_ITEM.CODE)
				.condition("dict_code='sex'");

		this.property(PersonMeta.MARITAL_STATUS_DICT_PROP)
				.using(HrTables.HR_PERSON.MARITAL_STATUS).join(FoxnicWeb.SYS_DICT_ITEM.CODE)
				.condition("dict_code='hr_marital_status'");

		this.property(PersonMeta.EMPLOYEE_OWNER_TYPE_DICT_PROP)
				.using(HrTables.HR_PERSON.EMPLOYEE_TYPE_CODE).join(FoxnicWeb.SYS_DICT_ITEM.CODE)
				.condition("dict_code='hr_employee_owner_type'");


		this.property(PersonMeta.EMPLOYEE_PROP)
				.using(HrTables.HR_PERSON.EMPLOYEE_ID).join(FoxnicWeb.HRM_EMPLOYEE.ID);

	}

	private void setupDcRack() {
//				this.property(DC_RACK.class, "roles", Role.class, "角色清单", "当前用户的所有角色清单").list().
//				using(new DBField[]{FoxnicWeb.SYS_USER.ID}).
//				join(new DBField[]{FoxnicWeb.SYS_ROLE_USER.USER_ID}).
//				using(new DBField[]{FoxnicWeb.SYS_ROLE_USER.ROLE_ID}).
//				join(new DBField[]{FoxnicWeb.SYS_ROLE.ID});
}


}
