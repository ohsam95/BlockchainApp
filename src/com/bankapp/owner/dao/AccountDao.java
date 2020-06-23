package com.bankapp.owner.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.bankapp.owner.db.DBConn;
import com.bankapp.owner.model.Account;



public class AccountDao {

	private static final String TAG = "AccountDao : ";
	private static AccountDao instance = new AccountDao();
	private AccountDao() {}
	public static AccountDao getinstance() {
		return instance;
	}
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	public int join(Account account) {
	final String SQL = "insert into account(id,name,pwd,phone,amount) values(account_seq.nextval,?,?,?,0)";
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			//물음표 완성
			pstmt.setString(1, account.getName());
			pstmt.setString(2, account.getPwd());
			pstmt.setString(3, account.getPhone());
			
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG+"save : "+e.getMessage());
		}finally {
			DBConn.close(conn, pstmt);
		}
		return -1;
	}
	public Account login(String name, String pwd) {
		final String SQL = "select id,name,phone,amount from account where name = ? and pwd = ?";
			Account account = null;
			try {
				conn = DBConn.getConnection();
				pstmt = conn.prepareStatement(SQL);
				//물음표 완성
				pstmt.setString(1, name);
				pstmt.setString(2, pwd);
				
				rs = pstmt.executeQuery();
				if (rs.next()) {
					account = new Account();
					account.setId(rs.getInt("id"));
					account.setName(rs.getString("name"));
					account.setPhone(rs.getString("phone"));
					account.setAmount(rs.getInt("amount"));
				}
				return account;

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(TAG+"save : "+e.getMessage());
			}finally {
				DBConn.close(conn, pstmt);
			}
			return null;
		}
	public int charge(int amount, String phone) {
		final String SQL = "update account set amount =amount+? where phone = ? ";
			try {
				conn = DBConn.getConnection();
				pstmt = conn.prepareStatement(SQL);
				//물음표 완성
				pstmt.setInt(1, amount);
				pstmt.setString(2, phone);
				
				return pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(TAG+"save : "+e.getMessage());
			}finally {
				DBConn.close(conn, pstmt);
			}
			return -1;
		}
	public Account find(String phone) {
		final String SQL = "select id,name,phone,amount from account where phone = ?";
			Account account = null;
			try {
				conn = DBConn.getConnection();
				pstmt = conn.prepareStatement(SQL);
				//물음표 완성
				pstmt.setString(1, phone);
				
				rs = pstmt.executeQuery();
				if (rs.next()) {
					account = new Account();
					account.setId(rs.getInt("id"));
					account.setName(rs.getString("name"));
					account.setPhone(rs.getString("phone"));
					account.setAmount(rs.getInt("amount"));
				}
				return account;

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(TAG+"save : "+e.getMessage());
			}finally {
				DBConn.close(conn, pstmt);
			}
			return null;
		}
}
