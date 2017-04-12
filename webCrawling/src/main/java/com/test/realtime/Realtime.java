package com.test.realtime;

import java.util.Date;

public class Realtime {
	private int realtime_no;
	private String realtime_keyword;
	private String realtime_url;
	private int realtime_rank;
	private Date realtime_timestamp;
	
	public Realtime() {
		super();
	}
	
	public Realtime(String realtime_keyword, String realtime_url, int realtime_rank, Date realtime_timestamp) {
		super();
		this.realtime_keyword = realtime_keyword;
		this.realtime_url = realtime_url;
		this.realtime_rank = realtime_rank;
		this.realtime_timestamp = realtime_timestamp;
	}

	public Realtime(String realtime_keyword, String realtime_url, int realtime_rank) {
		super();
		this.realtime_keyword = realtime_keyword;
		this.realtime_url = realtime_url;
		this.realtime_rank = realtime_rank;
	}
	public int getRealtime_no() {
		return realtime_no;
	}
	public void setRealtime_no(int realtime_no) {
		this.realtime_no = realtime_no;
	}
	public String getRealtime_keyword() {
		return realtime_keyword;
	}
	public void setRealtime_keyword(String realtime_keyword) {
		this.realtime_keyword = realtime_keyword;
	}
	public String getRealtime_url() {
		return realtime_url;
	}
	public void setRealtime_url(String realtime_url) {
		this.realtime_url = realtime_url;
	}
	public int getRealtime_rank() {
		return realtime_rank;
	}
	public void setRealtime_rank(int realtime_rank) {
		this.realtime_rank = realtime_rank;
	}
	public Date getRealtime_timestamp() {
		return realtime_timestamp;
	}
	public void setRealtime_timestamp(Date realtime_timestamp) {
		this.realtime_timestamp = realtime_timestamp;
	}
	
	
}
