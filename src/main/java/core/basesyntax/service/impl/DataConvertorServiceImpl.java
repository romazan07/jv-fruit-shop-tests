package core.basesyntax.service.impl;

import core.basesyntax.exception.ConvertationException;
import core.basesyntax.model.FruitTransaction;
import core.basesyntax.service.DataConvertorService;
import java.util.ArrayList;
import java.util.List;

public class DataConvertorServiceImpl implements DataConvertorService {
    private static final int OFFSET = 1;
    private static final int FRUIT_STRATEGY_POSITION = 0;
    private static final int FRUIT_NAME_POSITION = 1;
    private static final int FRUIT_AMOUNT_POSITION = 2;
    private static final String SEPARATOR = ",";

    @Override
    public List<FruitTransaction> convertData(List<String> inputList) {
        if (inputList.size() == 0) {
            throw new ConvertationException("The inputList is empty.");
        }
        List<FruitTransaction> fruitTransactionList = new ArrayList<>();
        for (int i = OFFSET; i < inputList.size(); i++) {
            String[] splitInputList = inputList.get(i).split(SEPARATOR);
            FruitTransaction.Operation operation = getfruitOperation(splitInputList);
            String fruitName = splitInputList[FRUIT_NAME_POSITION];
            int amountFruit = (int) Double.parseDouble(splitInputList[FRUIT_AMOUNT_POSITION]);
            fruitTransactionList.add(new FruitTransaction(operation, fruitName, amountFruit));
        }
        return fruitTransactionList;
    }

    private FruitTransaction.Operation getfruitOperation(String[] splitInputList) {
        FruitTransaction.Operation operation = null;
        for (FruitTransaction.Operation element : FruitTransaction.Operation.values()) {
            if (splitInputList[FRUIT_STRATEGY_POSITION].equals(element.getCode())) {
                return operation = element;
            }
        }
        throw new ConvertationException("The " + "'" + splitInputList[FRUIT_STRATEGY_POSITION]
                + "' isn't a strategy type.");
    }
}
