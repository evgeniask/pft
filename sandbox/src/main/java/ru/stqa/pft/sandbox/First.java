package ru.stqa.pft.sandbox;

public class First {
    public static void main(String[] args) {
        hello("world");
        hello("user");
        
        Square s = new Square(6);
        System.out.println("Площаль квадрата со стороной " + s.l + " = " + s.area());

        Rectangle r = new Rectangle(2, 3);
        System.out.println("Площаль прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());
    }

    public static void hello(String somebody) {
        System.out.println("Hello, " + somebody + "!");
    }

}