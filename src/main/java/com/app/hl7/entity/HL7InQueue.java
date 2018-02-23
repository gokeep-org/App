//package com.app.hl7.entity;
//
//import com.google.gson.annotations.SerializedName;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "hl7_in_queue")
//public class HL7InQueue extends HL7QueueItem {
//	@Id
//	@GeneratedValue(strategy= GenerationType.AUTO)
//	@SerializedName("hl7_in_queue_id")
//	private Integer id;
//	private static final long serialVersionUID = 8882704913734764446L;
//
//	private Integer hl7InQueueId;
//
//	private String errorMessage;
//
//	private Integer messageState;
//
//	/**
//	 * Default constructor
//	 */
//	public HL7InQueue() {
//	}
//	/**
//	 * @return Returns the hl7InQueueId.
//	 */
//	public Integer getHL7InQueueId() {
//		return hl7InQueueId;
//	}
//
//	/**
//	 * @param hl7InQueueId The hl7InQueueId to set.
//	 */
//	public void setHL7InQueueId(Integer hl7InQueueId) {
//		this.hl7InQueueId = hl7InQueueId;
//	}
//
//	/**
//	 * @return Returns the errorMessage.
//	 * @since 1.5
//	 */
//	public String getErrorMessage() {
//		return errorMessage;
//	}
//
//	/**
//	 * @param errorMessage The errorMessage to set.
//	 * @since 1.5
//	 */
//	public void setErrorMessage(String errorMessage) {
//		this.errorMessage = errorMessage;
//	}
//
//
//	public Integer getMessageState() {
//		return messageState;
//	}
//
//	public void setMessageState(Integer messageState) {
//		this.messageState = messageState;
//	}
//}
