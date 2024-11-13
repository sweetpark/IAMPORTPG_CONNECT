package pg.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentResponseDto {
    private String status;
    private Integer amount;
    private String impUid;
    private String merchantUid;
}
