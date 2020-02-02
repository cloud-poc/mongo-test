package org.akj.springboot.mongo.bean;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@Document(value = "users")
public class User implements Serializable {
    @Id
    private String id;

    private UserInfo userInfo;

    @NotEmpty
    @Indexed(unique = true)
    private String userName;

    @NotNull
    private String authType;

//    @NotNull
    private String password;

    private String token;

    @JsonIgnore
    private String schemaVersion;

    @CreatedDate
    @JsonIgnore
    private Date createDate;

    @LastModifiedDate
    @JsonIgnore
    private Date lastUpdateDate;
}
