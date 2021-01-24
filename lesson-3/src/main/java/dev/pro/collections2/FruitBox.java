package dev.pro.collections2;

import java.util.ArrayList;

public class FruitBox<T extends Fruit> {
    private final ArrayList<T> fruits = new ArrayList<>();

    public void add(T fruit){
        fruits.add(fruit);
    }

    public float getWeight(){
        float totalWeight = 0;
        for (var fruit:
                fruits) {
            totalWeight += fruit.getWeight();
        }
        return totalWeight;
    }

    public boolean compare(FruitBox<?> otherBox){
        final float epsilon = 0.001f;
        float diff = Math.abs(getWeight() - otherBox.getWeight());
        return diff < epsilon;
    }

    public int moveTo(FruitBox<T> other, int count){
        if(count > fruits.size()){
            count = fruits.size();
        }
        for (int i = count -1; i >= 0; i--) {
            var fruit = fruits.get(i);
            fruits.remove(i);
            other.add(fruit);
        }
        return 0;
    }

}
