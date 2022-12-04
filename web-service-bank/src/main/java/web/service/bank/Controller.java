package web.service.bank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import web.service.wsdl.bank.*;

@RestController
public class Controller {

    @Autowired
    private BankService bankService;

    @PostMapping("/api/v2/bank/getWalletList")
    public GetWalletListResponse getWalletList(@RequestBody GetWalletListRequest request) {
        GetWalletListResponse response = new GetWalletListResponse();
        response.getWallets().addAll(bankService.getWalletList(request.getUuid()));
        return response;
    }

    @PostMapping("/api/v2/bank/getBalance")
    public GetBalanceResponse getBalance(@RequestBody GetBalanceRequest request) {
        GetBalanceResponse response = new GetBalanceResponse();
        response.setWallet(bankService.getWallet(request.getUuid(), request.getCurrency()));
        return response;
    }

    @PostMapping("/api/v2/bank/addBalance")
    public AddMoneyWalletResponse postBalance(@RequestBody AddMoneyWalletRequest request) {
        AddMoneyWalletResponse response = new AddMoneyWalletResponse();
        response.setWallet(bankService.addWallet(request.getUuid(), request.getCurrency(), request.getValue()));
        return response;
    }

    @PostMapping("/api/v2/bank/removeBalance")
    public RemoveMoneyWalletResponse removeBalance(@RequestBody RemoveMoneyWalletRequest request) {
        RemoveMoneyWalletResponse response = new RemoveMoneyWalletResponse();
        response.setWallet(bankService.removeWallet(request.getUuid(), request.getCurrency(), request.getValue()));
        return response;
    }

}
