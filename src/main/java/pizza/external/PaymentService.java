
package pizza.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

// checkpoint4. req/res LDH 202011041030
// local test를 위해 url 정보 url="http://localhost:8082 로 수정 (localhost, port)
@FeignClient(name="Payment", url="http://Payment:8080")
//@FeignClient(name="Payment", url="http://localhost:8082")
//@FeignClient(name="payment", url="${api.url.payment}")
public interface PaymentService {

    @RequestMapping(method= RequestMethod.POST, path="/payments")
    public void doPayment(@RequestBody Payment payment);

}