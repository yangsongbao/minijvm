package pers.yangsongbao.minijvm.test;

/**
 *
 * @author songbao.yang
 * @date 2018/3/3
 */
public class Plane implements Flyable {
    private String name;
    private float weight;

    @Override
    public void fly() {
        System.out.println("----------Plane.fly()");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }
}
