package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional //readOnly는 수정이 안되기 때문에 따로 표기해야함.
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    @Transactional
    public void updateItem(Long itemId, String name, int price, int stockQuantity) { //가장 좋은 해결 방법 : 엔티티를 변경할 때는 항상 merge보다는 변경 감지를 사용하자.
        Item findItem = itemRepository.findOne(itemId);
        findItem.setName(name);
        findItem.setPrice(price);
        findItem.setStockQuantity(stockQuantity); //이런식으로 set을 사용하는게 아니라 의미있는 이름으로 메소드를 지정하여 사용해야한다.
    }


    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long Itemid) {
        return itemRepository.findOne(Itemid);
    }

}

//상품 서비스는 상품 리포지토리에 위임만 해주는 클래스이다.
