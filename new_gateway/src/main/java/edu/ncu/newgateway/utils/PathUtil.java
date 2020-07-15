package edu.ncu.newgateway.utils;

import org.springframework.util.AntPathMatcher;

public class PathUtil {

    private static final AntPathMatcher antPathMatcher = new AntPathMatcher();

    /**
     * 检查路径是否匹配配置当中的路径设置
     * @param pattern
     * @param path
     * @return
     */
    public static boolean isMatch(String pattern,String path){
        return antPathMatcher.match(pattern,path);
    }
}
