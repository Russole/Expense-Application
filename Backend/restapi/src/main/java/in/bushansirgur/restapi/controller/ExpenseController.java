package in.bushansirgur.restapi.controller;

import in.bushansirgur.restapi.dto.ExpenseDTO;
import in.bushansirgur.restapi.io.ExpenseResponse;
import in.bushansirgur.restapi.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This is controller class for Expense module
 * @author Russole Chen
 * */


@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin("*")
public class ExpenseController {

    private final ExpenseService expenseService;
    private final ModelMapper modelMapper;

    /**
     * It will fetch the expense from database
     * @return list
     * */
    @GetMapping("/expenses")
    public List<ExpenseResponse> getExpenses() {
        log.info("API GET /expenses called");
        //Call the service method
        List <ExpenseDTO >list= expenseService.getAllExpenses();
        log.info("Printing the data from service {}", list);
        //Convert the Expense DTO to Expense Response
        List<ExpenseResponse> response = list.stream().map(expenseDTO -> mapToExpenseResponse(expenseDTO)).collect(Collectors.toList());
        //Return the list/response
        return response;
    }
    /**
     * Mapper method for converting expense dto object to expense response
     * @param expenseDTO
     * @return ExpenseResponse
     * */
    private ExpenseResponse mapToExpenseResponse(ExpenseDTO expenseDTO) {
        return modelMapper.map(expenseDTO, ExpenseResponse.class);
    }
}
