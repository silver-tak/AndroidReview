package com.silvertak.androidreview;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.Manifest;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.function.IntToDoubleFunction;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.IntFunction;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

public class MainActivity extends AppCompatActivity {

    public static String TAG = "CAR_LOG";
    ArrayList<Car> carList;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestPermissions(
                new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION},
                000);

        TextView textView = findViewById(R.id.tv);
        Button btn = findViewById(R.id.btn);
        Button changeColorBtn = findViewById(R.id.changeColorBtn);
        Button changeSizeBtn = findViewById(R.id.changeSizeBtn);
        Button syncThreadSleep = findViewById(R.id.syncThreadSleep);
        Button asyncThreadSleep = findViewById(R.id.asyncThreadSleep);
        btn.setOnClickListener(v -> {
            // TODO Textview??? TEXT??? ??????????????????.
            textView.setText("??????????????????");
        });

        changeColorBtn.setOnClickListener(v -> {
            Random rnd = new Random();
            int backColor = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
            int textColor = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
            textView.setBackgroundColor(backColor);
            textView.setTextColor(textColor);
        });

        changeSizeBtn.setOnClickListener(v -> {
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) textView.getLayoutParams();
            layoutParams.width = new Random().nextInt(500);
            textView.requestLayout();
        });

        carList = new ArrayList<>();
        carList.add(new Car("??????", "2020 ?????????", 15700000, 10.5, false, true, true, false));
        carList.add(new Car("??????", "2021 ??????", 24350000, 11, true, true, false, false));
        carList.add(new Car("??????", "2020 ???????????????", 35730000, 8.9, true, true, false, false));
        carList.add(new Car("??????", "2020 ?????????", 31720000, 7.4, false, true, true, false));
        carList.add(new Car("??????", "2020 ?????????", 29750000, 9.5, true, true, false, false));
        carList.add(new Car("??????", "2021 ?????? ???????????????", 28570000, 16.2, false, true, false, true));

        carList.add(new Car("??????", "2021 ????????????", 23760000, 10.5, true, true, false, false));
        carList.add(new Car("??????", "2021 ?????????", 31600000, 8.9, true, true, false, false));
        carList.add(new Car("??????", "2021 K5", 23560000, 9.8, false, true, true, false));
        carList.add(new Car("??????", "2021 K8", 32200000, 7.7, true, true, false, false));
        carList.add(new Car("??????", "2020 ????????? ???????????????", 35340000, 13.2, false, true, false, true));
        carList.add(new Car("??????", "2021 K9", 54780000, 7.5, true, false, false, false));
        carList.add(new Car("??????", "2021 K3", 17570000, 14.1, false, true, false, false));
        carList.add(new Car("??????", "2021 ??????", 11750000, 15.7, false, true, false, false));

        carList.add(new Car("??????", "2021 A ?????????", 39400000, 11.9, false, true, false, false));
        carList.add(new Car("??????", "2021 C ?????????", 50000000, 11.4, true, true, false, false));
        carList.add(new Car("??????", "2021 E ?????????", 64500000, 10.1, true, true, false, false));
        carList.add(new Car("??????", "2021 CLS ?????????", 87700000, 9.8, true, true, false, false));
        carList.add(new Car("??????", "2021 GLS ?????????", 140600000, 7.3, true, true, false, false));

        carList.add(new Car("?????????", "2021 A4", 49350000, 10.5, true, true, false, false));
        carList.add(new Car("?????????", "2021 A6", 64570000, 11.4, true, true, false, false));
        carList.add(new Car("?????????", "2021 A7", 89230000, 9.5, true, true, false, false));
        carList.add(new Car("?????????", "2021 A8", 136960000, 11.3, false, true, false, false));
        carList.add(new Car("?????????", "2021 R8", 255690000, 6, false, true, false, false));

        carList.add(new Car("BMW", "2021 3?????????", 51700000, 9.9, true, true, false, false));
        carList.add(new Car("BMW", "2021 4?????????", 59400000, 10.4, false, true, false, false));
        carList.add(new Car("BMW", "2021 5?????????", 63600000, 7.9, true, true, false, false));
        carList.add(new Car("BMW", "2021 7?????????", 138600000, 8, true, true, false, false));

        // ??????, ?????? ?????? ??? ????????? 3????????? ??????, ?????? ????????? 9km ??????, ?????? r ??????????????? ????????? ???????????? ??????,
        // ??? ????????? ??? ????????? ?????? 1?????? ????????? ????????????. = findAny()
        // ??? ????????? ??? ????????? ????????? ????????? ????????????. = findFirst()
        // ??? ???????????? ????????? ????????????. = toList().size()
        // ???????????????, ??????????????? ???????????? ??? ???????????? ????????????.

        ArrayList<Car> resultList = new ArrayList<>();
        for (Car car : carList) {
            if(car.getPrice() <= 30000000
                    && car.getFuelEfficiency() > 9.0
                    && car.isHybrid())
            {
                resultList.add(car);
                resultList.sort((o1, o2) -> {
                    if(o1.getPrice() > o2.getPrice())
                        return 1;
                    else if(o1.getPrice() < o2.getPrice())
                        return -1;
                    else return 0;  // ==
                });
                Collections.sort(resultList, (car1, t1) -> {
                    if(car1.getPrice() > t1.getPrice())
                        return 1;
                    else if(car1.getPrice() < t1.getPrice())
                        return -1;
                    else return 0;  // ==
                });
            }
        }
        // return resultList.get(new Random().nextInt(resultList.size() - 1));
        // return resultList;

        Optional<Car> randomCar = carList.stream()
                .filter(car -> car.getPrice() < 800000000)
                .filter(car -> car.getFuelEfficiency() > 10)
                .sorted(new Comparator<Car>() {
                    @Override
                    public int compare(Car o1, Car o2) {
                        return 0;
                    }
                })
                .findAny();

        int nSum = carList.stream()
                .filter(car -> car.getPrice() < 800000000)
                .filter(car -> car.getFuelEfficiency() > 10)
                .mapToInt(value -> value.getPrice())
                .sum();

        List<Car> carList1 = carList.stream()
                .filter(car -> car.getCompany().equals("??????") || car.getCompany().equals("??????"))
                .filter(car -> car.getPrice() < 800000000)
                .filter(car -> car.isDisel() || car.isLpg())
                .collect(Collectors.toList());

        carList1.forEach(car -> Log.i(TAG, car.toString()));



        PublishSubject<String> stringSubject = PublishSubject.create();
        stringSubject.observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
                });

        syncThreadSleep.setOnClickListener(v -> {
            Integer[] integerArray = (Integer[]) IntStream.range(0, 4999999).boxed().toArray(Integer[]::new);
            ArrayList<String> stringArrayList = new ArrayList<>();
            Arrays.stream(integerArray).filter(i-> i % 2 ==0).forEach(i -> stringArrayList.add(Integer.toString(i)));
            Toast.makeText(this, stringArrayList.size() + "", Toast.LENGTH_SHORT).show();
        });
        asyncThreadSleep.setOnClickListener(v -> {
            /*runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });*/
            /*Observable.just("TEST")
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(s -> {
                        Thread.sleep(5000);
                        stringSubject.onNext(s);
                    });*/

            Observable.just("TEST")
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(s -> {
                        //Thread.sleep(5000);
                        Integer[] integers = IntStream.range(0, 4999999).boxed().toArray(Integer[]::new);
                        Flowable
                                .fromArray(integers)
                                .subscribeOn(Schedulers.newThread())
                                .filter(integer -> integer % 2 == 0)
                                .toList()
                                .subscribe(list -> stringSubject.onNext("Success " + list.size()));
                    });
        });
        /*for (int i = 0; i < 1000; i++) {
            Log.i(TAG, Integer.toString(i));
        }
        for (int i = 6000; i < 7000; i++) {
            Log.i(TAG, Integer.toString(i));
        }*/
        Single.timer(100L, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.newThread())
                .subscribe(notUse -> {
                    for (int i = 0; i < 1000; i++) {
                        Log.i(TAG, Integer.toString(i));
                    }
                });
        Single.timer(100L, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.newThread())
                .subscribe(notUse -> {
                    for (int i = 6000; i < 7000; i++) {
                        Log.i(TAG, Integer.toString(i));
                    }
                });

        /*new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 500; i++) {
                    Log.i(TAG, Integer.toString(i));
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 500; i < 1000; i++) {
                    Log.i(TAG, Integer.toString(i));
                }
            }
        }).start();*/



    }

    class NoCarException extends Exception {
        public NoCarException(String message) {
            super(message);
        }
    }


}