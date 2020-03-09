package com.jin.expertsystem.expertsystem.business.common.check;

import com.jin.expertsystem.expertsystem.business.common.dao.CommonRolePermissionDao;
import com.jin.expertsystem.expertsystem.business.common.dao.CommonRoleUserDao;
import com.jin.expertsystem.expertsystem.business.common.model.RolePermission;
import com.jin.expertsystem.expertsystem.business.common.model.RoleUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by JiChao on 2019/5/15.
 * 检查 用户、角色、权限 是否可以被删除
 * 关联了数据的信息不能被删除
 */
@Component
public class DeleteCheckService {

    @Autowired
    private CommonRoleUserDao commonRoleUserDao;
    @Autowired
    private CommonRolePermissionDao commonRolePermissionDao;

    /**
     * 判断用户是否可以被删除
     * @param id 用户表主键
     * @return
     */
    public boolean checkUser(Object id) {
        // 有关联的数据
        int abc = 0;
        int aaa = 1;
        if (abc > 0 && aaa > 0) {
            // 如果有关联的表中存在数据，不能删除
            return false;
        } else {
            // 如果没有数据，可以删除
            return true;
        }

    }

    /**
     * 判断角色是否可以被删除
     * @param id
     * @return
     */
    public boolean checkRole(Object id) {
        // 用户数据
        RoleUser roleUser = new RoleUser();
        roleUser.setRoleId(Integer.parseInt((String)id));
        int userCount = commonRoleUserDao.selectCount(roleUser);
        if (userCount > 0) {
            // 如果有关联的表中存在数据，不能删除
            return false;
        } else {
            // 如果没有数据，可以删除
            return true;
        }
    }

    /**
     * 判断权限是否可以被删除
     * @param id
     * @return
     */
    public boolean checkPerm(Object id) {
        // 权限数据
        RolePermission rolePermission = new RolePermission();
        rolePermission.setPermissionId((Integer) id);
        int permCount = commonRolePermissionDao.selectCount(rolePermission);
        if (permCount > 0) {
            // 如果有关联的表中存在数据，不能删除
            return false;
        } else {
            // 如果没有数据，可以删除
            return true;
        }
    }

}
