package cn.zealon.bookstore.common.result;

/**
 * 
 * http 状态码
 * ----------------------------------------------------------------------------
 * 200 OK - [GET]：服务器成功返回用户请求的数据，该操作是幂等的（Idempotent）。
 * 400 INVALID REQUEST - [POST/PUT/PATCH]：用户发出的请求有错误，服务器没有进行新建或修改数据的操作，该操作是幂等的。
 * 401 Unauthorized - [*]：表示用户没有权限（令牌、用户名、密码错误）。
 * 403 Forbidden - [*] 表示用户得到授权（与401错误相对），但是访问是被禁止的。
 * 404 NOT FOUND - [*]：用户发出的请求针对的是不存在的记录，服务器没有进行操作，该操作是幂等的。
 * 406 Not Acceptable - [GET]：用户请求的格式不可得（比如用户请求JSON格式，但是只有XML格式）。
 * 410 Gone -[GET]：用户请求的资源被永久删除，且不会再得到的。
 * 422 Unprocesable entity - [POST/PUT/PATCH] 当创建一个对象时，发生一个验证错误。
 * 500 INTERNAL SERVER ERROR - [*]：服务器发生错误，用户将无法判断发出的请求是否成功。
 * 600 UN_KNOW_ERROR - 未知错误
 * ----------------------------------------------------------------------------
 */
public enum HttpCodeEnum {

    OK(200,"操作成功"),
    INVALID_REQUEST(400,"参数错误"),
    UNAUTHORIZED(401,"没有权限"),
    FORBIDDEN(403,"禁止访问"),
    NOT_FOUND(404,"资源不存在"),
    NOT_ACCEPTABLE(406,"请求的格式不正确"),
    GONE(410,"数据被删除"),
    UNPROCESABLE_ENTITY(422,"参数验证错误"),
    INTERNAL_SERVER_ERROR(500,"服务器发生错误"),
    UN_KNOW_ERROR(500,"未知错误"),
    FAIL(501,"操作失败"),

    MODEL_NOT_EXIST(1000, "模型不存在"),
    VERIFICATION_FAILED(1001, "业务逻辑验证未通过"),
    USERNAME_OR_PASSWORD_ERR(2000,"用户未登录或token已失效"),
    DELETE_DEFAULT_PHOTO_ERR(2001,"默认头像不可删除"),
    TOKEN_ERR(200, "token无效");

    private final int code;
    private final String message;

    HttpCodeEnum(final int code, final String message){
        this.code=code;
        this.message=message;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

}
