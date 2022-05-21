package hello.spring_core.common;


import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
@Scope(value="request", proxyMode = ScopedProxyMode.TARGET_CLASS) //http 요청 당 하나씩 생성되고 http 요청이 끝나는 순간 소멸된다.
public class MyLogger {

    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL){
        this.requestURL=requestURL;
    }

    public void log(String message){
        System.out.println("["+ uuid + "]" + "["+ requestURL +"] "+ message);
    }

    @PostConstruct
    public void init(){
        uuid = UUID.randomUUID().toString();
        System.out.println("["+ uuid + "] request scope init" + this);
    }

    @PreDestroy
    public void destroy(){
        System.out.println("["+ uuid + "] request scope destroy" + this);
    }
}
