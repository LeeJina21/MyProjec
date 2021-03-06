package kopo.poly.service;

import kopo.poly.dto.UserInfoDTO;

public interface IUserInfoService {

    //회원가입
    int inserUserInfo(UserInfoDTO pDTO) throws Exception;

    //로그인을 위한 아이디와 비밀번호호가 일치하는지 확인하기
    int getUserLoginCheck(UserInfoDTO pDTO) throws Exception;
}
