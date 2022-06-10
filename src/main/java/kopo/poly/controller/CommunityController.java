package kopo.poly.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import kopo.poly.dto.UserInfoDTO;
import kopo.poly.service.ICommunityService;
import kopo.poly.util.CmmUtil;

@Slf4j
@Controller
public class CommunityController {
    @Resource(name = "CommunityService")
    private ICommunityService communityService;

    //게시판 리스트 페이지 출력
    @RequestMapping(value = "community/boardlist")
    public String boardlist(HttpServletRequest request, ModelMap model)throws Exception{
        List<UserInfoDTO> rList = new ArrayList<>();

        try{
            rList=communityService.getBoardList();

            if(rList==null){
                rList = new ArrayList<>();
            }



        }
    }
}



















