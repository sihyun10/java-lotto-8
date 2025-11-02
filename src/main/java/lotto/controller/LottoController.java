package lotto.controller;

import lotto.domain.LottoGameResult;
import lotto.service.LottoGameService;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;
    private final LottoGameService gameService;

    public LottoController(InputView inputView, OutputView outputView, LottoGameService gameService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.gameService = gameService;
    }

    public void start() {
        try {
            LottoGameResult result = gameService.playGame(inputView, outputView);
            outputView.printLottoResult(result.rankCounts(), result.profitRate());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
