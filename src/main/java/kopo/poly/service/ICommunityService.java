package kopo.poly.service;

import java.util.List;
import kopo.poly.dto.UserInfoDTO;

public interface ICommunityService {
    int insertCommunity(UserInfoDTO uDTO)throws Exception;
    List<UserInfoDTO> getBoardList() throws Exception;
    UserInfoDTO getBoard(UserInfoDTO pDTO) throws Exception;
    int updateCommunity(UserInfoDTO aDTO) throws Exception;
    int deleteCommunity(UserInfoDTO aDTO) throws Exception;
}
