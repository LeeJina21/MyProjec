package kopo.poly.persistance.mapper;

import kopo.poly.dto.UserInfoDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IPWCMapper {
    UserInfoDTO Search(UserInfoDTO uDTO) throws Exception;

    int New(UserInfoDTO uDTO) throws Exception;

    //비밀번호 일치 확인(DB조회)
    UserInfoDTO getPWExists(UserInfoDTO uDTO) throws Exception;
}
