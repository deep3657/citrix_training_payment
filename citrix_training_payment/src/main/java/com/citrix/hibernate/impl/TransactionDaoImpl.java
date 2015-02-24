package com.citrix.hibernate.impl;


import org.springframework.stereotype.Repository;

import com.citrix.hibernate.DAO.HibernateDao;
import com.citrix.hibernate.DAO.TransactionDao;
import com.citrix.training.hibernate.entity.Transactions;

@Repository("transactionDao")
public class TransactionDaoImpl extends HibernateDao<Transactions, Long> implements TransactionDao {

}

