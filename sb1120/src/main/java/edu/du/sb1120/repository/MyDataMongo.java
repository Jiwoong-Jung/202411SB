package edu.du.sb1120.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class MyDataMongo {
    @Id
    private String id;

    private String name;
    private String memo;
    private Date date;

    public MyDataMongo(String name, String memo) {
        this.name = name;
        this.memo = memo;
        this.date = new Date();
    }
}
