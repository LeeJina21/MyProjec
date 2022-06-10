package kopo.poly.service.impl;

import kopo.poly.dto.UserInfoDTO;
import kopo.poly.persistance.mapper.ICommunityMapper;
import kopo.poly.service.ICommunityService;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service("CommunityService")
public class CommunityService implements ICommunityService{

    private final ICommunityMapper communityMapper;

    public CommunityService(ICommunityMapper communityMapper){
        this.communityMapper = communityMapper;
    }

    @Override
    public int insertCommunity(UserInfoDTO uDTO)throws Exception{
        int res = 0;

        if(uDTO == null){
            uDTO = new UserInfoDTO();
        }else{
            communityMapper.insertCommunity(uDTO);
            res = 1;
            //게시글 입력 완료
        }

        return res;
    }

    @Override
    public List<UserInfoDTO> getBoardList() throws Exception{
        List<UserInfoDTO> rlist = new ArrayList<>();

        rlist = communityMapper.getBoardList();

        if(rlist==null){
            rlist = new ArrayList<>();
        }else{

        }

        return rlist;
    }

    @Override
    public UserInfoDTO getBoard(UserInfoDTO rDTO)throws Exception{
        if(rDTO == null){

        }
        UserInfoDTO pDTO = new UserInfoDTO();
        pDTO = communityMapper.getBoard(rDTO);

        if(pDTO == null){
            pDTO = new UserInfoDTO();
        }else{
            log.info("게시판 상제 불러오기 성공");
        }

        return pDTO;
    }

    @Override
    public int updateCommunity(UserInfoDTO uDTO) throws Exception{
        int res = 0;

        if(uDTO == null){
            UserInfoDTO zDTO = new UserInfoDTO();
        }else{
            communityMapper.updateCommunity(uDTO);
            res = 1;
            log.info("게시글 수정 성공");
        }

        return res;
    }

    @Override
    public int deleteCommunity(UserInfoDTO uDTO) throws Exception{
        int res = 0;

        if(uDTO == null){
            uDTO = new UserInfoDTO();
        }else{
            communityMapper.deleteCommunity(uDTO);
            res = 1;
            log.info("게시글 삭제 성공");
        }


        return res;
    }

}










