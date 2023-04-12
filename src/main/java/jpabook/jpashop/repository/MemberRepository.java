package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import java.util.List;

@Repository //컴포넌트스캔의 대상이 되어 자동으로 스프링빈으로 관리된다.
@RequiredArgsConstructor //lombok 기능. final 필드에 대한 생성자와 생성자 인젝션을 만들어준다.
public class MemberRepository {

    //@PersistenceContext //스프링이 EntityManager를 만들어서 자동으로 주입해준다.
    //@Autowired //스프링부트가 이 어노테이션을 사용해도 되도록 지원해준다.
    private final EntityManager em;

//    @PersistenceUnit
//    private EntityManagerFactory emf; //엔티티 매니저 팩토리를 주입받을 수 있다.

    public void save(Member member) { /** 저장 **/
        em.persist(member); //jpa가 member를 저장한다.
    }

    public Member findOne(Long id) { /** 조회 **/
        return em.find(Member.class, id); //id에 대한 member를 반환해준다.
    }

    public List<Member> findAll() { /**리스트 조회 **/
         return em.createQuery("select m from Member m", Member.class)
                 .getResultList();  //JPQL로 쿼리를 날려 리스트를 반환한다.
    }

    //SQL은 테이블 대상으로 쿼리를 하지만 JPQL은 엔티티 객체에 대한 쿼리를 한다.

    public List<Member> findByName(String name) { // name에 대한 member_list를 반환한다.
        return em.createQuery("select m from Member m where m.name = :name", Member.class) // :name이 파라미터에 바인딩되는 변수
                .setParameter("name", name)
                .getResultList();
    }
}
