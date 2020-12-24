package Less_3_1;

import java.util.ArrayList;
import java.util.List;

public class Box<T extends Fruit> extends mainClass{//будем пренимать только Fruits
    private List<T> fruits;

    public Box(){
        fruits = new ArrayList<>();
    }

    public void add(T fruit){      //метод добавляет фрукты в коробку
        fruits.add(fruit);         //
    }

    public void muveTo(Box<T> otherBox){                //метод перекладывает фрукты из одной
        fruits.forEach(fruits -> otherBox.add(fruits)); //коробки в другую
    }

    public float getWeight(){      //узнаем общий вес
        float weight = 0;          //фруктов в нашей коробке
        for (Fruit f : fruits) {   //
            weight += f.getWeight();//
        }
    return weight;
    }

    public boolean compare (Box<? extends Fruit> otherBox){   //сравниваем Box с otherBox
        return otherBox.getWeight() == getWeight();           //
    }
}
