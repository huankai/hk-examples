package hk.springboot.webflux.example.controller;

import com.hk.commons.JsonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author huangkai
 * @date 2019-04-25 23:03
 */
@RestController
public class IndexController {

    /**
     * {@link Mono} 返回一个对象
     *
     * @return
     */
    @GetMapping("/")
    public Mono<JsonResult<Void>> index() {
        return Mono.just(JsonResult.success());
    }
}
