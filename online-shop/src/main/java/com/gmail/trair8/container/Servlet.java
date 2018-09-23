package com.gmail.trair8.container;

public class Servlet {

    public void init() {
        System.out.println("init connection pool");
    }

    public String service(String request) {
        if(request.equals("a")) {
            return "Helllo";
        } else {
            return "Bye";
        }
    }

    public void destroy() {
        System.out.println("Delete connection pool");
    }
}
