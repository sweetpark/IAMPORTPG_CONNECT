package pg.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pg.api.domain.IamportClient;
import pg.api.domain.PgItem;
import pg.api.dto.IamportResponseDto;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PgItemService pgItemService;
    private final IamportClient iamportClient;

    public void verifyAndUpdatePgItem(String impUid, String merchantUid) throws Exception{
        IamportResponseDto response = iamportClient.paymentByImpUid(impUid);

        if("paid".equals(response.getResponse().getStatus())){
            PgItem pgItem = pgItemService.findByMerchantUid(merchantUid);
            pgItem.setStatus("paid");
        }else {
            throw new RuntimeException("결제 실패");
        }
    }

}
