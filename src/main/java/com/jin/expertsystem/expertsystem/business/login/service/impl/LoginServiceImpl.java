package com.jin.expertsystem.expertsystem.business.login.service.impl;

import com.jin.expertsystem.expertsystem.base.jwt.JwtTokenUtil;
import com.jin.expertsystem.expertsystem.base.jwt.JwtUser;
import com.jin.expertsystem.expertsystem.base.result.Result;
import com.jin.expertsystem.expertsystem.business.common.dao.CommonRoleUserDao;
import com.jin.expertsystem.expertsystem.business.common.dao.CommonUsersDao;
import com.jin.expertsystem.expertsystem.business.common.model.RoleUser;
import com.jin.expertsystem.expertsystem.business.common.model.Users;
import com.jin.expertsystem.expertsystem.business.login.dao.LoginDao;
import com.jin.expertsystem.expertsystem.business.login.service.LoginService;
import com.jin.expertsystem.expertsystem.business.sysmanage.model.UserRoleInfo;
import com.jin.expertsystem.expertsystem.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author GaoLiwei
 * @date 2019/5/15
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Value("${user.pwd}")
    String pwd;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.requestHeader}")
    private String requestHeader;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private CommonUsersDao commonUsersDao;

    @Autowired
    private CommonRoleUserDao commonRoleUserDao;


    @Autowired
    LoginDao loginDao;

    @Override
    public Result login(String userNumber, String password) {
        Users users = loginDao.queryUserStatus(userNumber);
        if (users != null) {
            if (users.getStatus().equals("0")) {
                return Result.result(0, "Discontinue use");
            }
        }
        //获得包含用户密码的Authentication
        UsernamePasswordAuthenticationToken usernamePasswordAuthentication = new UsernamePasswordAuthenticationToken(userNumber, password);
        //获得详细的Authentication
        final Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthentication);
        //在上下文中设置Authentication
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //生成token
        final String token = jwtTokenUtil.generateToken((JwtUser) authentication.getPrincipal());
        return Result.result(1, token);
    }


    @Override
    public Result register(Users user) {
        Users selUser = new Users();
        selUser.setUsername(user.getUsername());
        if (commonUsersDao.selectOne(selUser) != null) {
            return Result.result(0, "注册成功", "注册失败，用户已存在，请登录！");
        }
        String userId = Utils.getUUID();
        user.setId(userId);
        user.setStatus("1");
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(pwd));
        int insert = commonUsersDao.insert(user);
        if (insert > 0) {
            RoleUser roleUser = new RoleUser();
            roleUser.setUserId(userId);
            roleUser.setRoleId(2);
            commonRoleUserDao.insert(roleUser);
            return Result.result(1, "新建用户成功", "注册失败，用户已存在，请登录！");
        }
        return Result.result(0, "注册成功", "注册失败,请重试！");
    }
/*
    @Override
    public Result refresh(String oldToken) {
        final String tokenPlayload = oldToken.substring(tokenHead.length());
        if (jwtTokenUtil.canTokenBeRefreshed(tokenPlayload)) {
            return Result.result(1,"刷新成功","刷新失败", jwtTokenUtil.refreshToken(tokenPlayload));
        }
        return Result.result(0,"刷新成功","刷新失败", tokenPlayload);
    }

    @Override
    public List<MenuInfo> queryMenuList(HttpServletRequest request){
        String token = request.getHeader(requestHeader);
        String realToken = token.substring(tokenHeader.length());
        //获取登录名login_name
        String loginName = jwtTokenUtil.getUsernameFromToken(realToken);
        //UserRoleInfo userRoleInfo = userDao.getUserInfoByToken(loginName);
        List<MenuInfo> menuInfoList = new ArrayList<>();
        List<Integer> permIdList = menuInfoDao.listPermissions(loginName);
        StringBuilder permIds = new StringBuilder();
        permIdList.forEach(item -> {
            permIds.append(item);
            permIds.append(",");
        });
        //去除最后多余的逗号
        permIds.deleteCharAt(permIds.length() - 1);
        //查询父级菜单列表
        List<Menus> fatherMenus = menuInfoDao.queryFatherMenuList(permIds.toString());
        //查询子级菜单列表
        List<Menus> childMenus = menuInfoDao.queryChildMenuList(permIds.toString());
        //放入父级菜单列表
        fatherMenus.forEach(menus -> {
            MenuInfo menuInfo = new MenuInfo();
            menuInfo.setMenus(menus);
            menuInfoList.add(menuInfo);
        });
        for (MenuInfo menusInfo : menuInfoList) {
            for (Menus menus : childMenus) {
                //将子级菜单放入对于父级菜单下
                if (menusInfo.getMenus().getMenuId().equals(menus.getParentId())){
                    menusInfo.getMenusList().add(menus);
                }
            }
        }
        return menuInfoList;
    }
*/

    @Override
    public UserRoleInfo getUserInfoByToken(HttpServletRequest request) {
        String token = request.getHeader(requestHeader);
        String realToken = token.substring(tokenHeader.length());
        String loginName = jwtTokenUtil.getUsernameFromToken(realToken);
        return loginDao.getUserInfoByToken(loginName);
    }
}
