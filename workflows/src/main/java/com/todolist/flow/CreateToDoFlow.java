package com.todolist.flow; 
/* gowthamparamasivam created on 4/10/21 9:15 PM inside 
the package - com.todolist.flow */

import com.todolist.contracts.DummyToDoCommand;
import com.todolist.states.ToDoState;
import net.corda.core.flows.*;
import net.corda.core.identity.Party;
import net.corda.core.node.ServiceHub;
import net.corda.core.transactions.SignedTransaction;
import net.corda.core.transactions.TransactionBuilder;

import java.util.Collections;

@StartableByRPC
public class CreateToDoFlow extends FlowLogic<Void> {
    private final String taskDescription;

    public CreateToDoFlow(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    @Override
    public Void call() throws FlowException {
        ServiceHub serviceHub = getServiceHub();
        Party notary = serviceHub.getNetworkMapCache().getNotaryIdentities().get(0);
        Party me = getOurIdentity();
        ToDoState toDoState = new ToDoState(me, me, taskDescription);
        System.out.println("Linear ID of state is " + toDoState.getLinearId());
        TransactionBuilder tb = new TransactionBuilder(notary).addOutputState(toDoState)
                .addCommand(new DummyToDoCommand(), me.getOwningKey());
        SignedTransaction signedTransaction = getServiceHub().signInitialTransaction(tb);
        subFlow(new FinalityFlow(signedTransaction, Collections.<FlowSession>emptySet()));
        return null;
    }
}
