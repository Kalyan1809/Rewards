package com.rewards.demo.service;

import com.rewards.demo.model.CustomerDetails;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class GetTheCustomerRewards {

     private static final Map<Integer, TreeMap<LocalDateTime, Double>> customerList = new HashMap<Integer, TreeMap<LocalDateTime, Double>>();

    public void setTheMonthlyCustomerRewards() {
        //Setting the Values
        TreeMap<LocalDateTime, Double> firstCustomer = new TreeMap<LocalDateTime, Double>();
        firstCustomer.put(LocalDateTime.now(), 142.00);
        firstCustomer.put(LocalDateTime.now().minusMonths(1L), 130.00);
        firstCustomer.put(LocalDateTime.now().minusDays(1L), 99.00);
        firstCustomer.put(LocalDateTime.now(), 60.00);
        customerList.put(123, firstCustomer);

        TreeMap<LocalDateTime, Double> secondCustomer = new TreeMap<LocalDateTime, Double>();
        secondCustomer.put(LocalDateTime.now(), 199.0);
        secondCustomer.put(LocalDateTime.now().minusDays(1L), 77.0);
        secondCustomer.put(LocalDateTime.now().minusHours(1L), 95.5);
        customerList.put(456, secondCustomer);

    }

    public  List <CustomerDetails> getTheMonthlyCustomerRewards(Integer id) {
        List <CustomerDetails> response =new ArrayList<CustomerDetails>();
        customerList.entrySet()
                .stream()
                .filter(i -> i.getKey().equals(id))
                .forEach(list -> {
                    Map<Month, List<Map.Entry<LocalDateTime, Double>>> collect = list.getValue().entrySet()
                            .stream()
                            .collect(Collectors.groupingBy(e -> e.getKey().getMonth()));
                    for (Map.Entry<Month, List<Map.Entry<LocalDateTime, Double>>> entry : collect.entrySet()) {
                        System.out.println("------------------------");
                        CustomerDetails eachCustomer = new CustomerDetails();
                        List<Double> sum = new ArrayList<>();
                        entry.getValue()
                                .stream()
                                .forEach(e -> {

                                            Double rewardsCalculationLesThanHundred = 0.00;
                                            Double rewardsCalculationAboveHundred = 0.00;

                                            if (e.getValue() > 50 && e.getValue() <= 100) {

                                                //Maintaining the Decimal part
                                                Double rewards = new BigDecimal(e.getValue() - 50)
                                                        .setScale(3)
                                                        .doubleValue();

                                                //calculating the formula
                                                rewardsCalculationLesThanHundred = rewards * 1;
                                            }
                                            if (e.getValue() > 100) {

                                                Double rewards = e.getValue() - 100;
                                                Double bd = new BigDecimal(rewards).setScale(3).doubleValue();

                                                //calculating the formula
                                                rewardsCalculationAboveHundred = bd * 2;
                                                rewardsCalculationLesThanHundred = 50.0 * 1;
                                            }
                                            Double total = rewardsCalculationLesThanHundred + rewardsCalculationAboveHundred;
                                            sum.add(total);
                                        }
                                );
                        double totalRewardMonthly = sum.stream().mapToDouble(a -> a).sum();
                        System.out.println(totalRewardMonthly);
                        eachCustomer.setCustomerId(id);
                        eachCustomer.setRewards(totalRewardMonthly);
                        eachCustomer.setMonth(entry.getKey().name());
                        response.add(eachCustomer);
                    }
                });
        return response;
    }
}
