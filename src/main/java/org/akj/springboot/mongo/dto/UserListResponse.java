package org.akj.springboot.mongo.dto;

import lombok.Data;
import org.akj.springboot.common.exception.BaseResponse;
import org.akj.springboot.mongo.bean.User;

import java.util.List;

@Data
public class UserListResponse extends BaseResponse {
    private Pagination pagination;

    private List<User> users;
}
