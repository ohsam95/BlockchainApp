package com.bankapp.owner.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SendLogDto {

	private String receiver;
	private int sendAmount;
	private String sender;
	private Timestamp createDate;
}
