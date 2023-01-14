package com.rewards.demo.controller;

import com.rewards.demo.model.CustomerDetails;
import com.rewards.demo.service.GetTheCustomerRewards;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class RewardsController {
    static final Logger logger= LoggerFactory.getLogger(RewardsController.class);

    @Autowired
    GetTheCustomerRewards getTheCustomerRewards;

    @GetMapping("/rewards/{customerId}")
    public List <CustomerDetails> getTheCustomerReward(@PathVariable("customerId") Integer customerId){

        logger.info("customerId : {}",customerId);
        getTheCustomerRewards.setTheMonthlyCustomerRewards();
        List<CustomerDetails> response = getTheCustomerRewards.getTheMonthlyCustomerRewards(customerId);

    return response;
    }
}
