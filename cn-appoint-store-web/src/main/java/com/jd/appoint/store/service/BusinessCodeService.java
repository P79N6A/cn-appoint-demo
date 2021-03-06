package com.jd.appoint.store.service;

import com.jd.appoint.api.BusinessAndVenderMapFacade;
import com.jd.appoint.api.vo.BusinessVenderMapVO;
import com.jd.appoint.store.util.Constants;
import com.jd.jim.cli.Cluster;
import com.jd.travel.base.soa.SoaRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

@Service
public class BusinessCodeService {



    @Resource
    private Cluster jimClient;

    @Resource
    private BusinessAndVenderMapFacade businessAndVenderMapFacade;

    public String getBusinessCodeByVenderId(Long venderId) {
        String businessCode = "";
        String key = "business_code_"+venderId+"_V1";
        businessCode = jimClient.get(key);
        if(!StringUtils.isEmpty(businessCode)){
            return businessCode;
        }

        BusinessVenderMapVO result = businessAndVenderMapFacade.queryByVenderId(new SoaRequest<>(venderId)).getResult();
        if(result != null){
            businessCode = result.getBusinessCode();
            jimClient.set(key,businessCode);
            return businessCode;
        }

        //出错了返回服装code
        return Constants.FZ_BIZ_CODE;

    }

}
