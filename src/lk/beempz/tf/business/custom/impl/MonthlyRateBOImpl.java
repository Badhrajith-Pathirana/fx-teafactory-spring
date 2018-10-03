/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.beempz.tf.business.custom.impl;

import java.util.ArrayList;

import lk.beempz.tf.business.custom.MonthlyRateBO;
import lk.beempz.tf.dao.custom.RateDAO;
import lk.beempz.tf.dto.MonthlyRateDTO;
import lk.beempz.tf.dto.UnprocessedDebitDTO;
import lk.beempz.tf.entity.Rate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MonthlyRateBOImpl implements MonthlyRateBO {
    @Autowired
    RateDAO rateDAO;
   // DebitBO debitBO;
    
    public MonthlyRateBOImpl() {
    }

    

    

    @Override
    public boolean updateMonthlyRates(MonthlyRateDTO monthlyRateDTO)throws Exception {
        return rateDAO.save(new Rate(monthlyRateDTO.getDate(), monthlyRateDTO.getaGrade(), monthlyRateDTO.getbGrade(), monthlyRateDTO.getTravelling()));
    }

    @Override
    public MonthlyRateDTO getRates(UnprocessedDebitDTO debitDTO)throws Exception{
        Rate rate = rateDAO.findById(debitDTO.getDate());
        return new MonthlyRateDTO(debitDTO.getDate(), rate.getAkgper(), rate.getBkgper(), rate.getTravelling());
    }

    @Override
    public ArrayList<MonthlyRateDTO> getAllRates() throws Exception {
        ArrayList<MonthlyRateDTO> monthlyRateDTOs = new ArrayList<>();
        ArrayList<Rate> all = rateDAO.getAll();
        for (Rate rate : all) {
            monthlyRateDTOs.add(new MonthlyRateDTO(rate.getRateMonth(), rate.getAkgper(), rate.getBkgper(), rate.getTravelling()));
        }
        return monthlyRateDTOs;
    }

    
}
