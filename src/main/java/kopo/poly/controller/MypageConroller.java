package kopo.poly.controller;

import kopo.poly.dto.UserInfoDTO;
import kopo.poly.service.IMypageService;
import kopo.poly.service.IUserInfoService;
import kopo.poly.util.CmmUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

public class MypageConroller {

    @RequestMapping(value = "mypage/mypage")
    public String mypage(HttpSession session, ModelMap model){
        ArrayList<String> rList = (ArrayList)session.getAttribute("MypageAllerList");
        Collections.sort(rList);

        return "/mypage/mypage";
    }
}
