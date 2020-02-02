package org.akj.springboot.mongo.controller;

import lombok.extern.slf4j.Slf4j;
import org.akj.springboot.mongo.bean.User;
import org.akj.springboot.mongo.dto.Pagination;
import org.akj.springboot.mongo.dto.UserListRequest;
import org.akj.springboot.mongo.dto.UserListResponse;
import org.akj.springboot.mongo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public User createUser(@Valid @RequestBody User user) {
        return userService.create(user);
    }

    @GetMapping
    public User findUserByName(@RequestParam("userName") @NotNull @NotEmpty String userName) {
        return userService.findByUserName(userName);
    }

    @GetMapping("/{id}")
    public User findUserById(@PathVariable("id") @NotNull @NotEmpty String id) {
        return userService.findById(id);
    }

    @PostMapping("/filter")
    public UserListResponse findAll(@RequestBody @Valid UserListRequest request) {
        log.info("enter findAll, pagination is {}", request.getPagination());
        UserListResponse response = new UserListResponse();
        Pagination pagination = request.getPagination();

        Page<User> all = userService.findAll(pagination.getPageNumber(),
                pagination.getPageSize());
        if (all != null) {
            pagination.setHasNext(false);
            if (all.hasNext()) {
                pagination.setHasNext(true);
            }
            pagination.setTotalCount(all.getTotalElements());
            response.setUsers(all.getContent());
            log.debug("{} records been loaded, total record count is {}", all.getNumberOfElements(),
                    all.getTotalElements());
        }

        response.setPagination(pagination);

        return response;
    }
}
