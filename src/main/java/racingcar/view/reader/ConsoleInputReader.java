package racingcar.view.reader;

import camp.nextstep.edu.missionutils.Console;

public class ConsoleInputReader implements InputReader {
    @Override
    public String readLine() {
        return Console.readLine();
    }
}
