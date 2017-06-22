package com.yingjun.ssm.security;

import java.util.List;
import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;
import com.yingjun.ssm.entity.User;
import com.yingjun.ssm.user.service.PermissionService;
import com.yingjun.ssm.user.service.RoleService;
import com.yingjun.ssm.user.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 用户身份验证,授权 Realm 组件
 * 
 * @author StarZou
 * @since 2014年6月11日 上午11:35:28
 **/
@Component(value = "securityRealm")
public class SecurityRealm extends AuthorizingRealm {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private UserService userService;
    @Resource
    private RoleService roleService;

    @Resource
    private PermissionService permissionService;
    /**
     * 权限检查
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        String username = String.valueOf(principals.getPrimaryPrincipal());
        System.out.println(JSON.toJSON("=============权限检查start============="));
        System.out.println(JSON.toJSON(principals));
        System.out.println(JSON.toJSON("=============权限检查end============="));

        /*final User user = userService.selectByUsername(username);
        final List<Role> roleInfos = roleService.selectRolesByUserId(user.getId());
        for (Role role : roleInfos) {
            // 添加角色
            System.err.println(role);
            authorizationInfo.addRole(role.getRoleSign());

            final List<Permission> permissions = permissionService.selectPermissionsByRoleId(role.getId());
            for (Permission permission : permissions) {
                // 添加权限
                System.err.println(permission);
                authorizationInfo.addStringPermission(permission.getPermissionSign());
            }
        }*/
        return authorizationInfo;
    }

    /**
     * 登录验证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String account = String.valueOf(token.getPrincipal());
        String password = new String((char[]) token.getCredentials());
        logger.info("account: " + account);
        logger.info("password: " + password);
        // 通过数据库进行验证
        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        final User authentication = userService.authentication(user);
        System.out.println("authentication ================== " + JSON.toJSON(authentication));
        if (authentication == null) {
            logger.info("authentication == null====================");
            throw new AuthenticationException("用户名或密码错误.");
        }
        System.out.println("=========登录验证============");
        System.out.println(JSON.toJSON(token));
        System.out.println("=========登录验证============");
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(account, password, getName());
        System.out.println(JSON.toJSON(authenticationInfo));
        return authenticationInfo;
    }

}
