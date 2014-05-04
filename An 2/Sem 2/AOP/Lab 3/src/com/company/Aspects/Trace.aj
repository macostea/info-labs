package com.company.Aspects;

import org.aspectj.lang.Signature;

import java.util.logging.Logger;


public aspect Trace {
	private static Logger logger = Logger.getLogger("Students");
	
	pointcut traceMethods() : (execution(* *(..))&& !within(Trace));
	 
    before(): traceMethods(){
        Signature sig = thisJoinPoint.getStaticPart().getSignature();
        logger.info(
                "[Entering:] "
                    + sig.getDeclaringTypeName() + "." + sig.getName()
        );
    }
}
