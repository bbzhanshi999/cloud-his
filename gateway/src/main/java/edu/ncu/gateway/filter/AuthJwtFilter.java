package edu.ncu.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.nimbusds.jose.JOSEException;
import edu.ncu.commons.model.EmployeeInfo;
import edu.ncu.gateway.config.AuthPathProperties;
import edu.ncu.gateway.utils.JwtException;
import edu.ncu.gateway.utils.JwtUtil;
import edu.ncu.gateway.utils.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;

/**
 * 对jwt token进行验证
 */
@Component
public class AuthJwtFilter extends ZuulFilter {

    @Autowired
    private AuthPathProperties authPathProperties;

    /**
     * 拦截的位置为请求放行之前
     * @return
     */
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    /**
     * 过滤器的顺序
     * @return
     */
    @Override
    public int filterOrder() {
        return 2;
    }

    /**
     * 判定是否要拦截
     * @return
     */
    @Override
    public boolean shouldFilter() {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        String requestURI = request.getRequestURI();
        List<String> authPaths = authPathProperties.getAuthPaths();
        for(String authPath:authPaths){
            if(PathUtil.isMatch(authPath,requestURI)) {
                for(String loginPath:authPathProperties.getLoginPaths()){
                    if(PathUtil.isMatch(loginPath,requestURI)){
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    /**
     * 具体的token验证规则
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();

        String token = request.getHeader("access-token");

        try {
            EmployeeInfo employeeInfo = JwtUtil.validToken(token);

            //设置放行
            context.setSendZuulResponse(true);
            //在请求头中带上id和username
            context.addZuulRequestHeader("eid",employeeInfo.getId());
            context.addZuulRequestHeader("username",employeeInfo.getUsername());

            context.addZuulResponseHeader("access-token",JwtUtil.genToken(employeeInfo.getId(),employeeInfo.getUsername()));
        } catch (ParseException | JwtException | JOSEException e) {
            //验证失败，就返回的失败的信息
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(401);
            context.setResponseBody("forbidden");
            e.printStackTrace();
        }

        return null;
    }
}
