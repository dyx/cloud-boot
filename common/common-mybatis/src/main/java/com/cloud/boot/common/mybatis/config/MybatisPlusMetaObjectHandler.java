package com.cloud.boot.common.mybatis.config;

import cn.dev33.satoken.stp.StpUtil;
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
		Long userId = getUserId();
		this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, now);
		this.strictInsertFill(metaObject, "createBy", Long.class, userId);
		this.strictInsertFill(metaObject, "updateTime", LocalDateTime.class, now);
		this.strictInsertFill(metaObject, "updateBy", Long.class, userId);

	}

	@Override
	public void updateFill(MetaObject metaObject) {
		this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
		this.strictUpdateFill(metaObject, "updateBy", Long.class, getUserId());
	}

	private Long getUserId() {
		try {
			return StpUtil.getLoginIdAsLong();
		} catch (Exception ignored) {}
		return -1L;
	}
}
