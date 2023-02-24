package shop.mtcoding.jobara.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomerController {

    @GetMapping("/customer/list")
    public String listForm() {
        return "customer/list";
    }

    @GetMapping("/customer/detail")
    public String detail() {
        return "customer/detail";

    }

    @GetMapping("/customer/saveForm")
    public String saveForm() {
        return "customer/saveForm";

    }
}
