package lotto;

import java.util.List;

import static java.util.stream.Collectors.*;
import static lotto.LottoRank.*;

public class LottoWinningDiscriminator {
    private final List<Integer> winningNumber;
    private final Integer bonusNumber;

    public LottoWinningDiscriminator(List<Integer> winningNumber, Integer bonusNumber) {
        this.winningNumber = winningNumber;
        this.bonusNumber = bonusNumber;
    }

    public List<LottoRank> discriminate(List<Lotto> purchasedLotto) {
        return purchasedLotto.stream()
                .map(lotto -> winningResult(countCorrectWinningNumber(lotto), isCorrectBonusNumber(lotto)))
                .collect(toUnmodifiableList());
    }

    private int countCorrectWinningNumber(Lotto lotto) {
        return (int) lotto.getNumbers().stream()
                .filter(number -> winningNumber.contains(number))
                .count();
    }

    private boolean isCorrectBonusNumber(Lotto lotto) {
        return lotto.getNumbers().stream()
                .anyMatch(number -> bonusNumber.equals(number));
    }
}