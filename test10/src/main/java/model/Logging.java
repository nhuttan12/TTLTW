package model;


public  class Logging {
	private int id;
	private String time;
	private String IP;
	private String level;//level cua log( 
	private String message;//noi dung cua log
	private Object pre_value;//giatritruocdo
	private Object current_value;//giatrihientai
	public Logging() {
		
	}
	public Logging(int id,String time, String iP, String level, String message, Object pre_value, Object current_value) {
		super();
		this.id = id;
		this.time=time;
		this.IP = iP;
		this.level = level;
		this.message = message;
		this.pre_value = pre_value;
		this.current_value = current_value;
	}
	
	public Logging(String time, String iP, String message) {
		super();
		this.time = time;
		IP = iP;
		this.message = message;
	}
	
	public Logging(String time, String iP, String message, Object pre_value, Object current_value) {
		super();
		this.time = time;
		IP = iP;
		this.message = message;
		this.pre_value = pre_value;
		this.current_value = current_value;
	}
	
	public Logging(String time, String iP, String level, String message) {
		super();
		this.time = time;
		IP = iP;
		this.level = level;
		this.message = message;
	}
	
	public Logging(String time, String iP, String level, String message, Object pre_value,
			Object current_value) {
		super();
		this.time = time;
		IP = iP;
		this.level = level;
		this.message = message;
		this.pre_value = pre_value;
		this.current_value = current_value;
	}
	
	//phuongthuc insertLog
	public Logging( String level, String message, Object pre_value, Object current_value) {
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
	public Object getPre_value() {
		return pre_value;
	}
	public void setPre_value(Object pre_value) {
		this.pre_value = pre_value;
	}
	public Object getCurrent_value() {
		return current_value;
	}
	public void setCurrent_value(Object current_value) {
		this.current_value = current_value;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "Logging [id=" + id + ", time=" + time + ", IP=" + IP + ", level=" + level + ", message=" + message
				+ ", pre_value=" + pre_value + ", current_value=" + current_value + "]";
	}
	
	
	

}
