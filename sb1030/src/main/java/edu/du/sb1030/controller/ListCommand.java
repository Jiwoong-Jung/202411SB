package edu.du.sb1030.controller;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

@ToString
public class ListCommand {

	@DateTimeFormat(pattern = "yyyyMM")
	private LocalDateTime from;
	@DateTimeFormat(pattern = "yyyyMM")
	private LocalDateTime to;

	public LocalDateTime getFrom() {
		return from;
	}

	public void setFrom(LocalDateTime from) {
		this.from = from;
	}

	public LocalDateTime getTo() {
		return to;
	}

	public void setTo(LocalDateTime to) {
		this.to = to;
	}

}
