package kopo.poly.controller;

import kopo.poly.dto.MailDTO;
import kopo.poly.service.IEMailService;
import kopo.poly.util.CmmUtil;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class EMailController {

    @Resource(name = "EMailService")
    private IEMailService emailService;

    /**
     * 메일발송
     */

    @GetMapping (value = "mail/email")
    public String SendEmail() {


        return "/mail/email";
    }
    @PostMapping (value = "mail/emailLogic")
    public String emailLogic(HttpServletRequest request,ModelMap model, HttpSession session) throws Exception{

        log.info(this.getClass().getName() + "mail/sendMail 시작");
        int res = 0;

        //웹 URL로부터 전달받는 값들
        String toMail = "2120110030@gspace.kopo.ac.kr";
        String title = CmmUtil.nvl(request.getParameter("title"));          //제목
        String contents = CmmUtil.nvl(request.getParameter("contents"));    //내용
        String msg = "";
        String url = "";
        //메일 발송할 정보 넣기 위한 DTO 객체 생성
        MailDTO pDTO = new MailDTO();

        //웹에서 받은 값을 DTO에 넣기
        pDTO.setToMail(toMail);     //받은 사람을 디이토에 저장
        pDTO.setTitle("[고객센터] "+title);       //제목을 디티오에 저장
        pDTO.setContents("회신받을 계정 : "+session.getAttribute("user_id")+"\n 내용 : "+contents); //내용을 디티오에 저장

        //메일발송하기
        res = emailService.doSendEMail(pDTO);

        if (res == 1) {
            msg = "메일 발송 성공@!";
            url = "/mail/email";
            log.info(this.getClass().getName() + "메일 발송 성공");
        }else{
            msg = "메일 발송 실패!";
            url = "/mail/email";
            log.info(this.getClass().getName() + "메일 발송 실패");
        }

     
        model.addAttribute("msg", msg);
        model.addAttribute("url", url);
        log.info(this.getClass().getName() + "mail/sendEMail 끝");

        return "/redirect";
    }
}
