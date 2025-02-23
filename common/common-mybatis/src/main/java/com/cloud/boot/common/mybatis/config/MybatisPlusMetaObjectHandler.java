package com.cloud.boot.common.mybatis.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import java.time.LocalDateTime;

/**
 * @author lhd
 */
public class MybatisPlusMetaObjectHandler implements MetaObjectHandler {

	@Override
	public void insertFill(MetaObject metaObject) {
		LocalDateTime now = LocalDateTime.now();
		this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, now);
		this.strictInsertFill(metaObject, "createBy", Long.class, 0L);
		this.strictInsertFill(metaObject, "updateTime", LocalDateTime.class, now);
		this.strictInsertFill(metaObject, "updateBy", Long.class, 0L);

	}

	@Override
	public void updateFill(MetaObject metaObject) {
		this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
		this.strictUpdateFill(metaObject, "updateBy", Long.class, 0L);
	}
}
