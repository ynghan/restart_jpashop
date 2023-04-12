package jpabook.jpashop.repository;


import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item){
        if(item.getId() == null) {
            em.persist(item); //새로운 엔티티를 넣는 의미 (상품 등록)
        } else {
            em.merge(item); //update와 비슷한 의미 (상품 수정)
        }
    }

    // 상품 조회(단건 조회, 전체 조회)
    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findAll() { //모든 상품을 조회할 경우에는 JPQL을 사용해야 한다.
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }

}

