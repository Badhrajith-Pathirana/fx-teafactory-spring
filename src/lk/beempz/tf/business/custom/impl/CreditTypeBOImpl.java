/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.beempz.tf.business.custom.impl;

import java.util.ArrayList;
import lk.beempz.tf.business.custom.CreditTypeBO;
import lk.beempz.tf.dao.custom.Credit_TypeDAO;
import lk.beempz.tf.dto.CreditTypeDTO;
import lk.beempz.tf.entity.Credit_Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreditTypeBOImpl implements CreditTypeBO {

    @Autowired
    Credit_TypeDAO credit_TypeDAO;

    public CreditTypeBOImpl() {

    }
    @Override
    public CreditTypeDTO getCreditType(int id)throws Exception {
        Credit_Type ctype = credit_TypeDAO.findById(id);
        if(ctype == null)
            return null;
        return new CreditTypeDTO(ctype.getTypeid(), ctype.getType_name());
    }

    @Override
    public ArrayList<CreditTypeDTO> getCredits() throws Exception {
        ArrayList<CreditTypeDTO> creditTypeDTOs = new ArrayList<>();
        ArrayList<Credit_Type> all = credit_TypeDAO.getAll();
        for (Credit_Type credit_Type : all) {
            creditTypeDTOs.add(new CreditTypeDTO(credit_Type.getTypeid(), credit_Type.getType_name()));
        }
        return creditTypeDTOs;
    }

    @Override
    public int getIdByName(String type_Name) throws Exception {
        return credit_TypeDAO.getCreditTypeid(type_Name);
    }
    
}
