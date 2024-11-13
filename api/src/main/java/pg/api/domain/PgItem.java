package pg.api.domain;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PgItem {
    private String merchantUid;
    private Long id;
    private Integer amount;
    private String status;

}
