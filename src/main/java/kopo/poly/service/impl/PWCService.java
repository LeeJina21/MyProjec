package kopo.poly.service.impl;

import kopo.poly.dto.UserInfoDTO;
import kopo.poly.persistance.mapper.IPWCMapper;
import kopo.poly.service.IPWCService;

import javax.annotation.Resource;
import kopo.poly.util.CmmUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service("PWCService")
public class PWCService implements IPWCService{

    private final IPWCMapper pwcMapper;

    public PWCService(IPWCMapper pwcMapper){
        this.pwcMapper = pwcMapper;
    }

    @Override
    public int Search(UserInfoDTO oDTO) throws Exception{
        int res =0;

        if(oDTO==null){
            oDTO = new UserInfoDTO();
        }
        log.info("아이디 확인" + oDTO.getUser_id());

        UserInfoDTO nDTO = new UserInfoDTO();

        if(nDTO==null){
            res = 1;
            log.info("아이디 존재하지 않음");
        }else{
            res = 2;
            log.info("일치하는 아이디 존재");
        }
        return  res;
    }

    @Override
    public int New(UserInfoDTO wDTO) throws Exception{
        if(wDTO==null){
            wDTO = new UserInfoDTO();
        }
        int res = 0;
        res = pwcMapper.New(wDTO);

        return res;
    }

    @Override
    public int PWchange(UserInfoDTO uDTO)throws Exception{
        //비밀번호 변경 성공 :1, 현재 비밀번호 불의치로 인한 비밀번호 변경 실패:2, 기타에러:0
        int res = 0;

        //controller에서 값이 정상적으로 못 넘어오는 경ㅇ를 대비하기 위해 사용
        if(uDTO == null){
            uDTO =new UserInfoDTO();
        }

        //현재 비밀번호와 일치하는지 조회하기 위해 db에서 데이터를 조회
        UserInfoDTO rDTO = pwcMapper.getPWExists(uDTO);

        //맵에서 값이 정상적으로 못 넘어오는 경우를 대비하기 위해 사용
        if(rDTO == null){
            rDTO = new UserInfoDTO();
            log.info("rDTO가 널로 넘어옴");
        }

        if(CmmUtil.nvl(rDTO.getUser_email()).equals("N")){
            res = 2;
        }else{
            int success = pwcMapper.New(uDTO);

            if(success > 0){
                res = 1;

            }else{
                res = 0;
            }
        }
        return res;
    }
}
