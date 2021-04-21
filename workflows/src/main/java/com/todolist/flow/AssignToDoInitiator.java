package com.todolist.flow;
/* gowthamparamasivam created on 4/21/21 8:29 PM inside 
the package - com.todolist.states */

import net.corda.core.flows.FlowException;
import net.corda.core.flows.FlowLogic;
import net.corda.core.flows.InitiatingFlow;
import net.corda.core.flows.StartableByRPC;

@InitiatingFlow
@StartableByRPC
public class AssignToDoInitiator extends FlowLogic<Void> {
    @Override
    public Void call() throws FlowException {
        return null;
    }
}
