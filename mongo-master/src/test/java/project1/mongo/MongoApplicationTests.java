package project1.mongo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project1.mongo.repository.Member;
import project1.mongo.repository.MemberRepository;
import project1.mongo.repository.Team;
import project1.mongo.repository.TeamRepository;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class MongoApplicationTests {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    TeamRepository teamRepository;
    @Test
    void contextLoads() {
    }

    @Test
    public void test1() {
        Team team = Team.builder().id(1).teamName("서울").build();
        Member member = Member.builder().id(1).name("박지성").team(team).build();
        team.addMember(member);
        memberRepository.save(member);
        teamRepository.save(team);
        System.out.println("------------------------입력완료");
    }

    @Test
    public void test2() {
        Team team1 = Team.builder().id(2).teamName("부산").build();
        Team team2 = Team.builder().id(3).teamName("대전").build();

        Member member1 = Member.builder().id(2).name("손흥민").team(team1).build();
        Member member2 = Member.builder().id(3).name("홍길동").team(team1).build();

        memberRepository.save(member1);
        memberRepository.save(member2);

        team1.addMember(member1);
        team1.addMember(member2);

        teamRepository.save(team1);
        teamRepository.save(team2);
    }

    @Test
    public void search1() {
        Optional<Member> member = memberRepository.getMemberBy(2);
        System.out.println(member.get().getTeam());
        List<Member> list = member.get().getTeam().getMemberList();
        for (Member member1 : list) {
            System.out.println(member1);
        }
    }


}
