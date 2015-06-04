package com.example.rest.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;
import java.util.Date;
import java.util.Random;

/**
 * Representation of a message.
 *
 * @author <a href="mailto:dan@danbecker.info">Dan Becker</a>
 */
@JsonPropertyOrder({
	"message",
    "dateCreated",
    "dateModified",
    "syncTime",
    "asyncTime",
    "state"
})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Message implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final Random random = new Random();
	
	@JsonProperty("message")
	protected String message;
 
	@JsonProperty("dateCreated")
    protected long dateCreated;
    @JsonProperty("dateModified")
    protected long dateModified;

    @JsonProperty("syncTime")
    protected long syncTime;
    @JsonProperty("asyncTime")
    protected long asyncTime;

    @JsonProperty("state")
    protected MessageState state;

    /** Default constructor */
    public Message() {
    	setMessage( "Message " + (random.nextInt( 9000 ) + 1000)); // 4 digit suffix
    	setDateCreated( new Date() ); // Set date created to now
    	setDateModified( new Date() ); // Set date modified to now
    	setAsyncTime(0);
    	setSyncTime(0);
    	setState( MessageState.UNKNOWN );
    }

    /** A utility constructor that simply calls the setters for the provided parameters. */
    public Message( String message, long createTime, long modifiedTime, long syncTime, long asyncTime, MessageState state) {
    	setMessage( message ); 
    	setDateCreated( new Date( createTime ) ); // Set date created to now
    	setDateModified( new Date( modifiedTime ) ); // Set date modified to now
    	setAsyncTime(asyncTime);
    	setSyncTime(syncTime);
    	setState( state );
    }

    @JsonProperty("dateCreated")
	public final Date getDateCreated() {
		return new Date( dateCreated );
	}
	public void setDateCreated(final Date dateCreated) {
		if ( null == dateCreated )
			this.dateCreated = 0;
		else
			this.dateCreated = dateCreated.getTime();
	}
    @JsonProperty("dateModified")
	public final Date getDateModified() {
		return new Date( dateModified );
	}
	public void setDateModified(final Date dateModified) {
		if ( null == dateModified )
			this.dateModified = 0;
		else
			this.dateModified = dateModified.getTime();
	}

	/**
	 * @return the message
	 */
    @JsonProperty("message")
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the syncTime
	 */
    @JsonProperty("syncTime")
	public long getSyncTime() {
		return syncTime;
	}

	/**
	 * @param syncTime the syncTime to set
	 */
	public void setSyncTime(long syncTime) {
		this.syncTime = syncTime;
	}

	/**
	 * @return the asyncTime
	 */
    @JsonProperty("asyncTime")
	public long getAsyncTime() {
		return asyncTime;
	}

	/**
	 * @param asyncTime the asyncTime to set
	 */
	public void setAsyncTime(long asyncTime) {
		this.asyncTime = asyncTime;
	}

	/**
	 * @return the state
	 */
    @JsonProperty("state")
	public MessageState getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(MessageState state) {
		this.state = state;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (asyncTime ^ (asyncTime >>> 32));
		result = prime * result + (int) (dateCreated ^ (dateCreated >>> 32));
		result = prime * result + (int) (dateModified ^ (dateModified >>> 32));
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + (int) (syncTime ^ (syncTime >>> 32));
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Message other = (Message) obj;
		if (asyncTime != other.asyncTime)
			return false;
		if (dateCreated != other.dateCreated)
			return false;
		if (dateModified != other.dateModified)
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (state != other.state)
			return false;
		if (syncTime != other.syncTime)
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Message [message=");
		builder.append(message);
		builder.append(", dateCreated=");
		builder.append(dateCreated);
		builder.append(", dateModified=");
		builder.append(dateModified);
		builder.append(", syncTime=");
		builder.append(syncTime);
		builder.append(", asyncTime=");
		builder.append(asyncTime);
		builder.append(", state=");
		builder.append(state);
		builder.append("]");
		return builder.toString();
	}
}
