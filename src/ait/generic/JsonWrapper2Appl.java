package ait.generic;

import ait.generic.util.JsonWrapper2;

public class JsonWrapper2Appl {
    public static void main(String[] args) {
        JsonWrapper2<Integer> wrapper1 = new JsonWrapper2<>(10);
        System.out.println(wrapper1);
        Integer a = wrapper1.getValue();
        System.out.println(a);
        JsonWrapper2<String> wrapper2 = new JsonWrapper2<>("ten");
        System.out.println(wrapper2);
        String b = wrapper2.getValue();
        System.out.println(b);
    }
}
