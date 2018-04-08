package pers.yangsongbao.minijvm.test;

/**
 * @author songbao.yang
 * @date 2018/3/3
 */
public class Plane implements Flyable {
    private String name;

    public Plane(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        Plane plane = new Plane("yangsongbao");
        plane.fly();
    }

    @Override
    public void fly() {
        System.out.println(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
