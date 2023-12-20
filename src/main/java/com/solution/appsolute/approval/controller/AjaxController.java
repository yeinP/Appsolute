package com.solution.appsolute.approval.controller;

import com.solution.appsolute.approval.dao.ApprovalDao;
import com.solution.appsolute.approval.dto.AddPersonVO;
import com.solution.appsolute.approval.dto.Basic;
import com.solution.appsolute.approval.dto.PurchaseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/form")
public class AjaxController {

    @Autowired
    private ApprovalDao approvalDao;

    @RequestMapping(value = "/purchaseBasic",method = RequestMethod.POST)
    public long purchaseBasic(@RequestBody Basic basic){
        System.out.println("------------------"+basic);
        approvalDao.insertApp(basic);
        System.out.println("------------------22"+basic);
        return basic.getApprovalNum();
    }

    @RequestMapping(value = "/addPerson", method = RequestMethod.POST)
    public int addPerson(@RequestBody Map<String, List<Integer>> reqData){
        System.out.println("****************"+reqData.get("empNoData"));
        List<Integer> empNoData = reqData.get("empNoData");
        AddPersonVO addPersonVO = new AddPersonVO();
        for (int i = 1; i < empNoData.size(); i++) {
            System.out.println("itar > "+empNoData.get(i));
            addPersonVO.setEmpCheckNum(empNoData.get(i));
            addPersonVO.setSequence(i);
            addPersonVO.setApprovalNum(empNoData.get(0));
            System.out.println(addPersonVO+"--------"+i);
            approvalDao.addPerson(addPersonVO);
        }

        return 0;
    }

    @RequestMapping(value = "/addDetail", method = RequestMethod.POST)
    public int addDetail(@RequestBody List<PurchaseVO> detailPurchaseArray){
        int index = 0;
        System.out.println(detailPurchaseArray);
        for (PurchaseVO purchaseVO : detailPurchaseArray) {
            System.out.println(index);
            System.out.println(purchaseVO+"---indexExample------"+index);
            index++;
//            purchaseVO.setFieldKey(purchaseVO.getFieldKey()+index);
            approvalDao.addPurchaseDetail(purchaseVO);
        }
//        System.out.println("get(0) > "+detailPurchaseArray.get(0));
//        System.out.println("get(1) > "+detailPurchaseArray.get(1));
//        System.out.println("get(2) > "+detailPurchaseArray.get(2));
        return 0;
    }

    @RequestMapping(value = "/addPurchase", method = RequestMethod.POST)
    public int addPurchase(@RequestBody List<PurchaseVO> purchaseArray) {
        System.out.println(purchaseArray);
        approvalDao.addPurchaseDetail(purchaseArray.get(0));
        approvalDao.addPurchaseDetail(purchaseArray.get(1));
        return 0;
    }

    @RequestMapping(value = "/addGeneral", method = RequestMethod.POST)
    public int addGeneral(@RequestBody String content, @RequestBody int insertedKey) {
        System.out.println(content);
        System.out.println(insertedKey);
        return 0;
    }
}
