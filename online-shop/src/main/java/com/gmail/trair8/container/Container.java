package com.gmail.trair8.container;

public class Container {

    public static void main(String[] args) {
        Servlet servlet = new Servlet();

        servlet.init();

        for (int i = 0; i < 100; i++) {
            System.out.println(servlet.service("request"));
        }

        servlet.destroy();
    }
}
