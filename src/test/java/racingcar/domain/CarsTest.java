package racingcar.domain;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import racingcar.domain.strategy.RandomMoveStrategy;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CarsTest {
    private static final int MOVE = 1;
    private static final int STOP = 0;

    @Test
    void _4_미만_멈춤_4_이상_직진() {
        assertRandomNumberInRangeTest(
                () -> {
                    String[] names = IntStream.range(0, 10)
                            .mapToObj(i -> "car" + i)
                            .toArray(String[]::new);
                    Cars cars = new Cars(List.of(names),
                            new RandomMoveStrategy());

                    cars.moveAllCars();

                    Field allCarsField = Cars.class.getDeclaredField("allCars");
                    allCarsField.setAccessible(true);
                    @SuppressWarnings("unchecked")
                    List<Car> carObjects = (List<Car>) allCarsField.get(cars);
                    List<Integer> positions = carObjects.stream().map(Car::getPosition).collect(toList());

                    List<Integer> target = new ArrayList<>(
                            List.of(STOP, STOP, STOP, STOP, MOVE,
                                    MOVE, MOVE, MOVE, MOVE, MOVE));
                    assertThat(positions).isEqualTo(target);
                },
                0, 1, 2, 3, 4, 5, 6, 7, 8, 9
        );
    }
}
