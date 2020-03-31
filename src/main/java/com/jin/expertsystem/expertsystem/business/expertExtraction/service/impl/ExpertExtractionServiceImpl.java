package com.jin.expertsystem.expertsystem.business.expertExtraction.service.impl;

import com.jin.expertsystem.expertsystem.business.common.model.ExpertInfo;
import com.jin.expertsystem.expertsystem.business.expertExtraction.dao.ExpertExtractionDao;
import com.jin.expertsystem.expertsystem.business.expertExtraction.model.ExpertExtractionParam;
import com.jin.expertsystem.expertsystem.business.expertExtraction.service.ExpertExtractionService;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author jinmingyong
 * @date 2020/3/30 13:15
 */
@Service
public class ExpertExtractionServiceImpl implements ExpertExtractionService {
    @Autowired
    private ExpertExtractionDao expertExtractionDao;

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
