package kopo.poly.service.impl;

import kopo.poly.dto.MailDTO;
import kopo.poly.service.IEMailService;
import kopo.poly.util.CmmUtil;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service("EMailService")
public class EMailService implements IEMailService {

    final String host = "smtp.gmail.com";                //제공하는 SMTP서버
    final String user = "shindaepal@gmail.com";  //아이디
    final String password = "cxoyrwyfkuwuvhzy";             //비번

    @Override
    public int doSendEMail(MailDTO pDTO) throws Exception{
        log.info(this.getClass().getName() + "doSendMail 시작");

        //메일 발송 성공여부 (성공:1 실패:0)
        int res = 1;

        //전달받은 DTO로부터 데이터가져오기(DTO객체가 메모리에 올라가지 않아Null이 팔생할 수 있기 때문에 에러 방지용으로 if문 사용)
        if(pDTO ==null){
            pDTO = new MailDTO();
        }

        //받는 사람
        String toMail = CmmUtil.nvl(pDTO.getToMail());

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", 587);

        //smtp서버 인증 처리 로직
        Session session = Session.getDefaultInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });

        try {

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));

            // 메일 받는 사람
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toMail));
            log.info("메일 받는 사람 : " + toMail);

            // 메일 제목
            message.setSubject(CmmUtil.nvl(pDTO.getTitle()));
            log.info("받아온 메일 제목 : " + pDTO.getTitle());

            // 메일 내용
            message.setText(CmmUtil.nvl(pDTO.getContents()));
            log.info("받아온 메일 내용 : " + pDTO.getContents());

            // 메일 발송
            Transport.send(message);

        }catch(MessagingException e) {
            res = 0;
            log.info("[ERROR]" + this.getClass().getName() + ".doSendMail : " + e);
        }catch(Exception e) {
            res = 0;
            log.info("[ERROR]" + this.getClass().getName() + ".doSendMail : " + e);
        }

        log.info("CMailService : doSendmail(메일발송 서비스) 끝! ");
        return res;
    }
}
