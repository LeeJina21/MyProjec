package kopo.poly.persistance.mapper;

import kopo.poly.dto.UserInfoDTO;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface IUserInfoMapper {
    //회원가입
    int insertUserInfo(UserInfoDTO pDTO) throws Exception;
    //회원가입 전 중복체크
    UserInfoDTO getUserExists(UserInfoDTO pDTO) throws Exception;

    //로그인을 위해 아이디와 비밀번호가가 일치하는지 확인
    UserInfoDTO getUserLoginCheck(UserInfoDTO pDTO) throws Exception;
}
