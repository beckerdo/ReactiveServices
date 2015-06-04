package com.example.rest.example.model;

/**
 * This enumerates states that the object may take.
 * 
 * @author <a href="mailto:dan@danbecker.info">Dan Becker</a>
 */
public enum MessageState {
	UNKNOWN(    0, "unknown" ),
	ERROR(    1, "error" ),
	SUBMITTED( 100, "submitted" ),
	PROCESSING( 101, "processing" ),
	COMPLETED( 102, "completed" );

	protected int value;
	protected String type;

	private MessageState(int value, String type) {
		setValue(value);
		setType(type);
	}

	public int getValue() {
		return value;
	}

	void setValue(int value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	void setType(String type) {
		this.type = type;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder( "MessageState[");
		sb.append( value );
		sb.append( ", " );
		sb.append( type );
        sb.append( "]" );
        return sb.toString();
	}
}