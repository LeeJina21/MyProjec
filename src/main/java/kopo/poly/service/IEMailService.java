package kopo.poly.service;

import kopo.poly.dto.MailDTO;

public interface IEMailService {
    //메일발송
    int doSendEMail(MailDTO pDTO);
}
