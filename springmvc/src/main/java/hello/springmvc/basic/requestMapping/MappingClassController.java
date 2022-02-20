package hello.springmvc.basic.requestMapping;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mapping/users")
public class MappingClassController {


    /**
     * GET /mapping/users
     * POST /mapping/users
     * GET /mapping/users/{userId}
     * PATCH /mapping/users/{userId}
     * DELETE /mapping/users/{userId}
     */
    @GetMapping
    public String user() {
        return "get user";
    }

    @PostMapping
    public String addUser() {
        return "add users";
    }

    @GetMapping("/{userId}")
    public String findUser(@PathVariable String userId) {
        return "find user";
    }

    @PatchMapping("/{userId}")
    public String updateUser(@PathVariable String userId) {
        return "update userId=" + userId;
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable String userId) {
        return "delete userId=" + userId;
    }
}
