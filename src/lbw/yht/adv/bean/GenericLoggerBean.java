package lbw.yht.adv.bean;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;

public class GenericLoggerBean {
	private static Logger logger = LogManager
			.getLogger(GenericLoggerBean.class);

	public Object invoke(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println(joinPoint.getTarget().getClass());
		logger.warn("Beginning method : " + joinPoint.getTarget().getClass()
				+ "." + joinPoint.getSignature().getName() + "()");
		long startTime = System.currentTimeMillis();
		try {
			Object result = joinPoint.proceed();
			return result;
		} catch (Exception e) {
			logger.warn(joinPoint.getTarget().getClass() + "."
					+ joinPoint.getSignature().getName() + "() invoke error");
			logger.warn("error info [" + e.getMessage() + "]");
		} finally {
			logger.warn("Ending method : " + joinPoint.getTarget().getClass()
					+ "." + joinPoint.getSignature().getName() + "()");
			logger.warn("Method invocation time : "
					+ (System.currentTimeMillis() - startTime) + " ms.");
		}
		return null;
	}
}
