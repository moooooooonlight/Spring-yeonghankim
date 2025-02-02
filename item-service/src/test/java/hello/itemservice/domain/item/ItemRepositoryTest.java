package hello.itemservice.domain.item;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach(){
        itemRepository.clearStore();
    }

    @Test
    void save(){
        //given
        Item item = new Item("A",10000,10);
        //when
        Item saveItem = itemRepository.save(item);

        //then
        Item findItem = itemRepository.findByid(item.getId());
        assertThat(findItem).isEqualTo(saveItem);
    }
    @Test
    void findAll(){
        //given
        Item item1 = new Item("A",10000,10);
        Item item2 = new Item("B",20000,5);

        itemRepository.save(item1);
        itemRepository.save(item2);

        //when
        List<Item> findList = itemRepository.findAll();

        //then
        assertThat(findList.size()).isEqualTo(2);
        assertThat(findList).contains(item1, item2);
    }
    @Test
    void updateItem(){
        //given
        Item item = new Item("A",10000,10);
        Item saveItem = itemRepository.save(item);
        Long itemId = saveItem.getId();

        //when
        Item updateParam = new Item("A",10000,5);
        itemRepository.updata(itemId,updateParam);

        //then
        Item findItem = itemRepository.findByid(itemId);
        assertThat(findItem.getItemName()).isEqualTo(updateParam.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(updateParam.getPrice());
    }

}