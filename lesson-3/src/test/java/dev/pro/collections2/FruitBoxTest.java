package dev.pro.collections2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FruitBoxTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void boxShouldReturnTotalWeightOfAllAddedFruits() {
        FruitBox<Apple> box = getBoxWithApples(5);

        assertEquals(5.0f, box.getWeight());
    }

    private FruitBox<Apple> getBoxWithApples(int count) {
        FruitBox<Apple> box = new FruitBox<>();
        for (int i = 0; i < count; i++) {
            box.add(new Apple());
        }
        return box;
    }

    @Test
    void compareShouldReturnTrueForBoxWithEqualWeight(){
        FruitBox<Apple> appleBox = getBoxWithApples(6);
        FruitBox<Orange> orangeBox = new FruitBox<>();
        for (int i = 0; i < 4; i++) {
            orangeBox.add(new Orange());
        }

        assertTrue(orangeBox.compare(appleBox));
    }

    @Test
    void boxShouldMoveFruitToAnotherBox() {
        FruitBox<Apple> sourceBox = getBoxWithApples(5);
        FruitBox<Apple> targetBox = new FruitBox<>();

        sourceBox.moveTo(targetBox, 3);

        assertEquals(3f, targetBox.getWeight());
        assertEquals(2f, sourceBox.getWeight());
    }
}