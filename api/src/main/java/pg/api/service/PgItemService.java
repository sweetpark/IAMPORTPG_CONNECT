package pg.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pg.api.domain.PgItem;
import pg.api.repository.PgItemMemRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PgItemService {

    private final PgItemMemRepository repository;

    public PgItem findByMerchantUid(String merchantUid){
        return repository.findByMerchantUid(merchantUid)
                .orElseThrow(()->new RuntimeException("해당 UID에 해당하는 주문이 없습니다"));
    }

    public void savePgItem(PgItem pgItem){
        repository.save(pgItem);
    }


    public PgItem createPgItem(Integer amount){
        String merchantUid = generateMerchantUid();
        PgItem pgItem = new PgItem();
        pgItem.setMerchantUid(merchantUid);
        pgItem.setAmount(amount);
        pgItem.setStatus("created");

        repository.save(pgItem);
        return pgItem;
    }

    private String generateMerchantUid() {
        return "PG_" + UUID.randomUUID().toString();
    }
}
