package kopo.poly.service;

import kopo.poly.dto.MailDTO;

public interface IMailService {
    //메일발송
    int doSendMail(MailDTO pDTO);
}
