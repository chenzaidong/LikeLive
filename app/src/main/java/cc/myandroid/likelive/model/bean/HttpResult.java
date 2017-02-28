package cc.myandroid.likelive.model.bean;

/**
 * Created by chenzd on 2017/2/19.
 * 用于网络请求返回对象包装类
 */

public class HttpResult<T> {
    private int result;
    private String message;
    private T data;

    public HttpResult() {
    }

    public HttpResult(int result, String message, T data) {
        this.result = result;
        this.message = message;
        this.data = data;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
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

    @Override
    public String toString() {
        return "HttpResult{" +
                "result=" + result +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
