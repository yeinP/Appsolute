package com.solution.appsolute.approval.controller;

import com.solution.appsolute.approval.dao.ApprovalDao;
import com.solution.appsolute.approval.dto.Approval;
import com.solution.appsolute.approval.dto.ApprovalDetail;
import com.solution.appsolute.approval.dto.PurchaseVO;
import com.solution.appsolute.login.dto.AuthInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/approval")
public class ApprovalController {

    @Autowired
    private ApprovalDao approvalDao;

    @GetMapping("/purchase")
    public void purchase1(Model model, HttpSession session){

        AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
        model.addAttribute("authInfo", authInfo);
        model.addAttribute("userName", authInfo.getEmp_name());

        model.addAttribute("deptList", approvalDao.deptList());
        model.addAttribute("empList", approvalDao.empList());
        model.addAttribute("purchaseFieldNames", approvalDao.purchaseFieldNames());
//        System.out.println(approvalDao.deptList());
//        System.out.println(approvalDao.empList());
    }


    @GetMapping("/purchaseSuccess")
    public void purchasePost1(Model model, HttpSession session) {

        AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
        model.addAttribute("userName", authInfo.getEmp_name());

        model.addAttribute("approvalOne", approvalDao.approvalOne(69));
//        System.out.println(approvalDao.approvalOne(67));
        model.addAttribute("approvalDetailOne", approvalDao.approvalDetailOne(69));
        System.out.println("test > "+approvalDao.approvalDetailOne(69));
        model.addAttribute("approvalContentOne", approvalDao.approvalContentOne(69));
        System.out.println("test 2 > "+approvalDao.approvalContentOne(69));
//        System.out.println("--------------"+expenseReport);
        System.out.println(approvalDao.lastKey());
        model.addAttribute("lastKey", approvalDao.lastKey());
    }

    @PostMapping("/purchaseSuccess")
    public String purchasePost(Model model, HttpSession session) {
        AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
        model.addAttribute("userName", authInfo.getEmp_name());
        int lastKey = approvalDao.lastKey();
        model.addAttribute("approvalOne", approvalDao.approvalOne(lastKey));
        model.addAttribute("approvalDetailOne", approvalDao.approvalDetailOne(lastKey));
        model.addAttribute("approvalContentOne", approvalDao.approvalContentOne(lastKey));
        return "redirect:/approval/myApproval";
    }

    @GetMapping("/myApproval")
    public void myApproval(Model model, HttpSession session){
        AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
        model.addAttribute("userName", authInfo.getEmp_name());
        if (authInfo != null) {
            System.out.println(authInfo);
            System.out.println(authInfo.getEmp_num());

            model.addAttribute("myApproval",approvalDao.findMyApproval(authInfo.getEmp_num()));

        }

    }

