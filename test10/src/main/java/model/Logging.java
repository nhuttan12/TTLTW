package model;

import java.time.LocalDateTime;

public  class Logging {
	private int id;
	private LocalDateTime time;
	private String IP;
	private String level;//level cua log( 
	private String message;//noi dung cua log
	private String pre_value;//giatritruocdo
	private String current_value;//giatrihientai
	public Logging() {
		
	}
	public Logging(int id,LocalDateTime time, String iP, String level, String message, String pre_value, String current_value) {
		super();
		this.id = id;
		this.time=time;
		this.IP = iP;
		this.level = level;
		this.message = message;
		this.pre_value = pre_value;
		this.current_value = current_value;
	}
	
	public Logging(LocalDateTime time, String iP, String message) {
		super();
		this.time = time;
		IP = iP;
		this.message = message;
	}
	
	public Logging(LocalDateTime time, String iP, String message, String pre_value, String current_value) {
		super();
		this.time = time;
		IP = iP;
		this.message = message;
		this.pre_value = pre_value;
		this.current_value = current_value;
	}
	
	public Logging(LocalDateTime time, String iP, String level, String message) {
		super();
		this.time = time;
		IP = iP;
		this.level = level;
		this.message = message;
	}
	
	public Logging(LocalDateTime time, String iP, String level, String message, String pre_value,
			String current_value) {
		super();
		this.time = time;
		IP = iP;
		this.level = level;
		this.message = message;
		this.pre_value = pre_value;
		this.current_value = current_value;
	}
	
	//phuongthuc insertLog
	public Logging( String level, String message, String pre_value, String current_value) {
		super();

		this.level = level;
		this.message = message;
		this.pre_value = pre_value;
		this.current_value = current_value;
	}
	//phuongthuc insertLog
	public Logging( String level, String message) {
		super();
		this.level = level;
		this.message = message;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIP() {
		return IP;
	}
	public void setIP(String iP) {
		IP = iP;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getPre_value() {
		return pre_value;
	}
	public void setPre_value(String pre_value) {
		this.pre_value = pre_value;
	}
	public String getCurrent_value() {
		return current_value;
	}
	public void setCurrent_value(String current_value) {
		this.current_value = current_value;
	}
	public LocalDateTime getTime() {
		return time;
	}
	public void setTime(LocalDateTime time) {
		this.time = time;
	}
	
	

}
