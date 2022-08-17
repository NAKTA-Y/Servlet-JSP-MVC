package hello.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberRepositoryTest {

    @Autowired MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    public void save() {
        //given
        Member member = new Member("hello", 20);

        //when
        memberRepository.save(member);

        //then
        Member findMember = memberRepository.findById(member.getId());
        assertThat(findMember).isEqualTo(member);
    }

    @Test
    public void findAll() {
        //given
        Member member1 = new Member("test1", 10);
        Member member2 = new Member("test2", 20);

        memberRepository.save(member1);
        memberRepository.save(member2);

        //when
        List<Member> memberList = memberRepository.findAll();

        //then
        assertThat(memberList.size()).isEqualTo(2);
        assertThat(memberList).contains(member1, member2);
    }
}