    @GetMapping("/{approvalNum}")
    public String approval(Model model, @PathVariable long approvalNum, HttpSession session) {
        AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
        model.addAttribute("userName", authInfo.getEmp_name());
        model.addAttribute("sessionEmpNum", authInfo.getEmp_num());
        model.addAttribute("approval", approvalDao.approvalOne((int) approvalNum));
        model.addAttribute("purchaseEmp", approvalDao.purchaseEmp((int) approvalNum));
        model.addAttribute("approvalDetail", approvalDao.approvalDetailOne((int) approvalNum));
        model.addAttribute("approvalContent", approvalDao.approvalContentOne((int) approvalNum));

        List<ApprovalDetail> approvalDetails = approvalDao.approvalStatus((int) approvalNum, 0);
        ApprovalDetail apd = null;
        int index = 0;
        for (ApprovalDetail approvalDetail : approvalDetails) {
            if (approvalDetail.getApprovalStatus() == 0) {
                apd = approvalDetail;
                index++;
                break;
            }
        }

        ApprovalDetail aa = new ApprovalDetail();
        aa.setEmpCheckNum(0);
//        System.out.println(approvalDao.approvalStatus((int) approvalNum, 0));
        if (apd != null) {
            model.addAttribute("approvalStatus",apd);
        }else {
            model.addAttribute("approvalStatus",aa);
        }
        return "approval/approvalPage";
    }

//    @PostMapping("/{approvalNum}")
//    public String approvalPost(Model model, @PathVariable long approvalNum, HttpSession session, @RequestParam int checkNum){
//        System.out.println("checkNum----------------->"+checkNum);
//        System.out.println("되나?");
//        AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
//        model.addAttribute("sessionEmpNum", authInfo.getEmp_num());
//        model.addAttribute("approval", approvalDao.approvalOne((int) approvalNum));
//        model.addAttribute("approvalDetail", approvalDao.approvalDetailOne((int) approvalNum));
//        model.addAttribute("approvalContent", approvalDao.approvalContentOne((int) approvalNum));
//
//        approvalDao.selectApprovalDetailNum(approvalNum, authInfo.getEmp_num());
//        System.out.println(approvalDao.selectApprovalDetailNum(approvalNum, authInfo.getEmp_num()));
//        approvalDao.empCheckUpdate(checkNum, approvalDao.selectApprovalDetailNum(approvalNum, authInfo.getEmp_num()));
//        List<ApprovalDetail> approvalDetails = approvalDao.approvalStatus((int) approvalNum, 0);
//        ApprovalDetail apd = null;
//        int index = 0;
//        for (ApprovalDetail approvalDetail : approvalDetails) {
//            if (approvalDetail.getApprovalStatus() == 0) {
//                apd = approvalDetail;
//                index++;
//                break;
//            }
//        }
//
//        ApprovalDetail aa = new ApprovalDetail();
//        aa.setEmpCheckNum(0);
////        System.out.println(approvalDao.approvalStatus((int) approvalNum, 0));
//        if (apd != null) {
//            model.addAttribute("approvalStatus",apd);
//        }else {
//            model.addAttribute("approvalStatus",aa);
//        }
//        model.addAttribute("checkParam",1);
////        System.out.println(approvalDao.approvalDetailOne((int) approvalNum).get(0));
//        return "approval/approvalPage";
//    }


