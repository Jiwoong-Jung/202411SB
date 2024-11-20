package edu.du.sb1120;

import edu.du.sb1120.repository.Member;
import edu.du.sb1120.repository.MemberRepository;
import edu.du.sb1120.repository.Team;
import edu.du.sb1120.repository.TeamRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Sb1120ApplicationTests {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    TeamRepository teamRepository;

    @Test
    public void test1() {
        Team team = Team.builder().id(1).teamName("서울").build();
        Member member = Member.builder().id(1).name("박지성").team(team).build();
        team.addMember(member);
        memberRepository.save(member);
        teamRepository.save(team);
        System.out.println("------------------------입력완료");
    }

}
