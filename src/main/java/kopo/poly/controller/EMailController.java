package kopo.poly.controller;

import kopo.poly.dto.MailDTO;
import kopo.poly.service.IEMailService;
import kopo.poly.util.CmmUtil;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Controller
public class EMailController {

    @Resource(name = "EMailService")
    private IEMailService emailService;

    /**
     * 메일발송
     */
    @RequestMapping(value = "mail/sendEMail", method = RequestMethod.GET)
    public String sendEMail(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception{

        log.info(this.getClass().getName() + "mail/sendMail 시작");

        //웹 URL로부터 전달받는 값들
        String toMail = CmmUtil.nvl(request.getParameter("toMail"));        //받는사람
        String title = CmmUtil.nvl(request.getParameter("title"));          //제목
        String contents = CmmUtil.nvl(request.getParameter("contents"));    //내용

        //메일 발송할 정보 넣기 위한 DTO 객체 생성
        MailDTO pDTO = new MailDTO();

        //웹에서 받은 값을 DTO에 넣기
        pDTO.setToMail(toMail);     //받은 사람을 디이토에 저장
        pDTO.setTitle(title);       //제목을 디티오에 저장
        pDTO.setContents(contents); //내용을 디티오에 저장

        //메일발송하기
        int res = emailService.doSendEMail(pDTO);

        if (res == 1) {
            log.info(this.getClass().getName() + "메일 발송 성공");
        }else{
            log.info(this.getClass().getName() + "메일 발송 실패");
        }

        //메일 발송 결과를 JSP에 전달하기(데이터 전달시, 숫자보단 문자열이 컨트롤하기 편하기 때문에 강제로 숫자를 문자로 변환함
        model.addAttribute("res", String.valueOf(res));

        log.info(this.getClass().getName() + "mail/sendEMail 끝");

        return "/mail/sendEMailResult";
    }
}
