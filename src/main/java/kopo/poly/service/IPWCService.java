package kopo.poly.service;
import kopo.poly.dto.UserInfoDTO;

public interface IPWCService {
    int New(UserInfoDTO uDTO)throws Exception;
    int Search(UserInfoDTO oDTO)throws Exception;

    int PWchange(UserInfoDTO uDTO)throws Exception;
}
