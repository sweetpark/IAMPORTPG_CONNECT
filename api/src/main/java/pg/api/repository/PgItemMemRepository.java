package pg.api.repository;

import org.springframework.stereotype.Repository;
import pg.api.domain.PgItem;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class PgItemMemRepository {

    private Map<String, PgItem> pgItemMap = new ConcurrentHashMap<>();

    public void save (PgItem pgItem){
        pgItemMap.put(pgItem.getMerchantUid(), pgItem);
    }

    public Optional<PgItem> findByMerchantUid(String merchantUid){
        return Optional.ofNullable(pgItemMap.get(merchantUid));
    }

    public Iterable<PgItem> findAll(){
        return pgItemMap.values();
    }

    public void deleteByMerchantUid(String merchantUid){
        pgItemMap.remove(merchantUid);
    }
}
