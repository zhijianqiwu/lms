package com.lms.data;


import lombok.Data;

@Data
public class Result<T> {
    private Integer code;//返回码，0成功 -1失败
    private String message;//返回描述
    private Long count;//分页查询的总记录数
    private T data;//数据

    private Result(){}
    private Result(Integer code, String msg, Long count, T data) {
        this.code = code;
        this.message = msg;
        this.count = count;
        this.data = data;
    }


    public static Result<Object> success(){
        return new Result(0,"成功",null,null);

    }
    public static Result<Object> success(String msg){
        return new Result(0,msg,null,null);

    }
    public static Result<Object> success( Long count, Object data){
        return new Result(0,"成功",count,data);

    }

    public static Result<Object> fail(String msg){
        return new Result(-1,msg,null,null);

    }
    public static Result<Object> fail(){
        return new Result(-1,null,null,null);

    }


}
