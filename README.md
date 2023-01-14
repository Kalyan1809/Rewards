# Rewards

**API**
eg: 
localhost:8080/customer/rewards/123

I have added two customer when you call the api 

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
