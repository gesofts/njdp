package com.njty.tydp.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.njty.tydp.authenticator.impl.AuthenticateChain;
import com.njty.tydp.authenticator.impl.LocalAuthenticator;
import com.njty.tydp.model.MsgModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@Order(1)
@WebFilter(urlPatterns = "/*")
public class AuthenticationFilter implements Filter {

    public static boolean isEnabled = true;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("------------------------------------filter is inited");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (!isEnabled) {
            chain.doFilter(request,response);
            return;
        }
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        //跨域设置
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        //这里填写你允许进行跨域的主机ip
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        //允许的访问方法
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE, PATCH");
        //Access-Control-Max-Age 用于 CORS 相关配置的缓存
        httpServletResponse.setHeader("Access-Control-Max-Age", "3600");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, token");

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();
        String ctx = httpRequest.getContextPath();
        String requestPath = requestURI.replace(ctx,"");
        System.out.println("-getList--------------------------------------------------" + requestPath);
        System.out.println("-getList--------------------------------------------------" + requestPath.startsWith("/port/getList"));
        System.out.println("-token--------------------------------------------------" + httpRequest.getHeader("token"));
        System.out.println("-getParameter--------------------------------------------------" +  httpRequest.getParameter("token"));
        if (requestPath.startsWith("/dangerZoneSpeed/getList")
                || requestPath.contains("/webjars")
                || requestPath.contains("/api")
                || requestPath.contains("/test/list")
                || requestPath.contains("/test")
                || requestPath.contains("/login")
                || requestPath.startsWith("/favicon")
                || requestPath.contains("/swagger-ui.html")
                || requestPath.contains("/swagger-resources")
                || requestPath.contains("/v2/api-docs"))
        {

            System.out.println("---------------------------------------------------");
            chain.doFilter(request,response);
        }
        else if(!requestPath.startsWith("/signin") && !requestPath.startsWith("/test")){
            //认证
            String token = httpRequest.getHeader("token");
            System.out.println(">>>>>>>>>>>>>>>>>>从 Header 获取 Token:" + token);
           if(StringUtils.isBlank(token)){
                token = httpRequest.getParameter("token");
                System.out.println(">>>>>>>>>>>>>>>>>>从 Request 获取 Token:" + token);
            }


            System.out.println(">>>>>>>>>>>>>>>>>>最终 获取 Token:" + token);

            AuthenticateChain authenticateChain = new AuthenticateChain();
            authenticateChain.addAuthenticator(new LocalAuthenticator());
                   // .addAuthenticator(new OauthAuthenticator());

            MsgModel msgModel = authenticateChain.authenticate(token);
            boolean isAuthenticated = msgModel.isSuccess();

            if(!isAuthenticated){
                PrintWriter out = response.getWriter();
                out.print(JSON.toJSONString(msgModel, SerializerFeature.WriteMapNullValue));

                out.flush();
                out.close();
            }else{
                chain.doFilter(request,response);
            }

        }else{
            chain.doFilter(request,response);
        }
    }

    @Override
    public void destroy() {

    }
}
