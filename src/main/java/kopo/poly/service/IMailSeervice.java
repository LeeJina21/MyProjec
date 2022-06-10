package kopo.poly.service;

import kopo.poly.dto.MailDTO;

public interface IMailSeervice {
    int doSendmail(MailDTO pDTO);
    int contactwrite(MailDTO pDTO);
}
