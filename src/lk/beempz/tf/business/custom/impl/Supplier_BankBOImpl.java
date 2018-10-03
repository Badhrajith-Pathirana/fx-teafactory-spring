/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.beempz.tf.business.custom.impl;

import java.util.logging.Level;
import java.util.logging.Logger;
import lk.beempz.tf.business.custom.Supplier_BankBO;
import lk.beempz.tf.dao.custom.Supplier_BankDAO;
import lk.beempz.tf.dto.Supplier_BankDTO;
import lk.beempz.tf.entity.Supplier_Bank;
import lk.beempz.tf.entity.Supplier_Bank_PK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Supplier_BankBOImpl implements Supplier_BankBO {
    @Autowired
    private Supplier_BankDAO supplier_BankDAO;

    public Supplier_BankBOImpl() {
    }
    
    
    @Override
    public boolean addSuplierBank(Supplier_BankDTO supplier_BankDTO) {
        boolean result = false;
        try {
            result = supplier_BankDAO.save(new Supplier_Bank(new Supplier_Bank_PK(supplier_BankDTO.getBranchid(), supplier_BankDTO.getSupplierid()), supplier_BankDTO.getAcc_no()));
            
        } catch (Exception ex) {
            Logger.getLogger(Supplier_BankBOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
