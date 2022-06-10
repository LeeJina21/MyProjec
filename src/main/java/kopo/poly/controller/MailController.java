package kopo.poly.controller;

import kopo.poly.util.CmmUtil;
import kopo.poly.util.EncryptUtil;
import kopo.poly.dto.UserInfoDTO;
import kopo.poly.dto.MailDTO;
import kopo.poly.service.IPWCService;
import kopo.poly.service.IMailSeervice;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.apache.commons.lang3.RandomStringUtils;

@Component("MailController")
public class MailController {
    @Resource(name = "MailService")
    public IMailSeervice mailSeervice;

    @Resource(name = "PWCService") private IPWCService PWCService;
}











