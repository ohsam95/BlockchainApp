package com.bankapp.owner.test;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.bankapp.owner.dao.AccountDao;
import com.bankapp.owner.db.DBConn;
import com.bankapp.owner.dto.MempoolBlock;
import com.bankapp.owner.dto.mempoolBlocksJson;
import com.bankapp.owner.model.Block;
import com.google.gson.Gson;

public class ChainTest {



	
	public static void main(String[] args) {
		
		AccountDao accountDao = AccountDao.getinstance();
		String data = accountDao.sendBlockData();
		System.out.println(data);
		
		Block block = new Block(data, "0");
		System.out.println(block.hash);
	}
}
	
