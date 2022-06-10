package kopo.poly.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import kopo.poly.dto.MailDTO;
import org.apache.commons.lang3.RandomStringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import kopo.poly.dto.UserInfoDTO;
import kopo.poly.service.IPWCService;
import kopo.poly.service.IEMailService;
import kopo.poly.util.CmmUtil;
import kopo.poly.util.EncryptUtil;

@Slf4j
@Controller("PWController")
public class PWController {

    @Resource(name = "PWCService")
    private IPWCService PWCService;

    @Resource(name = "EMailService")
    private IEMailService emailService;

    //비밀번호 찾기 페이지
    @RequestMapping(value = "user/forgot")
    public String forgot(){
        return "/user/forgot";
    }

    //
    @RequestMapping(value = "user/Search")
    public String search(HttpServletRequest request, ModelMap model)throws Exception{

        String id = request.getParameter("toMail");
        log.info("웹 사이트에서 받아온 아이디값 : "+ id);

        UserInfoDTO oDTO = new UserInfoDTO();
        oDTO.getUser_id(id);
        log.info("oDTO에 입력된 id : " + oDTO.getUser_id());

        int res = PWCService.Search(oDTO);

        String result = "";

        if(res ==2){
            result = "/mail/sendMailResult";
        }else if( res ==1 ){
            result="/user/Return";
        }else{
            result = "ERRPR : 3064";
        }

        String toMail = CmmUtil.nvl(request.getParameter("toMail"));
        log.info("SMTP로 보낼 메일 주소" + toMail);
        String contents = RandomStringUtils.randomAlphanumeric(10);

        MailDTO pDTO = new MailDTO();

        pDTO.setToMail(toMail);
        pDTO.setTitle("임시 비밀번호 입니다. ");
        pDTO.setContents(contents+"\n ㅐ로그인 후 마이페이지에서 비밀번호를 변경해주세요");

        log.info("SMTP로 보낼 임시 비밀번호 설정 로직 실행");
        log.info("랜덤으로 생성된 임시 비밀번호 : " + contents);

        UserInfoDTO wDTO = new UserInfoDTO();
        wDTO.setUser_pw(EncryptUtil.encHashSHA256(contents));
        wDTO.getUser_id(id);
        log.info("wDTO에 들어간 임시 비밀번호 : " + wDTO.getUser_pw());

        int res1 = PWCService.New(wDTO);

        if(res1 == 0){
            log.info("매퍼에서 sql문 에러 -> 비밀번호 변경 실패");
        }else{
            log.info("매버페어 sql문 성공 -> 비밀번호 변경 성공");
        }

        int res2 = emailService.doSendEMail(pDTO);

        if(res2 == 1){
            log.info("메일발송 성공");
        }else{
            log.info("메일발송 실패");
        }

        model.addAttribute("res", String.valueOf(res2));

        return  result;

    }

    //비밀번호 변경 페이지
    @RequestMapping(value = "user/PWchange")
    public String change(){
        log.info("비밀번호 찾기 페이지");
        return "user/PWchange";
    }

    //비밀번호 변경 코드
    @RequestMapping(value = "user/PwchangeL")
    public String PWchangeL(HttpServletRequest request, ModelMap model, HttpSession session)throws Exception{
        String msg = "";

        UserInfoDTO uDTO = null;

        try{
            String passwordCheck = CmmUtil.nvl(request.getParameter("pwdck"));
            String password = CmmUtil.nvl(request.getParameter("pwd"));

            log.info("new_password : " + password);

            uDTO = new UserInfoDTO();

            uDTO.setUser_pwdck(EncryptUtil.encHashSHA256(passwordCheck));
            uDTO.setUser_id((String)session.getAttribute("id"));
            uDTO.setUser_pw(EncryptUtil.encHashSHA256(password));

            int res = PWCService.PWchange(uDTO);

            if(res==1){
                msg = "비밀번호 변경 성공";
            }else if(res==2){
                msg = "입력한 비밀번호가 현재 비밀번호와 일치하지 않아 종료됩니다";
            }else{
                msg = "오류로 인해 비밀번호 변경 실패";
            }
        }catch(Exception e){
            log.info(this.getClass().getName()+ " PWchangeL 비밀번호 변경 종료");
            //비밀번호 변경 결과 메세지 전달
            model.addAttribute("msg", msg);
            //비밀번호 변경 결과 메시지 전달
            model.addAttribute("uDTO", uDTO);
            uDTO = null;
        }
        return "/alert/PWchange";
    }

}




















