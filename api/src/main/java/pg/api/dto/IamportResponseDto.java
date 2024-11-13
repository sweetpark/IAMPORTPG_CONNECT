package pg.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class IamportResponseDto {
    private String code;
    private PaymentResponseDto response;
}
