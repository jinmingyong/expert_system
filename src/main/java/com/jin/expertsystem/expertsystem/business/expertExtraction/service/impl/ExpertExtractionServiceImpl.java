package com.jin.expertsystem.expertsystem.business.expertExtraction.service.impl;

import com.jin.expertsystem.expertsystem.base.jwt.JwtTokenUtil;
import com.jin.expertsystem.expertsystem.business.common.dao.CommonMessageManageDao;
import com.jin.expertsystem.expertsystem.business.common.dao.CommonProjectInfoDao;
import com.jin.expertsystem.expertsystem.business.common.dao.CommonResultDetailedInfoDao;
import com.jin.expertsystem.expertsystem.business.common.dao.CommonResultInfoDao;
import com.jin.expertsystem.expertsystem.business.common.model.*;
import com.jin.expertsystem.expertsystem.business.common.service.CommonProjectInfoService;
import com.jin.expertsystem.expertsystem.business.common.service.CommonResultDetailedInfoService;
import com.jin.expertsystem.expertsystem.business.common.service.CommonResultInfoService;
import com.jin.expertsystem.expertsystem.business.expertExtraction.dao.ExpertExtractionDao;
import com.jin.expertsystem.expertsystem.business.expertExtraction.dao.ResultInfoDao;
import com.jin.expertsystem.expertsystem.business.expertExtraction.model.ExpertExtractionParam;
import com.jin.expertsystem.expertsystem.business.expertExtraction.model.ExtractionResultInfo;
import com.jin.expertsystem.expertsystem.business.expertExtraction.service.ExpertExtractionService;
import com.jin.expertsystem.expertsystem.business.sysmanage.model.SendSmsParam;
import com.jin.expertsystem.expertsystem.business.sysmanage.service.SendSmsService;
import com.jin.expertsystem.expertsystem.utils.DFormat;
import com.jin.expertsystem.expertsystem.utils.Utils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author jinmingyong
 * @date 2020/3/30 13:15
 */
@Service
public class ExpertExtractionServiceImpl implements ExpertExtractionService {

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @Value("${jwt.requestHeader}")
    private String requestHeader;


    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private ExpertExtractionDao expertExtractionDao;

    @Autowired
    private CommonProjectInfoDao commonProjectInfDao;

    @Autowired
    private CommonResultInfoDao commonResultInfoDao;

    @Autowired
    private CommonResultDetailedInfoDao commonResultDetailedInfoDao;

    @Autowired
    private SendSmsService sendSmsService;

    @Autowired
    private ResultInfoDao resultInfoDao;

    @Autowired
    private CommonMessageManageDao commonMessageManageDao;

    @Override
    public List<ExpertInfo> expertExtraction(ExpertExtractionParam expertExtractionParam) {
        return expertExtractionDao.expertExtraction(
                expertExtractionParam.getCityList(),
                expertExtractionParam.getJobGradeList(),
                expertExtractionParam.getIndustryList(),
                expertExtractionParam.getCompanyList(),
                expertExtractionParam.getMajorList(),
                expertExtractionParam.getName()
                );
    }

    @Override
    public List<ExpertInfo> expertExtractionByRandom(ExpertExtractionParam expertExtractionParam) {
        List<ExpertInfo> list = expertExtractionDao.expertExtraction(expertExtractionParam.getCityList(),
                expertExtractionParam.getJobGradeList(),
                expertExtractionParam.getIndustryList(),
                expertExtractionParam.getCompanyList(),
                expertExtractionParam.getMajorList(),
                null);
        Set<Integer> key = sampletest(list.size(),expertExtractionParam.getNum()); //[n,m)
        List<ExpertInfo> newList = new ArrayList<>();
        for (Integer k:key
             ) {
            newList.add(list.get(k));
        }
        return newList;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String sendSms(SendSmsParam sendSmsParam, HttpServletRequest request) throws MessagingException {
        String token = request.getHeader(requestHeader);
        String realToken = token.substring(tokenHeader.length());
        String userId = jwtTokenUtil.getUserIdFromToken(realToken);
        // 更新项目信息
        commonProjectInfDao.updateByPrimaryKey(sendSmsParam.getProjectInfo());
        // 增加结果表
        ProjectInfo projectInfo = sendSmsParam.getProjectInfo();
        ResultInfo resultInfo=new ResultInfo();
        resultInfo.setResultId(Utils.getUUID());
        resultInfo.setProId(projectInfo.getProjectId());
        resultInfo.setType(sendSmsParam.getType());
        commonResultInfoDao.insertSelective(resultInfo);
        String resId = resultInfo.getResultId();
        List<ResultDetailedInfo> list = new ArrayList<>();
        List<MessageManage> messageManageList = new ArrayList<>();
        Map<String, Object> mapstring = new HashMap<String, Object>();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.setLenient(false);
        String time = format.format(projectInfo.getCreateTime());
        mapstring.put("createTime",time);
        mapstring.put("projectName",projectInfo.getProName());
        for (ExpertInfo e:sendSmsParam.getExpertInfo()) {
            ResultDetailedInfo r=new ResultDetailedInfo();
            r.setId(Utils.getUUID());
            r.setResId(resId);
            r.setFlagEmail("3");
            r.setExpId(e.getExpertId());
            for (String id : sendSmsParam.getSendId()
            ) {
                if (e.getExpertId().equals(id)){
                    MessageManage m = new MessageManage();
                    mapstring.put("expertName",e.getName());
                    String msg = DFormat.stringFormatAll(sendSmsParam.getSendText(),mapstring);
                    String mes_msg = msg + "参加请回复！";
                    m.setExpId(id);
                    m.setMesContent(mes_msg);
                    m.setMesTitle("系统消息");
                    m.setMesId(Utils.getUUID());
                    m.setResId(resId);
                    m.setSenderId(userId);
                    m.setStatus(0);
                    messageManageList.add(m);
                    Boolean sendStatus = sendSmsService.sendEmail(e.getEmail(),msg);
                    if (sendStatus == true) r.setFlagEmail("1");
                    else r.setFlagEmail("0");
                }
            }
            list.add(r);
        }
        commonResultDetailedInfoDao.batchSaveResultDetailed(list);
        commonMessageManageDao.batchSaveMessageManage(messageManageList);
        return resId;
    }

    @Override
    public List<ExtractionResultInfo> selectAllResult(String proName) {
        return resultInfoDao.selectAllResult(proName);
    }

    @Override
    public ExtractionResultInfo selectResultById(String resultId) {
        return resultInfoDao.selectResultById(resultId);
    }


    public static Set<Integer> sampletest(int n, int m) {
        if (n<m) m=n;
        Set<Integer> set = new HashSet<>();
        int first = RandomUtils.nextInt(0, n);
        set.add(first);
        while(set.size() < m) {
            int tmp = RandomUtils.nextInt(0, n);
            while(set.contains(tmp)) {
                tmp = RandomUtils.nextInt(0, n);
            }
            set.add(tmp);
        }
        return set;
    }
}
