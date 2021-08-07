package com.dt.platform.relation.modules;


import com.dt.platform.constants.db.EAMTables;

import com.dt.platform.domain.datacenter.meta.RackMeta;
import com.dt.platform.domain.eam.Goods;
import com.dt.platform.domain.eam.meta.GoodsMeta;
import com.github.foxnic.dao.relation.RelationManager;

public class EAMRelationManager extends RelationManager {
    @Override
    protected void config() {
        this.setupRelations();
        this.setupProperties();
        this.setupGoods();

    }

    public void setupProperties() {

    }

    private void setupRelations() {

    }
    private void setupGoods() {
        // 关联品牌
        this.property(GoodsMeta.BRAND_PROP)
                .using(EAMTables.EAM_GOODS.BRAND_ID).join(EAMTables.EAM_BRAND.ID);

        // 关联生产厂商
        this.property(GoodsMeta.MANUFACTURER_PROP)
                .using(EAMTables.EAM_GOODS.MANUFACTURER_ID).join(EAMTables.EAM_MANUFACTURER.ID);

        // 关联分类
        this.property(GoodsMeta.CATEGORY_PROP)
                .using(EAMTables.EAM_GOODS.CATEGORY_ID).join(EAMTables.EAM_CATEGORY.ID);

    }

}