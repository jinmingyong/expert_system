package com.jin.expertsystem.expertsystem.business.expertExtraction.service.impl;

import com.jin.expertsystem.expertsystem.business.common.dao.CommonProjectInfoDao;
import com.jin.expertsystem.expertsystem.business.common.dao.CommonResultDetailedInfoDao;
import com.jin.expertsystem.expertsystem.business.common.dao.CommonResultInfoDao;
import com.jin.expertsystem.expertsystem.business.common.model.ExpertInfo;
import com.jin.expertsystem.expertsystem.business.common.model.ProjectInfo;
import com.jin.expertsystem.expertsystem.business.common.model.ResultDetailedInfo;
import com.jin.expertsystem.expertsystem.business.common.model.ResultInfo;
import com.jin.expertsystem.expertsystem.business.common.service.CommonProjectInfoService;
import com.jin.expertsystem.expertsystem.business.common.service.CommonResultDetailedInfoService;
import com.jin.expertsystem.expertsystem.business.common.service.CommonResultInfoService;
import com.jin.expertsystem.expertsystem.business.expertExtraction.dao.ExpertExtractionDao;
import com.jin.expertsystem.expertsystem.business.expertExtraction.model.ExpertExtractionParam;
import com.jin.expertsystem.expertsystem.business.expertExtraction.service.ExpertExtractionService;
import com.jin.expertsystem.expertsystem.business.sysmanage.model.SendSmsParam;
import com.jin.expertsystem.expertsystem.business.sysmanage.service.SendSmsService;
import com.jin.expertsystem.expertsystem.utils.DFormat;
import com.jin.expertsystem.expertsystem.utils.Utils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.util.*;

/**
 * @author jinmingyong
 * @date 2020/3/30 13:15
 */
@Service
public class ExpertExtractionServiceImpl implements ExpertExtractionService {
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
    public Integer sendSms(SendSmsParam sendSmsParam) {
        // 更新项目信息
        int i =commonProjectInfDao.updateByPrimaryKey(sendSmsParam.getProjectInfo());
        // 增加结果表
        ProjectInfo projectInfo = sendSmsParam.getProjectInfo();
        ResultInfo resultInfo=new ResultInfo();
        resultInfo.setResultId(Utils.getUUID());
        resultInfo.setProId(projectInfo.getProjectId());
        resultInfo.setType(sendSmsParam.getType());
        int j = commonResultInfoDao.insert(resultInfo);
        String resId = resultInfo.getResultId();
        List<ResultDetailedInfo> list = new ArrayList<>();
        List<String> emailList = new ArrayList<>();
        Map<String, Object> mapstring = new HashMap<String, Object>();
        mapstring.put("createTime",projectInfo.getCreateTime());
        mapstring.put("projectName",projectInfo.getProName());
        String msg = DFormat.stringFormatAll(sendSmsParam.getSendText(),mapstring);
        for (ExpertInfo e:sendSmsParam.getExpertInfo()) {
            ResultDetailedInfo r=new ResultDetailedInfo();
            r.setId(Utils.getUUID());
            r.setExpId(e.getExpertId());
            r.setResId(resId);
            for (String id : sendSmsParam.getSendId()
            ) {
                if (e.getExpertId().equals(id)){
                    r.setFlagEmail("3");
                    emailList.add(e.getEmail());
                }
            }
            list.add(r);
        }

        int k = commonResultDetailedInfoDao.batchSaveResultDetailed(list);
        try {
            sendSmsService.sendEmail(emailList,msg);
        } catch (MessagingException e) {
            e.printStackTrace();
            return 0;
        }
        //albudmqrijwlbebf
        if (i*j*k == 0) {
            return 0;
        }else {
            return 1;
        }
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
