package com.job;

import java.sql.Timestamp;
import java.time.Instant;

public class Test {

	public static void main(String[] args) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		 Instant instant = timestamp.toInstant();
	        System.out.println(instant);

	}

}
