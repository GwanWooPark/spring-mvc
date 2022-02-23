package hello.itemservice.domain.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

//    @PostMapping("/add")
    public String addItemV1(@RequestParam String itemName,
                          @RequestParam Integer price,
                          @RequestParam Integer quantity,
                          Model model) {

        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);

        itemRepository.save(item);

        model.addAttribute("item", item);

        return "/basic/item";
    }


//    @PostMapping("/add")
    public String addItemV2(@ModelAttribute("item") Item item, Model model) {

        itemRepository.save(item);
//        model.addAttribute("item", item); // 자동 추가, 생략 가능
//        ModelAttribute에 담은 이름으로 model.addAttribute에 담긴다.

        return "/basic/item";
    }

    //    @PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item, Model model) {

        itemRepository.save(item);
        return "/basic/item";
    }

//    @PostMapping("/add")
    public String addItemV4(Item item) {

        // @ModelAttribute 생략 시 (default) : Item -> item
        itemRepository.save(item);
//        model.addAttribute("item", item); // 자동 추가, 생략 가능
//        ModelAttribute에 담은 이름으로 model.addAttribute에 담긴다.
        return "/basic/item";
    }


    /**
     * PRG - Post/Redirect/Get
     */
//    @PostMapping("/add")
    public String addItemV5(Item item) {

        itemRepository.save(item);
        // 아 방법의 문제점 경로에서는 인코딩이 필요함
        return "redirect:/basic/items/" + item.getId();
    }

    /**
     * redirectAttribute
     */
    @PostMapping("/add")
    public String addItemV6(Item item, RedirectAttributes redirectAttributes) {

        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);

        // redirectAttributes.addAttribute에 추가한 itemId 가 '인코딩' 되어서 치환됨 + PathVariable처럼 URL에 넣기 가능
        // status는 쿼리 파라미터 형식으로 들어간다.
        return "redirect:/basic/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String editItem(@PathVariable long itemId, Item item) {

        itemRepository.update(itemId, item);

        return "redirect:/basic/items/{itemId}";
    }
    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));
    }
}
