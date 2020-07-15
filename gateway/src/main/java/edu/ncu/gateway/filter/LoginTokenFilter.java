package edu.ncu.gateway.filter;

import com.netflix.util.Pair;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import edu.ncu.gateway.config.AuthPathProperties;
import edu.ncu.gateway.utils.JwtUtil;
import edu.ncu.gateway.utils.PathUtil;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Component
public class LoginTokenFilter extends ZuulFilter {
    
    @Autowired
    private AuthPathProperties authPathProperties;
    
    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.SEND_RESPONSE_FILTER_ORDER-2;
    }

    @Override
    public boolean shouldFilter() {
        String uri = RequestContext.getCurrentContext().getRequest().getRequestURI();
        for(String pattern:authPathProperties.getLoginPaths()){
            if(PathUtil.isMatch(pattern,uri)) return true;
        }
        return false;
    }

    /**
     * 给响应加jwt token
     * @return
     * @throws ZuulException
     */
    @SneakyThrows
    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        //将token写入响应头
        if(ctx.getResponse().getStatus()==200){
            String username = null;
            String id = null;
            //获取原始响应头list
            List<Pair<String, String>> headers = ctx.getOriginResponseHeaders();
            for(Pair<String,String> pair:headers){
                if("username".equals(pair.first())){
                    username = pair.second();
                }
                if("eid".equals(pair.first())){
                    id = pair.second();
                }
            }

            ctx.addZuulResponseHeader("access-token", JwtUtil.genToken(id,username));
        }
        return null;
    }
}
