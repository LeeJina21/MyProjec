package kopo.poly.persistance.mapper;

import java.util.List;
import kopo.poly.dto.UserInfoDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ICommunityMapper {
    void insertCommunity(UserInfoDTO uDTO) throws Exception;
    List<UserInfoDTO> getBoardList() throws Exception;
    UserInfoDTO getBoard(UserInfoDTO rDTO)throws Exception;
    void updateCommunity(UserInfoDTO uDTO)throws Exception;
    void deleteCommunity(UserInfoDTO uDTO)throws Exception;
}
