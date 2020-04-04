package com.jin.expertsystem.expertsystem.business.personalManage.service.impl;

import com.jin.expertsystem.expertsystem.base.jwt.JwtTokenUtil;
import com.jin.expertsystem.expertsystem.business.common.dao.CommonMessageManageDao;
import com.jin.expertsystem.expertsystem.business.common.dao.CommonResultDetailedInfoDao;
import com.jin.expertsystem.expertsystem.business.common.dao.CommonUsersDao;
import com.jin.expertsystem.expertsystem.business.common.model.MessageManage;
import com.jin.expertsystem.expertsystem.business.common.model.ResultDetailedInfo;
import com.jin.expertsystem.expertsystem.business.common.model.RolePermission;
import com.jin.expertsystem.expertsystem.business.common.model.Users;
import com.jin.expertsystem.expertsystem.business.personalManage.dao.PersonalDao;
import com.jin.expertsystem.expertsystem.business.personalManage.model.ReplyMessageParam;
import com.jin.expertsystem.expertsystem.business.personalManage.model.UpdatePasswordParams;
import com.jin.expertsystem.expertsystem.business.personalManage.service.PersonalService;
import com.jin.expertsystem.expertsystem.business.sysmanage.model.UserRoleInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author jinmingyong
 * @date 2020/3/23 18:27
 */
@Service
public class PersonalServiceImpl implements PersonalService {
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @Value("${jwt.requestHeader}")
    private String requestHeader;


    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private PersonalDao personalDao;

    @Autowired
    private CommonUsersDao commonUsersDao;

    @Autowired
    private CommonMessageManageDao commonMessageManageDao;

    @Autowired
    private CommonResultDetailedInfoDao commonResultDetailedInfoDao;

    @Override
    public UserRoleInfo selectUserInfoByToken(HttpServletRequest request) {
        String token = request.getHeader(requestHeader);
        String realToken = token.substring(tokenHeader.length());
        String userId = jwtTokenUtil.getUserIdFromToken(realToken);
        return personalDao.selectUserInfoByToken(userId);
    }

    @Override
    public int updatePasswordByToken(HttpServletRequest request, UpdatePasswordParams updatePasswordParams) {
        String token = request.getHeader(requestHeader);
        String realToken = token.substring(tokenHeader.length());
        String userId = jwtTokenUtil.getUserIdFromToken(realToken);
        Users users=commonUsersDao.selectByPrimaryKey(userId);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(updatePasswordParams.getOldPassword(),users.getPassword())){
            return 0;
        }else {
            if (encoder.matches(updatePasswordParams.getNewPassword(),users.getPassword())){
                return -1;
            }
            users.setPassword(encoder.encode(updatePasswordParams.getNewPassword()));
            return commonUsersDao.updateByPrimaryKeySelective(users);
        }
    }

    @Override
    public List<MessageManage> selectMessageByToken(HttpServletRequest request) {
        UserRoleInfo userRoleInfo = this.selectUserInfoByToken(request);
        if (userRoleInfo.getRoleId().equals(2)){
            Example example = new Example(MessageManage.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("expId", userRoleInfo.getId());
            criteria.andEqualTo("status",0);
            return commonMessageManageDao.selectByExample(example);
        }
        return null;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int replayMessage(ReplyMessageParam replyMessageParam) {
        MessageManage messageManage = new MessageManage();
        messageManage.setMesId(replyMessageParam.getMesId());
        messageManage.setStatus(1);
        int i = commonMessageManageDao.updateByPrimaryKeySelective(messageManage);
        Example example = new Example(ResultDetailedInfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("resId", replyMessageParam.getResId());
        criteria.andEqualTo("expId", replyMessageParam.getExpId());
        ResultDetailedInfo resultDetailedInfo = new ResultDetailedInfo();
        resultDetailedInfo.setFlagSts(replyMessageParam.getFlagSts());
        int j = commonResultDetailedInfoDao.updateByExampleSelective(resultDetailedInfo,example);
        if (i*j == 0) return 0;
        return 1;
    }

    @Override
    public Integer selectMessageCount(HttpServletRequest request) {
        String token = request.getHeader(requestHeader);
        String realToken = token.substring(tokenHeader.length());
        String userId = jwtTokenUtil.getUserIdFromToken(realToken);
        return personalDao.countNewMessageByToken(userId);
    }
}
