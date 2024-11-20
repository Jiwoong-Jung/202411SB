package edu.du.sb1120.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class MyDataMongo {
    @Id
    private String id;

    private String name;
    private String memo;
    private LocalDateTime date;

    public MyDataMongo(String name, String memo) {
        this.name = name;
        this.memo = memo;
        this.date = LocalDateTime.now();
    }
}
