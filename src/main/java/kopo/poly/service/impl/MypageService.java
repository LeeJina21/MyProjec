package kopo.poly.service.impl;

import kopo.poly.dto.UserInfoDTO;
import kopo.poly.persistance.mapper.IMypageMapper;
import kopo.poly.service.IMypageService;
import kopo.poly.util.CmmUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service("MypageService")
public class MypageService implements IMypageMapper{

    private final IMypageMapper MypageMapper;

    public MypageService(IMypageMapper MypageMapper) {
        this.MypageMapper = MypageMapper;
    }


    @Override
    public int updataMYpage(UserInfoDTO pDTO) throws Exception{
        int res =0;

        if(pDTO == null){
            pDTO = new UserInfoDTO();
        }

        //수정
        int success = MypageMapper.updataMYpage(pDTO);

        if(success > 0){
            res = 1;
            log.info("수정완료");
        }else{
            res = 0;
        }


        return res;
    }
}


