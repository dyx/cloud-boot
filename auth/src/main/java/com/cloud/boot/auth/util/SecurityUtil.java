package com.cloud.boot.auth.util;

import cn.hutool.core.util.StrUtil;
import com.cloud.boot.auth.service.UserDetailsVo;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author lhd
 */
@UtilityClass
public class SecurityUtil {

	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	public UserDetailsVo getUser(Authentication authentication) {
		Object principal = authentication.getPrincipal();
		if (principal instanceof UserDetailsVo) {
			return (UserDetailsVo) principal;
		}
		return null;
	}

	public UserDetailsVo getUser() {
		Authentication authentication = getAuthentication();
		if (authentication == null) {
			return null;
		}
		return getUser(authentication);
	}

	public List<Long> getRoleList() {
		Authentication authentication = getAuthentication();
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

		List<Long> roleIds = new ArrayList<>();
		authorities.stream()
			.filter(granted -> StrUtil.startWith(granted.getAuthority(), "ROLE_"))
			.forEach(granted -> {
				String id = StrUtil.removePrefix(granted.getAuthority(), "ROLE_");
				roleIds.add(Long.parseLong(id));
			});
		return roleIds;
	}

}
