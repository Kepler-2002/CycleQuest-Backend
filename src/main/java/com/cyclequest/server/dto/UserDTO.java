// dto/UserDTO.java
package com.cyclequest.server.dto;

import com.cyclequest.server.constant.UserStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

/**
 * 用户相关的数据传输对象
 */
public class UserDTO {

    public interface Create {}
    public interface Update {}
    public interface PasswordUpdate {}

    /**
     * 用户注册请求
     */
    @Data
    public static class RegisterRequest {
        @NotBlank(message = "用户名不能为空", groups = Create.class)
        private String username;

        @Email(message = "邮箱格式不正确", groups = {Create.class, Update.class})
        @NotBlank(message = "邮箱不能为空", groups = Create.class)
        private String email;

        @NotBlank(message = "密码不能为空", groups = Create.class)
        @Size(min = 6, max = 20, message = "密码长度必须在6-20之间", groups = {Create.class, PasswordUpdate.class})
        private String password;
    }

    @Data
    public static class RegisterResponse {
        private Long id;
        private String username;
        private String email;
    }

    @Data
    public static class UserInfo {
        private Long id;
        private String username;
        private String email;
        private String avatarUrl;
        private UserStatus status;
        private Integer totalRides;
        private Float totalDistance;
        private Long totalRideTime;
        private Long createdAt;
        private List<AchievementDTO.UserAchievementInfo> achievements;
        private List<AchievementDTO> displayedAchievements;
    }
    // 可以继续添加其他用户相关的 DTO
    @Data
    public static class UpdateProfileRequest {
        @Size(max = 200, groups = Update.class)
        private String avatarUrl;
        
        @Size(min = 6, max = 20, message = "密码长度必须在6-20之间", groups = PasswordUpdate.class)
        private String newPassword;
    }
}
