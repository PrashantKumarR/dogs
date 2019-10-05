package com.example.dogs;


import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReactiveProgramming {
    static final Logger LOGGER = Logger.getLogger(ReactiveProgramming.class.getSimpleName());
    String[] nameArray = new String[]{"Prashant", "Nisant", "Sushant"};
    public List<String> names = new ArrayList<>(Arrays.asList(nameArray));
/***
 *Transforming operations
 */



    /***
     *     Observable Creation
     *
     */
    public Observable getCreateObservable() {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> observableEmitter) throws Exception {
                LOGGER.log(Level.INFO, "Subscribed");
                for (String name : names) {
                    observableEmitter.onNext(name);
                }
            }
        });
    }

    public Observable justObservable = Observable.just("a", "b", "c");

    public Observable getFromObservable() {
//        return Observable.fromArray(nameArray);
        return Observable.fromIterable(names);
    }

    public Observable getIntervalObservable() {
        return Observable.interval(1, TimeUnit.MILLISECONDS);
    }

    public Observable getJustObservable() {
        return Observable.just("Prashant", "Nisant", "Sushant");
    }

    public Observable<Integer> getRangeObservable() {
        return Observable.range(1, 10);
//                .repeat(4);
    }

    public Observable getTimerObservable() {
        return Observable.timer(1, TimeUnit.SECONDS);
    }

    private String value;

    public void setValue(String value) {
        this.value = value;
    }

    public Observable<String> valueObservable() {
//        return Observable.defer(() -> Observable.just(value));
        return Observable.just(value);
    }


    Observer observer = new Observer() {
        @Override
        public void onSubscribe(Disposable disposable) {
            LOGGER.log(Level.INFO, "Subscribed");
        }

        @Override
        public void onNext(Object o) {
            System.out.println("Next: " + o);
        }

        @Override
        public void onError(Throwable throwable) {
            LOGGER.log(Level.SEVERE, throwable.toString());
        }

        @Override
        public void onComplete() {
            LOGGER.log(Level.INFO, "Completed");
        }
    };
}
