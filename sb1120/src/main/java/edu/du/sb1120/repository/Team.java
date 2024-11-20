package edu.du.sb1120.repository;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
@Getter
@NoArgsConstructor
@ToString
public class Team {

    @Id
    private Integer id;
    private String teamName;

    @DBRef
    @ToString.Exclude
    private List<Member> memberList = new ArrayList<>();

    @Builder
    public Team(Integer id, String teamName) {
        this.id = id;
        this.teamName = teamName;
    }

    public void addMember(Member member) {
        memberList.add(member);
    }
}
