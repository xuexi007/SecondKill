package com.zhangyong.config.transaction;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.*;

import java.util.Collections;
import java.util.HashMap;

/**
 * <p>ClassName: 事务配置 </p>
 * <p>Description: TODO Spring事务整理 </p>
 * <p>Company: http://www.shopin.net</p>
 *
 * @author zhangyong@shopin.cn
 * @version 1.0.0
 * @Date 2018/7/4 15:14
 */
@Aspect
@Configuration
public class TransactionConfig {
    //事务超时时间待深究
    private static final int TX_METHOD_TIMEOUT = 500;
    private static final String AOP_POINTCUT_EXPRESSION = "execution (* com.zhangyong.service.*.*(..))";

    @Autowired
    private DataSourceTransactionManager transactionManager;

    @Bean(name = "txAdvice")
    public TransactionInterceptor txAdvice() {
        NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
        /*只读事务,不做更新操作*/
        RuleBasedTransactionAttribute readOnlyTx = new RuleBasedTransactionAttribute();
        readOnlyTx.setReadOnly(true);
        readOnlyTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_NOT_SUPPORTED);
        /*当前存在事务就是用当前事务,当前不存在事务就创建一个新的事务*/
        RuleBasedTransactionAttribute requiredTx = new RuleBasedTransactionAttribute();
        readOnlyTx.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
        readOnlyTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        //设置超时时间;
        readOnlyTx.setTimeout(TX_METHOD_TIMEOUT);
        HashMap<String, TransactionAttribute> txMap = new HashMap<>();
        txMap.put("get*", readOnlyTx);
        txMap.put("select*", readOnlyTx);
        txMap.put("insert*", requiredTx);
        txMap.put("save*", requiredTx);
        txMap.put("create*", requiredTx);
        txMap.put("modify*", requiredTx);
        txMap.put("update*", requiredTx);
        txMap.put("cancel*", requiredTx);
        txMap.put("cancle*", requiredTx);
        txMap.put("delete*", requiredTx);
        txMap.put("merge*", requiredTx);
        txMap.put("remove*", requiredTx);
        txMap.put("overtime*", requiredTx);
        txMap.put("manualCancle*", requiredTx);
        txMap.put("des*", requiredTx);
        txMap.put("reg*", requiredTx);
        txMap.put("confirm*", requiredTx);
        txMap.put("*", readOnlyTx);
        source.setNameMap(txMap);
        TransactionInterceptor txAdvice = new TransactionInterceptor(transactionManager, source);
        return txAdvice;
    }

    @Bean
    public Advisor txAdviceAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(AOP_POINTCUT_EXPRESSION);
        return new DefaultPointcutAdvisor(pointcut, txAdvice());
    }
}
