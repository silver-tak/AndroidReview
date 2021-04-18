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
            // TODO Textview의 TEXT를 변경하고싶다.
            textView.setText("나우티앤에스");
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
        carList.add(new Car("현대", "2020 아반떼", 15700000, 10.5, false, true, true, false));
        carList.add(new Car("현대", "2021 투싼", 24350000, 11, true, true, false, false));
        carList.add(new Car("현대", "2020 펠리세이드", 35730000, 8.9, true, true, false, false));
        carList.add(new Car("현대", "2020 그랜저", 31720000, 7.4, false, true, true, false));
        carList.add(new Car("현대", "2020 싼타페", 29750000, 9.5, true, true, false, false));
        carList.add(new Car("현대", "2021 투싼 하이브리드", 28570000, 16.2, false, true, false, true));

        carList.add(new Car("기아", "2021 스포티지", 23760000, 10.5, true, true, false, false));
        carList.add(new Car("기아", "2021 카니발", 31600000, 8.9, true, true, false, false));
        carList.add(new Car("기아", "2021 K5", 23560000, 9.8, false, true, true, false));
        carList.add(new Car("기아", "2021 K8", 32200000, 7.7, true, true, false, false));
        carList.add(new Car("기아", "2020 쏘렌토 하이브리드", 35340000, 13.2, false, true, false, true));
        carList.add(new Car("기아", "2021 K9", 54780000, 7.5, true, false, false, false));
        carList.add(new Car("기아", "2021 K3", 17570000, 14.1, false, true, false, false));
        carList.add(new Car("기아", "2021 모닝", 11750000, 15.7, false, true, false, false));

        carList.add(new Car("벤츠", "2021 A 클래스", 39400000, 11.9, false, true, false, false));
        carList.add(new Car("벤츠", "2021 C 클래스", 50000000, 11.4, true, true, false, false));
        carList.add(new Car("벤츠", "2021 E 클래스", 64500000, 10.1, true, true, false, false));
        carList.add(new Car("벤츠", "2021 CLS 클래스", 87700000, 9.8, true, true, false, false));
        carList.add(new Car("벤츠", "2021 GLS 클래스", 140600000, 7.3, true, true, false, false));

        carList.add(new Car("아우디", "2021 A4", 49350000, 10.5, true, true, false, false));
        carList.add(new Car("아우디", "2021 A6", 64570000, 11.4, true, true, false, false));
        carList.add(new Car("아우디", "2021 A7", 89230000, 9.5, true, true, false, false));
        carList.add(new Car("아우디", "2021 A8", 136960000, 11.3, false, true, false, false));
        carList.add(new Car("아우디", "2021 R8", 255690000, 6, false, true, false, false));

        carList.add(new Car("BMW", "2021 3시리즈", 51700000, 9.9, true, true, false, false));
        carList.add(new Car("BMW", "2021 4시리즈", 59400000, 10.4, false, true, false, false));
        carList.add(new Car("BMW", "2021 5시리즈", 63600000, 7.9, true, true, false, false));
        carList.add(new Car("BMW", "2021 7시리즈", 138600000, 8, true, true, false, false));

        // 현대, 기아 차량 중 가격이 3천만원 이하, 연비 효율이 9km 이상, 디젤 r 하이브리드 차량의 리스트를 찾고,
        // 그 리스트 중 무작위 차량 1개의 정보를 출력하라. = findAny()
        // 그 리스트 중 첫번째 차량의 정보를 출력하라. = findFirst()
        // 그 리스트의 갯수를 출력하라. = toList().size()
        // 가격순대로, 연비순대로 정렬하고 그 리스트를 반환하라.

        ArrayList<Car> resultList = new ArrayList<>();
        for (Car car : carList) {
            if(car.getPrice() <= 30000000
                    && car.getFuelEfficiency() > 9.0
                    && car.isHybrid())
            {
                resultList.add(car);
                resultList.sort((o1, o2) -> {
                    if(o1.getPrice() > o2.getPrice())
                        return -1;
                    else if(o1.getPrice() > o2.getPrice())
                        return 1;
                    else return 0;
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
                .filter(car -> car.getCompany().equals("현대") || car.getCompany().equals("기아"))
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
                        Integer[] integers = (Integer[]) IntStream.range(0, 4999999).boxed().toArray(Integer[]::new);
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

    class Car {

        String company;
        String name;
        int price;
        double fuelEfficiency;
        boolean isDisel;
        boolean isGasoline;
        boolean isLpg;
        boolean isHybrid;

        public Car(String company, String name, int price, double fuelEfficiency, boolean isDisel, boolean isGasoline, boolean isLpg, boolean isHybrid) {
            this.company = company;
            this.name = name;
            this.price = price;
            this.fuelEfficiency = fuelEfficiency;
            this.isDisel = isDisel;
            this.isGasoline = isGasoline;
            this.isLpg = isLpg;
            this.isHybrid = isHybrid;
        }

        public String getCompany() {
            return company;
        }

        public String getName() {
            return name;
        }

        public int getPrice() {
            return price;
        }

        public double getFuelEfficiency() {
            return fuelEfficiency;
        }

        public boolean isDisel() {
            return isDisel;
        }

        public boolean isGasoline() {
            return isGasoline;
        }

        public boolean isLpg() {
            return isLpg;
        }

        public boolean isHybrid() {
            return isHybrid;
        }

        @Override
        public String toString() {
            return "Car{" +
                    "company='" + company + '\'' +
                    ", name='" + name + '\'' +
                    ", price=" + price +
                    ", fuelEfficiency=" + fuelEfficiency +
                    ", isDisel=" + isDisel +
                    ", isGasoline=" + isGasoline +
                    ", isLpg=" + isLpg +
                    ", isHybrid=" + isHybrid +
                    '}';
        }

    }
}