package edu.du.sb1120.repository;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
@Getter
@NoArgsConstructor
@ToString
public class Member {
    @Id
    private Integer id;
    private String name;

    @DBRef
    private Team team;

    @Builder
    public Member(Integer id, String name, Team team) {
        this.id = id;
        this.name = name;
        this.team = team;
    }
}
