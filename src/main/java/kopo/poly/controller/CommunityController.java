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

            for(UserInfoDTO p : rList){
                log.info("글 번호 : " );
            }
        }catch (Exception e){
            //log.info(e.getStackTrace());
        }finally {
            model.addAttribute("rList", rList);
        }
        return "/community/boardlist";
    }

    //게시판 글쓰기 페이지 출력
    @RequestMapping(value = "community/boardwrite")
    public String boardwrite(){
        return "/community/boardwrite";
    }

    //게시판 게시글 작성
    @RequestMapping(value = "community/boarderite/logic")
    public String insertCommunity(HttpServletRequest request, ModelMap model)throws Exception{
        String title = request.getParameter("title");
        String writer = request.getParameter("writer");
        String contents = request.getParameter("contents");
        String id = request.getParameter("id");

        UserInfoDTO aDTO = new UserInfoDTO();

        //
        //
        //
        //

        int res = communityService.insertCommunity(aDTO);

        if(res == 0){

        }else if(res ==1){

        }
        model.addAttribute("res", String.valueOf(res));

        return "/alert/boardwriteAlert";
    }

    //게시판 상세 페이지 출력
    @RequestMapping(value = "community/boardsee")
    public String boardsee(HttpServletRequest request, ModelMap model)throws Exception{
        String no = CmmUtil.nvl(request.getParameter("number").toString());


        UserInfoDTO pDTO = new UserInfoDTO();
        //

        UserInfoDTO rDTO = new UserInfoDTO();
        rDTO = communityService.getBoard(pDTO);
        pDTO = null;

        if(rDTO == null){
            rDTO = new UserInfoDTO();
        }

        model.addAttribute("rDTO", rDTO);

        return "/community/boardsee";
    }

    //게시글 수정 페이지 출력
    @RequestMapping(value = "community/boardupdate")
    public String boardupdate(HttpServletRequest request, ModelMap model)throws Exception{
        String no = CmmUtil.nvl(request.getParameter("number").toString());

        UserInfoDTO pDTO = new UserInfoDTO();
        //

        UserInfoDTO rDTO = new UserInfoDTO();

        rDTO = communityService.getBoard(pDTO);

        if(rDTO == null){
            rDTO = new UserInfoDTO();
        }

        pDTO = null;

        model.addAttribute("rDTO", rDTO);

        return "/community/boardupdate";
    }

    //게시글 수정 코드
    @RequestMapping(value = "community/boardupdate/logic")
    public String boardUpdateLogic(HttpServletRequest request, ModelMap model)throws Exception{
        String title = request.getParameter("title");
        String writer = request.getParameter("writer");
        String contents = request.getParameter("contents");
        String id = request.getParameter("id");

        UserInfoDTO aDTO = new UserInfoDTO();

        //
        //
        //
        //

        int res = communityService.insertCommunity(aDTO);

        if(res == 0){

        }else if(res ==1){

        }
        model.addAttribute("res", String.valueOf(res));

        return "/alert/boardupdateAlert";
    }

    //게시글 삭제 로직
    @RequestMapping(value = "community/boarddelete")
    public String boarddelete(HttpServletRequest request, ModelMap model)throws Exception{
        String seq = CmmUtil.nvl(request.getParameter("number").toString());

        UserInfoDTO aDTO = new UserInfoDTO();
        //


        int res = communityService.deleteCommunity(aDTO);

        if(res == 0){

        }else if(res==1){

        }

        aDTO = null;

        model.addAttribute("res", String.valueOf(res));

        return "/community/boarddeleteAlert";
    }

}



















