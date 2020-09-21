package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Transactional
// 스프링 컨테이너에 MemberService를 등록해준다
public class MemberService {

    private final MemberRepository memberRepository;


    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    //회원가입
    public Long join(Member member){
        //같은 이름이 이쓴ㄴ 중복 회원X
       // Optional<Member> result = memberRepository.findByName(member.getName()); Optional로 반환하는 것은 별로 좋지 않다.
       /*
        memberRepository.findByName(member.getName()).ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다."); // 그래서 이렇게
        });
        */
        //result.orElseGet() result가 값이 있으면 꺼내 Optional이라는 것을 사용하면 Optional의 메소드를 사용할 수 있다. get메소드를 이용해서 값을 꺼낼수 있다.
       // result.ifPresent(m ->{ //ifPresent는 result 널이 아니고 값이 있으면 이 로직이 동작
         //   throw new IllegalStateException("이미 존재하는 회원입니다.")
        //});
        validateDuplicateMember(member); // 중복 회원검증

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()).ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다."); // 그래서 이렇게
        });
    }

    //전체 회원 조회
    public List<Member> findMember(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }


}
