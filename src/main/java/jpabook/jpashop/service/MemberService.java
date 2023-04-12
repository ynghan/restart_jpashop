package jpabook.jpashop.service;


import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service //컴포넌트스캔의 대상이 되어 자동으로 스프링 빈에 등록된다.
@Transactional(readOnly = true) //읽기 전용에서 최적화 가능.
//@AllArgsConstructor //lombok 기능. 필드에 대한 생성자와 생성자 인젝션을 만들어준다.
@RequiredArgsConstructor //lombok 기능. final 필드에 대한 생성자와 생성자 인젝션을 만들어준다.
public class MemberService {

    //@Autowired //memberRepository가 인젝션 된다.
    private final MemberRepository memberRepository; //변경할 일 없기때문에 final 권장.

/** @Autowired //요즘 권장하는 것이 생성자 인젝션이다. @Autowired 없어도 생성자가 하나일 경우 자동 인젝션 가능.
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
**/




    /**
     * 회원 가입
     */
    @Transactional //읽기 전용으로 하면 데이터 변경 불가 -> false
    public Long join(Member member) {
        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);

        return member.getId();
    }

    /*
        중복 검사
     */
    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
        //was가 db에 동시에 validateDuplicateMember를 통과하게 되면 중복의 가능성이 있다.
        //db에 유니크 제약조건 권장.
    }


    //회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }


    @Transactional
    public void update(Long id, String name) {
        Member member = memberRepository.findOne(id);
        member.setName(name);
    }
}
