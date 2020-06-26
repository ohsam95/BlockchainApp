package com.bankapp.owner.test;


import com.bankapp.owner.dao.AccountDao;

public class bbbb {
	
	public static void main(String[] args) {
		AccountDao accountDao = AccountDao.getinstance();
		accountDao.sendBlockData();
}
}
