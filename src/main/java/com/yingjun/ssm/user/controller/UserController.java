package com.yingjun.ssm.user.controller;

import com.yingjun.ssm.common.result.Result;
import com.yingjun.ssm.entity.User;
import com.yingjun.ssm.user.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Resource
    private UserService userService;

    @RequestMapping(value = "/user/list", method = RequestMethod.GET)
    public String list(Model model, Integer offset, Integer limit) {
        LOG.info("invoke----------/user/list");
        offset = offset == null ? 0 : offset;//默认便宜0
        limit = limit == null ? 50 : limit;//默认展示50条
        List<User> list = userService.getUserList(offset, limit);
        model.addAttribute("userlist", list);
        return "userlist";
    }

    @RequestMapping(value = "/toLogin", method = RequestMethod.GET)
    public String toLogin() {
        LOG.info("============to login.jsp");
        return "login";
    }

    @RequestMapping(value = "login11", method = RequestMethod.POST)
    public String login() {


        return "index";
    }

    /**
     * 异常统一处理测试
     *
     * @param id
     * @throws Exception
     */
    @RequestMapping(value = "test/result/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Result getAgeReturnResult(@PathVariable("id") String id) throws Exception {
        return userService.getAgeReturnResult(id);
    }

    /**
     * 用户登录
     *
     * @param user
     * @param result
     * @return @Valid
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(User user, BindingResult result, Model model, HttpServletRequest request) {
        try {
            Subject subject = SecurityUtils.getSubject();
            // 已登陆则 跳到首页
           /* if (subject.isAuthenticated()) {
//				return "redirect:/";
                return "success";
            }
            if (result.hasErrors()) {
                model.addAttribute("error", "参数错误！");
                return "login";
            }*/
            /**
             *  身份验证，根据配置spring-shiro.xml 中的安全管理器配置的realm（此项目中是securityRealm）会跳转到
             *  securityRealm.java 中去进行身份验证，验证通过，此项目跳到index.jsp 页面
             *  在Index.jsp 中使用了 <shiro:hasAnyRoles name="super_admin"> ， <shiro:hasPermission name="user:create">
             *  类似这种需要验证角色，权限的，则又会去securityRealm.java 中去验证角色权限，来达到导航栏是否显示的目的
             *
             *
             */
            UsernamePasswordToken token = new UsernamePasswordToken(user.getAccount(), user.getPassword());
            subject.login(token);
            // 验证成功在Session中保存用户信息
            final User authUserInfo = userService.selectByAccount(user.getAccount());
            request.getSession().setAttribute("userInfo", authUserInfo);
            return "success";
        } catch (UnknownAccountException e) {
            // 身份验证失败
            model.addAttribute("error", "UnknownAccountException ！");
            return "login";
        } catch (IncorrectCredentialsException e) {
            // 身份验证失败
            model.addAttribute("error", "IncorrectCredentialsException ！");
            return "login";
        } catch (LockedAccountException e) {
            // 身份验证失败
            model.addAttribute("error", "LockedAccountException ！");
            return "login";
        } catch (ExcessiveAttemptsException e) {// 身份验证失败
            model.addAttribute("error", "ExcessiveAttemptsException ！");
            return "login";
        } catch (AuthenticationException e) {
            // 身份验证失败
            model.addAttribute("error", "AuthenticationException ！");
            return "login";
        }
        /*
        String url = null;
        try {
            //这里获得登陆之前访问的一个页面，如果有登陆之后直接跳转到当前访问的页面，如果没有，则根据配置文件跳转到登陆成功后的页面
            url = WebUtils.getSavedRequest(request).getRequestUrl();

        } catch (Exception e) {
            // TODO: handle exception
        }
        if (url == null || "".equals(url)) {
            return "success";
        }
        System.out.println(url);

        return "redirect:" + url;
        */
    }

    /**
     * 用户登出
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.removeAttribute("userInfo");
        // 登出操作
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }
}
