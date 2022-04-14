package tpp.tddproject.inwoo.service;

import org.springframework.stereotype.Service;
import tpp.tddproject.domain.entity.nu.NuOrder;
import tpp.tddproject.inwoo.dto.ResponseDataDto;
import tpp.tddproject.vo.nu.NuOrderVo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Service
public class ResponseService {

    private final int SUCCESS_CODE = 200;
    private final int CREATE_CODE = 201;
    private final int DELETE_CODE = 204;

    public ResponseDataDto<NuOrderVo> successNuOrderList(List<NuOrder> nuOrderList) {
        Stream<NuOrderVo> nuOrderVoStream = nuOrderList.stream()
                .map(NuOrder::toVo);

        NuOrderVo[] nuOrderVoArary = nuOrderVoStream.toArray(NuOrderVo[]::new);
        List<NuOrderVo> NuOrderVoList = Arrays.asList(nuOrderVoArary);

        return new ResponseDataDto<>() {{
            setCode(SUCCESS_CODE);
            setDataList(NuOrderVoList);
        }};
    }

    public ResponseDataDto<NuOrderVo> successNuOrder(NuOrder nuOrder) {
        NuOrderVo nuOrderVo = nuOrder.toVo();

        return new ResponseDataDto<>() {{
           setCode(SUCCESS_CODE);
           setData(nuOrderVo);
        }};
    }

    public ResponseDataDto<NuOrderVo> createNuOrder() {
        return new ResponseDataDto<>() {{
            setCode(CREATE_CODE);
        }};
    }

    public ResponseDataDto<NuOrderVo> deleteNuOrder() {
        return new ResponseDataDto<>() {{
            setCode(DELETE_CODE);
        }};
    }

}
