package com.todolist.states; 
/* gowthamparamasivam created on 4/10/21 9:03 PM inside 
the package - com.todolist.states */

import com.template.contracts.TemplateContract;
import net.corda.core.contracts.BelongsToContract;
import net.corda.core.contracts.ContractState;
import net.corda.core.contracts.LinearState;
import net.corda.core.contracts.UniqueIdentifier;
import net.corda.core.identity.AbstractParty;
import net.corda.core.identity.Party;
import net.corda.core.serialization.ConstructorForDeserialization;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

@BelongsToContract(TemplateContract.class)
public class ToDoState implements ContractState, LinearState {

    private final Party assignedBy;
    private final Party assignedTo;
    private final String taskDescription;
    private final UniqueIdentifier linearId;

    public ToDoState(Party assignedBy, Party assignedTo, String taskDescription) {
        this.assignedBy = assignedBy;
        this.assignedTo = assignedTo;
        this.taskDescription = taskDescription;
        this.linearId = new UniqueIdentifier();
    }
    @ConstructorForDeserialization
    public ToDoState(Party assignedBy, Party assignedTo, String taskDescription, UniqueIdentifier linearId) {
        this.assignedBy = assignedBy;
        this.assignedTo = assignedTo;
        this.taskDescription = taskDescription;
        this.linearId = linearId;
    }

    @NotNull
    @Override
    public List<AbstractParty> getParticipants() {
        return Arrays.asList(assignedBy,assignedTo);
    }

    @NotNull
    @Override
    public UniqueIdentifier getLinearId() {
        return linearId;
    }

    public Party getAssignedBy() {
        return assignedBy;
    }

    public Party getAssignedTo() {
        return assignedTo;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public ToDoState assign(Party assignedTo){
        return new ToDoState(assignedBy,assignedTo,taskDescription,linearId);
    }
}
