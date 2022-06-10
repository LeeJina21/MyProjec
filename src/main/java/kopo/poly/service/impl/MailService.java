package kopo.poly.service.impl;

import kopo.poly.dto.MailDTO;
import kopo.poly.service.IMailSeervice;
import kopo.poly.util.CmmUtil;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Properties;
import javax.annotation.Resource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


@Slf4j
@Service("MailService")
public class MailService implements IMailSeervice {

    final String host = "smtp.gmail.com";                //제공하는 SMTP서버
    final String user = "2120110030@gspace.kopo.ac.kr";  //아이디
    final String password = "vhfflxpr7407!";             //비번

    @Override
    public int doSendmail(MailDTO pDTO){
        int res = 1;

        if(pDTO == null){
            pDTO = new MailDTO();
        }

        String toMail = CmmUtil.nvl(pDTO.getToMail());

        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(user, password);
            }
        });

        try{
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toMail));
            message.setSubject(CmmUtil.nvl(pDTO.getToMail()));
            message.setText(CmmUtil.nvl(pDTO.getContents()));
            Transport.send(message);
        }catch (MessagingException e){
            res=0;
            log.info(this.getClass().getName()+ "[ERROR]"  + ".doSenMail : " + e);
        }catch(Exception e){
            res = 0;
            log.info(this.getClass().getName()+ "[ERROR]"  + ".doSenMail : " + e);
        }
        return res;
    }

    @Override
    public int contactwrite(MailDTO pDTO){
        int res = 1;

        if(pDTO == null){
            pDTO = new MailDTO();
        }

        String toMail = CmmUtil.nvl(pDTO.getToMail());

        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", "true");

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
            log.info("메일 제목" + pDTO.getTitle());

            //메일 내용
            message.setText(CmmUtil.nvl(pDTO.getContents()));
            log.info("메일 내용" + pDTO.getContents());

            //메일 발송
            Transport.send(message);
            log.info(toMail);
            log.info(pDTO.getTitle());

        }catch (MessagingException e){
            res = 0;
            log.info(this.getClass().getName()+ "[ERROR]"  + ".dosendMail : " + e);
        }catch (Exception e){
            res = 0;
            log.info(this.getClass().getName()+ "[ERROR]"  + ".dosendMail : " + e);
        }
        log.info(this.getClass().getName() + ".doSendMail 끝");

        return res;
    }

}





















