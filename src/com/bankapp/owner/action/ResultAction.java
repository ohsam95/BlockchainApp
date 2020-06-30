package com.bankapp.owner.action;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bankapp.owner.dao.AccountDao;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ResultAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		String hash = request.getParameter("prvHash");
		String previousHash = request.getParameter("nodePreviousHash");
		String data = request.getParameter("nodeData");
		String timestamp = request.getParameter("nodeTimestamp");
		int nonce = Integer.parseInt(request.getParameter("nodeNonce"));
		int nodeNumber = Integer.parseInt(request.getParameter("nodeNumber"));
		
		AccountDao accountDao = AccountDao.getinstance();
		// 블록을 db에 넣는 메서드
		accountDao.insertBlockList(hash, previousHash, data, timestamp, nonce, nodeNumber);
	
		//경쟁에서 이긴 노드가 1000원을 가져가는 메서드
		if (nodeNumber == 1) {
			accountDao.charge(1000, "01011111111");
			System.out.println("1번 노드가 채굴경쟁에서 이겼습니다.");
		}else if (nodeNumber==2) {
			accountDao.charge(1000, "01022222222");
			System.out.println("2번 노드가 채굴경쟁에서 이겼습니다.");
		}else if (nodeNumber==3) {
			accountDao.charge(1000, "01033333333");
			System.out.println("3번 노드가 채굴경쟁에서 이겼습니다.");
		}
		
		
		
		
		
	}

}
