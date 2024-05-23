package cn.maxs.system.filter;

import cn.maxs.common.entity.framework.RestResult;
import cn.maxs.common.enums.ResultStatus;
import cn.maxs.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * 本服务的统一异常处理
 * 2024/5/23
 *
 * @author Marcel.Maxs
 */
@Aspect
@Component
@Slf4j
public class ExceptionAop {

    /**
     * 注意：如果您修改了包名不是cn为最上层包路径，需要修改所有微服务模块中ExceptionAop类的@Around注解
     * 否则不会把异常包装为正常的RestResult结果返回，而是由Gateway中自定义的GlobalResponseFilter拦截到，并处理为普通的500错误提示信息
     */
    @Around("execution(public * cn..controller..*(..))")
    public RestResult around(ProceedingJoinPoint pjp){
        try{
            return pjp.proceed() instanceof RestResult ? (RestResult) pjp.proceed() : RestResult.ok(pjp.proceed());
        } catch (BusinessException e){
            Integer errorCode = e.getCode();
            String detailMessage = e.getMsg();
            log.error("【BEGIN】------------------------------------------------------------------------------");
            log.error("------【业务异常】：errorCode: 「{}」, detailMessage: 「{}」", errorCode, detailMessage);
            log.error("------【堆栈信息】：", e);
            log.error("【END】--------------------------------------------------------------------------------");
            return RestResult.fail(e.getCode(), e.getMsg());
        } catch (IllegalArgumentException e){
            log.error("【BEGIN】------------------------------------------------------------------------------");
            log.error("------【参数异常】：errorCode: 「{}」, detailMessage: 「{}」",
                    ResultStatus.ERR_455.getCode(),
                    ("".equals(e.getMessage()) ? ResultStatus.ERR_455.getMsg() : e.getMessage())
            );
            log.error("------【堆栈信息】：", e);
            log.error("【END】--------------------------------------------------------------------------------");
            return RestResult.fail(
                    ResultStatus.ERR_455.getCode(),
                    "".equals(e.getMessage()) ? ResultStatus.ERR_455.getMsg() : e.getMessage()
            );
        } catch (Throwable e){
            log.error("【BEGIN】------------------------------------------------------------------------------");
            log.error("------【全局异常】：errorCode: 「{}」, detailMessage: 「{}」",
                    ResultStatus.ERR_500.getCode(),
                    ResultStatus.ERR_500.getMsg()
            );
            log.error("------【堆栈信息】：", e);
            log.error("【END】--------------------------------------------------------------------------------");
            return RestResult.fail();
        }
    }
}
