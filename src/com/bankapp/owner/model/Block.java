package com.bankapp.owner.model;

import java.util.Date;

import com.bankapp.owner.util.SHA256;



public class Block {

	public String hash;
	public String previousHash;
	private String data;
	private long timestamp;
	private int nonce;
	
	public Block(String data, String proviousHash) {
		this.data=data;  //거래데이터를 제이슨화 시켜서 넣기
		this.previousHash = proviousHash;  //이전해쉬
		this.timestamp = new Date().getTime();  //시간
		this.hash = calculateHash();   //이전해쉬와 시간과 넌스, 데이터를 합친 해쉬
	}
	public String calculateHash() {
		String calculatehash = SHA256.encodeSha256(
				previousHash+
				Long.toString(timestamp)+
				Integer.toString(nonce)+
				data
				);
		return calculatehash;
	}
		//채굴 메서드는 추후에!
		//오늘은 블록을 만들어보는거 까지 연습을 했으니 내일은 더해봅시다!
	
	}
	
	