    @GetMapping("/receivedApproval")
    public void receivedApproval(Model model, HttpSession session){
        AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
//        System.out.println(approvalDao.includedMe(authInfo.getEmp_num()));
        List<ApprovalDetail> approvalsIncludedMe = approvalDao.includedMe(authInfo.getEmp_num());
//        System.out.println(approvalsIncludedMe);

        List<ApprovalDetail> pendingApproval = new ArrayList<>();
        List<ApprovalDetail> checkedApproval = new ArrayList<>();
        List<ApprovalDetail> rejectedApproval = new ArrayList<>();
//        List<Approval> title = new ArrayList<>();
        List<Integer> appNum = new ArrayList<>();

        for (ApprovalDetail approvalDetail : approvalsIncludedMe) {
            appNum.add((int)approvalDetail.getApprovalNum());
            if (approvalDetail.getApprovalStatus() == 0) {
                ApprovalDetail prevDetail = approvalDao.prevDetail((int)approvalDetail.getApprovalDetailNum()-1);
//                System.out.println(approvalDao.prevDetail((int)approvalDetail.getApprovalDetailNum()-1));
                if (prevDetail != null) {
                    System.out.println("여기까지 되나?");
                    if (prevDetail.getApprovalNum() == approvalDetail.getApprovalNum()) {
                        if (prevDetail.getApprovalStatus() == 1) {
                            pendingApproval.add(approvalDetail);
                        }
                    }else {
                        pendingApproval.add(approvalDetail);
                    }
                }


                //여기 필터링 확인
//                List<ApprovalDetail> approvalDetails = approvalDao.approvalStatus((int)approvalDetail.getApprovalNum(), 0);
//                ApprovalDetail apd = null;
//                int index = 0;
//                for (ApprovalDetail detail : approvalDetails) {
//                    if (detail.getApprovalStatus() == 0) {
//                        apd = detail;
//                        index++;
//                        break;
//                    }
//                }
//
//                ApprovalDetail aa = new ApprovalDetail();
//                aa.setEmpCheckNum(0);
//        System.out.println(approvalDao.approvalStatus((int) approvalNum, 0));
//                if (apd != null) {
//                    model.addAttribute("pendingApproval",apd);
//                }else {
//                    model.addAttribute("pendingApproval",aa);
//                }



//                title.add(approvalDao.approvalOne((int)approvalDetail.getApprovalNum()));
//                pendingApproval.add((int)approvalDetail.getApprovalNum());
            } else if (approvalDetail.getApprovalStatus() == 1) {
                checkedApproval.add(approvalDetail);
//                checkedApproval.add((int) approvalDetail.getApprovalNum());
            } else if (approvalDetail.getApprovalStatus() == 2) {
                rejectedApproval.add(approvalDetail);
//                rejectedApproval.add((int) approvalDetail.getApprovalNum());
            }
        }
        List<Approval> titleApproval = new ArrayList<>();
        for (Integer i : appNum) {
            titleApproval.add(approvalDao.approvalOne(i));
        }
//        System.out.println(appNum);
        System.out.println(pendingApproval);
        if (pendingApproval.isEmpty()) {
            List<ApprovalDetail> list = new ArrayList<>();
            list.add(new ApprovalDetail(0,0,0,null,0,0,0,null));
            pendingApproval = list;
        }
        if (checkedApproval.isEmpty()) {
            List<ApprovalDetail> list = new ArrayList<>();
            list.add(new ApprovalDetail(0,0,0,null,0,0,0,null));
            checkedApproval = list;
        }
        if (rejectedApproval.isEmpty()) {
            List<ApprovalDetail> list = new ArrayList<>();
            list.add(new ApprovalDetail(0,0,0,null,0,0,0,null));
            rejectedApproval = list;
        }
        if (titleApproval.isEmpty()) {
            List<Approval> list = new ArrayList<>();
            list.add(new Approval(0L,0,new Date(),0,0,"",0,0,0,null,null));
            titleApproval = list;
        }
        model.addAttribute("userName",authInfo.getEmp_name());
        model.addAttribute("pendingApproval", pendingApproval);
        model.addAttribute("checkedApproval", checkedApproval);
        model.addAttribute("rejectedApproval", rejectedApproval);
        model.addAttribute("titleApproval", titleApproval);


//        List<ApprovalDetail> pendingApprovals = null;
//        List<Approval> approvedByMe = null;
//        List<Approval> rejectedByMe = null;
//
//        for (Integer i : approvalsIncludedMe) {
//            pendingApprovals.add(approvalDao.approvalStatus(i, 0));
//            approvalDao.approvalStatus(i, 0);
//        }
//        model.addAttribute("pendingApproval", approvalDao.approvalStatus());
    }

