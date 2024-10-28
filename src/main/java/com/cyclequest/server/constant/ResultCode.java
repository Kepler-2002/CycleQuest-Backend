package com.cyclequest.server.constant;

public enum ResultCode {
    SUCCESS(200, "success"),
    PARAM_ERROR(400, "参数错误"),
    UNAUTHORIZED(401, "未授权"),
    NOT_FOUND(404, "资源不存在"),
    INTERNAL_ERROR(500, "服务器内部错误"),
    
    // 业务错误码 (1000-9999)
    USER_ALREADY_EXISTS(1001, "用户已存在"),
    EMAIL_ALREADY_EXISTS(1002, "邮箱已被注册"),
    USER_NOT_FOUND(1003, "用户不存在"),
    SETTINGS_NOT_FOUND(1004, "用户设置不存在"),
    INVALID_LANGUAGE(1005, "不支持的语言设置"),
    ACHIEVEMENT_NOT_FOUND(1006, "成就不存在"),
    ACHIEVEMENT_ALREADY_UNLOCKED(1007, "成就已解锁"),
    MAX_DISPLAYED_ACHIEVEMENTS_REACHED(1008, "已达到最大可展示成就数量"),
    ACHIEVEMENT_NOT_UNLOCKED(1009, "成就未解锁"),
    INVALID_DISPLAY_ORDER(1010, "无效的展示顺序"),
    INVALID_PROGRESS_VALUE(1011, "进度值必须在0-100之间"),
    MAX_ACHIEVEMENTS_REACHED(1012, "已达到最大成就数量");

    private final int code;
    private final String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }


}
