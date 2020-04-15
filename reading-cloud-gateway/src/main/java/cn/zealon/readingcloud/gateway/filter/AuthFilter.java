package cn.zealon.readingcloud.gateway.filter;

import cn.zealon.readingcloud.common.pojo.account.User;
import cn.zealon.readingcloud.common.result.HttpCodeEnum;
import cn.zealon.readingcloud.common.result.Result;
import cn.zealon.readingcloud.gateway.common.utils.JwtUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

/**
 * 身份认证过滤器
 * @author: zealon
 * @since: 2020/4/12
 */
@Component
public class AuthFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        Set<String> whiteList = this.getWhiteList();
        String path = exchange.getRequest().getPath().toString();
        if (path.equals("/account/user/login") || path.equals("/account/user/register")) {
            // 登录、注册接口放行
            return chain.filter(exchange);
        }

        String[] segments = path.split("/");
        if (!whiteList.contains(segments[1])) {
            // 认证
            String token = exchange.getRequest().getHeaders().getFirst("token");
            Result<User> result = JwtUtil.validationToken(token);
            if (result.getCode() == HttpCodeEnum.OK.getCode()) {
                // 认证通过
                User user = result.getData();
                // 追加请求头用户信息
                Consumer<HttpHeaders> httpHeaders = httpHeader -> {
                    httpHeader.set("loginName",user.getLoginName());
                    httpHeader.set("nickName",user.getNickName());
                    httpHeader.set("id",user.getId().toString());
                };
                ServerHttpRequest serverHttpRequest = exchange.getRequest().mutate().headers(httpHeaders).build();
                exchange.mutate().request(serverHttpRequest).build();
                return chain.filter(exchange);
            }

            // 认证过期、失败，均返回401
            ServerHttpResponse response = exchange.getResponse();
            byte[] bits = JSONObject.toJSONString(result).getBytes(StandardCharsets.UTF_8);
            DataBuffer buffer = response.bufferFactory().wrap(bits);
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            //指定编码，否则在浏览器中会中文乱码
            response.getHeaders().add("Content-Type", "text/plain;charset=UTF-8");
            return response.writeWith(Mono.just(buffer));
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }

    /**
     * 白名单
     * @return
     */
    private Set<String> getWhiteList(){
        Set<String> whiteList = new HashSet<>();
        whiteList.add("homepage");
        whiteList.add("book");
        return whiteList;
    }
}
