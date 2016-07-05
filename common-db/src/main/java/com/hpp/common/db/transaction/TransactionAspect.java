package com.hpp.common.db.transaction;

import com.hpp.common.db.ISqlExecutorFactory;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Created by huangpingping on 2016/2/22 9:17
 */

@Aspect
@Component
public class TransactionAspect {
    private static final Logger logger = Logger.getLogger(TransactionAspect.class);

    ThreadLocal<Transaction> transactionThreadLocal = new ThreadLocal<>();

    // TODO: 2016/7/1 嵌套的@Transactional的注解是不是会产生问题？
    @Around("@annotation(com.hpp.common.db.annotation.Transactional)")
    public Object listenTransactional(ProceedingJoinPoint pj) throws Throwable {
        Transaction transaction = transactionThreadLocal.get();
        if (transaction == null) {
            transaction = new DefaultTransaction();
            transactionThreadLocal.set(transaction);
        }

        logger.debug("i'm coming the transactional...");

        Object result;
        try {
            result = pj.proceed();
        } catch (Exception e) {
            transaction.rollBack();
            transactionThreadLocal.remove();
            throw e;
        }

        transaction.commit();
        transactionThreadLocal.remove();
        logger.debug("the transactional is completed...");
        return result;
    }


    @Around("execution(* com.hpp.common.db.ISqlExecutorFactory.createSqlExecutor(..))")
    public Object listenExecutor(ProceedingJoinPoint pj) throws Throwable {
        logger.debug("i'm coming the transactional...");
        ISqlExecutorFactory sqlExecutor = (ISqlExecutorFactory) pj.getThis();

        Transaction transaction = transactionThreadLocal.get();
        if (transaction != null) {
            // FIXME: 2016/7/1 唉，又在外面插入了个transaction，不好呀
            sqlExecutor.bindTransaction(transaction);
        }
        return pj.proceed();
    }
}
