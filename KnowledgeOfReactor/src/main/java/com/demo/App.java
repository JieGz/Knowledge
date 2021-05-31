package com.demo;

import reactor.core.publisher.Flux;

public class App {
    public static void main(String[] args) {
        Flux<Integer> flux = Flux.just(1, 2, 3, 4, 5);
        flux.subscribe(System.out::println);
    }
}
