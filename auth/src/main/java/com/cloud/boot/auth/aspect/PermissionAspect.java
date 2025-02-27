package com.cloud.boot.auth.aspect;

import com.cloud.boot.auth.annotation.HasPermission;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * @author lhd
 */
@Aspect
@Component
public class PermissionAspect {

    @Pointcut("@annotation( com.cloud.boot.auth.annotation.HasPermission)")
    public void pointcut() {}

    @Around("@annotation(hasPermissionAnnotation)")
    public Object checkPermission(ProceedingJoinPoint joinPoint, HasPermission hasPermissionAnnotation) throws Throwable {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String[] requiredPermissions = hasPermissionAnnotation.value();

        boolean hasPermission = checkUserPermissions(authentication, requiredPermissions);
        if (!hasPermission) {
            throw new AccessDeniedException("无权访问");
        }

        return joinPoint.proceed();
    }

    private boolean checkUserPermissions(Authentication authentication, String[] requiredPermissions) {
        return authentication.getAuthorities().stream()
                .anyMatch(authority -> {
                    for (String requiredPermission : requiredPermissions) {
                        if (authority.getAuthority().equals(requiredPermission)) {
                            return true;
                        }
                    }
                    return false;
                });
    }
}