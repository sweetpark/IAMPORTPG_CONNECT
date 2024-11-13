package pg.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pg.api.domain.PgItem;
import pg.api.service.PaymentService;
import pg.api.service.PgItemService;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class PaymentController {


    private final PgItemService pgItemService;
    private final PaymentService paymentService;

    @GetMapping("/main")
    public String getMainPage(){
        return "test";
    }


    @ResponseBody
    @PostMapping("/create")
    public ResponseEntity<PgItem> createOrder(@RequestParam Integer amount) {
        // Generate merchant_uid and store order in the database
        PgItem pgItem = pgItemService.createPgItem(amount);
        return new ResponseEntity<>(pgItem, HttpStatus.CREATED);
    }


    @ResponseBody
    @PostMapping("/payment/request")
    public ResponseEntity<?> requestPayment(@RequestParam String merchantUid) {
        PgItem order = pgItemService.findByMerchantUid(merchantUid);

        if (order != null) {
            return ResponseEntity.ok("Payment request successful for merchant_uid: " + merchantUid);
        } else {
            return new ResponseEntity<>("Order not found", HttpStatus.NOT_FOUND);
        }
    }


    @ResponseBody
    @PostMapping("/payment/complete")
    public ResponseEntity<?> completePayment(@RequestParam String impUid, @RequestParam String merchantUid) {
        try {
            paymentService.verifyAndUpdatePgItem(impUid, merchantUid);
            return ResponseEntity.ok("Payment successful for merchant_uid: " + merchantUid);
        } catch (Exception e) {
            return new ResponseEntity<>("Payment verification failed", HttpStatus.BAD_REQUEST);
        }
    }

}
