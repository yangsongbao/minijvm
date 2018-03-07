package pers.yangsongbao.minijvm.test;

/**
 * @author songbao.yang
 * @date 2018/3/3
 */
public class Plane implements Flyable {
    private String name;
    private float weight;

    @Override
    public void fly() throws Exception{
        long l = 56666;
        double d = 56666;
        int i = 3;
        try {
            if(weight > 1000) {
                throw new RuntimeException("too fat to fly");
            }
            System.out.println(weight);
            System.out.println(l);
            System.out.println(d);
            System.out.println(i);
            System.out.println("----------Plane.fly()");
        } catch (RuntimeException e){
            e.printStackTrace();
        }
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

    public static void main(String[] args) throws Exception {
        Plane plane = new Plane();
        plane.setWeight(111);
        plane.fly();
    }
}
