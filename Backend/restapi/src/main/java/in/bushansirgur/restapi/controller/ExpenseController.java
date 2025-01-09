package in.bushansirgur.restapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ExpenseController {

    @GetMapping("/expenses")
    public String getExpenses() {

        return "Reading the expenses from database";
    }
}
