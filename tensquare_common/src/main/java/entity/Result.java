package entity;

/**
 * @author zhoubing
 * @date 2021-05-23 16:16
 */
public class Result<T> {
    private boolean flag;//是否成功
    private Integer code;// 返回码
    private String message;//返回信息
    private T data;// 返回数据


    public static Result success(Object data){
        Result result = new Result();
        result.setCode(StatusCode.OK);
        result.setFlag(true);
        result.setData(data);
        return result;
    }

    public static Result fail(Object data){
        Result result = new Result();
        result.setCode(StatusCode.ERROR);
        result.setFlag(false);
        result.setData(data);
        return result;
    }

    public Result() {
    }

    public Result(boolean flag, Integer code, String message) {
        super();
        this.flag = flag;
        this.code = code;
        this.message = message;
    }

    public Result(boolean flag, Integer code, String message, T data) {
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
