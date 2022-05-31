package kopo.poly.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kopo.poly.dto.UserInfoDTO;
import kopo.poly.service.IUserInfoService;
import kopo.poly.util.CmmUtil;
import kopo.poly.util.EncryptUtil;

@Slf4j
@Controller
public class UserInfoContrller {

     /*@GetMapping(value = "/index")
    public String index() throws Exception{
        log.info(this.getClass().getName() + ".index 시작");

        return "/index";
    }*/

    @Resource(name = "UserInfoService")
    private IUserInfoService userInfoService;

    @RequestMapping(value="user/userRegForm")
    public  String userRegForm(){
        log.info(this.getClass().getName()+ ".user/userRegForm ok");

        return  "user/UserRegForm";
    }

    @RequestMapping(value = "user/insertUserInfo")
    public String insertUserInfo(HttpServletRequest request, HttpServletResponse response,
                                 ModelMap model)throws Exception{
        log.info(this.getClass().getName() + "insertUserInfo 시작");

        String msg ="";

        UserInfoDTO pDTO = null;


        try{
            //오류났던 이유 HttpServletRequest(여기 둘다 같아서 HttpServletRequest, HttpServletResponse 오른쪽에 있던걸로 써서 getParameter 빨간줄 뜸) request, HttpServletResponse response,
            String user_id = CmmUtil.nvl(request.getParameter("user_id"));
            String user_name = CmmUtil.nvl(request.getParameter("user_name"));
            String user_pw = CmmUtil.nvl(request.getParameter("user_pw"));
            String user_age = CmmUtil.nvl(request.getParameter("user_age"));

            log.info("user_id "+user_id);
            log.info("user_name "+user_name);
            log.info("user_pw "+user_pw);
            log.info("user_age "+user_age);

            pDTO = new UserInfoDTO();

            pDTO.setUser_id(user_id);
            pDTO.setUser_name(user_name);

            //비밀번호 암호화
            pDTO.setUser_pw(EncryptUtil.encHashSHA256(user_pw));

            //pDTO.setUser_email(EncryptUtil.encAES128CBC(user_email));

            int res = userInfoService.inserUserInfo(pDTO);

            if(res ==1){
                msg ="회원가입 완료";
            }else if(res==2){
                msg="이미 가입된 이메일 주소입니다 ";
            }else{
                msg="오류로 인해 회원가입 실패";
            }

        }catch (Exception e){
            //저장 실패시 보여줄 메세지
            msg = "실패하였습니다 : "+e.toString();
            log.info(e.toString());
            e.printStackTrace();
        }finally {

            log.info(this.getClass().getName() + "insertUserInfo 끝");

            //회원가입 여부 결과 메세지 전달
            model.addAttribute("msg", msg);
            //회원가입 여부 결과 메세지 전달
            model.addAttribute("pDTO", pDTO);

            //변수 초기화
            pDTO = null;

        }

        return "/user/Msg";

    }

    /**
     * 로그인을 위해 입력 화면으로 이동
     */
    @RequestMapping(value="user/login")
    public String loginForm(){
        log.info(this.getClass().getName() + ".user/login ok!");

        return "/user/login";
    }


    /**
     * 로그인 처리 및 결과 알려주는 화면으로 이동
     */
    @RequestMapping(value = "user/getUserLoginCheck")
    public String getUserLoginCheck(HttpSession session, HttpServletRequest request, HttpServletResponse response,
                                    ModelMap model) throws Exception{
        log.info(this.getClass().getName() + ".getUserLoginCheck 시작");

        //로그인 처리 결과를 저장할 변수
        int res =0;

        //웹에서 받는 정보를 저장할 변수
        UserInfoDTO pDTO = null;

        try{
            String user_id = CmmUtil.nvl(request.getParameter("User_id"));
            String user_pw = CmmUtil.nvl(request.getParameter("user_pw"));

            log.info("user_id : "+user_id);
            log.info("user_pw : "+user_pw);

            //웹에서 받는 정보를 저장할 변수를 메모리에 올리기
            pDTO = new UserInfoDTO();

            pDTO.setUser_id(user_id);

            //비밀번호는 절대로 복호화되지 않도록 해시 알고리즘으로 암호화함
            pDTO.setUser_pw(EncryptUtil.encHashSHA256(user_pw));

            //로그인을 위해 아이디와 비밀번호가 일치하는지 확인하기 위한 userInfoService 호출
            res = userInfoService.getUserLoginCheck(pDTO);

            //로그인 성공
            if(res==1){
                session.setAttribute("SS_USER_ID : ",user_id);
            }
        }catch(Exception e){
            //저장이 실패되면 사용자에게 보여줄 메시지
            res =2 ;
            log.info(e.toString());
            e.printStackTrace();
        }finally {
            log.info(this.getClass().getName() + ".insertUserInfo end");
            model.addAttribute("res : ", String.valueOf(res));

            //변수 초기화
            pDTO = null;
        }
        return  "/user/loginResult";
    }
}