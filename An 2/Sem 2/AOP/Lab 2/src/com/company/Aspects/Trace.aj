package com.company.Aspects;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.aspectj.lang.Signature;

public aspect Trace {
	pointcut traceMethods() : (execution(* *(..))&& !cflow(within(Trace)));
	 
    before(): traceMethods(){
        Signature sig = thisJoinPointStaticPart.getSignature();
        String line =""+ thisJoinPointStaticPart.getSourceLocation().getLine();
        String sourceName = thisJoinPointStaticPart.getSourceLocation().getWithinType().getCanonicalName();
        Logger.getLogger("Tracing").log(
                Level.INFO, 
                "Call from "
                    +  sourceName
                    +" line " +
                    line
                    +" to " +sig.getDeclaringTypeName() + "." + sig.getName()
        );
    }
}
