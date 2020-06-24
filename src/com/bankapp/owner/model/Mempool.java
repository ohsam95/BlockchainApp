package com.bankapp.owner.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Mempool {

	private int id;
	private String receiver;
	private int sendAmount;
	private String sender;
	private String hash;
	private Timestamp createDate;
	
}
