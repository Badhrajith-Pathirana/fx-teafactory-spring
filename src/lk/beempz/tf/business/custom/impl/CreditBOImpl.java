/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.beempz.tf.business.custom.impl;

import java.util.ArrayList;
import java.util.Date;

import lk.beempz.tf.business.custom.CreditBO;
import lk.beempz.tf.business.custom.CreditTypeBO;
import lk.beempz.tf.business.custom.SupplierBO;
import lk.beempz.tf.dao.custom.CreditDAO;
import lk.beempz.tf.dto.CreditDTO;
import lk.beempz.tf.entity.Credit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreditBOImpl implements CreditBO {

    @Autowired
    CreditDAO creditDAO;
    @Autowired
    SupplierBO supplierBO;
    @Autowired
    CreditTypeBO creditTypeBO ;

    public CreditBOImpl() {

    }
    @Override
    public boolean insertCredit(CreditDTO creditDTO) throws Exception {
        if(creditDTO.getCreditType() == -1){
            int idByName = creditTypeBO.getIdByName(creditDTO.getCredit_typename());
            creditDTO.setCreditType(idByName);
        }
        return creditDAO.saveAndGetGenerated(new Credit(creditDTO.getCreditid(), creditDTO.getSupplierid(), creditDTO.getCreditType(), creditDTO.getDate(), creditDTO.getAmount())) != null;
    }

    @Override
    public ArrayList<CreditDTO> getAllCredits(Date from, Date to) throws Exception {
        ArrayList<CreditDTO> creditDTOs = new ArrayList<>();
        ArrayList<Credit> credits = null;
        if(from == null || to ==  null)
            credits = creditDAO.getAll();
        else
            credits = creditDAO.getFromTo(from, to);
        for (Credit credit : credits) {
            creditDTOs.add(new CreditDTO(credit.getCreditid(), credit.getSupplierid(),supplierBO.findSupplier(credit.getSupplierid()).getName() , credit.getCredit_type(), creditTypeBO.getCreditType(credit.getCredit_type()).getCreditType(), credit.getDate(), credit.getAmount()));
        }
        return creditDTOs;
    }

    @Override
    public boolean deleteCredit(CreditDTO creditDTO) throws Exception {
        return creditDAO.delete(creditDTO.getCreditid());
    }
    
}
