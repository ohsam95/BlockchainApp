package com.bankapp.owner.action;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bankapp.owner.dao.AccountDao;
import com.bankapp.owner.dto.MempoolJson;
import com.bankapp.owner.model.Account;
import com.bankapp.owner.model.CheckSend;
import com.bankapp.owner.util.SHA256;
import com.bankapp.owner.util.Script;
import com.google.gson.Gson;



public class SendProcAction implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 유효성 검사
		HttpSession session = request.getSession();
		if
		(
				request.getParameter("receiver").equals("")||
				request.getParameter("receiver")==null||	
				request.getParameter("sendAmount").equals("")||
				request.getParameter("sendAmount")==null||		
				request.getParameter("pwd").equals("")||
				request.getParameter("pwd")==null	
				) {
			Script.back("누락된 항목이 있습니다.", response);
			return;
		}

		//파라메터 받기
		String receiver = request.getParameter("receiver");
		int sendAmount = Integer.parseInt(request.getParameter("sendAmount"));
		String pwd = request.getParameter("pwd");
		String phone = request.getParameter("phone");
		Date date = new Date();
		String time = date.toString();
		System.out.println(time);

		//이체 내역의 보안을 위해 해쉬화하기		
			MempoolJson mempoolJson = MempoolJson.builder()
					.receiver(receiver)
					.sendAmount(sendAmount)
					.phone(phone)
					.time(time)
					.build();
		Gson gson = new Gson();
		String sendInfo = gson.toJson(mempoolJson);
		response.setCharacterEncoding("UTF-8");
		String hash = SHA256.encodeSha256(sendInfo);
		
		
		// db연결
		AccountDao accountDao = AccountDao.getinstance();
		CheckSend check = accountDao.checkPhone(receiver);
		Account result = accountDao.pwdConfirm(pwd,phone);
		
		
		if (check==null) {
			Script.back("없는 계좌번호 입니다. 다시 한번 확인 부탁드립니다.", response);
		}else {
			//페이지 이동//
			if (result==null) {
				Script.back("비밀번호가 틀립니다.", response);
			}else {
				accountDao.recevie(sendAmount, receiver);
				accountDao.send(sendAmount, phone);
				
				accountDao.insertMempool(receiver, sendAmount, phone,hash);
				accountDao.insertBlockMempool(receiver, sendAmount, phone, hash);
				
				Account principal = accountDao.find(phone);
				session.setAttribute("principal", principal);
				Script.href("이체가 성공했습니다.", "/owner/account?cmd=home", response);			
			}
		}
		
	}
}
