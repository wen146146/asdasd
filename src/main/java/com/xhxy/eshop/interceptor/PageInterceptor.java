package com.xhxy.eshop.interceptor;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Map;
import java.util.Properties;

/**
 * 分页拦截器 - 自动处理分页参数
 * 
 * 【什么是拦截器？】
 * 拦截器就像一个"中间人"，在方法执行前拦截请求，可以进行预处理
 * 
 * 【这个拦截器的作用】
 * 自动将 page（页码）和 size（每页数量）转换为 start（起始位置）和 limit（每页数量）
 * 
 * 【工作流程】
 * 1. MyBatis 执行查询时，拦截器自动拦截
 * 2. 检查参数中是否有 page 和 size
 * 3. 如果有，计算 start = (page-1) * size
 * 4. 将 start 和 limit 添加到参数中
 * 5. 继续执行原查询
 */

// 【注解说明】
// @Intercepts: 告诉 MyBatis 这是一个拦截器
// @Signature: 指定要拦截哪个类的哪个方法
@Intercepts({
    @Signature(
        type = Executor.class,      // 拦截 Executor 类（MyBatis 的执行器）
        method = "query",           // 拦截 query 方法（所有查询方法）
        args = {                    // query 方法的参数类型
            MappedStatement.class,  // SQL 语句信息
            Object.class,           // 参数对象
            RowBounds.class,        // 分页对象（MyBatis 自带的）
            ResultHandler.class     // 结果处理器
        }
    )
})
public class PageInterceptor implements Interceptor {
    
    // 【常量定义】
    private static final int DEFAULT_PAGE_SIZE = 6;              // 默认每页6条
    private static final String DEFAULT_PAGE_PARAM = "page";     // 页码参数名
    private static final String DEFAULT_SIZE_PARAM = "size";     // 每页数量参数名
    
    // 【成员变量】存储分页信息
    private int page;   // 当前页码
    private int size;   // 每页数量
    private int start;  // 起始位置（计算得出）
    
    /**
     * 【核心方法】拦截逻辑
     * 
     * 这个方法会在每次查询执行前自动调用
     * 
     * @param invocation 包含被拦截方法的所有信息
     * @return 查询结果
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // 【步骤1】获取被拦截方法的参数
        // args 数组包含了 query 方法的所有参数
        Object[] args = invocation.getArgs();
        
        // args[0]: MappedStatement - SQL 语句信息
        MappedStatement ms = (MappedStatement) args[0];
        
        // args[1]: Object - 参数对象（我们传入的参数）
        Object parameter = args[1];
        
        // 【新增】检查是否是需要分页的查询方法
        // 只有 findBypage 方法才进行分页处理
        String methodId = ms.getId();
        if (!methodId.contains("findBypage")) {
            // 不是分页查询方法，直接放行
            return invocation.proceed();
        }
        
        // 【步骤2】检查参数是否为 Map 类型
        // MyBatis 的 @Param 注解会将多个参数封装成 Map
        if (parameter != null && parameter.getClass().getName().contains("Map")) {
            // 将参数转换为 Map
            java.util.Map<?, ?> parameterMap = (java.util.Map<?, ?>) parameter;
            
            // 【步骤3】检查参数中是否同时存在 page 和 size
            Object pageObj = parameterMap.get(DEFAULT_PAGE_PARAM);
            Object sizeObj = parameterMap.get(DEFAULT_SIZE_PARAM);
            
            // 如果缺少任何一个分页参数，直接放行，不进行分页处理
            if (pageObj == null || sizeObj == null) {
                return invocation.proceed();
            }
            
            // 【步骤4】处理参数值
            // page 和 size 都存在，进行分页处理
            this.page = Integer.parseInt(pageObj.toString());
            this.size = Integer.parseInt(sizeObj.toString());
            
            // 【步骤5】计算起始位置
            // 公式：start = (page - 1) * size
            // 例如：第2页，每页6条 -> start = (2-1) * 6 = 6
            this.start = (this.page - 1) * this.size;
            
            // 【步骤6】创建新的参数 Map
            // 复制原有参数
            java.util.Map<String, Object> newParameterMap = new java.util.HashMap<>();
            newParameterMap.putAll((Map<? extends String, ?>) parameterMap);
            
            // 添加计算后的分页参数
            newParameterMap.put("start", start);   // 起始位置
            newParameterMap.put("limit", size);    // 每页数量
            
            // 【步骤7】替换原参数
            // 将新的参数 Map 设置回去
            args[1] = newParameterMap;
        }
        
        // 【步骤8】继续执行原查询
        // invocation.proceed() 会调用被拦截的原方法
        return invocation.proceed();
    }
    
    /**
     * 【插件包装方法】
     * 
     * 这个方法用于创建代理对象
     * MyBatis 会自动调用这个方法，将拦截器包装到目标对象上
     * 
     * @param target 目标对象（Executor）
     * @return 代理对象
     */
    @Override
    public Object plugin(Object target) {
        // 使用 MyBatis 提供的 Plugin.wrap 方法创建代理
        return Plugin.wrap(target, this);
    }
    
    /**
     * 【设置属性方法】
     * 
     * 用于读取配置文件中的属性
     * 可以在 mybatis-config.xml 中配置拦截器的属性
     * 
     * @param properties 配置属性
     */
    @Override
    public void setProperties(Properties properties) {
        // 这里可以读取配置文件中的自定义属性
        // 例如：<property name="defaultPageSize" value="10"/>
    }
    
    // 【Getter 和 Setter 方法】
    // 这些方法用于获取和设置分页信息
    
    public int getPage() {
        return page;
    }
    
    public void setPage(int page) {
        this.page = page;
    }
    
    public int getSize() {
        return size;
    }
    
    public void setSize(int size) {
        this.size = size;
    }
    
    public int getStart() {
        return start;
    }
    
    public void setStart(int start) {
        this.start = start;
    }
}
