package pg.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentCompleteRequestDto {
    private String impUid;
    private String merchantUid;
}
