package com.demo;

import lombok.Builder;
import lombok.Data;
import org.junit.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class AppTest {

    @Test
    public void just() {
        Flux<Integer> flux = Flux.just(1);
        // flux.subscribe(new Consumer<Integer>() {
        //     @Override
        //     public void accept(Integer integer) {
        //         System.out.println(integer);
        //     }
        // });

        Consumer<Integer> consumer = element -> System.out.println(element + 1);
        consumer = consumer.andThen(element -> System.out.println(element + 2));
        flux.subscribe(consumer);
    }

    @Test
    public void justAny() {
        Flux<Integer> flux = Flux.just(1, 2, 3, 4, 5);
        // flux.log().subscribe(System.out::println);
        flux.log().subscribe(new Subscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription s) {
                s.request(10);
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println(integer);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        });
    }

    @Test
    public void flux() {
        final Request request = Request.builder()
                .name("Luke")
                .sex("boy")
                .build();
        Flux<Request> flux = Flux.just(request);
        final Set<Response> boy = flux.log().filter(element -> Objects.equals(element.getSex(), "boy"))
                .map(request1 -> Response.builder()
                        .code("200")
                        .body(request1.toString())
                        .build())
                .toStream().collect(Collectors.toSet());
        //.subscribe(System.out::println);
        System.out.println("------");
        flux.log()
                .doOnSubscribe(onSubscribe())
                .doOnComplete(onComplete())
                .doOnCancel(cancelRunnable())
                .doOnError(Exception.class, error -> System.out.println(error.getMessage()))
                .doOnNext(e -> System.out.println("doOnNext->" + e.getName()))
                .doOnTerminate(() -> System.out.println("doOnTerminate"))
                .subscribe(System.out::println);

    }

    private Consumer<Subscription> onSubscribe() {
        return subscription -> subscription.request(5);
    }

    private Runnable onComplete() {
        return () -> System.out.println("执行完成了");
    }

    private Runnable cancelRunnable() {
        return () -> System.out.println("取消了");
    }

    @Data
    @Builder
    private static class Request {
        private String name;
        private String sex;
    }

    @Data
    @Builder
    private static class Response {
        private String code;
        private String body;
    }

}
