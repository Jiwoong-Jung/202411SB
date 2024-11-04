package edu.du.sb1030.controller;

import java.time.LocalDateTime;

import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

@NoArgsConstructor
@ToString
public class ListCommand {

//	@DateTimeFormat(pattern = "yyyyMMddHH")
//	@DateTimeFormat(pattern = "yyyyMMdd")
@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime from;
//	@DateTimeFormat(pattern = "yyyyMMddHH")
//	@DateTimeFormat(pattern = "yyyyMMdd")
@DateTimeFormat(pattern = "yyyy-MM-dd")
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
