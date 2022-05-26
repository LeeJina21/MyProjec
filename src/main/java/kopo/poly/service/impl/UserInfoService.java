package kopo.poly.service.impl;

import kopo.poly.dto.UserInfoDTO;
import kopo.poly.persistance.mapper.IUserInfoMapper;
import kopo.poly.service.IUserInfoService;
import kopo.poly.util.CmmUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service("UserInfoService")
public class UserInfoService implements IUserInfoService {

    private final IUserInfoMapper userInfoMapper;

    public UserInfoService(IUserInfoMapper userInfoMapper) {
        this.userInfoMapper = userInfoMapper;
    }


    @Override
    public int inserUserInfo(UserInfoDTO pDTO) throws Exception{
        int res =0;

        if(pDTO==null){
            pDTO = new UserInfoDTO();
        }

        UserInfoDTO rDTO = userInfoMapper.getUserExists(pDTO);

        if(rDTO ==null){
            rDTO = new UserInfoDTO();
        }

        //이메일로 중복된 값 확인
        if(CmmUtil.nvl(rDTO.getUser_email()).equals("Y")){
            res=2;
        }else{
            //회원가입
            int success = userInfoMapper.InsertUserInfo(pDTO);

            if(success >0){
                res =1;
            }else{
                res=0;
            }
        }


        return res;
    }

    /**
     * 로그인을 위해 아이디와 비밀번호가 일치하는지 확인
     */
    @Override
    public int getUserLoginCheck(UserInfoDTO pDTO) throws Exception{
        //로그인 성공 :1, 실패:0
        int res = 0;

        //로그인을 위해 아이디와 비밀번호가 일치하는지 확인하기 위한 mapper 호출하기
        UserInfoDTO rDTO = userInfoMapper.getUserLoginCheck(pDTO);

        if(rDTO == null){
            rDTO = new UserInfoDTO();
        }
        /**
         * 로그인 성공 여부 체크 시작
         */
        if (CmmUtil.nvl(rDTO.getUser_id()).length()>0){
            res =1;
        }
        /**
         * 로그인 성공 여부 체크 끝
         */
        return res;
    }
}
