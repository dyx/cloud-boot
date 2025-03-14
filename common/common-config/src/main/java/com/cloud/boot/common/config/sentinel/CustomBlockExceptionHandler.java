package com.cloud.boot.common.config.sentinel;

import com.alibaba.csp.sentinel.adapter.spring.webmvc_v6x.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.cloud.boot.common.core.constant.enums.GlobalErrorCodeEnum;
import com.cloud.boot.common.core.jackson.JacksonUtil;
import com.cloud.boot.common.core.util.R;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

/**
 * @author lhd
 */
@Slf4j
public class CustomBlockExceptionHandler implements BlockExceptionHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, String resourceName, BlockException e) throws Exception {

		log.error("sentinel流控异常，资源名称：{}", resourceName, e);

		response.setContentType("application/json;charset=UTF-8");
		response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
		response.getWriter().print(JacksonUtil.toJsonStr(R.fail(GlobalErrorCodeEnum.SENTINEL_TOO_MANY_REQUESTS)));
	}

}