    @PostMapping("/receivedApproval")
    public String receivedApprovalPost(Model model, HttpSession session,@RequestParam int approvalNum, @RequestParam int checkNum){
        AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
        System.out.println(approvalDao.includedMe(authInfo.getEmp_num()));
        List<ApprovalDetail> approvalsIncludedMe = approvalDao.includedMe(authInfo.getEmp_num());

        approvalDao.selectApprovalDetailNum(approvalNum, authInfo.getEmp_num());
        System.out.println(approvalDao.selectApprovalDetailNum(approvalNum, authInfo.getEmp_num()));
        approvalDao.empCheckUpdate(checkNum, approvalDao.selectApprovalDetailNum(approvalNum, authInfo.getEmp_num()));

        List<ApprovalDetail> approvalDetails = approvalDao.approvalDetailOne(approvalNum);
//        boolean isValueIncluded = false;
        List<Integer> array = new ArrayList<>();
        for (ApprovalDetail approvalDetail : approvalDetails) {
            array.add(approvalDetail.getApprovalStatus());
        }

        if (array.contains(2)) {
            approvalDao.approvalCheckUpdate(2, approvalNum);
        } else if (array.contains(0)) {

        } else {
            approvalDao.approvalCheckUpdate(1, approvalNum);
        }

        List<ApprovalDetail> pendingApproval = new ArrayList<>();
        List<ApprovalDetail> checkedApproval = new ArrayList<>();
        List<ApprovalDetail> rejectedApproval = new ArrayList<>();
//        List<Approval> title = new ArrayList<>();
        List<Integer> appNum = new ArrayList<>();

        for (ApprovalDetail approvalDetail : approvalsIncludedMe) {
            appNum.add((int)approvalDetail.getApprovalNum());
            if (approvalDetail.getApprovalStatus() == 0) {
                pendingApproval.add(approvalDetail);

                List<ApprovalDetail> approvalDetailss = approvalDao.approvalStatus((int)approvalDetail.getApprovalNum(), 0);
                ApprovalDetail apd = null;
                int index = 0;
                for (ApprovalDetail detail : approvalDetailss) {
                    if (detail.getApprovalStatus() == 0) {
                        apd = detail;
                        index++;
                        break;
                    }
                }

                ApprovalDetail aa = new ApprovalDetail();
                aa.setEmpCheckNum(0);
//        System.out.println(approvalDao.approvalStatus((int) approvalNum, 0));
                if (apd != null) {
                    model.addAttribute("pendingApproval",apd);
                }else {
                    model.addAttribute("pendingApproval",aa);
                }



//                title.add(approvalDao.approvalOne((int)approvalDetail.getApprovalNum()));
//                pendingApproval.add((int)approvalDetail.getApprovalNum());
            } else if (approvalDetail.getApprovalStatus() == 1) {
                checkedApproval.add(approvalDetail);
//                checkedApproval.add((int) approvalDetail.getApprovalNum());
            } else if (approvalDetail.getApprovalStatus() == 2) {
                rejectedApproval.add(approvalDetail);
//                rejectedApproval.add((int) approvalDetail.getApprovalNum());
            }
        }
        List<Approval> titleApproval = new ArrayList<>();
        for (Integer i : appNum) {
            titleApproval.add(approvalDao.approvalOne(i));
        }
        System.out.println(appNum);
        model.addAttribute("pendingApproval", pendingApproval);
        model.addAttribute("checkedApproval", checkedApproval);
        model.addAttribute("rejectedApproval", rejectedApproval);
        model.addAttribute("titleApproval", titleApproval);

//        for (Integer i : array) {
//            if (i == 2) {
//                //update 2
//                approvalDao.approvalCheckUpdate(2, approvalNum);
//                break;
//            }else if(i == 0) {
//                //nothing
//                break;
//            }else {
//                //update 1
//                approvalDao.approvalCheckUpdate(1, approvalNum);
//            }
//        }

//        List<ApprovalDetail> pendingApprovals = null;
//        List<Approval> approvedByMe = null;
//        List<Approval> rejectedByMe = null;
//
//        for (Integer i : approvalsIncludedMe) {
//            pendingApprovals.add(approvalDao.approvalStatus(i, 0));
//            approvalDao.approvalStatus(i, 0);
//        }
//        model.addAttribute("pendingApproval", approvalDao.approvalStatus());

        return "redirect:/approval/receivedApproval";
    }


    @GetMapping("/general")
    public void general(Model model, HttpSession session){
        AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
        model.addAttribute("authInfo", authInfo);
        model.addAttribute("userName", authInfo.getEmp_name());
        model.addAttribute("deptList", approvalDao.deptList());
        model.addAttribute("empList", approvalDao.empList());
        model.addAttribute("lastKey", approvalDao.lastKey());
    }

    @PostMapping("/generalSuccess")
    public String generalSuccess(Model model, String content, String insertedKey, HttpSession session) {

        AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
        model.addAttribute("userName", authInfo.getEmp_name());
        System.out.println(content);
        System.out.println(insertedKey);
        PurchaseVO purchaseVO = new PurchaseVO(Long.parseLong(insertedKey)+1 , "2", content, "-", "-", "0", "0");
        approvalDao.addPurchaseDetail(purchaseVO);

        System.out.println(approvalDao.lastKey());
        int lastKey = approvalDao.lastKey();
        model.addAttribute("approvalOne", approvalDao.approvalOne(lastKey));
//        System.out.println(approvalDao.approvalOne(67));
        model.addAttribute("approvalDetailOne", approvalDao.approvalDetailOne(lastKey));
        System.out.println("test > "+approvalDao.approvalDetailOne(lastKey));
        model.addAttribute("approvalContentOne", approvalDao.approvalContentOne(lastKey));
        System.out.println("test 2 > "+approvalDao.approvalContentOne(lastKey));
//        System.out.println("--------------"+expenseReport);
        return "redirect:/approval/myApproval";
    }
}
