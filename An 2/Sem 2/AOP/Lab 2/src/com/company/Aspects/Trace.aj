package com.company.Aspects;

import java.util.logging.Logger;

import org.aspectj.lang.Signature;

public aspect Trace {
	private static Logger logger = Logger.getLogger("Students");
	
	pointcut traceMethods() : (execution(* *(..))&& !within(Trace));
	 
    before(): traceMethods(){
        Signature sig = thisJoinPointStaticPart.getSignature();
        logger.info(
                "[Entering:] "
                    + sig.getDeclaringTypeName() + "." + sig.getName()
        );
    }
}
