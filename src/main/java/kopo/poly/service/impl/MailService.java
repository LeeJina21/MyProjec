package kopo.poly.service.impl;

import kopo.poly.dto.MailDTO;
import kopo.poly.service.IMailService;
import kopo.poly.util.CmmUtil;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service("MailService")
public class MailService implements IMailService {

    final String host = "smtp.gmail.com";                //제공하는 SMTP서버
    final String user = "2120110030@gspace.kopo.ac.kr";  //아이디
    final String password = "vhfflxpr7407!";             //비번

    @Override
    public int doSendMail(MailDTO pDTO){
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
        props.put("mail.smtp.host", host); //jsvax 외부 라이브러리에 메일 보내는 사람의 정보 저장 설정
        props.put("mail.smtp.auth", "true"); //javax 메일 보내는 사람 인증 설정

        //smtp서버 인증 처리 로직
        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(user, password);
            }
        });

        try{
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toMail));

            //메일 제목
            message.setSubject(CmmUtil.nvl(pDTO.getTitle()));

            //메일 내용
            message.setText(CmmUtil.nvl(pDTO.getContents()));

            //메일 발송
            Transport.send(message);

        }catch (MessagingException e){ // 메일 전송 에러 다 잡기
            res = 0;
            log.info("[ERROR] " + this.getClass().getName() + ".doSendMail : " + e);
        } catch (Exception e){ //모든 에러 다 잡기
            res = 0;
            log.info("[ERROR] " + this.getClass().getName() + ".doSendMail : " + e);
        }

        log.info(this.getClass().getName() + "doSendMail 끝");

        return res;
    }
}
