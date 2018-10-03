/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.beempz.tf.business.custom.impl;

import java.util.ArrayList;
import java.util.Date;

import lk.beempz.tf.business.custom.DebitBO;
import lk.beempz.tf.business.custom.MonthlyRateBO;
import lk.beempz.tf.business.custom.SupplierBO;
import lk.beempz.tf.dao.custom.DebitDAO;
import lk.beempz.tf.dto.DebitDTO;
import lk.beempz.tf.entity.Debit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DebitBOImpl implements DebitBO {
    @Autowired
    DebitDAO debitDAO;
    @Autowired
    SupplierBO supplierBO;
    @Autowired
    MonthlyRateBO monthlyRateBO;

    public DebitBOImpl() {
    }
    @Override
    public ArrayList<DebitDTO> getDebitList(Date from, Date to) throws Exception {
        ArrayList<DebitDTO> debitDTOs = new ArrayList<>();
        ArrayList<Debit> debits = null;
        if(from == null && to == null){
            debits = debitDAO.getAll();
        }else{
            debits = debitDAO.getSortAndFiltered(from, to);
        }
        for (Debit debit : debits) {
            debitDTOs.add(new DebitDTO(debit.getDebitid(), debit.getDebitdate(), debit.getPurchaseid(), debit.getSupplierid(), supplierBO.findSupplier(debit.getSupplierid()).getName(), debit.getAmount()));
        }
        return debitDTOs;
    }

    

    @Override
    public boolean updateDebit(DebitDTO debitDTO) throws Exception {
        return debitDAO.update(new Debit(debitDTO.getDebitid(), debitDTO.getPurchaseid(), debitDTO.getDate(), debitDTO.getSupplierid(), debitDTO.getAmount()));
    }

    @Override
    public boolean deleteDebit(int debitId) throws Exception {
        return debitDAO.delete(debitId);
    }

    @Override
    public boolean insertDebit(DebitDTO debitDTO) throws Exception {
       return debitDAO.saveAndGetGenerated(new Debit(debitDTO.getDebitid(), debitDTO.getPurchaseid(), debitDTO.getDate(), debitDTO.getSupplierid(), debitDTO.getAmount())) != null;
    }

    @Override
    public boolean deleteByPurchase(int purchaseid) throws Exception {
        return debitDAO.deleteByPurchaseid(purchaseid);
    }

    @Override
    public DebitDTO getAllByPurchaseID(int purchaseid) throws Exception {
        Debit debit = debitDAO.selectByPurchaseID(purchaseid);
        if(debit == null)
            return null;
        return new DebitDTO(debit.getDebitid(), debit.getDebitdate(), debit.getPurchaseid(), debit.getSupplierid(), supplierBO.findSupplier(debit.getSupplierid()).getName(), debit.getAmount());
    }

    

    
    
}
