package com.jin.expertsystem.expertsystem.business.common.service.impl;

import com.jin.expertsystem.expertsystem.base.jwt.JwtTokenUtil;
import com.jin.expertsystem.expertsystem.business.common.dao.CommonExpertInfoDao;
import com.jin.expertsystem.expertsystem.business.common.dao.CommonRoleUserDao;
import com.jin.expertsystem.expertsystem.business.common.dao.CommonUsersDao;
import com.jin.expertsystem.expertsystem.business.common.model.ExpertInfo;
import com.jin.expertsystem.expertsystem.business.common.model.ExpertInfotoShow;
import com.jin.expertsystem.expertsystem.business.common.model.RoleUser;
import com.jin.expertsystem.expertsystem.business.common.model.Users;
import com.jin.expertsystem.expertsystem.business.common.service.CommonExpertInfoService;
import com.jin.expertsystem.expertsystem.business.common.need.AbstractMyService;
import com.jin.expertsystem.expertsystem.business.personalManage.service.PersonalService;
import com.jin.expertsystem.expertsystem.business.sysmanage.model.UserRoleInfo;
import com.jin.expertsystem.expertsystem.business.sysmanage.service.UsersService;
import com.jin.expertsystem.expertsystem.utils.Utils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.transaction.annotation.Transactional;
import sun.management.counter.Units;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* @author JMY
* @date 2020/03/17
*/
@Service
public class CommonExpertInfoServiceImpl extends AbstractMyService<ExpertInfo> implements CommonExpertInfoService {

    @Autowired
    private CommonExpertInfoDao commonExpertInfoDao;

    @Autowired
    private PersonalService personalService;
    @Autowired
    private CommonUsersDao commonUsersDao;
    @Autowired
    private CommonRoleUserDao commonRoleUserDao;


    @Override
    public PageInfo selectAllForPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo(commonExpertInfoDao.selectAll());
    }

    @Override
    public List<ExpertInfotoShow> selectExpertInfoByName(String name) {
        return commonExpertInfoDao.selectExpertInfoByName(name);
    }

    @Override
    public ExpertInfo selectExpertInfoById(String expertId) {
        return commonExpertInfoDao.selectExpertInfoById(expertId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer insertExpertInfo(ExpertInfo expertInfo, HttpServletRequest request) {
        UserRoleInfo userRoleInfo = personalService.selectUserInfoByToken(request);
        if (userRoleInfo.getRoleId() ==2){
            expertInfo.setExpertId(userRoleInfo.getId());
            return commonExpertInfoDao.insertSelective(expertInfo);
        }else {
            String userId = Utils.getUUID();
            Users users =new Users();
            expertInfo.setExpertId(userId);
            int j = commonExpertInfoDao.insertSelective(expertInfo);
            BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
            //初始密码为123456
            users.setId(userId);
            users.setUsername(expertInfo.getIcCard().substring(10));
            users.setPassword(encoder.encode("123456"));
            users.setStatus("1");
            users.setSex(expertInfo.getSex());
            users.setPhone(expertInfo.getPhone());
            int i=commonUsersDao.insertSelective(users);
            RoleUser roleUser=new RoleUser();
            roleUser.setUserId(users.getId());
            //默认是用户
            roleUser.setRoleId(2);
            int k=commonRoleUserDao.insertSelective(roleUser);
            return i*j*k;
        }
    }

}
