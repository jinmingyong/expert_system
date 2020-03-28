package com.jin.expertsystem.expertsystem.business.sysmanage.service.impl;

import com.jin.expertsystem.expertsystem.business.common.dao.CommonRoleUserDao;
import com.jin.expertsystem.expertsystem.business.common.dao.CommonUsersDao;
import com.jin.expertsystem.expertsystem.business.common.model.RoleUser;
import com.jin.expertsystem.expertsystem.business.common.model.Users;
import com.jin.expertsystem.expertsystem.business.sysmanage.model.UserRoleInfo;
import com.jin.expertsystem.expertsystem.business.sysmanage.service.UsersService;
import com.jin.expertsystem.expertsystem.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

/**
 * @author jinmingyong
 * @date 2020/3/24 20:12
 */
@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private CommonUsersDao commonUsersDao;
    @Autowired
    private CommonRoleUserDao commonRoleUserDao;

    @Override
    public Boolean selectUsersByUsername(Users users) {
        Example example =new Example(Users.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username",users.getUsername());
        if (commonUsersDao.selectCountByExample(example)==0){
            return true;
        }
        else{
            return false;
        }
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer insertUsersInfo(Users users) {
        users.setId(Utils.getUUID());
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        //初始密码为123456
        users.setPassword(encoder.encode("123456"));
        users.setStatus("1");
        int i=commonUsersDao.insertSelective(users);
        RoleUser roleUser=new RoleUser();
        roleUser.setUserId(users.getId());
        //默认是用户
        roleUser.setRoleId(2);
        int j=commonRoleUserDao.insertSelective(roleUser);
        if (i*j==0){
            return 0;
        }else {
            return 1;
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer deleteById(String Id) {
        int i = commonUsersDao.deleteByPrimaryKey(Id);
        Example example =new Example(RoleUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId",Id);
        int j = commonRoleUserDao.deleteByExample(example);
        if (i*j==0){
            return 0;
        }else {
            return 1;
        }
    }

    @Override
    public Integer updateUserRole(RoleUser roleUser) {
        Example example =new Example(RoleUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId",roleUser.getUserId());
        return commonRoleUserDao.updateByExampleSelective(roleUser,example);
    }
}
