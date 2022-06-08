package kopo.poly.controller;

import kopo.poly.dto.UserInfoDTO;
import kopo.poly.service.IMypageService;
import kopo.poly.service.impl.MypageService;
import kopo.poly.util.CmmUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import org.springframework.stereotype.Controller;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class MypageConroller {

    @Resource(name = "MypageService")
    private IMypageService mypageService;

    //마이페이지 보기
    @RequestMapping(value = "mypage/mypage")
    public String mypage(HttpSession session, ModelMap model){
        ArrayList<String> rList = (ArrayList)session.getAttribute("MypageAllerList");
        Collections.sort(rList);

        HashMap<String, String> hm = new HashMap<>();

        //수정봐야함

        return "/mypage/mypage";
    }

    //로그아웃
    @RequestMapping(value = "mypage/logout")
    public String logout(HttpSession session){
        session.invalidate();

        String res = "/mypage/logOutAlert";
        return res;
    }

    //마이페이지 수정
    @RequestMapping(value = "/user/userUpdata")
    public String userUpdata(HttpServletRequest request, ModelMap model, HttpSession session) throws Exception{
        int res =0;
        String msg="";
        UserInfoDTO pDTO = null;

        try{
            StringBuilder MySB=new StringBuilder();
            for(int i=0; i<2; i++){
                String str = CmmUtil.nvl(request.getParameter(("mypage")+(i+1)));
                if(!str.equals("") && !str.equals("")){
                    log.info("str : "+str);
                    MySB.append(str);
                    if(i!=1)
                    MySB.append(", ");
                }
            }
            String My = MySB.toString();
            log.info("MY : "+My);

            pDTO = new UserInfoDTO();
            pDTO.setUser_gender(My);
            pDTO.setUser_id(CmmUtil.nvl((String)session.getAttribute("id")));

            res = mypageService.MyupdataAller(pDTO);

            if(res==1){
                msg = "마이페이지 정보 수정 완료";
                model.addAttribute("My : ", My);
            }else{
                msg="수정 실패";
            }
        }catch (Exception e){
            //저장 실패시
            msg="실패하였습니다 : " + e.toString();
            res=0;
            log.info(e.toString());
            e.printStackTrace();
        }finally {
            log.info(this.getClass().getName() + "MypageConroller 끝");
            model.addAttribute("msg", msg);
            model.addAttribute("res", Integer.toString(res));
            pDTO=null;
        }
        return "/alert/userUpMsg";
    }
}
